package org.josh.jcri;

import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;
import javax.annotation.ParametersAreNonnullByDefault;

/**Common operations and fields for all protocol methods and their parameter classes.
 This base class handles method calling by sending request and waiting response from browser through
 web socket in {@link #call(String, Class, BiFunction)}, and we use {@link java.util.concurrent.CompletableFuture}
 to hold user specified method as a future task.
 @author Joshua */
@ParametersAreNonnullByDefault public abstract class CommandBase implements CommonDomainType {
    /**Response string replied by browser.
     This field stores <pre>result</pre> field json formatted string if method executed success, or
     error message contains in <pre>error</pre> field in browser replied data. */
    private volatile JsonNode _response;
    /**Indicate whether method execution is success or not.
     If this field is <pre>true</pre>, {@link #_response} stores method's return data in json
     formatted string. Otherwise it stores error message replied by browser. */
    private volatile boolean _success;
    /**Latch for waiting browser's reply.*/
    private final CountDownLatch _latch = new CountDownLatch(1);

    private EventCenter _evt;
    private WebSocket _ws;

    /**Set event center and web socket instance.
     This method can only be called in domain methods. */
    public void setEventCenterAndSocket(EventCenter ec, WebSocket ws) {
        _evt = ec;  _ws = ws;
    }

    /**Check and convert parameter object into json string and send to browser.
     @param commandName name of this command to be called.
     @param resultMetaClass meta class of method's result type.
     @param failResultFactory factory method that will create a failed result instance with given an
        error message.
     @param exec executor instance to spawn task.
     @return future instance that waits browser's reply.
     @throws IllegalArgumentException if any of parameter is not valid. */
    protected <T extends ResultBase> CompletableFuture<T> callAsync(
        String commandName, Class<T> resultMetaClass, BiFunction<Integer, String, T> failResultFactory,
        Executor exec
    ) throws IllegalArgumentException {
        //! Check if all parameters are ok
        check();
        return CompletableFuture.supplyAsync(() -> doCalling(commandName, resultMetaClass, failResultFactory), exec);
    }

    /**Check and convert parameter object into json string and send to browser.
     This method uses executor instances in event center.
     @param commandName name of this command to be called.
     @param resultMetaClass meta class of method's result type.
     @param failResultFactory factory method that will create a failed result instance with given an
     error message.
     @return future instance that waits browser's reply.
     @throws IllegalArgumentException if any of parameter is not valid.
     @see #callAsync(String, Class, BiFunction, Executor) */
    protected <T extends ResultBase> CompletableFuture<T> callAsync(
        String commandName, Class<T> resultMetaClass, BiFunction<Integer, String, T> failResultFactory
    ) throws IllegalArgumentException {
        return callAsync(commandName, resultMetaClass, failResultFactory, _evt.getExecutor());
    }

    /**Check and convert parameter object into json string and send to browser.
     @param commandName name of this command to be called.
     @param resultMetaClass meta class of method's result type.
     @param failResultFactory factory method that will create a failed result instance with given an
     error message.
     @return future instance that waits browser's reply.
     @throws IllegalArgumentException if any of parameter is not valid. */
    protected <T extends ResultBase> CompletableFuture<T> call(
        String commandName, Class<T> resultMetaClass, BiFunction<Integer, String, T> failResultFactory
    ) {
        //! Check if all parameters are ok
        check();
        return CompletableFuture.completedFuture(doCalling(commandName, resultMetaClass, failResultFactory));
    }

    /**Let event center set browser's response.*/
    final void setResponse(boolean success, JsonNode response) {
        _success = success; _response = response;
    }
    /**Get IO waiting latch.*/
    final CountDownLatch getLatch() { return _latch; }

    /**Commit command calling to browser.
     @param commandName name of this command to be called.
     @param resultMetaClass meta class of method's result type.
     @param failResultFactory factory method that will create a failed result instance with given an
     error message.
     @return future instance that waits browser's reply. */
    private <T extends ResultBase> T doCalling(
        String commandName, Class<T> resultMetaClass, BiFunction<Integer, String, T> failResultFactory
    ) {
        //! Get next id from event center
        final long id = _evt.getNextMethodId();
        //! Generate raw json command string
        /// TODO: use TLS to reuse string builder instance
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("{\"id\":").append(id)
            .append(",\"method\":").append('"').append(commandName).append('"')
            .append(",\"params\":");
        toJson(strBuilder).append('}');

        //! Clear frame name to id and context table in EventCenter
        if (commandName.equals("Page.navigate"))    _evt.clearFrameTable();

        //! Send command
        if (!_evt.enqueueMethod(id, this))
            throw new IllegalStateException("Command id " + String.valueOf(id) + " already existed in waiting queue");
        _ws.send(strBuilder.toString());
        _evt.getLog().log(_evt.getLogLevel(), () -> "Send: " + strBuilder.toString());

        /// Wait response
        /// TODO: adjust waiting time in parameter
//        for (int retry = 0; retry < 30; ++retry) {
//            if (_latch.await(1, TimeUnit.SECONDS))  break;
//            if (_ws.isClosed())     throw new InterruptedException("Web socket closed.");
//        }
        try { _latch.await(); }
        catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        if (_ws.isClosed()) { Thread.currentThread().interrupt(); }

        /// Check success or fail
        final JsonNode resp = _response;
        if (_success) {
            try {
                final T result = EventCenter.deserializeJson(resp, resultMetaClass);
                result.setCommandId(id);
                return result;
            }
            catch (IOException e) {
                _evt.getLog().error(e);
                return null;
            }
        }
        else {
            final T result = failResultFactory.apply(resp.get("code").asInt(), resp.get("message").asText());
            result.setCommandId(id);
            return result;
        }
    }
}

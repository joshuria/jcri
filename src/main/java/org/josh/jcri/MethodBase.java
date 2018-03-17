package org.josh.jcri;

import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.function.Function;
import javax.annotation.ParametersAreNonnullByDefault;

/**Common operations and fields for all protocol methods and their parameter classes.
 This base class handles method calling by sending request and waiting response from browser through
 web socket in {@link #call(Class, Function)}, and we use {@link java.util.concurrent.CompletableFuture}
 to hold user specified method as a future task.
 @author Joshua */
@ParametersAreNonnullByDefault
abstract class MethodBase implements CommonDomainType {
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

    private final EventCenter _evt;
    private final WebSocket _ws;

    public MethodBase(EventCenter eventCenter, WebSocket webSocket) {
        _evt = eventCenter; _ws = webSocket;
    }

    /**Check and convert parameter object into json string and send to browser.
     @param resultMetaClass meta class of method's result type.
     @param failResultFactory factory method that will create a failed result instance with given an
        error message.
     @return future instance that waits browser's reply.
     @throws IllegalArgumentException if any of parameter is not valid. */
    protected <T extends ResultBase> CompletableFuture<T> call(
        Class<T> resultMetaClass, Function<String, T> failResultFactory
    ) throws IllegalArgumentException {
        return CompletableFuture.supplyAsync(() -> {
            //! Check if all parameters are ok
            check();
            //! Get next id from event center
            final long id = _evt.getNextMethodId();
            //! Generate raw json command string
            /// TODO: use TLS to reuse string builder instance
            StringBuilder strBuilder = new StringBuilder();
            strBuilder.append("{\"id\":").append(id).append(",\"params\":");
            toJson(strBuilder).append('}');
            //! Send command
            if (!_evt.enqueueMethod(id, this))
                throw new IllegalStateException("Command id " + String.valueOf(id) + " already existed in waiting queue");
            _ws.send(strBuilder.toString());
            System.out.println("Send command: " + strBuilder.toString());

            /// Wait response
            try { _latch.await(); }
            catch (InterruptedException e) { Thread.currentThread().interrupt(); }

            /// Check success or fail
            final boolean success = _success;
            final JsonNode resp = _response;
            if (_success) {
                try {
                    final T result = EventCenter.deserializeJson(resp, resultMetaClass);
                    result.setId(id);
                    return result;
                }
                catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
            else {
                final T result = failResultFactory.apply(resp.asText());
                result.setId(id);
                return result;
            }
        });
    }

    /**Let event center set browser's response.*/
    final void setResponse(boolean success, JsonNode response) {
        _success = success; _response = response;
    }
    /**Get IO waiting latch.*/
    final CountDownLatch getLatch() { return _latch; }
}

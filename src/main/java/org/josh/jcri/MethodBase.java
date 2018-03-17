package org.josh.jcri;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import javax.annotation.ParametersAreNonnullByDefault;

/**Common operations and fields for all protocol methods and their parameter classes.
 This base class handles method calling by sending request and waiting response from browser through
 web socket in {@link #call()}, and we use {@link java.util.concurrent.CompletableFuture} to hold
 user specified method as a future task.
 @author Joshua */
@ParametersAreNonnullByDefault
abstract class MethodBase implements CommonDomainType {
    /**Response string replied by browser.
     This field stores <pre>result</pre> field json formatted string if method executed success, or
     error message contains in <pre>error</pre> field in browser replied data. */
    private volatile String _response;
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
     @return future instance that waits browser's reply.
     @throws IllegalArgumentException if any of parameter is not valid. */
    public <T> CompletableFuture<T> call() throws IllegalArgumentException {
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
            if (!_evt.enqueueMethod(this))
                throw new IllegalStateException("Command id " + String.valueOf(id) + " already existed in waiting queue");
//        _ws.send(strBuilder.toString());
            System.out.println("Send command: " + strBuilder.toString());

            /// Wait response
            try { _latch.wait(); }
            catch (InterruptedException e) { Thread.currentThread().interrupt(); }

            /// Check success or fail
            final boolean success = _success;
            final String resp = _response;
            /// TODO
            return null;
        });
    }

    /**Let event center set browser's response.*/
    final void setResponse(boolean success, String response) {
        _success = success; _response = response;
    }
}

/*
class Page {
    private final WebSocket _ws;
    private final EvtHandler _evt;

    public Page(WebSocket, EvtHandler() { ... }

    public final NavigateCommand navigate() { return new NavigateCommand(); }

    ...

    class NavigateCommand {
        public final NavigateCommand url(String url) { _url = url; return this; }
        public final NavigateCommand setUrl(String url) { return this.url(url); }
        public final NavigateCommand referrer(String referrer) { _referrer = referrer; return this; }
        public final NavigateCommand optReferrer(String referrer) { return this.referrer(referrer); }
        public CompletableFuture<NavigateResult> perform() {
            return CompletableFuture.run(() -> {
                String cmd = serializeJson(this);
                Response response = _evt.newResponseBuilder(Type.Command).build();
                _ws.send(cmd);
                response.getLatch().wait();
                if (response.isSuccess()) {
                    return new Result<NavigateResult>(Json.deserializeJson(response.getData()));
                }
                else {
                    NavigateResult result = new NavigateResult(ResultBase.ofError(response.getData()));
                    if (_evt.raiseExceptionWhenError())
                        throw new CommandFailException(result);
                    else
                        return result;
                }
            });
        }
    }

    class NavigateResult extends ResultBase {
        private final String xxx;
        NavigateResult(@JsonProperty("xxx") String xxx) {
            super();
            this.xxx = xxx;
        }
        NavigateResult(ResultBase.Error error) {
            super(error);
            this.xxx = null;
        }
    }


    class ResultBase {
        private final boolean _success;
        private final String _errorMessage;

        public static class Error {
            private final String msg;
            public Error(String msg) { this.msg = msg; }
        }

        public static Error ofError(String errorMessage) { return new Error(errorMessage); }

        public BaseResultBuilder(Error error) { _success = false;   _errorMessage = error.msg; }
        public BaseResultBuilder() { _success = true;   _errorMessage = ""; }

        public final boolean isSuccess() { return _success; }
        public final String getErrorMessage() { return _errorMessage; }
    }
}
 */

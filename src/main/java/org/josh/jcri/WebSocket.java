package org.josh.jcri;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

/**Web socket implementation based on {@link WebSocketClient}.
 This class handles network connection and message sending/receiving with single server.
 @author Joshua
 @since 1.0 */
@ParametersAreNonnullByDefault
class WebSocket extends WebSocketClient {
    /**Message received handler.*/
    private Consumer<String> _messageHandler;
    /**Web socket error handler.*/
    private Consumer<Exception> _errorHandler;
    /**Connection closed handler which accept a close code parameter.
     @see <a href="https://developer.mozilla.org/en-US/docs/Web/API/CloseEvent">Mozilla CloseEvent</a>
     @see <a href="https://tools.ietf.org/html/rfc6455#page-64">IETF RFC6455</a>*/
    private Consumer<Integer> _closeHandler;

    /**Create web socket instance.
     @param host host name with protocol such as <pre>"ws://localhost"</pre>.
     @param port TCP port to be used.
     @param path requested path in host.
     @param timeout connection timeout in second.
     @param onMessageHandler callback function when message received.
     @param onErrorHandler callback function when web socket exception raised.
     @param onCloseHandler callback function when connection is closed.
     @throws URISyntaxException if given host, port, and path cannot be constructed a valid URI.*/
    WebSocket(String host, int port, String path, int timeout,
        @Nullable Consumer<String> onMessageHandler, @Nullable Consumer<Exception> onErrorHandler,
        @Nullable Consumer<Integer> onCloseHandler
    ) throws URISyntaxException {
        this(new URI(String.format("%s:%d%s", host, port, path)), timeout,
            onMessageHandler, onErrorHandler, onCloseHandler);
    }

    /**Create web socket instance.
     @param uri destination URI (such as <pre>"ws://localhost:1234/file"</pre>) to connect to.
     @param timeout connection timeout in second.
     @param onMessageHandler callback function when message received.
     @param onErrorHandler callback function when web socket exception raised.
     @param onCloseHandler callback function when connection is closed.*/
    WebSocket(URI uri, int timeout,
        @Nullable Consumer<String> onMessageHandler, @Nullable Consumer<Exception> onErrorHandler,
        @Nullable Consumer<Integer> onCloseHandler
    ) {
        super(uri);
        setConnectionLostTimeout(timeout);
        setTcpNoDelay(true);
        _messageHandler = onMessageHandler;
        _errorHandler = onErrorHandler;
        _closeHandler = onCloseHandler;
    }

    /**Get current message handler.*/
    public final Consumer<String> getMessageHandler() { return _messageHandler; }
    /**Get current exception handler.*/
    public final Consumer<Exception> getErrorHandler() { return _errorHandler; }
    /**Get current connection closed handler.*/
    public final Consumer<Integer> getCloseHandler() { return _closeHandler; }

    /**Set message handler.*/
    public final void setMessageHandler(Consumer<String> onMessageHandler) { _messageHandler = onMessageHandler; }
    /**Set error handler.*/
    public final void setErrorHandler(Consumer<Exception> onErrorHandler) { _errorHandler = onErrorHandler; }
    /**Set connection closed handler.*/
    public final void setCloseHandler(Consumer<Integer> onCloseHandler) { _closeHandler = onCloseHandler; }

    /**When connect accepted by server.*/
    @Override public void onOpen(ServerHandshake handshake) {
        System.out.println("Connection established: " + handshake.toString());
    }

    /**When connection is closed by server.*/
    @Override public void onClose(int code, String reason, boolean remote) {
        if (_closeHandler != null)  _closeHandler.accept(code);
    }

    /**When recv message from server.*/
    @Override public void onMessage(String msg) {
        if (_messageHandler != null)  _messageHandler.accept(msg);
    }

    /**When exception occur.*/
    @Override public void onError(Exception e) {
        if (_errorHandler != null)  _errorHandler.accept(e);
    }
}

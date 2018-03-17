package org.josh.jcri;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.Closeable;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

/**JCRI main class.
 TODO: provide onError, and onClose handler
 TODO: provide executor for executing event callback
 TODO: provide handy methods by combining a set of debug protocol methods
 @author Joshua */
@ParametersAreNonnullByDefault
public class JCRI implements Closeable {
    /**System property name of connection timeout in second.*/
    private static final String DefaultConnectionTimeoutPropertyName = "org.josh.jcri.connectionTimeout";
    /**Default connection timeout second.*/
    private static final int DefaultConnectionTimeout = 30;

    /**Web socket instance.*/
    private final WebSocket _ws;
    /**Event handler center.*/
    private final EventCenter _evt;
    /**_ioExecutor needs to shutdown when {@link #close()} or {@link #closeAsync()} is called. */
    private boolean _shutdownExecutorWhenClose = false;


    /**Get all available tabs' information in target browser which had debug interface bound on given
     host and port.
     @param host host that browser's debug interface is bound on.
     @param port browser's debug port.
     @return list of {@link TabInfo} contains all tabs info (and some other invisible "tabs" used by
        browser extension). Return empty list if fail to parse browser returned data.
     @throws IOException if fail to connect to browser by given host and port parameters.*/
    public static List<TabInfo> getAllTabInfo(String host, int port) throws IOException {
        //! Get raw json formatted all tabs' information
        //! See: https://stackoverflow.com/questions/4328711/read-url-to-string-in-few-lines-of-java-code
        String rawJson;
        try (Scanner scanner = new Scanner(
            new URL(String.format("http://%s:%d/json", host, port)).openStream(), StandardCharsets.UTF_8.toString()
        ).useDelimiter("\\A")) {
            rawJson = scanner.next();
        }
        catch (MalformedURLException e) {
            return Collections.emptyList();
        }

        //! Process raw json
        ObjectMapper objectMapper = new ObjectMapper();
        try { return objectMapper.readValue(rawJson, new TypeReference<List<TabInfo>>() {}); }
        catch (IOException e) {
            //! This exception means:
            //!  1. browser returns incomplete json string, so Jackson fail to parse it.
            //!  2. format of returned json string is changed.
            return Collections.emptyList();
        }
    }

    /**Get all available tabs' information in target browser which had debug interface bound on given
     localhost and port.
     @param port browser's debug port.
     @return list of {@link TabInfo} contains all tabs info (and some other invisible "tabs" used by
         browser extension). Return empty list if fail to parse browser returned data.
     @throws IOException if fail to connect to browser by given host and port parameters.*/
    public static List<TabInfo> getAllTabInfo(int port) throws IOException {
        return getAllTabInfo("localhost", port);
    }

    /**Create new connection to browser with specified URL and connection timeout.
     @param webSocketDebuggerUrl destination web socket debug url that is provided by browser.
     @param timeout web socket connection timeout in second. Use 0 or negative to indicate never
        timeout.
     @param ioExecutor executor for providing IO waiting threads. This executor will be shutdown
        when {@link #close()} or {@link #closeAsync()} is called. To change this behavior, call
        {@link #shutdownExecutorWhenClose(boolean)}. */
    public JCRI(URI webSocketDebuggerUrl, int timeout, @Nullable ExecutorService ioExecutor) {
        _evt = new EventCenter(ioExecutor);
        _ws = new WebSocket(webSocketDebuggerUrl, timeout, _evt::onMessage, this::onError, this::onClose);
    }

    /**Create new connection to browser with specified URL and default connection timeout.
     @param webSocketDebuggerUrl destination web socket debug url that is provided by browser.
     @param ioExecutor executor for providing IO waiting threads. This executor will be shutdown
        when {@link #close()} or {@link #closeAsync()} is called. To change this behavior, call
        {@link #shutdownExecutorWhenClose(boolean)}. */
    public JCRI(URI webSocketDebuggerUrl, @Nullable ExecutorService ioExecutor) {
        this(webSocketDebuggerUrl, DefaultConnectionTimeout, ioExecutor);
        try {
            final int timeout = Integer.parseInt(System.getProperty(DefaultConnectionTimeoutPropertyName));
            if (timeout != DefaultConnectionTimeout)
                _ws.setConnectionLostTimeout(timeout);
        }
        catch (IllegalArgumentException e) {    // also include NumberFormatException
            // Do nothing, just use default value
        }
    }

    /**Get if web socket connection is closed.*/
    public boolean isClosed() { return _ws.isClosed(); }
    /**Get if web socket connection is currently closing.*/
    public boolean isClosing() { return _ws.isClosing(); }
    /**Get if web socket connection is opened (connected).*/
    public boolean isConnected() { return _ws.isOpen(); }
    /**Set io executor needs to shutdown when {@link #close()} or {@link #closeAsync()} is called.
     If set to false, user needs to manually shutdown it or the application will hang on exit. */
    public void shutdownExecutorWhenClose(boolean enable) { _shutdownExecutorWhenClose = enable; }

    /**Connect to browser.
     @return boolean value indicates whether connection established or not.*/
    public CompletableFuture<Boolean> connect() {
        return CompletableFuture.supplyAsync(() -> {
            try { return _ws.connectBlocking(); }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }
        }, _evt.getExecutor());
    }

    /**Close connection.
     Note that calling this method will only close web socket connection, and the browser is still
     running.
     @apiNote this is a blocking operation. */
    @Override public void close() {
        if (_shutdownExecutorWhenClose)     _evt.getExecutor().shutdownNow();
        try { _ws.closeBlocking(); }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**Close connection asynchronously.
     Note that calling this method will only close web socket connection, and the browser is still
     running.
     @see #isClosing()
     @see #isClosed() */
    public void closeAsync() {
        if (_shutdownExecutorWhenClose)     _evt.getExecutor().shutdownNow();
        _ws.close();
    }

    /**On web socket error callback method.*/
    private void onError(Exception exception) {
        /// TODO: not implemented
    }

    /**On web socket is closed callback method.
     @param code web socket close status code. */
    private void onClose(int code) {
        /// TODO: not implemented
    }

    @Override public String toString() {
        /// TODO: not implemented
        return "";
    }
}

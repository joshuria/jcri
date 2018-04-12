package org.josh.jcri;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.josh.jcri.domain.Accessibility;
import org.josh.jcri.domain.Animation;
import org.josh.jcri.domain.ApplicationCache;
import org.josh.jcri.domain.Audits;
import org.josh.jcri.domain.Browser;
import org.josh.jcri.domain.CSS;
import org.josh.jcri.domain.CacheStorage;
import org.josh.jcri.domain.Console;
import org.josh.jcri.domain.DOM;
import org.josh.jcri.domain.DOMDebugger;
import org.josh.jcri.domain.DOMSnapshot;
import org.josh.jcri.domain.DOMStorage;
import org.josh.jcri.domain.Database;
import org.josh.jcri.domain.Debugger;
import org.josh.jcri.domain.DeviceOrientation;
import org.josh.jcri.domain.Emulation;
import org.josh.jcri.domain.HeadlessExperimental;
import org.josh.jcri.domain.HeapProfiler;
import org.josh.jcri.domain.IO;
import org.josh.jcri.domain.IndexedDB;
import org.josh.jcri.domain.Input;
import org.josh.jcri.domain.Inspector;
import org.josh.jcri.domain.LayerTree;
import org.josh.jcri.domain.Log;
import org.josh.jcri.domain.Memory;
import org.josh.jcri.domain.Network;
import org.josh.jcri.domain.Overlay;
import org.josh.jcri.domain.Page;
import org.josh.jcri.domain.Performance;
import org.josh.jcri.domain.Profiler;
import org.josh.jcri.domain.Runtime;
import org.josh.jcri.domain.Schema;
import org.josh.jcri.domain.Security;
import org.josh.jcri.domain.ServiceWorker;
import org.josh.jcri.domain.Storage;
import org.josh.jcri.domain.SystemInfo;
import org.josh.jcri.domain.Target;
import org.josh.jcri.domain.Tethering;
import org.josh.jcri.domain.Tracing;
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
    private boolean _shutdownExecutorWhenClose = true;

    public final Accessibility Accessibility;
    public final Animation Animation;
    public final ApplicationCache ApplicationCache;
    public final Audits Audits;
    public final Browser Browser;
    public final CacheStorage CacheStorage;
    public final Console Console;
    public final CSS CSS;
    public final Database Database;
    public final Debugger Debugger;
    public final DeviceOrientation DeviceOrientation;
    public final DOM DOM;
    public final DOMDebugger DOMDebugger;
    public final DOMSnapshot DOMSnapshot;
    public final DOMStorage DOMStorage;
    public final Emulation Emulation;
    public final HeadlessExperimental HeadlessExperimental;
    public final HeapProfiler HeapProfiler;
    public final IndexedDB IndexedDB;
    public final Input Input;
    public final Inspector Inspector;
    public final IO IO;
    public final LayerTree LayerTree;
    public final Log Log;
    public final Memory Memory;
    public final Network Network;
    public final Overlay Overlay;
    public final Page Page;
    public final Performance Performance;
    public final Profiler Profiler;
    public final Runtime Runtime;
    public final Schema Schema;
    public final Security Security;
    public final ServiceWorker ServiceWorker;
    public final Storage Storage;
    public final SystemInfo SystemInfo;
    public final Target Target;
    public final Tethering Tethering;
    public final Tracing Tracing;


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
     @param executor executor creates new {@link java.util.concurrent.CompletableFuture} for calling
         domain commands, waiting response, and executing method callbacks. */
    public JCRI(URI webSocketDebuggerUrl, int timeout, @Nullable ExecutorService executor) {
        _evt = new EventCenter(executor);
        _ws = new WebSocket(webSocketDebuggerUrl, timeout, _evt::onMessage, this::onError, this::onClose);
        this.Accessibility = new Accessibility(_evt, _ws);
        this.Animation = new Animation(_evt, _ws);
        this.ApplicationCache = new ApplicationCache(_evt, _ws);
        this.Audits = new Audits(_evt, _ws);
        this.Browser = new Browser(_evt, _ws);
        this.CacheStorage = new CacheStorage(_evt, _ws);
        this.Console = new Console(_evt, _ws);
        this.CSS = new CSS(_evt, _ws);
        this.Database = new Database(_evt, _ws);
        this.Debugger = new Debugger(_evt, _ws);
        this.DeviceOrientation = new DeviceOrientation(_evt, _ws);
        this.DOM = new DOM(_evt, _ws);
        this.DOMDebugger = new DOMDebugger(_evt, _ws);
        this.DOMSnapshot = new DOMSnapshot(_evt, _ws);
        this.DOMStorage = new DOMStorage(_evt, _ws);
        this.Emulation = new Emulation(_evt, _ws);
        this.HeadlessExperimental = new HeadlessExperimental(_evt, _ws);
        this.HeapProfiler = new HeapProfiler(_evt, _ws);
        this.IndexedDB = new IndexedDB(_evt, _ws);
        this.Input = new Input(_evt, _ws);
        this.Inspector = new Inspector(_evt, _ws);
        this.IO = new IO(_evt, _ws);
        this.LayerTree = new LayerTree(_evt, _ws);
        this.Log = new Log(_evt, _ws);
        this.Memory = new Memory(_evt, _ws);
        this.Network = new Network(_evt, _ws);
        this.Overlay = new Overlay(_evt, _ws);
        this.Page = new Page(_evt, _ws);
        this.Performance = new Performance(_evt, _ws);
        this.Profiler = new Profiler(_evt, _ws);
        this.Runtime = new Runtime(_evt, _ws);
        this.Schema = new Schema(_evt, _ws);
        this.Security = new Security(_evt, _ws);
        this.ServiceWorker = new ServiceWorker(_evt, _ws);
        this.Storage = new Storage(_evt, _ws);
        this.SystemInfo = new SystemInfo(_evt, _ws);
        this.Target = new Target(_evt, _ws);
        this.Tethering = new Tethering(_evt, _ws);
        this.Tracing = new Tracing(_evt, _ws);
    }

    /**Create new connection to browser with specified URL and default connection timeout.
     @param webSocketDebuggerUrl destination web socket debug url that is provided by browser.
     @param executor executor creates new {@link java.util.concurrent.CompletableFuture} for calling
         domain commands, waiting response, and executing method callbacks. */
    public JCRI(URI webSocketDebuggerUrl, @Nullable ExecutorService executor) {
        this(webSocketDebuggerUrl, DefaultConnectionTimeout, executor);
        try {
            final int timeout = Integer.parseInt(System.getProperty(DefaultConnectionTimeoutPropertyName));
            if (timeout != DefaultConnectionTimeout)    _ws.setConnectionLostTimeout(timeout);
        }
        catch (NumberFormatException e) {
            // Do nothing, just use default value
        }
    }
    /**Create new connection to browser with specified URL and default connection timeout.
     @param webSocketDebuggerUrl destination web socket debug url that is provided by browser. */
    public JCRI(URI webSocketDebuggerUrl) {
        this(webSocketDebuggerUrl, DefaultConnectionTimeout, null);
        try {
            final int timeout = Integer.parseInt(System.getProperty(DefaultConnectionTimeoutPropertyName));
            if (timeout != DefaultConnectionTimeout)    _ws.setConnectionLostTimeout(timeout);
        }
        catch (NumberFormatException e) {
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
        if (_shutdownExecutorWhenClose) {
            _evt.getExecutor().shutdownNow();
        }
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
        if (_shutdownExecutorWhenClose) {
            _evt.getExecutor().shutdownNow();
        }
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

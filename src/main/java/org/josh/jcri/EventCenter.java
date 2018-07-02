package org.josh.jcri;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.apache.logging.log4j.Logger;
import org.josh.jcri.domain.Page;
import org.josh.jcri.domain.Runtime;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

/**Central command and event manager.
 @author Joshua */
@ParametersAreNonnullByDefault public class EventCenter {
    /**Default command and method execution thread pool size property name.*/
    private static final String DefaultThreadPoolSizeName = "org.josh.jcri.defaultThreadPoolSize";
    /**Default command and method execution thread pool size.*/
    private static final int DefaultThreadPoolSize = 32;
    /**Standalone json object mapper.*/
    private static final ObjectMapper _om = new ObjectMapper();

    /**Next command Id.*/
    private final AtomicLong _nextMethodId = new AtomicLong(0);
    /**Stores all protocol methods that are waiting browser response.*/
    private final Map<Long, CommandBase> _methodWaitingTable = new ConcurrentHashMap<>();
    /**Stores all bound event handlers.*/
    private final Map<String, Consumer<JsonNode>> _eventHandlerTable = new ConcurrentHashMap<>();
    /**Tracking table frame name -> frame ID.*/
    private final Map<String, String> _frameNameIdTable = new ConcurrentHashMap<>();
    /**Tracking table frame ID -> execution context ID.*/
    private final Map<String, Runtime.ExecutionContextId> _frameConntextIdTable = new ConcurrentHashMap<>();
    /**Command and method callback executor.*/
    private final ExecutorService _executor;
    /**JCRI log instance.*/
    private final Logger _log;

    /**Create new event center instance.
     @param executor executor creates new {@link java.util.concurrent.CompletableFuture} for calling
        domain commands, waiting response, and executing method callbacks. */
    EventCenter(@Nullable ExecutorService executor, Logger log) {
        _log = log;
        if (executor == null) {
            int commandThread = DefaultThreadPoolSize;
            try {
                final int count = Integer.parseInt(System.getProperty(DefaultThreadPoolSizeName));
                if (count > 0) commandThread = count;
            }
            catch (NullPointerException | NumberFormatException e) {
                // Do nothing, just use default value
            }
            _log.debug("Create EventCenter with thread pool size " + commandThread);
            _executor = Executors.newFixedThreadPool(commandThread);
        }
        else
            _executor = executor;
    }

    /**Get next unique method Id. */
    final long getNextMethodId() { return _nextMethodId.getAndIncrement(); }

    /**Get executor for running commands and callbacks.*/
    final ExecutorService getExecutor() { return _executor; }

    /**Get logger instance.*/
    public Logger getLog() { return _log; }

    /**Push a method into response waiting queue.
     If given method's Id is already existed in internal waiting queue, the given method will NOT
     be put into queue. */
    final boolean enqueueMethod(long commandId, CommandBase method) {
        return _methodWaitingTable.putIfAbsent(commandId, method) == null;
    }

    /**Pop out method from waiting queue.
     @param id method's id
     @return method instance in waiting queue with specified id. null if id not found. */
    final CommandBase popMethod(long id) { return _methodWaitingTable.remove(id); }

    /**Register domain's event callback handler.
     @param callbackWrap callback method wrapped by json converting. Null to clear.
     @see DomainBase#registerEventCallback(String, Consumer) */
    final void registerEventCallback(String eventName, @Nullable Consumer<JsonNode> callbackWrap) {
        if (callbackWrap != null)   _eventHandlerTable.put(eventName, callbackWrap);
        else                        _eventHandlerTable.remove(eventName);
    }

    /**Get execution context of given frame by name.
     @return Context id if found, null if not.*/
    final @Nullable Runtime.ExecutionContextId getFrameContextId(String frameName) {
        final String frameId = _frameNameIdTable.get(frameName);
        if (frameId == null)    return null;
        return _frameConntextIdTable.get(frameId);
    }

    /**Clear frame name to id and to context table.*/
    final void clearFrameTable() {
        _frameNameIdTable.clear();
        _frameConntextIdTable.clear();
        _log.trace("Frame table cleared");
    }

    /**Deserialize json string to object.
     @throws IOException if given json string is invalid. */
    static <T> T deserializeJson(String data) throws IOException {
        return deserializeJson(data, _om.reader().forType(new TypeReference<T>(){}));
    }

    /**Deserialize json string to object with a given object reader instance.
     @throws IOException if given json string is invalid. */
    static <T> T deserializeJson(String data, ObjectReader reader) throws IOException {
        return reader.readValue(data);
    }

    /**Deserialize json node instance to object with a given object reader instance.
     @throws IOException if given json node is invalid. */
    public static <T> T deserializeJson(JsonNode node, Class<T> meta) throws IOException {
        return _om.treeToValue(node, meta);
    }
    /**Deserialize json node instance to object with a given object reader instance.
     @throws IOException if given json node is invalid. */
    static <T> T deserializeJson(JsonNode node, Class<T> meta, ObjectReader reader) throws IOException {
        return reader.treeToValue(node, meta);
    }

    /**Serialize object to json string.
     @throws JsonProcessingException if fail to serialize given object. */
    static <T> String serializeJson(T object) throws JsonProcessingException {
        return serializeJson(object, _om.writer().forType(new TypeReference<T>() {}));
    }

    /**Serialize object to json string with given object writer instance.
     @throws JsonProcessingException if fail to serialize given object. */
    static <T> String serializeJson(T object, ObjectWriter writer) throws JsonProcessingException {
        return writer.writeValueAsString(object);
    }

    /**On receiving message from browser callback method.*/
    @SuppressWarnings("unchecked")
    void onMessage(String msg) {
        try {
            final JsonNode node = _om.readTree(msg);
            _log.trace("Recv: " + msg);
            //! Check if is response of method
            if (node.has("id")) {
                final CommandBase method = _methodWaitingTable.remove(node.get("id").asLong());
                if (method != null) {
                    if (node.has("result"))
                        method.setResponse(true, node.get("result"));
                    else
                        method.setResponse(false, node.get("error"));
                    method.getLatch().countDown();
                }
            }
            else if (node.has("method")) {
                final String method = node.get("method").asText();
                //! Process frame name, id, execution context change
                if (method.equals("Page.frameNavigated")) {
                    try {
                        Page.FrameNavigatedEventParameter param = EventCenter.deserializeJson(
                            node.get("params"), Page.FrameNavigatedEventParameter.class);
                        if (param.getFrame().getName() == null) param.getFrame().optName("");
                        _frameNameIdTable.put(param.getFrame().getName(), param.getFrame().getId());
                        _log.trace("  Frame name -> id mapping: ``%s'' -> %s", param.getFrame().getName(), param.getFrame().getId());
                    }
                    catch (IOException e) { _log.error(e); }
                }
                else if (method.equals("Runtime.executionContextCreated")) {
                    try {
                        Runtime.ExecutionContextCreatedEventParameter param = EventCenter.deserializeJson(
                            node.get("params"), Runtime.ExecutionContextCreatedEventParameter.class);
                        JsonNode auxData = _om.valueToTree(param.getContext().getAuxData());
                        if (auxData != null) {
                            final String frameId = auxData.get("frameId").asText();
                            _frameConntextIdTable.put(frameId, param.getContext().getId());
                            _log.trace("  Frame id -> context mapping: %s -> %d", frameId, param.getContext().getId().value());
                        }
                    }
                    catch (IOException|IllegalArgumentException e) { _log.error(e); }
                }

                //! Invoke callback function
                final Consumer<JsonNode> callback = _eventHandlerTable.get(method);
                if (callback != null)
                    _executor.submit(() -> {
                        try { callback.accept(node.get("params")); }
                        catch (Exception e) { _log.error(e); }
                    });
            }
            else {
                //! Browser response unexpected message?!
            }
        }
        catch (IOException e) { _log.error(e); }
    }
} // ! class EventCenter

package org.josh.jcri;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.josh.jcri.domain.Page;
import org.josh.jcri.domain.Runtime;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;
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
    /**Tracking table frame ID -> frame data (id, parent id, name, execution context id).*/
    private final Map<String, FrameData> _frameIdTable = new ConcurrentHashMap<>();
    /**Command and method callback executor.*/
    private final ExecutorService _executor;
    /**JCRI log instance.*/
    private final Logger _log;
    /**JCRI log level.*/
    private final Level _logLevel;

    /**Create new event center instance.
     @param executor executor creates new {@link java.util.concurrent.CompletableFuture} for calling
        domain commands, waiting response, and executing method callbacks. */
    EventCenter(@Nullable ExecutorService executor, Logger log, Level logLevel) {
        _log = log;
        _logLevel = logLevel;
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
    /**Get logger level.*/
    public Level getLogLevel() { return _logLevel; }

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
     <p>Note that web page can have multiple frames have the same name, so this method should be
     used only when the given frame name is identical in current web page.</p>
     @return First present (by time) frame's context id if found, null if not.*/
    final @Nullable Runtime.ExecutionContextId getFrameContextId(String ... frameName) {
        //! Query root frame
        Optional<FrameData> parent = _frameIdTable.values().stream()
                .filter(f -> f.parentId.equals(f.id)).findFirst();
        if (!parent.isPresent())     return null;

        //! Query by name
        for (String name: frameName) {
            final FrameData frame = parent.get();
            parent = _frameIdTable.values().stream()
                .filter(f-> f.name.equals(name) && f.parentId.equals(frame.id))
                .findFirst();
            if (!parent.isPresent())     return null;
        }
        return parent.get().contextId;
    }

    /**Clear frame name to id and to context table.*/
    final void clearFrameTable() { _frameIdTable.clear(); }

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
            _log.log(_logLevel, "Recv: " + msg);
            //! Check if is response of method
            if (node.has("id")) {
                final long id = node.get("id").asLong();
                final CommandBase method = _methodWaitingTable.remove(id);
                if (method != null) {
                    if (node.has("result"))
                        method.setResponse(true, node.get("result"));
                    else
                        method.setResponse(false, node.get("error"));
                    method.getLatch().countDown();
                    //! Print log if exception raised
                    if (node.hasNonNull("exceptionDetails")) {
                        final JsonNode detail = node.get("exceptionDetails");
                        _log.debug("[JCRI] Command %d response with exception:", id);
                        _log.debug("[JCRI]    Script Id: " + detail.get("scriptId").asLong());
                        _log.debug("[JCRI]    Line/Column: L%d, %d", detail.get("lineNumber").asLong(), detail.get("columnNumber").asLong());
                        _log.debug("[JCRI]    Exception Id: " + detail.get("exceptionId").asLong());
                        _log.debug("[JCRI]    text: " + detail.get("text").asText());
                        logStackTrace(detail.get("stackTrace"));
                    }
                }
            }
            else if (node.has("method")) {
                final String method = node.get("method").asText();
                //! Process frame name, id, execution context change
                if (method.equals("Page.frameNavigated")) {
                    try {
                        Page.FrameNavigatedEventParameter param = EventCenter.deserializeJson(
                            node.get("params"), Page.FrameNavigatedEventParameter.class);
                        final FrameData frameData = new FrameData(param.getFrame());
                        _frameIdTable.put(param.getFrame().getId(), frameData);
                        _log.trace("[JCRI] Frame name -> id mapping: ``%s'' -> %s, parent: %s",
                            frameData.name, frameData.id, frameData.parentId);
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
                            final FrameData frameData = _frameIdTable.get(frameId);
                            if (frameData != null) {
                                frameData.setContextId(param.getContext().getId());
                                _log.trace("[JCRI] Frame id -> context mapping: ``%s''(%s) -> %d",
                                    frameData.name, frameData.id, param.getContext().getId().value());
                            }
                            else {
                                _log.trace("[JCRI] Execution context %d's frame %s not found",
                                    param.getContext().getId().value(), frameId);
                            }
                        }
                    }
                    catch (IOException|IllegalArgumentException e) { _log.error(e); }
                }
                else if (method.equals("Runtime.executionContextDestroyed")) {
                    final Runtime.ExecutionContextDestroyedEventParameter param = EventCenter.deserializeJson(
                        node.get("params"), Runtime.ExecutionContextDestroyedEventParameter.class);
                    if (param.getExecutionContextId() != null) {
                        //! Removed exist frame with execution context id equals to destroyed
                        _frameIdTable.entrySet().removeIf(e ->
                            e.getValue().contextId.value().equals(param.getExecutionContextId().value()));
                    }
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
    } // ! EventCenter.onMessage

    /**Log console log or exception stack trace.*/
    private void logStackTrace(@Nullable JsonNode stackTrace) {
        if (stackTrace == null || !stackTrace.hasNonNull("callFrames")) {
            _log.debug("[JCRI]  -- StackTrace Not Available --");
            return;
        }
        _log.debug("[JCRI]  -- StackTrace --");
        for (JsonNode frame: stackTrace.get("callFrames"))
            _log.debug("[JCRI]    <%s> %s: L%d, %d\n%s", frame.get("scriptId").asText(), frame.get("functionName").asText(),
                frame.get("lineNumber").asLong(), frame.get("columnNumber").asLong(), frame.get("url").asText());
        _log.debug("[JCRI]  -- End of StackTrace --");
    }


    /**Internal class for storing frame's id, parent id, and name.*/
    @ParametersAreNonnullByDefault private static class FrameData {
        private final String id;
        private String name;
        private String parentId;
        private Runtime.ExecutionContextId contextId;

        private FrameData(Page.Frame frame) {
            id = frame.getId();
            name = frame.getName() != null ? frame.getName() : "";
            parentId = frame.getParentId() != null ? frame.getParentId() : frame.getId();
        }
        private FrameData(String id, Runtime.ExecutionContextId cxtId) {
            this.id = id;
            name = "";
            parentId = id;
            contextId = cxtId;
        }

        private void setName(String name) { this.name = name; }
        private void setParentId(String parentId) { this.parentId = parentId; }
        private void setContextId(Runtime.ExecutionContextId cxtId) { contextId = cxtId; }
    }
} // ! class EventCenter

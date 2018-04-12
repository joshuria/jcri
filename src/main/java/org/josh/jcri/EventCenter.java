package org.josh.jcri;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.io.IOException;
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
    /**Command and method callback executor.*/
    private final ExecutorService _executor;

    /**Create new event center instance.
     @param executor executor creates new {@link java.util.concurrent.CompletableFuture} for calling
        domain commands, waiting response, and executing method callbacks. */
    EventCenter(@Nullable ExecutorService executor) {
        if (executor == null) {
            int commandThread = DefaultThreadPoolSize;
            try {
                final int count = Integer.parseInt(System.getProperty(DefaultThreadPoolSizeName));
                if (count > 0) commandThread = count;
            }
            catch (NullPointerException | NumberFormatException e) {
                // Do nothing, just use default value
            }
            _executor = Executors.newFixedThreadPool(commandThread);
        }
        else
            _executor = executor;
    }

    /**Get next unique method Id. */
    final long getNextMethodId() { return _nextMethodId.getAndIncrement(); }

    /**Get executor for running commands and callbacks.*/
    final ExecutorService getExecutor() { return _executor; }

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
     @see DomainBase#registerEventCallback(String, Consumer) */
    final void registerEventCallback(String eventName, Consumer<JsonNode> callbackWrap) {
        _eventHandlerTable.put(eventName, callbackWrap);
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
            //System.out.println("Recv: " + msg);
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
                final Consumer<JsonNode> callback = _eventHandlerTable.get(node.get("method").asText());
                if (callback != null)
                    _executor.submit(() -> {
                        try { callback.accept(node.get("params")); }
                        catch (Exception e) { e.printStackTrace(); }
                    });
            }
            else {
                //! Browser response unexpected message?!
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
} // ! class EventCenter

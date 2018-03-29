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
@ParametersAreNonnullByDefault
public class EventCenter {
    /**System property name of default command waiting executor thread count.*/
    private static final String DefaultCommandWaitingThreadPropertyName = "org.josh.jcri.defaultCommandWaitingThread";
    /**Default command waiting thread count value.*/
    private static final int DefaultCommandWaitingThread = 1;
    /**System property name of default method callback execution thread count.*/
    private static final String DefaultMethodExecutionThreadPropertyName = "org.josh.jcri.defaultCommandWaitingThread";
    /**Default method callback execution thread count value.*/
    private static final int DefaultMethodExecutionThread = 1;
    /**Standalone json object mapper.*/
    private static final ObjectMapper _om = new ObjectMapper();

    /**Next command Id.*/
    private final AtomicLong _nextMethodId = new AtomicLong(0);
    /**Stores all protocol methods that are waiting browser response.*/
    private final Map<Long, CommandBase> _methodWaitingTable = new ConcurrentHashMap<>();
    /**Stores all bound event handlers.*/
    private final Map<String, Consumer<JsonNode>> _eventHandlerTable = new ConcurrentHashMap<>();
    /**Executor for submitting new domain method callback functions.*/
    private final ExecutorService _methodExecutionExecutor;
    /**Executor for waiting domain command response.*/
    private final ExecutorService _commandWaitingExecutor;

    /**Create new event center instance.
     @param commandExecutor executor creates new {@link java.util.concurrent.CompletableFuture} for
         calling domain methods and waiting their response.
     @param methodExecutor executor executes registered callback functions of domain methods. */
    EventCenter(@Nullable ExecutorService commandExecutor, @Nullable ExecutorService methodExecutor) {
        if (commandExecutor == null) {
            int commandThread = DefaultCommandWaitingThread;
            try {
                final int count = Integer.parseInt(System.getProperty(DefaultCommandWaitingThreadPropertyName));
                if (count > 0) commandThread = count;
            }
            catch (NullPointerException | NumberFormatException e) {
                // Do nothing, just use default value
            }
            _commandWaitingExecutor = Executors.newFixedThreadPool(commandThread);
        }
        else
            _commandWaitingExecutor = commandExecutor;
        if (methodExecutor == null) {
            int methodThread = DefaultMethodExecutionThread;
            try {
                final int count = Integer.parseInt(System.getProperty(DefaultMethodExecutionThreadPropertyName));
                if (count > 0)  methodThread = count;
            }
            catch (NullPointerException | NumberFormatException e) {
                // Do nothing, just use default value
            }
            _methodExecutionExecutor = Executors.newFixedThreadPool(methodThread);
        }
        else
            _methodExecutionExecutor = methodExecutor;
    }

    /**Get next unique method Id. */
    final long getNextMethodId() { return _nextMethodId.getAndIncrement(); }

    /**Get domain command waiting executor instance.*/
    final ExecutorService getCommandExecutor() { return _commandWaitingExecutor; }
    /**Get domain method callback function execution executor instance.*/
    final ExecutorService getMethodExecutor() { return _methodExecutionExecutor; }

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
                if (callback != null)    _methodExecutionExecutor.submit(() -> {
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

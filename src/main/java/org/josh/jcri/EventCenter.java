package org.josh.jcri;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

/**Central command and event manager.
 @author Joshua */
@ParametersAreNonnullByDefault
class EventCenter {
    /**System property name of default IO thread count.*/
    private static final String DefaultIOThreadPropertyName = "org.josh.jcri.defaultIOThread";
    /**Default IO thread count value.*/
    private static final int DefaultIOThread = 1;
    /**Standalone json object mapper.*/
    private static final ObjectMapper _om = new ObjectMapper();

    /**Next command Id.*/
    private final AtomicLong _nextMethodId = new AtomicLong(0);
    /**Stores all protocol methods that are waiting browser response.*/
    private final Map<Long, MethodBase> _methodWaitingTable = new ConcurrentHashMap<>();
    /**Executor for waiting browser's response of sent commends.*/
    private final ExecutorService _ioExecutor;

    /**Create new event center instance.
     @param ioExecutor executor to create new {@link java.util.concurrent.CompletableFuture} instance.*/
    EventCenter(@Nullable ExecutorService ioExecutor) {
        if (ioExecutor == null) {
            int ioThread = DefaultIOThread;
            try {
                final int count = Integer.parseInt(System.getProperty(DefaultIOThreadPropertyName));
                if (count > 0) ioThread = count;
            }
            catch (IllegalArgumentException e) {  // also include NumberFormatException
                // Do nothing, just use default value
            }
            _ioExecutor = Executors.newFixedThreadPool(ioThread);
        }
        else
            _ioExecutor = ioExecutor;
    }

    /**Get next unique method Id. */
    final long getNextMethodId() { return _nextMethodId.getAndIncrement(); }

    /**Get executor instance for submit new task.*/
    final ExecutorService getExecutor() { return _ioExecutor; }

    /**Push a method into response waiting queue.
     If given method's Id is already existed in internal waiting queue, the given method will NOT
     be put into queue. */
    final boolean enqueueMethod(MethodBase method) {
        //return _methodWaitingTable.putIfAbsent(method.getId(), method) == null;
        return false;
    }

    /**Pop out method from waiting queue.
     @param id method's id
     @return method instance in waiting queue with specified id. null if id not found. */
    final MethodBase popMethod(long id) { return _methodWaitingTable.remove(id); }

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
    void onMessage(String msg) {
        /// TODO: not implemented
    }
}

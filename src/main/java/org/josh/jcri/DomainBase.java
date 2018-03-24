package org.josh.jcri;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.function.Consumer;

/**Base class of all domains.
 @author Joshua */
public class DomainBase {
    /**Event center instance.*/
    protected final EventCenter _evt;
    /**WebSocket instance.*/
    protected final WebSocket _ws;

    protected DomainBase(EventCenter eventCenter, WebSocket webSocket) {
        _evt = eventCenter; _ws = webSocket;
    }

    /**Register event callback function.
     This method is for preventing public {@link EventCenter#registerEventCallback(String, Consumer)}*/
    protected void registerEventCallback(String eventName, Consumer<JsonNode> callbackWrap) {
        _evt.registerEventCallback(eventName, callbackWrap);
    }
}

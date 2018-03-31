package org.josh.jcri;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.function.Consumer;
import javax.annotation.ParametersAreNonnullByDefault;

/**Base class of all domains.
 @author Joshua */
@ParametersAreNonnullByDefault
public class DomainBase {
    /**Escape all quote character and return a new string instance.
     @see <a href="http://hg.openjdk.java.net/jdk9/jdk9/jdk/rev/db30d5179fe7">JDK 9's patch.</a> */
    public static String escapeQuote(String data) {
        int quoteIndex = data.indexOf('"');
        if (quoteIndex < 0)    return data;

        final char[] value = data.toCharArray();
        final char[] replValue = {'\\', '"'};
        int newLenHint = value.length + 1;
        if (newLenHint < 0)     throw new OutOfMemoryError();

        final StringBuilder sb = new StringBuilder(newLenHint);
        int i = 0;
        do {
            sb.append(value, i, quoteIndex - i).append(replValue);
            i = quoteIndex + 1;
        } while (quoteIndex < value.length && (quoteIndex = data.indexOf('"', quoteIndex + 1)) > 0);
        return sb.append(value, i, value.length - i).toString();
    }

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

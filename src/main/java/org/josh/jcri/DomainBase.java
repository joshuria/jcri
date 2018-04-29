package org.josh.jcri;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.function.Consumer;
import javax.annotation.ParametersAreNonnullByDefault;

/**Base class of all domains.
 @author Joshua */
@ParametersAreNonnullByDefault public class DomainBase {
    /**Escape all json invalid characters and return a new string instance.
     @see #_escape(String, String, String)   */
    public static String escapeJson(String data) {
        return _escape(_escape(data, "\\", "\\\\"), "\"", "\\\"");
    }

    /**Escape all quote and back-slash characters and return a new string instance.
     @see <a href="http://hg.openjdk.java.net/jdk9/jdk9/jdk/rev/db30d5179fe7">JDK 9's patch.</a> */
    private static String _escape(String data, String target, String replacement) {
        String starget = target.toString();
        String srepl = replacement.toString();
        int j = data.indexOf(starget);
        if (j < 0) {
            return data;
        }
        int targLen = starget.length();
        int targLen1 = Math.max(targLen, 1);
        final char[] value = data.toCharArray();
        final char[] replValue = srepl.toCharArray();
        int newLenHint = value.length - targLen + replValue.length;
        if (newLenHint < 0) {
            throw new OutOfMemoryError();
        }
        StringBuilder sb = new StringBuilder(newLenHint);
        int i = 0;
        do {
            sb.append(value, i, j - i)
                .append(replValue);
            i = j + targLen;
        } while (j < value.length && (j = data.indexOf(starget, j + targLen1)) > 0);

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

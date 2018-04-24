package org.josh.jcri.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.josh.jcri.CommandBase;
import org.josh.jcri.CommonDomainType;
import org.josh.jcri.EventCenter;
import org.josh.jcri.ResultBase;
import org.josh.jcri.WebSocket;
import org.josh.jcri.DomainBase;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Consumer;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.Nullable;

/**This domain is deprecated - use Runtime or Log instead.
<p>From: js_protocol.json</p>
<p>Protocol version: 1.3</p>
 @see Runtime
 @author Joshua*/
 @Deprecated
@ParametersAreNonnullByDefault public class Console extends DomainBase {
    public Console(EventCenter evt, WebSocket ws) { super(evt, ws); }

    /**Console message.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ConsoleMessage implements CommonDomainType {
        /**Message source.*/
        @ParametersAreNonnullByDefault public enum Source implements CommonDomainType {
            Xml("xml"),
            Javascript("javascript"),
            Network("network"),
            Console_api("console-api"),
            Storage("storage"),
            Appcache("appcache"),
            Rendering("rendering"),
            Security("security"),
            Other("other"),
            Deprecation("deprecation"),
            Worker("worker");

            private final String _value;
            /**Convert string representation to type.
             @throws IllegalArgumentException if given value cannot convert to enum type. */
            @JsonCreator public static Source of(String value) {
                return Enum.valueOf(Source.class, value.substring(0, 1).toUpperCase() + value.substring(1));
            }
            Source(String value) { _value = value; }
            /**Check if parameter fields of method are all valid. */
            @Override public void check() throws IllegalArgumentException { /* Need not check */ }
            /**Convert method parameter object into json string and append into string builder.
             @return string builder instance that is given in parameter (for chaining coding style use.) */
            @Override public StringBuilder toJson(StringBuilder strBuilder) { return strBuilder.append('"').append(_value).append('"'); }
            @Override public String toString() { return "\"" + _value + "\""; }
        }
        private Source source;
        /**Message severity.*/
        @ParametersAreNonnullByDefault public enum Level implements CommonDomainType {
            Log("log"),
            Warning("warning"),
            Error("error"),
            Debug("debug"),
            Info("info");

            private final String _value;
            /**Convert string representation to type.
             @throws IllegalArgumentException if given value cannot convert to enum type. */
            @JsonCreator public static Level of(String value) {
                return Enum.valueOf(Level.class, value.substring(0, 1).toUpperCase() + value.substring(1));
            }
            Level(String value) { _value = value; }
            /**Check if parameter fields of method are all valid. */
            @Override public void check() throws IllegalArgumentException { /* Need not check */ }
            /**Convert method parameter object into json string and append into string builder.
             @return string builder instance that is given in parameter (for chaining coding style use.) */
            @Override public StringBuilder toJson(StringBuilder strBuilder) { return strBuilder.append('"').append(_value).append('"'); }
            @Override public String toString() { return "\"" + _value + "\""; }
        }
        private Level level;
        /**Message text.*/
        private String text;
        /**URL of the message origin.
        <em>Optional.</em>*/
        private String url;
        /**Line number in the resource that generated this message (1-based).
        <em>Optional.</em>*/
        private Integer line;
        /**Column number in the resource that generated this message (1-based).
        <em>Optional.</em>*/
        private Integer column;
        public final ConsoleMessage source(Source source) { this.source = source; return this; }
        public final ConsoleMessage setSource(Source source) { return source(source); }
        public final Source source() { return source; }
        public final Source getSource() { return source(); }
        public final ConsoleMessage level(Level level) { this.level = level; return this; }
        public final ConsoleMessage setLevel(Level level) { return level(level); }
        public final Level level() { return level; }
        public final Level getLevel() { return level(); }
        public final ConsoleMessage text(String text) { this.text = text; return this; }
        public final ConsoleMessage setText(String text) { return text(text); }
        public final String text() { return text; }
        public final String getText() { return text(); }
        public final ConsoleMessage url(@Nullable String url) { this.url = url; return this; }
        public final ConsoleMessage optUrl(@Nullable String url) { return url(url); }
        public final String url() { return url; }
        public final String getUrl() { return url(); }
        public final ConsoleMessage line(@Nullable Integer line) { this.line = line; return this; }
        public final ConsoleMessage optLine(@Nullable Integer line) { return line(line); }
        public final Integer line() { return line; }
        public final Integer getLine() { return line(); }
        public final ConsoleMessage column(@Nullable Integer column) { this.column = column; return this; }
        public final ConsoleMessage optColumn(@Nullable Integer column) { return column(column); }
        public final Integer column() { return column; }
        public final Integer getColumn() { return column(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (source == null) throw new IllegalArgumentException("Console.ConsoleMessage.source is necessary field.");
            if (level == null) throw new IllegalArgumentException("Console.ConsoleMessage.level is necessary field.");
            if (text == null) throw new IllegalArgumentException("Console.ConsoleMessage.text is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"source\":").append(source);
            strBuilder.append(",\"level\":").append(level);
            strBuilder.append(",\"text\":").append('"').append(DomainBase.escapeQuote(text)).append('"');
            if (url != null) strBuilder.append(",\"url\":").append('"').append(DomainBase.escapeQuote(url)).append('"');
            if (line != null) strBuilder.append(",\"line\":").append(line);
            if (column != null) strBuilder.append(",\"column\":").append(column);
            strBuilder.append('}');
            return strBuilder;
        }
        public ConsoleMessage() {}
        public ConsoleMessage(
            @JsonProperty("source")Source source,
            @JsonProperty("level")Level level,
            @JsonProperty("text")String text,
            @Nullable @JsonProperty("url")String url,
            @Nullable @JsonProperty("line")Integer line,
            @Nullable @JsonProperty("column")Integer column
        ) {
            this.source = source;
            this.level = level;
            this.text = text;
            this.url = url;
            this.line = line;
            this.column = column;
        }
    }
    /**Does nothing.*/
    public ClearMessagesParameter clearMessages() { final ClearMessagesParameter v = new ClearMessagesParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of clearMessages.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ClearMessagesParameter extends CommandBase {
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append('}');
            return strBuilder;
        }
        public ClearMessagesParameter() {}
        public CompletableFuture<ClearMessagesResult> call() {
            return super.call("Console.clearMessages", ClearMessagesResult.class,
                (code, msg)->new ClearMessagesResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<ClearMessagesResult> call(Executor exec) {
            return super.call("Console.clearMessages", ClearMessagesResult.class,
                (code, msg)->new ClearMessagesResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of clearMessages.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ClearMessagesResult extends ResultBase {
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append('}');
            return strBuilder;
        }
        public ClearMessagesResult() { super(); }
        public ClearMessagesResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Disables console domain, prevents further console messages from being reported to the client.*/
    public DisableParameter disable() { final DisableParameter v = new DisableParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of disable.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class DisableParameter extends CommandBase {
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append('}');
            return strBuilder;
        }
        public DisableParameter() {}
        public CompletableFuture<DisableResult> call() {
            return super.call("Console.disable", DisableResult.class,
                (code, msg)->new DisableResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<DisableResult> call(Executor exec) {
            return super.call("Console.disable", DisableResult.class,
                (code, msg)->new DisableResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of disable.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class DisableResult extends ResultBase {
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append('}');
            return strBuilder;
        }
        public DisableResult() { super(); }
        public DisableResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Enables console domain, sends the messages collected so far to the client by means of the
`messageAdded` notification.*/
    public EnableParameter enable() { final EnableParameter v = new EnableParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of enable.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class EnableParameter extends CommandBase {
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append('}');
            return strBuilder;
        }
        public EnableParameter() {}
        public CompletableFuture<EnableResult> call() {
            return super.call("Console.enable", EnableResult.class,
                (code, msg)->new EnableResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<EnableResult> call(Executor exec) {
            return super.call("Console.enable", EnableResult.class,
                (code, msg)->new EnableResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of enable.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class EnableResult extends ResultBase {
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append('}');
            return strBuilder;
        }
        public EnableResult() { super(); }
        public EnableResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Event parameter of Console.messageAdded.
     @see #onMessageAdded*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class MessageAddedEventParameter implements CommonDomainType {
        /**Console message that has been added.*/
        private final ConsoleMessage message;
        public final ConsoleMessage message() { return message; }
        public final ConsoleMessage getMessage() { return message(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            message.toJson(strBuilder.append("\"message\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        MessageAddedEventParameter(
            @JsonProperty("message")ConsoleMessage message
        ) {
            this.message = message;
        }
    }
    /**Issued when new console message is added.
     @see MessageAddedEventParameter*/
    public void onMessageAdded(Consumer<MessageAddedEventParameter> callback) {
        registerEventCallback("Console.messageAdded", node -> {
            MessageAddedEventParameter param;
            try { param = EventCenter.deserializeJson(node, MessageAddedEventParameter.class); }
            catch (IOException e) { e.printStackTrace(); return; }
            callback.accept(param);
        });
    }
}

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

/**Provides access to log entries.
<p>From: browser_protocol.json</p>
<p>Protocol version: 1.3</p>
 @see Runtime
 @see Network
 @author Joshua*/
@ParametersAreNonnullByDefault public class Log extends DomainBase {
    public Log(EventCenter evt, WebSocket ws) { super(evt, ws); }

    /**Log entry.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class LogEntry implements CommonDomainType {
        /**Log entry source.*/
        @ParametersAreNonnullByDefault public enum Source implements CommonDomainType {
            Xml("xml"),
            Javascript("javascript"),
            Network("network"),
            Storage("storage"),
            Appcache("appcache"),
            Rendering("rendering"),
            Security("security"),
            Deprecation("deprecation"),
            Worker("worker"),
            Violation("violation"),
            Intervention("intervention"),
            Recommendation("recommendation"),
            Other("other");

            private final String _value;
            private static final Map<String, Source> _Lookup;
            static {
                Map<String, Source> m = new HashMap<>();
                for(Source v: values()) m.put(v.toString(), v);
                _Lookup = Collections.unmodifiableMap(m);
            }
            /**Convert string representation to type.
             @throws IllegalArgumentException if given value cannot convert to enum type. */
            @JsonCreator public static Source of(String value) {
                Source v = _Lookup.get(value.toLowerCase());
                return v != null ? v : Enum.valueOf(Source.class, value);
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
        /**Log entry severity.*/
        @ParametersAreNonnullByDefault public enum Level implements CommonDomainType {
            Verbose("verbose"),
            Info("info"),
            Warning("warning"),
            Error("error");

            private final String _value;
            private static final Map<String, Level> _Lookup;
            static {
                Map<String, Level> m = new HashMap<>();
                for(Level v: values()) m.put(v.toString(), v);
                _Lookup = Collections.unmodifiableMap(m);
            }
            /**Convert string representation to type.
             @throws IllegalArgumentException if given value cannot convert to enum type. */
            @JsonCreator public static Level of(String value) {
                Level v = _Lookup.get(value.toLowerCase());
                return v != null ? v : Enum.valueOf(Level.class, value);
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
        /**Logged text.*/
        private String text;
        /**Timestamp when this entry was added.*/
        private Runtime.Timestamp timestamp;
        /**URL of the resource if known.
        <em>Optional.</em>*/
        private String url;
        /**Line number in the resource.
        <em>Optional.</em>*/
        private Integer lineNumber;
        /**JavaScript stack trace.
        <em>Optional.</em>*/
        private Runtime.StackTrace stackTrace;
        /**Identifier of the network request associated with this entry.
        <em>Optional.</em>*/
        private Network.RequestId networkRequestId;
        /**Identifier of the worker associated with this entry.
        <em>Optional.</em>*/
        private String workerId;
        /**Call arguments.
        <em>Optional.</em>*/
        private List<Runtime.RemoteObject> args;
        public final LogEntry source(Source source) { this.source = source; return this; }
        public final LogEntry setSource(Source source) { return source(source); }
        public final Source source() { return source; }
        public final Source getSource() { return source(); }
        public final LogEntry level(Level level) { this.level = level; return this; }
        public final LogEntry setLevel(Level level) { return level(level); }
        public final Level level() { return level; }
        public final Level getLevel() { return level(); }
        public final LogEntry text(String text) { this.text = text; return this; }
        public final LogEntry setText(String text) { return text(text); }
        public final String text() { return text; }
        public final String getText() { return text(); }
        public final LogEntry timestamp(Runtime.Timestamp timestamp) { this.timestamp = timestamp; return this; }
        public final LogEntry setTimestamp(Runtime.Timestamp timestamp) { return timestamp(timestamp); }
        public final Runtime.Timestamp timestamp() { return timestamp; }
        public final Runtime.Timestamp getTimestamp() { return timestamp(); }
        public final LogEntry url(@Nullable String url) { this.url = url; return this; }
        public final LogEntry optUrl(@Nullable String url) { return url(url); }
        public final String url() { return url; }
        public final String getUrl() { return url(); }
        public final LogEntry lineNumber(@Nullable Integer lineNumber) { this.lineNumber = lineNumber; return this; }
        public final LogEntry optLineNumber(@Nullable Integer lineNumber) { return lineNumber(lineNumber); }
        public final Integer lineNumber() { return lineNumber; }
        public final Integer getLineNumber() { return lineNumber(); }
        public final LogEntry stackTrace(@Nullable Runtime.StackTrace stackTrace) { this.stackTrace = stackTrace; return this; }
        public final LogEntry optStackTrace(@Nullable Runtime.StackTrace stackTrace) { return stackTrace(stackTrace); }
        public final Runtime.StackTrace stackTrace() { return stackTrace; }
        public final Runtime.StackTrace getStackTrace() { return stackTrace(); }
        public final LogEntry networkRequestId(@Nullable Network.RequestId networkRequestId) { this.networkRequestId = networkRequestId; return this; }
        public final LogEntry optNetworkRequestId(@Nullable Network.RequestId networkRequestId) { return networkRequestId(networkRequestId); }
        public final Network.RequestId networkRequestId() { return networkRequestId; }
        public final Network.RequestId getNetworkRequestId() { return networkRequestId(); }
        public final LogEntry workerId(@Nullable String workerId) { this.workerId = workerId; return this; }
        public final LogEntry optWorkerId(@Nullable String workerId) { return workerId(workerId); }
        public final String workerId() { return workerId; }
        public final String getWorkerId() { return workerId(); }
        public final LogEntry args(@Nullable List<Runtime.RemoteObject> args) { this.args = args; return this; }
        public final LogEntry optArgs(@Nullable List<Runtime.RemoteObject> args) { return args(args); }
        public final List<Runtime.RemoteObject> args() { return args; }
        public final List<Runtime.RemoteObject> getArgs() { return args(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (source == null) throw new IllegalArgumentException("Log.LogEntry.source is necessary field.");
            if (level == null) throw new IllegalArgumentException("Log.LogEntry.level is necessary field.");
            if (text == null) throw new IllegalArgumentException("Log.LogEntry.text is necessary field.");
            if (timestamp == null) throw new IllegalArgumentException("Log.LogEntry.timestamp is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"source\":").append(source);
            strBuilder.append(",\"level\":").append(level);
            strBuilder.append(",\"text\":").append('"').append(DomainBase.escapeQuote(text)).append('"');
            timestamp.toJson(strBuilder.append(",\"timestamp\":"));
            if (url != null) strBuilder.append(",\"url\":").append('"').append(DomainBase.escapeQuote(url)).append('"');
            if (lineNumber != null) strBuilder.append(",\"lineNumber\":").append(lineNumber);
            if (stackTrace != null) stackTrace.toJson(strBuilder.append(",\"stackTrace\":"));
            if (networkRequestId != null) networkRequestId.toJson(strBuilder.append(",\"networkRequestId\":"));
            if (workerId != null) strBuilder.append(",\"workerId\":").append('"').append(DomainBase.escapeQuote(workerId)).append('"');
            if (args != null) {
                strBuilder.append(",\"args\":[");
                args.get(0).toJson(strBuilder);
                for (int i = 1; i < args.size(); ++i)
                    args.get(i).toJson(strBuilder.append(','));
                strBuilder.append(']');
            }
            strBuilder.append('}');
            return strBuilder;
        }
        public LogEntry() {}
        public LogEntry(
            @JsonProperty("source")Source source,
            @JsonProperty("level")Level level,
            @JsonProperty("text")String text,
            @JsonProperty("timestamp")Runtime.Timestamp timestamp,
            @Nullable @JsonProperty("url")String url,
            @Nullable @JsonProperty("lineNumber")Integer lineNumber,
            @Nullable @JsonProperty("stackTrace")Runtime.StackTrace stackTrace,
            @Nullable @JsonProperty("networkRequestId")Network.RequestId networkRequestId,
            @Nullable @JsonProperty("workerId")String workerId,
            @Nullable @JsonProperty("args")List<Runtime.RemoteObject> args
        ) {
            this.source = source;
            this.level = level;
            this.text = text;
            this.timestamp = timestamp;
            this.url = url;
            this.lineNumber = lineNumber;
            this.stackTrace = stackTrace;
            this.networkRequestId = networkRequestId;
            this.workerId = workerId;
            this.args = args;
        }
    }

    /**Violation configuration setting.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ViolationSetting implements CommonDomainType {
        /**Violation type.*/
        @ParametersAreNonnullByDefault public enum Name implements CommonDomainType {
            LongTask("longTask"),
            LongLayout("longLayout"),
            BlockedEvent("blockedEvent"),
            BlockedParser("blockedParser"),
            DiscouragedAPIUse("discouragedAPIUse"),
            Handler("handler"),
            RecurringHandler("recurringHandler");

            private final String _value;
            private static final Map<String, Name> _Lookup;
            static {
                Map<String, Name> m = new HashMap<>();
                for(Name v: values()) m.put(v.toString(), v);
                _Lookup = Collections.unmodifiableMap(m);
            }
            /**Convert string representation to type.
             @throws IllegalArgumentException if given value cannot convert to enum type. */
            @JsonCreator public static Name of(String value) {
                Name v = _Lookup.get(value.toLowerCase());
                return v != null ? v : Enum.valueOf(Name.class, value);
            }
            Name(String value) { _value = value; }
            /**Check if parameter fields of method are all valid. */
            @Override public void check() throws IllegalArgumentException { /* Need not check */ }
            /**Convert method parameter object into json string and append into string builder.
             @return string builder instance that is given in parameter (for chaining coding style use.) */
            @Override public StringBuilder toJson(StringBuilder strBuilder) { return strBuilder.append('"').append(_value).append('"'); }
            @Override public String toString() { return "\"" + _value + "\""; }
        }
        private Name name;
        /**Time threshold to trigger upon.*/
        private Double threshold;
        public final ViolationSetting name(Name name) { this.name = name; return this; }
        public final ViolationSetting setName(Name name) { return name(name); }
        public final Name name() { return name; }
        public final Name getName() { return name(); }
        public final ViolationSetting threshold(Double threshold) { this.threshold = threshold; return this; }
        public final ViolationSetting setThreshold(Double threshold) { return threshold(threshold); }
        public final Double threshold() { return threshold; }
        public final Double getThreshold() { return threshold(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (name == null) throw new IllegalArgumentException("Log.ViolationSetting.name is necessary field.");
            if (threshold == null) throw new IllegalArgumentException("Log.ViolationSetting.threshold is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"name\":").append(name);
            strBuilder.append(",\"threshold\":").append(threshold);
            strBuilder.append('}');
            return strBuilder;
        }
        public ViolationSetting() {}
        public ViolationSetting(
            @JsonProperty("name")Name name,
            @JsonProperty("threshold")Double threshold
        ) {
            this.name = name;
            this.threshold = threshold;
        }
    }
    /**Clears the log.*/
    public ClearParameter clear() { final ClearParameter v = new ClearParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of clear.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ClearParameter extends CommandBase {
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
        public ClearParameter() {}
        public CompletableFuture<ClearResult> call() {
            return super.call("Log.clear", ClearResult.class,
                (code, msg)->new ClearResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<ClearResult> call(Executor exec) {
            return super.call("Log.clear", ClearResult.class,
                (code, msg)->new ClearResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of clear.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ClearResult extends ResultBase {
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
        public ClearResult() { super(); }
        public ClearResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Disables log domain, prevents further log entries from being reported to the client.*/
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
            return super.call("Log.disable", DisableResult.class,
                (code, msg)->new DisableResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<DisableResult> call(Executor exec) {
            return super.call("Log.disable", DisableResult.class,
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
    /**Enables log domain, sends the entries collected so far to the client by means of the
`entryAdded` notification.*/
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
            return super.call("Log.enable", EnableResult.class,
                (code, msg)->new EnableResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<EnableResult> call(Executor exec) {
            return super.call("Log.enable", EnableResult.class,
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
    /**start violation reporting.*/
    public StartViolationsReportParameter startViolationsReport() { final StartViolationsReportParameter v = new StartViolationsReportParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of startViolationsReport.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class StartViolationsReportParameter extends CommandBase {
        /**Configuration for violations.*/
        private List<ViolationSetting> config;
        public final StartViolationsReportParameter config(List<ViolationSetting> config) { this.config = config; return this; }
        public final StartViolationsReportParameter setConfig(List<ViolationSetting> config) { return config(config); }
        public final List<ViolationSetting> config() { return config; }
        public final List<ViolationSetting> getConfig() { return config(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (config == null) throw new IllegalArgumentException("Log.StartViolationsReportParameter.config is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
                        strBuilder.append("\"config\":[");
            config.get(0).toJson(strBuilder);
            for (int i = 1; i < config.size(); ++i)
                config.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public StartViolationsReportParameter() {}
        public StartViolationsReportParameter(
            @JsonProperty("config")List<ViolationSetting> config
        ) {
            this();
            this.config = config;
        }
        public CompletableFuture<StartViolationsReportResult> call() {
            return super.call("Log.startViolationsReport", StartViolationsReportResult.class,
                (code, msg)->new StartViolationsReportResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<StartViolationsReportResult> call(Executor exec) {
            return super.call("Log.startViolationsReport", StartViolationsReportResult.class,
                (code, msg)->new StartViolationsReportResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of startViolationsReport.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class StartViolationsReportResult extends ResultBase {
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
        public StartViolationsReportResult() { super(); }
        public StartViolationsReportResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Stop violation reporting.*/
    public StopViolationsReportParameter stopViolationsReport() { final StopViolationsReportParameter v = new StopViolationsReportParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of stopViolationsReport.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class StopViolationsReportParameter extends CommandBase {
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
        public StopViolationsReportParameter() {}
        public CompletableFuture<StopViolationsReportResult> call() {
            return super.call("Log.stopViolationsReport", StopViolationsReportResult.class,
                (code, msg)->new StopViolationsReportResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<StopViolationsReportResult> call(Executor exec) {
            return super.call("Log.stopViolationsReport", StopViolationsReportResult.class,
                (code, msg)->new StopViolationsReportResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of stopViolationsReport.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class StopViolationsReportResult extends ResultBase {
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
        public StopViolationsReportResult() { super(); }
        public StopViolationsReportResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Event parameter of Log.entryAdded.
     @see #onEntryAdded*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class EntryAddedEventParameter implements CommonDomainType {
        /**The entry.*/
        private final LogEntry entry;
        public final LogEntry entry() { return entry; }
        public final LogEntry getEntry() { return entry(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            entry.toJson(strBuilder.append("\"entry\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        EntryAddedEventParameter(
            @JsonProperty("entry")LogEntry entry
        ) {
            this.entry = entry;
        }
    }
    /**Issued when new message was logged.
     @see EntryAddedEventParameter*/
    public void onEntryAdded(Consumer<EntryAddedEventParameter> callback) {
        registerEventCallback("Log.entryAdded", node -> {
            EntryAddedEventParameter param;
            try { param = EventCenter.deserializeJson(node, EntryAddedEventParameter.class); }
            catch (IOException e) { e.printStackTrace(); return; }
            callback.accept(param);
        });
    }
}

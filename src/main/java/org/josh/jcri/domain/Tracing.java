package org.josh.jcri.domain;

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

/**&lt;No document in protocol.&gt;
<p>From: browser_protocol.json</p>
<p>Protocol version: 1.3</p>
<p><strong>Experimental.</strong></p>
 @see IO
 @author Joshua*/
@ParametersAreNonnullByDefault public class Tracing extends DomainBase {
    public Tracing(EventCenter evt, WebSocket ws) { super(evt, ws); }

    /**Configuration for memory dump. Used only when "memory-infra" category is enabled.*/
    @ParametersAreNonnullByDefault public static class MemoryDumpConfig implements CommonDomainType {
        private Object _value;
        public MemoryDumpConfig() {}
        public MemoryDumpConfig(Object value) { _value = value; }
        public final MemoryDumpConfig value(Object value) { _value = value; return this; }
        public final Object value() { return _value; }
        public final MemoryDumpConfig setValue(Object value) { return value(value); }
        public final Object getValue() { return value(); }
        @Override public String toString() { return String.valueOf(_value); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (_value == null) throw new IllegalArgumentException("Tracing.MemoryDumpConfig.value is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            return strBuilder.append(_value);
        }
    }

    /**&lt;No document in protocol.&gt;*/
    @ParametersAreNonnullByDefault public static class TraceConfig implements CommonDomainType {
        /**Controls how the trace buffer stores data.
        <em>Optional.</em>*/
        @ParametersAreNonnullByDefault public enum RecordMode implements CommonDomainType {
            RecordUntilFull("recordUntilFull"),
            RecordContinuously("recordContinuously"),
            RecordAsMuchAsPossible("recordAsMuchAsPossible"),
            EchoToConsole("echoToConsole");

            private final String _value;
            private static final Map<String, RecordMode> _Lookup;
            static {
                Map<String, RecordMode> m = new HashMap<>();
                for(RecordMode v: values()) m.put(v.toString(), v);
                _Lookup = Collections.unmodifiableMap(m);
            }
            /**Convert string representation to type.
             @throws IllegalArgumentException if given value cannot convert to enum type. */
            @JsonCreator public static RecordMode of(String value) {
                RecordMode v = _Lookup.get(value.toLowerCase());
                return v != null ? v : Enum.valueOf(RecordMode.class, value);
            }
            RecordMode(String value) { _value = value; }
            /**Check if parameter fields of method are all valid. */
            @Override public void check() throws IllegalArgumentException { /* Need not check */ }
            /**Convert method parameter object into json string and append into string builder.
             @return string builder instance that is given in parameter (for chaining coding style use.) */
            @Override public StringBuilder toJson(StringBuilder strBuilder) { return strBuilder.append(toString()); }
            @Override public String toString() { return _value; }
        }
        private RecordMode recordMode;
        /**Turns on JavaScript stack sampling.
        <em>Optional.</em>*/
        private Boolean enableSampling;
        /**Turns on system tracing.
        <em>Optional.</em>*/
        private Boolean enableSystrace;
        /**Turns on argument filter.
        <em>Optional.</em>*/
        private Boolean enableArgumentFilter;
        /**Included category filters.
        <em>Optional.</em>*/
        private List<String> includedCategories;
        /**Excluded category filters.
        <em>Optional.</em>*/
        private List<String> excludedCategories;
        /**Configuration to synthesize the delays in tracing.
        <em>Optional.</em>*/
        private List<String> syntheticDelays;
        /**Configuration for memory dump triggers. Used only when "memory-infra" category is enabled.
        <em>Optional.</em>*/
        private MemoryDumpConfig memoryDumpConfig;
        public final TraceConfig recordMode(@Nullable RecordMode recordMode) { this.recordMode = recordMode; return this; }
        public final TraceConfig optRecordMode(@Nullable RecordMode recordMode) { return recordMode(recordMode); }
        public final RecordMode recordMode() { return recordMode; }
        public final RecordMode getRecordMode() { return recordMode(); }
        public final TraceConfig enableSampling(@Nullable Boolean enableSampling) { this.enableSampling = enableSampling; return this; }
        public final TraceConfig optEnableSampling(@Nullable Boolean enableSampling) { return enableSampling(enableSampling); }
        public final Boolean enableSampling() { return enableSampling; }
        public final Boolean getEnableSampling() { return enableSampling(); }
        public final TraceConfig enableSystrace(@Nullable Boolean enableSystrace) { this.enableSystrace = enableSystrace; return this; }
        public final TraceConfig optEnableSystrace(@Nullable Boolean enableSystrace) { return enableSystrace(enableSystrace); }
        public final Boolean enableSystrace() { return enableSystrace; }
        public final Boolean getEnableSystrace() { return enableSystrace(); }
        public final TraceConfig enableArgumentFilter(@Nullable Boolean enableArgumentFilter) { this.enableArgumentFilter = enableArgumentFilter; return this; }
        public final TraceConfig optEnableArgumentFilter(@Nullable Boolean enableArgumentFilter) { return enableArgumentFilter(enableArgumentFilter); }
        public final Boolean enableArgumentFilter() { return enableArgumentFilter; }
        public final Boolean getEnableArgumentFilter() { return enableArgumentFilter(); }
        public final TraceConfig includedCategories(@Nullable List<String> includedCategories) { this.includedCategories = includedCategories; return this; }
        public final TraceConfig optIncludedCategories(@Nullable List<String> includedCategories) { return includedCategories(includedCategories); }
        public final List<String> includedCategories() { return includedCategories; }
        public final List<String> getIncludedCategories() { return includedCategories(); }
        public final TraceConfig excludedCategories(@Nullable List<String> excludedCategories) { this.excludedCategories = excludedCategories; return this; }
        public final TraceConfig optExcludedCategories(@Nullable List<String> excludedCategories) { return excludedCategories(excludedCategories); }
        public final List<String> excludedCategories() { return excludedCategories; }
        public final List<String> getExcludedCategories() { return excludedCategories(); }
        public final TraceConfig syntheticDelays(@Nullable List<String> syntheticDelays) { this.syntheticDelays = syntheticDelays; return this; }
        public final TraceConfig optSyntheticDelays(@Nullable List<String> syntheticDelays) { return syntheticDelays(syntheticDelays); }
        public final List<String> syntheticDelays() { return syntheticDelays; }
        public final List<String> getSyntheticDelays() { return syntheticDelays(); }
        public final TraceConfig memoryDumpConfig(@Nullable MemoryDumpConfig memoryDumpConfig) { this.memoryDumpConfig = memoryDumpConfig; return this; }
        public final TraceConfig optMemoryDumpConfig(@Nullable MemoryDumpConfig memoryDumpConfig) { return memoryDumpConfig(memoryDumpConfig); }
        public final MemoryDumpConfig memoryDumpConfig() { return memoryDumpConfig; }
        public final MemoryDumpConfig getMemoryDumpConfig() { return memoryDumpConfig(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            if (recordMode != null) strBuilder.append("\"recordMode\":").append(recordMode);
            if (enableSampling != null) strBuilder.append(",\"enableSampling\":").append(enableSampling);
            if (enableSystrace != null) strBuilder.append(",\"enableSystrace\":").append(enableSystrace);
            if (enableArgumentFilter != null) strBuilder.append(",\"enableArgumentFilter\":").append(enableArgumentFilter);
            if (includedCategories != null) {
                strBuilder.append(",\"includedCategories\":[");
                strBuilder.append('"').append(DomainBase.escapeQuote(includedCategories.get(0))).append('"');
                for (int i = 1; i < includedCategories.size(); ++i)
                    strBuilder.append(",\"").append(DomainBase.escapeQuote(includedCategories.get(i))).append('"');
                strBuilder.append(']');
            }
            if (excludedCategories != null) {
                strBuilder.append(",\"excludedCategories\":[");
                strBuilder.append('"').append(DomainBase.escapeQuote(excludedCategories.get(0))).append('"');
                for (int i = 1; i < excludedCategories.size(); ++i)
                    strBuilder.append(",\"").append(DomainBase.escapeQuote(excludedCategories.get(i))).append('"');
                strBuilder.append(']');
            }
            if (syntheticDelays != null) {
                strBuilder.append(",\"syntheticDelays\":[");
                strBuilder.append('"').append(DomainBase.escapeQuote(syntheticDelays.get(0))).append('"');
                for (int i = 1; i < syntheticDelays.size(); ++i)
                    strBuilder.append(",\"").append(DomainBase.escapeQuote(syntheticDelays.get(i))).append('"');
                strBuilder.append(']');
            }
            if (memoryDumpConfig != null) memoryDumpConfig.toJson(strBuilder.append(",\"memoryDumpConfig\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public TraceConfig() {}
        public TraceConfig(
            @Nullable @JsonProperty("recordMode")RecordMode recordMode,
            @Nullable @JsonProperty("enableSampling")Boolean enableSampling,
            @Nullable @JsonProperty("enableSystrace")Boolean enableSystrace,
            @Nullable @JsonProperty("enableArgumentFilter")Boolean enableArgumentFilter,
            @Nullable @JsonProperty("includedCategories")List<String> includedCategories,
            @Nullable @JsonProperty("excludedCategories")List<String> excludedCategories,
            @Nullable @JsonProperty("syntheticDelays")List<String> syntheticDelays,
            @Nullable @JsonProperty("memoryDumpConfig")MemoryDumpConfig memoryDumpConfig
        ) {
            this.recordMode = recordMode;
            this.enableSampling = enableSampling;
            this.enableSystrace = enableSystrace;
            this.enableArgumentFilter = enableArgumentFilter;
            this.includedCategories = includedCategories;
            this.excludedCategories = excludedCategories;
            this.syntheticDelays = syntheticDelays;
            this.memoryDumpConfig = memoryDumpConfig;
        }
    }

    /**Compression type to use for traces returned via streams.*/
    @ParametersAreNonnullByDefault public enum StreamCompression implements CommonDomainType {
        None("none"),
        Gzip("gzip");

        private final String _value;
        private static final Map<String, StreamCompression> _Lookup;
        static {
            Map<String, StreamCompression> m = new HashMap<>();
            for(StreamCompression v: values()) m.put(v.toString(), v);
            _Lookup = Collections.unmodifiableMap(m);
        }
        /**Convert string representation to type.
         @throws IllegalArgumentException if given value cannot convert to enum type. */
        @JsonCreator public static StreamCompression of(String value) {
            StreamCompression v = _Lookup.get(value.toLowerCase());
            return v != null ? v : Enum.valueOf(StreamCompression.class, value);
        }
        StreamCompression(String value) { _value = value; }
        /**Check if parameter fields of method are all valid. */
        @Override public void check() throws IllegalArgumentException { /* Need not check */ }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) { return strBuilder.append(toString()); }
        @Override public String toString() { return _value; }
    }
    /**Stop trace events collection.*/
    public EndParameter end() { final EndParameter v = new EndParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of end.*/
    @ParametersAreNonnullByDefault public static class EndParameter extends CommandBase {
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
        public EndParameter() {}
        public CompletableFuture<EndResult> call() {
            return super.call("Tracing.end", EndResult.class,
                (code, msg)->new EndResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<EndResult> call(Executor exec) {
            return super.call("Tracing.end", EndResult.class,
                (code, msg)->new EndResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of end.*/
    @ParametersAreNonnullByDefault public static class EndResult extends ResultBase {
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
        public EndResult() { super(); }
        public EndResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Gets supported tracing categories.*/
    public GetCategoriesParameter getCategories() { final GetCategoriesParameter v = new GetCategoriesParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of getCategories.*/
    @ParametersAreNonnullByDefault public static class GetCategoriesParameter extends CommandBase {
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
        public GetCategoriesParameter() {}
        public CompletableFuture<GetCategoriesResult> call() {
            return super.call("Tracing.getCategories", GetCategoriesResult.class,
                (code, msg)->new GetCategoriesResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetCategoriesResult> call(Executor exec) {
            return super.call("Tracing.getCategories", GetCategoriesResult.class,
                (code, msg)->new GetCategoriesResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of getCategories.*/
    @ParametersAreNonnullByDefault public static class GetCategoriesResult extends ResultBase {
        /**A list of supported tracing categories.*/
        private final List<String> categories;
        public final List<String> categories() { return categories; }
        public final List<String> getCategories() { return categories(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
                        strBuilder.append("\"categories\":[");
            strBuilder.append('"').append(DomainBase.escapeQuote(categories.get(0))).append('"');
            for (int i = 1; i < categories.size(); ++i)
                strBuilder.append(",\"").append(DomainBase.escapeQuote(categories.get(i))).append('"');
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public GetCategoriesResult(
            @JsonProperty("categories")List<String> categories
        ) {
            this.categories = categories;
        }
        public GetCategoriesResult(ResultBase.FailedResult e) {
            super(e);
            categories = null;
        }
    }
    /**Record a clock sync marker in the trace.*/
    public RecordClockSyncMarkerParameter recordClockSyncMarker() { final RecordClockSyncMarkerParameter v = new RecordClockSyncMarkerParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of recordClockSyncMarker.*/
    @ParametersAreNonnullByDefault public static class RecordClockSyncMarkerParameter extends CommandBase {
        /**The ID of this clock sync marker*/
        private String syncId;
        public final RecordClockSyncMarkerParameter syncId(String syncId) { this.syncId = syncId; return this; }
        public final RecordClockSyncMarkerParameter setSyncId(String syncId) { return syncId(syncId); }
        public final String syncId() { return syncId; }
        public final String getSyncId() { return syncId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (syncId == null) throw new IllegalArgumentException("Tracing.RecordClockSyncMarkerParameter.syncId is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"syncId\":").append('"').append(DomainBase.escapeQuote(syncId)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public RecordClockSyncMarkerParameter() {}
        public RecordClockSyncMarkerParameter(
            @JsonProperty("syncId")String syncId
        ) {
            this();
            this.syncId = syncId;
        }
        public CompletableFuture<RecordClockSyncMarkerResult> call() {
            return super.call("Tracing.recordClockSyncMarker", RecordClockSyncMarkerResult.class,
                (code, msg)->new RecordClockSyncMarkerResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<RecordClockSyncMarkerResult> call(Executor exec) {
            return super.call("Tracing.recordClockSyncMarker", RecordClockSyncMarkerResult.class,
                (code, msg)->new RecordClockSyncMarkerResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of recordClockSyncMarker.*/
    @ParametersAreNonnullByDefault public static class RecordClockSyncMarkerResult extends ResultBase {
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
        public RecordClockSyncMarkerResult() { super(); }
        public RecordClockSyncMarkerResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Request a global memory dump.*/
    public RequestMemoryDumpParameter requestMemoryDump() { final RequestMemoryDumpParameter v = new RequestMemoryDumpParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of requestMemoryDump.*/
    @ParametersAreNonnullByDefault public static class RequestMemoryDumpParameter extends CommandBase {
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
        public RequestMemoryDumpParameter() {}
        public CompletableFuture<RequestMemoryDumpResult> call() {
            return super.call("Tracing.requestMemoryDump", RequestMemoryDumpResult.class,
                (code, msg)->new RequestMemoryDumpResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<RequestMemoryDumpResult> call(Executor exec) {
            return super.call("Tracing.requestMemoryDump", RequestMemoryDumpResult.class,
                (code, msg)->new RequestMemoryDumpResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of requestMemoryDump.*/
    @ParametersAreNonnullByDefault public static class RequestMemoryDumpResult extends ResultBase {
        /**GUID of the resulting global memory dump.*/
        private final String dumpGuid;
        /**True iff the global memory dump succeeded.*/
        private final Boolean success;
        public final String dumpGuid() { return dumpGuid; }
        public final String getDumpGuid() { return dumpGuid(); }
        public final Boolean success() { return success; }
        public final Boolean getSuccess() { return success(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"dumpGuid\":").append('"').append(DomainBase.escapeQuote(dumpGuid)).append('"');
            strBuilder.append(",\"success\":").append(success);
            strBuilder.append('}');
            return strBuilder;
        }
        public RequestMemoryDumpResult(
            @JsonProperty("dumpGuid")String dumpGuid,
            @JsonProperty("success")Boolean success
        ) {
            this.dumpGuid = dumpGuid;
            this.success = success;
        }
        public RequestMemoryDumpResult(ResultBase.FailedResult e) {
            super(e);
            dumpGuid = null;
            success = null;
        }
    }
    /**Start trace events collection.*/
    public StartParameter start() { final StartParameter v = new StartParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of start.*/
    @ParametersAreNonnullByDefault public static class StartParameter extends CommandBase {
        /**Category/tag filter
        <em>Optional.</em>
        @Deprecated*/
        private String categories;
        /**Tracing options
        <em>Optional.</em>
        @Deprecated*/
        private String options;
        /**If set, the agent will issue bufferUsage events at this interval, specified in milliseconds
        <em>Optional.</em>*/
        private Double bufferUsageReportingInterval;
        /**Whether to report trace events as series of dataCollected events or to save trace to a
stream (defaults to `ReportEvents`).
        <em>Optional.</em>*/
        @ParametersAreNonnullByDefault public enum TransferMode implements CommonDomainType {
            ReportEvents("ReportEvents"),
            ReturnAsStream("ReturnAsStream");

            private final String _value;
            private static final Map<String, TransferMode> _Lookup;
            static {
                Map<String, TransferMode> m = new HashMap<>();
                for(TransferMode v: values()) m.put(v.toString(), v);
                _Lookup = Collections.unmodifiableMap(m);
            }
            /**Convert string representation to type.
             @throws IllegalArgumentException if given value cannot convert to enum type. */
            @JsonCreator public static TransferMode of(String value) {
                TransferMode v = _Lookup.get(value.toLowerCase());
                return v != null ? v : Enum.valueOf(TransferMode.class, value);
            }
            TransferMode(String value) { _value = value; }
            /**Check if parameter fields of method are all valid. */
            @Override public void check() throws IllegalArgumentException { /* Need not check */ }
            /**Convert method parameter object into json string and append into string builder.
             @return string builder instance that is given in parameter (for chaining coding style use.) */
            @Override public StringBuilder toJson(StringBuilder strBuilder) { return strBuilder.append(toString()); }
            @Override public String toString() { return _value; }
        }
        private TransferMode transferMode;
        /**Compression format to use. This only applies when using `ReturnAsStream`
transfer mode (defaults to `none`)
        <em>Optional.</em>*/
        private StreamCompression streamCompression;
        /**&lt;No document in protocol.&gt;
        <em>Optional.</em>*/
        private TraceConfig traceConfig;
        public final StartParameter categories(@Nullable String categories) { this.categories = categories; return this; }
        public final StartParameter optCategories(@Nullable String categories) { return categories(categories); }
        public final String categories() { return categories; }
        public final String getCategories() { return categories(); }
        public final StartParameter options(@Nullable String options) { this.options = options; return this; }
        public final StartParameter optOptions(@Nullable String options) { return options(options); }
        public final String options() { return options; }
        public final String getOptions() { return options(); }
        public final StartParameter bufferUsageReportingInterval(@Nullable Double bufferUsageReportingInterval) { this.bufferUsageReportingInterval = bufferUsageReportingInterval; return this; }
        public final StartParameter optBufferUsageReportingInterval(@Nullable Double bufferUsageReportingInterval) { return bufferUsageReportingInterval(bufferUsageReportingInterval); }
        public final Double bufferUsageReportingInterval() { return bufferUsageReportingInterval; }
        public final Double getBufferUsageReportingInterval() { return bufferUsageReportingInterval(); }
        public final StartParameter transferMode(@Nullable TransferMode transferMode) { this.transferMode = transferMode; return this; }
        public final StartParameter optTransferMode(@Nullable TransferMode transferMode) { return transferMode(transferMode); }
        public final TransferMode transferMode() { return transferMode; }
        public final TransferMode getTransferMode() { return transferMode(); }
        public final StartParameter streamCompression(@Nullable StreamCompression streamCompression) { this.streamCompression = streamCompression; return this; }
        public final StartParameter optStreamCompression(@Nullable StreamCompression streamCompression) { return streamCompression(streamCompression); }
        public final StreamCompression streamCompression() { return streamCompression; }
        public final StreamCompression getStreamCompression() { return streamCompression(); }
        public final StartParameter traceConfig(@Nullable TraceConfig traceConfig) { this.traceConfig = traceConfig; return this; }
        public final StartParameter optTraceConfig(@Nullable TraceConfig traceConfig) { return traceConfig(traceConfig); }
        public final TraceConfig traceConfig() { return traceConfig; }
        public final TraceConfig getTraceConfig() { return traceConfig(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            if (categories != null) strBuilder.append("\"categories\":").append('"').append(DomainBase.escapeQuote(categories)).append('"');
            if (options != null) strBuilder.append(",\"options\":").append('"').append(DomainBase.escapeQuote(options)).append('"');
            if (bufferUsageReportingInterval != null) strBuilder.append(",\"bufferUsageReportingInterval\":").append(bufferUsageReportingInterval);
            if (transferMode != null) strBuilder.append(",\"transferMode\":").append(transferMode);
            if (streamCompression != null) streamCompression.toJson(strBuilder.append(",\"streamCompression\":"));
            if (traceConfig != null) traceConfig.toJson(strBuilder.append(",\"traceConfig\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public StartParameter() {}
        public StartParameter(
            @Nullable @JsonProperty("categories")String categories,
            @Nullable @JsonProperty("options")String options,
            @Nullable @JsonProperty("bufferUsageReportingInterval")Double bufferUsageReportingInterval,
            @Nullable @JsonProperty("transferMode")TransferMode transferMode,
            @Nullable @JsonProperty("streamCompression")StreamCompression streamCompression,
            @Nullable @JsonProperty("traceConfig")TraceConfig traceConfig
        ) {
            this();
            this.categories = categories;
            this.options = options;
            this.bufferUsageReportingInterval = bufferUsageReportingInterval;
            this.transferMode = transferMode;
            this.streamCompression = streamCompression;
            this.traceConfig = traceConfig;
        }
        public CompletableFuture<StartResult> call() {
            return super.call("Tracing.start", StartResult.class,
                (code, msg)->new StartResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<StartResult> call(Executor exec) {
            return super.call("Tracing.start", StartResult.class,
                (code, msg)->new StartResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of start.*/
    @ParametersAreNonnullByDefault public static class StartResult extends ResultBase {
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
        public StartResult() { super(); }
        public StartResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Event parameter of Tracing.bufferUsage.
     @see #onBufferUsage*/
    @ParametersAreNonnullByDefault public static class BufferUsageEventParameter implements CommonDomainType {
        /**A number in range [0..1] that indicates the used size of event buffer as a fraction of its
total size.
        <em>Optional.</em>*/
        private final Double percentFull;
        /**An approximate number of events in the trace log.
        <em>Optional.</em>*/
        private final Double eventCount;
        /**A number in range [0..1] that indicates the used size of event buffer as a fraction of its
total size.
        <em>Optional.</em>*/
        private final Double value;
        public final Double percentFull() { return percentFull; }
        public final Double getPercentFull() { return percentFull(); }
        public final Double eventCount() { return eventCount; }
        public final Double getEventCount() { return eventCount(); }
        public final Double value() { return value; }
        public final Double getValue() { return value(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            if (percentFull != null) strBuilder.append("\"percentFull\":").append(percentFull);
            if (eventCount != null) strBuilder.append(",\"eventCount\":").append(eventCount);
            if (value != null) strBuilder.append(",\"value\":").append(value);
            strBuilder.append('}');
            return strBuilder;
        }
        BufferUsageEventParameter(
            @Nullable @JsonProperty("percentFull")Double percentFull,
            @Nullable @JsonProperty("eventCount")Double eventCount,
            @Nullable @JsonProperty("value")Double value
        ) {
            this.percentFull = percentFull;
            this.eventCount = eventCount;
            this.value = value;
        }
    }
    /**&lt;No document in protocol.&gt;
     @see BufferUsageEventParameter*/
    public void onBufferUsage(Consumer<BufferUsageEventParameter> callback) {
        registerEventCallback("Tracing.bufferUsage", node -> {
            BufferUsageEventParameter param;
            try { param = EventCenter.deserializeJson(node, BufferUsageEventParameter.class); }
            catch (IOException e) { e.printStackTrace(); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of Tracing.dataCollected.
     @see #onDataCollected*/
    @ParametersAreNonnullByDefault public static class DataCollectedEventParameter implements CommonDomainType {
        /**&lt;No document in protocol.&gt;*/
        private final List<Object> value;
        public final List<Object> value() { return value; }
        public final List<Object> getValue() { return value(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
                        strBuilder.append("\"value\":[");
            strBuilder.append(value.get(0));
            for (int i = 1; i < value.size(); ++i)
                strBuilder.append(',').append(value.get(i));
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        DataCollectedEventParameter(
            @JsonProperty("value")List<Object> value
        ) {
            this.value = value;
        }
    }
    /**Contains an bucket of collected trace events. When tracing is stopped collected events will be
send as a sequence of dataCollected events followed by tracingComplete event.
     @see DataCollectedEventParameter*/
    public void onDataCollected(Consumer<DataCollectedEventParameter> callback) {
        registerEventCallback("Tracing.dataCollected", node -> {
            DataCollectedEventParameter param;
            try { param = EventCenter.deserializeJson(node, DataCollectedEventParameter.class); }
            catch (IOException e) { e.printStackTrace(); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of Tracing.tracingComplete.
     @see #onTracingComplete*/
    @ParametersAreNonnullByDefault public static class TracingCompleteEventParameter implements CommonDomainType {
        /**A handle of the stream that holds resulting trace data.
        <em>Optional.</em>*/
        private final IO.StreamHandle stream;
        /**Compression format of returned stream.
        <em>Optional.</em>*/
        private final StreamCompression streamCompression;
        public final IO.StreamHandle stream() { return stream; }
        public final IO.StreamHandle getStream() { return stream(); }
        public final StreamCompression streamCompression() { return streamCompression; }
        public final StreamCompression getStreamCompression() { return streamCompression(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            if (stream != null) stream.toJson(strBuilder.append("\"stream\":"));
            if (streamCompression != null) streamCompression.toJson(strBuilder.append(",\"streamCompression\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        TracingCompleteEventParameter(
            @Nullable @JsonProperty("stream")IO.StreamHandle stream,
            @Nullable @JsonProperty("streamCompression")StreamCompression streamCompression
        ) {
            this.stream = stream;
            this.streamCompression = streamCompression;
        }
    }
    /**Signals that tracing is stopped and there is no trace buffers pending flush, all data were
delivered via dataCollected events.
     @see TracingCompleteEventParameter*/
    public void onTracingComplete(Consumer<TracingCompleteEventParameter> callback) {
        registerEventCallback("Tracing.tracingComplete", node -> {
            TracingCompleteEventParameter param;
            try { param = EventCenter.deserializeJson(node, TracingCompleteEventParameter.class); }
            catch (IOException e) { e.printStackTrace(); return; }
            callback.accept(param);
        });
    }
}

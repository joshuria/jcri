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
 @author Joshua*/
@ParametersAreNonnullByDefault public class Memory extends DomainBase {
    public Memory(EventCenter evt, WebSocket ws) { super(evt, ws); }

    /**Memory pressure level.*/
    @ParametersAreNonnullByDefault public enum PressureLevel implements CommonDomainType {
        Moderate("moderate"),
        Critical("critical");

        private final String _value;
        private static final Map<String, PressureLevel> _Lookup;
        static {
            Map<String, PressureLevel> m = new HashMap<>();
            for(PressureLevel v: values()) m.put(v.toString(), v);
            _Lookup = Collections.unmodifiableMap(m);
        }
        /**Convert string representation to type.
         @throws IllegalArgumentException if given value cannot convert to enum type. */
        @JsonCreator public static PressureLevel of(String value) {
            PressureLevel v = _Lookup.get(value.toLowerCase());
            return v != null ? v : Enum.valueOf(PressureLevel.class, value);
        }
        PressureLevel(String value) { _value = value; }
        /**Check if parameter fields of method are all valid. */
        @Override public void check() throws IllegalArgumentException { /* Need not check */ }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) { return strBuilder.append(toString()); }
        @Override public String toString() { return _value; }
    }

    /**Heap profile sample.*/
    @ParametersAreNonnullByDefault public static class SamplingProfileNode implements CommonDomainType {
        /**Size of the sampled allocation.*/
        private Double size;
        /**Total bytes attributed to this sample.*/
        private Double total;
        /**Execution stack at the point of allocation.*/
        private List<String> stack;
        public final SamplingProfileNode size(Double size) { this.size = size; return this; }
        public final SamplingProfileNode setSize(Double size) { return size(size); }
        public final Double size() { return size; }
        public final Double getSize() { return size(); }
        public final SamplingProfileNode total(Double total) { this.total = total; return this; }
        public final SamplingProfileNode setTotal(Double total) { return total(total); }
        public final Double total() { return total; }
        public final Double getTotal() { return total(); }
        public final SamplingProfileNode stack(List<String> stack) { this.stack = stack; return this; }
        public final SamplingProfileNode setStack(List<String> stack) { return stack(stack); }
        public final List<String> stack() { return stack; }
        public final List<String> getStack() { return stack(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (size == null) throw new IllegalArgumentException("Memory.SamplingProfileNode.size is necessary field.");
            if (total == null) throw new IllegalArgumentException("Memory.SamplingProfileNode.total is necessary field.");
            if (stack == null) throw new IllegalArgumentException("Memory.SamplingProfileNode.stack is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"size\":").append(size);
            strBuilder.append(",\"total\":").append(total);
                        strBuilder.append(",\"stack\":[");
            strBuilder.append('"').append(stack.get(0)).append('"');
            for (int i = 1; i < stack.size(); ++i)
                strBuilder.append(",\"").append(stack.get(i)).append('"');
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public SamplingProfileNode() {}
        public SamplingProfileNode(
            @JsonProperty("size")Double size,
            @JsonProperty("total")Double total,
            @JsonProperty("stack")List<String> stack
        ) {
            this.size = size;
            this.total = total;
            this.stack = stack;
        }
    }

    /**Array of heap profile samples.*/
    @ParametersAreNonnullByDefault public static class SamplingProfile implements CommonDomainType {
        /**&lt;No document in protocol.&gt;*/
        private List<SamplingProfileNode> samples;
        public final SamplingProfile samples(List<SamplingProfileNode> samples) { this.samples = samples; return this; }
        public final SamplingProfile setSamples(List<SamplingProfileNode> samples) { return samples(samples); }
        public final List<SamplingProfileNode> samples() { return samples; }
        public final List<SamplingProfileNode> getSamples() { return samples(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (samples == null) throw new IllegalArgumentException("Memory.SamplingProfile.samples is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
                        strBuilder.append("\"samples\":[");
            samples.get(0).toJson(strBuilder);
            for (int i = 1; i < samples.size(); ++i)
                samples.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public SamplingProfile() {}
        public SamplingProfile(
            @JsonProperty("samples")List<SamplingProfileNode> samples
        ) {
            this.samples = samples;
        }
    }
    /**&lt;No document in protocol.&gt;*/
    public GetDOMCountersParameter getDOMCounters() { final GetDOMCountersParameter v = new GetDOMCountersParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of getDOMCounters.*/
    @ParametersAreNonnullByDefault public static class GetDOMCountersParameter extends CommandBase {
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
        public GetDOMCountersParameter() {}
        public CompletableFuture<GetDOMCountersResult> call() {
            return super.call("Memory.getDOMCounters", GetDOMCountersResult.class,
                (code, msg)->new GetDOMCountersResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetDOMCountersResult> call(Executor exec) {
            return super.call("Memory.getDOMCounters", GetDOMCountersResult.class,
                (code, msg)->new GetDOMCountersResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of getDOMCounters.*/
    @ParametersAreNonnullByDefault public static class GetDOMCountersResult extends ResultBase {
        /**&lt;No document in protocol.&gt;*/
        private final Integer documents;
        /**&lt;No document in protocol.&gt;*/
        private final Integer nodes;
        /**&lt;No document in protocol.&gt;*/
        private final Integer jsEventListeners;
        public final Integer documents() { return documents; }
        public final Integer getDocuments() { return documents(); }
        public final Integer nodes() { return nodes; }
        public final Integer getNodes() { return nodes(); }
        public final Integer jsEventListeners() { return jsEventListeners; }
        public final Integer getJsEventListeners() { return jsEventListeners(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"documents\":").append(documents);
            strBuilder.append(",\"nodes\":").append(nodes);
            strBuilder.append(",\"jsEventListeners\":").append(jsEventListeners);
            strBuilder.append('}');
            return strBuilder;
        }
        public GetDOMCountersResult(
            @JsonProperty("documents")Integer documents,
            @JsonProperty("nodes")Integer nodes,
            @JsonProperty("jsEventListeners")Integer jsEventListeners
        ) {
            this.documents = documents;
            this.nodes = nodes;
            this.jsEventListeners = jsEventListeners;
        }
        public GetDOMCountersResult(ResultBase.FailedResult e) {
            super(e);
            documents = null;
            nodes = null;
            jsEventListeners = null;
        }
    }
    /**&lt;No document in protocol.&gt;*/
    public PrepareForLeakDetectionParameter prepareForLeakDetection() { final PrepareForLeakDetectionParameter v = new PrepareForLeakDetectionParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of prepareForLeakDetection.*/
    @ParametersAreNonnullByDefault public static class PrepareForLeakDetectionParameter extends CommandBase {
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
        public PrepareForLeakDetectionParameter() {}
        public CompletableFuture<PrepareForLeakDetectionResult> call() {
            return super.call("Memory.prepareForLeakDetection", PrepareForLeakDetectionResult.class,
                (code, msg)->new PrepareForLeakDetectionResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<PrepareForLeakDetectionResult> call(Executor exec) {
            return super.call("Memory.prepareForLeakDetection", PrepareForLeakDetectionResult.class,
                (code, msg)->new PrepareForLeakDetectionResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of prepareForLeakDetection.*/
    @ParametersAreNonnullByDefault public static class PrepareForLeakDetectionResult extends ResultBase {
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
        public PrepareForLeakDetectionResult() { super(); }
        public PrepareForLeakDetectionResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Enable/disable suppressing memory pressure notifications in all processes.*/
    public SetPressureNotificationsSuppressedParameter setPressureNotificationsSuppressed() { final SetPressureNotificationsSuppressedParameter v = new SetPressureNotificationsSuppressedParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setPressureNotificationsSuppressed.*/
    @ParametersAreNonnullByDefault public static class SetPressureNotificationsSuppressedParameter extends CommandBase {
        /**If true, memory pressure notifications will be suppressed.*/
        private Boolean suppressed;
        public final SetPressureNotificationsSuppressedParameter suppressed(Boolean suppressed) { this.suppressed = suppressed; return this; }
        public final SetPressureNotificationsSuppressedParameter setSuppressed(Boolean suppressed) { return suppressed(suppressed); }
        public final Boolean suppressed() { return suppressed; }
        public final Boolean getSuppressed() { return suppressed(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (suppressed == null) throw new IllegalArgumentException("Memory.SetPressureNotificationsSuppressedParameter.suppressed is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"suppressed\":").append(suppressed);
            strBuilder.append('}');
            return strBuilder;
        }
        public SetPressureNotificationsSuppressedParameter() {}
        public SetPressureNotificationsSuppressedParameter(
            @JsonProperty("suppressed")Boolean suppressed
        ) {
            this();
            this.suppressed = suppressed;
        }
        public CompletableFuture<SetPressureNotificationsSuppressedResult> call() {
            return super.call("Memory.setPressureNotificationsSuppressed", SetPressureNotificationsSuppressedResult.class,
                (code, msg)->new SetPressureNotificationsSuppressedResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetPressureNotificationsSuppressedResult> call(Executor exec) {
            return super.call("Memory.setPressureNotificationsSuppressed", SetPressureNotificationsSuppressedResult.class,
                (code, msg)->new SetPressureNotificationsSuppressedResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setPressureNotificationsSuppressed.*/
    @ParametersAreNonnullByDefault public static class SetPressureNotificationsSuppressedResult extends ResultBase {
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
        public SetPressureNotificationsSuppressedResult() { super(); }
        public SetPressureNotificationsSuppressedResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Simulate a memory pressure notification in all processes.*/
    public SimulatePressureNotificationParameter simulatePressureNotification() { final SimulatePressureNotificationParameter v = new SimulatePressureNotificationParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of simulatePressureNotification.*/
    @ParametersAreNonnullByDefault public static class SimulatePressureNotificationParameter extends CommandBase {
        /**Memory pressure level of the notification.*/
        private PressureLevel level;
        public final SimulatePressureNotificationParameter level(PressureLevel level) { this.level = level; return this; }
        public final SimulatePressureNotificationParameter setLevel(PressureLevel level) { return level(level); }
        public final PressureLevel level() { return level; }
        public final PressureLevel getLevel() { return level(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (level == null) throw new IllegalArgumentException("Memory.SimulatePressureNotificationParameter.level is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            level.toJson(strBuilder.append("\"level\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public SimulatePressureNotificationParameter() {}
        public SimulatePressureNotificationParameter(
            @JsonProperty("level")PressureLevel level
        ) {
            this();
            this.level = level;
        }
        public CompletableFuture<SimulatePressureNotificationResult> call() {
            return super.call("Memory.simulatePressureNotification", SimulatePressureNotificationResult.class,
                (code, msg)->new SimulatePressureNotificationResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SimulatePressureNotificationResult> call(Executor exec) {
            return super.call("Memory.simulatePressureNotification", SimulatePressureNotificationResult.class,
                (code, msg)->new SimulatePressureNotificationResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of simulatePressureNotification.*/
    @ParametersAreNonnullByDefault public static class SimulatePressureNotificationResult extends ResultBase {
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
        public SimulatePressureNotificationResult() { super(); }
        public SimulatePressureNotificationResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Start collecting native memory profile.*/
    public StartSamplingParameter startSampling() { final StartSamplingParameter v = new StartSamplingParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of startSampling.*/
    @ParametersAreNonnullByDefault public static class StartSamplingParameter extends CommandBase {
        /**Average number of bytes between samples.
        <em>Optional.</em>*/
        private Integer samplingInterval;
        /**Do not randomize intervals between samples.
        <em>Optional.</em>*/
        private Boolean suppressRandomness;
        public final StartSamplingParameter samplingInterval(@Nullable Integer samplingInterval) { this.samplingInterval = samplingInterval; return this; }
        public final StartSamplingParameter optSamplingInterval(@Nullable Integer samplingInterval) { return samplingInterval(samplingInterval); }
        public final Integer samplingInterval() { return samplingInterval; }
        public final Integer getSamplingInterval() { return samplingInterval(); }
        public final StartSamplingParameter suppressRandomness(@Nullable Boolean suppressRandomness) { this.suppressRandomness = suppressRandomness; return this; }
        public final StartSamplingParameter optSuppressRandomness(@Nullable Boolean suppressRandomness) { return suppressRandomness(suppressRandomness); }
        public final Boolean suppressRandomness() { return suppressRandomness; }
        public final Boolean getSuppressRandomness() { return suppressRandomness(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            if (samplingInterval != null) strBuilder.append("\"samplingInterval\":").append(samplingInterval);
            if (suppressRandomness != null) strBuilder.append(",\"suppressRandomness\":").append(suppressRandomness);
            strBuilder.append('}');
            return strBuilder;
        }
        public StartSamplingParameter() {}
        public StartSamplingParameter(
            @Nullable @JsonProperty("samplingInterval")Integer samplingInterval,
            @Nullable @JsonProperty("suppressRandomness")Boolean suppressRandomness
        ) {
            this();
            this.samplingInterval = samplingInterval;
            this.suppressRandomness = suppressRandomness;
        }
        public CompletableFuture<StartSamplingResult> call() {
            return super.call("Memory.startSampling", StartSamplingResult.class,
                (code, msg)->new StartSamplingResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<StartSamplingResult> call(Executor exec) {
            return super.call("Memory.startSampling", StartSamplingResult.class,
                (code, msg)->new StartSamplingResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of startSampling.*/
    @ParametersAreNonnullByDefault public static class StartSamplingResult extends ResultBase {
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
        public StartSamplingResult() { super(); }
        public StartSamplingResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Stop collecting native memory profile.*/
    public StopSamplingParameter stopSampling() { final StopSamplingParameter v = new StopSamplingParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of stopSampling.*/
    @ParametersAreNonnullByDefault public static class StopSamplingParameter extends CommandBase {
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
        public StopSamplingParameter() {}
        public CompletableFuture<StopSamplingResult> call() {
            return super.call("Memory.stopSampling", StopSamplingResult.class,
                (code, msg)->new StopSamplingResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<StopSamplingResult> call(Executor exec) {
            return super.call("Memory.stopSampling", StopSamplingResult.class,
                (code, msg)->new StopSamplingResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of stopSampling.*/
    @ParametersAreNonnullByDefault public static class StopSamplingResult extends ResultBase {
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
        public StopSamplingResult() { super(); }
        public StopSamplingResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Retrieve native memory allocations profile
collected since renderer process startup.*/
    public GetAllTimeSamplingProfileParameter getAllTimeSamplingProfile() { final GetAllTimeSamplingProfileParameter v = new GetAllTimeSamplingProfileParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of getAllTimeSamplingProfile.*/
    @ParametersAreNonnullByDefault public static class GetAllTimeSamplingProfileParameter extends CommandBase {
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
        public GetAllTimeSamplingProfileParameter() {}
        public CompletableFuture<GetAllTimeSamplingProfileResult> call() {
            return super.call("Memory.getAllTimeSamplingProfile", GetAllTimeSamplingProfileResult.class,
                (code, msg)->new GetAllTimeSamplingProfileResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetAllTimeSamplingProfileResult> call(Executor exec) {
            return super.call("Memory.getAllTimeSamplingProfile", GetAllTimeSamplingProfileResult.class,
                (code, msg)->new GetAllTimeSamplingProfileResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of getAllTimeSamplingProfile.*/
    @ParametersAreNonnullByDefault public static class GetAllTimeSamplingProfileResult extends ResultBase {
        /**&lt;No document in protocol.&gt;*/
        private final SamplingProfile profile;
        public final SamplingProfile profile() { return profile; }
        public final SamplingProfile getProfile() { return profile(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            profile.toJson(strBuilder.append("\"profile\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public GetAllTimeSamplingProfileResult(
            @JsonProperty("profile")SamplingProfile profile
        ) {
            this.profile = profile;
        }
        public GetAllTimeSamplingProfileResult(ResultBase.FailedResult e) {
            super(e);
            profile = null;
        }
    }
    /**Retrieve native memory allocations profile
collected since browser process startup.*/
    public GetBrowserSamplingProfileParameter getBrowserSamplingProfile() { final GetBrowserSamplingProfileParameter v = new GetBrowserSamplingProfileParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of getBrowserSamplingProfile.*/
    @ParametersAreNonnullByDefault public static class GetBrowserSamplingProfileParameter extends CommandBase {
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
        public GetBrowserSamplingProfileParameter() {}
        public CompletableFuture<GetBrowserSamplingProfileResult> call() {
            return super.call("Memory.getBrowserSamplingProfile", GetBrowserSamplingProfileResult.class,
                (code, msg)->new GetBrowserSamplingProfileResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetBrowserSamplingProfileResult> call(Executor exec) {
            return super.call("Memory.getBrowserSamplingProfile", GetBrowserSamplingProfileResult.class,
                (code, msg)->new GetBrowserSamplingProfileResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of getBrowserSamplingProfile.*/
    @ParametersAreNonnullByDefault public static class GetBrowserSamplingProfileResult extends ResultBase {
        /**&lt;No document in protocol.&gt;*/
        private final SamplingProfile profile;
        public final SamplingProfile profile() { return profile; }
        public final SamplingProfile getProfile() { return profile(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            profile.toJson(strBuilder.append("\"profile\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public GetBrowserSamplingProfileResult(
            @JsonProperty("profile")SamplingProfile profile
        ) {
            this.profile = profile;
        }
        public GetBrowserSamplingProfileResult(ResultBase.FailedResult e) {
            super(e);
            profile = null;
        }
    }
    /**Retrieve native memory allocations profile collected since last
`startSampling` call.*/
    public GetSamplingProfileParameter getSamplingProfile() { final GetSamplingProfileParameter v = new GetSamplingProfileParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of getSamplingProfile.*/
    @ParametersAreNonnullByDefault public static class GetSamplingProfileParameter extends CommandBase {
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
        public GetSamplingProfileParameter() {}
        public CompletableFuture<GetSamplingProfileResult> call() {
            return super.call("Memory.getSamplingProfile", GetSamplingProfileResult.class,
                (code, msg)->new GetSamplingProfileResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetSamplingProfileResult> call(Executor exec) {
            return super.call("Memory.getSamplingProfile", GetSamplingProfileResult.class,
                (code, msg)->new GetSamplingProfileResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of getSamplingProfile.*/
    @ParametersAreNonnullByDefault public static class GetSamplingProfileResult extends ResultBase {
        /**&lt;No document in protocol.&gt;*/
        private final SamplingProfile profile;
        public final SamplingProfile profile() { return profile; }
        public final SamplingProfile getProfile() { return profile(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            profile.toJson(strBuilder.append("\"profile\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public GetSamplingProfileResult(
            @JsonProperty("profile")SamplingProfile profile
        ) {
            this.profile = profile;
        }
        public GetSamplingProfileResult(ResultBase.FailedResult e) {
            super(e);
            profile = null;
        }
    }
}

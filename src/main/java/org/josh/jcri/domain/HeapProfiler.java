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

/**&lt;No document in protocol.&gt;
<p>From: js_protocol.json</p>
<p>Protocol version: 1.3</p>
<p><strong>Experimental.</strong></p>
 @see Runtime
 @author Joshua*/
@ParametersAreNonnullByDefault public class HeapProfiler extends DomainBase {
    public HeapProfiler(EventCenter evt, WebSocket ws) { super(evt, ws); }

    /**Heap snapshot object id.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class HeapSnapshotObjectId implements CommonDomainType {
        private String _value;
        public HeapSnapshotObjectId() {}
        public HeapSnapshotObjectId(String value) { _value = value; }
        public final HeapSnapshotObjectId value(String value) { _value = value; return this; }
        public final String value() { return _value; }
        public final HeapSnapshotObjectId setValue(String value) { return value(value); }
        public final String getValue() { return value(); }
        @Override public String toString() { return _value; }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (_value == null) throw new IllegalArgumentException("HeapProfiler.HeapSnapshotObjectId.value is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            return strBuilder.append('"').append(DomainBase.escapeQuote(_value)).append('"');
        }
    }

    /**Sampling Heap Profile node. Holds callsite information, allocation statistics and child nodes.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SamplingHeapProfileNode implements CommonDomainType {
        /**Function location.*/
        private Runtime.CallFrame callFrame;
        /**Allocations size in bytes for the node excluding children.*/
        private Double selfSize;
        /**Child nodes.*/
        private List<SamplingHeapProfileNode> children;
        public final SamplingHeapProfileNode callFrame(Runtime.CallFrame callFrame) { this.callFrame = callFrame; return this; }
        public final SamplingHeapProfileNode setCallFrame(Runtime.CallFrame callFrame) { return callFrame(callFrame); }
        public final Runtime.CallFrame callFrame() { return callFrame; }
        public final Runtime.CallFrame getCallFrame() { return callFrame(); }
        public final SamplingHeapProfileNode selfSize(Double selfSize) { this.selfSize = selfSize; return this; }
        public final SamplingHeapProfileNode setSelfSize(Double selfSize) { return selfSize(selfSize); }
        public final Double selfSize() { return selfSize; }
        public final Double getSelfSize() { return selfSize(); }
        public final SamplingHeapProfileNode children(List<SamplingHeapProfileNode> children) { this.children = children; return this; }
        public final SamplingHeapProfileNode setChildren(List<SamplingHeapProfileNode> children) { return children(children); }
        public final List<SamplingHeapProfileNode> children() { return children; }
        public final List<SamplingHeapProfileNode> getChildren() { return children(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (callFrame == null) throw new IllegalArgumentException("HeapProfiler.SamplingHeapProfileNode.callFrame is necessary field.");
            if (selfSize == null) throw new IllegalArgumentException("HeapProfiler.SamplingHeapProfileNode.selfSize is necessary field.");
            if (children == null) throw new IllegalArgumentException("HeapProfiler.SamplingHeapProfileNode.children is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            callFrame.toJson(strBuilder.append("\"callFrame\":"));
            strBuilder.append(",\"selfSize\":").append(selfSize);
                        strBuilder.append(",\"children\":[");
            children.get(0).toJson(strBuilder);
            for (int i = 1; i < children.size(); ++i)
                children.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public SamplingHeapProfileNode() {}
        public SamplingHeapProfileNode(
            @JsonProperty("callFrame")Runtime.CallFrame callFrame,
            @JsonProperty("selfSize")Double selfSize,
            @JsonProperty("children")List<SamplingHeapProfileNode> children
        ) {
            this.callFrame = callFrame;
            this.selfSize = selfSize;
            this.children = children;
        }
    }

    /**Profile.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SamplingHeapProfile implements CommonDomainType {
        /**&lt;No document in protocol.&gt;*/
        private SamplingHeapProfileNode head;
        public final SamplingHeapProfile head(SamplingHeapProfileNode head) { this.head = head; return this; }
        public final SamplingHeapProfile setHead(SamplingHeapProfileNode head) { return head(head); }
        public final SamplingHeapProfileNode head() { return head; }
        public final SamplingHeapProfileNode getHead() { return head(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (head == null) throw new IllegalArgumentException("HeapProfiler.SamplingHeapProfile.head is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            head.toJson(strBuilder.append("\"head\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public SamplingHeapProfile() {}
        public SamplingHeapProfile(
            @JsonProperty("head")SamplingHeapProfileNode head
        ) {
            this.head = head;
        }
    }
    /**Enables console to refer to the node with given id via $x (see Command Line API for more details
$x functions).*/
    public AddInspectedHeapObjectParameter addInspectedHeapObject() { final AddInspectedHeapObjectParameter v = new AddInspectedHeapObjectParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of addInspectedHeapObject.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class AddInspectedHeapObjectParameter extends CommandBase {
        /**Heap snapshot object id to be accessible by means of $x command line API.*/
        private HeapSnapshotObjectId heapObjectId;
        public final AddInspectedHeapObjectParameter heapObjectId(HeapSnapshotObjectId heapObjectId) { this.heapObjectId = heapObjectId; return this; }
        public final AddInspectedHeapObjectParameter setHeapObjectId(HeapSnapshotObjectId heapObjectId) { return heapObjectId(heapObjectId); }
        public final HeapSnapshotObjectId heapObjectId() { return heapObjectId; }
        public final HeapSnapshotObjectId getHeapObjectId() { return heapObjectId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (heapObjectId == null) throw new IllegalArgumentException("HeapProfiler.AddInspectedHeapObjectParameter.heapObjectId is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            heapObjectId.toJson(strBuilder.append("\"heapObjectId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public AddInspectedHeapObjectParameter() {}
        public AddInspectedHeapObjectParameter(
            @JsonProperty("heapObjectId")HeapSnapshotObjectId heapObjectId
        ) {
            this();
            this.heapObjectId = heapObjectId;
        }
        public CompletableFuture<AddInspectedHeapObjectResult> call() {
            return super.call("HeapProfiler.addInspectedHeapObject", AddInspectedHeapObjectResult.class,
                (code, msg)->new AddInspectedHeapObjectResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<AddInspectedHeapObjectResult> call(Executor exec) {
            return super.call("HeapProfiler.addInspectedHeapObject", AddInspectedHeapObjectResult.class,
                (code, msg)->new AddInspectedHeapObjectResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of addInspectedHeapObject.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class AddInspectedHeapObjectResult extends ResultBase {
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
        public AddInspectedHeapObjectResult() { super(); }
        public AddInspectedHeapObjectResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**&lt;No document in protocol.&gt;*/
    public CollectGarbageParameter collectGarbage() { final CollectGarbageParameter v = new CollectGarbageParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of collectGarbage.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class CollectGarbageParameter extends CommandBase {
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
        public CollectGarbageParameter() {}
        public CompletableFuture<CollectGarbageResult> call() {
            return super.call("HeapProfiler.collectGarbage", CollectGarbageResult.class,
                (code, msg)->new CollectGarbageResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<CollectGarbageResult> call(Executor exec) {
            return super.call("HeapProfiler.collectGarbage", CollectGarbageResult.class,
                (code, msg)->new CollectGarbageResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of collectGarbage.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class CollectGarbageResult extends ResultBase {
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
        public CollectGarbageResult() { super(); }
        public CollectGarbageResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**&lt;No document in protocol.&gt;*/
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
            return super.call("HeapProfiler.disable", DisableResult.class,
                (code, msg)->new DisableResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<DisableResult> call(Executor exec) {
            return super.call("HeapProfiler.disable", DisableResult.class,
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
    /**&lt;No document in protocol.&gt;*/
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
            return super.call("HeapProfiler.enable", EnableResult.class,
                (code, msg)->new EnableResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<EnableResult> call(Executor exec) {
            return super.call("HeapProfiler.enable", EnableResult.class,
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
    /**&lt;No document in protocol.&gt;*/
    public GetHeapObjectIdParameter getHeapObjectId() { final GetHeapObjectIdParameter v = new GetHeapObjectIdParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of getHeapObjectId.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetHeapObjectIdParameter extends CommandBase {
        /**Identifier of the object to get heap object id for.*/
        private Runtime.RemoteObjectId objectId;
        public final GetHeapObjectIdParameter objectId(Runtime.RemoteObjectId objectId) { this.objectId = objectId; return this; }
        public final GetHeapObjectIdParameter setObjectId(Runtime.RemoteObjectId objectId) { return objectId(objectId); }
        public final Runtime.RemoteObjectId objectId() { return objectId; }
        public final Runtime.RemoteObjectId getObjectId() { return objectId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (objectId == null) throw new IllegalArgumentException("HeapProfiler.GetHeapObjectIdParameter.objectId is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            objectId.toJson(strBuilder.append("\"objectId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public GetHeapObjectIdParameter() {}
        public GetHeapObjectIdParameter(
            @JsonProperty("objectId")Runtime.RemoteObjectId objectId
        ) {
            this();
            this.objectId = objectId;
        }
        public CompletableFuture<GetHeapObjectIdResult> call() {
            return super.call("HeapProfiler.getHeapObjectId", GetHeapObjectIdResult.class,
                (code, msg)->new GetHeapObjectIdResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetHeapObjectIdResult> call(Executor exec) {
            return super.call("HeapProfiler.getHeapObjectId", GetHeapObjectIdResult.class,
                (code, msg)->new GetHeapObjectIdResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of getHeapObjectId.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetHeapObjectIdResult extends ResultBase {
        /**Id of the heap snapshot object corresponding to the passed remote object id.*/
        private final HeapSnapshotObjectId heapSnapshotObjectId;
        public final HeapSnapshotObjectId heapSnapshotObjectId() { return heapSnapshotObjectId; }
        public final HeapSnapshotObjectId getHeapSnapshotObjectId() { return heapSnapshotObjectId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            heapSnapshotObjectId.toJson(strBuilder.append("\"heapSnapshotObjectId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public GetHeapObjectIdResult(
            @JsonProperty("heapSnapshotObjectId")HeapSnapshotObjectId heapSnapshotObjectId
        ) {
            this.heapSnapshotObjectId = heapSnapshotObjectId;
        }
        public GetHeapObjectIdResult(ResultBase.FailedResult e) {
            super(e);
            heapSnapshotObjectId = null;
        }
    }
    /**&lt;No document in protocol.&gt;*/
    public GetObjectByHeapObjectIdParameter getObjectByHeapObjectId() { final GetObjectByHeapObjectIdParameter v = new GetObjectByHeapObjectIdParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of getObjectByHeapObjectId.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetObjectByHeapObjectIdParameter extends CommandBase {
        /**&lt;No document in protocol.&gt;*/
        private HeapSnapshotObjectId objectId;
        /**Symbolic group name that can be used to release multiple objects.
        <em>Optional.</em>*/
        private String objectGroup;
        public final GetObjectByHeapObjectIdParameter objectId(HeapSnapshotObjectId objectId) { this.objectId = objectId; return this; }
        public final GetObjectByHeapObjectIdParameter setObjectId(HeapSnapshotObjectId objectId) { return objectId(objectId); }
        public final HeapSnapshotObjectId objectId() { return objectId; }
        public final HeapSnapshotObjectId getObjectId() { return objectId(); }
        public final GetObjectByHeapObjectIdParameter objectGroup(@Nullable String objectGroup) { this.objectGroup = objectGroup; return this; }
        public final GetObjectByHeapObjectIdParameter optObjectGroup(@Nullable String objectGroup) { return objectGroup(objectGroup); }
        public final String objectGroup() { return objectGroup; }
        public final String getObjectGroup() { return objectGroup(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (objectId == null) throw new IllegalArgumentException("HeapProfiler.GetObjectByHeapObjectIdParameter.objectId is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            objectId.toJson(strBuilder.append("\"objectId\":"));
            if (objectGroup != null) strBuilder.append(",\"objectGroup\":").append('"').append(DomainBase.escapeQuote(objectGroup)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public GetObjectByHeapObjectIdParameter() {}
        public GetObjectByHeapObjectIdParameter(
            @JsonProperty("objectId")HeapSnapshotObjectId objectId,
            @Nullable @JsonProperty("objectGroup")String objectGroup
        ) {
            this();
            this.objectId = objectId;
            this.objectGroup = objectGroup;
        }
        public CompletableFuture<GetObjectByHeapObjectIdResult> call() {
            return super.call("HeapProfiler.getObjectByHeapObjectId", GetObjectByHeapObjectIdResult.class,
                (code, msg)->new GetObjectByHeapObjectIdResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetObjectByHeapObjectIdResult> call(Executor exec) {
            return super.call("HeapProfiler.getObjectByHeapObjectId", GetObjectByHeapObjectIdResult.class,
                (code, msg)->new GetObjectByHeapObjectIdResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of getObjectByHeapObjectId.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetObjectByHeapObjectIdResult extends ResultBase {
        /**Evaluation result.*/
        private final Runtime.RemoteObject result;
        public final Runtime.RemoteObject result() { return result; }
        public final Runtime.RemoteObject getResult() { return result(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            result.toJson(strBuilder.append("\"result\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public GetObjectByHeapObjectIdResult(
            @JsonProperty("result")Runtime.RemoteObject result
        ) {
            this.result = result;
        }
        public GetObjectByHeapObjectIdResult(ResultBase.FailedResult e) {
            super(e);
            result = null;
        }
    }
    /**&lt;No document in protocol.&gt;*/
    public GetSamplingProfileParameter getSamplingProfile() { final GetSamplingProfileParameter v = new GetSamplingProfileParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of getSamplingProfile.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
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
            return super.call("HeapProfiler.getSamplingProfile", GetSamplingProfileResult.class,
                (code, msg)->new GetSamplingProfileResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetSamplingProfileResult> call(Executor exec) {
            return super.call("HeapProfiler.getSamplingProfile", GetSamplingProfileResult.class,
                (code, msg)->new GetSamplingProfileResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of getSamplingProfile.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetSamplingProfileResult extends ResultBase {
        /**Return the sampling profile being collected.*/
        private final SamplingHeapProfile profile;
        public final SamplingHeapProfile profile() { return profile; }
        public final SamplingHeapProfile getProfile() { return profile(); }
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
            @JsonProperty("profile")SamplingHeapProfile profile
        ) {
            this.profile = profile;
        }
        public GetSamplingProfileResult(ResultBase.FailedResult e) {
            super(e);
            profile = null;
        }
    }
    /**&lt;No document in protocol.&gt;*/
    public StartSamplingParameter startSampling() { final StartSamplingParameter v = new StartSamplingParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of startSampling.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class StartSamplingParameter extends CommandBase {
        /**Average sample interval in bytes. Poisson distribution is used for the intervals. The
default value is 32768 bytes.
        <em>Optional.</em>*/
        private Double samplingInterval;
        public final StartSamplingParameter samplingInterval(@Nullable Double samplingInterval) { this.samplingInterval = samplingInterval; return this; }
        public final StartSamplingParameter optSamplingInterval(@Nullable Double samplingInterval) { return samplingInterval(samplingInterval); }
        public final Double samplingInterval() { return samplingInterval; }
        public final Double getSamplingInterval() { return samplingInterval(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            if (samplingInterval != null) strBuilder.append("\"samplingInterval\":").append(samplingInterval);
            strBuilder.append('}');
            return strBuilder;
        }
        public StartSamplingParameter() {}
        public StartSamplingParameter(
            @Nullable @JsonProperty("samplingInterval")Double samplingInterval
        ) {
            this();
            this.samplingInterval = samplingInterval;
        }
        public CompletableFuture<StartSamplingResult> call() {
            return super.call("HeapProfiler.startSampling", StartSamplingResult.class,
                (code, msg)->new StartSamplingResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<StartSamplingResult> call(Executor exec) {
            return super.call("HeapProfiler.startSampling", StartSamplingResult.class,
                (code, msg)->new StartSamplingResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of startSampling.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
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
    /**&lt;No document in protocol.&gt;*/
    public StartTrackingHeapObjectsParameter startTrackingHeapObjects() { final StartTrackingHeapObjectsParameter v = new StartTrackingHeapObjectsParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of startTrackingHeapObjects.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class StartTrackingHeapObjectsParameter extends CommandBase {
        /**&lt;No document in protocol.&gt;
        <em>Optional.</em>*/
        private Boolean trackAllocations;
        public final StartTrackingHeapObjectsParameter trackAllocations(@Nullable Boolean trackAllocations) { this.trackAllocations = trackAllocations; return this; }
        public final StartTrackingHeapObjectsParameter optTrackAllocations(@Nullable Boolean trackAllocations) { return trackAllocations(trackAllocations); }
        public final Boolean trackAllocations() { return trackAllocations; }
        public final Boolean getTrackAllocations() { return trackAllocations(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            if (trackAllocations != null) strBuilder.append("\"trackAllocations\":").append(trackAllocations);
            strBuilder.append('}');
            return strBuilder;
        }
        public StartTrackingHeapObjectsParameter() {}
        public StartTrackingHeapObjectsParameter(
            @Nullable @JsonProperty("trackAllocations")Boolean trackAllocations
        ) {
            this();
            this.trackAllocations = trackAllocations;
        }
        public CompletableFuture<StartTrackingHeapObjectsResult> call() {
            return super.call("HeapProfiler.startTrackingHeapObjects", StartTrackingHeapObjectsResult.class,
                (code, msg)->new StartTrackingHeapObjectsResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<StartTrackingHeapObjectsResult> call(Executor exec) {
            return super.call("HeapProfiler.startTrackingHeapObjects", StartTrackingHeapObjectsResult.class,
                (code, msg)->new StartTrackingHeapObjectsResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of startTrackingHeapObjects.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class StartTrackingHeapObjectsResult extends ResultBase {
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
        public StartTrackingHeapObjectsResult() { super(); }
        public StartTrackingHeapObjectsResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**&lt;No document in protocol.&gt;*/
    public StopSamplingParameter stopSampling() { final StopSamplingParameter v = new StopSamplingParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of stopSampling.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
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
            return super.call("HeapProfiler.stopSampling", StopSamplingResult.class,
                (code, msg)->new StopSamplingResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<StopSamplingResult> call(Executor exec) {
            return super.call("HeapProfiler.stopSampling", StopSamplingResult.class,
                (code, msg)->new StopSamplingResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of stopSampling.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class StopSamplingResult extends ResultBase {
        /**Recorded sampling heap profile.*/
        private final SamplingHeapProfile profile;
        public final SamplingHeapProfile profile() { return profile; }
        public final SamplingHeapProfile getProfile() { return profile(); }
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
        public StopSamplingResult(
            @JsonProperty("profile")SamplingHeapProfile profile
        ) {
            this.profile = profile;
        }
        public StopSamplingResult(ResultBase.FailedResult e) {
            super(e);
            profile = null;
        }
    }
    /**&lt;No document in protocol.&gt;*/
    public StopTrackingHeapObjectsParameter stopTrackingHeapObjects() { final StopTrackingHeapObjectsParameter v = new StopTrackingHeapObjectsParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of stopTrackingHeapObjects.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class StopTrackingHeapObjectsParameter extends CommandBase {
        /**If true 'reportHeapSnapshotProgress' events will be generated while snapshot is being taken
when the tracking is stopped.
        <em>Optional.</em>*/
        private Boolean reportProgress;
        public final StopTrackingHeapObjectsParameter reportProgress(@Nullable Boolean reportProgress) { this.reportProgress = reportProgress; return this; }
        public final StopTrackingHeapObjectsParameter optReportProgress(@Nullable Boolean reportProgress) { return reportProgress(reportProgress); }
        public final Boolean reportProgress() { return reportProgress; }
        public final Boolean getReportProgress() { return reportProgress(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            if (reportProgress != null) strBuilder.append("\"reportProgress\":").append(reportProgress);
            strBuilder.append('}');
            return strBuilder;
        }
        public StopTrackingHeapObjectsParameter() {}
        public StopTrackingHeapObjectsParameter(
            @Nullable @JsonProperty("reportProgress")Boolean reportProgress
        ) {
            this();
            this.reportProgress = reportProgress;
        }
        public CompletableFuture<StopTrackingHeapObjectsResult> call() {
            return super.call("HeapProfiler.stopTrackingHeapObjects", StopTrackingHeapObjectsResult.class,
                (code, msg)->new StopTrackingHeapObjectsResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<StopTrackingHeapObjectsResult> call(Executor exec) {
            return super.call("HeapProfiler.stopTrackingHeapObjects", StopTrackingHeapObjectsResult.class,
                (code, msg)->new StopTrackingHeapObjectsResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of stopTrackingHeapObjects.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class StopTrackingHeapObjectsResult extends ResultBase {
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
        public StopTrackingHeapObjectsResult() { super(); }
        public StopTrackingHeapObjectsResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**&lt;No document in protocol.&gt;*/
    public TakeHeapSnapshotParameter takeHeapSnapshot() { final TakeHeapSnapshotParameter v = new TakeHeapSnapshotParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of takeHeapSnapshot.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class TakeHeapSnapshotParameter extends CommandBase {
        /**If true 'reportHeapSnapshotProgress' events will be generated while snapshot is being taken.
        <em>Optional.</em>*/
        private Boolean reportProgress;
        public final TakeHeapSnapshotParameter reportProgress(@Nullable Boolean reportProgress) { this.reportProgress = reportProgress; return this; }
        public final TakeHeapSnapshotParameter optReportProgress(@Nullable Boolean reportProgress) { return reportProgress(reportProgress); }
        public final Boolean reportProgress() { return reportProgress; }
        public final Boolean getReportProgress() { return reportProgress(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            if (reportProgress != null) strBuilder.append("\"reportProgress\":").append(reportProgress);
            strBuilder.append('}');
            return strBuilder;
        }
        public TakeHeapSnapshotParameter() {}
        public TakeHeapSnapshotParameter(
            @Nullable @JsonProperty("reportProgress")Boolean reportProgress
        ) {
            this();
            this.reportProgress = reportProgress;
        }
        public CompletableFuture<TakeHeapSnapshotResult> call() {
            return super.call("HeapProfiler.takeHeapSnapshot", TakeHeapSnapshotResult.class,
                (code, msg)->new TakeHeapSnapshotResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<TakeHeapSnapshotResult> call(Executor exec) {
            return super.call("HeapProfiler.takeHeapSnapshot", TakeHeapSnapshotResult.class,
                (code, msg)->new TakeHeapSnapshotResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of takeHeapSnapshot.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class TakeHeapSnapshotResult extends ResultBase {
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
        public TakeHeapSnapshotResult() { super(); }
        public TakeHeapSnapshotResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Event parameter of HeapProfiler.addHeapSnapshotChunk.
     @see #onAddHeapSnapshotChunk*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class AddHeapSnapshotChunkEventParameter implements CommonDomainType {
        /**&lt;No document in protocol.&gt;*/
        private final String chunk;
        public final String chunk() { return chunk; }
        public final String getChunk() { return chunk(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"chunk\":").append('"').append(DomainBase.escapeQuote(chunk)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        AddHeapSnapshotChunkEventParameter(
            @JsonProperty("chunk")String chunk
        ) {
            this.chunk = chunk;
        }
    }
    /**&lt;No document in protocol.&gt;
     @see AddHeapSnapshotChunkEventParameter*/
    public void onAddHeapSnapshotChunk(Consumer<AddHeapSnapshotChunkEventParameter> callback) {
        registerEventCallback("HeapProfiler.addHeapSnapshotChunk", node -> {
            AddHeapSnapshotChunkEventParameter param;
            try { param = EventCenter.deserializeJson(node, AddHeapSnapshotChunkEventParameter.class); }
            catch (IOException e) { e.printStackTrace(); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of HeapProfiler.heapStatsUpdate.
     @see #onHeapStatsUpdate*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class HeapStatsUpdateEventParameter implements CommonDomainType {
        /**An array of triplets. Each triplet describes a fragment. The first integer is the fragment
index, the second integer is a total count of objects for the fragment, the third integer is
a total size of the objects for the fragment.*/
        private final List<Integer> statsUpdate;
        public final List<Integer> statsUpdate() { return statsUpdate; }
        public final List<Integer> getStatsUpdate() { return statsUpdate(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
                        strBuilder.append("\"statsUpdate\":[");
            strBuilder.append(statsUpdate.get(0));
            for (int i = 1; i < statsUpdate.size(); ++i)
                strBuilder.append(',').append(statsUpdate.get(i));
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        HeapStatsUpdateEventParameter(
            @JsonProperty("statsUpdate")List<Integer> statsUpdate
        ) {
            this.statsUpdate = statsUpdate;
        }
    }
    /**If heap objects tracking has been started then backend may send update for one or more fragments
     @see HeapStatsUpdateEventParameter*/
    public void onHeapStatsUpdate(Consumer<HeapStatsUpdateEventParameter> callback) {
        registerEventCallback("HeapProfiler.heapStatsUpdate", node -> {
            HeapStatsUpdateEventParameter param;
            try { param = EventCenter.deserializeJson(node, HeapStatsUpdateEventParameter.class); }
            catch (IOException e) { e.printStackTrace(); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of HeapProfiler.lastSeenObjectId.
     @see #onLastSeenObjectId*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class LastSeenObjectIdEventParameter implements CommonDomainType {
        /**&lt;No document in protocol.&gt;*/
        private final Integer lastSeenObjectId;
        /**&lt;No document in protocol.&gt;*/
        private final Double timestamp;
        public final Integer lastSeenObjectId() { return lastSeenObjectId; }
        public final Integer getLastSeenObjectId() { return lastSeenObjectId(); }
        public final Double timestamp() { return timestamp; }
        public final Double getTimestamp() { return timestamp(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"lastSeenObjectId\":").append(lastSeenObjectId);
            strBuilder.append(",\"timestamp\":").append(timestamp);
            strBuilder.append('}');
            return strBuilder;
        }
        LastSeenObjectIdEventParameter(
            @JsonProperty("lastSeenObjectId")Integer lastSeenObjectId,
            @JsonProperty("timestamp")Double timestamp
        ) {
            this.lastSeenObjectId = lastSeenObjectId;
            this.timestamp = timestamp;
        }
    }
    /**If heap objects tracking has been started then backend regularly sends a current value for last
seen object id and corresponding timestamp. If the were changes in the heap since last event
then one or more heapStatsUpdate events will be sent before a new lastSeenObjectId event.
     @see LastSeenObjectIdEventParameter*/
    public void onLastSeenObjectId(Consumer<LastSeenObjectIdEventParameter> callback) {
        registerEventCallback("HeapProfiler.lastSeenObjectId", node -> {
            LastSeenObjectIdEventParameter param;
            try { param = EventCenter.deserializeJson(node, LastSeenObjectIdEventParameter.class); }
            catch (IOException e) { e.printStackTrace(); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of HeapProfiler.reportHeapSnapshotProgress.
     @see #onReportHeapSnapshotProgress*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ReportHeapSnapshotProgressEventParameter implements CommonDomainType {
        /**&lt;No document in protocol.&gt;*/
        private final Integer done;
        /**&lt;No document in protocol.&gt;*/
        private final Integer total;
        /**&lt;No document in protocol.&gt;
        <em>Optional.</em>*/
        private final Boolean finished;
        public final Integer done() { return done; }
        public final Integer getDone() { return done(); }
        public final Integer total() { return total; }
        public final Integer getTotal() { return total(); }
        public final Boolean finished() { return finished; }
        public final Boolean getFinished() { return finished(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"done\":").append(done);
            strBuilder.append(",\"total\":").append(total);
            if (finished != null) strBuilder.append(",\"finished\":").append(finished);
            strBuilder.append('}');
            return strBuilder;
        }
        ReportHeapSnapshotProgressEventParameter(
            @JsonProperty("done")Integer done,
            @JsonProperty("total")Integer total,
            @Nullable @JsonProperty("finished")Boolean finished
        ) {
            this.done = done;
            this.total = total;
            this.finished = finished;
        }
    }
    /**&lt;No document in protocol.&gt;
     @see ReportHeapSnapshotProgressEventParameter*/
    public void onReportHeapSnapshotProgress(Consumer<ReportHeapSnapshotProgressEventParameter> callback) {
        registerEventCallback("HeapProfiler.reportHeapSnapshotProgress", node -> {
            ReportHeapSnapshotProgressEventParameter param;
            try { param = EventCenter.deserializeJson(node, ReportHeapSnapshotProgressEventParameter.class); }
            catch (IOException e) { e.printStackTrace(); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of HeapProfiler.resetProfiles.
     @see #onResetProfiles*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ResetProfilesEventParameter implements CommonDomainType {
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
        public ResetProfilesEventParameter() {}
    }
    /**&lt;No document in protocol.&gt;
     @see ResetProfilesEventParameter*/
    public void onResetProfiles(Consumer<ResetProfilesEventParameter> callback) {
        registerEventCallback("HeapProfiler.resetProfiles", node -> {
            ResetProfilesEventParameter param;
            try { param = EventCenter.deserializeJson(node, ResetProfilesEventParameter.class); }
            catch (IOException e) { e.printStackTrace(); return; }
            callback.accept(param);
        });
    }
}

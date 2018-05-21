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
 @see Runtime
 @see Debugger
 @author Joshua*/
@ParametersAreNonnullByDefault public class Profiler extends DomainBase {
    public Profiler(EventCenter evt, WebSocket ws) { super(evt, ws); }

    /**Profile node. Holds callsite information, execution statistics and child nodes.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ProfileNode implements CommonDomainType {
        /**Unique id of the node.*/
        private Integer id;
        /**Function location.*/
        private Runtime.CallFrame callFrame;
        /**Number of samples where this node was on top of the call stack.
        <em>Optional.</em>*/
        private Integer hitCount;
        /**Child node ids.
        <em>Optional.</em>*/
        private List<Integer> children;
        /**The reason of being not optimized. The function may be deoptimized or marked as don't
optimize.
        <em>Optional.</em>*/
        private String deoptReason;
        /**An array of source position ticks.
        <em>Optional.</em>*/
        private List<PositionTickInfo> positionTicks;
        public final ProfileNode id(Integer id) { this.id = id; return this; }
        public final ProfileNode setId(Integer id) { return id(id); }
        public final Integer id() { return id; }
        public final Integer getId() { return id(); }
        public final ProfileNode callFrame(Runtime.CallFrame callFrame) { this.callFrame = callFrame; return this; }
        public final ProfileNode setCallFrame(Runtime.CallFrame callFrame) { return callFrame(callFrame); }
        public final Runtime.CallFrame callFrame() { return callFrame; }
        public final Runtime.CallFrame getCallFrame() { return callFrame(); }
        public final ProfileNode hitCount(@Nullable Integer hitCount) { this.hitCount = hitCount; return this; }
        public final ProfileNode optHitCount(@Nullable Integer hitCount) { return hitCount(hitCount); }
        public final Integer hitCount() { return hitCount; }
        public final Integer getHitCount() { return hitCount(); }
        public final ProfileNode children(@Nullable List<Integer> children) { this.children = children; return this; }
        public final ProfileNode optChildren(@Nullable List<Integer> children) { return children(children); }
        public final List<Integer> children() { return children; }
        public final List<Integer> getChildren() { return children(); }
        public final ProfileNode deoptReason(@Nullable String deoptReason) { this.deoptReason = deoptReason; return this; }
        public final ProfileNode optDeoptReason(@Nullable String deoptReason) { return deoptReason(deoptReason); }
        public final String deoptReason() { return deoptReason; }
        public final String getDeoptReason() { return deoptReason(); }
        public final ProfileNode positionTicks(@Nullable List<PositionTickInfo> positionTicks) { this.positionTicks = positionTicks; return this; }
        public final ProfileNode optPositionTicks(@Nullable List<PositionTickInfo> positionTicks) { return positionTicks(positionTicks); }
        public final List<PositionTickInfo> positionTicks() { return positionTicks; }
        public final List<PositionTickInfo> getPositionTicks() { return positionTicks(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (id == null) throw new IllegalArgumentException("Profiler.ProfileNode.id is necessary field.");
            if (callFrame == null) throw new IllegalArgumentException("Profiler.ProfileNode.callFrame is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"id\":").append(id);
            callFrame.toJson(strBuilder.append(",\"callFrame\":"));
            if (hitCount != null) strBuilder.append(",\"hitCount\":").append(hitCount);
            if (children != null) {
                strBuilder.append(",\"children\":[");
                strBuilder.append(children.get(0));
                for (int i = 1; i < children.size(); ++i)
                    strBuilder.append(',').append(children.get(i));
                strBuilder.append(']');
            }
            if (deoptReason != null) strBuilder.append(",\"deoptReason\":").append('"').append(DomainBase.escapeJson(deoptReason)).append('"');
            if (positionTicks != null) {
                strBuilder.append(",\"positionTicks\":[");
                positionTicks.get(0).toJson(strBuilder);
                for (int i = 1; i < positionTicks.size(); ++i)
                    positionTicks.get(i).toJson(strBuilder.append(','));
                strBuilder.append(']');
            }
            strBuilder.append('}');
            return strBuilder;
        }
        public ProfileNode() {}
        public ProfileNode(
            @JsonProperty("id")Integer id,
            @JsonProperty("callFrame")Runtime.CallFrame callFrame,
            @Nullable @JsonProperty("hitCount")Integer hitCount,
            @Nullable @JsonProperty("children")List<Integer> children,
            @Nullable @JsonProperty("deoptReason")String deoptReason,
            @Nullable @JsonProperty("positionTicks")List<PositionTickInfo> positionTicks
        ) {
            this.id = id;
            this.callFrame = callFrame;
            this.hitCount = hitCount;
            this.children = children;
            this.deoptReason = deoptReason;
            this.positionTicks = positionTicks;
        }
    }

    /**Profile.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class Profile implements CommonDomainType {
        /**The list of profile nodes. First item is the root node.*/
        private List<ProfileNode> nodes;
        /**Profiling start timestamp in microseconds.*/
        private Double startTime;
        /**Profiling end timestamp in microseconds.*/
        private Double endTime;
        /**Ids of samples top nodes.
        <em>Optional.</em>*/
        private List<Integer> samples;
        /**Time intervals between adjacent samples in microseconds. The first delta is relative to the
profile startTime.
        <em>Optional.</em>*/
        private List<Integer> timeDeltas;
        public final Profile nodes(List<ProfileNode> nodes) { this.nodes = nodes; return this; }
        public final Profile setNodes(List<ProfileNode> nodes) { return nodes(nodes); }
        public final List<ProfileNode> nodes() { return nodes; }
        public final List<ProfileNode> getNodes() { return nodes(); }
        public final Profile startTime(Double startTime) { this.startTime = startTime; return this; }
        public final Profile setStartTime(Double startTime) { return startTime(startTime); }
        public final Double startTime() { return startTime; }
        public final Double getStartTime() { return startTime(); }
        public final Profile endTime(Double endTime) { this.endTime = endTime; return this; }
        public final Profile setEndTime(Double endTime) { return endTime(endTime); }
        public final Double endTime() { return endTime; }
        public final Double getEndTime() { return endTime(); }
        public final Profile samples(@Nullable List<Integer> samples) { this.samples = samples; return this; }
        public final Profile optSamples(@Nullable List<Integer> samples) { return samples(samples); }
        public final List<Integer> samples() { return samples; }
        public final List<Integer> getSamples() { return samples(); }
        public final Profile timeDeltas(@Nullable List<Integer> timeDeltas) { this.timeDeltas = timeDeltas; return this; }
        public final Profile optTimeDeltas(@Nullable List<Integer> timeDeltas) { return timeDeltas(timeDeltas); }
        public final List<Integer> timeDeltas() { return timeDeltas; }
        public final List<Integer> getTimeDeltas() { return timeDeltas(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (nodes == null) throw new IllegalArgumentException("Profiler.Profile.nodes is necessary field.");
            if (startTime == null) throw new IllegalArgumentException("Profiler.Profile.startTime is necessary field.");
            if (endTime == null) throw new IllegalArgumentException("Profiler.Profile.endTime is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
                        strBuilder.append("\"nodes\":[");
            nodes.get(0).toJson(strBuilder);
            for (int i = 1; i < nodes.size(); ++i)
                nodes.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            strBuilder.append(",\"startTime\":").append(startTime);
            strBuilder.append(",\"endTime\":").append(endTime);
            if (samples != null) {
                strBuilder.append(",\"samples\":[");
                strBuilder.append(samples.get(0));
                for (int i = 1; i < samples.size(); ++i)
                    strBuilder.append(',').append(samples.get(i));
                strBuilder.append(']');
            }
            if (timeDeltas != null) {
                strBuilder.append(",\"timeDeltas\":[");
                strBuilder.append(timeDeltas.get(0));
                for (int i = 1; i < timeDeltas.size(); ++i)
                    strBuilder.append(',').append(timeDeltas.get(i));
                strBuilder.append(']');
            }
            strBuilder.append('}');
            return strBuilder;
        }
        public Profile() {}
        public Profile(
            @JsonProperty("nodes")List<ProfileNode> nodes,
            @JsonProperty("startTime")Double startTime,
            @JsonProperty("endTime")Double endTime,
            @Nullable @JsonProperty("samples")List<Integer> samples,
            @Nullable @JsonProperty("timeDeltas")List<Integer> timeDeltas
        ) {
            this.nodes = nodes;
            this.startTime = startTime;
            this.endTime = endTime;
            this.samples = samples;
            this.timeDeltas = timeDeltas;
        }
    }

    /**Specifies a number of samples attributed to a certain source position.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class PositionTickInfo implements CommonDomainType {
        /**Source line number (1-based).*/
        private Integer line;
        /**Number of samples attributed to the source line.*/
        private Integer ticks;
        public final PositionTickInfo line(Integer line) { this.line = line; return this; }
        public final PositionTickInfo setLine(Integer line) { return line(line); }
        public final Integer line() { return line; }
        public final Integer getLine() { return line(); }
        public final PositionTickInfo ticks(Integer ticks) { this.ticks = ticks; return this; }
        public final PositionTickInfo setTicks(Integer ticks) { return ticks(ticks); }
        public final Integer ticks() { return ticks; }
        public final Integer getTicks() { return ticks(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (line == null) throw new IllegalArgumentException("Profiler.PositionTickInfo.line is necessary field.");
            if (ticks == null) throw new IllegalArgumentException("Profiler.PositionTickInfo.ticks is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"line\":").append(line);
            strBuilder.append(",\"ticks\":").append(ticks);
            strBuilder.append('}');
            return strBuilder;
        }
        public PositionTickInfo() {}
        public PositionTickInfo(
            @JsonProperty("line")Integer line,
            @JsonProperty("ticks")Integer ticks
        ) {
            this.line = line;
            this.ticks = ticks;
        }
    }

    /**Coverage data for a source range.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class CoverageRange implements CommonDomainType {
        /**JavaScript script source offset for the range start.*/
        private Integer startOffset;
        /**JavaScript script source offset for the range end.*/
        private Integer endOffset;
        /**Collected execution count of the source range.*/
        private Integer count;
        public final CoverageRange startOffset(Integer startOffset) { this.startOffset = startOffset; return this; }
        public final CoverageRange setStartOffset(Integer startOffset) { return startOffset(startOffset); }
        public final Integer startOffset() { return startOffset; }
        public final Integer getStartOffset() { return startOffset(); }
        public final CoverageRange endOffset(Integer endOffset) { this.endOffset = endOffset; return this; }
        public final CoverageRange setEndOffset(Integer endOffset) { return endOffset(endOffset); }
        public final Integer endOffset() { return endOffset; }
        public final Integer getEndOffset() { return endOffset(); }
        public final CoverageRange count(Integer count) { this.count = count; return this; }
        public final CoverageRange setCount(Integer count) { return count(count); }
        public final Integer count() { return count; }
        public final Integer getCount() { return count(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (startOffset == null) throw new IllegalArgumentException("Profiler.CoverageRange.startOffset is necessary field.");
            if (endOffset == null) throw new IllegalArgumentException("Profiler.CoverageRange.endOffset is necessary field.");
            if (count == null) throw new IllegalArgumentException("Profiler.CoverageRange.count is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"startOffset\":").append(startOffset);
            strBuilder.append(",\"endOffset\":").append(endOffset);
            strBuilder.append(",\"count\":").append(count);
            strBuilder.append('}');
            return strBuilder;
        }
        public CoverageRange() {}
        public CoverageRange(
            @JsonProperty("startOffset")Integer startOffset,
            @JsonProperty("endOffset")Integer endOffset,
            @JsonProperty("count")Integer count
        ) {
            this.startOffset = startOffset;
            this.endOffset = endOffset;
            this.count = count;
        }
    }

    /**Coverage data for a JavaScript function.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class FunctionCoverage implements CommonDomainType {
        /**JavaScript function name.*/
        private String functionName;
        /**Source ranges inside the function with coverage data.*/
        private List<CoverageRange> ranges;
        /**Whether coverage data for this function has block granularity.*/
        private Boolean isBlockCoverage;
        public final FunctionCoverage functionName(String functionName) { this.functionName = functionName; return this; }
        public final FunctionCoverage setFunctionName(String functionName) { return functionName(functionName); }
        public final String functionName() { return functionName; }
        public final String getFunctionName() { return functionName(); }
        public final FunctionCoverage ranges(List<CoverageRange> ranges) { this.ranges = ranges; return this; }
        public final FunctionCoverage setRanges(List<CoverageRange> ranges) { return ranges(ranges); }
        public final List<CoverageRange> ranges() { return ranges; }
        public final List<CoverageRange> getRanges() { return ranges(); }
        public final FunctionCoverage isBlockCoverage(Boolean isBlockCoverage) { this.isBlockCoverage = isBlockCoverage; return this; }
        public final FunctionCoverage setIsBlockCoverage(Boolean isBlockCoverage) { return isBlockCoverage(isBlockCoverage); }
        public final Boolean isBlockCoverage() { return isBlockCoverage; }
        public final Boolean getIsBlockCoverage() { return isBlockCoverage(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (functionName == null) throw new IllegalArgumentException("Profiler.FunctionCoverage.functionName is necessary field.");
            if (ranges == null) throw new IllegalArgumentException("Profiler.FunctionCoverage.ranges is necessary field.");
            if (isBlockCoverage == null) throw new IllegalArgumentException("Profiler.FunctionCoverage.isBlockCoverage is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"functionName\":").append('"').append(DomainBase.escapeJson(functionName)).append('"');
                        strBuilder.append(",\"ranges\":[");
            ranges.get(0).toJson(strBuilder);
            for (int i = 1; i < ranges.size(); ++i)
                ranges.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            strBuilder.append(",\"isBlockCoverage\":").append(isBlockCoverage);
            strBuilder.append('}');
            return strBuilder;
        }
        public FunctionCoverage() {}
        public FunctionCoverage(
            @JsonProperty("functionName")String functionName,
            @JsonProperty("ranges")List<CoverageRange> ranges,
            @JsonProperty("isBlockCoverage")Boolean isBlockCoverage
        ) {
            this.functionName = functionName;
            this.ranges = ranges;
            this.isBlockCoverage = isBlockCoverage;
        }
    }

    /**Coverage data for a JavaScript script.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ScriptCoverage implements CommonDomainType {
        /**JavaScript script id.*/
        private Runtime.ScriptId scriptId;
        /**JavaScript script name or url.*/
        private String url;
        /**Functions contained in the script that has coverage data.*/
        private List<FunctionCoverage> functions;
        public final ScriptCoverage scriptId(Runtime.ScriptId scriptId) { this.scriptId = scriptId; return this; }
        public final ScriptCoverage setScriptId(Runtime.ScriptId scriptId) { return scriptId(scriptId); }
        public final Runtime.ScriptId scriptId() { return scriptId; }
        public final Runtime.ScriptId getScriptId() { return scriptId(); }
        public final ScriptCoverage url(String url) { this.url = url; return this; }
        public final ScriptCoverage setUrl(String url) { return url(url); }
        public final String url() { return url; }
        public final String getUrl() { return url(); }
        public final ScriptCoverage functions(List<FunctionCoverage> functions) { this.functions = functions; return this; }
        public final ScriptCoverage setFunctions(List<FunctionCoverage> functions) { return functions(functions); }
        public final List<FunctionCoverage> functions() { return functions; }
        public final List<FunctionCoverage> getFunctions() { return functions(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (scriptId == null) throw new IllegalArgumentException("Profiler.ScriptCoverage.scriptId is necessary field.");
            if (url == null) throw new IllegalArgumentException("Profiler.ScriptCoverage.url is necessary field.");
            if (functions == null) throw new IllegalArgumentException("Profiler.ScriptCoverage.functions is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            scriptId.toJson(strBuilder.append("\"scriptId\":"));
            strBuilder.append(",\"url\":").append('"').append(DomainBase.escapeJson(url)).append('"');
                        strBuilder.append(",\"functions\":[");
            functions.get(0).toJson(strBuilder);
            for (int i = 1; i < functions.size(); ++i)
                functions.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public ScriptCoverage() {}
        public ScriptCoverage(
            @JsonProperty("scriptId")Runtime.ScriptId scriptId,
            @JsonProperty("url")String url,
            @JsonProperty("functions")List<FunctionCoverage> functions
        ) {
            this.scriptId = scriptId;
            this.url = url;
            this.functions = functions;
        }
    }

    /**Describes a type collected during runtime.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class TypeObject implements CommonDomainType {
        /**Name of a type collected with type profiling.*/
        private String name;
        public final TypeObject name(String name) { this.name = name; return this; }
        public final TypeObject setName(String name) { return name(name); }
        public final String name() { return name; }
        public final String getName() { return name(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (name == null) throw new IllegalArgumentException("Profiler.TypeObject.name is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"name\":").append('"').append(DomainBase.escapeJson(name)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public TypeObject() {}
        public TypeObject(
            @JsonProperty("name")String name
        ) {
            this.name = name;
        }
    }

    /**Source offset and types for a parameter or return value.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class TypeProfileEntry implements CommonDomainType {
        /**Source offset of the parameter or end of function for return values.*/
        private Integer offset;
        /**The types for this parameter or return value.*/
        private List<TypeObject> types;
        public final TypeProfileEntry offset(Integer offset) { this.offset = offset; return this; }
        public final TypeProfileEntry setOffset(Integer offset) { return offset(offset); }
        public final Integer offset() { return offset; }
        public final Integer getOffset() { return offset(); }
        public final TypeProfileEntry types(List<TypeObject> types) { this.types = types; return this; }
        public final TypeProfileEntry setTypes(List<TypeObject> types) { return types(types); }
        public final List<TypeObject> types() { return types; }
        public final List<TypeObject> getTypes() { return types(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (offset == null) throw new IllegalArgumentException("Profiler.TypeProfileEntry.offset is necessary field.");
            if (types == null) throw new IllegalArgumentException("Profiler.TypeProfileEntry.types is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"offset\":").append(offset);
                        strBuilder.append(",\"types\":[");
            types.get(0).toJson(strBuilder);
            for (int i = 1; i < types.size(); ++i)
                types.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public TypeProfileEntry() {}
        public TypeProfileEntry(
            @JsonProperty("offset")Integer offset,
            @JsonProperty("types")List<TypeObject> types
        ) {
            this.offset = offset;
            this.types = types;
        }
    }

    /**Type profile data collected during runtime for a JavaScript script.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ScriptTypeProfile implements CommonDomainType {
        /**JavaScript script id.*/
        private Runtime.ScriptId scriptId;
        /**JavaScript script name or url.*/
        private String url;
        /**Type profile entries for parameters and return values of the functions in the script.*/
        private List<TypeProfileEntry> entries;
        public final ScriptTypeProfile scriptId(Runtime.ScriptId scriptId) { this.scriptId = scriptId; return this; }
        public final ScriptTypeProfile setScriptId(Runtime.ScriptId scriptId) { return scriptId(scriptId); }
        public final Runtime.ScriptId scriptId() { return scriptId; }
        public final Runtime.ScriptId getScriptId() { return scriptId(); }
        public final ScriptTypeProfile url(String url) { this.url = url; return this; }
        public final ScriptTypeProfile setUrl(String url) { return url(url); }
        public final String url() { return url; }
        public final String getUrl() { return url(); }
        public final ScriptTypeProfile entries(List<TypeProfileEntry> entries) { this.entries = entries; return this; }
        public final ScriptTypeProfile setEntries(List<TypeProfileEntry> entries) { return entries(entries); }
        public final List<TypeProfileEntry> entries() { return entries; }
        public final List<TypeProfileEntry> getEntries() { return entries(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (scriptId == null) throw new IllegalArgumentException("Profiler.ScriptTypeProfile.scriptId is necessary field.");
            if (url == null) throw new IllegalArgumentException("Profiler.ScriptTypeProfile.url is necessary field.");
            if (entries == null) throw new IllegalArgumentException("Profiler.ScriptTypeProfile.entries is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            scriptId.toJson(strBuilder.append("\"scriptId\":"));
            strBuilder.append(",\"url\":").append('"').append(DomainBase.escapeJson(url)).append('"');
                        strBuilder.append(",\"entries\":[");
            entries.get(0).toJson(strBuilder);
            for (int i = 1; i < entries.size(); ++i)
                entries.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public ScriptTypeProfile() {}
        public ScriptTypeProfile(
            @JsonProperty("scriptId")Runtime.ScriptId scriptId,
            @JsonProperty("url")String url,
            @JsonProperty("entries")List<TypeProfileEntry> entries
        ) {
            this.scriptId = scriptId;
            this.url = url;
            this.entries = entries;
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
            return super.call("Profiler.disable", DisableResult.class,
                (code, msg)->new DisableResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<DisableResult> call(Executor exec) {
            return super.call("Profiler.disable", DisableResult.class,
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
            return super.call("Profiler.enable", EnableResult.class,
                (code, msg)->new EnableResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<EnableResult> call(Executor exec) {
            return super.call("Profiler.enable", EnableResult.class,
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
    /**Collect coverage data for the current isolate. The coverage data may be incomplete due to
garbage collection.*/
    public GetBestEffortCoverageParameter getBestEffortCoverage() { final GetBestEffortCoverageParameter v = new GetBestEffortCoverageParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of getBestEffortCoverage.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetBestEffortCoverageParameter extends CommandBase {
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
        public GetBestEffortCoverageParameter() {}
        public CompletableFuture<GetBestEffortCoverageResult> call() {
            return super.call("Profiler.getBestEffortCoverage", GetBestEffortCoverageResult.class,
                (code, msg)->new GetBestEffortCoverageResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetBestEffortCoverageResult> call(Executor exec) {
            return super.call("Profiler.getBestEffortCoverage", GetBestEffortCoverageResult.class,
                (code, msg)->new GetBestEffortCoverageResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of getBestEffortCoverage.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetBestEffortCoverageResult extends ResultBase {
        /**Coverage data for the current isolate.*/
        private final List<ScriptCoverage> result;
        public final List<ScriptCoverage> result() { return result; }
        public final List<ScriptCoverage> getResult() { return result(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
                        strBuilder.append("\"result\":[");
            result.get(0).toJson(strBuilder);
            for (int i = 1; i < result.size(); ++i)
                result.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public GetBestEffortCoverageResult(
            @JsonProperty("result")List<ScriptCoverage> result
        ) {
            this.result = result;
        }
        public GetBestEffortCoverageResult(ResultBase.FailedResult e) {
            super(e);
            result = null;
        }
    }
    /**Changes CPU profiler sampling interval. Must be called before CPU profiles recording started.*/
    public SetSamplingIntervalParameter setSamplingInterval() { final SetSamplingIntervalParameter v = new SetSamplingIntervalParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setSamplingInterval.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetSamplingIntervalParameter extends CommandBase {
        /**New sampling interval in microseconds.*/
        private Integer interval;
        public final SetSamplingIntervalParameter interval(Integer interval) { this.interval = interval; return this; }
        public final SetSamplingIntervalParameter setInterval(Integer interval) { return interval(interval); }
        public final Integer interval() { return interval; }
        public final Integer getInterval() { return interval(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (interval == null) throw new IllegalArgumentException("Profiler.SetSamplingIntervalParameter.interval is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"interval\":").append(interval);
            strBuilder.append('}');
            return strBuilder;
        }
        public SetSamplingIntervalParameter() {}
        public SetSamplingIntervalParameter(
            @JsonProperty("interval")Integer interval
        ) {
            this();
            this.interval = interval;
        }
        public CompletableFuture<SetSamplingIntervalResult> call() {
            return super.call("Profiler.setSamplingInterval", SetSamplingIntervalResult.class,
                (code, msg)->new SetSamplingIntervalResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetSamplingIntervalResult> call(Executor exec) {
            return super.call("Profiler.setSamplingInterval", SetSamplingIntervalResult.class,
                (code, msg)->new SetSamplingIntervalResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setSamplingInterval.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetSamplingIntervalResult extends ResultBase {
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
        public SetSamplingIntervalResult() { super(); }
        public SetSamplingIntervalResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**&lt;No document in protocol.&gt;*/
    public StartParameter start() { final StartParameter v = new StartParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of start.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class StartParameter extends CommandBase {
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
        public StartParameter() {}
        public CompletableFuture<StartResult> call() {
            return super.call("Profiler.start", StartResult.class,
                (code, msg)->new StartResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<StartResult> call(Executor exec) {
            return super.call("Profiler.start", StartResult.class,
                (code, msg)->new StartResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of start.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
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
    /**Enable precise code coverage. Coverage data for JavaScript executed before enabling precise code
coverage may be incomplete. Enabling prevents running optimized code and resets execution
counters.*/
    public StartPreciseCoverageParameter startPreciseCoverage() { final StartPreciseCoverageParameter v = new StartPreciseCoverageParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of startPreciseCoverage.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class StartPreciseCoverageParameter extends CommandBase {
        /**Collect accurate call counts beyond simple 'covered' or 'not covered'.
        <em>Optional.</em>*/
        private Boolean callCount;
        /**Collect block-based coverage.
        <em>Optional.</em>*/
        private Boolean detailed;
        public final StartPreciseCoverageParameter callCount(@Nullable Boolean callCount) { this.callCount = callCount; return this; }
        public final StartPreciseCoverageParameter optCallCount(@Nullable Boolean callCount) { return callCount(callCount); }
        public final Boolean callCount() { return callCount; }
        public final Boolean getCallCount() { return callCount(); }
        public final StartPreciseCoverageParameter detailed(@Nullable Boolean detailed) { this.detailed = detailed; return this; }
        public final StartPreciseCoverageParameter optDetailed(@Nullable Boolean detailed) { return detailed(detailed); }
        public final Boolean detailed() { return detailed; }
        public final Boolean getDetailed() { return detailed(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            if (callCount != null) strBuilder.append("\"callCount\":").append(callCount);
            if (detailed != null) strBuilder.append(",\"detailed\":").append(detailed);
            strBuilder.append('}');
            return strBuilder;
        }
        public StartPreciseCoverageParameter() {}
        public StartPreciseCoverageParameter(
            @Nullable @JsonProperty("callCount")Boolean callCount,
            @Nullable @JsonProperty("detailed")Boolean detailed
        ) {
            this();
            this.callCount = callCount;
            this.detailed = detailed;
        }
        public CompletableFuture<StartPreciseCoverageResult> call() {
            return super.call("Profiler.startPreciseCoverage", StartPreciseCoverageResult.class,
                (code, msg)->new StartPreciseCoverageResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<StartPreciseCoverageResult> call(Executor exec) {
            return super.call("Profiler.startPreciseCoverage", StartPreciseCoverageResult.class,
                (code, msg)->new StartPreciseCoverageResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of startPreciseCoverage.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class StartPreciseCoverageResult extends ResultBase {
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
        public StartPreciseCoverageResult() { super(); }
        public StartPreciseCoverageResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Enable type profile.
    <p><strong>Experimental.</strong></p>*/
    public StartTypeProfileParameter startTypeProfile() { final StartTypeProfileParameter v = new StartTypeProfileParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of startTypeProfile.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class StartTypeProfileParameter extends CommandBase {
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
        public StartTypeProfileParameter() {}
        public CompletableFuture<StartTypeProfileResult> call() {
            return super.call("Profiler.startTypeProfile", StartTypeProfileResult.class,
                (code, msg)->new StartTypeProfileResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<StartTypeProfileResult> call(Executor exec) {
            return super.call("Profiler.startTypeProfile", StartTypeProfileResult.class,
                (code, msg)->new StartTypeProfileResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of startTypeProfile.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class StartTypeProfileResult extends ResultBase {
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
        public StartTypeProfileResult() { super(); }
        public StartTypeProfileResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**&lt;No document in protocol.&gt;*/
    public StopParameter stop() { final StopParameter v = new StopParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of stop.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class StopParameter extends CommandBase {
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
        public StopParameter() {}
        public CompletableFuture<StopResult> call() {
            return super.call("Profiler.stop", StopResult.class,
                (code, msg)->new StopResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<StopResult> call(Executor exec) {
            return super.call("Profiler.stop", StopResult.class,
                (code, msg)->new StopResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of stop.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class StopResult extends ResultBase {
        /**Recorded profile.*/
        private final Profile profile;
        public final Profile profile() { return profile; }
        public final Profile getProfile() { return profile(); }
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
        public StopResult(
            @JsonProperty("profile")Profile profile
        ) {
            this.profile = profile;
        }
        public StopResult(ResultBase.FailedResult e) {
            super(e);
            profile = null;
        }
    }
    /**Disable precise code coverage. Disabling releases unnecessary execution count records and allows
executing optimized code.*/
    public StopPreciseCoverageParameter stopPreciseCoverage() { final StopPreciseCoverageParameter v = new StopPreciseCoverageParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of stopPreciseCoverage.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class StopPreciseCoverageParameter extends CommandBase {
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
        public StopPreciseCoverageParameter() {}
        public CompletableFuture<StopPreciseCoverageResult> call() {
            return super.call("Profiler.stopPreciseCoverage", StopPreciseCoverageResult.class,
                (code, msg)->new StopPreciseCoverageResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<StopPreciseCoverageResult> call(Executor exec) {
            return super.call("Profiler.stopPreciseCoverage", StopPreciseCoverageResult.class,
                (code, msg)->new StopPreciseCoverageResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of stopPreciseCoverage.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class StopPreciseCoverageResult extends ResultBase {
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
        public StopPreciseCoverageResult() { super(); }
        public StopPreciseCoverageResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Disable type profile. Disabling releases type profile data collected so far.
    <p><strong>Experimental.</strong></p>*/
    public StopTypeProfileParameter stopTypeProfile() { final StopTypeProfileParameter v = new StopTypeProfileParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of stopTypeProfile.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class StopTypeProfileParameter extends CommandBase {
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
        public StopTypeProfileParameter() {}
        public CompletableFuture<StopTypeProfileResult> call() {
            return super.call("Profiler.stopTypeProfile", StopTypeProfileResult.class,
                (code, msg)->new StopTypeProfileResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<StopTypeProfileResult> call(Executor exec) {
            return super.call("Profiler.stopTypeProfile", StopTypeProfileResult.class,
                (code, msg)->new StopTypeProfileResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of stopTypeProfile.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class StopTypeProfileResult extends ResultBase {
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
        public StopTypeProfileResult() { super(); }
        public StopTypeProfileResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Collect coverage data for the current isolate, and resets execution counters. Precise code
coverage needs to have started.*/
    public TakePreciseCoverageParameter takePreciseCoverage() { final TakePreciseCoverageParameter v = new TakePreciseCoverageParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of takePreciseCoverage.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class TakePreciseCoverageParameter extends CommandBase {
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
        public TakePreciseCoverageParameter() {}
        public CompletableFuture<TakePreciseCoverageResult> call() {
            return super.call("Profiler.takePreciseCoverage", TakePreciseCoverageResult.class,
                (code, msg)->new TakePreciseCoverageResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<TakePreciseCoverageResult> call(Executor exec) {
            return super.call("Profiler.takePreciseCoverage", TakePreciseCoverageResult.class,
                (code, msg)->new TakePreciseCoverageResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of takePreciseCoverage.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class TakePreciseCoverageResult extends ResultBase {
        /**Coverage data for the current isolate.*/
        private final List<ScriptCoverage> result;
        public final List<ScriptCoverage> result() { return result; }
        public final List<ScriptCoverage> getResult() { return result(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
                        strBuilder.append("\"result\":[");
            result.get(0).toJson(strBuilder);
            for (int i = 1; i < result.size(); ++i)
                result.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public TakePreciseCoverageResult(
            @JsonProperty("result")List<ScriptCoverage> result
        ) {
            this.result = result;
        }
        public TakePreciseCoverageResult(ResultBase.FailedResult e) {
            super(e);
            result = null;
        }
    }
    /**Collect type profile.
    <p><strong>Experimental.</strong></p>*/
    public TakeTypeProfileParameter takeTypeProfile() { final TakeTypeProfileParameter v = new TakeTypeProfileParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of takeTypeProfile.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class TakeTypeProfileParameter extends CommandBase {
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
        public TakeTypeProfileParameter() {}
        public CompletableFuture<TakeTypeProfileResult> call() {
            return super.call("Profiler.takeTypeProfile", TakeTypeProfileResult.class,
                (code, msg)->new TakeTypeProfileResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<TakeTypeProfileResult> call(Executor exec) {
            return super.call("Profiler.takeTypeProfile", TakeTypeProfileResult.class,
                (code, msg)->new TakeTypeProfileResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of takeTypeProfile.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class TakeTypeProfileResult extends ResultBase {
        /**Type profile for all scripts since startTypeProfile() was turned on.*/
        private final List<ScriptTypeProfile> result;
        public final List<ScriptTypeProfile> result() { return result; }
        public final List<ScriptTypeProfile> getResult() { return result(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
                        strBuilder.append("\"result\":[");
            result.get(0).toJson(strBuilder);
            for (int i = 1; i < result.size(); ++i)
                result.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public TakeTypeProfileResult(
            @JsonProperty("result")List<ScriptTypeProfile> result
        ) {
            this.result = result;
        }
        public TakeTypeProfileResult(ResultBase.FailedResult e) {
            super(e);
            result = null;
        }
    }
    /**Event parameter of Profiler.consoleProfileFinished.
     @see #onConsoleProfileFinished*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ConsoleProfileFinishedEventParameter implements CommonDomainType {
        /**&lt;No document in protocol.&gt;*/
        private final String id;
        /**Location of console.profileEnd().*/
        private final Debugger.Location location;
        /**&lt;No document in protocol.&gt;*/
        private final Profile profile;
        /**Profile title passed as an argument to console.profile().
        <em>Optional.</em>*/
        private final String title;
        public final String id() { return id; }
        public final String getId() { return id(); }
        public final Debugger.Location location() { return location; }
        public final Debugger.Location getLocation() { return location(); }
        public final Profile profile() { return profile; }
        public final Profile getProfile() { return profile(); }
        public final String title() { return title; }
        public final String getTitle() { return title(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"id\":").append('"').append(DomainBase.escapeJson(id)).append('"');
            location.toJson(strBuilder.append(",\"location\":"));
            profile.toJson(strBuilder.append(",\"profile\":"));
            if (title != null) strBuilder.append(",\"title\":").append('"').append(DomainBase.escapeJson(title)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        ConsoleProfileFinishedEventParameter(
            @JsonProperty("id")String id,
            @JsonProperty("location")Debugger.Location location,
            @JsonProperty("profile")Profile profile,
            @Nullable @JsonProperty("title")String title
        ) {
            this.id = id;
            this.location = location;
            this.profile = profile;
            this.title = title;
        }
    }
    /**&lt;No document in protocol.&gt;
     @see ConsoleProfileFinishedEventParameter*/
    public void onConsoleProfileFinished(Consumer<ConsoleProfileFinishedEventParameter> callback) {
        registerEventCallback("Profiler.consoleProfileFinished", node -> {
            ConsoleProfileFinishedEventParameter param;
            try { param = EventCenter.deserializeJson(node, ConsoleProfileFinishedEventParameter.class); }
            catch (IOException e) { _evt.getLog().error(e); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of Profiler.consoleProfileStarted.
     @see #onConsoleProfileStarted*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ConsoleProfileStartedEventParameter implements CommonDomainType {
        /**&lt;No document in protocol.&gt;*/
        private final String id;
        /**Location of console.profile().*/
        private final Debugger.Location location;
        /**Profile title passed as an argument to console.profile().
        <em>Optional.</em>*/
        private final String title;
        public final String id() { return id; }
        public final String getId() { return id(); }
        public final Debugger.Location location() { return location; }
        public final Debugger.Location getLocation() { return location(); }
        public final String title() { return title; }
        public final String getTitle() { return title(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"id\":").append('"').append(DomainBase.escapeJson(id)).append('"');
            location.toJson(strBuilder.append(",\"location\":"));
            if (title != null) strBuilder.append(",\"title\":").append('"').append(DomainBase.escapeJson(title)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        ConsoleProfileStartedEventParameter(
            @JsonProperty("id")String id,
            @JsonProperty("location")Debugger.Location location,
            @Nullable @JsonProperty("title")String title
        ) {
            this.id = id;
            this.location = location;
            this.title = title;
        }
    }
    /**Sent when new profile recording is started using console.profile() call.
     @see ConsoleProfileStartedEventParameter*/
    public void onConsoleProfileStarted(Consumer<ConsoleProfileStartedEventParameter> callback) {
        registerEventCallback("Profiler.consoleProfileStarted", node -> {
            ConsoleProfileStartedEventParameter param;
            try { param = EventCenter.deserializeJson(node, ConsoleProfileStartedEventParameter.class); }
            catch (IOException e) { _evt.getLog().error(e); return; }
            callback.accept(param);
        });
    }
}

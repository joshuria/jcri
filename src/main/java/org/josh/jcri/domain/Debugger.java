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

/**Debugger domain exposes JavaScript debugging capabilities. It allows setting and removing
breakpoints, stepping through execution, exploring stack traces, etc.
<p>From: js_protocol.json</p>
<p>Protocol version: 1.3</p>
 @see Runtime
 @author Joshua*/
@ParametersAreNonnullByDefault public class Debugger extends DomainBase {
    public Debugger(EventCenter evt, WebSocket ws) { super(evt, ws); }

    /**Breakpoint identifier.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class BreakpointId implements CommonDomainType {
        private String _value;
        public BreakpointId() {}
        public BreakpointId(String value) { _value = value; }
        public final BreakpointId value(String value) { _value = value; return this; }
        public final String value() { return _value; }
        public final BreakpointId setValue(String value) { return value(value); }
        public final String getValue() { return value(); }
        @Override public String toString() { return _value; }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (_value == null) throw new IllegalArgumentException("Debugger.BreakpointId.value is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            return strBuilder.append('"').append(DomainBase.escapeQuote(_value)).append('"');
        }
    }

    /**Call frame identifier.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class CallFrameId implements CommonDomainType {
        private String _value;
        public CallFrameId() {}
        public CallFrameId(String value) { _value = value; }
        public final CallFrameId value(String value) { _value = value; return this; }
        public final String value() { return _value; }
        public final CallFrameId setValue(String value) { return value(value); }
        public final String getValue() { return value(); }
        @Override public String toString() { return _value; }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (_value == null) throw new IllegalArgumentException("Debugger.CallFrameId.value is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            return strBuilder.append('"').append(DomainBase.escapeQuote(_value)).append('"');
        }
    }

    /**Location in the source code.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class Location implements CommonDomainType {
        /**Script identifier as reported in the `Debugger.scriptParsed`.*/
        private Runtime.ScriptId scriptId;
        /**Line number in the script (0-based).*/
        private Integer lineNumber;
        /**Column number in the script (0-based).
        <em>Optional.</em>*/
        private Integer columnNumber;
        public final Location scriptId(Runtime.ScriptId scriptId) { this.scriptId = scriptId; return this; }
        public final Location setScriptId(Runtime.ScriptId scriptId) { return scriptId(scriptId); }
        public final Runtime.ScriptId scriptId() { return scriptId; }
        public final Runtime.ScriptId getScriptId() { return scriptId(); }
        public final Location lineNumber(Integer lineNumber) { this.lineNumber = lineNumber; return this; }
        public final Location setLineNumber(Integer lineNumber) { return lineNumber(lineNumber); }
        public final Integer lineNumber() { return lineNumber; }
        public final Integer getLineNumber() { return lineNumber(); }
        public final Location columnNumber(@Nullable Integer columnNumber) { this.columnNumber = columnNumber; return this; }
        public final Location optColumnNumber(@Nullable Integer columnNumber) { return columnNumber(columnNumber); }
        public final Integer columnNumber() { return columnNumber; }
        public final Integer getColumnNumber() { return columnNumber(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (scriptId == null) throw new IllegalArgumentException("Debugger.Location.scriptId is necessary field.");
            if (lineNumber == null) throw new IllegalArgumentException("Debugger.Location.lineNumber is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            scriptId.toJson(strBuilder.append("\"scriptId\":"));
            strBuilder.append(",\"lineNumber\":").append(lineNumber);
            if (columnNumber != null) strBuilder.append(",\"columnNumber\":").append(columnNumber);
            strBuilder.append('}');
            return strBuilder;
        }
        public Location() {}
        public Location(
            @JsonProperty("scriptId")Runtime.ScriptId scriptId,
            @JsonProperty("lineNumber")Integer lineNumber,
            @Nullable @JsonProperty("columnNumber")Integer columnNumber
        ) {
            this.scriptId = scriptId;
            this.lineNumber = lineNumber;
            this.columnNumber = columnNumber;
        }
    }

    /**Location in the source code.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ScriptPosition implements CommonDomainType {
        /**&lt;No document in protocol.&gt;*/
        private Integer lineNumber;
        /**&lt;No document in protocol.&gt;*/
        private Integer columnNumber;
        public final ScriptPosition lineNumber(Integer lineNumber) { this.lineNumber = lineNumber; return this; }
        public final ScriptPosition setLineNumber(Integer lineNumber) { return lineNumber(lineNumber); }
        public final Integer lineNumber() { return lineNumber; }
        public final Integer getLineNumber() { return lineNumber(); }
        public final ScriptPosition columnNumber(Integer columnNumber) { this.columnNumber = columnNumber; return this; }
        public final ScriptPosition setColumnNumber(Integer columnNumber) { return columnNumber(columnNumber); }
        public final Integer columnNumber() { return columnNumber; }
        public final Integer getColumnNumber() { return columnNumber(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (lineNumber == null) throw new IllegalArgumentException("Debugger.ScriptPosition.lineNumber is necessary field.");
            if (columnNumber == null) throw new IllegalArgumentException("Debugger.ScriptPosition.columnNumber is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"lineNumber\":").append(lineNumber);
            strBuilder.append(",\"columnNumber\":").append(columnNumber);
            strBuilder.append('}');
            return strBuilder;
        }
        public ScriptPosition() {}
        public ScriptPosition(
            @JsonProperty("lineNumber")Integer lineNumber,
            @JsonProperty("columnNumber")Integer columnNumber
        ) {
            this.lineNumber = lineNumber;
            this.columnNumber = columnNumber;
        }
    }

    /**JavaScript call frame. Array of call frames form the call stack.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class CallFrame implements CommonDomainType {
        /**Call frame identifier. This identifier is only valid while the virtual machine is paused.*/
        private CallFrameId callFrameId;
        /**Name of the JavaScript function called on this call frame.*/
        private String functionName;
        /**Location in the source code.
        <em>Optional.</em>*/
        private Location functionLocation;
        /**Location in the source code.*/
        private Location location;
        /**JavaScript script name or url.*/
        private String url;
        /**Scope chain for this call frame.*/
        private List<Scope> scopeChain;
        /**`this` object for this call frame.*/
        private Runtime.RemoteObject thiz;
        /**The value being returned, if the function is at return point.
        <em>Optional.</em>*/
        private Runtime.RemoteObject returnValue;
        public final CallFrame callFrameId(CallFrameId callFrameId) { this.callFrameId = callFrameId; return this; }
        public final CallFrame setCallFrameId(CallFrameId callFrameId) { return callFrameId(callFrameId); }
        public final CallFrameId callFrameId() { return callFrameId; }
        public final CallFrameId getCallFrameId() { return callFrameId(); }
        public final CallFrame functionName(String functionName) { this.functionName = functionName; return this; }
        public final CallFrame setFunctionName(String functionName) { return functionName(functionName); }
        public final String functionName() { return functionName; }
        public final String getFunctionName() { return functionName(); }
        public final CallFrame functionLocation(@Nullable Location functionLocation) { this.functionLocation = functionLocation; return this; }
        public final CallFrame optFunctionLocation(@Nullable Location functionLocation) { return functionLocation(functionLocation); }
        public final Location functionLocation() { return functionLocation; }
        public final Location getFunctionLocation() { return functionLocation(); }
        public final CallFrame location(Location location) { this.location = location; return this; }
        public final CallFrame setLocation(Location location) { return location(location); }
        public final Location location() { return location; }
        public final Location getLocation() { return location(); }
        public final CallFrame url(String url) { this.url = url; return this; }
        public final CallFrame setUrl(String url) { return url(url); }
        public final String url() { return url; }
        public final String getUrl() { return url(); }
        public final CallFrame scopeChain(List<Scope> scopeChain) { this.scopeChain = scopeChain; return this; }
        public final CallFrame setScopeChain(List<Scope> scopeChain) { return scopeChain(scopeChain); }
        public final List<Scope> scopeChain() { return scopeChain; }
        public final List<Scope> getScopeChain() { return scopeChain(); }
        public final CallFrame thiz(Runtime.RemoteObject thiz) { this.thiz = thiz; return this; }
        public final CallFrame setThiz(Runtime.RemoteObject thiz) { return thiz(thiz); }
        public final Runtime.RemoteObject thiz() { return thiz; }
        public final Runtime.RemoteObject getThiz() { return thiz(); }
        public final CallFrame returnValue(@Nullable Runtime.RemoteObject returnValue) { this.returnValue = returnValue; return this; }
        public final CallFrame optReturnValue(@Nullable Runtime.RemoteObject returnValue) { return returnValue(returnValue); }
        public final Runtime.RemoteObject returnValue() { return returnValue; }
        public final Runtime.RemoteObject getReturnValue() { return returnValue(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (callFrameId == null) throw new IllegalArgumentException("Debugger.CallFrame.callFrameId is necessary field.");
            if (functionName == null) throw new IllegalArgumentException("Debugger.CallFrame.functionName is necessary field.");
            if (location == null) throw new IllegalArgumentException("Debugger.CallFrame.location is necessary field.");
            if (url == null) throw new IllegalArgumentException("Debugger.CallFrame.url is necessary field.");
            if (scopeChain == null) throw new IllegalArgumentException("Debugger.CallFrame.scopeChain is necessary field.");
            if (thiz == null) throw new IllegalArgumentException("Debugger.CallFrame.thiz is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            callFrameId.toJson(strBuilder.append("\"callFrameId\":"));
            strBuilder.append(",\"functionName\":").append('"').append(DomainBase.escapeQuote(functionName)).append('"');
            if (functionLocation != null) functionLocation.toJson(strBuilder.append(",\"functionLocation\":"));
            location.toJson(strBuilder.append(",\"location\":"));
            strBuilder.append(",\"url\":").append('"').append(DomainBase.escapeQuote(url)).append('"');
                        strBuilder.append(",\"scopeChain\":[");
            scopeChain.get(0).toJson(strBuilder);
            for (int i = 1; i < scopeChain.size(); ++i)
                scopeChain.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            thiz.toJson(strBuilder.append(",\"this\":"));
            if (returnValue != null) returnValue.toJson(strBuilder.append(",\"returnValue\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public CallFrame() {}
        public CallFrame(
            @JsonProperty("callFrameId")CallFrameId callFrameId,
            @JsonProperty("functionName")String functionName,
            @Nullable @JsonProperty("functionLocation")Location functionLocation,
            @JsonProperty("location")Location location,
            @JsonProperty("url")String url,
            @JsonProperty("scopeChain")List<Scope> scopeChain,
            @JsonProperty("this")Runtime.RemoteObject thiz,
            @Nullable @JsonProperty("returnValue")Runtime.RemoteObject returnValue
        ) {
            this.callFrameId = callFrameId;
            this.functionName = functionName;
            this.functionLocation = functionLocation;
            this.location = location;
            this.url = url;
            this.scopeChain = scopeChain;
            this.thiz = thiz;
            this.returnValue = returnValue;
        }
    }

    /**Scope description.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class Scope implements CommonDomainType {
        /**Scope type.*/
        @ParametersAreNonnullByDefault public enum Type implements CommonDomainType {
            Global("global"),
            Local("local"),
            With("with"),
            Closure("closure"),
            Catch("catch"),
            Block("block"),
            Script("script"),
            Eval("eval"),
            Module("module");

            private final String _value;
            /**Convert string representation to type.
             @throws IllegalArgumentException if given value cannot convert to enum type. */
            @JsonCreator public static Type of(String value) {
                return Enum.valueOf(Type.class, value.substring(0, 1).toUpperCase() + value.substring(1));
            }
            Type(String value) { _value = value; }
            /**Check if parameter fields of method are all valid. */
            @Override public void check() throws IllegalArgumentException { /* Need not check */ }
            /**Convert method parameter object into json string and append into string builder.
             @return string builder instance that is given in parameter (for chaining coding style use.) */
            @Override public StringBuilder toJson(StringBuilder strBuilder) { return strBuilder.append('"').append(_value).append('"'); }
            @Override public String toString() { return "\"" + _value + "\""; }
        }
        private Type type;
        /**Object representing the scope. For `global` and `with` scopes it represents the actual
object; for the rest of the scopes, it is artificial transient object enumerating scope
variables as its properties.*/
        private Runtime.RemoteObject object;
        /**&lt;No document in protocol.&gt;
        <em>Optional.</em>*/
        private String name;
        /**Location in the source code where scope starts
        <em>Optional.</em>*/
        private Location startLocation;
        /**Location in the source code where scope ends
        <em>Optional.</em>*/
        private Location endLocation;
        public final Scope type(Type type) { this.type = type; return this; }
        public final Scope setType(Type type) { return type(type); }
        public final Type type() { return type; }
        public final Type getType() { return type(); }
        public final Scope object(Runtime.RemoteObject object) { this.object = object; return this; }
        public final Scope setObject(Runtime.RemoteObject object) { return object(object); }
        public final Runtime.RemoteObject object() { return object; }
        public final Runtime.RemoteObject getObject() { return object(); }
        public final Scope name(@Nullable String name) { this.name = name; return this; }
        public final Scope optName(@Nullable String name) { return name(name); }
        public final String name() { return name; }
        public final String getName() { return name(); }
        public final Scope startLocation(@Nullable Location startLocation) { this.startLocation = startLocation; return this; }
        public final Scope optStartLocation(@Nullable Location startLocation) { return startLocation(startLocation); }
        public final Location startLocation() { return startLocation; }
        public final Location getStartLocation() { return startLocation(); }
        public final Scope endLocation(@Nullable Location endLocation) { this.endLocation = endLocation; return this; }
        public final Scope optEndLocation(@Nullable Location endLocation) { return endLocation(endLocation); }
        public final Location endLocation() { return endLocation; }
        public final Location getEndLocation() { return endLocation(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (type == null) throw new IllegalArgumentException("Debugger.Scope.type is necessary field.");
            if (object == null) throw new IllegalArgumentException("Debugger.Scope.object is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"type\":").append(type);
            object.toJson(strBuilder.append(",\"object\":"));
            if (name != null) strBuilder.append(",\"name\":").append('"').append(DomainBase.escapeQuote(name)).append('"');
            if (startLocation != null) startLocation.toJson(strBuilder.append(",\"startLocation\":"));
            if (endLocation != null) endLocation.toJson(strBuilder.append(",\"endLocation\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public Scope() {}
        public Scope(
            @JsonProperty("type")Type type,
            @JsonProperty("object")Runtime.RemoteObject object,
            @Nullable @JsonProperty("name")String name,
            @Nullable @JsonProperty("startLocation")Location startLocation,
            @Nullable @JsonProperty("endLocation")Location endLocation
        ) {
            this.type = type;
            this.object = object;
            this.name = name;
            this.startLocation = startLocation;
            this.endLocation = endLocation;
        }
    }

    /**Search match for resource.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SearchMatch implements CommonDomainType {
        /**Line number in resource content.*/
        private Double lineNumber;
        /**Line with match content.*/
        private String lineContent;
        public final SearchMatch lineNumber(Double lineNumber) { this.lineNumber = lineNumber; return this; }
        public final SearchMatch setLineNumber(Double lineNumber) { return lineNumber(lineNumber); }
        public final Double lineNumber() { return lineNumber; }
        public final Double getLineNumber() { return lineNumber(); }
        public final SearchMatch lineContent(String lineContent) { this.lineContent = lineContent; return this; }
        public final SearchMatch setLineContent(String lineContent) { return lineContent(lineContent); }
        public final String lineContent() { return lineContent; }
        public final String getLineContent() { return lineContent(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (lineNumber == null) throw new IllegalArgumentException("Debugger.SearchMatch.lineNumber is necessary field.");
            if (lineContent == null) throw new IllegalArgumentException("Debugger.SearchMatch.lineContent is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"lineNumber\":").append(lineNumber);
            strBuilder.append(",\"lineContent\":").append('"').append(DomainBase.escapeQuote(lineContent)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public SearchMatch() {}
        public SearchMatch(
            @JsonProperty("lineNumber")Double lineNumber,
            @JsonProperty("lineContent")String lineContent
        ) {
            this.lineNumber = lineNumber;
            this.lineContent = lineContent;
        }
    }

    /**&lt;No document in protocol.&gt;*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class BreakLocation implements CommonDomainType {
        /**Script identifier as reported in the `Debugger.scriptParsed`.*/
        private Runtime.ScriptId scriptId;
        /**Line number in the script (0-based).*/
        private Integer lineNumber;
        /**Column number in the script (0-based).
        <em>Optional.</em>*/
        private Integer columnNumber;
        /**&lt;No document in protocol.&gt;
        <em>Optional.</em>*/
        @ParametersAreNonnullByDefault public enum Type implements CommonDomainType {
            DebuggerStatement("debuggerStatement"),
            Call("call"),
            Return("return");

            private final String _value;
            /**Convert string representation to type.
             @throws IllegalArgumentException if given value cannot convert to enum type. */
            @JsonCreator public static Type of(String value) {
                return Enum.valueOf(Type.class, value.substring(0, 1).toUpperCase() + value.substring(1));
            }
            Type(String value) { _value = value; }
            /**Check if parameter fields of method are all valid. */
            @Override public void check() throws IllegalArgumentException { /* Need not check */ }
            /**Convert method parameter object into json string and append into string builder.
             @return string builder instance that is given in parameter (for chaining coding style use.) */
            @Override public StringBuilder toJson(StringBuilder strBuilder) { return strBuilder.append('"').append(_value).append('"'); }
            @Override public String toString() { return "\"" + _value + "\""; }
        }
        private Type type;
        public final BreakLocation scriptId(Runtime.ScriptId scriptId) { this.scriptId = scriptId; return this; }
        public final BreakLocation setScriptId(Runtime.ScriptId scriptId) { return scriptId(scriptId); }
        public final Runtime.ScriptId scriptId() { return scriptId; }
        public final Runtime.ScriptId getScriptId() { return scriptId(); }
        public final BreakLocation lineNumber(Integer lineNumber) { this.lineNumber = lineNumber; return this; }
        public final BreakLocation setLineNumber(Integer lineNumber) { return lineNumber(lineNumber); }
        public final Integer lineNumber() { return lineNumber; }
        public final Integer getLineNumber() { return lineNumber(); }
        public final BreakLocation columnNumber(@Nullable Integer columnNumber) { this.columnNumber = columnNumber; return this; }
        public final BreakLocation optColumnNumber(@Nullable Integer columnNumber) { return columnNumber(columnNumber); }
        public final Integer columnNumber() { return columnNumber; }
        public final Integer getColumnNumber() { return columnNumber(); }
        public final BreakLocation type(@Nullable Type type) { this.type = type; return this; }
        public final BreakLocation optType(@Nullable Type type) { return type(type); }
        public final Type type() { return type; }
        public final Type getType() { return type(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (scriptId == null) throw new IllegalArgumentException("Debugger.BreakLocation.scriptId is necessary field.");
            if (lineNumber == null) throw new IllegalArgumentException("Debugger.BreakLocation.lineNumber is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            scriptId.toJson(strBuilder.append("\"scriptId\":"));
            strBuilder.append(",\"lineNumber\":").append(lineNumber);
            if (columnNumber != null) strBuilder.append(",\"columnNumber\":").append(columnNumber);
            if (type != null) strBuilder.append(",\"type\":").append(type);
            strBuilder.append('}');
            return strBuilder;
        }
        public BreakLocation() {}
        public BreakLocation(
            @JsonProperty("scriptId")Runtime.ScriptId scriptId,
            @JsonProperty("lineNumber")Integer lineNumber,
            @Nullable @JsonProperty("columnNumber")Integer columnNumber,
            @Nullable @JsonProperty("type")Type type
        ) {
            this.scriptId = scriptId;
            this.lineNumber = lineNumber;
            this.columnNumber = columnNumber;
            this.type = type;
        }
    }
    /**Continues execution until specific location is reached.*/
    public ContinueToLocationParameter continueToLocation() { final ContinueToLocationParameter v = new ContinueToLocationParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of continueToLocation.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ContinueToLocationParameter extends CommandBase {
        /**Location to continue to.*/
        private Location location;
        /**&lt;No document in protocol.&gt;
        <em>Optional.</em>*/
        @ParametersAreNonnullByDefault public enum TargetCallFrames implements CommonDomainType {
            Object("any"),
            Current("current");

            private final String _value;
            /**Convert string representation to type.
             @throws IllegalArgumentException if given value cannot convert to enum type. */
            @JsonCreator public static TargetCallFrames of(String value) {
                return Enum.valueOf(TargetCallFrames.class, value.substring(0, 1).toUpperCase() + value.substring(1));
            }
            TargetCallFrames(String value) { _value = value; }
            /**Check if parameter fields of method are all valid. */
            @Override public void check() throws IllegalArgumentException { /* Need not check */ }
            /**Convert method parameter object into json string and append into string builder.
             @return string builder instance that is given in parameter (for chaining coding style use.) */
            @Override public StringBuilder toJson(StringBuilder strBuilder) { return strBuilder.append('"').append(_value).append('"'); }
            @Override public String toString() { return "\"" + _value + "\""; }
        }
        private TargetCallFrames targetCallFrames;
        public final ContinueToLocationParameter location(Location location) { this.location = location; return this; }
        public final ContinueToLocationParameter setLocation(Location location) { return location(location); }
        public final Location location() { return location; }
        public final Location getLocation() { return location(); }
        public final ContinueToLocationParameter targetCallFrames(@Nullable TargetCallFrames targetCallFrames) { this.targetCallFrames = targetCallFrames; return this; }
        public final ContinueToLocationParameter optTargetCallFrames(@Nullable TargetCallFrames targetCallFrames) { return targetCallFrames(targetCallFrames); }
        public final TargetCallFrames targetCallFrames() { return targetCallFrames; }
        public final TargetCallFrames getTargetCallFrames() { return targetCallFrames(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (location == null) throw new IllegalArgumentException("Debugger.ContinueToLocationParameter.location is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            location.toJson(strBuilder.append("\"location\":"));
            if (targetCallFrames != null) strBuilder.append(",\"targetCallFrames\":").append(targetCallFrames);
            strBuilder.append('}');
            return strBuilder;
        }
        public ContinueToLocationParameter() {}
        public ContinueToLocationParameter(
            @JsonProperty("location")Location location,
            @Nullable @JsonProperty("targetCallFrames")TargetCallFrames targetCallFrames
        ) {
            this();
            this.location = location;
            this.targetCallFrames = targetCallFrames;
        }
        public CompletableFuture<ContinueToLocationResult> call() {
            return super.call("Debugger.continueToLocation", ContinueToLocationResult.class,
                (code, msg)->new ContinueToLocationResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<ContinueToLocationResult> call(Executor exec) {
            return super.call("Debugger.continueToLocation", ContinueToLocationResult.class,
                (code, msg)->new ContinueToLocationResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of continueToLocation.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ContinueToLocationResult extends ResultBase {
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
        public ContinueToLocationResult() { super(); }
        public ContinueToLocationResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Disables debugger for given page.*/
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
            return super.call("Debugger.disable", DisableResult.class,
                (code, msg)->new DisableResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<DisableResult> call(Executor exec) {
            return super.call("Debugger.disable", DisableResult.class,
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
    /**Enables debugger for the given page. Clients should not assume that the debugging has been
enabled until the result for this command is received.*/
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
            return super.call("Debugger.enable", EnableResult.class,
                (code, msg)->new EnableResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<EnableResult> call(Executor exec) {
            return super.call("Debugger.enable", EnableResult.class,
                (code, msg)->new EnableResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of enable.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class EnableResult extends ResultBase {
        /**Unique identifier of the debugger.
        <p><strong>Experimental.</strong></p>*/
        private final Runtime.UniqueDebuggerId debuggerId;
        public final Runtime.UniqueDebuggerId debuggerId() { return debuggerId; }
        public final Runtime.UniqueDebuggerId getDebuggerId() { return debuggerId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            debuggerId.toJson(strBuilder.append("\"debuggerId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public EnableResult(
            @JsonProperty("debuggerId")Runtime.UniqueDebuggerId debuggerId
        ) {
            this.debuggerId = debuggerId;
        }
        public EnableResult(ResultBase.FailedResult e) {
            super(e);
            debuggerId = null;
        }
    }
    /**Evaluates expression on a given call frame.*/
    public EvaluateOnCallFrameParameter evaluateOnCallFrame() { final EvaluateOnCallFrameParameter v = new EvaluateOnCallFrameParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of evaluateOnCallFrame.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class EvaluateOnCallFrameParameter extends CommandBase {
        /**Call frame identifier to evaluate on.*/
        private CallFrameId callFrameId;
        /**Expression to evaluate.*/
        private String expression;
        /**String object group name to put result into (allows rapid releasing resulting object handles
using `releaseObjectGroup`).
        <em>Optional.</em>*/
        private String objectGroup;
        /**Specifies whether command line API should be available to the evaluated expression, defaults
to false.
        <em>Optional.</em>*/
        private Boolean includeCommandLineAPI;
        /**In silent mode exceptions thrown during evaluation are not reported and do not pause
execution. Overrides `setPauseOnException` state.
        <em>Optional.</em>*/
        private Boolean silent;
        /**Whether the result is expected to be a JSON object that should be sent by value.
        <em>Optional.</em>*/
        private Boolean returnByValue;
        /**Whether preview should be generated for the result.
        <em>Optional.</em>
        <p><strong>Experimental.</strong></p>*/
        private Boolean generatePreview;
        /**Whether to throw an exception if side effect cannot be ruled out during evaluation.
        <em>Optional.</em>*/
        private Boolean throwOnSideEffect;
        public final EvaluateOnCallFrameParameter callFrameId(CallFrameId callFrameId) { this.callFrameId = callFrameId; return this; }
        public final EvaluateOnCallFrameParameter setCallFrameId(CallFrameId callFrameId) { return callFrameId(callFrameId); }
        public final CallFrameId callFrameId() { return callFrameId; }
        public final CallFrameId getCallFrameId() { return callFrameId(); }
        public final EvaluateOnCallFrameParameter expression(String expression) { this.expression = expression; return this; }
        public final EvaluateOnCallFrameParameter setExpression(String expression) { return expression(expression); }
        public final String expression() { return expression; }
        public final String getExpression() { return expression(); }
        public final EvaluateOnCallFrameParameter objectGroup(@Nullable String objectGroup) { this.objectGroup = objectGroup; return this; }
        public final EvaluateOnCallFrameParameter optObjectGroup(@Nullable String objectGroup) { return objectGroup(objectGroup); }
        public final String objectGroup() { return objectGroup; }
        public final String getObjectGroup() { return objectGroup(); }
        public final EvaluateOnCallFrameParameter includeCommandLineAPI(@Nullable Boolean includeCommandLineAPI) { this.includeCommandLineAPI = includeCommandLineAPI; return this; }
        public final EvaluateOnCallFrameParameter optIncludeCommandLineAPI(@Nullable Boolean includeCommandLineAPI) { return includeCommandLineAPI(includeCommandLineAPI); }
        public final Boolean includeCommandLineAPI() { return includeCommandLineAPI; }
        public final Boolean getIncludeCommandLineAPI() { return includeCommandLineAPI(); }
        public final EvaluateOnCallFrameParameter silent(@Nullable Boolean silent) { this.silent = silent; return this; }
        public final EvaluateOnCallFrameParameter optSilent(@Nullable Boolean silent) { return silent(silent); }
        public final Boolean silent() { return silent; }
        public final Boolean getSilent() { return silent(); }
        public final EvaluateOnCallFrameParameter returnByValue(@Nullable Boolean returnByValue) { this.returnByValue = returnByValue; return this; }
        public final EvaluateOnCallFrameParameter optReturnByValue(@Nullable Boolean returnByValue) { return returnByValue(returnByValue); }
        public final Boolean returnByValue() { return returnByValue; }
        public final Boolean getReturnByValue() { return returnByValue(); }
        public final EvaluateOnCallFrameParameter generatePreview(@Nullable Boolean generatePreview) { this.generatePreview = generatePreview; return this; }
        public final EvaluateOnCallFrameParameter optGeneratePreview(@Nullable Boolean generatePreview) { return generatePreview(generatePreview); }
        public final Boolean generatePreview() { return generatePreview; }
        public final Boolean getGeneratePreview() { return generatePreview(); }
        public final EvaluateOnCallFrameParameter throwOnSideEffect(@Nullable Boolean throwOnSideEffect) { this.throwOnSideEffect = throwOnSideEffect; return this; }
        public final EvaluateOnCallFrameParameter optThrowOnSideEffect(@Nullable Boolean throwOnSideEffect) { return throwOnSideEffect(throwOnSideEffect); }
        public final Boolean throwOnSideEffect() { return throwOnSideEffect; }
        public final Boolean getThrowOnSideEffect() { return throwOnSideEffect(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (callFrameId == null) throw new IllegalArgumentException("Debugger.EvaluateOnCallFrameParameter.callFrameId is necessary field.");
            if (expression == null) throw new IllegalArgumentException("Debugger.EvaluateOnCallFrameParameter.expression is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            callFrameId.toJson(strBuilder.append("\"callFrameId\":"));
            strBuilder.append(",\"expression\":").append('"').append(DomainBase.escapeQuote(expression)).append('"');
            if (objectGroup != null) strBuilder.append(",\"objectGroup\":").append('"').append(DomainBase.escapeQuote(objectGroup)).append('"');
            if (includeCommandLineAPI != null) strBuilder.append(",\"includeCommandLineAPI\":").append(includeCommandLineAPI);
            if (silent != null) strBuilder.append(",\"silent\":").append(silent);
            if (returnByValue != null) strBuilder.append(",\"returnByValue\":").append(returnByValue);
            if (generatePreview != null) strBuilder.append(",\"generatePreview\":").append(generatePreview);
            if (throwOnSideEffect != null) strBuilder.append(",\"throwOnSideEffect\":").append(throwOnSideEffect);
            strBuilder.append('}');
            return strBuilder;
        }
        public EvaluateOnCallFrameParameter() {}
        public EvaluateOnCallFrameParameter(
            @JsonProperty("callFrameId")CallFrameId callFrameId,
            @JsonProperty("expression")String expression,
            @Nullable @JsonProperty("objectGroup")String objectGroup,
            @Nullable @JsonProperty("includeCommandLineAPI")Boolean includeCommandLineAPI,
            @Nullable @JsonProperty("silent")Boolean silent,
            @Nullable @JsonProperty("returnByValue")Boolean returnByValue,
            @Nullable @JsonProperty("generatePreview")Boolean generatePreview,
            @Nullable @JsonProperty("throwOnSideEffect")Boolean throwOnSideEffect
        ) {
            this();
            this.callFrameId = callFrameId;
            this.expression = expression;
            this.objectGroup = objectGroup;
            this.includeCommandLineAPI = includeCommandLineAPI;
            this.silent = silent;
            this.returnByValue = returnByValue;
            this.generatePreview = generatePreview;
            this.throwOnSideEffect = throwOnSideEffect;
        }
        public CompletableFuture<EvaluateOnCallFrameResult> call() {
            return super.call("Debugger.evaluateOnCallFrame", EvaluateOnCallFrameResult.class,
                (code, msg)->new EvaluateOnCallFrameResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<EvaluateOnCallFrameResult> call(Executor exec) {
            return super.call("Debugger.evaluateOnCallFrame", EvaluateOnCallFrameResult.class,
                (code, msg)->new EvaluateOnCallFrameResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of evaluateOnCallFrame.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class EvaluateOnCallFrameResult extends ResultBase {
        /**Object wrapper for the evaluation result.*/
        private final Runtime.RemoteObject result;
        /**Exception details.
        <em>Optional.</em>*/
        private final Runtime.ExceptionDetails exceptionDetails;
        public final Runtime.RemoteObject result() { return result; }
        public final Runtime.RemoteObject getResult() { return result(); }
        public final Runtime.ExceptionDetails exceptionDetails() { return exceptionDetails; }
        public final Runtime.ExceptionDetails getExceptionDetails() { return exceptionDetails(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            result.toJson(strBuilder.append("\"result\":"));
            if (exceptionDetails != null) exceptionDetails.toJson(strBuilder.append(",\"exceptionDetails\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public EvaluateOnCallFrameResult(
            @JsonProperty("result")Runtime.RemoteObject result,
            @Nullable @JsonProperty("exceptionDetails")Runtime.ExceptionDetails exceptionDetails
        ) {
            this.result = result;
            this.exceptionDetails = exceptionDetails;
        }
        public EvaluateOnCallFrameResult(ResultBase.FailedResult e) {
            super(e);
            result = null;
            exceptionDetails = null;
        }
    }
    /**Returns possible locations for breakpoint. scriptId in start and end range locations should be
the same.*/
    public GetPossibleBreakpointsParameter getPossibleBreakpoints() { final GetPossibleBreakpointsParameter v = new GetPossibleBreakpointsParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of getPossibleBreakpoints.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetPossibleBreakpointsParameter extends CommandBase {
        /**Start of range to search possible breakpoint locations in.*/
        private Location start;
        /**End of range to search possible breakpoint locations in (excluding). When not specified, end
of scripts is used as end of range.
        <em>Optional.</em>*/
        private Location end;
        /**Only consider locations which are in the same (non-nested) function as start.
        <em>Optional.</em>*/
        private Boolean restrictToFunction;
        public final GetPossibleBreakpointsParameter start(Location start) { this.start = start; return this; }
        public final GetPossibleBreakpointsParameter setStart(Location start) { return start(start); }
        public final Location start() { return start; }
        public final Location getStart() { return start(); }
        public final GetPossibleBreakpointsParameter end(@Nullable Location end) { this.end = end; return this; }
        public final GetPossibleBreakpointsParameter optEnd(@Nullable Location end) { return end(end); }
        public final Location end() { return end; }
        public final Location getEnd() { return end(); }
        public final GetPossibleBreakpointsParameter restrictToFunction(@Nullable Boolean restrictToFunction) { this.restrictToFunction = restrictToFunction; return this; }
        public final GetPossibleBreakpointsParameter optRestrictToFunction(@Nullable Boolean restrictToFunction) { return restrictToFunction(restrictToFunction); }
        public final Boolean restrictToFunction() { return restrictToFunction; }
        public final Boolean getRestrictToFunction() { return restrictToFunction(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (start == null) throw new IllegalArgumentException("Debugger.GetPossibleBreakpointsParameter.start is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            start.toJson(strBuilder.append("\"start\":"));
            if (end != null) end.toJson(strBuilder.append(",\"end\":"));
            if (restrictToFunction != null) strBuilder.append(",\"restrictToFunction\":").append(restrictToFunction);
            strBuilder.append('}');
            return strBuilder;
        }
        public GetPossibleBreakpointsParameter() {}
        public GetPossibleBreakpointsParameter(
            @JsonProperty("start")Location start,
            @Nullable @JsonProperty("end")Location end,
            @Nullable @JsonProperty("restrictToFunction")Boolean restrictToFunction
        ) {
            this();
            this.start = start;
            this.end = end;
            this.restrictToFunction = restrictToFunction;
        }
        public CompletableFuture<GetPossibleBreakpointsResult> call() {
            return super.call("Debugger.getPossibleBreakpoints", GetPossibleBreakpointsResult.class,
                (code, msg)->new GetPossibleBreakpointsResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetPossibleBreakpointsResult> call(Executor exec) {
            return super.call("Debugger.getPossibleBreakpoints", GetPossibleBreakpointsResult.class,
                (code, msg)->new GetPossibleBreakpointsResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of getPossibleBreakpoints.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetPossibleBreakpointsResult extends ResultBase {
        /**List of the possible breakpoint locations.*/
        private final List<BreakLocation> locations;
        public final List<BreakLocation> locations() { return locations; }
        public final List<BreakLocation> getLocations() { return locations(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
                        strBuilder.append("\"locations\":[");
            locations.get(0).toJson(strBuilder);
            for (int i = 1; i < locations.size(); ++i)
                locations.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public GetPossibleBreakpointsResult(
            @JsonProperty("locations")List<BreakLocation> locations
        ) {
            this.locations = locations;
        }
        public GetPossibleBreakpointsResult(ResultBase.FailedResult e) {
            super(e);
            locations = null;
        }
    }
    /**Returns source for the script with given id.*/
    public GetScriptSourceParameter getScriptSource() { final GetScriptSourceParameter v = new GetScriptSourceParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of getScriptSource.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetScriptSourceParameter extends CommandBase {
        /**Id of the script to get source for.*/
        private Runtime.ScriptId scriptId;
        public final GetScriptSourceParameter scriptId(Runtime.ScriptId scriptId) { this.scriptId = scriptId; return this; }
        public final GetScriptSourceParameter setScriptId(Runtime.ScriptId scriptId) { return scriptId(scriptId); }
        public final Runtime.ScriptId scriptId() { return scriptId; }
        public final Runtime.ScriptId getScriptId() { return scriptId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (scriptId == null) throw new IllegalArgumentException("Debugger.GetScriptSourceParameter.scriptId is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            scriptId.toJson(strBuilder.append("\"scriptId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public GetScriptSourceParameter() {}
        public GetScriptSourceParameter(
            @JsonProperty("scriptId")Runtime.ScriptId scriptId
        ) {
            this();
            this.scriptId = scriptId;
        }
        public CompletableFuture<GetScriptSourceResult> call() {
            return super.call("Debugger.getScriptSource", GetScriptSourceResult.class,
                (code, msg)->new GetScriptSourceResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetScriptSourceResult> call(Executor exec) {
            return super.call("Debugger.getScriptSource", GetScriptSourceResult.class,
                (code, msg)->new GetScriptSourceResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of getScriptSource.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetScriptSourceResult extends ResultBase {
        /**Script source.*/
        private final String scriptSource;
        public final String scriptSource() { return scriptSource; }
        public final String getScriptSource() { return scriptSource(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"scriptSource\":").append('"').append(DomainBase.escapeQuote(scriptSource)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public GetScriptSourceResult(
            @JsonProperty("scriptSource")String scriptSource
        ) {
            this.scriptSource = scriptSource;
        }
        public GetScriptSourceResult(ResultBase.FailedResult e) {
            super(e);
            scriptSource = null;
        }
    }
    /**Returns stack trace with given `stackTraceId`.
    <p><strong>Experimental.</strong></p>*/
    public GetStackTraceParameter getStackTrace() { final GetStackTraceParameter v = new GetStackTraceParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of getStackTrace.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetStackTraceParameter extends CommandBase {
        /**&lt;No document in protocol.&gt;*/
        private Runtime.StackTraceId stackTraceId;
        public final GetStackTraceParameter stackTraceId(Runtime.StackTraceId stackTraceId) { this.stackTraceId = stackTraceId; return this; }
        public final GetStackTraceParameter setStackTraceId(Runtime.StackTraceId stackTraceId) { return stackTraceId(stackTraceId); }
        public final Runtime.StackTraceId stackTraceId() { return stackTraceId; }
        public final Runtime.StackTraceId getStackTraceId() { return stackTraceId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (stackTraceId == null) throw new IllegalArgumentException("Debugger.GetStackTraceParameter.stackTraceId is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            stackTraceId.toJson(strBuilder.append("\"stackTraceId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public GetStackTraceParameter() {}
        public GetStackTraceParameter(
            @JsonProperty("stackTraceId")Runtime.StackTraceId stackTraceId
        ) {
            this();
            this.stackTraceId = stackTraceId;
        }
        public CompletableFuture<GetStackTraceResult> call() {
            return super.call("Debugger.getStackTrace", GetStackTraceResult.class,
                (code, msg)->new GetStackTraceResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetStackTraceResult> call(Executor exec) {
            return super.call("Debugger.getStackTrace", GetStackTraceResult.class,
                (code, msg)->new GetStackTraceResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of getStackTrace.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetStackTraceResult extends ResultBase {
        /**&lt;No document in protocol.&gt;*/
        private final Runtime.StackTrace stackTrace;
        public final Runtime.StackTrace stackTrace() { return stackTrace; }
        public final Runtime.StackTrace getStackTrace() { return stackTrace(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            stackTrace.toJson(strBuilder.append("\"stackTrace\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public GetStackTraceResult(
            @JsonProperty("stackTrace")Runtime.StackTrace stackTrace
        ) {
            this.stackTrace = stackTrace;
        }
        public GetStackTraceResult(ResultBase.FailedResult e) {
            super(e);
            stackTrace = null;
        }
    }
    /**Stops on the next JavaScript statement.*/
    public PauseParameter pause() { final PauseParameter v = new PauseParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of pause.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class PauseParameter extends CommandBase {
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
        public PauseParameter() {}
        public CompletableFuture<PauseResult> call() {
            return super.call("Debugger.pause", PauseResult.class,
                (code, msg)->new PauseResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<PauseResult> call(Executor exec) {
            return super.call("Debugger.pause", PauseResult.class,
                (code, msg)->new PauseResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of pause.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class PauseResult extends ResultBase {
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
        public PauseResult() { super(); }
        public PauseResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**&lt;No document in protocol.&gt;
    <p><strong>Experimental.</strong></p>*/
    public PauseOnAsyncCallParameter pauseOnAsyncCall() { final PauseOnAsyncCallParameter v = new PauseOnAsyncCallParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of pauseOnAsyncCall.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class PauseOnAsyncCallParameter extends CommandBase {
        /**Debugger will pause when async call with given stack trace is started.*/
        private Runtime.StackTraceId parentStackTraceId;
        public final PauseOnAsyncCallParameter parentStackTraceId(Runtime.StackTraceId parentStackTraceId) { this.parentStackTraceId = parentStackTraceId; return this; }
        public final PauseOnAsyncCallParameter setParentStackTraceId(Runtime.StackTraceId parentStackTraceId) { return parentStackTraceId(parentStackTraceId); }
        public final Runtime.StackTraceId parentStackTraceId() { return parentStackTraceId; }
        public final Runtime.StackTraceId getParentStackTraceId() { return parentStackTraceId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (parentStackTraceId == null) throw new IllegalArgumentException("Debugger.PauseOnAsyncCallParameter.parentStackTraceId is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            parentStackTraceId.toJson(strBuilder.append("\"parentStackTraceId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public PauseOnAsyncCallParameter() {}
        public PauseOnAsyncCallParameter(
            @JsonProperty("parentStackTraceId")Runtime.StackTraceId parentStackTraceId
        ) {
            this();
            this.parentStackTraceId = parentStackTraceId;
        }
        public CompletableFuture<PauseOnAsyncCallResult> call() {
            return super.call("Debugger.pauseOnAsyncCall", PauseOnAsyncCallResult.class,
                (code, msg)->new PauseOnAsyncCallResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<PauseOnAsyncCallResult> call(Executor exec) {
            return super.call("Debugger.pauseOnAsyncCall", PauseOnAsyncCallResult.class,
                (code, msg)->new PauseOnAsyncCallResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of pauseOnAsyncCall.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class PauseOnAsyncCallResult extends ResultBase {
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
        public PauseOnAsyncCallResult() { super(); }
        public PauseOnAsyncCallResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Removes JavaScript breakpoint.*/
    public RemoveBreakpointParameter removeBreakpoint() { final RemoveBreakpointParameter v = new RemoveBreakpointParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of removeBreakpoint.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class RemoveBreakpointParameter extends CommandBase {
        /**&lt;No document in protocol.&gt;*/
        private BreakpointId breakpointId;
        public final RemoveBreakpointParameter breakpointId(BreakpointId breakpointId) { this.breakpointId = breakpointId; return this; }
        public final RemoveBreakpointParameter setBreakpointId(BreakpointId breakpointId) { return breakpointId(breakpointId); }
        public final BreakpointId breakpointId() { return breakpointId; }
        public final BreakpointId getBreakpointId() { return breakpointId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (breakpointId == null) throw new IllegalArgumentException("Debugger.RemoveBreakpointParameter.breakpointId is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            breakpointId.toJson(strBuilder.append("\"breakpointId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public RemoveBreakpointParameter() {}
        public RemoveBreakpointParameter(
            @JsonProperty("breakpointId")BreakpointId breakpointId
        ) {
            this();
            this.breakpointId = breakpointId;
        }
        public CompletableFuture<RemoveBreakpointResult> call() {
            return super.call("Debugger.removeBreakpoint", RemoveBreakpointResult.class,
                (code, msg)->new RemoveBreakpointResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<RemoveBreakpointResult> call(Executor exec) {
            return super.call("Debugger.removeBreakpoint", RemoveBreakpointResult.class,
                (code, msg)->new RemoveBreakpointResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of removeBreakpoint.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class RemoveBreakpointResult extends ResultBase {
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
        public RemoveBreakpointResult() { super(); }
        public RemoveBreakpointResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Restarts particular call frame from the beginning.*/
    public RestartFrameParameter restartFrame() { final RestartFrameParameter v = new RestartFrameParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of restartFrame.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class RestartFrameParameter extends CommandBase {
        /**Call frame identifier to evaluate on.*/
        private CallFrameId callFrameId;
        public final RestartFrameParameter callFrameId(CallFrameId callFrameId) { this.callFrameId = callFrameId; return this; }
        public final RestartFrameParameter setCallFrameId(CallFrameId callFrameId) { return callFrameId(callFrameId); }
        public final CallFrameId callFrameId() { return callFrameId; }
        public final CallFrameId getCallFrameId() { return callFrameId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (callFrameId == null) throw new IllegalArgumentException("Debugger.RestartFrameParameter.callFrameId is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            callFrameId.toJson(strBuilder.append("\"callFrameId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public RestartFrameParameter() {}
        public RestartFrameParameter(
            @JsonProperty("callFrameId")CallFrameId callFrameId
        ) {
            this();
            this.callFrameId = callFrameId;
        }
        public CompletableFuture<RestartFrameResult> call() {
            return super.call("Debugger.restartFrame", RestartFrameResult.class,
                (code, msg)->new RestartFrameResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<RestartFrameResult> call(Executor exec) {
            return super.call("Debugger.restartFrame", RestartFrameResult.class,
                (code, msg)->new RestartFrameResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of restartFrame.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class RestartFrameResult extends ResultBase {
        /**New stack trace.*/
        private final List<CallFrame> callFrames;
        /**Async stack trace, if any.
        <em>Optional.</em>*/
        private final Runtime.StackTrace asyncStackTrace;
        /**Async stack trace, if any.
        <em>Optional.</em>
        <p><strong>Experimental.</strong></p>*/
        private final Runtime.StackTraceId asyncStackTraceId;
        public final List<CallFrame> callFrames() { return callFrames; }
        public final List<CallFrame> getCallFrames() { return callFrames(); }
        public final Runtime.StackTrace asyncStackTrace() { return asyncStackTrace; }
        public final Runtime.StackTrace getAsyncStackTrace() { return asyncStackTrace(); }
        public final Runtime.StackTraceId asyncStackTraceId() { return asyncStackTraceId; }
        public final Runtime.StackTraceId getAsyncStackTraceId() { return asyncStackTraceId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
                        strBuilder.append("\"callFrames\":[");
            callFrames.get(0).toJson(strBuilder);
            for (int i = 1; i < callFrames.size(); ++i)
                callFrames.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            if (asyncStackTrace != null) asyncStackTrace.toJson(strBuilder.append(",\"asyncStackTrace\":"));
            if (asyncStackTraceId != null) asyncStackTraceId.toJson(strBuilder.append(",\"asyncStackTraceId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public RestartFrameResult(
            @JsonProperty("callFrames")List<CallFrame> callFrames,
            @Nullable @JsonProperty("asyncStackTrace")Runtime.StackTrace asyncStackTrace,
            @Nullable @JsonProperty("asyncStackTraceId")Runtime.StackTraceId asyncStackTraceId
        ) {
            this.callFrames = callFrames;
            this.asyncStackTrace = asyncStackTrace;
            this.asyncStackTraceId = asyncStackTraceId;
        }
        public RestartFrameResult(ResultBase.FailedResult e) {
            super(e);
            callFrames = null;
            asyncStackTrace = null;
            asyncStackTraceId = null;
        }
    }
    /**Resumes JavaScript execution.*/
    public ResumeParameter resume() { final ResumeParameter v = new ResumeParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of resume.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ResumeParameter extends CommandBase {
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
        public ResumeParameter() {}
        public CompletableFuture<ResumeResult> call() {
            return super.call("Debugger.resume", ResumeResult.class,
                (code, msg)->new ResumeResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<ResumeResult> call(Executor exec) {
            return super.call("Debugger.resume", ResumeResult.class,
                (code, msg)->new ResumeResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of resume.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ResumeResult extends ResultBase {
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
        public ResumeResult() { super(); }
        public ResumeResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**This method is deprecated - use Debugger.stepInto with breakOnAsyncCall and
Debugger.pauseOnAsyncTask instead. Steps into next scheduled async task if any is scheduled
before next pause. Returns success when async task is actually scheduled, returns error if no
task were scheduled or another scheduleStepIntoAsync was called.
    <p><strong>Experimental.</strong></p>*/
    public ScheduleStepIntoAsyncParameter scheduleStepIntoAsync() { final ScheduleStepIntoAsyncParameter v = new ScheduleStepIntoAsyncParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of scheduleStepIntoAsync.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ScheduleStepIntoAsyncParameter extends CommandBase {
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
        public ScheduleStepIntoAsyncParameter() {}
        public CompletableFuture<ScheduleStepIntoAsyncResult> call() {
            return super.call("Debugger.scheduleStepIntoAsync", ScheduleStepIntoAsyncResult.class,
                (code, msg)->new ScheduleStepIntoAsyncResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<ScheduleStepIntoAsyncResult> call(Executor exec) {
            return super.call("Debugger.scheduleStepIntoAsync", ScheduleStepIntoAsyncResult.class,
                (code, msg)->new ScheduleStepIntoAsyncResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of scheduleStepIntoAsync.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ScheduleStepIntoAsyncResult extends ResultBase {
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
        public ScheduleStepIntoAsyncResult() { super(); }
        public ScheduleStepIntoAsyncResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Searches for given string in script content.*/
    public SearchInContentParameter searchInContent() { final SearchInContentParameter v = new SearchInContentParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of searchInContent.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SearchInContentParameter extends CommandBase {
        /**Id of the script to search in.*/
        private Runtime.ScriptId scriptId;
        /**String to search for.*/
        private String query;
        /**If true, search is case sensitive.
        <em>Optional.</em>*/
        private Boolean caseSensitive;
        /**If true, treats string parameter as regex.
        <em>Optional.</em>*/
        private Boolean isRegex;
        public final SearchInContentParameter scriptId(Runtime.ScriptId scriptId) { this.scriptId = scriptId; return this; }
        public final SearchInContentParameter setScriptId(Runtime.ScriptId scriptId) { return scriptId(scriptId); }
        public final Runtime.ScriptId scriptId() { return scriptId; }
        public final Runtime.ScriptId getScriptId() { return scriptId(); }
        public final SearchInContentParameter query(String query) { this.query = query; return this; }
        public final SearchInContentParameter setQuery(String query) { return query(query); }
        public final String query() { return query; }
        public final String getQuery() { return query(); }
        public final SearchInContentParameter caseSensitive(@Nullable Boolean caseSensitive) { this.caseSensitive = caseSensitive; return this; }
        public final SearchInContentParameter optCaseSensitive(@Nullable Boolean caseSensitive) { return caseSensitive(caseSensitive); }
        public final Boolean caseSensitive() { return caseSensitive; }
        public final Boolean getCaseSensitive() { return caseSensitive(); }
        public final SearchInContentParameter isRegex(@Nullable Boolean isRegex) { this.isRegex = isRegex; return this; }
        public final SearchInContentParameter optIsRegex(@Nullable Boolean isRegex) { return isRegex(isRegex); }
        public final Boolean isRegex() { return isRegex; }
        public final Boolean getIsRegex() { return isRegex(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (scriptId == null) throw new IllegalArgumentException("Debugger.SearchInContentParameter.scriptId is necessary field.");
            if (query == null) throw new IllegalArgumentException("Debugger.SearchInContentParameter.query is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            scriptId.toJson(strBuilder.append("\"scriptId\":"));
            strBuilder.append(",\"query\":").append('"').append(DomainBase.escapeQuote(query)).append('"');
            if (caseSensitive != null) strBuilder.append(",\"caseSensitive\":").append(caseSensitive);
            if (isRegex != null) strBuilder.append(",\"isRegex\":").append(isRegex);
            strBuilder.append('}');
            return strBuilder;
        }
        public SearchInContentParameter() {}
        public SearchInContentParameter(
            @JsonProperty("scriptId")Runtime.ScriptId scriptId,
            @JsonProperty("query")String query,
            @Nullable @JsonProperty("caseSensitive")Boolean caseSensitive,
            @Nullable @JsonProperty("isRegex")Boolean isRegex
        ) {
            this();
            this.scriptId = scriptId;
            this.query = query;
            this.caseSensitive = caseSensitive;
            this.isRegex = isRegex;
        }
        public CompletableFuture<SearchInContentResult> call() {
            return super.call("Debugger.searchInContent", SearchInContentResult.class,
                (code, msg)->new SearchInContentResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SearchInContentResult> call(Executor exec) {
            return super.call("Debugger.searchInContent", SearchInContentResult.class,
                (code, msg)->new SearchInContentResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of searchInContent.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SearchInContentResult extends ResultBase {
        /**List of search matches.*/
        private final List<SearchMatch> result;
        public final List<SearchMatch> result() { return result; }
        public final List<SearchMatch> getResult() { return result(); }
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
        public SearchInContentResult(
            @JsonProperty("result")List<SearchMatch> result
        ) {
            this.result = result;
        }
        public SearchInContentResult(ResultBase.FailedResult e) {
            super(e);
            result = null;
        }
    }
    /**Enables or disables async call stacks tracking.*/
    public SetAsyncCallStackDepthParameter setAsyncCallStackDepth() { final SetAsyncCallStackDepthParameter v = new SetAsyncCallStackDepthParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setAsyncCallStackDepth.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetAsyncCallStackDepthParameter extends CommandBase {
        /**Maximum depth of async call stacks. Setting to `0` will effectively disable collecting async
call stacks (default).*/
        private Integer maxDepth;
        public final SetAsyncCallStackDepthParameter maxDepth(Integer maxDepth) { this.maxDepth = maxDepth; return this; }
        public final SetAsyncCallStackDepthParameter setMaxDepth(Integer maxDepth) { return maxDepth(maxDepth); }
        public final Integer maxDepth() { return maxDepth; }
        public final Integer getMaxDepth() { return maxDepth(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (maxDepth == null) throw new IllegalArgumentException("Debugger.SetAsyncCallStackDepthParameter.maxDepth is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"maxDepth\":").append(maxDepth);
            strBuilder.append('}');
            return strBuilder;
        }
        public SetAsyncCallStackDepthParameter() {}
        public SetAsyncCallStackDepthParameter(
            @JsonProperty("maxDepth")Integer maxDepth
        ) {
            this();
            this.maxDepth = maxDepth;
        }
        public CompletableFuture<SetAsyncCallStackDepthResult> call() {
            return super.call("Debugger.setAsyncCallStackDepth", SetAsyncCallStackDepthResult.class,
                (code, msg)->new SetAsyncCallStackDepthResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetAsyncCallStackDepthResult> call(Executor exec) {
            return super.call("Debugger.setAsyncCallStackDepth", SetAsyncCallStackDepthResult.class,
                (code, msg)->new SetAsyncCallStackDepthResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setAsyncCallStackDepth.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetAsyncCallStackDepthResult extends ResultBase {
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
        public SetAsyncCallStackDepthResult() { super(); }
        public SetAsyncCallStackDepthResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Replace previous blackbox patterns with passed ones. Forces backend to skip stepping/pausing in
scripts with url matching one of the patterns. VM will try to leave blackboxed script by
performing 'step in' several times, finally resorting to 'step out' if unsuccessful.
    <p><strong>Experimental.</strong></p>*/
    public SetBlackboxPatternsParameter setBlackboxPatterns() { final SetBlackboxPatternsParameter v = new SetBlackboxPatternsParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setBlackboxPatterns.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetBlackboxPatternsParameter extends CommandBase {
        /**Array of regexps that will be used to check script url for blackbox state.*/
        private List<String> patterns;
        public final SetBlackboxPatternsParameter patterns(List<String> patterns) { this.patterns = patterns; return this; }
        public final SetBlackboxPatternsParameter setPatterns(List<String> patterns) { return patterns(patterns); }
        public final List<String> patterns() { return patterns; }
        public final List<String> getPatterns() { return patterns(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (patterns == null) throw new IllegalArgumentException("Debugger.SetBlackboxPatternsParameter.patterns is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
                        strBuilder.append("\"patterns\":[");
            strBuilder.append('"').append(DomainBase.escapeQuote(patterns.get(0))).append('"');
            for (int i = 1; i < patterns.size(); ++i)
                strBuilder.append(",\"").append(DomainBase.escapeQuote(patterns.get(i))).append('"');
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public SetBlackboxPatternsParameter() {}
        public SetBlackboxPatternsParameter(
            @JsonProperty("patterns")List<String> patterns
        ) {
            this();
            this.patterns = patterns;
        }
        public CompletableFuture<SetBlackboxPatternsResult> call() {
            return super.call("Debugger.setBlackboxPatterns", SetBlackboxPatternsResult.class,
                (code, msg)->new SetBlackboxPatternsResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetBlackboxPatternsResult> call(Executor exec) {
            return super.call("Debugger.setBlackboxPatterns", SetBlackboxPatternsResult.class,
                (code, msg)->new SetBlackboxPatternsResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setBlackboxPatterns.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetBlackboxPatternsResult extends ResultBase {
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
        public SetBlackboxPatternsResult() { super(); }
        public SetBlackboxPatternsResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Makes backend skip steps in the script in blackboxed ranges. VM will try leave blacklisted
scripts by performing 'step in' several times, finally resorting to 'step out' if unsuccessful.
Positions array contains positions where blackbox state is changed. First interval isn't
blackboxed. Array should be sorted.
    <p><strong>Experimental.</strong></p>*/
    public SetBlackboxedRangesParameter setBlackboxedRanges() { final SetBlackboxedRangesParameter v = new SetBlackboxedRangesParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setBlackboxedRanges.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetBlackboxedRangesParameter extends CommandBase {
        /**Id of the script.*/
        private Runtime.ScriptId scriptId;
        /**&lt;No document in protocol.&gt;*/
        private List<ScriptPosition> positions;
        public final SetBlackboxedRangesParameter scriptId(Runtime.ScriptId scriptId) { this.scriptId = scriptId; return this; }
        public final SetBlackboxedRangesParameter setScriptId(Runtime.ScriptId scriptId) { return scriptId(scriptId); }
        public final Runtime.ScriptId scriptId() { return scriptId; }
        public final Runtime.ScriptId getScriptId() { return scriptId(); }
        public final SetBlackboxedRangesParameter positions(List<ScriptPosition> positions) { this.positions = positions; return this; }
        public final SetBlackboxedRangesParameter setPositions(List<ScriptPosition> positions) { return positions(positions); }
        public final List<ScriptPosition> positions() { return positions; }
        public final List<ScriptPosition> getPositions() { return positions(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (scriptId == null) throw new IllegalArgumentException("Debugger.SetBlackboxedRangesParameter.scriptId is necessary field.");
            if (positions == null) throw new IllegalArgumentException("Debugger.SetBlackboxedRangesParameter.positions is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            scriptId.toJson(strBuilder.append("\"scriptId\":"));
                        strBuilder.append(",\"positions\":[");
            positions.get(0).toJson(strBuilder);
            for (int i = 1; i < positions.size(); ++i)
                positions.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public SetBlackboxedRangesParameter() {}
        public SetBlackboxedRangesParameter(
            @JsonProperty("scriptId")Runtime.ScriptId scriptId,
            @JsonProperty("positions")List<ScriptPosition> positions
        ) {
            this();
            this.scriptId = scriptId;
            this.positions = positions;
        }
        public CompletableFuture<SetBlackboxedRangesResult> call() {
            return super.call("Debugger.setBlackboxedRanges", SetBlackboxedRangesResult.class,
                (code, msg)->new SetBlackboxedRangesResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetBlackboxedRangesResult> call(Executor exec) {
            return super.call("Debugger.setBlackboxedRanges", SetBlackboxedRangesResult.class,
                (code, msg)->new SetBlackboxedRangesResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setBlackboxedRanges.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetBlackboxedRangesResult extends ResultBase {
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
        public SetBlackboxedRangesResult() { super(); }
        public SetBlackboxedRangesResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Sets JavaScript breakpoint at a given location.*/
    public SetBreakpointParameter setBreakpoint() { final SetBreakpointParameter v = new SetBreakpointParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setBreakpoint.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetBreakpointParameter extends CommandBase {
        /**Location to set breakpoint in.*/
        private Location location;
        /**Expression to use as a breakpoint condition. When specified, debugger will only stop on the
breakpoint if this expression evaluates to true.
        <em>Optional.</em>*/
        private String condition;
        public final SetBreakpointParameter location(Location location) { this.location = location; return this; }
        public final SetBreakpointParameter setLocation(Location location) { return location(location); }
        public final Location location() { return location; }
        public final Location getLocation() { return location(); }
        public final SetBreakpointParameter condition(@Nullable String condition) { this.condition = condition; return this; }
        public final SetBreakpointParameter optCondition(@Nullable String condition) { return condition(condition); }
        public final String condition() { return condition; }
        public final String getCondition() { return condition(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (location == null) throw new IllegalArgumentException("Debugger.SetBreakpointParameter.location is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            location.toJson(strBuilder.append("\"location\":"));
            if (condition != null) strBuilder.append(",\"condition\":").append('"').append(DomainBase.escapeQuote(condition)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public SetBreakpointParameter() {}
        public SetBreakpointParameter(
            @JsonProperty("location")Location location,
            @Nullable @JsonProperty("condition")String condition
        ) {
            this();
            this.location = location;
            this.condition = condition;
        }
        public CompletableFuture<SetBreakpointResult> call() {
            return super.call("Debugger.setBreakpoint", SetBreakpointResult.class,
                (code, msg)->new SetBreakpointResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetBreakpointResult> call(Executor exec) {
            return super.call("Debugger.setBreakpoint", SetBreakpointResult.class,
                (code, msg)->new SetBreakpointResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setBreakpoint.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetBreakpointResult extends ResultBase {
        /**Id of the created breakpoint for further reference.*/
        private final BreakpointId breakpointId;
        /**Location this breakpoint resolved into.*/
        private final Location actualLocation;
        public final BreakpointId breakpointId() { return breakpointId; }
        public final BreakpointId getBreakpointId() { return breakpointId(); }
        public final Location actualLocation() { return actualLocation; }
        public final Location getActualLocation() { return actualLocation(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            breakpointId.toJson(strBuilder.append("\"breakpointId\":"));
            actualLocation.toJson(strBuilder.append(",\"actualLocation\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public SetBreakpointResult(
            @JsonProperty("breakpointId")BreakpointId breakpointId,
            @JsonProperty("actualLocation")Location actualLocation
        ) {
            this.breakpointId = breakpointId;
            this.actualLocation = actualLocation;
        }
        public SetBreakpointResult(ResultBase.FailedResult e) {
            super(e);
            breakpointId = null;
            actualLocation = null;
        }
    }
    /**Sets JavaScript breakpoint at given location specified either by URL or URL regex. Once this
command is issued, all existing parsed scripts will have breakpoints resolved and returned in
`locations` property. Further matching script parsing will result in subsequent
`breakpointResolved` events issued. This logical breakpoint will survive page reloads.*/
    public SetBreakpointByUrlParameter setBreakpointByUrl() { final SetBreakpointByUrlParameter v = new SetBreakpointByUrlParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setBreakpointByUrl.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetBreakpointByUrlParameter extends CommandBase {
        /**Line number to set breakpoint at.*/
        private Integer lineNumber;
        /**URL of the resources to set breakpoint on.
        <em>Optional.</em>*/
        private String url;
        /**Regex pattern for the URLs of the resources to set breakpoints on. Either `url` or
`urlRegex` must be specified.
        <em>Optional.</em>*/
        private String urlRegex;
        /**Script hash of the resources to set breakpoint on.
        <em>Optional.</em>*/
        private String scriptHash;
        /**Offset in the line to set breakpoint at.
        <em>Optional.</em>*/
        private Integer columnNumber;
        /**Expression to use as a breakpoint condition. When specified, debugger will only stop on the
breakpoint if this expression evaluates to true.
        <em>Optional.</em>*/
        private String condition;
        public final SetBreakpointByUrlParameter lineNumber(Integer lineNumber) { this.lineNumber = lineNumber; return this; }
        public final SetBreakpointByUrlParameter setLineNumber(Integer lineNumber) { return lineNumber(lineNumber); }
        public final Integer lineNumber() { return lineNumber; }
        public final Integer getLineNumber() { return lineNumber(); }
        public final SetBreakpointByUrlParameter url(@Nullable String url) { this.url = url; return this; }
        public final SetBreakpointByUrlParameter optUrl(@Nullable String url) { return url(url); }
        public final String url() { return url; }
        public final String getUrl() { return url(); }
        public final SetBreakpointByUrlParameter urlRegex(@Nullable String urlRegex) { this.urlRegex = urlRegex; return this; }
        public final SetBreakpointByUrlParameter optUrlRegex(@Nullable String urlRegex) { return urlRegex(urlRegex); }
        public final String urlRegex() { return urlRegex; }
        public final String getUrlRegex() { return urlRegex(); }
        public final SetBreakpointByUrlParameter scriptHash(@Nullable String scriptHash) { this.scriptHash = scriptHash; return this; }
        public final SetBreakpointByUrlParameter optScriptHash(@Nullable String scriptHash) { return scriptHash(scriptHash); }
        public final String scriptHash() { return scriptHash; }
        public final String getScriptHash() { return scriptHash(); }
        public final SetBreakpointByUrlParameter columnNumber(@Nullable Integer columnNumber) { this.columnNumber = columnNumber; return this; }
        public final SetBreakpointByUrlParameter optColumnNumber(@Nullable Integer columnNumber) { return columnNumber(columnNumber); }
        public final Integer columnNumber() { return columnNumber; }
        public final Integer getColumnNumber() { return columnNumber(); }
        public final SetBreakpointByUrlParameter condition(@Nullable String condition) { this.condition = condition; return this; }
        public final SetBreakpointByUrlParameter optCondition(@Nullable String condition) { return condition(condition); }
        public final String condition() { return condition; }
        public final String getCondition() { return condition(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (lineNumber == null) throw new IllegalArgumentException("Debugger.SetBreakpointByUrlParameter.lineNumber is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"lineNumber\":").append(lineNumber);
            if (url != null) strBuilder.append(",\"url\":").append('"').append(DomainBase.escapeQuote(url)).append('"');
            if (urlRegex != null) strBuilder.append(",\"urlRegex\":").append('"').append(DomainBase.escapeQuote(urlRegex)).append('"');
            if (scriptHash != null) strBuilder.append(",\"scriptHash\":").append('"').append(DomainBase.escapeQuote(scriptHash)).append('"');
            if (columnNumber != null) strBuilder.append(",\"columnNumber\":").append(columnNumber);
            if (condition != null) strBuilder.append(",\"condition\":").append('"').append(DomainBase.escapeQuote(condition)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public SetBreakpointByUrlParameter() {}
        public SetBreakpointByUrlParameter(
            @JsonProperty("lineNumber")Integer lineNumber,
            @Nullable @JsonProperty("url")String url,
            @Nullable @JsonProperty("urlRegex")String urlRegex,
            @Nullable @JsonProperty("scriptHash")String scriptHash,
            @Nullable @JsonProperty("columnNumber")Integer columnNumber,
            @Nullable @JsonProperty("condition")String condition
        ) {
            this();
            this.lineNumber = lineNumber;
            this.url = url;
            this.urlRegex = urlRegex;
            this.scriptHash = scriptHash;
            this.columnNumber = columnNumber;
            this.condition = condition;
        }
        public CompletableFuture<SetBreakpointByUrlResult> call() {
            return super.call("Debugger.setBreakpointByUrl", SetBreakpointByUrlResult.class,
                (code, msg)->new SetBreakpointByUrlResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetBreakpointByUrlResult> call(Executor exec) {
            return super.call("Debugger.setBreakpointByUrl", SetBreakpointByUrlResult.class,
                (code, msg)->new SetBreakpointByUrlResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setBreakpointByUrl.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetBreakpointByUrlResult extends ResultBase {
        /**Id of the created breakpoint for further reference.*/
        private final BreakpointId breakpointId;
        /**List of the locations this breakpoint resolved into upon addition.*/
        private final List<Location> locations;
        public final BreakpointId breakpointId() { return breakpointId; }
        public final BreakpointId getBreakpointId() { return breakpointId(); }
        public final List<Location> locations() { return locations; }
        public final List<Location> getLocations() { return locations(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            breakpointId.toJson(strBuilder.append("\"breakpointId\":"));
                        strBuilder.append(",\"locations\":[");
            locations.get(0).toJson(strBuilder);
            for (int i = 1; i < locations.size(); ++i)
                locations.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public SetBreakpointByUrlResult(
            @JsonProperty("breakpointId")BreakpointId breakpointId,
            @JsonProperty("locations")List<Location> locations
        ) {
            this.breakpointId = breakpointId;
            this.locations = locations;
        }
        public SetBreakpointByUrlResult(ResultBase.FailedResult e) {
            super(e);
            breakpointId = null;
            locations = null;
        }
    }
    /**Activates / deactivates all breakpoints on the page.*/
    public SetBreakpointsActiveParameter setBreakpointsActive() { final SetBreakpointsActiveParameter v = new SetBreakpointsActiveParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setBreakpointsActive.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetBreakpointsActiveParameter extends CommandBase {
        /**New value for breakpoints active state.*/
        private Boolean active;
        public final SetBreakpointsActiveParameter active(Boolean active) { this.active = active; return this; }
        public final SetBreakpointsActiveParameter setActive(Boolean active) { return active(active); }
        public final Boolean active() { return active; }
        public final Boolean getActive() { return active(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (active == null) throw new IllegalArgumentException("Debugger.SetBreakpointsActiveParameter.active is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"active\":").append(active);
            strBuilder.append('}');
            return strBuilder;
        }
        public SetBreakpointsActiveParameter() {}
        public SetBreakpointsActiveParameter(
            @JsonProperty("active")Boolean active
        ) {
            this();
            this.active = active;
        }
        public CompletableFuture<SetBreakpointsActiveResult> call() {
            return super.call("Debugger.setBreakpointsActive", SetBreakpointsActiveResult.class,
                (code, msg)->new SetBreakpointsActiveResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetBreakpointsActiveResult> call(Executor exec) {
            return super.call("Debugger.setBreakpointsActive", SetBreakpointsActiveResult.class,
                (code, msg)->new SetBreakpointsActiveResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setBreakpointsActive.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetBreakpointsActiveResult extends ResultBase {
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
        public SetBreakpointsActiveResult() { super(); }
        public SetBreakpointsActiveResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Defines pause on exceptions state. Can be set to stop on all exceptions, uncaught exceptions or
no exceptions. Initial pause on exceptions state is `none`.*/
    public SetPauseOnExceptionsParameter setPauseOnExceptions() { final SetPauseOnExceptionsParameter v = new SetPauseOnExceptionsParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setPauseOnExceptions.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetPauseOnExceptionsParameter extends CommandBase {
        /**Pause on exceptions mode.*/
        @ParametersAreNonnullByDefault public enum State implements CommonDomainType {
            None("none"),
            Uncaught("uncaught"),
            All("all");

            private final String _value;
            /**Convert string representation to type.
             @throws IllegalArgumentException if given value cannot convert to enum type. */
            @JsonCreator public static State of(String value) {
                return Enum.valueOf(State.class, value.substring(0, 1).toUpperCase() + value.substring(1));
            }
            State(String value) { _value = value; }
            /**Check if parameter fields of method are all valid. */
            @Override public void check() throws IllegalArgumentException { /* Need not check */ }
            /**Convert method parameter object into json string and append into string builder.
             @return string builder instance that is given in parameter (for chaining coding style use.) */
            @Override public StringBuilder toJson(StringBuilder strBuilder) { return strBuilder.append('"').append(_value).append('"'); }
            @Override public String toString() { return "\"" + _value + "\""; }
        }
        private State state;
        public final SetPauseOnExceptionsParameter state(State state) { this.state = state; return this; }
        public final SetPauseOnExceptionsParameter setState(State state) { return state(state); }
        public final State state() { return state; }
        public final State getState() { return state(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (state == null) throw new IllegalArgumentException("Debugger.SetPauseOnExceptionsParameter.state is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"state\":").append(state);
            strBuilder.append('}');
            return strBuilder;
        }
        public SetPauseOnExceptionsParameter() {}
        public SetPauseOnExceptionsParameter(
            @JsonProperty("state")State state
        ) {
            this();
            this.state = state;
        }
        public CompletableFuture<SetPauseOnExceptionsResult> call() {
            return super.call("Debugger.setPauseOnExceptions", SetPauseOnExceptionsResult.class,
                (code, msg)->new SetPauseOnExceptionsResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetPauseOnExceptionsResult> call(Executor exec) {
            return super.call("Debugger.setPauseOnExceptions", SetPauseOnExceptionsResult.class,
                (code, msg)->new SetPauseOnExceptionsResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setPauseOnExceptions.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetPauseOnExceptionsResult extends ResultBase {
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
        public SetPauseOnExceptionsResult() { super(); }
        public SetPauseOnExceptionsResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Changes return value in top frame. Available only at return break position.
    <p><strong>Experimental.</strong></p>*/
    public SetReturnValueParameter setReturnValue() { final SetReturnValueParameter v = new SetReturnValueParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setReturnValue.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetReturnValueParameter extends CommandBase {
        /**New return value.*/
        private Runtime.CallArgument newValue;
        public final SetReturnValueParameter newValue(Runtime.CallArgument newValue) { this.newValue = newValue; return this; }
        public final SetReturnValueParameter setNewValue(Runtime.CallArgument newValue) { return newValue(newValue); }
        public final Runtime.CallArgument newValue() { return newValue; }
        public final Runtime.CallArgument getNewValue() { return newValue(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (newValue == null) throw new IllegalArgumentException("Debugger.SetReturnValueParameter.newValue is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            newValue.toJson(strBuilder.append("\"newValue\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public SetReturnValueParameter() {}
        public SetReturnValueParameter(
            @JsonProperty("newValue")Runtime.CallArgument newValue
        ) {
            this();
            this.newValue = newValue;
        }
        public CompletableFuture<SetReturnValueResult> call() {
            return super.call("Debugger.setReturnValue", SetReturnValueResult.class,
                (code, msg)->new SetReturnValueResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetReturnValueResult> call(Executor exec) {
            return super.call("Debugger.setReturnValue", SetReturnValueResult.class,
                (code, msg)->new SetReturnValueResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setReturnValue.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetReturnValueResult extends ResultBase {
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
        public SetReturnValueResult() { super(); }
        public SetReturnValueResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Edits JavaScript source live.*/
    public SetScriptSourceParameter setScriptSource() { final SetScriptSourceParameter v = new SetScriptSourceParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setScriptSource.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetScriptSourceParameter extends CommandBase {
        /**Id of the script to edit.*/
        private Runtime.ScriptId scriptId;
        /**New content of the script.*/
        private String scriptSource;
        /**If true the change will not actually be applied. Dry run may be used to get result
description without actually modifying the code.
        <em>Optional.</em>*/
        private Boolean dryRun;
        public final SetScriptSourceParameter scriptId(Runtime.ScriptId scriptId) { this.scriptId = scriptId; return this; }
        public final SetScriptSourceParameter setScriptId(Runtime.ScriptId scriptId) { return scriptId(scriptId); }
        public final Runtime.ScriptId scriptId() { return scriptId; }
        public final Runtime.ScriptId getScriptId() { return scriptId(); }
        public final SetScriptSourceParameter scriptSource(String scriptSource) { this.scriptSource = scriptSource; return this; }
        public final SetScriptSourceParameter setScriptSource(String scriptSource) { return scriptSource(scriptSource); }
        public final String scriptSource() { return scriptSource; }
        public final String getScriptSource() { return scriptSource(); }
        public final SetScriptSourceParameter dryRun(@Nullable Boolean dryRun) { this.dryRun = dryRun; return this; }
        public final SetScriptSourceParameter optDryRun(@Nullable Boolean dryRun) { return dryRun(dryRun); }
        public final Boolean dryRun() { return dryRun; }
        public final Boolean getDryRun() { return dryRun(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (scriptId == null) throw new IllegalArgumentException("Debugger.SetScriptSourceParameter.scriptId is necessary field.");
            if (scriptSource == null) throw new IllegalArgumentException("Debugger.SetScriptSourceParameter.scriptSource is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            scriptId.toJson(strBuilder.append("\"scriptId\":"));
            strBuilder.append(",\"scriptSource\":").append('"').append(DomainBase.escapeQuote(scriptSource)).append('"');
            if (dryRun != null) strBuilder.append(",\"dryRun\":").append(dryRun);
            strBuilder.append('}');
            return strBuilder;
        }
        public SetScriptSourceParameter() {}
        public SetScriptSourceParameter(
            @JsonProperty("scriptId")Runtime.ScriptId scriptId,
            @JsonProperty("scriptSource")String scriptSource,
            @Nullable @JsonProperty("dryRun")Boolean dryRun
        ) {
            this();
            this.scriptId = scriptId;
            this.scriptSource = scriptSource;
            this.dryRun = dryRun;
        }
        public CompletableFuture<SetScriptSourceResult> call() {
            return super.call("Debugger.setScriptSource", SetScriptSourceResult.class,
                (code, msg)->new SetScriptSourceResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetScriptSourceResult> call(Executor exec) {
            return super.call("Debugger.setScriptSource", SetScriptSourceResult.class,
                (code, msg)->new SetScriptSourceResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setScriptSource.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetScriptSourceResult extends ResultBase {
        /**New stack trace in case editing has happened while VM was stopped.
        <em>Optional.</em>*/
        private final List<CallFrame> callFrames;
        /**Whether current call stack  was modified after applying the changes.
        <em>Optional.</em>*/
        private final Boolean stackChanged;
        /**Async stack trace, if any.
        <em>Optional.</em>*/
        private final Runtime.StackTrace asyncStackTrace;
        /**Async stack trace, if any.
        <em>Optional.</em>
        <p><strong>Experimental.</strong></p>*/
        private final Runtime.StackTraceId asyncStackTraceId;
        /**Exception details if any.
        <em>Optional.</em>*/
        private final Runtime.ExceptionDetails exceptionDetails;
        public final List<CallFrame> callFrames() { return callFrames; }
        public final List<CallFrame> getCallFrames() { return callFrames(); }
        public final Boolean stackChanged() { return stackChanged; }
        public final Boolean getStackChanged() { return stackChanged(); }
        public final Runtime.StackTrace asyncStackTrace() { return asyncStackTrace; }
        public final Runtime.StackTrace getAsyncStackTrace() { return asyncStackTrace(); }
        public final Runtime.StackTraceId asyncStackTraceId() { return asyncStackTraceId; }
        public final Runtime.StackTraceId getAsyncStackTraceId() { return asyncStackTraceId(); }
        public final Runtime.ExceptionDetails exceptionDetails() { return exceptionDetails; }
        public final Runtime.ExceptionDetails getExceptionDetails() { return exceptionDetails(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            if (callFrames != null) {
                strBuilder.append("\"callFrames\":[");
                callFrames.get(0).toJson(strBuilder);
                for (int i = 1; i < callFrames.size(); ++i)
                    callFrames.get(i).toJson(strBuilder.append(','));
                strBuilder.append(']');
            }
            if (stackChanged != null) strBuilder.append(",\"stackChanged\":").append(stackChanged);
            if (asyncStackTrace != null) asyncStackTrace.toJson(strBuilder.append(",\"asyncStackTrace\":"));
            if (asyncStackTraceId != null) asyncStackTraceId.toJson(strBuilder.append(",\"asyncStackTraceId\":"));
            if (exceptionDetails != null) exceptionDetails.toJson(strBuilder.append(",\"exceptionDetails\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public SetScriptSourceResult(
            @Nullable @JsonProperty("callFrames")List<CallFrame> callFrames,
            @Nullable @JsonProperty("stackChanged")Boolean stackChanged,
            @Nullable @JsonProperty("asyncStackTrace")Runtime.StackTrace asyncStackTrace,
            @Nullable @JsonProperty("asyncStackTraceId")Runtime.StackTraceId asyncStackTraceId,
            @Nullable @JsonProperty("exceptionDetails")Runtime.ExceptionDetails exceptionDetails
        ) {
            this.callFrames = callFrames;
            this.stackChanged = stackChanged;
            this.asyncStackTrace = asyncStackTrace;
            this.asyncStackTraceId = asyncStackTraceId;
            this.exceptionDetails = exceptionDetails;
        }
        public SetScriptSourceResult(ResultBase.FailedResult e) {
            super(e);
            callFrames = null;
            stackChanged = null;
            asyncStackTrace = null;
            asyncStackTraceId = null;
            exceptionDetails = null;
        }
    }
    /**Makes page not interrupt on any pauses (breakpoint, exception, dom exception etc).*/
    public SetSkipAllPausesParameter setSkipAllPauses() { final SetSkipAllPausesParameter v = new SetSkipAllPausesParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setSkipAllPauses.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetSkipAllPausesParameter extends CommandBase {
        /**New value for skip pauses state.*/
        private Boolean skip;
        public final SetSkipAllPausesParameter skip(Boolean skip) { this.skip = skip; return this; }
        public final SetSkipAllPausesParameter setSkip(Boolean skip) { return skip(skip); }
        public final Boolean skip() { return skip; }
        public final Boolean getSkip() { return skip(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (skip == null) throw new IllegalArgumentException("Debugger.SetSkipAllPausesParameter.skip is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"skip\":").append(skip);
            strBuilder.append('}');
            return strBuilder;
        }
        public SetSkipAllPausesParameter() {}
        public SetSkipAllPausesParameter(
            @JsonProperty("skip")Boolean skip
        ) {
            this();
            this.skip = skip;
        }
        public CompletableFuture<SetSkipAllPausesResult> call() {
            return super.call("Debugger.setSkipAllPauses", SetSkipAllPausesResult.class,
                (code, msg)->new SetSkipAllPausesResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetSkipAllPausesResult> call(Executor exec) {
            return super.call("Debugger.setSkipAllPauses", SetSkipAllPausesResult.class,
                (code, msg)->new SetSkipAllPausesResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setSkipAllPauses.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetSkipAllPausesResult extends ResultBase {
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
        public SetSkipAllPausesResult() { super(); }
        public SetSkipAllPausesResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Changes value of variable in a callframe. Object-based scopes are not supported and must be
mutated manually.*/
    public SetVariableValueParameter setVariableValue() { final SetVariableValueParameter v = new SetVariableValueParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setVariableValue.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetVariableValueParameter extends CommandBase {
        /**0-based number of scope as was listed in scope chain. Only 'local', 'closure' and 'catch'
scope types are allowed. Other scopes could be manipulated manually.*/
        private Integer scopeNumber;
        /**Variable name.*/
        private String variableName;
        /**New variable value.*/
        private Runtime.CallArgument newValue;
        /**Id of callframe that holds variable.*/
        private CallFrameId callFrameId;
        public final SetVariableValueParameter scopeNumber(Integer scopeNumber) { this.scopeNumber = scopeNumber; return this; }
        public final SetVariableValueParameter setScopeNumber(Integer scopeNumber) { return scopeNumber(scopeNumber); }
        public final Integer scopeNumber() { return scopeNumber; }
        public final Integer getScopeNumber() { return scopeNumber(); }
        public final SetVariableValueParameter variableName(String variableName) { this.variableName = variableName; return this; }
        public final SetVariableValueParameter setVariableName(String variableName) { return variableName(variableName); }
        public final String variableName() { return variableName; }
        public final String getVariableName() { return variableName(); }
        public final SetVariableValueParameter newValue(Runtime.CallArgument newValue) { this.newValue = newValue; return this; }
        public final SetVariableValueParameter setNewValue(Runtime.CallArgument newValue) { return newValue(newValue); }
        public final Runtime.CallArgument newValue() { return newValue; }
        public final Runtime.CallArgument getNewValue() { return newValue(); }
        public final SetVariableValueParameter callFrameId(CallFrameId callFrameId) { this.callFrameId = callFrameId; return this; }
        public final SetVariableValueParameter setCallFrameId(CallFrameId callFrameId) { return callFrameId(callFrameId); }
        public final CallFrameId callFrameId() { return callFrameId; }
        public final CallFrameId getCallFrameId() { return callFrameId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (scopeNumber == null) throw new IllegalArgumentException("Debugger.SetVariableValueParameter.scopeNumber is necessary field.");
            if (variableName == null) throw new IllegalArgumentException("Debugger.SetVariableValueParameter.variableName is necessary field.");
            if (newValue == null) throw new IllegalArgumentException("Debugger.SetVariableValueParameter.newValue is necessary field.");
            if (callFrameId == null) throw new IllegalArgumentException("Debugger.SetVariableValueParameter.callFrameId is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"scopeNumber\":").append(scopeNumber);
            strBuilder.append(",\"variableName\":").append('"').append(DomainBase.escapeQuote(variableName)).append('"');
            newValue.toJson(strBuilder.append(",\"newValue\":"));
            callFrameId.toJson(strBuilder.append(",\"callFrameId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public SetVariableValueParameter() {}
        public SetVariableValueParameter(
            @JsonProperty("scopeNumber")Integer scopeNumber,
            @JsonProperty("variableName")String variableName,
            @JsonProperty("newValue")Runtime.CallArgument newValue,
            @JsonProperty("callFrameId")CallFrameId callFrameId
        ) {
            this();
            this.scopeNumber = scopeNumber;
            this.variableName = variableName;
            this.newValue = newValue;
            this.callFrameId = callFrameId;
        }
        public CompletableFuture<SetVariableValueResult> call() {
            return super.call("Debugger.setVariableValue", SetVariableValueResult.class,
                (code, msg)->new SetVariableValueResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetVariableValueResult> call(Executor exec) {
            return super.call("Debugger.setVariableValue", SetVariableValueResult.class,
                (code, msg)->new SetVariableValueResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setVariableValue.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetVariableValueResult extends ResultBase {
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
        public SetVariableValueResult() { super(); }
        public SetVariableValueResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Steps into the function call.*/
    public StepIntoParameter stepInto() { final StepIntoParameter v = new StepIntoParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of stepInto.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class StepIntoParameter extends CommandBase {
        /**Debugger will issue additional Debugger.paused notification if any async task is scheduled
before next pause.
        <em>Optional.</em>
        <p><strong>Experimental.</strong></p>*/
        private Boolean breakOnAsyncCall;
        public final StepIntoParameter breakOnAsyncCall(@Nullable Boolean breakOnAsyncCall) { this.breakOnAsyncCall = breakOnAsyncCall; return this; }
        public final StepIntoParameter optBreakOnAsyncCall(@Nullable Boolean breakOnAsyncCall) { return breakOnAsyncCall(breakOnAsyncCall); }
        public final Boolean breakOnAsyncCall() { return breakOnAsyncCall; }
        public final Boolean getBreakOnAsyncCall() { return breakOnAsyncCall(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            if (breakOnAsyncCall != null) strBuilder.append("\"breakOnAsyncCall\":").append(breakOnAsyncCall);
            strBuilder.append('}');
            return strBuilder;
        }
        public StepIntoParameter() {}
        public StepIntoParameter(
            @Nullable @JsonProperty("breakOnAsyncCall")Boolean breakOnAsyncCall
        ) {
            this();
            this.breakOnAsyncCall = breakOnAsyncCall;
        }
        public CompletableFuture<StepIntoResult> call() {
            return super.call("Debugger.stepInto", StepIntoResult.class,
                (code, msg)->new StepIntoResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<StepIntoResult> call(Executor exec) {
            return super.call("Debugger.stepInto", StepIntoResult.class,
                (code, msg)->new StepIntoResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of stepInto.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class StepIntoResult extends ResultBase {
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
        public StepIntoResult() { super(); }
        public StepIntoResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Steps out of the function call.*/
    public StepOutParameter stepOut() { final StepOutParameter v = new StepOutParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of stepOut.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class StepOutParameter extends CommandBase {
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
        public StepOutParameter() {}
        public CompletableFuture<StepOutResult> call() {
            return super.call("Debugger.stepOut", StepOutResult.class,
                (code, msg)->new StepOutResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<StepOutResult> call(Executor exec) {
            return super.call("Debugger.stepOut", StepOutResult.class,
                (code, msg)->new StepOutResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of stepOut.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class StepOutResult extends ResultBase {
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
        public StepOutResult() { super(); }
        public StepOutResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Steps over the statement.*/
    public StepOverParameter stepOver() { final StepOverParameter v = new StepOverParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of stepOver.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class StepOverParameter extends CommandBase {
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
        public StepOverParameter() {}
        public CompletableFuture<StepOverResult> call() {
            return super.call("Debugger.stepOver", StepOverResult.class,
                (code, msg)->new StepOverResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<StepOverResult> call(Executor exec) {
            return super.call("Debugger.stepOver", StepOverResult.class,
                (code, msg)->new StepOverResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of stepOver.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class StepOverResult extends ResultBase {
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
        public StepOverResult() { super(); }
        public StepOverResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Event parameter of Debugger.breakpointResolved.
     @see #onBreakpointResolved*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class BreakpointResolvedEventParameter implements CommonDomainType {
        /**Breakpoint unique identifier.*/
        private final BreakpointId breakpointId;
        /**Actual breakpoint location.*/
        private final Location location;
        public final BreakpointId breakpointId() { return breakpointId; }
        public final BreakpointId getBreakpointId() { return breakpointId(); }
        public final Location location() { return location; }
        public final Location getLocation() { return location(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            breakpointId.toJson(strBuilder.append("\"breakpointId\":"));
            location.toJson(strBuilder.append(",\"location\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        BreakpointResolvedEventParameter(
            @JsonProperty("breakpointId")BreakpointId breakpointId,
            @JsonProperty("location")Location location
        ) {
            this.breakpointId = breakpointId;
            this.location = location;
        }
    }
    /**Fired when breakpoint is resolved to an actual script and location.
     @see BreakpointResolvedEventParameter*/
    public void onBreakpointResolved(Consumer<BreakpointResolvedEventParameter> callback) {
        registerEventCallback("Debugger.breakpointResolved", node -> {
            BreakpointResolvedEventParameter param;
            try { param = EventCenter.deserializeJson(node, BreakpointResolvedEventParameter.class); }
            catch (IOException e) { e.printStackTrace(); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of Debugger.paused.
     @see #onPaused*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class PausedEventParameter implements CommonDomainType {
        /**Call stack the virtual machine stopped on.*/
        private final List<CallFrame> callFrames;
        /**Pause reason.*/
        @ParametersAreNonnullByDefault public enum Reason implements CommonDomainType {
            XHR("XHR"),
            DOM("DOM"),
            EventListener("EventListener"),
            Exception("exception"),
            Assert("assert"),
            DebugCommand("debugCommand"),
            PromiseRejection("promiseRejection"),
            OOM("OOM"),
            Other("other"),
            Ambiguous("ambiguous");

            private final String _value;
            /**Convert string representation to type.
             @throws IllegalArgumentException if given value cannot convert to enum type. */
            @JsonCreator public static Reason of(String value) {
                return Enum.valueOf(Reason.class, value.substring(0, 1).toUpperCase() + value.substring(1));
            }
            Reason(String value) { _value = value; }
            /**Check if parameter fields of method are all valid. */
            @Override public void check() throws IllegalArgumentException { /* Need not check */ }
            /**Convert method parameter object into json string and append into string builder.
             @return string builder instance that is given in parameter (for chaining coding style use.) */
            @Override public StringBuilder toJson(StringBuilder strBuilder) { return strBuilder.append('"').append(_value).append('"'); }
            @Override public String toString() { return "\"" + _value + "\""; }
        }
        private final Reason reason;
        /**Object containing break-specific auxiliary properties.
        <em>Optional.</em>*/
        private final Object data;
        /**Hit breakpoints IDs
        <em>Optional.</em>*/
        private final List<String> hitBreakpoints;
        /**Async stack trace, if any.
        <em>Optional.</em>*/
        private final Runtime.StackTrace asyncStackTrace;
        /**Async stack trace, if any.
        <em>Optional.</em>
        <p><strong>Experimental.</strong></p>*/
        private final Runtime.StackTraceId asyncStackTraceId;
        /**Just scheduled async call will have this stack trace as parent stack during async execution.
This field is available only after `Debugger.stepInto` call with `breakOnAsynCall` flag.
        <em>Optional.</em>
        <p><strong>Experimental.</strong></p>*/
        private final Runtime.StackTraceId asyncCallStackTraceId;
        public final List<CallFrame> callFrames() { return callFrames; }
        public final List<CallFrame> getCallFrames() { return callFrames(); }
        public final Reason reason() { return reason; }
        public final Reason getReason() { return reason(); }
        public final Object data() { return data; }
        public final Object getData() { return data(); }
        public final List<String> hitBreakpoints() { return hitBreakpoints; }
        public final List<String> getHitBreakpoints() { return hitBreakpoints(); }
        public final Runtime.StackTrace asyncStackTrace() { return asyncStackTrace; }
        public final Runtime.StackTrace getAsyncStackTrace() { return asyncStackTrace(); }
        public final Runtime.StackTraceId asyncStackTraceId() { return asyncStackTraceId; }
        public final Runtime.StackTraceId getAsyncStackTraceId() { return asyncStackTraceId(); }
        public final Runtime.StackTraceId asyncCallStackTraceId() { return asyncCallStackTraceId; }
        public final Runtime.StackTraceId getAsyncCallStackTraceId() { return asyncCallStackTraceId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
                        strBuilder.append("\"callFrames\":[");
            callFrames.get(0).toJson(strBuilder);
            for (int i = 1; i < callFrames.size(); ++i)
                callFrames.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            strBuilder.append(",\"reason\":").append(reason);
            if (data != null) strBuilder.append(",\"data\":").append(data);
            if (hitBreakpoints != null) {
                strBuilder.append(",\"hitBreakpoints\":[");
                strBuilder.append('"').append(DomainBase.escapeQuote(hitBreakpoints.get(0))).append('"');
                for (int i = 1; i < hitBreakpoints.size(); ++i)
                    strBuilder.append(",\"").append(DomainBase.escapeQuote(hitBreakpoints.get(i))).append('"');
                strBuilder.append(']');
            }
            if (asyncStackTrace != null) asyncStackTrace.toJson(strBuilder.append(",\"asyncStackTrace\":"));
            if (asyncStackTraceId != null) asyncStackTraceId.toJson(strBuilder.append(",\"asyncStackTraceId\":"));
            if (asyncCallStackTraceId != null) asyncCallStackTraceId.toJson(strBuilder.append(",\"asyncCallStackTraceId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        PausedEventParameter(
            @JsonProperty("callFrames")List<CallFrame> callFrames,
            @JsonProperty("reason")Reason reason,
            @Nullable @JsonProperty("data")Object data,
            @Nullable @JsonProperty("hitBreakpoints")List<String> hitBreakpoints,
            @Nullable @JsonProperty("asyncStackTrace")Runtime.StackTrace asyncStackTrace,
            @Nullable @JsonProperty("asyncStackTraceId")Runtime.StackTraceId asyncStackTraceId,
            @Nullable @JsonProperty("asyncCallStackTraceId")Runtime.StackTraceId asyncCallStackTraceId
        ) {
            this.callFrames = callFrames;
            this.reason = reason;
            this.data = data;
            this.hitBreakpoints = hitBreakpoints;
            this.asyncStackTrace = asyncStackTrace;
            this.asyncStackTraceId = asyncStackTraceId;
            this.asyncCallStackTraceId = asyncCallStackTraceId;
        }
    }
    /**Fired when the virtual machine stopped on breakpoint or exception or any other stop criteria.
     @see PausedEventParameter*/
    public void onPaused(Consumer<PausedEventParameter> callback) {
        registerEventCallback("Debugger.paused", node -> {
            PausedEventParameter param;
            try { param = EventCenter.deserializeJson(node, PausedEventParameter.class); }
            catch (IOException e) { e.printStackTrace(); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of Debugger.resumed.
     @see #onResumed*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ResumedEventParameter implements CommonDomainType {
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
        public ResumedEventParameter() {}
    }
    /**Fired when the virtual machine resumed execution.
     @see ResumedEventParameter*/
    public void onResumed(Consumer<ResumedEventParameter> callback) {
        registerEventCallback("Debugger.resumed", node -> {
            ResumedEventParameter param;
            try { param = EventCenter.deserializeJson(node, ResumedEventParameter.class); }
            catch (IOException e) { e.printStackTrace(); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of Debugger.scriptFailedToParse.
     @see #onScriptFailedToParse*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ScriptFailedToParseEventParameter implements CommonDomainType {
        /**Identifier of the script parsed.*/
        private final Runtime.ScriptId scriptId;
        /**URL or name of the script parsed (if any).*/
        private final String url;
        /**Line offset of the script within the resource with given URL (for script tags).*/
        private final Integer startLine;
        /**Column offset of the script within the resource with given URL.*/
        private final Integer startColumn;
        /**Last line of the script.*/
        private final Integer endLine;
        /**Length of the last line of the script.*/
        private final Integer endColumn;
        /**Specifies script creation context.*/
        private final Runtime.ExecutionContextId executionContextId;
        /**Content hash of the script.*/
        private final String hash;
        /**Embedder-specific auxiliary data.
        <em>Optional.</em>*/
        private final Object executionContextAuxData;
        /**URL of source map associated with script (if any).
        <em>Optional.</em>*/
        private final String sourceMapURL;
        /**True, if this script has sourceURL.
        <em>Optional.</em>*/
        private final Boolean hasSourceURL;
        /**True, if this script is ES6 module.
        <em>Optional.</em>*/
        private final Boolean isModule;
        /**This script length.
        <em>Optional.</em>*/
        private final Integer length;
        /**JavaScript top stack frame of where the script parsed event was triggered if available.
        <em>Optional.</em>
        <p><strong>Experimental.</strong></p>*/
        private final Runtime.StackTrace stackTrace;
        public final Runtime.ScriptId scriptId() { return scriptId; }
        public final Runtime.ScriptId getScriptId() { return scriptId(); }
        public final String url() { return url; }
        public final String getUrl() { return url(); }
        public final Integer startLine() { return startLine; }
        public final Integer getStartLine() { return startLine(); }
        public final Integer startColumn() { return startColumn; }
        public final Integer getStartColumn() { return startColumn(); }
        public final Integer endLine() { return endLine; }
        public final Integer getEndLine() { return endLine(); }
        public final Integer endColumn() { return endColumn; }
        public final Integer getEndColumn() { return endColumn(); }
        public final Runtime.ExecutionContextId executionContextId() { return executionContextId; }
        public final Runtime.ExecutionContextId getExecutionContextId() { return executionContextId(); }
        public final String hash() { return hash; }
        public final String getHash() { return hash(); }
        public final Object executionContextAuxData() { return executionContextAuxData; }
        public final Object getExecutionContextAuxData() { return executionContextAuxData(); }
        public final String sourceMapURL() { return sourceMapURL; }
        public final String getSourceMapURL() { return sourceMapURL(); }
        public final Boolean hasSourceURL() { return hasSourceURL; }
        public final Boolean getHasSourceURL() { return hasSourceURL(); }
        public final Boolean isModule() { return isModule; }
        public final Boolean getIsModule() { return isModule(); }
        public final Integer length() { return length; }
        public final Integer getLength() { return length(); }
        public final Runtime.StackTrace stackTrace() { return stackTrace; }
        public final Runtime.StackTrace getStackTrace() { return stackTrace(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            scriptId.toJson(strBuilder.append("\"scriptId\":"));
            strBuilder.append(",\"url\":").append('"').append(DomainBase.escapeQuote(url)).append('"');
            strBuilder.append(",\"startLine\":").append(startLine);
            strBuilder.append(",\"startColumn\":").append(startColumn);
            strBuilder.append(",\"endLine\":").append(endLine);
            strBuilder.append(",\"endColumn\":").append(endColumn);
            executionContextId.toJson(strBuilder.append(",\"executionContextId\":"));
            strBuilder.append(",\"hash\":").append('"').append(DomainBase.escapeQuote(hash)).append('"');
            if (executionContextAuxData != null) strBuilder.append(",\"executionContextAuxData\":").append(executionContextAuxData);
            if (sourceMapURL != null) strBuilder.append(",\"sourceMapURL\":").append('"').append(DomainBase.escapeQuote(sourceMapURL)).append('"');
            if (hasSourceURL != null) strBuilder.append(",\"hasSourceURL\":").append(hasSourceURL);
            if (isModule != null) strBuilder.append(",\"isModule\":").append(isModule);
            if (length != null) strBuilder.append(",\"length\":").append(length);
            if (stackTrace != null) stackTrace.toJson(strBuilder.append(",\"stackTrace\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        ScriptFailedToParseEventParameter(
            @JsonProperty("scriptId")Runtime.ScriptId scriptId,
            @JsonProperty("url")String url,
            @JsonProperty("startLine")Integer startLine,
            @JsonProperty("startColumn")Integer startColumn,
            @JsonProperty("endLine")Integer endLine,
            @JsonProperty("endColumn")Integer endColumn,
            @JsonProperty("executionContextId")Runtime.ExecutionContextId executionContextId,
            @JsonProperty("hash")String hash,
            @Nullable @JsonProperty("executionContextAuxData")Object executionContextAuxData,
            @Nullable @JsonProperty("sourceMapURL")String sourceMapURL,
            @Nullable @JsonProperty("hasSourceURL")Boolean hasSourceURL,
            @Nullable @JsonProperty("isModule")Boolean isModule,
            @Nullable @JsonProperty("length")Integer length,
            @Nullable @JsonProperty("stackTrace")Runtime.StackTrace stackTrace
        ) {
            this.scriptId = scriptId;
            this.url = url;
            this.startLine = startLine;
            this.startColumn = startColumn;
            this.endLine = endLine;
            this.endColumn = endColumn;
            this.executionContextId = executionContextId;
            this.hash = hash;
            this.executionContextAuxData = executionContextAuxData;
            this.sourceMapURL = sourceMapURL;
            this.hasSourceURL = hasSourceURL;
            this.isModule = isModule;
            this.length = length;
            this.stackTrace = stackTrace;
        }
    }
    /**Fired when virtual machine fails to parse the script.
     @see ScriptFailedToParseEventParameter*/
    public void onScriptFailedToParse(Consumer<ScriptFailedToParseEventParameter> callback) {
        registerEventCallback("Debugger.scriptFailedToParse", node -> {
            ScriptFailedToParseEventParameter param;
            try { param = EventCenter.deserializeJson(node, ScriptFailedToParseEventParameter.class); }
            catch (IOException e) { e.printStackTrace(); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of Debugger.scriptParsed.
     @see #onScriptParsed*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ScriptParsedEventParameter implements CommonDomainType {
        /**Identifier of the script parsed.*/
        private final Runtime.ScriptId scriptId;
        /**URL or name of the script parsed (if any).*/
        private final String url;
        /**Line offset of the script within the resource with given URL (for script tags).*/
        private final Integer startLine;
        /**Column offset of the script within the resource with given URL.*/
        private final Integer startColumn;
        /**Last line of the script.*/
        private final Integer endLine;
        /**Length of the last line of the script.*/
        private final Integer endColumn;
        /**Specifies script creation context.*/
        private final Runtime.ExecutionContextId executionContextId;
        /**Content hash of the script.*/
        private final String hash;
        /**Embedder-specific auxiliary data.
        <em>Optional.</em>*/
        private final Object executionContextAuxData;
        /**True, if this script is generated as a result of the live edit operation.
        <em>Optional.</em>
        <p><strong>Experimental.</strong></p>*/
        private final Boolean isLiveEdit;
        /**URL of source map associated with script (if any).
        <em>Optional.</em>*/
        private final String sourceMapURL;
        /**True, if this script has sourceURL.
        <em>Optional.</em>*/
        private final Boolean hasSourceURL;
        /**True, if this script is ES6 module.
        <em>Optional.</em>*/
        private final Boolean isModule;
        /**This script length.
        <em>Optional.</em>*/
        private final Integer length;
        /**JavaScript top stack frame of where the script parsed event was triggered if available.
        <em>Optional.</em>
        <p><strong>Experimental.</strong></p>*/
        private final Runtime.StackTrace stackTrace;
        public final Runtime.ScriptId scriptId() { return scriptId; }
        public final Runtime.ScriptId getScriptId() { return scriptId(); }
        public final String url() { return url; }
        public final String getUrl() { return url(); }
        public final Integer startLine() { return startLine; }
        public final Integer getStartLine() { return startLine(); }
        public final Integer startColumn() { return startColumn; }
        public final Integer getStartColumn() { return startColumn(); }
        public final Integer endLine() { return endLine; }
        public final Integer getEndLine() { return endLine(); }
        public final Integer endColumn() { return endColumn; }
        public final Integer getEndColumn() { return endColumn(); }
        public final Runtime.ExecutionContextId executionContextId() { return executionContextId; }
        public final Runtime.ExecutionContextId getExecutionContextId() { return executionContextId(); }
        public final String hash() { return hash; }
        public final String getHash() { return hash(); }
        public final Object executionContextAuxData() { return executionContextAuxData; }
        public final Object getExecutionContextAuxData() { return executionContextAuxData(); }
        public final Boolean isLiveEdit() { return isLiveEdit; }
        public final Boolean getIsLiveEdit() { return isLiveEdit(); }
        public final String sourceMapURL() { return sourceMapURL; }
        public final String getSourceMapURL() { return sourceMapURL(); }
        public final Boolean hasSourceURL() { return hasSourceURL; }
        public final Boolean getHasSourceURL() { return hasSourceURL(); }
        public final Boolean isModule() { return isModule; }
        public final Boolean getIsModule() { return isModule(); }
        public final Integer length() { return length; }
        public final Integer getLength() { return length(); }
        public final Runtime.StackTrace stackTrace() { return stackTrace; }
        public final Runtime.StackTrace getStackTrace() { return stackTrace(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            scriptId.toJson(strBuilder.append("\"scriptId\":"));
            strBuilder.append(",\"url\":").append('"').append(DomainBase.escapeQuote(url)).append('"');
            strBuilder.append(",\"startLine\":").append(startLine);
            strBuilder.append(",\"startColumn\":").append(startColumn);
            strBuilder.append(",\"endLine\":").append(endLine);
            strBuilder.append(",\"endColumn\":").append(endColumn);
            executionContextId.toJson(strBuilder.append(",\"executionContextId\":"));
            strBuilder.append(",\"hash\":").append('"').append(DomainBase.escapeQuote(hash)).append('"');
            if (executionContextAuxData != null) strBuilder.append(",\"executionContextAuxData\":").append(executionContextAuxData);
            if (isLiveEdit != null) strBuilder.append(",\"isLiveEdit\":").append(isLiveEdit);
            if (sourceMapURL != null) strBuilder.append(",\"sourceMapURL\":").append('"').append(DomainBase.escapeQuote(sourceMapURL)).append('"');
            if (hasSourceURL != null) strBuilder.append(",\"hasSourceURL\":").append(hasSourceURL);
            if (isModule != null) strBuilder.append(",\"isModule\":").append(isModule);
            if (length != null) strBuilder.append(",\"length\":").append(length);
            if (stackTrace != null) stackTrace.toJson(strBuilder.append(",\"stackTrace\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        ScriptParsedEventParameter(
            @JsonProperty("scriptId")Runtime.ScriptId scriptId,
            @JsonProperty("url")String url,
            @JsonProperty("startLine")Integer startLine,
            @JsonProperty("startColumn")Integer startColumn,
            @JsonProperty("endLine")Integer endLine,
            @JsonProperty("endColumn")Integer endColumn,
            @JsonProperty("executionContextId")Runtime.ExecutionContextId executionContextId,
            @JsonProperty("hash")String hash,
            @Nullable @JsonProperty("executionContextAuxData")Object executionContextAuxData,
            @Nullable @JsonProperty("isLiveEdit")Boolean isLiveEdit,
            @Nullable @JsonProperty("sourceMapURL")String sourceMapURL,
            @Nullable @JsonProperty("hasSourceURL")Boolean hasSourceURL,
            @Nullable @JsonProperty("isModule")Boolean isModule,
            @Nullable @JsonProperty("length")Integer length,
            @Nullable @JsonProperty("stackTrace")Runtime.StackTrace stackTrace
        ) {
            this.scriptId = scriptId;
            this.url = url;
            this.startLine = startLine;
            this.startColumn = startColumn;
            this.endLine = endLine;
            this.endColumn = endColumn;
            this.executionContextId = executionContextId;
            this.hash = hash;
            this.executionContextAuxData = executionContextAuxData;
            this.isLiveEdit = isLiveEdit;
            this.sourceMapURL = sourceMapURL;
            this.hasSourceURL = hasSourceURL;
            this.isModule = isModule;
            this.length = length;
            this.stackTrace = stackTrace;
        }
    }
    /**Fired when virtual machine parses script. This event is also fired for all known and uncollected
scripts upon enabling debugger.
     @see ScriptParsedEventParameter*/
    public void onScriptParsed(Consumer<ScriptParsedEventParameter> callback) {
        registerEventCallback("Debugger.scriptParsed", node -> {
            ScriptParsedEventParameter param;
            try { param = EventCenter.deserializeJson(node, ScriptParsedEventParameter.class); }
            catch (IOException e) { e.printStackTrace(); return; }
            callback.accept(param);
        });
    }
}

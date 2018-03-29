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

/**DOM debugging allows setting breakpoints on particular DOM operations and events. JavaScript
execution will stop on these operations as if there was a regular breakpoint set.
<p>From: browser_protocol.json</p>
<p>Protocol version: 1.3</p>
 @see DOM
 @see Debugger
 @author Joshua*/
@ParametersAreNonnullByDefault public class DOMDebugger extends DomainBase {
    public DOMDebugger(EventCenter evt, WebSocket ws) { super(evt, ws); }

    /**DOM breakpoint type.*/
    @ParametersAreNonnullByDefault public enum DOMBreakpointType implements CommonDomainType {
        Subtree_modified("subtree-modified"),
        Attribute_modified("attribute-modified"),
        Node_removed("node-removed");

        private final String _value;
        private static final Map<String, DOMBreakpointType> _Lookup;
        static {
            Map<String, DOMBreakpointType> m = new HashMap<>();
            for(DOMBreakpointType v: values()) m.put(v.toString(), v);
            _Lookup = Collections.unmodifiableMap(m);
        }
        /**Convert string representation to type.
         @throws IllegalArgumentException if given value cannot convert to enum type. */
        @JsonCreator public static DOMBreakpointType of(String value) {
            DOMBreakpointType v = _Lookup.get(value.toLowerCase());
            return v != null ? v : Enum.valueOf(DOMBreakpointType.class, value);
        }
        DOMBreakpointType(String value) { _value = value; }
        /**Check if parameter fields of method are all valid. */
        @Override public void check() throws IllegalArgumentException { /* Need not check */ }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) { return strBuilder.append(toString()); }
        @Override public String toString() { return _value; }
    }

    /**Object event listener.*/
    @ParametersAreNonnullByDefault public static class EventListener implements CommonDomainType {
        /**`EventListener`'s type.*/
        private String type;
        /**`EventListener`'s useCapture.*/
        private Boolean useCapture;
        /**`EventListener`'s passive flag.*/
        private Boolean passive;
        /**`EventListener`'s once flag.*/
        private Boolean once;
        /**Script id of the handler code.*/
        private Runtime.ScriptId scriptId;
        /**Line number in the script (0-based).*/
        private Integer lineNumber;
        /**Column number in the script (0-based).*/
        private Integer columnNumber;
        /**Event handler function value.
        <em>Optional.</em>*/
        private Runtime.RemoteObject handler;
        /**Event original handler function value.
        <em>Optional.</em>*/
        private Runtime.RemoteObject originalHandler;
        /**Node the listener is added to (if any).
        <em>Optional.</em>*/
        private DOM.BackendNodeId backendNodeId;
        public final EventListener type(String type) { this.type = type; return this; }
        public final EventListener setType(String type) { return type(type); }
        public final String type() { return type; }
        public final String getType() { return type(); }
        public final EventListener useCapture(Boolean useCapture) { this.useCapture = useCapture; return this; }
        public final EventListener setUseCapture(Boolean useCapture) { return useCapture(useCapture); }
        public final Boolean useCapture() { return useCapture; }
        public final Boolean getUseCapture() { return useCapture(); }
        public final EventListener passive(Boolean passive) { this.passive = passive; return this; }
        public final EventListener setPassive(Boolean passive) { return passive(passive); }
        public final Boolean passive() { return passive; }
        public final Boolean getPassive() { return passive(); }
        public final EventListener once(Boolean once) { this.once = once; return this; }
        public final EventListener setOnce(Boolean once) { return once(once); }
        public final Boolean once() { return once; }
        public final Boolean getOnce() { return once(); }
        public final EventListener scriptId(Runtime.ScriptId scriptId) { this.scriptId = scriptId; return this; }
        public final EventListener setScriptId(Runtime.ScriptId scriptId) { return scriptId(scriptId); }
        public final Runtime.ScriptId scriptId() { return scriptId; }
        public final Runtime.ScriptId getScriptId() { return scriptId(); }
        public final EventListener lineNumber(Integer lineNumber) { this.lineNumber = lineNumber; return this; }
        public final EventListener setLineNumber(Integer lineNumber) { return lineNumber(lineNumber); }
        public final Integer lineNumber() { return lineNumber; }
        public final Integer getLineNumber() { return lineNumber(); }
        public final EventListener columnNumber(Integer columnNumber) { this.columnNumber = columnNumber; return this; }
        public final EventListener setColumnNumber(Integer columnNumber) { return columnNumber(columnNumber); }
        public final Integer columnNumber() { return columnNumber; }
        public final Integer getColumnNumber() { return columnNumber(); }
        public final EventListener handler(@Nullable Runtime.RemoteObject handler) { this.handler = handler; return this; }
        public final EventListener optHandler(@Nullable Runtime.RemoteObject handler) { return handler(handler); }
        public final Runtime.RemoteObject handler() { return handler; }
        public final Runtime.RemoteObject getHandler() { return handler(); }
        public final EventListener originalHandler(@Nullable Runtime.RemoteObject originalHandler) { this.originalHandler = originalHandler; return this; }
        public final EventListener optOriginalHandler(@Nullable Runtime.RemoteObject originalHandler) { return originalHandler(originalHandler); }
        public final Runtime.RemoteObject originalHandler() { return originalHandler; }
        public final Runtime.RemoteObject getOriginalHandler() { return originalHandler(); }
        public final EventListener backendNodeId(@Nullable DOM.BackendNodeId backendNodeId) { this.backendNodeId = backendNodeId; return this; }
        public final EventListener optBackendNodeId(@Nullable DOM.BackendNodeId backendNodeId) { return backendNodeId(backendNodeId); }
        public final DOM.BackendNodeId backendNodeId() { return backendNodeId; }
        public final DOM.BackendNodeId getBackendNodeId() { return backendNodeId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (type == null) throw new IllegalArgumentException("DOMDebugger.EventListener.type is necessary field.");
            if (useCapture == null) throw new IllegalArgumentException("DOMDebugger.EventListener.useCapture is necessary field.");
            if (passive == null) throw new IllegalArgumentException("DOMDebugger.EventListener.passive is necessary field.");
            if (once == null) throw new IllegalArgumentException("DOMDebugger.EventListener.once is necessary field.");
            if (scriptId == null) throw new IllegalArgumentException("DOMDebugger.EventListener.scriptId is necessary field.");
            if (lineNumber == null) throw new IllegalArgumentException("DOMDebugger.EventListener.lineNumber is necessary field.");
            if (columnNumber == null) throw new IllegalArgumentException("DOMDebugger.EventListener.columnNumber is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"type\":").append('"').append(type).append('"');
            strBuilder.append(",\"useCapture\":").append(useCapture);
            strBuilder.append(",\"passive\":").append(passive);
            strBuilder.append(",\"once\":").append(once);
            scriptId.toJson(strBuilder.append(",\"scriptId\":"));
            strBuilder.append(",\"lineNumber\":").append(lineNumber);
            strBuilder.append(",\"columnNumber\":").append(columnNumber);
            if (handler != null) handler.toJson(strBuilder.append(",\"handler\":"));
            if (originalHandler != null) originalHandler.toJson(strBuilder.append(",\"originalHandler\":"));
            if (backendNodeId != null) backendNodeId.toJson(strBuilder.append(",\"backendNodeId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public EventListener() {}
        public EventListener(
            @JsonProperty("type")String type,
            @JsonProperty("useCapture")Boolean useCapture,
            @JsonProperty("passive")Boolean passive,
            @JsonProperty("once")Boolean once,
            @JsonProperty("scriptId")Runtime.ScriptId scriptId,
            @JsonProperty("lineNumber")Integer lineNumber,
            @JsonProperty("columnNumber")Integer columnNumber,
            @Nullable @JsonProperty("handler")Runtime.RemoteObject handler,
            @Nullable @JsonProperty("originalHandler")Runtime.RemoteObject originalHandler,
            @Nullable @JsonProperty("backendNodeId")DOM.BackendNodeId backendNodeId
        ) {
            this.type = type;
            this.useCapture = useCapture;
            this.passive = passive;
            this.once = once;
            this.scriptId = scriptId;
            this.lineNumber = lineNumber;
            this.columnNumber = columnNumber;
            this.handler = handler;
            this.originalHandler = originalHandler;
            this.backendNodeId = backendNodeId;
        }
    }
    /**Returns event listeners of the given object.*/
    public GetEventListenersParameter getEventListeners() { final GetEventListenersParameter v = new GetEventListenersParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of getEventListeners.*/
    @ParametersAreNonnullByDefault public static class GetEventListenersParameter extends CommandBase {
        /**Identifier of the object to return listeners for.*/
        private Runtime.RemoteObjectId objectId;
        /**The maximum depth at which Node children should be retrieved, defaults to 1. Use -1 for the
entire subtree or provide an integer larger than 0.
        <em>Optional.</em>*/
        private Integer depth;
        /**Whether or not iframes and shadow roots should be traversed when returning the subtree
(default is false). Reports listeners for all contexts if pierce is enabled.
        <em>Optional.</em>*/
        private Boolean pierce;
        public final GetEventListenersParameter objectId(Runtime.RemoteObjectId objectId) { this.objectId = objectId; return this; }
        public final GetEventListenersParameter setObjectId(Runtime.RemoteObjectId objectId) { return objectId(objectId); }
        public final Runtime.RemoteObjectId objectId() { return objectId; }
        public final Runtime.RemoteObjectId getObjectId() { return objectId(); }
        public final GetEventListenersParameter depth(@Nullable Integer depth) { this.depth = depth; return this; }
        public final GetEventListenersParameter optDepth(@Nullable Integer depth) { return depth(depth); }
        public final Integer depth() { return depth; }
        public final Integer getDepth() { return depth(); }
        public final GetEventListenersParameter pierce(@Nullable Boolean pierce) { this.pierce = pierce; return this; }
        public final GetEventListenersParameter optPierce(@Nullable Boolean pierce) { return pierce(pierce); }
        public final Boolean pierce() { return pierce; }
        public final Boolean getPierce() { return pierce(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (objectId == null) throw new IllegalArgumentException("DOMDebugger.GetEventListenersParameter.objectId is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            objectId.toJson(strBuilder.append("\"objectId\":"));
            if (depth != null) strBuilder.append(",\"depth\":").append(depth);
            if (pierce != null) strBuilder.append(",\"pierce\":").append(pierce);
            strBuilder.append('}');
            return strBuilder;
        }
        public GetEventListenersParameter() {}
        public GetEventListenersParameter(
            @JsonProperty("objectId")Runtime.RemoteObjectId objectId,
            @Nullable @JsonProperty("depth")Integer depth,
            @Nullable @JsonProperty("pierce")Boolean pierce
        ) {
            this();
            this.objectId = objectId;
            this.depth = depth;
            this.pierce = pierce;
        }
        public CompletableFuture<GetEventListenersResult> call() {
            return super.call("DOMDebugger.getEventListeners", GetEventListenersResult.class,
                (code, msg)->new GetEventListenersResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetEventListenersResult> call(Executor exec) {
            return super.call("DOMDebugger.getEventListeners", GetEventListenersResult.class,
                (code, msg)->new GetEventListenersResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of getEventListeners.*/
    @ParametersAreNonnullByDefault public static class GetEventListenersResult extends ResultBase {
        /**Array of relevant listeners.*/
        private final List<EventListener> listeners;
        public final List<EventListener> listeners() { return listeners; }
        public final List<EventListener> getListeners() { return listeners(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
                        strBuilder.append("\"listeners\":[");
            listeners.get(0).toJson(strBuilder);
            for (int i = 1; i < listeners.size(); ++i)
                listeners.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public GetEventListenersResult(
            @JsonProperty("listeners")List<EventListener> listeners
        ) {
            this.listeners = listeners;
        }
        public GetEventListenersResult(ResultBase.FailedResult e) {
            super(e);
            listeners = null;
        }
    }
    /**Removes DOM breakpoint that was set using `setDOMBreakpoint`.*/
    public RemoveDOMBreakpointParameter removeDOMBreakpoint() { final RemoveDOMBreakpointParameter v = new RemoveDOMBreakpointParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of removeDOMBreakpoint.*/
    @ParametersAreNonnullByDefault public static class RemoveDOMBreakpointParameter extends CommandBase {
        /**Identifier of the node to remove breakpoint from.*/
        private DOM.NodeId nodeId;
        /**Type of the breakpoint to remove.*/
        private DOMBreakpointType type;
        public final RemoveDOMBreakpointParameter nodeId(DOM.NodeId nodeId) { this.nodeId = nodeId; return this; }
        public final RemoveDOMBreakpointParameter setNodeId(DOM.NodeId nodeId) { return nodeId(nodeId); }
        public final DOM.NodeId nodeId() { return nodeId; }
        public final DOM.NodeId getNodeId() { return nodeId(); }
        public final RemoveDOMBreakpointParameter type(DOMBreakpointType type) { this.type = type; return this; }
        public final RemoveDOMBreakpointParameter setType(DOMBreakpointType type) { return type(type); }
        public final DOMBreakpointType type() { return type; }
        public final DOMBreakpointType getType() { return type(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (nodeId == null) throw new IllegalArgumentException("DOMDebugger.RemoveDOMBreakpointParameter.nodeId is necessary field.");
            if (type == null) throw new IllegalArgumentException("DOMDebugger.RemoveDOMBreakpointParameter.type is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            nodeId.toJson(strBuilder.append("\"nodeId\":"));
            type.toJson(strBuilder.append(",\"type\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public RemoveDOMBreakpointParameter() {}
        public RemoveDOMBreakpointParameter(
            @JsonProperty("nodeId")DOM.NodeId nodeId,
            @JsonProperty("type")DOMBreakpointType type
        ) {
            this();
            this.nodeId = nodeId;
            this.type = type;
        }
        public CompletableFuture<RemoveDOMBreakpointResult> call() {
            return super.call("DOMDebugger.removeDOMBreakpoint", RemoveDOMBreakpointResult.class,
                (code, msg)->new RemoveDOMBreakpointResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<RemoveDOMBreakpointResult> call(Executor exec) {
            return super.call("DOMDebugger.removeDOMBreakpoint", RemoveDOMBreakpointResult.class,
                (code, msg)->new RemoveDOMBreakpointResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of removeDOMBreakpoint.*/
    @ParametersAreNonnullByDefault public static class RemoveDOMBreakpointResult extends ResultBase {
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
        public RemoveDOMBreakpointResult() { super(); }
        public RemoveDOMBreakpointResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Removes breakpoint on particular DOM event.*/
    public RemoveEventListenerBreakpointParameter removeEventListenerBreakpoint() { final RemoveEventListenerBreakpointParameter v = new RemoveEventListenerBreakpointParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of removeEventListenerBreakpoint.*/
    @ParametersAreNonnullByDefault public static class RemoveEventListenerBreakpointParameter extends CommandBase {
        /**Event name.*/
        private String eventName;
        /**EventTarget interface name.
        <em>Optional.</em>
        <p><strong>Experimental.</strong></p>*/
        private String targetName;
        public final RemoveEventListenerBreakpointParameter eventName(String eventName) { this.eventName = eventName; return this; }
        public final RemoveEventListenerBreakpointParameter setEventName(String eventName) { return eventName(eventName); }
        public final String eventName() { return eventName; }
        public final String getEventName() { return eventName(); }
        public final RemoveEventListenerBreakpointParameter targetName(@Nullable String targetName) { this.targetName = targetName; return this; }
        public final RemoveEventListenerBreakpointParameter optTargetName(@Nullable String targetName) { return targetName(targetName); }
        public final String targetName() { return targetName; }
        public final String getTargetName() { return targetName(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (eventName == null) throw new IllegalArgumentException("DOMDebugger.RemoveEventListenerBreakpointParameter.eventName is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"eventName\":").append('"').append(eventName).append('"');
            if (targetName != null) strBuilder.append(",\"targetName\":").append('"').append(targetName).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public RemoveEventListenerBreakpointParameter() {}
        public RemoveEventListenerBreakpointParameter(
            @JsonProperty("eventName")String eventName,
            @Nullable @JsonProperty("targetName")String targetName
        ) {
            this();
            this.eventName = eventName;
            this.targetName = targetName;
        }
        public CompletableFuture<RemoveEventListenerBreakpointResult> call() {
            return super.call("DOMDebugger.removeEventListenerBreakpoint", RemoveEventListenerBreakpointResult.class,
                (code, msg)->new RemoveEventListenerBreakpointResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<RemoveEventListenerBreakpointResult> call(Executor exec) {
            return super.call("DOMDebugger.removeEventListenerBreakpoint", RemoveEventListenerBreakpointResult.class,
                (code, msg)->new RemoveEventListenerBreakpointResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of removeEventListenerBreakpoint.*/
    @ParametersAreNonnullByDefault public static class RemoveEventListenerBreakpointResult extends ResultBase {
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
        public RemoveEventListenerBreakpointResult() { super(); }
        public RemoveEventListenerBreakpointResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Removes breakpoint on particular native event.
    <p><strong>Experimental.</strong></p>*/
    public RemoveInstrumentationBreakpointParameter removeInstrumentationBreakpoint() { final RemoveInstrumentationBreakpointParameter v = new RemoveInstrumentationBreakpointParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of removeInstrumentationBreakpoint.
    <p><strong>Experimental.</strong></p>*/
    @ParametersAreNonnullByDefault public static class RemoveInstrumentationBreakpointParameter extends CommandBase {
        /**Instrumentation name to stop on.*/
        private String eventName;
        public final RemoveInstrumentationBreakpointParameter eventName(String eventName) { this.eventName = eventName; return this; }
        public final RemoveInstrumentationBreakpointParameter setEventName(String eventName) { return eventName(eventName); }
        public final String eventName() { return eventName; }
        public final String getEventName() { return eventName(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (eventName == null) throw new IllegalArgumentException("DOMDebugger.RemoveInstrumentationBreakpointParameter.eventName is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"eventName\":").append('"').append(eventName).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public RemoveInstrumentationBreakpointParameter() {}
        public RemoveInstrumentationBreakpointParameter(
            @JsonProperty("eventName")String eventName
        ) {
            this();
            this.eventName = eventName;
        }
        public CompletableFuture<RemoveInstrumentationBreakpointResult> call() {
            return super.call("DOMDebugger.removeInstrumentationBreakpoint", RemoveInstrumentationBreakpointResult.class,
                (code, msg)->new RemoveInstrumentationBreakpointResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<RemoveInstrumentationBreakpointResult> call(Executor exec) {
            return super.call("DOMDebugger.removeInstrumentationBreakpoint", RemoveInstrumentationBreakpointResult.class,
                (code, msg)->new RemoveInstrumentationBreakpointResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of removeInstrumentationBreakpoint.
    <p><strong>Experimental.</strong></p>*/
    @ParametersAreNonnullByDefault public static class RemoveInstrumentationBreakpointResult extends ResultBase {
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
        public RemoveInstrumentationBreakpointResult() { super(); }
        public RemoveInstrumentationBreakpointResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Removes breakpoint from XMLHttpRequest.*/
    public RemoveXHRBreakpointParameter removeXHRBreakpoint() { final RemoveXHRBreakpointParameter v = new RemoveXHRBreakpointParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of removeXHRBreakpoint.*/
    @ParametersAreNonnullByDefault public static class RemoveXHRBreakpointParameter extends CommandBase {
        /**Resource URL substring.*/
        private String url;
        public final RemoveXHRBreakpointParameter url(String url) { this.url = url; return this; }
        public final RemoveXHRBreakpointParameter setUrl(String url) { return url(url); }
        public final String url() { return url; }
        public final String getUrl() { return url(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (url == null) throw new IllegalArgumentException("DOMDebugger.RemoveXHRBreakpointParameter.url is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"url\":").append('"').append(url).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public RemoveXHRBreakpointParameter() {}
        public RemoveXHRBreakpointParameter(
            @JsonProperty("url")String url
        ) {
            this();
            this.url = url;
        }
        public CompletableFuture<RemoveXHRBreakpointResult> call() {
            return super.call("DOMDebugger.removeXHRBreakpoint", RemoveXHRBreakpointResult.class,
                (code, msg)->new RemoveXHRBreakpointResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<RemoveXHRBreakpointResult> call(Executor exec) {
            return super.call("DOMDebugger.removeXHRBreakpoint", RemoveXHRBreakpointResult.class,
                (code, msg)->new RemoveXHRBreakpointResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of removeXHRBreakpoint.*/
    @ParametersAreNonnullByDefault public static class RemoveXHRBreakpointResult extends ResultBase {
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
        public RemoveXHRBreakpointResult() { super(); }
        public RemoveXHRBreakpointResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Sets breakpoint on particular operation with DOM.*/
    public SetDOMBreakpointParameter setDOMBreakpoint() { final SetDOMBreakpointParameter v = new SetDOMBreakpointParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setDOMBreakpoint.*/
    @ParametersAreNonnullByDefault public static class SetDOMBreakpointParameter extends CommandBase {
        /**Identifier of the node to set breakpoint on.*/
        private DOM.NodeId nodeId;
        /**Type of the operation to stop upon.*/
        private DOMBreakpointType type;
        public final SetDOMBreakpointParameter nodeId(DOM.NodeId nodeId) { this.nodeId = nodeId; return this; }
        public final SetDOMBreakpointParameter setNodeId(DOM.NodeId nodeId) { return nodeId(nodeId); }
        public final DOM.NodeId nodeId() { return nodeId; }
        public final DOM.NodeId getNodeId() { return nodeId(); }
        public final SetDOMBreakpointParameter type(DOMBreakpointType type) { this.type = type; return this; }
        public final SetDOMBreakpointParameter setType(DOMBreakpointType type) { return type(type); }
        public final DOMBreakpointType type() { return type; }
        public final DOMBreakpointType getType() { return type(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (nodeId == null) throw new IllegalArgumentException("DOMDebugger.SetDOMBreakpointParameter.nodeId is necessary field.");
            if (type == null) throw new IllegalArgumentException("DOMDebugger.SetDOMBreakpointParameter.type is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            nodeId.toJson(strBuilder.append("\"nodeId\":"));
            type.toJson(strBuilder.append(",\"type\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public SetDOMBreakpointParameter() {}
        public SetDOMBreakpointParameter(
            @JsonProperty("nodeId")DOM.NodeId nodeId,
            @JsonProperty("type")DOMBreakpointType type
        ) {
            this();
            this.nodeId = nodeId;
            this.type = type;
        }
        public CompletableFuture<SetDOMBreakpointResult> call() {
            return super.call("DOMDebugger.setDOMBreakpoint", SetDOMBreakpointResult.class,
                (code, msg)->new SetDOMBreakpointResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetDOMBreakpointResult> call(Executor exec) {
            return super.call("DOMDebugger.setDOMBreakpoint", SetDOMBreakpointResult.class,
                (code, msg)->new SetDOMBreakpointResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setDOMBreakpoint.*/
    @ParametersAreNonnullByDefault public static class SetDOMBreakpointResult extends ResultBase {
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
        public SetDOMBreakpointResult() { super(); }
        public SetDOMBreakpointResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Sets breakpoint on particular DOM event.*/
    public SetEventListenerBreakpointParameter setEventListenerBreakpoint() { final SetEventListenerBreakpointParameter v = new SetEventListenerBreakpointParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setEventListenerBreakpoint.*/
    @ParametersAreNonnullByDefault public static class SetEventListenerBreakpointParameter extends CommandBase {
        /**DOM Event name to stop on (any DOM event will do).*/
        private String eventName;
        /**EventTarget interface name to stop on. If equal to `"*"` or not provided, will stop on any
EventTarget.
        <em>Optional.</em>
        <p><strong>Experimental.</strong></p>*/
        private String targetName;
        public final SetEventListenerBreakpointParameter eventName(String eventName) { this.eventName = eventName; return this; }
        public final SetEventListenerBreakpointParameter setEventName(String eventName) { return eventName(eventName); }
        public final String eventName() { return eventName; }
        public final String getEventName() { return eventName(); }
        public final SetEventListenerBreakpointParameter targetName(@Nullable String targetName) { this.targetName = targetName; return this; }
        public final SetEventListenerBreakpointParameter optTargetName(@Nullable String targetName) { return targetName(targetName); }
        public final String targetName() { return targetName; }
        public final String getTargetName() { return targetName(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (eventName == null) throw new IllegalArgumentException("DOMDebugger.SetEventListenerBreakpointParameter.eventName is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"eventName\":").append('"').append(eventName).append('"');
            if (targetName != null) strBuilder.append(",\"targetName\":").append('"').append(targetName).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public SetEventListenerBreakpointParameter() {}
        public SetEventListenerBreakpointParameter(
            @JsonProperty("eventName")String eventName,
            @Nullable @JsonProperty("targetName")String targetName
        ) {
            this();
            this.eventName = eventName;
            this.targetName = targetName;
        }
        public CompletableFuture<SetEventListenerBreakpointResult> call() {
            return super.call("DOMDebugger.setEventListenerBreakpoint", SetEventListenerBreakpointResult.class,
                (code, msg)->new SetEventListenerBreakpointResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetEventListenerBreakpointResult> call(Executor exec) {
            return super.call("DOMDebugger.setEventListenerBreakpoint", SetEventListenerBreakpointResult.class,
                (code, msg)->new SetEventListenerBreakpointResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setEventListenerBreakpoint.*/
    @ParametersAreNonnullByDefault public static class SetEventListenerBreakpointResult extends ResultBase {
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
        public SetEventListenerBreakpointResult() { super(); }
        public SetEventListenerBreakpointResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Sets breakpoint on particular native event.
    <p><strong>Experimental.</strong></p>*/
    public SetInstrumentationBreakpointParameter setInstrumentationBreakpoint() { final SetInstrumentationBreakpointParameter v = new SetInstrumentationBreakpointParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setInstrumentationBreakpoint.
    <p><strong>Experimental.</strong></p>*/
    @ParametersAreNonnullByDefault public static class SetInstrumentationBreakpointParameter extends CommandBase {
        /**Instrumentation name to stop on.*/
        private String eventName;
        public final SetInstrumentationBreakpointParameter eventName(String eventName) { this.eventName = eventName; return this; }
        public final SetInstrumentationBreakpointParameter setEventName(String eventName) { return eventName(eventName); }
        public final String eventName() { return eventName; }
        public final String getEventName() { return eventName(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (eventName == null) throw new IllegalArgumentException("DOMDebugger.SetInstrumentationBreakpointParameter.eventName is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"eventName\":").append('"').append(eventName).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public SetInstrumentationBreakpointParameter() {}
        public SetInstrumentationBreakpointParameter(
            @JsonProperty("eventName")String eventName
        ) {
            this();
            this.eventName = eventName;
        }
        public CompletableFuture<SetInstrumentationBreakpointResult> call() {
            return super.call("DOMDebugger.setInstrumentationBreakpoint", SetInstrumentationBreakpointResult.class,
                (code, msg)->new SetInstrumentationBreakpointResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetInstrumentationBreakpointResult> call(Executor exec) {
            return super.call("DOMDebugger.setInstrumentationBreakpoint", SetInstrumentationBreakpointResult.class,
                (code, msg)->new SetInstrumentationBreakpointResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setInstrumentationBreakpoint.
    <p><strong>Experimental.</strong></p>*/
    @ParametersAreNonnullByDefault public static class SetInstrumentationBreakpointResult extends ResultBase {
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
        public SetInstrumentationBreakpointResult() { super(); }
        public SetInstrumentationBreakpointResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Sets breakpoint on XMLHttpRequest.*/
    public SetXHRBreakpointParameter setXHRBreakpoint() { final SetXHRBreakpointParameter v = new SetXHRBreakpointParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setXHRBreakpoint.*/
    @ParametersAreNonnullByDefault public static class SetXHRBreakpointParameter extends CommandBase {
        /**Resource URL substring. All XHRs having this substring in the URL will get stopped upon.*/
        private String url;
        public final SetXHRBreakpointParameter url(String url) { this.url = url; return this; }
        public final SetXHRBreakpointParameter setUrl(String url) { return url(url); }
        public final String url() { return url; }
        public final String getUrl() { return url(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (url == null) throw new IllegalArgumentException("DOMDebugger.SetXHRBreakpointParameter.url is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"url\":").append('"').append(url).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public SetXHRBreakpointParameter() {}
        public SetXHRBreakpointParameter(
            @JsonProperty("url")String url
        ) {
            this();
            this.url = url;
        }
        public CompletableFuture<SetXHRBreakpointResult> call() {
            return super.call("DOMDebugger.setXHRBreakpoint", SetXHRBreakpointResult.class,
                (code, msg)->new SetXHRBreakpointResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetXHRBreakpointResult> call(Executor exec) {
            return super.call("DOMDebugger.setXHRBreakpoint", SetXHRBreakpointResult.class,
                (code, msg)->new SetXHRBreakpointResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setXHRBreakpoint.*/
    @ParametersAreNonnullByDefault public static class SetXHRBreakpointResult extends ResultBase {
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
        public SetXHRBreakpointResult() { super(); }
        public SetXHRBreakpointResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
}

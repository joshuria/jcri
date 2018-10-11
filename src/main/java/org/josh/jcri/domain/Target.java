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

/**Supports additional targets discovery and allows to attach to them.
<p>From: browser_protocol.json</p>
<p>Protocol version: 1.3</p>
 @author Joshua*/
@ParametersAreNonnullByDefault public class Target extends DomainBase {
    public Target(EventCenter evt, WebSocket ws) { super(evt, ws); }

    /**&lt;No document in protocol.&gt;*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class TargetID implements CommonDomainType {
        private String _value;
        public TargetID() {}
        public TargetID(String value) { _value = value; }
        public final TargetID value(String value) { _value = value; return this; }
        public final String value() { return _value; }
        public final TargetID setValue(String value) { return value(value); }
        public final String getValue() { return value(); }
        @Override public String toString() { return _value; }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (_value == null) throw new IllegalArgumentException("Target.TargetID.value is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            return strBuilder.append('"').append(DomainBase.escapeJson(_value)).append('"');
        }
    }

    /**Unique identifier of attached debugging session.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SessionID implements CommonDomainType {
        private String _value;
        public SessionID() {}
        public SessionID(String value) { _value = value; }
        public final SessionID value(String value) { _value = value; return this; }
        public final String value() { return _value; }
        public final SessionID setValue(String value) { return value(value); }
        public final String getValue() { return value(); }
        @Override public String toString() { return _value; }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (_value == null) throw new IllegalArgumentException("Target.SessionID.value is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            return strBuilder.append('"').append(DomainBase.escapeJson(_value)).append('"');
        }
    }

    /**&lt;No document in protocol.&gt;
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class BrowserContextID implements CommonDomainType {
        private String _value;
        public BrowserContextID() {}
        public BrowserContextID(String value) { _value = value; }
        public final BrowserContextID value(String value) { _value = value; return this; }
        public final String value() { return _value; }
        public final BrowserContextID setValue(String value) { return value(value); }
        public final String getValue() { return value(); }
        @Override public String toString() { return _value; }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (_value == null) throw new IllegalArgumentException("Target.BrowserContextID.value is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            return strBuilder.append('"').append(DomainBase.escapeJson(_value)).append('"');
        }
    }

    /**&lt;No document in protocol.&gt;*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class TargetInfo implements CommonDomainType {
        /**&lt;No document in protocol.&gt;*/
        private TargetID targetId;
        /**&lt;No document in protocol.&gt;*/
        private String type;
        /**&lt;No document in protocol.&gt;*/
        private String title;
        /**&lt;No document in protocol.&gt;*/
        private String url;
        /**Whether the target has an attached client.*/
        private Boolean attached;
        /**Opener target Id
        <em>Optional.</em>*/
        private TargetID openerId;
        /**&lt;No document in protocol.&gt;
        <em>Optional.</em>
        <p><strong>Experimental.</strong></p>*/
        private BrowserContextID browserContextId;
        public final TargetInfo targetId(TargetID targetId) { this.targetId = targetId; return this; }
        public final TargetInfo setTargetId(TargetID targetId) { return targetId(targetId); }
        public final TargetID targetId() { return targetId; }
        public final TargetID getTargetId() { return targetId(); }
        public final TargetInfo type(String type) { this.type = type; return this; }
        public final TargetInfo setType(String type) { return type(type); }
        public final String type() { return type; }
        public final String getType() { return type(); }
        public final TargetInfo title(String title) { this.title = title; return this; }
        public final TargetInfo setTitle(String title) { return title(title); }
        public final String title() { return title; }
        public final String getTitle() { return title(); }
        public final TargetInfo url(String url) { this.url = url; return this; }
        public final TargetInfo setUrl(String url) { return url(url); }
        public final String url() { return url; }
        public final String getUrl() { return url(); }
        public final TargetInfo attached(Boolean attached) { this.attached = attached; return this; }
        public final TargetInfo setAttached(Boolean attached) { return attached(attached); }
        public final Boolean attached() { return attached; }
        public final Boolean getAttached() { return attached(); }
        public final TargetInfo openerId(@Nullable TargetID openerId) { this.openerId = openerId; return this; }
        public final TargetInfo optOpenerId(@Nullable TargetID openerId) { return openerId(openerId); }
        public final TargetID openerId() { return openerId; }
        public final TargetID getOpenerId() { return openerId(); }
        public final TargetInfo browserContextId(@Nullable BrowserContextID browserContextId) { this.browserContextId = browserContextId; return this; }
        public final TargetInfo optBrowserContextId(@Nullable BrowserContextID browserContextId) { return browserContextId(browserContextId); }
        public final BrowserContextID browserContextId() { return browserContextId; }
        public final BrowserContextID getBrowserContextId() { return browserContextId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (targetId == null) throw new IllegalArgumentException("Target.TargetInfo.targetId is necessary field.");
            if (type == null) throw new IllegalArgumentException("Target.TargetInfo.type is necessary field.");
            if (title == null) throw new IllegalArgumentException("Target.TargetInfo.title is necessary field.");
            if (url == null) throw new IllegalArgumentException("Target.TargetInfo.url is necessary field.");
            if (attached == null) throw new IllegalArgumentException("Target.TargetInfo.attached is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            targetId.toJson(strBuilder.append("\"targetId\":"));
            strBuilder.append(",\"type\":").append('"').append(DomainBase.escapeJson(type)).append('"');
            strBuilder.append(",\"title\":").append('"').append(DomainBase.escapeJson(title)).append('"');
            strBuilder.append(",\"url\":").append('"').append(DomainBase.escapeJson(url)).append('"');
            strBuilder.append(",\"attached\":").append(attached);
            if (openerId != null) openerId.toJson(strBuilder.append(",\"openerId\":"));
            if (browserContextId != null) browserContextId.toJson(strBuilder.append(",\"browserContextId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public TargetInfo() {}
        public TargetInfo(
            @JsonProperty("targetId")TargetID targetId,
            @JsonProperty("type")String type,
            @JsonProperty("title")String title,
            @JsonProperty("url")String url,
            @JsonProperty("attached")Boolean attached,
            @Nullable @JsonProperty("openerId")TargetID openerId,
            @Nullable @JsonProperty("browserContextId")BrowserContextID browserContextId
        ) {
            this.targetId = targetId;
            this.type = type;
            this.title = title;
            this.url = url;
            this.attached = attached;
            this.openerId = openerId;
            this.browserContextId = browserContextId;
        }
    }

    /**&lt;No document in protocol.&gt;
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class RemoteLocation implements CommonDomainType {
        /**&lt;No document in protocol.&gt;*/
        private String host;
        /**&lt;No document in protocol.&gt;*/
        private Integer port;
        public final RemoteLocation host(String host) { this.host = host; return this; }
        public final RemoteLocation setHost(String host) { return host(host); }
        public final String host() { return host; }
        public final String getHost() { return host(); }
        public final RemoteLocation port(Integer port) { this.port = port; return this; }
        public final RemoteLocation setPort(Integer port) { return port(port); }
        public final Integer port() { return port; }
        public final Integer getPort() { return port(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (host == null) throw new IllegalArgumentException("Target.RemoteLocation.host is necessary field.");
            if (port == null) throw new IllegalArgumentException("Target.RemoteLocation.port is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"host\":").append('"').append(DomainBase.escapeJson(host)).append('"');
            strBuilder.append(",\"port\":").append(port);
            strBuilder.append('}');
            return strBuilder;
        }
        public RemoteLocation() {}
        public RemoteLocation(
            @JsonProperty("host")String host,
            @JsonProperty("port")Integer port
        ) {
            this.host = host;
            this.port = port;
        }
    }
    /**Activates (focuses) the target.*/
    public ActivateTargetParameter activateTarget() { final ActivateTargetParameter v = new ActivateTargetParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of activateTarget.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ActivateTargetParameter extends CommandBase {
        /**&lt;No document in protocol.&gt;*/
        private TargetID targetId;
        public final ActivateTargetParameter targetId(TargetID targetId) { this.targetId = targetId; return this; }
        public final ActivateTargetParameter setTargetId(TargetID targetId) { return targetId(targetId); }
        public final TargetID targetId() { return targetId; }
        public final TargetID getTargetId() { return targetId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (targetId == null) throw new IllegalArgumentException("Target.ActivateTargetParameter.targetId is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            targetId.toJson(strBuilder.append("\"targetId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public ActivateTargetParameter() {}
        public ActivateTargetParameter(
            @JsonProperty("targetId")TargetID targetId
        ) {
            this();
            this.targetId = targetId;
        }
        public CompletableFuture<ActivateTargetResult> call() {
            return super.call("Target.activateTarget", ActivateTargetResult.class,
                (code, msg)->new ActivateTargetResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<ActivateTargetResult> callAsync() {
            return super.callAsync("Target.activateTarget", ActivateTargetResult.class,
                (code, msg)->new ActivateTargetResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<ActivateTargetResult> callAsync(Executor exec) {
            return super.callAsync("Target.activateTarget", ActivateTargetResult.class,
                (code, msg)->new ActivateTargetResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of activateTarget.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ActivateTargetResult extends ResultBase {
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
        public ActivateTargetResult() { super(); }
        public ActivateTargetResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Attaches to the target with given id.*/
    public AttachToTargetParameter attachToTarget() { final AttachToTargetParameter v = new AttachToTargetParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of attachToTarget.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class AttachToTargetParameter extends CommandBase {
        /**&lt;No document in protocol.&gt;*/
        private TargetID targetId;
        /**Enables "flat" access to the session via specifying sessionId attribute in the commands.
        <em>Optional.</em>
        <p><strong>Experimental.</strong></p>*/
        private Boolean flatten;
        public final AttachToTargetParameter targetId(TargetID targetId) { this.targetId = targetId; return this; }
        public final AttachToTargetParameter setTargetId(TargetID targetId) { return targetId(targetId); }
        public final TargetID targetId() { return targetId; }
        public final TargetID getTargetId() { return targetId(); }
        public final AttachToTargetParameter flatten(@Nullable Boolean flatten) { this.flatten = flatten; return this; }
        public final AttachToTargetParameter optFlatten(@Nullable Boolean flatten) { return flatten(flatten); }
        public final Boolean flatten() { return flatten; }
        public final Boolean getFlatten() { return flatten(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (targetId == null) throw new IllegalArgumentException("Target.AttachToTargetParameter.targetId is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            targetId.toJson(strBuilder.append("\"targetId\":"));
            if (flatten != null) strBuilder.append(",\"flatten\":").append(flatten);
            strBuilder.append('}');
            return strBuilder;
        }
        public AttachToTargetParameter() {}
        public AttachToTargetParameter(
            @JsonProperty("targetId")TargetID targetId,
            @Nullable @JsonProperty("flatten")Boolean flatten
        ) {
            this();
            this.targetId = targetId;
            this.flatten = flatten;
        }
        public CompletableFuture<AttachToTargetResult> call() {
            return super.call("Target.attachToTarget", AttachToTargetResult.class,
                (code, msg)->new AttachToTargetResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<AttachToTargetResult> callAsync() {
            return super.callAsync("Target.attachToTarget", AttachToTargetResult.class,
                (code, msg)->new AttachToTargetResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<AttachToTargetResult> callAsync(Executor exec) {
            return super.callAsync("Target.attachToTarget", AttachToTargetResult.class,
                (code, msg)->new AttachToTargetResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of attachToTarget.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class AttachToTargetResult extends ResultBase {
        /**Id assigned to the session.*/
        private final SessionID sessionId;
        public final SessionID sessionId() { return sessionId; }
        public final SessionID getSessionId() { return sessionId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            sessionId.toJson(strBuilder.append("\"sessionId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public AttachToTargetResult(
            @JsonProperty("sessionId")SessionID sessionId
        ) {
            this.sessionId = sessionId;
        }
        public AttachToTargetResult(ResultBase.FailedResult e) {
            super(e);
            sessionId = null;
        }
    }
    /**Attaches to the browser target, only uses flat sessionId mode.
    <p><strong>Experimental.</strong></p>*/
    public AttachToBrowserTargetParameter attachToBrowserTarget() { final AttachToBrowserTargetParameter v = new AttachToBrowserTargetParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of attachToBrowserTarget.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class AttachToBrowserTargetParameter extends CommandBase {
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
        public AttachToBrowserTargetParameter() {}
        public CompletableFuture<AttachToBrowserTargetResult> call() {
            return super.call("Target.attachToBrowserTarget", AttachToBrowserTargetResult.class,
                (code, msg)->new AttachToBrowserTargetResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<AttachToBrowserTargetResult> callAsync() {
            return super.callAsync("Target.attachToBrowserTarget", AttachToBrowserTargetResult.class,
                (code, msg)->new AttachToBrowserTargetResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<AttachToBrowserTargetResult> callAsync(Executor exec) {
            return super.callAsync("Target.attachToBrowserTarget", AttachToBrowserTargetResult.class,
                (code, msg)->new AttachToBrowserTargetResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of attachToBrowserTarget.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class AttachToBrowserTargetResult extends ResultBase {
        /**Id assigned to the session.*/
        private final SessionID sessionId;
        public final SessionID sessionId() { return sessionId; }
        public final SessionID getSessionId() { return sessionId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            sessionId.toJson(strBuilder.append("\"sessionId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public AttachToBrowserTargetResult(
            @JsonProperty("sessionId")SessionID sessionId
        ) {
            this.sessionId = sessionId;
        }
        public AttachToBrowserTargetResult(ResultBase.FailedResult e) {
            super(e);
            sessionId = null;
        }
    }
    /**Closes the target. If the target is a page that gets closed too.*/
    public CloseTargetParameter closeTarget() { final CloseTargetParameter v = new CloseTargetParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of closeTarget.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class CloseTargetParameter extends CommandBase {
        /**&lt;No document in protocol.&gt;*/
        private TargetID targetId;
        public final CloseTargetParameter targetId(TargetID targetId) { this.targetId = targetId; return this; }
        public final CloseTargetParameter setTargetId(TargetID targetId) { return targetId(targetId); }
        public final TargetID targetId() { return targetId; }
        public final TargetID getTargetId() { return targetId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (targetId == null) throw new IllegalArgumentException("Target.CloseTargetParameter.targetId is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            targetId.toJson(strBuilder.append("\"targetId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public CloseTargetParameter() {}
        public CloseTargetParameter(
            @JsonProperty("targetId")TargetID targetId
        ) {
            this();
            this.targetId = targetId;
        }
        public CompletableFuture<CloseTargetResult> call() {
            return super.call("Target.closeTarget", CloseTargetResult.class,
                (code, msg)->new CloseTargetResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<CloseTargetResult> callAsync() {
            return super.callAsync("Target.closeTarget", CloseTargetResult.class,
                (code, msg)->new CloseTargetResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<CloseTargetResult> callAsync(Executor exec) {
            return super.callAsync("Target.closeTarget", CloseTargetResult.class,
                (code, msg)->new CloseTargetResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of closeTarget.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class CloseTargetResult extends ResultBase {
        /**&lt;No document in protocol.&gt;*/
        private final Boolean success;
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
            strBuilder.append("\"success\":").append(success);
            strBuilder.append('}');
            return strBuilder;
        }
        public CloseTargetResult(
            @JsonProperty("success")Boolean success
        ) {
            this.success = success;
        }
        public CloseTargetResult(ResultBase.FailedResult e) {
            super(e);
            success = null;
        }
    }
    /**Inject object to the target's main frame that provides a communication
channel with browser target.

Injected object will be available as `window[bindingName]`.

The object has the follwing API:
- `binding.send(json)` - a method to send messages over the remote debugging protocol
- `binding.onmessage = json => handleMessage(json)` - a callback that will be called for the protocol notifications and command responses.
    <p><strong>Experimental.</strong></p>*/
    public ExposeDevToolsProtocolParameter exposeDevToolsProtocol() { final ExposeDevToolsProtocolParameter v = new ExposeDevToolsProtocolParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of exposeDevToolsProtocol.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ExposeDevToolsProtocolParameter extends CommandBase {
        /**&lt;No document in protocol.&gt;*/
        private TargetID targetId;
        /**Binding name, 'cdp' if not specified.
        <em>Optional.</em>*/
        private String bindingName;
        public final ExposeDevToolsProtocolParameter targetId(TargetID targetId) { this.targetId = targetId; return this; }
        public final ExposeDevToolsProtocolParameter setTargetId(TargetID targetId) { return targetId(targetId); }
        public final TargetID targetId() { return targetId; }
        public final TargetID getTargetId() { return targetId(); }
        public final ExposeDevToolsProtocolParameter bindingName(@Nullable String bindingName) { this.bindingName = bindingName; return this; }
        public final ExposeDevToolsProtocolParameter optBindingName(@Nullable String bindingName) { return bindingName(bindingName); }
        public final String bindingName() { return bindingName; }
        public final String getBindingName() { return bindingName(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (targetId == null) throw new IllegalArgumentException("Target.ExposeDevToolsProtocolParameter.targetId is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            targetId.toJson(strBuilder.append("\"targetId\":"));
            if (bindingName != null) strBuilder.append(",\"bindingName\":").append('"').append(DomainBase.escapeJson(bindingName)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public ExposeDevToolsProtocolParameter() {}
        public ExposeDevToolsProtocolParameter(
            @JsonProperty("targetId")TargetID targetId,
            @Nullable @JsonProperty("bindingName")String bindingName
        ) {
            this();
            this.targetId = targetId;
            this.bindingName = bindingName;
        }
        public CompletableFuture<ExposeDevToolsProtocolResult> call() {
            return super.call("Target.exposeDevToolsProtocol", ExposeDevToolsProtocolResult.class,
                (code, msg)->new ExposeDevToolsProtocolResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<ExposeDevToolsProtocolResult> callAsync() {
            return super.callAsync("Target.exposeDevToolsProtocol", ExposeDevToolsProtocolResult.class,
                (code, msg)->new ExposeDevToolsProtocolResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<ExposeDevToolsProtocolResult> callAsync(Executor exec) {
            return super.callAsync("Target.exposeDevToolsProtocol", ExposeDevToolsProtocolResult.class,
                (code, msg)->new ExposeDevToolsProtocolResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of exposeDevToolsProtocol.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ExposeDevToolsProtocolResult extends ResultBase {
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
        public ExposeDevToolsProtocolResult() { super(); }
        public ExposeDevToolsProtocolResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Creates a new empty BrowserContext. Similar to an incognito profile but you can have more than
one.
    <p><strong>Experimental.</strong></p>*/
    public CreateBrowserContextParameter createBrowserContext() { final CreateBrowserContextParameter v = new CreateBrowserContextParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of createBrowserContext.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class CreateBrowserContextParameter extends CommandBase {
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
        public CreateBrowserContextParameter() {}
        public CompletableFuture<CreateBrowserContextResult> call() {
            return super.call("Target.createBrowserContext", CreateBrowserContextResult.class,
                (code, msg)->new CreateBrowserContextResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<CreateBrowserContextResult> callAsync() {
            return super.callAsync("Target.createBrowserContext", CreateBrowserContextResult.class,
                (code, msg)->new CreateBrowserContextResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<CreateBrowserContextResult> callAsync(Executor exec) {
            return super.callAsync("Target.createBrowserContext", CreateBrowserContextResult.class,
                (code, msg)->new CreateBrowserContextResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of createBrowserContext.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class CreateBrowserContextResult extends ResultBase {
        /**The id of the context created.*/
        private final BrowserContextID browserContextId;
        public final BrowserContextID browserContextId() { return browserContextId; }
        public final BrowserContextID getBrowserContextId() { return browserContextId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            browserContextId.toJson(strBuilder.append("\"browserContextId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public CreateBrowserContextResult(
            @JsonProperty("browserContextId")BrowserContextID browserContextId
        ) {
            this.browserContextId = browserContextId;
        }
        public CreateBrowserContextResult(ResultBase.FailedResult e) {
            super(e);
            browserContextId = null;
        }
    }
    /**Returns all browser contexts created with `Target.createBrowserContext` method.
    <p><strong>Experimental.</strong></p>*/
    public GetBrowserContextsParameter getBrowserContexts() { final GetBrowserContextsParameter v = new GetBrowserContextsParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of getBrowserContexts.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetBrowserContextsParameter extends CommandBase {
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
        public GetBrowserContextsParameter() {}
        public CompletableFuture<GetBrowserContextsResult> call() {
            return super.call("Target.getBrowserContexts", GetBrowserContextsResult.class,
                (code, msg)->new GetBrowserContextsResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetBrowserContextsResult> callAsync() {
            return super.callAsync("Target.getBrowserContexts", GetBrowserContextsResult.class,
                (code, msg)->new GetBrowserContextsResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetBrowserContextsResult> callAsync(Executor exec) {
            return super.callAsync("Target.getBrowserContexts", GetBrowserContextsResult.class,
                (code, msg)->new GetBrowserContextsResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of getBrowserContexts.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetBrowserContextsResult extends ResultBase {
        /**An array of browser context ids.*/
        private final List<BrowserContextID> browserContextIds;
        public final List<BrowserContextID> browserContextIds() { return browserContextIds; }
        public final List<BrowserContextID> getBrowserContextIds() { return browserContextIds(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
                        strBuilder.append("\"browserContextIds\":[");
            browserContextIds.get(0).toJson(strBuilder);
            for (int i = 1; i < browserContextIds.size(); ++i)
                browserContextIds.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public GetBrowserContextsResult(
            @JsonProperty("browserContextIds")List<BrowserContextID> browserContextIds
        ) {
            this.browserContextIds = browserContextIds;
        }
        public GetBrowserContextsResult(ResultBase.FailedResult e) {
            super(e);
            browserContextIds = null;
        }
    }
    /**Creates a new page.*/
    public CreateTargetParameter createTarget() { final CreateTargetParameter v = new CreateTargetParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of createTarget.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class CreateTargetParameter extends CommandBase {
        /**The initial URL the page will be navigated to.*/
        private String url;
        /**Frame width in DIP (headless chrome only).
        <em>Optional.</em>*/
        private Integer width;
        /**Frame height in DIP (headless chrome only).
        <em>Optional.</em>*/
        private Integer height;
        /**The browser context to create the page in.
        <em>Optional.</em>*/
        private BrowserContextID browserContextId;
        /**Whether BeginFrames for this target will be controlled via DevTools (headless chrome only,
not supported on MacOS yet, false by default).
        <em>Optional.</em>
        <p><strong>Experimental.</strong></p>*/
        private Boolean enableBeginFrameControl;
        public final CreateTargetParameter url(String url) { this.url = url; return this; }
        public final CreateTargetParameter setUrl(String url) { return url(url); }
        public final String url() { return url; }
        public final String getUrl() { return url(); }
        public final CreateTargetParameter width(@Nullable Integer width) { this.width = width; return this; }
        public final CreateTargetParameter optWidth(@Nullable Integer width) { return width(width); }
        public final Integer width() { return width; }
        public final Integer getWidth() { return width(); }
        public final CreateTargetParameter height(@Nullable Integer height) { this.height = height; return this; }
        public final CreateTargetParameter optHeight(@Nullable Integer height) { return height(height); }
        public final Integer height() { return height; }
        public final Integer getHeight() { return height(); }
        public final CreateTargetParameter browserContextId(@Nullable BrowserContextID browserContextId) { this.browserContextId = browserContextId; return this; }
        public final CreateTargetParameter optBrowserContextId(@Nullable BrowserContextID browserContextId) { return browserContextId(browserContextId); }
        public final BrowserContextID browserContextId() { return browserContextId; }
        public final BrowserContextID getBrowserContextId() { return browserContextId(); }
        public final CreateTargetParameter enableBeginFrameControl(@Nullable Boolean enableBeginFrameControl) { this.enableBeginFrameControl = enableBeginFrameControl; return this; }
        public final CreateTargetParameter optEnableBeginFrameControl(@Nullable Boolean enableBeginFrameControl) { return enableBeginFrameControl(enableBeginFrameControl); }
        public final Boolean enableBeginFrameControl() { return enableBeginFrameControl; }
        public final Boolean getEnableBeginFrameControl() { return enableBeginFrameControl(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (url == null) throw new IllegalArgumentException("Target.CreateTargetParameter.url is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"url\":").append('"').append(DomainBase.escapeJson(url)).append('"');
            if (width != null) strBuilder.append(",\"width\":").append(width);
            if (height != null) strBuilder.append(",\"height\":").append(height);
            if (browserContextId != null) browserContextId.toJson(strBuilder.append(",\"browserContextId\":"));
            if (enableBeginFrameControl != null) strBuilder.append(",\"enableBeginFrameControl\":").append(enableBeginFrameControl);
            strBuilder.append('}');
            return strBuilder;
        }
        public CreateTargetParameter() {}
        public CreateTargetParameter(
            @JsonProperty("url")String url,
            @Nullable @JsonProperty("width")Integer width,
            @Nullable @JsonProperty("height")Integer height,
            @Nullable @JsonProperty("browserContextId")BrowserContextID browserContextId,
            @Nullable @JsonProperty("enableBeginFrameControl")Boolean enableBeginFrameControl
        ) {
            this();
            this.url = url;
            this.width = width;
            this.height = height;
            this.browserContextId = browserContextId;
            this.enableBeginFrameControl = enableBeginFrameControl;
        }
        public CompletableFuture<CreateTargetResult> call() {
            return super.call("Target.createTarget", CreateTargetResult.class,
                (code, msg)->new CreateTargetResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<CreateTargetResult> callAsync() {
            return super.callAsync("Target.createTarget", CreateTargetResult.class,
                (code, msg)->new CreateTargetResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<CreateTargetResult> callAsync(Executor exec) {
            return super.callAsync("Target.createTarget", CreateTargetResult.class,
                (code, msg)->new CreateTargetResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of createTarget.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class CreateTargetResult extends ResultBase {
        /**The id of the page opened.*/
        private final TargetID targetId;
        public final TargetID targetId() { return targetId; }
        public final TargetID getTargetId() { return targetId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            targetId.toJson(strBuilder.append("\"targetId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public CreateTargetResult(
            @JsonProperty("targetId")TargetID targetId
        ) {
            this.targetId = targetId;
        }
        public CreateTargetResult(ResultBase.FailedResult e) {
            super(e);
            targetId = null;
        }
    }
    /**Detaches session with given id.*/
    public DetachFromTargetParameter detachFromTarget() { final DetachFromTargetParameter v = new DetachFromTargetParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of detachFromTarget.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class DetachFromTargetParameter extends CommandBase {
        /**Session to detach.
        <em>Optional.</em>*/
        private SessionID sessionId;
        /**Deprecated.
        <em>Optional.</em>
        @Deprecated*/
        private TargetID targetId;
        public final DetachFromTargetParameter sessionId(@Nullable SessionID sessionId) { this.sessionId = sessionId; return this; }
        public final DetachFromTargetParameter optSessionId(@Nullable SessionID sessionId) { return sessionId(sessionId); }
        public final SessionID sessionId() { return sessionId; }
        public final SessionID getSessionId() { return sessionId(); }
        public final DetachFromTargetParameter targetId(@Nullable TargetID targetId) { this.targetId = targetId; return this; }
        public final DetachFromTargetParameter optTargetId(@Nullable TargetID targetId) { return targetId(targetId); }
        public final TargetID targetId() { return targetId; }
        public final TargetID getTargetId() { return targetId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            if (sessionId != null) sessionId.toJson(strBuilder.append("\"sessionId\":"));
            if (targetId != null) targetId.toJson(strBuilder.append(",\"targetId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public DetachFromTargetParameter() {}
        public DetachFromTargetParameter(
            @Nullable @JsonProperty("sessionId")SessionID sessionId,
            @Nullable @JsonProperty("targetId")TargetID targetId
        ) {
            this();
            this.sessionId = sessionId;
            this.targetId = targetId;
        }
        public CompletableFuture<DetachFromTargetResult> call() {
            return super.call("Target.detachFromTarget", DetachFromTargetResult.class,
                (code, msg)->new DetachFromTargetResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<DetachFromTargetResult> callAsync() {
            return super.callAsync("Target.detachFromTarget", DetachFromTargetResult.class,
                (code, msg)->new DetachFromTargetResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<DetachFromTargetResult> callAsync(Executor exec) {
            return super.callAsync("Target.detachFromTarget", DetachFromTargetResult.class,
                (code, msg)->new DetachFromTargetResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of detachFromTarget.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class DetachFromTargetResult extends ResultBase {
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
        public DetachFromTargetResult() { super(); }
        public DetachFromTargetResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Deletes a BrowserContext. All the belonging pages will be closed without calling their
beforeunload hooks.
    <p><strong>Experimental.</strong></p>*/
    public DisposeBrowserContextParameter disposeBrowserContext() { final DisposeBrowserContextParameter v = new DisposeBrowserContextParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of disposeBrowserContext.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class DisposeBrowserContextParameter extends CommandBase {
        /**&lt;No document in protocol.&gt;*/
        private BrowserContextID browserContextId;
        public final DisposeBrowserContextParameter browserContextId(BrowserContextID browserContextId) { this.browserContextId = browserContextId; return this; }
        public final DisposeBrowserContextParameter setBrowserContextId(BrowserContextID browserContextId) { return browserContextId(browserContextId); }
        public final BrowserContextID browserContextId() { return browserContextId; }
        public final BrowserContextID getBrowserContextId() { return browserContextId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (browserContextId == null) throw new IllegalArgumentException("Target.DisposeBrowserContextParameter.browserContextId is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            browserContextId.toJson(strBuilder.append("\"browserContextId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public DisposeBrowserContextParameter() {}
        public DisposeBrowserContextParameter(
            @JsonProperty("browserContextId")BrowserContextID browserContextId
        ) {
            this();
            this.browserContextId = browserContextId;
        }
        public CompletableFuture<DisposeBrowserContextResult> call() {
            return super.call("Target.disposeBrowserContext", DisposeBrowserContextResult.class,
                (code, msg)->new DisposeBrowserContextResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<DisposeBrowserContextResult> callAsync() {
            return super.callAsync("Target.disposeBrowserContext", DisposeBrowserContextResult.class,
                (code, msg)->new DisposeBrowserContextResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<DisposeBrowserContextResult> callAsync(Executor exec) {
            return super.callAsync("Target.disposeBrowserContext", DisposeBrowserContextResult.class,
                (code, msg)->new DisposeBrowserContextResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of disposeBrowserContext.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class DisposeBrowserContextResult extends ResultBase {
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
        public DisposeBrowserContextResult() { super(); }
        public DisposeBrowserContextResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Returns information about a target.
    <p><strong>Experimental.</strong></p>*/
    public GetTargetInfoParameter getTargetInfo() { final GetTargetInfoParameter v = new GetTargetInfoParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of getTargetInfo.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetTargetInfoParameter extends CommandBase {
        /**&lt;No document in protocol.&gt;
        <em>Optional.</em>*/
        private TargetID targetId;
        public final GetTargetInfoParameter targetId(@Nullable TargetID targetId) { this.targetId = targetId; return this; }
        public final GetTargetInfoParameter optTargetId(@Nullable TargetID targetId) { return targetId(targetId); }
        public final TargetID targetId() { return targetId; }
        public final TargetID getTargetId() { return targetId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            if (targetId != null) targetId.toJson(strBuilder.append("\"targetId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public GetTargetInfoParameter() {}
        public GetTargetInfoParameter(
            @Nullable @JsonProperty("targetId")TargetID targetId
        ) {
            this();
            this.targetId = targetId;
        }
        public CompletableFuture<GetTargetInfoResult> call() {
            return super.call("Target.getTargetInfo", GetTargetInfoResult.class,
                (code, msg)->new GetTargetInfoResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetTargetInfoResult> callAsync() {
            return super.callAsync("Target.getTargetInfo", GetTargetInfoResult.class,
                (code, msg)->new GetTargetInfoResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetTargetInfoResult> callAsync(Executor exec) {
            return super.callAsync("Target.getTargetInfo", GetTargetInfoResult.class,
                (code, msg)->new GetTargetInfoResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of getTargetInfo.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetTargetInfoResult extends ResultBase {
        /**&lt;No document in protocol.&gt;*/
        private final TargetInfo targetInfo;
        public final TargetInfo targetInfo() { return targetInfo; }
        public final TargetInfo getTargetInfo() { return targetInfo(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            targetInfo.toJson(strBuilder.append("\"targetInfo\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public GetTargetInfoResult(
            @JsonProperty("targetInfo")TargetInfo targetInfo
        ) {
            this.targetInfo = targetInfo;
        }
        public GetTargetInfoResult(ResultBase.FailedResult e) {
            super(e);
            targetInfo = null;
        }
    }
    /**Retrieves a list of available targets.*/
    public GetTargetsParameter getTargets() { final GetTargetsParameter v = new GetTargetsParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of getTargets.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetTargetsParameter extends CommandBase {
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
        public GetTargetsParameter() {}
        public CompletableFuture<GetTargetsResult> call() {
            return super.call("Target.getTargets", GetTargetsResult.class,
                (code, msg)->new GetTargetsResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetTargetsResult> callAsync() {
            return super.callAsync("Target.getTargets", GetTargetsResult.class,
                (code, msg)->new GetTargetsResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetTargetsResult> callAsync(Executor exec) {
            return super.callAsync("Target.getTargets", GetTargetsResult.class,
                (code, msg)->new GetTargetsResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of getTargets.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetTargetsResult extends ResultBase {
        /**The list of targets.*/
        private final List<TargetInfo> targetInfos;
        public final List<TargetInfo> targetInfos() { return targetInfos; }
        public final List<TargetInfo> getTargetInfos() { return targetInfos(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
                        strBuilder.append("\"targetInfos\":[");
            targetInfos.get(0).toJson(strBuilder);
            for (int i = 1; i < targetInfos.size(); ++i)
                targetInfos.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public GetTargetsResult(
            @JsonProperty("targetInfos")List<TargetInfo> targetInfos
        ) {
            this.targetInfos = targetInfos;
        }
        public GetTargetsResult(ResultBase.FailedResult e) {
            super(e);
            targetInfos = null;
        }
    }
    /**Sends protocol message over session with given id.*/
    public SendMessageToTargetParameter sendMessageToTarget() { final SendMessageToTargetParameter v = new SendMessageToTargetParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of sendMessageToTarget.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SendMessageToTargetParameter extends CommandBase {
        /**&lt;No document in protocol.&gt;*/
        private String message;
        /**Identifier of the session.
        <em>Optional.</em>*/
        private SessionID sessionId;
        /**Deprecated.
        <em>Optional.</em>
        @Deprecated*/
        private TargetID targetId;
        public final SendMessageToTargetParameter message(String message) { this.message = message; return this; }
        public final SendMessageToTargetParameter setMessage(String message) { return message(message); }
        public final String message() { return message; }
        public final String getMessage() { return message(); }
        public final SendMessageToTargetParameter sessionId(@Nullable SessionID sessionId) { this.sessionId = sessionId; return this; }
        public final SendMessageToTargetParameter optSessionId(@Nullable SessionID sessionId) { return sessionId(sessionId); }
        public final SessionID sessionId() { return sessionId; }
        public final SessionID getSessionId() { return sessionId(); }
        public final SendMessageToTargetParameter targetId(@Nullable TargetID targetId) { this.targetId = targetId; return this; }
        public final SendMessageToTargetParameter optTargetId(@Nullable TargetID targetId) { return targetId(targetId); }
        public final TargetID targetId() { return targetId; }
        public final TargetID getTargetId() { return targetId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (message == null) throw new IllegalArgumentException("Target.SendMessageToTargetParameter.message is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"message\":").append('"').append(DomainBase.escapeJson(message)).append('"');
            if (sessionId != null) sessionId.toJson(strBuilder.append(",\"sessionId\":"));
            if (targetId != null) targetId.toJson(strBuilder.append(",\"targetId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public SendMessageToTargetParameter() {}
        public SendMessageToTargetParameter(
            @JsonProperty("message")String message,
            @Nullable @JsonProperty("sessionId")SessionID sessionId,
            @Nullable @JsonProperty("targetId")TargetID targetId
        ) {
            this();
            this.message = message;
            this.sessionId = sessionId;
            this.targetId = targetId;
        }
        public CompletableFuture<SendMessageToTargetResult> call() {
            return super.call("Target.sendMessageToTarget", SendMessageToTargetResult.class,
                (code, msg)->new SendMessageToTargetResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SendMessageToTargetResult> callAsync() {
            return super.callAsync("Target.sendMessageToTarget", SendMessageToTargetResult.class,
                (code, msg)->new SendMessageToTargetResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SendMessageToTargetResult> callAsync(Executor exec) {
            return super.callAsync("Target.sendMessageToTarget", SendMessageToTargetResult.class,
                (code, msg)->new SendMessageToTargetResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of sendMessageToTarget.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SendMessageToTargetResult extends ResultBase {
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
        public SendMessageToTargetResult() { super(); }
        public SendMessageToTargetResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Controls whether to automatically attach to new targets which are considered to be related to
this one. When turned on, attaches to all existing related targets as well. When turned off,
automatically detaches from all currently attached targets.
    <p><strong>Experimental.</strong></p>*/
    public SetAutoAttachParameter setAutoAttach() { final SetAutoAttachParameter v = new SetAutoAttachParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setAutoAttach.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetAutoAttachParameter extends CommandBase {
        /**Whether to auto-attach to related targets.*/
        private Boolean autoAttach;
        /**Whether to pause new targets when attaching to them. Use `Runtime.runIfWaitingForDebugger`
to run paused targets.*/
        private Boolean waitForDebuggerOnStart;
        /**Enables "flat" access to the session via specifying sessionId attribute in the commands.
        <em>Optional.</em>
        <p><strong>Experimental.</strong></p>*/
        private Boolean flatten;
        public final SetAutoAttachParameter autoAttach(Boolean autoAttach) { this.autoAttach = autoAttach; return this; }
        public final SetAutoAttachParameter setAutoAttach(Boolean autoAttach) { return autoAttach(autoAttach); }
        public final Boolean autoAttach() { return autoAttach; }
        public final Boolean getAutoAttach() { return autoAttach(); }
        public final SetAutoAttachParameter waitForDebuggerOnStart(Boolean waitForDebuggerOnStart) { this.waitForDebuggerOnStart = waitForDebuggerOnStart; return this; }
        public final SetAutoAttachParameter setWaitForDebuggerOnStart(Boolean waitForDebuggerOnStart) { return waitForDebuggerOnStart(waitForDebuggerOnStart); }
        public final Boolean waitForDebuggerOnStart() { return waitForDebuggerOnStart; }
        public final Boolean getWaitForDebuggerOnStart() { return waitForDebuggerOnStart(); }
        public final SetAutoAttachParameter flatten(@Nullable Boolean flatten) { this.flatten = flatten; return this; }
        public final SetAutoAttachParameter optFlatten(@Nullable Boolean flatten) { return flatten(flatten); }
        public final Boolean flatten() { return flatten; }
        public final Boolean getFlatten() { return flatten(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (autoAttach == null) throw new IllegalArgumentException("Target.SetAutoAttachParameter.autoAttach is necessary field.");
            if (waitForDebuggerOnStart == null) throw new IllegalArgumentException("Target.SetAutoAttachParameter.waitForDebuggerOnStart is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"autoAttach\":").append(autoAttach);
            strBuilder.append(",\"waitForDebuggerOnStart\":").append(waitForDebuggerOnStart);
            if (flatten != null) strBuilder.append(",\"flatten\":").append(flatten);
            strBuilder.append('}');
            return strBuilder;
        }
        public SetAutoAttachParameter() {}
        public SetAutoAttachParameter(
            @JsonProperty("autoAttach")Boolean autoAttach,
            @JsonProperty("waitForDebuggerOnStart")Boolean waitForDebuggerOnStart,
            @Nullable @JsonProperty("flatten")Boolean flatten
        ) {
            this();
            this.autoAttach = autoAttach;
            this.waitForDebuggerOnStart = waitForDebuggerOnStart;
            this.flatten = flatten;
        }
        public CompletableFuture<SetAutoAttachResult> call() {
            return super.call("Target.setAutoAttach", SetAutoAttachResult.class,
                (code, msg)->new SetAutoAttachResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetAutoAttachResult> callAsync() {
            return super.callAsync("Target.setAutoAttach", SetAutoAttachResult.class,
                (code, msg)->new SetAutoAttachResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetAutoAttachResult> callAsync(Executor exec) {
            return super.callAsync("Target.setAutoAttach", SetAutoAttachResult.class,
                (code, msg)->new SetAutoAttachResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setAutoAttach.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetAutoAttachResult extends ResultBase {
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
        public SetAutoAttachResult() { super(); }
        public SetAutoAttachResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Controls whether to discover available targets and notify via
`targetCreated/targetInfoChanged/targetDestroyed` events.*/
    public SetDiscoverTargetsParameter setDiscoverTargets() { final SetDiscoverTargetsParameter v = new SetDiscoverTargetsParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setDiscoverTargets.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetDiscoverTargetsParameter extends CommandBase {
        /**Whether to discover available targets.*/
        private Boolean discover;
        public final SetDiscoverTargetsParameter discover(Boolean discover) { this.discover = discover; return this; }
        public final SetDiscoverTargetsParameter setDiscover(Boolean discover) { return discover(discover); }
        public final Boolean discover() { return discover; }
        public final Boolean getDiscover() { return discover(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (discover == null) throw new IllegalArgumentException("Target.SetDiscoverTargetsParameter.discover is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"discover\":").append(discover);
            strBuilder.append('}');
            return strBuilder;
        }
        public SetDiscoverTargetsParameter() {}
        public SetDiscoverTargetsParameter(
            @JsonProperty("discover")Boolean discover
        ) {
            this();
            this.discover = discover;
        }
        public CompletableFuture<SetDiscoverTargetsResult> call() {
            return super.call("Target.setDiscoverTargets", SetDiscoverTargetsResult.class,
                (code, msg)->new SetDiscoverTargetsResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetDiscoverTargetsResult> callAsync() {
            return super.callAsync("Target.setDiscoverTargets", SetDiscoverTargetsResult.class,
                (code, msg)->new SetDiscoverTargetsResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetDiscoverTargetsResult> callAsync(Executor exec) {
            return super.callAsync("Target.setDiscoverTargets", SetDiscoverTargetsResult.class,
                (code, msg)->new SetDiscoverTargetsResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setDiscoverTargets.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetDiscoverTargetsResult extends ResultBase {
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
        public SetDiscoverTargetsResult() { super(); }
        public SetDiscoverTargetsResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Enables target discovery for the specified locations, when `setDiscoverTargets` was set to
`true`.
    <p><strong>Experimental.</strong></p>*/
    public SetRemoteLocationsParameter setRemoteLocations() { final SetRemoteLocationsParameter v = new SetRemoteLocationsParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setRemoteLocations.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetRemoteLocationsParameter extends CommandBase {
        /**List of remote locations.*/
        private List<RemoteLocation> locations;
        public final SetRemoteLocationsParameter locations(List<RemoteLocation> locations) { this.locations = locations; return this; }
        public final SetRemoteLocationsParameter setLocations(List<RemoteLocation> locations) { return locations(locations); }
        public final List<RemoteLocation> locations() { return locations; }
        public final List<RemoteLocation> getLocations() { return locations(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (locations == null) throw new IllegalArgumentException("Target.SetRemoteLocationsParameter.locations is necessary field.");
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
        public SetRemoteLocationsParameter() {}
        public SetRemoteLocationsParameter(
            @JsonProperty("locations")List<RemoteLocation> locations
        ) {
            this();
            this.locations = locations;
        }
        public CompletableFuture<SetRemoteLocationsResult> call() {
            return super.call("Target.setRemoteLocations", SetRemoteLocationsResult.class,
                (code, msg)->new SetRemoteLocationsResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetRemoteLocationsResult> callAsync() {
            return super.callAsync("Target.setRemoteLocations", SetRemoteLocationsResult.class,
                (code, msg)->new SetRemoteLocationsResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetRemoteLocationsResult> callAsync(Executor exec) {
            return super.callAsync("Target.setRemoteLocations", SetRemoteLocationsResult.class,
                (code, msg)->new SetRemoteLocationsResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setRemoteLocations.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetRemoteLocationsResult extends ResultBase {
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
        public SetRemoteLocationsResult() { super(); }
        public SetRemoteLocationsResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Event parameter of Target.attachedToTarget.
    <p><strong>Experimental.</strong></p>
     @see #onAttachedToTarget*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class AttachedToTargetEventParameter implements CommonDomainType {
        /**Identifier assigned to the session used to send/receive messages.*/
        private final SessionID sessionId;
        /**&lt;No document in protocol.&gt;*/
        private final TargetInfo targetInfo;
        /**&lt;No document in protocol.&gt;*/
        private final Boolean waitingForDebugger;
        public final SessionID sessionId() { return sessionId; }
        public final SessionID getSessionId() { return sessionId(); }
        public final TargetInfo targetInfo() { return targetInfo; }
        public final TargetInfo getTargetInfo() { return targetInfo(); }
        public final Boolean waitingForDebugger() { return waitingForDebugger; }
        public final Boolean getWaitingForDebugger() { return waitingForDebugger(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            sessionId.toJson(strBuilder.append("\"sessionId\":"));
            targetInfo.toJson(strBuilder.append(",\"targetInfo\":"));
            strBuilder.append(",\"waitingForDebugger\":").append(waitingForDebugger);
            strBuilder.append('}');
            return strBuilder;
        }
        AttachedToTargetEventParameter(
            @JsonProperty("sessionId")SessionID sessionId,
            @JsonProperty("targetInfo")TargetInfo targetInfo,
            @JsonProperty("waitingForDebugger")Boolean waitingForDebugger
        ) {
            this.sessionId = sessionId;
            this.targetInfo = targetInfo;
            this.waitingForDebugger = waitingForDebugger;
        }
    }
    /**Issued when attached to target because of auto-attach or `attachToTarget` command.
    <p><strong>Experimental.</strong></p>
     @see AttachedToTargetEventParameter*/
    public void onAttachedToTarget(@Nullable Consumer<AttachedToTargetEventParameter> callback) {
        if (callback != null)
            registerEventCallback("Target.attachedToTarget", node -> {
                AttachedToTargetEventParameter param;
                try { param = EventCenter.deserializeJson(node, AttachedToTargetEventParameter.class); }
                catch (IOException e) { _evt.getLog().error(e); return; }
                callback.accept(param);
            });
        else    registerEventCallback("Target.attachedToTarget", null);
    }
    /**Event parameter of Target.detachedFromTarget.
    <p><strong>Experimental.</strong></p>
     @see #onDetachedFromTarget*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class DetachedFromTargetEventParameter implements CommonDomainType {
        /**Detached session identifier.*/
        private final SessionID sessionId;
        /**Deprecated.
        <em>Optional.</em>
        @Deprecated*/
        private final TargetID targetId;
        public final SessionID sessionId() { return sessionId; }
        public final SessionID getSessionId() { return sessionId(); }
        public final TargetID targetId() { return targetId; }
        public final TargetID getTargetId() { return targetId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            sessionId.toJson(strBuilder.append("\"sessionId\":"));
            if (targetId != null) targetId.toJson(strBuilder.append(",\"targetId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        DetachedFromTargetEventParameter(
            @JsonProperty("sessionId")SessionID sessionId,
            @Nullable @JsonProperty("targetId")TargetID targetId
        ) {
            this.sessionId = sessionId;
            this.targetId = targetId;
        }
    }
    /**Issued when detached from target for any reason (including `detachFromTarget` command). Can be
issued multiple times per target if multiple sessions have been attached to it.
    <p><strong>Experimental.</strong></p>
     @see DetachedFromTargetEventParameter*/
    public void onDetachedFromTarget(@Nullable Consumer<DetachedFromTargetEventParameter> callback) {
        if (callback != null)
            registerEventCallback("Target.detachedFromTarget", node -> {
                DetachedFromTargetEventParameter param;
                try { param = EventCenter.deserializeJson(node, DetachedFromTargetEventParameter.class); }
                catch (IOException e) { _evt.getLog().error(e); return; }
                callback.accept(param);
            });
        else    registerEventCallback("Target.detachedFromTarget", null);
    }
    /**Event parameter of Target.receivedMessageFromTarget.
     @see #onReceivedMessageFromTarget*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ReceivedMessageFromTargetEventParameter implements CommonDomainType {
        /**Identifier of a session which sends a message.*/
        private final SessionID sessionId;
        /**&lt;No document in protocol.&gt;*/
        private final String message;
        /**Deprecated.
        <em>Optional.</em>
        @Deprecated*/
        private final TargetID targetId;
        public final SessionID sessionId() { return sessionId; }
        public final SessionID getSessionId() { return sessionId(); }
        public final String message() { return message; }
        public final String getMessage() { return message(); }
        public final TargetID targetId() { return targetId; }
        public final TargetID getTargetId() { return targetId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            sessionId.toJson(strBuilder.append("\"sessionId\":"));
            strBuilder.append(",\"message\":").append('"').append(DomainBase.escapeJson(message)).append('"');
            if (targetId != null) targetId.toJson(strBuilder.append(",\"targetId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        ReceivedMessageFromTargetEventParameter(
            @JsonProperty("sessionId")SessionID sessionId,
            @JsonProperty("message")String message,
            @Nullable @JsonProperty("targetId")TargetID targetId
        ) {
            this.sessionId = sessionId;
            this.message = message;
            this.targetId = targetId;
        }
    }
    /**Notifies about a new protocol message received from the session (as reported in
`attachedToTarget` event).
     @see ReceivedMessageFromTargetEventParameter*/
    public void onReceivedMessageFromTarget(@Nullable Consumer<ReceivedMessageFromTargetEventParameter> callback) {
        if (callback != null)
            registerEventCallback("Target.receivedMessageFromTarget", node -> {
                ReceivedMessageFromTargetEventParameter param;
                try { param = EventCenter.deserializeJson(node, ReceivedMessageFromTargetEventParameter.class); }
                catch (IOException e) { _evt.getLog().error(e); return; }
                callback.accept(param);
            });
        else    registerEventCallback("Target.receivedMessageFromTarget", null);
    }
    /**Event parameter of Target.targetCreated.
     @see #onTargetCreated*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class TargetCreatedEventParameter implements CommonDomainType {
        /**&lt;No document in protocol.&gt;*/
        private final TargetInfo targetInfo;
        public final TargetInfo targetInfo() { return targetInfo; }
        public final TargetInfo getTargetInfo() { return targetInfo(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            targetInfo.toJson(strBuilder.append("\"targetInfo\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        TargetCreatedEventParameter(
            @JsonProperty("targetInfo")TargetInfo targetInfo
        ) {
            this.targetInfo = targetInfo;
        }
    }
    /**Issued when a possible inspection target is created.
     @see TargetCreatedEventParameter*/
    public void onTargetCreated(@Nullable Consumer<TargetCreatedEventParameter> callback) {
        if (callback != null)
            registerEventCallback("Target.targetCreated", node -> {
                TargetCreatedEventParameter param;
                try { param = EventCenter.deserializeJson(node, TargetCreatedEventParameter.class); }
                catch (IOException e) { _evt.getLog().error(e); return; }
                callback.accept(param);
            });
        else    registerEventCallback("Target.targetCreated", null);
    }
    /**Event parameter of Target.targetDestroyed.
     @see #onTargetDestroyed*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class TargetDestroyedEventParameter implements CommonDomainType {
        /**&lt;No document in protocol.&gt;*/
        private final TargetID targetId;
        public final TargetID targetId() { return targetId; }
        public final TargetID getTargetId() { return targetId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            targetId.toJson(strBuilder.append("\"targetId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        TargetDestroyedEventParameter(
            @JsonProperty("targetId")TargetID targetId
        ) {
            this.targetId = targetId;
        }
    }
    /**Issued when a target is destroyed.
     @see TargetDestroyedEventParameter*/
    public void onTargetDestroyed(@Nullable Consumer<TargetDestroyedEventParameter> callback) {
        if (callback != null)
            registerEventCallback("Target.targetDestroyed", node -> {
                TargetDestroyedEventParameter param;
                try { param = EventCenter.deserializeJson(node, TargetDestroyedEventParameter.class); }
                catch (IOException e) { _evt.getLog().error(e); return; }
                callback.accept(param);
            });
        else    registerEventCallback("Target.targetDestroyed", null);
    }
    /**Event parameter of Target.targetCrashed.
     @see #onTargetCrashed*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class TargetCrashedEventParameter implements CommonDomainType {
        /**&lt;No document in protocol.&gt;*/
        private final TargetID targetId;
        /**Termination status type.*/
        private final String status;
        /**Termination error code.*/
        private final Integer errorCode;
        public final TargetID targetId() { return targetId; }
        public final TargetID getTargetId() { return targetId(); }
        public final String status() { return status; }
        public final String getStatus() { return status(); }
        public final Integer errorCode() { return errorCode; }
        public final Integer getErrorCode() { return errorCode(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            targetId.toJson(strBuilder.append("\"targetId\":"));
            strBuilder.append(",\"status\":").append('"').append(DomainBase.escapeJson(status)).append('"');
            strBuilder.append(",\"errorCode\":").append(errorCode);
            strBuilder.append('}');
            return strBuilder;
        }
        TargetCrashedEventParameter(
            @JsonProperty("targetId")TargetID targetId,
            @JsonProperty("status")String status,
            @JsonProperty("errorCode")Integer errorCode
        ) {
            this.targetId = targetId;
            this.status = status;
            this.errorCode = errorCode;
        }
    }
    /**Issued when a target has crashed.
     @see TargetCrashedEventParameter*/
    public void onTargetCrashed(@Nullable Consumer<TargetCrashedEventParameter> callback) {
        if (callback != null)
            registerEventCallback("Target.targetCrashed", node -> {
                TargetCrashedEventParameter param;
                try { param = EventCenter.deserializeJson(node, TargetCrashedEventParameter.class); }
                catch (IOException e) { _evt.getLog().error(e); return; }
                callback.accept(param);
            });
        else    registerEventCallback("Target.targetCrashed", null);
    }
    /**Event parameter of Target.targetInfoChanged.
     @see #onTargetInfoChanged*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class TargetInfoChangedEventParameter implements CommonDomainType {
        /**&lt;No document in protocol.&gt;*/
        private final TargetInfo targetInfo;
        public final TargetInfo targetInfo() { return targetInfo; }
        public final TargetInfo getTargetInfo() { return targetInfo(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            targetInfo.toJson(strBuilder.append("\"targetInfo\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        TargetInfoChangedEventParameter(
            @JsonProperty("targetInfo")TargetInfo targetInfo
        ) {
            this.targetInfo = targetInfo;
        }
    }
    /**Issued when some information about a target has changed. This only happens between
`targetCreated` and `targetDestroyed`.
     @see TargetInfoChangedEventParameter*/
    public void onTargetInfoChanged(@Nullable Consumer<TargetInfoChangedEventParameter> callback) {
        if (callback != null)
            registerEventCallback("Target.targetInfoChanged", node -> {
                TargetInfoChangedEventParameter param;
                try { param = EventCenter.deserializeJson(node, TargetInfoChangedEventParameter.class); }
                catch (IOException e) { _evt.getLog().error(e); return; }
                callback.accept(param);
            });
        else    registerEventCallback("Target.targetInfoChanged", null);
    }
}

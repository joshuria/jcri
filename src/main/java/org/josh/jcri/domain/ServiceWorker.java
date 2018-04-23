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
<p>From: browser_protocol.json</p>
<p>Protocol version: 1.3</p>
<p><strong>Experimental.</strong></p>
 @author Joshua*/
@ParametersAreNonnullByDefault public class ServiceWorker extends DomainBase {
    public ServiceWorker(EventCenter evt, WebSocket ws) { super(evt, ws); }

    /**ServiceWorker registration.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ServiceWorkerRegistration implements CommonDomainType {
        /**&lt;No document in protocol.&gt;*/
        private String registrationId;
        /**&lt;No document in protocol.&gt;*/
        private String scopeURL;
        /**&lt;No document in protocol.&gt;*/
        private Boolean isDeleted;
        public final ServiceWorkerRegistration registrationId(String registrationId) { this.registrationId = registrationId; return this; }
        public final ServiceWorkerRegistration setRegistrationId(String registrationId) { return registrationId(registrationId); }
        public final String registrationId() { return registrationId; }
        public final String getRegistrationId() { return registrationId(); }
        public final ServiceWorkerRegistration scopeURL(String scopeURL) { this.scopeURL = scopeURL; return this; }
        public final ServiceWorkerRegistration setScopeURL(String scopeURL) { return scopeURL(scopeURL); }
        public final String scopeURL() { return scopeURL; }
        public final String getScopeURL() { return scopeURL(); }
        public final ServiceWorkerRegistration isDeleted(Boolean isDeleted) { this.isDeleted = isDeleted; return this; }
        public final ServiceWorkerRegistration setIsDeleted(Boolean isDeleted) { return isDeleted(isDeleted); }
        public final Boolean isDeleted() { return isDeleted; }
        public final Boolean getIsDeleted() { return isDeleted(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (registrationId == null) throw new IllegalArgumentException("ServiceWorker.ServiceWorkerRegistration.registrationId is necessary field.");
            if (scopeURL == null) throw new IllegalArgumentException("ServiceWorker.ServiceWorkerRegistration.scopeURL is necessary field.");
            if (isDeleted == null) throw new IllegalArgumentException("ServiceWorker.ServiceWorkerRegistration.isDeleted is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"registrationId\":").append('"').append(DomainBase.escapeQuote(registrationId)).append('"');
            strBuilder.append(",\"scopeURL\":").append('"').append(DomainBase.escapeQuote(scopeURL)).append('"');
            strBuilder.append(",\"isDeleted\":").append(isDeleted);
            strBuilder.append('}');
            return strBuilder;
        }
        public ServiceWorkerRegistration() {}
        public ServiceWorkerRegistration(
            @JsonProperty("registrationId")String registrationId,
            @JsonProperty("scopeURL")String scopeURL,
            @JsonProperty("isDeleted")Boolean isDeleted
        ) {
            this.registrationId = registrationId;
            this.scopeURL = scopeURL;
            this.isDeleted = isDeleted;
        }
    }

    /**&lt;No document in protocol.&gt;*/
    @ParametersAreNonnullByDefault public enum ServiceWorkerVersionRunningStatus implements CommonDomainType {
        Stopped("stopped"),
        Starting("starting"),
        Running("running"),
        Stopping("stopping");

        private final String _value;
        private static final Map<String, ServiceWorkerVersionRunningStatus> _Lookup;
        static {
            Map<String, ServiceWorkerVersionRunningStatus> m = new HashMap<>();
            for(ServiceWorkerVersionRunningStatus v: values()) m.put(v.toString(), v);
            _Lookup = Collections.unmodifiableMap(m);
        }
        /**Convert string representation to type.
         @throws IllegalArgumentException if given value cannot convert to enum type. */
        @JsonCreator public static ServiceWorkerVersionRunningStatus of(String value) {
            ServiceWorkerVersionRunningStatus v = _Lookup.get(value.toLowerCase());
            return v != null ? v : Enum.valueOf(ServiceWorkerVersionRunningStatus.class, value);
        }
        ServiceWorkerVersionRunningStatus(String value) { _value = value; }
        /**Check if parameter fields of method are all valid. */
        @Override public void check() throws IllegalArgumentException { /* Need not check */ }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) { return strBuilder.append('"').append(_value).append('"'); }
        @Override public String toString() { return "\"" + _value + "\""; }
    }

    /**&lt;No document in protocol.&gt;*/
    @ParametersAreNonnullByDefault public enum ServiceWorkerVersionStatus implements CommonDomainType {
        New("new"),
        Installing("installing"),
        Installed("installed"),
        Activating("activating"),
        Activated("activated"),
        Redundant("redundant");

        private final String _value;
        private static final Map<String, ServiceWorkerVersionStatus> _Lookup;
        static {
            Map<String, ServiceWorkerVersionStatus> m = new HashMap<>();
            for(ServiceWorkerVersionStatus v: values()) m.put(v.toString(), v);
            _Lookup = Collections.unmodifiableMap(m);
        }
        /**Convert string representation to type.
         @throws IllegalArgumentException if given value cannot convert to enum type. */
        @JsonCreator public static ServiceWorkerVersionStatus of(String value) {
            ServiceWorkerVersionStatus v = _Lookup.get(value.toLowerCase());
            return v != null ? v : Enum.valueOf(ServiceWorkerVersionStatus.class, value);
        }
        ServiceWorkerVersionStatus(String value) { _value = value; }
        /**Check if parameter fields of method are all valid. */
        @Override public void check() throws IllegalArgumentException { /* Need not check */ }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) { return strBuilder.append('"').append(_value).append('"'); }
        @Override public String toString() { return "\"" + _value + "\""; }
    }

    /**ServiceWorker version.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ServiceWorkerVersion implements CommonDomainType {
        /**&lt;No document in protocol.&gt;*/
        private String versionId;
        /**&lt;No document in protocol.&gt;*/
        private String registrationId;
        /**&lt;No document in protocol.&gt;*/
        private String scriptURL;
        /**&lt;No document in protocol.&gt;*/
        private ServiceWorkerVersionRunningStatus runningStatus;
        /**&lt;No document in protocol.&gt;*/
        private ServiceWorkerVersionStatus status;
        /**The Last-Modified header value of the main script.
        <em>Optional.</em>*/
        private Double scriptLastModified;
        /**The time at which the response headers of the main script were received from the server.
For cached script it is the last time the cache entry was validated.
        <em>Optional.</em>*/
        private Double scriptResponseTime;
        /**&lt;No document in protocol.&gt;
        <em>Optional.</em>*/
        private List<Target.TargetID> controlledClients;
        /**&lt;No document in protocol.&gt;
        <em>Optional.</em>*/
        private Target.TargetID targetId;
        public final ServiceWorkerVersion versionId(String versionId) { this.versionId = versionId; return this; }
        public final ServiceWorkerVersion setVersionId(String versionId) { return versionId(versionId); }
        public final String versionId() { return versionId; }
        public final String getVersionId() { return versionId(); }
        public final ServiceWorkerVersion registrationId(String registrationId) { this.registrationId = registrationId; return this; }
        public final ServiceWorkerVersion setRegistrationId(String registrationId) { return registrationId(registrationId); }
        public final String registrationId() { return registrationId; }
        public final String getRegistrationId() { return registrationId(); }
        public final ServiceWorkerVersion scriptURL(String scriptURL) { this.scriptURL = scriptURL; return this; }
        public final ServiceWorkerVersion setScriptURL(String scriptURL) { return scriptURL(scriptURL); }
        public final String scriptURL() { return scriptURL; }
        public final String getScriptURL() { return scriptURL(); }
        public final ServiceWorkerVersion runningStatus(ServiceWorkerVersionRunningStatus runningStatus) { this.runningStatus = runningStatus; return this; }
        public final ServiceWorkerVersion setRunningStatus(ServiceWorkerVersionRunningStatus runningStatus) { return runningStatus(runningStatus); }
        public final ServiceWorkerVersionRunningStatus runningStatus() { return runningStatus; }
        public final ServiceWorkerVersionRunningStatus getRunningStatus() { return runningStatus(); }
        public final ServiceWorkerVersion status(ServiceWorkerVersionStatus status) { this.status = status; return this; }
        public final ServiceWorkerVersion setStatus(ServiceWorkerVersionStatus status) { return status(status); }
        public final ServiceWorkerVersionStatus status() { return status; }
        public final ServiceWorkerVersionStatus getStatus() { return status(); }
        public final ServiceWorkerVersion scriptLastModified(@Nullable Double scriptLastModified) { this.scriptLastModified = scriptLastModified; return this; }
        public final ServiceWorkerVersion optScriptLastModified(@Nullable Double scriptLastModified) { return scriptLastModified(scriptLastModified); }
        public final Double scriptLastModified() { return scriptLastModified; }
        public final Double getScriptLastModified() { return scriptLastModified(); }
        public final ServiceWorkerVersion scriptResponseTime(@Nullable Double scriptResponseTime) { this.scriptResponseTime = scriptResponseTime; return this; }
        public final ServiceWorkerVersion optScriptResponseTime(@Nullable Double scriptResponseTime) { return scriptResponseTime(scriptResponseTime); }
        public final Double scriptResponseTime() { return scriptResponseTime; }
        public final Double getScriptResponseTime() { return scriptResponseTime(); }
        public final ServiceWorkerVersion controlledClients(@Nullable List<Target.TargetID> controlledClients) { this.controlledClients = controlledClients; return this; }
        public final ServiceWorkerVersion optControlledClients(@Nullable List<Target.TargetID> controlledClients) { return controlledClients(controlledClients); }
        public final List<Target.TargetID> controlledClients() { return controlledClients; }
        public final List<Target.TargetID> getControlledClients() { return controlledClients(); }
        public final ServiceWorkerVersion targetId(@Nullable Target.TargetID targetId) { this.targetId = targetId; return this; }
        public final ServiceWorkerVersion optTargetId(@Nullable Target.TargetID targetId) { return targetId(targetId); }
        public final Target.TargetID targetId() { return targetId; }
        public final Target.TargetID getTargetId() { return targetId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (versionId == null) throw new IllegalArgumentException("ServiceWorker.ServiceWorkerVersion.versionId is necessary field.");
            if (registrationId == null) throw new IllegalArgumentException("ServiceWorker.ServiceWorkerVersion.registrationId is necessary field.");
            if (scriptURL == null) throw new IllegalArgumentException("ServiceWorker.ServiceWorkerVersion.scriptURL is necessary field.");
            if (runningStatus == null) throw new IllegalArgumentException("ServiceWorker.ServiceWorkerVersion.runningStatus is necessary field.");
            if (status == null) throw new IllegalArgumentException("ServiceWorker.ServiceWorkerVersion.status is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"versionId\":").append('"').append(DomainBase.escapeQuote(versionId)).append('"');
            strBuilder.append(",\"registrationId\":").append('"').append(DomainBase.escapeQuote(registrationId)).append('"');
            strBuilder.append(",\"scriptURL\":").append('"').append(DomainBase.escapeQuote(scriptURL)).append('"');
            runningStatus.toJson(strBuilder.append(",\"runningStatus\":"));
            status.toJson(strBuilder.append(",\"status\":"));
            if (scriptLastModified != null) strBuilder.append(",\"scriptLastModified\":").append(scriptLastModified);
            if (scriptResponseTime != null) strBuilder.append(",\"scriptResponseTime\":").append(scriptResponseTime);
            if (controlledClients != null) {
                strBuilder.append(",\"controlledClients\":[");
                controlledClients.get(0).toJson(strBuilder);
                for (int i = 1; i < controlledClients.size(); ++i)
                    controlledClients.get(i).toJson(strBuilder.append(','));
                strBuilder.append(']');
            }
            if (targetId != null) targetId.toJson(strBuilder.append(",\"targetId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public ServiceWorkerVersion() {}
        public ServiceWorkerVersion(
            @JsonProperty("versionId")String versionId,
            @JsonProperty("registrationId")String registrationId,
            @JsonProperty("scriptURL")String scriptURL,
            @JsonProperty("runningStatus")ServiceWorkerVersionRunningStatus runningStatus,
            @JsonProperty("status")ServiceWorkerVersionStatus status,
            @Nullable @JsonProperty("scriptLastModified")Double scriptLastModified,
            @Nullable @JsonProperty("scriptResponseTime")Double scriptResponseTime,
            @Nullable @JsonProperty("controlledClients")List<Target.TargetID> controlledClients,
            @Nullable @JsonProperty("targetId")Target.TargetID targetId
        ) {
            this.versionId = versionId;
            this.registrationId = registrationId;
            this.scriptURL = scriptURL;
            this.runningStatus = runningStatus;
            this.status = status;
            this.scriptLastModified = scriptLastModified;
            this.scriptResponseTime = scriptResponseTime;
            this.controlledClients = controlledClients;
            this.targetId = targetId;
        }
    }

    /**ServiceWorker error message.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ServiceWorkerErrorMessage implements CommonDomainType {
        /**&lt;No document in protocol.&gt;*/
        private String errorMessage;
        /**&lt;No document in protocol.&gt;*/
        private String registrationId;
        /**&lt;No document in protocol.&gt;*/
        private String versionId;
        /**&lt;No document in protocol.&gt;*/
        private String sourceURL;
        /**&lt;No document in protocol.&gt;*/
        private Integer lineNumber;
        /**&lt;No document in protocol.&gt;*/
        private Integer columnNumber;
        public final ServiceWorkerErrorMessage errorMessage(String errorMessage) { this.errorMessage = errorMessage; return this; }
        public final ServiceWorkerErrorMessage setErrorMessage(String errorMessage) { return errorMessage(errorMessage); }
        public final String errorMessage() { return errorMessage; }
        public final String getErrorMessage() { return errorMessage(); }
        public final ServiceWorkerErrorMessage registrationId(String registrationId) { this.registrationId = registrationId; return this; }
        public final ServiceWorkerErrorMessage setRegistrationId(String registrationId) { return registrationId(registrationId); }
        public final String registrationId() { return registrationId; }
        public final String getRegistrationId() { return registrationId(); }
        public final ServiceWorkerErrorMessage versionId(String versionId) { this.versionId = versionId; return this; }
        public final ServiceWorkerErrorMessage setVersionId(String versionId) { return versionId(versionId); }
        public final String versionId() { return versionId; }
        public final String getVersionId() { return versionId(); }
        public final ServiceWorkerErrorMessage sourceURL(String sourceURL) { this.sourceURL = sourceURL; return this; }
        public final ServiceWorkerErrorMessage setSourceURL(String sourceURL) { return sourceURL(sourceURL); }
        public final String sourceURL() { return sourceURL; }
        public final String getSourceURL() { return sourceURL(); }
        public final ServiceWorkerErrorMessage lineNumber(Integer lineNumber) { this.lineNumber = lineNumber; return this; }
        public final ServiceWorkerErrorMessage setLineNumber(Integer lineNumber) { return lineNumber(lineNumber); }
        public final Integer lineNumber() { return lineNumber; }
        public final Integer getLineNumber() { return lineNumber(); }
        public final ServiceWorkerErrorMessage columnNumber(Integer columnNumber) { this.columnNumber = columnNumber; return this; }
        public final ServiceWorkerErrorMessage setColumnNumber(Integer columnNumber) { return columnNumber(columnNumber); }
        public final Integer columnNumber() { return columnNumber; }
        public final Integer getColumnNumber() { return columnNumber(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (errorMessage == null) throw new IllegalArgumentException("ServiceWorker.ServiceWorkerErrorMessage.errorMessage is necessary field.");
            if (registrationId == null) throw new IllegalArgumentException("ServiceWorker.ServiceWorkerErrorMessage.registrationId is necessary field.");
            if (versionId == null) throw new IllegalArgumentException("ServiceWorker.ServiceWorkerErrorMessage.versionId is necessary field.");
            if (sourceURL == null) throw new IllegalArgumentException("ServiceWorker.ServiceWorkerErrorMessage.sourceURL is necessary field.");
            if (lineNumber == null) throw new IllegalArgumentException("ServiceWorker.ServiceWorkerErrorMessage.lineNumber is necessary field.");
            if (columnNumber == null) throw new IllegalArgumentException("ServiceWorker.ServiceWorkerErrorMessage.columnNumber is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"errorMessage\":").append('"').append(DomainBase.escapeQuote(errorMessage)).append('"');
            strBuilder.append(",\"registrationId\":").append('"').append(DomainBase.escapeQuote(registrationId)).append('"');
            strBuilder.append(",\"versionId\":").append('"').append(DomainBase.escapeQuote(versionId)).append('"');
            strBuilder.append(",\"sourceURL\":").append('"').append(DomainBase.escapeQuote(sourceURL)).append('"');
            strBuilder.append(",\"lineNumber\":").append(lineNumber);
            strBuilder.append(",\"columnNumber\":").append(columnNumber);
            strBuilder.append('}');
            return strBuilder;
        }
        public ServiceWorkerErrorMessage() {}
        public ServiceWorkerErrorMessage(
            @JsonProperty("errorMessage")String errorMessage,
            @JsonProperty("registrationId")String registrationId,
            @JsonProperty("versionId")String versionId,
            @JsonProperty("sourceURL")String sourceURL,
            @JsonProperty("lineNumber")Integer lineNumber,
            @JsonProperty("columnNumber")Integer columnNumber
        ) {
            this.errorMessage = errorMessage;
            this.registrationId = registrationId;
            this.versionId = versionId;
            this.sourceURL = sourceURL;
            this.lineNumber = lineNumber;
            this.columnNumber = columnNumber;
        }
    }
    /**&lt;No document in protocol.&gt;*/
    public DeliverPushMessageParameter deliverPushMessage() { final DeliverPushMessageParameter v = new DeliverPushMessageParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of deliverPushMessage.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class DeliverPushMessageParameter extends CommandBase {
        /**&lt;No document in protocol.&gt;*/
        private String origin;
        /**&lt;No document in protocol.&gt;*/
        private String registrationId;
        /**&lt;No document in protocol.&gt;*/
        private String data;
        public final DeliverPushMessageParameter origin(String origin) { this.origin = origin; return this; }
        public final DeliverPushMessageParameter setOrigin(String origin) { return origin(origin); }
        public final String origin() { return origin; }
        public final String getOrigin() { return origin(); }
        public final DeliverPushMessageParameter registrationId(String registrationId) { this.registrationId = registrationId; return this; }
        public final DeliverPushMessageParameter setRegistrationId(String registrationId) { return registrationId(registrationId); }
        public final String registrationId() { return registrationId; }
        public final String getRegistrationId() { return registrationId(); }
        public final DeliverPushMessageParameter data(String data) { this.data = data; return this; }
        public final DeliverPushMessageParameter setData(String data) { return data(data); }
        public final String data() { return data; }
        public final String getData() { return data(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (origin == null) throw new IllegalArgumentException("ServiceWorker.DeliverPushMessageParameter.origin is necessary field.");
            if (registrationId == null) throw new IllegalArgumentException("ServiceWorker.DeliverPushMessageParameter.registrationId is necessary field.");
            if (data == null) throw new IllegalArgumentException("ServiceWorker.DeliverPushMessageParameter.data is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"origin\":").append('"').append(DomainBase.escapeQuote(origin)).append('"');
            strBuilder.append(",\"registrationId\":").append('"').append(DomainBase.escapeQuote(registrationId)).append('"');
            strBuilder.append(",\"data\":").append('"').append(DomainBase.escapeQuote(data)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public DeliverPushMessageParameter() {}
        public DeliverPushMessageParameter(
            @JsonProperty("origin")String origin,
            @JsonProperty("registrationId")String registrationId,
            @JsonProperty("data")String data
        ) {
            this();
            this.origin = origin;
            this.registrationId = registrationId;
            this.data = data;
        }
        public CompletableFuture<DeliverPushMessageResult> call() {
            return super.call("ServiceWorker.deliverPushMessage", DeliverPushMessageResult.class,
                (code, msg)->new DeliverPushMessageResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<DeliverPushMessageResult> call(Executor exec) {
            return super.call("ServiceWorker.deliverPushMessage", DeliverPushMessageResult.class,
                (code, msg)->new DeliverPushMessageResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of deliverPushMessage.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class DeliverPushMessageResult extends ResultBase {
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
        public DeliverPushMessageResult() { super(); }
        public DeliverPushMessageResult(ResultBase.FailedResult e) {
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
            return super.call("ServiceWorker.disable", DisableResult.class,
                (code, msg)->new DisableResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<DisableResult> call(Executor exec) {
            return super.call("ServiceWorker.disable", DisableResult.class,
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
    public DispatchSyncEventParameter dispatchSyncEvent() { final DispatchSyncEventParameter v = new DispatchSyncEventParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of dispatchSyncEvent.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class DispatchSyncEventParameter extends CommandBase {
        /**&lt;No document in protocol.&gt;*/
        private String origin;
        /**&lt;No document in protocol.&gt;*/
        private String registrationId;
        /**&lt;No document in protocol.&gt;*/
        private String tag;
        /**&lt;No document in protocol.&gt;*/
        private Boolean lastChance;
        public final DispatchSyncEventParameter origin(String origin) { this.origin = origin; return this; }
        public final DispatchSyncEventParameter setOrigin(String origin) { return origin(origin); }
        public final String origin() { return origin; }
        public final String getOrigin() { return origin(); }
        public final DispatchSyncEventParameter registrationId(String registrationId) { this.registrationId = registrationId; return this; }
        public final DispatchSyncEventParameter setRegistrationId(String registrationId) { return registrationId(registrationId); }
        public final String registrationId() { return registrationId; }
        public final String getRegistrationId() { return registrationId(); }
        public final DispatchSyncEventParameter tag(String tag) { this.tag = tag; return this; }
        public final DispatchSyncEventParameter setTag(String tag) { return tag(tag); }
        public final String tag() { return tag; }
        public final String getTag() { return tag(); }
        public final DispatchSyncEventParameter lastChance(Boolean lastChance) { this.lastChance = lastChance; return this; }
        public final DispatchSyncEventParameter setLastChance(Boolean lastChance) { return lastChance(lastChance); }
        public final Boolean lastChance() { return lastChance; }
        public final Boolean getLastChance() { return lastChance(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (origin == null) throw new IllegalArgumentException("ServiceWorker.DispatchSyncEventParameter.origin is necessary field.");
            if (registrationId == null) throw new IllegalArgumentException("ServiceWorker.DispatchSyncEventParameter.registrationId is necessary field.");
            if (tag == null) throw new IllegalArgumentException("ServiceWorker.DispatchSyncEventParameter.tag is necessary field.");
            if (lastChance == null) throw new IllegalArgumentException("ServiceWorker.DispatchSyncEventParameter.lastChance is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"origin\":").append('"').append(DomainBase.escapeQuote(origin)).append('"');
            strBuilder.append(",\"registrationId\":").append('"').append(DomainBase.escapeQuote(registrationId)).append('"');
            strBuilder.append(",\"tag\":").append('"').append(DomainBase.escapeQuote(tag)).append('"');
            strBuilder.append(",\"lastChance\":").append(lastChance);
            strBuilder.append('}');
            return strBuilder;
        }
        public DispatchSyncEventParameter() {}
        public DispatchSyncEventParameter(
            @JsonProperty("origin")String origin,
            @JsonProperty("registrationId")String registrationId,
            @JsonProperty("tag")String tag,
            @JsonProperty("lastChance")Boolean lastChance
        ) {
            this();
            this.origin = origin;
            this.registrationId = registrationId;
            this.tag = tag;
            this.lastChance = lastChance;
        }
        public CompletableFuture<DispatchSyncEventResult> call() {
            return super.call("ServiceWorker.dispatchSyncEvent", DispatchSyncEventResult.class,
                (code, msg)->new DispatchSyncEventResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<DispatchSyncEventResult> call(Executor exec) {
            return super.call("ServiceWorker.dispatchSyncEvent", DispatchSyncEventResult.class,
                (code, msg)->new DispatchSyncEventResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of dispatchSyncEvent.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class DispatchSyncEventResult extends ResultBase {
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
        public DispatchSyncEventResult() { super(); }
        public DispatchSyncEventResult(ResultBase.FailedResult e) {
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
            return super.call("ServiceWorker.enable", EnableResult.class,
                (code, msg)->new EnableResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<EnableResult> call(Executor exec) {
            return super.call("ServiceWorker.enable", EnableResult.class,
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
    public InspectWorkerParameter inspectWorker() { final InspectWorkerParameter v = new InspectWorkerParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of inspectWorker.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class InspectWorkerParameter extends CommandBase {
        /**&lt;No document in protocol.&gt;*/
        private String versionId;
        public final InspectWorkerParameter versionId(String versionId) { this.versionId = versionId; return this; }
        public final InspectWorkerParameter setVersionId(String versionId) { return versionId(versionId); }
        public final String versionId() { return versionId; }
        public final String getVersionId() { return versionId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (versionId == null) throw new IllegalArgumentException("ServiceWorker.InspectWorkerParameter.versionId is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"versionId\":").append('"').append(DomainBase.escapeQuote(versionId)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public InspectWorkerParameter() {}
        public InspectWorkerParameter(
            @JsonProperty("versionId")String versionId
        ) {
            this();
            this.versionId = versionId;
        }
        public CompletableFuture<InspectWorkerResult> call() {
            return super.call("ServiceWorker.inspectWorker", InspectWorkerResult.class,
                (code, msg)->new InspectWorkerResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<InspectWorkerResult> call(Executor exec) {
            return super.call("ServiceWorker.inspectWorker", InspectWorkerResult.class,
                (code, msg)->new InspectWorkerResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of inspectWorker.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class InspectWorkerResult extends ResultBase {
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
        public InspectWorkerResult() { super(); }
        public InspectWorkerResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**&lt;No document in protocol.&gt;*/
    public SetForceUpdateOnPageLoadParameter setForceUpdateOnPageLoad() { final SetForceUpdateOnPageLoadParameter v = new SetForceUpdateOnPageLoadParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setForceUpdateOnPageLoad.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetForceUpdateOnPageLoadParameter extends CommandBase {
        /**&lt;No document in protocol.&gt;*/
        private Boolean forceUpdateOnPageLoad;
        public final SetForceUpdateOnPageLoadParameter forceUpdateOnPageLoad(Boolean forceUpdateOnPageLoad) { this.forceUpdateOnPageLoad = forceUpdateOnPageLoad; return this; }
        public final SetForceUpdateOnPageLoadParameter setForceUpdateOnPageLoad(Boolean forceUpdateOnPageLoad) { return forceUpdateOnPageLoad(forceUpdateOnPageLoad); }
        public final Boolean forceUpdateOnPageLoad() { return forceUpdateOnPageLoad; }
        public final Boolean getForceUpdateOnPageLoad() { return forceUpdateOnPageLoad(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (forceUpdateOnPageLoad == null) throw new IllegalArgumentException("ServiceWorker.SetForceUpdateOnPageLoadParameter.forceUpdateOnPageLoad is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"forceUpdateOnPageLoad\":").append(forceUpdateOnPageLoad);
            strBuilder.append('}');
            return strBuilder;
        }
        public SetForceUpdateOnPageLoadParameter() {}
        public SetForceUpdateOnPageLoadParameter(
            @JsonProperty("forceUpdateOnPageLoad")Boolean forceUpdateOnPageLoad
        ) {
            this();
            this.forceUpdateOnPageLoad = forceUpdateOnPageLoad;
        }
        public CompletableFuture<SetForceUpdateOnPageLoadResult> call() {
            return super.call("ServiceWorker.setForceUpdateOnPageLoad", SetForceUpdateOnPageLoadResult.class,
                (code, msg)->new SetForceUpdateOnPageLoadResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetForceUpdateOnPageLoadResult> call(Executor exec) {
            return super.call("ServiceWorker.setForceUpdateOnPageLoad", SetForceUpdateOnPageLoadResult.class,
                (code, msg)->new SetForceUpdateOnPageLoadResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setForceUpdateOnPageLoad.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetForceUpdateOnPageLoadResult extends ResultBase {
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
        public SetForceUpdateOnPageLoadResult() { super(); }
        public SetForceUpdateOnPageLoadResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**&lt;No document in protocol.&gt;*/
    public SkipWaitingParameter skipWaiting() { final SkipWaitingParameter v = new SkipWaitingParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of skipWaiting.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SkipWaitingParameter extends CommandBase {
        /**&lt;No document in protocol.&gt;*/
        private String scopeURL;
        public final SkipWaitingParameter scopeURL(String scopeURL) { this.scopeURL = scopeURL; return this; }
        public final SkipWaitingParameter setScopeURL(String scopeURL) { return scopeURL(scopeURL); }
        public final String scopeURL() { return scopeURL; }
        public final String getScopeURL() { return scopeURL(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (scopeURL == null) throw new IllegalArgumentException("ServiceWorker.SkipWaitingParameter.scopeURL is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"scopeURL\":").append('"').append(DomainBase.escapeQuote(scopeURL)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public SkipWaitingParameter() {}
        public SkipWaitingParameter(
            @JsonProperty("scopeURL")String scopeURL
        ) {
            this();
            this.scopeURL = scopeURL;
        }
        public CompletableFuture<SkipWaitingResult> call() {
            return super.call("ServiceWorker.skipWaiting", SkipWaitingResult.class,
                (code, msg)->new SkipWaitingResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SkipWaitingResult> call(Executor exec) {
            return super.call("ServiceWorker.skipWaiting", SkipWaitingResult.class,
                (code, msg)->new SkipWaitingResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of skipWaiting.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SkipWaitingResult extends ResultBase {
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
        public SkipWaitingResult() { super(); }
        public SkipWaitingResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**&lt;No document in protocol.&gt;*/
    public StartWorkerParameter startWorker() { final StartWorkerParameter v = new StartWorkerParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of startWorker.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class StartWorkerParameter extends CommandBase {
        /**&lt;No document in protocol.&gt;*/
        private String scopeURL;
        public final StartWorkerParameter scopeURL(String scopeURL) { this.scopeURL = scopeURL; return this; }
        public final StartWorkerParameter setScopeURL(String scopeURL) { return scopeURL(scopeURL); }
        public final String scopeURL() { return scopeURL; }
        public final String getScopeURL() { return scopeURL(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (scopeURL == null) throw new IllegalArgumentException("ServiceWorker.StartWorkerParameter.scopeURL is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"scopeURL\":").append('"').append(DomainBase.escapeQuote(scopeURL)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public StartWorkerParameter() {}
        public StartWorkerParameter(
            @JsonProperty("scopeURL")String scopeURL
        ) {
            this();
            this.scopeURL = scopeURL;
        }
        public CompletableFuture<StartWorkerResult> call() {
            return super.call("ServiceWorker.startWorker", StartWorkerResult.class,
                (code, msg)->new StartWorkerResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<StartWorkerResult> call(Executor exec) {
            return super.call("ServiceWorker.startWorker", StartWorkerResult.class,
                (code, msg)->new StartWorkerResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of startWorker.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class StartWorkerResult extends ResultBase {
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
        public StartWorkerResult() { super(); }
        public StartWorkerResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**&lt;No document in protocol.&gt;*/
    public StopAllWorkersParameter stopAllWorkers() { final StopAllWorkersParameter v = new StopAllWorkersParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of stopAllWorkers.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class StopAllWorkersParameter extends CommandBase {
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
        public StopAllWorkersParameter() {}
        public CompletableFuture<StopAllWorkersResult> call() {
            return super.call("ServiceWorker.stopAllWorkers", StopAllWorkersResult.class,
                (code, msg)->new StopAllWorkersResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<StopAllWorkersResult> call(Executor exec) {
            return super.call("ServiceWorker.stopAllWorkers", StopAllWorkersResult.class,
                (code, msg)->new StopAllWorkersResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of stopAllWorkers.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class StopAllWorkersResult extends ResultBase {
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
        public StopAllWorkersResult() { super(); }
        public StopAllWorkersResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**&lt;No document in protocol.&gt;*/
    public StopWorkerParameter stopWorker() { final StopWorkerParameter v = new StopWorkerParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of stopWorker.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class StopWorkerParameter extends CommandBase {
        /**&lt;No document in protocol.&gt;*/
        private String versionId;
        public final StopWorkerParameter versionId(String versionId) { this.versionId = versionId; return this; }
        public final StopWorkerParameter setVersionId(String versionId) { return versionId(versionId); }
        public final String versionId() { return versionId; }
        public final String getVersionId() { return versionId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (versionId == null) throw new IllegalArgumentException("ServiceWorker.StopWorkerParameter.versionId is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"versionId\":").append('"').append(DomainBase.escapeQuote(versionId)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public StopWorkerParameter() {}
        public StopWorkerParameter(
            @JsonProperty("versionId")String versionId
        ) {
            this();
            this.versionId = versionId;
        }
        public CompletableFuture<StopWorkerResult> call() {
            return super.call("ServiceWorker.stopWorker", StopWorkerResult.class,
                (code, msg)->new StopWorkerResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<StopWorkerResult> call(Executor exec) {
            return super.call("ServiceWorker.stopWorker", StopWorkerResult.class,
                (code, msg)->new StopWorkerResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of stopWorker.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class StopWorkerResult extends ResultBase {
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
        public StopWorkerResult() { super(); }
        public StopWorkerResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**&lt;No document in protocol.&gt;*/
    public UnregisterParameter unregister() { final UnregisterParameter v = new UnregisterParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of unregister.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class UnregisterParameter extends CommandBase {
        /**&lt;No document in protocol.&gt;*/
        private String scopeURL;
        public final UnregisterParameter scopeURL(String scopeURL) { this.scopeURL = scopeURL; return this; }
        public final UnregisterParameter setScopeURL(String scopeURL) { return scopeURL(scopeURL); }
        public final String scopeURL() { return scopeURL; }
        public final String getScopeURL() { return scopeURL(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (scopeURL == null) throw new IllegalArgumentException("ServiceWorker.UnregisterParameter.scopeURL is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"scopeURL\":").append('"').append(DomainBase.escapeQuote(scopeURL)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public UnregisterParameter() {}
        public UnregisterParameter(
            @JsonProperty("scopeURL")String scopeURL
        ) {
            this();
            this.scopeURL = scopeURL;
        }
        public CompletableFuture<UnregisterResult> call() {
            return super.call("ServiceWorker.unregister", UnregisterResult.class,
                (code, msg)->new UnregisterResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<UnregisterResult> call(Executor exec) {
            return super.call("ServiceWorker.unregister", UnregisterResult.class,
                (code, msg)->new UnregisterResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of unregister.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class UnregisterResult extends ResultBase {
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
        public UnregisterResult() { super(); }
        public UnregisterResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**&lt;No document in protocol.&gt;*/
    public UpdateRegistrationParameter updateRegistration() { final UpdateRegistrationParameter v = new UpdateRegistrationParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of updateRegistration.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class UpdateRegistrationParameter extends CommandBase {
        /**&lt;No document in protocol.&gt;*/
        private String scopeURL;
        public final UpdateRegistrationParameter scopeURL(String scopeURL) { this.scopeURL = scopeURL; return this; }
        public final UpdateRegistrationParameter setScopeURL(String scopeURL) { return scopeURL(scopeURL); }
        public final String scopeURL() { return scopeURL; }
        public final String getScopeURL() { return scopeURL(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (scopeURL == null) throw new IllegalArgumentException("ServiceWorker.UpdateRegistrationParameter.scopeURL is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"scopeURL\":").append('"').append(DomainBase.escapeQuote(scopeURL)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public UpdateRegistrationParameter() {}
        public UpdateRegistrationParameter(
            @JsonProperty("scopeURL")String scopeURL
        ) {
            this();
            this.scopeURL = scopeURL;
        }
        public CompletableFuture<UpdateRegistrationResult> call() {
            return super.call("ServiceWorker.updateRegistration", UpdateRegistrationResult.class,
                (code, msg)->new UpdateRegistrationResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<UpdateRegistrationResult> call(Executor exec) {
            return super.call("ServiceWorker.updateRegistration", UpdateRegistrationResult.class,
                (code, msg)->new UpdateRegistrationResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of updateRegistration.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class UpdateRegistrationResult extends ResultBase {
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
        public UpdateRegistrationResult() { super(); }
        public UpdateRegistrationResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Event parameter of ServiceWorker.workerErrorReported.
     @see #onWorkerErrorReported*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class WorkerErrorReportedEventParameter implements CommonDomainType {
        /**&lt;No document in protocol.&gt;*/
        private final ServiceWorkerErrorMessage errorMessage;
        public final ServiceWorkerErrorMessage errorMessage() { return errorMessage; }
        public final ServiceWorkerErrorMessage getErrorMessage() { return errorMessage(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            errorMessage.toJson(strBuilder.append("\"errorMessage\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        WorkerErrorReportedEventParameter(
            @JsonProperty("errorMessage")ServiceWorkerErrorMessage errorMessage
        ) {
            this.errorMessage = errorMessage;
        }
    }
    /**&lt;No document in protocol.&gt;
     @see WorkerErrorReportedEventParameter*/
    public void onWorkerErrorReported(Consumer<WorkerErrorReportedEventParameter> callback) {
        registerEventCallback("ServiceWorker.workerErrorReported", node -> {
            WorkerErrorReportedEventParameter param;
            try { param = EventCenter.deserializeJson(node, WorkerErrorReportedEventParameter.class); }
            catch (IOException e) { e.printStackTrace(); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of ServiceWorker.workerRegistrationUpdated.
     @see #onWorkerRegistrationUpdated*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class WorkerRegistrationUpdatedEventParameter implements CommonDomainType {
        /**&lt;No document in protocol.&gt;*/
        private final List<ServiceWorkerRegistration> registrations;
        public final List<ServiceWorkerRegistration> registrations() { return registrations; }
        public final List<ServiceWorkerRegistration> getRegistrations() { return registrations(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
                        strBuilder.append("\"registrations\":[");
            registrations.get(0).toJson(strBuilder);
            for (int i = 1; i < registrations.size(); ++i)
                registrations.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        WorkerRegistrationUpdatedEventParameter(
            @JsonProperty("registrations")List<ServiceWorkerRegistration> registrations
        ) {
            this.registrations = registrations;
        }
    }
    /**&lt;No document in protocol.&gt;
     @see WorkerRegistrationUpdatedEventParameter*/
    public void onWorkerRegistrationUpdated(Consumer<WorkerRegistrationUpdatedEventParameter> callback) {
        registerEventCallback("ServiceWorker.workerRegistrationUpdated", node -> {
            WorkerRegistrationUpdatedEventParameter param;
            try { param = EventCenter.deserializeJson(node, WorkerRegistrationUpdatedEventParameter.class); }
            catch (IOException e) { e.printStackTrace(); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of ServiceWorker.workerVersionUpdated.
     @see #onWorkerVersionUpdated*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class WorkerVersionUpdatedEventParameter implements CommonDomainType {
        /**&lt;No document in protocol.&gt;*/
        private final List<ServiceWorkerVersion> versions;
        public final List<ServiceWorkerVersion> versions() { return versions; }
        public final List<ServiceWorkerVersion> getVersions() { return versions(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
                        strBuilder.append("\"versions\":[");
            versions.get(0).toJson(strBuilder);
            for (int i = 1; i < versions.size(); ++i)
                versions.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        WorkerVersionUpdatedEventParameter(
            @JsonProperty("versions")List<ServiceWorkerVersion> versions
        ) {
            this.versions = versions;
        }
    }
    /**&lt;No document in protocol.&gt;
     @see WorkerVersionUpdatedEventParameter*/
    public void onWorkerVersionUpdated(Consumer<WorkerVersionUpdatedEventParameter> callback) {
        registerEventCallback("ServiceWorker.workerVersionUpdated", node -> {
            WorkerVersionUpdatedEventParameter param;
            try { param = EventCenter.deserializeJson(node, WorkerVersionUpdatedEventParameter.class); }
            catch (IOException e) { e.printStackTrace(); return; }
            callback.accept(param);
        });
    }
}

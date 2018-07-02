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

/**Security
<p>From: browser_protocol.json</p>
<p>Protocol version: 1.3</p>
 @author Joshua*/
@ParametersAreNonnullByDefault public class Security extends DomainBase {
    public Security(EventCenter evt, WebSocket ws) { super(evt, ws); }

    /**An internal certificate ID value.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class CertificateId implements CommonDomainType {
        private Integer _value;
        public CertificateId() {}
        public CertificateId(Integer value) { _value = value; }
        public final CertificateId value(Integer value) { _value = value; return this; }
        public final Integer value() { return _value; }
        public final CertificateId setValue(Integer value) { return value(value); }
        public final Integer getValue() { return value(); }
        @Override public String toString() { return String.valueOf(_value); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (_value == null) throw new IllegalArgumentException("Security.CertificateId.value is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            return strBuilder.append(_value);
        }
    }

    /**A description of mixed content (HTTP resources on HTTPS pages), as defined by
https://www.w3.org/TR/mixed-content/#categories*/
    @ParametersAreNonnullByDefault public enum MixedContentType implements CommonDomainType {
        Blockable("blockable"),
        Optionally_blockable("optionally-blockable"),
        None("none");

        private final String _value;
        /**Convert string representation to type.
         @throws IllegalArgumentException if given value cannot convert to enum type. */
        @JsonCreator public static MixedContentType of(String value) {
            return Enum.valueOf(MixedContentType.class, value.substring(0, 1).toUpperCase() + value.substring(1));
        }
        MixedContentType(String value) { _value = value; }
        /**Check if parameter fields of method are all valid. */
        @Override public void check() throws IllegalArgumentException { /* Need not check */ }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) { return strBuilder.append('"').append(_value).append('"'); }
        @Override public String toString() { return "\"" + _value + "\""; }
    }

    /**The security level of a page or resource.*/
    @ParametersAreNonnullByDefault public enum SecurityState implements CommonDomainType {
        Unknown("unknown"),
        Neutral("neutral"),
        Insecure("insecure"),
        Secure("secure"),
        Info("info");

        private final String _value;
        /**Convert string representation to type.
         @throws IllegalArgumentException if given value cannot convert to enum type. */
        @JsonCreator public static SecurityState of(String value) {
            return Enum.valueOf(SecurityState.class, value.substring(0, 1).toUpperCase() + value.substring(1));
        }
        SecurityState(String value) { _value = value; }
        /**Check if parameter fields of method are all valid. */
        @Override public void check() throws IllegalArgumentException { /* Need not check */ }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) { return strBuilder.append('"').append(_value).append('"'); }
        @Override public String toString() { return "\"" + _value + "\""; }
    }

    /**An explanation of an factor contributing to the security state.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SecurityStateExplanation implements CommonDomainType {
        /**Security state representing the severity of the factor being explained.*/
        private SecurityState securityState;
        /**Title describing the type of factor.*/
        private String title;
        /**Short phrase describing the type of factor.*/
        private String summary;
        /**Full text explanation of the factor.*/
        private String description;
        /**The type of mixed content described by the explanation.*/
        private MixedContentType mixedContentType;
        /**Page certificate.*/
        private List<String> certificate;
        public final SecurityStateExplanation securityState(SecurityState securityState) { this.securityState = securityState; return this; }
        public final SecurityStateExplanation setSecurityState(SecurityState securityState) { return securityState(securityState); }
        public final SecurityState securityState() { return securityState; }
        public final SecurityState getSecurityState() { return securityState(); }
        public final SecurityStateExplanation title(String title) { this.title = title; return this; }
        public final SecurityStateExplanation setTitle(String title) { return title(title); }
        public final String title() { return title; }
        public final String getTitle() { return title(); }
        public final SecurityStateExplanation summary(String summary) { this.summary = summary; return this; }
        public final SecurityStateExplanation setSummary(String summary) { return summary(summary); }
        public final String summary() { return summary; }
        public final String getSummary() { return summary(); }
        public final SecurityStateExplanation description(String description) { this.description = description; return this; }
        public final SecurityStateExplanation setDescription(String description) { return description(description); }
        public final String description() { return description; }
        public final String getDescription() { return description(); }
        public final SecurityStateExplanation mixedContentType(MixedContentType mixedContentType) { this.mixedContentType = mixedContentType; return this; }
        public final SecurityStateExplanation setMixedContentType(MixedContentType mixedContentType) { return mixedContentType(mixedContentType); }
        public final MixedContentType mixedContentType() { return mixedContentType; }
        public final MixedContentType getMixedContentType() { return mixedContentType(); }
        public final SecurityStateExplanation certificate(List<String> certificate) { this.certificate = certificate; return this; }
        public final SecurityStateExplanation setCertificate(List<String> certificate) { return certificate(certificate); }
        public final List<String> certificate() { return certificate; }
        public final List<String> getCertificate() { return certificate(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (securityState == null) throw new IllegalArgumentException("Security.SecurityStateExplanation.securityState is necessary field.");
            if (title == null) throw new IllegalArgumentException("Security.SecurityStateExplanation.title is necessary field.");
            if (summary == null) throw new IllegalArgumentException("Security.SecurityStateExplanation.summary is necessary field.");
            if (description == null) throw new IllegalArgumentException("Security.SecurityStateExplanation.description is necessary field.");
            if (mixedContentType == null) throw new IllegalArgumentException("Security.SecurityStateExplanation.mixedContentType is necessary field.");
            if (certificate == null) throw new IllegalArgumentException("Security.SecurityStateExplanation.certificate is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            securityState.toJson(strBuilder.append("\"securityState\":"));
            strBuilder.append(",\"title\":").append('"').append(DomainBase.escapeJson(title)).append('"');
            strBuilder.append(",\"summary\":").append('"').append(DomainBase.escapeJson(summary)).append('"');
            strBuilder.append(",\"description\":").append('"').append(DomainBase.escapeJson(description)).append('"');
            mixedContentType.toJson(strBuilder.append(",\"mixedContentType\":"));
                        strBuilder.append(",\"certificate\":[");
            strBuilder.append('"').append(DomainBase.escapeJson(certificate.get(0))).append('"');
            for (int i = 1; i < certificate.size(); ++i)
                strBuilder.append(",\"").append(DomainBase.escapeJson(certificate.get(i))).append('"');
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public SecurityStateExplanation() {}
        public SecurityStateExplanation(
            @JsonProperty("securityState")SecurityState securityState,
            @JsonProperty("title")String title,
            @JsonProperty("summary")String summary,
            @JsonProperty("description")String description,
            @JsonProperty("mixedContentType")MixedContentType mixedContentType,
            @JsonProperty("certificate")List<String> certificate
        ) {
            this.securityState = securityState;
            this.title = title;
            this.summary = summary;
            this.description = description;
            this.mixedContentType = mixedContentType;
            this.certificate = certificate;
        }
    }

    /**Information about insecure content on the page.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class InsecureContentStatus implements CommonDomainType {
        /**True if the page was loaded over HTTPS and ran mixed (HTTP) content such as scripts.*/
        private Boolean ranMixedContent;
        /**True if the page was loaded over HTTPS and displayed mixed (HTTP) content such as images.*/
        private Boolean displayedMixedContent;
        /**True if the page was loaded over HTTPS and contained a form targeting an insecure url.*/
        private Boolean containedMixedForm;
        /**True if the page was loaded over HTTPS without certificate errors, and ran content such as
scripts that were loaded with certificate errors.*/
        private Boolean ranContentWithCertErrors;
        /**True if the page was loaded over HTTPS without certificate errors, and displayed content
such as images that were loaded with certificate errors.*/
        private Boolean displayedContentWithCertErrors;
        /**Security state representing a page that ran insecure content.*/
        private SecurityState ranInsecureContentStyle;
        /**Security state representing a page that displayed insecure content.*/
        private SecurityState displayedInsecureContentStyle;
        public final InsecureContentStatus ranMixedContent(Boolean ranMixedContent) { this.ranMixedContent = ranMixedContent; return this; }
        public final InsecureContentStatus setRanMixedContent(Boolean ranMixedContent) { return ranMixedContent(ranMixedContent); }
        public final Boolean ranMixedContent() { return ranMixedContent; }
        public final Boolean getRanMixedContent() { return ranMixedContent(); }
        public final InsecureContentStatus displayedMixedContent(Boolean displayedMixedContent) { this.displayedMixedContent = displayedMixedContent; return this; }
        public final InsecureContentStatus setDisplayedMixedContent(Boolean displayedMixedContent) { return displayedMixedContent(displayedMixedContent); }
        public final Boolean displayedMixedContent() { return displayedMixedContent; }
        public final Boolean getDisplayedMixedContent() { return displayedMixedContent(); }
        public final InsecureContentStatus containedMixedForm(Boolean containedMixedForm) { this.containedMixedForm = containedMixedForm; return this; }
        public final InsecureContentStatus setContainedMixedForm(Boolean containedMixedForm) { return containedMixedForm(containedMixedForm); }
        public final Boolean containedMixedForm() { return containedMixedForm; }
        public final Boolean getContainedMixedForm() { return containedMixedForm(); }
        public final InsecureContentStatus ranContentWithCertErrors(Boolean ranContentWithCertErrors) { this.ranContentWithCertErrors = ranContentWithCertErrors; return this; }
        public final InsecureContentStatus setRanContentWithCertErrors(Boolean ranContentWithCertErrors) { return ranContentWithCertErrors(ranContentWithCertErrors); }
        public final Boolean ranContentWithCertErrors() { return ranContentWithCertErrors; }
        public final Boolean getRanContentWithCertErrors() { return ranContentWithCertErrors(); }
        public final InsecureContentStatus displayedContentWithCertErrors(Boolean displayedContentWithCertErrors) { this.displayedContentWithCertErrors = displayedContentWithCertErrors; return this; }
        public final InsecureContentStatus setDisplayedContentWithCertErrors(Boolean displayedContentWithCertErrors) { return displayedContentWithCertErrors(displayedContentWithCertErrors); }
        public final Boolean displayedContentWithCertErrors() { return displayedContentWithCertErrors; }
        public final Boolean getDisplayedContentWithCertErrors() { return displayedContentWithCertErrors(); }
        public final InsecureContentStatus ranInsecureContentStyle(SecurityState ranInsecureContentStyle) { this.ranInsecureContentStyle = ranInsecureContentStyle; return this; }
        public final InsecureContentStatus setRanInsecureContentStyle(SecurityState ranInsecureContentStyle) { return ranInsecureContentStyle(ranInsecureContentStyle); }
        public final SecurityState ranInsecureContentStyle() { return ranInsecureContentStyle; }
        public final SecurityState getRanInsecureContentStyle() { return ranInsecureContentStyle(); }
        public final InsecureContentStatus displayedInsecureContentStyle(SecurityState displayedInsecureContentStyle) { this.displayedInsecureContentStyle = displayedInsecureContentStyle; return this; }
        public final InsecureContentStatus setDisplayedInsecureContentStyle(SecurityState displayedInsecureContentStyle) { return displayedInsecureContentStyle(displayedInsecureContentStyle); }
        public final SecurityState displayedInsecureContentStyle() { return displayedInsecureContentStyle; }
        public final SecurityState getDisplayedInsecureContentStyle() { return displayedInsecureContentStyle(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (ranMixedContent == null) throw new IllegalArgumentException("Security.InsecureContentStatus.ranMixedContent is necessary field.");
            if (displayedMixedContent == null) throw new IllegalArgumentException("Security.InsecureContentStatus.displayedMixedContent is necessary field.");
            if (containedMixedForm == null) throw new IllegalArgumentException("Security.InsecureContentStatus.containedMixedForm is necessary field.");
            if (ranContentWithCertErrors == null) throw new IllegalArgumentException("Security.InsecureContentStatus.ranContentWithCertErrors is necessary field.");
            if (displayedContentWithCertErrors == null) throw new IllegalArgumentException("Security.InsecureContentStatus.displayedContentWithCertErrors is necessary field.");
            if (ranInsecureContentStyle == null) throw new IllegalArgumentException("Security.InsecureContentStatus.ranInsecureContentStyle is necessary field.");
            if (displayedInsecureContentStyle == null) throw new IllegalArgumentException("Security.InsecureContentStatus.displayedInsecureContentStyle is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"ranMixedContent\":").append(ranMixedContent);
            strBuilder.append(",\"displayedMixedContent\":").append(displayedMixedContent);
            strBuilder.append(",\"containedMixedForm\":").append(containedMixedForm);
            strBuilder.append(",\"ranContentWithCertErrors\":").append(ranContentWithCertErrors);
            strBuilder.append(",\"displayedContentWithCertErrors\":").append(displayedContentWithCertErrors);
            ranInsecureContentStyle.toJson(strBuilder.append(",\"ranInsecureContentStyle\":"));
            displayedInsecureContentStyle.toJson(strBuilder.append(",\"displayedInsecureContentStyle\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public InsecureContentStatus() {}
        public InsecureContentStatus(
            @JsonProperty("ranMixedContent")Boolean ranMixedContent,
            @JsonProperty("displayedMixedContent")Boolean displayedMixedContent,
            @JsonProperty("containedMixedForm")Boolean containedMixedForm,
            @JsonProperty("ranContentWithCertErrors")Boolean ranContentWithCertErrors,
            @JsonProperty("displayedContentWithCertErrors")Boolean displayedContentWithCertErrors,
            @JsonProperty("ranInsecureContentStyle")SecurityState ranInsecureContentStyle,
            @JsonProperty("displayedInsecureContentStyle")SecurityState displayedInsecureContentStyle
        ) {
            this.ranMixedContent = ranMixedContent;
            this.displayedMixedContent = displayedMixedContent;
            this.containedMixedForm = containedMixedForm;
            this.ranContentWithCertErrors = ranContentWithCertErrors;
            this.displayedContentWithCertErrors = displayedContentWithCertErrors;
            this.ranInsecureContentStyle = ranInsecureContentStyle;
            this.displayedInsecureContentStyle = displayedInsecureContentStyle;
        }
    }

    /**The action to take when a certificate error occurs. continue will continue processing the
request and cancel will cancel the request.*/
    @ParametersAreNonnullByDefault public enum CertificateErrorAction implements CommonDomainType {
        Continue("continue"),
        Cancel("cancel");

        private final String _value;
        /**Convert string representation to type.
         @throws IllegalArgumentException if given value cannot convert to enum type. */
        @JsonCreator public static CertificateErrorAction of(String value) {
            return Enum.valueOf(CertificateErrorAction.class, value.substring(0, 1).toUpperCase() + value.substring(1));
        }
        CertificateErrorAction(String value) { _value = value; }
        /**Check if parameter fields of method are all valid. */
        @Override public void check() throws IllegalArgumentException { /* Need not check */ }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) { return strBuilder.append('"').append(_value).append('"'); }
        @Override public String toString() { return "\"" + _value + "\""; }
    }
    /**Disables tracking security state changes.*/
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
            return super.call("Security.disable", DisableResult.class,
                (code, msg)->new DisableResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<DisableResult> call(Executor exec) {
            return super.call("Security.disable", DisableResult.class,
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
    /**Enables tracking security state changes.*/
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
            return super.call("Security.enable", EnableResult.class,
                (code, msg)->new EnableResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<EnableResult> call(Executor exec) {
            return super.call("Security.enable", EnableResult.class,
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
    /**Enable/disable whether all certificate errors should be ignored.
    <p><strong>Experimental.</strong></p>*/
    public SetIgnoreCertificateErrorsParameter setIgnoreCertificateErrors() { final SetIgnoreCertificateErrorsParameter v = new SetIgnoreCertificateErrorsParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setIgnoreCertificateErrors.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetIgnoreCertificateErrorsParameter extends CommandBase {
        /**If true, all certificate errors will be ignored.*/
        private Boolean ignore;
        public final SetIgnoreCertificateErrorsParameter ignore(Boolean ignore) { this.ignore = ignore; return this; }
        public final SetIgnoreCertificateErrorsParameter setIgnore(Boolean ignore) { return ignore(ignore); }
        public final Boolean ignore() { return ignore; }
        public final Boolean getIgnore() { return ignore(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (ignore == null) throw new IllegalArgumentException("Security.SetIgnoreCertificateErrorsParameter.ignore is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"ignore\":").append(ignore);
            strBuilder.append('}');
            return strBuilder;
        }
        public SetIgnoreCertificateErrorsParameter() {}
        public SetIgnoreCertificateErrorsParameter(
            @JsonProperty("ignore")Boolean ignore
        ) {
            this();
            this.ignore = ignore;
        }
        public CompletableFuture<SetIgnoreCertificateErrorsResult> call() {
            return super.call("Security.setIgnoreCertificateErrors", SetIgnoreCertificateErrorsResult.class,
                (code, msg)->new SetIgnoreCertificateErrorsResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetIgnoreCertificateErrorsResult> call(Executor exec) {
            return super.call("Security.setIgnoreCertificateErrors", SetIgnoreCertificateErrorsResult.class,
                (code, msg)->new SetIgnoreCertificateErrorsResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setIgnoreCertificateErrors.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetIgnoreCertificateErrorsResult extends ResultBase {
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
        public SetIgnoreCertificateErrorsResult() { super(); }
        public SetIgnoreCertificateErrorsResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Handles a certificate error that fired a certificateError event.
    @Deprecated*/
    public HandleCertificateErrorParameter handleCertificateError() { final HandleCertificateErrorParameter v = new HandleCertificateErrorParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of handleCertificateError.
    @Deprecated*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class HandleCertificateErrorParameter extends CommandBase {
        /**The ID of the event.*/
        private Integer eventId;
        /**The action to take on the certificate error.*/
        private CertificateErrorAction action;
        public final HandleCertificateErrorParameter eventId(Integer eventId) { this.eventId = eventId; return this; }
        public final HandleCertificateErrorParameter setEventId(Integer eventId) { return eventId(eventId); }
        public final Integer eventId() { return eventId; }
        public final Integer getEventId() { return eventId(); }
        public final HandleCertificateErrorParameter action(CertificateErrorAction action) { this.action = action; return this; }
        public final HandleCertificateErrorParameter setAction(CertificateErrorAction action) { return action(action); }
        public final CertificateErrorAction action() { return action; }
        public final CertificateErrorAction getAction() { return action(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (eventId == null) throw new IllegalArgumentException("Security.HandleCertificateErrorParameter.eventId is necessary field.");
            if (action == null) throw new IllegalArgumentException("Security.HandleCertificateErrorParameter.action is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"eventId\":").append(eventId);
            action.toJson(strBuilder.append(",\"action\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public HandleCertificateErrorParameter() {}
        public HandleCertificateErrorParameter(
            @JsonProperty("eventId")Integer eventId,
            @JsonProperty("action")CertificateErrorAction action
        ) {
            this();
            this.eventId = eventId;
            this.action = action;
        }
        public CompletableFuture<HandleCertificateErrorResult> call() {
            return super.call("Security.handleCertificateError", HandleCertificateErrorResult.class,
                (code, msg)->new HandleCertificateErrorResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<HandleCertificateErrorResult> call(Executor exec) {
            return super.call("Security.handleCertificateError", HandleCertificateErrorResult.class,
                (code, msg)->new HandleCertificateErrorResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of handleCertificateError.
    @Deprecated*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class HandleCertificateErrorResult extends ResultBase {
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
        public HandleCertificateErrorResult() { super(); }
        public HandleCertificateErrorResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Enable/disable overriding certificate errors. If enabled, all certificate error events need to
be handled by the DevTools client and should be answered with `handleCertificateError` commands.
    @Deprecated*/
    public SetOverrideCertificateErrorsParameter setOverrideCertificateErrors() { final SetOverrideCertificateErrorsParameter v = new SetOverrideCertificateErrorsParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setOverrideCertificateErrors.
    @Deprecated*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetOverrideCertificateErrorsParameter extends CommandBase {
        /**If true, certificate errors will be overridden.*/
        private Boolean override;
        public final SetOverrideCertificateErrorsParameter override(Boolean override) { this.override = override; return this; }
        public final SetOverrideCertificateErrorsParameter setOverride(Boolean override) { return override(override); }
        public final Boolean override() { return override; }
        public final Boolean getOverride() { return override(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (override == null) throw new IllegalArgumentException("Security.SetOverrideCertificateErrorsParameter.override is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"override\":").append(override);
            strBuilder.append('}');
            return strBuilder;
        }
        public SetOverrideCertificateErrorsParameter() {}
        public SetOverrideCertificateErrorsParameter(
            @JsonProperty("override")Boolean override
        ) {
            this();
            this.override = override;
        }
        public CompletableFuture<SetOverrideCertificateErrorsResult> call() {
            return super.call("Security.setOverrideCertificateErrors", SetOverrideCertificateErrorsResult.class,
                (code, msg)->new SetOverrideCertificateErrorsResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetOverrideCertificateErrorsResult> call(Executor exec) {
            return super.call("Security.setOverrideCertificateErrors", SetOverrideCertificateErrorsResult.class,
                (code, msg)->new SetOverrideCertificateErrorsResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setOverrideCertificateErrors.
    @Deprecated*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetOverrideCertificateErrorsResult extends ResultBase {
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
        public SetOverrideCertificateErrorsResult() { super(); }
        public SetOverrideCertificateErrorsResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Event parameter of Security.certificateError.
     @see #onCertificateError
    @Deprecated*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class CertificateErrorEventParameter implements CommonDomainType {
        /**The ID of the event.*/
        private final Integer eventId;
        /**The type of the error.*/
        private final String errorType;
        /**The url that was requested.*/
        private final String requestURL;
        public final Integer eventId() { return eventId; }
        public final Integer getEventId() { return eventId(); }
        public final String errorType() { return errorType; }
        public final String getErrorType() { return errorType(); }
        public final String requestURL() { return requestURL; }
        public final String getRequestURL() { return requestURL(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"eventId\":").append(eventId);
            strBuilder.append(",\"errorType\":").append('"').append(DomainBase.escapeJson(errorType)).append('"');
            strBuilder.append(",\"requestURL\":").append('"').append(DomainBase.escapeJson(requestURL)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        CertificateErrorEventParameter(
            @JsonProperty("eventId")Integer eventId,
            @JsonProperty("errorType")String errorType,
            @JsonProperty("requestURL")String requestURL
        ) {
            this.eventId = eventId;
            this.errorType = errorType;
            this.requestURL = requestURL;
        }
    }
    /**There is a certificate error. If overriding certificate errors is enabled, then it should be
handled with the `handleCertificateError` command. Note: this event does not fire if the
certificate error has been allowed internally. Only one client per target should override
certificate errors at the same time.
     @see CertificateErrorEventParameter
    @Deprecated*/
    public void onCertificateError(@Nullable Consumer<CertificateErrorEventParameter> callback) {
        if (callback != null)
            registerEventCallback("Security.certificateError", node -> {
                CertificateErrorEventParameter param;
                try { param = EventCenter.deserializeJson(node, CertificateErrorEventParameter.class); }
                catch (IOException e) { _evt.getLog().error(e); return; }
                callback.accept(param);
            });
        else    registerEventCallback("Security.certificateError", null);
    }
    /**Event parameter of Security.securityStateChanged.
     @see #onSecurityStateChanged*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SecurityStateChangedEventParameter implements CommonDomainType {
        /**Security state.*/
        private final SecurityState securityState;
        /**True if the page was loaded over cryptographic transport such as HTTPS.*/
        private final Boolean schemeIsCryptographic;
        /**List of explanations for the security state. If the overall security state is `insecure` or
`warning`, at least one corresponding explanation should be included.*/
        private final List<SecurityStateExplanation> explanations;
        /**Information about insecure content on the page.*/
        private final InsecureContentStatus insecureContentStatus;
        /**Overrides user-visible description of the state.
        <em>Optional.</em>*/
        private final String summary;
        public final SecurityState securityState() { return securityState; }
        public final SecurityState getSecurityState() { return securityState(); }
        public final Boolean schemeIsCryptographic() { return schemeIsCryptographic; }
        public final Boolean getSchemeIsCryptographic() { return schemeIsCryptographic(); }
        public final List<SecurityStateExplanation> explanations() { return explanations; }
        public final List<SecurityStateExplanation> getExplanations() { return explanations(); }
        public final InsecureContentStatus insecureContentStatus() { return insecureContentStatus; }
        public final InsecureContentStatus getInsecureContentStatus() { return insecureContentStatus(); }
        public final String summary() { return summary; }
        public final String getSummary() { return summary(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            securityState.toJson(strBuilder.append("\"securityState\":"));
            strBuilder.append(",\"schemeIsCryptographic\":").append(schemeIsCryptographic);
                        strBuilder.append(",\"explanations\":[");
            explanations.get(0).toJson(strBuilder);
            for (int i = 1; i < explanations.size(); ++i)
                explanations.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            insecureContentStatus.toJson(strBuilder.append(",\"insecureContentStatus\":"));
            if (summary != null) strBuilder.append(",\"summary\":").append('"').append(DomainBase.escapeJson(summary)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        SecurityStateChangedEventParameter(
            @JsonProperty("securityState")SecurityState securityState,
            @JsonProperty("schemeIsCryptographic")Boolean schemeIsCryptographic,
            @JsonProperty("explanations")List<SecurityStateExplanation> explanations,
            @JsonProperty("insecureContentStatus")InsecureContentStatus insecureContentStatus,
            @Nullable @JsonProperty("summary")String summary
        ) {
            this.securityState = securityState;
            this.schemeIsCryptographic = schemeIsCryptographic;
            this.explanations = explanations;
            this.insecureContentStatus = insecureContentStatus;
            this.summary = summary;
        }
    }
    /**The security state of the page changed.
     @see SecurityStateChangedEventParameter*/
    public void onSecurityStateChanged(@Nullable Consumer<SecurityStateChangedEventParameter> callback) {
        if (callback != null)
            registerEventCallback("Security.securityStateChanged", node -> {
                SecurityStateChangedEventParameter param;
                try { param = EventCenter.deserializeJson(node, SecurityStateChangedEventParameter.class); }
                catch (IOException e) { _evt.getLog().error(e); return; }
                callback.accept(param);
            });
        else    registerEventCallback("Security.securityStateChanged", null);
    }
}

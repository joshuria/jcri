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

/**Testing domain is a dumping ground for the capabilities requires for browser or app testing that do not fit other
domains.
<p>From: browser_protocol.json</p>
<p>Protocol version: 1.3</p>
<p><strong>Experimental.</strong></p>
 @see Page
 @author Joshua*/
@ParametersAreNonnullByDefault public class Testing extends DomainBase {
    public Testing(EventCenter evt, WebSocket ws) { super(evt, ws); }
    /**Generates a report for testing.*/
    public GenerateTestReportParameter generateTestReport() { final GenerateTestReportParameter v = new GenerateTestReportParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of generateTestReport.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GenerateTestReportParameter extends CommandBase {
        /**Message to be displayed in the report.*/
        private String message;
        /**Specifies the endpoint group to deliver the report to.
        <em>Optional.</em>*/
        private String group;
        public final GenerateTestReportParameter message(String message) { this.message = message; return this; }
        public final GenerateTestReportParameter setMessage(String message) { return message(message); }
        public final String message() { return message; }
        public final String getMessage() { return message(); }
        public final GenerateTestReportParameter group(@Nullable String group) { this.group = group; return this; }
        public final GenerateTestReportParameter optGroup(@Nullable String group) { return group(group); }
        public final String group() { return group; }
        public final String getGroup() { return group(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (message == null) throw new IllegalArgumentException("Testing.GenerateTestReportParameter.message is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"message\":").append('"').append(DomainBase.escapeJson(message)).append('"');
            if (group != null) strBuilder.append(",\"group\":").append('"').append(DomainBase.escapeJson(group)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public GenerateTestReportParameter() {}
        public GenerateTestReportParameter(
            @JsonProperty("message")String message,
            @Nullable @JsonProperty("group")String group
        ) {
            this();
            this.message = message;
            this.group = group;
        }
        public CompletableFuture<GenerateTestReportResult> call() {
            return super.call("Testing.generateTestReport", GenerateTestReportResult.class,
                (code, msg)->new GenerateTestReportResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GenerateTestReportResult> callAsync() {
            return super.callAsync("Testing.generateTestReport", GenerateTestReportResult.class,
                (code, msg)->new GenerateTestReportResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GenerateTestReportResult> callAsync(Executor exec) {
            return super.callAsync("Testing.generateTestReport", GenerateTestReportResult.class,
                (code, msg)->new GenerateTestReportResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of generateTestReport.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GenerateTestReportResult extends ResultBase {
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
        public GenerateTestReportResult() { super(); }
        public GenerateTestReportResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
}

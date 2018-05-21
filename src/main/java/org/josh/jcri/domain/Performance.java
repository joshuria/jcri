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
 @author Joshua*/
@ParametersAreNonnullByDefault public class Performance extends DomainBase {
    public Performance(EventCenter evt, WebSocket ws) { super(evt, ws); }

    /**Run-time execution metric.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class Metric implements CommonDomainType {
        /**Metric name.*/
        private String name;
        /**Metric value.*/
        private Double value;
        public final Metric name(String name) { this.name = name; return this; }
        public final Metric setName(String name) { return name(name); }
        public final String name() { return name; }
        public final String getName() { return name(); }
        public final Metric value(Double value) { this.value = value; return this; }
        public final Metric setValue(Double value) { return value(value); }
        public final Double value() { return value; }
        public final Double getValue() { return value(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (name == null) throw new IllegalArgumentException("Performance.Metric.name is necessary field.");
            if (value == null) throw new IllegalArgumentException("Performance.Metric.value is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"name\":").append('"').append(DomainBase.escapeJson(name)).append('"');
            strBuilder.append(",\"value\":").append(value);
            strBuilder.append('}');
            return strBuilder;
        }
        public Metric() {}
        public Metric(
            @JsonProperty("name")String name,
            @JsonProperty("value")Double value
        ) {
            this.name = name;
            this.value = value;
        }
    }
    /**Disable collecting and reporting metrics.*/
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
            return super.call("Performance.disable", DisableResult.class,
                (code, msg)->new DisableResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<DisableResult> call(Executor exec) {
            return super.call("Performance.disable", DisableResult.class,
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
    /**Enable collecting and reporting metrics.*/
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
            return super.call("Performance.enable", EnableResult.class,
                (code, msg)->new EnableResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<EnableResult> call(Executor exec) {
            return super.call("Performance.enable", EnableResult.class,
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
    /**Retrieve current values of run-time metrics.*/
    public GetMetricsParameter getMetrics() { final GetMetricsParameter v = new GetMetricsParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of getMetrics.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetMetricsParameter extends CommandBase {
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
        public GetMetricsParameter() {}
        public CompletableFuture<GetMetricsResult> call() {
            return super.call("Performance.getMetrics", GetMetricsResult.class,
                (code, msg)->new GetMetricsResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetMetricsResult> call(Executor exec) {
            return super.call("Performance.getMetrics", GetMetricsResult.class,
                (code, msg)->new GetMetricsResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of getMetrics.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetMetricsResult extends ResultBase {
        /**Current values for run-time metrics.*/
        private final List<Metric> metrics;
        public final List<Metric> metrics() { return metrics; }
        public final List<Metric> getMetrics() { return metrics(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
                        strBuilder.append("\"metrics\":[");
            metrics.get(0).toJson(strBuilder);
            for (int i = 1; i < metrics.size(); ++i)
                metrics.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public GetMetricsResult(
            @JsonProperty("metrics")List<Metric> metrics
        ) {
            this.metrics = metrics;
        }
        public GetMetricsResult(ResultBase.FailedResult e) {
            super(e);
            metrics = null;
        }
    }
    /**Event parameter of Performance.metrics.
     @see #onMetrics*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class MetricsEventParameter implements CommonDomainType {
        /**Current values of the metrics.*/
        private final List<Metric> metrics;
        /**Timestamp title.*/
        private final String title;
        public final List<Metric> metrics() { return metrics; }
        public final List<Metric> getMetrics() { return metrics(); }
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
                        strBuilder.append("\"metrics\":[");
            metrics.get(0).toJson(strBuilder);
            for (int i = 1; i < metrics.size(); ++i)
                metrics.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            strBuilder.append(",\"title\":").append('"').append(DomainBase.escapeJson(title)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        MetricsEventParameter(
            @JsonProperty("metrics")List<Metric> metrics,
            @JsonProperty("title")String title
        ) {
            this.metrics = metrics;
            this.title = title;
        }
    }
    /**Current values of the metrics.
     @see MetricsEventParameter*/
    public void onMetrics(Consumer<MetricsEventParameter> callback) {
        registerEventCallback("Performance.metrics", node -> {
            MetricsEventParameter param;
            try { param = EventCenter.deserializeJson(node, MetricsEventParameter.class); }
            catch (IOException e) { _evt.getLog().error(e); return; }
            callback.accept(param);
        });
    }
}

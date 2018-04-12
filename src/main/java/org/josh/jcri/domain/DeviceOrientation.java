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
@ParametersAreNonnullByDefault public class DeviceOrientation extends DomainBase {
    public DeviceOrientation(EventCenter evt, WebSocket ws) { super(evt, ws); }
    /**Clears the overridden Device Orientation.*/
    public ClearDeviceOrientationOverrideParameter clearDeviceOrientationOverride() { final ClearDeviceOrientationOverrideParameter v = new ClearDeviceOrientationOverrideParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of clearDeviceOrientationOverride.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ClearDeviceOrientationOverrideParameter extends CommandBase {
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
        public ClearDeviceOrientationOverrideParameter() {}
        public CompletableFuture<ClearDeviceOrientationOverrideResult> call() {
            return super.call("DeviceOrientation.clearDeviceOrientationOverride", ClearDeviceOrientationOverrideResult.class,
                (code, msg)->new ClearDeviceOrientationOverrideResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<ClearDeviceOrientationOverrideResult> call(Executor exec) {
            return super.call("DeviceOrientation.clearDeviceOrientationOverride", ClearDeviceOrientationOverrideResult.class,
                (code, msg)->new ClearDeviceOrientationOverrideResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of clearDeviceOrientationOverride.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ClearDeviceOrientationOverrideResult extends ResultBase {
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
        public ClearDeviceOrientationOverrideResult() { super(); }
        public ClearDeviceOrientationOverrideResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Overrides the Device Orientation.*/
    public SetDeviceOrientationOverrideParameter setDeviceOrientationOverride() { final SetDeviceOrientationOverrideParameter v = new SetDeviceOrientationOverrideParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setDeviceOrientationOverride.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetDeviceOrientationOverrideParameter extends CommandBase {
        /**Mock alpha*/
        private Double alpha;
        /**Mock beta*/
        private Double beta;
        /**Mock gamma*/
        private Double gamma;
        public final SetDeviceOrientationOverrideParameter alpha(Double alpha) { this.alpha = alpha; return this; }
        public final SetDeviceOrientationOverrideParameter setAlpha(Double alpha) { return alpha(alpha); }
        public final Double alpha() { return alpha; }
        public final Double getAlpha() { return alpha(); }
        public final SetDeviceOrientationOverrideParameter beta(Double beta) { this.beta = beta; return this; }
        public final SetDeviceOrientationOverrideParameter setBeta(Double beta) { return beta(beta); }
        public final Double beta() { return beta; }
        public final Double getBeta() { return beta(); }
        public final SetDeviceOrientationOverrideParameter gamma(Double gamma) { this.gamma = gamma; return this; }
        public final SetDeviceOrientationOverrideParameter setGamma(Double gamma) { return gamma(gamma); }
        public final Double gamma() { return gamma; }
        public final Double getGamma() { return gamma(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (alpha == null) throw new IllegalArgumentException("DeviceOrientation.SetDeviceOrientationOverrideParameter.alpha is necessary field.");
            if (beta == null) throw new IllegalArgumentException("DeviceOrientation.SetDeviceOrientationOverrideParameter.beta is necessary field.");
            if (gamma == null) throw new IllegalArgumentException("DeviceOrientation.SetDeviceOrientationOverrideParameter.gamma is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"alpha\":").append(alpha);
            strBuilder.append(",\"beta\":").append(beta);
            strBuilder.append(",\"gamma\":").append(gamma);
            strBuilder.append('}');
            return strBuilder;
        }
        public SetDeviceOrientationOverrideParameter() {}
        public SetDeviceOrientationOverrideParameter(
            @JsonProperty("alpha")Double alpha,
            @JsonProperty("beta")Double beta,
            @JsonProperty("gamma")Double gamma
        ) {
            this();
            this.alpha = alpha;
            this.beta = beta;
            this.gamma = gamma;
        }
        public CompletableFuture<SetDeviceOrientationOverrideResult> call() {
            return super.call("DeviceOrientation.setDeviceOrientationOverride", SetDeviceOrientationOverrideResult.class,
                (code, msg)->new SetDeviceOrientationOverrideResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetDeviceOrientationOverrideResult> call(Executor exec) {
            return super.call("DeviceOrientation.setDeviceOrientationOverride", SetDeviceOrientationOverrideResult.class,
                (code, msg)->new SetDeviceOrientationOverrideResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setDeviceOrientationOverride.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetDeviceOrientationOverrideResult extends ResultBase {
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
        public SetDeviceOrientationOverrideResult() { super(); }
        public SetDeviceOrientationOverrideResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
}

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

/**This domain emulates different environments for the page.
<p>From: browser_protocol.json</p>
<p>Protocol version: 1.3</p>
 @see DOM
 @see Page
 @see Runtime
 @author Joshua*/
@ParametersAreNonnullByDefault public class Emulation extends DomainBase {
    public Emulation(EventCenter evt, WebSocket ws) { super(evt, ws); }

    /**Screen orientation.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ScreenOrientation implements CommonDomainType {
        /**Orientation type.*/
        @ParametersAreNonnullByDefault public enum Type implements CommonDomainType {
            PortraitPrimary("portraitPrimary"),
            PortraitSecondary("portraitSecondary"),
            LandscapePrimary("landscapePrimary"),
            LandscapeSecondary("landscapeSecondary");

            private final String _value;
            private static final Map<String, Type> _Lookup;
            static {
                Map<String, Type> m = new HashMap<>();
                for(Type v: values()) m.put(v.toString(), v);
                _Lookup = Collections.unmodifiableMap(m);
            }
            /**Convert string representation to type.
             @throws IllegalArgumentException if given value cannot convert to enum type. */
            @JsonCreator public static Type of(String value) {
                Type v = _Lookup.get(value.toLowerCase());
                return v != null ? v : Enum.valueOf(Type.class, value);
            }
            Type(String value) { _value = value; }
            /**Check if parameter fields of method are all valid. */
            @Override public void check() throws IllegalArgumentException { /* Need not check */ }
            /**Convert method parameter object into json string and append into string builder.
             @return string builder instance that is given in parameter (for chaining coding style use.) */
            @Override public StringBuilder toJson(StringBuilder strBuilder) { return strBuilder.append(toString()); }
            @Override public String toString() { return _value; }
        }
        private Type type;
        /**Orientation angle.*/
        private Integer angle;
        public final ScreenOrientation type(Type type) { this.type = type; return this; }
        public final ScreenOrientation setType(Type type) { return type(type); }
        public final Type type() { return type; }
        public final Type getType() { return type(); }
        public final ScreenOrientation angle(Integer angle) { this.angle = angle; return this; }
        public final ScreenOrientation setAngle(Integer angle) { return angle(angle); }
        public final Integer angle() { return angle; }
        public final Integer getAngle() { return angle(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (type == null) throw new IllegalArgumentException("Emulation.ScreenOrientation.type is necessary field.");
            if (angle == null) throw new IllegalArgumentException("Emulation.ScreenOrientation.angle is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"type\":").append(type);
            strBuilder.append(",\"angle\":").append(angle);
            strBuilder.append('}');
            return strBuilder;
        }
        public ScreenOrientation() {}
        public ScreenOrientation(
            @JsonProperty("type")Type type,
            @JsonProperty("angle")Integer angle
        ) {
            this.type = type;
            this.angle = angle;
        }
    }

    /**advance: If the scheduler runs out of immediate work, the virtual time base may fast forward to
allow the next delayed task (if any) to run; pause: The virtual time base may not advance;
pauseIfNetworkFetchesPending: The virtual time base may not advance if there are any pending
resource fetches.
    <p><strong>Experimental.</strong></p>*/
    @ParametersAreNonnullByDefault public enum VirtualTimePolicy implements CommonDomainType {
        Advance("advance"),
        Pause("pause"),
        PauseIfNetworkFetchesPending("pauseIfNetworkFetchesPending");

        private final String _value;
        private static final Map<String, VirtualTimePolicy> _Lookup;
        static {
            Map<String, VirtualTimePolicy> m = new HashMap<>();
            for(VirtualTimePolicy v: values()) m.put(v.toString(), v);
            _Lookup = Collections.unmodifiableMap(m);
        }
        /**Convert string representation to type.
         @throws IllegalArgumentException if given value cannot convert to enum type. */
        @JsonCreator public static VirtualTimePolicy of(String value) {
            VirtualTimePolicy v = _Lookup.get(value.toLowerCase());
            return v != null ? v : Enum.valueOf(VirtualTimePolicy.class, value);
        }
        VirtualTimePolicy(String value) { _value = value; }
        /**Check if parameter fields of method are all valid. */
        @Override public void check() throws IllegalArgumentException { /* Need not check */ }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) { return strBuilder.append(toString()); }
        @Override public String toString() { return _value; }
    }
    /**Tells whether emulation is supported.*/
    public CanEmulateParameter canEmulate() { final CanEmulateParameter v = new CanEmulateParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of canEmulate.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class CanEmulateParameter extends CommandBase {
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
        public CanEmulateParameter() {}
        public CompletableFuture<CanEmulateResult> call() {
            return super.call("Emulation.canEmulate", CanEmulateResult.class,
                (code, msg)->new CanEmulateResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<CanEmulateResult> call(Executor exec) {
            return super.call("Emulation.canEmulate", CanEmulateResult.class,
                (code, msg)->new CanEmulateResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of canEmulate.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class CanEmulateResult extends ResultBase {
        /**True if emulation is supported.*/
        private final Boolean result;
        public final Boolean result() { return result; }
        public final Boolean getResult() { return result(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"result\":").append(result);
            strBuilder.append('}');
            return strBuilder;
        }
        public CanEmulateResult(
            @JsonProperty("result")Boolean result
        ) {
            this.result = result;
        }
        public CanEmulateResult(ResultBase.FailedResult e) {
            super(e);
            result = null;
        }
    }
    /**Clears the overriden device metrics.*/
    public ClearDeviceMetricsOverrideParameter clearDeviceMetricsOverride() { final ClearDeviceMetricsOverrideParameter v = new ClearDeviceMetricsOverrideParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of clearDeviceMetricsOverride.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ClearDeviceMetricsOverrideParameter extends CommandBase {
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
        public ClearDeviceMetricsOverrideParameter() {}
        public CompletableFuture<ClearDeviceMetricsOverrideResult> call() {
            return super.call("Emulation.clearDeviceMetricsOverride", ClearDeviceMetricsOverrideResult.class,
                (code, msg)->new ClearDeviceMetricsOverrideResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<ClearDeviceMetricsOverrideResult> call(Executor exec) {
            return super.call("Emulation.clearDeviceMetricsOverride", ClearDeviceMetricsOverrideResult.class,
                (code, msg)->new ClearDeviceMetricsOverrideResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of clearDeviceMetricsOverride.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ClearDeviceMetricsOverrideResult extends ResultBase {
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
        public ClearDeviceMetricsOverrideResult() { super(); }
        public ClearDeviceMetricsOverrideResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Clears the overriden Geolocation Position and Error.*/
    public ClearGeolocationOverrideParameter clearGeolocationOverride() { final ClearGeolocationOverrideParameter v = new ClearGeolocationOverrideParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of clearGeolocationOverride.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ClearGeolocationOverrideParameter extends CommandBase {
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
        public ClearGeolocationOverrideParameter() {}
        public CompletableFuture<ClearGeolocationOverrideResult> call() {
            return super.call("Emulation.clearGeolocationOverride", ClearGeolocationOverrideResult.class,
                (code, msg)->new ClearGeolocationOverrideResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<ClearGeolocationOverrideResult> call(Executor exec) {
            return super.call("Emulation.clearGeolocationOverride", ClearGeolocationOverrideResult.class,
                (code, msg)->new ClearGeolocationOverrideResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of clearGeolocationOverride.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ClearGeolocationOverrideResult extends ResultBase {
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
        public ClearGeolocationOverrideResult() { super(); }
        public ClearGeolocationOverrideResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Requests that page scale factor is reset to initial values.
    <p><strong>Experimental.</strong></p>*/
    public ResetPageScaleFactorParameter resetPageScaleFactor() { final ResetPageScaleFactorParameter v = new ResetPageScaleFactorParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of resetPageScaleFactor.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ResetPageScaleFactorParameter extends CommandBase {
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
        public ResetPageScaleFactorParameter() {}
        public CompletableFuture<ResetPageScaleFactorResult> call() {
            return super.call("Emulation.resetPageScaleFactor", ResetPageScaleFactorResult.class,
                (code, msg)->new ResetPageScaleFactorResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<ResetPageScaleFactorResult> call(Executor exec) {
            return super.call("Emulation.resetPageScaleFactor", ResetPageScaleFactorResult.class,
                (code, msg)->new ResetPageScaleFactorResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of resetPageScaleFactor.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ResetPageScaleFactorResult extends ResultBase {
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
        public ResetPageScaleFactorResult() { super(); }
        public ResetPageScaleFactorResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Enables CPU throttling to emulate slow CPUs.
    <p><strong>Experimental.</strong></p>*/
    public SetCPUThrottlingRateParameter setCPUThrottlingRate() { final SetCPUThrottlingRateParameter v = new SetCPUThrottlingRateParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setCPUThrottlingRate.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetCPUThrottlingRateParameter extends CommandBase {
        /**Throttling rate as a slowdown factor (1 is no throttle, 2 is 2x slowdown, etc).*/
        private Double rate;
        public final SetCPUThrottlingRateParameter rate(Double rate) { this.rate = rate; return this; }
        public final SetCPUThrottlingRateParameter setRate(Double rate) { return rate(rate); }
        public final Double rate() { return rate; }
        public final Double getRate() { return rate(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (rate == null) throw new IllegalArgumentException("Emulation.SetCPUThrottlingRateParameter.rate is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"rate\":").append(rate);
            strBuilder.append('}');
            return strBuilder;
        }
        public SetCPUThrottlingRateParameter() {}
        public SetCPUThrottlingRateParameter(
            @JsonProperty("rate")Double rate
        ) {
            this();
            this.rate = rate;
        }
        public CompletableFuture<SetCPUThrottlingRateResult> call() {
            return super.call("Emulation.setCPUThrottlingRate", SetCPUThrottlingRateResult.class,
                (code, msg)->new SetCPUThrottlingRateResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetCPUThrottlingRateResult> call(Executor exec) {
            return super.call("Emulation.setCPUThrottlingRate", SetCPUThrottlingRateResult.class,
                (code, msg)->new SetCPUThrottlingRateResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setCPUThrottlingRate.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetCPUThrottlingRateResult extends ResultBase {
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
        public SetCPUThrottlingRateResult() { super(); }
        public SetCPUThrottlingRateResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Sets or clears an override of the default background color of the frame. This override is used
if the content does not specify one.*/
    public SetDefaultBackgroundColorOverrideParameter setDefaultBackgroundColorOverride() { final SetDefaultBackgroundColorOverrideParameter v = new SetDefaultBackgroundColorOverrideParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setDefaultBackgroundColorOverride.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetDefaultBackgroundColorOverrideParameter extends CommandBase {
        /**RGBA of the default background color. If not specified, any existing override will be
cleared.
        <em>Optional.</em>*/
        private DOM.RGBA color;
        public final SetDefaultBackgroundColorOverrideParameter color(@Nullable DOM.RGBA color) { this.color = color; return this; }
        public final SetDefaultBackgroundColorOverrideParameter optColor(@Nullable DOM.RGBA color) { return color(color); }
        public final DOM.RGBA color() { return color; }
        public final DOM.RGBA getColor() { return color(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            if (color != null) color.toJson(strBuilder.append("\"color\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public SetDefaultBackgroundColorOverrideParameter() {}
        public SetDefaultBackgroundColorOverrideParameter(
            @Nullable @JsonProperty("color")DOM.RGBA color
        ) {
            this();
            this.color = color;
        }
        public CompletableFuture<SetDefaultBackgroundColorOverrideResult> call() {
            return super.call("Emulation.setDefaultBackgroundColorOverride", SetDefaultBackgroundColorOverrideResult.class,
                (code, msg)->new SetDefaultBackgroundColorOverrideResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetDefaultBackgroundColorOverrideResult> call(Executor exec) {
            return super.call("Emulation.setDefaultBackgroundColorOverride", SetDefaultBackgroundColorOverrideResult.class,
                (code, msg)->new SetDefaultBackgroundColorOverrideResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setDefaultBackgroundColorOverride.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetDefaultBackgroundColorOverrideResult extends ResultBase {
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
        public SetDefaultBackgroundColorOverrideResult() { super(); }
        public SetDefaultBackgroundColorOverrideResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Overrides the values of device screen dimensions (window.screen.width, window.screen.height,
window.innerWidth, window.innerHeight, and "device-width"/"device-height"-related CSS media
query results).*/
    public SetDeviceMetricsOverrideParameter setDeviceMetricsOverride() { final SetDeviceMetricsOverrideParameter v = new SetDeviceMetricsOverrideParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setDeviceMetricsOverride.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetDeviceMetricsOverrideParameter extends CommandBase {
        /**Overriding width value in pixels (minimum 0, maximum 10000000). 0 disables the override.*/
        private Integer width;
        /**Overriding height value in pixels (minimum 0, maximum 10000000). 0 disables the override.*/
        private Integer height;
        /**Overriding device scale factor value. 0 disables the override.*/
        private Double deviceScaleFactor;
        /**Whether to emulate mobile device. This includes viewport meta tag, overlay scrollbars, text
autosizing and more.*/
        private Boolean mobile;
        /**Scale to apply to resulting view image.
        <em>Optional.</em>
        <p><strong>Experimental.</strong></p>*/
        private Double scale;
        /**Overriding screen width value in pixels (minimum 0, maximum 10000000).
        <em>Optional.</em>
        <p><strong>Experimental.</strong></p>*/
        private Integer screenWidth;
        /**Overriding screen height value in pixels (minimum 0, maximum 10000000).
        <em>Optional.</em>
        <p><strong>Experimental.</strong></p>*/
        private Integer screenHeight;
        /**Overriding view X position on screen in pixels (minimum 0, maximum 10000000).
        <em>Optional.</em>
        <p><strong>Experimental.</strong></p>*/
        private Integer positionX;
        /**Overriding view Y position on screen in pixels (minimum 0, maximum 10000000).
        <em>Optional.</em>
        <p><strong>Experimental.</strong></p>*/
        private Integer positionY;
        /**Do not set visible view size, rely upon explicit setVisibleSize call.
        <em>Optional.</em>
        <p><strong>Experimental.</strong></p>*/
        private Boolean dontSetVisibleSize;
        /**Screen orientation override.
        <em>Optional.</em>*/
        private ScreenOrientation screenOrientation;
        /**If set, the visible area of the page will be overridden to this viewport. This viewport
change is not observed by the page, e.g. viewport-relative elements do not change positions.
        <em>Optional.</em>
        <p><strong>Experimental.</strong></p>*/
        private Page.Viewport viewport;
        public final SetDeviceMetricsOverrideParameter width(Integer width) { this.width = width; return this; }
        public final SetDeviceMetricsOverrideParameter setWidth(Integer width) { return width(width); }
        public final Integer width() { return width; }
        public final Integer getWidth() { return width(); }
        public final SetDeviceMetricsOverrideParameter height(Integer height) { this.height = height; return this; }
        public final SetDeviceMetricsOverrideParameter setHeight(Integer height) { return height(height); }
        public final Integer height() { return height; }
        public final Integer getHeight() { return height(); }
        public final SetDeviceMetricsOverrideParameter deviceScaleFactor(Double deviceScaleFactor) { this.deviceScaleFactor = deviceScaleFactor; return this; }
        public final SetDeviceMetricsOverrideParameter setDeviceScaleFactor(Double deviceScaleFactor) { return deviceScaleFactor(deviceScaleFactor); }
        public final Double deviceScaleFactor() { return deviceScaleFactor; }
        public final Double getDeviceScaleFactor() { return deviceScaleFactor(); }
        public final SetDeviceMetricsOverrideParameter mobile(Boolean mobile) { this.mobile = mobile; return this; }
        public final SetDeviceMetricsOverrideParameter setMobile(Boolean mobile) { return mobile(mobile); }
        public final Boolean mobile() { return mobile; }
        public final Boolean getMobile() { return mobile(); }
        public final SetDeviceMetricsOverrideParameter scale(@Nullable Double scale) { this.scale = scale; return this; }
        public final SetDeviceMetricsOverrideParameter optScale(@Nullable Double scale) { return scale(scale); }
        public final Double scale() { return scale; }
        public final Double getScale() { return scale(); }
        public final SetDeviceMetricsOverrideParameter screenWidth(@Nullable Integer screenWidth) { this.screenWidth = screenWidth; return this; }
        public final SetDeviceMetricsOverrideParameter optScreenWidth(@Nullable Integer screenWidth) { return screenWidth(screenWidth); }
        public final Integer screenWidth() { return screenWidth; }
        public final Integer getScreenWidth() { return screenWidth(); }
        public final SetDeviceMetricsOverrideParameter screenHeight(@Nullable Integer screenHeight) { this.screenHeight = screenHeight; return this; }
        public final SetDeviceMetricsOverrideParameter optScreenHeight(@Nullable Integer screenHeight) { return screenHeight(screenHeight); }
        public final Integer screenHeight() { return screenHeight; }
        public final Integer getScreenHeight() { return screenHeight(); }
        public final SetDeviceMetricsOverrideParameter positionX(@Nullable Integer positionX) { this.positionX = positionX; return this; }
        public final SetDeviceMetricsOverrideParameter optPositionX(@Nullable Integer positionX) { return positionX(positionX); }
        public final Integer positionX() { return positionX; }
        public final Integer getPositionX() { return positionX(); }
        public final SetDeviceMetricsOverrideParameter positionY(@Nullable Integer positionY) { this.positionY = positionY; return this; }
        public final SetDeviceMetricsOverrideParameter optPositionY(@Nullable Integer positionY) { return positionY(positionY); }
        public final Integer positionY() { return positionY; }
        public final Integer getPositionY() { return positionY(); }
        public final SetDeviceMetricsOverrideParameter dontSetVisibleSize(@Nullable Boolean dontSetVisibleSize) { this.dontSetVisibleSize = dontSetVisibleSize; return this; }
        public final SetDeviceMetricsOverrideParameter optDontSetVisibleSize(@Nullable Boolean dontSetVisibleSize) { return dontSetVisibleSize(dontSetVisibleSize); }
        public final Boolean dontSetVisibleSize() { return dontSetVisibleSize; }
        public final Boolean getDontSetVisibleSize() { return dontSetVisibleSize(); }
        public final SetDeviceMetricsOverrideParameter screenOrientation(@Nullable ScreenOrientation screenOrientation) { this.screenOrientation = screenOrientation; return this; }
        public final SetDeviceMetricsOverrideParameter optScreenOrientation(@Nullable ScreenOrientation screenOrientation) { return screenOrientation(screenOrientation); }
        public final ScreenOrientation screenOrientation() { return screenOrientation; }
        public final ScreenOrientation getScreenOrientation() { return screenOrientation(); }
        public final SetDeviceMetricsOverrideParameter viewport(@Nullable Page.Viewport viewport) { this.viewport = viewport; return this; }
        public final SetDeviceMetricsOverrideParameter optViewport(@Nullable Page.Viewport viewport) { return viewport(viewport); }
        public final Page.Viewport viewport() { return viewport; }
        public final Page.Viewport getViewport() { return viewport(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (width == null) throw new IllegalArgumentException("Emulation.SetDeviceMetricsOverrideParameter.width is necessary field.");
            if (height == null) throw new IllegalArgumentException("Emulation.SetDeviceMetricsOverrideParameter.height is necessary field.");
            if (deviceScaleFactor == null) throw new IllegalArgumentException("Emulation.SetDeviceMetricsOverrideParameter.deviceScaleFactor is necessary field.");
            if (mobile == null) throw new IllegalArgumentException("Emulation.SetDeviceMetricsOverrideParameter.mobile is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"width\":").append(width);
            strBuilder.append(",\"height\":").append(height);
            strBuilder.append(",\"deviceScaleFactor\":").append(deviceScaleFactor);
            strBuilder.append(",\"mobile\":").append(mobile);
            if (scale != null) strBuilder.append(",\"scale\":").append(scale);
            if (screenWidth != null) strBuilder.append(",\"screenWidth\":").append(screenWidth);
            if (screenHeight != null) strBuilder.append(",\"screenHeight\":").append(screenHeight);
            if (positionX != null) strBuilder.append(",\"positionX\":").append(positionX);
            if (positionY != null) strBuilder.append(",\"positionY\":").append(positionY);
            if (dontSetVisibleSize != null) strBuilder.append(",\"dontSetVisibleSize\":").append(dontSetVisibleSize);
            if (screenOrientation != null) screenOrientation.toJson(strBuilder.append(",\"screenOrientation\":"));
            if (viewport != null) viewport.toJson(strBuilder.append(",\"viewport\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public SetDeviceMetricsOverrideParameter() {}
        public SetDeviceMetricsOverrideParameter(
            @JsonProperty("width")Integer width,
            @JsonProperty("height")Integer height,
            @JsonProperty("deviceScaleFactor")Double deviceScaleFactor,
            @JsonProperty("mobile")Boolean mobile,
            @Nullable @JsonProperty("scale")Double scale,
            @Nullable @JsonProperty("screenWidth")Integer screenWidth,
            @Nullable @JsonProperty("screenHeight")Integer screenHeight,
            @Nullable @JsonProperty("positionX")Integer positionX,
            @Nullable @JsonProperty("positionY")Integer positionY,
            @Nullable @JsonProperty("dontSetVisibleSize")Boolean dontSetVisibleSize,
            @Nullable @JsonProperty("screenOrientation")ScreenOrientation screenOrientation,
            @Nullable @JsonProperty("viewport")Page.Viewport viewport
        ) {
            this();
            this.width = width;
            this.height = height;
            this.deviceScaleFactor = deviceScaleFactor;
            this.mobile = mobile;
            this.scale = scale;
            this.screenWidth = screenWidth;
            this.screenHeight = screenHeight;
            this.positionX = positionX;
            this.positionY = positionY;
            this.dontSetVisibleSize = dontSetVisibleSize;
            this.screenOrientation = screenOrientation;
            this.viewport = viewport;
        }
        public CompletableFuture<SetDeviceMetricsOverrideResult> call() {
            return super.call("Emulation.setDeviceMetricsOverride", SetDeviceMetricsOverrideResult.class,
                (code, msg)->new SetDeviceMetricsOverrideResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetDeviceMetricsOverrideResult> call(Executor exec) {
            return super.call("Emulation.setDeviceMetricsOverride", SetDeviceMetricsOverrideResult.class,
                (code, msg)->new SetDeviceMetricsOverrideResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setDeviceMetricsOverride.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetDeviceMetricsOverrideResult extends ResultBase {
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
        public SetDeviceMetricsOverrideResult() { super(); }
        public SetDeviceMetricsOverrideResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**&lt;No document in protocol.&gt;
    <p><strong>Experimental.</strong></p>*/
    public SetEmitTouchEventsForMouseParameter setEmitTouchEventsForMouse() { final SetEmitTouchEventsForMouseParameter v = new SetEmitTouchEventsForMouseParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setEmitTouchEventsForMouse.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetEmitTouchEventsForMouseParameter extends CommandBase {
        /**Whether touch emulation based on mouse input should be enabled.*/
        private Boolean enabled;
        /**Touch/gesture events configuration. Default: current platform.
        <em>Optional.</em>*/
        @ParametersAreNonnullByDefault public enum Configuration implements CommonDomainType {
            Mobile("mobile"),
            Desktop("desktop");

            private final String _value;
            private static final Map<String, Configuration> _Lookup;
            static {
                Map<String, Configuration> m = new HashMap<>();
                for(Configuration v: values()) m.put(v.toString(), v);
                _Lookup = Collections.unmodifiableMap(m);
            }
            /**Convert string representation to type.
             @throws IllegalArgumentException if given value cannot convert to enum type. */
            @JsonCreator public static Configuration of(String value) {
                Configuration v = _Lookup.get(value.toLowerCase());
                return v != null ? v : Enum.valueOf(Configuration.class, value);
            }
            Configuration(String value) { _value = value; }
            /**Check if parameter fields of method are all valid. */
            @Override public void check() throws IllegalArgumentException { /* Need not check */ }
            /**Convert method parameter object into json string and append into string builder.
             @return string builder instance that is given in parameter (for chaining coding style use.) */
            @Override public StringBuilder toJson(StringBuilder strBuilder) { return strBuilder.append(toString()); }
            @Override public String toString() { return _value; }
        }
        private Configuration configuration;
        public final SetEmitTouchEventsForMouseParameter enabled(Boolean enabled) { this.enabled = enabled; return this; }
        public final SetEmitTouchEventsForMouseParameter setEnabled(Boolean enabled) { return enabled(enabled); }
        public final Boolean enabled() { return enabled; }
        public final Boolean getEnabled() { return enabled(); }
        public final SetEmitTouchEventsForMouseParameter configuration(@Nullable Configuration configuration) { this.configuration = configuration; return this; }
        public final SetEmitTouchEventsForMouseParameter optConfiguration(@Nullable Configuration configuration) { return configuration(configuration); }
        public final Configuration configuration() { return configuration; }
        public final Configuration getConfiguration() { return configuration(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (enabled == null) throw new IllegalArgumentException("Emulation.SetEmitTouchEventsForMouseParameter.enabled is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"enabled\":").append(enabled);
            if (configuration != null) strBuilder.append(",\"configuration\":").append(configuration);
            strBuilder.append('}');
            return strBuilder;
        }
        public SetEmitTouchEventsForMouseParameter() {}
        public SetEmitTouchEventsForMouseParameter(
            @JsonProperty("enabled")Boolean enabled,
            @Nullable @JsonProperty("configuration")Configuration configuration
        ) {
            this();
            this.enabled = enabled;
            this.configuration = configuration;
        }
        public CompletableFuture<SetEmitTouchEventsForMouseResult> call() {
            return super.call("Emulation.setEmitTouchEventsForMouse", SetEmitTouchEventsForMouseResult.class,
                (code, msg)->new SetEmitTouchEventsForMouseResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetEmitTouchEventsForMouseResult> call(Executor exec) {
            return super.call("Emulation.setEmitTouchEventsForMouse", SetEmitTouchEventsForMouseResult.class,
                (code, msg)->new SetEmitTouchEventsForMouseResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setEmitTouchEventsForMouse.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetEmitTouchEventsForMouseResult extends ResultBase {
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
        public SetEmitTouchEventsForMouseResult() { super(); }
        public SetEmitTouchEventsForMouseResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Emulates the given media for CSS media queries.*/
    public SetEmulatedMediaParameter setEmulatedMedia() { final SetEmulatedMediaParameter v = new SetEmulatedMediaParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setEmulatedMedia.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetEmulatedMediaParameter extends CommandBase {
        /**Media type to emulate. Empty string disables the override.*/
        private String media;
        public final SetEmulatedMediaParameter media(String media) { this.media = media; return this; }
        public final SetEmulatedMediaParameter setMedia(String media) { return media(media); }
        public final String media() { return media; }
        public final String getMedia() { return media(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (media == null) throw new IllegalArgumentException("Emulation.SetEmulatedMediaParameter.media is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"media\":").append('"').append(DomainBase.escapeQuote(media)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public SetEmulatedMediaParameter() {}
        public SetEmulatedMediaParameter(
            @JsonProperty("media")String media
        ) {
            this();
            this.media = media;
        }
        public CompletableFuture<SetEmulatedMediaResult> call() {
            return super.call("Emulation.setEmulatedMedia", SetEmulatedMediaResult.class,
                (code, msg)->new SetEmulatedMediaResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetEmulatedMediaResult> call(Executor exec) {
            return super.call("Emulation.setEmulatedMedia", SetEmulatedMediaResult.class,
                (code, msg)->new SetEmulatedMediaResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setEmulatedMedia.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetEmulatedMediaResult extends ResultBase {
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
        public SetEmulatedMediaResult() { super(); }
        public SetEmulatedMediaResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Overrides the Geolocation Position or Error. Omitting any of the parameters emulates position
unavailable.*/
    public SetGeolocationOverrideParameter setGeolocationOverride() { final SetGeolocationOverrideParameter v = new SetGeolocationOverrideParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setGeolocationOverride.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetGeolocationOverrideParameter extends CommandBase {
        /**Mock latitude
        <em>Optional.</em>*/
        private Double latitude;
        /**Mock longitude
        <em>Optional.</em>*/
        private Double longitude;
        /**Mock accuracy
        <em>Optional.</em>*/
        private Double accuracy;
        public final SetGeolocationOverrideParameter latitude(@Nullable Double latitude) { this.latitude = latitude; return this; }
        public final SetGeolocationOverrideParameter optLatitude(@Nullable Double latitude) { return latitude(latitude); }
        public final Double latitude() { return latitude; }
        public final Double getLatitude() { return latitude(); }
        public final SetGeolocationOverrideParameter longitude(@Nullable Double longitude) { this.longitude = longitude; return this; }
        public final SetGeolocationOverrideParameter optLongitude(@Nullable Double longitude) { return longitude(longitude); }
        public final Double longitude() { return longitude; }
        public final Double getLongitude() { return longitude(); }
        public final SetGeolocationOverrideParameter accuracy(@Nullable Double accuracy) { this.accuracy = accuracy; return this; }
        public final SetGeolocationOverrideParameter optAccuracy(@Nullable Double accuracy) { return accuracy(accuracy); }
        public final Double accuracy() { return accuracy; }
        public final Double getAccuracy() { return accuracy(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            if (latitude != null) strBuilder.append("\"latitude\":").append(latitude);
            if (longitude != null) strBuilder.append(",\"longitude\":").append(longitude);
            if (accuracy != null) strBuilder.append(",\"accuracy\":").append(accuracy);
            strBuilder.append('}');
            return strBuilder;
        }
        public SetGeolocationOverrideParameter() {}
        public SetGeolocationOverrideParameter(
            @Nullable @JsonProperty("latitude")Double latitude,
            @Nullable @JsonProperty("longitude")Double longitude,
            @Nullable @JsonProperty("accuracy")Double accuracy
        ) {
            this();
            this.latitude = latitude;
            this.longitude = longitude;
            this.accuracy = accuracy;
        }
        public CompletableFuture<SetGeolocationOverrideResult> call() {
            return super.call("Emulation.setGeolocationOverride", SetGeolocationOverrideResult.class,
                (code, msg)->new SetGeolocationOverrideResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetGeolocationOverrideResult> call(Executor exec) {
            return super.call("Emulation.setGeolocationOverride", SetGeolocationOverrideResult.class,
                (code, msg)->new SetGeolocationOverrideResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setGeolocationOverride.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetGeolocationOverrideResult extends ResultBase {
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
        public SetGeolocationOverrideResult() { super(); }
        public SetGeolocationOverrideResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Overrides value returned by the javascript navigator object.
    <p><strong>Experimental.</strong></p>*/
    public SetNavigatorOverridesParameter setNavigatorOverrides() { final SetNavigatorOverridesParameter v = new SetNavigatorOverridesParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setNavigatorOverrides.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetNavigatorOverridesParameter extends CommandBase {
        /**The platform navigator.platform should return.*/
        private String platform;
        public final SetNavigatorOverridesParameter platform(String platform) { this.platform = platform; return this; }
        public final SetNavigatorOverridesParameter setPlatform(String platform) { return platform(platform); }
        public final String platform() { return platform; }
        public final String getPlatform() { return platform(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (platform == null) throw new IllegalArgumentException("Emulation.SetNavigatorOverridesParameter.platform is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"platform\":").append('"').append(DomainBase.escapeQuote(platform)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public SetNavigatorOverridesParameter() {}
        public SetNavigatorOverridesParameter(
            @JsonProperty("platform")String platform
        ) {
            this();
            this.platform = platform;
        }
        public CompletableFuture<SetNavigatorOverridesResult> call() {
            return super.call("Emulation.setNavigatorOverrides", SetNavigatorOverridesResult.class,
                (code, msg)->new SetNavigatorOverridesResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetNavigatorOverridesResult> call(Executor exec) {
            return super.call("Emulation.setNavigatorOverrides", SetNavigatorOverridesResult.class,
                (code, msg)->new SetNavigatorOverridesResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setNavigatorOverrides.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetNavigatorOverridesResult extends ResultBase {
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
        public SetNavigatorOverridesResult() { super(); }
        public SetNavigatorOverridesResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Sets a specified page scale factor.
    <p><strong>Experimental.</strong></p>*/
    public SetPageScaleFactorParameter setPageScaleFactor() { final SetPageScaleFactorParameter v = new SetPageScaleFactorParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setPageScaleFactor.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetPageScaleFactorParameter extends CommandBase {
        /**Page scale factor.*/
        private Double pageScaleFactor;
        public final SetPageScaleFactorParameter pageScaleFactor(Double pageScaleFactor) { this.pageScaleFactor = pageScaleFactor; return this; }
        public final SetPageScaleFactorParameter setPageScaleFactor(Double pageScaleFactor) { return pageScaleFactor(pageScaleFactor); }
        public final Double pageScaleFactor() { return pageScaleFactor; }
        public final Double getPageScaleFactor() { return pageScaleFactor(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (pageScaleFactor == null) throw new IllegalArgumentException("Emulation.SetPageScaleFactorParameter.pageScaleFactor is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"pageScaleFactor\":").append(pageScaleFactor);
            strBuilder.append('}');
            return strBuilder;
        }
        public SetPageScaleFactorParameter() {}
        public SetPageScaleFactorParameter(
            @JsonProperty("pageScaleFactor")Double pageScaleFactor
        ) {
            this();
            this.pageScaleFactor = pageScaleFactor;
        }
        public CompletableFuture<SetPageScaleFactorResult> call() {
            return super.call("Emulation.setPageScaleFactor", SetPageScaleFactorResult.class,
                (code, msg)->new SetPageScaleFactorResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetPageScaleFactorResult> call(Executor exec) {
            return super.call("Emulation.setPageScaleFactor", SetPageScaleFactorResult.class,
                (code, msg)->new SetPageScaleFactorResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setPageScaleFactor.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetPageScaleFactorResult extends ResultBase {
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
        public SetPageScaleFactorResult() { super(); }
        public SetPageScaleFactorResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Switches script execution in the page.*/
    public SetScriptExecutionDisabledParameter setScriptExecutionDisabled() { final SetScriptExecutionDisabledParameter v = new SetScriptExecutionDisabledParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setScriptExecutionDisabled.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetScriptExecutionDisabledParameter extends CommandBase {
        /**Whether script execution should be disabled in the page.*/
        private Boolean value;
        public final SetScriptExecutionDisabledParameter value(Boolean value) { this.value = value; return this; }
        public final SetScriptExecutionDisabledParameter setValue(Boolean value) { return value(value); }
        public final Boolean value() { return value; }
        public final Boolean getValue() { return value(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (value == null) throw new IllegalArgumentException("Emulation.SetScriptExecutionDisabledParameter.value is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"value\":").append(value);
            strBuilder.append('}');
            return strBuilder;
        }
        public SetScriptExecutionDisabledParameter() {}
        public SetScriptExecutionDisabledParameter(
            @JsonProperty("value")Boolean value
        ) {
            this();
            this.value = value;
        }
        public CompletableFuture<SetScriptExecutionDisabledResult> call() {
            return super.call("Emulation.setScriptExecutionDisabled", SetScriptExecutionDisabledResult.class,
                (code, msg)->new SetScriptExecutionDisabledResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetScriptExecutionDisabledResult> call(Executor exec) {
            return super.call("Emulation.setScriptExecutionDisabled", SetScriptExecutionDisabledResult.class,
                (code, msg)->new SetScriptExecutionDisabledResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setScriptExecutionDisabled.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetScriptExecutionDisabledResult extends ResultBase {
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
        public SetScriptExecutionDisabledResult() { super(); }
        public SetScriptExecutionDisabledResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Enables touch on platforms which do not support them.*/
    public SetTouchEmulationEnabledParameter setTouchEmulationEnabled() { final SetTouchEmulationEnabledParameter v = new SetTouchEmulationEnabledParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setTouchEmulationEnabled.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetTouchEmulationEnabledParameter extends CommandBase {
        /**Whether the touch event emulation should be enabled.*/
        private Boolean enabled;
        /**Maximum touch points supported. Defaults to one.
        <em>Optional.</em>*/
        private Integer maxTouchPoints;
        public final SetTouchEmulationEnabledParameter enabled(Boolean enabled) { this.enabled = enabled; return this; }
        public final SetTouchEmulationEnabledParameter setEnabled(Boolean enabled) { return enabled(enabled); }
        public final Boolean enabled() { return enabled; }
        public final Boolean getEnabled() { return enabled(); }
        public final SetTouchEmulationEnabledParameter maxTouchPoints(@Nullable Integer maxTouchPoints) { this.maxTouchPoints = maxTouchPoints; return this; }
        public final SetTouchEmulationEnabledParameter optMaxTouchPoints(@Nullable Integer maxTouchPoints) { return maxTouchPoints(maxTouchPoints); }
        public final Integer maxTouchPoints() { return maxTouchPoints; }
        public final Integer getMaxTouchPoints() { return maxTouchPoints(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (enabled == null) throw new IllegalArgumentException("Emulation.SetTouchEmulationEnabledParameter.enabled is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"enabled\":").append(enabled);
            if (maxTouchPoints != null) strBuilder.append(",\"maxTouchPoints\":").append(maxTouchPoints);
            strBuilder.append('}');
            return strBuilder;
        }
        public SetTouchEmulationEnabledParameter() {}
        public SetTouchEmulationEnabledParameter(
            @JsonProperty("enabled")Boolean enabled,
            @Nullable @JsonProperty("maxTouchPoints")Integer maxTouchPoints
        ) {
            this();
            this.enabled = enabled;
            this.maxTouchPoints = maxTouchPoints;
        }
        public CompletableFuture<SetTouchEmulationEnabledResult> call() {
            return super.call("Emulation.setTouchEmulationEnabled", SetTouchEmulationEnabledResult.class,
                (code, msg)->new SetTouchEmulationEnabledResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetTouchEmulationEnabledResult> call(Executor exec) {
            return super.call("Emulation.setTouchEmulationEnabled", SetTouchEmulationEnabledResult.class,
                (code, msg)->new SetTouchEmulationEnabledResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setTouchEmulationEnabled.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetTouchEmulationEnabledResult extends ResultBase {
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
        public SetTouchEmulationEnabledResult() { super(); }
        public SetTouchEmulationEnabledResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Turns on virtual time for all frames (replacing real-time with a synthetic time source) and sets
the current virtual time policy.  Note this supersedes any previous time budget.
    <p><strong>Experimental.</strong></p>*/
    public SetVirtualTimePolicyParameter setVirtualTimePolicy() { final SetVirtualTimePolicyParameter v = new SetVirtualTimePolicyParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setVirtualTimePolicy.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetVirtualTimePolicyParameter extends CommandBase {
        /**&lt;No document in protocol.&gt;*/
        private VirtualTimePolicy policy;
        /**If set, after this many virtual milliseconds have elapsed virtual time will be paused and a
virtualTimeBudgetExpired event is sent.
        <em>Optional.</em>*/
        private Double budget;
        /**If set this specifies the maximum number of tasks that can be run before virtual is forced
forwards to prevent deadlock.
        <em>Optional.</em>*/
        private Integer maxVirtualTimeTaskStarvationCount;
        /**If set the virtual time policy change should be deferred until any frame starts navigating.
Note any previous deferred policy change is superseded.
        <em>Optional.</em>*/
        private Boolean waitForNavigation;
        public final SetVirtualTimePolicyParameter policy(VirtualTimePolicy policy) { this.policy = policy; return this; }
        public final SetVirtualTimePolicyParameter setPolicy(VirtualTimePolicy policy) { return policy(policy); }
        public final VirtualTimePolicy policy() { return policy; }
        public final VirtualTimePolicy getPolicy() { return policy(); }
        public final SetVirtualTimePolicyParameter budget(@Nullable Double budget) { this.budget = budget; return this; }
        public final SetVirtualTimePolicyParameter optBudget(@Nullable Double budget) { return budget(budget); }
        public final Double budget() { return budget; }
        public final Double getBudget() { return budget(); }
        public final SetVirtualTimePolicyParameter maxVirtualTimeTaskStarvationCount(@Nullable Integer maxVirtualTimeTaskStarvationCount) { this.maxVirtualTimeTaskStarvationCount = maxVirtualTimeTaskStarvationCount; return this; }
        public final SetVirtualTimePolicyParameter optMaxVirtualTimeTaskStarvationCount(@Nullable Integer maxVirtualTimeTaskStarvationCount) { return maxVirtualTimeTaskStarvationCount(maxVirtualTimeTaskStarvationCount); }
        public final Integer maxVirtualTimeTaskStarvationCount() { return maxVirtualTimeTaskStarvationCount; }
        public final Integer getMaxVirtualTimeTaskStarvationCount() { return maxVirtualTimeTaskStarvationCount(); }
        public final SetVirtualTimePolicyParameter waitForNavigation(@Nullable Boolean waitForNavigation) { this.waitForNavigation = waitForNavigation; return this; }
        public final SetVirtualTimePolicyParameter optWaitForNavigation(@Nullable Boolean waitForNavigation) { return waitForNavigation(waitForNavigation); }
        public final Boolean waitForNavigation() { return waitForNavigation; }
        public final Boolean getWaitForNavigation() { return waitForNavigation(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (policy == null) throw new IllegalArgumentException("Emulation.SetVirtualTimePolicyParameter.policy is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            policy.toJson(strBuilder.append("\"policy\":"));
            if (budget != null) strBuilder.append(",\"budget\":").append(budget);
            if (maxVirtualTimeTaskStarvationCount != null) strBuilder.append(",\"maxVirtualTimeTaskStarvationCount\":").append(maxVirtualTimeTaskStarvationCount);
            if (waitForNavigation != null) strBuilder.append(",\"waitForNavigation\":").append(waitForNavigation);
            strBuilder.append('}');
            return strBuilder;
        }
        public SetVirtualTimePolicyParameter() {}
        public SetVirtualTimePolicyParameter(
            @JsonProperty("policy")VirtualTimePolicy policy,
            @Nullable @JsonProperty("budget")Double budget,
            @Nullable @JsonProperty("maxVirtualTimeTaskStarvationCount")Integer maxVirtualTimeTaskStarvationCount,
            @Nullable @JsonProperty("waitForNavigation")Boolean waitForNavigation
        ) {
            this();
            this.policy = policy;
            this.budget = budget;
            this.maxVirtualTimeTaskStarvationCount = maxVirtualTimeTaskStarvationCount;
            this.waitForNavigation = waitForNavigation;
        }
        public CompletableFuture<SetVirtualTimePolicyResult> call() {
            return super.call("Emulation.setVirtualTimePolicy", SetVirtualTimePolicyResult.class,
                (code, msg)->new SetVirtualTimePolicyResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetVirtualTimePolicyResult> call(Executor exec) {
            return super.call("Emulation.setVirtualTimePolicy", SetVirtualTimePolicyResult.class,
                (code, msg)->new SetVirtualTimePolicyResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setVirtualTimePolicy.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetVirtualTimePolicyResult extends ResultBase {
        /**Absolute timestamp at which virtual time was first enabled (milliseconds since epoch).*/
        private final Runtime.Timestamp virtualTimeBase;
        public final Runtime.Timestamp virtualTimeBase() { return virtualTimeBase; }
        public final Runtime.Timestamp getVirtualTimeBase() { return virtualTimeBase(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            virtualTimeBase.toJson(strBuilder.append("\"virtualTimeBase\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public SetVirtualTimePolicyResult(
            @JsonProperty("virtualTimeBase")Runtime.Timestamp virtualTimeBase
        ) {
            this.virtualTimeBase = virtualTimeBase;
        }
        public SetVirtualTimePolicyResult(ResultBase.FailedResult e) {
            super(e);
            virtualTimeBase = null;
        }
    }
    /**Resizes the frame/viewport of the page. Note that this does not affect the frame's container
(e.g. browser window). Can be used to produce screenshots of the specified size. Not supported
on Android.
    <p><strong>Experimental.</strong></p>
    @Deprecated*/
    public SetVisibleSizeParameter setVisibleSize() { final SetVisibleSizeParameter v = new SetVisibleSizeParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setVisibleSize.
    <p><strong>Experimental.</strong></p>
    @Deprecated*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetVisibleSizeParameter extends CommandBase {
        /**Frame width (DIP).*/
        private Integer width;
        /**Frame height (DIP).*/
        private Integer height;
        public final SetVisibleSizeParameter width(Integer width) { this.width = width; return this; }
        public final SetVisibleSizeParameter setWidth(Integer width) { return width(width); }
        public final Integer width() { return width; }
        public final Integer getWidth() { return width(); }
        public final SetVisibleSizeParameter height(Integer height) { this.height = height; return this; }
        public final SetVisibleSizeParameter setHeight(Integer height) { return height(height); }
        public final Integer height() { return height; }
        public final Integer getHeight() { return height(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (width == null) throw new IllegalArgumentException("Emulation.SetVisibleSizeParameter.width is necessary field.");
            if (height == null) throw new IllegalArgumentException("Emulation.SetVisibleSizeParameter.height is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"width\":").append(width);
            strBuilder.append(",\"height\":").append(height);
            strBuilder.append('}');
            return strBuilder;
        }
        public SetVisibleSizeParameter() {}
        public SetVisibleSizeParameter(
            @JsonProperty("width")Integer width,
            @JsonProperty("height")Integer height
        ) {
            this();
            this.width = width;
            this.height = height;
        }
        public CompletableFuture<SetVisibleSizeResult> call() {
            return super.call("Emulation.setVisibleSize", SetVisibleSizeResult.class,
                (code, msg)->new SetVisibleSizeResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetVisibleSizeResult> call(Executor exec) {
            return super.call("Emulation.setVisibleSize", SetVisibleSizeResult.class,
                (code, msg)->new SetVisibleSizeResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setVisibleSize.
    <p><strong>Experimental.</strong></p>
    @Deprecated*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetVisibleSizeResult extends ResultBase {
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
        public SetVisibleSizeResult() { super(); }
        public SetVisibleSizeResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Event parameter of Emulation.virtualTimeAdvanced.
    <p><strong>Experimental.</strong></p>
     @see #onVirtualTimeAdvanced*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class VirtualTimeAdvancedEventParameter implements CommonDomainType {
        /**The amount of virtual time that has elapsed in milliseconds since virtual time was first
enabled.*/
        private final Double virtualTimeElapsed;
        public final Double virtualTimeElapsed() { return virtualTimeElapsed; }
        public final Double getVirtualTimeElapsed() { return virtualTimeElapsed(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"virtualTimeElapsed\":").append(virtualTimeElapsed);
            strBuilder.append('}');
            return strBuilder;
        }
        VirtualTimeAdvancedEventParameter(
            @JsonProperty("virtualTimeElapsed")Double virtualTimeElapsed
        ) {
            this.virtualTimeElapsed = virtualTimeElapsed;
        }
    }
    /**Notification sent after the virtual time has advanced.
    <p><strong>Experimental.</strong></p>
     @see VirtualTimeAdvancedEventParameter*/
    public void onVirtualTimeAdvanced(Consumer<VirtualTimeAdvancedEventParameter> callback) {
        registerEventCallback("Emulation.virtualTimeAdvanced", node -> {
            VirtualTimeAdvancedEventParameter param;
            try { param = EventCenter.deserializeJson(node, VirtualTimeAdvancedEventParameter.class); }
            catch (IOException e) { e.printStackTrace(); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of Emulation.virtualTimeBudgetExpired.
    <p><strong>Experimental.</strong></p>
     @see #onVirtualTimeBudgetExpired*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class VirtualTimeBudgetExpiredEventParameter implements CommonDomainType {
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
        public VirtualTimeBudgetExpiredEventParameter() {}
    }
    /**Notification sent after the virtual time budget for the current VirtualTimePolicy has run out.
    <p><strong>Experimental.</strong></p>
     @see VirtualTimeBudgetExpiredEventParameter*/
    public void onVirtualTimeBudgetExpired(Consumer<VirtualTimeBudgetExpiredEventParameter> callback) {
        registerEventCallback("Emulation.virtualTimeBudgetExpired", node -> {
            VirtualTimeBudgetExpiredEventParameter param;
            try { param = EventCenter.deserializeJson(node, VirtualTimeBudgetExpiredEventParameter.class); }
            catch (IOException e) { e.printStackTrace(); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of Emulation.virtualTimePaused.
    <p><strong>Experimental.</strong></p>
     @see #onVirtualTimePaused*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class VirtualTimePausedEventParameter implements CommonDomainType {
        /**The amount of virtual time that has elapsed in milliseconds since virtual time was first
enabled.*/
        private final Double virtualTimeElapsed;
        public final Double virtualTimeElapsed() { return virtualTimeElapsed; }
        public final Double getVirtualTimeElapsed() { return virtualTimeElapsed(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"virtualTimeElapsed\":").append(virtualTimeElapsed);
            strBuilder.append('}');
            return strBuilder;
        }
        VirtualTimePausedEventParameter(
            @JsonProperty("virtualTimeElapsed")Double virtualTimeElapsed
        ) {
            this.virtualTimeElapsed = virtualTimeElapsed;
        }
    }
    /**Notification sent after the virtual time has paused.
    <p><strong>Experimental.</strong></p>
     @see VirtualTimePausedEventParameter*/
    public void onVirtualTimePaused(Consumer<VirtualTimePausedEventParameter> callback) {
        registerEventCallback("Emulation.virtualTimePaused", node -> {
            VirtualTimePausedEventParameter param;
            try { param = EventCenter.deserializeJson(node, VirtualTimePausedEventParameter.class); }
            catch (IOException e) { e.printStackTrace(); return; }
            callback.accept(param);
        });
    }
}

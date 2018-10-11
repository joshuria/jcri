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

/**This domain provides experimental commands only supported in headless mode.
<p>From: browser_protocol.json</p>
<p>Protocol version: 1.3</p>
<p><strong>Experimental.</strong></p>
 @see Page
 @see Runtime
 @author Joshua*/
@ParametersAreNonnullByDefault public class HeadlessExperimental extends DomainBase {
    public HeadlessExperimental(EventCenter evt, WebSocket ws) { super(evt, ws); }

    /**Encoding options for a screenshot.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ScreenshotParams implements CommonDomainType {
        /**Image compression format (defaults to png).
        <em>Optional.</em>*/
        @ParametersAreNonnullByDefault public enum Format implements CommonDomainType {
            Jpeg("jpeg"),
            Png("png");

            private final String _value;
            /**Convert string representation to type.
             @throws IllegalArgumentException if given value cannot convert to enum type. */
            @JsonCreator public static Format of(String value) {
                return Enum.valueOf(Format.class, value.substring(0, 1).toUpperCase() + value.substring(1));
            }
            Format(String value) { _value = value; }
            /**Check if parameter fields of method are all valid. */
            @Override public void check() throws IllegalArgumentException { /* Need not check */ }
            /**Convert method parameter object into json string and append into string builder.
             @return string builder instance that is given in parameter (for chaining coding style use.) */
            @Override public StringBuilder toJson(StringBuilder strBuilder) { return strBuilder.append('"').append(_value).append('"'); }
            @Override public String toString() { return "\"" + _value + "\""; }
        }
        private Format format;
        /**Compression quality from range [0..100] (jpeg only).
        <em>Optional.</em>*/
        private Integer quality;
        public final ScreenshotParams format(@Nullable Format format) { this.format = format; return this; }
        public final ScreenshotParams optFormat(@Nullable Format format) { return format(format); }
        public final Format format() { return format; }
        public final Format getFormat() { return format(); }
        public final ScreenshotParams quality(@Nullable Integer quality) { this.quality = quality; return this; }
        public final ScreenshotParams optQuality(@Nullable Integer quality) { return quality(quality); }
        public final Integer quality() { return quality; }
        public final Integer getQuality() { return quality(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            if (format != null) strBuilder.append("\"format\":").append(format);
            if (quality != null) strBuilder.append(",\"quality\":").append(quality);
            strBuilder.append('}');
            return strBuilder;
        }
        public ScreenshotParams() {}
        public ScreenshotParams(
            @Nullable @JsonProperty("format")Format format,
            @Nullable @JsonProperty("quality")Integer quality
        ) {
            this.format = format;
            this.quality = quality;
        }
    }
    /**Sends a BeginFrame to the target and returns when the frame was completed. Optionally captures a
screenshot from the resulting frame. Requires that the target was created with enabled
BeginFrameControl. Designed for use with --run-all-compositor-stages-before-draw, see also
https://goo.gl/3zHXhB for more background.*/
    public BeginFrameParameter beginFrame() { final BeginFrameParameter v = new BeginFrameParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of beginFrame.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class BeginFrameParameter extends CommandBase {
        /**Timestamp of this BeginFrame in Renderer TimeTicks (milliseconds of uptime). If not set,
the current time will be used.
        <em>Optional.</em>*/
        private Double frameTimeTicks;
        /**The interval between BeginFrames that is reported to the compositor, in milliseconds.
Defaults to a 60 frames/second interval, i.e. about 16.666 milliseconds.
        <em>Optional.</em>*/
        private Double interval;
        /**Whether updates should not be committed and drawn onto the display. False by default. If
true, only side effects of the BeginFrame will be run, such as layout and animations, but
any visual updates may not be visible on the display or in screenshots.
        <em>Optional.</em>*/
        private Boolean noDisplayUpdates;
        /**If set, a screenshot of the frame will be captured and returned in the response. Otherwise,
no screenshot will be captured. Note that capturing a screenshot can fail, for example,
during renderer initialization. In such a case, no screenshot data will be returned.
        <em>Optional.</em>*/
        private ScreenshotParams screenshot;
        public final BeginFrameParameter frameTimeTicks(@Nullable Double frameTimeTicks) { this.frameTimeTicks = frameTimeTicks; return this; }
        public final BeginFrameParameter optFrameTimeTicks(@Nullable Double frameTimeTicks) { return frameTimeTicks(frameTimeTicks); }
        public final Double frameTimeTicks() { return frameTimeTicks; }
        public final Double getFrameTimeTicks() { return frameTimeTicks(); }
        public final BeginFrameParameter interval(@Nullable Double interval) { this.interval = interval; return this; }
        public final BeginFrameParameter optInterval(@Nullable Double interval) { return interval(interval); }
        public final Double interval() { return interval; }
        public final Double getInterval() { return interval(); }
        public final BeginFrameParameter noDisplayUpdates(@Nullable Boolean noDisplayUpdates) { this.noDisplayUpdates = noDisplayUpdates; return this; }
        public final BeginFrameParameter optNoDisplayUpdates(@Nullable Boolean noDisplayUpdates) { return noDisplayUpdates(noDisplayUpdates); }
        public final Boolean noDisplayUpdates() { return noDisplayUpdates; }
        public final Boolean getNoDisplayUpdates() { return noDisplayUpdates(); }
        public final BeginFrameParameter screenshot(@Nullable ScreenshotParams screenshot) { this.screenshot = screenshot; return this; }
        public final BeginFrameParameter optScreenshot(@Nullable ScreenshotParams screenshot) { return screenshot(screenshot); }
        public final ScreenshotParams screenshot() { return screenshot; }
        public final ScreenshotParams getScreenshot() { return screenshot(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            if (frameTimeTicks != null) strBuilder.append("\"frameTimeTicks\":").append(frameTimeTicks);
            if (interval != null) strBuilder.append(",\"interval\":").append(interval);
            if (noDisplayUpdates != null) strBuilder.append(",\"noDisplayUpdates\":").append(noDisplayUpdates);
            if (screenshot != null) screenshot.toJson(strBuilder.append(",\"screenshot\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public BeginFrameParameter() {}
        public BeginFrameParameter(
            @Nullable @JsonProperty("frameTimeTicks")Double frameTimeTicks,
            @Nullable @JsonProperty("interval")Double interval,
            @Nullable @JsonProperty("noDisplayUpdates")Boolean noDisplayUpdates,
            @Nullable @JsonProperty("screenshot")ScreenshotParams screenshot
        ) {
            this();
            this.frameTimeTicks = frameTimeTicks;
            this.interval = interval;
            this.noDisplayUpdates = noDisplayUpdates;
            this.screenshot = screenshot;
        }
        public CompletableFuture<BeginFrameResult> call() {
            return super.call("HeadlessExperimental.beginFrame", BeginFrameResult.class,
                (code, msg)->new BeginFrameResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<BeginFrameResult> callAsync() {
            return super.callAsync("HeadlessExperimental.beginFrame", BeginFrameResult.class,
                (code, msg)->new BeginFrameResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<BeginFrameResult> callAsync(Executor exec) {
            return super.callAsync("HeadlessExperimental.beginFrame", BeginFrameResult.class,
                (code, msg)->new BeginFrameResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of beginFrame.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class BeginFrameResult extends ResultBase {
        /**Whether the BeginFrame resulted in damage and, thus, a new frame was committed to the
display. Reported for diagnostic uses, may be removed in the future.*/
        private final Boolean hasDamage;
        /**Base64-encoded image data of the screenshot, if one was requested and successfully taken.
        <em>Optional.</em>*/
        private final String screenshotData;
        public final Boolean hasDamage() { return hasDamage; }
        public final Boolean getHasDamage() { return hasDamage(); }
        public final String screenshotData() { return screenshotData; }
        public final String getScreenshotData() { return screenshotData(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"hasDamage\":").append(hasDamage);
            if (screenshotData != null) strBuilder.append(",\"screenshotData\":").append('"').append(DomainBase.escapeJson(screenshotData)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public BeginFrameResult(
            @JsonProperty("hasDamage")Boolean hasDamage,
            @Nullable @JsonProperty("screenshotData")String screenshotData
        ) {
            this.hasDamage = hasDamage;
            this.screenshotData = screenshotData;
        }
        public BeginFrameResult(ResultBase.FailedResult e) {
            super(e);
            hasDamage = null;
            screenshotData = null;
        }
    }
    /**Disables headless events for the target.*/
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
            return super.call("HeadlessExperimental.disable", DisableResult.class,
                (code, msg)->new DisableResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<DisableResult> callAsync() {
            return super.callAsync("HeadlessExperimental.disable", DisableResult.class,
                (code, msg)->new DisableResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<DisableResult> callAsync(Executor exec) {
            return super.callAsync("HeadlessExperimental.disable", DisableResult.class,
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
    /**Enables headless events for the target.*/
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
            return super.call("HeadlessExperimental.enable", EnableResult.class,
                (code, msg)->new EnableResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<EnableResult> callAsync() {
            return super.callAsync("HeadlessExperimental.enable", EnableResult.class,
                (code, msg)->new EnableResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<EnableResult> callAsync(Executor exec) {
            return super.callAsync("HeadlessExperimental.enable", EnableResult.class,
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
    /**Event parameter of HeadlessExperimental.needsBeginFramesChanged.
     @see #onNeedsBeginFramesChanged*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class NeedsBeginFramesChangedEventParameter implements CommonDomainType {
        /**True if BeginFrames are needed, false otherwise.*/
        private final Boolean needsBeginFrames;
        public final Boolean needsBeginFrames() { return needsBeginFrames; }
        public final Boolean getNeedsBeginFrames() { return needsBeginFrames(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"needsBeginFrames\":").append(needsBeginFrames);
            strBuilder.append('}');
            return strBuilder;
        }
        NeedsBeginFramesChangedEventParameter(
            @JsonProperty("needsBeginFrames")Boolean needsBeginFrames
        ) {
            this.needsBeginFrames = needsBeginFrames;
        }
    }
    /**Issued when the target starts or stops needing BeginFrames.
     @see NeedsBeginFramesChangedEventParameter*/
    public void onNeedsBeginFramesChanged(@Nullable Consumer<NeedsBeginFramesChangedEventParameter> callback) {
        if (callback != null)
            registerEventCallback("HeadlessExperimental.needsBeginFramesChanged", node -> {
                NeedsBeginFramesChangedEventParameter param;
                try { param = EventCenter.deserializeJson(node, NeedsBeginFramesChangedEventParameter.class); }
                catch (IOException e) { _evt.getLog().error(e); return; }
                callback.accept(param);
            });
        else    registerEventCallback("HeadlessExperimental.needsBeginFramesChanged", null);
    }
}

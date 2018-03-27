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

/**&lt;No document in protocol.&gt;
<p>From: browser_protocol.json</p>
<p>Protocol version: 1.3</p>
<p><strong>Experimental.</strong></p>
 @see Runtime
 @see DOM
 @author Joshua*/
@ParametersAreNonnullByDefault public class Animation extends DomainBase {
    public Animation(EventCenter evt, WebSocket ws) { super(evt, ws); }

    /**Animation instance.*/
    @ParametersAreNonnullByDefault public static class AnimationType implements CommonDomainType {
        /**`Animation`'s id.*/
        private String id;
        /**`Animation`'s name.*/
        private String name;
        /**`Animation`'s internal paused state.*/
        private Boolean pausedState;
        /**`Animation`'s play state.*/
        private String playState;
        /**`Animation`'s playback rate.*/
        private Double playbackRate;
        /**`Animation`'s start time.*/
        private Double startTime;
        /**`Animation`'s current time.*/
        private Double currentTime;
        /**Animation type of `Animation`.*/
        @ParametersAreNonnullByDefault public enum Type implements CommonDomainType {
            CSSTransition("CSSTransition"),
            CSSAnimation("CSSAnimation"),
            WebAnimation("WebAnimation");

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
        /**`Animation`'s source animation node.
        <em>Optional.</em>*/
        private AnimationEffect source;
        /**A unique ID for `Animation` representing the sources that triggered this CSS
animation/transition.
        <em>Optional.</em>*/
        private String cssId;
        public final AnimationType id(String id) { this.id = id; return this; }
        public final AnimationType setId(String id) { return id(id); }
        public final String id() { return id; }
        public final String getId() { return id(); }
        public final AnimationType name(String name) { this.name = name; return this; }
        public final AnimationType setName(String name) { return name(name); }
        public final String name() { return name; }
        public final String getName() { return name(); }
        public final AnimationType pausedState(Boolean pausedState) { this.pausedState = pausedState; return this; }
        public final AnimationType setPausedState(Boolean pausedState) { return pausedState(pausedState); }
        public final Boolean pausedState() { return pausedState; }
        public final Boolean getPausedState() { return pausedState(); }
        public final AnimationType playState(String playState) { this.playState = playState; return this; }
        public final AnimationType setPlayState(String playState) { return playState(playState); }
        public final String playState() { return playState; }
        public final String getPlayState() { return playState(); }
        public final AnimationType playbackRate(Double playbackRate) { this.playbackRate = playbackRate; return this; }
        public final AnimationType setPlaybackRate(Double playbackRate) { return playbackRate(playbackRate); }
        public final Double playbackRate() { return playbackRate; }
        public final Double getPlaybackRate() { return playbackRate(); }
        public final AnimationType startTime(Double startTime) { this.startTime = startTime; return this; }
        public final AnimationType setStartTime(Double startTime) { return startTime(startTime); }
        public final Double startTime() { return startTime; }
        public final Double getStartTime() { return startTime(); }
        public final AnimationType currentTime(Double currentTime) { this.currentTime = currentTime; return this; }
        public final AnimationType setCurrentTime(Double currentTime) { return currentTime(currentTime); }
        public final Double currentTime() { return currentTime; }
        public final Double getCurrentTime() { return currentTime(); }
        public final AnimationType type(Type type) { this.type = type; return this; }
        public final AnimationType setType(Type type) { return type(type); }
        public final Type type() { return type; }
        public final Type getType() { return type(); }
        public final AnimationType source(@Nullable AnimationEffect source) { this.source = source; return this; }
        public final AnimationType optSource(@Nullable AnimationEffect source) { return source(source); }
        public final AnimationEffect source() { return source; }
        public final AnimationEffect getSource() { return source(); }
        public final AnimationType cssId(@Nullable String cssId) { this.cssId = cssId; return this; }
        public final AnimationType optCssId(@Nullable String cssId) { return cssId(cssId); }
        public final String cssId() { return cssId; }
        public final String getCssId() { return cssId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (id == null) throw new IllegalArgumentException("Animation.AnimationType.id is necessary field.");
            if (name == null) throw new IllegalArgumentException("Animation.AnimationType.name is necessary field.");
            if (pausedState == null) throw new IllegalArgumentException("Animation.AnimationType.pausedState is necessary field.");
            if (playState == null) throw new IllegalArgumentException("Animation.AnimationType.playState is necessary field.");
            if (playbackRate == null) throw new IllegalArgumentException("Animation.AnimationType.playbackRate is necessary field.");
            if (startTime == null) throw new IllegalArgumentException("Animation.AnimationType.startTime is necessary field.");
            if (currentTime == null) throw new IllegalArgumentException("Animation.AnimationType.currentTime is necessary field.");
            if (type == null) throw new IllegalArgumentException("Animation.AnimationType.type is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"id\":").append('"').append(id).append('"');
            strBuilder.append(",\"name\":").append('"').append(name).append('"');
            strBuilder.append(",\"pausedState\":").append(pausedState);
            strBuilder.append(",\"playState\":").append('"').append(playState).append('"');
            strBuilder.append(",\"playbackRate\":").append(playbackRate);
            strBuilder.append(",\"startTime\":").append(startTime);
            strBuilder.append(",\"currentTime\":").append(currentTime);
            strBuilder.append(",\"type\":").append(type);
            if (source != null) source.toJson(strBuilder.append(",\"source\":"));
            if (cssId != null) strBuilder.append(",\"cssId\":").append('"').append(cssId).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public AnimationType() {}
        public AnimationType(
            @JsonProperty("id")String id,
            @JsonProperty("name")String name,
            @JsonProperty("pausedState")Boolean pausedState,
            @JsonProperty("playState")String playState,
            @JsonProperty("playbackRate")Double playbackRate,
            @JsonProperty("startTime")Double startTime,
            @JsonProperty("currentTime")Double currentTime,
            @JsonProperty("type")Type type,
            @Nullable @JsonProperty("source")AnimationEffect source,
            @Nullable @JsonProperty("cssId")String cssId
        ) {
            this.id = id;
            this.name = name;
            this.pausedState = pausedState;
            this.playState = playState;
            this.playbackRate = playbackRate;
            this.startTime = startTime;
            this.currentTime = currentTime;
            this.type = type;
            this.source = source;
            this.cssId = cssId;
        }
    }

    /**AnimationEffect instance*/
    @ParametersAreNonnullByDefault public static class AnimationEffect implements CommonDomainType {
        /**`AnimationEffect`'s delay.*/
        private Double delay;
        /**`AnimationEffect`'s end delay.*/
        private Double endDelay;
        /**`AnimationEffect`'s iteration start.*/
        private Double iterationStart;
        /**`AnimationEffect`'s iterations.*/
        private Double iterations;
        /**`AnimationEffect`'s iteration duration.*/
        private Double duration;
        /**`AnimationEffect`'s playback direction.*/
        private String direction;
        /**`AnimationEffect`'s fill mode.*/
        private String fill;
        /**`AnimationEffect`'s target node.
        <em>Optional.</em>*/
        private DOM.BackendNodeId backendNodeId;
        /**`AnimationEffect`'s keyframes.
        <em>Optional.</em>*/
        private KeyframesRule keyframesRule;
        /**`AnimationEffect`'s timing function.*/
        private String easing;
        public final AnimationEffect delay(Double delay) { this.delay = delay; return this; }
        public final AnimationEffect setDelay(Double delay) { return delay(delay); }
        public final Double delay() { return delay; }
        public final Double getDelay() { return delay(); }
        public final AnimationEffect endDelay(Double endDelay) { this.endDelay = endDelay; return this; }
        public final AnimationEffect setEndDelay(Double endDelay) { return endDelay(endDelay); }
        public final Double endDelay() { return endDelay; }
        public final Double getEndDelay() { return endDelay(); }
        public final AnimationEffect iterationStart(Double iterationStart) { this.iterationStart = iterationStart; return this; }
        public final AnimationEffect setIterationStart(Double iterationStart) { return iterationStart(iterationStart); }
        public final Double iterationStart() { return iterationStart; }
        public final Double getIterationStart() { return iterationStart(); }
        public final AnimationEffect iterations(Double iterations) { this.iterations = iterations; return this; }
        public final AnimationEffect setIterations(Double iterations) { return iterations(iterations); }
        public final Double iterations() { return iterations; }
        public final Double getIterations() { return iterations(); }
        public final AnimationEffect duration(Double duration) { this.duration = duration; return this; }
        public final AnimationEffect setDuration(Double duration) { return duration(duration); }
        public final Double duration() { return duration; }
        public final Double getDuration() { return duration(); }
        public final AnimationEffect direction(String direction) { this.direction = direction; return this; }
        public final AnimationEffect setDirection(String direction) { return direction(direction); }
        public final String direction() { return direction; }
        public final String getDirection() { return direction(); }
        public final AnimationEffect fill(String fill) { this.fill = fill; return this; }
        public final AnimationEffect setFill(String fill) { return fill(fill); }
        public final String fill() { return fill; }
        public final String getFill() { return fill(); }
        public final AnimationEffect backendNodeId(@Nullable DOM.BackendNodeId backendNodeId) { this.backendNodeId = backendNodeId; return this; }
        public final AnimationEffect optBackendNodeId(@Nullable DOM.BackendNodeId backendNodeId) { return backendNodeId(backendNodeId); }
        public final DOM.BackendNodeId backendNodeId() { return backendNodeId; }
        public final DOM.BackendNodeId getBackendNodeId() { return backendNodeId(); }
        public final AnimationEffect keyframesRule(@Nullable KeyframesRule keyframesRule) { this.keyframesRule = keyframesRule; return this; }
        public final AnimationEffect optKeyframesRule(@Nullable KeyframesRule keyframesRule) { return keyframesRule(keyframesRule); }
        public final KeyframesRule keyframesRule() { return keyframesRule; }
        public final KeyframesRule getKeyframesRule() { return keyframesRule(); }
        public final AnimationEffect easing(String easing) { this.easing = easing; return this; }
        public final AnimationEffect setEasing(String easing) { return easing(easing); }
        public final String easing() { return easing; }
        public final String getEasing() { return easing(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (delay == null) throw new IllegalArgumentException("Animation.AnimationEffect.delay is necessary field.");
            if (endDelay == null) throw new IllegalArgumentException("Animation.AnimationEffect.endDelay is necessary field.");
            if (iterationStart == null) throw new IllegalArgumentException("Animation.AnimationEffect.iterationStart is necessary field.");
            if (iterations == null) throw new IllegalArgumentException("Animation.AnimationEffect.iterations is necessary field.");
            if (duration == null) throw new IllegalArgumentException("Animation.AnimationEffect.duration is necessary field.");
            if (direction == null) throw new IllegalArgumentException("Animation.AnimationEffect.direction is necessary field.");
            if (fill == null) throw new IllegalArgumentException("Animation.AnimationEffect.fill is necessary field.");
            if (easing == null) throw new IllegalArgumentException("Animation.AnimationEffect.easing is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"delay\":").append(delay);
            strBuilder.append(",\"endDelay\":").append(endDelay);
            strBuilder.append(",\"iterationStart\":").append(iterationStart);
            strBuilder.append(",\"iterations\":").append(iterations);
            strBuilder.append(",\"duration\":").append(duration);
            strBuilder.append(",\"direction\":").append('"').append(direction).append('"');
            strBuilder.append(",\"fill\":").append('"').append(fill).append('"');
            if (backendNodeId != null) backendNodeId.toJson(strBuilder.append(",\"backendNodeId\":"));
            if (keyframesRule != null) keyframesRule.toJson(strBuilder.append(",\"keyframesRule\":"));
            strBuilder.append(",\"easing\":").append('"').append(easing).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public AnimationEffect() {}
        public AnimationEffect(
            @JsonProperty("delay")Double delay,
            @JsonProperty("endDelay")Double endDelay,
            @JsonProperty("iterationStart")Double iterationStart,
            @JsonProperty("iterations")Double iterations,
            @JsonProperty("duration")Double duration,
            @JsonProperty("direction")String direction,
            @JsonProperty("fill")String fill,
            @Nullable @JsonProperty("backendNodeId")DOM.BackendNodeId backendNodeId,
            @Nullable @JsonProperty("keyframesRule")KeyframesRule keyframesRule,
            @JsonProperty("easing")String easing
        ) {
            this.delay = delay;
            this.endDelay = endDelay;
            this.iterationStart = iterationStart;
            this.iterations = iterations;
            this.duration = duration;
            this.direction = direction;
            this.fill = fill;
            this.backendNodeId = backendNodeId;
            this.keyframesRule = keyframesRule;
            this.easing = easing;
        }
    }

    /**Keyframes Rule*/
    @ParametersAreNonnullByDefault public static class KeyframesRule implements CommonDomainType {
        /**CSS keyframed animation's name.
        <em>Optional.</em>*/
        private String name;
        /**List of animation keyframes.*/
        private List<KeyframeStyle> keyframes;
        public final KeyframesRule name(@Nullable String name) { this.name = name; return this; }
        public final KeyframesRule optName(@Nullable String name) { return name(name); }
        public final String name() { return name; }
        public final String getName() { return name(); }
        public final KeyframesRule keyframes(List<KeyframeStyle> keyframes) { this.keyframes = keyframes; return this; }
        public final KeyframesRule setKeyframes(List<KeyframeStyle> keyframes) { return keyframes(keyframes); }
        public final List<KeyframeStyle> keyframes() { return keyframes; }
        public final List<KeyframeStyle> getKeyframes() { return keyframes(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (keyframes == null) throw new IllegalArgumentException("Animation.KeyframesRule.keyframes is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            if (name != null) strBuilder.append("\"name\":").append('"').append(name).append('"');
                        strBuilder.append(",\"keyframes\":[");
            keyframes.get(0).toJson(strBuilder);
            for (int i = 1; i < keyframes.size(); ++i)
                keyframes.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public KeyframesRule() {}
        public KeyframesRule(
            @Nullable @JsonProperty("name")String name,
            @JsonProperty("keyframes")List<KeyframeStyle> keyframes
        ) {
            this.name = name;
            this.keyframes = keyframes;
        }
    }

    /**Keyframe Style*/
    @ParametersAreNonnullByDefault public static class KeyframeStyle implements CommonDomainType {
        /**Keyframe's time offset.*/
        private String offset;
        /**`AnimationEffect`'s timing function.*/
        private String easing;
        public final KeyframeStyle offset(String offset) { this.offset = offset; return this; }
        public final KeyframeStyle setOffset(String offset) { return offset(offset); }
        public final String offset() { return offset; }
        public final String getOffset() { return offset(); }
        public final KeyframeStyle easing(String easing) { this.easing = easing; return this; }
        public final KeyframeStyle setEasing(String easing) { return easing(easing); }
        public final String easing() { return easing; }
        public final String getEasing() { return easing(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (offset == null) throw new IllegalArgumentException("Animation.KeyframeStyle.offset is necessary field.");
            if (easing == null) throw new IllegalArgumentException("Animation.KeyframeStyle.easing is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"offset\":").append('"').append(offset).append('"');
            strBuilder.append(",\"easing\":").append('"').append(easing).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public KeyframeStyle() {}
        public KeyframeStyle(
            @JsonProperty("offset")String offset,
            @JsonProperty("easing")String easing
        ) {
            this.offset = offset;
            this.easing = easing;
        }
    }
    /**Disables animation domain notifications.*/
    public DisableParameter disable() { final DisableParameter v = new DisableParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of disable.*/
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
            return super.call("Animation.disable", DisableResult.class, msg->new DisableResult(ResultBase.ofError(msg)));
        }
        public CompletableFuture<DisableResult> call(Executor exec) {
            return super.call("Animation.disable", DisableResult.class, msg->new DisableResult(ResultBase.ofError(msg)), exec);
        }
    }
    /**Return result class of disable.*/
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
    /**Enables animation domain notifications.*/
    public EnableParameter enable() { final EnableParameter v = new EnableParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of enable.*/
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
            return super.call("Animation.enable", EnableResult.class, msg->new EnableResult(ResultBase.ofError(msg)));
        }
        public CompletableFuture<EnableResult> call(Executor exec) {
            return super.call("Animation.enable", EnableResult.class, msg->new EnableResult(ResultBase.ofError(msg)), exec);
        }
    }
    /**Return result class of enable.*/
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
    /**Returns the current time of the an animation.*/
    public GetCurrentTimeParameter getCurrentTime() { final GetCurrentTimeParameter v = new GetCurrentTimeParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of getCurrentTime.*/
    @ParametersAreNonnullByDefault public static class GetCurrentTimeParameter extends CommandBase {
        /**Id of animation.*/
        private String id;
        public final GetCurrentTimeParameter id(String id) { this.id = id; return this; }
        public final GetCurrentTimeParameter setId(String id) { return id(id); }
        public final String id() { return id; }
        public final String getId() { return id(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (id == null) throw new IllegalArgumentException("Animation.GetCurrentTimeParameter.id is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"id\":").append('"').append(id).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public GetCurrentTimeParameter() {}
        public GetCurrentTimeParameter(
            @JsonProperty("id")String id
        ) {
            this();
            this.id = id;
        }
        public CompletableFuture<GetCurrentTimeResult> call() {
            return super.call("Animation.getCurrentTime", GetCurrentTimeResult.class, msg->new GetCurrentTimeResult(ResultBase.ofError(msg)));
        }
        public CompletableFuture<GetCurrentTimeResult> call(Executor exec) {
            return super.call("Animation.getCurrentTime", GetCurrentTimeResult.class, msg->new GetCurrentTimeResult(ResultBase.ofError(msg)), exec);
        }
    }
    /**Return result class of getCurrentTime.*/
    @ParametersAreNonnullByDefault public static class GetCurrentTimeResult extends ResultBase {
        /**Current time of the page.*/
        private final Double currentTime;
        public final Double currentTime() { return currentTime; }
        public final Double getCurrentTime() { return currentTime(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"currentTime\":").append(currentTime);
            strBuilder.append('}');
            return strBuilder;
        }
        public GetCurrentTimeResult(
            @JsonProperty("currentTime")Double currentTime
        ) {
            this.currentTime = currentTime;
        }
        public GetCurrentTimeResult(ResultBase.FailedResult e) {
            super(e);
            currentTime = null;
        }
    }
    /**Gets the playback rate of the document timeline.*/
    public GetPlaybackRateParameter getPlaybackRate() { final GetPlaybackRateParameter v = new GetPlaybackRateParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of getPlaybackRate.*/
    @ParametersAreNonnullByDefault public static class GetPlaybackRateParameter extends CommandBase {
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
        public GetPlaybackRateParameter() {}
        public CompletableFuture<GetPlaybackRateResult> call() {
            return super.call("Animation.getPlaybackRate", GetPlaybackRateResult.class, msg->new GetPlaybackRateResult(ResultBase.ofError(msg)));
        }
        public CompletableFuture<GetPlaybackRateResult> call(Executor exec) {
            return super.call("Animation.getPlaybackRate", GetPlaybackRateResult.class, msg->new GetPlaybackRateResult(ResultBase.ofError(msg)), exec);
        }
    }
    /**Return result class of getPlaybackRate.*/
    @ParametersAreNonnullByDefault public static class GetPlaybackRateResult extends ResultBase {
        /**Playback rate for animations on page.*/
        private final Double playbackRate;
        public final Double playbackRate() { return playbackRate; }
        public final Double getPlaybackRate() { return playbackRate(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"playbackRate\":").append(playbackRate);
            strBuilder.append('}');
            return strBuilder;
        }
        public GetPlaybackRateResult(
            @JsonProperty("playbackRate")Double playbackRate
        ) {
            this.playbackRate = playbackRate;
        }
        public GetPlaybackRateResult(ResultBase.FailedResult e) {
            super(e);
            playbackRate = null;
        }
    }
    /**Releases a set of animations to no longer be manipulated.*/
    public ReleaseAnimationsParameter releaseAnimations() { final ReleaseAnimationsParameter v = new ReleaseAnimationsParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of releaseAnimations.*/
    @ParametersAreNonnullByDefault public static class ReleaseAnimationsParameter extends CommandBase {
        /**List of animation ids to seek.*/
        private List<String> animations;
        public final ReleaseAnimationsParameter animations(List<String> animations) { this.animations = animations; return this; }
        public final ReleaseAnimationsParameter setAnimations(List<String> animations) { return animations(animations); }
        public final List<String> animations() { return animations; }
        public final List<String> getAnimations() { return animations(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (animations == null) throw new IllegalArgumentException("Animation.ReleaseAnimationsParameter.animations is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
                        strBuilder.append("\"animations\":[");
            strBuilder.append('"').append(animations.get(0)).append('"');
            for (int i = 1; i < animations.size(); ++i)
                strBuilder.append(",\"").append(animations.get(i)).append('"');
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public ReleaseAnimationsParameter() {}
        public ReleaseAnimationsParameter(
            @JsonProperty("animations")List<String> animations
        ) {
            this();
            this.animations = animations;
        }
        public CompletableFuture<ReleaseAnimationsResult> call() {
            return super.call("Animation.releaseAnimations", ReleaseAnimationsResult.class, msg->new ReleaseAnimationsResult(ResultBase.ofError(msg)));
        }
        public CompletableFuture<ReleaseAnimationsResult> call(Executor exec) {
            return super.call("Animation.releaseAnimations", ReleaseAnimationsResult.class, msg->new ReleaseAnimationsResult(ResultBase.ofError(msg)), exec);
        }
    }
    /**Return result class of releaseAnimations.*/
    @ParametersAreNonnullByDefault public static class ReleaseAnimationsResult extends ResultBase {
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
        public ReleaseAnimationsResult() { super(); }
        public ReleaseAnimationsResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Gets the remote object of the Animation.*/
    public ResolveAnimationParameter resolveAnimation() { final ResolveAnimationParameter v = new ResolveAnimationParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of resolveAnimation.*/
    @ParametersAreNonnullByDefault public static class ResolveAnimationParameter extends CommandBase {
        /**Animation id.*/
        private String animationId;
        public final ResolveAnimationParameter animationId(String animationId) { this.animationId = animationId; return this; }
        public final ResolveAnimationParameter setAnimationId(String animationId) { return animationId(animationId); }
        public final String animationId() { return animationId; }
        public final String getAnimationId() { return animationId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (animationId == null) throw new IllegalArgumentException("Animation.ResolveAnimationParameter.animationId is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"animationId\":").append('"').append(animationId).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public ResolveAnimationParameter() {}
        public ResolveAnimationParameter(
            @JsonProperty("animationId")String animationId
        ) {
            this();
            this.animationId = animationId;
        }
        public CompletableFuture<ResolveAnimationResult> call() {
            return super.call("Animation.resolveAnimation", ResolveAnimationResult.class, msg->new ResolveAnimationResult(ResultBase.ofError(msg)));
        }
        public CompletableFuture<ResolveAnimationResult> call(Executor exec) {
            return super.call("Animation.resolveAnimation", ResolveAnimationResult.class, msg->new ResolveAnimationResult(ResultBase.ofError(msg)), exec);
        }
    }
    /**Return result class of resolveAnimation.*/
    @ParametersAreNonnullByDefault public static class ResolveAnimationResult extends ResultBase {
        /**Corresponding remote object.*/
        private final Runtime.RemoteObject remoteObject;
        public final Runtime.RemoteObject remoteObject() { return remoteObject; }
        public final Runtime.RemoteObject getRemoteObject() { return remoteObject(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            remoteObject.toJson(strBuilder.append("\"remoteObject\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public ResolveAnimationResult(
            @JsonProperty("remoteObject")Runtime.RemoteObject remoteObject
        ) {
            this.remoteObject = remoteObject;
        }
        public ResolveAnimationResult(ResultBase.FailedResult e) {
            super(e);
            remoteObject = null;
        }
    }
    /**Seek a set of animations to a particular time within each animation.*/
    public SeekAnimationsParameter seekAnimations() { final SeekAnimationsParameter v = new SeekAnimationsParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of seekAnimations.*/
    @ParametersAreNonnullByDefault public static class SeekAnimationsParameter extends CommandBase {
        /**List of animation ids to seek.*/
        private List<String> animations;
        /**Set the current time of each animation.*/
        private Double currentTime;
        public final SeekAnimationsParameter animations(List<String> animations) { this.animations = animations; return this; }
        public final SeekAnimationsParameter setAnimations(List<String> animations) { return animations(animations); }
        public final List<String> animations() { return animations; }
        public final List<String> getAnimations() { return animations(); }
        public final SeekAnimationsParameter currentTime(Double currentTime) { this.currentTime = currentTime; return this; }
        public final SeekAnimationsParameter setCurrentTime(Double currentTime) { return currentTime(currentTime); }
        public final Double currentTime() { return currentTime; }
        public final Double getCurrentTime() { return currentTime(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (animations == null) throw new IllegalArgumentException("Animation.SeekAnimationsParameter.animations is necessary field.");
            if (currentTime == null) throw new IllegalArgumentException("Animation.SeekAnimationsParameter.currentTime is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
                        strBuilder.append("\"animations\":[");
            strBuilder.append('"').append(animations.get(0)).append('"');
            for (int i = 1; i < animations.size(); ++i)
                strBuilder.append(",\"").append(animations.get(i)).append('"');
            strBuilder.append(']');
            strBuilder.append(",\"currentTime\":").append(currentTime);
            strBuilder.append('}');
            return strBuilder;
        }
        public SeekAnimationsParameter() {}
        public SeekAnimationsParameter(
            @JsonProperty("animations")List<String> animations,
            @JsonProperty("currentTime")Double currentTime
        ) {
            this();
            this.animations = animations;
            this.currentTime = currentTime;
        }
        public CompletableFuture<SeekAnimationsResult> call() {
            return super.call("Animation.seekAnimations", SeekAnimationsResult.class, msg->new SeekAnimationsResult(ResultBase.ofError(msg)));
        }
        public CompletableFuture<SeekAnimationsResult> call(Executor exec) {
            return super.call("Animation.seekAnimations", SeekAnimationsResult.class, msg->new SeekAnimationsResult(ResultBase.ofError(msg)), exec);
        }
    }
    /**Return result class of seekAnimations.*/
    @ParametersAreNonnullByDefault public static class SeekAnimationsResult extends ResultBase {
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
        public SeekAnimationsResult() { super(); }
        public SeekAnimationsResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Sets the paused state of a set of animations.*/
    public SetPausedParameter setPaused() { final SetPausedParameter v = new SetPausedParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setPaused.*/
    @ParametersAreNonnullByDefault public static class SetPausedParameter extends CommandBase {
        /**Animations to set the pause state of.*/
        private List<String> animations;
        /**Paused state to set to.*/
        private Boolean paused;
        public final SetPausedParameter animations(List<String> animations) { this.animations = animations; return this; }
        public final SetPausedParameter setAnimations(List<String> animations) { return animations(animations); }
        public final List<String> animations() { return animations; }
        public final List<String> getAnimations() { return animations(); }
        public final SetPausedParameter paused(Boolean paused) { this.paused = paused; return this; }
        public final SetPausedParameter setPaused(Boolean paused) { return paused(paused); }
        public final Boolean paused() { return paused; }
        public final Boolean getPaused() { return paused(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (animations == null) throw new IllegalArgumentException("Animation.SetPausedParameter.animations is necessary field.");
            if (paused == null) throw new IllegalArgumentException("Animation.SetPausedParameter.paused is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
                        strBuilder.append("\"animations\":[");
            strBuilder.append('"').append(animations.get(0)).append('"');
            for (int i = 1; i < animations.size(); ++i)
                strBuilder.append(",\"").append(animations.get(i)).append('"');
            strBuilder.append(']');
            strBuilder.append(",\"paused\":").append(paused);
            strBuilder.append('}');
            return strBuilder;
        }
        public SetPausedParameter() {}
        public SetPausedParameter(
            @JsonProperty("animations")List<String> animations,
            @JsonProperty("paused")Boolean paused
        ) {
            this();
            this.animations = animations;
            this.paused = paused;
        }
        public CompletableFuture<SetPausedResult> call() {
            return super.call("Animation.setPaused", SetPausedResult.class, msg->new SetPausedResult(ResultBase.ofError(msg)));
        }
        public CompletableFuture<SetPausedResult> call(Executor exec) {
            return super.call("Animation.setPaused", SetPausedResult.class, msg->new SetPausedResult(ResultBase.ofError(msg)), exec);
        }
    }
    /**Return result class of setPaused.*/
    @ParametersAreNonnullByDefault public static class SetPausedResult extends ResultBase {
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
        public SetPausedResult() { super(); }
        public SetPausedResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Sets the playback rate of the document timeline.*/
    public SetPlaybackRateParameter setPlaybackRate() { final SetPlaybackRateParameter v = new SetPlaybackRateParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setPlaybackRate.*/
    @ParametersAreNonnullByDefault public static class SetPlaybackRateParameter extends CommandBase {
        /**Playback rate for animations on page*/
        private Double playbackRate;
        public final SetPlaybackRateParameter playbackRate(Double playbackRate) { this.playbackRate = playbackRate; return this; }
        public final SetPlaybackRateParameter setPlaybackRate(Double playbackRate) { return playbackRate(playbackRate); }
        public final Double playbackRate() { return playbackRate; }
        public final Double getPlaybackRate() { return playbackRate(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (playbackRate == null) throw new IllegalArgumentException("Animation.SetPlaybackRateParameter.playbackRate is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"playbackRate\":").append(playbackRate);
            strBuilder.append('}');
            return strBuilder;
        }
        public SetPlaybackRateParameter() {}
        public SetPlaybackRateParameter(
            @JsonProperty("playbackRate")Double playbackRate
        ) {
            this();
            this.playbackRate = playbackRate;
        }
        public CompletableFuture<SetPlaybackRateResult> call() {
            return super.call("Animation.setPlaybackRate", SetPlaybackRateResult.class, msg->new SetPlaybackRateResult(ResultBase.ofError(msg)));
        }
        public CompletableFuture<SetPlaybackRateResult> call(Executor exec) {
            return super.call("Animation.setPlaybackRate", SetPlaybackRateResult.class, msg->new SetPlaybackRateResult(ResultBase.ofError(msg)), exec);
        }
    }
    /**Return result class of setPlaybackRate.*/
    @ParametersAreNonnullByDefault public static class SetPlaybackRateResult extends ResultBase {
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
        public SetPlaybackRateResult() { super(); }
        public SetPlaybackRateResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Sets the timing of an animation node.*/
    public SetTimingParameter setTiming() { final SetTimingParameter v = new SetTimingParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setTiming.*/
    @ParametersAreNonnullByDefault public static class SetTimingParameter extends CommandBase {
        /**Animation id.*/
        private String animationId;
        /**Duration of the animation.*/
        private Double duration;
        /**Delay of the animation.*/
        private Double delay;
        public final SetTimingParameter animationId(String animationId) { this.animationId = animationId; return this; }
        public final SetTimingParameter setAnimationId(String animationId) { return animationId(animationId); }
        public final String animationId() { return animationId; }
        public final String getAnimationId() { return animationId(); }
        public final SetTimingParameter duration(Double duration) { this.duration = duration; return this; }
        public final SetTimingParameter setDuration(Double duration) { return duration(duration); }
        public final Double duration() { return duration; }
        public final Double getDuration() { return duration(); }
        public final SetTimingParameter delay(Double delay) { this.delay = delay; return this; }
        public final SetTimingParameter setDelay(Double delay) { return delay(delay); }
        public final Double delay() { return delay; }
        public final Double getDelay() { return delay(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (animationId == null) throw new IllegalArgumentException("Animation.SetTimingParameter.animationId is necessary field.");
            if (duration == null) throw new IllegalArgumentException("Animation.SetTimingParameter.duration is necessary field.");
            if (delay == null) throw new IllegalArgumentException("Animation.SetTimingParameter.delay is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"animationId\":").append('"').append(animationId).append('"');
            strBuilder.append(",\"duration\":").append(duration);
            strBuilder.append(",\"delay\":").append(delay);
            strBuilder.append('}');
            return strBuilder;
        }
        public SetTimingParameter() {}
        public SetTimingParameter(
            @JsonProperty("animationId")String animationId,
            @JsonProperty("duration")Double duration,
            @JsonProperty("delay")Double delay
        ) {
            this();
            this.animationId = animationId;
            this.duration = duration;
            this.delay = delay;
        }
        public CompletableFuture<SetTimingResult> call() {
            return super.call("Animation.setTiming", SetTimingResult.class, msg->new SetTimingResult(ResultBase.ofError(msg)));
        }
        public CompletableFuture<SetTimingResult> call(Executor exec) {
            return super.call("Animation.setTiming", SetTimingResult.class, msg->new SetTimingResult(ResultBase.ofError(msg)), exec);
        }
    }
    /**Return result class of setTiming.*/
    @ParametersAreNonnullByDefault public static class SetTimingResult extends ResultBase {
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
        public SetTimingResult() { super(); }
        public SetTimingResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Event parameter of Animation.animationCanceled.
     @see #onAnimationCanceled*/
    @ParametersAreNonnullByDefault public static class AnimationCanceledEventParameter implements CommonDomainType {
        /**Id of the animation that was cancelled.*/
        private final String id;
        public final String id() { return id; }
        public final String getId() { return id(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"id\":").append('"').append(id).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        AnimationCanceledEventParameter(
            @JsonProperty("id")String id
        ) {
            this.id = id;
        }
    }
    /**Event for when an animation has been cancelled.
     @see AnimationCanceledEventParameter*/
    public void onAnimationCanceled(Consumer<AnimationCanceledEventParameter> callback) {
        registerEventCallback("Animation.animationCanceled", node -> {
            AnimationCanceledEventParameter param;
            try { param = EventCenter.deserializeJson(node, AnimationCanceledEventParameter.class); }
            catch (IOException e) { e.printStackTrace(); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of Animation.animationCreated.
     @see #onAnimationCreated*/
    @ParametersAreNonnullByDefault public static class AnimationCreatedEventParameter implements CommonDomainType {
        /**Id of the animation that was created.*/
        private final String id;
        public final String id() { return id; }
        public final String getId() { return id(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"id\":").append('"').append(id).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        AnimationCreatedEventParameter(
            @JsonProperty("id")String id
        ) {
            this.id = id;
        }
    }
    /**Event for each animation that has been created.
     @see AnimationCreatedEventParameter*/
    public void onAnimationCreated(Consumer<AnimationCreatedEventParameter> callback) {
        registerEventCallback("Animation.animationCreated", node -> {
            AnimationCreatedEventParameter param;
            try { param = EventCenter.deserializeJson(node, AnimationCreatedEventParameter.class); }
            catch (IOException e) { e.printStackTrace(); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of Animation.animationStarted.
     @see #onAnimationStarted*/
    @ParametersAreNonnullByDefault public static class AnimationStartedEventParameter implements CommonDomainType {
        /**Animation that was started.*/
        private final AnimationType animation;
        public final AnimationType animation() { return animation; }
        public final AnimationType getAnimation() { return animation(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            animation.toJson(strBuilder.append("\"animation\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        AnimationStartedEventParameter(
            @JsonProperty("animation")AnimationType animation
        ) {
            this.animation = animation;
        }
    }
    /**Event for animation that has been started.
     @see AnimationStartedEventParameter*/
    public void onAnimationStarted(Consumer<AnimationStartedEventParameter> callback) {
        registerEventCallback("Animation.animationStarted", node -> {
            AnimationStartedEventParameter param;
            try { param = EventCenter.deserializeJson(node, AnimationStartedEventParameter.class); }
            catch (IOException e) { e.printStackTrace(); return; }
            callback.accept(param);
        });
    }
}

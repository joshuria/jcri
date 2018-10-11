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
@ParametersAreNonnullByDefault public class Input extends DomainBase {
    public Input(EventCenter evt, WebSocket ws) { super(evt, ws); }

    /**&lt;No document in protocol.&gt;*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class TouchPoint implements CommonDomainType {
        /**X coordinate of the event relative to the main frame's viewport in CSS pixels.*/
        private Double x;
        /**Y coordinate of the event relative to the main frame's viewport in CSS pixels. 0 refers to
the top of the viewport and Y increases as it proceeds towards the bottom of the viewport.*/
        private Double y;
        /**X radius of the touch area (default: 1.0).
        <em>Optional.</em>*/
        private Double radiusX;
        /**Y radius of the touch area (default: 1.0).
        <em>Optional.</em>*/
        private Double radiusY;
        /**Rotation angle (default: 0.0).
        <em>Optional.</em>*/
        private Double rotationAngle;
        /**Force (default: 1.0).
        <em>Optional.</em>*/
        private Double force;
        /**Identifier used to track touch sources between events, must be unique within an event.
        <em>Optional.</em>*/
        private Double id;
        public final TouchPoint x(Double x) { this.x = x; return this; }
        public final TouchPoint setX(Double x) { return x(x); }
        public final Double x() { return x; }
        public final Double getX() { return x(); }
        public final TouchPoint y(Double y) { this.y = y; return this; }
        public final TouchPoint setY(Double y) { return y(y); }
        public final Double y() { return y; }
        public final Double getY() { return y(); }
        public final TouchPoint radiusX(@Nullable Double radiusX) { this.radiusX = radiusX; return this; }
        public final TouchPoint optRadiusX(@Nullable Double radiusX) { return radiusX(radiusX); }
        public final Double radiusX() { return radiusX; }
        public final Double getRadiusX() { return radiusX(); }
        public final TouchPoint radiusY(@Nullable Double radiusY) { this.radiusY = radiusY; return this; }
        public final TouchPoint optRadiusY(@Nullable Double radiusY) { return radiusY(radiusY); }
        public final Double radiusY() { return radiusY; }
        public final Double getRadiusY() { return radiusY(); }
        public final TouchPoint rotationAngle(@Nullable Double rotationAngle) { this.rotationAngle = rotationAngle; return this; }
        public final TouchPoint optRotationAngle(@Nullable Double rotationAngle) { return rotationAngle(rotationAngle); }
        public final Double rotationAngle() { return rotationAngle; }
        public final Double getRotationAngle() { return rotationAngle(); }
        public final TouchPoint force(@Nullable Double force) { this.force = force; return this; }
        public final TouchPoint optForce(@Nullable Double force) { return force(force); }
        public final Double force() { return force; }
        public final Double getForce() { return force(); }
        public final TouchPoint id(@Nullable Double id) { this.id = id; return this; }
        public final TouchPoint optId(@Nullable Double id) { return id(id); }
        public final Double id() { return id; }
        public final Double getId() { return id(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (x == null) throw new IllegalArgumentException("Input.TouchPoint.x is necessary field.");
            if (y == null) throw new IllegalArgumentException("Input.TouchPoint.y is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"x\":").append(x);
            strBuilder.append(",\"y\":").append(y);
            if (radiusX != null) strBuilder.append(",\"radiusX\":").append(radiusX);
            if (radiusY != null) strBuilder.append(",\"radiusY\":").append(radiusY);
            if (rotationAngle != null) strBuilder.append(",\"rotationAngle\":").append(rotationAngle);
            if (force != null) strBuilder.append(",\"force\":").append(force);
            if (id != null) strBuilder.append(",\"id\":").append(id);
            strBuilder.append('}');
            return strBuilder;
        }
        public TouchPoint() {}
        public TouchPoint(
            @JsonProperty("x")Double x,
            @JsonProperty("y")Double y,
            @Nullable @JsonProperty("radiusX")Double radiusX,
            @Nullable @JsonProperty("radiusY")Double radiusY,
            @Nullable @JsonProperty("rotationAngle")Double rotationAngle,
            @Nullable @JsonProperty("force")Double force,
            @Nullable @JsonProperty("id")Double id
        ) {
            this.x = x;
            this.y = y;
            this.radiusX = radiusX;
            this.radiusY = radiusY;
            this.rotationAngle = rotationAngle;
            this.force = force;
            this.id = id;
        }
    }

    /**&lt;No document in protocol.&gt;
    <p><strong>Experimental.</strong></p>*/
    @ParametersAreNonnullByDefault public enum GestureSourceType implements CommonDomainType {
        Default("default"),
        Touch("touch"),
        Mouse("mouse");

        private final String _value;
        /**Convert string representation to type.
         @throws IllegalArgumentException if given value cannot convert to enum type. */
        @JsonCreator public static GestureSourceType of(String value) {
            return Enum.valueOf(GestureSourceType.class, value.substring(0, 1).toUpperCase() + value.substring(1));
        }
        GestureSourceType(String value) { _value = value; }
        /**Check if parameter fields of method are all valid. */
        @Override public void check() throws IllegalArgumentException { /* Need not check */ }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) { return strBuilder.append('"').append(_value).append('"'); }
        @Override public String toString() { return "\"" + _value + "\""; }
    }

    /**UTC time in seconds, counted from January 1, 1970.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class TimeSinceEpoch implements CommonDomainType {
        private Double _value;
        public TimeSinceEpoch() {}
        public TimeSinceEpoch(@Nullable Double value) { _value = value; }
        public TimeSinceEpoch(Long value) { _value = value.doubleValue(); }
        public final TimeSinceEpoch value(Double value) { _value = value; return this; }
        public final Double value() { return _value; }
        public final TimeSinceEpoch setValue(Double value) { return value(value); }
        public final Double getValue() { return value(); }
        @Override public String toString() { return String.valueOf(_value); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (_value == null) throw new IllegalArgumentException("Input.TimeSinceEpoch.value is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            return strBuilder.append(_value);
        }
    }
    /**Dispatches a key event to the page.*/
    public DispatchKeyEventParameter dispatchKeyEvent() { final DispatchKeyEventParameter v = new DispatchKeyEventParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of dispatchKeyEvent.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class DispatchKeyEventParameter extends CommandBase {
        /**Type of the key event.*/
        @ParametersAreNonnullByDefault public enum Type implements CommonDomainType {
            KeyDown("keyDown"),
            KeyUp("keyUp"),
            RawKeyDown("rawKeyDown"),
            Char("char");

            private final String _value;
            /**Convert string representation to type.
             @throws IllegalArgumentException if given value cannot convert to enum type. */
            @JsonCreator public static Type of(String value) {
                return Enum.valueOf(Type.class, value.substring(0, 1).toUpperCase() + value.substring(1));
            }
            Type(String value) { _value = value; }
            /**Check if parameter fields of method are all valid. */
            @Override public void check() throws IllegalArgumentException { /* Need not check */ }
            /**Convert method parameter object into json string and append into string builder.
             @return string builder instance that is given in parameter (for chaining coding style use.) */
            @Override public StringBuilder toJson(StringBuilder strBuilder) { return strBuilder.append('"').append(_value).append('"'); }
            @Override public String toString() { return "\"" + _value + "\""; }
        }
        private Type type;
        /**Bit field representing pressed modifier keys. Alt=1, Ctrl=2, Meta/Command=4, Shift=8
(default: 0).
        <em>Optional.</em>*/
        private Integer modifiers;
        /**Time at which the event occurred.
        <em>Optional.</em>*/
        private TimeSinceEpoch timestamp;
        /**Text as generated by processing a virtual key code with a keyboard layout. Not needed for
for `keyUp` and `rawKeyDown` events (default: "")
        <em>Optional.</em>*/
        private String text;
        /**Text that would have been generated by the keyboard if no modifiers were pressed (except for
shift). Useful for shortcut (accelerator) key handling (default: "").
        <em>Optional.</em>*/
        private String unmodifiedText;
        /**Unique key identifier (e.g., 'U+0041') (default: "").
        <em>Optional.</em>*/
        private String keyIdentifier;
        /**Unique DOM defined string value for each physical key (e.g., 'KeyA') (default: "").
        <em>Optional.</em>*/
        private String code;
        /**Unique DOM defined string value describing the meaning of the key in the context of active
modifiers, keyboard layout, etc (e.g., 'AltGr') (default: "").
        <em>Optional.</em>*/
        private String key;
        /**Windows virtual key code (default: 0).
        <em>Optional.</em>*/
        private Integer windowsVirtualKeyCode;
        /**Native virtual key code (default: 0).
        <em>Optional.</em>*/
        private Integer nativeVirtualKeyCode;
        /**Whether the event was generated from auto repeat (default: false).
        <em>Optional.</em>*/
        private Boolean autoRepeat;
        /**Whether the event was generated from the keypad (default: false).
        <em>Optional.</em>*/
        private Boolean isKeypad;
        /**Whether the event was a system key event (default: false).
        <em>Optional.</em>*/
        private Boolean isSystemKey;
        /**Whether the event was from the left or right side of the keyboard. 1=Left, 2=Right (default:
0).
        <em>Optional.</em>*/
        private Integer location;
        public final DispatchKeyEventParameter type(Type type) { this.type = type; return this; }
        public final DispatchKeyEventParameter setType(Type type) { return type(type); }
        public final Type type() { return type; }
        public final Type getType() { return type(); }
        public final DispatchKeyEventParameter modifiers(@Nullable Integer modifiers) { this.modifiers = modifiers; return this; }
        public final DispatchKeyEventParameter optModifiers(@Nullable Integer modifiers) { return modifiers(modifiers); }
        public final Integer modifiers() { return modifiers; }
        public final Integer getModifiers() { return modifiers(); }
        public final DispatchKeyEventParameter timestamp(@Nullable TimeSinceEpoch timestamp) { this.timestamp = timestamp; return this; }
        public final DispatchKeyEventParameter optTimestamp(@Nullable TimeSinceEpoch timestamp) { return timestamp(timestamp); }
        public final TimeSinceEpoch timestamp() { return timestamp; }
        public final TimeSinceEpoch getTimestamp() { return timestamp(); }
        public final DispatchKeyEventParameter text(@Nullable String text) { this.text = text; return this; }
        public final DispatchKeyEventParameter optText(@Nullable String text) { return text(text); }
        public final String text() { return text; }
        public final String getText() { return text(); }
        public final DispatchKeyEventParameter unmodifiedText(@Nullable String unmodifiedText) { this.unmodifiedText = unmodifiedText; return this; }
        public final DispatchKeyEventParameter optUnmodifiedText(@Nullable String unmodifiedText) { return unmodifiedText(unmodifiedText); }
        public final String unmodifiedText() { return unmodifiedText; }
        public final String getUnmodifiedText() { return unmodifiedText(); }
        public final DispatchKeyEventParameter keyIdentifier(@Nullable String keyIdentifier) { this.keyIdentifier = keyIdentifier; return this; }
        public final DispatchKeyEventParameter optKeyIdentifier(@Nullable String keyIdentifier) { return keyIdentifier(keyIdentifier); }
        public final String keyIdentifier() { return keyIdentifier; }
        public final String getKeyIdentifier() { return keyIdentifier(); }
        public final DispatchKeyEventParameter code(@Nullable String code) { this.code = code; return this; }
        public final DispatchKeyEventParameter optCode(@Nullable String code) { return code(code); }
        public final String code() { return code; }
        public final String getCode() { return code(); }
        public final DispatchKeyEventParameter key(@Nullable String key) { this.key = key; return this; }
        public final DispatchKeyEventParameter optKey(@Nullable String key) { return key(key); }
        public final String key() { return key; }
        public final String getKey() { return key(); }
        public final DispatchKeyEventParameter windowsVirtualKeyCode(@Nullable Integer windowsVirtualKeyCode) { this.windowsVirtualKeyCode = windowsVirtualKeyCode; return this; }
        public final DispatchKeyEventParameter optWindowsVirtualKeyCode(@Nullable Integer windowsVirtualKeyCode) { return windowsVirtualKeyCode(windowsVirtualKeyCode); }
        public final Integer windowsVirtualKeyCode() { return windowsVirtualKeyCode; }
        public final Integer getWindowsVirtualKeyCode() { return windowsVirtualKeyCode(); }
        public final DispatchKeyEventParameter nativeVirtualKeyCode(@Nullable Integer nativeVirtualKeyCode) { this.nativeVirtualKeyCode = nativeVirtualKeyCode; return this; }
        public final DispatchKeyEventParameter optNativeVirtualKeyCode(@Nullable Integer nativeVirtualKeyCode) { return nativeVirtualKeyCode(nativeVirtualKeyCode); }
        public final Integer nativeVirtualKeyCode() { return nativeVirtualKeyCode; }
        public final Integer getNativeVirtualKeyCode() { return nativeVirtualKeyCode(); }
        public final DispatchKeyEventParameter autoRepeat(@Nullable Boolean autoRepeat) { this.autoRepeat = autoRepeat; return this; }
        public final DispatchKeyEventParameter optAutoRepeat(@Nullable Boolean autoRepeat) { return autoRepeat(autoRepeat); }
        public final Boolean autoRepeat() { return autoRepeat; }
        public final Boolean getAutoRepeat() { return autoRepeat(); }
        public final DispatchKeyEventParameter isKeypad(@Nullable Boolean isKeypad) { this.isKeypad = isKeypad; return this; }
        public final DispatchKeyEventParameter optIsKeypad(@Nullable Boolean isKeypad) { return isKeypad(isKeypad); }
        public final Boolean isKeypad() { return isKeypad; }
        public final Boolean getIsKeypad() { return isKeypad(); }
        public final DispatchKeyEventParameter isSystemKey(@Nullable Boolean isSystemKey) { this.isSystemKey = isSystemKey; return this; }
        public final DispatchKeyEventParameter optIsSystemKey(@Nullable Boolean isSystemKey) { return isSystemKey(isSystemKey); }
        public final Boolean isSystemKey() { return isSystemKey; }
        public final Boolean getIsSystemKey() { return isSystemKey(); }
        public final DispatchKeyEventParameter location(@Nullable Integer location) { this.location = location; return this; }
        public final DispatchKeyEventParameter optLocation(@Nullable Integer location) { return location(location); }
        public final Integer location() { return location; }
        public final Integer getLocation() { return location(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (type == null) throw new IllegalArgumentException("Input.DispatchKeyEventParameter.type is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"type\":").append(type);
            if (modifiers != null) strBuilder.append(",\"modifiers\":").append(modifiers);
            if (timestamp != null) timestamp.toJson(strBuilder.append(",\"timestamp\":"));
            if (text != null) strBuilder.append(",\"text\":").append('"').append(DomainBase.escapeJson(text)).append('"');
            if (unmodifiedText != null) strBuilder.append(",\"unmodifiedText\":").append('"').append(DomainBase.escapeJson(unmodifiedText)).append('"');
            if (keyIdentifier != null) strBuilder.append(",\"keyIdentifier\":").append('"').append(DomainBase.escapeJson(keyIdentifier)).append('"');
            if (code != null) strBuilder.append(",\"code\":").append('"').append(DomainBase.escapeJson(code)).append('"');
            if (key != null) strBuilder.append(",\"key\":").append('"').append(DomainBase.escapeJson(key)).append('"');
            if (windowsVirtualKeyCode != null) strBuilder.append(",\"windowsVirtualKeyCode\":").append(windowsVirtualKeyCode);
            if (nativeVirtualKeyCode != null) strBuilder.append(",\"nativeVirtualKeyCode\":").append(nativeVirtualKeyCode);
            if (autoRepeat != null) strBuilder.append(",\"autoRepeat\":").append(autoRepeat);
            if (isKeypad != null) strBuilder.append(",\"isKeypad\":").append(isKeypad);
            if (isSystemKey != null) strBuilder.append(",\"isSystemKey\":").append(isSystemKey);
            if (location != null) strBuilder.append(",\"location\":").append(location);
            strBuilder.append('}');
            return strBuilder;
        }
        public DispatchKeyEventParameter() {}
        public DispatchKeyEventParameter(
            @JsonProperty("type")Type type,
            @Nullable @JsonProperty("modifiers")Integer modifiers,
            @Nullable @JsonProperty("timestamp")TimeSinceEpoch timestamp,
            @Nullable @JsonProperty("text")String text,
            @Nullable @JsonProperty("unmodifiedText")String unmodifiedText,
            @Nullable @JsonProperty("keyIdentifier")String keyIdentifier,
            @Nullable @JsonProperty("code")String code,
            @Nullable @JsonProperty("key")String key,
            @Nullable @JsonProperty("windowsVirtualKeyCode")Integer windowsVirtualKeyCode,
            @Nullable @JsonProperty("nativeVirtualKeyCode")Integer nativeVirtualKeyCode,
            @Nullable @JsonProperty("autoRepeat")Boolean autoRepeat,
            @Nullable @JsonProperty("isKeypad")Boolean isKeypad,
            @Nullable @JsonProperty("isSystemKey")Boolean isSystemKey,
            @Nullable @JsonProperty("location")Integer location
        ) {
            this();
            this.type = type;
            this.modifiers = modifiers;
            this.timestamp = timestamp;
            this.text = text;
            this.unmodifiedText = unmodifiedText;
            this.keyIdentifier = keyIdentifier;
            this.code = code;
            this.key = key;
            this.windowsVirtualKeyCode = windowsVirtualKeyCode;
            this.nativeVirtualKeyCode = nativeVirtualKeyCode;
            this.autoRepeat = autoRepeat;
            this.isKeypad = isKeypad;
            this.isSystemKey = isSystemKey;
            this.location = location;
        }
        public CompletableFuture<DispatchKeyEventResult> call() {
            return super.call("Input.dispatchKeyEvent", DispatchKeyEventResult.class,
                (code, msg)->new DispatchKeyEventResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<DispatchKeyEventResult> callAsync() {
            return super.callAsync("Input.dispatchKeyEvent", DispatchKeyEventResult.class,
                (code, msg)->new DispatchKeyEventResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<DispatchKeyEventResult> callAsync(Executor exec) {
            return super.callAsync("Input.dispatchKeyEvent", DispatchKeyEventResult.class,
                (code, msg)->new DispatchKeyEventResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of dispatchKeyEvent.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class DispatchKeyEventResult extends ResultBase {
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
        public DispatchKeyEventResult() { super(); }
        public DispatchKeyEventResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**This method emulates inserting text that doesn't come from a key press,
for example an emoji keyboard or an IME.
    <p><strong>Experimental.</strong></p>*/
    public InsertTextParameter insertText() { final InsertTextParameter v = new InsertTextParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of insertText.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class InsertTextParameter extends CommandBase {
        /**The text to insert.*/
        private String text;
        public final InsertTextParameter text(String text) { this.text = text; return this; }
        public final InsertTextParameter setText(String text) { return text(text); }
        public final String text() { return text; }
        public final String getText() { return text(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (text == null) throw new IllegalArgumentException("Input.InsertTextParameter.text is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"text\":").append('"').append(DomainBase.escapeJson(text)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public InsertTextParameter() {}
        public InsertTextParameter(
            @JsonProperty("text")String text
        ) {
            this();
            this.text = text;
        }
        public CompletableFuture<InsertTextResult> call() {
            return super.call("Input.insertText", InsertTextResult.class,
                (code, msg)->new InsertTextResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<InsertTextResult> callAsync() {
            return super.callAsync("Input.insertText", InsertTextResult.class,
                (code, msg)->new InsertTextResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<InsertTextResult> callAsync(Executor exec) {
            return super.callAsync("Input.insertText", InsertTextResult.class,
                (code, msg)->new InsertTextResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of insertText.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class InsertTextResult extends ResultBase {
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
        public InsertTextResult() { super(); }
        public InsertTextResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Dispatches a mouse event to the page.*/
    public DispatchMouseEventParameter dispatchMouseEvent() { final DispatchMouseEventParameter v = new DispatchMouseEventParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of dispatchMouseEvent.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class DispatchMouseEventParameter extends CommandBase {
        /**Type of the mouse event.*/
        @ParametersAreNonnullByDefault public enum Type implements CommonDomainType {
            MousePressed("mousePressed"),
            MouseReleased("mouseReleased"),
            MouseMoved("mouseMoved"),
            MouseWheel("mouseWheel");

            private final String _value;
            /**Convert string representation to type.
             @throws IllegalArgumentException if given value cannot convert to enum type. */
            @JsonCreator public static Type of(String value) {
                return Enum.valueOf(Type.class, value.substring(0, 1).toUpperCase() + value.substring(1));
            }
            Type(String value) { _value = value; }
            /**Check if parameter fields of method are all valid. */
            @Override public void check() throws IllegalArgumentException { /* Need not check */ }
            /**Convert method parameter object into json string and append into string builder.
             @return string builder instance that is given in parameter (for chaining coding style use.) */
            @Override public StringBuilder toJson(StringBuilder strBuilder) { return strBuilder.append('"').append(_value).append('"'); }
            @Override public String toString() { return "\"" + _value + "\""; }
        }
        private Type type;
        /**X coordinate of the event relative to the main frame's viewport in CSS pixels.*/
        private Double x;
        /**Y coordinate of the event relative to the main frame's viewport in CSS pixels. 0 refers to
the top of the viewport and Y increases as it proceeds towards the bottom of the viewport.*/
        private Double y;
        /**Bit field representing pressed modifier keys. Alt=1, Ctrl=2, Meta/Command=4, Shift=8
(default: 0).
        <em>Optional.</em>*/
        private Integer modifiers;
        /**Time at which the event occurred.
        <em>Optional.</em>*/
        private TimeSinceEpoch timestamp;
        /**Mouse button (default: "none").
        <em>Optional.</em>*/
        @ParametersAreNonnullByDefault public enum Button implements CommonDomainType {
            None("none"),
            Left("left"),
            Middle("middle"),
            Right("right");

            private final String _value;
            /**Convert string representation to type.
             @throws IllegalArgumentException if given value cannot convert to enum type. */
            @JsonCreator public static Button of(String value) {
                return Enum.valueOf(Button.class, value.substring(0, 1).toUpperCase() + value.substring(1));
            }
            Button(String value) { _value = value; }
            /**Check if parameter fields of method are all valid. */
            @Override public void check() throws IllegalArgumentException { /* Need not check */ }
            /**Convert method parameter object into json string and append into string builder.
             @return string builder instance that is given in parameter (for chaining coding style use.) */
            @Override public StringBuilder toJson(StringBuilder strBuilder) { return strBuilder.append('"').append(_value).append('"'); }
            @Override public String toString() { return "\"" + _value + "\""; }
        }
        private Button button;
        /**Number of times the mouse button was clicked (default: 0).
        <em>Optional.</em>*/
        private Integer clickCount;
        /**X delta in CSS pixels for mouse wheel event (default: 0).
        <em>Optional.</em>*/
        private Double deltaX;
        /**Y delta in CSS pixels for mouse wheel event (default: 0).
        <em>Optional.</em>*/
        private Double deltaY;
        public final DispatchMouseEventParameter type(Type type) { this.type = type; return this; }
        public final DispatchMouseEventParameter setType(Type type) { return type(type); }
        public final Type type() { return type; }
        public final Type getType() { return type(); }
        public final DispatchMouseEventParameter x(Double x) { this.x = x; return this; }
        public final DispatchMouseEventParameter setX(Double x) { return x(x); }
        public final Double x() { return x; }
        public final Double getX() { return x(); }
        public final DispatchMouseEventParameter y(Double y) { this.y = y; return this; }
        public final DispatchMouseEventParameter setY(Double y) { return y(y); }
        public final Double y() { return y; }
        public final Double getY() { return y(); }
        public final DispatchMouseEventParameter modifiers(@Nullable Integer modifiers) { this.modifiers = modifiers; return this; }
        public final DispatchMouseEventParameter optModifiers(@Nullable Integer modifiers) { return modifiers(modifiers); }
        public final Integer modifiers() { return modifiers; }
        public final Integer getModifiers() { return modifiers(); }
        public final DispatchMouseEventParameter timestamp(@Nullable TimeSinceEpoch timestamp) { this.timestamp = timestamp; return this; }
        public final DispatchMouseEventParameter optTimestamp(@Nullable TimeSinceEpoch timestamp) { return timestamp(timestamp); }
        public final TimeSinceEpoch timestamp() { return timestamp; }
        public final TimeSinceEpoch getTimestamp() { return timestamp(); }
        public final DispatchMouseEventParameter button(@Nullable Button button) { this.button = button; return this; }
        public final DispatchMouseEventParameter optButton(@Nullable Button button) { return button(button); }
        public final Button button() { return button; }
        public final Button getButton() { return button(); }
        public final DispatchMouseEventParameter clickCount(@Nullable Integer clickCount) { this.clickCount = clickCount; return this; }
        public final DispatchMouseEventParameter optClickCount(@Nullable Integer clickCount) { return clickCount(clickCount); }
        public final Integer clickCount() { return clickCount; }
        public final Integer getClickCount() { return clickCount(); }
        public final DispatchMouseEventParameter deltaX(@Nullable Double deltaX) { this.deltaX = deltaX; return this; }
        public final DispatchMouseEventParameter optDeltaX(@Nullable Double deltaX) { return deltaX(deltaX); }
        public final Double deltaX() { return deltaX; }
        public final Double getDeltaX() { return deltaX(); }
        public final DispatchMouseEventParameter deltaY(@Nullable Double deltaY) { this.deltaY = deltaY; return this; }
        public final DispatchMouseEventParameter optDeltaY(@Nullable Double deltaY) { return deltaY(deltaY); }
        public final Double deltaY() { return deltaY; }
        public final Double getDeltaY() { return deltaY(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (type == null) throw new IllegalArgumentException("Input.DispatchMouseEventParameter.type is necessary field.");
            if (x == null) throw new IllegalArgumentException("Input.DispatchMouseEventParameter.x is necessary field.");
            if (y == null) throw new IllegalArgumentException("Input.DispatchMouseEventParameter.y is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"type\":").append(type);
            strBuilder.append(",\"x\":").append(x);
            strBuilder.append(",\"y\":").append(y);
            if (modifiers != null) strBuilder.append(",\"modifiers\":").append(modifiers);
            if (timestamp != null) timestamp.toJson(strBuilder.append(",\"timestamp\":"));
            if (button != null) strBuilder.append(",\"button\":").append(button);
            if (clickCount != null) strBuilder.append(",\"clickCount\":").append(clickCount);
            if (deltaX != null) strBuilder.append(",\"deltaX\":").append(deltaX);
            if (deltaY != null) strBuilder.append(",\"deltaY\":").append(deltaY);
            strBuilder.append('}');
            return strBuilder;
        }
        public DispatchMouseEventParameter() {}
        public DispatchMouseEventParameter(
            @JsonProperty("type")Type type,
            @JsonProperty("x")Double x,
            @JsonProperty("y")Double y,
            @Nullable @JsonProperty("modifiers")Integer modifiers,
            @Nullable @JsonProperty("timestamp")TimeSinceEpoch timestamp,
            @Nullable @JsonProperty("button")Button button,
            @Nullable @JsonProperty("clickCount")Integer clickCount,
            @Nullable @JsonProperty("deltaX")Double deltaX,
            @Nullable @JsonProperty("deltaY")Double deltaY
        ) {
            this();
            this.type = type;
            this.x = x;
            this.y = y;
            this.modifiers = modifiers;
            this.timestamp = timestamp;
            this.button = button;
            this.clickCount = clickCount;
            this.deltaX = deltaX;
            this.deltaY = deltaY;
        }
        public CompletableFuture<DispatchMouseEventResult> call() {
            return super.call("Input.dispatchMouseEvent", DispatchMouseEventResult.class,
                (code, msg)->new DispatchMouseEventResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<DispatchMouseEventResult> callAsync() {
            return super.callAsync("Input.dispatchMouseEvent", DispatchMouseEventResult.class,
                (code, msg)->new DispatchMouseEventResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<DispatchMouseEventResult> callAsync(Executor exec) {
            return super.callAsync("Input.dispatchMouseEvent", DispatchMouseEventResult.class,
                (code, msg)->new DispatchMouseEventResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of dispatchMouseEvent.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class DispatchMouseEventResult extends ResultBase {
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
        public DispatchMouseEventResult() { super(); }
        public DispatchMouseEventResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Dispatches a touch event to the page.*/
    public DispatchTouchEventParameter dispatchTouchEvent() { final DispatchTouchEventParameter v = new DispatchTouchEventParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of dispatchTouchEvent.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class DispatchTouchEventParameter extends CommandBase {
        /**Type of the touch event. TouchEnd and TouchCancel must not contain any touch points, while
TouchStart and TouchMove must contains at least one.*/
        @ParametersAreNonnullByDefault public enum Type implements CommonDomainType {
            TouchStart("touchStart"),
            TouchEnd("touchEnd"),
            TouchMove("touchMove"),
            TouchCancel("touchCancel");

            private final String _value;
            /**Convert string representation to type.
             @throws IllegalArgumentException if given value cannot convert to enum type. */
            @JsonCreator public static Type of(String value) {
                return Enum.valueOf(Type.class, value.substring(0, 1).toUpperCase() + value.substring(1));
            }
            Type(String value) { _value = value; }
            /**Check if parameter fields of method are all valid. */
            @Override public void check() throws IllegalArgumentException { /* Need not check */ }
            /**Convert method parameter object into json string and append into string builder.
             @return string builder instance that is given in parameter (for chaining coding style use.) */
            @Override public StringBuilder toJson(StringBuilder strBuilder) { return strBuilder.append('"').append(_value).append('"'); }
            @Override public String toString() { return "\"" + _value + "\""; }
        }
        private Type type;
        /**Active touch points on the touch device. One event per any changed point (compared to
previous touch event in a sequence) is generated, emulating pressing/moving/releasing points
one by one.*/
        private List<TouchPoint> touchPoints;
        /**Bit field representing pressed modifier keys. Alt=1, Ctrl=2, Meta/Command=4, Shift=8
(default: 0).
        <em>Optional.</em>*/
        private Integer modifiers;
        /**Time at which the event occurred.
        <em>Optional.</em>*/
        private TimeSinceEpoch timestamp;
        public final DispatchTouchEventParameter type(Type type) { this.type = type; return this; }
        public final DispatchTouchEventParameter setType(Type type) { return type(type); }
        public final Type type() { return type; }
        public final Type getType() { return type(); }
        public final DispatchTouchEventParameter touchPoints(List<TouchPoint> touchPoints) { this.touchPoints = touchPoints; return this; }
        public final DispatchTouchEventParameter setTouchPoints(List<TouchPoint> touchPoints) { return touchPoints(touchPoints); }
        public final List<TouchPoint> touchPoints() { return touchPoints; }
        public final List<TouchPoint> getTouchPoints() { return touchPoints(); }
        public final DispatchTouchEventParameter modifiers(@Nullable Integer modifiers) { this.modifiers = modifiers; return this; }
        public final DispatchTouchEventParameter optModifiers(@Nullable Integer modifiers) { return modifiers(modifiers); }
        public final Integer modifiers() { return modifiers; }
        public final Integer getModifiers() { return modifiers(); }
        public final DispatchTouchEventParameter timestamp(@Nullable TimeSinceEpoch timestamp) { this.timestamp = timestamp; return this; }
        public final DispatchTouchEventParameter optTimestamp(@Nullable TimeSinceEpoch timestamp) { return timestamp(timestamp); }
        public final TimeSinceEpoch timestamp() { return timestamp; }
        public final TimeSinceEpoch getTimestamp() { return timestamp(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (type == null) throw new IllegalArgumentException("Input.DispatchTouchEventParameter.type is necessary field.");
            if (touchPoints == null) throw new IllegalArgumentException("Input.DispatchTouchEventParameter.touchPoints is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"type\":").append(type);
                        strBuilder.append(",\"touchPoints\":[");
            touchPoints.get(0).toJson(strBuilder);
            for (int i = 1; i < touchPoints.size(); ++i)
                touchPoints.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            if (modifiers != null) strBuilder.append(",\"modifiers\":").append(modifiers);
            if (timestamp != null) timestamp.toJson(strBuilder.append(",\"timestamp\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public DispatchTouchEventParameter() {}
        public DispatchTouchEventParameter(
            @JsonProperty("type")Type type,
            @JsonProperty("touchPoints")List<TouchPoint> touchPoints,
            @Nullable @JsonProperty("modifiers")Integer modifiers,
            @Nullable @JsonProperty("timestamp")TimeSinceEpoch timestamp
        ) {
            this();
            this.type = type;
            this.touchPoints = touchPoints;
            this.modifiers = modifiers;
            this.timestamp = timestamp;
        }
        public CompletableFuture<DispatchTouchEventResult> call() {
            return super.call("Input.dispatchTouchEvent", DispatchTouchEventResult.class,
                (code, msg)->new DispatchTouchEventResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<DispatchTouchEventResult> callAsync() {
            return super.callAsync("Input.dispatchTouchEvent", DispatchTouchEventResult.class,
                (code, msg)->new DispatchTouchEventResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<DispatchTouchEventResult> callAsync(Executor exec) {
            return super.callAsync("Input.dispatchTouchEvent", DispatchTouchEventResult.class,
                (code, msg)->new DispatchTouchEventResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of dispatchTouchEvent.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class DispatchTouchEventResult extends ResultBase {
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
        public DispatchTouchEventResult() { super(); }
        public DispatchTouchEventResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Emulates touch event from the mouse event parameters.
    <p><strong>Experimental.</strong></p>*/
    public EmulateTouchFromMouseEventParameter emulateTouchFromMouseEvent() { final EmulateTouchFromMouseEventParameter v = new EmulateTouchFromMouseEventParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of emulateTouchFromMouseEvent.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class EmulateTouchFromMouseEventParameter extends CommandBase {
        /**Type of the mouse event.*/
        @ParametersAreNonnullByDefault public enum Type implements CommonDomainType {
            MousePressed("mousePressed"),
            MouseReleased("mouseReleased"),
            MouseMoved("mouseMoved"),
            MouseWheel("mouseWheel");

            private final String _value;
            /**Convert string representation to type.
             @throws IllegalArgumentException if given value cannot convert to enum type. */
            @JsonCreator public static Type of(String value) {
                return Enum.valueOf(Type.class, value.substring(0, 1).toUpperCase() + value.substring(1));
            }
            Type(String value) { _value = value; }
            /**Check if parameter fields of method are all valid. */
            @Override public void check() throws IllegalArgumentException { /* Need not check */ }
            /**Convert method parameter object into json string and append into string builder.
             @return string builder instance that is given in parameter (for chaining coding style use.) */
            @Override public StringBuilder toJson(StringBuilder strBuilder) { return strBuilder.append('"').append(_value).append('"'); }
            @Override public String toString() { return "\"" + _value + "\""; }
        }
        private Type type;
        /**X coordinate of the mouse pointer in DIP.*/
        private Integer x;
        /**Y coordinate of the mouse pointer in DIP.*/
        private Integer y;
        /**Mouse button.*/
        @ParametersAreNonnullByDefault public enum Button implements CommonDomainType {
            None("none"),
            Left("left"),
            Middle("middle"),
            Right("right");

            private final String _value;
            /**Convert string representation to type.
             @throws IllegalArgumentException if given value cannot convert to enum type. */
            @JsonCreator public static Button of(String value) {
                return Enum.valueOf(Button.class, value.substring(0, 1).toUpperCase() + value.substring(1));
            }
            Button(String value) { _value = value; }
            /**Check if parameter fields of method are all valid. */
            @Override public void check() throws IllegalArgumentException { /* Need not check */ }
            /**Convert method parameter object into json string and append into string builder.
             @return string builder instance that is given in parameter (for chaining coding style use.) */
            @Override public StringBuilder toJson(StringBuilder strBuilder) { return strBuilder.append('"').append(_value).append('"'); }
            @Override public String toString() { return "\"" + _value + "\""; }
        }
        private Button button;
        /**Time at which the event occurred (default: current time).
        <em>Optional.</em>*/
        private TimeSinceEpoch timestamp;
        /**X delta in DIP for mouse wheel event (default: 0).
        <em>Optional.</em>*/
        private Double deltaX;
        /**Y delta in DIP for mouse wheel event (default: 0).
        <em>Optional.</em>*/
        private Double deltaY;
        /**Bit field representing pressed modifier keys. Alt=1, Ctrl=2, Meta/Command=4, Shift=8
(default: 0).
        <em>Optional.</em>*/
        private Integer modifiers;
        /**Number of times the mouse button was clicked (default: 0).
        <em>Optional.</em>*/
        private Integer clickCount;
        public final EmulateTouchFromMouseEventParameter type(Type type) { this.type = type; return this; }
        public final EmulateTouchFromMouseEventParameter setType(Type type) { return type(type); }
        public final Type type() { return type; }
        public final Type getType() { return type(); }
        public final EmulateTouchFromMouseEventParameter x(Integer x) { this.x = x; return this; }
        public final EmulateTouchFromMouseEventParameter setX(Integer x) { return x(x); }
        public final Integer x() { return x; }
        public final Integer getX() { return x(); }
        public final EmulateTouchFromMouseEventParameter y(Integer y) { this.y = y; return this; }
        public final EmulateTouchFromMouseEventParameter setY(Integer y) { return y(y); }
        public final Integer y() { return y; }
        public final Integer getY() { return y(); }
        public final EmulateTouchFromMouseEventParameter button(Button button) { this.button = button; return this; }
        public final EmulateTouchFromMouseEventParameter setButton(Button button) { return button(button); }
        public final Button button() { return button; }
        public final Button getButton() { return button(); }
        public final EmulateTouchFromMouseEventParameter timestamp(@Nullable TimeSinceEpoch timestamp) { this.timestamp = timestamp; return this; }
        public final EmulateTouchFromMouseEventParameter optTimestamp(@Nullable TimeSinceEpoch timestamp) { return timestamp(timestamp); }
        public final TimeSinceEpoch timestamp() { return timestamp; }
        public final TimeSinceEpoch getTimestamp() { return timestamp(); }
        public final EmulateTouchFromMouseEventParameter deltaX(@Nullable Double deltaX) { this.deltaX = deltaX; return this; }
        public final EmulateTouchFromMouseEventParameter optDeltaX(@Nullable Double deltaX) { return deltaX(deltaX); }
        public final Double deltaX() { return deltaX; }
        public final Double getDeltaX() { return deltaX(); }
        public final EmulateTouchFromMouseEventParameter deltaY(@Nullable Double deltaY) { this.deltaY = deltaY; return this; }
        public final EmulateTouchFromMouseEventParameter optDeltaY(@Nullable Double deltaY) { return deltaY(deltaY); }
        public final Double deltaY() { return deltaY; }
        public final Double getDeltaY() { return deltaY(); }
        public final EmulateTouchFromMouseEventParameter modifiers(@Nullable Integer modifiers) { this.modifiers = modifiers; return this; }
        public final EmulateTouchFromMouseEventParameter optModifiers(@Nullable Integer modifiers) { return modifiers(modifiers); }
        public final Integer modifiers() { return modifiers; }
        public final Integer getModifiers() { return modifiers(); }
        public final EmulateTouchFromMouseEventParameter clickCount(@Nullable Integer clickCount) { this.clickCount = clickCount; return this; }
        public final EmulateTouchFromMouseEventParameter optClickCount(@Nullable Integer clickCount) { return clickCount(clickCount); }
        public final Integer clickCount() { return clickCount; }
        public final Integer getClickCount() { return clickCount(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (type == null) throw new IllegalArgumentException("Input.EmulateTouchFromMouseEventParameter.type is necessary field.");
            if (x == null) throw new IllegalArgumentException("Input.EmulateTouchFromMouseEventParameter.x is necessary field.");
            if (y == null) throw new IllegalArgumentException("Input.EmulateTouchFromMouseEventParameter.y is necessary field.");
            if (button == null) throw new IllegalArgumentException("Input.EmulateTouchFromMouseEventParameter.button is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"type\":").append(type);
            strBuilder.append(",\"x\":").append(x);
            strBuilder.append(",\"y\":").append(y);
            strBuilder.append(",\"button\":").append(button);
            if (timestamp != null) timestamp.toJson(strBuilder.append(",\"timestamp\":"));
            if (deltaX != null) strBuilder.append(",\"deltaX\":").append(deltaX);
            if (deltaY != null) strBuilder.append(",\"deltaY\":").append(deltaY);
            if (modifiers != null) strBuilder.append(",\"modifiers\":").append(modifiers);
            if (clickCount != null) strBuilder.append(",\"clickCount\":").append(clickCount);
            strBuilder.append('}');
            return strBuilder;
        }
        public EmulateTouchFromMouseEventParameter() {}
        public EmulateTouchFromMouseEventParameter(
            @JsonProperty("type")Type type,
            @JsonProperty("x")Integer x,
            @JsonProperty("y")Integer y,
            @JsonProperty("button")Button button,
            @Nullable @JsonProperty("timestamp")TimeSinceEpoch timestamp,
            @Nullable @JsonProperty("deltaX")Double deltaX,
            @Nullable @JsonProperty("deltaY")Double deltaY,
            @Nullable @JsonProperty("modifiers")Integer modifiers,
            @Nullable @JsonProperty("clickCount")Integer clickCount
        ) {
            this();
            this.type = type;
            this.x = x;
            this.y = y;
            this.button = button;
            this.timestamp = timestamp;
            this.deltaX = deltaX;
            this.deltaY = deltaY;
            this.modifiers = modifiers;
            this.clickCount = clickCount;
        }
        public CompletableFuture<EmulateTouchFromMouseEventResult> call() {
            return super.call("Input.emulateTouchFromMouseEvent", EmulateTouchFromMouseEventResult.class,
                (code, msg)->new EmulateTouchFromMouseEventResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<EmulateTouchFromMouseEventResult> callAsync() {
            return super.callAsync("Input.emulateTouchFromMouseEvent", EmulateTouchFromMouseEventResult.class,
                (code, msg)->new EmulateTouchFromMouseEventResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<EmulateTouchFromMouseEventResult> callAsync(Executor exec) {
            return super.callAsync("Input.emulateTouchFromMouseEvent", EmulateTouchFromMouseEventResult.class,
                (code, msg)->new EmulateTouchFromMouseEventResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of emulateTouchFromMouseEvent.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class EmulateTouchFromMouseEventResult extends ResultBase {
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
        public EmulateTouchFromMouseEventResult() { super(); }
        public EmulateTouchFromMouseEventResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Ignores input events (useful while auditing page).*/
    public SetIgnoreInputEventsParameter setIgnoreInputEvents() { final SetIgnoreInputEventsParameter v = new SetIgnoreInputEventsParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setIgnoreInputEvents.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetIgnoreInputEventsParameter extends CommandBase {
        /**Ignores input events processing when set to true.*/
        private Boolean ignore;
        public final SetIgnoreInputEventsParameter ignore(Boolean ignore) { this.ignore = ignore; return this; }
        public final SetIgnoreInputEventsParameter setIgnore(Boolean ignore) { return ignore(ignore); }
        public final Boolean ignore() { return ignore; }
        public final Boolean getIgnore() { return ignore(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (ignore == null) throw new IllegalArgumentException("Input.SetIgnoreInputEventsParameter.ignore is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"ignore\":").append(ignore);
            strBuilder.append('}');
            return strBuilder;
        }
        public SetIgnoreInputEventsParameter() {}
        public SetIgnoreInputEventsParameter(
            @JsonProperty("ignore")Boolean ignore
        ) {
            this();
            this.ignore = ignore;
        }
        public CompletableFuture<SetIgnoreInputEventsResult> call() {
            return super.call("Input.setIgnoreInputEvents", SetIgnoreInputEventsResult.class,
                (code, msg)->new SetIgnoreInputEventsResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetIgnoreInputEventsResult> callAsync() {
            return super.callAsync("Input.setIgnoreInputEvents", SetIgnoreInputEventsResult.class,
                (code, msg)->new SetIgnoreInputEventsResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetIgnoreInputEventsResult> callAsync(Executor exec) {
            return super.callAsync("Input.setIgnoreInputEvents", SetIgnoreInputEventsResult.class,
                (code, msg)->new SetIgnoreInputEventsResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setIgnoreInputEvents.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetIgnoreInputEventsResult extends ResultBase {
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
        public SetIgnoreInputEventsResult() { super(); }
        public SetIgnoreInputEventsResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Synthesizes a pinch gesture over a time period by issuing appropriate touch events.
    <p><strong>Experimental.</strong></p>*/
    public SynthesizePinchGestureParameter synthesizePinchGesture() { final SynthesizePinchGestureParameter v = new SynthesizePinchGestureParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of synthesizePinchGesture.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SynthesizePinchGestureParameter extends CommandBase {
        /**X coordinate of the start of the gesture in CSS pixels.*/
        private Double x;
        /**Y coordinate of the start of the gesture in CSS pixels.*/
        private Double y;
        /**Relative scale factor after zooming (>1.0 zooms in, <1.0 zooms out).*/
        private Double scaleFactor;
        /**Relative pointer speed in pixels per second (default: 800).
        <em>Optional.</em>*/
        private Integer relativeSpeed;
        /**Which type of input events to be generated (default: 'default', which queries the platform
for the preferred input type).
        <em>Optional.</em>*/
        private GestureSourceType gestureSourceType;
        public final SynthesizePinchGestureParameter x(Double x) { this.x = x; return this; }
        public final SynthesizePinchGestureParameter setX(Double x) { return x(x); }
        public final Double x() { return x; }
        public final Double getX() { return x(); }
        public final SynthesizePinchGestureParameter y(Double y) { this.y = y; return this; }
        public final SynthesizePinchGestureParameter setY(Double y) { return y(y); }
        public final Double y() { return y; }
        public final Double getY() { return y(); }
        public final SynthesizePinchGestureParameter scaleFactor(Double scaleFactor) { this.scaleFactor = scaleFactor; return this; }
        public final SynthesizePinchGestureParameter setScaleFactor(Double scaleFactor) { return scaleFactor(scaleFactor); }
        public final Double scaleFactor() { return scaleFactor; }
        public final Double getScaleFactor() { return scaleFactor(); }
        public final SynthesizePinchGestureParameter relativeSpeed(@Nullable Integer relativeSpeed) { this.relativeSpeed = relativeSpeed; return this; }
        public final SynthesizePinchGestureParameter optRelativeSpeed(@Nullable Integer relativeSpeed) { return relativeSpeed(relativeSpeed); }
        public final Integer relativeSpeed() { return relativeSpeed; }
        public final Integer getRelativeSpeed() { return relativeSpeed(); }
        public final SynthesizePinchGestureParameter gestureSourceType(@Nullable GestureSourceType gestureSourceType) { this.gestureSourceType = gestureSourceType; return this; }
        public final SynthesizePinchGestureParameter optGestureSourceType(@Nullable GestureSourceType gestureSourceType) { return gestureSourceType(gestureSourceType); }
        public final GestureSourceType gestureSourceType() { return gestureSourceType; }
        public final GestureSourceType getGestureSourceType() { return gestureSourceType(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (x == null) throw new IllegalArgumentException("Input.SynthesizePinchGestureParameter.x is necessary field.");
            if (y == null) throw new IllegalArgumentException("Input.SynthesizePinchGestureParameter.y is necessary field.");
            if (scaleFactor == null) throw new IllegalArgumentException("Input.SynthesizePinchGestureParameter.scaleFactor is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"x\":").append(x);
            strBuilder.append(",\"y\":").append(y);
            strBuilder.append(",\"scaleFactor\":").append(scaleFactor);
            if (relativeSpeed != null) strBuilder.append(",\"relativeSpeed\":").append(relativeSpeed);
            if (gestureSourceType != null) gestureSourceType.toJson(strBuilder.append(",\"gestureSourceType\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public SynthesizePinchGestureParameter() {}
        public SynthesizePinchGestureParameter(
            @JsonProperty("x")Double x,
            @JsonProperty("y")Double y,
            @JsonProperty("scaleFactor")Double scaleFactor,
            @Nullable @JsonProperty("relativeSpeed")Integer relativeSpeed,
            @Nullable @JsonProperty("gestureSourceType")GestureSourceType gestureSourceType
        ) {
            this();
            this.x = x;
            this.y = y;
            this.scaleFactor = scaleFactor;
            this.relativeSpeed = relativeSpeed;
            this.gestureSourceType = gestureSourceType;
        }
        public CompletableFuture<SynthesizePinchGestureResult> call() {
            return super.call("Input.synthesizePinchGesture", SynthesizePinchGestureResult.class,
                (code, msg)->new SynthesizePinchGestureResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SynthesizePinchGestureResult> callAsync() {
            return super.callAsync("Input.synthesizePinchGesture", SynthesizePinchGestureResult.class,
                (code, msg)->new SynthesizePinchGestureResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SynthesizePinchGestureResult> callAsync(Executor exec) {
            return super.callAsync("Input.synthesizePinchGesture", SynthesizePinchGestureResult.class,
                (code, msg)->new SynthesizePinchGestureResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of synthesizePinchGesture.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SynthesizePinchGestureResult extends ResultBase {
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
        public SynthesizePinchGestureResult() { super(); }
        public SynthesizePinchGestureResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Synthesizes a scroll gesture over a time period by issuing appropriate touch events.
    <p><strong>Experimental.</strong></p>*/
    public SynthesizeScrollGestureParameter synthesizeScrollGesture() { final SynthesizeScrollGestureParameter v = new SynthesizeScrollGestureParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of synthesizeScrollGesture.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SynthesizeScrollGestureParameter extends CommandBase {
        /**X coordinate of the start of the gesture in CSS pixels.*/
        private Double x;
        /**Y coordinate of the start of the gesture in CSS pixels.*/
        private Double y;
        /**The distance to scroll along the X axis (positive to scroll left).
        <em>Optional.</em>*/
        private Double xDistance;
        /**The distance to scroll along the Y axis (positive to scroll up).
        <em>Optional.</em>*/
        private Double yDistance;
        /**The number of additional pixels to scroll back along the X axis, in addition to the given
distance.
        <em>Optional.</em>*/
        private Double xOverscroll;
        /**The number of additional pixels to scroll back along the Y axis, in addition to the given
distance.
        <em>Optional.</em>*/
        private Double yOverscroll;
        /**Prevent fling (default: true).
        <em>Optional.</em>*/
        private Boolean preventFling;
        /**Swipe speed in pixels per second (default: 800).
        <em>Optional.</em>*/
        private Integer speed;
        /**Which type of input events to be generated (default: 'default', which queries the platform
for the preferred input type).
        <em>Optional.</em>*/
        private GestureSourceType gestureSourceType;
        /**The number of times to repeat the gesture (default: 0).
        <em>Optional.</em>*/
        private Integer repeatCount;
        /**The number of milliseconds delay between each repeat. (default: 250).
        <em>Optional.</em>*/
        private Integer repeatDelayMs;
        /**The name of the interaction markers to generate, if not empty (default: "").
        <em>Optional.</em>*/
        private String interactionMarkerName;
        public final SynthesizeScrollGestureParameter x(Double x) { this.x = x; return this; }
        public final SynthesizeScrollGestureParameter setX(Double x) { return x(x); }
        public final Double x() { return x; }
        public final Double getX() { return x(); }
        public final SynthesizeScrollGestureParameter y(Double y) { this.y = y; return this; }
        public final SynthesizeScrollGestureParameter setY(Double y) { return y(y); }
        public final Double y() { return y; }
        public final Double getY() { return y(); }
        public final SynthesizeScrollGestureParameter xDistance(@Nullable Double xDistance) { this.xDistance = xDistance; return this; }
        public final SynthesizeScrollGestureParameter optXDistance(@Nullable Double xDistance) { return xDistance(xDistance); }
        public final Double xDistance() { return xDistance; }
        public final Double getXDistance() { return xDistance(); }
        public final SynthesizeScrollGestureParameter yDistance(@Nullable Double yDistance) { this.yDistance = yDistance; return this; }
        public final SynthesizeScrollGestureParameter optYDistance(@Nullable Double yDistance) { return yDistance(yDistance); }
        public final Double yDistance() { return yDistance; }
        public final Double getYDistance() { return yDistance(); }
        public final SynthesizeScrollGestureParameter xOverscroll(@Nullable Double xOverscroll) { this.xOverscroll = xOverscroll; return this; }
        public final SynthesizeScrollGestureParameter optXOverscroll(@Nullable Double xOverscroll) { return xOverscroll(xOverscroll); }
        public final Double xOverscroll() { return xOverscroll; }
        public final Double getXOverscroll() { return xOverscroll(); }
        public final SynthesizeScrollGestureParameter yOverscroll(@Nullable Double yOverscroll) { this.yOverscroll = yOverscroll; return this; }
        public final SynthesizeScrollGestureParameter optYOverscroll(@Nullable Double yOverscroll) { return yOverscroll(yOverscroll); }
        public final Double yOverscroll() { return yOverscroll; }
        public final Double getYOverscroll() { return yOverscroll(); }
        public final SynthesizeScrollGestureParameter preventFling(@Nullable Boolean preventFling) { this.preventFling = preventFling; return this; }
        public final SynthesizeScrollGestureParameter optPreventFling(@Nullable Boolean preventFling) { return preventFling(preventFling); }
        public final Boolean preventFling() { return preventFling; }
        public final Boolean getPreventFling() { return preventFling(); }
        public final SynthesizeScrollGestureParameter speed(@Nullable Integer speed) { this.speed = speed; return this; }
        public final SynthesizeScrollGestureParameter optSpeed(@Nullable Integer speed) { return speed(speed); }
        public final Integer speed() { return speed; }
        public final Integer getSpeed() { return speed(); }
        public final SynthesizeScrollGestureParameter gestureSourceType(@Nullable GestureSourceType gestureSourceType) { this.gestureSourceType = gestureSourceType; return this; }
        public final SynthesizeScrollGestureParameter optGestureSourceType(@Nullable GestureSourceType gestureSourceType) { return gestureSourceType(gestureSourceType); }
        public final GestureSourceType gestureSourceType() { return gestureSourceType; }
        public final GestureSourceType getGestureSourceType() { return gestureSourceType(); }
        public final SynthesizeScrollGestureParameter repeatCount(@Nullable Integer repeatCount) { this.repeatCount = repeatCount; return this; }
        public final SynthesizeScrollGestureParameter optRepeatCount(@Nullable Integer repeatCount) { return repeatCount(repeatCount); }
        public final Integer repeatCount() { return repeatCount; }
        public final Integer getRepeatCount() { return repeatCount(); }
        public final SynthesizeScrollGestureParameter repeatDelayMs(@Nullable Integer repeatDelayMs) { this.repeatDelayMs = repeatDelayMs; return this; }
        public final SynthesizeScrollGestureParameter optRepeatDelayMs(@Nullable Integer repeatDelayMs) { return repeatDelayMs(repeatDelayMs); }
        public final Integer repeatDelayMs() { return repeatDelayMs; }
        public final Integer getRepeatDelayMs() { return repeatDelayMs(); }
        public final SynthesizeScrollGestureParameter interactionMarkerName(@Nullable String interactionMarkerName) { this.interactionMarkerName = interactionMarkerName; return this; }
        public final SynthesizeScrollGestureParameter optInteractionMarkerName(@Nullable String interactionMarkerName) { return interactionMarkerName(interactionMarkerName); }
        public final String interactionMarkerName() { return interactionMarkerName; }
        public final String getInteractionMarkerName() { return interactionMarkerName(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (x == null) throw new IllegalArgumentException("Input.SynthesizeScrollGestureParameter.x is necessary field.");
            if (y == null) throw new IllegalArgumentException("Input.SynthesizeScrollGestureParameter.y is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"x\":").append(x);
            strBuilder.append(",\"y\":").append(y);
            if (xDistance != null) strBuilder.append(",\"xDistance\":").append(xDistance);
            if (yDistance != null) strBuilder.append(",\"yDistance\":").append(yDistance);
            if (xOverscroll != null) strBuilder.append(",\"xOverscroll\":").append(xOverscroll);
            if (yOverscroll != null) strBuilder.append(",\"yOverscroll\":").append(yOverscroll);
            if (preventFling != null) strBuilder.append(",\"preventFling\":").append(preventFling);
            if (speed != null) strBuilder.append(",\"speed\":").append(speed);
            if (gestureSourceType != null) gestureSourceType.toJson(strBuilder.append(",\"gestureSourceType\":"));
            if (repeatCount != null) strBuilder.append(",\"repeatCount\":").append(repeatCount);
            if (repeatDelayMs != null) strBuilder.append(",\"repeatDelayMs\":").append(repeatDelayMs);
            if (interactionMarkerName != null) strBuilder.append(",\"interactionMarkerName\":").append('"').append(DomainBase.escapeJson(interactionMarkerName)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public SynthesizeScrollGestureParameter() {}
        public SynthesizeScrollGestureParameter(
            @JsonProperty("x")Double x,
            @JsonProperty("y")Double y,
            @Nullable @JsonProperty("xDistance")Double xDistance,
            @Nullable @JsonProperty("yDistance")Double yDistance,
            @Nullable @JsonProperty("xOverscroll")Double xOverscroll,
            @Nullable @JsonProperty("yOverscroll")Double yOverscroll,
            @Nullable @JsonProperty("preventFling")Boolean preventFling,
            @Nullable @JsonProperty("speed")Integer speed,
            @Nullable @JsonProperty("gestureSourceType")GestureSourceType gestureSourceType,
            @Nullable @JsonProperty("repeatCount")Integer repeatCount,
            @Nullable @JsonProperty("repeatDelayMs")Integer repeatDelayMs,
            @Nullable @JsonProperty("interactionMarkerName")String interactionMarkerName
        ) {
            this();
            this.x = x;
            this.y = y;
            this.xDistance = xDistance;
            this.yDistance = yDistance;
            this.xOverscroll = xOverscroll;
            this.yOverscroll = yOverscroll;
            this.preventFling = preventFling;
            this.speed = speed;
            this.gestureSourceType = gestureSourceType;
            this.repeatCount = repeatCount;
            this.repeatDelayMs = repeatDelayMs;
            this.interactionMarkerName = interactionMarkerName;
        }
        public CompletableFuture<SynthesizeScrollGestureResult> call() {
            return super.call("Input.synthesizeScrollGesture", SynthesizeScrollGestureResult.class,
                (code, msg)->new SynthesizeScrollGestureResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SynthesizeScrollGestureResult> callAsync() {
            return super.callAsync("Input.synthesizeScrollGesture", SynthesizeScrollGestureResult.class,
                (code, msg)->new SynthesizeScrollGestureResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SynthesizeScrollGestureResult> callAsync(Executor exec) {
            return super.callAsync("Input.synthesizeScrollGesture", SynthesizeScrollGestureResult.class,
                (code, msg)->new SynthesizeScrollGestureResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of synthesizeScrollGesture.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SynthesizeScrollGestureResult extends ResultBase {
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
        public SynthesizeScrollGestureResult() { super(); }
        public SynthesizeScrollGestureResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Synthesizes a tap gesture over a time period by issuing appropriate touch events.
    <p><strong>Experimental.</strong></p>*/
    public SynthesizeTapGestureParameter synthesizeTapGesture() { final SynthesizeTapGestureParameter v = new SynthesizeTapGestureParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of synthesizeTapGesture.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SynthesizeTapGestureParameter extends CommandBase {
        /**X coordinate of the start of the gesture in CSS pixels.*/
        private Double x;
        /**Y coordinate of the start of the gesture in CSS pixels.*/
        private Double y;
        /**Duration between touchdown and touchup events in ms (default: 50).
        <em>Optional.</em>*/
        private Integer duration;
        /**Number of times to perform the tap (e.g. 2 for double tap, default: 1).
        <em>Optional.</em>*/
        private Integer tapCount;
        /**Which type of input events to be generated (default: 'default', which queries the platform
for the preferred input type).
        <em>Optional.</em>*/
        private GestureSourceType gestureSourceType;
        public final SynthesizeTapGestureParameter x(Double x) { this.x = x; return this; }
        public final SynthesizeTapGestureParameter setX(Double x) { return x(x); }
        public final Double x() { return x; }
        public final Double getX() { return x(); }
        public final SynthesizeTapGestureParameter y(Double y) { this.y = y; return this; }
        public final SynthesizeTapGestureParameter setY(Double y) { return y(y); }
        public final Double y() { return y; }
        public final Double getY() { return y(); }
        public final SynthesizeTapGestureParameter duration(@Nullable Integer duration) { this.duration = duration; return this; }
        public final SynthesizeTapGestureParameter optDuration(@Nullable Integer duration) { return duration(duration); }
        public final Integer duration() { return duration; }
        public final Integer getDuration() { return duration(); }
        public final SynthesizeTapGestureParameter tapCount(@Nullable Integer tapCount) { this.tapCount = tapCount; return this; }
        public final SynthesizeTapGestureParameter optTapCount(@Nullable Integer tapCount) { return tapCount(tapCount); }
        public final Integer tapCount() { return tapCount; }
        public final Integer getTapCount() { return tapCount(); }
        public final SynthesizeTapGestureParameter gestureSourceType(@Nullable GestureSourceType gestureSourceType) { this.gestureSourceType = gestureSourceType; return this; }
        public final SynthesizeTapGestureParameter optGestureSourceType(@Nullable GestureSourceType gestureSourceType) { return gestureSourceType(gestureSourceType); }
        public final GestureSourceType gestureSourceType() { return gestureSourceType; }
        public final GestureSourceType getGestureSourceType() { return gestureSourceType(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (x == null) throw new IllegalArgumentException("Input.SynthesizeTapGestureParameter.x is necessary field.");
            if (y == null) throw new IllegalArgumentException("Input.SynthesizeTapGestureParameter.y is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"x\":").append(x);
            strBuilder.append(",\"y\":").append(y);
            if (duration != null) strBuilder.append(",\"duration\":").append(duration);
            if (tapCount != null) strBuilder.append(",\"tapCount\":").append(tapCount);
            if (gestureSourceType != null) gestureSourceType.toJson(strBuilder.append(",\"gestureSourceType\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public SynthesizeTapGestureParameter() {}
        public SynthesizeTapGestureParameter(
            @JsonProperty("x")Double x,
            @JsonProperty("y")Double y,
            @Nullable @JsonProperty("duration")Integer duration,
            @Nullable @JsonProperty("tapCount")Integer tapCount,
            @Nullable @JsonProperty("gestureSourceType")GestureSourceType gestureSourceType
        ) {
            this();
            this.x = x;
            this.y = y;
            this.duration = duration;
            this.tapCount = tapCount;
            this.gestureSourceType = gestureSourceType;
        }
        public CompletableFuture<SynthesizeTapGestureResult> call() {
            return super.call("Input.synthesizeTapGesture", SynthesizeTapGestureResult.class,
                (code, msg)->new SynthesizeTapGestureResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SynthesizeTapGestureResult> callAsync() {
            return super.callAsync("Input.synthesizeTapGesture", SynthesizeTapGestureResult.class,
                (code, msg)->new SynthesizeTapGestureResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SynthesizeTapGestureResult> callAsync(Executor exec) {
            return super.callAsync("Input.synthesizeTapGesture", SynthesizeTapGestureResult.class,
                (code, msg)->new SynthesizeTapGestureResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of synthesizeTapGesture.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SynthesizeTapGestureResult extends ResultBase {
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
        public SynthesizeTapGestureResult() { super(); }
        public SynthesizeTapGestureResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
}

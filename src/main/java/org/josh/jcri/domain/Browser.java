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

/**The Browser domain defines methods and events for browser managing.
<p>From: browser_protocol.json</p>
<p>Protocol version: 1.3</p>
 @author Joshua*/
@ParametersAreNonnullByDefault public class Browser extends DomainBase {
    public Browser(EventCenter evt, WebSocket ws) { super(evt, ws); }

    /**&lt;No document in protocol.&gt;
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class WindowID implements CommonDomainType {
        private Integer _value;
        public WindowID() {}
        public WindowID(Integer value) { _value = value; }
        public final WindowID value(Integer value) { _value = value; return this; }
        public final Integer value() { return _value; }
        public final WindowID setValue(Integer value) { return value(value); }
        public final Integer getValue() { return value(); }
        @Override public String toString() { return String.valueOf(_value); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (_value == null) throw new IllegalArgumentException("Browser.WindowID.value is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            return strBuilder.append(_value);
        }
    }

    /**The state of the browser window.
    <p><strong>Experimental.</strong></p>*/
    @ParametersAreNonnullByDefault public enum WindowState implements CommonDomainType {
        Normal("normal"),
        Minimized("minimized"),
        Maximized("maximized"),
        Fullscreen("fullscreen");

        private final String _value;
        /**Convert string representation to type.
         @throws IllegalArgumentException if given value cannot convert to enum type. */
        @JsonCreator public static WindowState of(String value) {
            return Enum.valueOf(WindowState.class, value.substring(0, 1).toUpperCase() + value.substring(1));
        }
        WindowState(String value) { _value = value; }
        /**Check if parameter fields of method are all valid. */
        @Override public void check() throws IllegalArgumentException { /* Need not check */ }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) { return strBuilder.append('"').append(_value).append('"'); }
        @Override public String toString() { return "\"" + _value + "\""; }
    }

    /**Browser window bounds information
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class Bounds implements CommonDomainType {
        /**The offset from the left edge of the screen to the window in pixels.
        <em>Optional.</em>*/
        private Integer left;
        /**The offset from the top edge of the screen to the window in pixels.
        <em>Optional.</em>*/
        private Integer top;
        /**The window width in pixels.
        <em>Optional.</em>*/
        private Integer width;
        /**The window height in pixels.
        <em>Optional.</em>*/
        private Integer height;
        /**The window state. Default to normal.
        <em>Optional.</em>*/
        private WindowState windowState;
        public final Bounds left(@Nullable Integer left) { this.left = left; return this; }
        public final Bounds optLeft(@Nullable Integer left) { return left(left); }
        public final Integer left() { return left; }
        public final Integer getLeft() { return left(); }
        public final Bounds top(@Nullable Integer top) { this.top = top; return this; }
        public final Bounds optTop(@Nullable Integer top) { return top(top); }
        public final Integer top() { return top; }
        public final Integer getTop() { return top(); }
        public final Bounds width(@Nullable Integer width) { this.width = width; return this; }
        public final Bounds optWidth(@Nullable Integer width) { return width(width); }
        public final Integer width() { return width; }
        public final Integer getWidth() { return width(); }
        public final Bounds height(@Nullable Integer height) { this.height = height; return this; }
        public final Bounds optHeight(@Nullable Integer height) { return height(height); }
        public final Integer height() { return height; }
        public final Integer getHeight() { return height(); }
        public final Bounds windowState(@Nullable WindowState windowState) { this.windowState = windowState; return this; }
        public final Bounds optWindowState(@Nullable WindowState windowState) { return windowState(windowState); }
        public final WindowState windowState() { return windowState; }
        public final WindowState getWindowState() { return windowState(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            if (left != null) strBuilder.append("\"left\":").append(left);
            if (top != null) strBuilder.append(",\"top\":").append(top);
            if (width != null) strBuilder.append(",\"width\":").append(width);
            if (height != null) strBuilder.append(",\"height\":").append(height);
            if (windowState != null) windowState.toJson(strBuilder.append(",\"windowState\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public Bounds() {}
        public Bounds(
            @Nullable @JsonProperty("left")Integer left,
            @Nullable @JsonProperty("top")Integer top,
            @Nullable @JsonProperty("width")Integer width,
            @Nullable @JsonProperty("height")Integer height,
            @Nullable @JsonProperty("windowState")WindowState windowState
        ) {
            this.left = left;
            this.top = top;
            this.width = width;
            this.height = height;
            this.windowState = windowState;
        }
    }

    /**&lt;No document in protocol.&gt;
    <p><strong>Experimental.</strong></p>*/
    @ParametersAreNonnullByDefault public enum PermissionType implements CommonDomainType {
        AccessibilityEvents("accessibilityEvents"),
        AudioCapture("audioCapture"),
        BackgroundSync("backgroundSync"),
        ClipboardRead("clipboardRead"),
        ClipboardWrite("clipboardWrite"),
        DurableStorage("durableStorage"),
        Flash("flash"),
        Geolocation("geolocation"),
        Midi("midi"),
        MidiSysex("midiSysex"),
        Notifications("notifications"),
        PaymentHandler("paymentHandler"),
        ProtectedMediaIdentifier("protectedMediaIdentifier"),
        Sensors("sensors"),
        VideoCapture("videoCapture");

        private final String _value;
        /**Convert string representation to type.
         @throws IllegalArgumentException if given value cannot convert to enum type. */
        @JsonCreator public static PermissionType of(String value) {
            return Enum.valueOf(PermissionType.class, value.substring(0, 1).toUpperCase() + value.substring(1));
        }
        PermissionType(String value) { _value = value; }
        /**Check if parameter fields of method are all valid. */
        @Override public void check() throws IllegalArgumentException { /* Need not check */ }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) { return strBuilder.append('"').append(_value).append('"'); }
        @Override public String toString() { return "\"" + _value + "\""; }
    }

    /**Chrome histogram bucket.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class Bucket implements CommonDomainType {
        /**Minimum value (inclusive).*/
        private Integer low;
        /**Maximum value (exclusive).*/
        private Integer high;
        /**Number of samples.*/
        private Integer count;
        public final Bucket low(Integer low) { this.low = low; return this; }
        public final Bucket setLow(Integer low) { return low(low); }
        public final Integer low() { return low; }
        public final Integer getLow() { return low(); }
        public final Bucket high(Integer high) { this.high = high; return this; }
        public final Bucket setHigh(Integer high) { return high(high); }
        public final Integer high() { return high; }
        public final Integer getHigh() { return high(); }
        public final Bucket count(Integer count) { this.count = count; return this; }
        public final Bucket setCount(Integer count) { return count(count); }
        public final Integer count() { return count; }
        public final Integer getCount() { return count(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (low == null) throw new IllegalArgumentException("Browser.Bucket.low is necessary field.");
            if (high == null) throw new IllegalArgumentException("Browser.Bucket.high is necessary field.");
            if (count == null) throw new IllegalArgumentException("Browser.Bucket.count is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"low\":").append(low);
            strBuilder.append(",\"high\":").append(high);
            strBuilder.append(",\"count\":").append(count);
            strBuilder.append('}');
            return strBuilder;
        }
        public Bucket() {}
        public Bucket(
            @JsonProperty("low")Integer low,
            @JsonProperty("high")Integer high,
            @JsonProperty("count")Integer count
        ) {
            this.low = low;
            this.high = high;
            this.count = count;
        }
    }

    /**Chrome histogram.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class Histogram implements CommonDomainType {
        /**Name.*/
        private String name;
        /**Sum of sample values.*/
        private Integer sum;
        /**Total number of samples.*/
        private Integer count;
        /**Buckets.*/
        private List<Bucket> buckets;
        public final Histogram name(String name) { this.name = name; return this; }
        public final Histogram setName(String name) { return name(name); }
        public final String name() { return name; }
        public final String getName() { return name(); }
        public final Histogram sum(Integer sum) { this.sum = sum; return this; }
        public final Histogram setSum(Integer sum) { return sum(sum); }
        public final Integer sum() { return sum; }
        public final Integer getSum() { return sum(); }
        public final Histogram count(Integer count) { this.count = count; return this; }
        public final Histogram setCount(Integer count) { return count(count); }
        public final Integer count() { return count; }
        public final Integer getCount() { return count(); }
        public final Histogram buckets(List<Bucket> buckets) { this.buckets = buckets; return this; }
        public final Histogram setBuckets(List<Bucket> buckets) { return buckets(buckets); }
        public final List<Bucket> buckets() { return buckets; }
        public final List<Bucket> getBuckets() { return buckets(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (name == null) throw new IllegalArgumentException("Browser.Histogram.name is necessary field.");
            if (sum == null) throw new IllegalArgumentException("Browser.Histogram.sum is necessary field.");
            if (count == null) throw new IllegalArgumentException("Browser.Histogram.count is necessary field.");
            if (buckets == null) throw new IllegalArgumentException("Browser.Histogram.buckets is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"name\":").append('"').append(DomainBase.escapeJson(name)).append('"');
            strBuilder.append(",\"sum\":").append(sum);
            strBuilder.append(",\"count\":").append(count);
                        strBuilder.append(",\"buckets\":[");
            buckets.get(0).toJson(strBuilder);
            for (int i = 1; i < buckets.size(); ++i)
                buckets.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public Histogram() {}
        public Histogram(
            @JsonProperty("name")String name,
            @JsonProperty("sum")Integer sum,
            @JsonProperty("count")Integer count,
            @JsonProperty("buckets")List<Bucket> buckets
        ) {
            this.name = name;
            this.sum = sum;
            this.count = count;
            this.buckets = buckets;
        }
    }
    /**Grant specific permissions to the given origin and reject all others.
    <p><strong>Experimental.</strong></p>*/
    public GrantPermissionsParameter grantPermissions() { final GrantPermissionsParameter v = new GrantPermissionsParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of grantPermissions.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GrantPermissionsParameter extends CommandBase {
        /**&lt;No document in protocol.&gt;*/
        private String origin;
        /**&lt;No document in protocol.&gt;*/
        private List<PermissionType> permissions;
        /**BrowserContext to override permissions. When omitted, default browser context is used.
        <em>Optional.</em>*/
        private Target.BrowserContextID browserContextId;
        public final GrantPermissionsParameter origin(String origin) { this.origin = origin; return this; }
        public final GrantPermissionsParameter setOrigin(String origin) { return origin(origin); }
        public final String origin() { return origin; }
        public final String getOrigin() { return origin(); }
        public final GrantPermissionsParameter permissions(List<PermissionType> permissions) { this.permissions = permissions; return this; }
        public final GrantPermissionsParameter setPermissions(List<PermissionType> permissions) { return permissions(permissions); }
        public final List<PermissionType> permissions() { return permissions; }
        public final List<PermissionType> getPermissions() { return permissions(); }
        public final GrantPermissionsParameter browserContextId(@Nullable Target.BrowserContextID browserContextId) { this.browserContextId = browserContextId; return this; }
        public final GrantPermissionsParameter optBrowserContextId(@Nullable Target.BrowserContextID browserContextId) { return browserContextId(browserContextId); }
        public final Target.BrowserContextID browserContextId() { return browserContextId; }
        public final Target.BrowserContextID getBrowserContextId() { return browserContextId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (origin == null) throw new IllegalArgumentException("Browser.GrantPermissionsParameter.origin is necessary field.");
            if (permissions == null) throw new IllegalArgumentException("Browser.GrantPermissionsParameter.permissions is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"origin\":").append('"').append(DomainBase.escapeJson(origin)).append('"');
                        strBuilder.append(",\"permissions\":[");
            permissions.get(0).toJson(strBuilder);
            for (int i = 1; i < permissions.size(); ++i)
                permissions.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            if (browserContextId != null) browserContextId.toJson(strBuilder.append(",\"browserContextId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public GrantPermissionsParameter() {}
        public GrantPermissionsParameter(
            @JsonProperty("origin")String origin,
            @JsonProperty("permissions")List<PermissionType> permissions,
            @Nullable @JsonProperty("browserContextId")Target.BrowserContextID browserContextId
        ) {
            this();
            this.origin = origin;
            this.permissions = permissions;
            this.browserContextId = browserContextId;
        }
        public CompletableFuture<GrantPermissionsResult> call() {
            return super.call("Browser.grantPermissions", GrantPermissionsResult.class,
                (code, msg)->new GrantPermissionsResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GrantPermissionsResult> callAsync() {
            return super.callAsync("Browser.grantPermissions", GrantPermissionsResult.class,
                (code, msg)->new GrantPermissionsResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GrantPermissionsResult> callAsync(Executor exec) {
            return super.callAsync("Browser.grantPermissions", GrantPermissionsResult.class,
                (code, msg)->new GrantPermissionsResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of grantPermissions.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GrantPermissionsResult extends ResultBase {
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
        public GrantPermissionsResult() { super(); }
        public GrantPermissionsResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Reset all permission management for all origins.
    <p><strong>Experimental.</strong></p>*/
    public ResetPermissionsParameter resetPermissions() { final ResetPermissionsParameter v = new ResetPermissionsParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of resetPermissions.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ResetPermissionsParameter extends CommandBase {
        /**BrowserContext to reset permissions. When omitted, default browser context is used.
        <em>Optional.</em>*/
        private Target.BrowserContextID browserContextId;
        public final ResetPermissionsParameter browserContextId(@Nullable Target.BrowserContextID browserContextId) { this.browserContextId = browserContextId; return this; }
        public final ResetPermissionsParameter optBrowserContextId(@Nullable Target.BrowserContextID browserContextId) { return browserContextId(browserContextId); }
        public final Target.BrowserContextID browserContextId() { return browserContextId; }
        public final Target.BrowserContextID getBrowserContextId() { return browserContextId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            if (browserContextId != null) browserContextId.toJson(strBuilder.append("\"browserContextId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public ResetPermissionsParameter() {}
        public ResetPermissionsParameter(
            @Nullable @JsonProperty("browserContextId")Target.BrowserContextID browserContextId
        ) {
            this();
            this.browserContextId = browserContextId;
        }
        public CompletableFuture<ResetPermissionsResult> call() {
            return super.call("Browser.resetPermissions", ResetPermissionsResult.class,
                (code, msg)->new ResetPermissionsResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<ResetPermissionsResult> callAsync() {
            return super.callAsync("Browser.resetPermissions", ResetPermissionsResult.class,
                (code, msg)->new ResetPermissionsResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<ResetPermissionsResult> callAsync(Executor exec) {
            return super.callAsync("Browser.resetPermissions", ResetPermissionsResult.class,
                (code, msg)->new ResetPermissionsResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of resetPermissions.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ResetPermissionsResult extends ResultBase {
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
        public ResetPermissionsResult() { super(); }
        public ResetPermissionsResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Close browser gracefully.*/
    public CloseParameter close() { final CloseParameter v = new CloseParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of close.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class CloseParameter extends CommandBase {
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
        public CloseParameter() {}
        public CompletableFuture<CloseResult> call() {
            return super.call("Browser.close", CloseResult.class,
                (code, msg)->new CloseResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<CloseResult> callAsync() {
            return super.callAsync("Browser.close", CloseResult.class,
                (code, msg)->new CloseResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<CloseResult> callAsync(Executor exec) {
            return super.callAsync("Browser.close", CloseResult.class,
                (code, msg)->new CloseResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of close.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class CloseResult extends ResultBase {
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
        public CloseResult() { super(); }
        public CloseResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Crashes browser on the main thread.
    <p><strong>Experimental.</strong></p>*/
    public CrashParameter crash() { final CrashParameter v = new CrashParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of crash.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class CrashParameter extends CommandBase {
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
        public CrashParameter() {}
        public CompletableFuture<CrashResult> call() {
            return super.call("Browser.crash", CrashResult.class,
                (code, msg)->new CrashResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<CrashResult> callAsync() {
            return super.callAsync("Browser.crash", CrashResult.class,
                (code, msg)->new CrashResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<CrashResult> callAsync(Executor exec) {
            return super.callAsync("Browser.crash", CrashResult.class,
                (code, msg)->new CrashResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of crash.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class CrashResult extends ResultBase {
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
        public CrashResult() { super(); }
        public CrashResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Returns version information.*/
    public GetVersionParameter getVersion() { final GetVersionParameter v = new GetVersionParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of getVersion.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetVersionParameter extends CommandBase {
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
        public GetVersionParameter() {}
        public CompletableFuture<GetVersionResult> call() {
            return super.call("Browser.getVersion", GetVersionResult.class,
                (code, msg)->new GetVersionResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetVersionResult> callAsync() {
            return super.callAsync("Browser.getVersion", GetVersionResult.class,
                (code, msg)->new GetVersionResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetVersionResult> callAsync(Executor exec) {
            return super.callAsync("Browser.getVersion", GetVersionResult.class,
                (code, msg)->new GetVersionResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of getVersion.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetVersionResult extends ResultBase {
        /**Protocol version.*/
        private final String protocolVersion;
        /**Product name.*/
        private final String product;
        /**Product revision.*/
        private final String revision;
        /**User-Agent.*/
        private final String userAgent;
        /**V8 version.*/
        private final String jsVersion;
        public final String protocolVersion() { return protocolVersion; }
        public final String getProtocolVersion() { return protocolVersion(); }
        public final String product() { return product; }
        public final String getProduct() { return product(); }
        public final String revision() { return revision; }
        public final String getRevision() { return revision(); }
        public final String userAgent() { return userAgent; }
        public final String getUserAgent() { return userAgent(); }
        public final String jsVersion() { return jsVersion; }
        public final String getJsVersion() { return jsVersion(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"protocolVersion\":").append('"').append(DomainBase.escapeJson(protocolVersion)).append('"');
            strBuilder.append(",\"product\":").append('"').append(DomainBase.escapeJson(product)).append('"');
            strBuilder.append(",\"revision\":").append('"').append(DomainBase.escapeJson(revision)).append('"');
            strBuilder.append(",\"userAgent\":").append('"').append(DomainBase.escapeJson(userAgent)).append('"');
            strBuilder.append(",\"jsVersion\":").append('"').append(DomainBase.escapeJson(jsVersion)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public GetVersionResult(
            @JsonProperty("protocolVersion")String protocolVersion,
            @JsonProperty("product")String product,
            @JsonProperty("revision")String revision,
            @JsonProperty("userAgent")String userAgent,
            @JsonProperty("jsVersion")String jsVersion
        ) {
            this.protocolVersion = protocolVersion;
            this.product = product;
            this.revision = revision;
            this.userAgent = userAgent;
            this.jsVersion = jsVersion;
        }
        public GetVersionResult(ResultBase.FailedResult e) {
            super(e);
            protocolVersion = null;
            product = null;
            revision = null;
            userAgent = null;
            jsVersion = null;
        }
    }
    /**Returns the command line switches for the browser process if, and only if
--enable-automation is on the commandline.
    <p><strong>Experimental.</strong></p>*/
    public GetBrowserCommandLineParameter getBrowserCommandLine() { final GetBrowserCommandLineParameter v = new GetBrowserCommandLineParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of getBrowserCommandLine.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetBrowserCommandLineParameter extends CommandBase {
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
        public GetBrowserCommandLineParameter() {}
        public CompletableFuture<GetBrowserCommandLineResult> call() {
            return super.call("Browser.getBrowserCommandLine", GetBrowserCommandLineResult.class,
                (code, msg)->new GetBrowserCommandLineResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetBrowserCommandLineResult> callAsync() {
            return super.callAsync("Browser.getBrowserCommandLine", GetBrowserCommandLineResult.class,
                (code, msg)->new GetBrowserCommandLineResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetBrowserCommandLineResult> callAsync(Executor exec) {
            return super.callAsync("Browser.getBrowserCommandLine", GetBrowserCommandLineResult.class,
                (code, msg)->new GetBrowserCommandLineResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of getBrowserCommandLine.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetBrowserCommandLineResult extends ResultBase {
        /**Commandline parameters*/
        private final List<String> arguments;
        public final List<String> arguments() { return arguments; }
        public final List<String> getArguments() { return arguments(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
                        strBuilder.append("\"arguments\":[");
            strBuilder.append('"').append(DomainBase.escapeJson(arguments.get(0))).append('"');
            for (int i = 1; i < arguments.size(); ++i)
                strBuilder.append(",\"").append(DomainBase.escapeJson(arguments.get(i))).append('"');
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public GetBrowserCommandLineResult(
            @JsonProperty("arguments")List<String> arguments
        ) {
            this.arguments = arguments;
        }
        public GetBrowserCommandLineResult(ResultBase.FailedResult e) {
            super(e);
            arguments = null;
        }
    }
    /**Get Chrome histograms.
    <p><strong>Experimental.</strong></p>*/
    public GetHistogramsParameter getHistograms() { final GetHistogramsParameter v = new GetHistogramsParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of getHistograms.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetHistogramsParameter extends CommandBase {
        /**Requested substring in name. Only histograms which have query as a
substring in their name are extracted. An empty or absent query returns
all histograms.
        <em>Optional.</em>*/
        private String query;
        /**If true, retrieve delta since last call.
        <em>Optional.</em>*/
        private Boolean delta;
        public final GetHistogramsParameter query(@Nullable String query) { this.query = query; return this; }
        public final GetHistogramsParameter optQuery(@Nullable String query) { return query(query); }
        public final String query() { return query; }
        public final String getQuery() { return query(); }
        public final GetHistogramsParameter delta(@Nullable Boolean delta) { this.delta = delta; return this; }
        public final GetHistogramsParameter optDelta(@Nullable Boolean delta) { return delta(delta); }
        public final Boolean delta() { return delta; }
        public final Boolean getDelta() { return delta(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            if (query != null) strBuilder.append("\"query\":").append('"').append(DomainBase.escapeJson(query)).append('"');
            if (delta != null) strBuilder.append(",\"delta\":").append(delta);
            strBuilder.append('}');
            return strBuilder;
        }
        public GetHistogramsParameter() {}
        public GetHistogramsParameter(
            @Nullable @JsonProperty("query")String query,
            @Nullable @JsonProperty("delta")Boolean delta
        ) {
            this();
            this.query = query;
            this.delta = delta;
        }
        public CompletableFuture<GetHistogramsResult> call() {
            return super.call("Browser.getHistograms", GetHistogramsResult.class,
                (code, msg)->new GetHistogramsResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetHistogramsResult> callAsync() {
            return super.callAsync("Browser.getHistograms", GetHistogramsResult.class,
                (code, msg)->new GetHistogramsResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetHistogramsResult> callAsync(Executor exec) {
            return super.callAsync("Browser.getHistograms", GetHistogramsResult.class,
                (code, msg)->new GetHistogramsResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of getHistograms.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetHistogramsResult extends ResultBase {
        /**Histograms.*/
        private final List<Histogram> histograms;
        public final List<Histogram> histograms() { return histograms; }
        public final List<Histogram> getHistograms() { return histograms(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
                        strBuilder.append("\"histograms\":[");
            histograms.get(0).toJson(strBuilder);
            for (int i = 1; i < histograms.size(); ++i)
                histograms.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public GetHistogramsResult(
            @JsonProperty("histograms")List<Histogram> histograms
        ) {
            this.histograms = histograms;
        }
        public GetHistogramsResult(ResultBase.FailedResult e) {
            super(e);
            histograms = null;
        }
    }
    /**Get a Chrome histogram by name.
    <p><strong>Experimental.</strong></p>*/
    public GetHistogramParameter getHistogram() { final GetHistogramParameter v = new GetHistogramParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of getHistogram.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetHistogramParameter extends CommandBase {
        /**Requested histogram name.*/
        private String name;
        /**If true, retrieve delta since last call.
        <em>Optional.</em>*/
        private Boolean delta;
        public final GetHistogramParameter name(String name) { this.name = name; return this; }
        public final GetHistogramParameter setName(String name) { return name(name); }
        public final String name() { return name; }
        public final String getName() { return name(); }
        public final GetHistogramParameter delta(@Nullable Boolean delta) { this.delta = delta; return this; }
        public final GetHistogramParameter optDelta(@Nullable Boolean delta) { return delta(delta); }
        public final Boolean delta() { return delta; }
        public final Boolean getDelta() { return delta(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (name == null) throw new IllegalArgumentException("Browser.GetHistogramParameter.name is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"name\":").append('"').append(DomainBase.escapeJson(name)).append('"');
            if (delta != null) strBuilder.append(",\"delta\":").append(delta);
            strBuilder.append('}');
            return strBuilder;
        }
        public GetHistogramParameter() {}
        public GetHistogramParameter(
            @JsonProperty("name")String name,
            @Nullable @JsonProperty("delta")Boolean delta
        ) {
            this();
            this.name = name;
            this.delta = delta;
        }
        public CompletableFuture<GetHistogramResult> call() {
            return super.call("Browser.getHistogram", GetHistogramResult.class,
                (code, msg)->new GetHistogramResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetHistogramResult> callAsync() {
            return super.callAsync("Browser.getHistogram", GetHistogramResult.class,
                (code, msg)->new GetHistogramResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetHistogramResult> callAsync(Executor exec) {
            return super.callAsync("Browser.getHistogram", GetHistogramResult.class,
                (code, msg)->new GetHistogramResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of getHistogram.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetHistogramResult extends ResultBase {
        /**Histogram.*/
        private final Histogram histogram;
        public final Histogram histogram() { return histogram; }
        public final Histogram getHistogram() { return histogram(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            histogram.toJson(strBuilder.append("\"histogram\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public GetHistogramResult(
            @JsonProperty("histogram")Histogram histogram
        ) {
            this.histogram = histogram;
        }
        public GetHistogramResult(ResultBase.FailedResult e) {
            super(e);
            histogram = null;
        }
    }
    /**Get position and size of the browser window.
    <p><strong>Experimental.</strong></p>*/
    public GetWindowBoundsParameter getWindowBounds() { final GetWindowBoundsParameter v = new GetWindowBoundsParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of getWindowBounds.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetWindowBoundsParameter extends CommandBase {
        /**Browser window id.*/
        private WindowID windowId;
        public final GetWindowBoundsParameter windowId(WindowID windowId) { this.windowId = windowId; return this; }
        public final GetWindowBoundsParameter setWindowId(WindowID windowId) { return windowId(windowId); }
        public final WindowID windowId() { return windowId; }
        public final WindowID getWindowId() { return windowId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (windowId == null) throw new IllegalArgumentException("Browser.GetWindowBoundsParameter.windowId is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            windowId.toJson(strBuilder.append("\"windowId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public GetWindowBoundsParameter() {}
        public GetWindowBoundsParameter(
            @JsonProperty("windowId")WindowID windowId
        ) {
            this();
            this.windowId = windowId;
        }
        public CompletableFuture<GetWindowBoundsResult> call() {
            return super.call("Browser.getWindowBounds", GetWindowBoundsResult.class,
                (code, msg)->new GetWindowBoundsResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetWindowBoundsResult> callAsync() {
            return super.callAsync("Browser.getWindowBounds", GetWindowBoundsResult.class,
                (code, msg)->new GetWindowBoundsResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetWindowBoundsResult> callAsync(Executor exec) {
            return super.callAsync("Browser.getWindowBounds", GetWindowBoundsResult.class,
                (code, msg)->new GetWindowBoundsResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of getWindowBounds.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetWindowBoundsResult extends ResultBase {
        /**Bounds information of the window. When window state is 'minimized', the restored window
position and size are returned.*/
        private final Bounds bounds;
        public final Bounds bounds() { return bounds; }
        public final Bounds getBounds() { return bounds(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            bounds.toJson(strBuilder.append("\"bounds\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public GetWindowBoundsResult(
            @JsonProperty("bounds")Bounds bounds
        ) {
            this.bounds = bounds;
        }
        public GetWindowBoundsResult(ResultBase.FailedResult e) {
            super(e);
            bounds = null;
        }
    }
    /**Get the browser window that contains the devtools target.
    <p><strong>Experimental.</strong></p>*/
    public GetWindowForTargetParameter getWindowForTarget() { final GetWindowForTargetParameter v = new GetWindowForTargetParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of getWindowForTarget.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetWindowForTargetParameter extends CommandBase {
        /**Devtools agent host id.*/
        private Target.TargetID targetId;
        public final GetWindowForTargetParameter targetId(Target.TargetID targetId) { this.targetId = targetId; return this; }
        public final GetWindowForTargetParameter setTargetId(Target.TargetID targetId) { return targetId(targetId); }
        public final Target.TargetID targetId() { return targetId; }
        public final Target.TargetID getTargetId() { return targetId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (targetId == null) throw new IllegalArgumentException("Browser.GetWindowForTargetParameter.targetId is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            targetId.toJson(strBuilder.append("\"targetId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public GetWindowForTargetParameter() {}
        public GetWindowForTargetParameter(
            @JsonProperty("targetId")Target.TargetID targetId
        ) {
            this();
            this.targetId = targetId;
        }
        public CompletableFuture<GetWindowForTargetResult> call() {
            return super.call("Browser.getWindowForTarget", GetWindowForTargetResult.class,
                (code, msg)->new GetWindowForTargetResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetWindowForTargetResult> callAsync() {
            return super.callAsync("Browser.getWindowForTarget", GetWindowForTargetResult.class,
                (code, msg)->new GetWindowForTargetResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetWindowForTargetResult> callAsync(Executor exec) {
            return super.callAsync("Browser.getWindowForTarget", GetWindowForTargetResult.class,
                (code, msg)->new GetWindowForTargetResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of getWindowForTarget.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetWindowForTargetResult extends ResultBase {
        /**Browser window id.*/
        private final WindowID windowId;
        /**Bounds information of the window. When window state is 'minimized', the restored window
position and size are returned.*/
        private final Bounds bounds;
        public final WindowID windowId() { return windowId; }
        public final WindowID getWindowId() { return windowId(); }
        public final Bounds bounds() { return bounds; }
        public final Bounds getBounds() { return bounds(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            windowId.toJson(strBuilder.append("\"windowId\":"));
            bounds.toJson(strBuilder.append(",\"bounds\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public GetWindowForTargetResult(
            @JsonProperty("windowId")WindowID windowId,
            @JsonProperty("bounds")Bounds bounds
        ) {
            this.windowId = windowId;
            this.bounds = bounds;
        }
        public GetWindowForTargetResult(ResultBase.FailedResult e) {
            super(e);
            windowId = null;
            bounds = null;
        }
    }
    /**Set position and/or size of the browser window.
    <p><strong>Experimental.</strong></p>*/
    public SetWindowBoundsParameter setWindowBounds() { final SetWindowBoundsParameter v = new SetWindowBoundsParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setWindowBounds.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetWindowBoundsParameter extends CommandBase {
        /**Browser window id.*/
        private WindowID windowId;
        /**New window bounds. The 'minimized', 'maximized' and 'fullscreen' states cannot be combined
with 'left', 'top', 'width' or 'height'. Leaves unspecified fields unchanged.*/
        private Bounds bounds;
        public final SetWindowBoundsParameter windowId(WindowID windowId) { this.windowId = windowId; return this; }
        public final SetWindowBoundsParameter setWindowId(WindowID windowId) { return windowId(windowId); }
        public final WindowID windowId() { return windowId; }
        public final WindowID getWindowId() { return windowId(); }
        public final SetWindowBoundsParameter bounds(Bounds bounds) { this.bounds = bounds; return this; }
        public final SetWindowBoundsParameter setBounds(Bounds bounds) { return bounds(bounds); }
        public final Bounds bounds() { return bounds; }
        public final Bounds getBounds() { return bounds(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (windowId == null) throw new IllegalArgumentException("Browser.SetWindowBoundsParameter.windowId is necessary field.");
            if (bounds == null) throw new IllegalArgumentException("Browser.SetWindowBoundsParameter.bounds is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            windowId.toJson(strBuilder.append("\"windowId\":"));
            bounds.toJson(strBuilder.append(",\"bounds\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public SetWindowBoundsParameter() {}
        public SetWindowBoundsParameter(
            @JsonProperty("windowId")WindowID windowId,
            @JsonProperty("bounds")Bounds bounds
        ) {
            this();
            this.windowId = windowId;
            this.bounds = bounds;
        }
        public CompletableFuture<SetWindowBoundsResult> call() {
            return super.call("Browser.setWindowBounds", SetWindowBoundsResult.class,
                (code, msg)->new SetWindowBoundsResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetWindowBoundsResult> callAsync() {
            return super.callAsync("Browser.setWindowBounds", SetWindowBoundsResult.class,
                (code, msg)->new SetWindowBoundsResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetWindowBoundsResult> callAsync(Executor exec) {
            return super.callAsync("Browser.setWindowBounds", SetWindowBoundsResult.class,
                (code, msg)->new SetWindowBoundsResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setWindowBounds.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetWindowBoundsResult extends ResultBase {
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
        public SetWindowBoundsResult() { super(); }
        public SetWindowBoundsResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
}

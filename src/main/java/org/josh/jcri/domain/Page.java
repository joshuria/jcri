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

/**Actions and events related to the inspected page belong to the page domain.
<p>From: browser_protocol.json</p>
<p>Protocol version: 1.3</p>
 @see Debugger
 @see DOM
 @see Network
 @author Joshua*/
@ParametersAreNonnullByDefault public class Page extends DomainBase {
    public Page(EventCenter evt, WebSocket ws) { super(evt, ws); }

    /**Resource type as it was perceived by the rendering engine.*/
    @ParametersAreNonnullByDefault public enum ResourceType implements CommonDomainType {
        Document("Document"),
        Stylesheet("Stylesheet"),
        Image("Image"),
        Media("Media"),
        Font("Font"),
        Script("Script"),
        TextTrack("TextTrack"),
        XHR("XHR"),
        Fetch("Fetch"),
        EventSource("EventSource"),
        WebSocket("WebSocket"),
        Manifest("Manifest"),
        SignedExchange("SignedExchange"),
        Other("Other");

        private final String _value;
        /**Convert string representation to type.
         @throws IllegalArgumentException if given value cannot convert to enum type. */
        @JsonCreator public static ResourceType of(String value) {
            return Enum.valueOf(ResourceType.class, value.substring(0, 1).toUpperCase() + value.substring(1));
        }
        ResourceType(String value) { _value = value; }
        /**Check if parameter fields of method are all valid. */
        @Override public void check() throws IllegalArgumentException { /* Need not check */ }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) { return strBuilder.append('"').append(_value).append('"'); }
        @Override public String toString() { return "\"" + _value + "\""; }
    }

    /**Unique frame identifier.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class FrameId implements CommonDomainType {
        private String _value;
        public FrameId() {}
        public FrameId(String value) { _value = value; }
        public final FrameId value(String value) { _value = value; return this; }
        public final String value() { return _value; }
        public final FrameId setValue(String value) { return value(value); }
        public final String getValue() { return value(); }
        @Override public String toString() { return _value; }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (_value == null) throw new IllegalArgumentException("Page.FrameId.value is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            return strBuilder.append('"').append(DomainBase.escapeJson(_value)).append('"');
        }
    }

    /**Information about the Frame on the page.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class Frame implements CommonDomainType {
        /**Frame unique identifier.*/
        private String id;
        /**Parent frame identifier.
        <em>Optional.</em>*/
        private String parentId;
        /**Identifier of the loader associated with this frame.*/
        private Network.LoaderId loaderId;
        /**Frame's name as specified in the tag.
        <em>Optional.</em>*/
        private String name;
        /**Frame document's URL.*/
        private String url;
        /**Frame document's security origin.*/
        private String securityOrigin;
        /**Frame document's mimeType as determined by the browser.*/
        private String mimeType;
        /**If the frame failed to load, this contains the URL that could not be loaded.
        <em>Optional.</em>
        <p><strong>Experimental.</strong></p>*/
        private String unreachableUrl;
        public final Frame id(String id) { this.id = id; return this; }
        public final Frame setId(String id) { return id(id); }
        public final String id() { return id; }
        public final String getId() { return id(); }
        public final Frame parentId(@Nullable String parentId) { this.parentId = parentId; return this; }
        public final Frame optParentId(@Nullable String parentId) { return parentId(parentId); }
        public final String parentId() { return parentId; }
        public final String getParentId() { return parentId(); }
        public final Frame loaderId(Network.LoaderId loaderId) { this.loaderId = loaderId; return this; }
        public final Frame setLoaderId(Network.LoaderId loaderId) { return loaderId(loaderId); }
        public final Network.LoaderId loaderId() { return loaderId; }
        public final Network.LoaderId getLoaderId() { return loaderId(); }
        public final Frame name(@Nullable String name) { this.name = name; return this; }
        public final Frame optName(@Nullable String name) { return name(name); }
        public final String name() { return name; }
        public final String getName() { return name(); }
        public final Frame url(String url) { this.url = url; return this; }
        public final Frame setUrl(String url) { return url(url); }
        public final String url() { return url; }
        public final String getUrl() { return url(); }
        public final Frame securityOrigin(String securityOrigin) { this.securityOrigin = securityOrigin; return this; }
        public final Frame setSecurityOrigin(String securityOrigin) { return securityOrigin(securityOrigin); }
        public final String securityOrigin() { return securityOrigin; }
        public final String getSecurityOrigin() { return securityOrigin(); }
        public final Frame mimeType(String mimeType) { this.mimeType = mimeType; return this; }
        public final Frame setMimeType(String mimeType) { return mimeType(mimeType); }
        public final String mimeType() { return mimeType; }
        public final String getMimeType() { return mimeType(); }
        public final Frame unreachableUrl(@Nullable String unreachableUrl) { this.unreachableUrl = unreachableUrl; return this; }
        public final Frame optUnreachableUrl(@Nullable String unreachableUrl) { return unreachableUrl(unreachableUrl); }
        public final String unreachableUrl() { return unreachableUrl; }
        public final String getUnreachableUrl() { return unreachableUrl(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (id == null) throw new IllegalArgumentException("Page.Frame.id is necessary field.");
            if (loaderId == null) throw new IllegalArgumentException("Page.Frame.loaderId is necessary field.");
            if (url == null) throw new IllegalArgumentException("Page.Frame.url is necessary field.");
            if (securityOrigin == null) throw new IllegalArgumentException("Page.Frame.securityOrigin is necessary field.");
            if (mimeType == null) throw new IllegalArgumentException("Page.Frame.mimeType is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"id\":").append('"').append(DomainBase.escapeJson(id)).append('"');
            if (parentId != null) strBuilder.append(",\"parentId\":").append('"').append(DomainBase.escapeJson(parentId)).append('"');
            loaderId.toJson(strBuilder.append(",\"loaderId\":"));
            if (name != null) strBuilder.append(",\"name\":").append('"').append(DomainBase.escapeJson(name)).append('"');
            strBuilder.append(",\"url\":").append('"').append(DomainBase.escapeJson(url)).append('"');
            strBuilder.append(",\"securityOrigin\":").append('"').append(DomainBase.escapeJson(securityOrigin)).append('"');
            strBuilder.append(",\"mimeType\":").append('"').append(DomainBase.escapeJson(mimeType)).append('"');
            if (unreachableUrl != null) strBuilder.append(",\"unreachableUrl\":").append('"').append(DomainBase.escapeJson(unreachableUrl)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public Frame() {}
        public Frame(
            @JsonProperty("id")String id,
            @Nullable @JsonProperty("parentId")String parentId,
            @JsonProperty("loaderId")Network.LoaderId loaderId,
            @Nullable @JsonProperty("name")String name,
            @JsonProperty("url")String url,
            @JsonProperty("securityOrigin")String securityOrigin,
            @JsonProperty("mimeType")String mimeType,
            @Nullable @JsonProperty("unreachableUrl")String unreachableUrl
        ) {
            this.id = id;
            this.parentId = parentId;
            this.loaderId = loaderId;
            this.name = name;
            this.url = url;
            this.securityOrigin = securityOrigin;
            this.mimeType = mimeType;
            this.unreachableUrl = unreachableUrl;
        }
    }

    /**Information about the Resource on the page.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class FrameResource implements CommonDomainType {
        /**Resource URL.*/
        private String url;
        /**Type of this resource.*/
        private ResourceType type;
        /**Resource mimeType as determined by the browser.*/
        private String mimeType;
        /**last-modified timestamp as reported by server.
        <em>Optional.</em>*/
        private Network.TimeSinceEpoch lastModified;
        /**Resource content size.
        <em>Optional.</em>*/
        private Double contentSize;
        /**True if the resource failed to load.
        <em>Optional.</em>*/
        private Boolean failed;
        /**True if the resource was canceled during loading.
        <em>Optional.</em>*/
        private Boolean canceled;
        public final FrameResource url(String url) { this.url = url; return this; }
        public final FrameResource setUrl(String url) { return url(url); }
        public final String url() { return url; }
        public final String getUrl() { return url(); }
        public final FrameResource type(ResourceType type) { this.type = type; return this; }
        public final FrameResource setType(ResourceType type) { return type(type); }
        public final ResourceType type() { return type; }
        public final ResourceType getType() { return type(); }
        public final FrameResource mimeType(String mimeType) { this.mimeType = mimeType; return this; }
        public final FrameResource setMimeType(String mimeType) { return mimeType(mimeType); }
        public final String mimeType() { return mimeType; }
        public final String getMimeType() { return mimeType(); }
        public final FrameResource lastModified(@Nullable Network.TimeSinceEpoch lastModified) { this.lastModified = lastModified; return this; }
        public final FrameResource optLastModified(@Nullable Network.TimeSinceEpoch lastModified) { return lastModified(lastModified); }
        public final Network.TimeSinceEpoch lastModified() { return lastModified; }
        public final Network.TimeSinceEpoch getLastModified() { return lastModified(); }
        public final FrameResource contentSize(@Nullable Double contentSize) { this.contentSize = contentSize; return this; }
        public final FrameResource optContentSize(@Nullable Double contentSize) { return contentSize(contentSize); }
        public final Double contentSize() { return contentSize; }
        public final Double getContentSize() { return contentSize(); }
        public final FrameResource failed(@Nullable Boolean failed) { this.failed = failed; return this; }
        public final FrameResource optFailed(@Nullable Boolean failed) { return failed(failed); }
        public final Boolean failed() { return failed; }
        public final Boolean getFailed() { return failed(); }
        public final FrameResource canceled(@Nullable Boolean canceled) { this.canceled = canceled; return this; }
        public final FrameResource optCanceled(@Nullable Boolean canceled) { return canceled(canceled); }
        public final Boolean canceled() { return canceled; }
        public final Boolean getCanceled() { return canceled(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (url == null) throw new IllegalArgumentException("Page.FrameResource.url is necessary field.");
            if (type == null) throw new IllegalArgumentException("Page.FrameResource.type is necessary field.");
            if (mimeType == null) throw new IllegalArgumentException("Page.FrameResource.mimeType is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"url\":").append('"').append(DomainBase.escapeJson(url)).append('"');
            type.toJson(strBuilder.append(",\"type\":"));
            strBuilder.append(",\"mimeType\":").append('"').append(DomainBase.escapeJson(mimeType)).append('"');
            if (lastModified != null) lastModified.toJson(strBuilder.append(",\"lastModified\":"));
            if (contentSize != null) strBuilder.append(",\"contentSize\":").append(contentSize);
            if (failed != null) strBuilder.append(",\"failed\":").append(failed);
            if (canceled != null) strBuilder.append(",\"canceled\":").append(canceled);
            strBuilder.append('}');
            return strBuilder;
        }
        public FrameResource() {}
        public FrameResource(
            @JsonProperty("url")String url,
            @JsonProperty("type")ResourceType type,
            @JsonProperty("mimeType")String mimeType,
            @Nullable @JsonProperty("lastModified")Network.TimeSinceEpoch lastModified,
            @Nullable @JsonProperty("contentSize")Double contentSize,
            @Nullable @JsonProperty("failed")Boolean failed,
            @Nullable @JsonProperty("canceled")Boolean canceled
        ) {
            this.url = url;
            this.type = type;
            this.mimeType = mimeType;
            this.lastModified = lastModified;
            this.contentSize = contentSize;
            this.failed = failed;
            this.canceled = canceled;
        }
    }

    /**Information about the Frame hierarchy along with their cached resources.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class FrameResourceTree implements CommonDomainType {
        /**Frame information for this tree item.*/
        private Frame frame;
        /**Child frames.
        <em>Optional.</em>*/
        private List<FrameResourceTree> childFrames;
        /**Information about frame resources.*/
        private List<FrameResource> resources;
        public final FrameResourceTree frame(Frame frame) { this.frame = frame; return this; }
        public final FrameResourceTree setFrame(Frame frame) { return frame(frame); }
        public final Frame frame() { return frame; }
        public final Frame getFrame() { return frame(); }
        public final FrameResourceTree childFrames(@Nullable List<FrameResourceTree> childFrames) { this.childFrames = childFrames; return this; }
        public final FrameResourceTree optChildFrames(@Nullable List<FrameResourceTree> childFrames) { return childFrames(childFrames); }
        public final List<FrameResourceTree> childFrames() { return childFrames; }
        public final List<FrameResourceTree> getChildFrames() { return childFrames(); }
        public final FrameResourceTree resources(List<FrameResource> resources) { this.resources = resources; return this; }
        public final FrameResourceTree setResources(List<FrameResource> resources) { return resources(resources); }
        public final List<FrameResource> resources() { return resources; }
        public final List<FrameResource> getResources() { return resources(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (frame == null) throw new IllegalArgumentException("Page.FrameResourceTree.frame is necessary field.");
            if (resources == null) throw new IllegalArgumentException("Page.FrameResourceTree.resources is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            frame.toJson(strBuilder.append("\"frame\":"));
            if (childFrames != null) {
                strBuilder.append(",\"childFrames\":[");
                childFrames.get(0).toJson(strBuilder);
                for (int i = 1; i < childFrames.size(); ++i)
                    childFrames.get(i).toJson(strBuilder.append(','));
                strBuilder.append(']');
            }
                        strBuilder.append(",\"resources\":[");
            resources.get(0).toJson(strBuilder);
            for (int i = 1; i < resources.size(); ++i)
                resources.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public FrameResourceTree() {}
        public FrameResourceTree(
            @JsonProperty("frame")Frame frame,
            @Nullable @JsonProperty("childFrames")List<FrameResourceTree> childFrames,
            @JsonProperty("resources")List<FrameResource> resources
        ) {
            this.frame = frame;
            this.childFrames = childFrames;
            this.resources = resources;
        }
    }

    /**Information about the Frame hierarchy.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class FrameTree implements CommonDomainType {
        /**Frame information for this tree item.*/
        private Frame frame;
        /**Child frames.
        <em>Optional.</em>*/
        private List<FrameTree> childFrames;
        public final FrameTree frame(Frame frame) { this.frame = frame; return this; }
        public final FrameTree setFrame(Frame frame) { return frame(frame); }
        public final Frame frame() { return frame; }
        public final Frame getFrame() { return frame(); }
        public final FrameTree childFrames(@Nullable List<FrameTree> childFrames) { this.childFrames = childFrames; return this; }
        public final FrameTree optChildFrames(@Nullable List<FrameTree> childFrames) { return childFrames(childFrames); }
        public final List<FrameTree> childFrames() { return childFrames; }
        public final List<FrameTree> getChildFrames() { return childFrames(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (frame == null) throw new IllegalArgumentException("Page.FrameTree.frame is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            frame.toJson(strBuilder.append("\"frame\":"));
            if (childFrames != null) {
                strBuilder.append(",\"childFrames\":[");
                childFrames.get(0).toJson(strBuilder);
                for (int i = 1; i < childFrames.size(); ++i)
                    childFrames.get(i).toJson(strBuilder.append(','));
                strBuilder.append(']');
            }
            strBuilder.append('}');
            return strBuilder;
        }
        public FrameTree() {}
        public FrameTree(
            @JsonProperty("frame")Frame frame,
            @Nullable @JsonProperty("childFrames")List<FrameTree> childFrames
        ) {
            this.frame = frame;
            this.childFrames = childFrames;
        }
    }

    /**Unique script identifier.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ScriptIdentifier implements CommonDomainType {
        private String _value;
        public ScriptIdentifier() {}
        public ScriptIdentifier(String value) { _value = value; }
        public final ScriptIdentifier value(String value) { _value = value; return this; }
        public final String value() { return _value; }
        public final ScriptIdentifier setValue(String value) { return value(value); }
        public final String getValue() { return value(); }
        @Override public String toString() { return _value; }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (_value == null) throw new IllegalArgumentException("Page.ScriptIdentifier.value is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            return strBuilder.append('"').append(DomainBase.escapeJson(_value)).append('"');
        }
    }

    /**Transition type.*/
    @ParametersAreNonnullByDefault public enum TransitionType implements CommonDomainType {
        Link("link"),
        Typed("typed"),
        Auto_bookmark("auto_bookmark"),
        Auto_subframe("auto_subframe"),
        Manual_subframe("manual_subframe"),
        Generated("generated"),
        Auto_toplevel("auto_toplevel"),
        Form_submit("form_submit"),
        Reload("reload"),
        Keyword("keyword"),
        Keyword_generated("keyword_generated"),
        Other("other");

        private final String _value;
        /**Convert string representation to type.
         @throws IllegalArgumentException if given value cannot convert to enum type. */
        @JsonCreator public static TransitionType of(String value) {
            return Enum.valueOf(TransitionType.class, value.substring(0, 1).toUpperCase() + value.substring(1));
        }
        TransitionType(String value) { _value = value; }
        /**Check if parameter fields of method are all valid. */
        @Override public void check() throws IllegalArgumentException { /* Need not check */ }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) { return strBuilder.append('"').append(_value).append('"'); }
        @Override public String toString() { return "\"" + _value + "\""; }
    }

    /**Navigation history entry.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class NavigationEntry implements CommonDomainType {
        /**Unique id of the navigation history entry.*/
        private Integer id;
        /**URL of the navigation history entry.*/
        private String url;
        /**URL that the user typed in the url bar.*/
        private String userTypedURL;
        /**Title of the navigation history entry.*/
        private String title;
        /**Transition type.*/
        private TransitionType transitionType;
        public final NavigationEntry id(Integer id) { this.id = id; return this; }
        public final NavigationEntry setId(Integer id) { return id(id); }
        public final Integer id() { return id; }
        public final Integer getId() { return id(); }
        public final NavigationEntry url(String url) { this.url = url; return this; }
        public final NavigationEntry setUrl(String url) { return url(url); }
        public final String url() { return url; }
        public final String getUrl() { return url(); }
        public final NavigationEntry userTypedURL(String userTypedURL) { this.userTypedURL = userTypedURL; return this; }
        public final NavigationEntry setUserTypedURL(String userTypedURL) { return userTypedURL(userTypedURL); }
        public final String userTypedURL() { return userTypedURL; }
        public final String getUserTypedURL() { return userTypedURL(); }
        public final NavigationEntry title(String title) { this.title = title; return this; }
        public final NavigationEntry setTitle(String title) { return title(title); }
        public final String title() { return title; }
        public final String getTitle() { return title(); }
        public final NavigationEntry transitionType(TransitionType transitionType) { this.transitionType = transitionType; return this; }
        public final NavigationEntry setTransitionType(TransitionType transitionType) { return transitionType(transitionType); }
        public final TransitionType transitionType() { return transitionType; }
        public final TransitionType getTransitionType() { return transitionType(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (id == null) throw new IllegalArgumentException("Page.NavigationEntry.id is necessary field.");
            if (url == null) throw new IllegalArgumentException("Page.NavigationEntry.url is necessary field.");
            if (userTypedURL == null) throw new IllegalArgumentException("Page.NavigationEntry.userTypedURL is necessary field.");
            if (title == null) throw new IllegalArgumentException("Page.NavigationEntry.title is necessary field.");
            if (transitionType == null) throw new IllegalArgumentException("Page.NavigationEntry.transitionType is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"id\":").append(id);
            strBuilder.append(",\"url\":").append('"').append(DomainBase.escapeJson(url)).append('"');
            strBuilder.append(",\"userTypedURL\":").append('"').append(DomainBase.escapeJson(userTypedURL)).append('"');
            strBuilder.append(",\"title\":").append('"').append(DomainBase.escapeJson(title)).append('"');
            transitionType.toJson(strBuilder.append(",\"transitionType\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public NavigationEntry() {}
        public NavigationEntry(
            @JsonProperty("id")Integer id,
            @JsonProperty("url")String url,
            @JsonProperty("userTypedURL")String userTypedURL,
            @JsonProperty("title")String title,
            @JsonProperty("transitionType")TransitionType transitionType
        ) {
            this.id = id;
            this.url = url;
            this.userTypedURL = userTypedURL;
            this.title = title;
            this.transitionType = transitionType;
        }
    }

    /**Screencast frame metadata.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ScreencastFrameMetadata implements CommonDomainType {
        /**Top offset in DIP.*/
        private Double offsetTop;
        /**Page scale factor.*/
        private Double pageScaleFactor;
        /**Device screen width in DIP.*/
        private Double deviceWidth;
        /**Device screen height in DIP.*/
        private Double deviceHeight;
        /**Position of horizontal scroll in CSS pixels.*/
        private Double scrollOffsetX;
        /**Position of vertical scroll in CSS pixels.*/
        private Double scrollOffsetY;
        /**Frame swap timestamp.
        <em>Optional.</em>*/
        private Network.TimeSinceEpoch timestamp;
        public final ScreencastFrameMetadata offsetTop(Double offsetTop) { this.offsetTop = offsetTop; return this; }
        public final ScreencastFrameMetadata setOffsetTop(Double offsetTop) { return offsetTop(offsetTop); }
        public final Double offsetTop() { return offsetTop; }
        public final Double getOffsetTop() { return offsetTop(); }
        public final ScreencastFrameMetadata pageScaleFactor(Double pageScaleFactor) { this.pageScaleFactor = pageScaleFactor; return this; }
        public final ScreencastFrameMetadata setPageScaleFactor(Double pageScaleFactor) { return pageScaleFactor(pageScaleFactor); }
        public final Double pageScaleFactor() { return pageScaleFactor; }
        public final Double getPageScaleFactor() { return pageScaleFactor(); }
        public final ScreencastFrameMetadata deviceWidth(Double deviceWidth) { this.deviceWidth = deviceWidth; return this; }
        public final ScreencastFrameMetadata setDeviceWidth(Double deviceWidth) { return deviceWidth(deviceWidth); }
        public final Double deviceWidth() { return deviceWidth; }
        public final Double getDeviceWidth() { return deviceWidth(); }
        public final ScreencastFrameMetadata deviceHeight(Double deviceHeight) { this.deviceHeight = deviceHeight; return this; }
        public final ScreencastFrameMetadata setDeviceHeight(Double deviceHeight) { return deviceHeight(deviceHeight); }
        public final Double deviceHeight() { return deviceHeight; }
        public final Double getDeviceHeight() { return deviceHeight(); }
        public final ScreencastFrameMetadata scrollOffsetX(Double scrollOffsetX) { this.scrollOffsetX = scrollOffsetX; return this; }
        public final ScreencastFrameMetadata setScrollOffsetX(Double scrollOffsetX) { return scrollOffsetX(scrollOffsetX); }
        public final Double scrollOffsetX() { return scrollOffsetX; }
        public final Double getScrollOffsetX() { return scrollOffsetX(); }
        public final ScreencastFrameMetadata scrollOffsetY(Double scrollOffsetY) { this.scrollOffsetY = scrollOffsetY; return this; }
        public final ScreencastFrameMetadata setScrollOffsetY(Double scrollOffsetY) { return scrollOffsetY(scrollOffsetY); }
        public final Double scrollOffsetY() { return scrollOffsetY; }
        public final Double getScrollOffsetY() { return scrollOffsetY(); }
        public final ScreencastFrameMetadata timestamp(@Nullable Network.TimeSinceEpoch timestamp) { this.timestamp = timestamp; return this; }
        public final ScreencastFrameMetadata optTimestamp(@Nullable Network.TimeSinceEpoch timestamp) { return timestamp(timestamp); }
        public final Network.TimeSinceEpoch timestamp() { return timestamp; }
        public final Network.TimeSinceEpoch getTimestamp() { return timestamp(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (offsetTop == null) throw new IllegalArgumentException("Page.ScreencastFrameMetadata.offsetTop is necessary field.");
            if (pageScaleFactor == null) throw new IllegalArgumentException("Page.ScreencastFrameMetadata.pageScaleFactor is necessary field.");
            if (deviceWidth == null) throw new IllegalArgumentException("Page.ScreencastFrameMetadata.deviceWidth is necessary field.");
            if (deviceHeight == null) throw new IllegalArgumentException("Page.ScreencastFrameMetadata.deviceHeight is necessary field.");
            if (scrollOffsetX == null) throw new IllegalArgumentException("Page.ScreencastFrameMetadata.scrollOffsetX is necessary field.");
            if (scrollOffsetY == null) throw new IllegalArgumentException("Page.ScreencastFrameMetadata.scrollOffsetY is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"offsetTop\":").append(offsetTop);
            strBuilder.append(",\"pageScaleFactor\":").append(pageScaleFactor);
            strBuilder.append(",\"deviceWidth\":").append(deviceWidth);
            strBuilder.append(",\"deviceHeight\":").append(deviceHeight);
            strBuilder.append(",\"scrollOffsetX\":").append(scrollOffsetX);
            strBuilder.append(",\"scrollOffsetY\":").append(scrollOffsetY);
            if (timestamp != null) timestamp.toJson(strBuilder.append(",\"timestamp\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public ScreencastFrameMetadata() {}
        public ScreencastFrameMetadata(
            @JsonProperty("offsetTop")Double offsetTop,
            @JsonProperty("pageScaleFactor")Double pageScaleFactor,
            @JsonProperty("deviceWidth")Double deviceWidth,
            @JsonProperty("deviceHeight")Double deviceHeight,
            @JsonProperty("scrollOffsetX")Double scrollOffsetX,
            @JsonProperty("scrollOffsetY")Double scrollOffsetY,
            @Nullable @JsonProperty("timestamp")Network.TimeSinceEpoch timestamp
        ) {
            this.offsetTop = offsetTop;
            this.pageScaleFactor = pageScaleFactor;
            this.deviceWidth = deviceWidth;
            this.deviceHeight = deviceHeight;
            this.scrollOffsetX = scrollOffsetX;
            this.scrollOffsetY = scrollOffsetY;
            this.timestamp = timestamp;
        }
    }

    /**Javascript dialog type.*/
    @ParametersAreNonnullByDefault public enum DialogType implements CommonDomainType {
        Alert("alert"),
        Confirm("confirm"),
        Prompt("prompt"),
        Beforeunload("beforeunload");

        private final String _value;
        /**Convert string representation to type.
         @throws IllegalArgumentException if given value cannot convert to enum type. */
        @JsonCreator public static DialogType of(String value) {
            return Enum.valueOf(DialogType.class, value.substring(0, 1).toUpperCase() + value.substring(1));
        }
        DialogType(String value) { _value = value; }
        /**Check if parameter fields of method are all valid. */
        @Override public void check() throws IllegalArgumentException { /* Need not check */ }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) { return strBuilder.append('"').append(_value).append('"'); }
        @Override public String toString() { return "\"" + _value + "\""; }
    }

    /**Error while paring app manifest.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class AppManifestError implements CommonDomainType {
        /**Error message.*/
        private String message;
        /**If criticial, this is a non-recoverable parse error.*/
        private Integer critical;
        /**Error line.*/
        private Integer line;
        /**Error column.*/
        private Integer column;
        public final AppManifestError message(String message) { this.message = message; return this; }
        public final AppManifestError setMessage(String message) { return message(message); }
        public final String message() { return message; }
        public final String getMessage() { return message(); }
        public final AppManifestError critical(Integer critical) { this.critical = critical; return this; }
        public final AppManifestError setCritical(Integer critical) { return critical(critical); }
        public final Integer critical() { return critical; }
        public final Integer getCritical() { return critical(); }
        public final AppManifestError line(Integer line) { this.line = line; return this; }
        public final AppManifestError setLine(Integer line) { return line(line); }
        public final Integer line() { return line; }
        public final Integer getLine() { return line(); }
        public final AppManifestError column(Integer column) { this.column = column; return this; }
        public final AppManifestError setColumn(Integer column) { return column(column); }
        public final Integer column() { return column; }
        public final Integer getColumn() { return column(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (message == null) throw new IllegalArgumentException("Page.AppManifestError.message is necessary field.");
            if (critical == null) throw new IllegalArgumentException("Page.AppManifestError.critical is necessary field.");
            if (line == null) throw new IllegalArgumentException("Page.AppManifestError.line is necessary field.");
            if (column == null) throw new IllegalArgumentException("Page.AppManifestError.column is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"message\":").append('"').append(DomainBase.escapeJson(message)).append('"');
            strBuilder.append(",\"critical\":").append(critical);
            strBuilder.append(",\"line\":").append(line);
            strBuilder.append(",\"column\":").append(column);
            strBuilder.append('}');
            return strBuilder;
        }
        public AppManifestError() {}
        public AppManifestError(
            @JsonProperty("message")String message,
            @JsonProperty("critical")Integer critical,
            @JsonProperty("line")Integer line,
            @JsonProperty("column")Integer column
        ) {
            this.message = message;
            this.critical = critical;
            this.line = line;
            this.column = column;
        }
    }

    /**Layout viewport position and dimensions.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class LayoutViewport implements CommonDomainType {
        /**Horizontal offset relative to the document (CSS pixels).*/
        private Integer pageX;
        /**Vertical offset relative to the document (CSS pixels).*/
        private Integer pageY;
        /**Width (CSS pixels), excludes scrollbar if present.*/
        private Integer clientWidth;
        /**Height (CSS pixels), excludes scrollbar if present.*/
        private Integer clientHeight;
        public final LayoutViewport pageX(Integer pageX) { this.pageX = pageX; return this; }
        public final LayoutViewport setPageX(Integer pageX) { return pageX(pageX); }
        public final Integer pageX() { return pageX; }
        public final Integer getPageX() { return pageX(); }
        public final LayoutViewport pageY(Integer pageY) { this.pageY = pageY; return this; }
        public final LayoutViewport setPageY(Integer pageY) { return pageY(pageY); }
        public final Integer pageY() { return pageY; }
        public final Integer getPageY() { return pageY(); }
        public final LayoutViewport clientWidth(Integer clientWidth) { this.clientWidth = clientWidth; return this; }
        public final LayoutViewport setClientWidth(Integer clientWidth) { return clientWidth(clientWidth); }
        public final Integer clientWidth() { return clientWidth; }
        public final Integer getClientWidth() { return clientWidth(); }
        public final LayoutViewport clientHeight(Integer clientHeight) { this.clientHeight = clientHeight; return this; }
        public final LayoutViewport setClientHeight(Integer clientHeight) { return clientHeight(clientHeight); }
        public final Integer clientHeight() { return clientHeight; }
        public final Integer getClientHeight() { return clientHeight(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (pageX == null) throw new IllegalArgumentException("Page.LayoutViewport.pageX is necessary field.");
            if (pageY == null) throw new IllegalArgumentException("Page.LayoutViewport.pageY is necessary field.");
            if (clientWidth == null) throw new IllegalArgumentException("Page.LayoutViewport.clientWidth is necessary field.");
            if (clientHeight == null) throw new IllegalArgumentException("Page.LayoutViewport.clientHeight is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"pageX\":").append(pageX);
            strBuilder.append(",\"pageY\":").append(pageY);
            strBuilder.append(",\"clientWidth\":").append(clientWidth);
            strBuilder.append(",\"clientHeight\":").append(clientHeight);
            strBuilder.append('}');
            return strBuilder;
        }
        public LayoutViewport() {}
        public LayoutViewport(
            @JsonProperty("pageX")Integer pageX,
            @JsonProperty("pageY")Integer pageY,
            @JsonProperty("clientWidth")Integer clientWidth,
            @JsonProperty("clientHeight")Integer clientHeight
        ) {
            this.pageX = pageX;
            this.pageY = pageY;
            this.clientWidth = clientWidth;
            this.clientHeight = clientHeight;
        }
    }

    /**Visual viewport position, dimensions, and scale.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class VisualViewport implements CommonDomainType {
        /**Horizontal offset relative to the layout viewport (CSS pixels).*/
        private Double offsetX;
        /**Vertical offset relative to the layout viewport (CSS pixels).*/
        private Double offsetY;
        /**Horizontal offset relative to the document (CSS pixels).*/
        private Double pageX;
        /**Vertical offset relative to the document (CSS pixels).*/
        private Double pageY;
        /**Width (CSS pixels), excludes scrollbar if present.*/
        private Double clientWidth;
        /**Height (CSS pixels), excludes scrollbar if present.*/
        private Double clientHeight;
        /**Scale relative to the ideal viewport (size at width=device-width).*/
        private Double scale;
        public final VisualViewport offsetX(Double offsetX) { this.offsetX = offsetX; return this; }
        public final VisualViewport setOffsetX(Double offsetX) { return offsetX(offsetX); }
        public final Double offsetX() { return offsetX; }
        public final Double getOffsetX() { return offsetX(); }
        public final VisualViewport offsetY(Double offsetY) { this.offsetY = offsetY; return this; }
        public final VisualViewport setOffsetY(Double offsetY) { return offsetY(offsetY); }
        public final Double offsetY() { return offsetY; }
        public final Double getOffsetY() { return offsetY(); }
        public final VisualViewport pageX(Double pageX) { this.pageX = pageX; return this; }
        public final VisualViewport setPageX(Double pageX) { return pageX(pageX); }
        public final Double pageX() { return pageX; }
        public final Double getPageX() { return pageX(); }
        public final VisualViewport pageY(Double pageY) { this.pageY = pageY; return this; }
        public final VisualViewport setPageY(Double pageY) { return pageY(pageY); }
        public final Double pageY() { return pageY; }
        public final Double getPageY() { return pageY(); }
        public final VisualViewport clientWidth(Double clientWidth) { this.clientWidth = clientWidth; return this; }
        public final VisualViewport setClientWidth(Double clientWidth) { return clientWidth(clientWidth); }
        public final Double clientWidth() { return clientWidth; }
        public final Double getClientWidth() { return clientWidth(); }
        public final VisualViewport clientHeight(Double clientHeight) { this.clientHeight = clientHeight; return this; }
        public final VisualViewport setClientHeight(Double clientHeight) { return clientHeight(clientHeight); }
        public final Double clientHeight() { return clientHeight; }
        public final Double getClientHeight() { return clientHeight(); }
        public final VisualViewport scale(Double scale) { this.scale = scale; return this; }
        public final VisualViewport setScale(Double scale) { return scale(scale); }
        public final Double scale() { return scale; }
        public final Double getScale() { return scale(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (offsetX == null) throw new IllegalArgumentException("Page.VisualViewport.offsetX is necessary field.");
            if (offsetY == null) throw new IllegalArgumentException("Page.VisualViewport.offsetY is necessary field.");
            if (pageX == null) throw new IllegalArgumentException("Page.VisualViewport.pageX is necessary field.");
            if (pageY == null) throw new IllegalArgumentException("Page.VisualViewport.pageY is necessary field.");
            if (clientWidth == null) throw new IllegalArgumentException("Page.VisualViewport.clientWidth is necessary field.");
            if (clientHeight == null) throw new IllegalArgumentException("Page.VisualViewport.clientHeight is necessary field.");
            if (scale == null) throw new IllegalArgumentException("Page.VisualViewport.scale is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"offsetX\":").append(offsetX);
            strBuilder.append(",\"offsetY\":").append(offsetY);
            strBuilder.append(",\"pageX\":").append(pageX);
            strBuilder.append(",\"pageY\":").append(pageY);
            strBuilder.append(",\"clientWidth\":").append(clientWidth);
            strBuilder.append(",\"clientHeight\":").append(clientHeight);
            strBuilder.append(",\"scale\":").append(scale);
            strBuilder.append('}');
            return strBuilder;
        }
        public VisualViewport() {}
        public VisualViewport(
            @JsonProperty("offsetX")Double offsetX,
            @JsonProperty("offsetY")Double offsetY,
            @JsonProperty("pageX")Double pageX,
            @JsonProperty("pageY")Double pageY,
            @JsonProperty("clientWidth")Double clientWidth,
            @JsonProperty("clientHeight")Double clientHeight,
            @JsonProperty("scale")Double scale
        ) {
            this.offsetX = offsetX;
            this.offsetY = offsetY;
            this.pageX = pageX;
            this.pageY = pageY;
            this.clientWidth = clientWidth;
            this.clientHeight = clientHeight;
            this.scale = scale;
        }
    }

    /**Viewport for capturing screenshot.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class Viewport implements CommonDomainType {
        /**X offset in CSS pixels.*/
        private Double x;
        /**Y offset in CSS pixels*/
        private Double y;
        /**Rectangle width in CSS pixels*/
        private Double width;
        /**Rectangle height in CSS pixels*/
        private Double height;
        /**Page scale factor.*/
        private Double scale;
        public final Viewport x(Double x) { this.x = x; return this; }
        public final Viewport setX(Double x) { return x(x); }
        public final Double x() { return x; }
        public final Double getX() { return x(); }
        public final Viewport y(Double y) { this.y = y; return this; }
        public final Viewport setY(Double y) { return y(y); }
        public final Double y() { return y; }
        public final Double getY() { return y(); }
        public final Viewport width(Double width) { this.width = width; return this; }
        public final Viewport setWidth(Double width) { return width(width); }
        public final Double width() { return width; }
        public final Double getWidth() { return width(); }
        public final Viewport height(Double height) { this.height = height; return this; }
        public final Viewport setHeight(Double height) { return height(height); }
        public final Double height() { return height; }
        public final Double getHeight() { return height(); }
        public final Viewport scale(Double scale) { this.scale = scale; return this; }
        public final Viewport setScale(Double scale) { return scale(scale); }
        public final Double scale() { return scale; }
        public final Double getScale() { return scale(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (x == null) throw new IllegalArgumentException("Page.Viewport.x is necessary field.");
            if (y == null) throw new IllegalArgumentException("Page.Viewport.y is necessary field.");
            if (width == null) throw new IllegalArgumentException("Page.Viewport.width is necessary field.");
            if (height == null) throw new IllegalArgumentException("Page.Viewport.height is necessary field.");
            if (scale == null) throw new IllegalArgumentException("Page.Viewport.scale is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"x\":").append(x);
            strBuilder.append(",\"y\":").append(y);
            strBuilder.append(",\"width\":").append(width);
            strBuilder.append(",\"height\":").append(height);
            strBuilder.append(",\"scale\":").append(scale);
            strBuilder.append('}');
            return strBuilder;
        }
        public Viewport() {}
        public Viewport(
            @JsonProperty("x")Double x,
            @JsonProperty("y")Double y,
            @JsonProperty("width")Double width,
            @JsonProperty("height")Double height,
            @JsonProperty("scale")Double scale
        ) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.scale = scale;
        }
    }
    /**Deprecated, please use addScriptToEvaluateOnNewDocument instead.
    <p><strong>Experimental.</strong></p>
    @Deprecated*/
    public AddScriptToEvaluateOnLoadParameter addScriptToEvaluateOnLoad() { final AddScriptToEvaluateOnLoadParameter v = new AddScriptToEvaluateOnLoadParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of addScriptToEvaluateOnLoad.
    <p><strong>Experimental.</strong></p>
    @Deprecated*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class AddScriptToEvaluateOnLoadParameter extends CommandBase {
        /**&lt;No document in protocol.&gt;*/
        private String scriptSource;
        public final AddScriptToEvaluateOnLoadParameter scriptSource(String scriptSource) { this.scriptSource = scriptSource; return this; }
        public final AddScriptToEvaluateOnLoadParameter setScriptSource(String scriptSource) { return scriptSource(scriptSource); }
        public final String scriptSource() { return scriptSource; }
        public final String getScriptSource() { return scriptSource(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (scriptSource == null) throw new IllegalArgumentException("Page.AddScriptToEvaluateOnLoadParameter.scriptSource is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"scriptSource\":").append('"').append(DomainBase.escapeJson(scriptSource)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public AddScriptToEvaluateOnLoadParameter() {}
        public AddScriptToEvaluateOnLoadParameter(
            @JsonProperty("scriptSource")String scriptSource
        ) {
            this();
            this.scriptSource = scriptSource;
        }
        public CompletableFuture<AddScriptToEvaluateOnLoadResult> call() {
            return super.call("Page.addScriptToEvaluateOnLoad", AddScriptToEvaluateOnLoadResult.class,
                (code, msg)->new AddScriptToEvaluateOnLoadResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<AddScriptToEvaluateOnLoadResult> call(Executor exec) {
            return super.call("Page.addScriptToEvaluateOnLoad", AddScriptToEvaluateOnLoadResult.class,
                (code, msg)->new AddScriptToEvaluateOnLoadResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of addScriptToEvaluateOnLoad.
    <p><strong>Experimental.</strong></p>
    @Deprecated*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class AddScriptToEvaluateOnLoadResult extends ResultBase {
        /**Identifier of the added script.*/
        private final ScriptIdentifier identifier;
        public final ScriptIdentifier identifier() { return identifier; }
        public final ScriptIdentifier getIdentifier() { return identifier(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            identifier.toJson(strBuilder.append("\"identifier\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public AddScriptToEvaluateOnLoadResult(
            @JsonProperty("identifier")ScriptIdentifier identifier
        ) {
            this.identifier = identifier;
        }
        public AddScriptToEvaluateOnLoadResult(ResultBase.FailedResult e) {
            super(e);
            identifier = null;
        }
    }
    /**Evaluates given script in every frame upon creation (before loading frame's scripts).*/
    public AddScriptToEvaluateOnNewDocumentParameter addScriptToEvaluateOnNewDocument() { final AddScriptToEvaluateOnNewDocumentParameter v = new AddScriptToEvaluateOnNewDocumentParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of addScriptToEvaluateOnNewDocument.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class AddScriptToEvaluateOnNewDocumentParameter extends CommandBase {
        /**&lt;No document in protocol.&gt;*/
        private String source;
        public final AddScriptToEvaluateOnNewDocumentParameter source(String source) { this.source = source; return this; }
        public final AddScriptToEvaluateOnNewDocumentParameter setSource(String source) { return source(source); }
        public final String source() { return source; }
        public final String getSource() { return source(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (source == null) throw new IllegalArgumentException("Page.AddScriptToEvaluateOnNewDocumentParameter.source is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"source\":").append('"').append(DomainBase.escapeJson(source)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public AddScriptToEvaluateOnNewDocumentParameter() {}
        public AddScriptToEvaluateOnNewDocumentParameter(
            @JsonProperty("source")String source
        ) {
            this();
            this.source = source;
        }
        public CompletableFuture<AddScriptToEvaluateOnNewDocumentResult> call() {
            return super.call("Page.addScriptToEvaluateOnNewDocument", AddScriptToEvaluateOnNewDocumentResult.class,
                (code, msg)->new AddScriptToEvaluateOnNewDocumentResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<AddScriptToEvaluateOnNewDocumentResult> call(Executor exec) {
            return super.call("Page.addScriptToEvaluateOnNewDocument", AddScriptToEvaluateOnNewDocumentResult.class,
                (code, msg)->new AddScriptToEvaluateOnNewDocumentResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of addScriptToEvaluateOnNewDocument.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class AddScriptToEvaluateOnNewDocumentResult extends ResultBase {
        /**Identifier of the added script.*/
        private final ScriptIdentifier identifier;
        public final ScriptIdentifier identifier() { return identifier; }
        public final ScriptIdentifier getIdentifier() { return identifier(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            identifier.toJson(strBuilder.append("\"identifier\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public AddScriptToEvaluateOnNewDocumentResult(
            @JsonProperty("identifier")ScriptIdentifier identifier
        ) {
            this.identifier = identifier;
        }
        public AddScriptToEvaluateOnNewDocumentResult(ResultBase.FailedResult e) {
            super(e);
            identifier = null;
        }
    }
    /**Brings page to front (activates tab).*/
    public BringToFrontParameter bringToFront() { final BringToFrontParameter v = new BringToFrontParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of bringToFront.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class BringToFrontParameter extends CommandBase {
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
        public BringToFrontParameter() {}
        public CompletableFuture<BringToFrontResult> call() {
            return super.call("Page.bringToFront", BringToFrontResult.class,
                (code, msg)->new BringToFrontResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<BringToFrontResult> call(Executor exec) {
            return super.call("Page.bringToFront", BringToFrontResult.class,
                (code, msg)->new BringToFrontResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of bringToFront.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class BringToFrontResult extends ResultBase {
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
        public BringToFrontResult() { super(); }
        public BringToFrontResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Capture page screenshot.*/
    public CaptureScreenshotParameter captureScreenshot() { final CaptureScreenshotParameter v = new CaptureScreenshotParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of captureScreenshot.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class CaptureScreenshotParameter extends CommandBase {
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
        /**Capture the screenshot of a given region only.
        <em>Optional.</em>*/
        private Viewport clip;
        /**Capture the screenshot from the surface, rather than the view. Defaults to true.
        <em>Optional.</em>
        <p><strong>Experimental.</strong></p>*/
        private Boolean fromSurface;
        public final CaptureScreenshotParameter format(@Nullable Format format) { this.format = format; return this; }
        public final CaptureScreenshotParameter optFormat(@Nullable Format format) { return format(format); }
        public final Format format() { return format; }
        public final Format getFormat() { return format(); }
        public final CaptureScreenshotParameter quality(@Nullable Integer quality) { this.quality = quality; return this; }
        public final CaptureScreenshotParameter optQuality(@Nullable Integer quality) { return quality(quality); }
        public final Integer quality() { return quality; }
        public final Integer getQuality() { return quality(); }
        public final CaptureScreenshotParameter clip(@Nullable Viewport clip) { this.clip = clip; return this; }
        public final CaptureScreenshotParameter optClip(@Nullable Viewport clip) { return clip(clip); }
        public final Viewport clip() { return clip; }
        public final Viewport getClip() { return clip(); }
        public final CaptureScreenshotParameter fromSurface(@Nullable Boolean fromSurface) { this.fromSurface = fromSurface; return this; }
        public final CaptureScreenshotParameter optFromSurface(@Nullable Boolean fromSurface) { return fromSurface(fromSurface); }
        public final Boolean fromSurface() { return fromSurface; }
        public final Boolean getFromSurface() { return fromSurface(); }
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
            if (clip != null) clip.toJson(strBuilder.append(",\"clip\":"));
            if (fromSurface != null) strBuilder.append(",\"fromSurface\":").append(fromSurface);
            strBuilder.append('}');
            return strBuilder;
        }
        public CaptureScreenshotParameter() {}
        public CaptureScreenshotParameter(
            @Nullable @JsonProperty("format")Format format,
            @Nullable @JsonProperty("quality")Integer quality,
            @Nullable @JsonProperty("clip")Viewport clip,
            @Nullable @JsonProperty("fromSurface")Boolean fromSurface
        ) {
            this();
            this.format = format;
            this.quality = quality;
            this.clip = clip;
            this.fromSurface = fromSurface;
        }
        public CompletableFuture<CaptureScreenshotResult> call() {
            return super.call("Page.captureScreenshot", CaptureScreenshotResult.class,
                (code, msg)->new CaptureScreenshotResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<CaptureScreenshotResult> call(Executor exec) {
            return super.call("Page.captureScreenshot", CaptureScreenshotResult.class,
                (code, msg)->new CaptureScreenshotResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of captureScreenshot.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class CaptureScreenshotResult extends ResultBase {
        /**Base64-encoded image data.*/
        private final String data;
        public final String data() { return data; }
        public final String getData() { return data(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"data\":").append('"').append(DomainBase.escapeJson(data)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public CaptureScreenshotResult(
            @JsonProperty("data")String data
        ) {
            this.data = data;
        }
        public CaptureScreenshotResult(ResultBase.FailedResult e) {
            super(e);
            data = null;
        }
    }
    /**Clears the overriden device metrics.
    <p><strong>Experimental.</strong></p>
    @Deprecated*/
    public ClearDeviceMetricsOverrideParameter clearDeviceMetricsOverride() { final ClearDeviceMetricsOverrideParameter v = new ClearDeviceMetricsOverrideParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of clearDeviceMetricsOverride.
    <p><strong>Experimental.</strong></p>
    @Deprecated*/
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
            return super.call("Page.clearDeviceMetricsOverride", ClearDeviceMetricsOverrideResult.class,
                (code, msg)->new ClearDeviceMetricsOverrideResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<ClearDeviceMetricsOverrideResult> call(Executor exec) {
            return super.call("Page.clearDeviceMetricsOverride", ClearDeviceMetricsOverrideResult.class,
                (code, msg)->new ClearDeviceMetricsOverrideResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of clearDeviceMetricsOverride.
    <p><strong>Experimental.</strong></p>
    @Deprecated*/
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
    /**Clears the overridden Device Orientation.
    <p><strong>Experimental.</strong></p>
    @Deprecated*/
    public ClearDeviceOrientationOverrideParameter clearDeviceOrientationOverride() { final ClearDeviceOrientationOverrideParameter v = new ClearDeviceOrientationOverrideParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of clearDeviceOrientationOverride.
    <p><strong>Experimental.</strong></p>
    @Deprecated*/
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
            return super.call("Page.clearDeviceOrientationOverride", ClearDeviceOrientationOverrideResult.class,
                (code, msg)->new ClearDeviceOrientationOverrideResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<ClearDeviceOrientationOverrideResult> call(Executor exec) {
            return super.call("Page.clearDeviceOrientationOverride", ClearDeviceOrientationOverrideResult.class,
                (code, msg)->new ClearDeviceOrientationOverrideResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of clearDeviceOrientationOverride.
    <p><strong>Experimental.</strong></p>
    @Deprecated*/
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
    /**Clears the overriden Geolocation Position and Error.
    @Deprecated*/
    public ClearGeolocationOverrideParameter clearGeolocationOverride() { final ClearGeolocationOverrideParameter v = new ClearGeolocationOverrideParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of clearGeolocationOverride.
    @Deprecated*/
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
            return super.call("Page.clearGeolocationOverride", ClearGeolocationOverrideResult.class,
                (code, msg)->new ClearGeolocationOverrideResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<ClearGeolocationOverrideResult> call(Executor exec) {
            return super.call("Page.clearGeolocationOverride", ClearGeolocationOverrideResult.class,
                (code, msg)->new ClearGeolocationOverrideResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of clearGeolocationOverride.
    @Deprecated*/
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
    /**Creates an isolated world for the given frame.*/
    public CreateIsolatedWorldParameter createIsolatedWorld() { final CreateIsolatedWorldParameter v = new CreateIsolatedWorldParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of createIsolatedWorld.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class CreateIsolatedWorldParameter extends CommandBase {
        /**Id of the frame in which the isolated world should be created.*/
        private FrameId frameId;
        /**An optional name which is reported in the Execution Context.
        <em>Optional.</em>*/
        private String worldName;
        /**Whether or not universal access should be granted to the isolated world. This is a powerful
option, use with caution.
        <em>Optional.</em>*/
        private Boolean grantUniveralAccess;
        public final CreateIsolatedWorldParameter frameId(FrameId frameId) { this.frameId = frameId; return this; }
        public final CreateIsolatedWorldParameter setFrameId(FrameId frameId) { return frameId(frameId); }
        public final FrameId frameId() { return frameId; }
        public final FrameId getFrameId() { return frameId(); }
        public final CreateIsolatedWorldParameter worldName(@Nullable String worldName) { this.worldName = worldName; return this; }
        public final CreateIsolatedWorldParameter optWorldName(@Nullable String worldName) { return worldName(worldName); }
        public final String worldName() { return worldName; }
        public final String getWorldName() { return worldName(); }
        public final CreateIsolatedWorldParameter grantUniveralAccess(@Nullable Boolean grantUniveralAccess) { this.grantUniveralAccess = grantUniveralAccess; return this; }
        public final CreateIsolatedWorldParameter optGrantUniveralAccess(@Nullable Boolean grantUniveralAccess) { return grantUniveralAccess(grantUniveralAccess); }
        public final Boolean grantUniveralAccess() { return grantUniveralAccess; }
        public final Boolean getGrantUniveralAccess() { return grantUniveralAccess(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (frameId == null) throw new IllegalArgumentException("Page.CreateIsolatedWorldParameter.frameId is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            frameId.toJson(strBuilder.append("\"frameId\":"));
            if (worldName != null) strBuilder.append(",\"worldName\":").append('"').append(DomainBase.escapeJson(worldName)).append('"');
            if (grantUniveralAccess != null) strBuilder.append(",\"grantUniveralAccess\":").append(grantUniveralAccess);
            strBuilder.append('}');
            return strBuilder;
        }
        public CreateIsolatedWorldParameter() {}
        public CreateIsolatedWorldParameter(
            @JsonProperty("frameId")FrameId frameId,
            @Nullable @JsonProperty("worldName")String worldName,
            @Nullable @JsonProperty("grantUniveralAccess")Boolean grantUniveralAccess
        ) {
            this();
            this.frameId = frameId;
            this.worldName = worldName;
            this.grantUniveralAccess = grantUniveralAccess;
        }
        public CompletableFuture<CreateIsolatedWorldResult> call() {
            return super.call("Page.createIsolatedWorld", CreateIsolatedWorldResult.class,
                (code, msg)->new CreateIsolatedWorldResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<CreateIsolatedWorldResult> call(Executor exec) {
            return super.call("Page.createIsolatedWorld", CreateIsolatedWorldResult.class,
                (code, msg)->new CreateIsolatedWorldResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of createIsolatedWorld.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class CreateIsolatedWorldResult extends ResultBase {
        /**Execution context of the isolated world.*/
        private final Runtime.ExecutionContextId executionContextId;
        public final Runtime.ExecutionContextId executionContextId() { return executionContextId; }
        public final Runtime.ExecutionContextId getExecutionContextId() { return executionContextId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            executionContextId.toJson(strBuilder.append("\"executionContextId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public CreateIsolatedWorldResult(
            @JsonProperty("executionContextId")Runtime.ExecutionContextId executionContextId
        ) {
            this.executionContextId = executionContextId;
        }
        public CreateIsolatedWorldResult(ResultBase.FailedResult e) {
            super(e);
            executionContextId = null;
        }
    }
    /**Deletes browser cookie with given name, domain and path.
    <p><strong>Experimental.</strong></p>
    @Deprecated*/
    public DeleteCookieParameter deleteCookie() { final DeleteCookieParameter v = new DeleteCookieParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of deleteCookie.
    <p><strong>Experimental.</strong></p>
    @Deprecated*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class DeleteCookieParameter extends CommandBase {
        /**Name of the cookie to remove.*/
        private String cookieName;
        /**URL to match cooke domain and path.*/
        private String url;
        public final DeleteCookieParameter cookieName(String cookieName) { this.cookieName = cookieName; return this; }
        public final DeleteCookieParameter setCookieName(String cookieName) { return cookieName(cookieName); }
        public final String cookieName() { return cookieName; }
        public final String getCookieName() { return cookieName(); }
        public final DeleteCookieParameter url(String url) { this.url = url; return this; }
        public final DeleteCookieParameter setUrl(String url) { return url(url); }
        public final String url() { return url; }
        public final String getUrl() { return url(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (cookieName == null) throw new IllegalArgumentException("Page.DeleteCookieParameter.cookieName is necessary field.");
            if (url == null) throw new IllegalArgumentException("Page.DeleteCookieParameter.url is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"cookieName\":").append('"').append(DomainBase.escapeJson(cookieName)).append('"');
            strBuilder.append(",\"url\":").append('"').append(DomainBase.escapeJson(url)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public DeleteCookieParameter() {}
        public DeleteCookieParameter(
            @JsonProperty("cookieName")String cookieName,
            @JsonProperty("url")String url
        ) {
            this();
            this.cookieName = cookieName;
            this.url = url;
        }
        public CompletableFuture<DeleteCookieResult> call() {
            return super.call("Page.deleteCookie", DeleteCookieResult.class,
                (code, msg)->new DeleteCookieResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<DeleteCookieResult> call(Executor exec) {
            return super.call("Page.deleteCookie", DeleteCookieResult.class,
                (code, msg)->new DeleteCookieResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of deleteCookie.
    <p><strong>Experimental.</strong></p>
    @Deprecated*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class DeleteCookieResult extends ResultBase {
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
        public DeleteCookieResult() { super(); }
        public DeleteCookieResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Disables page domain notifications.*/
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
            return super.call("Page.disable", DisableResult.class,
                (code, msg)->new DisableResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<DisableResult> call(Executor exec) {
            return super.call("Page.disable", DisableResult.class,
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
    /**Enables page domain notifications.*/
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
            return super.call("Page.enable", EnableResult.class,
                (code, msg)->new EnableResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<EnableResult> call(Executor exec) {
            return super.call("Page.enable", EnableResult.class,
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
    public GetAppManifestParameter getAppManifest() { final GetAppManifestParameter v = new GetAppManifestParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of getAppManifest.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetAppManifestParameter extends CommandBase {
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
        public GetAppManifestParameter() {}
        public CompletableFuture<GetAppManifestResult> call() {
            return super.call("Page.getAppManifest", GetAppManifestResult.class,
                (code, msg)->new GetAppManifestResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetAppManifestResult> call(Executor exec) {
            return super.call("Page.getAppManifest", GetAppManifestResult.class,
                (code, msg)->new GetAppManifestResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of getAppManifest.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetAppManifestResult extends ResultBase {
        /**Manifest location.*/
        private final String url;
        /**&lt;No document in protocol.&gt;*/
        private final List<AppManifestError> errors;
        /**Manifest content.
        <em>Optional.</em>*/
        private final String data;
        public final String url() { return url; }
        public final String getUrl() { return url(); }
        public final List<AppManifestError> errors() { return errors; }
        public final List<AppManifestError> getErrors() { return errors(); }
        public final String data() { return data; }
        public final String getData() { return data(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"url\":").append('"').append(DomainBase.escapeJson(url)).append('"');
                        strBuilder.append(",\"errors\":[");
            errors.get(0).toJson(strBuilder);
            for (int i = 1; i < errors.size(); ++i)
                errors.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            if (data != null) strBuilder.append(",\"data\":").append('"').append(DomainBase.escapeJson(data)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public GetAppManifestResult(
            @JsonProperty("url")String url,
            @JsonProperty("errors")List<AppManifestError> errors,
            @Nullable @JsonProperty("data")String data
        ) {
            this.url = url;
            this.errors = errors;
            this.data = data;
        }
        public GetAppManifestResult(ResultBase.FailedResult e) {
            super(e);
            url = null;
            errors = null;
            data = null;
        }
    }
    /**Returns all browser cookies. Depending on the backend support, will return detailed cookie
information in the `cookies` field.
    <p><strong>Experimental.</strong></p>
    @Deprecated*/
    public GetCookiesParameter getCookies() { final GetCookiesParameter v = new GetCookiesParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of getCookies.
    <p><strong>Experimental.</strong></p>
    @Deprecated*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetCookiesParameter extends CommandBase {
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
        public GetCookiesParameter() {}
        public CompletableFuture<GetCookiesResult> call() {
            return super.call("Page.getCookies", GetCookiesResult.class,
                (code, msg)->new GetCookiesResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetCookiesResult> call(Executor exec) {
            return super.call("Page.getCookies", GetCookiesResult.class,
                (code, msg)->new GetCookiesResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of getCookies.
    <p><strong>Experimental.</strong></p>
    @Deprecated*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetCookiesResult extends ResultBase {
        /**Array of cookie objects.*/
        private final List<Network.Cookie> cookies;
        public final List<Network.Cookie> cookies() { return cookies; }
        public final List<Network.Cookie> getCookies() { return cookies(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
                        strBuilder.append("\"cookies\":[");
            cookies.get(0).toJson(strBuilder);
            for (int i = 1; i < cookies.size(); ++i)
                cookies.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public GetCookiesResult(
            @JsonProperty("cookies")List<Network.Cookie> cookies
        ) {
            this.cookies = cookies;
        }
        public GetCookiesResult(ResultBase.FailedResult e) {
            super(e);
            cookies = null;
        }
    }
    /**Returns present frame tree structure.*/
    public GetFrameTreeParameter getFrameTree() { final GetFrameTreeParameter v = new GetFrameTreeParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of getFrameTree.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetFrameTreeParameter extends CommandBase {
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
        public GetFrameTreeParameter() {}
        public CompletableFuture<GetFrameTreeResult> call() {
            return super.call("Page.getFrameTree", GetFrameTreeResult.class,
                (code, msg)->new GetFrameTreeResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetFrameTreeResult> call(Executor exec) {
            return super.call("Page.getFrameTree", GetFrameTreeResult.class,
                (code, msg)->new GetFrameTreeResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of getFrameTree.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetFrameTreeResult extends ResultBase {
        /**Present frame tree structure.*/
        private final FrameTree frameTree;
        public final FrameTree frameTree() { return frameTree; }
        public final FrameTree getFrameTree() { return frameTree(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            frameTree.toJson(strBuilder.append("\"frameTree\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public GetFrameTreeResult(
            @JsonProperty("frameTree")FrameTree frameTree
        ) {
            this.frameTree = frameTree;
        }
        public GetFrameTreeResult(ResultBase.FailedResult e) {
            super(e);
            frameTree = null;
        }
    }
    /**Returns metrics relating to the layouting of the page, such as viewport bounds/scale.*/
    public GetLayoutMetricsParameter getLayoutMetrics() { final GetLayoutMetricsParameter v = new GetLayoutMetricsParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of getLayoutMetrics.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetLayoutMetricsParameter extends CommandBase {
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
        public GetLayoutMetricsParameter() {}
        public CompletableFuture<GetLayoutMetricsResult> call() {
            return super.call("Page.getLayoutMetrics", GetLayoutMetricsResult.class,
                (code, msg)->new GetLayoutMetricsResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetLayoutMetricsResult> call(Executor exec) {
            return super.call("Page.getLayoutMetrics", GetLayoutMetricsResult.class,
                (code, msg)->new GetLayoutMetricsResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of getLayoutMetrics.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetLayoutMetricsResult extends ResultBase {
        /**Metrics relating to the layout viewport.*/
        private final LayoutViewport layoutViewport;
        /**Metrics relating to the visual viewport.*/
        private final VisualViewport visualViewport;
        /**Size of scrollable area.*/
        private final DOM.Rect contentSize;
        public final LayoutViewport layoutViewport() { return layoutViewport; }
        public final LayoutViewport getLayoutViewport() { return layoutViewport(); }
        public final VisualViewport visualViewport() { return visualViewport; }
        public final VisualViewport getVisualViewport() { return visualViewport(); }
        public final DOM.Rect contentSize() { return contentSize; }
        public final DOM.Rect getContentSize() { return contentSize(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            layoutViewport.toJson(strBuilder.append("\"layoutViewport\":"));
            visualViewport.toJson(strBuilder.append(",\"visualViewport\":"));
            contentSize.toJson(strBuilder.append(",\"contentSize\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public GetLayoutMetricsResult(
            @JsonProperty("layoutViewport")LayoutViewport layoutViewport,
            @JsonProperty("visualViewport")VisualViewport visualViewport,
            @JsonProperty("contentSize")DOM.Rect contentSize
        ) {
            this.layoutViewport = layoutViewport;
            this.visualViewport = visualViewport;
            this.contentSize = contentSize;
        }
        public GetLayoutMetricsResult(ResultBase.FailedResult e) {
            super(e);
            layoutViewport = null;
            visualViewport = null;
            contentSize = null;
        }
    }
    /**Returns navigation history for the current page.*/
    public GetNavigationHistoryParameter getNavigationHistory() { final GetNavigationHistoryParameter v = new GetNavigationHistoryParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of getNavigationHistory.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetNavigationHistoryParameter extends CommandBase {
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
        public GetNavigationHistoryParameter() {}
        public CompletableFuture<GetNavigationHistoryResult> call() {
            return super.call("Page.getNavigationHistory", GetNavigationHistoryResult.class,
                (code, msg)->new GetNavigationHistoryResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetNavigationHistoryResult> call(Executor exec) {
            return super.call("Page.getNavigationHistory", GetNavigationHistoryResult.class,
                (code, msg)->new GetNavigationHistoryResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of getNavigationHistory.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetNavigationHistoryResult extends ResultBase {
        /**Index of the current navigation history entry.*/
        private final Integer currentIndex;
        /**Array of navigation history entries.*/
        private final List<NavigationEntry> entries;
        public final Integer currentIndex() { return currentIndex; }
        public final Integer getCurrentIndex() { return currentIndex(); }
        public final List<NavigationEntry> entries() { return entries; }
        public final List<NavigationEntry> getEntries() { return entries(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"currentIndex\":").append(currentIndex);
                        strBuilder.append(",\"entries\":[");
            entries.get(0).toJson(strBuilder);
            for (int i = 1; i < entries.size(); ++i)
                entries.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public GetNavigationHistoryResult(
            @JsonProperty("currentIndex")Integer currentIndex,
            @JsonProperty("entries")List<NavigationEntry> entries
        ) {
            this.currentIndex = currentIndex;
            this.entries = entries;
        }
        public GetNavigationHistoryResult(ResultBase.FailedResult e) {
            super(e);
            currentIndex = null;
            entries = null;
        }
    }
    /**Returns content of the given resource.
    <p><strong>Experimental.</strong></p>*/
    public GetResourceContentParameter getResourceContent() { final GetResourceContentParameter v = new GetResourceContentParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of getResourceContent.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetResourceContentParameter extends CommandBase {
        /**Frame id to get resource for.*/
        private FrameId frameId;
        /**URL of the resource to get content for.*/
        private String url;
        public final GetResourceContentParameter frameId(FrameId frameId) { this.frameId = frameId; return this; }
        public final GetResourceContentParameter setFrameId(FrameId frameId) { return frameId(frameId); }
        public final FrameId frameId() { return frameId; }
        public final FrameId getFrameId() { return frameId(); }
        public final GetResourceContentParameter url(String url) { this.url = url; return this; }
        public final GetResourceContentParameter setUrl(String url) { return url(url); }
        public final String url() { return url; }
        public final String getUrl() { return url(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (frameId == null) throw new IllegalArgumentException("Page.GetResourceContentParameter.frameId is necessary field.");
            if (url == null) throw new IllegalArgumentException("Page.GetResourceContentParameter.url is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            frameId.toJson(strBuilder.append("\"frameId\":"));
            strBuilder.append(",\"url\":").append('"').append(DomainBase.escapeJson(url)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public GetResourceContentParameter() {}
        public GetResourceContentParameter(
            @JsonProperty("frameId")FrameId frameId,
            @JsonProperty("url")String url
        ) {
            this();
            this.frameId = frameId;
            this.url = url;
        }
        public CompletableFuture<GetResourceContentResult> call() {
            return super.call("Page.getResourceContent", GetResourceContentResult.class,
                (code, msg)->new GetResourceContentResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetResourceContentResult> call(Executor exec) {
            return super.call("Page.getResourceContent", GetResourceContentResult.class,
                (code, msg)->new GetResourceContentResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of getResourceContent.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetResourceContentResult extends ResultBase {
        /**Resource content.*/
        private final String content;
        /**True, if content was served as base64.*/
        private final Boolean base64Encoded;
        public final String content() { return content; }
        public final String getContent() { return content(); }
        public final Boolean base64Encoded() { return base64Encoded; }
        public final Boolean getBase64Encoded() { return base64Encoded(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"content\":").append('"').append(DomainBase.escapeJson(content)).append('"');
            strBuilder.append(",\"base64Encoded\":").append(base64Encoded);
            strBuilder.append('}');
            return strBuilder;
        }
        public GetResourceContentResult(
            @JsonProperty("content")String content,
            @JsonProperty("base64Encoded")Boolean base64Encoded
        ) {
            this.content = content;
            this.base64Encoded = base64Encoded;
        }
        public GetResourceContentResult(ResultBase.FailedResult e) {
            super(e);
            content = null;
            base64Encoded = null;
        }
    }
    /**Returns present frame / resource tree structure.
    <p><strong>Experimental.</strong></p>*/
    public GetResourceTreeParameter getResourceTree() { final GetResourceTreeParameter v = new GetResourceTreeParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of getResourceTree.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetResourceTreeParameter extends CommandBase {
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
        public GetResourceTreeParameter() {}
        public CompletableFuture<GetResourceTreeResult> call() {
            return super.call("Page.getResourceTree", GetResourceTreeResult.class,
                (code, msg)->new GetResourceTreeResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetResourceTreeResult> call(Executor exec) {
            return super.call("Page.getResourceTree", GetResourceTreeResult.class,
                (code, msg)->new GetResourceTreeResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of getResourceTree.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetResourceTreeResult extends ResultBase {
        /**Present frame / resource tree structure.*/
        private final FrameResourceTree frameTree;
        public final FrameResourceTree frameTree() { return frameTree; }
        public final FrameResourceTree getFrameTree() { return frameTree(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            frameTree.toJson(strBuilder.append("\"frameTree\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public GetResourceTreeResult(
            @JsonProperty("frameTree")FrameResourceTree frameTree
        ) {
            this.frameTree = frameTree;
        }
        public GetResourceTreeResult(ResultBase.FailedResult e) {
            super(e);
            frameTree = null;
        }
    }
    /**Accepts or dismisses a JavaScript initiated dialog (alert, confirm, prompt, or onbeforeunload).*/
    public HandleJavaScriptDialogParameter handleJavaScriptDialog() { final HandleJavaScriptDialogParameter v = new HandleJavaScriptDialogParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of handleJavaScriptDialog.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class HandleJavaScriptDialogParameter extends CommandBase {
        /**Whether to accept or dismiss the dialog.*/
        private Boolean accept;
        /**The text to enter into the dialog prompt before accepting. Used only if this is a prompt
dialog.
        <em>Optional.</em>*/
        private String promptText;
        public final HandleJavaScriptDialogParameter accept(Boolean accept) { this.accept = accept; return this; }
        public final HandleJavaScriptDialogParameter setAccept(Boolean accept) { return accept(accept); }
        public final Boolean accept() { return accept; }
        public final Boolean getAccept() { return accept(); }
        public final HandleJavaScriptDialogParameter promptText(@Nullable String promptText) { this.promptText = promptText; return this; }
        public final HandleJavaScriptDialogParameter optPromptText(@Nullable String promptText) { return promptText(promptText); }
        public final String promptText() { return promptText; }
        public final String getPromptText() { return promptText(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (accept == null) throw new IllegalArgumentException("Page.HandleJavaScriptDialogParameter.accept is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"accept\":").append(accept);
            if (promptText != null) strBuilder.append(",\"promptText\":").append('"').append(DomainBase.escapeJson(promptText)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public HandleJavaScriptDialogParameter() {}
        public HandleJavaScriptDialogParameter(
            @JsonProperty("accept")Boolean accept,
            @Nullable @JsonProperty("promptText")String promptText
        ) {
            this();
            this.accept = accept;
            this.promptText = promptText;
        }
        public CompletableFuture<HandleJavaScriptDialogResult> call() {
            return super.call("Page.handleJavaScriptDialog", HandleJavaScriptDialogResult.class,
                (code, msg)->new HandleJavaScriptDialogResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<HandleJavaScriptDialogResult> call(Executor exec) {
            return super.call("Page.handleJavaScriptDialog", HandleJavaScriptDialogResult.class,
                (code, msg)->new HandleJavaScriptDialogResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of handleJavaScriptDialog.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class HandleJavaScriptDialogResult extends ResultBase {
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
        public HandleJavaScriptDialogResult() { super(); }
        public HandleJavaScriptDialogResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Navigates current page to the given URL.*/
    public NavigateParameter navigate() { final NavigateParameter v = new NavigateParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of navigate.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class NavigateParameter extends CommandBase {
        /**URL to navigate the page to.*/
        private String url;
        /**Referrer URL.
        <em>Optional.</em>*/
        private String referrer;
        /**Intended transition type.
        <em>Optional.</em>*/
        private TransitionType transitionType;
        /**Frame id to navigate, if not specified navigates the top frame.
        <em>Optional.</em>*/
        private FrameId frameId;
        public final NavigateParameter url(String url) { this.url = url; return this; }
        public final NavigateParameter setUrl(String url) { return url(url); }
        public final String url() { return url; }
        public final String getUrl() { return url(); }
        public final NavigateParameter referrer(@Nullable String referrer) { this.referrer = referrer; return this; }
        public final NavigateParameter optReferrer(@Nullable String referrer) { return referrer(referrer); }
        public final String referrer() { return referrer; }
        public final String getReferrer() { return referrer(); }
        public final NavigateParameter transitionType(@Nullable TransitionType transitionType) { this.transitionType = transitionType; return this; }
        public final NavigateParameter optTransitionType(@Nullable TransitionType transitionType) { return transitionType(transitionType); }
        public final TransitionType transitionType() { return transitionType; }
        public final TransitionType getTransitionType() { return transitionType(); }
        public final NavigateParameter frameId(@Nullable FrameId frameId) { this.frameId = frameId; return this; }
        public final NavigateParameter optFrameId(@Nullable FrameId frameId) { return frameId(frameId); }
        public final FrameId frameId() { return frameId; }
        public final FrameId getFrameId() { return frameId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (url == null) throw new IllegalArgumentException("Page.NavigateParameter.url is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"url\":").append('"').append(DomainBase.escapeJson(url)).append('"');
            if (referrer != null) strBuilder.append(",\"referrer\":").append('"').append(DomainBase.escapeJson(referrer)).append('"');
            if (transitionType != null) transitionType.toJson(strBuilder.append(",\"transitionType\":"));
            if (frameId != null) frameId.toJson(strBuilder.append(",\"frameId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public NavigateParameter() {}
        public NavigateParameter(
            @JsonProperty("url")String url,
            @Nullable @JsonProperty("referrer")String referrer,
            @Nullable @JsonProperty("transitionType")TransitionType transitionType,
            @Nullable @JsonProperty("frameId")FrameId frameId
        ) {
            this();
            this.url = url;
            this.referrer = referrer;
            this.transitionType = transitionType;
            this.frameId = frameId;
        }
        public CompletableFuture<NavigateResult> call() {
            return super.call("Page.navigate", NavigateResult.class,
                (code, msg)->new NavigateResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<NavigateResult> call(Executor exec) {
            return super.call("Page.navigate", NavigateResult.class,
                (code, msg)->new NavigateResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of navigate.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class NavigateResult extends ResultBase {
        /**Frame id that has navigated (or failed to navigate)*/
        private final FrameId frameId;
        /**Loader identifier.
        <em>Optional.</em>*/
        private final Network.LoaderId loaderId;
        /**User friendly error message, present if and only if navigation has failed.
        <em>Optional.</em>*/
        private final String errorText;
        public final FrameId frameId() { return frameId; }
        public final FrameId getFrameId() { return frameId(); }
        public final Network.LoaderId loaderId() { return loaderId; }
        public final Network.LoaderId getLoaderId() { return loaderId(); }
        public final String errorText() { return errorText; }
        public final String getErrorText() { return errorText(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            frameId.toJson(strBuilder.append("\"frameId\":"));
            if (loaderId != null) loaderId.toJson(strBuilder.append(",\"loaderId\":"));
            if (errorText != null) strBuilder.append(",\"errorText\":").append('"').append(DomainBase.escapeJson(errorText)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public NavigateResult(
            @JsonProperty("frameId")FrameId frameId,
            @Nullable @JsonProperty("loaderId")Network.LoaderId loaderId,
            @Nullable @JsonProperty("errorText")String errorText
        ) {
            this.frameId = frameId;
            this.loaderId = loaderId;
            this.errorText = errorText;
        }
        public NavigateResult(ResultBase.FailedResult e) {
            super(e);
            frameId = null;
            loaderId = null;
            errorText = null;
        }
    }
    /**Navigates current page to the given history entry.*/
    public NavigateToHistoryEntryParameter navigateToHistoryEntry() { final NavigateToHistoryEntryParameter v = new NavigateToHistoryEntryParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of navigateToHistoryEntry.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class NavigateToHistoryEntryParameter extends CommandBase {
        /**Unique id of the entry to navigate to.*/
        private Integer entryId;
        public final NavigateToHistoryEntryParameter entryId(Integer entryId) { this.entryId = entryId; return this; }
        public final NavigateToHistoryEntryParameter setEntryId(Integer entryId) { return entryId(entryId); }
        public final Integer entryId() { return entryId; }
        public final Integer getEntryId() { return entryId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (entryId == null) throw new IllegalArgumentException("Page.NavigateToHistoryEntryParameter.entryId is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"entryId\":").append(entryId);
            strBuilder.append('}');
            return strBuilder;
        }
        public NavigateToHistoryEntryParameter() {}
        public NavigateToHistoryEntryParameter(
            @JsonProperty("entryId")Integer entryId
        ) {
            this();
            this.entryId = entryId;
        }
        public CompletableFuture<NavigateToHistoryEntryResult> call() {
            return super.call("Page.navigateToHistoryEntry", NavigateToHistoryEntryResult.class,
                (code, msg)->new NavigateToHistoryEntryResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<NavigateToHistoryEntryResult> call(Executor exec) {
            return super.call("Page.navigateToHistoryEntry", NavigateToHistoryEntryResult.class,
                (code, msg)->new NavigateToHistoryEntryResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of navigateToHistoryEntry.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class NavigateToHistoryEntryResult extends ResultBase {
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
        public NavigateToHistoryEntryResult() { super(); }
        public NavigateToHistoryEntryResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Print page as PDF.*/
    public PrintToPDFParameter printToPDF() { final PrintToPDFParameter v = new PrintToPDFParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of printToPDF.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class PrintToPDFParameter extends CommandBase {
        /**Paper orientation. Defaults to false.
        <em>Optional.</em>*/
        private Boolean landscape;
        /**Display header and footer. Defaults to false.
        <em>Optional.</em>*/
        private Boolean displayHeaderFooter;
        /**Print background graphics. Defaults to false.
        <em>Optional.</em>*/
        private Boolean printBackground;
        /**Scale of the webpage rendering. Defaults to 1.
        <em>Optional.</em>*/
        private Double scale;
        /**Paper width in inches. Defaults to 8.5 inches.
        <em>Optional.</em>*/
        private Double paperWidth;
        /**Paper height in inches. Defaults to 11 inches.
        <em>Optional.</em>*/
        private Double paperHeight;
        /**Top margin in inches. Defaults to 1cm (~0.4 inches).
        <em>Optional.</em>*/
        private Double marginTop;
        /**Bottom margin in inches. Defaults to 1cm (~0.4 inches).
        <em>Optional.</em>*/
        private Double marginBottom;
        /**Left margin in inches. Defaults to 1cm (~0.4 inches).
        <em>Optional.</em>*/
        private Double marginLeft;
        /**Right margin in inches. Defaults to 1cm (~0.4 inches).
        <em>Optional.</em>*/
        private Double marginRight;
        /**Paper ranges to print, e.g., '1-5, 8, 11-13'. Defaults to the empty string, which means
print all pages.
        <em>Optional.</em>*/
        private String pageRanges;
        /**Whether to silently ignore invalid but successfully parsed page ranges, such as '3-2'.
Defaults to false.
        <em>Optional.</em>*/
        private Boolean ignoreInvalidPageRanges;
        /**HTML template for the print header. Should be valid HTML markup with following
classes used to inject printing values into them:
- `date`: formatted print date
- `title`: document title
- `url`: document location
- `pageNumber`: current page number
- `totalPages`: total pages in the document

For example, `<span class=title></span>` would generate span containing the title.
        <em>Optional.</em>*/
        private String headerTemplate;
        /**HTML template for the print footer. Should use the same format as the `headerTemplate`.
        <em>Optional.</em>*/
        private String footerTemplate;
        /**Whether or not to prefer page size as defined by css. Defaults to false,
in which case the content will be scaled to fit the paper size.
        <em>Optional.</em>*/
        private Boolean preferCSSPageSize;
        public final PrintToPDFParameter landscape(@Nullable Boolean landscape) { this.landscape = landscape; return this; }
        public final PrintToPDFParameter optLandscape(@Nullable Boolean landscape) { return landscape(landscape); }
        public final Boolean landscape() { return landscape; }
        public final Boolean getLandscape() { return landscape(); }
        public final PrintToPDFParameter displayHeaderFooter(@Nullable Boolean displayHeaderFooter) { this.displayHeaderFooter = displayHeaderFooter; return this; }
        public final PrintToPDFParameter optDisplayHeaderFooter(@Nullable Boolean displayHeaderFooter) { return displayHeaderFooter(displayHeaderFooter); }
        public final Boolean displayHeaderFooter() { return displayHeaderFooter; }
        public final Boolean getDisplayHeaderFooter() { return displayHeaderFooter(); }
        public final PrintToPDFParameter printBackground(@Nullable Boolean printBackground) { this.printBackground = printBackground; return this; }
        public final PrintToPDFParameter optPrintBackground(@Nullable Boolean printBackground) { return printBackground(printBackground); }
        public final Boolean printBackground() { return printBackground; }
        public final Boolean getPrintBackground() { return printBackground(); }
        public final PrintToPDFParameter scale(@Nullable Double scale) { this.scale = scale; return this; }
        public final PrintToPDFParameter optScale(@Nullable Double scale) { return scale(scale); }
        public final Double scale() { return scale; }
        public final Double getScale() { return scale(); }
        public final PrintToPDFParameter paperWidth(@Nullable Double paperWidth) { this.paperWidth = paperWidth; return this; }
        public final PrintToPDFParameter optPaperWidth(@Nullable Double paperWidth) { return paperWidth(paperWidth); }
        public final Double paperWidth() { return paperWidth; }
        public final Double getPaperWidth() { return paperWidth(); }
        public final PrintToPDFParameter paperHeight(@Nullable Double paperHeight) { this.paperHeight = paperHeight; return this; }
        public final PrintToPDFParameter optPaperHeight(@Nullable Double paperHeight) { return paperHeight(paperHeight); }
        public final Double paperHeight() { return paperHeight; }
        public final Double getPaperHeight() { return paperHeight(); }
        public final PrintToPDFParameter marginTop(@Nullable Double marginTop) { this.marginTop = marginTop; return this; }
        public final PrintToPDFParameter optMarginTop(@Nullable Double marginTop) { return marginTop(marginTop); }
        public final Double marginTop() { return marginTop; }
        public final Double getMarginTop() { return marginTop(); }
        public final PrintToPDFParameter marginBottom(@Nullable Double marginBottom) { this.marginBottom = marginBottom; return this; }
        public final PrintToPDFParameter optMarginBottom(@Nullable Double marginBottom) { return marginBottom(marginBottom); }
        public final Double marginBottom() { return marginBottom; }
        public final Double getMarginBottom() { return marginBottom(); }
        public final PrintToPDFParameter marginLeft(@Nullable Double marginLeft) { this.marginLeft = marginLeft; return this; }
        public final PrintToPDFParameter optMarginLeft(@Nullable Double marginLeft) { return marginLeft(marginLeft); }
        public final Double marginLeft() { return marginLeft; }
        public final Double getMarginLeft() { return marginLeft(); }
        public final PrintToPDFParameter marginRight(@Nullable Double marginRight) { this.marginRight = marginRight; return this; }
        public final PrintToPDFParameter optMarginRight(@Nullable Double marginRight) { return marginRight(marginRight); }
        public final Double marginRight() { return marginRight; }
        public final Double getMarginRight() { return marginRight(); }
        public final PrintToPDFParameter pageRanges(@Nullable String pageRanges) { this.pageRanges = pageRanges; return this; }
        public final PrintToPDFParameter optPageRanges(@Nullable String pageRanges) { return pageRanges(pageRanges); }
        public final String pageRanges() { return pageRanges; }
        public final String getPageRanges() { return pageRanges(); }
        public final PrintToPDFParameter ignoreInvalidPageRanges(@Nullable Boolean ignoreInvalidPageRanges) { this.ignoreInvalidPageRanges = ignoreInvalidPageRanges; return this; }
        public final PrintToPDFParameter optIgnoreInvalidPageRanges(@Nullable Boolean ignoreInvalidPageRanges) { return ignoreInvalidPageRanges(ignoreInvalidPageRanges); }
        public final Boolean ignoreInvalidPageRanges() { return ignoreInvalidPageRanges; }
        public final Boolean getIgnoreInvalidPageRanges() { return ignoreInvalidPageRanges(); }
        public final PrintToPDFParameter headerTemplate(@Nullable String headerTemplate) { this.headerTemplate = headerTemplate; return this; }
        public final PrintToPDFParameter optHeaderTemplate(@Nullable String headerTemplate) { return headerTemplate(headerTemplate); }
        public final String headerTemplate() { return headerTemplate; }
        public final String getHeaderTemplate() { return headerTemplate(); }
        public final PrintToPDFParameter footerTemplate(@Nullable String footerTemplate) { this.footerTemplate = footerTemplate; return this; }
        public final PrintToPDFParameter optFooterTemplate(@Nullable String footerTemplate) { return footerTemplate(footerTemplate); }
        public final String footerTemplate() { return footerTemplate; }
        public final String getFooterTemplate() { return footerTemplate(); }
        public final PrintToPDFParameter preferCSSPageSize(@Nullable Boolean preferCSSPageSize) { this.preferCSSPageSize = preferCSSPageSize; return this; }
        public final PrintToPDFParameter optPreferCSSPageSize(@Nullable Boolean preferCSSPageSize) { return preferCSSPageSize(preferCSSPageSize); }
        public final Boolean preferCSSPageSize() { return preferCSSPageSize; }
        public final Boolean getPreferCSSPageSize() { return preferCSSPageSize(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            if (landscape != null) strBuilder.append("\"landscape\":").append(landscape);
            if (displayHeaderFooter != null) strBuilder.append(",\"displayHeaderFooter\":").append(displayHeaderFooter);
            if (printBackground != null) strBuilder.append(",\"printBackground\":").append(printBackground);
            if (scale != null) strBuilder.append(",\"scale\":").append(scale);
            if (paperWidth != null) strBuilder.append(",\"paperWidth\":").append(paperWidth);
            if (paperHeight != null) strBuilder.append(",\"paperHeight\":").append(paperHeight);
            if (marginTop != null) strBuilder.append(",\"marginTop\":").append(marginTop);
            if (marginBottom != null) strBuilder.append(",\"marginBottom\":").append(marginBottom);
            if (marginLeft != null) strBuilder.append(",\"marginLeft\":").append(marginLeft);
            if (marginRight != null) strBuilder.append(",\"marginRight\":").append(marginRight);
            if (pageRanges != null) strBuilder.append(",\"pageRanges\":").append('"').append(DomainBase.escapeJson(pageRanges)).append('"');
            if (ignoreInvalidPageRanges != null) strBuilder.append(",\"ignoreInvalidPageRanges\":").append(ignoreInvalidPageRanges);
            if (headerTemplate != null) strBuilder.append(",\"headerTemplate\":").append('"').append(DomainBase.escapeJson(headerTemplate)).append('"');
            if (footerTemplate != null) strBuilder.append(",\"footerTemplate\":").append('"').append(DomainBase.escapeJson(footerTemplate)).append('"');
            if (preferCSSPageSize != null) strBuilder.append(",\"preferCSSPageSize\":").append(preferCSSPageSize);
            strBuilder.append('}');
            return strBuilder;
        }
        public PrintToPDFParameter() {}
        public PrintToPDFParameter(
            @Nullable @JsonProperty("landscape")Boolean landscape,
            @Nullable @JsonProperty("displayHeaderFooter")Boolean displayHeaderFooter,
            @Nullable @JsonProperty("printBackground")Boolean printBackground,
            @Nullable @JsonProperty("scale")Double scale,
            @Nullable @JsonProperty("paperWidth")Double paperWidth,
            @Nullable @JsonProperty("paperHeight")Double paperHeight,
            @Nullable @JsonProperty("marginTop")Double marginTop,
            @Nullable @JsonProperty("marginBottom")Double marginBottom,
            @Nullable @JsonProperty("marginLeft")Double marginLeft,
            @Nullable @JsonProperty("marginRight")Double marginRight,
            @Nullable @JsonProperty("pageRanges")String pageRanges,
            @Nullable @JsonProperty("ignoreInvalidPageRanges")Boolean ignoreInvalidPageRanges,
            @Nullable @JsonProperty("headerTemplate")String headerTemplate,
            @Nullable @JsonProperty("footerTemplate")String footerTemplate,
            @Nullable @JsonProperty("preferCSSPageSize")Boolean preferCSSPageSize
        ) {
            this();
            this.landscape = landscape;
            this.displayHeaderFooter = displayHeaderFooter;
            this.printBackground = printBackground;
            this.scale = scale;
            this.paperWidth = paperWidth;
            this.paperHeight = paperHeight;
            this.marginTop = marginTop;
            this.marginBottom = marginBottom;
            this.marginLeft = marginLeft;
            this.marginRight = marginRight;
            this.pageRanges = pageRanges;
            this.ignoreInvalidPageRanges = ignoreInvalidPageRanges;
            this.headerTemplate = headerTemplate;
            this.footerTemplate = footerTemplate;
            this.preferCSSPageSize = preferCSSPageSize;
        }
        public CompletableFuture<PrintToPDFResult> call() {
            return super.call("Page.printToPDF", PrintToPDFResult.class,
                (code, msg)->new PrintToPDFResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<PrintToPDFResult> call(Executor exec) {
            return super.call("Page.printToPDF", PrintToPDFResult.class,
                (code, msg)->new PrintToPDFResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of printToPDF.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class PrintToPDFResult extends ResultBase {
        /**Base64-encoded pdf data.*/
        private final String data;
        public final String data() { return data; }
        public final String getData() { return data(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"data\":").append('"').append(DomainBase.escapeJson(data)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public PrintToPDFResult(
            @JsonProperty("data")String data
        ) {
            this.data = data;
        }
        public PrintToPDFResult(ResultBase.FailedResult e) {
            super(e);
            data = null;
        }
    }
    /**Reloads given page optionally ignoring the cache.*/
    public ReloadParameter reload() { final ReloadParameter v = new ReloadParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of reload.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ReloadParameter extends CommandBase {
        /**If true, browser cache is ignored (as if the user pressed Shift+refresh).
        <em>Optional.</em>*/
        private Boolean ignoreCache;
        /**If set, the script will be injected into all frames of the inspected page after reload.
Argument will be ignored if reloading dataURL origin.
        <em>Optional.</em>*/
        private String scriptToEvaluateOnLoad;
        public final ReloadParameter ignoreCache(@Nullable Boolean ignoreCache) { this.ignoreCache = ignoreCache; return this; }
        public final ReloadParameter optIgnoreCache(@Nullable Boolean ignoreCache) { return ignoreCache(ignoreCache); }
        public final Boolean ignoreCache() { return ignoreCache; }
        public final Boolean getIgnoreCache() { return ignoreCache(); }
        public final ReloadParameter scriptToEvaluateOnLoad(@Nullable String scriptToEvaluateOnLoad) { this.scriptToEvaluateOnLoad = scriptToEvaluateOnLoad; return this; }
        public final ReloadParameter optScriptToEvaluateOnLoad(@Nullable String scriptToEvaluateOnLoad) { return scriptToEvaluateOnLoad(scriptToEvaluateOnLoad); }
        public final String scriptToEvaluateOnLoad() { return scriptToEvaluateOnLoad; }
        public final String getScriptToEvaluateOnLoad() { return scriptToEvaluateOnLoad(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            if (ignoreCache != null) strBuilder.append("\"ignoreCache\":").append(ignoreCache);
            if (scriptToEvaluateOnLoad != null) strBuilder.append(",\"scriptToEvaluateOnLoad\":").append('"').append(DomainBase.escapeJson(scriptToEvaluateOnLoad)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public ReloadParameter() {}
        public ReloadParameter(
            @Nullable @JsonProperty("ignoreCache")Boolean ignoreCache,
            @Nullable @JsonProperty("scriptToEvaluateOnLoad")String scriptToEvaluateOnLoad
        ) {
            this();
            this.ignoreCache = ignoreCache;
            this.scriptToEvaluateOnLoad = scriptToEvaluateOnLoad;
        }
        public CompletableFuture<ReloadResult> call() {
            return super.call("Page.reload", ReloadResult.class,
                (code, msg)->new ReloadResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<ReloadResult> call(Executor exec) {
            return super.call("Page.reload", ReloadResult.class,
                (code, msg)->new ReloadResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of reload.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ReloadResult extends ResultBase {
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
        public ReloadResult() { super(); }
        public ReloadResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Deprecated, please use removeScriptToEvaluateOnNewDocument instead.
    <p><strong>Experimental.</strong></p>
    @Deprecated*/
    public RemoveScriptToEvaluateOnLoadParameter removeScriptToEvaluateOnLoad() { final RemoveScriptToEvaluateOnLoadParameter v = new RemoveScriptToEvaluateOnLoadParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of removeScriptToEvaluateOnLoad.
    <p><strong>Experimental.</strong></p>
    @Deprecated*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class RemoveScriptToEvaluateOnLoadParameter extends CommandBase {
        /**&lt;No document in protocol.&gt;*/
        private ScriptIdentifier identifier;
        public final RemoveScriptToEvaluateOnLoadParameter identifier(ScriptIdentifier identifier) { this.identifier = identifier; return this; }
        public final RemoveScriptToEvaluateOnLoadParameter setIdentifier(ScriptIdentifier identifier) { return identifier(identifier); }
        public final ScriptIdentifier identifier() { return identifier; }
        public final ScriptIdentifier getIdentifier() { return identifier(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (identifier == null) throw new IllegalArgumentException("Page.RemoveScriptToEvaluateOnLoadParameter.identifier is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            identifier.toJson(strBuilder.append("\"identifier\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public RemoveScriptToEvaluateOnLoadParameter() {}
        public RemoveScriptToEvaluateOnLoadParameter(
            @JsonProperty("identifier")ScriptIdentifier identifier
        ) {
            this();
            this.identifier = identifier;
        }
        public CompletableFuture<RemoveScriptToEvaluateOnLoadResult> call() {
            return super.call("Page.removeScriptToEvaluateOnLoad", RemoveScriptToEvaluateOnLoadResult.class,
                (code, msg)->new RemoveScriptToEvaluateOnLoadResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<RemoveScriptToEvaluateOnLoadResult> call(Executor exec) {
            return super.call("Page.removeScriptToEvaluateOnLoad", RemoveScriptToEvaluateOnLoadResult.class,
                (code, msg)->new RemoveScriptToEvaluateOnLoadResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of removeScriptToEvaluateOnLoad.
    <p><strong>Experimental.</strong></p>
    @Deprecated*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class RemoveScriptToEvaluateOnLoadResult extends ResultBase {
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
        public RemoveScriptToEvaluateOnLoadResult() { super(); }
        public RemoveScriptToEvaluateOnLoadResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Removes given script from the list.*/
    public RemoveScriptToEvaluateOnNewDocumentParameter removeScriptToEvaluateOnNewDocument() { final RemoveScriptToEvaluateOnNewDocumentParameter v = new RemoveScriptToEvaluateOnNewDocumentParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of removeScriptToEvaluateOnNewDocument.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class RemoveScriptToEvaluateOnNewDocumentParameter extends CommandBase {
        /**&lt;No document in protocol.&gt;*/
        private ScriptIdentifier identifier;
        public final RemoveScriptToEvaluateOnNewDocumentParameter identifier(ScriptIdentifier identifier) { this.identifier = identifier; return this; }
        public final RemoveScriptToEvaluateOnNewDocumentParameter setIdentifier(ScriptIdentifier identifier) { return identifier(identifier); }
        public final ScriptIdentifier identifier() { return identifier; }
        public final ScriptIdentifier getIdentifier() { return identifier(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (identifier == null) throw new IllegalArgumentException("Page.RemoveScriptToEvaluateOnNewDocumentParameter.identifier is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            identifier.toJson(strBuilder.append("\"identifier\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public RemoveScriptToEvaluateOnNewDocumentParameter() {}
        public RemoveScriptToEvaluateOnNewDocumentParameter(
            @JsonProperty("identifier")ScriptIdentifier identifier
        ) {
            this();
            this.identifier = identifier;
        }
        public CompletableFuture<RemoveScriptToEvaluateOnNewDocumentResult> call() {
            return super.call("Page.removeScriptToEvaluateOnNewDocument", RemoveScriptToEvaluateOnNewDocumentResult.class,
                (code, msg)->new RemoveScriptToEvaluateOnNewDocumentResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<RemoveScriptToEvaluateOnNewDocumentResult> call(Executor exec) {
            return super.call("Page.removeScriptToEvaluateOnNewDocument", RemoveScriptToEvaluateOnNewDocumentResult.class,
                (code, msg)->new RemoveScriptToEvaluateOnNewDocumentResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of removeScriptToEvaluateOnNewDocument.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class RemoveScriptToEvaluateOnNewDocumentResult extends ResultBase {
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
        public RemoveScriptToEvaluateOnNewDocumentResult() { super(); }
        public RemoveScriptToEvaluateOnNewDocumentResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**&lt;No document in protocol.&gt;
    <p><strong>Experimental.</strong></p>*/
    public RequestAppBannerParameter requestAppBanner() { final RequestAppBannerParameter v = new RequestAppBannerParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of requestAppBanner.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class RequestAppBannerParameter extends CommandBase {
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
        public RequestAppBannerParameter() {}
        public CompletableFuture<RequestAppBannerResult> call() {
            return super.call("Page.requestAppBanner", RequestAppBannerResult.class,
                (code, msg)->new RequestAppBannerResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<RequestAppBannerResult> call(Executor exec) {
            return super.call("Page.requestAppBanner", RequestAppBannerResult.class,
                (code, msg)->new RequestAppBannerResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of requestAppBanner.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class RequestAppBannerResult extends ResultBase {
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
        public RequestAppBannerResult() { super(); }
        public RequestAppBannerResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Acknowledges that a screencast frame has been received by the frontend.
    <p><strong>Experimental.</strong></p>*/
    public ScreencastFrameAckParameter screencastFrameAck() { final ScreencastFrameAckParameter v = new ScreencastFrameAckParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of screencastFrameAck.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ScreencastFrameAckParameter extends CommandBase {
        /**Frame number.*/
        private Integer sessionId;
        public final ScreencastFrameAckParameter sessionId(Integer sessionId) { this.sessionId = sessionId; return this; }
        public final ScreencastFrameAckParameter setSessionId(Integer sessionId) { return sessionId(sessionId); }
        public final Integer sessionId() { return sessionId; }
        public final Integer getSessionId() { return sessionId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (sessionId == null) throw new IllegalArgumentException("Page.ScreencastFrameAckParameter.sessionId is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"sessionId\":").append(sessionId);
            strBuilder.append('}');
            return strBuilder;
        }
        public ScreencastFrameAckParameter() {}
        public ScreencastFrameAckParameter(
            @JsonProperty("sessionId")Integer sessionId
        ) {
            this();
            this.sessionId = sessionId;
        }
        public CompletableFuture<ScreencastFrameAckResult> call() {
            return super.call("Page.screencastFrameAck", ScreencastFrameAckResult.class,
                (code, msg)->new ScreencastFrameAckResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<ScreencastFrameAckResult> call(Executor exec) {
            return super.call("Page.screencastFrameAck", ScreencastFrameAckResult.class,
                (code, msg)->new ScreencastFrameAckResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of screencastFrameAck.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ScreencastFrameAckResult extends ResultBase {
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
        public ScreencastFrameAckResult() { super(); }
        public ScreencastFrameAckResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Searches for given string in resource content.
    <p><strong>Experimental.</strong></p>*/
    public SearchInResourceParameter searchInResource() { final SearchInResourceParameter v = new SearchInResourceParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of searchInResource.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SearchInResourceParameter extends CommandBase {
        /**Frame id for resource to search in.*/
        private FrameId frameId;
        /**URL of the resource to search in.*/
        private String url;
        /**String to search for.*/
        private String query;
        /**If true, search is case sensitive.
        <em>Optional.</em>*/
        private Boolean caseSensitive;
        /**If true, treats string parameter as regex.
        <em>Optional.</em>*/
        private Boolean isRegex;
        public final SearchInResourceParameter frameId(FrameId frameId) { this.frameId = frameId; return this; }
        public final SearchInResourceParameter setFrameId(FrameId frameId) { return frameId(frameId); }
        public final FrameId frameId() { return frameId; }
        public final FrameId getFrameId() { return frameId(); }
        public final SearchInResourceParameter url(String url) { this.url = url; return this; }
        public final SearchInResourceParameter setUrl(String url) { return url(url); }
        public final String url() { return url; }
        public final String getUrl() { return url(); }
        public final SearchInResourceParameter query(String query) { this.query = query; return this; }
        public final SearchInResourceParameter setQuery(String query) { return query(query); }
        public final String query() { return query; }
        public final String getQuery() { return query(); }
        public final SearchInResourceParameter caseSensitive(@Nullable Boolean caseSensitive) { this.caseSensitive = caseSensitive; return this; }
        public final SearchInResourceParameter optCaseSensitive(@Nullable Boolean caseSensitive) { return caseSensitive(caseSensitive); }
        public final Boolean caseSensitive() { return caseSensitive; }
        public final Boolean getCaseSensitive() { return caseSensitive(); }
        public final SearchInResourceParameter isRegex(@Nullable Boolean isRegex) { this.isRegex = isRegex; return this; }
        public final SearchInResourceParameter optIsRegex(@Nullable Boolean isRegex) { return isRegex(isRegex); }
        public final Boolean isRegex() { return isRegex; }
        public final Boolean getIsRegex() { return isRegex(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (frameId == null) throw new IllegalArgumentException("Page.SearchInResourceParameter.frameId is necessary field.");
            if (url == null) throw new IllegalArgumentException("Page.SearchInResourceParameter.url is necessary field.");
            if (query == null) throw new IllegalArgumentException("Page.SearchInResourceParameter.query is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            frameId.toJson(strBuilder.append("\"frameId\":"));
            strBuilder.append(",\"url\":").append('"').append(DomainBase.escapeJson(url)).append('"');
            strBuilder.append(",\"query\":").append('"').append(DomainBase.escapeJson(query)).append('"');
            if (caseSensitive != null) strBuilder.append(",\"caseSensitive\":").append(caseSensitive);
            if (isRegex != null) strBuilder.append(",\"isRegex\":").append(isRegex);
            strBuilder.append('}');
            return strBuilder;
        }
        public SearchInResourceParameter() {}
        public SearchInResourceParameter(
            @JsonProperty("frameId")FrameId frameId,
            @JsonProperty("url")String url,
            @JsonProperty("query")String query,
            @Nullable @JsonProperty("caseSensitive")Boolean caseSensitive,
            @Nullable @JsonProperty("isRegex")Boolean isRegex
        ) {
            this();
            this.frameId = frameId;
            this.url = url;
            this.query = query;
            this.caseSensitive = caseSensitive;
            this.isRegex = isRegex;
        }
        public CompletableFuture<SearchInResourceResult> call() {
            return super.call("Page.searchInResource", SearchInResourceResult.class,
                (code, msg)->new SearchInResourceResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SearchInResourceResult> call(Executor exec) {
            return super.call("Page.searchInResource", SearchInResourceResult.class,
                (code, msg)->new SearchInResourceResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of searchInResource.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SearchInResourceResult extends ResultBase {
        /**List of search matches.*/
        private final List<Debugger.SearchMatch> result;
        public final List<Debugger.SearchMatch> result() { return result; }
        public final List<Debugger.SearchMatch> getResult() { return result(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
                        strBuilder.append("\"result\":[");
            result.get(0).toJson(strBuilder);
            for (int i = 1; i < result.size(); ++i)
                result.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public SearchInResourceResult(
            @JsonProperty("result")List<Debugger.SearchMatch> result
        ) {
            this.result = result;
        }
        public SearchInResourceResult(ResultBase.FailedResult e) {
            super(e);
            result = null;
        }
    }
    /**Enable Chrome's experimental ad filter on all sites.
    <p><strong>Experimental.</strong></p>*/
    public SetAdBlockingEnabledParameter setAdBlockingEnabled() { final SetAdBlockingEnabledParameter v = new SetAdBlockingEnabledParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setAdBlockingEnabled.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetAdBlockingEnabledParameter extends CommandBase {
        /**Whether to block ads.*/
        private Boolean enabled;
        public final SetAdBlockingEnabledParameter enabled(Boolean enabled) { this.enabled = enabled; return this; }
        public final SetAdBlockingEnabledParameter setEnabled(Boolean enabled) { return enabled(enabled); }
        public final Boolean enabled() { return enabled; }
        public final Boolean getEnabled() { return enabled(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (enabled == null) throw new IllegalArgumentException("Page.SetAdBlockingEnabledParameter.enabled is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"enabled\":").append(enabled);
            strBuilder.append('}');
            return strBuilder;
        }
        public SetAdBlockingEnabledParameter() {}
        public SetAdBlockingEnabledParameter(
            @JsonProperty("enabled")Boolean enabled
        ) {
            this();
            this.enabled = enabled;
        }
        public CompletableFuture<SetAdBlockingEnabledResult> call() {
            return super.call("Page.setAdBlockingEnabled", SetAdBlockingEnabledResult.class,
                (code, msg)->new SetAdBlockingEnabledResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetAdBlockingEnabledResult> call(Executor exec) {
            return super.call("Page.setAdBlockingEnabled", SetAdBlockingEnabledResult.class,
                (code, msg)->new SetAdBlockingEnabledResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setAdBlockingEnabled.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetAdBlockingEnabledResult extends ResultBase {
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
        public SetAdBlockingEnabledResult() { super(); }
        public SetAdBlockingEnabledResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Enable page Content Security Policy by-passing.
    <p><strong>Experimental.</strong></p>*/
    public SetBypassCSPParameter setBypassCSP() { final SetBypassCSPParameter v = new SetBypassCSPParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setBypassCSP.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetBypassCSPParameter extends CommandBase {
        /**Whether to bypass page CSP.*/
        private Boolean enabled;
        public final SetBypassCSPParameter enabled(Boolean enabled) { this.enabled = enabled; return this; }
        public final SetBypassCSPParameter setEnabled(Boolean enabled) { return enabled(enabled); }
        public final Boolean enabled() { return enabled; }
        public final Boolean getEnabled() { return enabled(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (enabled == null) throw new IllegalArgumentException("Page.SetBypassCSPParameter.enabled is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"enabled\":").append(enabled);
            strBuilder.append('}');
            return strBuilder;
        }
        public SetBypassCSPParameter() {}
        public SetBypassCSPParameter(
            @JsonProperty("enabled")Boolean enabled
        ) {
            this();
            this.enabled = enabled;
        }
        public CompletableFuture<SetBypassCSPResult> call() {
            return super.call("Page.setBypassCSP", SetBypassCSPResult.class,
                (code, msg)->new SetBypassCSPResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetBypassCSPResult> call(Executor exec) {
            return super.call("Page.setBypassCSP", SetBypassCSPResult.class,
                (code, msg)->new SetBypassCSPResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setBypassCSP.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetBypassCSPResult extends ResultBase {
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
        public SetBypassCSPResult() { super(); }
        public SetBypassCSPResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Overrides the values of device screen dimensions (window.screen.width, window.screen.height,
window.innerWidth, window.innerHeight, and "device-width"/"device-height"-related CSS media
query results).
    <p><strong>Experimental.</strong></p>
    @Deprecated*/
    public SetDeviceMetricsOverrideParameter setDeviceMetricsOverride() { final SetDeviceMetricsOverrideParameter v = new SetDeviceMetricsOverrideParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setDeviceMetricsOverride.
    <p><strong>Experimental.</strong></p>
    @Deprecated*/
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
        <em>Optional.</em>*/
        private Double scale;
        /**Overriding screen width value in pixels (minimum 0, maximum 10000000).
        <em>Optional.</em>*/
        private Integer screenWidth;
        /**Overriding screen height value in pixels (minimum 0, maximum 10000000).
        <em>Optional.</em>*/
        private Integer screenHeight;
        /**Overriding view X position on screen in pixels (minimum 0, maximum 10000000).
        <em>Optional.</em>*/
        private Integer positionX;
        /**Overriding view Y position on screen in pixels (minimum 0, maximum 10000000).
        <em>Optional.</em>*/
        private Integer positionY;
        /**Do not set visible view size, rely upon explicit setVisibleSize call.
        <em>Optional.</em>*/
        private Boolean dontSetVisibleSize;
        /**Screen orientation override.
        <em>Optional.</em>*/
        private Emulation.ScreenOrientation screenOrientation;
        /**The viewport dimensions and scale. If not set, the override is cleared.
        <em>Optional.</em>*/
        private Viewport viewport;
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
        public final SetDeviceMetricsOverrideParameter screenOrientation(@Nullable Emulation.ScreenOrientation screenOrientation) { this.screenOrientation = screenOrientation; return this; }
        public final SetDeviceMetricsOverrideParameter optScreenOrientation(@Nullable Emulation.ScreenOrientation screenOrientation) { return screenOrientation(screenOrientation); }
        public final Emulation.ScreenOrientation screenOrientation() { return screenOrientation; }
        public final Emulation.ScreenOrientation getScreenOrientation() { return screenOrientation(); }
        public final SetDeviceMetricsOverrideParameter viewport(@Nullable Viewport viewport) { this.viewport = viewport; return this; }
        public final SetDeviceMetricsOverrideParameter optViewport(@Nullable Viewport viewport) { return viewport(viewport); }
        public final Viewport viewport() { return viewport; }
        public final Viewport getViewport() { return viewport(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (width == null) throw new IllegalArgumentException("Page.SetDeviceMetricsOverrideParameter.width is necessary field.");
            if (height == null) throw new IllegalArgumentException("Page.SetDeviceMetricsOverrideParameter.height is necessary field.");
            if (deviceScaleFactor == null) throw new IllegalArgumentException("Page.SetDeviceMetricsOverrideParameter.deviceScaleFactor is necessary field.");
            if (mobile == null) throw new IllegalArgumentException("Page.SetDeviceMetricsOverrideParameter.mobile is necessary field.");
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
            @Nullable @JsonProperty("screenOrientation")Emulation.ScreenOrientation screenOrientation,
            @Nullable @JsonProperty("viewport")Viewport viewport
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
            return super.call("Page.setDeviceMetricsOverride", SetDeviceMetricsOverrideResult.class,
                (code, msg)->new SetDeviceMetricsOverrideResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetDeviceMetricsOverrideResult> call(Executor exec) {
            return super.call("Page.setDeviceMetricsOverride", SetDeviceMetricsOverrideResult.class,
                (code, msg)->new SetDeviceMetricsOverrideResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setDeviceMetricsOverride.
    <p><strong>Experimental.</strong></p>
    @Deprecated*/
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
    /**Overrides the Device Orientation.
    <p><strong>Experimental.</strong></p>
    @Deprecated*/
    public SetDeviceOrientationOverrideParameter setDeviceOrientationOverride() { final SetDeviceOrientationOverrideParameter v = new SetDeviceOrientationOverrideParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setDeviceOrientationOverride.
    <p><strong>Experimental.</strong></p>
    @Deprecated*/
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
            if (alpha == null) throw new IllegalArgumentException("Page.SetDeviceOrientationOverrideParameter.alpha is necessary field.");
            if (beta == null) throw new IllegalArgumentException("Page.SetDeviceOrientationOverrideParameter.beta is necessary field.");
            if (gamma == null) throw new IllegalArgumentException("Page.SetDeviceOrientationOverrideParameter.gamma is necessary field.");
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
            return super.call("Page.setDeviceOrientationOverride", SetDeviceOrientationOverrideResult.class,
                (code, msg)->new SetDeviceOrientationOverrideResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetDeviceOrientationOverrideResult> call(Executor exec) {
            return super.call("Page.setDeviceOrientationOverride", SetDeviceOrientationOverrideResult.class,
                (code, msg)->new SetDeviceOrientationOverrideResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setDeviceOrientationOverride.
    <p><strong>Experimental.</strong></p>
    @Deprecated*/
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
    /**Sets given markup as the document's HTML.*/
    public SetDocumentContentParameter setDocumentContent() { final SetDocumentContentParameter v = new SetDocumentContentParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setDocumentContent.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetDocumentContentParameter extends CommandBase {
        /**Frame id to set HTML for.*/
        private FrameId frameId;
        /**HTML content to set.*/
        private String html;
        public final SetDocumentContentParameter frameId(FrameId frameId) { this.frameId = frameId; return this; }
        public final SetDocumentContentParameter setFrameId(FrameId frameId) { return frameId(frameId); }
        public final FrameId frameId() { return frameId; }
        public final FrameId getFrameId() { return frameId(); }
        public final SetDocumentContentParameter html(String html) { this.html = html; return this; }
        public final SetDocumentContentParameter setHtml(String html) { return html(html); }
        public final String html() { return html; }
        public final String getHtml() { return html(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (frameId == null) throw new IllegalArgumentException("Page.SetDocumentContentParameter.frameId is necessary field.");
            if (html == null) throw new IllegalArgumentException("Page.SetDocumentContentParameter.html is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            frameId.toJson(strBuilder.append("\"frameId\":"));
            strBuilder.append(",\"html\":").append('"').append(DomainBase.escapeJson(html)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public SetDocumentContentParameter() {}
        public SetDocumentContentParameter(
            @JsonProperty("frameId")FrameId frameId,
            @JsonProperty("html")String html
        ) {
            this();
            this.frameId = frameId;
            this.html = html;
        }
        public CompletableFuture<SetDocumentContentResult> call() {
            return super.call("Page.setDocumentContent", SetDocumentContentResult.class,
                (code, msg)->new SetDocumentContentResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetDocumentContentResult> call(Executor exec) {
            return super.call("Page.setDocumentContent", SetDocumentContentResult.class,
                (code, msg)->new SetDocumentContentResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setDocumentContent.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetDocumentContentResult extends ResultBase {
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
        public SetDocumentContentResult() { super(); }
        public SetDocumentContentResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Set the behavior when downloading a file.
    <p><strong>Experimental.</strong></p>*/
    public SetDownloadBehaviorParameter setDownloadBehavior() { final SetDownloadBehaviorParameter v = new SetDownloadBehaviorParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setDownloadBehavior.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetDownloadBehaviorParameter extends CommandBase {
        /**Whether to allow all or deny all download requests, or use default Chrome behavior if
available (otherwise deny).*/
        @ParametersAreNonnullByDefault public enum Behavior implements CommonDomainType {
            Deny("deny"),
            Allow("allow"),
            Default("default");

            private final String _value;
            /**Convert string representation to type.
             @throws IllegalArgumentException if given value cannot convert to enum type. */
            @JsonCreator public static Behavior of(String value) {
                return Enum.valueOf(Behavior.class, value.substring(0, 1).toUpperCase() + value.substring(1));
            }
            Behavior(String value) { _value = value; }
            /**Check if parameter fields of method are all valid. */
            @Override public void check() throws IllegalArgumentException { /* Need not check */ }
            /**Convert method parameter object into json string and append into string builder.
             @return string builder instance that is given in parameter (for chaining coding style use.) */
            @Override public StringBuilder toJson(StringBuilder strBuilder) { return strBuilder.append('"').append(_value).append('"'); }
            @Override public String toString() { return "\"" + _value + "\""; }
        }
        private Behavior behavior;
        /**The default path to save downloaded files to. This is requred if behavior is set to 'allow'
        <em>Optional.</em>*/
        private String downloadPath;
        public final SetDownloadBehaviorParameter behavior(Behavior behavior) { this.behavior = behavior; return this; }
        public final SetDownloadBehaviorParameter setBehavior(Behavior behavior) { return behavior(behavior); }
        public final Behavior behavior() { return behavior; }
        public final Behavior getBehavior() { return behavior(); }
        public final SetDownloadBehaviorParameter downloadPath(@Nullable String downloadPath) { this.downloadPath = downloadPath; return this; }
        public final SetDownloadBehaviorParameter optDownloadPath(@Nullable String downloadPath) { return downloadPath(downloadPath); }
        public final String downloadPath() { return downloadPath; }
        public final String getDownloadPath() { return downloadPath(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (behavior == null) throw new IllegalArgumentException("Page.SetDownloadBehaviorParameter.behavior is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"behavior\":").append(behavior);
            if (downloadPath != null) strBuilder.append(",\"downloadPath\":").append('"').append(DomainBase.escapeJson(downloadPath)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public SetDownloadBehaviorParameter() {}
        public SetDownloadBehaviorParameter(
            @JsonProperty("behavior")Behavior behavior,
            @Nullable @JsonProperty("downloadPath")String downloadPath
        ) {
            this();
            this.behavior = behavior;
            this.downloadPath = downloadPath;
        }
        public CompletableFuture<SetDownloadBehaviorResult> call() {
            return super.call("Page.setDownloadBehavior", SetDownloadBehaviorResult.class,
                (code, msg)->new SetDownloadBehaviorResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetDownloadBehaviorResult> call(Executor exec) {
            return super.call("Page.setDownloadBehavior", SetDownloadBehaviorResult.class,
                (code, msg)->new SetDownloadBehaviorResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setDownloadBehavior.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetDownloadBehaviorResult extends ResultBase {
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
        public SetDownloadBehaviorResult() { super(); }
        public SetDownloadBehaviorResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Overrides the Geolocation Position or Error. Omitting any of the parameters emulates position
unavailable.
    @Deprecated*/
    public SetGeolocationOverrideParameter setGeolocationOverride() { final SetGeolocationOverrideParameter v = new SetGeolocationOverrideParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setGeolocationOverride.
    @Deprecated*/
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
            return super.call("Page.setGeolocationOverride", SetGeolocationOverrideResult.class,
                (code, msg)->new SetGeolocationOverrideResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetGeolocationOverrideResult> call(Executor exec) {
            return super.call("Page.setGeolocationOverride", SetGeolocationOverrideResult.class,
                (code, msg)->new SetGeolocationOverrideResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setGeolocationOverride.
    @Deprecated*/
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
    /**Controls whether page will emit lifecycle events.
    <p><strong>Experimental.</strong></p>*/
    public SetLifecycleEventsEnabledParameter setLifecycleEventsEnabled() { final SetLifecycleEventsEnabledParameter v = new SetLifecycleEventsEnabledParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setLifecycleEventsEnabled.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetLifecycleEventsEnabledParameter extends CommandBase {
        /**If true, starts emitting lifecycle events.*/
        private Boolean enabled;
        public final SetLifecycleEventsEnabledParameter enabled(Boolean enabled) { this.enabled = enabled; return this; }
        public final SetLifecycleEventsEnabledParameter setEnabled(Boolean enabled) { return enabled(enabled); }
        public final Boolean enabled() { return enabled; }
        public final Boolean getEnabled() { return enabled(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (enabled == null) throw new IllegalArgumentException("Page.SetLifecycleEventsEnabledParameter.enabled is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"enabled\":").append(enabled);
            strBuilder.append('}');
            return strBuilder;
        }
        public SetLifecycleEventsEnabledParameter() {}
        public SetLifecycleEventsEnabledParameter(
            @JsonProperty("enabled")Boolean enabled
        ) {
            this();
            this.enabled = enabled;
        }
        public CompletableFuture<SetLifecycleEventsEnabledResult> call() {
            return super.call("Page.setLifecycleEventsEnabled", SetLifecycleEventsEnabledResult.class,
                (code, msg)->new SetLifecycleEventsEnabledResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetLifecycleEventsEnabledResult> call(Executor exec) {
            return super.call("Page.setLifecycleEventsEnabled", SetLifecycleEventsEnabledResult.class,
                (code, msg)->new SetLifecycleEventsEnabledResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setLifecycleEventsEnabled.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetLifecycleEventsEnabledResult extends ResultBase {
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
        public SetLifecycleEventsEnabledResult() { super(); }
        public SetLifecycleEventsEnabledResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Toggles mouse event-based touch event emulation.
    <p><strong>Experimental.</strong></p>
    @Deprecated*/
    public SetTouchEmulationEnabledParameter setTouchEmulationEnabled() { final SetTouchEmulationEnabledParameter v = new SetTouchEmulationEnabledParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setTouchEmulationEnabled.
    <p><strong>Experimental.</strong></p>
    @Deprecated*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetTouchEmulationEnabledParameter extends CommandBase {
        /**Whether the touch event emulation should be enabled.*/
        private Boolean enabled;
        /**Touch/gesture events configuration. Default: current platform.
        <em>Optional.</em>*/
        @ParametersAreNonnullByDefault public enum Configuration implements CommonDomainType {
            Mobile("mobile"),
            Desktop("desktop");

            private final String _value;
            /**Convert string representation to type.
             @throws IllegalArgumentException if given value cannot convert to enum type. */
            @JsonCreator public static Configuration of(String value) {
                return Enum.valueOf(Configuration.class, value.substring(0, 1).toUpperCase() + value.substring(1));
            }
            Configuration(String value) { _value = value; }
            /**Check if parameter fields of method are all valid. */
            @Override public void check() throws IllegalArgumentException { /* Need not check */ }
            /**Convert method parameter object into json string and append into string builder.
             @return string builder instance that is given in parameter (for chaining coding style use.) */
            @Override public StringBuilder toJson(StringBuilder strBuilder) { return strBuilder.append('"').append(_value).append('"'); }
            @Override public String toString() { return "\"" + _value + "\""; }
        }
        private Configuration configuration;
        public final SetTouchEmulationEnabledParameter enabled(Boolean enabled) { this.enabled = enabled; return this; }
        public final SetTouchEmulationEnabledParameter setEnabled(Boolean enabled) { return enabled(enabled); }
        public final Boolean enabled() { return enabled; }
        public final Boolean getEnabled() { return enabled(); }
        public final SetTouchEmulationEnabledParameter configuration(@Nullable Configuration configuration) { this.configuration = configuration; return this; }
        public final SetTouchEmulationEnabledParameter optConfiguration(@Nullable Configuration configuration) { return configuration(configuration); }
        public final Configuration configuration() { return configuration; }
        public final Configuration getConfiguration() { return configuration(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (enabled == null) throw new IllegalArgumentException("Page.SetTouchEmulationEnabledParameter.enabled is necessary field.");
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
        public SetTouchEmulationEnabledParameter() {}
        public SetTouchEmulationEnabledParameter(
            @JsonProperty("enabled")Boolean enabled,
            @Nullable @JsonProperty("configuration")Configuration configuration
        ) {
            this();
            this.enabled = enabled;
            this.configuration = configuration;
        }
        public CompletableFuture<SetTouchEmulationEnabledResult> call() {
            return super.call("Page.setTouchEmulationEnabled", SetTouchEmulationEnabledResult.class,
                (code, msg)->new SetTouchEmulationEnabledResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetTouchEmulationEnabledResult> call(Executor exec) {
            return super.call("Page.setTouchEmulationEnabled", SetTouchEmulationEnabledResult.class,
                (code, msg)->new SetTouchEmulationEnabledResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setTouchEmulationEnabled.
    <p><strong>Experimental.</strong></p>
    @Deprecated*/
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
    /**Starts sending each frame using the `screencastFrame` event.
    <p><strong>Experimental.</strong></p>*/
    public StartScreencastParameter startScreencast() { final StartScreencastParameter v = new StartScreencastParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of startScreencast.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class StartScreencastParameter extends CommandBase {
        /**Image compression format.
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
        /**Compression quality from range [0..100].
        <em>Optional.</em>*/
        private Integer quality;
        /**Maximum screenshot width.
        <em>Optional.</em>*/
        private Integer maxWidth;
        /**Maximum screenshot height.
        <em>Optional.</em>*/
        private Integer maxHeight;
        /**Send every n-th frame.
        <em>Optional.</em>*/
        private Integer everyNthFrame;
        public final StartScreencastParameter format(@Nullable Format format) { this.format = format; return this; }
        public final StartScreencastParameter optFormat(@Nullable Format format) { return format(format); }
        public final Format format() { return format; }
        public final Format getFormat() { return format(); }
        public final StartScreencastParameter quality(@Nullable Integer quality) { this.quality = quality; return this; }
        public final StartScreencastParameter optQuality(@Nullable Integer quality) { return quality(quality); }
        public final Integer quality() { return quality; }
        public final Integer getQuality() { return quality(); }
        public final StartScreencastParameter maxWidth(@Nullable Integer maxWidth) { this.maxWidth = maxWidth; return this; }
        public final StartScreencastParameter optMaxWidth(@Nullable Integer maxWidth) { return maxWidth(maxWidth); }
        public final Integer maxWidth() { return maxWidth; }
        public final Integer getMaxWidth() { return maxWidth(); }
        public final StartScreencastParameter maxHeight(@Nullable Integer maxHeight) { this.maxHeight = maxHeight; return this; }
        public final StartScreencastParameter optMaxHeight(@Nullable Integer maxHeight) { return maxHeight(maxHeight); }
        public final Integer maxHeight() { return maxHeight; }
        public final Integer getMaxHeight() { return maxHeight(); }
        public final StartScreencastParameter everyNthFrame(@Nullable Integer everyNthFrame) { this.everyNthFrame = everyNthFrame; return this; }
        public final StartScreencastParameter optEveryNthFrame(@Nullable Integer everyNthFrame) { return everyNthFrame(everyNthFrame); }
        public final Integer everyNthFrame() { return everyNthFrame; }
        public final Integer getEveryNthFrame() { return everyNthFrame(); }
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
            if (maxWidth != null) strBuilder.append(",\"maxWidth\":").append(maxWidth);
            if (maxHeight != null) strBuilder.append(",\"maxHeight\":").append(maxHeight);
            if (everyNthFrame != null) strBuilder.append(",\"everyNthFrame\":").append(everyNthFrame);
            strBuilder.append('}');
            return strBuilder;
        }
        public StartScreencastParameter() {}
        public StartScreencastParameter(
            @Nullable @JsonProperty("format")Format format,
            @Nullable @JsonProperty("quality")Integer quality,
            @Nullable @JsonProperty("maxWidth")Integer maxWidth,
            @Nullable @JsonProperty("maxHeight")Integer maxHeight,
            @Nullable @JsonProperty("everyNthFrame")Integer everyNthFrame
        ) {
            this();
            this.format = format;
            this.quality = quality;
            this.maxWidth = maxWidth;
            this.maxHeight = maxHeight;
            this.everyNthFrame = everyNthFrame;
        }
        public CompletableFuture<StartScreencastResult> call() {
            return super.call("Page.startScreencast", StartScreencastResult.class,
                (code, msg)->new StartScreencastResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<StartScreencastResult> call(Executor exec) {
            return super.call("Page.startScreencast", StartScreencastResult.class,
                (code, msg)->new StartScreencastResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of startScreencast.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class StartScreencastResult extends ResultBase {
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
        public StartScreencastResult() { super(); }
        public StartScreencastResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Force the page stop all navigations and pending resource fetches.*/
    public StopLoadingParameter stopLoading() { final StopLoadingParameter v = new StopLoadingParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of stopLoading.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class StopLoadingParameter extends CommandBase {
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
        public StopLoadingParameter() {}
        public CompletableFuture<StopLoadingResult> call() {
            return super.call("Page.stopLoading", StopLoadingResult.class,
                (code, msg)->new StopLoadingResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<StopLoadingResult> call(Executor exec) {
            return super.call("Page.stopLoading", StopLoadingResult.class,
                (code, msg)->new StopLoadingResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of stopLoading.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class StopLoadingResult extends ResultBase {
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
        public StopLoadingResult() { super(); }
        public StopLoadingResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Crashes renderer on the IO thread, generates minidumps.
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
            return super.call("Page.crash", CrashResult.class,
                (code, msg)->new CrashResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<CrashResult> call(Executor exec) {
            return super.call("Page.crash", CrashResult.class,
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
    /**Tries to close page, running its beforeunload hooks, if any.
    <p><strong>Experimental.</strong></p>*/
    public CloseParameter close() { final CloseParameter v = new CloseParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of close.
    <p><strong>Experimental.</strong></p>*/
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
            return super.call("Page.close", CloseResult.class,
                (code, msg)->new CloseResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<CloseResult> call(Executor exec) {
            return super.call("Page.close", CloseResult.class,
                (code, msg)->new CloseResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of close.
    <p><strong>Experimental.</strong></p>*/
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
    /**Tries to update the web lifecycle state of the page.
It will transition the page to the given state according to:
https://github.com/WICG/web-lifecycle/
    <p><strong>Experimental.</strong></p>*/
    public SetWebLifecycleStateParameter setWebLifecycleState() { final SetWebLifecycleStateParameter v = new SetWebLifecycleStateParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setWebLifecycleState.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetWebLifecycleStateParameter extends CommandBase {
        /**Target lifecycle state*/
        @ParametersAreNonnullByDefault public enum State implements CommonDomainType {
            Frozen("frozen"),
            Active("active");

            private final String _value;
            /**Convert string representation to type.
             @throws IllegalArgumentException if given value cannot convert to enum type. */
            @JsonCreator public static State of(String value) {
                return Enum.valueOf(State.class, value.substring(0, 1).toUpperCase() + value.substring(1));
            }
            State(String value) { _value = value; }
            /**Check if parameter fields of method are all valid. */
            @Override public void check() throws IllegalArgumentException { /* Need not check */ }
            /**Convert method parameter object into json string and append into string builder.
             @return string builder instance that is given in parameter (for chaining coding style use.) */
            @Override public StringBuilder toJson(StringBuilder strBuilder) { return strBuilder.append('"').append(_value).append('"'); }
            @Override public String toString() { return "\"" + _value + "\""; }
        }
        private State state;
        public final SetWebLifecycleStateParameter state(State state) { this.state = state; return this; }
        public final SetWebLifecycleStateParameter setState(State state) { return state(state); }
        public final State state() { return state; }
        public final State getState() { return state(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (state == null) throw new IllegalArgumentException("Page.SetWebLifecycleStateParameter.state is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"state\":").append(state);
            strBuilder.append('}');
            return strBuilder;
        }
        public SetWebLifecycleStateParameter() {}
        public SetWebLifecycleStateParameter(
            @JsonProperty("state")State state
        ) {
            this();
            this.state = state;
        }
        public CompletableFuture<SetWebLifecycleStateResult> call() {
            return super.call("Page.setWebLifecycleState", SetWebLifecycleStateResult.class,
                (code, msg)->new SetWebLifecycleStateResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetWebLifecycleStateResult> call(Executor exec) {
            return super.call("Page.setWebLifecycleState", SetWebLifecycleStateResult.class,
                (code, msg)->new SetWebLifecycleStateResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setWebLifecycleState.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetWebLifecycleStateResult extends ResultBase {
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
        public SetWebLifecycleStateResult() { super(); }
        public SetWebLifecycleStateResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Stops sending each frame in the `screencastFrame`.
    <p><strong>Experimental.</strong></p>*/
    public StopScreencastParameter stopScreencast() { final StopScreencastParameter v = new StopScreencastParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of stopScreencast.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class StopScreencastParameter extends CommandBase {
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
        public StopScreencastParameter() {}
        public CompletableFuture<StopScreencastResult> call() {
            return super.call("Page.stopScreencast", StopScreencastResult.class,
                (code, msg)->new StopScreencastResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<StopScreencastResult> call(Executor exec) {
            return super.call("Page.stopScreencast", StopScreencastResult.class,
                (code, msg)->new StopScreencastResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of stopScreencast.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class StopScreencastResult extends ResultBase {
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
        public StopScreencastResult() { super(); }
        public StopScreencastResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Event parameter of Page.domContentEventFired.
     @see #onDomContentEventFired*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class DomContentEventFiredEventParameter implements CommonDomainType {
        /**&lt;No document in protocol.&gt;*/
        private final Network.MonotonicTime timestamp;
        public final Network.MonotonicTime timestamp() { return timestamp; }
        public final Network.MonotonicTime getTimestamp() { return timestamp(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            timestamp.toJson(strBuilder.append("\"timestamp\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        DomContentEventFiredEventParameter(
            @JsonProperty("timestamp")Network.MonotonicTime timestamp
        ) {
            this.timestamp = timestamp;
        }
    }
    /**&lt;No document in protocol.&gt;
     @see DomContentEventFiredEventParameter*/
    public void onDomContentEventFired(Consumer<DomContentEventFiredEventParameter> callback) {
        registerEventCallback("Page.domContentEventFired", node -> {
            DomContentEventFiredEventParameter param;
            try { param = EventCenter.deserializeJson(node, DomContentEventFiredEventParameter.class); }
            catch (IOException e) { _evt.getLog().error(e); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of Page.frameAttached.
     @see #onFrameAttached*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class FrameAttachedEventParameter implements CommonDomainType {
        /**Id of the frame that has been attached.*/
        private final FrameId frameId;
        /**Parent frame identifier.*/
        private final FrameId parentFrameId;
        /**JavaScript stack trace of when frame was attached, only set if frame initiated from script.
        <em>Optional.</em>*/
        private final Runtime.StackTrace stack;
        public final FrameId frameId() { return frameId; }
        public final FrameId getFrameId() { return frameId(); }
        public final FrameId parentFrameId() { return parentFrameId; }
        public final FrameId getParentFrameId() { return parentFrameId(); }
        public final Runtime.StackTrace stack() { return stack; }
        public final Runtime.StackTrace getStack() { return stack(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            frameId.toJson(strBuilder.append("\"frameId\":"));
            parentFrameId.toJson(strBuilder.append(",\"parentFrameId\":"));
            if (stack != null) stack.toJson(strBuilder.append(",\"stack\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        FrameAttachedEventParameter(
            @JsonProperty("frameId")FrameId frameId,
            @JsonProperty("parentFrameId")FrameId parentFrameId,
            @Nullable @JsonProperty("stack")Runtime.StackTrace stack
        ) {
            this.frameId = frameId;
            this.parentFrameId = parentFrameId;
            this.stack = stack;
        }
    }
    /**Fired when frame has been attached to its parent.
     @see FrameAttachedEventParameter*/
    public void onFrameAttached(Consumer<FrameAttachedEventParameter> callback) {
        registerEventCallback("Page.frameAttached", node -> {
            FrameAttachedEventParameter param;
            try { param = EventCenter.deserializeJson(node, FrameAttachedEventParameter.class); }
            catch (IOException e) { _evt.getLog().error(e); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of Page.frameClearedScheduledNavigation.
    <p><strong>Experimental.</strong></p>
     @see #onFrameClearedScheduledNavigation*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class FrameClearedScheduledNavigationEventParameter implements CommonDomainType {
        /**Id of the frame that has cleared its scheduled navigation.*/
        private final FrameId frameId;
        public final FrameId frameId() { return frameId; }
        public final FrameId getFrameId() { return frameId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            frameId.toJson(strBuilder.append("\"frameId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        FrameClearedScheduledNavigationEventParameter(
            @JsonProperty("frameId")FrameId frameId
        ) {
            this.frameId = frameId;
        }
    }
    /**Fired when frame no longer has a scheduled navigation.
    <p><strong>Experimental.</strong></p>
     @see FrameClearedScheduledNavigationEventParameter*/
    public void onFrameClearedScheduledNavigation(Consumer<FrameClearedScheduledNavigationEventParameter> callback) {
        registerEventCallback("Page.frameClearedScheduledNavigation", node -> {
            FrameClearedScheduledNavigationEventParameter param;
            try { param = EventCenter.deserializeJson(node, FrameClearedScheduledNavigationEventParameter.class); }
            catch (IOException e) { _evt.getLog().error(e); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of Page.frameDetached.
     @see #onFrameDetached*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class FrameDetachedEventParameter implements CommonDomainType {
        /**Id of the frame that has been detached.*/
        private final FrameId frameId;
        public final FrameId frameId() { return frameId; }
        public final FrameId getFrameId() { return frameId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            frameId.toJson(strBuilder.append("\"frameId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        FrameDetachedEventParameter(
            @JsonProperty("frameId")FrameId frameId
        ) {
            this.frameId = frameId;
        }
    }
    /**Fired when frame has been detached from its parent.
     @see FrameDetachedEventParameter*/
    public void onFrameDetached(Consumer<FrameDetachedEventParameter> callback) {
        registerEventCallback("Page.frameDetached", node -> {
            FrameDetachedEventParameter param;
            try { param = EventCenter.deserializeJson(node, FrameDetachedEventParameter.class); }
            catch (IOException e) { _evt.getLog().error(e); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of Page.frameNavigated.
     @see #onFrameNavigated*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class FrameNavigatedEventParameter implements CommonDomainType {
        /**Frame object.*/
        private final Frame frame;
        public final Frame frame() { return frame; }
        public final Frame getFrame() { return frame(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            frame.toJson(strBuilder.append("\"frame\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        FrameNavigatedEventParameter(
            @JsonProperty("frame")Frame frame
        ) {
            this.frame = frame;
        }
    }
    /**Fired once navigation of the frame has completed. Frame is now associated with the new loader.
     @see FrameNavigatedEventParameter*/
    public void onFrameNavigated(Consumer<FrameNavigatedEventParameter> callback) {
        registerEventCallback("Page.frameNavigated", node -> {
            FrameNavigatedEventParameter param;
            try { param = EventCenter.deserializeJson(node, FrameNavigatedEventParameter.class); }
            catch (IOException e) { _evt.getLog().error(e); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of Page.frameResized.
    <p><strong>Experimental.</strong></p>
     @see #onFrameResized*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class FrameResizedEventParameter implements CommonDomainType {
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
        public FrameResizedEventParameter() {}
    }
    /**&lt;No document in protocol.&gt;
    <p><strong>Experimental.</strong></p>
     @see FrameResizedEventParameter*/
    public void onFrameResized(Consumer<FrameResizedEventParameter> callback) {
        registerEventCallback("Page.frameResized", node -> {
            FrameResizedEventParameter param;
            try { param = EventCenter.deserializeJson(node, FrameResizedEventParameter.class); }
            catch (IOException e) { _evt.getLog().error(e); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of Page.frameScheduledNavigation.
    <p><strong>Experimental.</strong></p>
     @see #onFrameScheduledNavigation*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class FrameScheduledNavigationEventParameter implements CommonDomainType {
        /**Id of the frame that has scheduled a navigation.*/
        private final FrameId frameId;
        /**Delay (in seconds) until the navigation is scheduled to begin. The navigation is not
guaranteed to start.*/
        private final Double delay;
        /**The reason for the navigation.*/
        @ParametersAreNonnullByDefault public enum Reason implements CommonDomainType {
            FormSubmissionGet("formSubmissionGet"),
            FormSubmissionPost("formSubmissionPost"),
            HttpHeaderRefresh("httpHeaderRefresh"),
            ScriptInitiated("scriptInitiated"),
            MetaTagRefresh("metaTagRefresh"),
            PageBlockInterstitial("pageBlockInterstitial"),
            Reload("reload");

            private final String _value;
            /**Convert string representation to type.
             @throws IllegalArgumentException if given value cannot convert to enum type. */
            @JsonCreator public static Reason of(String value) {
                return Enum.valueOf(Reason.class, value.substring(0, 1).toUpperCase() + value.substring(1));
            }
            Reason(String value) { _value = value; }
            /**Check if parameter fields of method are all valid. */
            @Override public void check() throws IllegalArgumentException { /* Need not check */ }
            /**Convert method parameter object into json string and append into string builder.
             @return string builder instance that is given in parameter (for chaining coding style use.) */
            @Override public StringBuilder toJson(StringBuilder strBuilder) { return strBuilder.append('"').append(_value).append('"'); }
            @Override public String toString() { return "\"" + _value + "\""; }
        }
        private final Reason reason;
        /**The destination URL for the scheduled navigation.*/
        private final String url;
        public final FrameId frameId() { return frameId; }
        public final FrameId getFrameId() { return frameId(); }
        public final Double delay() { return delay; }
        public final Double getDelay() { return delay(); }
        public final Reason reason() { return reason; }
        public final Reason getReason() { return reason(); }
        public final String url() { return url; }
        public final String getUrl() { return url(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            frameId.toJson(strBuilder.append("\"frameId\":"));
            strBuilder.append(",\"delay\":").append(delay);
            strBuilder.append(",\"reason\":").append(reason);
            strBuilder.append(",\"url\":").append('"').append(DomainBase.escapeJson(url)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        FrameScheduledNavigationEventParameter(
            @JsonProperty("frameId")FrameId frameId,
            @JsonProperty("delay")Double delay,
            @JsonProperty("reason")Reason reason,
            @JsonProperty("url")String url
        ) {
            this.frameId = frameId;
            this.delay = delay;
            this.reason = reason;
            this.url = url;
        }
    }
    /**Fired when frame schedules a potential navigation.
    <p><strong>Experimental.</strong></p>
     @see FrameScheduledNavigationEventParameter*/
    public void onFrameScheduledNavigation(Consumer<FrameScheduledNavigationEventParameter> callback) {
        registerEventCallback("Page.frameScheduledNavigation", node -> {
            FrameScheduledNavigationEventParameter param;
            try { param = EventCenter.deserializeJson(node, FrameScheduledNavigationEventParameter.class); }
            catch (IOException e) { _evt.getLog().error(e); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of Page.frameStartedLoading.
    <p><strong>Experimental.</strong></p>
     @see #onFrameStartedLoading*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class FrameStartedLoadingEventParameter implements CommonDomainType {
        /**Id of the frame that has started loading.*/
        private final FrameId frameId;
        public final FrameId frameId() { return frameId; }
        public final FrameId getFrameId() { return frameId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            frameId.toJson(strBuilder.append("\"frameId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        FrameStartedLoadingEventParameter(
            @JsonProperty("frameId")FrameId frameId
        ) {
            this.frameId = frameId;
        }
    }
    /**Fired when frame has started loading.
    <p><strong>Experimental.</strong></p>
     @see FrameStartedLoadingEventParameter*/
    public void onFrameStartedLoading(Consumer<FrameStartedLoadingEventParameter> callback) {
        registerEventCallback("Page.frameStartedLoading", node -> {
            FrameStartedLoadingEventParameter param;
            try { param = EventCenter.deserializeJson(node, FrameStartedLoadingEventParameter.class); }
            catch (IOException e) { _evt.getLog().error(e); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of Page.frameStoppedLoading.
    <p><strong>Experimental.</strong></p>
     @see #onFrameStoppedLoading*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class FrameStoppedLoadingEventParameter implements CommonDomainType {
        /**Id of the frame that has stopped loading.*/
        private final FrameId frameId;
        public final FrameId frameId() { return frameId; }
        public final FrameId getFrameId() { return frameId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            frameId.toJson(strBuilder.append("\"frameId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        FrameStoppedLoadingEventParameter(
            @JsonProperty("frameId")FrameId frameId
        ) {
            this.frameId = frameId;
        }
    }
    /**Fired when frame has stopped loading.
    <p><strong>Experimental.</strong></p>
     @see FrameStoppedLoadingEventParameter*/
    public void onFrameStoppedLoading(Consumer<FrameStoppedLoadingEventParameter> callback) {
        registerEventCallback("Page.frameStoppedLoading", node -> {
            FrameStoppedLoadingEventParameter param;
            try { param = EventCenter.deserializeJson(node, FrameStoppedLoadingEventParameter.class); }
            catch (IOException e) { _evt.getLog().error(e); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of Page.interstitialHidden.
     @see #onInterstitialHidden*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class InterstitialHiddenEventParameter implements CommonDomainType {
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
        public InterstitialHiddenEventParameter() {}
    }
    /**Fired when interstitial page was hidden
     @see InterstitialHiddenEventParameter*/
    public void onInterstitialHidden(Consumer<InterstitialHiddenEventParameter> callback) {
        registerEventCallback("Page.interstitialHidden", node -> {
            InterstitialHiddenEventParameter param;
            try { param = EventCenter.deserializeJson(node, InterstitialHiddenEventParameter.class); }
            catch (IOException e) { _evt.getLog().error(e); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of Page.interstitialShown.
     @see #onInterstitialShown*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class InterstitialShownEventParameter implements CommonDomainType {
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
        public InterstitialShownEventParameter() {}
    }
    /**Fired when interstitial page was shown
     @see InterstitialShownEventParameter*/
    public void onInterstitialShown(Consumer<InterstitialShownEventParameter> callback) {
        registerEventCallback("Page.interstitialShown", node -> {
            InterstitialShownEventParameter param;
            try { param = EventCenter.deserializeJson(node, InterstitialShownEventParameter.class); }
            catch (IOException e) { _evt.getLog().error(e); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of Page.javascriptDialogClosed.
     @see #onJavascriptDialogClosed*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class JavascriptDialogClosedEventParameter implements CommonDomainType {
        /**Whether dialog was confirmed.*/
        private final Boolean result;
        /**User input in case of prompt.*/
        private final String userInput;
        public final Boolean result() { return result; }
        public final Boolean getResult() { return result(); }
        public final String userInput() { return userInput; }
        public final String getUserInput() { return userInput(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"result\":").append(result);
            strBuilder.append(",\"userInput\":").append('"').append(DomainBase.escapeJson(userInput)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        JavascriptDialogClosedEventParameter(
            @JsonProperty("result")Boolean result,
            @JsonProperty("userInput")String userInput
        ) {
            this.result = result;
            this.userInput = userInput;
        }
    }
    /**Fired when a JavaScript initiated dialog (alert, confirm, prompt, or onbeforeunload) has been
closed.
     @see JavascriptDialogClosedEventParameter*/
    public void onJavascriptDialogClosed(Consumer<JavascriptDialogClosedEventParameter> callback) {
        registerEventCallback("Page.javascriptDialogClosed", node -> {
            JavascriptDialogClosedEventParameter param;
            try { param = EventCenter.deserializeJson(node, JavascriptDialogClosedEventParameter.class); }
            catch (IOException e) { _evt.getLog().error(e); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of Page.javascriptDialogOpening.
     @see #onJavascriptDialogOpening*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class JavascriptDialogOpeningEventParameter implements CommonDomainType {
        /**Frame url.*/
        private final String url;
        /**Message that will be displayed by the dialog.*/
        private final String message;
        /**Dialog type.*/
        private final DialogType type;
        /**True iff browser is capable showing or acting on the given dialog. When browser has no
dialog handler for given target, calling alert while Page domain is engaged will stall
the page execution. Execution can be resumed via calling Page.handleJavaScriptDialog.*/
        private final Boolean hasBrowserHandler;
        /**Default dialog prompt.
        <em>Optional.</em>*/
        private final String defaultPrompt;
        public final String url() { return url; }
        public final String getUrl() { return url(); }
        public final String message() { return message; }
        public final String getMessage() { return message(); }
        public final DialogType type() { return type; }
        public final DialogType getType() { return type(); }
        public final Boolean hasBrowserHandler() { return hasBrowserHandler; }
        public final Boolean getHasBrowserHandler() { return hasBrowserHandler(); }
        public final String defaultPrompt() { return defaultPrompt; }
        public final String getDefaultPrompt() { return defaultPrompt(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"url\":").append('"').append(DomainBase.escapeJson(url)).append('"');
            strBuilder.append(",\"message\":").append('"').append(DomainBase.escapeJson(message)).append('"');
            type.toJson(strBuilder.append(",\"type\":"));
            strBuilder.append(",\"hasBrowserHandler\":").append(hasBrowserHandler);
            if (defaultPrompt != null) strBuilder.append(",\"defaultPrompt\":").append('"').append(DomainBase.escapeJson(defaultPrompt)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        JavascriptDialogOpeningEventParameter(
            @JsonProperty("url")String url,
            @JsonProperty("message")String message,
            @JsonProperty("type")DialogType type,
            @JsonProperty("hasBrowserHandler")Boolean hasBrowserHandler,
            @Nullable @JsonProperty("defaultPrompt")String defaultPrompt
        ) {
            this.url = url;
            this.message = message;
            this.type = type;
            this.hasBrowserHandler = hasBrowserHandler;
            this.defaultPrompt = defaultPrompt;
        }
    }
    /**Fired when a JavaScript initiated dialog (alert, confirm, prompt, or onbeforeunload) is about to
open.
     @see JavascriptDialogOpeningEventParameter*/
    public void onJavascriptDialogOpening(Consumer<JavascriptDialogOpeningEventParameter> callback) {
        registerEventCallback("Page.javascriptDialogOpening", node -> {
            JavascriptDialogOpeningEventParameter param;
            try { param = EventCenter.deserializeJson(node, JavascriptDialogOpeningEventParameter.class); }
            catch (IOException e) { _evt.getLog().error(e); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of Page.lifecycleEvent.
     @see #onLifecycleEvent*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class LifecycleEventEventParameter implements CommonDomainType {
        /**Id of the frame.*/
        private final FrameId frameId;
        /**Loader identifier. Empty string if the request is fetched from worker.*/
        private final Network.LoaderId loaderId;
        /**&lt;No document in protocol.&gt;*/
        private final String name;
        /**&lt;No document in protocol.&gt;*/
        private final Network.MonotonicTime timestamp;
        public final FrameId frameId() { return frameId; }
        public final FrameId getFrameId() { return frameId(); }
        public final Network.LoaderId loaderId() { return loaderId; }
        public final Network.LoaderId getLoaderId() { return loaderId(); }
        public final String name() { return name; }
        public final String getName() { return name(); }
        public final Network.MonotonicTime timestamp() { return timestamp; }
        public final Network.MonotonicTime getTimestamp() { return timestamp(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            frameId.toJson(strBuilder.append("\"frameId\":"));
            loaderId.toJson(strBuilder.append(",\"loaderId\":"));
            strBuilder.append(",\"name\":").append('"').append(DomainBase.escapeJson(name)).append('"');
            timestamp.toJson(strBuilder.append(",\"timestamp\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        LifecycleEventEventParameter(
            @JsonProperty("frameId")FrameId frameId,
            @JsonProperty("loaderId")Network.LoaderId loaderId,
            @JsonProperty("name")String name,
            @JsonProperty("timestamp")Network.MonotonicTime timestamp
        ) {
            this.frameId = frameId;
            this.loaderId = loaderId;
            this.name = name;
            this.timestamp = timestamp;
        }
    }
    /**Fired for top level page lifecycle events such as navigation, load, paint, etc.
     @see LifecycleEventEventParameter*/
    public void onLifecycleEvent(Consumer<LifecycleEventEventParameter> callback) {
        registerEventCallback("Page.lifecycleEvent", node -> {
            LifecycleEventEventParameter param;
            try { param = EventCenter.deserializeJson(node, LifecycleEventEventParameter.class); }
            catch (IOException e) { _evt.getLog().error(e); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of Page.loadEventFired.
     @see #onLoadEventFired*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class LoadEventFiredEventParameter implements CommonDomainType {
        /**&lt;No document in protocol.&gt;*/
        private final Network.MonotonicTime timestamp;
        public final Network.MonotonicTime timestamp() { return timestamp; }
        public final Network.MonotonicTime getTimestamp() { return timestamp(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            timestamp.toJson(strBuilder.append("\"timestamp\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        LoadEventFiredEventParameter(
            @JsonProperty("timestamp")Network.MonotonicTime timestamp
        ) {
            this.timestamp = timestamp;
        }
    }
    /**&lt;No document in protocol.&gt;
     @see LoadEventFiredEventParameter*/
    public void onLoadEventFired(Consumer<LoadEventFiredEventParameter> callback) {
        registerEventCallback("Page.loadEventFired", node -> {
            LoadEventFiredEventParameter param;
            try { param = EventCenter.deserializeJson(node, LoadEventFiredEventParameter.class); }
            catch (IOException e) { _evt.getLog().error(e); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of Page.navigatedWithinDocument.
    <p><strong>Experimental.</strong></p>
     @see #onNavigatedWithinDocument*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class NavigatedWithinDocumentEventParameter implements CommonDomainType {
        /**Id of the frame.*/
        private final FrameId frameId;
        /**Frame's new url.*/
        private final String url;
        public final FrameId frameId() { return frameId; }
        public final FrameId getFrameId() { return frameId(); }
        public final String url() { return url; }
        public final String getUrl() { return url(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            frameId.toJson(strBuilder.append("\"frameId\":"));
            strBuilder.append(",\"url\":").append('"').append(DomainBase.escapeJson(url)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        NavigatedWithinDocumentEventParameter(
            @JsonProperty("frameId")FrameId frameId,
            @JsonProperty("url")String url
        ) {
            this.frameId = frameId;
            this.url = url;
        }
    }
    /**Fired when same-document navigation happens, e.g. due to history API usage or anchor navigation.
    <p><strong>Experimental.</strong></p>
     @see NavigatedWithinDocumentEventParameter*/
    public void onNavigatedWithinDocument(Consumer<NavigatedWithinDocumentEventParameter> callback) {
        registerEventCallback("Page.navigatedWithinDocument", node -> {
            NavigatedWithinDocumentEventParameter param;
            try { param = EventCenter.deserializeJson(node, NavigatedWithinDocumentEventParameter.class); }
            catch (IOException e) { _evt.getLog().error(e); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of Page.screencastFrame.
    <p><strong>Experimental.</strong></p>
     @see #onScreencastFrame*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ScreencastFrameEventParameter implements CommonDomainType {
        /**Base64-encoded compressed image.*/
        private final String data;
        /**Screencast frame metadata.*/
        private final ScreencastFrameMetadata metadata;
        /**Frame number.*/
        private final Integer sessionId;
        public final String data() { return data; }
        public final String getData() { return data(); }
        public final ScreencastFrameMetadata metadata() { return metadata; }
        public final ScreencastFrameMetadata getMetadata() { return metadata(); }
        public final Integer sessionId() { return sessionId; }
        public final Integer getSessionId() { return sessionId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"data\":").append('"').append(DomainBase.escapeJson(data)).append('"');
            metadata.toJson(strBuilder.append(",\"metadata\":"));
            strBuilder.append(",\"sessionId\":").append(sessionId);
            strBuilder.append('}');
            return strBuilder;
        }
        ScreencastFrameEventParameter(
            @JsonProperty("data")String data,
            @JsonProperty("metadata")ScreencastFrameMetadata metadata,
            @JsonProperty("sessionId")Integer sessionId
        ) {
            this.data = data;
            this.metadata = metadata;
            this.sessionId = sessionId;
        }
    }
    /**Compressed image data requested by the `startScreencast`.
    <p><strong>Experimental.</strong></p>
     @see ScreencastFrameEventParameter*/
    public void onScreencastFrame(Consumer<ScreencastFrameEventParameter> callback) {
        registerEventCallback("Page.screencastFrame", node -> {
            ScreencastFrameEventParameter param;
            try { param = EventCenter.deserializeJson(node, ScreencastFrameEventParameter.class); }
            catch (IOException e) { _evt.getLog().error(e); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of Page.screencastVisibilityChanged.
    <p><strong>Experimental.</strong></p>
     @see #onScreencastVisibilityChanged*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ScreencastVisibilityChangedEventParameter implements CommonDomainType {
        /**True if the page is visible.*/
        private final Boolean visible;
        public final Boolean visible() { return visible; }
        public final Boolean getVisible() { return visible(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"visible\":").append(visible);
            strBuilder.append('}');
            return strBuilder;
        }
        ScreencastVisibilityChangedEventParameter(
            @JsonProperty("visible")Boolean visible
        ) {
            this.visible = visible;
        }
    }
    /**Fired when the page with currently enabled screencast was shown or hidden `.
    <p><strong>Experimental.</strong></p>
     @see ScreencastVisibilityChangedEventParameter*/
    public void onScreencastVisibilityChanged(Consumer<ScreencastVisibilityChangedEventParameter> callback) {
        registerEventCallback("Page.screencastVisibilityChanged", node -> {
            ScreencastVisibilityChangedEventParameter param;
            try { param = EventCenter.deserializeJson(node, ScreencastVisibilityChangedEventParameter.class); }
            catch (IOException e) { _evt.getLog().error(e); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of Page.windowOpen.
     @see #onWindowOpen*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class WindowOpenEventParameter implements CommonDomainType {
        /**The URL for the new window.*/
        private final String url;
        /**Window name.*/
        private final String windowName;
        /**An array of enabled window features.*/
        private final List<String> windowFeatures;
        /**Whether or not it was triggered by user gesture.*/
        private final Boolean userGesture;
        public final String url() { return url; }
        public final String getUrl() { return url(); }
        public final String windowName() { return windowName; }
        public final String getWindowName() { return windowName(); }
        public final List<String> windowFeatures() { return windowFeatures; }
        public final List<String> getWindowFeatures() { return windowFeatures(); }
        public final Boolean userGesture() { return userGesture; }
        public final Boolean getUserGesture() { return userGesture(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"url\":").append('"').append(DomainBase.escapeJson(url)).append('"');
            strBuilder.append(",\"windowName\":").append('"').append(DomainBase.escapeJson(windowName)).append('"');
                        strBuilder.append(",\"windowFeatures\":[");
            strBuilder.append('"').append(DomainBase.escapeJson(windowFeatures.get(0))).append('"');
            for (int i = 1; i < windowFeatures.size(); ++i)
                strBuilder.append(",\"").append(DomainBase.escapeJson(windowFeatures.get(i))).append('"');
            strBuilder.append(']');
            strBuilder.append(",\"userGesture\":").append(userGesture);
            strBuilder.append('}');
            return strBuilder;
        }
        WindowOpenEventParameter(
            @JsonProperty("url")String url,
            @JsonProperty("windowName")String windowName,
            @JsonProperty("windowFeatures")List<String> windowFeatures,
            @JsonProperty("userGesture")Boolean userGesture
        ) {
            this.url = url;
            this.windowName = windowName;
            this.windowFeatures = windowFeatures;
            this.userGesture = userGesture;
        }
    }
    /**Fired when a new window is going to be opened, via window.open(), link click, form submission,
etc.
     @see WindowOpenEventParameter*/
    public void onWindowOpen(Consumer<WindowOpenEventParameter> callback) {
        registerEventCallback("Page.windowOpen", node -> {
            WindowOpenEventParameter param;
            try { param = EventCenter.deserializeJson(node, WindowOpenEventParameter.class); }
            catch (IOException e) { _evt.getLog().error(e); return; }
            callback.accept(param);
        });
    }
}

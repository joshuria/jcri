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
@ParametersAreNonnullByDefault public class ApplicationCache extends DomainBase {
    public ApplicationCache(EventCenter evt, WebSocket ws) { super(evt, ws); }

    /**Detailed application cache resource information.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ApplicationCacheResource implements CommonDomainType {
        /**Resource url.*/
        private String url;
        /**Resource size.*/
        private Integer size;
        /**Resource type.*/
        private String type;
        public final ApplicationCacheResource url(String url) { this.url = url; return this; }
        public final ApplicationCacheResource setUrl(String url) { return url(url); }
        public final String url() { return url; }
        public final String getUrl() { return url(); }
        public final ApplicationCacheResource size(Integer size) { this.size = size; return this; }
        public final ApplicationCacheResource setSize(Integer size) { return size(size); }
        public final Integer size() { return size; }
        public final Integer getSize() { return size(); }
        public final ApplicationCacheResource type(String type) { this.type = type; return this; }
        public final ApplicationCacheResource setType(String type) { return type(type); }
        public final String type() { return type; }
        public final String getType() { return type(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (url == null) throw new IllegalArgumentException("ApplicationCache.ApplicationCacheResource.url is necessary field.");
            if (size == null) throw new IllegalArgumentException("ApplicationCache.ApplicationCacheResource.size is necessary field.");
            if (type == null) throw new IllegalArgumentException("ApplicationCache.ApplicationCacheResource.type is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"url\":").append('"').append(DomainBase.escapeJson(url)).append('"');
            strBuilder.append(",\"size\":").append(size);
            strBuilder.append(",\"type\":").append('"').append(DomainBase.escapeJson(type)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public ApplicationCacheResource() {}
        public ApplicationCacheResource(
            @JsonProperty("url")String url,
            @JsonProperty("size")Integer size,
            @JsonProperty("type")String type
        ) {
            this.url = url;
            this.size = size;
            this.type = type;
        }
    }

    /**Detailed application cache information.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ApplicationCacheType implements CommonDomainType {
        /**Manifest URL.*/
        private String manifestURL;
        /**Application cache size.*/
        private Double size;
        /**Application cache creation time.*/
        private Double creationTime;
        /**Application cache update time.*/
        private Double updateTime;
        /**Application cache resources.*/
        private List<ApplicationCacheResource> resources;
        public final ApplicationCacheType manifestURL(String manifestURL) { this.manifestURL = manifestURL; return this; }
        public final ApplicationCacheType setManifestURL(String manifestURL) { return manifestURL(manifestURL); }
        public final String manifestURL() { return manifestURL; }
        public final String getManifestURL() { return manifestURL(); }
        public final ApplicationCacheType size(Double size) { this.size = size; return this; }
        public final ApplicationCacheType setSize(Double size) { return size(size); }
        public final Double size() { return size; }
        public final Double getSize() { return size(); }
        public final ApplicationCacheType creationTime(Double creationTime) { this.creationTime = creationTime; return this; }
        public final ApplicationCacheType setCreationTime(Double creationTime) { return creationTime(creationTime); }
        public final Double creationTime() { return creationTime; }
        public final Double getCreationTime() { return creationTime(); }
        public final ApplicationCacheType updateTime(Double updateTime) { this.updateTime = updateTime; return this; }
        public final ApplicationCacheType setUpdateTime(Double updateTime) { return updateTime(updateTime); }
        public final Double updateTime() { return updateTime; }
        public final Double getUpdateTime() { return updateTime(); }
        public final ApplicationCacheType resources(List<ApplicationCacheResource> resources) { this.resources = resources; return this; }
        public final ApplicationCacheType setResources(List<ApplicationCacheResource> resources) { return resources(resources); }
        public final List<ApplicationCacheResource> resources() { return resources; }
        public final List<ApplicationCacheResource> getResources() { return resources(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (manifestURL == null) throw new IllegalArgumentException("ApplicationCache.ApplicationCacheType.manifestURL is necessary field.");
            if (size == null) throw new IllegalArgumentException("ApplicationCache.ApplicationCacheType.size is necessary field.");
            if (creationTime == null) throw new IllegalArgumentException("ApplicationCache.ApplicationCacheType.creationTime is necessary field.");
            if (updateTime == null) throw new IllegalArgumentException("ApplicationCache.ApplicationCacheType.updateTime is necessary field.");
            if (resources == null) throw new IllegalArgumentException("ApplicationCache.ApplicationCacheType.resources is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"manifestURL\":").append('"').append(DomainBase.escapeJson(manifestURL)).append('"');
            strBuilder.append(",\"size\":").append(size);
            strBuilder.append(",\"creationTime\":").append(creationTime);
            strBuilder.append(",\"updateTime\":").append(updateTime);
                        strBuilder.append(",\"resources\":[");
            resources.get(0).toJson(strBuilder);
            for (int i = 1; i < resources.size(); ++i)
                resources.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public ApplicationCacheType() {}
        public ApplicationCacheType(
            @JsonProperty("manifestURL")String manifestURL,
            @JsonProperty("size")Double size,
            @JsonProperty("creationTime")Double creationTime,
            @JsonProperty("updateTime")Double updateTime,
            @JsonProperty("resources")List<ApplicationCacheResource> resources
        ) {
            this.manifestURL = manifestURL;
            this.size = size;
            this.creationTime = creationTime;
            this.updateTime = updateTime;
            this.resources = resources;
        }
    }

    /**Frame identifier - manifest URL pair.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class FrameWithManifest implements CommonDomainType {
        /**Frame identifier.*/
        private Page.FrameId frameId;
        /**Manifest URL.*/
        private String manifestURL;
        /**Application cache status.*/
        private Integer status;
        public final FrameWithManifest frameId(Page.FrameId frameId) { this.frameId = frameId; return this; }
        public final FrameWithManifest setFrameId(Page.FrameId frameId) { return frameId(frameId); }
        public final Page.FrameId frameId() { return frameId; }
        public final Page.FrameId getFrameId() { return frameId(); }
        public final FrameWithManifest manifestURL(String manifestURL) { this.manifestURL = manifestURL; return this; }
        public final FrameWithManifest setManifestURL(String manifestURL) { return manifestURL(manifestURL); }
        public final String manifestURL() { return manifestURL; }
        public final String getManifestURL() { return manifestURL(); }
        public final FrameWithManifest status(Integer status) { this.status = status; return this; }
        public final FrameWithManifest setStatus(Integer status) { return status(status); }
        public final Integer status() { return status; }
        public final Integer getStatus() { return status(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (frameId == null) throw new IllegalArgumentException("ApplicationCache.FrameWithManifest.frameId is necessary field.");
            if (manifestURL == null) throw new IllegalArgumentException("ApplicationCache.FrameWithManifest.manifestURL is necessary field.");
            if (status == null) throw new IllegalArgumentException("ApplicationCache.FrameWithManifest.status is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            frameId.toJson(strBuilder.append("\"frameId\":"));
            strBuilder.append(",\"manifestURL\":").append('"').append(DomainBase.escapeJson(manifestURL)).append('"');
            strBuilder.append(",\"status\":").append(status);
            strBuilder.append('}');
            return strBuilder;
        }
        public FrameWithManifest() {}
        public FrameWithManifest(
            @JsonProperty("frameId")Page.FrameId frameId,
            @JsonProperty("manifestURL")String manifestURL,
            @JsonProperty("status")Integer status
        ) {
            this.frameId = frameId;
            this.manifestURL = manifestURL;
            this.status = status;
        }
    }
    /**Enables application cache domain notifications.*/
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
            return super.call("ApplicationCache.enable", EnableResult.class,
                (code, msg)->new EnableResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<EnableResult> call(Executor exec) {
            return super.call("ApplicationCache.enable", EnableResult.class,
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
    /**Returns relevant application cache data for the document in given frame.*/
    public GetApplicationCacheForFrameParameter getApplicationCacheForFrame() { final GetApplicationCacheForFrameParameter v = new GetApplicationCacheForFrameParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of getApplicationCacheForFrame.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetApplicationCacheForFrameParameter extends CommandBase {
        /**Identifier of the frame containing document whose application cache is retrieved.*/
        private Page.FrameId frameId;
        public final GetApplicationCacheForFrameParameter frameId(Page.FrameId frameId) { this.frameId = frameId; return this; }
        public final GetApplicationCacheForFrameParameter setFrameId(Page.FrameId frameId) { return frameId(frameId); }
        public final Page.FrameId frameId() { return frameId; }
        public final Page.FrameId getFrameId() { return frameId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (frameId == null) throw new IllegalArgumentException("ApplicationCache.GetApplicationCacheForFrameParameter.frameId is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            frameId.toJson(strBuilder.append("\"frameId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public GetApplicationCacheForFrameParameter() {}
        public GetApplicationCacheForFrameParameter(
            @JsonProperty("frameId")Page.FrameId frameId
        ) {
            this();
            this.frameId = frameId;
        }
        public CompletableFuture<GetApplicationCacheForFrameResult> call() {
            return super.call("ApplicationCache.getApplicationCacheForFrame", GetApplicationCacheForFrameResult.class,
                (code, msg)->new GetApplicationCacheForFrameResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetApplicationCacheForFrameResult> call(Executor exec) {
            return super.call("ApplicationCache.getApplicationCacheForFrame", GetApplicationCacheForFrameResult.class,
                (code, msg)->new GetApplicationCacheForFrameResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of getApplicationCacheForFrame.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetApplicationCacheForFrameResult extends ResultBase {
        /**Relevant application cache data for the document in given frame.*/
        private final ApplicationCacheType applicationCache;
        public final ApplicationCacheType applicationCache() { return applicationCache; }
        public final ApplicationCacheType getApplicationCache() { return applicationCache(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            applicationCache.toJson(strBuilder.append("\"applicationCache\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public GetApplicationCacheForFrameResult(
            @JsonProperty("applicationCache")ApplicationCacheType applicationCache
        ) {
            this.applicationCache = applicationCache;
        }
        public GetApplicationCacheForFrameResult(ResultBase.FailedResult e) {
            super(e);
            applicationCache = null;
        }
    }
    /**Returns array of frame identifiers with manifest urls for each frame containing a document
associated with some application cache.*/
    public GetFramesWithManifestsParameter getFramesWithManifests() { final GetFramesWithManifestsParameter v = new GetFramesWithManifestsParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of getFramesWithManifests.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetFramesWithManifestsParameter extends CommandBase {
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
        public GetFramesWithManifestsParameter() {}
        public CompletableFuture<GetFramesWithManifestsResult> call() {
            return super.call("ApplicationCache.getFramesWithManifests", GetFramesWithManifestsResult.class,
                (code, msg)->new GetFramesWithManifestsResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetFramesWithManifestsResult> call(Executor exec) {
            return super.call("ApplicationCache.getFramesWithManifests", GetFramesWithManifestsResult.class,
                (code, msg)->new GetFramesWithManifestsResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of getFramesWithManifests.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetFramesWithManifestsResult extends ResultBase {
        /**Array of frame identifiers with manifest urls for each frame containing a document
associated with some application cache.*/
        private final List<FrameWithManifest> frameIds;
        public final List<FrameWithManifest> frameIds() { return frameIds; }
        public final List<FrameWithManifest> getFrameIds() { return frameIds(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
                        strBuilder.append("\"frameIds\":[");
            frameIds.get(0).toJson(strBuilder);
            for (int i = 1; i < frameIds.size(); ++i)
                frameIds.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public GetFramesWithManifestsResult(
            @JsonProperty("frameIds")List<FrameWithManifest> frameIds
        ) {
            this.frameIds = frameIds;
        }
        public GetFramesWithManifestsResult(ResultBase.FailedResult e) {
            super(e);
            frameIds = null;
        }
    }
    /**Returns manifest URL for document in the given frame.*/
    public GetManifestForFrameParameter getManifestForFrame() { final GetManifestForFrameParameter v = new GetManifestForFrameParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of getManifestForFrame.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetManifestForFrameParameter extends CommandBase {
        /**Identifier of the frame containing document whose manifest is retrieved.*/
        private Page.FrameId frameId;
        public final GetManifestForFrameParameter frameId(Page.FrameId frameId) { this.frameId = frameId; return this; }
        public final GetManifestForFrameParameter setFrameId(Page.FrameId frameId) { return frameId(frameId); }
        public final Page.FrameId frameId() { return frameId; }
        public final Page.FrameId getFrameId() { return frameId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (frameId == null) throw new IllegalArgumentException("ApplicationCache.GetManifestForFrameParameter.frameId is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            frameId.toJson(strBuilder.append("\"frameId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public GetManifestForFrameParameter() {}
        public GetManifestForFrameParameter(
            @JsonProperty("frameId")Page.FrameId frameId
        ) {
            this();
            this.frameId = frameId;
        }
        public CompletableFuture<GetManifestForFrameResult> call() {
            return super.call("ApplicationCache.getManifestForFrame", GetManifestForFrameResult.class,
                (code, msg)->new GetManifestForFrameResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetManifestForFrameResult> call(Executor exec) {
            return super.call("ApplicationCache.getManifestForFrame", GetManifestForFrameResult.class,
                (code, msg)->new GetManifestForFrameResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of getManifestForFrame.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetManifestForFrameResult extends ResultBase {
        /**Manifest URL for document in the given frame.*/
        private final String manifestURL;
        public final String manifestURL() { return manifestURL; }
        public final String getManifestURL() { return manifestURL(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"manifestURL\":").append('"').append(DomainBase.escapeJson(manifestURL)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public GetManifestForFrameResult(
            @JsonProperty("manifestURL")String manifestURL
        ) {
            this.manifestURL = manifestURL;
        }
        public GetManifestForFrameResult(ResultBase.FailedResult e) {
            super(e);
            manifestURL = null;
        }
    }
    /**Event parameter of ApplicationCache.applicationCacheStatusUpdated.
     @see #onApplicationCacheStatusUpdated*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ApplicationCacheStatusUpdatedEventParameter implements CommonDomainType {
        /**Identifier of the frame containing document whose application cache updated status.*/
        private final Page.FrameId frameId;
        /**Manifest URL.*/
        private final String manifestURL;
        /**Updated application cache status.*/
        private final Integer status;
        public final Page.FrameId frameId() { return frameId; }
        public final Page.FrameId getFrameId() { return frameId(); }
        public final String manifestURL() { return manifestURL; }
        public final String getManifestURL() { return manifestURL(); }
        public final Integer status() { return status; }
        public final Integer getStatus() { return status(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            frameId.toJson(strBuilder.append("\"frameId\":"));
            strBuilder.append(",\"manifestURL\":").append('"').append(DomainBase.escapeJson(manifestURL)).append('"');
            strBuilder.append(",\"status\":").append(status);
            strBuilder.append('}');
            return strBuilder;
        }
        ApplicationCacheStatusUpdatedEventParameter(
            @JsonProperty("frameId")Page.FrameId frameId,
            @JsonProperty("manifestURL")String manifestURL,
            @JsonProperty("status")Integer status
        ) {
            this.frameId = frameId;
            this.manifestURL = manifestURL;
            this.status = status;
        }
    }
    /**&lt;No document in protocol.&gt;
     @see ApplicationCacheStatusUpdatedEventParameter*/
    public void onApplicationCacheStatusUpdated(@Nullable Consumer<ApplicationCacheStatusUpdatedEventParameter> callback) {
        if (callback != null)
            registerEventCallback("ApplicationCache.applicationCacheStatusUpdated", node -> {
                ApplicationCacheStatusUpdatedEventParameter param;
                try { param = EventCenter.deserializeJson(node, ApplicationCacheStatusUpdatedEventParameter.class); }
                catch (IOException e) { _evt.getLog().error(e); return; }
                callback.accept(param);
            });
        else    registerEventCallback("ApplicationCache.applicationCacheStatusUpdated", null);
    }
    /**Event parameter of ApplicationCache.networkStateUpdated.
     @see #onNetworkStateUpdated*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class NetworkStateUpdatedEventParameter implements CommonDomainType {
        /**&lt;No document in protocol.&gt;*/
        private final Boolean isNowOnline;
        public final Boolean isNowOnline() { return isNowOnline; }
        public final Boolean getIsNowOnline() { return isNowOnline(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"isNowOnline\":").append(isNowOnline);
            strBuilder.append('}');
            return strBuilder;
        }
        NetworkStateUpdatedEventParameter(
            @JsonProperty("isNowOnline")Boolean isNowOnline
        ) {
            this.isNowOnline = isNowOnline;
        }
    }
    /**&lt;No document in protocol.&gt;
     @see NetworkStateUpdatedEventParameter*/
    public void onNetworkStateUpdated(@Nullable Consumer<NetworkStateUpdatedEventParameter> callback) {
        if (callback != null)
            registerEventCallback("ApplicationCache.networkStateUpdated", node -> {
                NetworkStateUpdatedEventParameter param;
                try { param = EventCenter.deserializeJson(node, NetworkStateUpdatedEventParameter.class); }
                catch (IOException e) { _evt.getLog().error(e); return; }
                callback.accept(param);
            });
        else    registerEventCallback("ApplicationCache.networkStateUpdated", null);
    }
}

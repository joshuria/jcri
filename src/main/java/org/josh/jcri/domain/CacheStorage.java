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
 @author Joshua*/
@ParametersAreNonnullByDefault public class CacheStorage extends DomainBase {
    public CacheStorage(EventCenter evt, WebSocket ws) { super(evt, ws); }

    /**Unique identifier of the Cache object.*/
    @ParametersAreNonnullByDefault public static class CacheId implements CommonDomainType {
        private String _value;
        public CacheId() {}
        public CacheId(String value) { _value = value; }
        public final CacheId value(String value) { _value = value; return this; }
        public final String value() { return _value; }
        public final CacheId setValue(String value) { return value(value); }
        public final String getValue() { return value(); }
        @Override public String toString() { return _value; }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (_value == null) throw new IllegalArgumentException("CacheStorage.CacheId.value is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            return strBuilder.append(_value);
        }
    }

    /**Data entry.*/
    @ParametersAreNonnullByDefault public static class DataEntry implements CommonDomainType {
        /**Request URL.*/
        private String requestURL;
        /**Request method.*/
        private String requestMethod;
        /**Request headers*/
        private List<Header> requestHeaders;
        /**Number of seconds since epoch.*/
        private Double responseTime;
        /**HTTP response status code.*/
        private Integer responseStatus;
        /**HTTP response status text.*/
        private String responseStatusText;
        /**Response headers*/
        private List<Header> responseHeaders;
        public final DataEntry requestURL(String requestURL) { this.requestURL = requestURL; return this; }
        public final DataEntry setRequestURL(String requestURL) { return requestURL(requestURL); }
        public final String requestURL() { return requestURL; }
        public final String getRequestURL() { return requestURL(); }
        public final DataEntry requestMethod(String requestMethod) { this.requestMethod = requestMethod; return this; }
        public final DataEntry setRequestMethod(String requestMethod) { return requestMethod(requestMethod); }
        public final String requestMethod() { return requestMethod; }
        public final String getRequestMethod() { return requestMethod(); }
        public final DataEntry requestHeaders(List<Header> requestHeaders) { this.requestHeaders = requestHeaders; return this; }
        public final DataEntry setRequestHeaders(List<Header> requestHeaders) { return requestHeaders(requestHeaders); }
        public final List<Header> requestHeaders() { return requestHeaders; }
        public final List<Header> getRequestHeaders() { return requestHeaders(); }
        public final DataEntry responseTime(Double responseTime) { this.responseTime = responseTime; return this; }
        public final DataEntry setResponseTime(Double responseTime) { return responseTime(responseTime); }
        public final Double responseTime() { return responseTime; }
        public final Double getResponseTime() { return responseTime(); }
        public final DataEntry responseStatus(Integer responseStatus) { this.responseStatus = responseStatus; return this; }
        public final DataEntry setResponseStatus(Integer responseStatus) { return responseStatus(responseStatus); }
        public final Integer responseStatus() { return responseStatus; }
        public final Integer getResponseStatus() { return responseStatus(); }
        public final DataEntry responseStatusText(String responseStatusText) { this.responseStatusText = responseStatusText; return this; }
        public final DataEntry setResponseStatusText(String responseStatusText) { return responseStatusText(responseStatusText); }
        public final String responseStatusText() { return responseStatusText; }
        public final String getResponseStatusText() { return responseStatusText(); }
        public final DataEntry responseHeaders(List<Header> responseHeaders) { this.responseHeaders = responseHeaders; return this; }
        public final DataEntry setResponseHeaders(List<Header> responseHeaders) { return responseHeaders(responseHeaders); }
        public final List<Header> responseHeaders() { return responseHeaders; }
        public final List<Header> getResponseHeaders() { return responseHeaders(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (requestURL == null) throw new IllegalArgumentException("CacheStorage.DataEntry.requestURL is necessary field.");
            if (requestMethod == null) throw new IllegalArgumentException("CacheStorage.DataEntry.requestMethod is necessary field.");
            if (requestHeaders == null) throw new IllegalArgumentException("CacheStorage.DataEntry.requestHeaders is necessary field.");
            if (responseTime == null) throw new IllegalArgumentException("CacheStorage.DataEntry.responseTime is necessary field.");
            if (responseStatus == null) throw new IllegalArgumentException("CacheStorage.DataEntry.responseStatus is necessary field.");
            if (responseStatusText == null) throw new IllegalArgumentException("CacheStorage.DataEntry.responseStatusText is necessary field.");
            if (responseHeaders == null) throw new IllegalArgumentException("CacheStorage.DataEntry.responseHeaders is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"requestURL\":").append('"').append(requestURL).append('"');
            strBuilder.append(",\"requestMethod\":").append('"').append(requestMethod).append('"');
                        strBuilder.append(",\"requestHeaders\":[");
            requestHeaders.get(0).toJson(strBuilder);
            for (int i = 1; i < requestHeaders.size(); ++i)
                requestHeaders.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            strBuilder.append(",\"responseTime\":").append(responseTime);
            strBuilder.append(",\"responseStatus\":").append(responseStatus);
            strBuilder.append(",\"responseStatusText\":").append('"').append(responseStatusText).append('"');
                        strBuilder.append(",\"responseHeaders\":[");
            responseHeaders.get(0).toJson(strBuilder);
            for (int i = 1; i < responseHeaders.size(); ++i)
                responseHeaders.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public DataEntry() {}
        public DataEntry(
            @JsonProperty("requestURL")String requestURL,
            @JsonProperty("requestMethod")String requestMethod,
            @JsonProperty("requestHeaders")List<Header> requestHeaders,
            @JsonProperty("responseTime")Double responseTime,
            @JsonProperty("responseStatus")Integer responseStatus,
            @JsonProperty("responseStatusText")String responseStatusText,
            @JsonProperty("responseHeaders")List<Header> responseHeaders
        ) {
            this.requestURL = requestURL;
            this.requestMethod = requestMethod;
            this.requestHeaders = requestHeaders;
            this.responseTime = responseTime;
            this.responseStatus = responseStatus;
            this.responseStatusText = responseStatusText;
            this.responseHeaders = responseHeaders;
        }
    }

    /**Cache identifier.*/
    @ParametersAreNonnullByDefault public static class Cache implements CommonDomainType {
        /**An opaque unique id of the cache.*/
        private CacheId cacheId;
        /**Security origin of the cache.*/
        private String securityOrigin;
        /**The name of the cache.*/
        private String cacheName;
        public final Cache cacheId(CacheId cacheId) { this.cacheId = cacheId; return this; }
        public final Cache setCacheId(CacheId cacheId) { return cacheId(cacheId); }
        public final CacheId cacheId() { return cacheId; }
        public final CacheId getCacheId() { return cacheId(); }
        public final Cache securityOrigin(String securityOrigin) { this.securityOrigin = securityOrigin; return this; }
        public final Cache setSecurityOrigin(String securityOrigin) { return securityOrigin(securityOrigin); }
        public final String securityOrigin() { return securityOrigin; }
        public final String getSecurityOrigin() { return securityOrigin(); }
        public final Cache cacheName(String cacheName) { this.cacheName = cacheName; return this; }
        public final Cache setCacheName(String cacheName) { return cacheName(cacheName); }
        public final String cacheName() { return cacheName; }
        public final String getCacheName() { return cacheName(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (cacheId == null) throw new IllegalArgumentException("CacheStorage.Cache.cacheId is necessary field.");
            if (securityOrigin == null) throw new IllegalArgumentException("CacheStorage.Cache.securityOrigin is necessary field.");
            if (cacheName == null) throw new IllegalArgumentException("CacheStorage.Cache.cacheName is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            cacheId.toJson(strBuilder.append("\"cacheId\":"));
            strBuilder.append(",\"securityOrigin\":").append('"').append(securityOrigin).append('"');
            strBuilder.append(",\"cacheName\":").append('"').append(cacheName).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public Cache() {}
        public Cache(
            @JsonProperty("cacheId")CacheId cacheId,
            @JsonProperty("securityOrigin")String securityOrigin,
            @JsonProperty("cacheName")String cacheName
        ) {
            this.cacheId = cacheId;
            this.securityOrigin = securityOrigin;
            this.cacheName = cacheName;
        }
    }

    /**&lt;No document in protocol.&gt;*/
    @ParametersAreNonnullByDefault public static class Header implements CommonDomainType {
        /**&lt;No document in protocol.&gt;*/
        private String name;
        /**&lt;No document in protocol.&gt;*/
        private String value;
        public final Header name(String name) { this.name = name; return this; }
        public final Header setName(String name) { return name(name); }
        public final String name() { return name; }
        public final String getName() { return name(); }
        public final Header value(String value) { this.value = value; return this; }
        public final Header setValue(String value) { return value(value); }
        public final String value() { return value; }
        public final String getValue() { return value(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (name == null) throw new IllegalArgumentException("CacheStorage.Header.name is necessary field.");
            if (value == null) throw new IllegalArgumentException("CacheStorage.Header.value is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"name\":").append('"').append(name).append('"');
            strBuilder.append(",\"value\":").append('"').append(value).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public Header() {}
        public Header(
            @JsonProperty("name")String name,
            @JsonProperty("value")String value
        ) {
            this.name = name;
            this.value = value;
        }
    }

    /**Cached response*/
    @ParametersAreNonnullByDefault public static class CachedResponse implements CommonDomainType {
        /**Entry content, base64-encoded.*/
        private String body;
        public final CachedResponse body(String body) { this.body = body; return this; }
        public final CachedResponse setBody(String body) { return body(body); }
        public final String body() { return body; }
        public final String getBody() { return body(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (body == null) throw new IllegalArgumentException("CacheStorage.CachedResponse.body is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"body\":").append('"').append(body).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public CachedResponse() {}
        public CachedResponse(
            @JsonProperty("body")String body
        ) {
            this.body = body;
        }
    }
    /**Deletes a cache.*/
    public DeleteCacheParameter deleteCache() { final DeleteCacheParameter v = new DeleteCacheParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of deleteCache.*/
    @ParametersAreNonnullByDefault public static class DeleteCacheParameter extends CommandBase {
        /**Id of cache for deletion.*/
        private CacheId cacheId;
        public final DeleteCacheParameter cacheId(CacheId cacheId) { this.cacheId = cacheId; return this; }
        public final DeleteCacheParameter setCacheId(CacheId cacheId) { return cacheId(cacheId); }
        public final CacheId cacheId() { return cacheId; }
        public final CacheId getCacheId() { return cacheId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (cacheId == null) throw new IllegalArgumentException("CacheStorage.DeleteCacheParameter.cacheId is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            cacheId.toJson(strBuilder.append("\"cacheId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public DeleteCacheParameter() {}
        public DeleteCacheParameter(
            @JsonProperty("cacheId")CacheId cacheId
        ) {
            this();
            this.cacheId = cacheId;
        }
        public CompletableFuture<DeleteCacheResult> call() {
            return super.call("CacheStorage.deleteCache", DeleteCacheResult.class, msg->new DeleteCacheResult(ResultBase.ofError(msg)));
        }
        public CompletableFuture<DeleteCacheResult> call(Executor exec) {
            return super.call("CacheStorage.deleteCache", DeleteCacheResult.class, msg->new DeleteCacheResult(ResultBase.ofError(msg)), exec);
        }
    }
    /**Return result class of deleteCache.*/
    @ParametersAreNonnullByDefault public static class DeleteCacheResult extends ResultBase {
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
        public DeleteCacheResult() { super(); }
        public DeleteCacheResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Deletes a cache entry.*/
    public DeleteEntryParameter deleteEntry() { final DeleteEntryParameter v = new DeleteEntryParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of deleteEntry.*/
    @ParametersAreNonnullByDefault public static class DeleteEntryParameter extends CommandBase {
        /**Id of cache where the entry will be deleted.*/
        private CacheId cacheId;
        /**URL spec of the request.*/
        private String request;
        public final DeleteEntryParameter cacheId(CacheId cacheId) { this.cacheId = cacheId; return this; }
        public final DeleteEntryParameter setCacheId(CacheId cacheId) { return cacheId(cacheId); }
        public final CacheId cacheId() { return cacheId; }
        public final CacheId getCacheId() { return cacheId(); }
        public final DeleteEntryParameter request(String request) { this.request = request; return this; }
        public final DeleteEntryParameter setRequest(String request) { return request(request); }
        public final String request() { return request; }
        public final String getRequest() { return request(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (cacheId == null) throw new IllegalArgumentException("CacheStorage.DeleteEntryParameter.cacheId is necessary field.");
            if (request == null) throw new IllegalArgumentException("CacheStorage.DeleteEntryParameter.request is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            cacheId.toJson(strBuilder.append("\"cacheId\":"));
            strBuilder.append(",\"request\":").append('"').append(request).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public DeleteEntryParameter() {}
        public DeleteEntryParameter(
            @JsonProperty("cacheId")CacheId cacheId,
            @JsonProperty("request")String request
        ) {
            this();
            this.cacheId = cacheId;
            this.request = request;
        }
        public CompletableFuture<DeleteEntryResult> call() {
            return super.call("CacheStorage.deleteEntry", DeleteEntryResult.class, msg->new DeleteEntryResult(ResultBase.ofError(msg)));
        }
        public CompletableFuture<DeleteEntryResult> call(Executor exec) {
            return super.call("CacheStorage.deleteEntry", DeleteEntryResult.class, msg->new DeleteEntryResult(ResultBase.ofError(msg)), exec);
        }
    }
    /**Return result class of deleteEntry.*/
    @ParametersAreNonnullByDefault public static class DeleteEntryResult extends ResultBase {
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
        public DeleteEntryResult() { super(); }
        public DeleteEntryResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Requests cache names.*/
    public RequestCacheNamesParameter requestCacheNames() { final RequestCacheNamesParameter v = new RequestCacheNamesParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of requestCacheNames.*/
    @ParametersAreNonnullByDefault public static class RequestCacheNamesParameter extends CommandBase {
        /**Security origin.*/
        private String securityOrigin;
        public final RequestCacheNamesParameter securityOrigin(String securityOrigin) { this.securityOrigin = securityOrigin; return this; }
        public final RequestCacheNamesParameter setSecurityOrigin(String securityOrigin) { return securityOrigin(securityOrigin); }
        public final String securityOrigin() { return securityOrigin; }
        public final String getSecurityOrigin() { return securityOrigin(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (securityOrigin == null) throw new IllegalArgumentException("CacheStorage.RequestCacheNamesParameter.securityOrigin is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"securityOrigin\":").append('"').append(securityOrigin).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public RequestCacheNamesParameter() {}
        public RequestCacheNamesParameter(
            @JsonProperty("securityOrigin")String securityOrigin
        ) {
            this();
            this.securityOrigin = securityOrigin;
        }
        public CompletableFuture<RequestCacheNamesResult> call() {
            return super.call("CacheStorage.requestCacheNames", RequestCacheNamesResult.class, msg->new RequestCacheNamesResult(ResultBase.ofError(msg)));
        }
        public CompletableFuture<RequestCacheNamesResult> call(Executor exec) {
            return super.call("CacheStorage.requestCacheNames", RequestCacheNamesResult.class, msg->new RequestCacheNamesResult(ResultBase.ofError(msg)), exec);
        }
    }
    /**Return result class of requestCacheNames.*/
    @ParametersAreNonnullByDefault public static class RequestCacheNamesResult extends ResultBase {
        /**Caches for the security origin.*/
        private final List<Cache> caches;
        public final List<Cache> caches() { return caches; }
        public final List<Cache> getCaches() { return caches(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
                        strBuilder.append("\"caches\":[");
            caches.get(0).toJson(strBuilder);
            for (int i = 1; i < caches.size(); ++i)
                caches.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public RequestCacheNamesResult(
            @JsonProperty("caches")List<Cache> caches
        ) {
            this.caches = caches;
        }
        public RequestCacheNamesResult(ResultBase.FailedResult e) {
            super(e);
            caches = null;
        }
    }
    /**Fetches cache entry.*/
    public RequestCachedResponseParameter requestCachedResponse() { final RequestCachedResponseParameter v = new RequestCachedResponseParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of requestCachedResponse.*/
    @ParametersAreNonnullByDefault public static class RequestCachedResponseParameter extends CommandBase {
        /**Id of cache that contains the enty.*/
        private CacheId cacheId;
        /**URL spec of the request.*/
        private String requestURL;
        public final RequestCachedResponseParameter cacheId(CacheId cacheId) { this.cacheId = cacheId; return this; }
        public final RequestCachedResponseParameter setCacheId(CacheId cacheId) { return cacheId(cacheId); }
        public final CacheId cacheId() { return cacheId; }
        public final CacheId getCacheId() { return cacheId(); }
        public final RequestCachedResponseParameter requestURL(String requestURL) { this.requestURL = requestURL; return this; }
        public final RequestCachedResponseParameter setRequestURL(String requestURL) { return requestURL(requestURL); }
        public final String requestURL() { return requestURL; }
        public final String getRequestURL() { return requestURL(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (cacheId == null) throw new IllegalArgumentException("CacheStorage.RequestCachedResponseParameter.cacheId is necessary field.");
            if (requestURL == null) throw new IllegalArgumentException("CacheStorage.RequestCachedResponseParameter.requestURL is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            cacheId.toJson(strBuilder.append("\"cacheId\":"));
            strBuilder.append(",\"requestURL\":").append('"').append(requestURL).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public RequestCachedResponseParameter() {}
        public RequestCachedResponseParameter(
            @JsonProperty("cacheId")CacheId cacheId,
            @JsonProperty("requestURL")String requestURL
        ) {
            this();
            this.cacheId = cacheId;
            this.requestURL = requestURL;
        }
        public CompletableFuture<RequestCachedResponseResult> call() {
            return super.call("CacheStorage.requestCachedResponse", RequestCachedResponseResult.class, msg->new RequestCachedResponseResult(ResultBase.ofError(msg)));
        }
        public CompletableFuture<RequestCachedResponseResult> call(Executor exec) {
            return super.call("CacheStorage.requestCachedResponse", RequestCachedResponseResult.class, msg->new RequestCachedResponseResult(ResultBase.ofError(msg)), exec);
        }
    }
    /**Return result class of requestCachedResponse.*/
    @ParametersAreNonnullByDefault public static class RequestCachedResponseResult extends ResultBase {
        /**Response read from the cache.*/
        private final CachedResponse response;
        public final CachedResponse response() { return response; }
        public final CachedResponse getResponse() { return response(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            response.toJson(strBuilder.append("\"response\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public RequestCachedResponseResult(
            @JsonProperty("response")CachedResponse response
        ) {
            this.response = response;
        }
        public RequestCachedResponseResult(ResultBase.FailedResult e) {
            super(e);
            response = null;
        }
    }
    /**Requests data from cache.*/
    public RequestEntriesParameter requestEntries() { final RequestEntriesParameter v = new RequestEntriesParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of requestEntries.*/
    @ParametersAreNonnullByDefault public static class RequestEntriesParameter extends CommandBase {
        /**ID of cache to get entries from.*/
        private CacheId cacheId;
        /**Number of records to skip.*/
        private Integer skipCount;
        /**Number of records to fetch.*/
        private Integer pageSize;
        public final RequestEntriesParameter cacheId(CacheId cacheId) { this.cacheId = cacheId; return this; }
        public final RequestEntriesParameter setCacheId(CacheId cacheId) { return cacheId(cacheId); }
        public final CacheId cacheId() { return cacheId; }
        public final CacheId getCacheId() { return cacheId(); }
        public final RequestEntriesParameter skipCount(Integer skipCount) { this.skipCount = skipCount; return this; }
        public final RequestEntriesParameter setSkipCount(Integer skipCount) { return skipCount(skipCount); }
        public final Integer skipCount() { return skipCount; }
        public final Integer getSkipCount() { return skipCount(); }
        public final RequestEntriesParameter pageSize(Integer pageSize) { this.pageSize = pageSize; return this; }
        public final RequestEntriesParameter setPageSize(Integer pageSize) { return pageSize(pageSize); }
        public final Integer pageSize() { return pageSize; }
        public final Integer getPageSize() { return pageSize(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (cacheId == null) throw new IllegalArgumentException("CacheStorage.RequestEntriesParameter.cacheId is necessary field.");
            if (skipCount == null) throw new IllegalArgumentException("CacheStorage.RequestEntriesParameter.skipCount is necessary field.");
            if (pageSize == null) throw new IllegalArgumentException("CacheStorage.RequestEntriesParameter.pageSize is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            cacheId.toJson(strBuilder.append("\"cacheId\":"));
            strBuilder.append(",\"skipCount\":").append(skipCount);
            strBuilder.append(",\"pageSize\":").append(pageSize);
            strBuilder.append('}');
            return strBuilder;
        }
        public RequestEntriesParameter() {}
        public RequestEntriesParameter(
            @JsonProperty("cacheId")CacheId cacheId,
            @JsonProperty("skipCount")Integer skipCount,
            @JsonProperty("pageSize")Integer pageSize
        ) {
            this();
            this.cacheId = cacheId;
            this.skipCount = skipCount;
            this.pageSize = pageSize;
        }
        public CompletableFuture<RequestEntriesResult> call() {
            return super.call("CacheStorage.requestEntries", RequestEntriesResult.class, msg->new RequestEntriesResult(ResultBase.ofError(msg)));
        }
        public CompletableFuture<RequestEntriesResult> call(Executor exec) {
            return super.call("CacheStorage.requestEntries", RequestEntriesResult.class, msg->new RequestEntriesResult(ResultBase.ofError(msg)), exec);
        }
    }
    /**Return result class of requestEntries.*/
    @ParametersAreNonnullByDefault public static class RequestEntriesResult extends ResultBase {
        /**Array of object store data entries.*/
        private final List<DataEntry> cacheDataEntries;
        /**If true, there are more entries to fetch in the given range.*/
        private final Boolean hasMore;
        public final List<DataEntry> cacheDataEntries() { return cacheDataEntries; }
        public final List<DataEntry> getCacheDataEntries() { return cacheDataEntries(); }
        public final Boolean hasMore() { return hasMore; }
        public final Boolean getHasMore() { return hasMore(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
                        strBuilder.append("\"cacheDataEntries\":[");
            cacheDataEntries.get(0).toJson(strBuilder);
            for (int i = 1; i < cacheDataEntries.size(); ++i)
                cacheDataEntries.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            strBuilder.append(",\"hasMore\":").append(hasMore);
            strBuilder.append('}');
            return strBuilder;
        }
        public RequestEntriesResult(
            @JsonProperty("cacheDataEntries")List<DataEntry> cacheDataEntries,
            @JsonProperty("hasMore")Boolean hasMore
        ) {
            this.cacheDataEntries = cacheDataEntries;
            this.hasMore = hasMore;
        }
        public RequestEntriesResult(ResultBase.FailedResult e) {
            super(e);
            cacheDataEntries = null;
            hasMore = null;
        }
    }
}

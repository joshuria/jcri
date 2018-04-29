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

/**Network domain allows tracking network activities of the page. It exposes information about http,
file, data and other requests and responses, their headers, bodies, timing, etc.
<p>From: browser_protocol.json</p>
<p>Protocol version: 1.3</p>
 @see Debugger
 @see Runtime
 @see Security
 @author Joshua*/
@ParametersAreNonnullByDefault public class Network extends DomainBase {
    public Network(EventCenter evt, WebSocket ws) { super(evt, ws); }

    /**Unique loader identifier.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class LoaderId implements CommonDomainType {
        private String _value;
        public LoaderId() {}
        public LoaderId(String value) { _value = value; }
        public final LoaderId value(String value) { _value = value; return this; }
        public final String value() { return _value; }
        public final LoaderId setValue(String value) { return value(value); }
        public final String getValue() { return value(); }
        @Override public String toString() { return _value; }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (_value == null) throw new IllegalArgumentException("Network.LoaderId.value is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            return strBuilder.append('"').append(DomainBase.escapeJson(_value)).append('"');
        }
    }

    /**Unique request identifier.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class RequestId implements CommonDomainType {
        private String _value;
        public RequestId() {}
        public RequestId(String value) { _value = value; }
        public final RequestId value(String value) { _value = value; return this; }
        public final String value() { return _value; }
        public final RequestId setValue(String value) { return value(value); }
        public final String getValue() { return value(); }
        @Override public String toString() { return _value; }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (_value == null) throw new IllegalArgumentException("Network.RequestId.value is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            return strBuilder.append('"').append(DomainBase.escapeJson(_value)).append('"');
        }
    }

    /**Unique intercepted request identifier.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class InterceptionId implements CommonDomainType {
        private String _value;
        public InterceptionId() {}
        public InterceptionId(String value) { _value = value; }
        public final InterceptionId value(String value) { _value = value; return this; }
        public final String value() { return _value; }
        public final InterceptionId setValue(String value) { return value(value); }
        public final String getValue() { return value(); }
        @Override public String toString() { return _value; }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (_value == null) throw new IllegalArgumentException("Network.InterceptionId.value is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            return strBuilder.append('"').append(DomainBase.escapeJson(_value)).append('"');
        }
    }

    /**Network level fetch failure reason.*/
    @ParametersAreNonnullByDefault public enum ErrorReason implements CommonDomainType {
        Failed("Failed"),
        Aborted("Aborted"),
        TimedOut("TimedOut"),
        AccessDenied("AccessDenied"),
        ConnectionClosed("ConnectionClosed"),
        ConnectionReset("ConnectionReset"),
        ConnectionRefused("ConnectionRefused"),
        ConnectionAborted("ConnectionAborted"),
        ConnectionFailed("ConnectionFailed"),
        NameNotResolved("NameNotResolved"),
        InternetDisconnected("InternetDisconnected"),
        AddressUnreachable("AddressUnreachable");

        private final String _value;
        /**Convert string representation to type.
         @throws IllegalArgumentException if given value cannot convert to enum type. */
        @JsonCreator public static ErrorReason of(String value) {
            return Enum.valueOf(ErrorReason.class, value.substring(0, 1).toUpperCase() + value.substring(1));
        }
        ErrorReason(String value) { _value = value; }
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
        public TimeSinceEpoch(Double value) { _value = value; }
        public final TimeSinceEpoch value(Double value) { _value = value; return this; }
        public final Double value() { return _value; }
        public final TimeSinceEpoch setValue(Double value) { return value(value); }
        public final Double getValue() { return value(); }
        @Override public String toString() { return String.valueOf(_value); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (_value == null) throw new IllegalArgumentException("Network.TimeSinceEpoch.value is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            return strBuilder.append(_value);
        }
    }

    /**Monotonically increasing time in seconds since an arbitrary point in the past.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class MonotonicTime implements CommonDomainType {
        private Double _value;
        public MonotonicTime() {}
        public MonotonicTime(Double value) { _value = value; }
        public final MonotonicTime value(Double value) { _value = value; return this; }
        public final Double value() { return _value; }
        public final MonotonicTime setValue(Double value) { return value(value); }
        public final Double getValue() { return value(); }
        @Override public String toString() { return String.valueOf(_value); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (_value == null) throw new IllegalArgumentException("Network.MonotonicTime.value is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            return strBuilder.append(_value);
        }
    }

    /**Request / response headers as keys / values of JSON object.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class Headers implements CommonDomainType {
        private Object _value;
        public Headers() {}
        public Headers(Object value) { _value = value; }
        public final Headers value(Object value) { _value = value; return this; }
        public final Object value() { return _value; }
        public final Headers setValue(Object value) { return value(value); }
        public final Object getValue() { return value(); }
        @Override public String toString() { return String.valueOf(_value); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (_value == null) throw new IllegalArgumentException("Network.Headers.value is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            return strBuilder.append(_value);
        }
    }

    /**The underlying connection technology that the browser is supposedly using.*/
    @ParametersAreNonnullByDefault public enum ConnectionType implements CommonDomainType {
        None("none"),
        Cellular2g("cellular2g"),
        Cellular3g("cellular3g"),
        Cellular4g("cellular4g"),
        Bluetooth("bluetooth"),
        Ethernet("ethernet"),
        Wifi("wifi"),
        Wimax("wimax"),
        Other("other");

        private final String _value;
        /**Convert string representation to type.
         @throws IllegalArgumentException if given value cannot convert to enum type. */
        @JsonCreator public static ConnectionType of(String value) {
            return Enum.valueOf(ConnectionType.class, value.substring(0, 1).toUpperCase() + value.substring(1));
        }
        ConnectionType(String value) { _value = value; }
        /**Check if parameter fields of method are all valid. */
        @Override public void check() throws IllegalArgumentException { /* Need not check */ }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) { return strBuilder.append('"').append(_value).append('"'); }
        @Override public String toString() { return "\"" + _value + "\""; }
    }

    /**Represents the cookie's 'SameSite' status:
https://tools.ietf.org/html/draft-west-first-party-cookies*/
    @ParametersAreNonnullByDefault public enum CookieSameSite implements CommonDomainType {
        Strict("Strict"),
        Lax("Lax");

        private final String _value;
        /**Convert string representation to type.
         @throws IllegalArgumentException if given value cannot convert to enum type. */
        @JsonCreator public static CookieSameSite of(String value) {
            return Enum.valueOf(CookieSameSite.class, value.substring(0, 1).toUpperCase() + value.substring(1));
        }
        CookieSameSite(String value) { _value = value; }
        /**Check if parameter fields of method are all valid. */
        @Override public void check() throws IllegalArgumentException { /* Need not check */ }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) { return strBuilder.append('"').append(_value).append('"'); }
        @Override public String toString() { return "\"" + _value + "\""; }
    }

    /**Timing information for the request.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ResourceTiming implements CommonDomainType {
        /**Timing's requestTime is a baseline in seconds, while the other numbers are ticks in
milliseconds relatively to this requestTime.*/
        private Double requestTime;
        /**Started resolving proxy.*/
        private Double proxyStart;
        /**Finished resolving proxy.*/
        private Double proxyEnd;
        /**Started DNS address resolve.*/
        private Double dnsStart;
        /**Finished DNS address resolve.*/
        private Double dnsEnd;
        /**Started connecting to the remote host.*/
        private Double connectStart;
        /**Connected to the remote host.*/
        private Double connectEnd;
        /**Started SSL handshake.*/
        private Double sslStart;
        /**Finished SSL handshake.*/
        private Double sslEnd;
        /**Started running ServiceWorker.
        <p><strong>Experimental.</strong></p>*/
        private Double workerStart;
        /**Finished Starting ServiceWorker.
        <p><strong>Experimental.</strong></p>*/
        private Double workerReady;
        /**Started sending request.*/
        private Double sendStart;
        /**Finished sending request.*/
        private Double sendEnd;
        /**Time the server started pushing request.
        <p><strong>Experimental.</strong></p>*/
        private Double pushStart;
        /**Time the server finished pushing request.
        <p><strong>Experimental.</strong></p>*/
        private Double pushEnd;
        /**Finished receiving response headers.*/
        private Double receiveHeadersEnd;
        public final ResourceTiming requestTime(Double requestTime) { this.requestTime = requestTime; return this; }
        public final ResourceTiming setRequestTime(Double requestTime) { return requestTime(requestTime); }
        public final Double requestTime() { return requestTime; }
        public final Double getRequestTime() { return requestTime(); }
        public final ResourceTiming proxyStart(Double proxyStart) { this.proxyStart = proxyStart; return this; }
        public final ResourceTiming setProxyStart(Double proxyStart) { return proxyStart(proxyStart); }
        public final Double proxyStart() { return proxyStart; }
        public final Double getProxyStart() { return proxyStart(); }
        public final ResourceTiming proxyEnd(Double proxyEnd) { this.proxyEnd = proxyEnd; return this; }
        public final ResourceTiming setProxyEnd(Double proxyEnd) { return proxyEnd(proxyEnd); }
        public final Double proxyEnd() { return proxyEnd; }
        public final Double getProxyEnd() { return proxyEnd(); }
        public final ResourceTiming dnsStart(Double dnsStart) { this.dnsStart = dnsStart; return this; }
        public final ResourceTiming setDnsStart(Double dnsStart) { return dnsStart(dnsStart); }
        public final Double dnsStart() { return dnsStart; }
        public final Double getDnsStart() { return dnsStart(); }
        public final ResourceTiming dnsEnd(Double dnsEnd) { this.dnsEnd = dnsEnd; return this; }
        public final ResourceTiming setDnsEnd(Double dnsEnd) { return dnsEnd(dnsEnd); }
        public final Double dnsEnd() { return dnsEnd; }
        public final Double getDnsEnd() { return dnsEnd(); }
        public final ResourceTiming connectStart(Double connectStart) { this.connectStart = connectStart; return this; }
        public final ResourceTiming setConnectStart(Double connectStart) { return connectStart(connectStart); }
        public final Double connectStart() { return connectStart; }
        public final Double getConnectStart() { return connectStart(); }
        public final ResourceTiming connectEnd(Double connectEnd) { this.connectEnd = connectEnd; return this; }
        public final ResourceTiming setConnectEnd(Double connectEnd) { return connectEnd(connectEnd); }
        public final Double connectEnd() { return connectEnd; }
        public final Double getConnectEnd() { return connectEnd(); }
        public final ResourceTiming sslStart(Double sslStart) { this.sslStart = sslStart; return this; }
        public final ResourceTiming setSslStart(Double sslStart) { return sslStart(sslStart); }
        public final Double sslStart() { return sslStart; }
        public final Double getSslStart() { return sslStart(); }
        public final ResourceTiming sslEnd(Double sslEnd) { this.sslEnd = sslEnd; return this; }
        public final ResourceTiming setSslEnd(Double sslEnd) { return sslEnd(sslEnd); }
        public final Double sslEnd() { return sslEnd; }
        public final Double getSslEnd() { return sslEnd(); }
        public final ResourceTiming workerStart(Double workerStart) { this.workerStart = workerStart; return this; }
        public final ResourceTiming setWorkerStart(Double workerStart) { return workerStart(workerStart); }
        public final Double workerStart() { return workerStart; }
        public final Double getWorkerStart() { return workerStart(); }
        public final ResourceTiming workerReady(Double workerReady) { this.workerReady = workerReady; return this; }
        public final ResourceTiming setWorkerReady(Double workerReady) { return workerReady(workerReady); }
        public final Double workerReady() { return workerReady; }
        public final Double getWorkerReady() { return workerReady(); }
        public final ResourceTiming sendStart(Double sendStart) { this.sendStart = sendStart; return this; }
        public final ResourceTiming setSendStart(Double sendStart) { return sendStart(sendStart); }
        public final Double sendStart() { return sendStart; }
        public final Double getSendStart() { return sendStart(); }
        public final ResourceTiming sendEnd(Double sendEnd) { this.sendEnd = sendEnd; return this; }
        public final ResourceTiming setSendEnd(Double sendEnd) { return sendEnd(sendEnd); }
        public final Double sendEnd() { return sendEnd; }
        public final Double getSendEnd() { return sendEnd(); }
        public final ResourceTiming pushStart(Double pushStart) { this.pushStart = pushStart; return this; }
        public final ResourceTiming setPushStart(Double pushStart) { return pushStart(pushStart); }
        public final Double pushStart() { return pushStart; }
        public final Double getPushStart() { return pushStart(); }
        public final ResourceTiming pushEnd(Double pushEnd) { this.pushEnd = pushEnd; return this; }
        public final ResourceTiming setPushEnd(Double pushEnd) { return pushEnd(pushEnd); }
        public final Double pushEnd() { return pushEnd; }
        public final Double getPushEnd() { return pushEnd(); }
        public final ResourceTiming receiveHeadersEnd(Double receiveHeadersEnd) { this.receiveHeadersEnd = receiveHeadersEnd; return this; }
        public final ResourceTiming setReceiveHeadersEnd(Double receiveHeadersEnd) { return receiveHeadersEnd(receiveHeadersEnd); }
        public final Double receiveHeadersEnd() { return receiveHeadersEnd; }
        public final Double getReceiveHeadersEnd() { return receiveHeadersEnd(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (requestTime == null) throw new IllegalArgumentException("Network.ResourceTiming.requestTime is necessary field.");
            if (proxyStart == null) throw new IllegalArgumentException("Network.ResourceTiming.proxyStart is necessary field.");
            if (proxyEnd == null) throw new IllegalArgumentException("Network.ResourceTiming.proxyEnd is necessary field.");
            if (dnsStart == null) throw new IllegalArgumentException("Network.ResourceTiming.dnsStart is necessary field.");
            if (dnsEnd == null) throw new IllegalArgumentException("Network.ResourceTiming.dnsEnd is necessary field.");
            if (connectStart == null) throw new IllegalArgumentException("Network.ResourceTiming.connectStart is necessary field.");
            if (connectEnd == null) throw new IllegalArgumentException("Network.ResourceTiming.connectEnd is necessary field.");
            if (sslStart == null) throw new IllegalArgumentException("Network.ResourceTiming.sslStart is necessary field.");
            if (sslEnd == null) throw new IllegalArgumentException("Network.ResourceTiming.sslEnd is necessary field.");
            if (workerStart == null) throw new IllegalArgumentException("Network.ResourceTiming.workerStart is necessary field.");
            if (workerReady == null) throw new IllegalArgumentException("Network.ResourceTiming.workerReady is necessary field.");
            if (sendStart == null) throw new IllegalArgumentException("Network.ResourceTiming.sendStart is necessary field.");
            if (sendEnd == null) throw new IllegalArgumentException("Network.ResourceTiming.sendEnd is necessary field.");
            if (pushStart == null) throw new IllegalArgumentException("Network.ResourceTiming.pushStart is necessary field.");
            if (pushEnd == null) throw new IllegalArgumentException("Network.ResourceTiming.pushEnd is necessary field.");
            if (receiveHeadersEnd == null) throw new IllegalArgumentException("Network.ResourceTiming.receiveHeadersEnd is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"requestTime\":").append(requestTime);
            strBuilder.append(",\"proxyStart\":").append(proxyStart);
            strBuilder.append(",\"proxyEnd\":").append(proxyEnd);
            strBuilder.append(",\"dnsStart\":").append(dnsStart);
            strBuilder.append(",\"dnsEnd\":").append(dnsEnd);
            strBuilder.append(",\"connectStart\":").append(connectStart);
            strBuilder.append(",\"connectEnd\":").append(connectEnd);
            strBuilder.append(",\"sslStart\":").append(sslStart);
            strBuilder.append(",\"sslEnd\":").append(sslEnd);
            strBuilder.append(",\"workerStart\":").append(workerStart);
            strBuilder.append(",\"workerReady\":").append(workerReady);
            strBuilder.append(",\"sendStart\":").append(sendStart);
            strBuilder.append(",\"sendEnd\":").append(sendEnd);
            strBuilder.append(",\"pushStart\":").append(pushStart);
            strBuilder.append(",\"pushEnd\":").append(pushEnd);
            strBuilder.append(",\"receiveHeadersEnd\":").append(receiveHeadersEnd);
            strBuilder.append('}');
            return strBuilder;
        }
        public ResourceTiming() {}
        public ResourceTiming(
            @JsonProperty("requestTime")Double requestTime,
            @JsonProperty("proxyStart")Double proxyStart,
            @JsonProperty("proxyEnd")Double proxyEnd,
            @JsonProperty("dnsStart")Double dnsStart,
            @JsonProperty("dnsEnd")Double dnsEnd,
            @JsonProperty("connectStart")Double connectStart,
            @JsonProperty("connectEnd")Double connectEnd,
            @JsonProperty("sslStart")Double sslStart,
            @JsonProperty("sslEnd")Double sslEnd,
            @JsonProperty("workerStart")Double workerStart,
            @JsonProperty("workerReady")Double workerReady,
            @JsonProperty("sendStart")Double sendStart,
            @JsonProperty("sendEnd")Double sendEnd,
            @JsonProperty("pushStart")Double pushStart,
            @JsonProperty("pushEnd")Double pushEnd,
            @JsonProperty("receiveHeadersEnd")Double receiveHeadersEnd
        ) {
            this.requestTime = requestTime;
            this.proxyStart = proxyStart;
            this.proxyEnd = proxyEnd;
            this.dnsStart = dnsStart;
            this.dnsEnd = dnsEnd;
            this.connectStart = connectStart;
            this.connectEnd = connectEnd;
            this.sslStart = sslStart;
            this.sslEnd = sslEnd;
            this.workerStart = workerStart;
            this.workerReady = workerReady;
            this.sendStart = sendStart;
            this.sendEnd = sendEnd;
            this.pushStart = pushStart;
            this.pushEnd = pushEnd;
            this.receiveHeadersEnd = receiveHeadersEnd;
        }
    }

    /**Loading priority of a resource request.*/
    @ParametersAreNonnullByDefault public enum ResourcePriority implements CommonDomainType {
        VeryLow("VeryLow"),
        Low("Low"),
        Medium("Medium"),
        High("High"),
        VeryHigh("VeryHigh");

        private final String _value;
        /**Convert string representation to type.
         @throws IllegalArgumentException if given value cannot convert to enum type. */
        @JsonCreator public static ResourcePriority of(String value) {
            return Enum.valueOf(ResourcePriority.class, value.substring(0, 1).toUpperCase() + value.substring(1));
        }
        ResourcePriority(String value) { _value = value; }
        /**Check if parameter fields of method are all valid. */
        @Override public void check() throws IllegalArgumentException { /* Need not check */ }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) { return strBuilder.append('"').append(_value).append('"'); }
        @Override public String toString() { return "\"" + _value + "\""; }
    }

    /**HTTP request data.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class Request implements CommonDomainType {
        /**Request URL.*/
        private String url;
        /**HTTP request method.*/
        private String method;
        /**HTTP request headers.*/
        private Headers headers;
        /**HTTP POST request data.
        <em>Optional.</em>*/
        private String postData;
        /**True when the request has POST data. Note that postData might still be omitted when this flag is true when the data is too long.
        <em>Optional.</em>*/
        private Boolean hasPostData;
        /**The mixed content type of the request.
        <em>Optional.</em>*/
        private Security.MixedContentType mixedContentType;
        /**Priority of the resource request at the time request is sent.*/
        private ResourcePriority initialPriority;
        /**The referrer policy of the request, as defined in https://www.w3.org/TR/referrer-policy/*/
        @ParametersAreNonnullByDefault public enum ReferrerPolicy implements CommonDomainType {
            Unsafe_url("unsafe-url"),
            No_referrer_when_downgrade("no-referrer-when-downgrade"),
            No_referrer("no-referrer"),
            Origin("origin"),
            Origin_when_cross_origin("origin-when-cross-origin"),
            Same_origin("same-origin"),
            Strict_origin("strict-origin"),
            Strict_origin_when_cross_origin("strict-origin-when-cross-origin");

            private final String _value;
            /**Convert string representation to type.
             @throws IllegalArgumentException if given value cannot convert to enum type. */
            @JsonCreator public static ReferrerPolicy of(String value) {
                return Enum.valueOf(ReferrerPolicy.class, value.substring(0, 1).toUpperCase() + value.substring(1));
            }
            ReferrerPolicy(String value) { _value = value; }
            /**Check if parameter fields of method are all valid. */
            @Override public void check() throws IllegalArgumentException { /* Need not check */ }
            /**Convert method parameter object into json string and append into string builder.
             @return string builder instance that is given in parameter (for chaining coding style use.) */
            @Override public StringBuilder toJson(StringBuilder strBuilder) { return strBuilder.append('"').append(_value).append('"'); }
            @Override public String toString() { return "\"" + _value + "\""; }
        }
        private ReferrerPolicy referrerPolicy;
        /**Whether is loaded via link preload.
        <em>Optional.</em>*/
        private Boolean isLinkPreload;
        public final Request url(String url) { this.url = url; return this; }
        public final Request setUrl(String url) { return url(url); }
        public final String url() { return url; }
        public final String getUrl() { return url(); }
        public final Request method(String method) { this.method = method; return this; }
        public final Request setMethod(String method) { return method(method); }
        public final String method() { return method; }
        public final String getMethod() { return method(); }
        public final Request headers(Headers headers) { this.headers = headers; return this; }
        public final Request setHeaders(Headers headers) { return headers(headers); }
        public final Headers headers() { return headers; }
        public final Headers getHeaders() { return headers(); }
        public final Request postData(@Nullable String postData) { this.postData = postData; return this; }
        public final Request optPostData(@Nullable String postData) { return postData(postData); }
        public final String postData() { return postData; }
        public final String getPostData() { return postData(); }
        public final Request hasPostData(@Nullable Boolean hasPostData) { this.hasPostData = hasPostData; return this; }
        public final Request optHasPostData(@Nullable Boolean hasPostData) { return hasPostData(hasPostData); }
        public final Boolean hasPostData() { return hasPostData; }
        public final Boolean getHasPostData() { return hasPostData(); }
        public final Request mixedContentType(@Nullable Security.MixedContentType mixedContentType) { this.mixedContentType = mixedContentType; return this; }
        public final Request optMixedContentType(@Nullable Security.MixedContentType mixedContentType) { return mixedContentType(mixedContentType); }
        public final Security.MixedContentType mixedContentType() { return mixedContentType; }
        public final Security.MixedContentType getMixedContentType() { return mixedContentType(); }
        public final Request initialPriority(ResourcePriority initialPriority) { this.initialPriority = initialPriority; return this; }
        public final Request setInitialPriority(ResourcePriority initialPriority) { return initialPriority(initialPriority); }
        public final ResourcePriority initialPriority() { return initialPriority; }
        public final ResourcePriority getInitialPriority() { return initialPriority(); }
        public final Request referrerPolicy(ReferrerPolicy referrerPolicy) { this.referrerPolicy = referrerPolicy; return this; }
        public final Request setReferrerPolicy(ReferrerPolicy referrerPolicy) { return referrerPolicy(referrerPolicy); }
        public final ReferrerPolicy referrerPolicy() { return referrerPolicy; }
        public final ReferrerPolicy getReferrerPolicy() { return referrerPolicy(); }
        public final Request isLinkPreload(@Nullable Boolean isLinkPreload) { this.isLinkPreload = isLinkPreload; return this; }
        public final Request optIsLinkPreload(@Nullable Boolean isLinkPreload) { return isLinkPreload(isLinkPreload); }
        public final Boolean isLinkPreload() { return isLinkPreload; }
        public final Boolean getIsLinkPreload() { return isLinkPreload(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (url == null) throw new IllegalArgumentException("Network.Request.url is necessary field.");
            if (method == null) throw new IllegalArgumentException("Network.Request.method is necessary field.");
            if (headers == null) throw new IllegalArgumentException("Network.Request.headers is necessary field.");
            if (initialPriority == null) throw new IllegalArgumentException("Network.Request.initialPriority is necessary field.");
            if (referrerPolicy == null) throw new IllegalArgumentException("Network.Request.referrerPolicy is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"url\":").append('"').append(DomainBase.escapeJson(url)).append('"');
            strBuilder.append(",\"method\":").append('"').append(DomainBase.escapeJson(method)).append('"');
            headers.toJson(strBuilder.append(",\"headers\":"));
            if (postData != null) strBuilder.append(",\"postData\":").append('"').append(DomainBase.escapeJson(postData)).append('"');
            if (hasPostData != null) strBuilder.append(",\"hasPostData\":").append(hasPostData);
            if (mixedContentType != null) mixedContentType.toJson(strBuilder.append(",\"mixedContentType\":"));
            initialPriority.toJson(strBuilder.append(",\"initialPriority\":"));
            strBuilder.append(",\"referrerPolicy\":").append(referrerPolicy);
            if (isLinkPreload != null) strBuilder.append(",\"isLinkPreload\":").append(isLinkPreload);
            strBuilder.append('}');
            return strBuilder;
        }
        public Request() {}
        public Request(
            @JsonProperty("url")String url,
            @JsonProperty("method")String method,
            @JsonProperty("headers")Headers headers,
            @Nullable @JsonProperty("postData")String postData,
            @Nullable @JsonProperty("hasPostData")Boolean hasPostData,
            @Nullable @JsonProperty("mixedContentType")Security.MixedContentType mixedContentType,
            @JsonProperty("initialPriority")ResourcePriority initialPriority,
            @JsonProperty("referrerPolicy")ReferrerPolicy referrerPolicy,
            @Nullable @JsonProperty("isLinkPreload")Boolean isLinkPreload
        ) {
            this.url = url;
            this.method = method;
            this.headers = headers;
            this.postData = postData;
            this.hasPostData = hasPostData;
            this.mixedContentType = mixedContentType;
            this.initialPriority = initialPriority;
            this.referrerPolicy = referrerPolicy;
            this.isLinkPreload = isLinkPreload;
        }
    }

    /**Details of a signed certificate timestamp (SCT).*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SignedCertificateTimestamp implements CommonDomainType {
        /**Validation status.*/
        private String status;
        /**Origin.*/
        private String origin;
        /**Log name / description.*/
        private String logDescription;
        /**Log ID.*/
        private String logId;
        /**Issuance date.*/
        private TimeSinceEpoch timestamp;
        /**Hash algorithm.*/
        private String hashAlgorithm;
        /**Signature algorithm.*/
        private String signatureAlgorithm;
        /**Signature data.*/
        private String signatureData;
        public final SignedCertificateTimestamp status(String status) { this.status = status; return this; }
        public final SignedCertificateTimestamp setStatus(String status) { return status(status); }
        public final String status() { return status; }
        public final String getStatus() { return status(); }
        public final SignedCertificateTimestamp origin(String origin) { this.origin = origin; return this; }
        public final SignedCertificateTimestamp setOrigin(String origin) { return origin(origin); }
        public final String origin() { return origin; }
        public final String getOrigin() { return origin(); }
        public final SignedCertificateTimestamp logDescription(String logDescription) { this.logDescription = logDescription; return this; }
        public final SignedCertificateTimestamp setLogDescription(String logDescription) { return logDescription(logDescription); }
        public final String logDescription() { return logDescription; }
        public final String getLogDescription() { return logDescription(); }
        public final SignedCertificateTimestamp logId(String logId) { this.logId = logId; return this; }
        public final SignedCertificateTimestamp setLogId(String logId) { return logId(logId); }
        public final String logId() { return logId; }
        public final String getLogId() { return logId(); }
        public final SignedCertificateTimestamp timestamp(TimeSinceEpoch timestamp) { this.timestamp = timestamp; return this; }
        public final SignedCertificateTimestamp setTimestamp(TimeSinceEpoch timestamp) { return timestamp(timestamp); }
        public final TimeSinceEpoch timestamp() { return timestamp; }
        public final TimeSinceEpoch getTimestamp() { return timestamp(); }
        public final SignedCertificateTimestamp hashAlgorithm(String hashAlgorithm) { this.hashAlgorithm = hashAlgorithm; return this; }
        public final SignedCertificateTimestamp setHashAlgorithm(String hashAlgorithm) { return hashAlgorithm(hashAlgorithm); }
        public final String hashAlgorithm() { return hashAlgorithm; }
        public final String getHashAlgorithm() { return hashAlgorithm(); }
        public final SignedCertificateTimestamp signatureAlgorithm(String signatureAlgorithm) { this.signatureAlgorithm = signatureAlgorithm; return this; }
        public final SignedCertificateTimestamp setSignatureAlgorithm(String signatureAlgorithm) { return signatureAlgorithm(signatureAlgorithm); }
        public final String signatureAlgorithm() { return signatureAlgorithm; }
        public final String getSignatureAlgorithm() { return signatureAlgorithm(); }
        public final SignedCertificateTimestamp signatureData(String signatureData) { this.signatureData = signatureData; return this; }
        public final SignedCertificateTimestamp setSignatureData(String signatureData) { return signatureData(signatureData); }
        public final String signatureData() { return signatureData; }
        public final String getSignatureData() { return signatureData(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (status == null) throw new IllegalArgumentException("Network.SignedCertificateTimestamp.status is necessary field.");
            if (origin == null) throw new IllegalArgumentException("Network.SignedCertificateTimestamp.origin is necessary field.");
            if (logDescription == null) throw new IllegalArgumentException("Network.SignedCertificateTimestamp.logDescription is necessary field.");
            if (logId == null) throw new IllegalArgumentException("Network.SignedCertificateTimestamp.logId is necessary field.");
            if (timestamp == null) throw new IllegalArgumentException("Network.SignedCertificateTimestamp.timestamp is necessary field.");
            if (hashAlgorithm == null) throw new IllegalArgumentException("Network.SignedCertificateTimestamp.hashAlgorithm is necessary field.");
            if (signatureAlgorithm == null) throw new IllegalArgumentException("Network.SignedCertificateTimestamp.signatureAlgorithm is necessary field.");
            if (signatureData == null) throw new IllegalArgumentException("Network.SignedCertificateTimestamp.signatureData is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"status\":").append('"').append(DomainBase.escapeJson(status)).append('"');
            strBuilder.append(",\"origin\":").append('"').append(DomainBase.escapeJson(origin)).append('"');
            strBuilder.append(",\"logDescription\":").append('"').append(DomainBase.escapeJson(logDescription)).append('"');
            strBuilder.append(",\"logId\":").append('"').append(DomainBase.escapeJson(logId)).append('"');
            timestamp.toJson(strBuilder.append(",\"timestamp\":"));
            strBuilder.append(",\"hashAlgorithm\":").append('"').append(DomainBase.escapeJson(hashAlgorithm)).append('"');
            strBuilder.append(",\"signatureAlgorithm\":").append('"').append(DomainBase.escapeJson(signatureAlgorithm)).append('"');
            strBuilder.append(",\"signatureData\":").append('"').append(DomainBase.escapeJson(signatureData)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public SignedCertificateTimestamp() {}
        public SignedCertificateTimestamp(
            @JsonProperty("status")String status,
            @JsonProperty("origin")String origin,
            @JsonProperty("logDescription")String logDescription,
            @JsonProperty("logId")String logId,
            @JsonProperty("timestamp")TimeSinceEpoch timestamp,
            @JsonProperty("hashAlgorithm")String hashAlgorithm,
            @JsonProperty("signatureAlgorithm")String signatureAlgorithm,
            @JsonProperty("signatureData")String signatureData
        ) {
            this.status = status;
            this.origin = origin;
            this.logDescription = logDescription;
            this.logId = logId;
            this.timestamp = timestamp;
            this.hashAlgorithm = hashAlgorithm;
            this.signatureAlgorithm = signatureAlgorithm;
            this.signatureData = signatureData;
        }
    }

    /**Security details about a request.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SecurityDetails implements CommonDomainType {
        /**Protocol name (e.g. "TLS 1.2" or "QUIC").*/
        private String protocol;
        /**Key Exchange used by the connection, or the empty string if not applicable.*/
        private String keyExchange;
        /**(EC)DH group used by the connection, if applicable.
        <em>Optional.</em>*/
        private String keyExchangeGroup;
        /**Cipher name.*/
        private String cipher;
        /**TLS MAC. Note that AEAD ciphers do not have separate MACs.
        <em>Optional.</em>*/
        private String mac;
        /**Certificate ID value.*/
        private Security.CertificateId certificateId;
        /**Certificate subject name.*/
        private String subjectName;
        /**Subject Alternative Name (SAN) DNS names and IP addresses.*/
        private List<String> sanList;
        /**Name of the issuing CA.*/
        private String issuer;
        /**Certificate valid from date.*/
        private TimeSinceEpoch validFrom;
        /**Certificate valid to (expiration) date*/
        private TimeSinceEpoch validTo;
        /**List of signed certificate timestamps (SCTs).*/
        private List<SignedCertificateTimestamp> signedCertificateTimestampList;
        /**Whether the request complied with Certificate Transparency policy*/
        private CertificateTransparencyCompliance certificateTransparencyCompliance;
        public final SecurityDetails protocol(String protocol) { this.protocol = protocol; return this; }
        public final SecurityDetails setProtocol(String protocol) { return protocol(protocol); }
        public final String protocol() { return protocol; }
        public final String getProtocol() { return protocol(); }
        public final SecurityDetails keyExchange(String keyExchange) { this.keyExchange = keyExchange; return this; }
        public final SecurityDetails setKeyExchange(String keyExchange) { return keyExchange(keyExchange); }
        public final String keyExchange() { return keyExchange; }
        public final String getKeyExchange() { return keyExchange(); }
        public final SecurityDetails keyExchangeGroup(@Nullable String keyExchangeGroup) { this.keyExchangeGroup = keyExchangeGroup; return this; }
        public final SecurityDetails optKeyExchangeGroup(@Nullable String keyExchangeGroup) { return keyExchangeGroup(keyExchangeGroup); }
        public final String keyExchangeGroup() { return keyExchangeGroup; }
        public final String getKeyExchangeGroup() { return keyExchangeGroup(); }
        public final SecurityDetails cipher(String cipher) { this.cipher = cipher; return this; }
        public final SecurityDetails setCipher(String cipher) { return cipher(cipher); }
        public final String cipher() { return cipher; }
        public final String getCipher() { return cipher(); }
        public final SecurityDetails mac(@Nullable String mac) { this.mac = mac; return this; }
        public final SecurityDetails optMac(@Nullable String mac) { return mac(mac); }
        public final String mac() { return mac; }
        public final String getMac() { return mac(); }
        public final SecurityDetails certificateId(Security.CertificateId certificateId) { this.certificateId = certificateId; return this; }
        public final SecurityDetails setCertificateId(Security.CertificateId certificateId) { return certificateId(certificateId); }
        public final Security.CertificateId certificateId() { return certificateId; }
        public final Security.CertificateId getCertificateId() { return certificateId(); }
        public final SecurityDetails subjectName(String subjectName) { this.subjectName = subjectName; return this; }
        public final SecurityDetails setSubjectName(String subjectName) { return subjectName(subjectName); }
        public final String subjectName() { return subjectName; }
        public final String getSubjectName() { return subjectName(); }
        public final SecurityDetails sanList(List<String> sanList) { this.sanList = sanList; return this; }
        public final SecurityDetails setSanList(List<String> sanList) { return sanList(sanList); }
        public final List<String> sanList() { return sanList; }
        public final List<String> getSanList() { return sanList(); }
        public final SecurityDetails issuer(String issuer) { this.issuer = issuer; return this; }
        public final SecurityDetails setIssuer(String issuer) { return issuer(issuer); }
        public final String issuer() { return issuer; }
        public final String getIssuer() { return issuer(); }
        public final SecurityDetails validFrom(TimeSinceEpoch validFrom) { this.validFrom = validFrom; return this; }
        public final SecurityDetails setValidFrom(TimeSinceEpoch validFrom) { return validFrom(validFrom); }
        public final TimeSinceEpoch validFrom() { return validFrom; }
        public final TimeSinceEpoch getValidFrom() { return validFrom(); }
        public final SecurityDetails validTo(TimeSinceEpoch validTo) { this.validTo = validTo; return this; }
        public final SecurityDetails setValidTo(TimeSinceEpoch validTo) { return validTo(validTo); }
        public final TimeSinceEpoch validTo() { return validTo; }
        public final TimeSinceEpoch getValidTo() { return validTo(); }
        public final SecurityDetails signedCertificateTimestampList(List<SignedCertificateTimestamp> signedCertificateTimestampList) { this.signedCertificateTimestampList = signedCertificateTimestampList; return this; }
        public final SecurityDetails setSignedCertificateTimestampList(List<SignedCertificateTimestamp> signedCertificateTimestampList) { return signedCertificateTimestampList(signedCertificateTimestampList); }
        public final List<SignedCertificateTimestamp> signedCertificateTimestampList() { return signedCertificateTimestampList; }
        public final List<SignedCertificateTimestamp> getSignedCertificateTimestampList() { return signedCertificateTimestampList(); }
        public final SecurityDetails certificateTransparencyCompliance(CertificateTransparencyCompliance certificateTransparencyCompliance) { this.certificateTransparencyCompliance = certificateTransparencyCompliance; return this; }
        public final SecurityDetails setCertificateTransparencyCompliance(CertificateTransparencyCompliance certificateTransparencyCompliance) { return certificateTransparencyCompliance(certificateTransparencyCompliance); }
        public final CertificateTransparencyCompliance certificateTransparencyCompliance() { return certificateTransparencyCompliance; }
        public final CertificateTransparencyCompliance getCertificateTransparencyCompliance() { return certificateTransparencyCompliance(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (protocol == null) throw new IllegalArgumentException("Network.SecurityDetails.protocol is necessary field.");
            if (keyExchange == null) throw new IllegalArgumentException("Network.SecurityDetails.keyExchange is necessary field.");
            if (cipher == null) throw new IllegalArgumentException("Network.SecurityDetails.cipher is necessary field.");
            if (certificateId == null) throw new IllegalArgumentException("Network.SecurityDetails.certificateId is necessary field.");
            if (subjectName == null) throw new IllegalArgumentException("Network.SecurityDetails.subjectName is necessary field.");
            if (sanList == null) throw new IllegalArgumentException("Network.SecurityDetails.sanList is necessary field.");
            if (issuer == null) throw new IllegalArgumentException("Network.SecurityDetails.issuer is necessary field.");
            if (validFrom == null) throw new IllegalArgumentException("Network.SecurityDetails.validFrom is necessary field.");
            if (validTo == null) throw new IllegalArgumentException("Network.SecurityDetails.validTo is necessary field.");
            if (signedCertificateTimestampList == null) throw new IllegalArgumentException("Network.SecurityDetails.signedCertificateTimestampList is necessary field.");
            if (certificateTransparencyCompliance == null) throw new IllegalArgumentException("Network.SecurityDetails.certificateTransparencyCompliance is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"protocol\":").append('"').append(DomainBase.escapeJson(protocol)).append('"');
            strBuilder.append(",\"keyExchange\":").append('"').append(DomainBase.escapeJson(keyExchange)).append('"');
            if (keyExchangeGroup != null) strBuilder.append(",\"keyExchangeGroup\":").append('"').append(DomainBase.escapeJson(keyExchangeGroup)).append('"');
            strBuilder.append(",\"cipher\":").append('"').append(DomainBase.escapeJson(cipher)).append('"');
            if (mac != null) strBuilder.append(",\"mac\":").append('"').append(DomainBase.escapeJson(mac)).append('"');
            certificateId.toJson(strBuilder.append(",\"certificateId\":"));
            strBuilder.append(",\"subjectName\":").append('"').append(DomainBase.escapeJson(subjectName)).append('"');
                        strBuilder.append(",\"sanList\":[");
            strBuilder.append('"').append(DomainBase.escapeJson(sanList.get(0))).append('"');
            for (int i = 1; i < sanList.size(); ++i)
                strBuilder.append(",\"").append(DomainBase.escapeJson(sanList.get(i))).append('"');
            strBuilder.append(']');
            strBuilder.append(",\"issuer\":").append('"').append(DomainBase.escapeJson(issuer)).append('"');
            validFrom.toJson(strBuilder.append(",\"validFrom\":"));
            validTo.toJson(strBuilder.append(",\"validTo\":"));
                        strBuilder.append(",\"signedCertificateTimestampList\":[");
            signedCertificateTimestampList.get(0).toJson(strBuilder);
            for (int i = 1; i < signedCertificateTimestampList.size(); ++i)
                signedCertificateTimestampList.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            certificateTransparencyCompliance.toJson(strBuilder.append(",\"certificateTransparencyCompliance\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public SecurityDetails() {}
        public SecurityDetails(
            @JsonProperty("protocol")String protocol,
            @JsonProperty("keyExchange")String keyExchange,
            @Nullable @JsonProperty("keyExchangeGroup")String keyExchangeGroup,
            @JsonProperty("cipher")String cipher,
            @Nullable @JsonProperty("mac")String mac,
            @JsonProperty("certificateId")Security.CertificateId certificateId,
            @JsonProperty("subjectName")String subjectName,
            @JsonProperty("sanList")List<String> sanList,
            @JsonProperty("issuer")String issuer,
            @JsonProperty("validFrom")TimeSinceEpoch validFrom,
            @JsonProperty("validTo")TimeSinceEpoch validTo,
            @JsonProperty("signedCertificateTimestampList")List<SignedCertificateTimestamp> signedCertificateTimestampList,
            @JsonProperty("certificateTransparencyCompliance")CertificateTransparencyCompliance certificateTransparencyCompliance
        ) {
            this.protocol = protocol;
            this.keyExchange = keyExchange;
            this.keyExchangeGroup = keyExchangeGroup;
            this.cipher = cipher;
            this.mac = mac;
            this.certificateId = certificateId;
            this.subjectName = subjectName;
            this.sanList = sanList;
            this.issuer = issuer;
            this.validFrom = validFrom;
            this.validTo = validTo;
            this.signedCertificateTimestampList = signedCertificateTimestampList;
            this.certificateTransparencyCompliance = certificateTransparencyCompliance;
        }
    }

    /**Whether the request complied with Certificate Transparency policy.*/
    @ParametersAreNonnullByDefault public enum CertificateTransparencyCompliance implements CommonDomainType {
        Unknown("unknown"),
        Not_compliant("not-compliant"),
        Compliant("compliant");

        private final String _value;
        /**Convert string representation to type.
         @throws IllegalArgumentException if given value cannot convert to enum type. */
        @JsonCreator public static CertificateTransparencyCompliance of(String value) {
            return Enum.valueOf(CertificateTransparencyCompliance.class, value.substring(0, 1).toUpperCase() + value.substring(1));
        }
        CertificateTransparencyCompliance(String value) { _value = value; }
        /**Check if parameter fields of method are all valid. */
        @Override public void check() throws IllegalArgumentException { /* Need not check */ }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) { return strBuilder.append('"').append(_value).append('"'); }
        @Override public String toString() { return "\"" + _value + "\""; }
    }

    /**The reason why request was blocked.*/
    @ParametersAreNonnullByDefault public enum BlockedReason implements CommonDomainType {
        Csp("csp"),
        Mixed_content("mixed-content"),
        Origin("origin"),
        Inspector("inspector"),
        Subresource_filter("subresource-filter"),
        Other("other");

        private final String _value;
        /**Convert string representation to type.
         @throws IllegalArgumentException if given value cannot convert to enum type. */
        @JsonCreator public static BlockedReason of(String value) {
            return Enum.valueOf(BlockedReason.class, value.substring(0, 1).toUpperCase() + value.substring(1));
        }
        BlockedReason(String value) { _value = value; }
        /**Check if parameter fields of method are all valid. */
        @Override public void check() throws IllegalArgumentException { /* Need not check */ }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) { return strBuilder.append('"').append(_value).append('"'); }
        @Override public String toString() { return "\"" + _value + "\""; }
    }

    /**HTTP response data.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class Response implements CommonDomainType {
        /**Response URL. This URL can be different from CachedResource.url in case of redirect.*/
        private String url;
        /**HTTP response status code.*/
        private Integer status;
        /**HTTP response status text.*/
        private String statusText;
        /**HTTP response headers.*/
        private Headers headers;
        /**HTTP response headers text.
        <em>Optional.</em>*/
        private String headersText;
        /**Resource mimeType as determined by the browser.*/
        private String mimeType;
        /**Refined HTTP request headers that were actually transmitted over the network.
        <em>Optional.</em>*/
        private Headers requestHeaders;
        /**HTTP request headers text.
        <em>Optional.</em>*/
        private String requestHeadersText;
        /**Specifies whether physical connection was actually reused for this request.*/
        private Boolean connectionReused;
        /**Physical connection id that was actually used for this request.*/
        private Double connectionId;
        /**Remote IP address.
        <em>Optional.</em>*/
        private String remoteIPAddress;
        /**Remote port.
        <em>Optional.</em>*/
        private Integer remotePort;
        /**Specifies that the request was served from the disk cache.
        <em>Optional.</em>*/
        private Boolean fromDiskCache;
        /**Specifies that the request was served from the ServiceWorker.
        <em>Optional.</em>*/
        private Boolean fromServiceWorker;
        /**Total number of bytes received for this request so far.*/
        private Double encodedDataLength;
        /**Timing information for the given request.
        <em>Optional.</em>*/
        private ResourceTiming timing;
        /**Protocol used to fetch this request.
        <em>Optional.</em>*/
        private String protocol;
        /**Security state of the request resource.*/
        private Security.SecurityState securityState;
        /**Security details for the request.
        <em>Optional.</em>*/
        private SecurityDetails securityDetails;
        public final Response url(String url) { this.url = url; return this; }
        public final Response setUrl(String url) { return url(url); }
        public final String url() { return url; }
        public final String getUrl() { return url(); }
        public final Response status(Integer status) { this.status = status; return this; }
        public final Response setStatus(Integer status) { return status(status); }
        public final Integer status() { return status; }
        public final Integer getStatus() { return status(); }
        public final Response statusText(String statusText) { this.statusText = statusText; return this; }
        public final Response setStatusText(String statusText) { return statusText(statusText); }
        public final String statusText() { return statusText; }
        public final String getStatusText() { return statusText(); }
        public final Response headers(Headers headers) { this.headers = headers; return this; }
        public final Response setHeaders(Headers headers) { return headers(headers); }
        public final Headers headers() { return headers; }
        public final Headers getHeaders() { return headers(); }
        public final Response headersText(@Nullable String headersText) { this.headersText = headersText; return this; }
        public final Response optHeadersText(@Nullable String headersText) { return headersText(headersText); }
        public final String headersText() { return headersText; }
        public final String getHeadersText() { return headersText(); }
        public final Response mimeType(String mimeType) { this.mimeType = mimeType; return this; }
        public final Response setMimeType(String mimeType) { return mimeType(mimeType); }
        public final String mimeType() { return mimeType; }
        public final String getMimeType() { return mimeType(); }
        public final Response requestHeaders(@Nullable Headers requestHeaders) { this.requestHeaders = requestHeaders; return this; }
        public final Response optRequestHeaders(@Nullable Headers requestHeaders) { return requestHeaders(requestHeaders); }
        public final Headers requestHeaders() { return requestHeaders; }
        public final Headers getRequestHeaders() { return requestHeaders(); }
        public final Response requestHeadersText(@Nullable String requestHeadersText) { this.requestHeadersText = requestHeadersText; return this; }
        public final Response optRequestHeadersText(@Nullable String requestHeadersText) { return requestHeadersText(requestHeadersText); }
        public final String requestHeadersText() { return requestHeadersText; }
        public final String getRequestHeadersText() { return requestHeadersText(); }
        public final Response connectionReused(Boolean connectionReused) { this.connectionReused = connectionReused; return this; }
        public final Response setConnectionReused(Boolean connectionReused) { return connectionReused(connectionReused); }
        public final Boolean connectionReused() { return connectionReused; }
        public final Boolean getConnectionReused() { return connectionReused(); }
        public final Response connectionId(Double connectionId) { this.connectionId = connectionId; return this; }
        public final Response setConnectionId(Double connectionId) { return connectionId(connectionId); }
        public final Double connectionId() { return connectionId; }
        public final Double getConnectionId() { return connectionId(); }
        public final Response remoteIPAddress(@Nullable String remoteIPAddress) { this.remoteIPAddress = remoteIPAddress; return this; }
        public final Response optRemoteIPAddress(@Nullable String remoteIPAddress) { return remoteIPAddress(remoteIPAddress); }
        public final String remoteIPAddress() { return remoteIPAddress; }
        public final String getRemoteIPAddress() { return remoteIPAddress(); }
        public final Response remotePort(@Nullable Integer remotePort) { this.remotePort = remotePort; return this; }
        public final Response optRemotePort(@Nullable Integer remotePort) { return remotePort(remotePort); }
        public final Integer remotePort() { return remotePort; }
        public final Integer getRemotePort() { return remotePort(); }
        public final Response fromDiskCache(@Nullable Boolean fromDiskCache) { this.fromDiskCache = fromDiskCache; return this; }
        public final Response optFromDiskCache(@Nullable Boolean fromDiskCache) { return fromDiskCache(fromDiskCache); }
        public final Boolean fromDiskCache() { return fromDiskCache; }
        public final Boolean getFromDiskCache() { return fromDiskCache(); }
        public final Response fromServiceWorker(@Nullable Boolean fromServiceWorker) { this.fromServiceWorker = fromServiceWorker; return this; }
        public final Response optFromServiceWorker(@Nullable Boolean fromServiceWorker) { return fromServiceWorker(fromServiceWorker); }
        public final Boolean fromServiceWorker() { return fromServiceWorker; }
        public final Boolean getFromServiceWorker() { return fromServiceWorker(); }
        public final Response encodedDataLength(Double encodedDataLength) { this.encodedDataLength = encodedDataLength; return this; }
        public final Response setEncodedDataLength(Double encodedDataLength) { return encodedDataLength(encodedDataLength); }
        public final Double encodedDataLength() { return encodedDataLength; }
        public final Double getEncodedDataLength() { return encodedDataLength(); }
        public final Response timing(@Nullable ResourceTiming timing) { this.timing = timing; return this; }
        public final Response optTiming(@Nullable ResourceTiming timing) { return timing(timing); }
        public final ResourceTiming timing() { return timing; }
        public final ResourceTiming getTiming() { return timing(); }
        public final Response protocol(@Nullable String protocol) { this.protocol = protocol; return this; }
        public final Response optProtocol(@Nullable String protocol) { return protocol(protocol); }
        public final String protocol() { return protocol; }
        public final String getProtocol() { return protocol(); }
        public final Response securityState(Security.SecurityState securityState) { this.securityState = securityState; return this; }
        public final Response setSecurityState(Security.SecurityState securityState) { return securityState(securityState); }
        public final Security.SecurityState securityState() { return securityState; }
        public final Security.SecurityState getSecurityState() { return securityState(); }
        public final Response securityDetails(@Nullable SecurityDetails securityDetails) { this.securityDetails = securityDetails; return this; }
        public final Response optSecurityDetails(@Nullable SecurityDetails securityDetails) { return securityDetails(securityDetails); }
        public final SecurityDetails securityDetails() { return securityDetails; }
        public final SecurityDetails getSecurityDetails() { return securityDetails(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (url == null) throw new IllegalArgumentException("Network.Response.url is necessary field.");
            if (status == null) throw new IllegalArgumentException("Network.Response.status is necessary field.");
            if (statusText == null) throw new IllegalArgumentException("Network.Response.statusText is necessary field.");
            if (headers == null) throw new IllegalArgumentException("Network.Response.headers is necessary field.");
            if (mimeType == null) throw new IllegalArgumentException("Network.Response.mimeType is necessary field.");
            if (connectionReused == null) throw new IllegalArgumentException("Network.Response.connectionReused is necessary field.");
            if (connectionId == null) throw new IllegalArgumentException("Network.Response.connectionId is necessary field.");
            if (encodedDataLength == null) throw new IllegalArgumentException("Network.Response.encodedDataLength is necessary field.");
            if (securityState == null) throw new IllegalArgumentException("Network.Response.securityState is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"url\":").append('"').append(DomainBase.escapeJson(url)).append('"');
            strBuilder.append(",\"status\":").append(status);
            strBuilder.append(",\"statusText\":").append('"').append(DomainBase.escapeJson(statusText)).append('"');
            headers.toJson(strBuilder.append(",\"headers\":"));
            if (headersText != null) strBuilder.append(",\"headersText\":").append('"').append(DomainBase.escapeJson(headersText)).append('"');
            strBuilder.append(",\"mimeType\":").append('"').append(DomainBase.escapeJson(mimeType)).append('"');
            if (requestHeaders != null) requestHeaders.toJson(strBuilder.append(",\"requestHeaders\":"));
            if (requestHeadersText != null) strBuilder.append(",\"requestHeadersText\":").append('"').append(DomainBase.escapeJson(requestHeadersText)).append('"');
            strBuilder.append(",\"connectionReused\":").append(connectionReused);
            strBuilder.append(",\"connectionId\":").append(connectionId);
            if (remoteIPAddress != null) strBuilder.append(",\"remoteIPAddress\":").append('"').append(DomainBase.escapeJson(remoteIPAddress)).append('"');
            if (remotePort != null) strBuilder.append(",\"remotePort\":").append(remotePort);
            if (fromDiskCache != null) strBuilder.append(",\"fromDiskCache\":").append(fromDiskCache);
            if (fromServiceWorker != null) strBuilder.append(",\"fromServiceWorker\":").append(fromServiceWorker);
            strBuilder.append(",\"encodedDataLength\":").append(encodedDataLength);
            if (timing != null) timing.toJson(strBuilder.append(",\"timing\":"));
            if (protocol != null) strBuilder.append(",\"protocol\":").append('"').append(DomainBase.escapeJson(protocol)).append('"');
            securityState.toJson(strBuilder.append(",\"securityState\":"));
            if (securityDetails != null) securityDetails.toJson(strBuilder.append(",\"securityDetails\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public Response() {}
        public Response(
            @JsonProperty("url")String url,
            @JsonProperty("status")Integer status,
            @JsonProperty("statusText")String statusText,
            @JsonProperty("headers")Headers headers,
            @Nullable @JsonProperty("headersText")String headersText,
            @JsonProperty("mimeType")String mimeType,
            @Nullable @JsonProperty("requestHeaders")Headers requestHeaders,
            @Nullable @JsonProperty("requestHeadersText")String requestHeadersText,
            @JsonProperty("connectionReused")Boolean connectionReused,
            @JsonProperty("connectionId")Double connectionId,
            @Nullable @JsonProperty("remoteIPAddress")String remoteIPAddress,
            @Nullable @JsonProperty("remotePort")Integer remotePort,
            @Nullable @JsonProperty("fromDiskCache")Boolean fromDiskCache,
            @Nullable @JsonProperty("fromServiceWorker")Boolean fromServiceWorker,
            @JsonProperty("encodedDataLength")Double encodedDataLength,
            @Nullable @JsonProperty("timing")ResourceTiming timing,
            @Nullable @JsonProperty("protocol")String protocol,
            @JsonProperty("securityState")Security.SecurityState securityState,
            @Nullable @JsonProperty("securityDetails")SecurityDetails securityDetails
        ) {
            this.url = url;
            this.status = status;
            this.statusText = statusText;
            this.headers = headers;
            this.headersText = headersText;
            this.mimeType = mimeType;
            this.requestHeaders = requestHeaders;
            this.requestHeadersText = requestHeadersText;
            this.connectionReused = connectionReused;
            this.connectionId = connectionId;
            this.remoteIPAddress = remoteIPAddress;
            this.remotePort = remotePort;
            this.fromDiskCache = fromDiskCache;
            this.fromServiceWorker = fromServiceWorker;
            this.encodedDataLength = encodedDataLength;
            this.timing = timing;
            this.protocol = protocol;
            this.securityState = securityState;
            this.securityDetails = securityDetails;
        }
    }

    /**WebSocket request data.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class WebSocketRequest implements CommonDomainType {
        /**HTTP request headers.*/
        private Headers headers;
        public final WebSocketRequest headers(Headers headers) { this.headers = headers; return this; }
        public final WebSocketRequest setHeaders(Headers headers) { return headers(headers); }
        public final Headers headers() { return headers; }
        public final Headers getHeaders() { return headers(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (headers == null) throw new IllegalArgumentException("Network.WebSocketRequest.headers is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            headers.toJson(strBuilder.append("\"headers\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public WebSocketRequest() {}
        public WebSocketRequest(
            @JsonProperty("headers")Headers headers
        ) {
            this.headers = headers;
        }
    }

    /**WebSocket response data.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class WebSocketResponse implements CommonDomainType {
        /**HTTP response status code.*/
        private Integer status;
        /**HTTP response status text.*/
        private String statusText;
        /**HTTP response headers.*/
        private Headers headers;
        /**HTTP response headers text.
        <em>Optional.</em>*/
        private String headersText;
        /**HTTP request headers.
        <em>Optional.</em>*/
        private Headers requestHeaders;
        /**HTTP request headers text.
        <em>Optional.</em>*/
        private String requestHeadersText;
        public final WebSocketResponse status(Integer status) { this.status = status; return this; }
        public final WebSocketResponse setStatus(Integer status) { return status(status); }
        public final Integer status() { return status; }
        public final Integer getStatus() { return status(); }
        public final WebSocketResponse statusText(String statusText) { this.statusText = statusText; return this; }
        public final WebSocketResponse setStatusText(String statusText) { return statusText(statusText); }
        public final String statusText() { return statusText; }
        public final String getStatusText() { return statusText(); }
        public final WebSocketResponse headers(Headers headers) { this.headers = headers; return this; }
        public final WebSocketResponse setHeaders(Headers headers) { return headers(headers); }
        public final Headers headers() { return headers; }
        public final Headers getHeaders() { return headers(); }
        public final WebSocketResponse headersText(@Nullable String headersText) { this.headersText = headersText; return this; }
        public final WebSocketResponse optHeadersText(@Nullable String headersText) { return headersText(headersText); }
        public final String headersText() { return headersText; }
        public final String getHeadersText() { return headersText(); }
        public final WebSocketResponse requestHeaders(@Nullable Headers requestHeaders) { this.requestHeaders = requestHeaders; return this; }
        public final WebSocketResponse optRequestHeaders(@Nullable Headers requestHeaders) { return requestHeaders(requestHeaders); }
        public final Headers requestHeaders() { return requestHeaders; }
        public final Headers getRequestHeaders() { return requestHeaders(); }
        public final WebSocketResponse requestHeadersText(@Nullable String requestHeadersText) { this.requestHeadersText = requestHeadersText; return this; }
        public final WebSocketResponse optRequestHeadersText(@Nullable String requestHeadersText) { return requestHeadersText(requestHeadersText); }
        public final String requestHeadersText() { return requestHeadersText; }
        public final String getRequestHeadersText() { return requestHeadersText(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (status == null) throw new IllegalArgumentException("Network.WebSocketResponse.status is necessary field.");
            if (statusText == null) throw new IllegalArgumentException("Network.WebSocketResponse.statusText is necessary field.");
            if (headers == null) throw new IllegalArgumentException("Network.WebSocketResponse.headers is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"status\":").append(status);
            strBuilder.append(",\"statusText\":").append('"').append(DomainBase.escapeJson(statusText)).append('"');
            headers.toJson(strBuilder.append(",\"headers\":"));
            if (headersText != null) strBuilder.append(",\"headersText\":").append('"').append(DomainBase.escapeJson(headersText)).append('"');
            if (requestHeaders != null) requestHeaders.toJson(strBuilder.append(",\"requestHeaders\":"));
            if (requestHeadersText != null) strBuilder.append(",\"requestHeadersText\":").append('"').append(DomainBase.escapeJson(requestHeadersText)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public WebSocketResponse() {}
        public WebSocketResponse(
            @JsonProperty("status")Integer status,
            @JsonProperty("statusText")String statusText,
            @JsonProperty("headers")Headers headers,
            @Nullable @JsonProperty("headersText")String headersText,
            @Nullable @JsonProperty("requestHeaders")Headers requestHeaders,
            @Nullable @JsonProperty("requestHeadersText")String requestHeadersText
        ) {
            this.status = status;
            this.statusText = statusText;
            this.headers = headers;
            this.headersText = headersText;
            this.requestHeaders = requestHeaders;
            this.requestHeadersText = requestHeadersText;
        }
    }

    /**WebSocket frame data.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class WebSocketFrame implements CommonDomainType {
        /**WebSocket frame opcode.*/
        private Double opcode;
        /**WebSocke frame mask.*/
        private Boolean mask;
        /**WebSocke frame payload data.*/
        private String payloadData;
        public final WebSocketFrame opcode(Double opcode) { this.opcode = opcode; return this; }
        public final WebSocketFrame setOpcode(Double opcode) { return opcode(opcode); }
        public final Double opcode() { return opcode; }
        public final Double getOpcode() { return opcode(); }
        public final WebSocketFrame mask(Boolean mask) { this.mask = mask; return this; }
        public final WebSocketFrame setMask(Boolean mask) { return mask(mask); }
        public final Boolean mask() { return mask; }
        public final Boolean getMask() { return mask(); }
        public final WebSocketFrame payloadData(String payloadData) { this.payloadData = payloadData; return this; }
        public final WebSocketFrame setPayloadData(String payloadData) { return payloadData(payloadData); }
        public final String payloadData() { return payloadData; }
        public final String getPayloadData() { return payloadData(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (opcode == null) throw new IllegalArgumentException("Network.WebSocketFrame.opcode is necessary field.");
            if (mask == null) throw new IllegalArgumentException("Network.WebSocketFrame.mask is necessary field.");
            if (payloadData == null) throw new IllegalArgumentException("Network.WebSocketFrame.payloadData is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"opcode\":").append(opcode);
            strBuilder.append(",\"mask\":").append(mask);
            strBuilder.append(",\"payloadData\":").append('"').append(DomainBase.escapeJson(payloadData)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public WebSocketFrame() {}
        public WebSocketFrame(
            @JsonProperty("opcode")Double opcode,
            @JsonProperty("mask")Boolean mask,
            @JsonProperty("payloadData")String payloadData
        ) {
            this.opcode = opcode;
            this.mask = mask;
            this.payloadData = payloadData;
        }
    }

    /**Information about the cached resource.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class CachedResource implements CommonDomainType {
        /**Resource URL. This is the url of the original network request.*/
        private String url;
        /**Type of this resource.*/
        private Page.ResourceType type;
        /**Cached response data.
        <em>Optional.</em>*/
        private Response response;
        /**Cached response body size.*/
        private Double bodySize;
        public final CachedResource url(String url) { this.url = url; return this; }
        public final CachedResource setUrl(String url) { return url(url); }
        public final String url() { return url; }
        public final String getUrl() { return url(); }
        public final CachedResource type(Page.ResourceType type) { this.type = type; return this; }
        public final CachedResource setType(Page.ResourceType type) { return type(type); }
        public final Page.ResourceType type() { return type; }
        public final Page.ResourceType getType() { return type(); }
        public final CachedResource response(@Nullable Response response) { this.response = response; return this; }
        public final CachedResource optResponse(@Nullable Response response) { return response(response); }
        public final Response response() { return response; }
        public final Response getResponse() { return response(); }
        public final CachedResource bodySize(Double bodySize) { this.bodySize = bodySize; return this; }
        public final CachedResource setBodySize(Double bodySize) { return bodySize(bodySize); }
        public final Double bodySize() { return bodySize; }
        public final Double getBodySize() { return bodySize(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (url == null) throw new IllegalArgumentException("Network.CachedResource.url is necessary field.");
            if (type == null) throw new IllegalArgumentException("Network.CachedResource.type is necessary field.");
            if (bodySize == null) throw new IllegalArgumentException("Network.CachedResource.bodySize is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"url\":").append('"').append(DomainBase.escapeJson(url)).append('"');
            type.toJson(strBuilder.append(",\"type\":"));
            if (response != null) response.toJson(strBuilder.append(",\"response\":"));
            strBuilder.append(",\"bodySize\":").append(bodySize);
            strBuilder.append('}');
            return strBuilder;
        }
        public CachedResource() {}
        public CachedResource(
            @JsonProperty("url")String url,
            @JsonProperty("type")Page.ResourceType type,
            @Nullable @JsonProperty("response")Response response,
            @JsonProperty("bodySize")Double bodySize
        ) {
            this.url = url;
            this.type = type;
            this.response = response;
            this.bodySize = bodySize;
        }
    }

    /**Information about the request initiator.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class Initiator implements CommonDomainType {
        /**Type of this initiator.*/
        @ParametersAreNonnullByDefault public enum Type implements CommonDomainType {
            Parser("parser"),
            Script("script"),
            Preload("preload"),
            Other("other");

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
        /**Initiator JavaScript stack trace, set for Script only.
        <em>Optional.</em>*/
        private Runtime.StackTrace stack;
        /**Initiator URL, set for Parser type or for Script type (when script is importing module).
        <em>Optional.</em>*/
        private String url;
        /**Initiator line number, set for Parser type or for Script type (when script is importing
module) (0-based).
        <em>Optional.</em>*/
        private Double lineNumber;
        public final Initiator type(Type type) { this.type = type; return this; }
        public final Initiator setType(Type type) { return type(type); }
        public final Type type() { return type; }
        public final Type getType() { return type(); }
        public final Initiator stack(@Nullable Runtime.StackTrace stack) { this.stack = stack; return this; }
        public final Initiator optStack(@Nullable Runtime.StackTrace stack) { return stack(stack); }
        public final Runtime.StackTrace stack() { return stack; }
        public final Runtime.StackTrace getStack() { return stack(); }
        public final Initiator url(@Nullable String url) { this.url = url; return this; }
        public final Initiator optUrl(@Nullable String url) { return url(url); }
        public final String url() { return url; }
        public final String getUrl() { return url(); }
        public final Initiator lineNumber(@Nullable Double lineNumber) { this.lineNumber = lineNumber; return this; }
        public final Initiator optLineNumber(@Nullable Double lineNumber) { return lineNumber(lineNumber); }
        public final Double lineNumber() { return lineNumber; }
        public final Double getLineNumber() { return lineNumber(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (type == null) throw new IllegalArgumentException("Network.Initiator.type is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"type\":").append(type);
            if (stack != null) stack.toJson(strBuilder.append(",\"stack\":"));
            if (url != null) strBuilder.append(",\"url\":").append('"').append(DomainBase.escapeJson(url)).append('"');
            if (lineNumber != null) strBuilder.append(",\"lineNumber\":").append(lineNumber);
            strBuilder.append('}');
            return strBuilder;
        }
        public Initiator() {}
        public Initiator(
            @JsonProperty("type")Type type,
            @Nullable @JsonProperty("stack")Runtime.StackTrace stack,
            @Nullable @JsonProperty("url")String url,
            @Nullable @JsonProperty("lineNumber")Double lineNumber
        ) {
            this.type = type;
            this.stack = stack;
            this.url = url;
            this.lineNumber = lineNumber;
        }
    }

    /**Cookie object*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class Cookie implements CommonDomainType {
        /**Cookie name.*/
        private String name;
        /**Cookie value.*/
        private String value;
        /**Cookie domain.*/
        private String domain;
        /**Cookie path.*/
        private String path;
        /**Cookie expiration date as the number of seconds since the UNIX epoch.*/
        private Double expires;
        /**Cookie size.*/
        private Integer size;
        /**True if cookie is http-only.*/
        private Boolean httpOnly;
        /**True if cookie is secure.*/
        private Boolean secure;
        /**True in case of session cookie.*/
        private Boolean session;
        /**Cookie SameSite type.
        <em>Optional.</em>*/
        private CookieSameSite sameSite;
        public final Cookie name(String name) { this.name = name; return this; }
        public final Cookie setName(String name) { return name(name); }
        public final String name() { return name; }
        public final String getName() { return name(); }
        public final Cookie value(String value) { this.value = value; return this; }
        public final Cookie setValue(String value) { return value(value); }
        public final String value() { return value; }
        public final String getValue() { return value(); }
        public final Cookie domain(String domain) { this.domain = domain; return this; }
        public final Cookie setDomain(String domain) { return domain(domain); }
        public final String domain() { return domain; }
        public final String getDomain() { return domain(); }
        public final Cookie path(String path) { this.path = path; return this; }
        public final Cookie setPath(String path) { return path(path); }
        public final String path() { return path; }
        public final String getPath() { return path(); }
        public final Cookie expires(Double expires) { this.expires = expires; return this; }
        public final Cookie setExpires(Double expires) { return expires(expires); }
        public final Double expires() { return expires; }
        public final Double getExpires() { return expires(); }
        public final Cookie size(Integer size) { this.size = size; return this; }
        public final Cookie setSize(Integer size) { return size(size); }
        public final Integer size() { return size; }
        public final Integer getSize() { return size(); }
        public final Cookie httpOnly(Boolean httpOnly) { this.httpOnly = httpOnly; return this; }
        public final Cookie setHttpOnly(Boolean httpOnly) { return httpOnly(httpOnly); }
        public final Boolean httpOnly() { return httpOnly; }
        public final Boolean getHttpOnly() { return httpOnly(); }
        public final Cookie secure(Boolean secure) { this.secure = secure; return this; }
        public final Cookie setSecure(Boolean secure) { return secure(secure); }
        public final Boolean secure() { return secure; }
        public final Boolean getSecure() { return secure(); }
        public final Cookie session(Boolean session) { this.session = session; return this; }
        public final Cookie setSession(Boolean session) { return session(session); }
        public final Boolean session() { return session; }
        public final Boolean getSession() { return session(); }
        public final Cookie sameSite(@Nullable CookieSameSite sameSite) { this.sameSite = sameSite; return this; }
        public final Cookie optSameSite(@Nullable CookieSameSite sameSite) { return sameSite(sameSite); }
        public final CookieSameSite sameSite() { return sameSite; }
        public final CookieSameSite getSameSite() { return sameSite(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (name == null) throw new IllegalArgumentException("Network.Cookie.name is necessary field.");
            if (value == null) throw new IllegalArgumentException("Network.Cookie.value is necessary field.");
            if (domain == null) throw new IllegalArgumentException("Network.Cookie.domain is necessary field.");
            if (path == null) throw new IllegalArgumentException("Network.Cookie.path is necessary field.");
            if (expires == null) throw new IllegalArgumentException("Network.Cookie.expires is necessary field.");
            if (size == null) throw new IllegalArgumentException("Network.Cookie.size is necessary field.");
            if (httpOnly == null) throw new IllegalArgumentException("Network.Cookie.httpOnly is necessary field.");
            if (secure == null) throw new IllegalArgumentException("Network.Cookie.secure is necessary field.");
            if (session == null) throw new IllegalArgumentException("Network.Cookie.session is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"name\":").append('"').append(DomainBase.escapeJson(name)).append('"');
            strBuilder.append(",\"value\":").append('"').append(DomainBase.escapeJson(value)).append('"');
            strBuilder.append(",\"domain\":").append('"').append(DomainBase.escapeJson(domain)).append('"');
            strBuilder.append(",\"path\":").append('"').append(DomainBase.escapeJson(path)).append('"');
            strBuilder.append(",\"expires\":").append(expires);
            strBuilder.append(",\"size\":").append(size);
            strBuilder.append(",\"httpOnly\":").append(httpOnly);
            strBuilder.append(",\"secure\":").append(secure);
            strBuilder.append(",\"session\":").append(session);
            if (sameSite != null) sameSite.toJson(strBuilder.append(",\"sameSite\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public Cookie() {}
        public Cookie(
            @JsonProperty("name")String name,
            @JsonProperty("value")String value,
            @JsonProperty("domain")String domain,
            @JsonProperty("path")String path,
            @JsonProperty("expires")Double expires,
            @JsonProperty("size")Integer size,
            @JsonProperty("httpOnly")Boolean httpOnly,
            @JsonProperty("secure")Boolean secure,
            @JsonProperty("session")Boolean session,
            @Nullable @JsonProperty("sameSite")CookieSameSite sameSite
        ) {
            this.name = name;
            this.value = value;
            this.domain = domain;
            this.path = path;
            this.expires = expires;
            this.size = size;
            this.httpOnly = httpOnly;
            this.secure = secure;
            this.session = session;
            this.sameSite = sameSite;
        }
    }

    /**Cookie parameter object*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class CookieParam implements CommonDomainType {
        /**Cookie name.*/
        private String name;
        /**Cookie value.*/
        private String value;
        /**The request-URI to associate with the setting of the cookie. This value can affect the
default domain and path values of the created cookie.
        <em>Optional.</em>*/
        private String url;
        /**Cookie domain.
        <em>Optional.</em>*/
        private String domain;
        /**Cookie path.
        <em>Optional.</em>*/
        private String path;
        /**True if cookie is secure.
        <em>Optional.</em>*/
        private Boolean secure;
        /**True if cookie is http-only.
        <em>Optional.</em>*/
        private Boolean httpOnly;
        /**Cookie SameSite type.
        <em>Optional.</em>*/
        private CookieSameSite sameSite;
        /**Cookie expiration date, session cookie if not set
        <em>Optional.</em>*/
        private TimeSinceEpoch expires;
        public final CookieParam name(String name) { this.name = name; return this; }
        public final CookieParam setName(String name) { return name(name); }
        public final String name() { return name; }
        public final String getName() { return name(); }
        public final CookieParam value(String value) { this.value = value; return this; }
        public final CookieParam setValue(String value) { return value(value); }
        public final String value() { return value; }
        public final String getValue() { return value(); }
        public final CookieParam url(@Nullable String url) { this.url = url; return this; }
        public final CookieParam optUrl(@Nullable String url) { return url(url); }
        public final String url() { return url; }
        public final String getUrl() { return url(); }
        public final CookieParam domain(@Nullable String domain) { this.domain = domain; return this; }
        public final CookieParam optDomain(@Nullable String domain) { return domain(domain); }
        public final String domain() { return domain; }
        public final String getDomain() { return domain(); }
        public final CookieParam path(@Nullable String path) { this.path = path; return this; }
        public final CookieParam optPath(@Nullable String path) { return path(path); }
        public final String path() { return path; }
        public final String getPath() { return path(); }
        public final CookieParam secure(@Nullable Boolean secure) { this.secure = secure; return this; }
        public final CookieParam optSecure(@Nullable Boolean secure) { return secure(secure); }
        public final Boolean secure() { return secure; }
        public final Boolean getSecure() { return secure(); }
        public final CookieParam httpOnly(@Nullable Boolean httpOnly) { this.httpOnly = httpOnly; return this; }
        public final CookieParam optHttpOnly(@Nullable Boolean httpOnly) { return httpOnly(httpOnly); }
        public final Boolean httpOnly() { return httpOnly; }
        public final Boolean getHttpOnly() { return httpOnly(); }
        public final CookieParam sameSite(@Nullable CookieSameSite sameSite) { this.sameSite = sameSite; return this; }
        public final CookieParam optSameSite(@Nullable CookieSameSite sameSite) { return sameSite(sameSite); }
        public final CookieSameSite sameSite() { return sameSite; }
        public final CookieSameSite getSameSite() { return sameSite(); }
        public final CookieParam expires(@Nullable TimeSinceEpoch expires) { this.expires = expires; return this; }
        public final CookieParam optExpires(@Nullable TimeSinceEpoch expires) { return expires(expires); }
        public final TimeSinceEpoch expires() { return expires; }
        public final TimeSinceEpoch getExpires() { return expires(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (name == null) throw new IllegalArgumentException("Network.CookieParam.name is necessary field.");
            if (value == null) throw new IllegalArgumentException("Network.CookieParam.value is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"name\":").append('"').append(DomainBase.escapeJson(name)).append('"');
            strBuilder.append(",\"value\":").append('"').append(DomainBase.escapeJson(value)).append('"');
            if (url != null) strBuilder.append(",\"url\":").append('"').append(DomainBase.escapeJson(url)).append('"');
            if (domain != null) strBuilder.append(",\"domain\":").append('"').append(DomainBase.escapeJson(domain)).append('"');
            if (path != null) strBuilder.append(",\"path\":").append('"').append(DomainBase.escapeJson(path)).append('"');
            if (secure != null) strBuilder.append(",\"secure\":").append(secure);
            if (httpOnly != null) strBuilder.append(",\"httpOnly\":").append(httpOnly);
            if (sameSite != null) sameSite.toJson(strBuilder.append(",\"sameSite\":"));
            if (expires != null) expires.toJson(strBuilder.append(",\"expires\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public CookieParam() {}
        public CookieParam(
            @JsonProperty("name")String name,
            @JsonProperty("value")String value,
            @Nullable @JsonProperty("url")String url,
            @Nullable @JsonProperty("domain")String domain,
            @Nullable @JsonProperty("path")String path,
            @Nullable @JsonProperty("secure")Boolean secure,
            @Nullable @JsonProperty("httpOnly")Boolean httpOnly,
            @Nullable @JsonProperty("sameSite")CookieSameSite sameSite,
            @Nullable @JsonProperty("expires")TimeSinceEpoch expires
        ) {
            this.name = name;
            this.value = value;
            this.url = url;
            this.domain = domain;
            this.path = path;
            this.secure = secure;
            this.httpOnly = httpOnly;
            this.sameSite = sameSite;
            this.expires = expires;
        }
    }

    /**Authorization challenge for HTTP status code 401 or 407.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class AuthChallenge implements CommonDomainType {
        /**Source of the authentication challenge.
        <em>Optional.</em>*/
        @ParametersAreNonnullByDefault public enum Source implements CommonDomainType {
            Server("Server"),
            Proxy("Proxy");

            private final String _value;
            /**Convert string representation to type.
             @throws IllegalArgumentException if given value cannot convert to enum type. */
            @JsonCreator public static Source of(String value) {
                return Enum.valueOf(Source.class, value.substring(0, 1).toUpperCase() + value.substring(1));
            }
            Source(String value) { _value = value; }
            /**Check if parameter fields of method are all valid. */
            @Override public void check() throws IllegalArgumentException { /* Need not check */ }
            /**Convert method parameter object into json string and append into string builder.
             @return string builder instance that is given in parameter (for chaining coding style use.) */
            @Override public StringBuilder toJson(StringBuilder strBuilder) { return strBuilder.append('"').append(_value).append('"'); }
            @Override public String toString() { return "\"" + _value + "\""; }
        }
        private Source source;
        /**Origin of the challenger.*/
        private String origin;
        /**The authentication scheme used, such as basic or digest*/
        private String scheme;
        /**The realm of the challenge. May be empty.*/
        private String realm;
        public final AuthChallenge source(@Nullable Source source) { this.source = source; return this; }
        public final AuthChallenge optSource(@Nullable Source source) { return source(source); }
        public final Source source() { return source; }
        public final Source getSource() { return source(); }
        public final AuthChallenge origin(String origin) { this.origin = origin; return this; }
        public final AuthChallenge setOrigin(String origin) { return origin(origin); }
        public final String origin() { return origin; }
        public final String getOrigin() { return origin(); }
        public final AuthChallenge scheme(String scheme) { this.scheme = scheme; return this; }
        public final AuthChallenge setScheme(String scheme) { return scheme(scheme); }
        public final String scheme() { return scheme; }
        public final String getScheme() { return scheme(); }
        public final AuthChallenge realm(String realm) { this.realm = realm; return this; }
        public final AuthChallenge setRealm(String realm) { return realm(realm); }
        public final String realm() { return realm; }
        public final String getRealm() { return realm(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (origin == null) throw new IllegalArgumentException("Network.AuthChallenge.origin is necessary field.");
            if (scheme == null) throw new IllegalArgumentException("Network.AuthChallenge.scheme is necessary field.");
            if (realm == null) throw new IllegalArgumentException("Network.AuthChallenge.realm is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            if (source != null) strBuilder.append("\"source\":").append(source);
            strBuilder.append(",\"origin\":").append('"').append(DomainBase.escapeJson(origin)).append('"');
            strBuilder.append(",\"scheme\":").append('"').append(DomainBase.escapeJson(scheme)).append('"');
            strBuilder.append(",\"realm\":").append('"').append(DomainBase.escapeJson(realm)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public AuthChallenge() {}
        public AuthChallenge(
            @Nullable @JsonProperty("source")Source source,
            @JsonProperty("origin")String origin,
            @JsonProperty("scheme")String scheme,
            @JsonProperty("realm")String realm
        ) {
            this.source = source;
            this.origin = origin;
            this.scheme = scheme;
            this.realm = realm;
        }
    }

    /**Response to an AuthChallenge.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class AuthChallengeResponse implements CommonDomainType {
        /**The decision on what to do in response to the authorization challenge.  Default means
deferring to the default behavior of the net stack, which will likely either the Cancel
authentication or display a popup dialog box.*/
        @ParametersAreNonnullByDefault public enum Response implements CommonDomainType {
            Default("Default"),
            CancelAuth("CancelAuth"),
            ProvideCredentials("ProvideCredentials");

            private final String _value;
            /**Convert string representation to type.
             @throws IllegalArgumentException if given value cannot convert to enum type. */
            @JsonCreator public static Response of(String value) {
                return Enum.valueOf(Response.class, value.substring(0, 1).toUpperCase() + value.substring(1));
            }
            Response(String value) { _value = value; }
            /**Check if parameter fields of method are all valid. */
            @Override public void check() throws IllegalArgumentException { /* Need not check */ }
            /**Convert method parameter object into json string and append into string builder.
             @return string builder instance that is given in parameter (for chaining coding style use.) */
            @Override public StringBuilder toJson(StringBuilder strBuilder) { return strBuilder.append('"').append(_value).append('"'); }
            @Override public String toString() { return "\"" + _value + "\""; }
        }
        private Response response;
        /**The username to provide, possibly empty. Should only be set if response is
ProvideCredentials.
        <em>Optional.</em>*/
        private String username;
        /**The password to provide, possibly empty. Should only be set if response is
ProvideCredentials.
        <em>Optional.</em>*/
        private String password;
        public final AuthChallengeResponse response(Response response) { this.response = response; return this; }
        public final AuthChallengeResponse setResponse(Response response) { return response(response); }
        public final Response response() { return response; }
        public final Response getResponse() { return response(); }
        public final AuthChallengeResponse username(@Nullable String username) { this.username = username; return this; }
        public final AuthChallengeResponse optUsername(@Nullable String username) { return username(username); }
        public final String username() { return username; }
        public final String getUsername() { return username(); }
        public final AuthChallengeResponse password(@Nullable String password) { this.password = password; return this; }
        public final AuthChallengeResponse optPassword(@Nullable String password) { return password(password); }
        public final String password() { return password; }
        public final String getPassword() { return password(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (response == null) throw new IllegalArgumentException("Network.AuthChallengeResponse.response is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"response\":").append(response);
            if (username != null) strBuilder.append(",\"username\":").append('"').append(DomainBase.escapeJson(username)).append('"');
            if (password != null) strBuilder.append(",\"password\":").append('"').append(DomainBase.escapeJson(password)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public AuthChallengeResponse() {}
        public AuthChallengeResponse(
            @JsonProperty("response")Response response,
            @Nullable @JsonProperty("username")String username,
            @Nullable @JsonProperty("password")String password
        ) {
            this.response = response;
            this.username = username;
            this.password = password;
        }
    }

    /**Stages of the interception to begin intercepting. Request will intercept before the request is
sent. Response will intercept after the response is received.
    <p><strong>Experimental.</strong></p>*/
    @ParametersAreNonnullByDefault public enum InterceptionStage implements CommonDomainType {
        Request("Request"),
        HeadersReceived("HeadersReceived");

        private final String _value;
        /**Convert string representation to type.
         @throws IllegalArgumentException if given value cannot convert to enum type. */
        @JsonCreator public static InterceptionStage of(String value) {
            return Enum.valueOf(InterceptionStage.class, value.substring(0, 1).toUpperCase() + value.substring(1));
        }
        InterceptionStage(String value) { _value = value; }
        /**Check if parameter fields of method are all valid. */
        @Override public void check() throws IllegalArgumentException { /* Need not check */ }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) { return strBuilder.append('"').append(_value).append('"'); }
        @Override public String toString() { return "\"" + _value + "\""; }
    }

    /**Request pattern for interception.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class RequestPattern implements CommonDomainType {
        /**Wildcards ('*' -> zero or more, '?' -> exactly one) are allowed. Escape character is
backslash. Omitting is equivalent to "*".
        <em>Optional.</em>*/
        private String urlPattern;
        /**If set, only requests for matching resource types will be intercepted.
        <em>Optional.</em>*/
        private Page.ResourceType resourceType;
        /**Stage at wich to begin intercepting requests. Default is Request.
        <em>Optional.</em>*/
        private InterceptionStage interceptionStage;
        public final RequestPattern urlPattern(@Nullable String urlPattern) { this.urlPattern = urlPattern; return this; }
        public final RequestPattern optUrlPattern(@Nullable String urlPattern) { return urlPattern(urlPattern); }
        public final String urlPattern() { return urlPattern; }
        public final String getUrlPattern() { return urlPattern(); }
        public final RequestPattern resourceType(@Nullable Page.ResourceType resourceType) { this.resourceType = resourceType; return this; }
        public final RequestPattern optResourceType(@Nullable Page.ResourceType resourceType) { return resourceType(resourceType); }
        public final Page.ResourceType resourceType() { return resourceType; }
        public final Page.ResourceType getResourceType() { return resourceType(); }
        public final RequestPattern interceptionStage(@Nullable InterceptionStage interceptionStage) { this.interceptionStage = interceptionStage; return this; }
        public final RequestPattern optInterceptionStage(@Nullable InterceptionStage interceptionStage) { return interceptionStage(interceptionStage); }
        public final InterceptionStage interceptionStage() { return interceptionStage; }
        public final InterceptionStage getInterceptionStage() { return interceptionStage(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            if (urlPattern != null) strBuilder.append("\"urlPattern\":").append('"').append(DomainBase.escapeJson(urlPattern)).append('"');
            if (resourceType != null) resourceType.toJson(strBuilder.append(",\"resourceType\":"));
            if (interceptionStage != null) interceptionStage.toJson(strBuilder.append(",\"interceptionStage\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public RequestPattern() {}
        public RequestPattern(
            @Nullable @JsonProperty("urlPattern")String urlPattern,
            @Nullable @JsonProperty("resourceType")Page.ResourceType resourceType,
            @Nullable @JsonProperty("interceptionStage")InterceptionStage interceptionStage
        ) {
            this.urlPattern = urlPattern;
            this.resourceType = resourceType;
            this.interceptionStage = interceptionStage;
        }
    }
    /**Tells whether clearing browser cache is supported.
    @Deprecated*/
    public CanClearBrowserCacheParameter canClearBrowserCache() { final CanClearBrowserCacheParameter v = new CanClearBrowserCacheParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of canClearBrowserCache.
    @Deprecated*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class CanClearBrowserCacheParameter extends CommandBase {
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
        public CanClearBrowserCacheParameter() {}
        public CompletableFuture<CanClearBrowserCacheResult> call() {
            return super.call("Network.canClearBrowserCache", CanClearBrowserCacheResult.class,
                (code, msg)->new CanClearBrowserCacheResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<CanClearBrowserCacheResult> call(Executor exec) {
            return super.call("Network.canClearBrowserCache", CanClearBrowserCacheResult.class,
                (code, msg)->new CanClearBrowserCacheResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of canClearBrowserCache.
    @Deprecated*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class CanClearBrowserCacheResult extends ResultBase {
        /**True if browser cache can be cleared.*/
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
        public CanClearBrowserCacheResult(
            @JsonProperty("result")Boolean result
        ) {
            this.result = result;
        }
        public CanClearBrowserCacheResult(ResultBase.FailedResult e) {
            super(e);
            result = null;
        }
    }
    /**Tells whether clearing browser cookies is supported.
    @Deprecated*/
    public CanClearBrowserCookiesParameter canClearBrowserCookies() { final CanClearBrowserCookiesParameter v = new CanClearBrowserCookiesParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of canClearBrowserCookies.
    @Deprecated*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class CanClearBrowserCookiesParameter extends CommandBase {
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
        public CanClearBrowserCookiesParameter() {}
        public CompletableFuture<CanClearBrowserCookiesResult> call() {
            return super.call("Network.canClearBrowserCookies", CanClearBrowserCookiesResult.class,
                (code, msg)->new CanClearBrowserCookiesResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<CanClearBrowserCookiesResult> call(Executor exec) {
            return super.call("Network.canClearBrowserCookies", CanClearBrowserCookiesResult.class,
                (code, msg)->new CanClearBrowserCookiesResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of canClearBrowserCookies.
    @Deprecated*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class CanClearBrowserCookiesResult extends ResultBase {
        /**True if browser cookies can be cleared.*/
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
        public CanClearBrowserCookiesResult(
            @JsonProperty("result")Boolean result
        ) {
            this.result = result;
        }
        public CanClearBrowserCookiesResult(ResultBase.FailedResult e) {
            super(e);
            result = null;
        }
    }
    /**Tells whether emulation of network conditions is supported.
    @Deprecated*/
    public CanEmulateNetworkConditionsParameter canEmulateNetworkConditions() { final CanEmulateNetworkConditionsParameter v = new CanEmulateNetworkConditionsParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of canEmulateNetworkConditions.
    @Deprecated*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class CanEmulateNetworkConditionsParameter extends CommandBase {
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
        public CanEmulateNetworkConditionsParameter() {}
        public CompletableFuture<CanEmulateNetworkConditionsResult> call() {
            return super.call("Network.canEmulateNetworkConditions", CanEmulateNetworkConditionsResult.class,
                (code, msg)->new CanEmulateNetworkConditionsResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<CanEmulateNetworkConditionsResult> call(Executor exec) {
            return super.call("Network.canEmulateNetworkConditions", CanEmulateNetworkConditionsResult.class,
                (code, msg)->new CanEmulateNetworkConditionsResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of canEmulateNetworkConditions.
    @Deprecated*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class CanEmulateNetworkConditionsResult extends ResultBase {
        /**True if emulation of network conditions is supported.*/
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
        public CanEmulateNetworkConditionsResult(
            @JsonProperty("result")Boolean result
        ) {
            this.result = result;
        }
        public CanEmulateNetworkConditionsResult(ResultBase.FailedResult e) {
            super(e);
            result = null;
        }
    }
    /**Clears browser cache.*/
    public ClearBrowserCacheParameter clearBrowserCache() { final ClearBrowserCacheParameter v = new ClearBrowserCacheParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of clearBrowserCache.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ClearBrowserCacheParameter extends CommandBase {
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
        public ClearBrowserCacheParameter() {}
        public CompletableFuture<ClearBrowserCacheResult> call() {
            return super.call("Network.clearBrowserCache", ClearBrowserCacheResult.class,
                (code, msg)->new ClearBrowserCacheResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<ClearBrowserCacheResult> call(Executor exec) {
            return super.call("Network.clearBrowserCache", ClearBrowserCacheResult.class,
                (code, msg)->new ClearBrowserCacheResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of clearBrowserCache.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ClearBrowserCacheResult extends ResultBase {
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
        public ClearBrowserCacheResult() { super(); }
        public ClearBrowserCacheResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Clears browser cookies.*/
    public ClearBrowserCookiesParameter clearBrowserCookies() { final ClearBrowserCookiesParameter v = new ClearBrowserCookiesParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of clearBrowserCookies.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ClearBrowserCookiesParameter extends CommandBase {
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
        public ClearBrowserCookiesParameter() {}
        public CompletableFuture<ClearBrowserCookiesResult> call() {
            return super.call("Network.clearBrowserCookies", ClearBrowserCookiesResult.class,
                (code, msg)->new ClearBrowserCookiesResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<ClearBrowserCookiesResult> call(Executor exec) {
            return super.call("Network.clearBrowserCookies", ClearBrowserCookiesResult.class,
                (code, msg)->new ClearBrowserCookiesResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of clearBrowserCookies.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ClearBrowserCookiesResult extends ResultBase {
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
        public ClearBrowserCookiesResult() { super(); }
        public ClearBrowserCookiesResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Response to Network.requestIntercepted which either modifies the request to continue with any
modifications, or blocks it, or completes it with the provided response bytes. If a network
fetch occurs as a result which encounters a redirect an additional Network.requestIntercepted
event will be sent with the same InterceptionId.
    <p><strong>Experimental.</strong></p>*/
    public ContinueInterceptedRequestParameter continueInterceptedRequest() { final ContinueInterceptedRequestParameter v = new ContinueInterceptedRequestParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of continueInterceptedRequest.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ContinueInterceptedRequestParameter extends CommandBase {
        /**&lt;No document in protocol.&gt;*/
        private InterceptionId interceptionId;
        /**If set this causes the request to fail with the given reason. Passing `Aborted` for requests
marked with `isNavigationRequest` also cancels the navigation. Must not be set in response
to an authChallenge.
        <em>Optional.</em>*/
        private ErrorReason errorReason;
        /**If set the requests completes using with the provided base64 encoded raw response, including
HTTP status line and headers etc... Must not be set in response to an authChallenge.
        <em>Optional.</em>*/
        private String rawResponse;
        /**If set the request url will be modified in a way that's not observable by page. Must not be
set in response to an authChallenge.
        <em>Optional.</em>*/
        private String url;
        /**If set this allows the request method to be overridden. Must not be set in response to an
authChallenge.
        <em>Optional.</em>*/
        private String method;
        /**If set this allows postData to be set. Must not be set in response to an authChallenge.
        <em>Optional.</em>*/
        private String postData;
        /**If set this allows the request headers to be changed. Must not be set in response to an
authChallenge.
        <em>Optional.</em>*/
        private Headers headers;
        /**Response to a requestIntercepted with an authChallenge. Must not be set otherwise.
        <em>Optional.</em>*/
        private AuthChallengeResponse authChallengeResponse;
        public final ContinueInterceptedRequestParameter interceptionId(InterceptionId interceptionId) { this.interceptionId = interceptionId; return this; }
        public final ContinueInterceptedRequestParameter setInterceptionId(InterceptionId interceptionId) { return interceptionId(interceptionId); }
        public final InterceptionId interceptionId() { return interceptionId; }
        public final InterceptionId getInterceptionId() { return interceptionId(); }
        public final ContinueInterceptedRequestParameter errorReason(@Nullable ErrorReason errorReason) { this.errorReason = errorReason; return this; }
        public final ContinueInterceptedRequestParameter optErrorReason(@Nullable ErrorReason errorReason) { return errorReason(errorReason); }
        public final ErrorReason errorReason() { return errorReason; }
        public final ErrorReason getErrorReason() { return errorReason(); }
        public final ContinueInterceptedRequestParameter rawResponse(@Nullable String rawResponse) { this.rawResponse = rawResponse; return this; }
        public final ContinueInterceptedRequestParameter optRawResponse(@Nullable String rawResponse) { return rawResponse(rawResponse); }
        public final String rawResponse() { return rawResponse; }
        public final String getRawResponse() { return rawResponse(); }
        public final ContinueInterceptedRequestParameter url(@Nullable String url) { this.url = url; return this; }
        public final ContinueInterceptedRequestParameter optUrl(@Nullable String url) { return url(url); }
        public final String url() { return url; }
        public final String getUrl() { return url(); }
        public final ContinueInterceptedRequestParameter method(@Nullable String method) { this.method = method; return this; }
        public final ContinueInterceptedRequestParameter optMethod(@Nullable String method) { return method(method); }
        public final String method() { return method; }
        public final String getMethod() { return method(); }
        public final ContinueInterceptedRequestParameter postData(@Nullable String postData) { this.postData = postData; return this; }
        public final ContinueInterceptedRequestParameter optPostData(@Nullable String postData) { return postData(postData); }
        public final String postData() { return postData; }
        public final String getPostData() { return postData(); }
        public final ContinueInterceptedRequestParameter headers(@Nullable Headers headers) { this.headers = headers; return this; }
        public final ContinueInterceptedRequestParameter optHeaders(@Nullable Headers headers) { return headers(headers); }
        public final Headers headers() { return headers; }
        public final Headers getHeaders() { return headers(); }
        public final ContinueInterceptedRequestParameter authChallengeResponse(@Nullable AuthChallengeResponse authChallengeResponse) { this.authChallengeResponse = authChallengeResponse; return this; }
        public final ContinueInterceptedRequestParameter optAuthChallengeResponse(@Nullable AuthChallengeResponse authChallengeResponse) { return authChallengeResponse(authChallengeResponse); }
        public final AuthChallengeResponse authChallengeResponse() { return authChallengeResponse; }
        public final AuthChallengeResponse getAuthChallengeResponse() { return authChallengeResponse(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (interceptionId == null) throw new IllegalArgumentException("Network.ContinueInterceptedRequestParameter.interceptionId is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            interceptionId.toJson(strBuilder.append("\"interceptionId\":"));
            if (errorReason != null) errorReason.toJson(strBuilder.append(",\"errorReason\":"));
            if (rawResponse != null) strBuilder.append(",\"rawResponse\":").append('"').append(DomainBase.escapeJson(rawResponse)).append('"');
            if (url != null) strBuilder.append(",\"url\":").append('"').append(DomainBase.escapeJson(url)).append('"');
            if (method != null) strBuilder.append(",\"method\":").append('"').append(DomainBase.escapeJson(method)).append('"');
            if (postData != null) strBuilder.append(",\"postData\":").append('"').append(DomainBase.escapeJson(postData)).append('"');
            if (headers != null) headers.toJson(strBuilder.append(",\"headers\":"));
            if (authChallengeResponse != null) authChallengeResponse.toJson(strBuilder.append(",\"authChallengeResponse\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public ContinueInterceptedRequestParameter() {}
        public ContinueInterceptedRequestParameter(
            @JsonProperty("interceptionId")InterceptionId interceptionId,
            @Nullable @JsonProperty("errorReason")ErrorReason errorReason,
            @Nullable @JsonProperty("rawResponse")String rawResponse,
            @Nullable @JsonProperty("url")String url,
            @Nullable @JsonProperty("method")String method,
            @Nullable @JsonProperty("postData")String postData,
            @Nullable @JsonProperty("headers")Headers headers,
            @Nullable @JsonProperty("authChallengeResponse")AuthChallengeResponse authChallengeResponse
        ) {
            this();
            this.interceptionId = interceptionId;
            this.errorReason = errorReason;
            this.rawResponse = rawResponse;
            this.url = url;
            this.method = method;
            this.postData = postData;
            this.headers = headers;
            this.authChallengeResponse = authChallengeResponse;
        }
        public CompletableFuture<ContinueInterceptedRequestResult> call() {
            return super.call("Network.continueInterceptedRequest", ContinueInterceptedRequestResult.class,
                (code, msg)->new ContinueInterceptedRequestResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<ContinueInterceptedRequestResult> call(Executor exec) {
            return super.call("Network.continueInterceptedRequest", ContinueInterceptedRequestResult.class,
                (code, msg)->new ContinueInterceptedRequestResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of continueInterceptedRequest.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ContinueInterceptedRequestResult extends ResultBase {
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
        public ContinueInterceptedRequestResult() { super(); }
        public ContinueInterceptedRequestResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Deletes browser cookies with matching name and url or domain/path pair.*/
    public DeleteCookiesParameter deleteCookies() { final DeleteCookiesParameter v = new DeleteCookiesParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of deleteCookies.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class DeleteCookiesParameter extends CommandBase {
        /**Name of the cookies to remove.*/
        private String name;
        /**If specified, deletes all the cookies with the given name where domain and path match
provided URL.
        <em>Optional.</em>*/
        private String url;
        /**If specified, deletes only cookies with the exact domain.
        <em>Optional.</em>*/
        private String domain;
        /**If specified, deletes only cookies with the exact path.
        <em>Optional.</em>*/
        private String path;
        public final DeleteCookiesParameter name(String name) { this.name = name; return this; }
        public final DeleteCookiesParameter setName(String name) { return name(name); }
        public final String name() { return name; }
        public final String getName() { return name(); }
        public final DeleteCookiesParameter url(@Nullable String url) { this.url = url; return this; }
        public final DeleteCookiesParameter optUrl(@Nullable String url) { return url(url); }
        public final String url() { return url; }
        public final String getUrl() { return url(); }
        public final DeleteCookiesParameter domain(@Nullable String domain) { this.domain = domain; return this; }
        public final DeleteCookiesParameter optDomain(@Nullable String domain) { return domain(domain); }
        public final String domain() { return domain; }
        public final String getDomain() { return domain(); }
        public final DeleteCookiesParameter path(@Nullable String path) { this.path = path; return this; }
        public final DeleteCookiesParameter optPath(@Nullable String path) { return path(path); }
        public final String path() { return path; }
        public final String getPath() { return path(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (name == null) throw new IllegalArgumentException("Network.DeleteCookiesParameter.name is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"name\":").append('"').append(DomainBase.escapeJson(name)).append('"');
            if (url != null) strBuilder.append(",\"url\":").append('"').append(DomainBase.escapeJson(url)).append('"');
            if (domain != null) strBuilder.append(",\"domain\":").append('"').append(DomainBase.escapeJson(domain)).append('"');
            if (path != null) strBuilder.append(",\"path\":").append('"').append(DomainBase.escapeJson(path)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public DeleteCookiesParameter() {}
        public DeleteCookiesParameter(
            @JsonProperty("name")String name,
            @Nullable @JsonProperty("url")String url,
            @Nullable @JsonProperty("domain")String domain,
            @Nullable @JsonProperty("path")String path
        ) {
            this();
            this.name = name;
            this.url = url;
            this.domain = domain;
            this.path = path;
        }
        public CompletableFuture<DeleteCookiesResult> call() {
            return super.call("Network.deleteCookies", DeleteCookiesResult.class,
                (code, msg)->new DeleteCookiesResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<DeleteCookiesResult> call(Executor exec) {
            return super.call("Network.deleteCookies", DeleteCookiesResult.class,
                (code, msg)->new DeleteCookiesResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of deleteCookies.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class DeleteCookiesResult extends ResultBase {
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
        public DeleteCookiesResult() { super(); }
        public DeleteCookiesResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Disables network tracking, prevents network events from being sent to the client.*/
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
            return super.call("Network.disable", DisableResult.class,
                (code, msg)->new DisableResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<DisableResult> call(Executor exec) {
            return super.call("Network.disable", DisableResult.class,
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
    /**Activates emulation of network conditions.*/
    public EmulateNetworkConditionsParameter emulateNetworkConditions() { final EmulateNetworkConditionsParameter v = new EmulateNetworkConditionsParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of emulateNetworkConditions.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class EmulateNetworkConditionsParameter extends CommandBase {
        /**True to emulate internet disconnection.*/
        private Boolean offline;
        /**Minimum latency from request sent to response headers received (ms).*/
        private Double latency;
        /**Maximal aggregated download throughput (bytes/sec). -1 disables download throttling.*/
        private Double downloadThroughput;
        /**Maximal aggregated upload throughput (bytes/sec).  -1 disables upload throttling.*/
        private Double uploadThroughput;
        /**Connection type if known.
        <em>Optional.</em>*/
        private ConnectionType connectionType;
        public final EmulateNetworkConditionsParameter offline(Boolean offline) { this.offline = offline; return this; }
        public final EmulateNetworkConditionsParameter setOffline(Boolean offline) { return offline(offline); }
        public final Boolean offline() { return offline; }
        public final Boolean getOffline() { return offline(); }
        public final EmulateNetworkConditionsParameter latency(Double latency) { this.latency = latency; return this; }
        public final EmulateNetworkConditionsParameter setLatency(Double latency) { return latency(latency); }
        public final Double latency() { return latency; }
        public final Double getLatency() { return latency(); }
        public final EmulateNetworkConditionsParameter downloadThroughput(Double downloadThroughput) { this.downloadThroughput = downloadThroughput; return this; }
        public final EmulateNetworkConditionsParameter setDownloadThroughput(Double downloadThroughput) { return downloadThroughput(downloadThroughput); }
        public final Double downloadThroughput() { return downloadThroughput; }
        public final Double getDownloadThroughput() { return downloadThroughput(); }
        public final EmulateNetworkConditionsParameter uploadThroughput(Double uploadThroughput) { this.uploadThroughput = uploadThroughput; return this; }
        public final EmulateNetworkConditionsParameter setUploadThroughput(Double uploadThroughput) { return uploadThroughput(uploadThroughput); }
        public final Double uploadThroughput() { return uploadThroughput; }
        public final Double getUploadThroughput() { return uploadThroughput(); }
        public final EmulateNetworkConditionsParameter connectionType(@Nullable ConnectionType connectionType) { this.connectionType = connectionType; return this; }
        public final EmulateNetworkConditionsParameter optConnectionType(@Nullable ConnectionType connectionType) { return connectionType(connectionType); }
        public final ConnectionType connectionType() { return connectionType; }
        public final ConnectionType getConnectionType() { return connectionType(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (offline == null) throw new IllegalArgumentException("Network.EmulateNetworkConditionsParameter.offline is necessary field.");
            if (latency == null) throw new IllegalArgumentException("Network.EmulateNetworkConditionsParameter.latency is necessary field.");
            if (downloadThroughput == null) throw new IllegalArgumentException("Network.EmulateNetworkConditionsParameter.downloadThroughput is necessary field.");
            if (uploadThroughput == null) throw new IllegalArgumentException("Network.EmulateNetworkConditionsParameter.uploadThroughput is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"offline\":").append(offline);
            strBuilder.append(",\"latency\":").append(latency);
            strBuilder.append(",\"downloadThroughput\":").append(downloadThroughput);
            strBuilder.append(",\"uploadThroughput\":").append(uploadThroughput);
            if (connectionType != null) connectionType.toJson(strBuilder.append(",\"connectionType\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public EmulateNetworkConditionsParameter() {}
        public EmulateNetworkConditionsParameter(
            @JsonProperty("offline")Boolean offline,
            @JsonProperty("latency")Double latency,
            @JsonProperty("downloadThroughput")Double downloadThroughput,
            @JsonProperty("uploadThroughput")Double uploadThroughput,
            @Nullable @JsonProperty("connectionType")ConnectionType connectionType
        ) {
            this();
            this.offline = offline;
            this.latency = latency;
            this.downloadThroughput = downloadThroughput;
            this.uploadThroughput = uploadThroughput;
            this.connectionType = connectionType;
        }
        public CompletableFuture<EmulateNetworkConditionsResult> call() {
            return super.call("Network.emulateNetworkConditions", EmulateNetworkConditionsResult.class,
                (code, msg)->new EmulateNetworkConditionsResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<EmulateNetworkConditionsResult> call(Executor exec) {
            return super.call("Network.emulateNetworkConditions", EmulateNetworkConditionsResult.class,
                (code, msg)->new EmulateNetworkConditionsResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of emulateNetworkConditions.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class EmulateNetworkConditionsResult extends ResultBase {
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
        public EmulateNetworkConditionsResult() { super(); }
        public EmulateNetworkConditionsResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Enables network tracking, network events will now be delivered to the client.*/
    public EnableParameter enable() { final EnableParameter v = new EnableParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of enable.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class EnableParameter extends CommandBase {
        /**Buffer size in bytes to use when preserving network payloads (XHRs, etc).
        <em>Optional.</em>
        <p><strong>Experimental.</strong></p>*/
        private Integer maxTotalBufferSize;
        /**Per-resource buffer size in bytes to use when preserving network payloads (XHRs, etc).
        <em>Optional.</em>
        <p><strong>Experimental.</strong></p>*/
        private Integer maxResourceBufferSize;
        /**Longest post body size (in bytes) that would be included in requestWillBeSent notification
        <em>Optional.</em>*/
        private Integer maxPostDataSize;
        public final EnableParameter maxTotalBufferSize(@Nullable Integer maxTotalBufferSize) { this.maxTotalBufferSize = maxTotalBufferSize; return this; }
        public final EnableParameter optMaxTotalBufferSize(@Nullable Integer maxTotalBufferSize) { return maxTotalBufferSize(maxTotalBufferSize); }
        public final Integer maxTotalBufferSize() { return maxTotalBufferSize; }
        public final Integer getMaxTotalBufferSize() { return maxTotalBufferSize(); }
        public final EnableParameter maxResourceBufferSize(@Nullable Integer maxResourceBufferSize) { this.maxResourceBufferSize = maxResourceBufferSize; return this; }
        public final EnableParameter optMaxResourceBufferSize(@Nullable Integer maxResourceBufferSize) { return maxResourceBufferSize(maxResourceBufferSize); }
        public final Integer maxResourceBufferSize() { return maxResourceBufferSize; }
        public final Integer getMaxResourceBufferSize() { return maxResourceBufferSize(); }
        public final EnableParameter maxPostDataSize(@Nullable Integer maxPostDataSize) { this.maxPostDataSize = maxPostDataSize; return this; }
        public final EnableParameter optMaxPostDataSize(@Nullable Integer maxPostDataSize) { return maxPostDataSize(maxPostDataSize); }
        public final Integer maxPostDataSize() { return maxPostDataSize; }
        public final Integer getMaxPostDataSize() { return maxPostDataSize(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            if (maxTotalBufferSize != null) strBuilder.append("\"maxTotalBufferSize\":").append(maxTotalBufferSize);
            if (maxResourceBufferSize != null) strBuilder.append(",\"maxResourceBufferSize\":").append(maxResourceBufferSize);
            if (maxPostDataSize != null) strBuilder.append(",\"maxPostDataSize\":").append(maxPostDataSize);
            strBuilder.append('}');
            return strBuilder;
        }
        public EnableParameter() {}
        public EnableParameter(
            @Nullable @JsonProperty("maxTotalBufferSize")Integer maxTotalBufferSize,
            @Nullable @JsonProperty("maxResourceBufferSize")Integer maxResourceBufferSize,
            @Nullable @JsonProperty("maxPostDataSize")Integer maxPostDataSize
        ) {
            this();
            this.maxTotalBufferSize = maxTotalBufferSize;
            this.maxResourceBufferSize = maxResourceBufferSize;
            this.maxPostDataSize = maxPostDataSize;
        }
        public CompletableFuture<EnableResult> call() {
            return super.call("Network.enable", EnableResult.class,
                (code, msg)->new EnableResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<EnableResult> call(Executor exec) {
            return super.call("Network.enable", EnableResult.class,
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
    /**Returns all browser cookies. Depending on the backend support, will return detailed cookie
information in the `cookies` field.*/
    public GetAllCookiesParameter getAllCookies() { final GetAllCookiesParameter v = new GetAllCookiesParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of getAllCookies.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetAllCookiesParameter extends CommandBase {
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
        public GetAllCookiesParameter() {}
        public CompletableFuture<GetAllCookiesResult> call() {
            return super.call("Network.getAllCookies", GetAllCookiesResult.class,
                (code, msg)->new GetAllCookiesResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetAllCookiesResult> call(Executor exec) {
            return super.call("Network.getAllCookies", GetAllCookiesResult.class,
                (code, msg)->new GetAllCookiesResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of getAllCookies.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetAllCookiesResult extends ResultBase {
        /**Array of cookie objects.*/
        private final List<Cookie> cookies;
        public final List<Cookie> cookies() { return cookies; }
        public final List<Cookie> getCookies() { return cookies(); }
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
        public GetAllCookiesResult(
            @JsonProperty("cookies")List<Cookie> cookies
        ) {
            this.cookies = cookies;
        }
        public GetAllCookiesResult(ResultBase.FailedResult e) {
            super(e);
            cookies = null;
        }
    }
    /**Returns the DER-encoded certificate.
    <p><strong>Experimental.</strong></p>*/
    public GetCertificateParameter getCertificate() { final GetCertificateParameter v = new GetCertificateParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of getCertificate.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetCertificateParameter extends CommandBase {
        /**Origin to get certificate for.*/
        private String origin;
        public final GetCertificateParameter origin(String origin) { this.origin = origin; return this; }
        public final GetCertificateParameter setOrigin(String origin) { return origin(origin); }
        public final String origin() { return origin; }
        public final String getOrigin() { return origin(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (origin == null) throw new IllegalArgumentException("Network.GetCertificateParameter.origin is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"origin\":").append('"').append(DomainBase.escapeJson(origin)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public GetCertificateParameter() {}
        public GetCertificateParameter(
            @JsonProperty("origin")String origin
        ) {
            this();
            this.origin = origin;
        }
        public CompletableFuture<GetCertificateResult> call() {
            return super.call("Network.getCertificate", GetCertificateResult.class,
                (code, msg)->new GetCertificateResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetCertificateResult> call(Executor exec) {
            return super.call("Network.getCertificate", GetCertificateResult.class,
                (code, msg)->new GetCertificateResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of getCertificate.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetCertificateResult extends ResultBase {
        /**&lt;No document in protocol.&gt;*/
        private final List<String> tableNames;
        public final List<String> tableNames() { return tableNames; }
        public final List<String> getTableNames() { return tableNames(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
                        strBuilder.append("\"tableNames\":[");
            strBuilder.append('"').append(DomainBase.escapeJson(tableNames.get(0))).append('"');
            for (int i = 1; i < tableNames.size(); ++i)
                strBuilder.append(",\"").append(DomainBase.escapeJson(tableNames.get(i))).append('"');
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public GetCertificateResult(
            @JsonProperty("tableNames")List<String> tableNames
        ) {
            this.tableNames = tableNames;
        }
        public GetCertificateResult(ResultBase.FailedResult e) {
            super(e);
            tableNames = null;
        }
    }
    /**Returns all browser cookies for the current URL. Depending on the backend support, will return
detailed cookie information in the `cookies` field.*/
    public GetCookiesParameter getCookies() { final GetCookiesParameter v = new GetCookiesParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of getCookies.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetCookiesParameter extends CommandBase {
        /**The list of URLs for which applicable cookies will be fetched
        <em>Optional.</em>*/
        private List<String> urls;
        public final GetCookiesParameter urls(@Nullable List<String> urls) { this.urls = urls; return this; }
        public final GetCookiesParameter optUrls(@Nullable List<String> urls) { return urls(urls); }
        public final List<String> urls() { return urls; }
        public final List<String> getUrls() { return urls(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            if (urls != null) {
                strBuilder.append("\"urls\":[");
                strBuilder.append('"').append(DomainBase.escapeJson(urls.get(0))).append('"');
                for (int i = 1; i < urls.size(); ++i)
                    strBuilder.append(",\"").append(DomainBase.escapeJson(urls.get(i))).append('"');
                strBuilder.append(']');
            }
            strBuilder.append('}');
            return strBuilder;
        }
        public GetCookiesParameter() {}
        public GetCookiesParameter(
            @Nullable @JsonProperty("urls")List<String> urls
        ) {
            this();
            this.urls = urls;
        }
        public CompletableFuture<GetCookiesResult> call() {
            return super.call("Network.getCookies", GetCookiesResult.class,
                (code, msg)->new GetCookiesResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetCookiesResult> call(Executor exec) {
            return super.call("Network.getCookies", GetCookiesResult.class,
                (code, msg)->new GetCookiesResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of getCookies.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetCookiesResult extends ResultBase {
        /**Array of cookie objects.*/
        private final List<Cookie> cookies;
        public final List<Cookie> cookies() { return cookies; }
        public final List<Cookie> getCookies() { return cookies(); }
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
            @JsonProperty("cookies")List<Cookie> cookies
        ) {
            this.cookies = cookies;
        }
        public GetCookiesResult(ResultBase.FailedResult e) {
            super(e);
            cookies = null;
        }
    }
    /**Returns content served for the given request.*/
    public GetResponseBodyParameter getResponseBody() { final GetResponseBodyParameter v = new GetResponseBodyParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of getResponseBody.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetResponseBodyParameter extends CommandBase {
        /**Identifier of the network request to get content for.*/
        private RequestId requestId;
        public final GetResponseBodyParameter requestId(RequestId requestId) { this.requestId = requestId; return this; }
        public final GetResponseBodyParameter setRequestId(RequestId requestId) { return requestId(requestId); }
        public final RequestId requestId() { return requestId; }
        public final RequestId getRequestId() { return requestId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (requestId == null) throw new IllegalArgumentException("Network.GetResponseBodyParameter.requestId is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            requestId.toJson(strBuilder.append("\"requestId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public GetResponseBodyParameter() {}
        public GetResponseBodyParameter(
            @JsonProperty("requestId")RequestId requestId
        ) {
            this();
            this.requestId = requestId;
        }
        public CompletableFuture<GetResponseBodyResult> call() {
            return super.call("Network.getResponseBody", GetResponseBodyResult.class,
                (code, msg)->new GetResponseBodyResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetResponseBodyResult> call(Executor exec) {
            return super.call("Network.getResponseBody", GetResponseBodyResult.class,
                (code, msg)->new GetResponseBodyResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of getResponseBody.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetResponseBodyResult extends ResultBase {
        /**Response body.*/
        private final String body;
        /**True, if content was sent as base64.*/
        private final Boolean base64Encoded;
        public final String body() { return body; }
        public final String getBody() { return body(); }
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
            strBuilder.append("\"body\":").append('"').append(DomainBase.escapeJson(body)).append('"');
            strBuilder.append(",\"base64Encoded\":").append(base64Encoded);
            strBuilder.append('}');
            return strBuilder;
        }
        public GetResponseBodyResult(
            @JsonProperty("body")String body,
            @JsonProperty("base64Encoded")Boolean base64Encoded
        ) {
            this.body = body;
            this.base64Encoded = base64Encoded;
        }
        public GetResponseBodyResult(ResultBase.FailedResult e) {
            super(e);
            body = null;
            base64Encoded = null;
        }
    }
    /**Returns post data sent with the request. Returns an error when no data was sent with the request.*/
    public GetRequestPostDataParameter getRequestPostData() { final GetRequestPostDataParameter v = new GetRequestPostDataParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of getRequestPostData.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetRequestPostDataParameter extends CommandBase {
        /**Identifier of the network request to get content for.*/
        private RequestId requestId;
        public final GetRequestPostDataParameter requestId(RequestId requestId) { this.requestId = requestId; return this; }
        public final GetRequestPostDataParameter setRequestId(RequestId requestId) { return requestId(requestId); }
        public final RequestId requestId() { return requestId; }
        public final RequestId getRequestId() { return requestId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (requestId == null) throw new IllegalArgumentException("Network.GetRequestPostDataParameter.requestId is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            requestId.toJson(strBuilder.append("\"requestId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public GetRequestPostDataParameter() {}
        public GetRequestPostDataParameter(
            @JsonProperty("requestId")RequestId requestId
        ) {
            this();
            this.requestId = requestId;
        }
        public CompletableFuture<GetRequestPostDataResult> call() {
            return super.call("Network.getRequestPostData", GetRequestPostDataResult.class,
                (code, msg)->new GetRequestPostDataResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetRequestPostDataResult> call(Executor exec) {
            return super.call("Network.getRequestPostData", GetRequestPostDataResult.class,
                (code, msg)->new GetRequestPostDataResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of getRequestPostData.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetRequestPostDataResult extends ResultBase {
        /**Base64-encoded request body.*/
        private final String postData;
        public final String postData() { return postData; }
        public final String getPostData() { return postData(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"postData\":").append('"').append(DomainBase.escapeJson(postData)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public GetRequestPostDataResult(
            @JsonProperty("postData")String postData
        ) {
            this.postData = postData;
        }
        public GetRequestPostDataResult(ResultBase.FailedResult e) {
            super(e);
            postData = null;
        }
    }
    /**Returns content served for the given currently intercepted request.
    <p><strong>Experimental.</strong></p>*/
    public GetResponseBodyForInterceptionParameter getResponseBodyForInterception() { final GetResponseBodyForInterceptionParameter v = new GetResponseBodyForInterceptionParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of getResponseBodyForInterception.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetResponseBodyForInterceptionParameter extends CommandBase {
        /**Identifier for the intercepted request to get body for.*/
        private InterceptionId interceptionId;
        public final GetResponseBodyForInterceptionParameter interceptionId(InterceptionId interceptionId) { this.interceptionId = interceptionId; return this; }
        public final GetResponseBodyForInterceptionParameter setInterceptionId(InterceptionId interceptionId) { return interceptionId(interceptionId); }
        public final InterceptionId interceptionId() { return interceptionId; }
        public final InterceptionId getInterceptionId() { return interceptionId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (interceptionId == null) throw new IllegalArgumentException("Network.GetResponseBodyForInterceptionParameter.interceptionId is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            interceptionId.toJson(strBuilder.append("\"interceptionId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public GetResponseBodyForInterceptionParameter() {}
        public GetResponseBodyForInterceptionParameter(
            @JsonProperty("interceptionId")InterceptionId interceptionId
        ) {
            this();
            this.interceptionId = interceptionId;
        }
        public CompletableFuture<GetResponseBodyForInterceptionResult> call() {
            return super.call("Network.getResponseBodyForInterception", GetResponseBodyForInterceptionResult.class,
                (code, msg)->new GetResponseBodyForInterceptionResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetResponseBodyForInterceptionResult> call(Executor exec) {
            return super.call("Network.getResponseBodyForInterception", GetResponseBodyForInterceptionResult.class,
                (code, msg)->new GetResponseBodyForInterceptionResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of getResponseBodyForInterception.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetResponseBodyForInterceptionResult extends ResultBase {
        /**Response body.*/
        private final String body;
        /**True, if content was sent as base64.*/
        private final Boolean base64Encoded;
        public final String body() { return body; }
        public final String getBody() { return body(); }
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
            strBuilder.append("\"body\":").append('"').append(DomainBase.escapeJson(body)).append('"');
            strBuilder.append(",\"base64Encoded\":").append(base64Encoded);
            strBuilder.append('}');
            return strBuilder;
        }
        public GetResponseBodyForInterceptionResult(
            @JsonProperty("body")String body,
            @JsonProperty("base64Encoded")Boolean base64Encoded
        ) {
            this.body = body;
            this.base64Encoded = base64Encoded;
        }
        public GetResponseBodyForInterceptionResult(ResultBase.FailedResult e) {
            super(e);
            body = null;
            base64Encoded = null;
        }
    }
    /**This method sends a new XMLHttpRequest which is identical to the original one. The following
parameters should be identical: method, url, async, request body, extra headers, withCredentials
attribute, user, password.
    <p><strong>Experimental.</strong></p>*/
    public ReplayXHRParameter replayXHR() { final ReplayXHRParameter v = new ReplayXHRParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of replayXHR.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ReplayXHRParameter extends CommandBase {
        /**Identifier of XHR to replay.*/
        private RequestId requestId;
        public final ReplayXHRParameter requestId(RequestId requestId) { this.requestId = requestId; return this; }
        public final ReplayXHRParameter setRequestId(RequestId requestId) { return requestId(requestId); }
        public final RequestId requestId() { return requestId; }
        public final RequestId getRequestId() { return requestId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (requestId == null) throw new IllegalArgumentException("Network.ReplayXHRParameter.requestId is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            requestId.toJson(strBuilder.append("\"requestId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public ReplayXHRParameter() {}
        public ReplayXHRParameter(
            @JsonProperty("requestId")RequestId requestId
        ) {
            this();
            this.requestId = requestId;
        }
        public CompletableFuture<ReplayXHRResult> call() {
            return super.call("Network.replayXHR", ReplayXHRResult.class,
                (code, msg)->new ReplayXHRResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<ReplayXHRResult> call(Executor exec) {
            return super.call("Network.replayXHR", ReplayXHRResult.class,
                (code, msg)->new ReplayXHRResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of replayXHR.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ReplayXHRResult extends ResultBase {
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
        public ReplayXHRResult() { super(); }
        public ReplayXHRResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Searches for given string in response content.
    <p><strong>Experimental.</strong></p>*/
    public SearchInResponseBodyParameter searchInResponseBody() { final SearchInResponseBodyParameter v = new SearchInResponseBodyParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of searchInResponseBody.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SearchInResponseBodyParameter extends CommandBase {
        /**Identifier of the network response to search.*/
        private RequestId requestId;
        /**String to search for.*/
        private String query;
        /**If true, search is case sensitive.
        <em>Optional.</em>*/
        private Boolean caseSensitive;
        /**If true, treats string parameter as regex.
        <em>Optional.</em>*/
        private Boolean isRegex;
        public final SearchInResponseBodyParameter requestId(RequestId requestId) { this.requestId = requestId; return this; }
        public final SearchInResponseBodyParameter setRequestId(RequestId requestId) { return requestId(requestId); }
        public final RequestId requestId() { return requestId; }
        public final RequestId getRequestId() { return requestId(); }
        public final SearchInResponseBodyParameter query(String query) { this.query = query; return this; }
        public final SearchInResponseBodyParameter setQuery(String query) { return query(query); }
        public final String query() { return query; }
        public final String getQuery() { return query(); }
        public final SearchInResponseBodyParameter caseSensitive(@Nullable Boolean caseSensitive) { this.caseSensitive = caseSensitive; return this; }
        public final SearchInResponseBodyParameter optCaseSensitive(@Nullable Boolean caseSensitive) { return caseSensitive(caseSensitive); }
        public final Boolean caseSensitive() { return caseSensitive; }
        public final Boolean getCaseSensitive() { return caseSensitive(); }
        public final SearchInResponseBodyParameter isRegex(@Nullable Boolean isRegex) { this.isRegex = isRegex; return this; }
        public final SearchInResponseBodyParameter optIsRegex(@Nullable Boolean isRegex) { return isRegex(isRegex); }
        public final Boolean isRegex() { return isRegex; }
        public final Boolean getIsRegex() { return isRegex(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (requestId == null) throw new IllegalArgumentException("Network.SearchInResponseBodyParameter.requestId is necessary field.");
            if (query == null) throw new IllegalArgumentException("Network.SearchInResponseBodyParameter.query is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            requestId.toJson(strBuilder.append("\"requestId\":"));
            strBuilder.append(",\"query\":").append('"').append(DomainBase.escapeJson(query)).append('"');
            if (caseSensitive != null) strBuilder.append(",\"caseSensitive\":").append(caseSensitive);
            if (isRegex != null) strBuilder.append(",\"isRegex\":").append(isRegex);
            strBuilder.append('}');
            return strBuilder;
        }
        public SearchInResponseBodyParameter() {}
        public SearchInResponseBodyParameter(
            @JsonProperty("requestId")RequestId requestId,
            @JsonProperty("query")String query,
            @Nullable @JsonProperty("caseSensitive")Boolean caseSensitive,
            @Nullable @JsonProperty("isRegex")Boolean isRegex
        ) {
            this();
            this.requestId = requestId;
            this.query = query;
            this.caseSensitive = caseSensitive;
            this.isRegex = isRegex;
        }
        public CompletableFuture<SearchInResponseBodyResult> call() {
            return super.call("Network.searchInResponseBody", SearchInResponseBodyResult.class,
                (code, msg)->new SearchInResponseBodyResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SearchInResponseBodyResult> call(Executor exec) {
            return super.call("Network.searchInResponseBody", SearchInResponseBodyResult.class,
                (code, msg)->new SearchInResponseBodyResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of searchInResponseBody.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SearchInResponseBodyResult extends ResultBase {
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
        public SearchInResponseBodyResult(
            @JsonProperty("result")List<Debugger.SearchMatch> result
        ) {
            this.result = result;
        }
        public SearchInResponseBodyResult(ResultBase.FailedResult e) {
            super(e);
            result = null;
        }
    }
    /**Blocks URLs from loading.
    <p><strong>Experimental.</strong></p>*/
    public SetBlockedURLsParameter setBlockedURLs() { final SetBlockedURLsParameter v = new SetBlockedURLsParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setBlockedURLs.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetBlockedURLsParameter extends CommandBase {
        /**URL patterns to block. Wildcards ('*') are allowed.*/
        private List<String> urls;
        public final SetBlockedURLsParameter urls(List<String> urls) { this.urls = urls; return this; }
        public final SetBlockedURLsParameter setUrls(List<String> urls) { return urls(urls); }
        public final List<String> urls() { return urls; }
        public final List<String> getUrls() { return urls(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (urls == null) throw new IllegalArgumentException("Network.SetBlockedURLsParameter.urls is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
                        strBuilder.append("\"urls\":[");
            strBuilder.append('"').append(DomainBase.escapeJson(urls.get(0))).append('"');
            for (int i = 1; i < urls.size(); ++i)
                strBuilder.append(",\"").append(DomainBase.escapeJson(urls.get(i))).append('"');
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public SetBlockedURLsParameter() {}
        public SetBlockedURLsParameter(
            @JsonProperty("urls")List<String> urls
        ) {
            this();
            this.urls = urls;
        }
        public CompletableFuture<SetBlockedURLsResult> call() {
            return super.call("Network.setBlockedURLs", SetBlockedURLsResult.class,
                (code, msg)->new SetBlockedURLsResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetBlockedURLsResult> call(Executor exec) {
            return super.call("Network.setBlockedURLs", SetBlockedURLsResult.class,
                (code, msg)->new SetBlockedURLsResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setBlockedURLs.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetBlockedURLsResult extends ResultBase {
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
        public SetBlockedURLsResult() { super(); }
        public SetBlockedURLsResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Toggles ignoring of service worker for each request.
    <p><strong>Experimental.</strong></p>*/
    public SetBypassServiceWorkerParameter setBypassServiceWorker() { final SetBypassServiceWorkerParameter v = new SetBypassServiceWorkerParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setBypassServiceWorker.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetBypassServiceWorkerParameter extends CommandBase {
        /**Bypass service worker and load from network.*/
        private Boolean bypass;
        public final SetBypassServiceWorkerParameter bypass(Boolean bypass) { this.bypass = bypass; return this; }
        public final SetBypassServiceWorkerParameter setBypass(Boolean bypass) { return bypass(bypass); }
        public final Boolean bypass() { return bypass; }
        public final Boolean getBypass() { return bypass(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (bypass == null) throw new IllegalArgumentException("Network.SetBypassServiceWorkerParameter.bypass is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"bypass\":").append(bypass);
            strBuilder.append('}');
            return strBuilder;
        }
        public SetBypassServiceWorkerParameter() {}
        public SetBypassServiceWorkerParameter(
            @JsonProperty("bypass")Boolean bypass
        ) {
            this();
            this.bypass = bypass;
        }
        public CompletableFuture<SetBypassServiceWorkerResult> call() {
            return super.call("Network.setBypassServiceWorker", SetBypassServiceWorkerResult.class,
                (code, msg)->new SetBypassServiceWorkerResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetBypassServiceWorkerResult> call(Executor exec) {
            return super.call("Network.setBypassServiceWorker", SetBypassServiceWorkerResult.class,
                (code, msg)->new SetBypassServiceWorkerResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setBypassServiceWorker.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetBypassServiceWorkerResult extends ResultBase {
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
        public SetBypassServiceWorkerResult() { super(); }
        public SetBypassServiceWorkerResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Toggles ignoring cache for each request. If `true`, cache will not be used.*/
    public SetCacheDisabledParameter setCacheDisabled() { final SetCacheDisabledParameter v = new SetCacheDisabledParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setCacheDisabled.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetCacheDisabledParameter extends CommandBase {
        /**Cache disabled state.*/
        private Boolean cacheDisabled;
        public final SetCacheDisabledParameter cacheDisabled(Boolean cacheDisabled) { this.cacheDisabled = cacheDisabled; return this; }
        public final SetCacheDisabledParameter setCacheDisabled(Boolean cacheDisabled) { return cacheDisabled(cacheDisabled); }
        public final Boolean cacheDisabled() { return cacheDisabled; }
        public final Boolean getCacheDisabled() { return cacheDisabled(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (cacheDisabled == null) throw new IllegalArgumentException("Network.SetCacheDisabledParameter.cacheDisabled is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"cacheDisabled\":").append(cacheDisabled);
            strBuilder.append('}');
            return strBuilder;
        }
        public SetCacheDisabledParameter() {}
        public SetCacheDisabledParameter(
            @JsonProperty("cacheDisabled")Boolean cacheDisabled
        ) {
            this();
            this.cacheDisabled = cacheDisabled;
        }
        public CompletableFuture<SetCacheDisabledResult> call() {
            return super.call("Network.setCacheDisabled", SetCacheDisabledResult.class,
                (code, msg)->new SetCacheDisabledResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetCacheDisabledResult> call(Executor exec) {
            return super.call("Network.setCacheDisabled", SetCacheDisabledResult.class,
                (code, msg)->new SetCacheDisabledResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setCacheDisabled.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetCacheDisabledResult extends ResultBase {
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
        public SetCacheDisabledResult() { super(); }
        public SetCacheDisabledResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Sets a cookie with the given cookie data; may overwrite equivalent cookies if they exist.*/
    public SetCookieParameter setCookie() { final SetCookieParameter v = new SetCookieParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setCookie.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetCookieParameter extends CommandBase {
        /**Cookie name.*/
        private String name;
        /**Cookie value.*/
        private String value;
        /**The request-URI to associate with the setting of the cookie. This value can affect the
default domain and path values of the created cookie.
        <em>Optional.</em>*/
        private String url;
        /**Cookie domain.
        <em>Optional.</em>*/
        private String domain;
        /**Cookie path.
        <em>Optional.</em>*/
        private String path;
        /**True if cookie is secure.
        <em>Optional.</em>*/
        private Boolean secure;
        /**True if cookie is http-only.
        <em>Optional.</em>*/
        private Boolean httpOnly;
        /**Cookie SameSite type.
        <em>Optional.</em>*/
        private CookieSameSite sameSite;
        /**Cookie expiration date, session cookie if not set
        <em>Optional.</em>*/
        private TimeSinceEpoch expires;
        public final SetCookieParameter name(String name) { this.name = name; return this; }
        public final SetCookieParameter setName(String name) { return name(name); }
        public final String name() { return name; }
        public final String getName() { return name(); }
        public final SetCookieParameter value(String value) { this.value = value; return this; }
        public final SetCookieParameter setValue(String value) { return value(value); }
        public final String value() { return value; }
        public final String getValue() { return value(); }
        public final SetCookieParameter url(@Nullable String url) { this.url = url; return this; }
        public final SetCookieParameter optUrl(@Nullable String url) { return url(url); }
        public final String url() { return url; }
        public final String getUrl() { return url(); }
        public final SetCookieParameter domain(@Nullable String domain) { this.domain = domain; return this; }
        public final SetCookieParameter optDomain(@Nullable String domain) { return domain(domain); }
        public final String domain() { return domain; }
        public final String getDomain() { return domain(); }
        public final SetCookieParameter path(@Nullable String path) { this.path = path; return this; }
        public final SetCookieParameter optPath(@Nullable String path) { return path(path); }
        public final String path() { return path; }
        public final String getPath() { return path(); }
        public final SetCookieParameter secure(@Nullable Boolean secure) { this.secure = secure; return this; }
        public final SetCookieParameter optSecure(@Nullable Boolean secure) { return secure(secure); }
        public final Boolean secure() { return secure; }
        public final Boolean getSecure() { return secure(); }
        public final SetCookieParameter httpOnly(@Nullable Boolean httpOnly) { this.httpOnly = httpOnly; return this; }
        public final SetCookieParameter optHttpOnly(@Nullable Boolean httpOnly) { return httpOnly(httpOnly); }
        public final Boolean httpOnly() { return httpOnly; }
        public final Boolean getHttpOnly() { return httpOnly(); }
        public final SetCookieParameter sameSite(@Nullable CookieSameSite sameSite) { this.sameSite = sameSite; return this; }
        public final SetCookieParameter optSameSite(@Nullable CookieSameSite sameSite) { return sameSite(sameSite); }
        public final CookieSameSite sameSite() { return sameSite; }
        public final CookieSameSite getSameSite() { return sameSite(); }
        public final SetCookieParameter expires(@Nullable TimeSinceEpoch expires) { this.expires = expires; return this; }
        public final SetCookieParameter optExpires(@Nullable TimeSinceEpoch expires) { return expires(expires); }
        public final TimeSinceEpoch expires() { return expires; }
        public final TimeSinceEpoch getExpires() { return expires(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (name == null) throw new IllegalArgumentException("Network.SetCookieParameter.name is necessary field.");
            if (value == null) throw new IllegalArgumentException("Network.SetCookieParameter.value is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"name\":").append('"').append(DomainBase.escapeJson(name)).append('"');
            strBuilder.append(",\"value\":").append('"').append(DomainBase.escapeJson(value)).append('"');
            if (url != null) strBuilder.append(",\"url\":").append('"').append(DomainBase.escapeJson(url)).append('"');
            if (domain != null) strBuilder.append(",\"domain\":").append('"').append(DomainBase.escapeJson(domain)).append('"');
            if (path != null) strBuilder.append(",\"path\":").append('"').append(DomainBase.escapeJson(path)).append('"');
            if (secure != null) strBuilder.append(",\"secure\":").append(secure);
            if (httpOnly != null) strBuilder.append(",\"httpOnly\":").append(httpOnly);
            if (sameSite != null) sameSite.toJson(strBuilder.append(",\"sameSite\":"));
            if (expires != null) expires.toJson(strBuilder.append(",\"expires\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public SetCookieParameter() {}
        public SetCookieParameter(
            @JsonProperty("name")String name,
            @JsonProperty("value")String value,
            @Nullable @JsonProperty("url")String url,
            @Nullable @JsonProperty("domain")String domain,
            @Nullable @JsonProperty("path")String path,
            @Nullable @JsonProperty("secure")Boolean secure,
            @Nullable @JsonProperty("httpOnly")Boolean httpOnly,
            @Nullable @JsonProperty("sameSite")CookieSameSite sameSite,
            @Nullable @JsonProperty("expires")TimeSinceEpoch expires
        ) {
            this();
            this.name = name;
            this.value = value;
            this.url = url;
            this.domain = domain;
            this.path = path;
            this.secure = secure;
            this.httpOnly = httpOnly;
            this.sameSite = sameSite;
            this.expires = expires;
        }
        public CompletableFuture<SetCookieResult> call() {
            return super.call("Network.setCookie", SetCookieResult.class,
                (code, msg)->new SetCookieResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetCookieResult> call(Executor exec) {
            return super.call("Network.setCookie", SetCookieResult.class,
                (code, msg)->new SetCookieResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setCookie.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetCookieResult extends ResultBase {
        /**True if successfully set cookie.*/
        private final Boolean success;
        public final Boolean success() { return success; }
        public final Boolean getSuccess() { return success(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"success\":").append(success);
            strBuilder.append('}');
            return strBuilder;
        }
        public SetCookieResult(
            @JsonProperty("success")Boolean success
        ) {
            this.success = success;
        }
        public SetCookieResult(ResultBase.FailedResult e) {
            super(e);
            success = null;
        }
    }
    /**Sets given cookies.*/
    public SetCookiesParameter setCookies() { final SetCookiesParameter v = new SetCookiesParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setCookies.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetCookiesParameter extends CommandBase {
        /**Cookies to be set.*/
        private List<CookieParam> cookies;
        public final SetCookiesParameter cookies(List<CookieParam> cookies) { this.cookies = cookies; return this; }
        public final SetCookiesParameter setCookies(List<CookieParam> cookies) { return cookies(cookies); }
        public final List<CookieParam> cookies() { return cookies; }
        public final List<CookieParam> getCookies() { return cookies(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (cookies == null) throw new IllegalArgumentException("Network.SetCookiesParameter.cookies is necessary field.");
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
        public SetCookiesParameter() {}
        public SetCookiesParameter(
            @JsonProperty("cookies")List<CookieParam> cookies
        ) {
            this();
            this.cookies = cookies;
        }
        public CompletableFuture<SetCookiesResult> call() {
            return super.call("Network.setCookies", SetCookiesResult.class,
                (code, msg)->new SetCookiesResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetCookiesResult> call(Executor exec) {
            return super.call("Network.setCookies", SetCookiesResult.class,
                (code, msg)->new SetCookiesResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setCookies.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetCookiesResult extends ResultBase {
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
        public SetCookiesResult() { super(); }
        public SetCookiesResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**For testing.
    <p><strong>Experimental.</strong></p>*/
    public SetDataSizeLimitsForTestParameter setDataSizeLimitsForTest() { final SetDataSizeLimitsForTestParameter v = new SetDataSizeLimitsForTestParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setDataSizeLimitsForTest.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetDataSizeLimitsForTestParameter extends CommandBase {
        /**Maximum total buffer size.*/
        private Integer maxTotalSize;
        /**Maximum per-resource size.*/
        private Integer maxResourceSize;
        public final SetDataSizeLimitsForTestParameter maxTotalSize(Integer maxTotalSize) { this.maxTotalSize = maxTotalSize; return this; }
        public final SetDataSizeLimitsForTestParameter setMaxTotalSize(Integer maxTotalSize) { return maxTotalSize(maxTotalSize); }
        public final Integer maxTotalSize() { return maxTotalSize; }
        public final Integer getMaxTotalSize() { return maxTotalSize(); }
        public final SetDataSizeLimitsForTestParameter maxResourceSize(Integer maxResourceSize) { this.maxResourceSize = maxResourceSize; return this; }
        public final SetDataSizeLimitsForTestParameter setMaxResourceSize(Integer maxResourceSize) { return maxResourceSize(maxResourceSize); }
        public final Integer maxResourceSize() { return maxResourceSize; }
        public final Integer getMaxResourceSize() { return maxResourceSize(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (maxTotalSize == null) throw new IllegalArgumentException("Network.SetDataSizeLimitsForTestParameter.maxTotalSize is necessary field.");
            if (maxResourceSize == null) throw new IllegalArgumentException("Network.SetDataSizeLimitsForTestParameter.maxResourceSize is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"maxTotalSize\":").append(maxTotalSize);
            strBuilder.append(",\"maxResourceSize\":").append(maxResourceSize);
            strBuilder.append('}');
            return strBuilder;
        }
        public SetDataSizeLimitsForTestParameter() {}
        public SetDataSizeLimitsForTestParameter(
            @JsonProperty("maxTotalSize")Integer maxTotalSize,
            @JsonProperty("maxResourceSize")Integer maxResourceSize
        ) {
            this();
            this.maxTotalSize = maxTotalSize;
            this.maxResourceSize = maxResourceSize;
        }
        public CompletableFuture<SetDataSizeLimitsForTestResult> call() {
            return super.call("Network.setDataSizeLimitsForTest", SetDataSizeLimitsForTestResult.class,
                (code, msg)->new SetDataSizeLimitsForTestResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetDataSizeLimitsForTestResult> call(Executor exec) {
            return super.call("Network.setDataSizeLimitsForTest", SetDataSizeLimitsForTestResult.class,
                (code, msg)->new SetDataSizeLimitsForTestResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setDataSizeLimitsForTest.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetDataSizeLimitsForTestResult extends ResultBase {
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
        public SetDataSizeLimitsForTestResult() { super(); }
        public SetDataSizeLimitsForTestResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Specifies whether to always send extra HTTP headers with the requests from this page.*/
    public SetExtraHTTPHeadersParameter setExtraHTTPHeaders() { final SetExtraHTTPHeadersParameter v = new SetExtraHTTPHeadersParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setExtraHTTPHeaders.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetExtraHTTPHeadersParameter extends CommandBase {
        /**Map with extra HTTP headers.*/
        private Headers headers;
        public final SetExtraHTTPHeadersParameter headers(Headers headers) { this.headers = headers; return this; }
        public final SetExtraHTTPHeadersParameter setHeaders(Headers headers) { return headers(headers); }
        public final Headers headers() { return headers; }
        public final Headers getHeaders() { return headers(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (headers == null) throw new IllegalArgumentException("Network.SetExtraHTTPHeadersParameter.headers is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            headers.toJson(strBuilder.append("\"headers\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public SetExtraHTTPHeadersParameter() {}
        public SetExtraHTTPHeadersParameter(
            @JsonProperty("headers")Headers headers
        ) {
            this();
            this.headers = headers;
        }
        public CompletableFuture<SetExtraHTTPHeadersResult> call() {
            return super.call("Network.setExtraHTTPHeaders", SetExtraHTTPHeadersResult.class,
                (code, msg)->new SetExtraHTTPHeadersResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetExtraHTTPHeadersResult> call(Executor exec) {
            return super.call("Network.setExtraHTTPHeaders", SetExtraHTTPHeadersResult.class,
                (code, msg)->new SetExtraHTTPHeadersResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setExtraHTTPHeaders.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetExtraHTTPHeadersResult extends ResultBase {
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
        public SetExtraHTTPHeadersResult() { super(); }
        public SetExtraHTTPHeadersResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Sets the requests to intercept that match a the provided patterns and optionally resource types.
    <p><strong>Experimental.</strong></p>*/
    public SetRequestInterceptionParameter setRequestInterception() { final SetRequestInterceptionParameter v = new SetRequestInterceptionParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setRequestInterception.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetRequestInterceptionParameter extends CommandBase {
        /**Requests matching any of these patterns will be forwarded and wait for the corresponding
continueInterceptedRequest call.*/
        private List<RequestPattern> patterns;
        public final SetRequestInterceptionParameter patterns(List<RequestPattern> patterns) { this.patterns = patterns; return this; }
        public final SetRequestInterceptionParameter setPatterns(List<RequestPattern> patterns) { return patterns(patterns); }
        public final List<RequestPattern> patterns() { return patterns; }
        public final List<RequestPattern> getPatterns() { return patterns(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (patterns == null) throw new IllegalArgumentException("Network.SetRequestInterceptionParameter.patterns is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
                        strBuilder.append("\"patterns\":[");
            patterns.get(0).toJson(strBuilder);
            for (int i = 1; i < patterns.size(); ++i)
                patterns.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public SetRequestInterceptionParameter() {}
        public SetRequestInterceptionParameter(
            @JsonProperty("patterns")List<RequestPattern> patterns
        ) {
            this();
            this.patterns = patterns;
        }
        public CompletableFuture<SetRequestInterceptionResult> call() {
            return super.call("Network.setRequestInterception", SetRequestInterceptionResult.class,
                (code, msg)->new SetRequestInterceptionResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetRequestInterceptionResult> call(Executor exec) {
            return super.call("Network.setRequestInterception", SetRequestInterceptionResult.class,
                (code, msg)->new SetRequestInterceptionResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setRequestInterception.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetRequestInterceptionResult extends ResultBase {
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
        public SetRequestInterceptionResult() { super(); }
        public SetRequestInterceptionResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Allows overriding user agent with the given string.*/
    public SetUserAgentOverrideParameter setUserAgentOverride() { final SetUserAgentOverrideParameter v = new SetUserAgentOverrideParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setUserAgentOverride.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetUserAgentOverrideParameter extends CommandBase {
        /**User agent to use.*/
        private String userAgent;
        public final SetUserAgentOverrideParameter userAgent(String userAgent) { this.userAgent = userAgent; return this; }
        public final SetUserAgentOverrideParameter setUserAgent(String userAgent) { return userAgent(userAgent); }
        public final String userAgent() { return userAgent; }
        public final String getUserAgent() { return userAgent(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (userAgent == null) throw new IllegalArgumentException("Network.SetUserAgentOverrideParameter.userAgent is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"userAgent\":").append('"').append(DomainBase.escapeJson(userAgent)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public SetUserAgentOverrideParameter() {}
        public SetUserAgentOverrideParameter(
            @JsonProperty("userAgent")String userAgent
        ) {
            this();
            this.userAgent = userAgent;
        }
        public CompletableFuture<SetUserAgentOverrideResult> call() {
            return super.call("Network.setUserAgentOverride", SetUserAgentOverrideResult.class,
                (code, msg)->new SetUserAgentOverrideResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetUserAgentOverrideResult> call(Executor exec) {
            return super.call("Network.setUserAgentOverride", SetUserAgentOverrideResult.class,
                (code, msg)->new SetUserAgentOverrideResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setUserAgentOverride.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetUserAgentOverrideResult extends ResultBase {
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
        public SetUserAgentOverrideResult() { super(); }
        public SetUserAgentOverrideResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Event parameter of Network.dataReceived.
     @see #onDataReceived*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class DataReceivedEventParameter implements CommonDomainType {
        /**Request identifier.*/
        private final RequestId requestId;
        /**Timestamp.*/
        private final MonotonicTime timestamp;
        /**Data chunk length.*/
        private final Integer dataLength;
        /**Actual bytes received (might be less than dataLength for compressed encodings).*/
        private final Integer encodedDataLength;
        public final RequestId requestId() { return requestId; }
        public final RequestId getRequestId() { return requestId(); }
        public final MonotonicTime timestamp() { return timestamp; }
        public final MonotonicTime getTimestamp() { return timestamp(); }
        public final Integer dataLength() { return dataLength; }
        public final Integer getDataLength() { return dataLength(); }
        public final Integer encodedDataLength() { return encodedDataLength; }
        public final Integer getEncodedDataLength() { return encodedDataLength(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            requestId.toJson(strBuilder.append("\"requestId\":"));
            timestamp.toJson(strBuilder.append(",\"timestamp\":"));
            strBuilder.append(",\"dataLength\":").append(dataLength);
            strBuilder.append(",\"encodedDataLength\":").append(encodedDataLength);
            strBuilder.append('}');
            return strBuilder;
        }
        DataReceivedEventParameter(
            @JsonProperty("requestId")RequestId requestId,
            @JsonProperty("timestamp")MonotonicTime timestamp,
            @JsonProperty("dataLength")Integer dataLength,
            @JsonProperty("encodedDataLength")Integer encodedDataLength
        ) {
            this.requestId = requestId;
            this.timestamp = timestamp;
            this.dataLength = dataLength;
            this.encodedDataLength = encodedDataLength;
        }
    }
    /**Fired when data chunk was received over the network.
     @see DataReceivedEventParameter*/
    public void onDataReceived(Consumer<DataReceivedEventParameter> callback) {
        registerEventCallback("Network.dataReceived", node -> {
            DataReceivedEventParameter param;
            try { param = EventCenter.deserializeJson(node, DataReceivedEventParameter.class); }
            catch (IOException e) { e.printStackTrace(); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of Network.eventSourceMessageReceived.
     @see #onEventSourceMessageReceived*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class EventSourceMessageReceivedEventParameter implements CommonDomainType {
        /**Request identifier.*/
        private final RequestId requestId;
        /**Timestamp.*/
        private final MonotonicTime timestamp;
        /**Message type.*/
        private final String eventName;
        /**Message identifier.*/
        private final String eventId;
        /**Message content.*/
        private final String data;
        public final RequestId requestId() { return requestId; }
        public final RequestId getRequestId() { return requestId(); }
        public final MonotonicTime timestamp() { return timestamp; }
        public final MonotonicTime getTimestamp() { return timestamp(); }
        public final String eventName() { return eventName; }
        public final String getEventName() { return eventName(); }
        public final String eventId() { return eventId; }
        public final String getEventId() { return eventId(); }
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
            requestId.toJson(strBuilder.append("\"requestId\":"));
            timestamp.toJson(strBuilder.append(",\"timestamp\":"));
            strBuilder.append(",\"eventName\":").append('"').append(DomainBase.escapeJson(eventName)).append('"');
            strBuilder.append(",\"eventId\":").append('"').append(DomainBase.escapeJson(eventId)).append('"');
            strBuilder.append(",\"data\":").append('"').append(DomainBase.escapeJson(data)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        EventSourceMessageReceivedEventParameter(
            @JsonProperty("requestId")RequestId requestId,
            @JsonProperty("timestamp")MonotonicTime timestamp,
            @JsonProperty("eventName")String eventName,
            @JsonProperty("eventId")String eventId,
            @JsonProperty("data")String data
        ) {
            this.requestId = requestId;
            this.timestamp = timestamp;
            this.eventName = eventName;
            this.eventId = eventId;
            this.data = data;
        }
    }
    /**Fired when EventSource message is received.
     @see EventSourceMessageReceivedEventParameter*/
    public void onEventSourceMessageReceived(Consumer<EventSourceMessageReceivedEventParameter> callback) {
        registerEventCallback("Network.eventSourceMessageReceived", node -> {
            EventSourceMessageReceivedEventParameter param;
            try { param = EventCenter.deserializeJson(node, EventSourceMessageReceivedEventParameter.class); }
            catch (IOException e) { e.printStackTrace(); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of Network.loadingFailed.
     @see #onLoadingFailed*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class LoadingFailedEventParameter implements CommonDomainType {
        /**Request identifier.*/
        private final RequestId requestId;
        /**Timestamp.*/
        private final MonotonicTime timestamp;
        /**Resource type.*/
        private final Page.ResourceType type;
        /**User friendly error message.*/
        private final String errorText;
        /**True if loading was canceled.
        <em>Optional.</em>*/
        private final Boolean canceled;
        /**The reason why loading was blocked, if any.
        <em>Optional.</em>*/
        private final BlockedReason blockedReason;
        public final RequestId requestId() { return requestId; }
        public final RequestId getRequestId() { return requestId(); }
        public final MonotonicTime timestamp() { return timestamp; }
        public final MonotonicTime getTimestamp() { return timestamp(); }
        public final Page.ResourceType type() { return type; }
        public final Page.ResourceType getType() { return type(); }
        public final String errorText() { return errorText; }
        public final String getErrorText() { return errorText(); }
        public final Boolean canceled() { return canceled; }
        public final Boolean getCanceled() { return canceled(); }
        public final BlockedReason blockedReason() { return blockedReason; }
        public final BlockedReason getBlockedReason() { return blockedReason(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            requestId.toJson(strBuilder.append("\"requestId\":"));
            timestamp.toJson(strBuilder.append(",\"timestamp\":"));
            type.toJson(strBuilder.append(",\"type\":"));
            strBuilder.append(",\"errorText\":").append('"').append(DomainBase.escapeJson(errorText)).append('"');
            if (canceled != null) strBuilder.append(",\"canceled\":").append(canceled);
            if (blockedReason != null) blockedReason.toJson(strBuilder.append(",\"blockedReason\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        LoadingFailedEventParameter(
            @JsonProperty("requestId")RequestId requestId,
            @JsonProperty("timestamp")MonotonicTime timestamp,
            @JsonProperty("type")Page.ResourceType type,
            @JsonProperty("errorText")String errorText,
            @Nullable @JsonProperty("canceled")Boolean canceled,
            @Nullable @JsonProperty("blockedReason")BlockedReason blockedReason
        ) {
            this.requestId = requestId;
            this.timestamp = timestamp;
            this.type = type;
            this.errorText = errorText;
            this.canceled = canceled;
            this.blockedReason = blockedReason;
        }
    }
    /**Fired when HTTP request has failed to load.
     @see LoadingFailedEventParameter*/
    public void onLoadingFailed(Consumer<LoadingFailedEventParameter> callback) {
        registerEventCallback("Network.loadingFailed", node -> {
            LoadingFailedEventParameter param;
            try { param = EventCenter.deserializeJson(node, LoadingFailedEventParameter.class); }
            catch (IOException e) { e.printStackTrace(); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of Network.loadingFinished.
     @see #onLoadingFinished*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class LoadingFinishedEventParameter implements CommonDomainType {
        /**Request identifier.*/
        private final RequestId requestId;
        /**Timestamp.*/
        private final MonotonicTime timestamp;
        /**Total number of bytes received for this request.*/
        private final Double encodedDataLength;
        /**Set when response was blocked due to being cross-site document response.
        <em>Optional.</em>*/
        private final Boolean blockedCrossSiteDocument;
        public final RequestId requestId() { return requestId; }
        public final RequestId getRequestId() { return requestId(); }
        public final MonotonicTime timestamp() { return timestamp; }
        public final MonotonicTime getTimestamp() { return timestamp(); }
        public final Double encodedDataLength() { return encodedDataLength; }
        public final Double getEncodedDataLength() { return encodedDataLength(); }
        public final Boolean blockedCrossSiteDocument() { return blockedCrossSiteDocument; }
        public final Boolean getBlockedCrossSiteDocument() { return blockedCrossSiteDocument(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            requestId.toJson(strBuilder.append("\"requestId\":"));
            timestamp.toJson(strBuilder.append(",\"timestamp\":"));
            strBuilder.append(",\"encodedDataLength\":").append(encodedDataLength);
            if (blockedCrossSiteDocument != null) strBuilder.append(",\"blockedCrossSiteDocument\":").append(blockedCrossSiteDocument);
            strBuilder.append('}');
            return strBuilder;
        }
        LoadingFinishedEventParameter(
            @JsonProperty("requestId")RequestId requestId,
            @JsonProperty("timestamp")MonotonicTime timestamp,
            @JsonProperty("encodedDataLength")Double encodedDataLength,
            @Nullable @JsonProperty("blockedCrossSiteDocument")Boolean blockedCrossSiteDocument
        ) {
            this.requestId = requestId;
            this.timestamp = timestamp;
            this.encodedDataLength = encodedDataLength;
            this.blockedCrossSiteDocument = blockedCrossSiteDocument;
        }
    }
    /**Fired when HTTP request has finished loading.
     @see LoadingFinishedEventParameter*/
    public void onLoadingFinished(Consumer<LoadingFinishedEventParameter> callback) {
        registerEventCallback("Network.loadingFinished", node -> {
            LoadingFinishedEventParameter param;
            try { param = EventCenter.deserializeJson(node, LoadingFinishedEventParameter.class); }
            catch (IOException e) { e.printStackTrace(); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of Network.requestIntercepted.
    <p><strong>Experimental.</strong></p>
     @see #onRequestIntercepted*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class RequestInterceptedEventParameter implements CommonDomainType {
        /**Each request the page makes will have a unique id, however if any redirects are encountered
while processing that fetch, they will be reported with the same id as the original fetch.
Likewise if HTTP authentication is needed then the same fetch id will be used.*/
        private final InterceptionId interceptionId;
        /**&lt;No document in protocol.&gt;*/
        private final Request request;
        /**The id of the frame that initiated the request.*/
        private final Page.FrameId frameId;
        /**How the requested resource will be used.*/
        private final Page.ResourceType resourceType;
        /**Whether this is a navigation request, which can abort the navigation completely.*/
        private final Boolean isNavigationRequest;
        /**Redirect location, only sent if a redirect was intercepted.
        <em>Optional.</em>*/
        private final String redirectUrl;
        /**Details of the Authorization Challenge encountered. If this is set then
continueInterceptedRequest must contain an authChallengeResponse.
        <em>Optional.</em>*/
        private final AuthChallenge authChallenge;
        /**Response error if intercepted at response stage or if redirect occurred while intercepting
request.
        <em>Optional.</em>*/
        private final ErrorReason responseErrorReason;
        /**Response code if intercepted at response stage or if redirect occurred while intercepting
request or auth retry occurred.
        <em>Optional.</em>*/
        private final Integer responseStatusCode;
        /**Response headers if intercepted at the response stage or if redirect occurred while
intercepting request or auth retry occurred.
        <em>Optional.</em>*/
        private final Headers responseHeaders;
        public final InterceptionId interceptionId() { return interceptionId; }
        public final InterceptionId getInterceptionId() { return interceptionId(); }
        public final Request request() { return request; }
        public final Request getRequest() { return request(); }
        public final Page.FrameId frameId() { return frameId; }
        public final Page.FrameId getFrameId() { return frameId(); }
        public final Page.ResourceType resourceType() { return resourceType; }
        public final Page.ResourceType getResourceType() { return resourceType(); }
        public final Boolean isNavigationRequest() { return isNavigationRequest; }
        public final Boolean getIsNavigationRequest() { return isNavigationRequest(); }
        public final String redirectUrl() { return redirectUrl; }
        public final String getRedirectUrl() { return redirectUrl(); }
        public final AuthChallenge authChallenge() { return authChallenge; }
        public final AuthChallenge getAuthChallenge() { return authChallenge(); }
        public final ErrorReason responseErrorReason() { return responseErrorReason; }
        public final ErrorReason getResponseErrorReason() { return responseErrorReason(); }
        public final Integer responseStatusCode() { return responseStatusCode; }
        public final Integer getResponseStatusCode() { return responseStatusCode(); }
        public final Headers responseHeaders() { return responseHeaders; }
        public final Headers getResponseHeaders() { return responseHeaders(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            interceptionId.toJson(strBuilder.append("\"interceptionId\":"));
            request.toJson(strBuilder.append(",\"request\":"));
            frameId.toJson(strBuilder.append(",\"frameId\":"));
            resourceType.toJson(strBuilder.append(",\"resourceType\":"));
            strBuilder.append(",\"isNavigationRequest\":").append(isNavigationRequest);
            if (redirectUrl != null) strBuilder.append(",\"redirectUrl\":").append('"').append(DomainBase.escapeJson(redirectUrl)).append('"');
            if (authChallenge != null) authChallenge.toJson(strBuilder.append(",\"authChallenge\":"));
            if (responseErrorReason != null) responseErrorReason.toJson(strBuilder.append(",\"responseErrorReason\":"));
            if (responseStatusCode != null) strBuilder.append(",\"responseStatusCode\":").append(responseStatusCode);
            if (responseHeaders != null) responseHeaders.toJson(strBuilder.append(",\"responseHeaders\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        RequestInterceptedEventParameter(
            @JsonProperty("interceptionId")InterceptionId interceptionId,
            @JsonProperty("request")Request request,
            @JsonProperty("frameId")Page.FrameId frameId,
            @JsonProperty("resourceType")Page.ResourceType resourceType,
            @JsonProperty("isNavigationRequest")Boolean isNavigationRequest,
            @Nullable @JsonProperty("redirectUrl")String redirectUrl,
            @Nullable @JsonProperty("authChallenge")AuthChallenge authChallenge,
            @Nullable @JsonProperty("responseErrorReason")ErrorReason responseErrorReason,
            @Nullable @JsonProperty("responseStatusCode")Integer responseStatusCode,
            @Nullable @JsonProperty("responseHeaders")Headers responseHeaders
        ) {
            this.interceptionId = interceptionId;
            this.request = request;
            this.frameId = frameId;
            this.resourceType = resourceType;
            this.isNavigationRequest = isNavigationRequest;
            this.redirectUrl = redirectUrl;
            this.authChallenge = authChallenge;
            this.responseErrorReason = responseErrorReason;
            this.responseStatusCode = responseStatusCode;
            this.responseHeaders = responseHeaders;
        }
    }
    /**Details of an intercepted HTTP request, which must be either allowed, blocked, modified or
mocked.
    <p><strong>Experimental.</strong></p>
     @see RequestInterceptedEventParameter*/
    public void onRequestIntercepted(Consumer<RequestInterceptedEventParameter> callback) {
        registerEventCallback("Network.requestIntercepted", node -> {
            RequestInterceptedEventParameter param;
            try { param = EventCenter.deserializeJson(node, RequestInterceptedEventParameter.class); }
            catch (IOException e) { e.printStackTrace(); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of Network.requestServedFromCache.
     @see #onRequestServedFromCache*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class RequestServedFromCacheEventParameter implements CommonDomainType {
        /**Request identifier.*/
        private final RequestId requestId;
        public final RequestId requestId() { return requestId; }
        public final RequestId getRequestId() { return requestId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            requestId.toJson(strBuilder.append("\"requestId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        RequestServedFromCacheEventParameter(
            @JsonProperty("requestId")RequestId requestId
        ) {
            this.requestId = requestId;
        }
    }
    /**Fired if request ended up loading from cache.
     @see RequestServedFromCacheEventParameter*/
    public void onRequestServedFromCache(Consumer<RequestServedFromCacheEventParameter> callback) {
        registerEventCallback("Network.requestServedFromCache", node -> {
            RequestServedFromCacheEventParameter param;
            try { param = EventCenter.deserializeJson(node, RequestServedFromCacheEventParameter.class); }
            catch (IOException e) { e.printStackTrace(); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of Network.requestWillBeSent.
     @see #onRequestWillBeSent*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class RequestWillBeSentEventParameter implements CommonDomainType {
        /**Request identifier.*/
        private final RequestId requestId;
        /**Loader identifier. Empty string if the request is fetched from worker.*/
        private final LoaderId loaderId;
        /**URL of the document this request is loaded for.*/
        private final String documentURL;
        /**Request data.*/
        private final Request request;
        /**Timestamp.*/
        private final MonotonicTime timestamp;
        /**Timestamp.*/
        private final TimeSinceEpoch wallTime;
        /**Request initiator.*/
        private final Initiator initiator;
        /**Redirect response data.
        <em>Optional.</em>*/
        private final Response redirectResponse;
        /**Type of this resource.
        <em>Optional.</em>*/
        private final Page.ResourceType type;
        /**Frame identifier.
        <em>Optional.</em>*/
        private final Page.FrameId frameId;
        /**Whether the request is initiated by a user gesture. Defaults to false.
        <em>Optional.</em>*/
        private final Boolean hasUserGesture;
        public final RequestId requestId() { return requestId; }
        public final RequestId getRequestId() { return requestId(); }
        public final LoaderId loaderId() { return loaderId; }
        public final LoaderId getLoaderId() { return loaderId(); }
        public final String documentURL() { return documentURL; }
        public final String getDocumentURL() { return documentURL(); }
        public final Request request() { return request; }
        public final Request getRequest() { return request(); }
        public final MonotonicTime timestamp() { return timestamp; }
        public final MonotonicTime getTimestamp() { return timestamp(); }
        public final TimeSinceEpoch wallTime() { return wallTime; }
        public final TimeSinceEpoch getWallTime() { return wallTime(); }
        public final Initiator initiator() { return initiator; }
        public final Initiator getInitiator() { return initiator(); }
        public final Response redirectResponse() { return redirectResponse; }
        public final Response getRedirectResponse() { return redirectResponse(); }
        public final Page.ResourceType type() { return type; }
        public final Page.ResourceType getType() { return type(); }
        public final Page.FrameId frameId() { return frameId; }
        public final Page.FrameId getFrameId() { return frameId(); }
        public final Boolean hasUserGesture() { return hasUserGesture; }
        public final Boolean getHasUserGesture() { return hasUserGesture(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            requestId.toJson(strBuilder.append("\"requestId\":"));
            loaderId.toJson(strBuilder.append(",\"loaderId\":"));
            strBuilder.append(",\"documentURL\":").append('"').append(DomainBase.escapeJson(documentURL)).append('"');
            request.toJson(strBuilder.append(",\"request\":"));
            timestamp.toJson(strBuilder.append(",\"timestamp\":"));
            wallTime.toJson(strBuilder.append(",\"wallTime\":"));
            initiator.toJson(strBuilder.append(",\"initiator\":"));
            if (redirectResponse != null) redirectResponse.toJson(strBuilder.append(",\"redirectResponse\":"));
            if (type != null) type.toJson(strBuilder.append(",\"type\":"));
            if (frameId != null) frameId.toJson(strBuilder.append(",\"frameId\":"));
            if (hasUserGesture != null) strBuilder.append(",\"hasUserGesture\":").append(hasUserGesture);
            strBuilder.append('}');
            return strBuilder;
        }
        RequestWillBeSentEventParameter(
            @JsonProperty("requestId")RequestId requestId,
            @JsonProperty("loaderId")LoaderId loaderId,
            @JsonProperty("documentURL")String documentURL,
            @JsonProperty("request")Request request,
            @JsonProperty("timestamp")MonotonicTime timestamp,
            @JsonProperty("wallTime")TimeSinceEpoch wallTime,
            @JsonProperty("initiator")Initiator initiator,
            @Nullable @JsonProperty("redirectResponse")Response redirectResponse,
            @Nullable @JsonProperty("type")Page.ResourceType type,
            @Nullable @JsonProperty("frameId")Page.FrameId frameId,
            @Nullable @JsonProperty("hasUserGesture")Boolean hasUserGesture
        ) {
            this.requestId = requestId;
            this.loaderId = loaderId;
            this.documentURL = documentURL;
            this.request = request;
            this.timestamp = timestamp;
            this.wallTime = wallTime;
            this.initiator = initiator;
            this.redirectResponse = redirectResponse;
            this.type = type;
            this.frameId = frameId;
            this.hasUserGesture = hasUserGesture;
        }
    }
    /**Fired when page is about to send HTTP request.
     @see RequestWillBeSentEventParameter*/
    public void onRequestWillBeSent(Consumer<RequestWillBeSentEventParameter> callback) {
        registerEventCallback("Network.requestWillBeSent", node -> {
            RequestWillBeSentEventParameter param;
            try { param = EventCenter.deserializeJson(node, RequestWillBeSentEventParameter.class); }
            catch (IOException e) { e.printStackTrace(); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of Network.resourceChangedPriority.
    <p><strong>Experimental.</strong></p>
     @see #onResourceChangedPriority*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ResourceChangedPriorityEventParameter implements CommonDomainType {
        /**Request identifier.*/
        private final RequestId requestId;
        /**New priority*/
        private final ResourcePriority newPriority;
        /**Timestamp.*/
        private final MonotonicTime timestamp;
        public final RequestId requestId() { return requestId; }
        public final RequestId getRequestId() { return requestId(); }
        public final ResourcePriority newPriority() { return newPriority; }
        public final ResourcePriority getNewPriority() { return newPriority(); }
        public final MonotonicTime timestamp() { return timestamp; }
        public final MonotonicTime getTimestamp() { return timestamp(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            requestId.toJson(strBuilder.append("\"requestId\":"));
            newPriority.toJson(strBuilder.append(",\"newPriority\":"));
            timestamp.toJson(strBuilder.append(",\"timestamp\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        ResourceChangedPriorityEventParameter(
            @JsonProperty("requestId")RequestId requestId,
            @JsonProperty("newPriority")ResourcePriority newPriority,
            @JsonProperty("timestamp")MonotonicTime timestamp
        ) {
            this.requestId = requestId;
            this.newPriority = newPriority;
            this.timestamp = timestamp;
        }
    }
    /**Fired when resource loading priority is changed
    <p><strong>Experimental.</strong></p>
     @see ResourceChangedPriorityEventParameter*/
    public void onResourceChangedPriority(Consumer<ResourceChangedPriorityEventParameter> callback) {
        registerEventCallback("Network.resourceChangedPriority", node -> {
            ResourceChangedPriorityEventParameter param;
            try { param = EventCenter.deserializeJson(node, ResourceChangedPriorityEventParameter.class); }
            catch (IOException e) { e.printStackTrace(); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of Network.responseReceived.
     @see #onResponseReceived*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ResponseReceivedEventParameter implements CommonDomainType {
        /**Request identifier.*/
        private final RequestId requestId;
        /**Loader identifier. Empty string if the request is fetched from worker.*/
        private final LoaderId loaderId;
        /**Timestamp.*/
        private final MonotonicTime timestamp;
        /**Resource type.*/
        private final Page.ResourceType type;
        /**Response data.*/
        private final Response response;
        /**Frame identifier.
        <em>Optional.</em>*/
        private final Page.FrameId frameId;
        public final RequestId requestId() { return requestId; }
        public final RequestId getRequestId() { return requestId(); }
        public final LoaderId loaderId() { return loaderId; }
        public final LoaderId getLoaderId() { return loaderId(); }
        public final MonotonicTime timestamp() { return timestamp; }
        public final MonotonicTime getTimestamp() { return timestamp(); }
        public final Page.ResourceType type() { return type; }
        public final Page.ResourceType getType() { return type(); }
        public final Response response() { return response; }
        public final Response getResponse() { return response(); }
        public final Page.FrameId frameId() { return frameId; }
        public final Page.FrameId getFrameId() { return frameId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            requestId.toJson(strBuilder.append("\"requestId\":"));
            loaderId.toJson(strBuilder.append(",\"loaderId\":"));
            timestamp.toJson(strBuilder.append(",\"timestamp\":"));
            type.toJson(strBuilder.append(",\"type\":"));
            response.toJson(strBuilder.append(",\"response\":"));
            if (frameId != null) frameId.toJson(strBuilder.append(",\"frameId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        ResponseReceivedEventParameter(
            @JsonProperty("requestId")RequestId requestId,
            @JsonProperty("loaderId")LoaderId loaderId,
            @JsonProperty("timestamp")MonotonicTime timestamp,
            @JsonProperty("type")Page.ResourceType type,
            @JsonProperty("response")Response response,
            @Nullable @JsonProperty("frameId")Page.FrameId frameId
        ) {
            this.requestId = requestId;
            this.loaderId = loaderId;
            this.timestamp = timestamp;
            this.type = type;
            this.response = response;
            this.frameId = frameId;
        }
    }
    /**Fired when HTTP response is available.
     @see ResponseReceivedEventParameter*/
    public void onResponseReceived(Consumer<ResponseReceivedEventParameter> callback) {
        registerEventCallback("Network.responseReceived", node -> {
            ResponseReceivedEventParameter param;
            try { param = EventCenter.deserializeJson(node, ResponseReceivedEventParameter.class); }
            catch (IOException e) { e.printStackTrace(); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of Network.webSocketClosed.
     @see #onWebSocketClosed*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class WebSocketClosedEventParameter implements CommonDomainType {
        /**Request identifier.*/
        private final RequestId requestId;
        /**Timestamp.*/
        private final MonotonicTime timestamp;
        public final RequestId requestId() { return requestId; }
        public final RequestId getRequestId() { return requestId(); }
        public final MonotonicTime timestamp() { return timestamp; }
        public final MonotonicTime getTimestamp() { return timestamp(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            requestId.toJson(strBuilder.append("\"requestId\":"));
            timestamp.toJson(strBuilder.append(",\"timestamp\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        WebSocketClosedEventParameter(
            @JsonProperty("requestId")RequestId requestId,
            @JsonProperty("timestamp")MonotonicTime timestamp
        ) {
            this.requestId = requestId;
            this.timestamp = timestamp;
        }
    }
    /**Fired when WebSocket is closed.
     @see WebSocketClosedEventParameter*/
    public void onWebSocketClosed(Consumer<WebSocketClosedEventParameter> callback) {
        registerEventCallback("Network.webSocketClosed", node -> {
            WebSocketClosedEventParameter param;
            try { param = EventCenter.deserializeJson(node, WebSocketClosedEventParameter.class); }
            catch (IOException e) { e.printStackTrace(); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of Network.webSocketCreated.
     @see #onWebSocketCreated*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class WebSocketCreatedEventParameter implements CommonDomainType {
        /**Request identifier.*/
        private final RequestId requestId;
        /**WebSocket request URL.*/
        private final String url;
        /**Request initiator.
        <em>Optional.</em>*/
        private final Initiator initiator;
        public final RequestId requestId() { return requestId; }
        public final RequestId getRequestId() { return requestId(); }
        public final String url() { return url; }
        public final String getUrl() { return url(); }
        public final Initiator initiator() { return initiator; }
        public final Initiator getInitiator() { return initiator(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            requestId.toJson(strBuilder.append("\"requestId\":"));
            strBuilder.append(",\"url\":").append('"').append(DomainBase.escapeJson(url)).append('"');
            if (initiator != null) initiator.toJson(strBuilder.append(",\"initiator\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        WebSocketCreatedEventParameter(
            @JsonProperty("requestId")RequestId requestId,
            @JsonProperty("url")String url,
            @Nullable @JsonProperty("initiator")Initiator initiator
        ) {
            this.requestId = requestId;
            this.url = url;
            this.initiator = initiator;
        }
    }
    /**Fired upon WebSocket creation.
     @see WebSocketCreatedEventParameter*/
    public void onWebSocketCreated(Consumer<WebSocketCreatedEventParameter> callback) {
        registerEventCallback("Network.webSocketCreated", node -> {
            WebSocketCreatedEventParameter param;
            try { param = EventCenter.deserializeJson(node, WebSocketCreatedEventParameter.class); }
            catch (IOException e) { e.printStackTrace(); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of Network.webSocketFrameError.
     @see #onWebSocketFrameError*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class WebSocketFrameErrorEventParameter implements CommonDomainType {
        /**Request identifier.*/
        private final RequestId requestId;
        /**Timestamp.*/
        private final MonotonicTime timestamp;
        /**WebSocket frame error message.*/
        private final String errorMessage;
        public final RequestId requestId() { return requestId; }
        public final RequestId getRequestId() { return requestId(); }
        public final MonotonicTime timestamp() { return timestamp; }
        public final MonotonicTime getTimestamp() { return timestamp(); }
        public final String errorMessage() { return errorMessage; }
        public final String getErrorMessage() { return errorMessage(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            requestId.toJson(strBuilder.append("\"requestId\":"));
            timestamp.toJson(strBuilder.append(",\"timestamp\":"));
            strBuilder.append(",\"errorMessage\":").append('"').append(DomainBase.escapeJson(errorMessage)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        WebSocketFrameErrorEventParameter(
            @JsonProperty("requestId")RequestId requestId,
            @JsonProperty("timestamp")MonotonicTime timestamp,
            @JsonProperty("errorMessage")String errorMessage
        ) {
            this.requestId = requestId;
            this.timestamp = timestamp;
            this.errorMessage = errorMessage;
        }
    }
    /**Fired when WebSocket frame error occurs.
     @see WebSocketFrameErrorEventParameter*/
    public void onWebSocketFrameError(Consumer<WebSocketFrameErrorEventParameter> callback) {
        registerEventCallback("Network.webSocketFrameError", node -> {
            WebSocketFrameErrorEventParameter param;
            try { param = EventCenter.deserializeJson(node, WebSocketFrameErrorEventParameter.class); }
            catch (IOException e) { e.printStackTrace(); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of Network.webSocketFrameReceived.
     @see #onWebSocketFrameReceived*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class WebSocketFrameReceivedEventParameter implements CommonDomainType {
        /**Request identifier.*/
        private final RequestId requestId;
        /**Timestamp.*/
        private final MonotonicTime timestamp;
        /**WebSocket response data.*/
        private final WebSocketFrame response;
        public final RequestId requestId() { return requestId; }
        public final RequestId getRequestId() { return requestId(); }
        public final MonotonicTime timestamp() { return timestamp; }
        public final MonotonicTime getTimestamp() { return timestamp(); }
        public final WebSocketFrame response() { return response; }
        public final WebSocketFrame getResponse() { return response(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            requestId.toJson(strBuilder.append("\"requestId\":"));
            timestamp.toJson(strBuilder.append(",\"timestamp\":"));
            response.toJson(strBuilder.append(",\"response\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        WebSocketFrameReceivedEventParameter(
            @JsonProperty("requestId")RequestId requestId,
            @JsonProperty("timestamp")MonotonicTime timestamp,
            @JsonProperty("response")WebSocketFrame response
        ) {
            this.requestId = requestId;
            this.timestamp = timestamp;
            this.response = response;
        }
    }
    /**Fired when WebSocket frame is received.
     @see WebSocketFrameReceivedEventParameter*/
    public void onWebSocketFrameReceived(Consumer<WebSocketFrameReceivedEventParameter> callback) {
        registerEventCallback("Network.webSocketFrameReceived", node -> {
            WebSocketFrameReceivedEventParameter param;
            try { param = EventCenter.deserializeJson(node, WebSocketFrameReceivedEventParameter.class); }
            catch (IOException e) { e.printStackTrace(); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of Network.webSocketFrameSent.
     @see #onWebSocketFrameSent*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class WebSocketFrameSentEventParameter implements CommonDomainType {
        /**Request identifier.*/
        private final RequestId requestId;
        /**Timestamp.*/
        private final MonotonicTime timestamp;
        /**WebSocket response data.*/
        private final WebSocketFrame response;
        public final RequestId requestId() { return requestId; }
        public final RequestId getRequestId() { return requestId(); }
        public final MonotonicTime timestamp() { return timestamp; }
        public final MonotonicTime getTimestamp() { return timestamp(); }
        public final WebSocketFrame response() { return response; }
        public final WebSocketFrame getResponse() { return response(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            requestId.toJson(strBuilder.append("\"requestId\":"));
            timestamp.toJson(strBuilder.append(",\"timestamp\":"));
            response.toJson(strBuilder.append(",\"response\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        WebSocketFrameSentEventParameter(
            @JsonProperty("requestId")RequestId requestId,
            @JsonProperty("timestamp")MonotonicTime timestamp,
            @JsonProperty("response")WebSocketFrame response
        ) {
            this.requestId = requestId;
            this.timestamp = timestamp;
            this.response = response;
        }
    }
    /**Fired when WebSocket frame is sent.
     @see WebSocketFrameSentEventParameter*/
    public void onWebSocketFrameSent(Consumer<WebSocketFrameSentEventParameter> callback) {
        registerEventCallback("Network.webSocketFrameSent", node -> {
            WebSocketFrameSentEventParameter param;
            try { param = EventCenter.deserializeJson(node, WebSocketFrameSentEventParameter.class); }
            catch (IOException e) { e.printStackTrace(); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of Network.webSocketHandshakeResponseReceived.
     @see #onWebSocketHandshakeResponseReceived*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class WebSocketHandshakeResponseReceivedEventParameter implements CommonDomainType {
        /**Request identifier.*/
        private final RequestId requestId;
        /**Timestamp.*/
        private final MonotonicTime timestamp;
        /**WebSocket response data.*/
        private final WebSocketResponse response;
        public final RequestId requestId() { return requestId; }
        public final RequestId getRequestId() { return requestId(); }
        public final MonotonicTime timestamp() { return timestamp; }
        public final MonotonicTime getTimestamp() { return timestamp(); }
        public final WebSocketResponse response() { return response; }
        public final WebSocketResponse getResponse() { return response(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            requestId.toJson(strBuilder.append("\"requestId\":"));
            timestamp.toJson(strBuilder.append(",\"timestamp\":"));
            response.toJson(strBuilder.append(",\"response\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        WebSocketHandshakeResponseReceivedEventParameter(
            @JsonProperty("requestId")RequestId requestId,
            @JsonProperty("timestamp")MonotonicTime timestamp,
            @JsonProperty("response")WebSocketResponse response
        ) {
            this.requestId = requestId;
            this.timestamp = timestamp;
            this.response = response;
        }
    }
    /**Fired when WebSocket handshake response becomes available.
     @see WebSocketHandshakeResponseReceivedEventParameter*/
    public void onWebSocketHandshakeResponseReceived(Consumer<WebSocketHandshakeResponseReceivedEventParameter> callback) {
        registerEventCallback("Network.webSocketHandshakeResponseReceived", node -> {
            WebSocketHandshakeResponseReceivedEventParameter param;
            try { param = EventCenter.deserializeJson(node, WebSocketHandshakeResponseReceivedEventParameter.class); }
            catch (IOException e) { e.printStackTrace(); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of Network.webSocketWillSendHandshakeRequest.
     @see #onWebSocketWillSendHandshakeRequest*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class WebSocketWillSendHandshakeRequestEventParameter implements CommonDomainType {
        /**Request identifier.*/
        private final RequestId requestId;
        /**Timestamp.*/
        private final MonotonicTime timestamp;
        /**UTC Timestamp.*/
        private final TimeSinceEpoch wallTime;
        /**WebSocket request data.*/
        private final WebSocketRequest request;
        public final RequestId requestId() { return requestId; }
        public final RequestId getRequestId() { return requestId(); }
        public final MonotonicTime timestamp() { return timestamp; }
        public final MonotonicTime getTimestamp() { return timestamp(); }
        public final TimeSinceEpoch wallTime() { return wallTime; }
        public final TimeSinceEpoch getWallTime() { return wallTime(); }
        public final WebSocketRequest request() { return request; }
        public final WebSocketRequest getRequest() { return request(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            requestId.toJson(strBuilder.append("\"requestId\":"));
            timestamp.toJson(strBuilder.append(",\"timestamp\":"));
            wallTime.toJson(strBuilder.append(",\"wallTime\":"));
            request.toJson(strBuilder.append(",\"request\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        WebSocketWillSendHandshakeRequestEventParameter(
            @JsonProperty("requestId")RequestId requestId,
            @JsonProperty("timestamp")MonotonicTime timestamp,
            @JsonProperty("wallTime")TimeSinceEpoch wallTime,
            @JsonProperty("request")WebSocketRequest request
        ) {
            this.requestId = requestId;
            this.timestamp = timestamp;
            this.wallTime = wallTime;
            this.request = request;
        }
    }
    /**Fired when WebSocket is about to initiate handshake.
     @see WebSocketWillSendHandshakeRequestEventParameter*/
    public void onWebSocketWillSendHandshakeRequest(Consumer<WebSocketWillSendHandshakeRequestEventParameter> callback) {
        registerEventCallback("Network.webSocketWillSendHandshakeRequest", node -> {
            WebSocketWillSendHandshakeRequestEventParameter param;
            try { param = EventCenter.deserializeJson(node, WebSocketWillSendHandshakeRequestEventParameter.class); }
            catch (IOException e) { e.printStackTrace(); return; }
            callback.accept(param);
        });
    }
}

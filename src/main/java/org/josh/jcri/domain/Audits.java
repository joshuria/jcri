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

/**Audits domain allows investigation of page violations and possible improvements.
<p>From: browser_protocol.json</p>
<p>Protocol version: 1.3</p>
<p><strong>Experimental.</strong></p>
 @see Network
 @author Joshua*/
@ParametersAreNonnullByDefault public class Audits extends DomainBase {
    public Audits(EventCenter evt, WebSocket ws) { super(evt, ws); }
    /**Returns the response body and size if it were re-encoded with the specified settings. Only
applies to images.*/
    public GetEncodedResponseParameter getEncodedResponse() { final GetEncodedResponseParameter v = new GetEncodedResponseParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of getEncodedResponse.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetEncodedResponseParameter extends CommandBase {
        /**Identifier of the network request to get content for.*/
        private Network.RequestId requestId;
        /**The encoding to use.*/
        @ParametersAreNonnullByDefault public enum Encoding implements CommonDomainType {
            Webp("webp"),
            Jpeg("jpeg"),
            Png("png");

            private final String _value;
            /**Convert string representation to type.
             @throws IllegalArgumentException if given value cannot convert to enum type. */
            @JsonCreator public static Encoding of(String value) {
                return Enum.valueOf(Encoding.class, value.substring(0, 1).toUpperCase() + value.substring(1));
            }
            Encoding(String value) { _value = value; }
            /**Check if parameter fields of method are all valid. */
            @Override public void check() throws IllegalArgumentException { /* Need not check */ }
            /**Convert method parameter object into json string and append into string builder.
             @return string builder instance that is given in parameter (for chaining coding style use.) */
            @Override public StringBuilder toJson(StringBuilder strBuilder) { return strBuilder.append('"').append(_value).append('"'); }
            @Override public String toString() { return "\"" + _value + "\""; }
        }
        private Encoding encoding;
        /**The quality of the encoding (0-1). (defaults to 1)
        <em>Optional.</em>*/
        private Double quality;
        /**Whether to only return the size information (defaults to false).
        <em>Optional.</em>*/
        private Boolean sizeOnly;
        public final GetEncodedResponseParameter requestId(Network.RequestId requestId) { this.requestId = requestId; return this; }
        public final GetEncodedResponseParameter setRequestId(Network.RequestId requestId) { return requestId(requestId); }
        public final Network.RequestId requestId() { return requestId; }
        public final Network.RequestId getRequestId() { return requestId(); }
        public final GetEncodedResponseParameter encoding(Encoding encoding) { this.encoding = encoding; return this; }
        public final GetEncodedResponseParameter setEncoding(Encoding encoding) { return encoding(encoding); }
        public final Encoding encoding() { return encoding; }
        public final Encoding getEncoding() { return encoding(); }
        public final GetEncodedResponseParameter quality(@Nullable Double quality) { this.quality = quality; return this; }
        public final GetEncodedResponseParameter optQuality(@Nullable Double quality) { return quality(quality); }
        public final Double quality() { return quality; }
        public final Double getQuality() { return quality(); }
        public final GetEncodedResponseParameter sizeOnly(@Nullable Boolean sizeOnly) { this.sizeOnly = sizeOnly; return this; }
        public final GetEncodedResponseParameter optSizeOnly(@Nullable Boolean sizeOnly) { return sizeOnly(sizeOnly); }
        public final Boolean sizeOnly() { return sizeOnly; }
        public final Boolean getSizeOnly() { return sizeOnly(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (requestId == null) throw new IllegalArgumentException("Audits.GetEncodedResponseParameter.requestId is necessary field.");
            if (encoding == null) throw new IllegalArgumentException("Audits.GetEncodedResponseParameter.encoding is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            requestId.toJson(strBuilder.append("\"requestId\":"));
            strBuilder.append(",\"encoding\":").append(encoding);
            if (quality != null) strBuilder.append(",\"quality\":").append(quality);
            if (sizeOnly != null) strBuilder.append(",\"sizeOnly\":").append(sizeOnly);
            strBuilder.append('}');
            return strBuilder;
        }
        public GetEncodedResponseParameter() {}
        public GetEncodedResponseParameter(
            @JsonProperty("requestId")Network.RequestId requestId,
            @JsonProperty("encoding")Encoding encoding,
            @Nullable @JsonProperty("quality")Double quality,
            @Nullable @JsonProperty("sizeOnly")Boolean sizeOnly
        ) {
            this();
            this.requestId = requestId;
            this.encoding = encoding;
            this.quality = quality;
            this.sizeOnly = sizeOnly;
        }
        public CompletableFuture<GetEncodedResponseResult> call() {
            return super.call("Audits.getEncodedResponse", GetEncodedResponseResult.class,
                (code, msg)->new GetEncodedResponseResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetEncodedResponseResult> call(Executor exec) {
            return super.call("Audits.getEncodedResponse", GetEncodedResponseResult.class,
                (code, msg)->new GetEncodedResponseResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of getEncodedResponse.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetEncodedResponseResult extends ResultBase {
        /**The encoded body as a base64 string. Omitted if sizeOnly is true.
        <em>Optional.</em>*/
        private final String body;
        /**Size before re-encoding.*/
        private final Integer originalSize;
        /**Size after re-encoding.*/
        private final Integer encodedSize;
        public final String body() { return body; }
        public final String getBody() { return body(); }
        public final Integer originalSize() { return originalSize; }
        public final Integer getOriginalSize() { return originalSize(); }
        public final Integer encodedSize() { return encodedSize; }
        public final Integer getEncodedSize() { return encodedSize(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            if (body != null) strBuilder.append("\"body\":").append('"').append(DomainBase.escapeJson(body)).append('"');
            strBuilder.append(",\"originalSize\":").append(originalSize);
            strBuilder.append(",\"encodedSize\":").append(encodedSize);
            strBuilder.append('}');
            return strBuilder;
        }
        public GetEncodedResponseResult(
            @Nullable @JsonProperty("body")String body,
            @JsonProperty("originalSize")Integer originalSize,
            @JsonProperty("encodedSize")Integer encodedSize
        ) {
            this.body = body;
            this.originalSize = originalSize;
            this.encodedSize = encodedSize;
        }
        public GetEncodedResponseResult(ResultBase.FailedResult e) {
            super(e);
            body = null;
            originalSize = null;
            encodedSize = null;
        }
    }
}

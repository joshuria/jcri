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

/**Input/Output operations for streams produced by DevTools.
<p>From: browser_protocol.json</p>
<p>Protocol version: 1.3</p>
 @author Joshua*/
@ParametersAreNonnullByDefault public class IO extends DomainBase {
    public IO(EventCenter evt, WebSocket ws) { super(evt, ws); }

    /**This is either obtained from another method or specifed as `blob:&lt;uuid&gt;` where
`&lt;uuid&gt` is an UUID of a Blob.*/
    @ParametersAreNonnullByDefault public static class StreamHandle implements CommonDomainType {
        private String _value;
        public StreamHandle() {}
        public StreamHandle(String value) { _value = value; }
        public final StreamHandle value(String value) { _value = value; return this; }
        public final String value() { return _value; }
        public final StreamHandle setValue(String value) { return value(value); }
        public final String getValue() { return value(); }
        @Override public String toString() { return _value; }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (_value == null) throw new IllegalArgumentException("IO.StreamHandle.value is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            return strBuilder.append('"').append(_value).append('"');
        }
    }
    /**Close the stream, discard any temporary backing storage.*/
    public CloseParameter close() { final CloseParameter v = new CloseParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of close.*/
    @ParametersAreNonnullByDefault public static class CloseParameter extends CommandBase {
        /**Handle of the stream to close.*/
        private StreamHandle handle;
        public final CloseParameter handle(StreamHandle handle) { this.handle = handle; return this; }
        public final CloseParameter setHandle(StreamHandle handle) { return handle(handle); }
        public final StreamHandle handle() { return handle; }
        public final StreamHandle getHandle() { return handle(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (handle == null) throw new IllegalArgumentException("IO.CloseParameter.handle is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            handle.toJson(strBuilder.append("\"handle\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public CloseParameter() {}
        public CloseParameter(
            @JsonProperty("handle")StreamHandle handle
        ) {
            this();
            this.handle = handle;
        }
        public CompletableFuture<CloseResult> call() {
            return super.call("IO.close", CloseResult.class,
                (code, msg)->new CloseResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<CloseResult> call(Executor exec) {
            return super.call("IO.close", CloseResult.class,
                (code, msg)->new CloseResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of close.*/
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
    /**Read a chunk of the stream*/
    public ReadParameter read() { final ReadParameter v = new ReadParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of read.*/
    @ParametersAreNonnullByDefault public static class ReadParameter extends CommandBase {
        /**Handle of the stream to read.*/
        private StreamHandle handle;
        /**Seek to the specified offset before reading (if not specificed, proceed with offset
following the last read).
        <em>Optional.</em>*/
        private Integer offset;
        /**Maximum number of bytes to read (left upon the agent discretion if not specified).
        <em>Optional.</em>*/
        private Integer size;
        public final ReadParameter handle(StreamHandle handle) { this.handle = handle; return this; }
        public final ReadParameter setHandle(StreamHandle handle) { return handle(handle); }
        public final StreamHandle handle() { return handle; }
        public final StreamHandle getHandle() { return handle(); }
        public final ReadParameter offset(@Nullable Integer offset) { this.offset = offset; return this; }
        public final ReadParameter optOffset(@Nullable Integer offset) { return offset(offset); }
        public final Integer offset() { return offset; }
        public final Integer getOffset() { return offset(); }
        public final ReadParameter size(@Nullable Integer size) { this.size = size; return this; }
        public final ReadParameter optSize(@Nullable Integer size) { return size(size); }
        public final Integer size() { return size; }
        public final Integer getSize() { return size(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (handle == null) throw new IllegalArgumentException("IO.ReadParameter.handle is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            handle.toJson(strBuilder.append("\"handle\":"));
            if (offset != null) strBuilder.append(",\"offset\":").append(offset);
            if (size != null) strBuilder.append(",\"size\":").append(size);
            strBuilder.append('}');
            return strBuilder;
        }
        public ReadParameter() {}
        public ReadParameter(
            @JsonProperty("handle")StreamHandle handle,
            @Nullable @JsonProperty("offset")Integer offset,
            @Nullable @JsonProperty("size")Integer size
        ) {
            this();
            this.handle = handle;
            this.offset = offset;
            this.size = size;
        }
        public CompletableFuture<ReadResult> call() {
            return super.call("IO.read", ReadResult.class,
                (code, msg)->new ReadResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<ReadResult> call(Executor exec) {
            return super.call("IO.read", ReadResult.class,
                (code, msg)->new ReadResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of read.*/
    @ParametersAreNonnullByDefault public static class ReadResult extends ResultBase {
        /**Set if the data is base64-encoded
        <em>Optional.</em>*/
        private final Boolean base64Encoded;
        /**Data that were read.*/
        private final String data;
        /**Set if the end-of-file condition occured while reading.*/
        private final Boolean eof;
        public final Boolean base64Encoded() { return base64Encoded; }
        public final Boolean getBase64Encoded() { return base64Encoded(); }
        public final String data() { return data; }
        public final String getData() { return data(); }
        public final Boolean eof() { return eof; }
        public final Boolean getEof() { return eof(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            if (base64Encoded != null) strBuilder.append("\"base64Encoded\":").append(base64Encoded);
            strBuilder.append(",\"data\":").append('"').append(data).append('"');
            strBuilder.append(",\"eof\":").append(eof);
            strBuilder.append('}');
            return strBuilder;
        }
        public ReadResult(
            @Nullable @JsonProperty("base64Encoded")Boolean base64Encoded,
            @JsonProperty("data")String data,
            @JsonProperty("eof")Boolean eof
        ) {
            this.base64Encoded = base64Encoded;
            this.data = data;
            this.eof = eof;
        }
        public ReadResult(ResultBase.FailedResult e) {
            super(e);
            base64Encoded = null;
            data = null;
            eof = null;
        }
    }
    /**Return UUID of Blob object specified by a remote object id.*/
    public ResolveBlobParameter resolveBlob() { final ResolveBlobParameter v = new ResolveBlobParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of resolveBlob.*/
    @ParametersAreNonnullByDefault public static class ResolveBlobParameter extends CommandBase {
        /**Object id of a Blob object wrapper.*/
        private Runtime.RemoteObjectId objectId;
        public final ResolveBlobParameter objectId(Runtime.RemoteObjectId objectId) { this.objectId = objectId; return this; }
        public final ResolveBlobParameter setObjectId(Runtime.RemoteObjectId objectId) { return objectId(objectId); }
        public final Runtime.RemoteObjectId objectId() { return objectId; }
        public final Runtime.RemoteObjectId getObjectId() { return objectId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (objectId == null) throw new IllegalArgumentException("IO.ResolveBlobParameter.objectId is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            objectId.toJson(strBuilder.append("\"objectId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public ResolveBlobParameter() {}
        public ResolveBlobParameter(
            @JsonProperty("objectId")Runtime.RemoteObjectId objectId
        ) {
            this();
            this.objectId = objectId;
        }
        public CompletableFuture<ResolveBlobResult> call() {
            return super.call("IO.resolveBlob", ResolveBlobResult.class,
                (code, msg)->new ResolveBlobResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<ResolveBlobResult> call(Executor exec) {
            return super.call("IO.resolveBlob", ResolveBlobResult.class,
                (code, msg)->new ResolveBlobResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of resolveBlob.*/
    @ParametersAreNonnullByDefault public static class ResolveBlobResult extends ResultBase {
        /**UUID of the specified Blob.*/
        private final String uuid;
        public final String uuid() { return uuid; }
        public final String getUuid() { return uuid(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"uuid\":").append('"').append(uuid).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public ResolveBlobResult(
            @JsonProperty("uuid")String uuid
        ) {
            this.uuid = uuid;
        }
        public ResolveBlobResult(ResultBase.FailedResult e) {
            super(e);
            uuid = null;
        }
    }
}

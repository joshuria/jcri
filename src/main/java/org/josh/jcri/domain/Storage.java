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
@ParametersAreNonnullByDefault public class Storage extends DomainBase {
    public Storage(EventCenter evt, WebSocket ws) { super(evt, ws); }

    /**Enum of possible storage types.*/
    @ParametersAreNonnullByDefault public enum StorageType implements CommonDomainType {
        Appcache("appcache"),
        Cookies("cookies"),
        File_systems("file_systems"),
        Indexeddb("indexeddb"),
        Local_storage("local_storage"),
        Shader_cache("shader_cache"),
        Websql("websql"),
        Service_workers("service_workers"),
        Cache_storage("cache_storage"),
        All("all"),
        Other("other");

        private final String _value;
        private static final Map<String, StorageType> _Lookup;
        static {
            Map<String, StorageType> m = new HashMap<>();
            for(StorageType v: values()) m.put(v.toString(), v);
            _Lookup = Collections.unmodifiableMap(m);
        }
        /**Convert string representation to type.
         @throws IllegalArgumentException if given value cannot convert to enum type. */
        @JsonCreator public static StorageType of(String value) {
            StorageType v = _Lookup.get(value.toLowerCase());
            return v != null ? v : Enum.valueOf(StorageType.class, value);
        }
        StorageType(String value) { _value = value; }
        /**Check if parameter fields of method are all valid. */
        @Override public void check() throws IllegalArgumentException { /* Need not check */ }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) { return strBuilder.append(toString()); }
        @Override public String toString() { return _value; }
    }

    /**Usage for a storage type.*/
    @ParametersAreNonnullByDefault public static class UsageForType implements CommonDomainType {
        /**Name of storage type.*/
        private StorageType storageType;
        /**Storage usage (bytes).*/
        private Double usage;
        public final UsageForType storageType(StorageType storageType) { this.storageType = storageType; return this; }
        public final UsageForType setStorageType(StorageType storageType) { return storageType(storageType); }
        public final StorageType storageType() { return storageType; }
        public final StorageType getStorageType() { return storageType(); }
        public final UsageForType usage(Double usage) { this.usage = usage; return this; }
        public final UsageForType setUsage(Double usage) { return usage(usage); }
        public final Double usage() { return usage; }
        public final Double getUsage() { return usage(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (storageType == null) throw new IllegalArgumentException("Storage.UsageForType.storageType is necessary field.");
            if (usage == null) throw new IllegalArgumentException("Storage.UsageForType.usage is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            storageType.toJson(strBuilder.append("\"storageType\":"));
            strBuilder.append(",\"usage\":").append(usage);
            strBuilder.append('}');
            return strBuilder;
        }
        public UsageForType() {}
        public UsageForType(
            @JsonProperty("storageType")StorageType storageType,
            @JsonProperty("usage")Double usage
        ) {
            this.storageType = storageType;
            this.usage = usage;
        }
    }
    /**Clears storage for origin.*/
    public ClearDataForOriginParameter clearDataForOrigin() { final ClearDataForOriginParameter v = new ClearDataForOriginParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of clearDataForOrigin.*/
    @ParametersAreNonnullByDefault public static class ClearDataForOriginParameter extends CommandBase {
        /**Security origin.*/
        private String origin;
        /**Comma separated origin names.*/
        private String storageTypes;
        public final ClearDataForOriginParameter origin(String origin) { this.origin = origin; return this; }
        public final ClearDataForOriginParameter setOrigin(String origin) { return origin(origin); }
        public final String origin() { return origin; }
        public final String getOrigin() { return origin(); }
        public final ClearDataForOriginParameter storageTypes(String storageTypes) { this.storageTypes = storageTypes; return this; }
        public final ClearDataForOriginParameter setStorageTypes(String storageTypes) { return storageTypes(storageTypes); }
        public final String storageTypes() { return storageTypes; }
        public final String getStorageTypes() { return storageTypes(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (origin == null) throw new IllegalArgumentException("Storage.ClearDataForOriginParameter.origin is necessary field.");
            if (storageTypes == null) throw new IllegalArgumentException("Storage.ClearDataForOriginParameter.storageTypes is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"origin\":").append('"').append(DomainBase.escapeQuote(origin)).append('"');
            strBuilder.append(",\"storageTypes\":").append('"').append(DomainBase.escapeQuote(storageTypes)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public ClearDataForOriginParameter() {}
        public ClearDataForOriginParameter(
            @JsonProperty("origin")String origin,
            @JsonProperty("storageTypes")String storageTypes
        ) {
            this();
            this.origin = origin;
            this.storageTypes = storageTypes;
        }
        public CompletableFuture<ClearDataForOriginResult> call() {
            return super.call("Storage.clearDataForOrigin", ClearDataForOriginResult.class,
                (code, msg)->new ClearDataForOriginResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<ClearDataForOriginResult> call(Executor exec) {
            return super.call("Storage.clearDataForOrigin", ClearDataForOriginResult.class,
                (code, msg)->new ClearDataForOriginResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of clearDataForOrigin.*/
    @ParametersAreNonnullByDefault public static class ClearDataForOriginResult extends ResultBase {
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
        public ClearDataForOriginResult() { super(); }
        public ClearDataForOriginResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Returns usage and quota in bytes.*/
    public GetUsageAndQuotaParameter getUsageAndQuota() { final GetUsageAndQuotaParameter v = new GetUsageAndQuotaParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of getUsageAndQuota.*/
    @ParametersAreNonnullByDefault public static class GetUsageAndQuotaParameter extends CommandBase {
        /**Security origin.*/
        private String origin;
        public final GetUsageAndQuotaParameter origin(String origin) { this.origin = origin; return this; }
        public final GetUsageAndQuotaParameter setOrigin(String origin) { return origin(origin); }
        public final String origin() { return origin; }
        public final String getOrigin() { return origin(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (origin == null) throw new IllegalArgumentException("Storage.GetUsageAndQuotaParameter.origin is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"origin\":").append('"').append(DomainBase.escapeQuote(origin)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public GetUsageAndQuotaParameter() {}
        public GetUsageAndQuotaParameter(
            @JsonProperty("origin")String origin
        ) {
            this();
            this.origin = origin;
        }
        public CompletableFuture<GetUsageAndQuotaResult> call() {
            return super.call("Storage.getUsageAndQuota", GetUsageAndQuotaResult.class,
                (code, msg)->new GetUsageAndQuotaResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetUsageAndQuotaResult> call(Executor exec) {
            return super.call("Storage.getUsageAndQuota", GetUsageAndQuotaResult.class,
                (code, msg)->new GetUsageAndQuotaResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of getUsageAndQuota.*/
    @ParametersAreNonnullByDefault public static class GetUsageAndQuotaResult extends ResultBase {
        /**Storage usage (bytes).*/
        private final Double usage;
        /**Storage quota (bytes).*/
        private final Double quota;
        /**Storage usage per type (bytes).*/
        private final List<UsageForType> usageBreakdown;
        public final Double usage() { return usage; }
        public final Double getUsage() { return usage(); }
        public final Double quota() { return quota; }
        public final Double getQuota() { return quota(); }
        public final List<UsageForType> usageBreakdown() { return usageBreakdown; }
        public final List<UsageForType> getUsageBreakdown() { return usageBreakdown(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"usage\":").append(usage);
            strBuilder.append(",\"quota\":").append(quota);
                        strBuilder.append(",\"usageBreakdown\":[");
            usageBreakdown.get(0).toJson(strBuilder);
            for (int i = 1; i < usageBreakdown.size(); ++i)
                usageBreakdown.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public GetUsageAndQuotaResult(
            @JsonProperty("usage")Double usage,
            @JsonProperty("quota")Double quota,
            @JsonProperty("usageBreakdown")List<UsageForType> usageBreakdown
        ) {
            this.usage = usage;
            this.quota = quota;
            this.usageBreakdown = usageBreakdown;
        }
        public GetUsageAndQuotaResult(ResultBase.FailedResult e) {
            super(e);
            usage = null;
            quota = null;
            usageBreakdown = null;
        }
    }
    /**Registers origin to be notified when an update occurs to its cache storage list.*/
    public TrackCacheStorageForOriginParameter trackCacheStorageForOrigin() { final TrackCacheStorageForOriginParameter v = new TrackCacheStorageForOriginParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of trackCacheStorageForOrigin.*/
    @ParametersAreNonnullByDefault public static class TrackCacheStorageForOriginParameter extends CommandBase {
        /**Security origin.*/
        private String origin;
        public final TrackCacheStorageForOriginParameter origin(String origin) { this.origin = origin; return this; }
        public final TrackCacheStorageForOriginParameter setOrigin(String origin) { return origin(origin); }
        public final String origin() { return origin; }
        public final String getOrigin() { return origin(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (origin == null) throw new IllegalArgumentException("Storage.TrackCacheStorageForOriginParameter.origin is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"origin\":").append('"').append(DomainBase.escapeQuote(origin)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public TrackCacheStorageForOriginParameter() {}
        public TrackCacheStorageForOriginParameter(
            @JsonProperty("origin")String origin
        ) {
            this();
            this.origin = origin;
        }
        public CompletableFuture<TrackCacheStorageForOriginResult> call() {
            return super.call("Storage.trackCacheStorageForOrigin", TrackCacheStorageForOriginResult.class,
                (code, msg)->new TrackCacheStorageForOriginResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<TrackCacheStorageForOriginResult> call(Executor exec) {
            return super.call("Storage.trackCacheStorageForOrigin", TrackCacheStorageForOriginResult.class,
                (code, msg)->new TrackCacheStorageForOriginResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of trackCacheStorageForOrigin.*/
    @ParametersAreNonnullByDefault public static class TrackCacheStorageForOriginResult extends ResultBase {
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
        public TrackCacheStorageForOriginResult() { super(); }
        public TrackCacheStorageForOriginResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Registers origin to be notified when an update occurs to its IndexedDB.*/
    public TrackIndexedDBForOriginParameter trackIndexedDBForOrigin() { final TrackIndexedDBForOriginParameter v = new TrackIndexedDBForOriginParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of trackIndexedDBForOrigin.*/
    @ParametersAreNonnullByDefault public static class TrackIndexedDBForOriginParameter extends CommandBase {
        /**Security origin.*/
        private String origin;
        public final TrackIndexedDBForOriginParameter origin(String origin) { this.origin = origin; return this; }
        public final TrackIndexedDBForOriginParameter setOrigin(String origin) { return origin(origin); }
        public final String origin() { return origin; }
        public final String getOrigin() { return origin(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (origin == null) throw new IllegalArgumentException("Storage.TrackIndexedDBForOriginParameter.origin is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"origin\":").append('"').append(DomainBase.escapeQuote(origin)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public TrackIndexedDBForOriginParameter() {}
        public TrackIndexedDBForOriginParameter(
            @JsonProperty("origin")String origin
        ) {
            this();
            this.origin = origin;
        }
        public CompletableFuture<TrackIndexedDBForOriginResult> call() {
            return super.call("Storage.trackIndexedDBForOrigin", TrackIndexedDBForOriginResult.class,
                (code, msg)->new TrackIndexedDBForOriginResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<TrackIndexedDBForOriginResult> call(Executor exec) {
            return super.call("Storage.trackIndexedDBForOrigin", TrackIndexedDBForOriginResult.class,
                (code, msg)->new TrackIndexedDBForOriginResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of trackIndexedDBForOrigin.*/
    @ParametersAreNonnullByDefault public static class TrackIndexedDBForOriginResult extends ResultBase {
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
        public TrackIndexedDBForOriginResult() { super(); }
        public TrackIndexedDBForOriginResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Unregisters origin from receiving notifications for cache storage.*/
    public UntrackCacheStorageForOriginParameter untrackCacheStorageForOrigin() { final UntrackCacheStorageForOriginParameter v = new UntrackCacheStorageForOriginParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of untrackCacheStorageForOrigin.*/
    @ParametersAreNonnullByDefault public static class UntrackCacheStorageForOriginParameter extends CommandBase {
        /**Security origin.*/
        private String origin;
        public final UntrackCacheStorageForOriginParameter origin(String origin) { this.origin = origin; return this; }
        public final UntrackCacheStorageForOriginParameter setOrigin(String origin) { return origin(origin); }
        public final String origin() { return origin; }
        public final String getOrigin() { return origin(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (origin == null) throw new IllegalArgumentException("Storage.UntrackCacheStorageForOriginParameter.origin is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"origin\":").append('"').append(DomainBase.escapeQuote(origin)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public UntrackCacheStorageForOriginParameter() {}
        public UntrackCacheStorageForOriginParameter(
            @JsonProperty("origin")String origin
        ) {
            this();
            this.origin = origin;
        }
        public CompletableFuture<UntrackCacheStorageForOriginResult> call() {
            return super.call("Storage.untrackCacheStorageForOrigin", UntrackCacheStorageForOriginResult.class,
                (code, msg)->new UntrackCacheStorageForOriginResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<UntrackCacheStorageForOriginResult> call(Executor exec) {
            return super.call("Storage.untrackCacheStorageForOrigin", UntrackCacheStorageForOriginResult.class,
                (code, msg)->new UntrackCacheStorageForOriginResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of untrackCacheStorageForOrigin.*/
    @ParametersAreNonnullByDefault public static class UntrackCacheStorageForOriginResult extends ResultBase {
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
        public UntrackCacheStorageForOriginResult() { super(); }
        public UntrackCacheStorageForOriginResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Unregisters origin from receiving notifications for IndexedDB.*/
    public UntrackIndexedDBForOriginParameter untrackIndexedDBForOrigin() { final UntrackIndexedDBForOriginParameter v = new UntrackIndexedDBForOriginParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of untrackIndexedDBForOrigin.*/
    @ParametersAreNonnullByDefault public static class UntrackIndexedDBForOriginParameter extends CommandBase {
        /**Security origin.*/
        private String origin;
        public final UntrackIndexedDBForOriginParameter origin(String origin) { this.origin = origin; return this; }
        public final UntrackIndexedDBForOriginParameter setOrigin(String origin) { return origin(origin); }
        public final String origin() { return origin; }
        public final String getOrigin() { return origin(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (origin == null) throw new IllegalArgumentException("Storage.UntrackIndexedDBForOriginParameter.origin is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"origin\":").append('"').append(DomainBase.escapeQuote(origin)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public UntrackIndexedDBForOriginParameter() {}
        public UntrackIndexedDBForOriginParameter(
            @JsonProperty("origin")String origin
        ) {
            this();
            this.origin = origin;
        }
        public CompletableFuture<UntrackIndexedDBForOriginResult> call() {
            return super.call("Storage.untrackIndexedDBForOrigin", UntrackIndexedDBForOriginResult.class,
                (code, msg)->new UntrackIndexedDBForOriginResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<UntrackIndexedDBForOriginResult> call(Executor exec) {
            return super.call("Storage.untrackIndexedDBForOrigin", UntrackIndexedDBForOriginResult.class,
                (code, msg)->new UntrackIndexedDBForOriginResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of untrackIndexedDBForOrigin.*/
    @ParametersAreNonnullByDefault public static class UntrackIndexedDBForOriginResult extends ResultBase {
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
        public UntrackIndexedDBForOriginResult() { super(); }
        public UntrackIndexedDBForOriginResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Event parameter of Storage.cacheStorageContentUpdated.
     @see #onCacheStorageContentUpdated*/
    @ParametersAreNonnullByDefault public static class CacheStorageContentUpdatedEventParameter implements CommonDomainType {
        /**Origin to update.*/
        private final String origin;
        /**Name of cache in origin.*/
        private final String cacheName;
        public final String origin() { return origin; }
        public final String getOrigin() { return origin(); }
        public final String cacheName() { return cacheName; }
        public final String getCacheName() { return cacheName(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"origin\":").append('"').append(DomainBase.escapeQuote(origin)).append('"');
            strBuilder.append(",\"cacheName\":").append('"').append(DomainBase.escapeQuote(cacheName)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        CacheStorageContentUpdatedEventParameter(
            @JsonProperty("origin")String origin,
            @JsonProperty("cacheName")String cacheName
        ) {
            this.origin = origin;
            this.cacheName = cacheName;
        }
    }
    /**A cache's contents have been modified.
     @see CacheStorageContentUpdatedEventParameter*/
    public void onCacheStorageContentUpdated(Consumer<CacheStorageContentUpdatedEventParameter> callback) {
        registerEventCallback("Storage.cacheStorageContentUpdated", node -> {
            CacheStorageContentUpdatedEventParameter param;
            try { param = EventCenter.deserializeJson(node, CacheStorageContentUpdatedEventParameter.class); }
            catch (IOException e) { e.printStackTrace(); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of Storage.cacheStorageListUpdated.
     @see #onCacheStorageListUpdated*/
    @ParametersAreNonnullByDefault public static class CacheStorageListUpdatedEventParameter implements CommonDomainType {
        /**Origin to update.*/
        private final String origin;
        public final String origin() { return origin; }
        public final String getOrigin() { return origin(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"origin\":").append('"').append(DomainBase.escapeQuote(origin)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        CacheStorageListUpdatedEventParameter(
            @JsonProperty("origin")String origin
        ) {
            this.origin = origin;
        }
    }
    /**A cache has been added/deleted.
     @see CacheStorageListUpdatedEventParameter*/
    public void onCacheStorageListUpdated(Consumer<CacheStorageListUpdatedEventParameter> callback) {
        registerEventCallback("Storage.cacheStorageListUpdated", node -> {
            CacheStorageListUpdatedEventParameter param;
            try { param = EventCenter.deserializeJson(node, CacheStorageListUpdatedEventParameter.class); }
            catch (IOException e) { e.printStackTrace(); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of Storage.indexedDBContentUpdated.
     @see #onIndexedDBContentUpdated*/
    @ParametersAreNonnullByDefault public static class IndexedDBContentUpdatedEventParameter implements CommonDomainType {
        /**Origin to update.*/
        private final String origin;
        /**Database to update.*/
        private final String databaseName;
        /**ObjectStore to update.*/
        private final String objectStoreName;
        public final String origin() { return origin; }
        public final String getOrigin() { return origin(); }
        public final String databaseName() { return databaseName; }
        public final String getDatabaseName() { return databaseName(); }
        public final String objectStoreName() { return objectStoreName; }
        public final String getObjectStoreName() { return objectStoreName(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"origin\":").append('"').append(DomainBase.escapeQuote(origin)).append('"');
            strBuilder.append(",\"databaseName\":").append('"').append(DomainBase.escapeQuote(databaseName)).append('"');
            strBuilder.append(",\"objectStoreName\":").append('"').append(DomainBase.escapeQuote(objectStoreName)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        IndexedDBContentUpdatedEventParameter(
            @JsonProperty("origin")String origin,
            @JsonProperty("databaseName")String databaseName,
            @JsonProperty("objectStoreName")String objectStoreName
        ) {
            this.origin = origin;
            this.databaseName = databaseName;
            this.objectStoreName = objectStoreName;
        }
    }
    /**The origin's IndexedDB object store has been modified.
     @see IndexedDBContentUpdatedEventParameter*/
    public void onIndexedDBContentUpdated(Consumer<IndexedDBContentUpdatedEventParameter> callback) {
        registerEventCallback("Storage.indexedDBContentUpdated", node -> {
            IndexedDBContentUpdatedEventParameter param;
            try { param = EventCenter.deserializeJson(node, IndexedDBContentUpdatedEventParameter.class); }
            catch (IOException e) { e.printStackTrace(); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of Storage.indexedDBListUpdated.
     @see #onIndexedDBListUpdated*/
    @ParametersAreNonnullByDefault public static class IndexedDBListUpdatedEventParameter implements CommonDomainType {
        /**Origin to update.*/
        private final String origin;
        public final String origin() { return origin; }
        public final String getOrigin() { return origin(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"origin\":").append('"').append(DomainBase.escapeQuote(origin)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        IndexedDBListUpdatedEventParameter(
            @JsonProperty("origin")String origin
        ) {
            this.origin = origin;
        }
    }
    /**The origin's IndexedDB database list has been modified.
     @see IndexedDBListUpdatedEventParameter*/
    public void onIndexedDBListUpdated(Consumer<IndexedDBListUpdatedEventParameter> callback) {
        registerEventCallback("Storage.indexedDBListUpdated", node -> {
            IndexedDBListUpdatedEventParameter param;
            try { param = EventCenter.deserializeJson(node, IndexedDBListUpdatedEventParameter.class); }
            catch (IOException e) { e.printStackTrace(); return; }
            callback.accept(param);
        });
    }
}

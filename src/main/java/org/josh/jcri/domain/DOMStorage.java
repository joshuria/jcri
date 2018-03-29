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

/**Query and modify DOM storage.
<p>From: browser_protocol.json</p>
<p>Protocol version: 1.3</p>
<p><strong>Experimental.</strong></p>
 @author Joshua*/
@ParametersAreNonnullByDefault public class DOMStorage extends DomainBase {
    public DOMStorage(EventCenter evt, WebSocket ws) { super(evt, ws); }

    /**DOM Storage identifier.*/
    @ParametersAreNonnullByDefault public static class StorageId implements CommonDomainType {
        /**Security origin for the storage.*/
        private String securityOrigin;
        /**Whether the storage is local storage (not session storage).*/
        private Boolean isLocalStorage;
        public final StorageId securityOrigin(String securityOrigin) { this.securityOrigin = securityOrigin; return this; }
        public final StorageId setSecurityOrigin(String securityOrigin) { return securityOrigin(securityOrigin); }
        public final String securityOrigin() { return securityOrigin; }
        public final String getSecurityOrigin() { return securityOrigin(); }
        public final StorageId isLocalStorage(Boolean isLocalStorage) { this.isLocalStorage = isLocalStorage; return this; }
        public final StorageId setIsLocalStorage(Boolean isLocalStorage) { return isLocalStorage(isLocalStorage); }
        public final Boolean isLocalStorage() { return isLocalStorage; }
        public final Boolean getIsLocalStorage() { return isLocalStorage(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (securityOrigin == null) throw new IllegalArgumentException("DOMStorage.StorageId.securityOrigin is necessary field.");
            if (isLocalStorage == null) throw new IllegalArgumentException("DOMStorage.StorageId.isLocalStorage is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"securityOrigin\":").append('"').append(securityOrigin).append('"');
            strBuilder.append(",\"isLocalStorage\":").append(isLocalStorage);
            strBuilder.append('}');
            return strBuilder;
        }
        public StorageId() {}
        public StorageId(
            @JsonProperty("securityOrigin")String securityOrigin,
            @JsonProperty("isLocalStorage")Boolean isLocalStorage
        ) {
            this.securityOrigin = securityOrigin;
            this.isLocalStorage = isLocalStorage;
        }
    }

    /**DOM Storage item.*/
    @ParametersAreNonnullByDefault public static class Item implements CommonDomainType {
        private List<String> _value;
        public Item() {}
        public Item(List<String> value) { _value = value; }
        public final Item value(List<String> value) { _value = value; return this; }
        public final List<String> value() { return _value; }
        public final Item setValue(List<String> value) { return value(value); }
        public final List<String> getValue() { return value(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (_value == null) throw new IllegalArgumentException("DOMStorage.Item.value is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            return strBuilder.append(_value);
        }
    }
    /**&lt;No document in protocol.&gt;*/
    public ClearParameter clear() { final ClearParameter v = new ClearParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of clear.*/
    @ParametersAreNonnullByDefault public static class ClearParameter extends CommandBase {
        /**&lt;No document in protocol.&gt;*/
        private StorageId storageId;
        public final ClearParameter storageId(StorageId storageId) { this.storageId = storageId; return this; }
        public final ClearParameter setStorageId(StorageId storageId) { return storageId(storageId); }
        public final StorageId storageId() { return storageId; }
        public final StorageId getStorageId() { return storageId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (storageId == null) throw new IllegalArgumentException("DOMStorage.ClearParameter.storageId is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            storageId.toJson(strBuilder.append("\"storageId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public ClearParameter() {}
        public ClearParameter(
            @JsonProperty("storageId")StorageId storageId
        ) {
            this();
            this.storageId = storageId;
        }
        public CompletableFuture<ClearResult> call() {
            return super.call("DOMStorage.clear", ClearResult.class,
                (code, msg)->new ClearResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<ClearResult> call(Executor exec) {
            return super.call("DOMStorage.clear", ClearResult.class,
                (code, msg)->new ClearResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of clear.*/
    @ParametersAreNonnullByDefault public static class ClearResult extends ResultBase {
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
        public ClearResult() { super(); }
        public ClearResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Disables storage tracking, prevents storage events from being sent to the client.*/
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
            return super.call("DOMStorage.disable", DisableResult.class,
                (code, msg)->new DisableResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<DisableResult> call(Executor exec) {
            return super.call("DOMStorage.disable", DisableResult.class,
                (code, msg)->new DisableResult(ResultBase.ofError(code, msg)), exec);
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
    /**Enables storage tracking, storage events will now be delivered to the client.*/
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
            return super.call("DOMStorage.enable", EnableResult.class,
                (code, msg)->new EnableResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<EnableResult> call(Executor exec) {
            return super.call("DOMStorage.enable", EnableResult.class,
                (code, msg)->new EnableResult(ResultBase.ofError(code, msg)), exec);
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
    /**&lt;No document in protocol.&gt;*/
    public GetDOMStorageItemsParameter getDOMStorageItems() { final GetDOMStorageItemsParameter v = new GetDOMStorageItemsParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of getDOMStorageItems.*/
    @ParametersAreNonnullByDefault public static class GetDOMStorageItemsParameter extends CommandBase {
        /**&lt;No document in protocol.&gt;*/
        private StorageId storageId;
        public final GetDOMStorageItemsParameter storageId(StorageId storageId) { this.storageId = storageId; return this; }
        public final GetDOMStorageItemsParameter setStorageId(StorageId storageId) { return storageId(storageId); }
        public final StorageId storageId() { return storageId; }
        public final StorageId getStorageId() { return storageId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (storageId == null) throw new IllegalArgumentException("DOMStorage.GetDOMStorageItemsParameter.storageId is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            storageId.toJson(strBuilder.append("\"storageId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public GetDOMStorageItemsParameter() {}
        public GetDOMStorageItemsParameter(
            @JsonProperty("storageId")StorageId storageId
        ) {
            this();
            this.storageId = storageId;
        }
        public CompletableFuture<GetDOMStorageItemsResult> call() {
            return super.call("DOMStorage.getDOMStorageItems", GetDOMStorageItemsResult.class,
                (code, msg)->new GetDOMStorageItemsResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetDOMStorageItemsResult> call(Executor exec) {
            return super.call("DOMStorage.getDOMStorageItems", GetDOMStorageItemsResult.class,
                (code, msg)->new GetDOMStorageItemsResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of getDOMStorageItems.*/
    @ParametersAreNonnullByDefault public static class GetDOMStorageItemsResult extends ResultBase {
        /**&lt;No document in protocol.&gt;*/
        private final List<Item> entries;
        public final List<Item> entries() { return entries; }
        public final List<Item> getEntries() { return entries(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
                        strBuilder.append("\"entries\":[");
            entries.get(0).toJson(strBuilder);
            for (int i = 1; i < entries.size(); ++i)
                entries.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public GetDOMStorageItemsResult(
            @JsonProperty("entries")List<Item> entries
        ) {
            this.entries = entries;
        }
        public GetDOMStorageItemsResult(ResultBase.FailedResult e) {
            super(e);
            entries = null;
        }
    }
    /**&lt;No document in protocol.&gt;*/
    public RemoveDOMStorageItemParameter removeDOMStorageItem() { final RemoveDOMStorageItemParameter v = new RemoveDOMStorageItemParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of removeDOMStorageItem.*/
    @ParametersAreNonnullByDefault public static class RemoveDOMStorageItemParameter extends CommandBase {
        /**&lt;No document in protocol.&gt;*/
        private StorageId storageId;
        /**&lt;No document in protocol.&gt;*/
        private String key;
        public final RemoveDOMStorageItemParameter storageId(StorageId storageId) { this.storageId = storageId; return this; }
        public final RemoveDOMStorageItemParameter setStorageId(StorageId storageId) { return storageId(storageId); }
        public final StorageId storageId() { return storageId; }
        public final StorageId getStorageId() { return storageId(); }
        public final RemoveDOMStorageItemParameter key(String key) { this.key = key; return this; }
        public final RemoveDOMStorageItemParameter setKey(String key) { return key(key); }
        public final String key() { return key; }
        public final String getKey() { return key(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (storageId == null) throw new IllegalArgumentException("DOMStorage.RemoveDOMStorageItemParameter.storageId is necessary field.");
            if (key == null) throw new IllegalArgumentException("DOMStorage.RemoveDOMStorageItemParameter.key is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            storageId.toJson(strBuilder.append("\"storageId\":"));
            strBuilder.append(",\"key\":").append('"').append(key).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public RemoveDOMStorageItemParameter() {}
        public RemoveDOMStorageItemParameter(
            @JsonProperty("storageId")StorageId storageId,
            @JsonProperty("key")String key
        ) {
            this();
            this.storageId = storageId;
            this.key = key;
        }
        public CompletableFuture<RemoveDOMStorageItemResult> call() {
            return super.call("DOMStorage.removeDOMStorageItem", RemoveDOMStorageItemResult.class,
                (code, msg)->new RemoveDOMStorageItemResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<RemoveDOMStorageItemResult> call(Executor exec) {
            return super.call("DOMStorage.removeDOMStorageItem", RemoveDOMStorageItemResult.class,
                (code, msg)->new RemoveDOMStorageItemResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of removeDOMStorageItem.*/
    @ParametersAreNonnullByDefault public static class RemoveDOMStorageItemResult extends ResultBase {
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
        public RemoveDOMStorageItemResult() { super(); }
        public RemoveDOMStorageItemResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**&lt;No document in protocol.&gt;*/
    public SetDOMStorageItemParameter setDOMStorageItem() { final SetDOMStorageItemParameter v = new SetDOMStorageItemParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setDOMStorageItem.*/
    @ParametersAreNonnullByDefault public static class SetDOMStorageItemParameter extends CommandBase {
        /**&lt;No document in protocol.&gt;*/
        private StorageId storageId;
        /**&lt;No document in protocol.&gt;*/
        private String key;
        /**&lt;No document in protocol.&gt;*/
        private String value;
        public final SetDOMStorageItemParameter storageId(StorageId storageId) { this.storageId = storageId; return this; }
        public final SetDOMStorageItemParameter setStorageId(StorageId storageId) { return storageId(storageId); }
        public final StorageId storageId() { return storageId; }
        public final StorageId getStorageId() { return storageId(); }
        public final SetDOMStorageItemParameter key(String key) { this.key = key; return this; }
        public final SetDOMStorageItemParameter setKey(String key) { return key(key); }
        public final String key() { return key; }
        public final String getKey() { return key(); }
        public final SetDOMStorageItemParameter value(String value) { this.value = value; return this; }
        public final SetDOMStorageItemParameter setValue(String value) { return value(value); }
        public final String value() { return value; }
        public final String getValue() { return value(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (storageId == null) throw new IllegalArgumentException("DOMStorage.SetDOMStorageItemParameter.storageId is necessary field.");
            if (key == null) throw new IllegalArgumentException("DOMStorage.SetDOMStorageItemParameter.key is necessary field.");
            if (value == null) throw new IllegalArgumentException("DOMStorage.SetDOMStorageItemParameter.value is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            storageId.toJson(strBuilder.append("\"storageId\":"));
            strBuilder.append(",\"key\":").append('"').append(key).append('"');
            strBuilder.append(",\"value\":").append('"').append(value).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public SetDOMStorageItemParameter() {}
        public SetDOMStorageItemParameter(
            @JsonProperty("storageId")StorageId storageId,
            @JsonProperty("key")String key,
            @JsonProperty("value")String value
        ) {
            this();
            this.storageId = storageId;
            this.key = key;
            this.value = value;
        }
        public CompletableFuture<SetDOMStorageItemResult> call() {
            return super.call("DOMStorage.setDOMStorageItem", SetDOMStorageItemResult.class,
                (code, msg)->new SetDOMStorageItemResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetDOMStorageItemResult> call(Executor exec) {
            return super.call("DOMStorage.setDOMStorageItem", SetDOMStorageItemResult.class,
                (code, msg)->new SetDOMStorageItemResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setDOMStorageItem.*/
    @ParametersAreNonnullByDefault public static class SetDOMStorageItemResult extends ResultBase {
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
        public SetDOMStorageItemResult() { super(); }
        public SetDOMStorageItemResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Event parameter of DOMStorage.domStorageItemAdded.
     @see #onDomStorageItemAdded*/
    @ParametersAreNonnullByDefault public static class DomStorageItemAddedEventParameter implements CommonDomainType {
        /**&lt;No document in protocol.&gt;*/
        private final StorageId storageId;
        /**&lt;No document in protocol.&gt;*/
        private final String key;
        /**&lt;No document in protocol.&gt;*/
        private final String newValue;
        public final StorageId storageId() { return storageId; }
        public final StorageId getStorageId() { return storageId(); }
        public final String key() { return key; }
        public final String getKey() { return key(); }
        public final String newValue() { return newValue; }
        public final String getNewValue() { return newValue(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            storageId.toJson(strBuilder.append("\"storageId\":"));
            strBuilder.append(",\"key\":").append('"').append(key).append('"');
            strBuilder.append(",\"newValue\":").append('"').append(newValue).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        DomStorageItemAddedEventParameter(
            @JsonProperty("storageId")StorageId storageId,
            @JsonProperty("key")String key,
            @JsonProperty("newValue")String newValue
        ) {
            this.storageId = storageId;
            this.key = key;
            this.newValue = newValue;
        }
    }
    /**&lt;No document in protocol.&gt;
     @see DomStorageItemAddedEventParameter*/
    public void onDomStorageItemAdded(Consumer<DomStorageItemAddedEventParameter> callback) {
        registerEventCallback("DOMStorage.domStorageItemAdded", node -> {
            DomStorageItemAddedEventParameter param;
            try { param = EventCenter.deserializeJson(node, DomStorageItemAddedEventParameter.class); }
            catch (IOException e) { e.printStackTrace(); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of DOMStorage.domStorageItemRemoved.
     @see #onDomStorageItemRemoved*/
    @ParametersAreNonnullByDefault public static class DomStorageItemRemovedEventParameter implements CommonDomainType {
        /**&lt;No document in protocol.&gt;*/
        private final StorageId storageId;
        /**&lt;No document in protocol.&gt;*/
        private final String key;
        public final StorageId storageId() { return storageId; }
        public final StorageId getStorageId() { return storageId(); }
        public final String key() { return key; }
        public final String getKey() { return key(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            storageId.toJson(strBuilder.append("\"storageId\":"));
            strBuilder.append(",\"key\":").append('"').append(key).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        DomStorageItemRemovedEventParameter(
            @JsonProperty("storageId")StorageId storageId,
            @JsonProperty("key")String key
        ) {
            this.storageId = storageId;
            this.key = key;
        }
    }
    /**&lt;No document in protocol.&gt;
     @see DomStorageItemRemovedEventParameter*/
    public void onDomStorageItemRemoved(Consumer<DomStorageItemRemovedEventParameter> callback) {
        registerEventCallback("DOMStorage.domStorageItemRemoved", node -> {
            DomStorageItemRemovedEventParameter param;
            try { param = EventCenter.deserializeJson(node, DomStorageItemRemovedEventParameter.class); }
            catch (IOException e) { e.printStackTrace(); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of DOMStorage.domStorageItemUpdated.
     @see #onDomStorageItemUpdated*/
    @ParametersAreNonnullByDefault public static class DomStorageItemUpdatedEventParameter implements CommonDomainType {
        /**&lt;No document in protocol.&gt;*/
        private final StorageId storageId;
        /**&lt;No document in protocol.&gt;*/
        private final String key;
        /**&lt;No document in protocol.&gt;*/
        private final String oldValue;
        /**&lt;No document in protocol.&gt;*/
        private final String newValue;
        public final StorageId storageId() { return storageId; }
        public final StorageId getStorageId() { return storageId(); }
        public final String key() { return key; }
        public final String getKey() { return key(); }
        public final String oldValue() { return oldValue; }
        public final String getOldValue() { return oldValue(); }
        public final String newValue() { return newValue; }
        public final String getNewValue() { return newValue(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            storageId.toJson(strBuilder.append("\"storageId\":"));
            strBuilder.append(",\"key\":").append('"').append(key).append('"');
            strBuilder.append(",\"oldValue\":").append('"').append(oldValue).append('"');
            strBuilder.append(",\"newValue\":").append('"').append(newValue).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        DomStorageItemUpdatedEventParameter(
            @JsonProperty("storageId")StorageId storageId,
            @JsonProperty("key")String key,
            @JsonProperty("oldValue")String oldValue,
            @JsonProperty("newValue")String newValue
        ) {
            this.storageId = storageId;
            this.key = key;
            this.oldValue = oldValue;
            this.newValue = newValue;
        }
    }
    /**&lt;No document in protocol.&gt;
     @see DomStorageItemUpdatedEventParameter*/
    public void onDomStorageItemUpdated(Consumer<DomStorageItemUpdatedEventParameter> callback) {
        registerEventCallback("DOMStorage.domStorageItemUpdated", node -> {
            DomStorageItemUpdatedEventParameter param;
            try { param = EventCenter.deserializeJson(node, DomStorageItemUpdatedEventParameter.class); }
            catch (IOException e) { e.printStackTrace(); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of DOMStorage.domStorageItemsCleared.
     @see #onDomStorageItemsCleared*/
    @ParametersAreNonnullByDefault public static class DomStorageItemsClearedEventParameter implements CommonDomainType {
        /**&lt;No document in protocol.&gt;*/
        private final StorageId storageId;
        public final StorageId storageId() { return storageId; }
        public final StorageId getStorageId() { return storageId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            storageId.toJson(strBuilder.append("\"storageId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        DomStorageItemsClearedEventParameter(
            @JsonProperty("storageId")StorageId storageId
        ) {
            this.storageId = storageId;
        }
    }
    /**&lt;No document in protocol.&gt;
     @see DomStorageItemsClearedEventParameter*/
    public void onDomStorageItemsCleared(Consumer<DomStorageItemsClearedEventParameter> callback) {
        registerEventCallback("DOMStorage.domStorageItemsCleared", node -> {
            DomStorageItemsClearedEventParameter param;
            try { param = EventCenter.deserializeJson(node, DomStorageItemsClearedEventParameter.class); }
            catch (IOException e) { e.printStackTrace(); return; }
            callback.accept(param);
        });
    }
}

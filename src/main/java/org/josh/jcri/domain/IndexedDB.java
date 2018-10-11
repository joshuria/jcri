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
 @see Runtime
 @author Joshua*/
@ParametersAreNonnullByDefault public class IndexedDB extends DomainBase {
    public IndexedDB(EventCenter evt, WebSocket ws) { super(evt, ws); }

    /**Database with an array of object stores.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class DatabaseWithObjectStores implements CommonDomainType {
        /**Database name.*/
        private String name;
        /**Database version.*/
        private Integer version;
        /**Object stores in this database.*/
        private List<ObjectStore> objectStores;
        public final DatabaseWithObjectStores name(String name) { this.name = name; return this; }
        public final DatabaseWithObjectStores setName(String name) { return name(name); }
        public final String name() { return name; }
        public final String getName() { return name(); }
        public final DatabaseWithObjectStores version(Integer version) { this.version = version; return this; }
        public final DatabaseWithObjectStores setVersion(Integer version) { return version(version); }
        public final Integer version() { return version; }
        public final Integer getVersion() { return version(); }
        public final DatabaseWithObjectStores objectStores(List<ObjectStore> objectStores) { this.objectStores = objectStores; return this; }
        public final DatabaseWithObjectStores setObjectStores(List<ObjectStore> objectStores) { return objectStores(objectStores); }
        public final List<ObjectStore> objectStores() { return objectStores; }
        public final List<ObjectStore> getObjectStores() { return objectStores(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (name == null) throw new IllegalArgumentException("IndexedDB.DatabaseWithObjectStores.name is necessary field.");
            if (version == null) throw new IllegalArgumentException("IndexedDB.DatabaseWithObjectStores.version is necessary field.");
            if (objectStores == null) throw new IllegalArgumentException("IndexedDB.DatabaseWithObjectStores.objectStores is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"name\":").append('"').append(DomainBase.escapeJson(name)).append('"');
            strBuilder.append(",\"version\":").append(version);
                        strBuilder.append(",\"objectStores\":[");
            objectStores.get(0).toJson(strBuilder);
            for (int i = 1; i < objectStores.size(); ++i)
                objectStores.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public DatabaseWithObjectStores() {}
        public DatabaseWithObjectStores(
            @JsonProperty("name")String name,
            @JsonProperty("version")Integer version,
            @JsonProperty("objectStores")List<ObjectStore> objectStores
        ) {
            this.name = name;
            this.version = version;
            this.objectStores = objectStores;
        }
    }

    /**Object store.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ObjectStore implements CommonDomainType {
        /**Object store name.*/
        private String name;
        /**Object store key path.*/
        private KeyPath keyPath;
        /**If true, object store has auto increment flag set.*/
        private Boolean autoIncrement;
        /**Indexes in this object store.*/
        private List<ObjectStoreIndex> indexes;
        public final ObjectStore name(String name) { this.name = name; return this; }
        public final ObjectStore setName(String name) { return name(name); }
        public final String name() { return name; }
        public final String getName() { return name(); }
        public final ObjectStore keyPath(KeyPath keyPath) { this.keyPath = keyPath; return this; }
        public final ObjectStore setKeyPath(KeyPath keyPath) { return keyPath(keyPath); }
        public final KeyPath keyPath() { return keyPath; }
        public final KeyPath getKeyPath() { return keyPath(); }
        public final ObjectStore autoIncrement(Boolean autoIncrement) { this.autoIncrement = autoIncrement; return this; }
        public final ObjectStore setAutoIncrement(Boolean autoIncrement) { return autoIncrement(autoIncrement); }
        public final Boolean autoIncrement() { return autoIncrement; }
        public final Boolean getAutoIncrement() { return autoIncrement(); }
        public final ObjectStore indexes(List<ObjectStoreIndex> indexes) { this.indexes = indexes; return this; }
        public final ObjectStore setIndexes(List<ObjectStoreIndex> indexes) { return indexes(indexes); }
        public final List<ObjectStoreIndex> indexes() { return indexes; }
        public final List<ObjectStoreIndex> getIndexes() { return indexes(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (name == null) throw new IllegalArgumentException("IndexedDB.ObjectStore.name is necessary field.");
            if (keyPath == null) throw new IllegalArgumentException("IndexedDB.ObjectStore.keyPath is necessary field.");
            if (autoIncrement == null) throw new IllegalArgumentException("IndexedDB.ObjectStore.autoIncrement is necessary field.");
            if (indexes == null) throw new IllegalArgumentException("IndexedDB.ObjectStore.indexes is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"name\":").append('"').append(DomainBase.escapeJson(name)).append('"');
            keyPath.toJson(strBuilder.append(",\"keyPath\":"));
            strBuilder.append(",\"autoIncrement\":").append(autoIncrement);
                        strBuilder.append(",\"indexes\":[");
            indexes.get(0).toJson(strBuilder);
            for (int i = 1; i < indexes.size(); ++i)
                indexes.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public ObjectStore() {}
        public ObjectStore(
            @JsonProperty("name")String name,
            @JsonProperty("keyPath")KeyPath keyPath,
            @JsonProperty("autoIncrement")Boolean autoIncrement,
            @JsonProperty("indexes")List<ObjectStoreIndex> indexes
        ) {
            this.name = name;
            this.keyPath = keyPath;
            this.autoIncrement = autoIncrement;
            this.indexes = indexes;
        }
    }

    /**Object store index.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ObjectStoreIndex implements CommonDomainType {
        /**Index name.*/
        private String name;
        /**Index key path.*/
        private KeyPath keyPath;
        /**If true, index is unique.*/
        private Boolean unique;
        /**If true, index allows multiple entries for a key.*/
        private Boolean multiEntry;
        public final ObjectStoreIndex name(String name) { this.name = name; return this; }
        public final ObjectStoreIndex setName(String name) { return name(name); }
        public final String name() { return name; }
        public final String getName() { return name(); }
        public final ObjectStoreIndex keyPath(KeyPath keyPath) { this.keyPath = keyPath; return this; }
        public final ObjectStoreIndex setKeyPath(KeyPath keyPath) { return keyPath(keyPath); }
        public final KeyPath keyPath() { return keyPath; }
        public final KeyPath getKeyPath() { return keyPath(); }
        public final ObjectStoreIndex unique(Boolean unique) { this.unique = unique; return this; }
        public final ObjectStoreIndex setUnique(Boolean unique) { return unique(unique); }
        public final Boolean unique() { return unique; }
        public final Boolean getUnique() { return unique(); }
        public final ObjectStoreIndex multiEntry(Boolean multiEntry) { this.multiEntry = multiEntry; return this; }
        public final ObjectStoreIndex setMultiEntry(Boolean multiEntry) { return multiEntry(multiEntry); }
        public final Boolean multiEntry() { return multiEntry; }
        public final Boolean getMultiEntry() { return multiEntry(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (name == null) throw new IllegalArgumentException("IndexedDB.ObjectStoreIndex.name is necessary field.");
            if (keyPath == null) throw new IllegalArgumentException("IndexedDB.ObjectStoreIndex.keyPath is necessary field.");
            if (unique == null) throw new IllegalArgumentException("IndexedDB.ObjectStoreIndex.unique is necessary field.");
            if (multiEntry == null) throw new IllegalArgumentException("IndexedDB.ObjectStoreIndex.multiEntry is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"name\":").append('"').append(DomainBase.escapeJson(name)).append('"');
            keyPath.toJson(strBuilder.append(",\"keyPath\":"));
            strBuilder.append(",\"unique\":").append(unique);
            strBuilder.append(",\"multiEntry\":").append(multiEntry);
            strBuilder.append('}');
            return strBuilder;
        }
        public ObjectStoreIndex() {}
        public ObjectStoreIndex(
            @JsonProperty("name")String name,
            @JsonProperty("keyPath")KeyPath keyPath,
            @JsonProperty("unique")Boolean unique,
            @JsonProperty("multiEntry")Boolean multiEntry
        ) {
            this.name = name;
            this.keyPath = keyPath;
            this.unique = unique;
            this.multiEntry = multiEntry;
        }
    }

    /**Key.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class Key implements CommonDomainType {
        /**Key type.*/
        @ParametersAreNonnullByDefault public enum Type implements CommonDomainType {
            Number("number"),
            String("string"),
            Date("date"),
            Array("array");

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
        /**Number value.
        <em>Optional.</em>*/
        private Double number;
        /**String value.
        <em>Optional.</em>*/
        private String string;
        /**Date value.
        <em>Optional.</em>*/
        private Double date;
        /**Array value.
        <em>Optional.</em>*/
        private List<Key> array;
        public final Key type(Type type) { this.type = type; return this; }
        public final Key setType(Type type) { return type(type); }
        public final Type type() { return type; }
        public final Type getType() { return type(); }
        public final Key number(@Nullable Double number) { this.number = number; return this; }
        public final Key optDouble(@Nullable Double number) { return number(number); }
        public final Double number() { return number; }
        public final Double getDouble() { return number(); }
        public final Key string(@Nullable String string) { this.string = string; return this; }
        public final Key optString(@Nullable String string) { return string(string); }
        public final String string() { return string; }
        public final String getString() { return string(); }
        public final Key date(@Nullable Double date) { this.date = date; return this; }
        public final Key optDate(@Nullable Double date) { return date(date); }
        public final Double date() { return date; }
        public final Double getDate() { return date(); }
        public final Key array(@Nullable List<Key> array) { this.array = array; return this; }
        public final Key optArray(@Nullable List<Key> array) { return array(array); }
        public final List<Key> array() { return array; }
        public final List<Key> getArray() { return array(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (type == null) throw new IllegalArgumentException("IndexedDB.Key.type is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"type\":").append(type);
            if (number != null) strBuilder.append(",\"number\":").append(number);
            if (string != null) strBuilder.append(",\"string\":").append('"').append(DomainBase.escapeJson(string)).append('"');
            if (date != null) strBuilder.append(",\"date\":").append(date);
            if (array != null) {
                strBuilder.append(",\"array\":[");
                array.get(0).toJson(strBuilder);
                for (int i = 1; i < array.size(); ++i)
                    array.get(i).toJson(strBuilder.append(','));
                strBuilder.append(']');
            }
            strBuilder.append('}');
            return strBuilder;
        }
        public Key() {}
        public Key(
            @JsonProperty("type")Type type,
            @Nullable @JsonProperty("number")Double number,
            @Nullable @JsonProperty("string")String string,
            @Nullable @JsonProperty("date")Double date,
            @Nullable @JsonProperty("array")List<Key> array
        ) {
            this.type = type;
            this.number = number;
            this.string = string;
            this.date = date;
            this.array = array;
        }
    }

    /**Key range.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class KeyRange implements CommonDomainType {
        /**Lower bound.
        <em>Optional.</em>*/
        private Key lower;
        /**Upper bound.
        <em>Optional.</em>*/
        private Key upper;
        /**If true lower bound is open.*/
        private Boolean lowerOpen;
        /**If true upper bound is open.*/
        private Boolean upperOpen;
        public final KeyRange lower(@Nullable Key lower) { this.lower = lower; return this; }
        public final KeyRange optLower(@Nullable Key lower) { return lower(lower); }
        public final Key lower() { return lower; }
        public final Key getLower() { return lower(); }
        public final KeyRange upper(@Nullable Key upper) { this.upper = upper; return this; }
        public final KeyRange optUpper(@Nullable Key upper) { return upper(upper); }
        public final Key upper() { return upper; }
        public final Key getUpper() { return upper(); }
        public final KeyRange lowerOpen(Boolean lowerOpen) { this.lowerOpen = lowerOpen; return this; }
        public final KeyRange setLowerOpen(Boolean lowerOpen) { return lowerOpen(lowerOpen); }
        public final Boolean lowerOpen() { return lowerOpen; }
        public final Boolean getLowerOpen() { return lowerOpen(); }
        public final KeyRange upperOpen(Boolean upperOpen) { this.upperOpen = upperOpen; return this; }
        public final KeyRange setUpperOpen(Boolean upperOpen) { return upperOpen(upperOpen); }
        public final Boolean upperOpen() { return upperOpen; }
        public final Boolean getUpperOpen() { return upperOpen(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (lowerOpen == null) throw new IllegalArgumentException("IndexedDB.KeyRange.lowerOpen is necessary field.");
            if (upperOpen == null) throw new IllegalArgumentException("IndexedDB.KeyRange.upperOpen is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            if (lower != null) lower.toJson(strBuilder.append("\"lower\":"));
            if (upper != null) upper.toJson(strBuilder.append(",\"upper\":"));
            strBuilder.append(",\"lowerOpen\":").append(lowerOpen);
            strBuilder.append(",\"upperOpen\":").append(upperOpen);
            strBuilder.append('}');
            return strBuilder;
        }
        public KeyRange() {}
        public KeyRange(
            @Nullable @JsonProperty("lower")Key lower,
            @Nullable @JsonProperty("upper")Key upper,
            @JsonProperty("lowerOpen")Boolean lowerOpen,
            @JsonProperty("upperOpen")Boolean upperOpen
        ) {
            this.lower = lower;
            this.upper = upper;
            this.lowerOpen = lowerOpen;
            this.upperOpen = upperOpen;
        }
    }

    /**Data entry.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class DataEntry implements CommonDomainType {
        /**Key object.*/
        private Runtime.RemoteObject key;
        /**Primary key object.*/
        private Runtime.RemoteObject primaryKey;
        /**Value object.*/
        private Runtime.RemoteObject value;
        public final DataEntry key(Runtime.RemoteObject key) { this.key = key; return this; }
        public final DataEntry setKey(Runtime.RemoteObject key) { return key(key); }
        public final Runtime.RemoteObject key() { return key; }
        public final Runtime.RemoteObject getKey() { return key(); }
        public final DataEntry primaryKey(Runtime.RemoteObject primaryKey) { this.primaryKey = primaryKey; return this; }
        public final DataEntry setPrimaryKey(Runtime.RemoteObject primaryKey) { return primaryKey(primaryKey); }
        public final Runtime.RemoteObject primaryKey() { return primaryKey; }
        public final Runtime.RemoteObject getPrimaryKey() { return primaryKey(); }
        public final DataEntry value(Runtime.RemoteObject value) { this.value = value; return this; }
        public final DataEntry setValue(Runtime.RemoteObject value) { return value(value); }
        public final Runtime.RemoteObject value() { return value; }
        public final Runtime.RemoteObject getValue() { return value(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (key == null) throw new IllegalArgumentException("IndexedDB.DataEntry.key is necessary field.");
            if (primaryKey == null) throw new IllegalArgumentException("IndexedDB.DataEntry.primaryKey is necessary field.");
            if (value == null) throw new IllegalArgumentException("IndexedDB.DataEntry.value is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            key.toJson(strBuilder.append("\"key\":"));
            primaryKey.toJson(strBuilder.append(",\"primaryKey\":"));
            value.toJson(strBuilder.append(",\"value\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public DataEntry() {}
        public DataEntry(
            @JsonProperty("key")Runtime.RemoteObject key,
            @JsonProperty("primaryKey")Runtime.RemoteObject primaryKey,
            @JsonProperty("value")Runtime.RemoteObject value
        ) {
            this.key = key;
            this.primaryKey = primaryKey;
            this.value = value;
        }
    }

    /**Key path.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class KeyPath implements CommonDomainType {
        /**Key path type.*/
        @ParametersAreNonnullByDefault public enum Type implements CommonDomainType {
            Null("null"),
            String("string"),
            Array("array");

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
        /**String value.
        <em>Optional.</em>*/
        private String string;
        /**Array value.
        <em>Optional.</em>*/
        private List<String> array;
        public final KeyPath type(Type type) { this.type = type; return this; }
        public final KeyPath setType(Type type) { return type(type); }
        public final Type type() { return type; }
        public final Type getType() { return type(); }
        public final KeyPath string(@Nullable String string) { this.string = string; return this; }
        public final KeyPath optString(@Nullable String string) { return string(string); }
        public final String string() { return string; }
        public final String getString() { return string(); }
        public final KeyPath array(@Nullable List<String> array) { this.array = array; return this; }
        public final KeyPath optArray(@Nullable List<String> array) { return array(array); }
        public final List<String> array() { return array; }
        public final List<String> getArray() { return array(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (type == null) throw new IllegalArgumentException("IndexedDB.KeyPath.type is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"type\":").append(type);
            if (string != null) strBuilder.append(",\"string\":").append('"').append(DomainBase.escapeJson(string)).append('"');
            if (array != null) {
                strBuilder.append(",\"array\":[");
                strBuilder.append('"').append(DomainBase.escapeJson(array.get(0))).append('"');
                for (int i = 1; i < array.size(); ++i)
                    strBuilder.append(",\"").append(DomainBase.escapeJson(array.get(i))).append('"');
                strBuilder.append(']');
            }
            strBuilder.append('}');
            return strBuilder;
        }
        public KeyPath() {}
        public KeyPath(
            @JsonProperty("type")Type type,
            @Nullable @JsonProperty("string")String string,
            @Nullable @JsonProperty("array")List<String> array
        ) {
            this.type = type;
            this.string = string;
            this.array = array;
        }
    }
    /**Clears all entries from an object store.*/
    public ClearObjectStoreParameter clearObjectStore() { final ClearObjectStoreParameter v = new ClearObjectStoreParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of clearObjectStore.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ClearObjectStoreParameter extends CommandBase {
        /**Security origin.*/
        private String securityOrigin;
        /**Database name.*/
        private String databaseName;
        /**Object store name.*/
        private String objectStoreName;
        public final ClearObjectStoreParameter securityOrigin(String securityOrigin) { this.securityOrigin = securityOrigin; return this; }
        public final ClearObjectStoreParameter setSecurityOrigin(String securityOrigin) { return securityOrigin(securityOrigin); }
        public final String securityOrigin() { return securityOrigin; }
        public final String getSecurityOrigin() { return securityOrigin(); }
        public final ClearObjectStoreParameter databaseName(String databaseName) { this.databaseName = databaseName; return this; }
        public final ClearObjectStoreParameter setDatabaseName(String databaseName) { return databaseName(databaseName); }
        public final String databaseName() { return databaseName; }
        public final String getDatabaseName() { return databaseName(); }
        public final ClearObjectStoreParameter objectStoreName(String objectStoreName) { this.objectStoreName = objectStoreName; return this; }
        public final ClearObjectStoreParameter setObjectStoreName(String objectStoreName) { return objectStoreName(objectStoreName); }
        public final String objectStoreName() { return objectStoreName; }
        public final String getObjectStoreName() { return objectStoreName(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (securityOrigin == null) throw new IllegalArgumentException("IndexedDB.ClearObjectStoreParameter.securityOrigin is necessary field.");
            if (databaseName == null) throw new IllegalArgumentException("IndexedDB.ClearObjectStoreParameter.databaseName is necessary field.");
            if (objectStoreName == null) throw new IllegalArgumentException("IndexedDB.ClearObjectStoreParameter.objectStoreName is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"securityOrigin\":").append('"').append(DomainBase.escapeJson(securityOrigin)).append('"');
            strBuilder.append(",\"databaseName\":").append('"').append(DomainBase.escapeJson(databaseName)).append('"');
            strBuilder.append(",\"objectStoreName\":").append('"').append(DomainBase.escapeJson(objectStoreName)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public ClearObjectStoreParameter() {}
        public ClearObjectStoreParameter(
            @JsonProperty("securityOrigin")String securityOrigin,
            @JsonProperty("databaseName")String databaseName,
            @JsonProperty("objectStoreName")String objectStoreName
        ) {
            this();
            this.securityOrigin = securityOrigin;
            this.databaseName = databaseName;
            this.objectStoreName = objectStoreName;
        }
        public CompletableFuture<ClearObjectStoreResult> call() {
            return super.call("IndexedDB.clearObjectStore", ClearObjectStoreResult.class,
                (code, msg)->new ClearObjectStoreResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<ClearObjectStoreResult> callAsync() {
            return super.callAsync("IndexedDB.clearObjectStore", ClearObjectStoreResult.class,
                (code, msg)->new ClearObjectStoreResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<ClearObjectStoreResult> callAsync(Executor exec) {
            return super.callAsync("IndexedDB.clearObjectStore", ClearObjectStoreResult.class,
                (code, msg)->new ClearObjectStoreResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of clearObjectStore.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ClearObjectStoreResult extends ResultBase {
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
        public ClearObjectStoreResult() { super(); }
        public ClearObjectStoreResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Deletes a database.*/
    public DeleteDatabaseParameter deleteDatabase() { final DeleteDatabaseParameter v = new DeleteDatabaseParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of deleteDatabase.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class DeleteDatabaseParameter extends CommandBase {
        /**Security origin.*/
        private String securityOrigin;
        /**Database name.*/
        private String databaseName;
        public final DeleteDatabaseParameter securityOrigin(String securityOrigin) { this.securityOrigin = securityOrigin; return this; }
        public final DeleteDatabaseParameter setSecurityOrigin(String securityOrigin) { return securityOrigin(securityOrigin); }
        public final String securityOrigin() { return securityOrigin; }
        public final String getSecurityOrigin() { return securityOrigin(); }
        public final DeleteDatabaseParameter databaseName(String databaseName) { this.databaseName = databaseName; return this; }
        public final DeleteDatabaseParameter setDatabaseName(String databaseName) { return databaseName(databaseName); }
        public final String databaseName() { return databaseName; }
        public final String getDatabaseName() { return databaseName(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (securityOrigin == null) throw new IllegalArgumentException("IndexedDB.DeleteDatabaseParameter.securityOrigin is necessary field.");
            if (databaseName == null) throw new IllegalArgumentException("IndexedDB.DeleteDatabaseParameter.databaseName is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"securityOrigin\":").append('"').append(DomainBase.escapeJson(securityOrigin)).append('"');
            strBuilder.append(",\"databaseName\":").append('"').append(DomainBase.escapeJson(databaseName)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public DeleteDatabaseParameter() {}
        public DeleteDatabaseParameter(
            @JsonProperty("securityOrigin")String securityOrigin,
            @JsonProperty("databaseName")String databaseName
        ) {
            this();
            this.securityOrigin = securityOrigin;
            this.databaseName = databaseName;
        }
        public CompletableFuture<DeleteDatabaseResult> call() {
            return super.call("IndexedDB.deleteDatabase", DeleteDatabaseResult.class,
                (code, msg)->new DeleteDatabaseResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<DeleteDatabaseResult> callAsync() {
            return super.callAsync("IndexedDB.deleteDatabase", DeleteDatabaseResult.class,
                (code, msg)->new DeleteDatabaseResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<DeleteDatabaseResult> callAsync(Executor exec) {
            return super.callAsync("IndexedDB.deleteDatabase", DeleteDatabaseResult.class,
                (code, msg)->new DeleteDatabaseResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of deleteDatabase.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class DeleteDatabaseResult extends ResultBase {
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
        public DeleteDatabaseResult() { super(); }
        public DeleteDatabaseResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Delete a range of entries from an object store*/
    public DeleteObjectStoreEntriesParameter deleteObjectStoreEntries() { final DeleteObjectStoreEntriesParameter v = new DeleteObjectStoreEntriesParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of deleteObjectStoreEntries.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class DeleteObjectStoreEntriesParameter extends CommandBase {
        /**&lt;No document in protocol.&gt;*/
        private String securityOrigin;
        /**&lt;No document in protocol.&gt;*/
        private String databaseName;
        /**&lt;No document in protocol.&gt;*/
        private String objectStoreName;
        /**Range of entry keys to delete*/
        private KeyRange keyRange;
        public final DeleteObjectStoreEntriesParameter securityOrigin(String securityOrigin) { this.securityOrigin = securityOrigin; return this; }
        public final DeleteObjectStoreEntriesParameter setSecurityOrigin(String securityOrigin) { return securityOrigin(securityOrigin); }
        public final String securityOrigin() { return securityOrigin; }
        public final String getSecurityOrigin() { return securityOrigin(); }
        public final DeleteObjectStoreEntriesParameter databaseName(String databaseName) { this.databaseName = databaseName; return this; }
        public final DeleteObjectStoreEntriesParameter setDatabaseName(String databaseName) { return databaseName(databaseName); }
        public final String databaseName() { return databaseName; }
        public final String getDatabaseName() { return databaseName(); }
        public final DeleteObjectStoreEntriesParameter objectStoreName(String objectStoreName) { this.objectStoreName = objectStoreName; return this; }
        public final DeleteObjectStoreEntriesParameter setObjectStoreName(String objectStoreName) { return objectStoreName(objectStoreName); }
        public final String objectStoreName() { return objectStoreName; }
        public final String getObjectStoreName() { return objectStoreName(); }
        public final DeleteObjectStoreEntriesParameter keyRange(KeyRange keyRange) { this.keyRange = keyRange; return this; }
        public final DeleteObjectStoreEntriesParameter setKeyRange(KeyRange keyRange) { return keyRange(keyRange); }
        public final KeyRange keyRange() { return keyRange; }
        public final KeyRange getKeyRange() { return keyRange(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (securityOrigin == null) throw new IllegalArgumentException("IndexedDB.DeleteObjectStoreEntriesParameter.securityOrigin is necessary field.");
            if (databaseName == null) throw new IllegalArgumentException("IndexedDB.DeleteObjectStoreEntriesParameter.databaseName is necessary field.");
            if (objectStoreName == null) throw new IllegalArgumentException("IndexedDB.DeleteObjectStoreEntriesParameter.objectStoreName is necessary field.");
            if (keyRange == null) throw new IllegalArgumentException("IndexedDB.DeleteObjectStoreEntriesParameter.keyRange is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"securityOrigin\":").append('"').append(DomainBase.escapeJson(securityOrigin)).append('"');
            strBuilder.append(",\"databaseName\":").append('"').append(DomainBase.escapeJson(databaseName)).append('"');
            strBuilder.append(",\"objectStoreName\":").append('"').append(DomainBase.escapeJson(objectStoreName)).append('"');
            keyRange.toJson(strBuilder.append(",\"keyRange\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public DeleteObjectStoreEntriesParameter() {}
        public DeleteObjectStoreEntriesParameter(
            @JsonProperty("securityOrigin")String securityOrigin,
            @JsonProperty("databaseName")String databaseName,
            @JsonProperty("objectStoreName")String objectStoreName,
            @JsonProperty("keyRange")KeyRange keyRange
        ) {
            this();
            this.securityOrigin = securityOrigin;
            this.databaseName = databaseName;
            this.objectStoreName = objectStoreName;
            this.keyRange = keyRange;
        }
        public CompletableFuture<DeleteObjectStoreEntriesResult> call() {
            return super.call("IndexedDB.deleteObjectStoreEntries", DeleteObjectStoreEntriesResult.class,
                (code, msg)->new DeleteObjectStoreEntriesResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<DeleteObjectStoreEntriesResult> callAsync() {
            return super.callAsync("IndexedDB.deleteObjectStoreEntries", DeleteObjectStoreEntriesResult.class,
                (code, msg)->new DeleteObjectStoreEntriesResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<DeleteObjectStoreEntriesResult> callAsync(Executor exec) {
            return super.callAsync("IndexedDB.deleteObjectStoreEntries", DeleteObjectStoreEntriesResult.class,
                (code, msg)->new DeleteObjectStoreEntriesResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of deleteObjectStoreEntries.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class DeleteObjectStoreEntriesResult extends ResultBase {
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
        public DeleteObjectStoreEntriesResult() { super(); }
        public DeleteObjectStoreEntriesResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Disables events from backend.*/
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
            return super.call("IndexedDB.disable", DisableResult.class,
                (code, msg)->new DisableResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<DisableResult> callAsync() {
            return super.callAsync("IndexedDB.disable", DisableResult.class,
                (code, msg)->new DisableResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<DisableResult> callAsync(Executor exec) {
            return super.callAsync("IndexedDB.disable", DisableResult.class,
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
    /**Enables events from backend.*/
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
            return super.call("IndexedDB.enable", EnableResult.class,
                (code, msg)->new EnableResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<EnableResult> callAsync() {
            return super.callAsync("IndexedDB.enable", EnableResult.class,
                (code, msg)->new EnableResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<EnableResult> callAsync(Executor exec) {
            return super.callAsync("IndexedDB.enable", EnableResult.class,
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
    /**Requests data from object store or index.*/
    public RequestDataParameter requestData() { final RequestDataParameter v = new RequestDataParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of requestData.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class RequestDataParameter extends CommandBase {
        /**Security origin.*/
        private String securityOrigin;
        /**Database name.*/
        private String databaseName;
        /**Object store name.*/
        private String objectStoreName;
        /**Index name, empty string for object store data requests.*/
        private String indexName;
        /**Number of records to skip.*/
        private Integer skipCount;
        /**Number of records to fetch.*/
        private Integer pageSize;
        /**Key range.
        <em>Optional.</em>*/
        private KeyRange keyRange;
        public final RequestDataParameter securityOrigin(String securityOrigin) { this.securityOrigin = securityOrigin; return this; }
        public final RequestDataParameter setSecurityOrigin(String securityOrigin) { return securityOrigin(securityOrigin); }
        public final String securityOrigin() { return securityOrigin; }
        public final String getSecurityOrigin() { return securityOrigin(); }
        public final RequestDataParameter databaseName(String databaseName) { this.databaseName = databaseName; return this; }
        public final RequestDataParameter setDatabaseName(String databaseName) { return databaseName(databaseName); }
        public final String databaseName() { return databaseName; }
        public final String getDatabaseName() { return databaseName(); }
        public final RequestDataParameter objectStoreName(String objectStoreName) { this.objectStoreName = objectStoreName; return this; }
        public final RequestDataParameter setObjectStoreName(String objectStoreName) { return objectStoreName(objectStoreName); }
        public final String objectStoreName() { return objectStoreName; }
        public final String getObjectStoreName() { return objectStoreName(); }
        public final RequestDataParameter indexName(String indexName) { this.indexName = indexName; return this; }
        public final RequestDataParameter setIndexName(String indexName) { return indexName(indexName); }
        public final String indexName() { return indexName; }
        public final String getIndexName() { return indexName(); }
        public final RequestDataParameter skipCount(Integer skipCount) { this.skipCount = skipCount; return this; }
        public final RequestDataParameter setSkipCount(Integer skipCount) { return skipCount(skipCount); }
        public final Integer skipCount() { return skipCount; }
        public final Integer getSkipCount() { return skipCount(); }
        public final RequestDataParameter pageSize(Integer pageSize) { this.pageSize = pageSize; return this; }
        public final RequestDataParameter setPageSize(Integer pageSize) { return pageSize(pageSize); }
        public final Integer pageSize() { return pageSize; }
        public final Integer getPageSize() { return pageSize(); }
        public final RequestDataParameter keyRange(@Nullable KeyRange keyRange) { this.keyRange = keyRange; return this; }
        public final RequestDataParameter optKeyRange(@Nullable KeyRange keyRange) { return keyRange(keyRange); }
        public final KeyRange keyRange() { return keyRange; }
        public final KeyRange getKeyRange() { return keyRange(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (securityOrigin == null) throw new IllegalArgumentException("IndexedDB.RequestDataParameter.securityOrigin is necessary field.");
            if (databaseName == null) throw new IllegalArgumentException("IndexedDB.RequestDataParameter.databaseName is necessary field.");
            if (objectStoreName == null) throw new IllegalArgumentException("IndexedDB.RequestDataParameter.objectStoreName is necessary field.");
            if (indexName == null) throw new IllegalArgumentException("IndexedDB.RequestDataParameter.indexName is necessary field.");
            if (skipCount == null) throw new IllegalArgumentException("IndexedDB.RequestDataParameter.skipCount is necessary field.");
            if (pageSize == null) throw new IllegalArgumentException("IndexedDB.RequestDataParameter.pageSize is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"securityOrigin\":").append('"').append(DomainBase.escapeJson(securityOrigin)).append('"');
            strBuilder.append(",\"databaseName\":").append('"').append(DomainBase.escapeJson(databaseName)).append('"');
            strBuilder.append(",\"objectStoreName\":").append('"').append(DomainBase.escapeJson(objectStoreName)).append('"');
            strBuilder.append(",\"indexName\":").append('"').append(DomainBase.escapeJson(indexName)).append('"');
            strBuilder.append(",\"skipCount\":").append(skipCount);
            strBuilder.append(",\"pageSize\":").append(pageSize);
            if (keyRange != null) keyRange.toJson(strBuilder.append(",\"keyRange\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public RequestDataParameter() {}
        public RequestDataParameter(
            @JsonProperty("securityOrigin")String securityOrigin,
            @JsonProperty("databaseName")String databaseName,
            @JsonProperty("objectStoreName")String objectStoreName,
            @JsonProperty("indexName")String indexName,
            @JsonProperty("skipCount")Integer skipCount,
            @JsonProperty("pageSize")Integer pageSize,
            @Nullable @JsonProperty("keyRange")KeyRange keyRange
        ) {
            this();
            this.securityOrigin = securityOrigin;
            this.databaseName = databaseName;
            this.objectStoreName = objectStoreName;
            this.indexName = indexName;
            this.skipCount = skipCount;
            this.pageSize = pageSize;
            this.keyRange = keyRange;
        }
        public CompletableFuture<RequestDataResult> call() {
            return super.call("IndexedDB.requestData", RequestDataResult.class,
                (code, msg)->new RequestDataResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<RequestDataResult> callAsync() {
            return super.callAsync("IndexedDB.requestData", RequestDataResult.class,
                (code, msg)->new RequestDataResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<RequestDataResult> callAsync(Executor exec) {
            return super.callAsync("IndexedDB.requestData", RequestDataResult.class,
                (code, msg)->new RequestDataResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of requestData.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class RequestDataResult extends ResultBase {
        /**Array of object store data entries.*/
        private final List<DataEntry> objectStoreDataEntries;
        /**If true, there are more entries to fetch in the given range.*/
        private final Boolean hasMore;
        public final List<DataEntry> objectStoreDataEntries() { return objectStoreDataEntries; }
        public final List<DataEntry> getObjectStoreDataEntries() { return objectStoreDataEntries(); }
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
                        strBuilder.append("\"objectStoreDataEntries\":[");
            objectStoreDataEntries.get(0).toJson(strBuilder);
            for (int i = 1; i < objectStoreDataEntries.size(); ++i)
                objectStoreDataEntries.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            strBuilder.append(",\"hasMore\":").append(hasMore);
            strBuilder.append('}');
            return strBuilder;
        }
        public RequestDataResult(
            @JsonProperty("objectStoreDataEntries")List<DataEntry> objectStoreDataEntries,
            @JsonProperty("hasMore")Boolean hasMore
        ) {
            this.objectStoreDataEntries = objectStoreDataEntries;
            this.hasMore = hasMore;
        }
        public RequestDataResult(ResultBase.FailedResult e) {
            super(e);
            objectStoreDataEntries = null;
            hasMore = null;
        }
    }
    /**Requests database with given name in given frame.*/
    public RequestDatabaseParameter requestDatabase() { final RequestDatabaseParameter v = new RequestDatabaseParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of requestDatabase.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class RequestDatabaseParameter extends CommandBase {
        /**Security origin.*/
        private String securityOrigin;
        /**Database name.*/
        private String databaseName;
        public final RequestDatabaseParameter securityOrigin(String securityOrigin) { this.securityOrigin = securityOrigin; return this; }
        public final RequestDatabaseParameter setSecurityOrigin(String securityOrigin) { return securityOrigin(securityOrigin); }
        public final String securityOrigin() { return securityOrigin; }
        public final String getSecurityOrigin() { return securityOrigin(); }
        public final RequestDatabaseParameter databaseName(String databaseName) { this.databaseName = databaseName; return this; }
        public final RequestDatabaseParameter setDatabaseName(String databaseName) { return databaseName(databaseName); }
        public final String databaseName() { return databaseName; }
        public final String getDatabaseName() { return databaseName(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (securityOrigin == null) throw new IllegalArgumentException("IndexedDB.RequestDatabaseParameter.securityOrigin is necessary field.");
            if (databaseName == null) throw new IllegalArgumentException("IndexedDB.RequestDatabaseParameter.databaseName is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"securityOrigin\":").append('"').append(DomainBase.escapeJson(securityOrigin)).append('"');
            strBuilder.append(",\"databaseName\":").append('"').append(DomainBase.escapeJson(databaseName)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public RequestDatabaseParameter() {}
        public RequestDatabaseParameter(
            @JsonProperty("securityOrigin")String securityOrigin,
            @JsonProperty("databaseName")String databaseName
        ) {
            this();
            this.securityOrigin = securityOrigin;
            this.databaseName = databaseName;
        }
        public CompletableFuture<RequestDatabaseResult> call() {
            return super.call("IndexedDB.requestDatabase", RequestDatabaseResult.class,
                (code, msg)->new RequestDatabaseResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<RequestDatabaseResult> callAsync() {
            return super.callAsync("IndexedDB.requestDatabase", RequestDatabaseResult.class,
                (code, msg)->new RequestDatabaseResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<RequestDatabaseResult> callAsync(Executor exec) {
            return super.callAsync("IndexedDB.requestDatabase", RequestDatabaseResult.class,
                (code, msg)->new RequestDatabaseResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of requestDatabase.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class RequestDatabaseResult extends ResultBase {
        /**Database with an array of object stores.*/
        private final DatabaseWithObjectStores databaseWithObjectStores;
        public final DatabaseWithObjectStores databaseWithObjectStores() { return databaseWithObjectStores; }
        public final DatabaseWithObjectStores getDatabaseWithObjectStores() { return databaseWithObjectStores(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            databaseWithObjectStores.toJson(strBuilder.append("\"databaseWithObjectStores\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public RequestDatabaseResult(
            @JsonProperty("databaseWithObjectStores")DatabaseWithObjectStores databaseWithObjectStores
        ) {
            this.databaseWithObjectStores = databaseWithObjectStores;
        }
        public RequestDatabaseResult(ResultBase.FailedResult e) {
            super(e);
            databaseWithObjectStores = null;
        }
    }
    /**Requests database names for given security origin.*/
    public RequestDatabaseNamesParameter requestDatabaseNames() { final RequestDatabaseNamesParameter v = new RequestDatabaseNamesParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of requestDatabaseNames.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class RequestDatabaseNamesParameter extends CommandBase {
        /**Security origin.*/
        private String securityOrigin;
        public final RequestDatabaseNamesParameter securityOrigin(String securityOrigin) { this.securityOrigin = securityOrigin; return this; }
        public final RequestDatabaseNamesParameter setSecurityOrigin(String securityOrigin) { return securityOrigin(securityOrigin); }
        public final String securityOrigin() { return securityOrigin; }
        public final String getSecurityOrigin() { return securityOrigin(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (securityOrigin == null) throw new IllegalArgumentException("IndexedDB.RequestDatabaseNamesParameter.securityOrigin is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"securityOrigin\":").append('"').append(DomainBase.escapeJson(securityOrigin)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public RequestDatabaseNamesParameter() {}
        public RequestDatabaseNamesParameter(
            @JsonProperty("securityOrigin")String securityOrigin
        ) {
            this();
            this.securityOrigin = securityOrigin;
        }
        public CompletableFuture<RequestDatabaseNamesResult> call() {
            return super.call("IndexedDB.requestDatabaseNames", RequestDatabaseNamesResult.class,
                (code, msg)->new RequestDatabaseNamesResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<RequestDatabaseNamesResult> callAsync() {
            return super.callAsync("IndexedDB.requestDatabaseNames", RequestDatabaseNamesResult.class,
                (code, msg)->new RequestDatabaseNamesResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<RequestDatabaseNamesResult> callAsync(Executor exec) {
            return super.callAsync("IndexedDB.requestDatabaseNames", RequestDatabaseNamesResult.class,
                (code, msg)->new RequestDatabaseNamesResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of requestDatabaseNames.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class RequestDatabaseNamesResult extends ResultBase {
        /**Database names for origin.*/
        private final List<String> databaseNames;
        public final List<String> databaseNames() { return databaseNames; }
        public final List<String> getDatabaseNames() { return databaseNames(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
                        strBuilder.append("\"databaseNames\":[");
            strBuilder.append('"').append(DomainBase.escapeJson(databaseNames.get(0))).append('"');
            for (int i = 1; i < databaseNames.size(); ++i)
                strBuilder.append(",\"").append(DomainBase.escapeJson(databaseNames.get(i))).append('"');
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public RequestDatabaseNamesResult(
            @JsonProperty("databaseNames")List<String> databaseNames
        ) {
            this.databaseNames = databaseNames;
        }
        public RequestDatabaseNamesResult(ResultBase.FailedResult e) {
            super(e);
            databaseNames = null;
        }
    }
}

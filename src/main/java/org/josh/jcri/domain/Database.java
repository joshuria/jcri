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
@ParametersAreNonnullByDefault public class Database extends DomainBase {
    public Database(EventCenter evt, WebSocket ws) { super(evt, ws); }

    /**Unique identifier of Database object.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class DatabaseId implements CommonDomainType {
        private String _value;
        public DatabaseId() {}
        public DatabaseId(String value) { _value = value; }
        public final DatabaseId value(String value) { _value = value; return this; }
        public final String value() { return _value; }
        public final DatabaseId setValue(String value) { return value(value); }
        public final String getValue() { return value(); }
        @Override public String toString() { return _value; }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (_value == null) throw new IllegalArgumentException("Database.DatabaseId.value is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            return strBuilder.append('"').append(DomainBase.escapeJson(_value)).append('"');
        }
    }

    /**Database object.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class DatabaseType implements CommonDomainType {
        /**Database ID.*/
        private DatabaseId id;
        /**Database domain.*/
        private String domain;
        /**Database name.*/
        private String name;
        /**Database version.*/
        private String version;
        public final DatabaseType id(DatabaseId id) { this.id = id; return this; }
        public final DatabaseType setId(DatabaseId id) { return id(id); }
        public final DatabaseId id() { return id; }
        public final DatabaseId getId() { return id(); }
        public final DatabaseType domain(String domain) { this.domain = domain; return this; }
        public final DatabaseType setDomain(String domain) { return domain(domain); }
        public final String domain() { return domain; }
        public final String getDomain() { return domain(); }
        public final DatabaseType name(String name) { this.name = name; return this; }
        public final DatabaseType setName(String name) { return name(name); }
        public final String name() { return name; }
        public final String getName() { return name(); }
        public final DatabaseType version(String version) { this.version = version; return this; }
        public final DatabaseType setVersion(String version) { return version(version); }
        public final String version() { return version; }
        public final String getVersion() { return version(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (id == null) throw new IllegalArgumentException("Database.DatabaseType.id is necessary field.");
            if (domain == null) throw new IllegalArgumentException("Database.DatabaseType.domain is necessary field.");
            if (name == null) throw new IllegalArgumentException("Database.DatabaseType.name is necessary field.");
            if (version == null) throw new IllegalArgumentException("Database.DatabaseType.version is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            id.toJson(strBuilder.append("\"id\":"));
            strBuilder.append(",\"domain\":").append('"').append(DomainBase.escapeJson(domain)).append('"');
            strBuilder.append(",\"name\":").append('"').append(DomainBase.escapeJson(name)).append('"');
            strBuilder.append(",\"version\":").append('"').append(DomainBase.escapeJson(version)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public DatabaseType() {}
        public DatabaseType(
            @JsonProperty("id")DatabaseId id,
            @JsonProperty("domain")String domain,
            @JsonProperty("name")String name,
            @JsonProperty("version")String version
        ) {
            this.id = id;
            this.domain = domain;
            this.name = name;
            this.version = version;
        }
    }

    /**Database error.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class Error implements CommonDomainType {
        /**Error message.*/
        private String message;
        /**Error code.*/
        private Integer code;
        public final Error message(String message) { this.message = message; return this; }
        public final Error setMessage(String message) { return message(message); }
        public final String message() { return message; }
        public final String getMessage() { return message(); }
        public final Error code(Integer code) { this.code = code; return this; }
        public final Error setCode(Integer code) { return code(code); }
        public final Integer code() { return code; }
        public final Integer getCode() { return code(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (message == null) throw new IllegalArgumentException("Database.Error.message is necessary field.");
            if (code == null) throw new IllegalArgumentException("Database.Error.code is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"message\":").append('"').append(DomainBase.escapeJson(message)).append('"');
            strBuilder.append(",\"code\":").append(code);
            strBuilder.append('}');
            return strBuilder;
        }
        public Error() {}
        public Error(
            @JsonProperty("message")String message,
            @JsonProperty("code")Integer code
        ) {
            this.message = message;
            this.code = code;
        }
    }
    /**Disables database tracking, prevents database events from being sent to the client.*/
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
            return super.call("Database.disable", DisableResult.class,
                (code, msg)->new DisableResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<DisableResult> callAsync() {
            return super.callAsync("Database.disable", DisableResult.class,
                (code, msg)->new DisableResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<DisableResult> callAsync(Executor exec) {
            return super.callAsync("Database.disable", DisableResult.class,
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
    /**Enables database tracking, database events will now be delivered to the client.*/
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
            return super.call("Database.enable", EnableResult.class,
                (code, msg)->new EnableResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<EnableResult> callAsync() {
            return super.callAsync("Database.enable", EnableResult.class,
                (code, msg)->new EnableResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<EnableResult> callAsync(Executor exec) {
            return super.callAsync("Database.enable", EnableResult.class,
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
    public ExecuteSQLParameter executeSQL() { final ExecuteSQLParameter v = new ExecuteSQLParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of executeSQL.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ExecuteSQLParameter extends CommandBase {
        /**&lt;No document in protocol.&gt;*/
        private DatabaseId databaseId;
        /**&lt;No document in protocol.&gt;*/
        private String query;
        public final ExecuteSQLParameter databaseId(DatabaseId databaseId) { this.databaseId = databaseId; return this; }
        public final ExecuteSQLParameter setDatabaseId(DatabaseId databaseId) { return databaseId(databaseId); }
        public final DatabaseId databaseId() { return databaseId; }
        public final DatabaseId getDatabaseId() { return databaseId(); }
        public final ExecuteSQLParameter query(String query) { this.query = query; return this; }
        public final ExecuteSQLParameter setQuery(String query) { return query(query); }
        public final String query() { return query; }
        public final String getQuery() { return query(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (databaseId == null) throw new IllegalArgumentException("Database.ExecuteSQLParameter.databaseId is necessary field.");
            if (query == null) throw new IllegalArgumentException("Database.ExecuteSQLParameter.query is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            databaseId.toJson(strBuilder.append("\"databaseId\":"));
            strBuilder.append(",\"query\":").append('"').append(DomainBase.escapeJson(query)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public ExecuteSQLParameter() {}
        public ExecuteSQLParameter(
            @JsonProperty("databaseId")DatabaseId databaseId,
            @JsonProperty("query")String query
        ) {
            this();
            this.databaseId = databaseId;
            this.query = query;
        }
        public CompletableFuture<ExecuteSQLResult> call() {
            return super.call("Database.executeSQL", ExecuteSQLResult.class,
                (code, msg)->new ExecuteSQLResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<ExecuteSQLResult> callAsync() {
            return super.callAsync("Database.executeSQL", ExecuteSQLResult.class,
                (code, msg)->new ExecuteSQLResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<ExecuteSQLResult> callAsync(Executor exec) {
            return super.callAsync("Database.executeSQL", ExecuteSQLResult.class,
                (code, msg)->new ExecuteSQLResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of executeSQL.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ExecuteSQLResult extends ResultBase {
        /**&lt;No document in protocol.&gt;
        <em>Optional.</em>*/
        private final List<String> columnNames;
        /**&lt;No document in protocol.&gt;
        <em>Optional.</em>*/
        private final List<Object> values;
        /**&lt;No document in protocol.&gt;
        <em>Optional.</em>*/
        private final Error sqlError;
        public final List<String> columnNames() { return columnNames; }
        public final List<String> getColumnNames() { return columnNames(); }
        public final List<Object> values() { return values; }
        public final List<Object> getValues() { return values(); }
        public final Error sqlError() { return sqlError; }
        public final Error getSqlError() { return sqlError(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            if (columnNames != null) {
                strBuilder.append("\"columnNames\":[");
                strBuilder.append('"').append(DomainBase.escapeJson(columnNames.get(0))).append('"');
                for (int i = 1; i < columnNames.size(); ++i)
                    strBuilder.append(",\"").append(DomainBase.escapeJson(columnNames.get(i))).append('"');
                strBuilder.append(']');
            }
            if (values != null) {
                strBuilder.append(",\"values\":[");
                strBuilder.append(values.get(0));
                for (int i = 1; i < values.size(); ++i)
                    strBuilder.append(',').append(values.get(i));
                strBuilder.append(']');
            }
            if (sqlError != null) sqlError.toJson(strBuilder.append(",\"sqlError\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public ExecuteSQLResult(
            @Nullable @JsonProperty("columnNames")List<String> columnNames,
            @Nullable @JsonProperty("values")List<Object> values,
            @Nullable @JsonProperty("sqlError")Error sqlError
        ) {
            this.columnNames = columnNames;
            this.values = values;
            this.sqlError = sqlError;
        }
        public ExecuteSQLResult(ResultBase.FailedResult e) {
            super(e);
            columnNames = null;
            values = null;
            sqlError = null;
        }
    }
    /**&lt;No document in protocol.&gt;*/
    public GetDatabaseTableNamesParameter getDatabaseTableNames() { final GetDatabaseTableNamesParameter v = new GetDatabaseTableNamesParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of getDatabaseTableNames.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetDatabaseTableNamesParameter extends CommandBase {
        /**&lt;No document in protocol.&gt;*/
        private DatabaseId databaseId;
        public final GetDatabaseTableNamesParameter databaseId(DatabaseId databaseId) { this.databaseId = databaseId; return this; }
        public final GetDatabaseTableNamesParameter setDatabaseId(DatabaseId databaseId) { return databaseId(databaseId); }
        public final DatabaseId databaseId() { return databaseId; }
        public final DatabaseId getDatabaseId() { return databaseId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (databaseId == null) throw new IllegalArgumentException("Database.GetDatabaseTableNamesParameter.databaseId is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            databaseId.toJson(strBuilder.append("\"databaseId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public GetDatabaseTableNamesParameter() {}
        public GetDatabaseTableNamesParameter(
            @JsonProperty("databaseId")DatabaseId databaseId
        ) {
            this();
            this.databaseId = databaseId;
        }
        public CompletableFuture<GetDatabaseTableNamesResult> call() {
            return super.call("Database.getDatabaseTableNames", GetDatabaseTableNamesResult.class,
                (code, msg)->new GetDatabaseTableNamesResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetDatabaseTableNamesResult> callAsync() {
            return super.callAsync("Database.getDatabaseTableNames", GetDatabaseTableNamesResult.class,
                (code, msg)->new GetDatabaseTableNamesResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetDatabaseTableNamesResult> callAsync(Executor exec) {
            return super.callAsync("Database.getDatabaseTableNames", GetDatabaseTableNamesResult.class,
                (code, msg)->new GetDatabaseTableNamesResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of getDatabaseTableNames.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetDatabaseTableNamesResult extends ResultBase {
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
        public GetDatabaseTableNamesResult(
            @JsonProperty("tableNames")List<String> tableNames
        ) {
            this.tableNames = tableNames;
        }
        public GetDatabaseTableNamesResult(ResultBase.FailedResult e) {
            super(e);
            tableNames = null;
        }
    }
    /**Event parameter of Database.addDatabase.
     @see #onAddDatabase*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class AddDatabaseEventParameter implements CommonDomainType {
        /**&lt;No document in protocol.&gt;*/
        private final DatabaseType database;
        public final DatabaseType database() { return database; }
        public final DatabaseType getDatabase() { return database(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            database.toJson(strBuilder.append("\"database\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        AddDatabaseEventParameter(
            @JsonProperty("database")DatabaseType database
        ) {
            this.database = database;
        }
    }
    /**&lt;No document in protocol.&gt;
     @see AddDatabaseEventParameter*/
    public void onAddDatabase(@Nullable Consumer<AddDatabaseEventParameter> callback) {
        if (callback != null)
            registerEventCallback("Database.addDatabase", node -> {
                AddDatabaseEventParameter param;
                try { param = EventCenter.deserializeJson(node, AddDatabaseEventParameter.class); }
                catch (IOException e) { _evt.getLog().error(e); return; }
                callback.accept(param);
            });
        else    registerEventCallback("Database.addDatabase", null);
    }
}

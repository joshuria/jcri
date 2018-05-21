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

/**Runtime domain exposes JavaScript runtime by means of remote evaluation and mirror objects.
Evaluation results are returned as mirror object that expose object type, string representation
and unique identifier that can be used for further object reference. Original objects are
maintained in memory unless they are either explicitly released or are released along with the
other objects in their object group.
<p>From: js_protocol.json</p>
<p>Protocol version: 1.3</p>
 @author Joshua*/
@ParametersAreNonnullByDefault public class Runtime extends DomainBase {
    public Runtime(EventCenter evt, WebSocket ws) { super(evt, ws); }

    /**Unique script identifier.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ScriptId implements CommonDomainType {
        private String _value;
        public ScriptId() {}
        public ScriptId(String value) { _value = value; }
        public final ScriptId value(String value) { _value = value; return this; }
        public final String value() { return _value; }
        public final ScriptId setValue(String value) { return value(value); }
        public final String getValue() { return value(); }
        @Override public String toString() { return _value; }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (_value == null) throw new IllegalArgumentException("Runtime.ScriptId.value is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            return strBuilder.append('"').append(DomainBase.escapeJson(_value)).append('"');
        }
    }

    /**Unique object identifier.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class RemoteObjectId implements CommonDomainType {
        private String _value;
        public RemoteObjectId() {}
        public RemoteObjectId(String value) { _value = value; }
        public final RemoteObjectId value(String value) { _value = value; return this; }
        public final String value() { return _value; }
        public final RemoteObjectId setValue(String value) { return value(value); }
        public final String getValue() { return value(); }
        @Override public String toString() { return _value; }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (_value == null) throw new IllegalArgumentException("Runtime.RemoteObjectId.value is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            return strBuilder.append('"').append(DomainBase.escapeJson(_value)).append('"');
        }
    }

    /**Primitive value which cannot be JSON-stringified. Includes values `-0`, `NaN`, `Infinity`,
`-Infinity`, and bigint literals.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class UnserializableValue implements CommonDomainType {
        private String _value;
        public UnserializableValue() {}
        public UnserializableValue(String value) { _value = value; }
        public final UnserializableValue value(String value) { _value = value; return this; }
        public final String value() { return _value; }
        public final UnserializableValue setValue(String value) { return value(value); }
        public final String getValue() { return value(); }
        @Override public String toString() { return _value; }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (_value == null) throw new IllegalArgumentException("Runtime.UnserializableValue.value is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            return strBuilder.append('"').append(DomainBase.escapeJson(_value)).append('"');
        }
    }

    /**Mirror object referencing original JavaScript object.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class RemoteObject implements CommonDomainType {
        /**Object type.*/
        @ParametersAreNonnullByDefault public enum Type implements CommonDomainType {
            Object("object"),
            Function("function"),
            Undefined("undefined"),
            String("string"),
            Number("number"),
            Boolean("boolean"),
            Symbol("symbol"),
            Bigint("bigint");

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
        /**Object subtype hint. Specified for `object` type values only.
        <em>Optional.</em>*/
        @ParametersAreNonnullByDefault public enum Subtype implements CommonDomainType {
            Array("array"),
            Null("null"),
            Node("node"),
            Regexp("regexp"),
            Date("date"),
            Map("map"),
            Set("set"),
            Weakmap("weakmap"),
            Weakset("weakset"),
            Iterator("iterator"),
            Generator("generator"),
            Error("error"),
            Proxy("proxy"),
            Promise("promise"),
            Typedarray("typedarray");

            private final String _value;
            /**Convert string representation to type.
             @throws IllegalArgumentException if given value cannot convert to enum type. */
            @JsonCreator public static Subtype of(String value) {
                return Enum.valueOf(Subtype.class, value.substring(0, 1).toUpperCase() + value.substring(1));
            }
            Subtype(String value) { _value = value; }
            /**Check if parameter fields of method are all valid. */
            @Override public void check() throws IllegalArgumentException { /* Need not check */ }
            /**Convert method parameter object into json string and append into string builder.
             @return string builder instance that is given in parameter (for chaining coding style use.) */
            @Override public StringBuilder toJson(StringBuilder strBuilder) { return strBuilder.append('"').append(_value).append('"'); }
            @Override public String toString() { return "\"" + _value + "\""; }
        }
        private Subtype subtype;
        /**Object class (constructor) name. Specified for `object` type values only.
        <em>Optional.</em>*/
        private String className;
        /**Remote object value in case of primitive values or JSON values (if it was requested).
        <em>Optional.</em>*/
        private Object value;
        /**Primitive value which can not be JSON-stringified does not have `value`, but gets this
property.
        <em>Optional.</em>*/
        private UnserializableValue unserializableValue;
        /**String representation of the object.
        <em>Optional.</em>*/
        private String description;
        /**Unique object identifier (for non-primitive values).
        <em>Optional.</em>*/
        private RemoteObjectId objectId;
        /**Preview containing abbreviated property values. Specified for `object` type values only.
        <em>Optional.</em>
        <p><strong>Experimental.</strong></p>*/
        private ObjectPreview preview;
        /**&lt;No document in protocol.&gt;
        <em>Optional.</em>
        <p><strong>Experimental.</strong></p>*/
        private CustomPreview customPreview;
        public final RemoteObject type(Type type) { this.type = type; return this; }
        public final RemoteObject setType(Type type) { return type(type); }
        public final Type type() { return type; }
        public final Type getType() { return type(); }
        public final RemoteObject subtype(@Nullable Subtype subtype) { this.subtype = subtype; return this; }
        public final RemoteObject optSubtype(@Nullable Subtype subtype) { return subtype(subtype); }
        public final Subtype subtype() { return subtype; }
        public final Subtype getSubtype() { return subtype(); }
        public final RemoteObject className(@Nullable String className) { this.className = className; return this; }
        public final RemoteObject optClassName(@Nullable String className) { return className(className); }
        public final String className() { return className; }
        public final String getClassName() { return className(); }
        public final RemoteObject value(@Nullable Object value) { this.value = value; return this; }
        public final RemoteObject optValue(@Nullable Object value) { return value(value); }
        public final Object value() { return value; }
        public final Object getValue() { return value(); }
        public final RemoteObject unserializableValue(@Nullable UnserializableValue unserializableValue) { this.unserializableValue = unserializableValue; return this; }
        public final RemoteObject optUnserializableValue(@Nullable UnserializableValue unserializableValue) { return unserializableValue(unserializableValue); }
        public final UnserializableValue unserializableValue() { return unserializableValue; }
        public final UnserializableValue getUnserializableValue() { return unserializableValue(); }
        public final RemoteObject description(@Nullable String description) { this.description = description; return this; }
        public final RemoteObject optDescription(@Nullable String description) { return description(description); }
        public final String description() { return description; }
        public final String getDescription() { return description(); }
        public final RemoteObject objectId(@Nullable RemoteObjectId objectId) { this.objectId = objectId; return this; }
        public final RemoteObject optObjectId(@Nullable RemoteObjectId objectId) { return objectId(objectId); }
        public final RemoteObjectId objectId() { return objectId; }
        public final RemoteObjectId getObjectId() { return objectId(); }
        public final RemoteObject preview(@Nullable ObjectPreview preview) { this.preview = preview; return this; }
        public final RemoteObject optPreview(@Nullable ObjectPreview preview) { return preview(preview); }
        public final ObjectPreview preview() { return preview; }
        public final ObjectPreview getPreview() { return preview(); }
        public final RemoteObject customPreview(@Nullable CustomPreview customPreview) { this.customPreview = customPreview; return this; }
        public final RemoteObject optCustomPreview(@Nullable CustomPreview customPreview) { return customPreview(customPreview); }
        public final CustomPreview customPreview() { return customPreview; }
        public final CustomPreview getCustomPreview() { return customPreview(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (type == null) throw new IllegalArgumentException("Runtime.RemoteObject.type is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"type\":").append(type);
            if (subtype != null) strBuilder.append(",\"subtype\":").append(subtype);
            if (className != null) strBuilder.append(",\"className\":").append('"').append(DomainBase.escapeJson(className)).append('"');
            if (value != null) strBuilder.append(",\"value\":").append(value);
            if (unserializableValue != null) unserializableValue.toJson(strBuilder.append(",\"unserializableValue\":"));
            if (description != null) strBuilder.append(",\"description\":").append('"').append(DomainBase.escapeJson(description)).append('"');
            if (objectId != null) objectId.toJson(strBuilder.append(",\"objectId\":"));
            if (preview != null) preview.toJson(strBuilder.append(",\"preview\":"));
            if (customPreview != null) customPreview.toJson(strBuilder.append(",\"customPreview\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public RemoteObject() {}
        public RemoteObject(
            @JsonProperty("type")Type type,
            @Nullable @JsonProperty("subtype")Subtype subtype,
            @Nullable @JsonProperty("className")String className,
            @Nullable @JsonProperty("value")Object value,
            @Nullable @JsonProperty("unserializableValue")UnserializableValue unserializableValue,
            @Nullable @JsonProperty("description")String description,
            @Nullable @JsonProperty("objectId")RemoteObjectId objectId,
            @Nullable @JsonProperty("preview")ObjectPreview preview,
            @Nullable @JsonProperty("customPreview")CustomPreview customPreview
        ) {
            this.type = type;
            this.subtype = subtype;
            this.className = className;
            this.value = value;
            this.unserializableValue = unserializableValue;
            this.description = description;
            this.objectId = objectId;
            this.preview = preview;
            this.customPreview = customPreview;
        }
    }

    /**&lt;No document in protocol.&gt;
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class CustomPreview implements CommonDomainType {
        /**&lt;No document in protocol.&gt;*/
        private String header;
        /**&lt;No document in protocol.&gt;*/
        private Boolean hasBody;
        /**&lt;No document in protocol.&gt;*/
        private RemoteObjectId formatterObjectId;
        /**&lt;No document in protocol.&gt;*/
        private RemoteObjectId bindRemoteObjectFunctionId;
        /**&lt;No document in protocol.&gt;
        <em>Optional.</em>*/
        private RemoteObjectId configObjectId;
        public final CustomPreview header(String header) { this.header = header; return this; }
        public final CustomPreview setHeader(String header) { return header(header); }
        public final String header() { return header; }
        public final String getHeader() { return header(); }
        public final CustomPreview hasBody(Boolean hasBody) { this.hasBody = hasBody; return this; }
        public final CustomPreview setHasBody(Boolean hasBody) { return hasBody(hasBody); }
        public final Boolean hasBody() { return hasBody; }
        public final Boolean getHasBody() { return hasBody(); }
        public final CustomPreview formatterObjectId(RemoteObjectId formatterObjectId) { this.formatterObjectId = formatterObjectId; return this; }
        public final CustomPreview setFormatterObjectId(RemoteObjectId formatterObjectId) { return formatterObjectId(formatterObjectId); }
        public final RemoteObjectId formatterObjectId() { return formatterObjectId; }
        public final RemoteObjectId getFormatterObjectId() { return formatterObjectId(); }
        public final CustomPreview bindRemoteObjectFunctionId(RemoteObjectId bindRemoteObjectFunctionId) { this.bindRemoteObjectFunctionId = bindRemoteObjectFunctionId; return this; }
        public final CustomPreview setBindRemoteObjectFunctionId(RemoteObjectId bindRemoteObjectFunctionId) { return bindRemoteObjectFunctionId(bindRemoteObjectFunctionId); }
        public final RemoteObjectId bindRemoteObjectFunctionId() { return bindRemoteObjectFunctionId; }
        public final RemoteObjectId getBindRemoteObjectFunctionId() { return bindRemoteObjectFunctionId(); }
        public final CustomPreview configObjectId(@Nullable RemoteObjectId configObjectId) { this.configObjectId = configObjectId; return this; }
        public final CustomPreview optConfigObjectId(@Nullable RemoteObjectId configObjectId) { return configObjectId(configObjectId); }
        public final RemoteObjectId configObjectId() { return configObjectId; }
        public final RemoteObjectId getConfigObjectId() { return configObjectId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (header == null) throw new IllegalArgumentException("Runtime.CustomPreview.header is necessary field.");
            if (hasBody == null) throw new IllegalArgumentException("Runtime.CustomPreview.hasBody is necessary field.");
            if (formatterObjectId == null) throw new IllegalArgumentException("Runtime.CustomPreview.formatterObjectId is necessary field.");
            if (bindRemoteObjectFunctionId == null) throw new IllegalArgumentException("Runtime.CustomPreview.bindRemoteObjectFunctionId is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"header\":").append('"').append(DomainBase.escapeJson(header)).append('"');
            strBuilder.append(",\"hasBody\":").append(hasBody);
            formatterObjectId.toJson(strBuilder.append(",\"formatterObjectId\":"));
            bindRemoteObjectFunctionId.toJson(strBuilder.append(",\"bindRemoteObjectFunctionId\":"));
            if (configObjectId != null) configObjectId.toJson(strBuilder.append(",\"configObjectId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public CustomPreview() {}
        public CustomPreview(
            @JsonProperty("header")String header,
            @JsonProperty("hasBody")Boolean hasBody,
            @JsonProperty("formatterObjectId")RemoteObjectId formatterObjectId,
            @JsonProperty("bindRemoteObjectFunctionId")RemoteObjectId bindRemoteObjectFunctionId,
            @Nullable @JsonProperty("configObjectId")RemoteObjectId configObjectId
        ) {
            this.header = header;
            this.hasBody = hasBody;
            this.formatterObjectId = formatterObjectId;
            this.bindRemoteObjectFunctionId = bindRemoteObjectFunctionId;
            this.configObjectId = configObjectId;
        }
    }

    /**Object containing abbreviated remote object value.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ObjectPreview implements CommonDomainType {
        /**Object type.*/
        @ParametersAreNonnullByDefault public enum Type implements CommonDomainType {
            Object("object"),
            Function("function"),
            Undefined("undefined"),
            String("string"),
            Number("number"),
            Boolean("boolean"),
            Symbol("symbol"),
            Bigint("bigint");

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
        /**Object subtype hint. Specified for `object` type values only.
        <em>Optional.</em>*/
        @ParametersAreNonnullByDefault public enum Subtype implements CommonDomainType {
            Array("array"),
            Null("null"),
            Node("node"),
            Regexp("regexp"),
            Date("date"),
            Map("map"),
            Set("set"),
            Weakmap("weakmap"),
            Weakset("weakset"),
            Iterator("iterator"),
            Generator("generator"),
            Error("error");

            private final String _value;
            /**Convert string representation to type.
             @throws IllegalArgumentException if given value cannot convert to enum type. */
            @JsonCreator public static Subtype of(String value) {
                return Enum.valueOf(Subtype.class, value.substring(0, 1).toUpperCase() + value.substring(1));
            }
            Subtype(String value) { _value = value; }
            /**Check if parameter fields of method are all valid. */
            @Override public void check() throws IllegalArgumentException { /* Need not check */ }
            /**Convert method parameter object into json string and append into string builder.
             @return string builder instance that is given in parameter (for chaining coding style use.) */
            @Override public StringBuilder toJson(StringBuilder strBuilder) { return strBuilder.append('"').append(_value).append('"'); }
            @Override public String toString() { return "\"" + _value + "\""; }
        }
        private Subtype subtype;
        /**String representation of the object.
        <em>Optional.</em>*/
        private String description;
        /**True iff some of the properties or entries of the original object did not fit.*/
        private Boolean overflow;
        /**List of the properties.*/
        private List<PropertyPreview> properties;
        /**List of the entries. Specified for `map` and `set` subtype values only.
        <em>Optional.</em>*/
        private List<EntryPreview> entries;
        public final ObjectPreview type(Type type) { this.type = type; return this; }
        public final ObjectPreview setType(Type type) { return type(type); }
        public final Type type() { return type; }
        public final Type getType() { return type(); }
        public final ObjectPreview subtype(@Nullable Subtype subtype) { this.subtype = subtype; return this; }
        public final ObjectPreview optSubtype(@Nullable Subtype subtype) { return subtype(subtype); }
        public final Subtype subtype() { return subtype; }
        public final Subtype getSubtype() { return subtype(); }
        public final ObjectPreview description(@Nullable String description) { this.description = description; return this; }
        public final ObjectPreview optDescription(@Nullable String description) { return description(description); }
        public final String description() { return description; }
        public final String getDescription() { return description(); }
        public final ObjectPreview overflow(Boolean overflow) { this.overflow = overflow; return this; }
        public final ObjectPreview setOverflow(Boolean overflow) { return overflow(overflow); }
        public final Boolean overflow() { return overflow; }
        public final Boolean getOverflow() { return overflow(); }
        public final ObjectPreview properties(List<PropertyPreview> properties) { this.properties = properties; return this; }
        public final ObjectPreview setProperties(List<PropertyPreview> properties) { return properties(properties); }
        public final List<PropertyPreview> properties() { return properties; }
        public final List<PropertyPreview> getProperties() { return properties(); }
        public final ObjectPreview entries(@Nullable List<EntryPreview> entries) { this.entries = entries; return this; }
        public final ObjectPreview optEntries(@Nullable List<EntryPreview> entries) { return entries(entries); }
        public final List<EntryPreview> entries() { return entries; }
        public final List<EntryPreview> getEntries() { return entries(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (type == null) throw new IllegalArgumentException("Runtime.ObjectPreview.type is necessary field.");
            if (overflow == null) throw new IllegalArgumentException("Runtime.ObjectPreview.overflow is necessary field.");
            if (properties == null) throw new IllegalArgumentException("Runtime.ObjectPreview.properties is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"type\":").append(type);
            if (subtype != null) strBuilder.append(",\"subtype\":").append(subtype);
            if (description != null) strBuilder.append(",\"description\":").append('"').append(DomainBase.escapeJson(description)).append('"');
            strBuilder.append(",\"overflow\":").append(overflow);
                        strBuilder.append(",\"properties\":[");
            properties.get(0).toJson(strBuilder);
            for (int i = 1; i < properties.size(); ++i)
                properties.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            if (entries != null) {
                strBuilder.append(",\"entries\":[");
                entries.get(0).toJson(strBuilder);
                for (int i = 1; i < entries.size(); ++i)
                    entries.get(i).toJson(strBuilder.append(','));
                strBuilder.append(']');
            }
            strBuilder.append('}');
            return strBuilder;
        }
        public ObjectPreview() {}
        public ObjectPreview(
            @JsonProperty("type")Type type,
            @Nullable @JsonProperty("subtype")Subtype subtype,
            @Nullable @JsonProperty("description")String description,
            @JsonProperty("overflow")Boolean overflow,
            @JsonProperty("properties")List<PropertyPreview> properties,
            @Nullable @JsonProperty("entries")List<EntryPreview> entries
        ) {
            this.type = type;
            this.subtype = subtype;
            this.description = description;
            this.overflow = overflow;
            this.properties = properties;
            this.entries = entries;
        }
    }

    /**&lt;No document in protocol.&gt;
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class PropertyPreview implements CommonDomainType {
        /**Property name.*/
        private String name;
        /**Object type. Accessor means that the property itself is an accessor property.*/
        @ParametersAreNonnullByDefault public enum Type implements CommonDomainType {
            Object("object"),
            Function("function"),
            Undefined("undefined"),
            String("string"),
            Number("number"),
            Boolean("boolean"),
            Symbol("symbol"),
            Accessor("accessor"),
            Bigint("bigint");

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
        /**User-friendly property value string.
        <em>Optional.</em>*/
        private String value;
        /**Nested value preview.
        <em>Optional.</em>*/
        private ObjectPreview valuePreview;
        /**Object subtype hint. Specified for `object` type values only.
        <em>Optional.</em>*/
        @ParametersAreNonnullByDefault public enum Subtype implements CommonDomainType {
            Array("array"),
            Null("null"),
            Node("node"),
            Regexp("regexp"),
            Date("date"),
            Map("map"),
            Set("set"),
            Weakmap("weakmap"),
            Weakset("weakset"),
            Iterator("iterator"),
            Generator("generator"),
            Error("error");

            private final String _value;
            /**Convert string representation to type.
             @throws IllegalArgumentException if given value cannot convert to enum type. */
            @JsonCreator public static Subtype of(String value) {
                return Enum.valueOf(Subtype.class, value.substring(0, 1).toUpperCase() + value.substring(1));
            }
            Subtype(String value) { _value = value; }
            /**Check if parameter fields of method are all valid. */
            @Override public void check() throws IllegalArgumentException { /* Need not check */ }
            /**Convert method parameter object into json string and append into string builder.
             @return string builder instance that is given in parameter (for chaining coding style use.) */
            @Override public StringBuilder toJson(StringBuilder strBuilder) { return strBuilder.append('"').append(_value).append('"'); }
            @Override public String toString() { return "\"" + _value + "\""; }
        }
        private Subtype subtype;
        public final PropertyPreview name(String name) { this.name = name; return this; }
        public final PropertyPreview setName(String name) { return name(name); }
        public final String name() { return name; }
        public final String getName() { return name(); }
        public final PropertyPreview type(Type type) { this.type = type; return this; }
        public final PropertyPreview setType(Type type) { return type(type); }
        public final Type type() { return type; }
        public final Type getType() { return type(); }
        public final PropertyPreview value(@Nullable String value) { this.value = value; return this; }
        public final PropertyPreview optValue(@Nullable String value) { return value(value); }
        public final String value() { return value; }
        public final String getValue() { return value(); }
        public final PropertyPreview valuePreview(@Nullable ObjectPreview valuePreview) { this.valuePreview = valuePreview; return this; }
        public final PropertyPreview optValuePreview(@Nullable ObjectPreview valuePreview) { return valuePreview(valuePreview); }
        public final ObjectPreview valuePreview() { return valuePreview; }
        public final ObjectPreview getValuePreview() { return valuePreview(); }
        public final PropertyPreview subtype(@Nullable Subtype subtype) { this.subtype = subtype; return this; }
        public final PropertyPreview optSubtype(@Nullable Subtype subtype) { return subtype(subtype); }
        public final Subtype subtype() { return subtype; }
        public final Subtype getSubtype() { return subtype(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (name == null) throw new IllegalArgumentException("Runtime.PropertyPreview.name is necessary field.");
            if (type == null) throw new IllegalArgumentException("Runtime.PropertyPreview.type is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"name\":").append('"').append(DomainBase.escapeJson(name)).append('"');
            strBuilder.append(",\"type\":").append(type);
            if (value != null) strBuilder.append(",\"value\":").append('"').append(DomainBase.escapeJson(value)).append('"');
            if (valuePreview != null) valuePreview.toJson(strBuilder.append(",\"valuePreview\":"));
            if (subtype != null) strBuilder.append(",\"subtype\":").append(subtype);
            strBuilder.append('}');
            return strBuilder;
        }
        public PropertyPreview() {}
        public PropertyPreview(
            @JsonProperty("name")String name,
            @JsonProperty("type")Type type,
            @Nullable @JsonProperty("value")String value,
            @Nullable @JsonProperty("valuePreview")ObjectPreview valuePreview,
            @Nullable @JsonProperty("subtype")Subtype subtype
        ) {
            this.name = name;
            this.type = type;
            this.value = value;
            this.valuePreview = valuePreview;
            this.subtype = subtype;
        }
    }

    /**&lt;No document in protocol.&gt;
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class EntryPreview implements CommonDomainType {
        /**Preview of the key. Specified for map-like collection entries.
        <em>Optional.</em>*/
        private ObjectPreview key;
        /**Preview of the value.*/
        private ObjectPreview value;
        public final EntryPreview key(@Nullable ObjectPreview key) { this.key = key; return this; }
        public final EntryPreview optKey(@Nullable ObjectPreview key) { return key(key); }
        public final ObjectPreview key() { return key; }
        public final ObjectPreview getKey() { return key(); }
        public final EntryPreview value(ObjectPreview value) { this.value = value; return this; }
        public final EntryPreview setValue(ObjectPreview value) { return value(value); }
        public final ObjectPreview value() { return value; }
        public final ObjectPreview getValue() { return value(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (value == null) throw new IllegalArgumentException("Runtime.EntryPreview.value is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            if (key != null) key.toJson(strBuilder.append("\"key\":"));
            value.toJson(strBuilder.append(",\"value\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public EntryPreview() {}
        public EntryPreview(
            @Nullable @JsonProperty("key")ObjectPreview key,
            @JsonProperty("value")ObjectPreview value
        ) {
            this.key = key;
            this.value = value;
        }
    }

    /**Object property descriptor.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class PropertyDescriptor implements CommonDomainType {
        /**Property name or symbol description.*/
        private String name;
        /**The value associated with the property.
        <em>Optional.</em>*/
        private RemoteObject value;
        /**True if the value associated with the property may be changed (data descriptors only).
        <em>Optional.</em>*/
        private Boolean writable;
        /**A function which serves as a getter for the property, or `undefined` if there is no getter
(accessor descriptors only).
        <em>Optional.</em>*/
        private RemoteObject get;
        /**A function which serves as a setter for the property, or `undefined` if there is no setter
(accessor descriptors only).
        <em>Optional.</em>*/
        private RemoteObject set;
        /**True if the type of this property descriptor may be changed and if the property may be
deleted from the corresponding object.*/
        private Boolean configurable;
        /**True if this property shows up during enumeration of the properties on the corresponding
object.*/
        private Boolean enumerable;
        /**True if the result was thrown during the evaluation.
        <em>Optional.</em>*/
        private Boolean wasThrown;
        /**True if the property is owned for the object.
        <em>Optional.</em>*/
        private Boolean isOwn;
        /**Property symbol object, if the property is of the `symbol` type.
        <em>Optional.</em>*/
        private RemoteObject symbol;
        public final PropertyDescriptor name(String name) { this.name = name; return this; }
        public final PropertyDescriptor setName(String name) { return name(name); }
        public final String name() { return name; }
        public final String getName() { return name(); }
        public final PropertyDescriptor value(@Nullable RemoteObject value) { this.value = value; return this; }
        public final PropertyDescriptor optValue(@Nullable RemoteObject value) { return value(value); }
        public final RemoteObject value() { return value; }
        public final RemoteObject getValue() { return value(); }
        public final PropertyDescriptor writable(@Nullable Boolean writable) { this.writable = writable; return this; }
        public final PropertyDescriptor optWritable(@Nullable Boolean writable) { return writable(writable); }
        public final Boolean writable() { return writable; }
        public final Boolean getWritable() { return writable(); }
        public final PropertyDescriptor get(@Nullable RemoteObject get) { this.get = get; return this; }
        public final PropertyDescriptor optGet(@Nullable RemoteObject get) { return get(get); }
        public final RemoteObject get() { return get; }
        public final RemoteObject getGet() { return get(); }
        public final PropertyDescriptor set(@Nullable RemoteObject set) { this.set = set; return this; }
        public final PropertyDescriptor optSet(@Nullable RemoteObject set) { return set(set); }
        public final RemoteObject set() { return set; }
        public final RemoteObject getSet() { return set(); }
        public final PropertyDescriptor configurable(Boolean configurable) { this.configurable = configurable; return this; }
        public final PropertyDescriptor setConfigurable(Boolean configurable) { return configurable(configurable); }
        public final Boolean configurable() { return configurable; }
        public final Boolean getConfigurable() { return configurable(); }
        public final PropertyDescriptor enumerable(Boolean enumerable) { this.enumerable = enumerable; return this; }
        public final PropertyDescriptor setEnumerable(Boolean enumerable) { return enumerable(enumerable); }
        public final Boolean enumerable() { return enumerable; }
        public final Boolean getEnumerable() { return enumerable(); }
        public final PropertyDescriptor wasThrown(@Nullable Boolean wasThrown) { this.wasThrown = wasThrown; return this; }
        public final PropertyDescriptor optWasThrown(@Nullable Boolean wasThrown) { return wasThrown(wasThrown); }
        public final Boolean wasThrown() { return wasThrown; }
        public final Boolean getWasThrown() { return wasThrown(); }
        public final PropertyDescriptor isOwn(@Nullable Boolean isOwn) { this.isOwn = isOwn; return this; }
        public final PropertyDescriptor optIsOwn(@Nullable Boolean isOwn) { return isOwn(isOwn); }
        public final Boolean isOwn() { return isOwn; }
        public final Boolean getIsOwn() { return isOwn(); }
        public final PropertyDescriptor symbol(@Nullable RemoteObject symbol) { this.symbol = symbol; return this; }
        public final PropertyDescriptor optSymbol(@Nullable RemoteObject symbol) { return symbol(symbol); }
        public final RemoteObject symbol() { return symbol; }
        public final RemoteObject getSymbol() { return symbol(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (name == null) throw new IllegalArgumentException("Runtime.PropertyDescriptor.name is necessary field.");
            if (configurable == null) throw new IllegalArgumentException("Runtime.PropertyDescriptor.configurable is necessary field.");
            if (enumerable == null) throw new IllegalArgumentException("Runtime.PropertyDescriptor.enumerable is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"name\":").append('"').append(DomainBase.escapeJson(name)).append('"');
            if (value != null) value.toJson(strBuilder.append(",\"value\":"));
            if (writable != null) strBuilder.append(",\"writable\":").append(writable);
            if (get != null) get.toJson(strBuilder.append(",\"get\":"));
            if (set != null) set.toJson(strBuilder.append(",\"set\":"));
            strBuilder.append(",\"configurable\":").append(configurable);
            strBuilder.append(",\"enumerable\":").append(enumerable);
            if (wasThrown != null) strBuilder.append(",\"wasThrown\":").append(wasThrown);
            if (isOwn != null) strBuilder.append(",\"isOwn\":").append(isOwn);
            if (symbol != null) symbol.toJson(strBuilder.append(",\"symbol\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public PropertyDescriptor() {}
        public PropertyDescriptor(
            @JsonProperty("name")String name,
            @Nullable @JsonProperty("value")RemoteObject value,
            @Nullable @JsonProperty("writable")Boolean writable,
            @Nullable @JsonProperty("get")RemoteObject get,
            @Nullable @JsonProperty("set")RemoteObject set,
            @JsonProperty("configurable")Boolean configurable,
            @JsonProperty("enumerable")Boolean enumerable,
            @Nullable @JsonProperty("wasThrown")Boolean wasThrown,
            @Nullable @JsonProperty("isOwn")Boolean isOwn,
            @Nullable @JsonProperty("symbol")RemoteObject symbol
        ) {
            this.name = name;
            this.value = value;
            this.writable = writable;
            this.get = get;
            this.set = set;
            this.configurable = configurable;
            this.enumerable = enumerable;
            this.wasThrown = wasThrown;
            this.isOwn = isOwn;
            this.symbol = symbol;
        }
    }

    /**Object internal property descriptor. This property isn't normally visible in JavaScript code.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class InternalPropertyDescriptor implements CommonDomainType {
        /**Conventional property name.*/
        private String name;
        /**The value associated with the property.
        <em>Optional.</em>*/
        private RemoteObject value;
        public final InternalPropertyDescriptor name(String name) { this.name = name; return this; }
        public final InternalPropertyDescriptor setName(String name) { return name(name); }
        public final String name() { return name; }
        public final String getName() { return name(); }
        public final InternalPropertyDescriptor value(@Nullable RemoteObject value) { this.value = value; return this; }
        public final InternalPropertyDescriptor optValue(@Nullable RemoteObject value) { return value(value); }
        public final RemoteObject value() { return value; }
        public final RemoteObject getValue() { return value(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (name == null) throw new IllegalArgumentException("Runtime.InternalPropertyDescriptor.name is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"name\":").append('"').append(DomainBase.escapeJson(name)).append('"');
            if (value != null) value.toJson(strBuilder.append(",\"value\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public InternalPropertyDescriptor() {}
        public InternalPropertyDescriptor(
            @JsonProperty("name")String name,
            @Nullable @JsonProperty("value")RemoteObject value
        ) {
            this.name = name;
            this.value = value;
        }
    }

    /**Represents function call argument. Either remote object id `objectId`, primitive `value`,
unserializable primitive value or neither of (for undefined) them should be specified.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class CallArgument implements CommonDomainType {
        /**Primitive value or serializable javascript object.
        <em>Optional.</em>*/
        private Object value;
        /**Primitive value which can not be JSON-stringified.
        <em>Optional.</em>*/
        private UnserializableValue unserializableValue;
        /**Remote object handle.
        <em>Optional.</em>*/
        private RemoteObjectId objectId;
        public final CallArgument value(@Nullable Object value) { this.value = value; return this; }
        public final CallArgument optValue(@Nullable Object value) { return value(value); }
        public final Object value() { return value; }
        public final Object getValue() { return value(); }
        public final CallArgument unserializableValue(@Nullable UnserializableValue unserializableValue) { this.unserializableValue = unserializableValue; return this; }
        public final CallArgument optUnserializableValue(@Nullable UnserializableValue unserializableValue) { return unserializableValue(unserializableValue); }
        public final UnserializableValue unserializableValue() { return unserializableValue; }
        public final UnserializableValue getUnserializableValue() { return unserializableValue(); }
        public final CallArgument objectId(@Nullable RemoteObjectId objectId) { this.objectId = objectId; return this; }
        public final CallArgument optObjectId(@Nullable RemoteObjectId objectId) { return objectId(objectId); }
        public final RemoteObjectId objectId() { return objectId; }
        public final RemoteObjectId getObjectId() { return objectId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            if (value != null) strBuilder.append("\"value\":").append(value);
            if (unserializableValue != null) unserializableValue.toJson(strBuilder.append(",\"unserializableValue\":"));
            if (objectId != null) objectId.toJson(strBuilder.append(",\"objectId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public CallArgument() {}
        public CallArgument(
            @Nullable @JsonProperty("value")Object value,
            @Nullable @JsonProperty("unserializableValue")UnserializableValue unserializableValue,
            @Nullable @JsonProperty("objectId")RemoteObjectId objectId
        ) {
            this.value = value;
            this.unserializableValue = unserializableValue;
            this.objectId = objectId;
        }
    }

    /**Id of an execution context.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ExecutionContextId implements CommonDomainType {
        private Integer _value;
        public ExecutionContextId() {}
        public ExecutionContextId(Integer value) { _value = value; }
        public final ExecutionContextId value(Integer value) { _value = value; return this; }
        public final Integer value() { return _value; }
        public final ExecutionContextId setValue(Integer value) { return value(value); }
        public final Integer getValue() { return value(); }
        @Override public String toString() { return String.valueOf(_value); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (_value == null) throw new IllegalArgumentException("Runtime.ExecutionContextId.value is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            return strBuilder.append(_value);
        }
    }

    /**Description of an isolated world.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ExecutionContextDescription implements CommonDomainType {
        /**Unique id of the execution context. It can be used to specify in which execution context
script evaluation should be performed.*/
        private ExecutionContextId id;
        /**Execution context origin.*/
        private String origin;
        /**Human readable name describing given context.*/
        private String name;
        /**Embedder-specific auxiliary data.
        <em>Optional.</em>*/
        private Object auxData;
        public final ExecutionContextDescription id(ExecutionContextId id) { this.id = id; return this; }
        public final ExecutionContextDescription setId(ExecutionContextId id) { return id(id); }
        public final ExecutionContextId id() { return id; }
        public final ExecutionContextId getId() { return id(); }
        public final ExecutionContextDescription origin(String origin) { this.origin = origin; return this; }
        public final ExecutionContextDescription setOrigin(String origin) { return origin(origin); }
        public final String origin() { return origin; }
        public final String getOrigin() { return origin(); }
        public final ExecutionContextDescription name(String name) { this.name = name; return this; }
        public final ExecutionContextDescription setName(String name) { return name(name); }
        public final String name() { return name; }
        public final String getName() { return name(); }
        public final ExecutionContextDescription auxData(@Nullable Object auxData) { this.auxData = auxData; return this; }
        public final ExecutionContextDescription optAuxData(@Nullable Object auxData) { return auxData(auxData); }
        public final Object auxData() { return auxData; }
        public final Object getAuxData() { return auxData(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (id == null) throw new IllegalArgumentException("Runtime.ExecutionContextDescription.id is necessary field.");
            if (origin == null) throw new IllegalArgumentException("Runtime.ExecutionContextDescription.origin is necessary field.");
            if (name == null) throw new IllegalArgumentException("Runtime.ExecutionContextDescription.name is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            id.toJson(strBuilder.append("\"id\":"));
            strBuilder.append(",\"origin\":").append('"').append(DomainBase.escapeJson(origin)).append('"');
            strBuilder.append(",\"name\":").append('"').append(DomainBase.escapeJson(name)).append('"');
            if (auxData != null) strBuilder.append(",\"auxData\":").append(auxData);
            strBuilder.append('}');
            return strBuilder;
        }
        public ExecutionContextDescription() {}
        public ExecutionContextDescription(
            @JsonProperty("id")ExecutionContextId id,
            @JsonProperty("origin")String origin,
            @JsonProperty("name")String name,
            @Nullable @JsonProperty("auxData")Object auxData
        ) {
            this.id = id;
            this.origin = origin;
            this.name = name;
            this.auxData = auxData;
        }
    }

    /**Detailed information about exception (or error) that was thrown during script compilation or
execution.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ExceptionDetails implements CommonDomainType {
        /**Exception id.*/
        private Integer exceptionId;
        /**Exception text, which should be used together with exception object when available.*/
        private String text;
        /**Line number of the exception location (0-based).*/
        private Integer lineNumber;
        /**Column number of the exception location (0-based).*/
        private Integer columnNumber;
        /**Script ID of the exception location.
        <em>Optional.</em>*/
        private ScriptId scriptId;
        /**URL of the exception location, to be used when the script was not reported.
        <em>Optional.</em>*/
        private String url;
        /**JavaScript stack trace if available.
        <em>Optional.</em>*/
        private StackTrace stackTrace;
        /**Exception object if available.
        <em>Optional.</em>*/
        private RemoteObject exception;
        /**Identifier of the context where exception happened.
        <em>Optional.</em>*/
        private ExecutionContextId executionContextId;
        public final ExceptionDetails exceptionId(Integer exceptionId) { this.exceptionId = exceptionId; return this; }
        public final ExceptionDetails setExceptionId(Integer exceptionId) { return exceptionId(exceptionId); }
        public final Integer exceptionId() { return exceptionId; }
        public final Integer getExceptionId() { return exceptionId(); }
        public final ExceptionDetails text(String text) { this.text = text; return this; }
        public final ExceptionDetails setText(String text) { return text(text); }
        public final String text() { return text; }
        public final String getText() { return text(); }
        public final ExceptionDetails lineNumber(Integer lineNumber) { this.lineNumber = lineNumber; return this; }
        public final ExceptionDetails setLineNumber(Integer lineNumber) { return lineNumber(lineNumber); }
        public final Integer lineNumber() { return lineNumber; }
        public final Integer getLineNumber() { return lineNumber(); }
        public final ExceptionDetails columnNumber(Integer columnNumber) { this.columnNumber = columnNumber; return this; }
        public final ExceptionDetails setColumnNumber(Integer columnNumber) { return columnNumber(columnNumber); }
        public final Integer columnNumber() { return columnNumber; }
        public final Integer getColumnNumber() { return columnNumber(); }
        public final ExceptionDetails scriptId(@Nullable ScriptId scriptId) { this.scriptId = scriptId; return this; }
        public final ExceptionDetails optScriptId(@Nullable ScriptId scriptId) { return scriptId(scriptId); }
        public final ScriptId scriptId() { return scriptId; }
        public final ScriptId getScriptId() { return scriptId(); }
        public final ExceptionDetails url(@Nullable String url) { this.url = url; return this; }
        public final ExceptionDetails optUrl(@Nullable String url) { return url(url); }
        public final String url() { return url; }
        public final String getUrl() { return url(); }
        public final ExceptionDetails stackTrace(@Nullable StackTrace stackTrace) { this.stackTrace = stackTrace; return this; }
        public final ExceptionDetails optStackTrace(@Nullable StackTrace stackTrace) { return stackTrace(stackTrace); }
        public final StackTrace stackTrace() { return stackTrace; }
        public final StackTrace getStackTrace() { return stackTrace(); }
        public final ExceptionDetails exception(@Nullable RemoteObject exception) { this.exception = exception; return this; }
        public final ExceptionDetails optException(@Nullable RemoteObject exception) { return exception(exception); }
        public final RemoteObject exception() { return exception; }
        public final RemoteObject getException() { return exception(); }
        public final ExceptionDetails executionContextId(@Nullable ExecutionContextId executionContextId) { this.executionContextId = executionContextId; return this; }
        public final ExceptionDetails optExecutionContextId(@Nullable ExecutionContextId executionContextId) { return executionContextId(executionContextId); }
        public final ExecutionContextId executionContextId() { return executionContextId; }
        public final ExecutionContextId getExecutionContextId() { return executionContextId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (exceptionId == null) throw new IllegalArgumentException("Runtime.ExceptionDetails.exceptionId is necessary field.");
            if (text == null) throw new IllegalArgumentException("Runtime.ExceptionDetails.text is necessary field.");
            if (lineNumber == null) throw new IllegalArgumentException("Runtime.ExceptionDetails.lineNumber is necessary field.");
            if (columnNumber == null) throw new IllegalArgumentException("Runtime.ExceptionDetails.columnNumber is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"exceptionId\":").append(exceptionId);
            strBuilder.append(",\"text\":").append('"').append(DomainBase.escapeJson(text)).append('"');
            strBuilder.append(",\"lineNumber\":").append(lineNumber);
            strBuilder.append(",\"columnNumber\":").append(columnNumber);
            if (scriptId != null) scriptId.toJson(strBuilder.append(",\"scriptId\":"));
            if (url != null) strBuilder.append(",\"url\":").append('"').append(DomainBase.escapeJson(url)).append('"');
            if (stackTrace != null) stackTrace.toJson(strBuilder.append(",\"stackTrace\":"));
            if (exception != null) exception.toJson(strBuilder.append(",\"exception\":"));
            if (executionContextId != null) executionContextId.toJson(strBuilder.append(",\"executionContextId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public ExceptionDetails() {}
        public ExceptionDetails(
            @JsonProperty("exceptionId")Integer exceptionId,
            @JsonProperty("text")String text,
            @JsonProperty("lineNumber")Integer lineNumber,
            @JsonProperty("columnNumber")Integer columnNumber,
            @Nullable @JsonProperty("scriptId")ScriptId scriptId,
            @Nullable @JsonProperty("url")String url,
            @Nullable @JsonProperty("stackTrace")StackTrace stackTrace,
            @Nullable @JsonProperty("exception")RemoteObject exception,
            @Nullable @JsonProperty("executionContextId")ExecutionContextId executionContextId
        ) {
            this.exceptionId = exceptionId;
            this.text = text;
            this.lineNumber = lineNumber;
            this.columnNumber = columnNumber;
            this.scriptId = scriptId;
            this.url = url;
            this.stackTrace = stackTrace;
            this.exception = exception;
            this.executionContextId = executionContextId;
        }
    }

    /**Number of milliseconds since epoch.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class Timestamp implements CommonDomainType {
        private Double _value;
        public Timestamp() {}
        public Timestamp(Double value) { _value = value; }
        public final Timestamp value(Double value) { _value = value; return this; }
        public final Double value() { return _value; }
        public final Timestamp setValue(Double value) { return value(value); }
        public final Double getValue() { return value(); }
        @Override public String toString() { return String.valueOf(_value); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (_value == null) throw new IllegalArgumentException("Runtime.Timestamp.value is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            return strBuilder.append(_value);
        }
    }

    /**Number of milliseconds.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class TimeDelta implements CommonDomainType {
        private Double _value;
        public TimeDelta() {}
        public TimeDelta(Double value) { _value = value; }
        public final TimeDelta value(Double value) { _value = value; return this; }
        public final Double value() { return _value; }
        public final TimeDelta setValue(Double value) { return value(value); }
        public final Double getValue() { return value(); }
        @Override public String toString() { return String.valueOf(_value); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (_value == null) throw new IllegalArgumentException("Runtime.TimeDelta.value is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            return strBuilder.append(_value);
        }
    }

    /**Stack entry for runtime errors and assertions.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class CallFrame implements CommonDomainType {
        /**JavaScript function name.*/
        private String functionName;
        /**JavaScript script id.*/
        private ScriptId scriptId;
        /**JavaScript script name or url.*/
        private String url;
        /**JavaScript script line number (0-based).*/
        private Integer lineNumber;
        /**JavaScript script column number (0-based).*/
        private Integer columnNumber;
        public final CallFrame functionName(String functionName) { this.functionName = functionName; return this; }
        public final CallFrame setFunctionName(String functionName) { return functionName(functionName); }
        public final String functionName() { return functionName; }
        public final String getFunctionName() { return functionName(); }
        public final CallFrame scriptId(ScriptId scriptId) { this.scriptId = scriptId; return this; }
        public final CallFrame setScriptId(ScriptId scriptId) { return scriptId(scriptId); }
        public final ScriptId scriptId() { return scriptId; }
        public final ScriptId getScriptId() { return scriptId(); }
        public final CallFrame url(String url) { this.url = url; return this; }
        public final CallFrame setUrl(String url) { return url(url); }
        public final String url() { return url; }
        public final String getUrl() { return url(); }
        public final CallFrame lineNumber(Integer lineNumber) { this.lineNumber = lineNumber; return this; }
        public final CallFrame setLineNumber(Integer lineNumber) { return lineNumber(lineNumber); }
        public final Integer lineNumber() { return lineNumber; }
        public final Integer getLineNumber() { return lineNumber(); }
        public final CallFrame columnNumber(Integer columnNumber) { this.columnNumber = columnNumber; return this; }
        public final CallFrame setColumnNumber(Integer columnNumber) { return columnNumber(columnNumber); }
        public final Integer columnNumber() { return columnNumber; }
        public final Integer getColumnNumber() { return columnNumber(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (functionName == null) throw new IllegalArgumentException("Runtime.CallFrame.functionName is necessary field.");
            if (scriptId == null) throw new IllegalArgumentException("Runtime.CallFrame.scriptId is necessary field.");
            if (url == null) throw new IllegalArgumentException("Runtime.CallFrame.url is necessary field.");
            if (lineNumber == null) throw new IllegalArgumentException("Runtime.CallFrame.lineNumber is necessary field.");
            if (columnNumber == null) throw new IllegalArgumentException("Runtime.CallFrame.columnNumber is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"functionName\":").append('"').append(DomainBase.escapeJson(functionName)).append('"');
            scriptId.toJson(strBuilder.append(",\"scriptId\":"));
            strBuilder.append(",\"url\":").append('"').append(DomainBase.escapeJson(url)).append('"');
            strBuilder.append(",\"lineNumber\":").append(lineNumber);
            strBuilder.append(",\"columnNumber\":").append(columnNumber);
            strBuilder.append('}');
            return strBuilder;
        }
        public CallFrame() {}
        public CallFrame(
            @JsonProperty("functionName")String functionName,
            @JsonProperty("scriptId")ScriptId scriptId,
            @JsonProperty("url")String url,
            @JsonProperty("lineNumber")Integer lineNumber,
            @JsonProperty("columnNumber")Integer columnNumber
        ) {
            this.functionName = functionName;
            this.scriptId = scriptId;
            this.url = url;
            this.lineNumber = lineNumber;
            this.columnNumber = columnNumber;
        }
    }

    /**Call frames for assertions or error messages.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class StackTrace implements CommonDomainType {
        /**String label of this stack trace. For async traces this may be a name of the function that
initiated the async call.
        <em>Optional.</em>*/
        private String description;
        /**JavaScript function name.*/
        private List<CallFrame> callFrames;
        /**Asynchronous JavaScript stack trace that preceded this stack, if available.
        <em>Optional.</em>*/
        private StackTrace parent;
        /**Asynchronous JavaScript stack trace that preceded this stack, if available.
        <em>Optional.</em>
        <p><strong>Experimental.</strong></p>*/
        private StackTraceId parentId;
        public final StackTrace description(@Nullable String description) { this.description = description; return this; }
        public final StackTrace optDescription(@Nullable String description) { return description(description); }
        public final String description() { return description; }
        public final String getDescription() { return description(); }
        public final StackTrace callFrames(List<CallFrame> callFrames) { this.callFrames = callFrames; return this; }
        public final StackTrace setCallFrames(List<CallFrame> callFrames) { return callFrames(callFrames); }
        public final List<CallFrame> callFrames() { return callFrames; }
        public final List<CallFrame> getCallFrames() { return callFrames(); }
        public final StackTrace parent(@Nullable StackTrace parent) { this.parent = parent; return this; }
        public final StackTrace optParent(@Nullable StackTrace parent) { return parent(parent); }
        public final StackTrace parent() { return parent; }
        public final StackTrace getParent() { return parent(); }
        public final StackTrace parentId(@Nullable StackTraceId parentId) { this.parentId = parentId; return this; }
        public final StackTrace optParentId(@Nullable StackTraceId parentId) { return parentId(parentId); }
        public final StackTraceId parentId() { return parentId; }
        public final StackTraceId getParentId() { return parentId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (callFrames == null) throw new IllegalArgumentException("Runtime.StackTrace.callFrames is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            if (description != null) strBuilder.append("\"description\":").append('"').append(DomainBase.escapeJson(description)).append('"');
                        strBuilder.append(",\"callFrames\":[");
            callFrames.get(0).toJson(strBuilder);
            for (int i = 1; i < callFrames.size(); ++i)
                callFrames.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            if (parent != null) parent.toJson(strBuilder.append(",\"parent\":"));
            if (parentId != null) parentId.toJson(strBuilder.append(",\"parentId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public StackTrace() {}
        public StackTrace(
            @Nullable @JsonProperty("description")String description,
            @JsonProperty("callFrames")List<CallFrame> callFrames,
            @Nullable @JsonProperty("parent")StackTrace parent,
            @Nullable @JsonProperty("parentId")StackTraceId parentId
        ) {
            this.description = description;
            this.callFrames = callFrames;
            this.parent = parent;
            this.parentId = parentId;
        }
    }

    /**Unique identifier of current debugger.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class UniqueDebuggerId implements CommonDomainType {
        private String _value;
        public UniqueDebuggerId() {}
        public UniqueDebuggerId(String value) { _value = value; }
        public final UniqueDebuggerId value(String value) { _value = value; return this; }
        public final String value() { return _value; }
        public final UniqueDebuggerId setValue(String value) { return value(value); }
        public final String getValue() { return value(); }
        @Override public String toString() { return _value; }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (_value == null) throw new IllegalArgumentException("Runtime.UniqueDebuggerId.value is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            return strBuilder.append('"').append(DomainBase.escapeJson(_value)).append('"');
        }
    }

    /**If `debuggerId` is set stack trace comes from another debugger and can be resolved there. This
allows to track cross-debugger calls. See `Runtime.StackTrace` and `Debugger.paused` for usages.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class StackTraceId implements CommonDomainType {
        /**&lt;No document in protocol.&gt;*/
        private String id;
        /**&lt;No document in protocol.&gt;
        <em>Optional.</em>*/
        private UniqueDebuggerId debuggerId;
        public final StackTraceId id(String id) { this.id = id; return this; }
        public final StackTraceId setId(String id) { return id(id); }
        public final String id() { return id; }
        public final String getId() { return id(); }
        public final StackTraceId debuggerId(@Nullable UniqueDebuggerId debuggerId) { this.debuggerId = debuggerId; return this; }
        public final StackTraceId optDebuggerId(@Nullable UniqueDebuggerId debuggerId) { return debuggerId(debuggerId); }
        public final UniqueDebuggerId debuggerId() { return debuggerId; }
        public final UniqueDebuggerId getDebuggerId() { return debuggerId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (id == null) throw new IllegalArgumentException("Runtime.StackTraceId.id is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"id\":").append('"').append(DomainBase.escapeJson(id)).append('"');
            if (debuggerId != null) debuggerId.toJson(strBuilder.append(",\"debuggerId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public StackTraceId() {}
        public StackTraceId(
            @JsonProperty("id")String id,
            @Nullable @JsonProperty("debuggerId")UniqueDebuggerId debuggerId
        ) {
            this.id = id;
            this.debuggerId = debuggerId;
        }
    }
    /**Add handler to promise with given promise object id.*/
    public AwaitPromiseParameter awaitPromise() { final AwaitPromiseParameter v = new AwaitPromiseParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of awaitPromise.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class AwaitPromiseParameter extends CommandBase {
        /**Identifier of the promise.*/
        private RemoteObjectId promiseObjectId;
        /**Whether the result is expected to be a JSON object that should be sent by value.
        <em>Optional.</em>*/
        private Boolean returnByValue;
        /**Whether preview should be generated for the result.
        <em>Optional.</em>*/
        private Boolean generatePreview;
        public final AwaitPromiseParameter promiseObjectId(RemoteObjectId promiseObjectId) { this.promiseObjectId = promiseObjectId; return this; }
        public final AwaitPromiseParameter setPromiseObjectId(RemoteObjectId promiseObjectId) { return promiseObjectId(promiseObjectId); }
        public final RemoteObjectId promiseObjectId() { return promiseObjectId; }
        public final RemoteObjectId getPromiseObjectId() { return promiseObjectId(); }
        public final AwaitPromiseParameter returnByValue(@Nullable Boolean returnByValue) { this.returnByValue = returnByValue; return this; }
        public final AwaitPromiseParameter optReturnByValue(@Nullable Boolean returnByValue) { return returnByValue(returnByValue); }
        public final Boolean returnByValue() { return returnByValue; }
        public final Boolean getReturnByValue() { return returnByValue(); }
        public final AwaitPromiseParameter generatePreview(@Nullable Boolean generatePreview) { this.generatePreview = generatePreview; return this; }
        public final AwaitPromiseParameter optGeneratePreview(@Nullable Boolean generatePreview) { return generatePreview(generatePreview); }
        public final Boolean generatePreview() { return generatePreview; }
        public final Boolean getGeneratePreview() { return generatePreview(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (promiseObjectId == null) throw new IllegalArgumentException("Runtime.AwaitPromiseParameter.promiseObjectId is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            promiseObjectId.toJson(strBuilder.append("\"promiseObjectId\":"));
            if (returnByValue != null) strBuilder.append(",\"returnByValue\":").append(returnByValue);
            if (generatePreview != null) strBuilder.append(",\"generatePreview\":").append(generatePreview);
            strBuilder.append('}');
            return strBuilder;
        }
        public AwaitPromiseParameter() {}
        public AwaitPromiseParameter(
            @JsonProperty("promiseObjectId")RemoteObjectId promiseObjectId,
            @Nullable @JsonProperty("returnByValue")Boolean returnByValue,
            @Nullable @JsonProperty("generatePreview")Boolean generatePreview
        ) {
            this();
            this.promiseObjectId = promiseObjectId;
            this.returnByValue = returnByValue;
            this.generatePreview = generatePreview;
        }
        public CompletableFuture<AwaitPromiseResult> call() {
            return super.call("Runtime.awaitPromise", AwaitPromiseResult.class,
                (code, msg)->new AwaitPromiseResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<AwaitPromiseResult> call(Executor exec) {
            return super.call("Runtime.awaitPromise", AwaitPromiseResult.class,
                (code, msg)->new AwaitPromiseResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of awaitPromise.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class AwaitPromiseResult extends ResultBase {
        /**Promise result. Will contain rejected value if promise was rejected.*/
        private final RemoteObject result;
        /**Exception details if stack strace is available.
        <em>Optional.</em>*/
        private final ExceptionDetails exceptionDetails;
        public final RemoteObject result() { return result; }
        public final RemoteObject getResult() { return result(); }
        public final ExceptionDetails exceptionDetails() { return exceptionDetails; }
        public final ExceptionDetails getExceptionDetails() { return exceptionDetails(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            result.toJson(strBuilder.append("\"result\":"));
            if (exceptionDetails != null) exceptionDetails.toJson(strBuilder.append(",\"exceptionDetails\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public AwaitPromiseResult(
            @JsonProperty("result")RemoteObject result,
            @Nullable @JsonProperty("exceptionDetails")ExceptionDetails exceptionDetails
        ) {
            this.result = result;
            this.exceptionDetails = exceptionDetails;
        }
        public AwaitPromiseResult(ResultBase.FailedResult e) {
            super(e);
            result = null;
            exceptionDetails = null;
        }
    }
    /**Calls function with given declaration on the given object. Object group of the result is
inherited from the target object.*/
    public CallFunctionOnParameter callFunctionOn() { final CallFunctionOnParameter v = new CallFunctionOnParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of callFunctionOn.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class CallFunctionOnParameter extends CommandBase {
        /**Declaration of the function to call.*/
        private String functionDeclaration;
        /**Identifier of the object to call function on. Either objectId or executionContextId should
be specified.
        <em>Optional.</em>*/
        private RemoteObjectId objectId;
        /**Call arguments. All call arguments must belong to the same JavaScript world as the target
object.
        <em>Optional.</em>*/
        private List<CallArgument> arguments;
        /**In silent mode exceptions thrown during evaluation are not reported and do not pause
execution. Overrides `setPauseOnException` state.
        <em>Optional.</em>*/
        private Boolean silent;
        /**Whether the result is expected to be a JSON object which should be sent by value.
        <em>Optional.</em>*/
        private Boolean returnByValue;
        /**Whether preview should be generated for the result.
        <em>Optional.</em>
        <p><strong>Experimental.</strong></p>*/
        private Boolean generatePreview;
        /**Whether execution should be treated as initiated by user in the UI.
        <em>Optional.</em>*/
        private Boolean userGesture;
        /**Whether execution should `await` for resulting value and return once awaited promise is
resolved.
        <em>Optional.</em>*/
        private Boolean awaitPromise;
        /**Specifies execution context which global object will be used to call function on. Either
executionContextId or objectId should be specified.
        <em>Optional.</em>*/
        private ExecutionContextId executionContextId;
        /**Symbolic group name that can be used to release multiple objects. If objectGroup is not
specified and objectId is, objectGroup will be inherited from object.
        <em>Optional.</em>*/
        private String objectGroup;
        public final CallFunctionOnParameter functionDeclaration(String functionDeclaration) { this.functionDeclaration = functionDeclaration; return this; }
        public final CallFunctionOnParameter setFunctionDeclaration(String functionDeclaration) { return functionDeclaration(functionDeclaration); }
        public final String functionDeclaration() { return functionDeclaration; }
        public final String getFunctionDeclaration() { return functionDeclaration(); }
        public final CallFunctionOnParameter objectId(@Nullable RemoteObjectId objectId) { this.objectId = objectId; return this; }
        public final CallFunctionOnParameter optObjectId(@Nullable RemoteObjectId objectId) { return objectId(objectId); }
        public final RemoteObjectId objectId() { return objectId; }
        public final RemoteObjectId getObjectId() { return objectId(); }
        public final CallFunctionOnParameter arguments(@Nullable List<CallArgument> arguments) { this.arguments = arguments; return this; }
        public final CallFunctionOnParameter optArguments(@Nullable List<CallArgument> arguments) { return arguments(arguments); }
        public final List<CallArgument> arguments() { return arguments; }
        public final List<CallArgument> getArguments() { return arguments(); }
        public final CallFunctionOnParameter silent(@Nullable Boolean silent) { this.silent = silent; return this; }
        public final CallFunctionOnParameter optSilent(@Nullable Boolean silent) { return silent(silent); }
        public final Boolean silent() { return silent; }
        public final Boolean getSilent() { return silent(); }
        public final CallFunctionOnParameter returnByValue(@Nullable Boolean returnByValue) { this.returnByValue = returnByValue; return this; }
        public final CallFunctionOnParameter optReturnByValue(@Nullable Boolean returnByValue) { return returnByValue(returnByValue); }
        public final Boolean returnByValue() { return returnByValue; }
        public final Boolean getReturnByValue() { return returnByValue(); }
        public final CallFunctionOnParameter generatePreview(@Nullable Boolean generatePreview) { this.generatePreview = generatePreview; return this; }
        public final CallFunctionOnParameter optGeneratePreview(@Nullable Boolean generatePreview) { return generatePreview(generatePreview); }
        public final Boolean generatePreview() { return generatePreview; }
        public final Boolean getGeneratePreview() { return generatePreview(); }
        public final CallFunctionOnParameter userGesture(@Nullable Boolean userGesture) { this.userGesture = userGesture; return this; }
        public final CallFunctionOnParameter optUserGesture(@Nullable Boolean userGesture) { return userGesture(userGesture); }
        public final Boolean userGesture() { return userGesture; }
        public final Boolean getUserGesture() { return userGesture(); }
        public final CallFunctionOnParameter awaitPromise(@Nullable Boolean awaitPromise) { this.awaitPromise = awaitPromise; return this; }
        public final CallFunctionOnParameter optAwaitPromise(@Nullable Boolean awaitPromise) { return awaitPromise(awaitPromise); }
        public final Boolean awaitPromise() { return awaitPromise; }
        public final Boolean getAwaitPromise() { return awaitPromise(); }
        public final CallFunctionOnParameter executionContextId(@Nullable ExecutionContextId executionContextId) { this.executionContextId = executionContextId; return this; }
        public final CallFunctionOnParameter optExecutionContextId(@Nullable ExecutionContextId executionContextId) { return executionContextId(executionContextId); }
        public final ExecutionContextId executionContextId() { return executionContextId; }
        public final ExecutionContextId getExecutionContextId() { return executionContextId(); }
        public final CallFunctionOnParameter objectGroup(@Nullable String objectGroup) { this.objectGroup = objectGroup; return this; }
        public final CallFunctionOnParameter optObjectGroup(@Nullable String objectGroup) { return objectGroup(objectGroup); }
        public final String objectGroup() { return objectGroup; }
        public final String getObjectGroup() { return objectGroup(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (functionDeclaration == null) throw new IllegalArgumentException("Runtime.CallFunctionOnParameter.functionDeclaration is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"functionDeclaration\":").append('"').append(DomainBase.escapeJson(functionDeclaration)).append('"');
            if (objectId != null) objectId.toJson(strBuilder.append(",\"objectId\":"));
            if (arguments != null) {
                strBuilder.append(",\"arguments\":[");
                arguments.get(0).toJson(strBuilder);
                for (int i = 1; i < arguments.size(); ++i)
                    arguments.get(i).toJson(strBuilder.append(','));
                strBuilder.append(']');
            }
            if (silent != null) strBuilder.append(",\"silent\":").append(silent);
            if (returnByValue != null) strBuilder.append(",\"returnByValue\":").append(returnByValue);
            if (generatePreview != null) strBuilder.append(",\"generatePreview\":").append(generatePreview);
            if (userGesture != null) strBuilder.append(",\"userGesture\":").append(userGesture);
            if (awaitPromise != null) strBuilder.append(",\"awaitPromise\":").append(awaitPromise);
            if (executionContextId != null) executionContextId.toJson(strBuilder.append(",\"executionContextId\":"));
            if (objectGroup != null) strBuilder.append(",\"objectGroup\":").append('"').append(DomainBase.escapeJson(objectGroup)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public CallFunctionOnParameter() {}
        public CallFunctionOnParameter(
            @JsonProperty("functionDeclaration")String functionDeclaration,
            @Nullable @JsonProperty("objectId")RemoteObjectId objectId,
            @Nullable @JsonProperty("arguments")List<CallArgument> arguments,
            @Nullable @JsonProperty("silent")Boolean silent,
            @Nullable @JsonProperty("returnByValue")Boolean returnByValue,
            @Nullable @JsonProperty("generatePreview")Boolean generatePreview,
            @Nullable @JsonProperty("userGesture")Boolean userGesture,
            @Nullable @JsonProperty("awaitPromise")Boolean awaitPromise,
            @Nullable @JsonProperty("executionContextId")ExecutionContextId executionContextId,
            @Nullable @JsonProperty("objectGroup")String objectGroup
        ) {
            this();
            this.functionDeclaration = functionDeclaration;
            this.objectId = objectId;
            this.arguments = arguments;
            this.silent = silent;
            this.returnByValue = returnByValue;
            this.generatePreview = generatePreview;
            this.userGesture = userGesture;
            this.awaitPromise = awaitPromise;
            this.executionContextId = executionContextId;
            this.objectGroup = objectGroup;
        }
        public CompletableFuture<CallFunctionOnResult> call() {
            return super.call("Runtime.callFunctionOn", CallFunctionOnResult.class,
                (code, msg)->new CallFunctionOnResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<CallFunctionOnResult> call(Executor exec) {
            return super.call("Runtime.callFunctionOn", CallFunctionOnResult.class,
                (code, msg)->new CallFunctionOnResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of callFunctionOn.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class CallFunctionOnResult extends ResultBase {
        /**Call result.*/
        private final RemoteObject result;
        /**Exception details.
        <em>Optional.</em>*/
        private final ExceptionDetails exceptionDetails;
        public final RemoteObject result() { return result; }
        public final RemoteObject getResult() { return result(); }
        public final ExceptionDetails exceptionDetails() { return exceptionDetails; }
        public final ExceptionDetails getExceptionDetails() { return exceptionDetails(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            result.toJson(strBuilder.append("\"result\":"));
            if (exceptionDetails != null) exceptionDetails.toJson(strBuilder.append(",\"exceptionDetails\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public CallFunctionOnResult(
            @JsonProperty("result")RemoteObject result,
            @Nullable @JsonProperty("exceptionDetails")ExceptionDetails exceptionDetails
        ) {
            this.result = result;
            this.exceptionDetails = exceptionDetails;
        }
        public CallFunctionOnResult(ResultBase.FailedResult e) {
            super(e);
            result = null;
            exceptionDetails = null;
        }
    }
    /**Compiles expression.*/
    public CompileScriptParameter compileScript() { final CompileScriptParameter v = new CompileScriptParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of compileScript.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class CompileScriptParameter extends CommandBase {
        /**Expression to compile.*/
        private String expression;
        /**Source url to be set for the script.*/
        private String sourceURL;
        /**Specifies whether the compiled script should be persisted.*/
        private Boolean persistScript;
        /**Specifies in which execution context to perform script run. If the parameter is omitted the
evaluation will be performed in the context of the inspected page.
        <em>Optional.</em>*/
        private ExecutionContextId executionContextId;
        public final CompileScriptParameter expression(String expression) { this.expression = expression; return this; }
        public final CompileScriptParameter setExpression(String expression) { return expression(expression); }
        public final String expression() { return expression; }
        public final String getExpression() { return expression(); }
        public final CompileScriptParameter sourceURL(String sourceURL) { this.sourceURL = sourceURL; return this; }
        public final CompileScriptParameter setSourceURL(String sourceURL) { return sourceURL(sourceURL); }
        public final String sourceURL() { return sourceURL; }
        public final String getSourceURL() { return sourceURL(); }
        public final CompileScriptParameter persistScript(Boolean persistScript) { this.persistScript = persistScript; return this; }
        public final CompileScriptParameter setPersistScript(Boolean persistScript) { return persistScript(persistScript); }
        public final Boolean persistScript() { return persistScript; }
        public final Boolean getPersistScript() { return persistScript(); }
        public final CompileScriptParameter executionContextId(@Nullable ExecutionContextId executionContextId) { this.executionContextId = executionContextId; return this; }
        public final CompileScriptParameter optExecutionContextId(@Nullable ExecutionContextId executionContextId) { return executionContextId(executionContextId); }
        public final ExecutionContextId executionContextId() { return executionContextId; }
        public final ExecutionContextId getExecutionContextId() { return executionContextId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (expression == null) throw new IllegalArgumentException("Runtime.CompileScriptParameter.expression is necessary field.");
            if (sourceURL == null) throw new IllegalArgumentException("Runtime.CompileScriptParameter.sourceURL is necessary field.");
            if (persistScript == null) throw new IllegalArgumentException("Runtime.CompileScriptParameter.persistScript is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"expression\":").append('"').append(DomainBase.escapeJson(expression)).append('"');
            strBuilder.append(",\"sourceURL\":").append('"').append(DomainBase.escapeJson(sourceURL)).append('"');
            strBuilder.append(",\"persistScript\":").append(persistScript);
            if (executionContextId != null) executionContextId.toJson(strBuilder.append(",\"executionContextId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public CompileScriptParameter() {}
        public CompileScriptParameter(
            @JsonProperty("expression")String expression,
            @JsonProperty("sourceURL")String sourceURL,
            @JsonProperty("persistScript")Boolean persistScript,
            @Nullable @JsonProperty("executionContextId")ExecutionContextId executionContextId
        ) {
            this();
            this.expression = expression;
            this.sourceURL = sourceURL;
            this.persistScript = persistScript;
            this.executionContextId = executionContextId;
        }
        public CompletableFuture<CompileScriptResult> call() {
            return super.call("Runtime.compileScript", CompileScriptResult.class,
                (code, msg)->new CompileScriptResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<CompileScriptResult> call(Executor exec) {
            return super.call("Runtime.compileScript", CompileScriptResult.class,
                (code, msg)->new CompileScriptResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of compileScript.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class CompileScriptResult extends ResultBase {
        /**Id of the script.
        <em>Optional.</em>*/
        private final ScriptId scriptId;
        /**Exception details.
        <em>Optional.</em>*/
        private final ExceptionDetails exceptionDetails;
        public final ScriptId scriptId() { return scriptId; }
        public final ScriptId getScriptId() { return scriptId(); }
        public final ExceptionDetails exceptionDetails() { return exceptionDetails; }
        public final ExceptionDetails getExceptionDetails() { return exceptionDetails(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            if (scriptId != null) scriptId.toJson(strBuilder.append("\"scriptId\":"));
            if (exceptionDetails != null) exceptionDetails.toJson(strBuilder.append(",\"exceptionDetails\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public CompileScriptResult(
            @Nullable @JsonProperty("scriptId")ScriptId scriptId,
            @Nullable @JsonProperty("exceptionDetails")ExceptionDetails exceptionDetails
        ) {
            this.scriptId = scriptId;
            this.exceptionDetails = exceptionDetails;
        }
        public CompileScriptResult(ResultBase.FailedResult e) {
            super(e);
            scriptId = null;
            exceptionDetails = null;
        }
    }
    /**Disables reporting of execution contexts creation.*/
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
            return super.call("Runtime.disable", DisableResult.class,
                (code, msg)->new DisableResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<DisableResult> call(Executor exec) {
            return super.call("Runtime.disable", DisableResult.class,
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
    /**Discards collected exceptions and console API calls.*/
    public DiscardConsoleEntriesParameter discardConsoleEntries() { final DiscardConsoleEntriesParameter v = new DiscardConsoleEntriesParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of discardConsoleEntries.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class DiscardConsoleEntriesParameter extends CommandBase {
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
        public DiscardConsoleEntriesParameter() {}
        public CompletableFuture<DiscardConsoleEntriesResult> call() {
            return super.call("Runtime.discardConsoleEntries", DiscardConsoleEntriesResult.class,
                (code, msg)->new DiscardConsoleEntriesResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<DiscardConsoleEntriesResult> call(Executor exec) {
            return super.call("Runtime.discardConsoleEntries", DiscardConsoleEntriesResult.class,
                (code, msg)->new DiscardConsoleEntriesResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of discardConsoleEntries.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class DiscardConsoleEntriesResult extends ResultBase {
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
        public DiscardConsoleEntriesResult() { super(); }
        public DiscardConsoleEntriesResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Enables reporting of execution contexts creation by means of `executionContextCreated` event.
When the reporting gets enabled the event will be sent immediately for each existing execution
context.*/
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
            return super.call("Runtime.enable", EnableResult.class,
                (code, msg)->new EnableResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<EnableResult> call(Executor exec) {
            return super.call("Runtime.enable", EnableResult.class,
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
    /**Evaluates expression on global object.*/
    public EvaluateParameter evaluate() { final EvaluateParameter v = new EvaluateParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of evaluate.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class EvaluateParameter extends CommandBase {
        /**Expression to evaluate.*/
        private String expression;
        /**Symbolic group name that can be used to release multiple objects.
        <em>Optional.</em>*/
        private String objectGroup;
        /**Determines whether Command Line API should be available during the evaluation.
        <em>Optional.</em>*/
        private Boolean includeCommandLineAPI;
        /**In silent mode exceptions thrown during evaluation are not reported and do not pause
execution. Overrides `setPauseOnException` state.
        <em>Optional.</em>*/
        private Boolean silent;
        /**Specifies in which execution context to perform evaluation. If the parameter is omitted the
evaluation will be performed in the context of the inspected page.
        <em>Optional.</em>*/
        private ExecutionContextId contextId;
        /**Whether the result is expected to be a JSON object that should be sent by value.
        <em>Optional.</em>*/
        private Boolean returnByValue;
        /**Whether preview should be generated for the result.
        <em>Optional.</em>
        <p><strong>Experimental.</strong></p>*/
        private Boolean generatePreview;
        /**Whether execution should be treated as initiated by user in the UI.
        <em>Optional.</em>*/
        private Boolean userGesture;
        /**Whether execution should `await` for resulting value and return once awaited promise is
resolved.
        <em>Optional.</em>*/
        private Boolean awaitPromise;
        /**Whether to throw an exception if side effect cannot be ruled out during evaluation.
        <em>Optional.</em>
        <p><strong>Experimental.</strong></p>*/
        private Boolean throwOnSideEffect;
        /**Terminate execution after timing out (number of milliseconds).
        <em>Optional.</em>
        <p><strong>Experimental.</strong></p>*/
        private TimeDelta timeout;
        public final EvaluateParameter expression(String expression) { this.expression = expression; return this; }
        public final EvaluateParameter setExpression(String expression) { return expression(expression); }
        public final String expression() { return expression; }
        public final String getExpression() { return expression(); }
        public final EvaluateParameter objectGroup(@Nullable String objectGroup) { this.objectGroup = objectGroup; return this; }
        public final EvaluateParameter optObjectGroup(@Nullable String objectGroup) { return objectGroup(objectGroup); }
        public final String objectGroup() { return objectGroup; }
        public final String getObjectGroup() { return objectGroup(); }
        public final EvaluateParameter includeCommandLineAPI(@Nullable Boolean includeCommandLineAPI) { this.includeCommandLineAPI = includeCommandLineAPI; return this; }
        public final EvaluateParameter optIncludeCommandLineAPI(@Nullable Boolean includeCommandLineAPI) { return includeCommandLineAPI(includeCommandLineAPI); }
        public final Boolean includeCommandLineAPI() { return includeCommandLineAPI; }
        public final Boolean getIncludeCommandLineAPI() { return includeCommandLineAPI(); }
        public final EvaluateParameter silent(@Nullable Boolean silent) { this.silent = silent; return this; }
        public final EvaluateParameter optSilent(@Nullable Boolean silent) { return silent(silent); }
        public final Boolean silent() { return silent; }
        public final Boolean getSilent() { return silent(); }
        public final EvaluateParameter contextId(@Nullable ExecutionContextId contextId) { this.contextId = contextId; return this; }
        public final EvaluateParameter optContextId(@Nullable ExecutionContextId contextId) { return contextId(contextId); }
        public final ExecutionContextId contextId() { return contextId; }
        public final ExecutionContextId getContextId() { return contextId(); }
        public final EvaluateParameter returnByValue(@Nullable Boolean returnByValue) { this.returnByValue = returnByValue; return this; }
        public final EvaluateParameter optReturnByValue(@Nullable Boolean returnByValue) { return returnByValue(returnByValue); }
        public final Boolean returnByValue() { return returnByValue; }
        public final Boolean getReturnByValue() { return returnByValue(); }
        public final EvaluateParameter generatePreview(@Nullable Boolean generatePreview) { this.generatePreview = generatePreview; return this; }
        public final EvaluateParameter optGeneratePreview(@Nullable Boolean generatePreview) { return generatePreview(generatePreview); }
        public final Boolean generatePreview() { return generatePreview; }
        public final Boolean getGeneratePreview() { return generatePreview(); }
        public final EvaluateParameter userGesture(@Nullable Boolean userGesture) { this.userGesture = userGesture; return this; }
        public final EvaluateParameter optUserGesture(@Nullable Boolean userGesture) { return userGesture(userGesture); }
        public final Boolean userGesture() { return userGesture; }
        public final Boolean getUserGesture() { return userGesture(); }
        public final EvaluateParameter awaitPromise(@Nullable Boolean awaitPromise) { this.awaitPromise = awaitPromise; return this; }
        public final EvaluateParameter optAwaitPromise(@Nullable Boolean awaitPromise) { return awaitPromise(awaitPromise); }
        public final Boolean awaitPromise() { return awaitPromise; }
        public final Boolean getAwaitPromise() { return awaitPromise(); }
        public final EvaluateParameter throwOnSideEffect(@Nullable Boolean throwOnSideEffect) { this.throwOnSideEffect = throwOnSideEffect; return this; }
        public final EvaluateParameter optThrowOnSideEffect(@Nullable Boolean throwOnSideEffect) { return throwOnSideEffect(throwOnSideEffect); }
        public final Boolean throwOnSideEffect() { return throwOnSideEffect; }
        public final Boolean getThrowOnSideEffect() { return throwOnSideEffect(); }
        public final EvaluateParameter timeout(@Nullable TimeDelta timeout) { this.timeout = timeout; return this; }
        public final EvaluateParameter optTimeout(@Nullable TimeDelta timeout) { return timeout(timeout); }
        public final TimeDelta timeout() { return timeout; }
        public final TimeDelta getTimeout() { return timeout(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (expression == null) throw new IllegalArgumentException("Runtime.EvaluateParameter.expression is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"expression\":").append('"').append(DomainBase.escapeJson(expression)).append('"');
            if (objectGroup != null) strBuilder.append(",\"objectGroup\":").append('"').append(DomainBase.escapeJson(objectGroup)).append('"');
            if (includeCommandLineAPI != null) strBuilder.append(",\"includeCommandLineAPI\":").append(includeCommandLineAPI);
            if (silent != null) strBuilder.append(",\"silent\":").append(silent);
            if (contextId != null) contextId.toJson(strBuilder.append(",\"contextId\":"));
            if (returnByValue != null) strBuilder.append(",\"returnByValue\":").append(returnByValue);
            if (generatePreview != null) strBuilder.append(",\"generatePreview\":").append(generatePreview);
            if (userGesture != null) strBuilder.append(",\"userGesture\":").append(userGesture);
            if (awaitPromise != null) strBuilder.append(",\"awaitPromise\":").append(awaitPromise);
            if (throwOnSideEffect != null) strBuilder.append(",\"throwOnSideEffect\":").append(throwOnSideEffect);
            if (timeout != null) timeout.toJson(strBuilder.append(",\"timeout\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public EvaluateParameter() {}
        public EvaluateParameter(
            @JsonProperty("expression")String expression,
            @Nullable @JsonProperty("objectGroup")String objectGroup,
            @Nullable @JsonProperty("includeCommandLineAPI")Boolean includeCommandLineAPI,
            @Nullable @JsonProperty("silent")Boolean silent,
            @Nullable @JsonProperty("contextId")ExecutionContextId contextId,
            @Nullable @JsonProperty("returnByValue")Boolean returnByValue,
            @Nullable @JsonProperty("generatePreview")Boolean generatePreview,
            @Nullable @JsonProperty("userGesture")Boolean userGesture,
            @Nullable @JsonProperty("awaitPromise")Boolean awaitPromise,
            @Nullable @JsonProperty("throwOnSideEffect")Boolean throwOnSideEffect,
            @Nullable @JsonProperty("timeout")TimeDelta timeout
        ) {
            this();
            this.expression = expression;
            this.objectGroup = objectGroup;
            this.includeCommandLineAPI = includeCommandLineAPI;
            this.silent = silent;
            this.contextId = contextId;
            this.returnByValue = returnByValue;
            this.generatePreview = generatePreview;
            this.userGesture = userGesture;
            this.awaitPromise = awaitPromise;
            this.throwOnSideEffect = throwOnSideEffect;
            this.timeout = timeout;
        }
        public CompletableFuture<EvaluateResult> call() {
            return super.call("Runtime.evaluate", EvaluateResult.class,
                (code, msg)->new EvaluateResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<EvaluateResult> call(Executor exec) {
            return super.call("Runtime.evaluate", EvaluateResult.class,
                (code, msg)->new EvaluateResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of evaluate.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class EvaluateResult extends ResultBase {
        /**Evaluation result.*/
        private final RemoteObject result;
        /**Exception details.
        <em>Optional.</em>*/
        private final ExceptionDetails exceptionDetails;
        public final RemoteObject result() { return result; }
        public final RemoteObject getResult() { return result(); }
        public final ExceptionDetails exceptionDetails() { return exceptionDetails; }
        public final ExceptionDetails getExceptionDetails() { return exceptionDetails(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            result.toJson(strBuilder.append("\"result\":"));
            if (exceptionDetails != null) exceptionDetails.toJson(strBuilder.append(",\"exceptionDetails\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public EvaluateResult(
            @JsonProperty("result")RemoteObject result,
            @Nullable @JsonProperty("exceptionDetails")ExceptionDetails exceptionDetails
        ) {
            this.result = result;
            this.exceptionDetails = exceptionDetails;
        }
        public EvaluateResult(ResultBase.FailedResult e) {
            super(e);
            result = null;
            exceptionDetails = null;
        }
    }
    /**Returns the isolate id.
    <p><strong>Experimental.</strong></p>*/
    public GetIsolateIdParameter getIsolateId() { final GetIsolateIdParameter v = new GetIsolateIdParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of getIsolateId.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetIsolateIdParameter extends CommandBase {
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
        public GetIsolateIdParameter() {}
        public CompletableFuture<GetIsolateIdResult> call() {
            return super.call("Runtime.getIsolateId", GetIsolateIdResult.class,
                (code, msg)->new GetIsolateIdResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetIsolateIdResult> call(Executor exec) {
            return super.call("Runtime.getIsolateId", GetIsolateIdResult.class,
                (code, msg)->new GetIsolateIdResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of getIsolateId.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetIsolateIdResult extends ResultBase {
        /**The isolate id.*/
        private final String id;
        public final String id() { return id; }
        public final String getId() { return id(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"id\":").append('"').append(DomainBase.escapeJson(id)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public GetIsolateIdResult(
            @JsonProperty("id")String id
        ) {
            this.id = id;
        }
        public GetIsolateIdResult(ResultBase.FailedResult e) {
            super(e);
            id = null;
        }
    }
    /**Returns the JavaScript heap usage.
It is the total usage of the corresponding isolate not scoped to a particular Runtime.
    <p><strong>Experimental.</strong></p>*/
    public GetHeapUsageParameter getHeapUsage() { final GetHeapUsageParameter v = new GetHeapUsageParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of getHeapUsage.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetHeapUsageParameter extends CommandBase {
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
        public GetHeapUsageParameter() {}
        public CompletableFuture<GetHeapUsageResult> call() {
            return super.call("Runtime.getHeapUsage", GetHeapUsageResult.class,
                (code, msg)->new GetHeapUsageResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetHeapUsageResult> call(Executor exec) {
            return super.call("Runtime.getHeapUsage", GetHeapUsageResult.class,
                (code, msg)->new GetHeapUsageResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of getHeapUsage.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetHeapUsageResult extends ResultBase {
        /**Used heap size in bytes.*/
        private final Double usedSize;
        /**Allocated heap size in bytes.*/
        private final Double totalSize;
        public final Double usedSize() { return usedSize; }
        public final Double getUsedSize() { return usedSize(); }
        public final Double totalSize() { return totalSize; }
        public final Double getTotalSize() { return totalSize(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"usedSize\":").append(usedSize);
            strBuilder.append(",\"totalSize\":").append(totalSize);
            strBuilder.append('}');
            return strBuilder;
        }
        public GetHeapUsageResult(
            @JsonProperty("usedSize")Double usedSize,
            @JsonProperty("totalSize")Double totalSize
        ) {
            this.usedSize = usedSize;
            this.totalSize = totalSize;
        }
        public GetHeapUsageResult(ResultBase.FailedResult e) {
            super(e);
            usedSize = null;
            totalSize = null;
        }
    }
    /**Returns properties of a given object. Object group of the result is inherited from the target
object.*/
    public GetPropertiesParameter getProperties() { final GetPropertiesParameter v = new GetPropertiesParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of getProperties.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetPropertiesParameter extends CommandBase {
        /**Identifier of the object to return properties for.*/
        private RemoteObjectId objectId;
        /**If true, returns properties belonging only to the element itself, not to its prototype
chain.
        <em>Optional.</em>*/
        private Boolean ownProperties;
        /**If true, returns accessor properties (with getter/setter) only; internal properties are not
returned either.
        <em>Optional.</em>
        <p><strong>Experimental.</strong></p>*/
        private Boolean accessorPropertiesOnly;
        /**Whether preview should be generated for the results.
        <em>Optional.</em>
        <p><strong>Experimental.</strong></p>*/
        private Boolean generatePreview;
        public final GetPropertiesParameter objectId(RemoteObjectId objectId) { this.objectId = objectId; return this; }
        public final GetPropertiesParameter setObjectId(RemoteObjectId objectId) { return objectId(objectId); }
        public final RemoteObjectId objectId() { return objectId; }
        public final RemoteObjectId getObjectId() { return objectId(); }
        public final GetPropertiesParameter ownProperties(@Nullable Boolean ownProperties) { this.ownProperties = ownProperties; return this; }
        public final GetPropertiesParameter optOwnProperties(@Nullable Boolean ownProperties) { return ownProperties(ownProperties); }
        public final Boolean ownProperties() { return ownProperties; }
        public final Boolean getOwnProperties() { return ownProperties(); }
        public final GetPropertiesParameter accessorPropertiesOnly(@Nullable Boolean accessorPropertiesOnly) { this.accessorPropertiesOnly = accessorPropertiesOnly; return this; }
        public final GetPropertiesParameter optAccessorPropertiesOnly(@Nullable Boolean accessorPropertiesOnly) { return accessorPropertiesOnly(accessorPropertiesOnly); }
        public final Boolean accessorPropertiesOnly() { return accessorPropertiesOnly; }
        public final Boolean getAccessorPropertiesOnly() { return accessorPropertiesOnly(); }
        public final GetPropertiesParameter generatePreview(@Nullable Boolean generatePreview) { this.generatePreview = generatePreview; return this; }
        public final GetPropertiesParameter optGeneratePreview(@Nullable Boolean generatePreview) { return generatePreview(generatePreview); }
        public final Boolean generatePreview() { return generatePreview; }
        public final Boolean getGeneratePreview() { return generatePreview(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (objectId == null) throw new IllegalArgumentException("Runtime.GetPropertiesParameter.objectId is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            objectId.toJson(strBuilder.append("\"objectId\":"));
            if (ownProperties != null) strBuilder.append(",\"ownProperties\":").append(ownProperties);
            if (accessorPropertiesOnly != null) strBuilder.append(",\"accessorPropertiesOnly\":").append(accessorPropertiesOnly);
            if (generatePreview != null) strBuilder.append(",\"generatePreview\":").append(generatePreview);
            strBuilder.append('}');
            return strBuilder;
        }
        public GetPropertiesParameter() {}
        public GetPropertiesParameter(
            @JsonProperty("objectId")RemoteObjectId objectId,
            @Nullable @JsonProperty("ownProperties")Boolean ownProperties,
            @Nullable @JsonProperty("accessorPropertiesOnly")Boolean accessorPropertiesOnly,
            @Nullable @JsonProperty("generatePreview")Boolean generatePreview
        ) {
            this();
            this.objectId = objectId;
            this.ownProperties = ownProperties;
            this.accessorPropertiesOnly = accessorPropertiesOnly;
            this.generatePreview = generatePreview;
        }
        public CompletableFuture<GetPropertiesResult> call() {
            return super.call("Runtime.getProperties", GetPropertiesResult.class,
                (code, msg)->new GetPropertiesResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetPropertiesResult> call(Executor exec) {
            return super.call("Runtime.getProperties", GetPropertiesResult.class,
                (code, msg)->new GetPropertiesResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of getProperties.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetPropertiesResult extends ResultBase {
        /**Object properties.*/
        private final List<PropertyDescriptor> result;
        /**Internal object properties (only of the element itself).
        <em>Optional.</em>*/
        private final List<InternalPropertyDescriptor> internalProperties;
        /**Exception details.
        <em>Optional.</em>*/
        private final ExceptionDetails exceptionDetails;
        public final List<PropertyDescriptor> result() { return result; }
        public final List<PropertyDescriptor> getResult() { return result(); }
        public final List<InternalPropertyDescriptor> internalProperties() { return internalProperties; }
        public final List<InternalPropertyDescriptor> getInternalProperties() { return internalProperties(); }
        public final ExceptionDetails exceptionDetails() { return exceptionDetails; }
        public final ExceptionDetails getExceptionDetails() { return exceptionDetails(); }
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
            if (internalProperties != null) {
                strBuilder.append(",\"internalProperties\":[");
                internalProperties.get(0).toJson(strBuilder);
                for (int i = 1; i < internalProperties.size(); ++i)
                    internalProperties.get(i).toJson(strBuilder.append(','));
                strBuilder.append(']');
            }
            if (exceptionDetails != null) exceptionDetails.toJson(strBuilder.append(",\"exceptionDetails\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public GetPropertiesResult(
            @JsonProperty("result")List<PropertyDescriptor> result,
            @Nullable @JsonProperty("internalProperties")List<InternalPropertyDescriptor> internalProperties,
            @Nullable @JsonProperty("exceptionDetails")ExceptionDetails exceptionDetails
        ) {
            this.result = result;
            this.internalProperties = internalProperties;
            this.exceptionDetails = exceptionDetails;
        }
        public GetPropertiesResult(ResultBase.FailedResult e) {
            super(e);
            result = null;
            internalProperties = null;
            exceptionDetails = null;
        }
    }
    /**Returns all let, const and class variables from global scope.*/
    public GlobalLexicalScopeNamesParameter globalLexicalScopeNames() { final GlobalLexicalScopeNamesParameter v = new GlobalLexicalScopeNamesParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of globalLexicalScopeNames.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GlobalLexicalScopeNamesParameter extends CommandBase {
        /**Specifies in which execution context to lookup global scope variables.
        <em>Optional.</em>*/
        private ExecutionContextId executionContextId;
        public final GlobalLexicalScopeNamesParameter executionContextId(@Nullable ExecutionContextId executionContextId) { this.executionContextId = executionContextId; return this; }
        public final GlobalLexicalScopeNamesParameter optExecutionContextId(@Nullable ExecutionContextId executionContextId) { return executionContextId(executionContextId); }
        public final ExecutionContextId executionContextId() { return executionContextId; }
        public final ExecutionContextId getExecutionContextId() { return executionContextId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            if (executionContextId != null) executionContextId.toJson(strBuilder.append("\"executionContextId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public GlobalLexicalScopeNamesParameter() {}
        public GlobalLexicalScopeNamesParameter(
            @Nullable @JsonProperty("executionContextId")ExecutionContextId executionContextId
        ) {
            this();
            this.executionContextId = executionContextId;
        }
        public CompletableFuture<GlobalLexicalScopeNamesResult> call() {
            return super.call("Runtime.globalLexicalScopeNames", GlobalLexicalScopeNamesResult.class,
                (code, msg)->new GlobalLexicalScopeNamesResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GlobalLexicalScopeNamesResult> call(Executor exec) {
            return super.call("Runtime.globalLexicalScopeNames", GlobalLexicalScopeNamesResult.class,
                (code, msg)->new GlobalLexicalScopeNamesResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of globalLexicalScopeNames.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GlobalLexicalScopeNamesResult extends ResultBase {
        /**&lt;No document in protocol.&gt;*/
        private final List<String> names;
        public final List<String> names() { return names; }
        public final List<String> getNames() { return names(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
                        strBuilder.append("\"names\":[");
            strBuilder.append('"').append(DomainBase.escapeJson(names.get(0))).append('"');
            for (int i = 1; i < names.size(); ++i)
                strBuilder.append(",\"").append(DomainBase.escapeJson(names.get(i))).append('"');
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public GlobalLexicalScopeNamesResult(
            @JsonProperty("names")List<String> names
        ) {
            this.names = names;
        }
        public GlobalLexicalScopeNamesResult(ResultBase.FailedResult e) {
            super(e);
            names = null;
        }
    }
    /**&lt;No document in protocol.&gt;*/
    public QueryObjectsParameter queryObjects() { final QueryObjectsParameter v = new QueryObjectsParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of queryObjects.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class QueryObjectsParameter extends CommandBase {
        /**Identifier of the prototype to return objects for.*/
        private RemoteObjectId prototypeObjectId;
        /**Symbolic group name that can be used to release the results.
        <em>Optional.</em>*/
        private String objectGroup;
        public final QueryObjectsParameter prototypeObjectId(RemoteObjectId prototypeObjectId) { this.prototypeObjectId = prototypeObjectId; return this; }
        public final QueryObjectsParameter setPrototypeObjectId(RemoteObjectId prototypeObjectId) { return prototypeObjectId(prototypeObjectId); }
        public final RemoteObjectId prototypeObjectId() { return prototypeObjectId; }
        public final RemoteObjectId getPrototypeObjectId() { return prototypeObjectId(); }
        public final QueryObjectsParameter objectGroup(@Nullable String objectGroup) { this.objectGroup = objectGroup; return this; }
        public final QueryObjectsParameter optObjectGroup(@Nullable String objectGroup) { return objectGroup(objectGroup); }
        public final String objectGroup() { return objectGroup; }
        public final String getObjectGroup() { return objectGroup(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (prototypeObjectId == null) throw new IllegalArgumentException("Runtime.QueryObjectsParameter.prototypeObjectId is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            prototypeObjectId.toJson(strBuilder.append("\"prototypeObjectId\":"));
            if (objectGroup != null) strBuilder.append(",\"objectGroup\":").append('"').append(DomainBase.escapeJson(objectGroup)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public QueryObjectsParameter() {}
        public QueryObjectsParameter(
            @JsonProperty("prototypeObjectId")RemoteObjectId prototypeObjectId,
            @Nullable @JsonProperty("objectGroup")String objectGroup
        ) {
            this();
            this.prototypeObjectId = prototypeObjectId;
            this.objectGroup = objectGroup;
        }
        public CompletableFuture<QueryObjectsResult> call() {
            return super.call("Runtime.queryObjects", QueryObjectsResult.class,
                (code, msg)->new QueryObjectsResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<QueryObjectsResult> call(Executor exec) {
            return super.call("Runtime.queryObjects", QueryObjectsResult.class,
                (code, msg)->new QueryObjectsResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of queryObjects.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class QueryObjectsResult extends ResultBase {
        /**Array with objects.*/
        private final RemoteObject objects;
        public final RemoteObject objects() { return objects; }
        public final RemoteObject getObjects() { return objects(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            objects.toJson(strBuilder.append("\"objects\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public QueryObjectsResult(
            @JsonProperty("objects")RemoteObject objects
        ) {
            this.objects = objects;
        }
        public QueryObjectsResult(ResultBase.FailedResult e) {
            super(e);
            objects = null;
        }
    }
    /**Releases remote object with given id.*/
    public ReleaseObjectParameter releaseObject() { final ReleaseObjectParameter v = new ReleaseObjectParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of releaseObject.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ReleaseObjectParameter extends CommandBase {
        /**Identifier of the object to release.*/
        private RemoteObjectId objectId;
        public final ReleaseObjectParameter objectId(RemoteObjectId objectId) { this.objectId = objectId; return this; }
        public final ReleaseObjectParameter setObjectId(RemoteObjectId objectId) { return objectId(objectId); }
        public final RemoteObjectId objectId() { return objectId; }
        public final RemoteObjectId getObjectId() { return objectId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (objectId == null) throw new IllegalArgumentException("Runtime.ReleaseObjectParameter.objectId is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            objectId.toJson(strBuilder.append("\"objectId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public ReleaseObjectParameter() {}
        public ReleaseObjectParameter(
            @JsonProperty("objectId")RemoteObjectId objectId
        ) {
            this();
            this.objectId = objectId;
        }
        public CompletableFuture<ReleaseObjectResult> call() {
            return super.call("Runtime.releaseObject", ReleaseObjectResult.class,
                (code, msg)->new ReleaseObjectResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<ReleaseObjectResult> call(Executor exec) {
            return super.call("Runtime.releaseObject", ReleaseObjectResult.class,
                (code, msg)->new ReleaseObjectResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of releaseObject.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ReleaseObjectResult extends ResultBase {
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
        public ReleaseObjectResult() { super(); }
        public ReleaseObjectResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Releases all remote objects that belong to a given group.*/
    public ReleaseObjectGroupParameter releaseObjectGroup() { final ReleaseObjectGroupParameter v = new ReleaseObjectGroupParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of releaseObjectGroup.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ReleaseObjectGroupParameter extends CommandBase {
        /**Symbolic object group name.*/
        private String objectGroup;
        public final ReleaseObjectGroupParameter objectGroup(String objectGroup) { this.objectGroup = objectGroup; return this; }
        public final ReleaseObjectGroupParameter setObjectGroup(String objectGroup) { return objectGroup(objectGroup); }
        public final String objectGroup() { return objectGroup; }
        public final String getObjectGroup() { return objectGroup(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (objectGroup == null) throw new IllegalArgumentException("Runtime.ReleaseObjectGroupParameter.objectGroup is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"objectGroup\":").append('"').append(DomainBase.escapeJson(objectGroup)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public ReleaseObjectGroupParameter() {}
        public ReleaseObjectGroupParameter(
            @JsonProperty("objectGroup")String objectGroup
        ) {
            this();
            this.objectGroup = objectGroup;
        }
        public CompletableFuture<ReleaseObjectGroupResult> call() {
            return super.call("Runtime.releaseObjectGroup", ReleaseObjectGroupResult.class,
                (code, msg)->new ReleaseObjectGroupResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<ReleaseObjectGroupResult> call(Executor exec) {
            return super.call("Runtime.releaseObjectGroup", ReleaseObjectGroupResult.class,
                (code, msg)->new ReleaseObjectGroupResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of releaseObjectGroup.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ReleaseObjectGroupResult extends ResultBase {
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
        public ReleaseObjectGroupResult() { super(); }
        public ReleaseObjectGroupResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Tells inspected instance to run if it was waiting for debugger to attach.*/
    public RunIfWaitingForDebuggerParameter runIfWaitingForDebugger() { final RunIfWaitingForDebuggerParameter v = new RunIfWaitingForDebuggerParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of runIfWaitingForDebugger.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class RunIfWaitingForDebuggerParameter extends CommandBase {
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
        public RunIfWaitingForDebuggerParameter() {}
        public CompletableFuture<RunIfWaitingForDebuggerResult> call() {
            return super.call("Runtime.runIfWaitingForDebugger", RunIfWaitingForDebuggerResult.class,
                (code, msg)->new RunIfWaitingForDebuggerResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<RunIfWaitingForDebuggerResult> call(Executor exec) {
            return super.call("Runtime.runIfWaitingForDebugger", RunIfWaitingForDebuggerResult.class,
                (code, msg)->new RunIfWaitingForDebuggerResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of runIfWaitingForDebugger.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class RunIfWaitingForDebuggerResult extends ResultBase {
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
        public RunIfWaitingForDebuggerResult() { super(); }
        public RunIfWaitingForDebuggerResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Runs script with given id in a given context.*/
    public RunScriptParameter runScript() { final RunScriptParameter v = new RunScriptParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of runScript.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class RunScriptParameter extends CommandBase {
        /**Id of the script to run.*/
        private ScriptId scriptId;
        /**Specifies in which execution context to perform script run. If the parameter is omitted the
evaluation will be performed in the context of the inspected page.
        <em>Optional.</em>*/
        private ExecutionContextId executionContextId;
        /**Symbolic group name that can be used to release multiple objects.
        <em>Optional.</em>*/
        private String objectGroup;
        /**In silent mode exceptions thrown during evaluation are not reported and do not pause
execution. Overrides `setPauseOnException` state.
        <em>Optional.</em>*/
        private Boolean silent;
        /**Determines whether Command Line API should be available during the evaluation.
        <em>Optional.</em>*/
        private Boolean includeCommandLineAPI;
        /**Whether the result is expected to be a JSON object which should be sent by value.
        <em>Optional.</em>*/
        private Boolean returnByValue;
        /**Whether preview should be generated for the result.
        <em>Optional.</em>*/
        private Boolean generatePreview;
        /**Whether execution should `await` for resulting value and return once awaited promise is
resolved.
        <em>Optional.</em>*/
        private Boolean awaitPromise;
        public final RunScriptParameter scriptId(ScriptId scriptId) { this.scriptId = scriptId; return this; }
        public final RunScriptParameter setScriptId(ScriptId scriptId) { return scriptId(scriptId); }
        public final ScriptId scriptId() { return scriptId; }
        public final ScriptId getScriptId() { return scriptId(); }
        public final RunScriptParameter executionContextId(@Nullable ExecutionContextId executionContextId) { this.executionContextId = executionContextId; return this; }
        public final RunScriptParameter optExecutionContextId(@Nullable ExecutionContextId executionContextId) { return executionContextId(executionContextId); }
        public final ExecutionContextId executionContextId() { return executionContextId; }
        public final ExecutionContextId getExecutionContextId() { return executionContextId(); }
        public final RunScriptParameter objectGroup(@Nullable String objectGroup) { this.objectGroup = objectGroup; return this; }
        public final RunScriptParameter optObjectGroup(@Nullable String objectGroup) { return objectGroup(objectGroup); }
        public final String objectGroup() { return objectGroup; }
        public final String getObjectGroup() { return objectGroup(); }
        public final RunScriptParameter silent(@Nullable Boolean silent) { this.silent = silent; return this; }
        public final RunScriptParameter optSilent(@Nullable Boolean silent) { return silent(silent); }
        public final Boolean silent() { return silent; }
        public final Boolean getSilent() { return silent(); }
        public final RunScriptParameter includeCommandLineAPI(@Nullable Boolean includeCommandLineAPI) { this.includeCommandLineAPI = includeCommandLineAPI; return this; }
        public final RunScriptParameter optIncludeCommandLineAPI(@Nullable Boolean includeCommandLineAPI) { return includeCommandLineAPI(includeCommandLineAPI); }
        public final Boolean includeCommandLineAPI() { return includeCommandLineAPI; }
        public final Boolean getIncludeCommandLineAPI() { return includeCommandLineAPI(); }
        public final RunScriptParameter returnByValue(@Nullable Boolean returnByValue) { this.returnByValue = returnByValue; return this; }
        public final RunScriptParameter optReturnByValue(@Nullable Boolean returnByValue) { return returnByValue(returnByValue); }
        public final Boolean returnByValue() { return returnByValue; }
        public final Boolean getReturnByValue() { return returnByValue(); }
        public final RunScriptParameter generatePreview(@Nullable Boolean generatePreview) { this.generatePreview = generatePreview; return this; }
        public final RunScriptParameter optGeneratePreview(@Nullable Boolean generatePreview) { return generatePreview(generatePreview); }
        public final Boolean generatePreview() { return generatePreview; }
        public final Boolean getGeneratePreview() { return generatePreview(); }
        public final RunScriptParameter awaitPromise(@Nullable Boolean awaitPromise) { this.awaitPromise = awaitPromise; return this; }
        public final RunScriptParameter optAwaitPromise(@Nullable Boolean awaitPromise) { return awaitPromise(awaitPromise); }
        public final Boolean awaitPromise() { return awaitPromise; }
        public final Boolean getAwaitPromise() { return awaitPromise(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (scriptId == null) throw new IllegalArgumentException("Runtime.RunScriptParameter.scriptId is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            scriptId.toJson(strBuilder.append("\"scriptId\":"));
            if (executionContextId != null) executionContextId.toJson(strBuilder.append(",\"executionContextId\":"));
            if (objectGroup != null) strBuilder.append(",\"objectGroup\":").append('"').append(DomainBase.escapeJson(objectGroup)).append('"');
            if (silent != null) strBuilder.append(",\"silent\":").append(silent);
            if (includeCommandLineAPI != null) strBuilder.append(",\"includeCommandLineAPI\":").append(includeCommandLineAPI);
            if (returnByValue != null) strBuilder.append(",\"returnByValue\":").append(returnByValue);
            if (generatePreview != null) strBuilder.append(",\"generatePreview\":").append(generatePreview);
            if (awaitPromise != null) strBuilder.append(",\"awaitPromise\":").append(awaitPromise);
            strBuilder.append('}');
            return strBuilder;
        }
        public RunScriptParameter() {}
        public RunScriptParameter(
            @JsonProperty("scriptId")ScriptId scriptId,
            @Nullable @JsonProperty("executionContextId")ExecutionContextId executionContextId,
            @Nullable @JsonProperty("objectGroup")String objectGroup,
            @Nullable @JsonProperty("silent")Boolean silent,
            @Nullable @JsonProperty("includeCommandLineAPI")Boolean includeCommandLineAPI,
            @Nullable @JsonProperty("returnByValue")Boolean returnByValue,
            @Nullable @JsonProperty("generatePreview")Boolean generatePreview,
            @Nullable @JsonProperty("awaitPromise")Boolean awaitPromise
        ) {
            this();
            this.scriptId = scriptId;
            this.executionContextId = executionContextId;
            this.objectGroup = objectGroup;
            this.silent = silent;
            this.includeCommandLineAPI = includeCommandLineAPI;
            this.returnByValue = returnByValue;
            this.generatePreview = generatePreview;
            this.awaitPromise = awaitPromise;
        }
        public CompletableFuture<RunScriptResult> call() {
            return super.call("Runtime.runScript", RunScriptResult.class,
                (code, msg)->new RunScriptResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<RunScriptResult> call(Executor exec) {
            return super.call("Runtime.runScript", RunScriptResult.class,
                (code, msg)->new RunScriptResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of runScript.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class RunScriptResult extends ResultBase {
        /**Run result.*/
        private final RemoteObject result;
        /**Exception details.
        <em>Optional.</em>*/
        private final ExceptionDetails exceptionDetails;
        public final RemoteObject result() { return result; }
        public final RemoteObject getResult() { return result(); }
        public final ExceptionDetails exceptionDetails() { return exceptionDetails; }
        public final ExceptionDetails getExceptionDetails() { return exceptionDetails(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            result.toJson(strBuilder.append("\"result\":"));
            if (exceptionDetails != null) exceptionDetails.toJson(strBuilder.append(",\"exceptionDetails\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public RunScriptResult(
            @JsonProperty("result")RemoteObject result,
            @Nullable @JsonProperty("exceptionDetails")ExceptionDetails exceptionDetails
        ) {
            this.result = result;
            this.exceptionDetails = exceptionDetails;
        }
        public RunScriptResult(ResultBase.FailedResult e) {
            super(e);
            result = null;
            exceptionDetails = null;
        }
    }
    /**&lt;No document in protocol.&gt;
    <p><strong>Experimental.</strong></p>*/
    public SetCustomObjectFormatterEnabledParameter setCustomObjectFormatterEnabled() { final SetCustomObjectFormatterEnabledParameter v = new SetCustomObjectFormatterEnabledParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setCustomObjectFormatterEnabled.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetCustomObjectFormatterEnabledParameter extends CommandBase {
        /**&lt;No document in protocol.&gt;*/
        private Boolean enabled;
        public final SetCustomObjectFormatterEnabledParameter enabled(Boolean enabled) { this.enabled = enabled; return this; }
        public final SetCustomObjectFormatterEnabledParameter setEnabled(Boolean enabled) { return enabled(enabled); }
        public final Boolean enabled() { return enabled; }
        public final Boolean getEnabled() { return enabled(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (enabled == null) throw new IllegalArgumentException("Runtime.SetCustomObjectFormatterEnabledParameter.enabled is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"enabled\":").append(enabled);
            strBuilder.append('}');
            return strBuilder;
        }
        public SetCustomObjectFormatterEnabledParameter() {}
        public SetCustomObjectFormatterEnabledParameter(
            @JsonProperty("enabled")Boolean enabled
        ) {
            this();
            this.enabled = enabled;
        }
        public CompletableFuture<SetCustomObjectFormatterEnabledResult> call() {
            return super.call("Runtime.setCustomObjectFormatterEnabled", SetCustomObjectFormatterEnabledResult.class,
                (code, msg)->new SetCustomObjectFormatterEnabledResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetCustomObjectFormatterEnabledResult> call(Executor exec) {
            return super.call("Runtime.setCustomObjectFormatterEnabled", SetCustomObjectFormatterEnabledResult.class,
                (code, msg)->new SetCustomObjectFormatterEnabledResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setCustomObjectFormatterEnabled.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetCustomObjectFormatterEnabledResult extends ResultBase {
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
        public SetCustomObjectFormatterEnabledResult() { super(); }
        public SetCustomObjectFormatterEnabledResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Terminate current or next JavaScript execution.
Will cancel the termination when the outer-most script execution ends.
    <p><strong>Experimental.</strong></p>*/
    public TerminateExecutionParameter terminateExecution() { final TerminateExecutionParameter v = new TerminateExecutionParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of terminateExecution.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class TerminateExecutionParameter extends CommandBase {
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
        public TerminateExecutionParameter() {}
        public CompletableFuture<TerminateExecutionResult> call() {
            return super.call("Runtime.terminateExecution", TerminateExecutionResult.class,
                (code, msg)->new TerminateExecutionResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<TerminateExecutionResult> call(Executor exec) {
            return super.call("Runtime.terminateExecution", TerminateExecutionResult.class,
                (code, msg)->new TerminateExecutionResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of terminateExecution.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class TerminateExecutionResult extends ResultBase {
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
        public TerminateExecutionResult() { super(); }
        public TerminateExecutionResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Event parameter of Runtime.consoleAPICalled.
     @see #onConsoleAPICalled*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ConsoleAPICalledEventParameter implements CommonDomainType {
        /**Type of the call.*/
        @ParametersAreNonnullByDefault public enum Type implements CommonDomainType {
            Log("log"),
            Debug("debug"),
            Info("info"),
            Error("error"),
            Warning("warning"),
            Dir("dir"),
            Dirxml("dirxml"),
            Table("table"),
            Trace("trace"),
            Clear("clear"),
            StartGroup("startGroup"),
            StartGroupCollapsed("startGroupCollapsed"),
            EndGroup("endGroup"),
            Assert("assert"),
            Profile("profile"),
            ProfileEnd("profileEnd"),
            Count("count"),
            TimeEnd("timeEnd");

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
        private final Type type;
        /**Call arguments.*/
        private final List<RemoteObject> args;
        /**Identifier of the context where the call was made.*/
        private final ExecutionContextId executionContextId;
        /**Call timestamp.*/
        private final Timestamp timestamp;
        /**Stack trace captured when the call was made.
        <em>Optional.</em>*/
        private final StackTrace stackTrace;
        /**Console context descriptor for calls on non-default console context (not console.*):
'anonymous#unique-logger-id' for call on unnamed context, 'name#unique-logger-id' for call
on named context.
        <em>Optional.</em>
        <p><strong>Experimental.</strong></p>*/
        private final String context;
        public final Type type() { return type; }
        public final Type getType() { return type(); }
        public final List<RemoteObject> args() { return args; }
        public final List<RemoteObject> getArgs() { return args(); }
        public final ExecutionContextId executionContextId() { return executionContextId; }
        public final ExecutionContextId getExecutionContextId() { return executionContextId(); }
        public final Timestamp timestamp() { return timestamp; }
        public final Timestamp getTimestamp() { return timestamp(); }
        public final StackTrace stackTrace() { return stackTrace; }
        public final StackTrace getStackTrace() { return stackTrace(); }
        public final String context() { return context; }
        public final String getContext() { return context(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"type\":").append(type);
                        strBuilder.append(",\"args\":[");
            args.get(0).toJson(strBuilder);
            for (int i = 1; i < args.size(); ++i)
                args.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            executionContextId.toJson(strBuilder.append(",\"executionContextId\":"));
            timestamp.toJson(strBuilder.append(",\"timestamp\":"));
            if (stackTrace != null) stackTrace.toJson(strBuilder.append(",\"stackTrace\":"));
            if (context != null) strBuilder.append(",\"context\":").append('"').append(DomainBase.escapeJson(context)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        ConsoleAPICalledEventParameter(
            @JsonProperty("type")Type type,
            @JsonProperty("args")List<RemoteObject> args,
            @JsonProperty("executionContextId")ExecutionContextId executionContextId,
            @JsonProperty("timestamp")Timestamp timestamp,
            @Nullable @JsonProperty("stackTrace")StackTrace stackTrace,
            @Nullable @JsonProperty("context")String context
        ) {
            this.type = type;
            this.args = args;
            this.executionContextId = executionContextId;
            this.timestamp = timestamp;
            this.stackTrace = stackTrace;
            this.context = context;
        }
    }
    /**Issued when console API was called.
     @see ConsoleAPICalledEventParameter*/
    public void onConsoleAPICalled(Consumer<ConsoleAPICalledEventParameter> callback) {
        registerEventCallback("Runtime.consoleAPICalled", node -> {
            ConsoleAPICalledEventParameter param;
            try { param = EventCenter.deserializeJson(node, ConsoleAPICalledEventParameter.class); }
            catch (IOException e) { _evt.getLog().error(e); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of Runtime.exceptionRevoked.
     @see #onExceptionRevoked*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ExceptionRevokedEventParameter implements CommonDomainType {
        /**Reason describing why exception was revoked.*/
        private final String reason;
        /**The id of revoked exception, as reported in `exceptionThrown`.*/
        private final Integer exceptionId;
        public final String reason() { return reason; }
        public final String getReason() { return reason(); }
        public final Integer exceptionId() { return exceptionId; }
        public final Integer getExceptionId() { return exceptionId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"reason\":").append('"').append(DomainBase.escapeJson(reason)).append('"');
            strBuilder.append(",\"exceptionId\":").append(exceptionId);
            strBuilder.append('}');
            return strBuilder;
        }
        ExceptionRevokedEventParameter(
            @JsonProperty("reason")String reason,
            @JsonProperty("exceptionId")Integer exceptionId
        ) {
            this.reason = reason;
            this.exceptionId = exceptionId;
        }
    }
    /**Issued when unhandled exception was revoked.
     @see ExceptionRevokedEventParameter*/
    public void onExceptionRevoked(Consumer<ExceptionRevokedEventParameter> callback) {
        registerEventCallback("Runtime.exceptionRevoked", node -> {
            ExceptionRevokedEventParameter param;
            try { param = EventCenter.deserializeJson(node, ExceptionRevokedEventParameter.class); }
            catch (IOException e) { _evt.getLog().error(e); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of Runtime.exceptionThrown.
     @see #onExceptionThrown*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ExceptionThrownEventParameter implements CommonDomainType {
        /**Timestamp of the exception.*/
        private final Timestamp timestamp;
        /**&lt;No document in protocol.&gt;*/
        private final ExceptionDetails exceptionDetails;
        public final Timestamp timestamp() { return timestamp; }
        public final Timestamp getTimestamp() { return timestamp(); }
        public final ExceptionDetails exceptionDetails() { return exceptionDetails; }
        public final ExceptionDetails getExceptionDetails() { return exceptionDetails(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            timestamp.toJson(strBuilder.append("\"timestamp\":"));
            exceptionDetails.toJson(strBuilder.append(",\"exceptionDetails\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        ExceptionThrownEventParameter(
            @JsonProperty("timestamp")Timestamp timestamp,
            @JsonProperty("exceptionDetails")ExceptionDetails exceptionDetails
        ) {
            this.timestamp = timestamp;
            this.exceptionDetails = exceptionDetails;
        }
    }
    /**Issued when exception was thrown and unhandled.
     @see ExceptionThrownEventParameter*/
    public void onExceptionThrown(Consumer<ExceptionThrownEventParameter> callback) {
        registerEventCallback("Runtime.exceptionThrown", node -> {
            ExceptionThrownEventParameter param;
            try { param = EventCenter.deserializeJson(node, ExceptionThrownEventParameter.class); }
            catch (IOException e) { _evt.getLog().error(e); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of Runtime.executionContextCreated.
     @see #onExecutionContextCreated*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ExecutionContextCreatedEventParameter implements CommonDomainType {
        /**A newly created execution context.*/
        private final ExecutionContextDescription context;
        public final ExecutionContextDescription context() { return context; }
        public final ExecutionContextDescription getContext() { return context(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            context.toJson(strBuilder.append("\"context\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        ExecutionContextCreatedEventParameter(
            @JsonProperty("context")ExecutionContextDescription context
        ) {
            this.context = context;
        }
    }
    /**Issued when new execution context is created.
     @see ExecutionContextCreatedEventParameter*/
    public void onExecutionContextCreated(Consumer<ExecutionContextCreatedEventParameter> callback) {
        registerEventCallback("Runtime.executionContextCreated", node -> {
            ExecutionContextCreatedEventParameter param;
            try { param = EventCenter.deserializeJson(node, ExecutionContextCreatedEventParameter.class); }
            catch (IOException e) { _evt.getLog().error(e); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of Runtime.executionContextDestroyed.
     @see #onExecutionContextDestroyed*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ExecutionContextDestroyedEventParameter implements CommonDomainType {
        /**Id of the destroyed context*/
        private final ExecutionContextId executionContextId;
        public final ExecutionContextId executionContextId() { return executionContextId; }
        public final ExecutionContextId getExecutionContextId() { return executionContextId(); }
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
        ExecutionContextDestroyedEventParameter(
            @JsonProperty("executionContextId")ExecutionContextId executionContextId
        ) {
            this.executionContextId = executionContextId;
        }
    }
    /**Issued when execution context is destroyed.
     @see ExecutionContextDestroyedEventParameter*/
    public void onExecutionContextDestroyed(Consumer<ExecutionContextDestroyedEventParameter> callback) {
        registerEventCallback("Runtime.executionContextDestroyed", node -> {
            ExecutionContextDestroyedEventParameter param;
            try { param = EventCenter.deserializeJson(node, ExecutionContextDestroyedEventParameter.class); }
            catch (IOException e) { _evt.getLog().error(e); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of Runtime.executionContextsCleared.
     @see #onExecutionContextsCleared*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ExecutionContextsClearedEventParameter implements CommonDomainType {
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
        public ExecutionContextsClearedEventParameter() {}
    }
    /**Issued when all executionContexts were cleared in browser
     @see ExecutionContextsClearedEventParameter*/
    public void onExecutionContextsCleared(Consumer<ExecutionContextsClearedEventParameter> callback) {
        registerEventCallback("Runtime.executionContextsCleared", node -> {
            ExecutionContextsClearedEventParameter param;
            try { param = EventCenter.deserializeJson(node, ExecutionContextsClearedEventParameter.class); }
            catch (IOException e) { _evt.getLog().error(e); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of Runtime.inspectRequested.
     @see #onInspectRequested*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class InspectRequestedEventParameter implements CommonDomainType {
        /**&lt;No document in protocol.&gt;*/
        private final RemoteObject object;
        /**&lt;No document in protocol.&gt;*/
        private final Object hints;
        public final RemoteObject object() { return object; }
        public final RemoteObject getObject() { return object(); }
        public final Object hints() { return hints; }
        public final Object getHints() { return hints(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            object.toJson(strBuilder.append("\"object\":"));
            strBuilder.append(",\"hints\":").append(hints);
            strBuilder.append('}');
            return strBuilder;
        }
        InspectRequestedEventParameter(
            @JsonProperty("object")RemoteObject object,
            @JsonProperty("hints")Object hints
        ) {
            this.object = object;
            this.hints = hints;
        }
    }
    /**Issued when object should be inspected (for example, as a result of inspect() command line API
call).
     @see InspectRequestedEventParameter*/
    public void onInspectRequested(Consumer<InspectRequestedEventParameter> callback) {
        registerEventCallback("Runtime.inspectRequested", node -> {
            InspectRequestedEventParameter param;
            try { param = EventCenter.deserializeJson(node, InspectRequestedEventParameter.class); }
            catch (IOException e) { _evt.getLog().error(e); return; }
            callback.accept(param);
        });
    }
}

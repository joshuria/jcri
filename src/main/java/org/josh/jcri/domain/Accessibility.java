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
 @see DOM
 @author Joshua*/
@ParametersAreNonnullByDefault public class Accessibility extends DomainBase {
    public Accessibility(EventCenter evt, WebSocket ws) { super(evt, ws); }

    /**Unique accessibility node identifier.*/
    @ParametersAreNonnullByDefault public static class AXNodeId implements CommonDomainType {
        private String _value;
        public AXNodeId() {}
        public AXNodeId(String value) { _value = value; }
        public final AXNodeId value(String value) { _value = value; return this; }
        public final String value() { return _value; }
        public final AXNodeId setValue(String value) { return value(value); }
        public final String getValue() { return value(); }
        @Override public String toString() { return _value; }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (_value == null) throw new IllegalArgumentException("Accessibility.AXNodeId.value is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            return strBuilder.append(_value);
        }
    }

    /**Enum of possible property types.*/
    @ParametersAreNonnullByDefault public enum AXValueType implements CommonDomainType {
        Boolean("boolean"),
        Tristate("tristate"),
        BooleanOrUndefined("booleanOrUndefined"),
        Idref("idref"),
        IdrefList("idrefList"),
        Integer("integer"),
        Node("node"),
        NodeList("nodeList"),
        Double("number"),
        String("string"),
        ComputedString("computedString"),
        Token("token"),
        TokenList("tokenList"),
        DomRelation("domRelation"),
        Role("role"),
        InternalRole("internalRole"),
        ValueUndefined("valueUndefined");

        private final String _value;
        private static final Map<String, AXValueType> _Lookup = Collections.unmodifiableMap(new HashMap<String, AXValueType>() {{
            for (AXValueType v: values())    put(v.toString(), v);
        }});
        /**Convert string representation to type.
         @throws IllegalArgumentException if given value cannot convert to enum type. */
        @JsonCreator public static AXValueType of(String value) {
            AXValueType v = _Lookup.get(value.toLowerCase());
            return v != null ? v : Enum.valueOf(AXValueType.class, value);
        }
        AXValueType(String value) { _value = value; }
        /**Check if parameter fields of method are all valid. */
        @Override public void check() throws IllegalArgumentException { /* Need not check */ }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) { return strBuilder.append(toString()); }
        @Override public String toString() { return _value; }
    }

    /**Enum of possible property sources.*/
    @ParametersAreNonnullByDefault public enum AXValueSourceType implements CommonDomainType {
        Attribute("attribute"),
        Implicit("implicit"),
        Style("style"),
        Contents("contents"),
        Placeholder("placeholder"),
        RelatedElement("relatedElement");

        private final String _value;
        private static final Map<String, AXValueSourceType> _Lookup = Collections.unmodifiableMap(new HashMap<String, AXValueSourceType>() {{
            for (AXValueSourceType v: values())    put(v.toString(), v);
        }});
        /**Convert string representation to type.
         @throws IllegalArgumentException if given value cannot convert to enum type. */
        @JsonCreator public static AXValueSourceType of(String value) {
            AXValueSourceType v = _Lookup.get(value.toLowerCase());
            return v != null ? v : Enum.valueOf(AXValueSourceType.class, value);
        }
        AXValueSourceType(String value) { _value = value; }
        /**Check if parameter fields of method are all valid. */
        @Override public void check() throws IllegalArgumentException { /* Need not check */ }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) { return strBuilder.append(toString()); }
        @Override public String toString() { return _value; }
    }

    /**Enum of possible native property sources (as a subtype of a particular AXValueSourceType).*/
    @ParametersAreNonnullByDefault public enum AXValueNativeSourceType implements CommonDomainType {
        Figcaption("figcaption"),
        Label("label"),
        Labelfor("labelfor"),
        Labelwrapped("labelwrapped"),
        Legend("legend"),
        Tablecaption("tablecaption"),
        Title("title"),
        Other("other");

        private final String _value;
        private static final Map<String, AXValueNativeSourceType> _Lookup = Collections.unmodifiableMap(new HashMap<String, AXValueNativeSourceType>() {{
            for (AXValueNativeSourceType v: values())    put(v.toString(), v);
        }});
        /**Convert string representation to type.
         @throws IllegalArgumentException if given value cannot convert to enum type. */
        @JsonCreator public static AXValueNativeSourceType of(String value) {
            AXValueNativeSourceType v = _Lookup.get(value.toLowerCase());
            return v != null ? v : Enum.valueOf(AXValueNativeSourceType.class, value);
        }
        AXValueNativeSourceType(String value) { _value = value; }
        /**Check if parameter fields of method are all valid. */
        @Override public void check() throws IllegalArgumentException { /* Need not check */ }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) { return strBuilder.append(toString()); }
        @Override public String toString() { return _value; }
    }

    /**A single source for a computed AX property.*/
    @ParametersAreNonnullByDefault public static class AXValueSource implements CommonDomainType {
        /**What type of source this is.*/
        private AXValueSourceType type;
        /**The value of this property source.
        <em>Optional.</em>*/
        private AXValue value;
        /**The name of the relevant attribute, if any.
        <em>Optional.</em>*/
        private String attribute;
        /**The value of the relevant attribute, if any.
        <em>Optional.</em>*/
        private AXValue attributeValue;
        /**Whether this source is superseded by a higher priority source.
        <em>Optional.</em>*/
        private Boolean superseded;
        /**The native markup source for this value, e.g. a <label> element.
        <em>Optional.</em>*/
        private AXValueNativeSourceType nativeSource;
        /**The value, such as a node or node list, of the native source.
        <em>Optional.</em>*/
        private AXValue nativeSourceValue;
        /**Whether the value for this property is invalid.
        <em>Optional.</em>*/
        private Boolean invalid;
        /**Reason for the value being invalid, if it is.
        <em>Optional.</em>*/
        private String invalidReason;
        public final AXValueSource type(AXValueSourceType type) { this.type = type; return this; }
        public final AXValueSource setType(AXValueSourceType type) { return type(type); }
        public final AXValueSourceType type() { return type; }
        public final AXValueSourceType getType() { return type(); }
        public final AXValueSource value(@Nullable AXValue value) { this.value = value; return this; }
        public final AXValueSource optValue(@Nullable AXValue value) { return value(value); }
        public final AXValue value() { return value; }
        public final AXValue getValue() { return value(); }
        public final AXValueSource attribute(@Nullable String attribute) { this.attribute = attribute; return this; }
        public final AXValueSource optAttribute(@Nullable String attribute) { return attribute(attribute); }
        public final String attribute() { return attribute; }
        public final String getAttribute() { return attribute(); }
        public final AXValueSource attributeValue(@Nullable AXValue attributeValue) { this.attributeValue = attributeValue; return this; }
        public final AXValueSource optAttributeValue(@Nullable AXValue attributeValue) { return attributeValue(attributeValue); }
        public final AXValue attributeValue() { return attributeValue; }
        public final AXValue getAttributeValue() { return attributeValue(); }
        public final AXValueSource superseded(@Nullable Boolean superseded) { this.superseded = superseded; return this; }
        public final AXValueSource optSuperseded(@Nullable Boolean superseded) { return superseded(superseded); }
        public final Boolean superseded() { return superseded; }
        public final Boolean getSuperseded() { return superseded(); }
        public final AXValueSource nativeSource(@Nullable AXValueNativeSourceType nativeSource) { this.nativeSource = nativeSource; return this; }
        public final AXValueSource optNativeSource(@Nullable AXValueNativeSourceType nativeSource) { return nativeSource(nativeSource); }
        public final AXValueNativeSourceType nativeSource() { return nativeSource; }
        public final AXValueNativeSourceType getNativeSource() { return nativeSource(); }
        public final AXValueSource nativeSourceValue(@Nullable AXValue nativeSourceValue) { this.nativeSourceValue = nativeSourceValue; return this; }
        public final AXValueSource optNativeSourceValue(@Nullable AXValue nativeSourceValue) { return nativeSourceValue(nativeSourceValue); }
        public final AXValue nativeSourceValue() { return nativeSourceValue; }
        public final AXValue getNativeSourceValue() { return nativeSourceValue(); }
        public final AXValueSource invalid(@Nullable Boolean invalid) { this.invalid = invalid; return this; }
        public final AXValueSource optInvalid(@Nullable Boolean invalid) { return invalid(invalid); }
        public final Boolean invalid() { return invalid; }
        public final Boolean getInvalid() { return invalid(); }
        public final AXValueSource invalidReason(@Nullable String invalidReason) { this.invalidReason = invalidReason; return this; }
        public final AXValueSource optInvalidReason(@Nullable String invalidReason) { return invalidReason(invalidReason); }
        public final String invalidReason() { return invalidReason; }
        public final String getInvalidReason() { return invalidReason(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (type == null) throw new IllegalArgumentException("Accessibility.AXValueSource.type is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            type.toJson(strBuilder.append("\"type\":"));
            if (value != null) value.toJson(strBuilder.append(",\"value\":"));
            if (attribute != null) strBuilder.append(",\"attribute\":").append('"').append(attribute).append('"');
            if (attributeValue != null) attributeValue.toJson(strBuilder.append(",\"attributeValue\":"));
            if (superseded != null) strBuilder.append(",\"superseded\":").append(superseded);
            if (nativeSource != null) nativeSource.toJson(strBuilder.append(",\"nativeSource\":"));
            if (nativeSourceValue != null) nativeSourceValue.toJson(strBuilder.append(",\"nativeSourceValue\":"));
            if (invalid != null) strBuilder.append(",\"invalid\":").append(invalid);
            if (invalidReason != null) strBuilder.append(",\"invalidReason\":").append('"').append(invalidReason).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public AXValueSource() {}
        public AXValueSource(
            @JsonProperty("type")AXValueSourceType type,
            @Nullable @JsonProperty("value")AXValue value,
            @Nullable @JsonProperty("attribute")String attribute,
            @Nullable @JsonProperty("attributeValue")AXValue attributeValue,
            @Nullable @JsonProperty("superseded")Boolean superseded,
            @Nullable @JsonProperty("nativeSource")AXValueNativeSourceType nativeSource,
            @Nullable @JsonProperty("nativeSourceValue")AXValue nativeSourceValue,
            @Nullable @JsonProperty("invalid")Boolean invalid,
            @Nullable @JsonProperty("invalidReason")String invalidReason
        ) {
            this.type = type;
            this.value = value;
            this.attribute = attribute;
            this.attributeValue = attributeValue;
            this.superseded = superseded;
            this.nativeSource = nativeSource;
            this.nativeSourceValue = nativeSourceValue;
            this.invalid = invalid;
            this.invalidReason = invalidReason;
        }
    }

    /**&lt;No document in protocol.&gt;*/
    @ParametersAreNonnullByDefault public static class AXRelatedNode implements CommonDomainType {
        /**The BackendNodeId of the related DOM node.*/
        private DOM.BackendNodeId backendDOMNodeId;
        /**The IDRef value provided, if any.
        <em>Optional.</em>*/
        private String idref;
        /**The text alternative of this node in the current context.
        <em>Optional.</em>*/
        private String text;
        public final AXRelatedNode backendDOMNodeId(DOM.BackendNodeId backendDOMNodeId) { this.backendDOMNodeId = backendDOMNodeId; return this; }
        public final AXRelatedNode setBackendDOMNodeId(DOM.BackendNodeId backendDOMNodeId) { return backendDOMNodeId(backendDOMNodeId); }
        public final DOM.BackendNodeId backendDOMNodeId() { return backendDOMNodeId; }
        public final DOM.BackendNodeId getBackendDOMNodeId() { return backendDOMNodeId(); }
        public final AXRelatedNode idref(@Nullable String idref) { this.idref = idref; return this; }
        public final AXRelatedNode optIdref(@Nullable String idref) { return idref(idref); }
        public final String idref() { return idref; }
        public final String getIdref() { return idref(); }
        public final AXRelatedNode text(@Nullable String text) { this.text = text; return this; }
        public final AXRelatedNode optText(@Nullable String text) { return text(text); }
        public final String text() { return text; }
        public final String getText() { return text(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (backendDOMNodeId == null) throw new IllegalArgumentException("Accessibility.AXRelatedNode.backendDOMNodeId is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            backendDOMNodeId.toJson(strBuilder.append("\"backendDOMNodeId\":"));
            if (idref != null) strBuilder.append(",\"idref\":").append('"').append(idref).append('"');
            if (text != null) strBuilder.append(",\"text\":").append('"').append(text).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public AXRelatedNode() {}
        public AXRelatedNode(
            @JsonProperty("backendDOMNodeId")DOM.BackendNodeId backendDOMNodeId,
            @Nullable @JsonProperty("idref")String idref,
            @Nullable @JsonProperty("text")String text
        ) {
            this.backendDOMNodeId = backendDOMNodeId;
            this.idref = idref;
            this.text = text;
        }
    }

    /**&lt;No document in protocol.&gt;*/
    @ParametersAreNonnullByDefault public static class AXProperty implements CommonDomainType {
        /**The name of this property.*/
        private AXPropertyName name;
        /**The value of this property.*/
        private AXValue value;
        public final AXProperty name(AXPropertyName name) { this.name = name; return this; }
        public final AXProperty setName(AXPropertyName name) { return name(name); }
        public final AXPropertyName name() { return name; }
        public final AXPropertyName getName() { return name(); }
        public final AXProperty value(AXValue value) { this.value = value; return this; }
        public final AXProperty setValue(AXValue value) { return value(value); }
        public final AXValue value() { return value; }
        public final AXValue getValue() { return value(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (name == null) throw new IllegalArgumentException("Accessibility.AXProperty.name is necessary field.");
            if (value == null) throw new IllegalArgumentException("Accessibility.AXProperty.value is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            name.toJson(strBuilder.append("\"name\":"));
            value.toJson(strBuilder.append(",\"value\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public AXProperty() {}
        public AXProperty(
            @JsonProperty("name")AXPropertyName name,
            @JsonProperty("value")AXValue value
        ) {
            this.name = name;
            this.value = value;
        }
    }

    /**A single computed AX property.*/
    @ParametersAreNonnullByDefault public static class AXValue implements CommonDomainType {
        /**The type of this value.*/
        private AXValueType type;
        /**The computed value of this property.
        <em>Optional.</em>*/
        private Object value;
        /**One or more related nodes, if applicable.
        <em>Optional.</em>*/
        private List<AXRelatedNode> relatedNodes;
        /**The sources which contributed to the computation of this property.
        <em>Optional.</em>*/
        private List<AXValueSource> sources;
        public final AXValue type(AXValueType type) { this.type = type; return this; }
        public final AXValue setType(AXValueType type) { return type(type); }
        public final AXValueType type() { return type; }
        public final AXValueType getType() { return type(); }
        public final AXValue value(@Nullable Object value) { this.value = value; return this; }
        public final AXValue optValue(@Nullable Object value) { return value(value); }
        public final Object value() { return value; }
        public final Object getValue() { return value(); }
        public final AXValue relatedNodes(@Nullable List<AXRelatedNode> relatedNodes) { this.relatedNodes = relatedNodes; return this; }
        public final AXValue optRelatedNodes(@Nullable List<AXRelatedNode> relatedNodes) { return relatedNodes(relatedNodes); }
        public final List<AXRelatedNode> relatedNodes() { return relatedNodes; }
        public final List<AXRelatedNode> getRelatedNodes() { return relatedNodes(); }
        public final AXValue sources(@Nullable List<AXValueSource> sources) { this.sources = sources; return this; }
        public final AXValue optSources(@Nullable List<AXValueSource> sources) { return sources(sources); }
        public final List<AXValueSource> sources() { return sources; }
        public final List<AXValueSource> getSources() { return sources(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (type == null) throw new IllegalArgumentException("Accessibility.AXValue.type is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            type.toJson(strBuilder.append("\"type\":"));
            if (value != null) strBuilder.append(",\"value\":").append(value);
            if (relatedNodes != null) {
                strBuilder.append(",\"relatedNodes\":[");
                relatedNodes.get(0).toJson(strBuilder);
                for (int i = 1; i < relatedNodes.size(); ++i)
                    relatedNodes.get(i).toJson(strBuilder.append(','));
                strBuilder.append(']');
            }
            if (sources != null) {
                strBuilder.append(",\"sources\":[");
                sources.get(0).toJson(strBuilder);
                for (int i = 1; i < sources.size(); ++i)
                    sources.get(i).toJson(strBuilder.append(','));
                strBuilder.append(']');
            }
            strBuilder.append('}');
            return strBuilder;
        }
        public AXValue() {}
        public AXValue(
            @JsonProperty("type")AXValueType type,
            @Nullable @JsonProperty("value")Object value,
            @Nullable @JsonProperty("relatedNodes")List<AXRelatedNode> relatedNodes,
            @Nullable @JsonProperty("sources")List<AXValueSource> sources
        ) {
            this.type = type;
            this.value = value;
            this.relatedNodes = relatedNodes;
            this.sources = sources;
        }
    }

    /**Values of AXProperty name: from 'busy' to 'roledescription' - states which apply to every AX
node, from 'live' to 'root' - attributes which apply to nodes in live regions, from
'autocomplete' to 'valuetext' - attributes which apply to widgets, from 'checked' to 'selected'
- states which apply to widgets, from 'activedescendant' to 'owns' - relationships between
elements other than parent/child/sibling.*/
    @ParametersAreNonnullByDefault public enum AXPropertyName implements CommonDomainType {
        Busy("busy"),
        Disabled("disabled"),
        Hidden("hidden"),
        HiddenRoot("hiddenRoot"),
        Invalid("invalid"),
        Keyshortcuts("keyshortcuts"),
        Roledescription("roledescription"),
        Live("live"),
        Atomic("atomic"),
        Relevant("relevant"),
        Root("root"),
        Autocomplete("autocomplete"),
        Haspopup("haspopup"),
        Level("level"),
        Multiselectable("multiselectable"),
        Orientation("orientation"),
        Multiline("multiline"),
        Readonly("readonly"),
        Required("required"),
        Valuemin("valuemin"),
        Valuemax("valuemax"),
        Valuetext("valuetext"),
        Checked("checked"),
        Expanded("expanded"),
        Modal("modal"),
        Pressed("pressed"),
        Selected("selected"),
        Activedescendant("activedescendant"),
        Controls("controls"),
        Describedby("describedby"),
        Details("details"),
        Errormessage("errormessage"),
        Flowto("flowto"),
        Labelledby("labelledby"),
        Owns("owns");

        private final String _value;
        private static final Map<String, AXPropertyName> _Lookup = Collections.unmodifiableMap(new HashMap<String, AXPropertyName>() {{
            for (AXPropertyName v: values())    put(v.toString(), v);
        }});
        /**Convert string representation to type.
         @throws IllegalArgumentException if given value cannot convert to enum type. */
        @JsonCreator public static AXPropertyName of(String value) {
            AXPropertyName v = _Lookup.get(value.toLowerCase());
            return v != null ? v : Enum.valueOf(AXPropertyName.class, value);
        }
        AXPropertyName(String value) { _value = value; }
        /**Check if parameter fields of method are all valid. */
        @Override public void check() throws IllegalArgumentException { /* Need not check */ }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) { return strBuilder.append(toString()); }
        @Override public String toString() { return _value; }
    }

    /**A node in the accessibility tree.*/
    @ParametersAreNonnullByDefault public static class AXNode implements CommonDomainType {
        /**Unique identifier for this node.*/
        private AXNodeId nodeId;
        /**Whether this node is ignored for accessibility*/
        private Boolean ignored;
        /**Collection of reasons why this node is hidden.
        <em>Optional.</em>*/
        private List<AXProperty> ignoredReasons;
        /**This `Node`'s role, whether explicit or implicit.
        <em>Optional.</em>*/
        private AXValue role;
        /**The accessible name for this `Node`.
        <em>Optional.</em>*/
        private AXValue name;
        /**The accessible description for this `Node`.
        <em>Optional.</em>*/
        private AXValue description;
        /**The value for this `Node`.
        <em>Optional.</em>*/
        private AXValue value;
        /**All other properties
        <em>Optional.</em>*/
        private List<AXProperty> properties;
        /**IDs for each of this node's child nodes.
        <em>Optional.</em>*/
        private List<AXNodeId> childIds;
        /**The backend ID for the associated DOM node, if any.
        <em>Optional.</em>*/
        private DOM.BackendNodeId backendDOMNodeId;
        public final AXNode nodeId(AXNodeId nodeId) { this.nodeId = nodeId; return this; }
        public final AXNode setNodeId(AXNodeId nodeId) { return nodeId(nodeId); }
        public final AXNodeId nodeId() { return nodeId; }
        public final AXNodeId getNodeId() { return nodeId(); }
        public final AXNode ignored(Boolean ignored) { this.ignored = ignored; return this; }
        public final AXNode setIgnored(Boolean ignored) { return ignored(ignored); }
        public final Boolean ignored() { return ignored; }
        public final Boolean getIgnored() { return ignored(); }
        public final AXNode ignoredReasons(@Nullable List<AXProperty> ignoredReasons) { this.ignoredReasons = ignoredReasons; return this; }
        public final AXNode optIgnoredReasons(@Nullable List<AXProperty> ignoredReasons) { return ignoredReasons(ignoredReasons); }
        public final List<AXProperty> ignoredReasons() { return ignoredReasons; }
        public final List<AXProperty> getIgnoredReasons() { return ignoredReasons(); }
        public final AXNode role(@Nullable AXValue role) { this.role = role; return this; }
        public final AXNode optRole(@Nullable AXValue role) { return role(role); }
        public final AXValue role() { return role; }
        public final AXValue getRole() { return role(); }
        public final AXNode name(@Nullable AXValue name) { this.name = name; return this; }
        public final AXNode optName(@Nullable AXValue name) { return name(name); }
        public final AXValue name() { return name; }
        public final AXValue getName() { return name(); }
        public final AXNode description(@Nullable AXValue description) { this.description = description; return this; }
        public final AXNode optDescription(@Nullable AXValue description) { return description(description); }
        public final AXValue description() { return description; }
        public final AXValue getDescription() { return description(); }
        public final AXNode value(@Nullable AXValue value) { this.value = value; return this; }
        public final AXNode optValue(@Nullable AXValue value) { return value(value); }
        public final AXValue value() { return value; }
        public final AXValue getValue() { return value(); }
        public final AXNode properties(@Nullable List<AXProperty> properties) { this.properties = properties; return this; }
        public final AXNode optProperties(@Nullable List<AXProperty> properties) { return properties(properties); }
        public final List<AXProperty> properties() { return properties; }
        public final List<AXProperty> getProperties() { return properties(); }
        public final AXNode childIds(@Nullable List<AXNodeId> childIds) { this.childIds = childIds; return this; }
        public final AXNode optChildIds(@Nullable List<AXNodeId> childIds) { return childIds(childIds); }
        public final List<AXNodeId> childIds() { return childIds; }
        public final List<AXNodeId> getChildIds() { return childIds(); }
        public final AXNode backendDOMNodeId(@Nullable DOM.BackendNodeId backendDOMNodeId) { this.backendDOMNodeId = backendDOMNodeId; return this; }
        public final AXNode optBackendDOMNodeId(@Nullable DOM.BackendNodeId backendDOMNodeId) { return backendDOMNodeId(backendDOMNodeId); }
        public final DOM.BackendNodeId backendDOMNodeId() { return backendDOMNodeId; }
        public final DOM.BackendNodeId getBackendDOMNodeId() { return backendDOMNodeId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (nodeId == null) throw new IllegalArgumentException("Accessibility.AXNode.nodeId is necessary field.");
            if (ignored == null) throw new IllegalArgumentException("Accessibility.AXNode.ignored is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            nodeId.toJson(strBuilder.append("\"nodeId\":"));
            strBuilder.append(",\"ignored\":").append(ignored);
            if (ignoredReasons != null) {
                strBuilder.append(",\"ignoredReasons\":[");
                ignoredReasons.get(0).toJson(strBuilder);
                for (int i = 1; i < ignoredReasons.size(); ++i)
                    ignoredReasons.get(i).toJson(strBuilder.append(','));
                strBuilder.append(']');
            }
            if (role != null) role.toJson(strBuilder.append(",\"role\":"));
            if (name != null) name.toJson(strBuilder.append(",\"name\":"));
            if (description != null) description.toJson(strBuilder.append(",\"description\":"));
            if (value != null) value.toJson(strBuilder.append(",\"value\":"));
            if (properties != null) {
                strBuilder.append(",\"properties\":[");
                properties.get(0).toJson(strBuilder);
                for (int i = 1; i < properties.size(); ++i)
                    properties.get(i).toJson(strBuilder.append(','));
                strBuilder.append(']');
            }
            if (childIds != null) {
                strBuilder.append(",\"childIds\":[");
                childIds.get(0).toJson(strBuilder);
                for (int i = 1; i < childIds.size(); ++i)
                    childIds.get(i).toJson(strBuilder.append(','));
                strBuilder.append(']');
            }
            if (backendDOMNodeId != null) backendDOMNodeId.toJson(strBuilder.append(",\"backendDOMNodeId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public AXNode() {}
        public AXNode(
            @JsonProperty("nodeId")AXNodeId nodeId,
            @JsonProperty("ignored")Boolean ignored,
            @Nullable @JsonProperty("ignoredReasons")List<AXProperty> ignoredReasons,
            @Nullable @JsonProperty("role")AXValue role,
            @Nullable @JsonProperty("name")AXValue name,
            @Nullable @JsonProperty("description")AXValue description,
            @Nullable @JsonProperty("value")AXValue value,
            @Nullable @JsonProperty("properties")List<AXProperty> properties,
            @Nullable @JsonProperty("childIds")List<AXNodeId> childIds,
            @Nullable @JsonProperty("backendDOMNodeId")DOM.BackendNodeId backendDOMNodeId
        ) {
            this.nodeId = nodeId;
            this.ignored = ignored;
            this.ignoredReasons = ignoredReasons;
            this.role = role;
            this.name = name;
            this.description = description;
            this.value = value;
            this.properties = properties;
            this.childIds = childIds;
            this.backendDOMNodeId = backendDOMNodeId;
        }
    }
    /**Fetches the accessibility node and partial accessibility tree for this DOM node, if it exists.
    <p><strong>Experimental.</strong></p>*/
    public GetPartialAXTreeParameter getPartialAXTree() { final GetPartialAXTreeParameter v = new GetPartialAXTreeParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of getPartialAXTree.
    <p><strong>Experimental.</strong></p>*/
    @ParametersAreNonnullByDefault public static class GetPartialAXTreeParameter extends CommandBase {
        /**ID of node to get the partial accessibility tree for.*/
        private DOM.NodeId nodeId;
        /**Whether to fetch this nodes ancestors, siblings and children. Defaults to true.
        <em>Optional.</em>*/
        private Boolean fetchRelatives;
        public final GetPartialAXTreeParameter nodeId(DOM.NodeId nodeId) { this.nodeId = nodeId; return this; }
        public final GetPartialAXTreeParameter setNodeId(DOM.NodeId nodeId) { return nodeId(nodeId); }
        public final DOM.NodeId nodeId() { return nodeId; }
        public final DOM.NodeId getNodeId() { return nodeId(); }
        public final GetPartialAXTreeParameter fetchRelatives(@Nullable Boolean fetchRelatives) { this.fetchRelatives = fetchRelatives; return this; }
        public final GetPartialAXTreeParameter optFetchRelatives(@Nullable Boolean fetchRelatives) { return fetchRelatives(fetchRelatives); }
        public final Boolean fetchRelatives() { return fetchRelatives; }
        public final Boolean getFetchRelatives() { return fetchRelatives(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (nodeId == null) throw new IllegalArgumentException("Accessibility.GetPartialAXTreeParameter.nodeId is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            nodeId.toJson(strBuilder.append("\"nodeId\":"));
            if (fetchRelatives != null) strBuilder.append(",\"fetchRelatives\":").append(fetchRelatives);
            strBuilder.append('}');
            return strBuilder;
        }
        public GetPartialAXTreeParameter() {}
        public GetPartialAXTreeParameter(
            @JsonProperty("nodeId")DOM.NodeId nodeId,
            @Nullable @JsonProperty("fetchRelatives")Boolean fetchRelatives
        ) {
            this();
            this.nodeId = nodeId;
            this.fetchRelatives = fetchRelatives;
        }
        public CompletableFuture<GetPartialAXTreeResult> call() {
            return super.call("Accessibility.getPartialAXTree", GetPartialAXTreeResult.class, msg->new GetPartialAXTreeResult(ResultBase.ofError(msg)));
        }
        public CompletableFuture<GetPartialAXTreeResult> call(Executor exec) {
            return super.call("Accessibility.getPartialAXTree", GetPartialAXTreeResult.class, msg->new GetPartialAXTreeResult(ResultBase.ofError(msg)), exec);
        }
    }
    /**Return result class of getPartialAXTree.
    <p><strong>Experimental.</strong></p>*/
    @ParametersAreNonnullByDefault public static class GetPartialAXTreeResult extends ResultBase {
        /**The `Accessibility.AXNode` for this DOM node, if it exists, plus its ancestors, siblings and
children, if requested.*/
        private final List<AXNode> nodes;
        public final List<AXNode> nodes() { return nodes; }
        public final List<AXNode> getNodes() { return nodes(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
                        strBuilder.append("\"nodes\":[");
            nodes.get(0).toJson(strBuilder);
            for (int i = 1; i < nodes.size(); ++i)
                nodes.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public GetPartialAXTreeResult(
            @JsonProperty("nodes")List<AXNode> nodes
        ) {
            this.nodes = nodes;
        }
        public GetPartialAXTreeResult(ResultBase.FailedResult e) {
            super(e);
            nodes = null;
        }
    }
}

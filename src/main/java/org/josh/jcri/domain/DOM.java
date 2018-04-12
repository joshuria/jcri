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

/**This domain exposes DOM read/write operations. Each DOM Node is represented with its mirror object
that has an `id`. This `id` can be used to get additional information on the Node, resolve it into
the JavaScript object wrapper, etc. It is important that client receives DOM events only for the
nodes that are known to the client. Backend keeps track of the nodes that were sent to the client
and never sends the same node twice. It is client's responsibility to collect information about
the nodes that were sent to the client.<p>Note that `iframe` owner elements will return
corresponding document elements as their child nodes.</p>
<p>From: browser_protocol.json</p>
<p>Protocol version: 1.3</p>
 @see Runtime
 @author Joshua*/
@ParametersAreNonnullByDefault public class DOM extends DomainBase {
    public DOM(EventCenter evt, WebSocket ws) { super(evt, ws); }

    /**Unique DOM node identifier.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class NodeId implements CommonDomainType {
        private Integer _value;
        public NodeId() {}
        public NodeId(Integer value) { _value = value; }
        public final NodeId value(Integer value) { _value = value; return this; }
        public final Integer value() { return _value; }
        public final NodeId setValue(Integer value) { return value(value); }
        public final Integer getValue() { return value(); }
        @Override public String toString() { return String.valueOf(_value); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (_value == null) throw new IllegalArgumentException("DOM.NodeId.value is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            return strBuilder.append(_value);
        }
    }

    /**Unique DOM node identifier used to reference a node that may not have been pushed to the
front-end.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class BackendNodeId implements CommonDomainType {
        private Integer _value;
        public BackendNodeId() {}
        public BackendNodeId(Integer value) { _value = value; }
        public final BackendNodeId value(Integer value) { _value = value; return this; }
        public final Integer value() { return _value; }
        public final BackendNodeId setValue(Integer value) { return value(value); }
        public final Integer getValue() { return value(); }
        @Override public String toString() { return String.valueOf(_value); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (_value == null) throw new IllegalArgumentException("DOM.BackendNodeId.value is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            return strBuilder.append(_value);
        }
    }

    /**Backend node with a friendly name.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class BackendNode implements CommonDomainType {
        /**`Node`'s nodeType.*/
        private Integer nodeType;
        /**`Node`'s nodeName.*/
        private String nodeName;
        /**&lt;No document in protocol.&gt;*/
        private BackendNodeId backendNodeId;
        public final BackendNode nodeType(Integer nodeType) { this.nodeType = nodeType; return this; }
        public final BackendNode setNodeType(Integer nodeType) { return nodeType(nodeType); }
        public final Integer nodeType() { return nodeType; }
        public final Integer getNodeType() { return nodeType(); }
        public final BackendNode nodeName(String nodeName) { this.nodeName = nodeName; return this; }
        public final BackendNode setNodeName(String nodeName) { return nodeName(nodeName); }
        public final String nodeName() { return nodeName; }
        public final String getNodeName() { return nodeName(); }
        public final BackendNode backendNodeId(BackendNodeId backendNodeId) { this.backendNodeId = backendNodeId; return this; }
        public final BackendNode setBackendNodeId(BackendNodeId backendNodeId) { return backendNodeId(backendNodeId); }
        public final BackendNodeId backendNodeId() { return backendNodeId; }
        public final BackendNodeId getBackendNodeId() { return backendNodeId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (nodeType == null) throw new IllegalArgumentException("DOM.BackendNode.nodeType is necessary field.");
            if (nodeName == null) throw new IllegalArgumentException("DOM.BackendNode.nodeName is necessary field.");
            if (backendNodeId == null) throw new IllegalArgumentException("DOM.BackendNode.backendNodeId is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"nodeType\":").append(nodeType);
            strBuilder.append(",\"nodeName\":").append('"').append(DomainBase.escapeQuote(nodeName)).append('"');
            backendNodeId.toJson(strBuilder.append(",\"backendNodeId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public BackendNode() {}
        public BackendNode(
            @JsonProperty("nodeType")Integer nodeType,
            @JsonProperty("nodeName")String nodeName,
            @JsonProperty("backendNodeId")BackendNodeId backendNodeId
        ) {
            this.nodeType = nodeType;
            this.nodeName = nodeName;
            this.backendNodeId = backendNodeId;
        }
    }

    /**Pseudo element type.*/
    @ParametersAreNonnullByDefault public enum PseudoType implements CommonDomainType {
        First_line("first-line"),
        First_letter("first-letter"),
        Before("before"),
        After("after"),
        Backdrop("backdrop"),
        Selection("selection"),
        First_line_inherited("first-line-inherited"),
        Scrollbar("scrollbar"),
        Scrollbar_thumb("scrollbar-thumb"),
        Scrollbar_button("scrollbar-button"),
        Scrollbar_track("scrollbar-track"),
        Scrollbar_track_piece("scrollbar-track-piece"),
        Scrollbar_corner("scrollbar-corner"),
        Resizer("resizer"),
        Input_list_button("input-list-button");

        private final String _value;
        private static final Map<String, PseudoType> _Lookup;
        static {
            Map<String, PseudoType> m = new HashMap<>();
            for(PseudoType v: values()) m.put(v.toString(), v);
            _Lookup = Collections.unmodifiableMap(m);
        }
        /**Convert string representation to type.
         @throws IllegalArgumentException if given value cannot convert to enum type. */
        @JsonCreator public static PseudoType of(String value) {
            PseudoType v = _Lookup.get(value.toLowerCase());
            return v != null ? v : Enum.valueOf(PseudoType.class, value);
        }
        PseudoType(String value) { _value = value; }
        /**Check if parameter fields of method are all valid. */
        @Override public void check() throws IllegalArgumentException { /* Need not check */ }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) { return strBuilder.append(toString()); }
        @Override public String toString() { return _value; }
    }

    /**Shadow root type.*/
    @ParametersAreNonnullByDefault public enum ShadowRootType implements CommonDomainType {
        User_agent("user-agent"),
        Open("open"),
        Closed("closed");

        private final String _value;
        private static final Map<String, ShadowRootType> _Lookup;
        static {
            Map<String, ShadowRootType> m = new HashMap<>();
            for(ShadowRootType v: values()) m.put(v.toString(), v);
            _Lookup = Collections.unmodifiableMap(m);
        }
        /**Convert string representation to type.
         @throws IllegalArgumentException if given value cannot convert to enum type. */
        @JsonCreator public static ShadowRootType of(String value) {
            ShadowRootType v = _Lookup.get(value.toLowerCase());
            return v != null ? v : Enum.valueOf(ShadowRootType.class, value);
        }
        ShadowRootType(String value) { _value = value; }
        /**Check if parameter fields of method are all valid. */
        @Override public void check() throws IllegalArgumentException { /* Need not check */ }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) { return strBuilder.append(toString()); }
        @Override public String toString() { return _value; }
    }

    /**DOM interaction is implemented in terms of mirror objects that represent the actual DOM nodes.
DOMNode is a base node mirror type.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class Node implements CommonDomainType {
        /**Node identifier that is passed into the rest of the DOM messages as the `nodeId`. Backend
will only push node with given `id` once. It is aware of all requested nodes and will only
fire DOM events for nodes known to the client.*/
        private NodeId nodeId;
        /**The id of the parent node if any.
        <em>Optional.</em>*/
        private NodeId parentId;
        /**The BackendNodeId for this node.*/
        private BackendNodeId backendNodeId;
        /**`Node`'s nodeType.*/
        private Integer nodeType;
        /**`Node`'s nodeName.*/
        private String nodeName;
        /**`Node`'s localName.*/
        private String localName;
        /**`Node`'s nodeValue.*/
        private String nodeValue;
        /**Child count for `Container` nodes.
        <em>Optional.</em>*/
        private Integer childNodeCount;
        /**Child nodes of this node when requested with children.
        <em>Optional.</em>*/
        private List<Node> children;
        /**Attributes of the `Element` node in the form of flat array `[name1, value1, name2, value2]`.
        <em>Optional.</em>*/
        private List<String> attributes;
        /**Document URL that `Document` or `FrameOwner` node points to.
        <em>Optional.</em>*/
        private String documentURL;
        /**Base URL that `Document` or `FrameOwner` node uses for URL completion.
        <em>Optional.</em>*/
        private String baseURL;
        /**`DocumentType`'s publicId.
        <em>Optional.</em>*/
        private String publicId;
        /**`DocumentType`'s systemId.
        <em>Optional.</em>*/
        private String systemId;
        /**`DocumentType`'s internalSubset.
        <em>Optional.</em>*/
        private String internalSubset;
        /**`Document`'s XML version in case of XML documents.
        <em>Optional.</em>*/
        private String xmlVersion;
        /**`Attr`'s name.
        <em>Optional.</em>*/
        private String name;
        /**`Attr`'s value.
        <em>Optional.</em>*/
        private String value;
        /**Pseudo element type for this node.
        <em>Optional.</em>*/
        private PseudoType pseudoType;
        /**Shadow root type.
        <em>Optional.</em>*/
        private ShadowRootType shadowRootType;
        /**Frame ID for frame owner elements.
        <em>Optional.</em>*/
        private Page.FrameId frameId;
        /**Content document for frame owner elements.
        <em>Optional.</em>*/
        private Node contentDocument;
        /**Shadow root list for given element host.
        <em>Optional.</em>*/
        private List<Node> shadowRoots;
        /**Content document fragment for template elements.
        <em>Optional.</em>*/
        private Node templateContent;
        /**Pseudo elements associated with this node.
        <em>Optional.</em>*/
        private List<Node> pseudoElements;
        /**Import document for the HTMLImport links.
        <em>Optional.</em>*/
        private Node importedDocument;
        /**Distributed nodes for given insertion point.
        <em>Optional.</em>*/
        private List<BackendNode> distributedNodes;
        /**Whether the node is SVG.
        <em>Optional.</em>*/
        private Boolean isSVG;
        public final Node nodeId(NodeId nodeId) { this.nodeId = nodeId; return this; }
        public final Node setNodeId(NodeId nodeId) { return nodeId(nodeId); }
        public final NodeId nodeId() { return nodeId; }
        public final NodeId getNodeId() { return nodeId(); }
        public final Node parentId(@Nullable NodeId parentId) { this.parentId = parentId; return this; }
        public final Node optParentId(@Nullable NodeId parentId) { return parentId(parentId); }
        public final NodeId parentId() { return parentId; }
        public final NodeId getParentId() { return parentId(); }
        public final Node backendNodeId(BackendNodeId backendNodeId) { this.backendNodeId = backendNodeId; return this; }
        public final Node setBackendNodeId(BackendNodeId backendNodeId) { return backendNodeId(backendNodeId); }
        public final BackendNodeId backendNodeId() { return backendNodeId; }
        public final BackendNodeId getBackendNodeId() { return backendNodeId(); }
        public final Node nodeType(Integer nodeType) { this.nodeType = nodeType; return this; }
        public final Node setNodeType(Integer nodeType) { return nodeType(nodeType); }
        public final Integer nodeType() { return nodeType; }
        public final Integer getNodeType() { return nodeType(); }
        public final Node nodeName(String nodeName) { this.nodeName = nodeName; return this; }
        public final Node setNodeName(String nodeName) { return nodeName(nodeName); }
        public final String nodeName() { return nodeName; }
        public final String getNodeName() { return nodeName(); }
        public final Node localName(String localName) { this.localName = localName; return this; }
        public final Node setLocalName(String localName) { return localName(localName); }
        public final String localName() { return localName; }
        public final String getLocalName() { return localName(); }
        public final Node nodeValue(String nodeValue) { this.nodeValue = nodeValue; return this; }
        public final Node setNodeValue(String nodeValue) { return nodeValue(nodeValue); }
        public final String nodeValue() { return nodeValue; }
        public final String getNodeValue() { return nodeValue(); }
        public final Node childNodeCount(@Nullable Integer childNodeCount) { this.childNodeCount = childNodeCount; return this; }
        public final Node optChildNodeCount(@Nullable Integer childNodeCount) { return childNodeCount(childNodeCount); }
        public final Integer childNodeCount() { return childNodeCount; }
        public final Integer getChildNodeCount() { return childNodeCount(); }
        public final Node children(@Nullable List<Node> children) { this.children = children; return this; }
        public final Node optChildren(@Nullable List<Node> children) { return children(children); }
        public final List<Node> children() { return children; }
        public final List<Node> getChildren() { return children(); }
        public final Node attributes(@Nullable List<String> attributes) { this.attributes = attributes; return this; }
        public final Node optAttributes(@Nullable List<String> attributes) { return attributes(attributes); }
        public final List<String> attributes() { return attributes; }
        public final List<String> getAttributes() { return attributes(); }
        public final Node documentURL(@Nullable String documentURL) { this.documentURL = documentURL; return this; }
        public final Node optDocumentURL(@Nullable String documentURL) { return documentURL(documentURL); }
        public final String documentURL() { return documentURL; }
        public final String getDocumentURL() { return documentURL(); }
        public final Node baseURL(@Nullable String baseURL) { this.baseURL = baseURL; return this; }
        public final Node optBaseURL(@Nullable String baseURL) { return baseURL(baseURL); }
        public final String baseURL() { return baseURL; }
        public final String getBaseURL() { return baseURL(); }
        public final Node publicId(@Nullable String publicId) { this.publicId = publicId; return this; }
        public final Node optPublicId(@Nullable String publicId) { return publicId(publicId); }
        public final String publicId() { return publicId; }
        public final String getPublicId() { return publicId(); }
        public final Node systemId(@Nullable String systemId) { this.systemId = systemId; return this; }
        public final Node optSystemId(@Nullable String systemId) { return systemId(systemId); }
        public final String systemId() { return systemId; }
        public final String getSystemId() { return systemId(); }
        public final Node internalSubset(@Nullable String internalSubset) { this.internalSubset = internalSubset; return this; }
        public final Node optInternalSubset(@Nullable String internalSubset) { return internalSubset(internalSubset); }
        public final String internalSubset() { return internalSubset; }
        public final String getInternalSubset() { return internalSubset(); }
        public final Node xmlVersion(@Nullable String xmlVersion) { this.xmlVersion = xmlVersion; return this; }
        public final Node optXmlVersion(@Nullable String xmlVersion) { return xmlVersion(xmlVersion); }
        public final String xmlVersion() { return xmlVersion; }
        public final String getXmlVersion() { return xmlVersion(); }
        public final Node name(@Nullable String name) { this.name = name; return this; }
        public final Node optName(@Nullable String name) { return name(name); }
        public final String name() { return name; }
        public final String getName() { return name(); }
        public final Node value(@Nullable String value) { this.value = value; return this; }
        public final Node optValue(@Nullable String value) { return value(value); }
        public final String value() { return value; }
        public final String getValue() { return value(); }
        public final Node pseudoType(@Nullable PseudoType pseudoType) { this.pseudoType = pseudoType; return this; }
        public final Node optPseudoType(@Nullable PseudoType pseudoType) { return pseudoType(pseudoType); }
        public final PseudoType pseudoType() { return pseudoType; }
        public final PseudoType getPseudoType() { return pseudoType(); }
        public final Node shadowRootType(@Nullable ShadowRootType shadowRootType) { this.shadowRootType = shadowRootType; return this; }
        public final Node optShadowRootType(@Nullable ShadowRootType shadowRootType) { return shadowRootType(shadowRootType); }
        public final ShadowRootType shadowRootType() { return shadowRootType; }
        public final ShadowRootType getShadowRootType() { return shadowRootType(); }
        public final Node frameId(@Nullable Page.FrameId frameId) { this.frameId = frameId; return this; }
        public final Node optFrameId(@Nullable Page.FrameId frameId) { return frameId(frameId); }
        public final Page.FrameId frameId() { return frameId; }
        public final Page.FrameId getFrameId() { return frameId(); }
        public final Node contentDocument(@Nullable Node contentDocument) { this.contentDocument = contentDocument; return this; }
        public final Node optContentDocument(@Nullable Node contentDocument) { return contentDocument(contentDocument); }
        public final Node contentDocument() { return contentDocument; }
        public final Node getContentDocument() { return contentDocument(); }
        public final Node shadowRoots(@Nullable List<Node> shadowRoots) { this.shadowRoots = shadowRoots; return this; }
        public final Node optShadowRoots(@Nullable List<Node> shadowRoots) { return shadowRoots(shadowRoots); }
        public final List<Node> shadowRoots() { return shadowRoots; }
        public final List<Node> getShadowRoots() { return shadowRoots(); }
        public final Node templateContent(@Nullable Node templateContent) { this.templateContent = templateContent; return this; }
        public final Node optTemplateContent(@Nullable Node templateContent) { return templateContent(templateContent); }
        public final Node templateContent() { return templateContent; }
        public final Node getTemplateContent() { return templateContent(); }
        public final Node pseudoElements(@Nullable List<Node> pseudoElements) { this.pseudoElements = pseudoElements; return this; }
        public final Node optPseudoElements(@Nullable List<Node> pseudoElements) { return pseudoElements(pseudoElements); }
        public final List<Node> pseudoElements() { return pseudoElements; }
        public final List<Node> getPseudoElements() { return pseudoElements(); }
        public final Node importedDocument(@Nullable Node importedDocument) { this.importedDocument = importedDocument; return this; }
        public final Node optImportedDocument(@Nullable Node importedDocument) { return importedDocument(importedDocument); }
        public final Node importedDocument() { return importedDocument; }
        public final Node getImportedDocument() { return importedDocument(); }
        public final Node distributedNodes(@Nullable List<BackendNode> distributedNodes) { this.distributedNodes = distributedNodes; return this; }
        public final Node optDistributedNodes(@Nullable List<BackendNode> distributedNodes) { return distributedNodes(distributedNodes); }
        public final List<BackendNode> distributedNodes() { return distributedNodes; }
        public final List<BackendNode> getDistributedNodes() { return distributedNodes(); }
        public final Node isSVG(@Nullable Boolean isSVG) { this.isSVG = isSVG; return this; }
        public final Node optIsSVG(@Nullable Boolean isSVG) { return isSVG(isSVG); }
        public final Boolean isSVG() { return isSVG; }
        public final Boolean getIsSVG() { return isSVG(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (nodeId == null) throw new IllegalArgumentException("DOM.Node.nodeId is necessary field.");
            if (backendNodeId == null) throw new IllegalArgumentException("DOM.Node.backendNodeId is necessary field.");
            if (nodeType == null) throw new IllegalArgumentException("DOM.Node.nodeType is necessary field.");
            if (nodeName == null) throw new IllegalArgumentException("DOM.Node.nodeName is necessary field.");
            if (localName == null) throw new IllegalArgumentException("DOM.Node.localName is necessary field.");
            if (nodeValue == null) throw new IllegalArgumentException("DOM.Node.nodeValue is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            nodeId.toJson(strBuilder.append("\"nodeId\":"));
            if (parentId != null) parentId.toJson(strBuilder.append(",\"parentId\":"));
            backendNodeId.toJson(strBuilder.append(",\"backendNodeId\":"));
            strBuilder.append(",\"nodeType\":").append(nodeType);
            strBuilder.append(",\"nodeName\":").append('"').append(DomainBase.escapeQuote(nodeName)).append('"');
            strBuilder.append(",\"localName\":").append('"').append(DomainBase.escapeQuote(localName)).append('"');
            strBuilder.append(",\"nodeValue\":").append('"').append(DomainBase.escapeQuote(nodeValue)).append('"');
            if (childNodeCount != null) strBuilder.append(",\"childNodeCount\":").append(childNodeCount);
            if (children != null) {
                strBuilder.append(",\"children\":[");
                children.get(0).toJson(strBuilder);
                for (int i = 1; i < children.size(); ++i)
                    children.get(i).toJson(strBuilder.append(','));
                strBuilder.append(']');
            }
            if (attributes != null) {
                strBuilder.append(",\"attributes\":[");
                strBuilder.append('"').append(DomainBase.escapeQuote(attributes.get(0))).append('"');
                for (int i = 1; i < attributes.size(); ++i)
                    strBuilder.append(",\"").append(DomainBase.escapeQuote(attributes.get(i))).append('"');
                strBuilder.append(']');
            }
            if (documentURL != null) strBuilder.append(",\"documentURL\":").append('"').append(DomainBase.escapeQuote(documentURL)).append('"');
            if (baseURL != null) strBuilder.append(",\"baseURL\":").append('"').append(DomainBase.escapeQuote(baseURL)).append('"');
            if (publicId != null) strBuilder.append(",\"publicId\":").append('"').append(DomainBase.escapeQuote(publicId)).append('"');
            if (systemId != null) strBuilder.append(",\"systemId\":").append('"').append(DomainBase.escapeQuote(systemId)).append('"');
            if (internalSubset != null) strBuilder.append(",\"internalSubset\":").append('"').append(DomainBase.escapeQuote(internalSubset)).append('"');
            if (xmlVersion != null) strBuilder.append(",\"xmlVersion\":").append('"').append(DomainBase.escapeQuote(xmlVersion)).append('"');
            if (name != null) strBuilder.append(",\"name\":").append('"').append(DomainBase.escapeQuote(name)).append('"');
            if (value != null) strBuilder.append(",\"value\":").append('"').append(DomainBase.escapeQuote(value)).append('"');
            if (pseudoType != null) pseudoType.toJson(strBuilder.append(",\"pseudoType\":"));
            if (shadowRootType != null) shadowRootType.toJson(strBuilder.append(",\"shadowRootType\":"));
            if (frameId != null) frameId.toJson(strBuilder.append(",\"frameId\":"));
            if (contentDocument != null) contentDocument.toJson(strBuilder.append(",\"contentDocument\":"));
            if (shadowRoots != null) {
                strBuilder.append(",\"shadowRoots\":[");
                shadowRoots.get(0).toJson(strBuilder);
                for (int i = 1; i < shadowRoots.size(); ++i)
                    shadowRoots.get(i).toJson(strBuilder.append(','));
                strBuilder.append(']');
            }
            if (templateContent != null) templateContent.toJson(strBuilder.append(",\"templateContent\":"));
            if (pseudoElements != null) {
                strBuilder.append(",\"pseudoElements\":[");
                pseudoElements.get(0).toJson(strBuilder);
                for (int i = 1; i < pseudoElements.size(); ++i)
                    pseudoElements.get(i).toJson(strBuilder.append(','));
                strBuilder.append(']');
            }
            if (importedDocument != null) importedDocument.toJson(strBuilder.append(",\"importedDocument\":"));
            if (distributedNodes != null) {
                strBuilder.append(",\"distributedNodes\":[");
                distributedNodes.get(0).toJson(strBuilder);
                for (int i = 1; i < distributedNodes.size(); ++i)
                    distributedNodes.get(i).toJson(strBuilder.append(','));
                strBuilder.append(']');
            }
            if (isSVG != null) strBuilder.append(",\"isSVG\":").append(isSVG);
            strBuilder.append('}');
            return strBuilder;
        }
        public Node() {}
        public Node(
            @JsonProperty("nodeId")NodeId nodeId,
            @Nullable @JsonProperty("parentId")NodeId parentId,
            @JsonProperty("backendNodeId")BackendNodeId backendNodeId,
            @JsonProperty("nodeType")Integer nodeType,
            @JsonProperty("nodeName")String nodeName,
            @JsonProperty("localName")String localName,
            @JsonProperty("nodeValue")String nodeValue,
            @Nullable @JsonProperty("childNodeCount")Integer childNodeCount,
            @Nullable @JsonProperty("children")List<Node> children,
            @Nullable @JsonProperty("attributes")List<String> attributes,
            @Nullable @JsonProperty("documentURL")String documentURL,
            @Nullable @JsonProperty("baseURL")String baseURL,
            @Nullable @JsonProperty("publicId")String publicId,
            @Nullable @JsonProperty("systemId")String systemId,
            @Nullable @JsonProperty("internalSubset")String internalSubset,
            @Nullable @JsonProperty("xmlVersion")String xmlVersion,
            @Nullable @JsonProperty("name")String name,
            @Nullable @JsonProperty("value")String value,
            @Nullable @JsonProperty("pseudoType")PseudoType pseudoType,
            @Nullable @JsonProperty("shadowRootType")ShadowRootType shadowRootType,
            @Nullable @JsonProperty("frameId")Page.FrameId frameId,
            @Nullable @JsonProperty("contentDocument")Node contentDocument,
            @Nullable @JsonProperty("shadowRoots")List<Node> shadowRoots,
            @Nullable @JsonProperty("templateContent")Node templateContent,
            @Nullable @JsonProperty("pseudoElements")List<Node> pseudoElements,
            @Nullable @JsonProperty("importedDocument")Node importedDocument,
            @Nullable @JsonProperty("distributedNodes")List<BackendNode> distributedNodes,
            @Nullable @JsonProperty("isSVG")Boolean isSVG
        ) {
            this.nodeId = nodeId;
            this.parentId = parentId;
            this.backendNodeId = backendNodeId;
            this.nodeType = nodeType;
            this.nodeName = nodeName;
            this.localName = localName;
            this.nodeValue = nodeValue;
            this.childNodeCount = childNodeCount;
            this.children = children;
            this.attributes = attributes;
            this.documentURL = documentURL;
            this.baseURL = baseURL;
            this.publicId = publicId;
            this.systemId = systemId;
            this.internalSubset = internalSubset;
            this.xmlVersion = xmlVersion;
            this.name = name;
            this.value = value;
            this.pseudoType = pseudoType;
            this.shadowRootType = shadowRootType;
            this.frameId = frameId;
            this.contentDocument = contentDocument;
            this.shadowRoots = shadowRoots;
            this.templateContent = templateContent;
            this.pseudoElements = pseudoElements;
            this.importedDocument = importedDocument;
            this.distributedNodes = distributedNodes;
            this.isSVG = isSVG;
        }
    }

    /**A structure holding an RGBA color.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class RGBA implements CommonDomainType {
        /**The red component, in the [0-255] range.*/
        private Integer r;
        /**The green component, in the [0-255] range.*/
        private Integer g;
        /**The blue component, in the [0-255] range.*/
        private Integer b;
        /**The alpha component, in the [0-1] range (default: 1).
        <em>Optional.</em>*/
        private Double a;
        public final RGBA r(Integer r) { this.r = r; return this; }
        public final RGBA setR(Integer r) { return r(r); }
        public final Integer r() { return r; }
        public final Integer getR() { return r(); }
        public final RGBA g(Integer g) { this.g = g; return this; }
        public final RGBA setG(Integer g) { return g(g); }
        public final Integer g() { return g; }
        public final Integer getG() { return g(); }
        public final RGBA b(Integer b) { this.b = b; return this; }
        public final RGBA setB(Integer b) { return b(b); }
        public final Integer b() { return b; }
        public final Integer getB() { return b(); }
        public final RGBA a(@Nullable Double a) { this.a = a; return this; }
        public final RGBA optA(@Nullable Double a) { return a(a); }
        public final Double a() { return a; }
        public final Double getA() { return a(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (r == null) throw new IllegalArgumentException("DOM.RGBA.r is necessary field.");
            if (g == null) throw new IllegalArgumentException("DOM.RGBA.g is necessary field.");
            if (b == null) throw new IllegalArgumentException("DOM.RGBA.b is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"r\":").append(r);
            strBuilder.append(",\"g\":").append(g);
            strBuilder.append(",\"b\":").append(b);
            if (a != null) strBuilder.append(",\"a\":").append(a);
            strBuilder.append('}');
            return strBuilder;
        }
        public RGBA() {}
        public RGBA(
            @JsonProperty("r")Integer r,
            @JsonProperty("g")Integer g,
            @JsonProperty("b")Integer b,
            @Nullable @JsonProperty("a")Double a
        ) {
            this.r = r;
            this.g = g;
            this.b = b;
            this.a = a;
        }
    }

    /**An array of quad vertices, x immediately followed by y for each point, points clock-wise.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class Quad implements CommonDomainType {
        private List<Double> _value;
        public Quad() {}
        public Quad(List<Double> value) { _value = value; }
        public final Quad value(List<Double> value) { _value = value; return this; }
        public final List<Double> value() { return _value; }
        public final Quad setValue(List<Double> value) { return value(value); }
        public final List<Double> getValue() { return value(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (_value == null) throw new IllegalArgumentException("DOM.Quad.value is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            return strBuilder.append(_value);
        }
    }

    /**Box model.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class BoxModel implements CommonDomainType {
        /**Content box*/
        private Quad content;
        /**Padding box*/
        private Quad padding;
        /**Border box*/
        private Quad border;
        /**Margin box*/
        private Quad margin;
        /**Node width*/
        private Integer width;
        /**Node height*/
        private Integer height;
        /**Shape outside coordinates
        <em>Optional.</em>*/
        private ShapeOutsideInfo shapeOutside;
        public final BoxModel content(Quad content) { this.content = content; return this; }
        public final BoxModel setContent(Quad content) { return content(content); }
        public final Quad content() { return content; }
        public final Quad getContent() { return content(); }
        public final BoxModel padding(Quad padding) { this.padding = padding; return this; }
        public final BoxModel setPadding(Quad padding) { return padding(padding); }
        public final Quad padding() { return padding; }
        public final Quad getPadding() { return padding(); }
        public final BoxModel border(Quad border) { this.border = border; return this; }
        public final BoxModel setBorder(Quad border) { return border(border); }
        public final Quad border() { return border; }
        public final Quad getBorder() { return border(); }
        public final BoxModel margin(Quad margin) { this.margin = margin; return this; }
        public final BoxModel setMargin(Quad margin) { return margin(margin); }
        public final Quad margin() { return margin; }
        public final Quad getMargin() { return margin(); }
        public final BoxModel width(Integer width) { this.width = width; return this; }
        public final BoxModel setWidth(Integer width) { return width(width); }
        public final Integer width() { return width; }
        public final Integer getWidth() { return width(); }
        public final BoxModel height(Integer height) { this.height = height; return this; }
        public final BoxModel setHeight(Integer height) { return height(height); }
        public final Integer height() { return height; }
        public final Integer getHeight() { return height(); }
        public final BoxModel shapeOutside(@Nullable ShapeOutsideInfo shapeOutside) { this.shapeOutside = shapeOutside; return this; }
        public final BoxModel optShapeOutside(@Nullable ShapeOutsideInfo shapeOutside) { return shapeOutside(shapeOutside); }
        public final ShapeOutsideInfo shapeOutside() { return shapeOutside; }
        public final ShapeOutsideInfo getShapeOutside() { return shapeOutside(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (content == null) throw new IllegalArgumentException("DOM.BoxModel.content is necessary field.");
            if (padding == null) throw new IllegalArgumentException("DOM.BoxModel.padding is necessary field.");
            if (border == null) throw new IllegalArgumentException("DOM.BoxModel.border is necessary field.");
            if (margin == null) throw new IllegalArgumentException("DOM.BoxModel.margin is necessary field.");
            if (width == null) throw new IllegalArgumentException("DOM.BoxModel.width is necessary field.");
            if (height == null) throw new IllegalArgumentException("DOM.BoxModel.height is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            content.toJson(strBuilder.append("\"content\":"));
            padding.toJson(strBuilder.append(",\"padding\":"));
            border.toJson(strBuilder.append(",\"border\":"));
            margin.toJson(strBuilder.append(",\"margin\":"));
            strBuilder.append(",\"width\":").append(width);
            strBuilder.append(",\"height\":").append(height);
            if (shapeOutside != null) shapeOutside.toJson(strBuilder.append(",\"shapeOutside\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public BoxModel() {}
        public BoxModel(
            @JsonProperty("content")Quad content,
            @JsonProperty("padding")Quad padding,
            @JsonProperty("border")Quad border,
            @JsonProperty("margin")Quad margin,
            @JsonProperty("width")Integer width,
            @JsonProperty("height")Integer height,
            @Nullable @JsonProperty("shapeOutside")ShapeOutsideInfo shapeOutside
        ) {
            this.content = content;
            this.padding = padding;
            this.border = border;
            this.margin = margin;
            this.width = width;
            this.height = height;
            this.shapeOutside = shapeOutside;
        }
    }

    /**CSS Shape Outside details.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ShapeOutsideInfo implements CommonDomainType {
        /**Shape bounds*/
        private Quad bounds;
        /**Shape coordinate details*/
        private List<Object> shape;
        /**Margin shape bounds*/
        private List<Object> marginShape;
        public final ShapeOutsideInfo bounds(Quad bounds) { this.bounds = bounds; return this; }
        public final ShapeOutsideInfo setBounds(Quad bounds) { return bounds(bounds); }
        public final Quad bounds() { return bounds; }
        public final Quad getBounds() { return bounds(); }
        public final ShapeOutsideInfo shape(List<Object> shape) { this.shape = shape; return this; }
        public final ShapeOutsideInfo setShape(List<Object> shape) { return shape(shape); }
        public final List<Object> shape() { return shape; }
        public final List<Object> getShape() { return shape(); }
        public final ShapeOutsideInfo marginShape(List<Object> marginShape) { this.marginShape = marginShape; return this; }
        public final ShapeOutsideInfo setMarginShape(List<Object> marginShape) { return marginShape(marginShape); }
        public final List<Object> marginShape() { return marginShape; }
        public final List<Object> getMarginShape() { return marginShape(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (bounds == null) throw new IllegalArgumentException("DOM.ShapeOutsideInfo.bounds is necessary field.");
            if (shape == null) throw new IllegalArgumentException("DOM.ShapeOutsideInfo.shape is necessary field.");
            if (marginShape == null) throw new IllegalArgumentException("DOM.ShapeOutsideInfo.marginShape is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            bounds.toJson(strBuilder.append("\"bounds\":"));
                        strBuilder.append(",\"shape\":[");
            strBuilder.append(shape.get(0));
            for (int i = 1; i < shape.size(); ++i)
                strBuilder.append(',').append(shape.get(i));
            strBuilder.append(']');
                        strBuilder.append(",\"marginShape\":[");
            strBuilder.append(marginShape.get(0));
            for (int i = 1; i < marginShape.size(); ++i)
                strBuilder.append(',').append(marginShape.get(i));
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public ShapeOutsideInfo() {}
        public ShapeOutsideInfo(
            @JsonProperty("bounds")Quad bounds,
            @JsonProperty("shape")List<Object> shape,
            @JsonProperty("marginShape")List<Object> marginShape
        ) {
            this.bounds = bounds;
            this.shape = shape;
            this.marginShape = marginShape;
        }
    }

    /**Rectangle.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class Rect implements CommonDomainType {
        /**X coordinate*/
        private Double x;
        /**Y coordinate*/
        private Double y;
        /**Rectangle width*/
        private Double width;
        /**Rectangle height*/
        private Double height;
        public final Rect x(Double x) { this.x = x; return this; }
        public final Rect setX(Double x) { return x(x); }
        public final Double x() { return x; }
        public final Double getX() { return x(); }
        public final Rect y(Double y) { this.y = y; return this; }
        public final Rect setY(Double y) { return y(y); }
        public final Double y() { return y; }
        public final Double getY() { return y(); }
        public final Rect width(Double width) { this.width = width; return this; }
        public final Rect setWidth(Double width) { return width(width); }
        public final Double width() { return width; }
        public final Double getWidth() { return width(); }
        public final Rect height(Double height) { this.height = height; return this; }
        public final Rect setHeight(Double height) { return height(height); }
        public final Double height() { return height; }
        public final Double getHeight() { return height(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (x == null) throw new IllegalArgumentException("DOM.Rect.x is necessary field.");
            if (y == null) throw new IllegalArgumentException("DOM.Rect.y is necessary field.");
            if (width == null) throw new IllegalArgumentException("DOM.Rect.width is necessary field.");
            if (height == null) throw new IllegalArgumentException("DOM.Rect.height is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"x\":").append(x);
            strBuilder.append(",\"y\":").append(y);
            strBuilder.append(",\"width\":").append(width);
            strBuilder.append(",\"height\":").append(height);
            strBuilder.append('}');
            return strBuilder;
        }
        public Rect() {}
        public Rect(
            @JsonProperty("x")Double x,
            @JsonProperty("y")Double y,
            @JsonProperty("width")Double width,
            @JsonProperty("height")Double height
        ) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }
    }
    /**Collects class names for the node with given id and all of it's child nodes.
    <p><strong>Experimental.</strong></p>*/
    public CollectClassNamesFromSubtreeParameter collectClassNamesFromSubtree() { final CollectClassNamesFromSubtreeParameter v = new CollectClassNamesFromSubtreeParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of collectClassNamesFromSubtree.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class CollectClassNamesFromSubtreeParameter extends CommandBase {
        /**Id of the node to collect class names.*/
        private NodeId nodeId;
        public final CollectClassNamesFromSubtreeParameter nodeId(NodeId nodeId) { this.nodeId = nodeId; return this; }
        public final CollectClassNamesFromSubtreeParameter setNodeId(NodeId nodeId) { return nodeId(nodeId); }
        public final NodeId nodeId() { return nodeId; }
        public final NodeId getNodeId() { return nodeId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (nodeId == null) throw new IllegalArgumentException("DOM.CollectClassNamesFromSubtreeParameter.nodeId is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            nodeId.toJson(strBuilder.append("\"nodeId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public CollectClassNamesFromSubtreeParameter() {}
        public CollectClassNamesFromSubtreeParameter(
            @JsonProperty("nodeId")NodeId nodeId
        ) {
            this();
            this.nodeId = nodeId;
        }
        public CompletableFuture<CollectClassNamesFromSubtreeResult> call() {
            return super.call("DOM.collectClassNamesFromSubtree", CollectClassNamesFromSubtreeResult.class,
                (code, msg)->new CollectClassNamesFromSubtreeResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<CollectClassNamesFromSubtreeResult> call(Executor exec) {
            return super.call("DOM.collectClassNamesFromSubtree", CollectClassNamesFromSubtreeResult.class,
                (code, msg)->new CollectClassNamesFromSubtreeResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of collectClassNamesFromSubtree.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class CollectClassNamesFromSubtreeResult extends ResultBase {
        /**Class name list.*/
        private final List<String> classNames;
        public final List<String> classNames() { return classNames; }
        public final List<String> getClassNames() { return classNames(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
                        strBuilder.append("\"classNames\":[");
            strBuilder.append('"').append(DomainBase.escapeQuote(classNames.get(0))).append('"');
            for (int i = 1; i < classNames.size(); ++i)
                strBuilder.append(",\"").append(DomainBase.escapeQuote(classNames.get(i))).append('"');
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public CollectClassNamesFromSubtreeResult(
            @JsonProperty("classNames")List<String> classNames
        ) {
            this.classNames = classNames;
        }
        public CollectClassNamesFromSubtreeResult(ResultBase.FailedResult e) {
            super(e);
            classNames = null;
        }
    }
    /**Creates a deep copy of the specified node and places it into the target container before the
given anchor.
    <p><strong>Experimental.</strong></p>*/
    public CopyToParameter copyTo() { final CopyToParameter v = new CopyToParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of copyTo.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class CopyToParameter extends CommandBase {
        /**Id of the node to copy.*/
        private NodeId nodeId;
        /**Id of the element to drop the copy into.*/
        private NodeId targetNodeId;
        /**Drop the copy before this node (if absent, the copy becomes the last child of
`targetNodeId`).
        <em>Optional.</em>*/
        private NodeId insertBeforeNodeId;
        public final CopyToParameter nodeId(NodeId nodeId) { this.nodeId = nodeId; return this; }
        public final CopyToParameter setNodeId(NodeId nodeId) { return nodeId(nodeId); }
        public final NodeId nodeId() { return nodeId; }
        public final NodeId getNodeId() { return nodeId(); }
        public final CopyToParameter targetNodeId(NodeId targetNodeId) { this.targetNodeId = targetNodeId; return this; }
        public final CopyToParameter setTargetNodeId(NodeId targetNodeId) { return targetNodeId(targetNodeId); }
        public final NodeId targetNodeId() { return targetNodeId; }
        public final NodeId getTargetNodeId() { return targetNodeId(); }
        public final CopyToParameter insertBeforeNodeId(@Nullable NodeId insertBeforeNodeId) { this.insertBeforeNodeId = insertBeforeNodeId; return this; }
        public final CopyToParameter optInsertBeforeNodeId(@Nullable NodeId insertBeforeNodeId) { return insertBeforeNodeId(insertBeforeNodeId); }
        public final NodeId insertBeforeNodeId() { return insertBeforeNodeId; }
        public final NodeId getInsertBeforeNodeId() { return insertBeforeNodeId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (nodeId == null) throw new IllegalArgumentException("DOM.CopyToParameter.nodeId is necessary field.");
            if (targetNodeId == null) throw new IllegalArgumentException("DOM.CopyToParameter.targetNodeId is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            nodeId.toJson(strBuilder.append("\"nodeId\":"));
            targetNodeId.toJson(strBuilder.append(",\"targetNodeId\":"));
            if (insertBeforeNodeId != null) insertBeforeNodeId.toJson(strBuilder.append(",\"insertBeforeNodeId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public CopyToParameter() {}
        public CopyToParameter(
            @JsonProperty("nodeId")NodeId nodeId,
            @JsonProperty("targetNodeId")NodeId targetNodeId,
            @Nullable @JsonProperty("insertBeforeNodeId")NodeId insertBeforeNodeId
        ) {
            this();
            this.nodeId = nodeId;
            this.targetNodeId = targetNodeId;
            this.insertBeforeNodeId = insertBeforeNodeId;
        }
        public CompletableFuture<CopyToResult> call() {
            return super.call("DOM.copyTo", CopyToResult.class,
                (code, msg)->new CopyToResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<CopyToResult> call(Executor exec) {
            return super.call("DOM.copyTo", CopyToResult.class,
                (code, msg)->new CopyToResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of copyTo.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class CopyToResult extends ResultBase {
        /**Id of the node clone.*/
        private final NodeId nodeId;
        public final NodeId nodeId() { return nodeId; }
        public final NodeId getNodeId() { return nodeId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            nodeId.toJson(strBuilder.append("\"nodeId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public CopyToResult(
            @JsonProperty("nodeId")NodeId nodeId
        ) {
            this.nodeId = nodeId;
        }
        public CopyToResult(ResultBase.FailedResult e) {
            super(e);
            nodeId = null;
        }
    }
    /**Describes node given its id, does not require domain to be enabled. Does not start tracking any
objects, can be used for automation.*/
    public DescribeNodeParameter describeNode() { final DescribeNodeParameter v = new DescribeNodeParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of describeNode.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class DescribeNodeParameter extends CommandBase {
        /**Identifier of the node.
        <em>Optional.</em>*/
        private NodeId nodeId;
        /**Identifier of the backend node.
        <em>Optional.</em>*/
        private BackendNodeId backendNodeId;
        /**JavaScript object id of the node wrapper.
        <em>Optional.</em>*/
        private Runtime.RemoteObjectId objectId;
        /**The maximum depth at which children should be retrieved, defaults to 1. Use -1 for the
entire subtree or provide an integer larger than 0.
        <em>Optional.</em>*/
        private Integer depth;
        /**Whether or not iframes and shadow roots should be traversed when returning the subtree
(default is false).
        <em>Optional.</em>*/
        private Boolean pierce;
        public final DescribeNodeParameter nodeId(@Nullable NodeId nodeId) { this.nodeId = nodeId; return this; }
        public final DescribeNodeParameter optNodeId(@Nullable NodeId nodeId) { return nodeId(nodeId); }
        public final NodeId nodeId() { return nodeId; }
        public final NodeId getNodeId() { return nodeId(); }
        public final DescribeNodeParameter backendNodeId(@Nullable BackendNodeId backendNodeId) { this.backendNodeId = backendNodeId; return this; }
        public final DescribeNodeParameter optBackendNodeId(@Nullable BackendNodeId backendNodeId) { return backendNodeId(backendNodeId); }
        public final BackendNodeId backendNodeId() { return backendNodeId; }
        public final BackendNodeId getBackendNodeId() { return backendNodeId(); }
        public final DescribeNodeParameter objectId(@Nullable Runtime.RemoteObjectId objectId) { this.objectId = objectId; return this; }
        public final DescribeNodeParameter optObjectId(@Nullable Runtime.RemoteObjectId objectId) { return objectId(objectId); }
        public final Runtime.RemoteObjectId objectId() { return objectId; }
        public final Runtime.RemoteObjectId getObjectId() { return objectId(); }
        public final DescribeNodeParameter depth(@Nullable Integer depth) { this.depth = depth; return this; }
        public final DescribeNodeParameter optDepth(@Nullable Integer depth) { return depth(depth); }
        public final Integer depth() { return depth; }
        public final Integer getDepth() { return depth(); }
        public final DescribeNodeParameter pierce(@Nullable Boolean pierce) { this.pierce = pierce; return this; }
        public final DescribeNodeParameter optPierce(@Nullable Boolean pierce) { return pierce(pierce); }
        public final Boolean pierce() { return pierce; }
        public final Boolean getPierce() { return pierce(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            if (nodeId != null) nodeId.toJson(strBuilder.append("\"nodeId\":"));
            if (backendNodeId != null) backendNodeId.toJson(strBuilder.append(",\"backendNodeId\":"));
            if (objectId != null) objectId.toJson(strBuilder.append(",\"objectId\":"));
            if (depth != null) strBuilder.append(",\"depth\":").append(depth);
            if (pierce != null) strBuilder.append(",\"pierce\":").append(pierce);
            strBuilder.append('}');
            return strBuilder;
        }
        public DescribeNodeParameter() {}
        public DescribeNodeParameter(
            @Nullable @JsonProperty("nodeId")NodeId nodeId,
            @Nullable @JsonProperty("backendNodeId")BackendNodeId backendNodeId,
            @Nullable @JsonProperty("objectId")Runtime.RemoteObjectId objectId,
            @Nullable @JsonProperty("depth")Integer depth,
            @Nullable @JsonProperty("pierce")Boolean pierce
        ) {
            this();
            this.nodeId = nodeId;
            this.backendNodeId = backendNodeId;
            this.objectId = objectId;
            this.depth = depth;
            this.pierce = pierce;
        }
        public CompletableFuture<DescribeNodeResult> call() {
            return super.call("DOM.describeNode", DescribeNodeResult.class,
                (code, msg)->new DescribeNodeResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<DescribeNodeResult> call(Executor exec) {
            return super.call("DOM.describeNode", DescribeNodeResult.class,
                (code, msg)->new DescribeNodeResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of describeNode.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class DescribeNodeResult extends ResultBase {
        /**Node description.*/
        private final Node node;
        public final Node node() { return node; }
        public final Node getNode() { return node(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            node.toJson(strBuilder.append("\"node\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public DescribeNodeResult(
            @JsonProperty("node")Node node
        ) {
            this.node = node;
        }
        public DescribeNodeResult(ResultBase.FailedResult e) {
            super(e);
            node = null;
        }
    }
    /**Disables DOM agent for the given page.*/
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
            return super.call("DOM.disable", DisableResult.class,
                (code, msg)->new DisableResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<DisableResult> call(Executor exec) {
            return super.call("DOM.disable", DisableResult.class,
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
    /**Discards search results from the session with the given id. `getSearchResults` should no longer
be called for that search.
    <p><strong>Experimental.</strong></p>*/
    public DiscardSearchResultsParameter discardSearchResults() { final DiscardSearchResultsParameter v = new DiscardSearchResultsParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of discardSearchResults.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class DiscardSearchResultsParameter extends CommandBase {
        /**Unique search session identifier.*/
        private String searchId;
        public final DiscardSearchResultsParameter searchId(String searchId) { this.searchId = searchId; return this; }
        public final DiscardSearchResultsParameter setSearchId(String searchId) { return searchId(searchId); }
        public final String searchId() { return searchId; }
        public final String getSearchId() { return searchId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (searchId == null) throw new IllegalArgumentException("DOM.DiscardSearchResultsParameter.searchId is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"searchId\":").append('"').append(DomainBase.escapeQuote(searchId)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public DiscardSearchResultsParameter() {}
        public DiscardSearchResultsParameter(
            @JsonProperty("searchId")String searchId
        ) {
            this();
            this.searchId = searchId;
        }
        public CompletableFuture<DiscardSearchResultsResult> call() {
            return super.call("DOM.discardSearchResults", DiscardSearchResultsResult.class,
                (code, msg)->new DiscardSearchResultsResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<DiscardSearchResultsResult> call(Executor exec) {
            return super.call("DOM.discardSearchResults", DiscardSearchResultsResult.class,
                (code, msg)->new DiscardSearchResultsResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of discardSearchResults.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class DiscardSearchResultsResult extends ResultBase {
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
        public DiscardSearchResultsResult() { super(); }
        public DiscardSearchResultsResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Enables DOM agent for the given page.*/
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
            return super.call("DOM.enable", EnableResult.class,
                (code, msg)->new EnableResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<EnableResult> call(Executor exec) {
            return super.call("DOM.enable", EnableResult.class,
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
    /**Focuses the given element.*/
    public FocusParameter focus() { final FocusParameter v = new FocusParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of focus.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class FocusParameter extends CommandBase {
        /**Identifier of the node.
        <em>Optional.</em>*/
        private NodeId nodeId;
        /**Identifier of the backend node.
        <em>Optional.</em>*/
        private BackendNodeId backendNodeId;
        /**JavaScript object id of the node wrapper.
        <em>Optional.</em>*/
        private Runtime.RemoteObjectId objectId;
        public final FocusParameter nodeId(@Nullable NodeId nodeId) { this.nodeId = nodeId; return this; }
        public final FocusParameter optNodeId(@Nullable NodeId nodeId) { return nodeId(nodeId); }
        public final NodeId nodeId() { return nodeId; }
        public final NodeId getNodeId() { return nodeId(); }
        public final FocusParameter backendNodeId(@Nullable BackendNodeId backendNodeId) { this.backendNodeId = backendNodeId; return this; }
        public final FocusParameter optBackendNodeId(@Nullable BackendNodeId backendNodeId) { return backendNodeId(backendNodeId); }
        public final BackendNodeId backendNodeId() { return backendNodeId; }
        public final BackendNodeId getBackendNodeId() { return backendNodeId(); }
        public final FocusParameter objectId(@Nullable Runtime.RemoteObjectId objectId) { this.objectId = objectId; return this; }
        public final FocusParameter optObjectId(@Nullable Runtime.RemoteObjectId objectId) { return objectId(objectId); }
        public final Runtime.RemoteObjectId objectId() { return objectId; }
        public final Runtime.RemoteObjectId getObjectId() { return objectId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            if (nodeId != null) nodeId.toJson(strBuilder.append("\"nodeId\":"));
            if (backendNodeId != null) backendNodeId.toJson(strBuilder.append(",\"backendNodeId\":"));
            if (objectId != null) objectId.toJson(strBuilder.append(",\"objectId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public FocusParameter() {}
        public FocusParameter(
            @Nullable @JsonProperty("nodeId")NodeId nodeId,
            @Nullable @JsonProperty("backendNodeId")BackendNodeId backendNodeId,
            @Nullable @JsonProperty("objectId")Runtime.RemoteObjectId objectId
        ) {
            this();
            this.nodeId = nodeId;
            this.backendNodeId = backendNodeId;
            this.objectId = objectId;
        }
        public CompletableFuture<FocusResult> call() {
            return super.call("DOM.focus", FocusResult.class,
                (code, msg)->new FocusResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<FocusResult> call(Executor exec) {
            return super.call("DOM.focus", FocusResult.class,
                (code, msg)->new FocusResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of focus.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class FocusResult extends ResultBase {
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
        public FocusResult() { super(); }
        public FocusResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Returns attributes for the specified node.*/
    public GetAttributesParameter getAttributes() { final GetAttributesParameter v = new GetAttributesParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of getAttributes.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetAttributesParameter extends CommandBase {
        /**Id of the node to retrieve attibutes for.*/
        private NodeId nodeId;
        public final GetAttributesParameter nodeId(NodeId nodeId) { this.nodeId = nodeId; return this; }
        public final GetAttributesParameter setNodeId(NodeId nodeId) { return nodeId(nodeId); }
        public final NodeId nodeId() { return nodeId; }
        public final NodeId getNodeId() { return nodeId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (nodeId == null) throw new IllegalArgumentException("DOM.GetAttributesParameter.nodeId is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            nodeId.toJson(strBuilder.append("\"nodeId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public GetAttributesParameter() {}
        public GetAttributesParameter(
            @JsonProperty("nodeId")NodeId nodeId
        ) {
            this();
            this.nodeId = nodeId;
        }
        public CompletableFuture<GetAttributesResult> call() {
            return super.call("DOM.getAttributes", GetAttributesResult.class,
                (code, msg)->new GetAttributesResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetAttributesResult> call(Executor exec) {
            return super.call("DOM.getAttributes", GetAttributesResult.class,
                (code, msg)->new GetAttributesResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of getAttributes.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetAttributesResult extends ResultBase {
        /**An interleaved array of node attribute names and values.*/
        private final List<String> attributes;
        public final List<String> attributes() { return attributes; }
        public final List<String> getAttributes() { return attributes(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
                        strBuilder.append("\"attributes\":[");
            strBuilder.append('"').append(DomainBase.escapeQuote(attributes.get(0))).append('"');
            for (int i = 1; i < attributes.size(); ++i)
                strBuilder.append(",\"").append(DomainBase.escapeQuote(attributes.get(i))).append('"');
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public GetAttributesResult(
            @JsonProperty("attributes")List<String> attributes
        ) {
            this.attributes = attributes;
        }
        public GetAttributesResult(ResultBase.FailedResult e) {
            super(e);
            attributes = null;
        }
    }
    /**Returns boxes for the given node.*/
    public GetBoxModelParameter getBoxModel() { final GetBoxModelParameter v = new GetBoxModelParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of getBoxModel.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetBoxModelParameter extends CommandBase {
        /**Identifier of the node.
        <em>Optional.</em>*/
        private NodeId nodeId;
        /**Identifier of the backend node.
        <em>Optional.</em>*/
        private BackendNodeId backendNodeId;
        /**JavaScript object id of the node wrapper.
        <em>Optional.</em>*/
        private Runtime.RemoteObjectId objectId;
        public final GetBoxModelParameter nodeId(@Nullable NodeId nodeId) { this.nodeId = nodeId; return this; }
        public final GetBoxModelParameter optNodeId(@Nullable NodeId nodeId) { return nodeId(nodeId); }
        public final NodeId nodeId() { return nodeId; }
        public final NodeId getNodeId() { return nodeId(); }
        public final GetBoxModelParameter backendNodeId(@Nullable BackendNodeId backendNodeId) { this.backendNodeId = backendNodeId; return this; }
        public final GetBoxModelParameter optBackendNodeId(@Nullable BackendNodeId backendNodeId) { return backendNodeId(backendNodeId); }
        public final BackendNodeId backendNodeId() { return backendNodeId; }
        public final BackendNodeId getBackendNodeId() { return backendNodeId(); }
        public final GetBoxModelParameter objectId(@Nullable Runtime.RemoteObjectId objectId) { this.objectId = objectId; return this; }
        public final GetBoxModelParameter optObjectId(@Nullable Runtime.RemoteObjectId objectId) { return objectId(objectId); }
        public final Runtime.RemoteObjectId objectId() { return objectId; }
        public final Runtime.RemoteObjectId getObjectId() { return objectId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            if (nodeId != null) nodeId.toJson(strBuilder.append("\"nodeId\":"));
            if (backendNodeId != null) backendNodeId.toJson(strBuilder.append(",\"backendNodeId\":"));
            if (objectId != null) objectId.toJson(strBuilder.append(",\"objectId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public GetBoxModelParameter() {}
        public GetBoxModelParameter(
            @Nullable @JsonProperty("nodeId")NodeId nodeId,
            @Nullable @JsonProperty("backendNodeId")BackendNodeId backendNodeId,
            @Nullable @JsonProperty("objectId")Runtime.RemoteObjectId objectId
        ) {
            this();
            this.nodeId = nodeId;
            this.backendNodeId = backendNodeId;
            this.objectId = objectId;
        }
        public CompletableFuture<GetBoxModelResult> call() {
            return super.call("DOM.getBoxModel", GetBoxModelResult.class,
                (code, msg)->new GetBoxModelResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetBoxModelResult> call(Executor exec) {
            return super.call("DOM.getBoxModel", GetBoxModelResult.class,
                (code, msg)->new GetBoxModelResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of getBoxModel.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetBoxModelResult extends ResultBase {
        /**Box model for the node.*/
        private final BoxModel model;
        public final BoxModel model() { return model; }
        public final BoxModel getModel() { return model(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            model.toJson(strBuilder.append("\"model\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public GetBoxModelResult(
            @JsonProperty("model")BoxModel model
        ) {
            this.model = model;
        }
        public GetBoxModelResult(ResultBase.FailedResult e) {
            super(e);
            model = null;
        }
    }
    /**Returns the root DOM node (and optionally the subtree) to the caller.*/
    public GetDocumentParameter getDocument() { final GetDocumentParameter v = new GetDocumentParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of getDocument.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetDocumentParameter extends CommandBase {
        /**The maximum depth at which children should be retrieved, defaults to 1. Use -1 for the
entire subtree or provide an integer larger than 0.
        <em>Optional.</em>*/
        private Integer depth;
        /**Whether or not iframes and shadow roots should be traversed when returning the subtree
(default is false).
        <em>Optional.</em>*/
        private Boolean pierce;
        public final GetDocumentParameter depth(@Nullable Integer depth) { this.depth = depth; return this; }
        public final GetDocumentParameter optDepth(@Nullable Integer depth) { return depth(depth); }
        public final Integer depth() { return depth; }
        public final Integer getDepth() { return depth(); }
        public final GetDocumentParameter pierce(@Nullable Boolean pierce) { this.pierce = pierce; return this; }
        public final GetDocumentParameter optPierce(@Nullable Boolean pierce) { return pierce(pierce); }
        public final Boolean pierce() { return pierce; }
        public final Boolean getPierce() { return pierce(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            if (depth != null) strBuilder.append("\"depth\":").append(depth);
            if (pierce != null) strBuilder.append(",\"pierce\":").append(pierce);
            strBuilder.append('}');
            return strBuilder;
        }
        public GetDocumentParameter() {}
        public GetDocumentParameter(
            @Nullable @JsonProperty("depth")Integer depth,
            @Nullable @JsonProperty("pierce")Boolean pierce
        ) {
            this();
            this.depth = depth;
            this.pierce = pierce;
        }
        public CompletableFuture<GetDocumentResult> call() {
            return super.call("DOM.getDocument", GetDocumentResult.class,
                (code, msg)->new GetDocumentResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetDocumentResult> call(Executor exec) {
            return super.call("DOM.getDocument", GetDocumentResult.class,
                (code, msg)->new GetDocumentResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of getDocument.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetDocumentResult extends ResultBase {
        /**Resulting node.*/
        private final Node root;
        public final Node root() { return root; }
        public final Node getRoot() { return root(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            root.toJson(strBuilder.append("\"root\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public GetDocumentResult(
            @JsonProperty("root")Node root
        ) {
            this.root = root;
        }
        public GetDocumentResult(ResultBase.FailedResult e) {
            super(e);
            root = null;
        }
    }
    /**Returns the root DOM node (and optionally the subtree) to the caller.*/
    public GetFlattenedDocumentParameter getFlattenedDocument() { final GetFlattenedDocumentParameter v = new GetFlattenedDocumentParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of getFlattenedDocument.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetFlattenedDocumentParameter extends CommandBase {
        /**The maximum depth at which children should be retrieved, defaults to 1. Use -1 for the
entire subtree or provide an integer larger than 0.
        <em>Optional.</em>*/
        private Integer depth;
        /**Whether or not iframes and shadow roots should be traversed when returning the subtree
(default is false).
        <em>Optional.</em>*/
        private Boolean pierce;
        public final GetFlattenedDocumentParameter depth(@Nullable Integer depth) { this.depth = depth; return this; }
        public final GetFlattenedDocumentParameter optDepth(@Nullable Integer depth) { return depth(depth); }
        public final Integer depth() { return depth; }
        public final Integer getDepth() { return depth(); }
        public final GetFlattenedDocumentParameter pierce(@Nullable Boolean pierce) { this.pierce = pierce; return this; }
        public final GetFlattenedDocumentParameter optPierce(@Nullable Boolean pierce) { return pierce(pierce); }
        public final Boolean pierce() { return pierce; }
        public final Boolean getPierce() { return pierce(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            if (depth != null) strBuilder.append("\"depth\":").append(depth);
            if (pierce != null) strBuilder.append(",\"pierce\":").append(pierce);
            strBuilder.append('}');
            return strBuilder;
        }
        public GetFlattenedDocumentParameter() {}
        public GetFlattenedDocumentParameter(
            @Nullable @JsonProperty("depth")Integer depth,
            @Nullable @JsonProperty("pierce")Boolean pierce
        ) {
            this();
            this.depth = depth;
            this.pierce = pierce;
        }
        public CompletableFuture<GetFlattenedDocumentResult> call() {
            return super.call("DOM.getFlattenedDocument", GetFlattenedDocumentResult.class,
                (code, msg)->new GetFlattenedDocumentResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetFlattenedDocumentResult> call(Executor exec) {
            return super.call("DOM.getFlattenedDocument", GetFlattenedDocumentResult.class,
                (code, msg)->new GetFlattenedDocumentResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of getFlattenedDocument.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetFlattenedDocumentResult extends ResultBase {
        /**Resulting node.*/
        private final List<Node> nodes;
        public final List<Node> nodes() { return nodes; }
        public final List<Node> getNodes() { return nodes(); }
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
        public GetFlattenedDocumentResult(
            @JsonProperty("nodes")List<Node> nodes
        ) {
            this.nodes = nodes;
        }
        public GetFlattenedDocumentResult(ResultBase.FailedResult e) {
            super(e);
            nodes = null;
        }
    }
    /**Returns node id at given location.
    <p><strong>Experimental.</strong></p>*/
    public GetNodeForLocationParameter getNodeForLocation() { final GetNodeForLocationParameter v = new GetNodeForLocationParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of getNodeForLocation.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetNodeForLocationParameter extends CommandBase {
        /**X coordinate.*/
        private Integer x;
        /**Y coordinate.*/
        private Integer y;
        /**False to skip to the nearest non-UA shadow root ancestor (default: false).
        <em>Optional.</em>*/
        private Boolean includeUserAgentShadowDOM;
        public final GetNodeForLocationParameter x(Integer x) { this.x = x; return this; }
        public final GetNodeForLocationParameter setX(Integer x) { return x(x); }
        public final Integer x() { return x; }
        public final Integer getX() { return x(); }
        public final GetNodeForLocationParameter y(Integer y) { this.y = y; return this; }
        public final GetNodeForLocationParameter setY(Integer y) { return y(y); }
        public final Integer y() { return y; }
        public final Integer getY() { return y(); }
        public final GetNodeForLocationParameter includeUserAgentShadowDOM(@Nullable Boolean includeUserAgentShadowDOM) { this.includeUserAgentShadowDOM = includeUserAgentShadowDOM; return this; }
        public final GetNodeForLocationParameter optIncludeUserAgentShadowDOM(@Nullable Boolean includeUserAgentShadowDOM) { return includeUserAgentShadowDOM(includeUserAgentShadowDOM); }
        public final Boolean includeUserAgentShadowDOM() { return includeUserAgentShadowDOM; }
        public final Boolean getIncludeUserAgentShadowDOM() { return includeUserAgentShadowDOM(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (x == null) throw new IllegalArgumentException("DOM.GetNodeForLocationParameter.x is necessary field.");
            if (y == null) throw new IllegalArgumentException("DOM.GetNodeForLocationParameter.y is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"x\":").append(x);
            strBuilder.append(",\"y\":").append(y);
            if (includeUserAgentShadowDOM != null) strBuilder.append(",\"includeUserAgentShadowDOM\":").append(includeUserAgentShadowDOM);
            strBuilder.append('}');
            return strBuilder;
        }
        public GetNodeForLocationParameter() {}
        public GetNodeForLocationParameter(
            @JsonProperty("x")Integer x,
            @JsonProperty("y")Integer y,
            @Nullable @JsonProperty("includeUserAgentShadowDOM")Boolean includeUserAgentShadowDOM
        ) {
            this();
            this.x = x;
            this.y = y;
            this.includeUserAgentShadowDOM = includeUserAgentShadowDOM;
        }
        public CompletableFuture<GetNodeForLocationResult> call() {
            return super.call("DOM.getNodeForLocation", GetNodeForLocationResult.class,
                (code, msg)->new GetNodeForLocationResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetNodeForLocationResult> call(Executor exec) {
            return super.call("DOM.getNodeForLocation", GetNodeForLocationResult.class,
                (code, msg)->new GetNodeForLocationResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of getNodeForLocation.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetNodeForLocationResult extends ResultBase {
        /**Id of the node at given coordinates.*/
        private final NodeId nodeId;
        public final NodeId nodeId() { return nodeId; }
        public final NodeId getNodeId() { return nodeId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            nodeId.toJson(strBuilder.append("\"nodeId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public GetNodeForLocationResult(
            @JsonProperty("nodeId")NodeId nodeId
        ) {
            this.nodeId = nodeId;
        }
        public GetNodeForLocationResult(ResultBase.FailedResult e) {
            super(e);
            nodeId = null;
        }
    }
    /**Returns node's HTML markup.*/
    public GetOuterHTMLParameter getOuterHTML() { final GetOuterHTMLParameter v = new GetOuterHTMLParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of getOuterHTML.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetOuterHTMLParameter extends CommandBase {
        /**Identifier of the node.
        <em>Optional.</em>*/
        private NodeId nodeId;
        /**Identifier of the backend node.
        <em>Optional.</em>*/
        private BackendNodeId backendNodeId;
        /**JavaScript object id of the node wrapper.
        <em>Optional.</em>*/
        private Runtime.RemoteObjectId objectId;
        public final GetOuterHTMLParameter nodeId(@Nullable NodeId nodeId) { this.nodeId = nodeId; return this; }
        public final GetOuterHTMLParameter optNodeId(@Nullable NodeId nodeId) { return nodeId(nodeId); }
        public final NodeId nodeId() { return nodeId; }
        public final NodeId getNodeId() { return nodeId(); }
        public final GetOuterHTMLParameter backendNodeId(@Nullable BackendNodeId backendNodeId) { this.backendNodeId = backendNodeId; return this; }
        public final GetOuterHTMLParameter optBackendNodeId(@Nullable BackendNodeId backendNodeId) { return backendNodeId(backendNodeId); }
        public final BackendNodeId backendNodeId() { return backendNodeId; }
        public final BackendNodeId getBackendNodeId() { return backendNodeId(); }
        public final GetOuterHTMLParameter objectId(@Nullable Runtime.RemoteObjectId objectId) { this.objectId = objectId; return this; }
        public final GetOuterHTMLParameter optObjectId(@Nullable Runtime.RemoteObjectId objectId) { return objectId(objectId); }
        public final Runtime.RemoteObjectId objectId() { return objectId; }
        public final Runtime.RemoteObjectId getObjectId() { return objectId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            if (nodeId != null) nodeId.toJson(strBuilder.append("\"nodeId\":"));
            if (backendNodeId != null) backendNodeId.toJson(strBuilder.append(",\"backendNodeId\":"));
            if (objectId != null) objectId.toJson(strBuilder.append(",\"objectId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public GetOuterHTMLParameter() {}
        public GetOuterHTMLParameter(
            @Nullable @JsonProperty("nodeId")NodeId nodeId,
            @Nullable @JsonProperty("backendNodeId")BackendNodeId backendNodeId,
            @Nullable @JsonProperty("objectId")Runtime.RemoteObjectId objectId
        ) {
            this();
            this.nodeId = nodeId;
            this.backendNodeId = backendNodeId;
            this.objectId = objectId;
        }
        public CompletableFuture<GetOuterHTMLResult> call() {
            return super.call("DOM.getOuterHTML", GetOuterHTMLResult.class,
                (code, msg)->new GetOuterHTMLResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetOuterHTMLResult> call(Executor exec) {
            return super.call("DOM.getOuterHTML", GetOuterHTMLResult.class,
                (code, msg)->new GetOuterHTMLResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of getOuterHTML.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetOuterHTMLResult extends ResultBase {
        /**Outer HTML markup.*/
        private final String outerHTML;
        public final String outerHTML() { return outerHTML; }
        public final String getOuterHTML() { return outerHTML(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"outerHTML\":").append('"').append(DomainBase.escapeQuote(outerHTML)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public GetOuterHTMLResult(
            @JsonProperty("outerHTML")String outerHTML
        ) {
            this.outerHTML = outerHTML;
        }
        public GetOuterHTMLResult(ResultBase.FailedResult e) {
            super(e);
            outerHTML = null;
        }
    }
    /**Returns the id of the nearest ancestor that is a relayout boundary.
    <p><strong>Experimental.</strong></p>*/
    public GetRelayoutBoundaryParameter getRelayoutBoundary() { final GetRelayoutBoundaryParameter v = new GetRelayoutBoundaryParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of getRelayoutBoundary.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetRelayoutBoundaryParameter extends CommandBase {
        /**Id of the node.*/
        private NodeId nodeId;
        public final GetRelayoutBoundaryParameter nodeId(NodeId nodeId) { this.nodeId = nodeId; return this; }
        public final GetRelayoutBoundaryParameter setNodeId(NodeId nodeId) { return nodeId(nodeId); }
        public final NodeId nodeId() { return nodeId; }
        public final NodeId getNodeId() { return nodeId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (nodeId == null) throw new IllegalArgumentException("DOM.GetRelayoutBoundaryParameter.nodeId is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            nodeId.toJson(strBuilder.append("\"nodeId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public GetRelayoutBoundaryParameter() {}
        public GetRelayoutBoundaryParameter(
            @JsonProperty("nodeId")NodeId nodeId
        ) {
            this();
            this.nodeId = nodeId;
        }
        public CompletableFuture<GetRelayoutBoundaryResult> call() {
            return super.call("DOM.getRelayoutBoundary", GetRelayoutBoundaryResult.class,
                (code, msg)->new GetRelayoutBoundaryResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetRelayoutBoundaryResult> call(Executor exec) {
            return super.call("DOM.getRelayoutBoundary", GetRelayoutBoundaryResult.class,
                (code, msg)->new GetRelayoutBoundaryResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of getRelayoutBoundary.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetRelayoutBoundaryResult extends ResultBase {
        /**Relayout boundary node id for the given node.*/
        private final NodeId nodeId;
        public final NodeId nodeId() { return nodeId; }
        public final NodeId getNodeId() { return nodeId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            nodeId.toJson(strBuilder.append("\"nodeId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public GetRelayoutBoundaryResult(
            @JsonProperty("nodeId")NodeId nodeId
        ) {
            this.nodeId = nodeId;
        }
        public GetRelayoutBoundaryResult(ResultBase.FailedResult e) {
            super(e);
            nodeId = null;
        }
    }
    /**Returns search results from given `fromIndex` to given `toIndex` from the search with the given
identifier.
    <p><strong>Experimental.</strong></p>*/
    public GetSearchResultsParameter getSearchResults() { final GetSearchResultsParameter v = new GetSearchResultsParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of getSearchResults.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetSearchResultsParameter extends CommandBase {
        /**Unique search session identifier.*/
        private String searchId;
        /**Start index of the search result to be returned.*/
        private Integer fromIndex;
        /**End index of the search result to be returned.*/
        private Integer toIndex;
        public final GetSearchResultsParameter searchId(String searchId) { this.searchId = searchId; return this; }
        public final GetSearchResultsParameter setSearchId(String searchId) { return searchId(searchId); }
        public final String searchId() { return searchId; }
        public final String getSearchId() { return searchId(); }
        public final GetSearchResultsParameter fromIndex(Integer fromIndex) { this.fromIndex = fromIndex; return this; }
        public final GetSearchResultsParameter setFromIndex(Integer fromIndex) { return fromIndex(fromIndex); }
        public final Integer fromIndex() { return fromIndex; }
        public final Integer getFromIndex() { return fromIndex(); }
        public final GetSearchResultsParameter toIndex(Integer toIndex) { this.toIndex = toIndex; return this; }
        public final GetSearchResultsParameter setToIndex(Integer toIndex) { return toIndex(toIndex); }
        public final Integer toIndex() { return toIndex; }
        public final Integer getToIndex() { return toIndex(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (searchId == null) throw new IllegalArgumentException("DOM.GetSearchResultsParameter.searchId is necessary field.");
            if (fromIndex == null) throw new IllegalArgumentException("DOM.GetSearchResultsParameter.fromIndex is necessary field.");
            if (toIndex == null) throw new IllegalArgumentException("DOM.GetSearchResultsParameter.toIndex is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"searchId\":").append('"').append(DomainBase.escapeQuote(searchId)).append('"');
            strBuilder.append(",\"fromIndex\":").append(fromIndex);
            strBuilder.append(",\"toIndex\":").append(toIndex);
            strBuilder.append('}');
            return strBuilder;
        }
        public GetSearchResultsParameter() {}
        public GetSearchResultsParameter(
            @JsonProperty("searchId")String searchId,
            @JsonProperty("fromIndex")Integer fromIndex,
            @JsonProperty("toIndex")Integer toIndex
        ) {
            this();
            this.searchId = searchId;
            this.fromIndex = fromIndex;
            this.toIndex = toIndex;
        }
        public CompletableFuture<GetSearchResultsResult> call() {
            return super.call("DOM.getSearchResults", GetSearchResultsResult.class,
                (code, msg)->new GetSearchResultsResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetSearchResultsResult> call(Executor exec) {
            return super.call("DOM.getSearchResults", GetSearchResultsResult.class,
                (code, msg)->new GetSearchResultsResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of getSearchResults.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetSearchResultsResult extends ResultBase {
        /**Ids of the search result nodes.*/
        private final List<NodeId> nodeIds;
        public final List<NodeId> nodeIds() { return nodeIds; }
        public final List<NodeId> getNodeIds() { return nodeIds(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
                        strBuilder.append("\"nodeIds\":[");
            nodeIds.get(0).toJson(strBuilder);
            for (int i = 1; i < nodeIds.size(); ++i)
                nodeIds.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public GetSearchResultsResult(
            @JsonProperty("nodeIds")List<NodeId> nodeIds
        ) {
            this.nodeIds = nodeIds;
        }
        public GetSearchResultsResult(ResultBase.FailedResult e) {
            super(e);
            nodeIds = null;
        }
    }
    /**Hides any highlight.*/
    public HideHighlightParameter hideHighlight() { final HideHighlightParameter v = new HideHighlightParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of hideHighlight.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class HideHighlightParameter extends CommandBase {
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
        public HideHighlightParameter() {}
        public CompletableFuture<HideHighlightResult> call() {
            return super.call("DOM.hideHighlight", HideHighlightResult.class,
                (code, msg)->new HideHighlightResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<HideHighlightResult> call(Executor exec) {
            return super.call("DOM.hideHighlight", HideHighlightResult.class,
                (code, msg)->new HideHighlightResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of hideHighlight.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class HideHighlightResult extends ResultBase {
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
        public HideHighlightResult() { super(); }
        public HideHighlightResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Highlights DOM node.*/
    public HighlightNodeParameter highlightNode() { final HighlightNodeParameter v = new HighlightNodeParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of highlightNode.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class HighlightNodeParameter extends CommandBase {
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
        public HighlightNodeParameter() {}
        public CompletableFuture<HighlightNodeResult> call() {
            return super.call("DOM.highlightNode", HighlightNodeResult.class,
                (code, msg)->new HighlightNodeResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<HighlightNodeResult> call(Executor exec) {
            return super.call("DOM.highlightNode", HighlightNodeResult.class,
                (code, msg)->new HighlightNodeResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of highlightNode.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class HighlightNodeResult extends ResultBase {
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
        public HighlightNodeResult() { super(); }
        public HighlightNodeResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Highlights given rectangle.*/
    public HighlightRectParameter highlightRect() { final HighlightRectParameter v = new HighlightRectParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of highlightRect.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class HighlightRectParameter extends CommandBase {
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
        public HighlightRectParameter() {}
        public CompletableFuture<HighlightRectResult> call() {
            return super.call("DOM.highlightRect", HighlightRectResult.class,
                (code, msg)->new HighlightRectResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<HighlightRectResult> call(Executor exec) {
            return super.call("DOM.highlightRect", HighlightRectResult.class,
                (code, msg)->new HighlightRectResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of highlightRect.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class HighlightRectResult extends ResultBase {
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
        public HighlightRectResult() { super(); }
        public HighlightRectResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Marks last undoable state.
    <p><strong>Experimental.</strong></p>*/
    public MarkUndoableStateParameter markUndoableState() { final MarkUndoableStateParameter v = new MarkUndoableStateParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of markUndoableState.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class MarkUndoableStateParameter extends CommandBase {
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
        public MarkUndoableStateParameter() {}
        public CompletableFuture<MarkUndoableStateResult> call() {
            return super.call("DOM.markUndoableState", MarkUndoableStateResult.class,
                (code, msg)->new MarkUndoableStateResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<MarkUndoableStateResult> call(Executor exec) {
            return super.call("DOM.markUndoableState", MarkUndoableStateResult.class,
                (code, msg)->new MarkUndoableStateResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of markUndoableState.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class MarkUndoableStateResult extends ResultBase {
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
        public MarkUndoableStateResult() { super(); }
        public MarkUndoableStateResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Moves node into the new container, places it before the given anchor.*/
    public MoveToParameter moveTo() { final MoveToParameter v = new MoveToParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of moveTo.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class MoveToParameter extends CommandBase {
        /**Id of the node to move.*/
        private NodeId nodeId;
        /**Id of the element to drop the moved node into.*/
        private NodeId targetNodeId;
        /**Drop node before this one (if absent, the moved node becomes the last child of
`targetNodeId`).
        <em>Optional.</em>*/
        private NodeId insertBeforeNodeId;
        public final MoveToParameter nodeId(NodeId nodeId) { this.nodeId = nodeId; return this; }
        public final MoveToParameter setNodeId(NodeId nodeId) { return nodeId(nodeId); }
        public final NodeId nodeId() { return nodeId; }
        public final NodeId getNodeId() { return nodeId(); }
        public final MoveToParameter targetNodeId(NodeId targetNodeId) { this.targetNodeId = targetNodeId; return this; }
        public final MoveToParameter setTargetNodeId(NodeId targetNodeId) { return targetNodeId(targetNodeId); }
        public final NodeId targetNodeId() { return targetNodeId; }
        public final NodeId getTargetNodeId() { return targetNodeId(); }
        public final MoveToParameter insertBeforeNodeId(@Nullable NodeId insertBeforeNodeId) { this.insertBeforeNodeId = insertBeforeNodeId; return this; }
        public final MoveToParameter optInsertBeforeNodeId(@Nullable NodeId insertBeforeNodeId) { return insertBeforeNodeId(insertBeforeNodeId); }
        public final NodeId insertBeforeNodeId() { return insertBeforeNodeId; }
        public final NodeId getInsertBeforeNodeId() { return insertBeforeNodeId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (nodeId == null) throw new IllegalArgumentException("DOM.MoveToParameter.nodeId is necessary field.");
            if (targetNodeId == null) throw new IllegalArgumentException("DOM.MoveToParameter.targetNodeId is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            nodeId.toJson(strBuilder.append("\"nodeId\":"));
            targetNodeId.toJson(strBuilder.append(",\"targetNodeId\":"));
            if (insertBeforeNodeId != null) insertBeforeNodeId.toJson(strBuilder.append(",\"insertBeforeNodeId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public MoveToParameter() {}
        public MoveToParameter(
            @JsonProperty("nodeId")NodeId nodeId,
            @JsonProperty("targetNodeId")NodeId targetNodeId,
            @Nullable @JsonProperty("insertBeforeNodeId")NodeId insertBeforeNodeId
        ) {
            this();
            this.nodeId = nodeId;
            this.targetNodeId = targetNodeId;
            this.insertBeforeNodeId = insertBeforeNodeId;
        }
        public CompletableFuture<MoveToResult> call() {
            return super.call("DOM.moveTo", MoveToResult.class,
                (code, msg)->new MoveToResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<MoveToResult> call(Executor exec) {
            return super.call("DOM.moveTo", MoveToResult.class,
                (code, msg)->new MoveToResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of moveTo.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class MoveToResult extends ResultBase {
        /**New id of the moved node.*/
        private final NodeId nodeId;
        public final NodeId nodeId() { return nodeId; }
        public final NodeId getNodeId() { return nodeId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            nodeId.toJson(strBuilder.append("\"nodeId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public MoveToResult(
            @JsonProperty("nodeId")NodeId nodeId
        ) {
            this.nodeId = nodeId;
        }
        public MoveToResult(ResultBase.FailedResult e) {
            super(e);
            nodeId = null;
        }
    }
    /**Searches for a given string in the DOM tree. Use `getSearchResults` to access search results or
`cancelSearch` to end this search session.
    <p><strong>Experimental.</strong></p>*/
    public PerformSearchParameter performSearch() { final PerformSearchParameter v = new PerformSearchParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of performSearch.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class PerformSearchParameter extends CommandBase {
        /**Plain text or query selector or XPath search query.*/
        private String query;
        /**True to search in user agent shadow DOM.
        <em>Optional.</em>*/
        private Boolean includeUserAgentShadowDOM;
        public final PerformSearchParameter query(String query) { this.query = query; return this; }
        public final PerformSearchParameter setQuery(String query) { return query(query); }
        public final String query() { return query; }
        public final String getQuery() { return query(); }
        public final PerformSearchParameter includeUserAgentShadowDOM(@Nullable Boolean includeUserAgentShadowDOM) { this.includeUserAgentShadowDOM = includeUserAgentShadowDOM; return this; }
        public final PerformSearchParameter optIncludeUserAgentShadowDOM(@Nullable Boolean includeUserAgentShadowDOM) { return includeUserAgentShadowDOM(includeUserAgentShadowDOM); }
        public final Boolean includeUserAgentShadowDOM() { return includeUserAgentShadowDOM; }
        public final Boolean getIncludeUserAgentShadowDOM() { return includeUserAgentShadowDOM(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (query == null) throw new IllegalArgumentException("DOM.PerformSearchParameter.query is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"query\":").append('"').append(DomainBase.escapeQuote(query)).append('"');
            if (includeUserAgentShadowDOM != null) strBuilder.append(",\"includeUserAgentShadowDOM\":").append(includeUserAgentShadowDOM);
            strBuilder.append('}');
            return strBuilder;
        }
        public PerformSearchParameter() {}
        public PerformSearchParameter(
            @JsonProperty("query")String query,
            @Nullable @JsonProperty("includeUserAgentShadowDOM")Boolean includeUserAgentShadowDOM
        ) {
            this();
            this.query = query;
            this.includeUserAgentShadowDOM = includeUserAgentShadowDOM;
        }
        public CompletableFuture<PerformSearchResult> call() {
            return super.call("DOM.performSearch", PerformSearchResult.class,
                (code, msg)->new PerformSearchResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<PerformSearchResult> call(Executor exec) {
            return super.call("DOM.performSearch", PerformSearchResult.class,
                (code, msg)->new PerformSearchResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of performSearch.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class PerformSearchResult extends ResultBase {
        /**Unique search session identifier.*/
        private final String searchId;
        /**Number of search results.*/
        private final Integer resultCount;
        public final String searchId() { return searchId; }
        public final String getSearchId() { return searchId(); }
        public final Integer resultCount() { return resultCount; }
        public final Integer getResultCount() { return resultCount(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"searchId\":").append('"').append(DomainBase.escapeQuote(searchId)).append('"');
            strBuilder.append(",\"resultCount\":").append(resultCount);
            strBuilder.append('}');
            return strBuilder;
        }
        public PerformSearchResult(
            @JsonProperty("searchId")String searchId,
            @JsonProperty("resultCount")Integer resultCount
        ) {
            this.searchId = searchId;
            this.resultCount = resultCount;
        }
        public PerformSearchResult(ResultBase.FailedResult e) {
            super(e);
            searchId = null;
            resultCount = null;
        }
    }
    /**Requests that the node is sent to the caller given its path. // FIXME, use XPath
    <p><strong>Experimental.</strong></p>*/
    public PushNodeByPathToFrontendParameter pushNodeByPathToFrontend() { final PushNodeByPathToFrontendParameter v = new PushNodeByPathToFrontendParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of pushNodeByPathToFrontend.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class PushNodeByPathToFrontendParameter extends CommandBase {
        /**Path to node in the proprietary format.*/
        private String path;
        public final PushNodeByPathToFrontendParameter path(String path) { this.path = path; return this; }
        public final PushNodeByPathToFrontendParameter setPath(String path) { return path(path); }
        public final String path() { return path; }
        public final String getPath() { return path(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (path == null) throw new IllegalArgumentException("DOM.PushNodeByPathToFrontendParameter.path is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"path\":").append('"').append(DomainBase.escapeQuote(path)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public PushNodeByPathToFrontendParameter() {}
        public PushNodeByPathToFrontendParameter(
            @JsonProperty("path")String path
        ) {
            this();
            this.path = path;
        }
        public CompletableFuture<PushNodeByPathToFrontendResult> call() {
            return super.call("DOM.pushNodeByPathToFrontend", PushNodeByPathToFrontendResult.class,
                (code, msg)->new PushNodeByPathToFrontendResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<PushNodeByPathToFrontendResult> call(Executor exec) {
            return super.call("DOM.pushNodeByPathToFrontend", PushNodeByPathToFrontendResult.class,
                (code, msg)->new PushNodeByPathToFrontendResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of pushNodeByPathToFrontend.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class PushNodeByPathToFrontendResult extends ResultBase {
        /**Id of the node for given path.*/
        private final NodeId nodeId;
        public final NodeId nodeId() { return nodeId; }
        public final NodeId getNodeId() { return nodeId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            nodeId.toJson(strBuilder.append("\"nodeId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public PushNodeByPathToFrontendResult(
            @JsonProperty("nodeId")NodeId nodeId
        ) {
            this.nodeId = nodeId;
        }
        public PushNodeByPathToFrontendResult(ResultBase.FailedResult e) {
            super(e);
            nodeId = null;
        }
    }
    /**Requests that a batch of nodes is sent to the caller given their backend node ids.
    <p><strong>Experimental.</strong></p>*/
    public PushNodesByBackendIdsToFrontendParameter pushNodesByBackendIdsToFrontend() { final PushNodesByBackendIdsToFrontendParameter v = new PushNodesByBackendIdsToFrontendParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of pushNodesByBackendIdsToFrontend.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class PushNodesByBackendIdsToFrontendParameter extends CommandBase {
        /**The array of backend node ids.*/
        private List<BackendNodeId> backendNodeIds;
        public final PushNodesByBackendIdsToFrontendParameter backendNodeIds(List<BackendNodeId> backendNodeIds) { this.backendNodeIds = backendNodeIds; return this; }
        public final PushNodesByBackendIdsToFrontendParameter setBackendNodeIds(List<BackendNodeId> backendNodeIds) { return backendNodeIds(backendNodeIds); }
        public final List<BackendNodeId> backendNodeIds() { return backendNodeIds; }
        public final List<BackendNodeId> getBackendNodeIds() { return backendNodeIds(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (backendNodeIds == null) throw new IllegalArgumentException("DOM.PushNodesByBackendIdsToFrontendParameter.backendNodeIds is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
                        strBuilder.append("\"backendNodeIds\":[");
            backendNodeIds.get(0).toJson(strBuilder);
            for (int i = 1; i < backendNodeIds.size(); ++i)
                backendNodeIds.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public PushNodesByBackendIdsToFrontendParameter() {}
        public PushNodesByBackendIdsToFrontendParameter(
            @JsonProperty("backendNodeIds")List<BackendNodeId> backendNodeIds
        ) {
            this();
            this.backendNodeIds = backendNodeIds;
        }
        public CompletableFuture<PushNodesByBackendIdsToFrontendResult> call() {
            return super.call("DOM.pushNodesByBackendIdsToFrontend", PushNodesByBackendIdsToFrontendResult.class,
                (code, msg)->new PushNodesByBackendIdsToFrontendResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<PushNodesByBackendIdsToFrontendResult> call(Executor exec) {
            return super.call("DOM.pushNodesByBackendIdsToFrontend", PushNodesByBackendIdsToFrontendResult.class,
                (code, msg)->new PushNodesByBackendIdsToFrontendResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of pushNodesByBackendIdsToFrontend.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class PushNodesByBackendIdsToFrontendResult extends ResultBase {
        /**The array of ids of pushed nodes that correspond to the backend ids specified in
backendNodeIds.*/
        private final List<NodeId> nodeIds;
        public final List<NodeId> nodeIds() { return nodeIds; }
        public final List<NodeId> getNodeIds() { return nodeIds(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
                        strBuilder.append("\"nodeIds\":[");
            nodeIds.get(0).toJson(strBuilder);
            for (int i = 1; i < nodeIds.size(); ++i)
                nodeIds.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public PushNodesByBackendIdsToFrontendResult(
            @JsonProperty("nodeIds")List<NodeId> nodeIds
        ) {
            this.nodeIds = nodeIds;
        }
        public PushNodesByBackendIdsToFrontendResult(ResultBase.FailedResult e) {
            super(e);
            nodeIds = null;
        }
    }
    /**Executes `querySelector` on a given node.*/
    public QuerySelectorParameter querySelector() { final QuerySelectorParameter v = new QuerySelectorParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of querySelector.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class QuerySelectorParameter extends CommandBase {
        /**Id of the node to query upon.*/
        private NodeId nodeId;
        /**Selector string.*/
        private String selector;
        public final QuerySelectorParameter nodeId(NodeId nodeId) { this.nodeId = nodeId; return this; }
        public final QuerySelectorParameter setNodeId(NodeId nodeId) { return nodeId(nodeId); }
        public final NodeId nodeId() { return nodeId; }
        public final NodeId getNodeId() { return nodeId(); }
        public final QuerySelectorParameter selector(String selector) { this.selector = selector; return this; }
        public final QuerySelectorParameter setSelector(String selector) { return selector(selector); }
        public final String selector() { return selector; }
        public final String getSelector() { return selector(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (nodeId == null) throw new IllegalArgumentException("DOM.QuerySelectorParameter.nodeId is necessary field.");
            if (selector == null) throw new IllegalArgumentException("DOM.QuerySelectorParameter.selector is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            nodeId.toJson(strBuilder.append("\"nodeId\":"));
            strBuilder.append(",\"selector\":").append('"').append(DomainBase.escapeQuote(selector)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public QuerySelectorParameter() {}
        public QuerySelectorParameter(
            @JsonProperty("nodeId")NodeId nodeId,
            @JsonProperty("selector")String selector
        ) {
            this();
            this.nodeId = nodeId;
            this.selector = selector;
        }
        public CompletableFuture<QuerySelectorResult> call() {
            return super.call("DOM.querySelector", QuerySelectorResult.class,
                (code, msg)->new QuerySelectorResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<QuerySelectorResult> call(Executor exec) {
            return super.call("DOM.querySelector", QuerySelectorResult.class,
                (code, msg)->new QuerySelectorResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of querySelector.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class QuerySelectorResult extends ResultBase {
        /**Query selector result.*/
        private final NodeId nodeId;
        public final NodeId nodeId() { return nodeId; }
        public final NodeId getNodeId() { return nodeId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            nodeId.toJson(strBuilder.append("\"nodeId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public QuerySelectorResult(
            @JsonProperty("nodeId")NodeId nodeId
        ) {
            this.nodeId = nodeId;
        }
        public QuerySelectorResult(ResultBase.FailedResult e) {
            super(e);
            nodeId = null;
        }
    }
    /**Executes `querySelectorAll` on a given node.*/
    public QuerySelectorAllParameter querySelectorAll() { final QuerySelectorAllParameter v = new QuerySelectorAllParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of querySelectorAll.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class QuerySelectorAllParameter extends CommandBase {
        /**Id of the node to query upon.*/
        private NodeId nodeId;
        /**Selector string.*/
        private String selector;
        public final QuerySelectorAllParameter nodeId(NodeId nodeId) { this.nodeId = nodeId; return this; }
        public final QuerySelectorAllParameter setNodeId(NodeId nodeId) { return nodeId(nodeId); }
        public final NodeId nodeId() { return nodeId; }
        public final NodeId getNodeId() { return nodeId(); }
        public final QuerySelectorAllParameter selector(String selector) { this.selector = selector; return this; }
        public final QuerySelectorAllParameter setSelector(String selector) { return selector(selector); }
        public final String selector() { return selector; }
        public final String getSelector() { return selector(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (nodeId == null) throw new IllegalArgumentException("DOM.QuerySelectorAllParameter.nodeId is necessary field.");
            if (selector == null) throw new IllegalArgumentException("DOM.QuerySelectorAllParameter.selector is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            nodeId.toJson(strBuilder.append("\"nodeId\":"));
            strBuilder.append(",\"selector\":").append('"').append(DomainBase.escapeQuote(selector)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public QuerySelectorAllParameter() {}
        public QuerySelectorAllParameter(
            @JsonProperty("nodeId")NodeId nodeId,
            @JsonProperty("selector")String selector
        ) {
            this();
            this.nodeId = nodeId;
            this.selector = selector;
        }
        public CompletableFuture<QuerySelectorAllResult> call() {
            return super.call("DOM.querySelectorAll", QuerySelectorAllResult.class,
                (code, msg)->new QuerySelectorAllResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<QuerySelectorAllResult> call(Executor exec) {
            return super.call("DOM.querySelectorAll", QuerySelectorAllResult.class,
                (code, msg)->new QuerySelectorAllResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of querySelectorAll.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class QuerySelectorAllResult extends ResultBase {
        /**Query selector result.*/
        private final List<NodeId> nodeIds;
        public final List<NodeId> nodeIds() { return nodeIds; }
        public final List<NodeId> getNodeIds() { return nodeIds(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
                        strBuilder.append("\"nodeIds\":[");
            nodeIds.get(0).toJson(strBuilder);
            for (int i = 1; i < nodeIds.size(); ++i)
                nodeIds.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public QuerySelectorAllResult(
            @JsonProperty("nodeIds")List<NodeId> nodeIds
        ) {
            this.nodeIds = nodeIds;
        }
        public QuerySelectorAllResult(ResultBase.FailedResult e) {
            super(e);
            nodeIds = null;
        }
    }
    /**Re-does the last undone action.
    <p><strong>Experimental.</strong></p>*/
    public RedoParameter redo() { final RedoParameter v = new RedoParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of redo.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class RedoParameter extends CommandBase {
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
        public RedoParameter() {}
        public CompletableFuture<RedoResult> call() {
            return super.call("DOM.redo", RedoResult.class,
                (code, msg)->new RedoResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<RedoResult> call(Executor exec) {
            return super.call("DOM.redo", RedoResult.class,
                (code, msg)->new RedoResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of redo.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class RedoResult extends ResultBase {
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
        public RedoResult() { super(); }
        public RedoResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Removes attribute with given name from an element with given id.*/
    public RemoveAttributeParameter removeAttribute() { final RemoveAttributeParameter v = new RemoveAttributeParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of removeAttribute.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class RemoveAttributeParameter extends CommandBase {
        /**Id of the element to remove attribute from.*/
        private NodeId nodeId;
        /**Name of the attribute to remove.*/
        private String name;
        public final RemoveAttributeParameter nodeId(NodeId nodeId) { this.nodeId = nodeId; return this; }
        public final RemoveAttributeParameter setNodeId(NodeId nodeId) { return nodeId(nodeId); }
        public final NodeId nodeId() { return nodeId; }
        public final NodeId getNodeId() { return nodeId(); }
        public final RemoveAttributeParameter name(String name) { this.name = name; return this; }
        public final RemoveAttributeParameter setName(String name) { return name(name); }
        public final String name() { return name; }
        public final String getName() { return name(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (nodeId == null) throw new IllegalArgumentException("DOM.RemoveAttributeParameter.nodeId is necessary field.");
            if (name == null) throw new IllegalArgumentException("DOM.RemoveAttributeParameter.name is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            nodeId.toJson(strBuilder.append("\"nodeId\":"));
            strBuilder.append(",\"name\":").append('"').append(DomainBase.escapeQuote(name)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public RemoveAttributeParameter() {}
        public RemoveAttributeParameter(
            @JsonProperty("nodeId")NodeId nodeId,
            @JsonProperty("name")String name
        ) {
            this();
            this.nodeId = nodeId;
            this.name = name;
        }
        public CompletableFuture<RemoveAttributeResult> call() {
            return super.call("DOM.removeAttribute", RemoveAttributeResult.class,
                (code, msg)->new RemoveAttributeResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<RemoveAttributeResult> call(Executor exec) {
            return super.call("DOM.removeAttribute", RemoveAttributeResult.class,
                (code, msg)->new RemoveAttributeResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of removeAttribute.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class RemoveAttributeResult extends ResultBase {
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
        public RemoveAttributeResult() { super(); }
        public RemoveAttributeResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Removes node with given id.*/
    public RemoveNodeParameter removeNode() { final RemoveNodeParameter v = new RemoveNodeParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of removeNode.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class RemoveNodeParameter extends CommandBase {
        /**Id of the node to remove.*/
        private NodeId nodeId;
        public final RemoveNodeParameter nodeId(NodeId nodeId) { this.nodeId = nodeId; return this; }
        public final RemoveNodeParameter setNodeId(NodeId nodeId) { return nodeId(nodeId); }
        public final NodeId nodeId() { return nodeId; }
        public final NodeId getNodeId() { return nodeId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (nodeId == null) throw new IllegalArgumentException("DOM.RemoveNodeParameter.nodeId is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            nodeId.toJson(strBuilder.append("\"nodeId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public RemoveNodeParameter() {}
        public RemoveNodeParameter(
            @JsonProperty("nodeId")NodeId nodeId
        ) {
            this();
            this.nodeId = nodeId;
        }
        public CompletableFuture<RemoveNodeResult> call() {
            return super.call("DOM.removeNode", RemoveNodeResult.class,
                (code, msg)->new RemoveNodeResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<RemoveNodeResult> call(Executor exec) {
            return super.call("DOM.removeNode", RemoveNodeResult.class,
                (code, msg)->new RemoveNodeResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of removeNode.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class RemoveNodeResult extends ResultBase {
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
        public RemoveNodeResult() { super(); }
        public RemoveNodeResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Requests that children of the node with given id are returned to the caller in form of
`setChildNodes` events where not only immediate children are retrieved, but all children down to
the specified depth.*/
    public RequestChildNodesParameter requestChildNodes() { final RequestChildNodesParameter v = new RequestChildNodesParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of requestChildNodes.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class RequestChildNodesParameter extends CommandBase {
        /**Id of the node to get children for.*/
        private NodeId nodeId;
        /**The maximum depth at which children should be retrieved, defaults to 1. Use -1 for the
entire subtree or provide an integer larger than 0.
        <em>Optional.</em>*/
        private Integer depth;
        /**Whether or not iframes and shadow roots should be traversed when returning the sub-tree
(default is false).
        <em>Optional.</em>*/
        private Boolean pierce;
        public final RequestChildNodesParameter nodeId(NodeId nodeId) { this.nodeId = nodeId; return this; }
        public final RequestChildNodesParameter setNodeId(NodeId nodeId) { return nodeId(nodeId); }
        public final NodeId nodeId() { return nodeId; }
        public final NodeId getNodeId() { return nodeId(); }
        public final RequestChildNodesParameter depth(@Nullable Integer depth) { this.depth = depth; return this; }
        public final RequestChildNodesParameter optDepth(@Nullable Integer depth) { return depth(depth); }
        public final Integer depth() { return depth; }
        public final Integer getDepth() { return depth(); }
        public final RequestChildNodesParameter pierce(@Nullable Boolean pierce) { this.pierce = pierce; return this; }
        public final RequestChildNodesParameter optPierce(@Nullable Boolean pierce) { return pierce(pierce); }
        public final Boolean pierce() { return pierce; }
        public final Boolean getPierce() { return pierce(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (nodeId == null) throw new IllegalArgumentException("DOM.RequestChildNodesParameter.nodeId is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            nodeId.toJson(strBuilder.append("\"nodeId\":"));
            if (depth != null) strBuilder.append(",\"depth\":").append(depth);
            if (pierce != null) strBuilder.append(",\"pierce\":").append(pierce);
            strBuilder.append('}');
            return strBuilder;
        }
        public RequestChildNodesParameter() {}
        public RequestChildNodesParameter(
            @JsonProperty("nodeId")NodeId nodeId,
            @Nullable @JsonProperty("depth")Integer depth,
            @Nullable @JsonProperty("pierce")Boolean pierce
        ) {
            this();
            this.nodeId = nodeId;
            this.depth = depth;
            this.pierce = pierce;
        }
        public CompletableFuture<RequestChildNodesResult> call() {
            return super.call("DOM.requestChildNodes", RequestChildNodesResult.class,
                (code, msg)->new RequestChildNodesResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<RequestChildNodesResult> call(Executor exec) {
            return super.call("DOM.requestChildNodes", RequestChildNodesResult.class,
                (code, msg)->new RequestChildNodesResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of requestChildNodes.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class RequestChildNodesResult extends ResultBase {
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
        public RequestChildNodesResult() { super(); }
        public RequestChildNodesResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Requests that the node is sent to the caller given the JavaScript node object reference. All
nodes that form the path from the node to the root are also sent to the client as a series of
`setChildNodes` notifications.*/
    public RequestNodeParameter requestNode() { final RequestNodeParameter v = new RequestNodeParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of requestNode.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class RequestNodeParameter extends CommandBase {
        /**JavaScript object id to convert into node.*/
        private Runtime.RemoteObjectId objectId;
        public final RequestNodeParameter objectId(Runtime.RemoteObjectId objectId) { this.objectId = objectId; return this; }
        public final RequestNodeParameter setObjectId(Runtime.RemoteObjectId objectId) { return objectId(objectId); }
        public final Runtime.RemoteObjectId objectId() { return objectId; }
        public final Runtime.RemoteObjectId getObjectId() { return objectId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (objectId == null) throw new IllegalArgumentException("DOM.RequestNodeParameter.objectId is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            objectId.toJson(strBuilder.append("\"objectId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public RequestNodeParameter() {}
        public RequestNodeParameter(
            @JsonProperty("objectId")Runtime.RemoteObjectId objectId
        ) {
            this();
            this.objectId = objectId;
        }
        public CompletableFuture<RequestNodeResult> call() {
            return super.call("DOM.requestNode", RequestNodeResult.class,
                (code, msg)->new RequestNodeResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<RequestNodeResult> call(Executor exec) {
            return super.call("DOM.requestNode", RequestNodeResult.class,
                (code, msg)->new RequestNodeResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of requestNode.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class RequestNodeResult extends ResultBase {
        /**Node id for given object.*/
        private final NodeId nodeId;
        public final NodeId nodeId() { return nodeId; }
        public final NodeId getNodeId() { return nodeId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            nodeId.toJson(strBuilder.append("\"nodeId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public RequestNodeResult(
            @JsonProperty("nodeId")NodeId nodeId
        ) {
            this.nodeId = nodeId;
        }
        public RequestNodeResult(ResultBase.FailedResult e) {
            super(e);
            nodeId = null;
        }
    }
    /**Resolves the JavaScript node object for a given NodeId or BackendNodeId.*/
    public ResolveNodeParameter resolveNode() { final ResolveNodeParameter v = new ResolveNodeParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of resolveNode.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ResolveNodeParameter extends CommandBase {
        /**Id of the node to resolve.
        <em>Optional.</em>*/
        private NodeId nodeId;
        /**Backend identifier of the node to resolve.
        <em>Optional.</em>*/
        private DOM.BackendNodeId backendNodeId;
        /**Symbolic group name that can be used to release multiple objects.
        <em>Optional.</em>*/
        private String objectGroup;
        public final ResolveNodeParameter nodeId(@Nullable NodeId nodeId) { this.nodeId = nodeId; return this; }
        public final ResolveNodeParameter optNodeId(@Nullable NodeId nodeId) { return nodeId(nodeId); }
        public final NodeId nodeId() { return nodeId; }
        public final NodeId getNodeId() { return nodeId(); }
        public final ResolveNodeParameter backendNodeId(@Nullable DOM.BackendNodeId backendNodeId) { this.backendNodeId = backendNodeId; return this; }
        public final ResolveNodeParameter optBackendNodeId(@Nullable DOM.BackendNodeId backendNodeId) { return backendNodeId(backendNodeId); }
        public final DOM.BackendNodeId backendNodeId() { return backendNodeId; }
        public final DOM.BackendNodeId getBackendNodeId() { return backendNodeId(); }
        public final ResolveNodeParameter objectGroup(@Nullable String objectGroup) { this.objectGroup = objectGroup; return this; }
        public final ResolveNodeParameter optObjectGroup(@Nullable String objectGroup) { return objectGroup(objectGroup); }
        public final String objectGroup() { return objectGroup; }
        public final String getObjectGroup() { return objectGroup(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            if (nodeId != null) nodeId.toJson(strBuilder.append("\"nodeId\":"));
            if (backendNodeId != null) backendNodeId.toJson(strBuilder.append(",\"backendNodeId\":"));
            if (objectGroup != null) strBuilder.append(",\"objectGroup\":").append('"').append(DomainBase.escapeQuote(objectGroup)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public ResolveNodeParameter() {}
        public ResolveNodeParameter(
            @Nullable @JsonProperty("nodeId")NodeId nodeId,
            @Nullable @JsonProperty("backendNodeId")DOM.BackendNodeId backendNodeId,
            @Nullable @JsonProperty("objectGroup")String objectGroup
        ) {
            this();
            this.nodeId = nodeId;
            this.backendNodeId = backendNodeId;
            this.objectGroup = objectGroup;
        }
        public CompletableFuture<ResolveNodeResult> call() {
            return super.call("DOM.resolveNode", ResolveNodeResult.class,
                (code, msg)->new ResolveNodeResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<ResolveNodeResult> call(Executor exec) {
            return super.call("DOM.resolveNode", ResolveNodeResult.class,
                (code, msg)->new ResolveNodeResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of resolveNode.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ResolveNodeResult extends ResultBase {
        /**JavaScript object wrapper for given node.*/
        private final Runtime.RemoteObject object;
        public final Runtime.RemoteObject object() { return object; }
        public final Runtime.RemoteObject getObject() { return object(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            object.toJson(strBuilder.append("\"object\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public ResolveNodeResult(
            @JsonProperty("object")Runtime.RemoteObject object
        ) {
            this.object = object;
        }
        public ResolveNodeResult(ResultBase.FailedResult e) {
            super(e);
            object = null;
        }
    }
    /**Sets attribute for an element with given id.*/
    public SetAttributeValueParameter setAttributeValue() { final SetAttributeValueParameter v = new SetAttributeValueParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setAttributeValue.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetAttributeValueParameter extends CommandBase {
        /**Id of the element to set attribute for.*/
        private NodeId nodeId;
        /**Attribute name.*/
        private String name;
        /**Attribute value.*/
        private String value;
        public final SetAttributeValueParameter nodeId(NodeId nodeId) { this.nodeId = nodeId; return this; }
        public final SetAttributeValueParameter setNodeId(NodeId nodeId) { return nodeId(nodeId); }
        public final NodeId nodeId() { return nodeId; }
        public final NodeId getNodeId() { return nodeId(); }
        public final SetAttributeValueParameter name(String name) { this.name = name; return this; }
        public final SetAttributeValueParameter setName(String name) { return name(name); }
        public final String name() { return name; }
        public final String getName() { return name(); }
        public final SetAttributeValueParameter value(String value) { this.value = value; return this; }
        public final SetAttributeValueParameter setValue(String value) { return value(value); }
        public final String value() { return value; }
        public final String getValue() { return value(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (nodeId == null) throw new IllegalArgumentException("DOM.SetAttributeValueParameter.nodeId is necessary field.");
            if (name == null) throw new IllegalArgumentException("DOM.SetAttributeValueParameter.name is necessary field.");
            if (value == null) throw new IllegalArgumentException("DOM.SetAttributeValueParameter.value is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            nodeId.toJson(strBuilder.append("\"nodeId\":"));
            strBuilder.append(",\"name\":").append('"').append(DomainBase.escapeQuote(name)).append('"');
            strBuilder.append(",\"value\":").append('"').append(DomainBase.escapeQuote(value)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public SetAttributeValueParameter() {}
        public SetAttributeValueParameter(
            @JsonProperty("nodeId")NodeId nodeId,
            @JsonProperty("name")String name,
            @JsonProperty("value")String value
        ) {
            this();
            this.nodeId = nodeId;
            this.name = name;
            this.value = value;
        }
        public CompletableFuture<SetAttributeValueResult> call() {
            return super.call("DOM.setAttributeValue", SetAttributeValueResult.class,
                (code, msg)->new SetAttributeValueResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetAttributeValueResult> call(Executor exec) {
            return super.call("DOM.setAttributeValue", SetAttributeValueResult.class,
                (code, msg)->new SetAttributeValueResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setAttributeValue.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetAttributeValueResult extends ResultBase {
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
        public SetAttributeValueResult() { super(); }
        public SetAttributeValueResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Sets attributes on element with given id. This method is useful when user edits some existing
attribute value and types in several attribute name/value pairs.*/
    public SetAttributesAsTextParameter setAttributesAsText() { final SetAttributesAsTextParameter v = new SetAttributesAsTextParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setAttributesAsText.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetAttributesAsTextParameter extends CommandBase {
        /**Id of the element to set attributes for.*/
        private NodeId nodeId;
        /**Text with a number of attributes. Will parse this text using HTML parser.*/
        private String text;
        /**Attribute name to replace with new attributes derived from text in case text parsed
successfully.
        <em>Optional.</em>*/
        private String name;
        public final SetAttributesAsTextParameter nodeId(NodeId nodeId) { this.nodeId = nodeId; return this; }
        public final SetAttributesAsTextParameter setNodeId(NodeId nodeId) { return nodeId(nodeId); }
        public final NodeId nodeId() { return nodeId; }
        public final NodeId getNodeId() { return nodeId(); }
        public final SetAttributesAsTextParameter text(String text) { this.text = text; return this; }
        public final SetAttributesAsTextParameter setText(String text) { return text(text); }
        public final String text() { return text; }
        public final String getText() { return text(); }
        public final SetAttributesAsTextParameter name(@Nullable String name) { this.name = name; return this; }
        public final SetAttributesAsTextParameter optName(@Nullable String name) { return name(name); }
        public final String name() { return name; }
        public final String getName() { return name(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (nodeId == null) throw new IllegalArgumentException("DOM.SetAttributesAsTextParameter.nodeId is necessary field.");
            if (text == null) throw new IllegalArgumentException("DOM.SetAttributesAsTextParameter.text is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            nodeId.toJson(strBuilder.append("\"nodeId\":"));
            strBuilder.append(",\"text\":").append('"').append(DomainBase.escapeQuote(text)).append('"');
            if (name != null) strBuilder.append(",\"name\":").append('"').append(DomainBase.escapeQuote(name)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public SetAttributesAsTextParameter() {}
        public SetAttributesAsTextParameter(
            @JsonProperty("nodeId")NodeId nodeId,
            @JsonProperty("text")String text,
            @Nullable @JsonProperty("name")String name
        ) {
            this();
            this.nodeId = nodeId;
            this.text = text;
            this.name = name;
        }
        public CompletableFuture<SetAttributesAsTextResult> call() {
            return super.call("DOM.setAttributesAsText", SetAttributesAsTextResult.class,
                (code, msg)->new SetAttributesAsTextResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetAttributesAsTextResult> call(Executor exec) {
            return super.call("DOM.setAttributesAsText", SetAttributesAsTextResult.class,
                (code, msg)->new SetAttributesAsTextResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setAttributesAsText.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetAttributesAsTextResult extends ResultBase {
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
        public SetAttributesAsTextResult() { super(); }
        public SetAttributesAsTextResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Sets files for the given file input element.*/
    public SetFileInputFilesParameter setFileInputFiles() { final SetFileInputFilesParameter v = new SetFileInputFilesParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setFileInputFiles.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetFileInputFilesParameter extends CommandBase {
        /**Array of file paths to set.*/
        private List<String> files;
        /**Identifier of the node.
        <em>Optional.</em>*/
        private NodeId nodeId;
        /**Identifier of the backend node.
        <em>Optional.</em>*/
        private BackendNodeId backendNodeId;
        /**JavaScript object id of the node wrapper.
        <em>Optional.</em>*/
        private Runtime.RemoteObjectId objectId;
        public final SetFileInputFilesParameter files(List<String> files) { this.files = files; return this; }
        public final SetFileInputFilesParameter setFiles(List<String> files) { return files(files); }
        public final List<String> files() { return files; }
        public final List<String> getFiles() { return files(); }
        public final SetFileInputFilesParameter nodeId(@Nullable NodeId nodeId) { this.nodeId = nodeId; return this; }
        public final SetFileInputFilesParameter optNodeId(@Nullable NodeId nodeId) { return nodeId(nodeId); }
        public final NodeId nodeId() { return nodeId; }
        public final NodeId getNodeId() { return nodeId(); }
        public final SetFileInputFilesParameter backendNodeId(@Nullable BackendNodeId backendNodeId) { this.backendNodeId = backendNodeId; return this; }
        public final SetFileInputFilesParameter optBackendNodeId(@Nullable BackendNodeId backendNodeId) { return backendNodeId(backendNodeId); }
        public final BackendNodeId backendNodeId() { return backendNodeId; }
        public final BackendNodeId getBackendNodeId() { return backendNodeId(); }
        public final SetFileInputFilesParameter objectId(@Nullable Runtime.RemoteObjectId objectId) { this.objectId = objectId; return this; }
        public final SetFileInputFilesParameter optObjectId(@Nullable Runtime.RemoteObjectId objectId) { return objectId(objectId); }
        public final Runtime.RemoteObjectId objectId() { return objectId; }
        public final Runtime.RemoteObjectId getObjectId() { return objectId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (files == null) throw new IllegalArgumentException("DOM.SetFileInputFilesParameter.files is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
                        strBuilder.append("\"files\":[");
            strBuilder.append('"').append(DomainBase.escapeQuote(files.get(0))).append('"');
            for (int i = 1; i < files.size(); ++i)
                strBuilder.append(",\"").append(DomainBase.escapeQuote(files.get(i))).append('"');
            strBuilder.append(']');
            if (nodeId != null) nodeId.toJson(strBuilder.append(",\"nodeId\":"));
            if (backendNodeId != null) backendNodeId.toJson(strBuilder.append(",\"backendNodeId\":"));
            if (objectId != null) objectId.toJson(strBuilder.append(",\"objectId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public SetFileInputFilesParameter() {}
        public SetFileInputFilesParameter(
            @JsonProperty("files")List<String> files,
            @Nullable @JsonProperty("nodeId")NodeId nodeId,
            @Nullable @JsonProperty("backendNodeId")BackendNodeId backendNodeId,
            @Nullable @JsonProperty("objectId")Runtime.RemoteObjectId objectId
        ) {
            this();
            this.files = files;
            this.nodeId = nodeId;
            this.backendNodeId = backendNodeId;
            this.objectId = objectId;
        }
        public CompletableFuture<SetFileInputFilesResult> call() {
            return super.call("DOM.setFileInputFiles", SetFileInputFilesResult.class,
                (code, msg)->new SetFileInputFilesResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetFileInputFilesResult> call(Executor exec) {
            return super.call("DOM.setFileInputFiles", SetFileInputFilesResult.class,
                (code, msg)->new SetFileInputFilesResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setFileInputFiles.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetFileInputFilesResult extends ResultBase {
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
        public SetFileInputFilesResult() { super(); }
        public SetFileInputFilesResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Enables console to refer to the node with given id via $x (see Command Line API for more details
$x functions).
    <p><strong>Experimental.</strong></p>*/
    public SetInspectedNodeParameter setInspectedNode() { final SetInspectedNodeParameter v = new SetInspectedNodeParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setInspectedNode.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetInspectedNodeParameter extends CommandBase {
        /**DOM node id to be accessible by means of $x command line API.*/
        private NodeId nodeId;
        public final SetInspectedNodeParameter nodeId(NodeId nodeId) { this.nodeId = nodeId; return this; }
        public final SetInspectedNodeParameter setNodeId(NodeId nodeId) { return nodeId(nodeId); }
        public final NodeId nodeId() { return nodeId; }
        public final NodeId getNodeId() { return nodeId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (nodeId == null) throw new IllegalArgumentException("DOM.SetInspectedNodeParameter.nodeId is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            nodeId.toJson(strBuilder.append("\"nodeId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public SetInspectedNodeParameter() {}
        public SetInspectedNodeParameter(
            @JsonProperty("nodeId")NodeId nodeId
        ) {
            this();
            this.nodeId = nodeId;
        }
        public CompletableFuture<SetInspectedNodeResult> call() {
            return super.call("DOM.setInspectedNode", SetInspectedNodeResult.class,
                (code, msg)->new SetInspectedNodeResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetInspectedNodeResult> call(Executor exec) {
            return super.call("DOM.setInspectedNode", SetInspectedNodeResult.class,
                (code, msg)->new SetInspectedNodeResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setInspectedNode.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetInspectedNodeResult extends ResultBase {
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
        public SetInspectedNodeResult() { super(); }
        public SetInspectedNodeResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Sets node name for a node with given id.*/
    public SetNodeNameParameter setNodeName() { final SetNodeNameParameter v = new SetNodeNameParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setNodeName.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetNodeNameParameter extends CommandBase {
        /**Id of the node to set name for.*/
        private NodeId nodeId;
        /**New node's name.*/
        private String name;
        public final SetNodeNameParameter nodeId(NodeId nodeId) { this.nodeId = nodeId; return this; }
        public final SetNodeNameParameter setNodeId(NodeId nodeId) { return nodeId(nodeId); }
        public final NodeId nodeId() { return nodeId; }
        public final NodeId getNodeId() { return nodeId(); }
        public final SetNodeNameParameter name(String name) { this.name = name; return this; }
        public final SetNodeNameParameter setName(String name) { return name(name); }
        public final String name() { return name; }
        public final String getName() { return name(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (nodeId == null) throw new IllegalArgumentException("DOM.SetNodeNameParameter.nodeId is necessary field.");
            if (name == null) throw new IllegalArgumentException("DOM.SetNodeNameParameter.name is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            nodeId.toJson(strBuilder.append("\"nodeId\":"));
            strBuilder.append(",\"name\":").append('"').append(DomainBase.escapeQuote(name)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public SetNodeNameParameter() {}
        public SetNodeNameParameter(
            @JsonProperty("nodeId")NodeId nodeId,
            @JsonProperty("name")String name
        ) {
            this();
            this.nodeId = nodeId;
            this.name = name;
        }
        public CompletableFuture<SetNodeNameResult> call() {
            return super.call("DOM.setNodeName", SetNodeNameResult.class,
                (code, msg)->new SetNodeNameResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetNodeNameResult> call(Executor exec) {
            return super.call("DOM.setNodeName", SetNodeNameResult.class,
                (code, msg)->new SetNodeNameResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setNodeName.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetNodeNameResult extends ResultBase {
        /**New node's id.*/
        private final NodeId nodeId;
        public final NodeId nodeId() { return nodeId; }
        public final NodeId getNodeId() { return nodeId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            nodeId.toJson(strBuilder.append("\"nodeId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public SetNodeNameResult(
            @JsonProperty("nodeId")NodeId nodeId
        ) {
            this.nodeId = nodeId;
        }
        public SetNodeNameResult(ResultBase.FailedResult e) {
            super(e);
            nodeId = null;
        }
    }
    /**Sets node value for a node with given id.*/
    public SetNodeValueParameter setNodeValue() { final SetNodeValueParameter v = new SetNodeValueParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setNodeValue.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetNodeValueParameter extends CommandBase {
        /**Id of the node to set value for.*/
        private NodeId nodeId;
        /**New node's value.*/
        private String value;
        public final SetNodeValueParameter nodeId(NodeId nodeId) { this.nodeId = nodeId; return this; }
        public final SetNodeValueParameter setNodeId(NodeId nodeId) { return nodeId(nodeId); }
        public final NodeId nodeId() { return nodeId; }
        public final NodeId getNodeId() { return nodeId(); }
        public final SetNodeValueParameter value(String value) { this.value = value; return this; }
        public final SetNodeValueParameter setValue(String value) { return value(value); }
        public final String value() { return value; }
        public final String getValue() { return value(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (nodeId == null) throw new IllegalArgumentException("DOM.SetNodeValueParameter.nodeId is necessary field.");
            if (value == null) throw new IllegalArgumentException("DOM.SetNodeValueParameter.value is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            nodeId.toJson(strBuilder.append("\"nodeId\":"));
            strBuilder.append(",\"value\":").append('"').append(DomainBase.escapeQuote(value)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public SetNodeValueParameter() {}
        public SetNodeValueParameter(
            @JsonProperty("nodeId")NodeId nodeId,
            @JsonProperty("value")String value
        ) {
            this();
            this.nodeId = nodeId;
            this.value = value;
        }
        public CompletableFuture<SetNodeValueResult> call() {
            return super.call("DOM.setNodeValue", SetNodeValueResult.class,
                (code, msg)->new SetNodeValueResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetNodeValueResult> call(Executor exec) {
            return super.call("DOM.setNodeValue", SetNodeValueResult.class,
                (code, msg)->new SetNodeValueResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setNodeValue.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetNodeValueResult extends ResultBase {
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
        public SetNodeValueResult() { super(); }
        public SetNodeValueResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Sets node HTML markup, returns new node id.*/
    public SetOuterHTMLParameter setOuterHTML() { final SetOuterHTMLParameter v = new SetOuterHTMLParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setOuterHTML.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetOuterHTMLParameter extends CommandBase {
        /**Id of the node to set markup for.*/
        private NodeId nodeId;
        /**Outer HTML markup to set.*/
        private String outerHTML;
        public final SetOuterHTMLParameter nodeId(NodeId nodeId) { this.nodeId = nodeId; return this; }
        public final SetOuterHTMLParameter setNodeId(NodeId nodeId) { return nodeId(nodeId); }
        public final NodeId nodeId() { return nodeId; }
        public final NodeId getNodeId() { return nodeId(); }
        public final SetOuterHTMLParameter outerHTML(String outerHTML) { this.outerHTML = outerHTML; return this; }
        public final SetOuterHTMLParameter setOuterHTML(String outerHTML) { return outerHTML(outerHTML); }
        public final String outerHTML() { return outerHTML; }
        public final String getOuterHTML() { return outerHTML(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (nodeId == null) throw new IllegalArgumentException("DOM.SetOuterHTMLParameter.nodeId is necessary field.");
            if (outerHTML == null) throw new IllegalArgumentException("DOM.SetOuterHTMLParameter.outerHTML is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            nodeId.toJson(strBuilder.append("\"nodeId\":"));
            strBuilder.append(",\"outerHTML\":").append('"').append(DomainBase.escapeQuote(outerHTML)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public SetOuterHTMLParameter() {}
        public SetOuterHTMLParameter(
            @JsonProperty("nodeId")NodeId nodeId,
            @JsonProperty("outerHTML")String outerHTML
        ) {
            this();
            this.nodeId = nodeId;
            this.outerHTML = outerHTML;
        }
        public CompletableFuture<SetOuterHTMLResult> call() {
            return super.call("DOM.setOuterHTML", SetOuterHTMLResult.class,
                (code, msg)->new SetOuterHTMLResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetOuterHTMLResult> call(Executor exec) {
            return super.call("DOM.setOuterHTML", SetOuterHTMLResult.class,
                (code, msg)->new SetOuterHTMLResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setOuterHTML.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetOuterHTMLResult extends ResultBase {
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
        public SetOuterHTMLResult() { super(); }
        public SetOuterHTMLResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Undoes the last performed action.
    <p><strong>Experimental.</strong></p>*/
    public UndoParameter undo() { final UndoParameter v = new UndoParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of undo.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class UndoParameter extends CommandBase {
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
        public UndoParameter() {}
        public CompletableFuture<UndoResult> call() {
            return super.call("DOM.undo", UndoResult.class,
                (code, msg)->new UndoResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<UndoResult> call(Executor exec) {
            return super.call("DOM.undo", UndoResult.class,
                (code, msg)->new UndoResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of undo.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class UndoResult extends ResultBase {
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
        public UndoResult() { super(); }
        public UndoResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Returns iframe node that owns iframe with the given domain.
    <p><strong>Experimental.</strong></p>*/
    public GetFrameOwnerParameter getFrameOwner() { final GetFrameOwnerParameter v = new GetFrameOwnerParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of getFrameOwner.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetFrameOwnerParameter extends CommandBase {
        /**&lt;No document in protocol.&gt;*/
        private Page.FrameId frameId;
        public final GetFrameOwnerParameter frameId(Page.FrameId frameId) { this.frameId = frameId; return this; }
        public final GetFrameOwnerParameter setFrameId(Page.FrameId frameId) { return frameId(frameId); }
        public final Page.FrameId frameId() { return frameId; }
        public final Page.FrameId getFrameId() { return frameId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (frameId == null) throw new IllegalArgumentException("DOM.GetFrameOwnerParameter.frameId is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            frameId.toJson(strBuilder.append("\"frameId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public GetFrameOwnerParameter() {}
        public GetFrameOwnerParameter(
            @JsonProperty("frameId")Page.FrameId frameId
        ) {
            this();
            this.frameId = frameId;
        }
        public CompletableFuture<GetFrameOwnerResult> call() {
            return super.call("DOM.getFrameOwner", GetFrameOwnerResult.class,
                (code, msg)->new GetFrameOwnerResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetFrameOwnerResult> call(Executor exec) {
            return super.call("DOM.getFrameOwner", GetFrameOwnerResult.class,
                (code, msg)->new GetFrameOwnerResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of getFrameOwner.
    <p><strong>Experimental.</strong></p>*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetFrameOwnerResult extends ResultBase {
        /**&lt;No document in protocol.&gt;*/
        private final NodeId nodeId;
        public final NodeId nodeId() { return nodeId; }
        public final NodeId getNodeId() { return nodeId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            nodeId.toJson(strBuilder.append("\"nodeId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public GetFrameOwnerResult(
            @JsonProperty("nodeId")NodeId nodeId
        ) {
            this.nodeId = nodeId;
        }
        public GetFrameOwnerResult(ResultBase.FailedResult e) {
            super(e);
            nodeId = null;
        }
    }
    /**Event parameter of DOM.attributeModified.
     @see #onAttributeModified*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class AttributeModifiedEventParameter implements CommonDomainType {
        /**Id of the node that has changed.*/
        private final NodeId nodeId;
        /**Attribute name.*/
        private final String name;
        /**Attribute value.*/
        private final String value;
        public final NodeId nodeId() { return nodeId; }
        public final NodeId getNodeId() { return nodeId(); }
        public final String name() { return name; }
        public final String getName() { return name(); }
        public final String value() { return value; }
        public final String getValue() { return value(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            nodeId.toJson(strBuilder.append("\"nodeId\":"));
            strBuilder.append(",\"name\":").append('"').append(DomainBase.escapeQuote(name)).append('"');
            strBuilder.append(",\"value\":").append('"').append(DomainBase.escapeQuote(value)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        AttributeModifiedEventParameter(
            @JsonProperty("nodeId")NodeId nodeId,
            @JsonProperty("name")String name,
            @JsonProperty("value")String value
        ) {
            this.nodeId = nodeId;
            this.name = name;
            this.value = value;
        }
    }
    /**Fired when `Element`'s attribute is modified.
     @see AttributeModifiedEventParameter*/
    public void onAttributeModified(Consumer<AttributeModifiedEventParameter> callback) {
        registerEventCallback("DOM.attributeModified", node -> {
            AttributeModifiedEventParameter param;
            try { param = EventCenter.deserializeJson(node, AttributeModifiedEventParameter.class); }
            catch (IOException e) { e.printStackTrace(); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of DOM.attributeRemoved.
     @see #onAttributeRemoved*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class AttributeRemovedEventParameter implements CommonDomainType {
        /**Id of the node that has changed.*/
        private final NodeId nodeId;
        /**A ttribute name.*/
        private final String name;
        public final NodeId nodeId() { return nodeId; }
        public final NodeId getNodeId() { return nodeId(); }
        public final String name() { return name; }
        public final String getName() { return name(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            nodeId.toJson(strBuilder.append("\"nodeId\":"));
            strBuilder.append(",\"name\":").append('"').append(DomainBase.escapeQuote(name)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        AttributeRemovedEventParameter(
            @JsonProperty("nodeId")NodeId nodeId,
            @JsonProperty("name")String name
        ) {
            this.nodeId = nodeId;
            this.name = name;
        }
    }
    /**Fired when `Element`'s attribute is removed.
     @see AttributeRemovedEventParameter*/
    public void onAttributeRemoved(Consumer<AttributeRemovedEventParameter> callback) {
        registerEventCallback("DOM.attributeRemoved", node -> {
            AttributeRemovedEventParameter param;
            try { param = EventCenter.deserializeJson(node, AttributeRemovedEventParameter.class); }
            catch (IOException e) { e.printStackTrace(); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of DOM.characterDataModified.
     @see #onCharacterDataModified*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class CharacterDataModifiedEventParameter implements CommonDomainType {
        /**Id of the node that has changed.*/
        private final NodeId nodeId;
        /**New text value.*/
        private final String characterData;
        public final NodeId nodeId() { return nodeId; }
        public final NodeId getNodeId() { return nodeId(); }
        public final String characterData() { return characterData; }
        public final String getCharacterData() { return characterData(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            nodeId.toJson(strBuilder.append("\"nodeId\":"));
            strBuilder.append(",\"characterData\":").append('"').append(DomainBase.escapeQuote(characterData)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        CharacterDataModifiedEventParameter(
            @JsonProperty("nodeId")NodeId nodeId,
            @JsonProperty("characterData")String characterData
        ) {
            this.nodeId = nodeId;
            this.characterData = characterData;
        }
    }
    /**Mirrors `DOMCharacterDataModified` event.
     @see CharacterDataModifiedEventParameter*/
    public void onCharacterDataModified(Consumer<CharacterDataModifiedEventParameter> callback) {
        registerEventCallback("DOM.characterDataModified", node -> {
            CharacterDataModifiedEventParameter param;
            try { param = EventCenter.deserializeJson(node, CharacterDataModifiedEventParameter.class); }
            catch (IOException e) { e.printStackTrace(); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of DOM.childNodeCountUpdated.
     @see #onChildNodeCountUpdated*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ChildNodeCountUpdatedEventParameter implements CommonDomainType {
        /**Id of the node that has changed.*/
        private final NodeId nodeId;
        /**New node count.*/
        private final Integer childNodeCount;
        public final NodeId nodeId() { return nodeId; }
        public final NodeId getNodeId() { return nodeId(); }
        public final Integer childNodeCount() { return childNodeCount; }
        public final Integer getChildNodeCount() { return childNodeCount(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            nodeId.toJson(strBuilder.append("\"nodeId\":"));
            strBuilder.append(",\"childNodeCount\":").append(childNodeCount);
            strBuilder.append('}');
            return strBuilder;
        }
        ChildNodeCountUpdatedEventParameter(
            @JsonProperty("nodeId")NodeId nodeId,
            @JsonProperty("childNodeCount")Integer childNodeCount
        ) {
            this.nodeId = nodeId;
            this.childNodeCount = childNodeCount;
        }
    }
    /**Fired when `Container`'s child node count has changed.
     @see ChildNodeCountUpdatedEventParameter*/
    public void onChildNodeCountUpdated(Consumer<ChildNodeCountUpdatedEventParameter> callback) {
        registerEventCallback("DOM.childNodeCountUpdated", node -> {
            ChildNodeCountUpdatedEventParameter param;
            try { param = EventCenter.deserializeJson(node, ChildNodeCountUpdatedEventParameter.class); }
            catch (IOException e) { e.printStackTrace(); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of DOM.childNodeInserted.
     @see #onChildNodeInserted*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ChildNodeInsertedEventParameter implements CommonDomainType {
        /**Id of the node that has changed.*/
        private final NodeId parentNodeId;
        /**If of the previous siblint.*/
        private final NodeId previousNodeId;
        /**Inserted node data.*/
        private final Node node;
        public final NodeId parentNodeId() { return parentNodeId; }
        public final NodeId getParentNodeId() { return parentNodeId(); }
        public final NodeId previousNodeId() { return previousNodeId; }
        public final NodeId getPreviousNodeId() { return previousNodeId(); }
        public final Node node() { return node; }
        public final Node getNode() { return node(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            parentNodeId.toJson(strBuilder.append("\"parentNodeId\":"));
            previousNodeId.toJson(strBuilder.append(",\"previousNodeId\":"));
            node.toJson(strBuilder.append(",\"node\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        ChildNodeInsertedEventParameter(
            @JsonProperty("parentNodeId")NodeId parentNodeId,
            @JsonProperty("previousNodeId")NodeId previousNodeId,
            @JsonProperty("node")Node node
        ) {
            this.parentNodeId = parentNodeId;
            this.previousNodeId = previousNodeId;
            this.node = node;
        }
    }
    /**Mirrors `DOMNodeInserted` event.
     @see ChildNodeInsertedEventParameter*/
    public void onChildNodeInserted(Consumer<ChildNodeInsertedEventParameter> callback) {
        registerEventCallback("DOM.childNodeInserted", node -> {
            ChildNodeInsertedEventParameter param;
            try { param = EventCenter.deserializeJson(node, ChildNodeInsertedEventParameter.class); }
            catch (IOException e) { e.printStackTrace(); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of DOM.childNodeRemoved.
     @see #onChildNodeRemoved*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ChildNodeRemovedEventParameter implements CommonDomainType {
        /**Parent id.*/
        private final NodeId parentNodeId;
        /**Id of the node that has been removed.*/
        private final NodeId nodeId;
        public final NodeId parentNodeId() { return parentNodeId; }
        public final NodeId getParentNodeId() { return parentNodeId(); }
        public final NodeId nodeId() { return nodeId; }
        public final NodeId getNodeId() { return nodeId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            parentNodeId.toJson(strBuilder.append("\"parentNodeId\":"));
            nodeId.toJson(strBuilder.append(",\"nodeId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        ChildNodeRemovedEventParameter(
            @JsonProperty("parentNodeId")NodeId parentNodeId,
            @JsonProperty("nodeId")NodeId nodeId
        ) {
            this.parentNodeId = parentNodeId;
            this.nodeId = nodeId;
        }
    }
    /**Mirrors `DOMNodeRemoved` event.
     @see ChildNodeRemovedEventParameter*/
    public void onChildNodeRemoved(Consumer<ChildNodeRemovedEventParameter> callback) {
        registerEventCallback("DOM.childNodeRemoved", node -> {
            ChildNodeRemovedEventParameter param;
            try { param = EventCenter.deserializeJson(node, ChildNodeRemovedEventParameter.class); }
            catch (IOException e) { e.printStackTrace(); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of DOM.distributedNodesUpdated.
    <p><strong>Experimental.</strong></p>
     @see #onDistributedNodesUpdated*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class DistributedNodesUpdatedEventParameter implements CommonDomainType {
        /**Insertion point where distrubuted nodes were updated.*/
        private final NodeId insertionPointId;
        /**Distributed nodes for given insertion point.*/
        private final List<BackendNode> distributedNodes;
        public final NodeId insertionPointId() { return insertionPointId; }
        public final NodeId getInsertionPointId() { return insertionPointId(); }
        public final List<BackendNode> distributedNodes() { return distributedNodes; }
        public final List<BackendNode> getDistributedNodes() { return distributedNodes(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            insertionPointId.toJson(strBuilder.append("\"insertionPointId\":"));
                        strBuilder.append(",\"distributedNodes\":[");
            distributedNodes.get(0).toJson(strBuilder);
            for (int i = 1; i < distributedNodes.size(); ++i)
                distributedNodes.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        DistributedNodesUpdatedEventParameter(
            @JsonProperty("insertionPointId")NodeId insertionPointId,
            @JsonProperty("distributedNodes")List<BackendNode> distributedNodes
        ) {
            this.insertionPointId = insertionPointId;
            this.distributedNodes = distributedNodes;
        }
    }
    /**Called when distrubution is changed.
    <p><strong>Experimental.</strong></p>
     @see DistributedNodesUpdatedEventParameter*/
    public void onDistributedNodesUpdated(Consumer<DistributedNodesUpdatedEventParameter> callback) {
        registerEventCallback("DOM.distributedNodesUpdated", node -> {
            DistributedNodesUpdatedEventParameter param;
            try { param = EventCenter.deserializeJson(node, DistributedNodesUpdatedEventParameter.class); }
            catch (IOException e) { e.printStackTrace(); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of DOM.documentUpdated.
     @see #onDocumentUpdated*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class DocumentUpdatedEventParameter implements CommonDomainType {
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
        public DocumentUpdatedEventParameter() {}
    }
    /**Fired when `Document` has been totally updated. Node ids are no longer valid.
     @see DocumentUpdatedEventParameter*/
    public void onDocumentUpdated(Consumer<DocumentUpdatedEventParameter> callback) {
        registerEventCallback("DOM.documentUpdated", node -> {
            DocumentUpdatedEventParameter param;
            try { param = EventCenter.deserializeJson(node, DocumentUpdatedEventParameter.class); }
            catch (IOException e) { e.printStackTrace(); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of DOM.inlineStyleInvalidated.
    <p><strong>Experimental.</strong></p>
     @see #onInlineStyleInvalidated*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class InlineStyleInvalidatedEventParameter implements CommonDomainType {
        /**Ids of the nodes for which the inline styles have been invalidated.*/
        private final List<NodeId> nodeIds;
        public final List<NodeId> nodeIds() { return nodeIds; }
        public final List<NodeId> getNodeIds() { return nodeIds(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
                        strBuilder.append("\"nodeIds\":[");
            nodeIds.get(0).toJson(strBuilder);
            for (int i = 1; i < nodeIds.size(); ++i)
                nodeIds.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        InlineStyleInvalidatedEventParameter(
            @JsonProperty("nodeIds")List<NodeId> nodeIds
        ) {
            this.nodeIds = nodeIds;
        }
    }
    /**Fired when `Element`'s inline style is modified via a CSS property modification.
    <p><strong>Experimental.</strong></p>
     @see InlineStyleInvalidatedEventParameter*/
    public void onInlineStyleInvalidated(Consumer<InlineStyleInvalidatedEventParameter> callback) {
        registerEventCallback("DOM.inlineStyleInvalidated", node -> {
            InlineStyleInvalidatedEventParameter param;
            try { param = EventCenter.deserializeJson(node, InlineStyleInvalidatedEventParameter.class); }
            catch (IOException e) { e.printStackTrace(); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of DOM.pseudoElementAdded.
    <p><strong>Experimental.</strong></p>
     @see #onPseudoElementAdded*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class PseudoElementAddedEventParameter implements CommonDomainType {
        /**Pseudo element's parent element id.*/
        private final NodeId parentId;
        /**The added pseudo element.*/
        private final Node pseudoElement;
        public final NodeId parentId() { return parentId; }
        public final NodeId getParentId() { return parentId(); }
        public final Node pseudoElement() { return pseudoElement; }
        public final Node getPseudoElement() { return pseudoElement(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            parentId.toJson(strBuilder.append("\"parentId\":"));
            pseudoElement.toJson(strBuilder.append(",\"pseudoElement\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        PseudoElementAddedEventParameter(
            @JsonProperty("parentId")NodeId parentId,
            @JsonProperty("pseudoElement")Node pseudoElement
        ) {
            this.parentId = parentId;
            this.pseudoElement = pseudoElement;
        }
    }
    /**Called when a pseudo element is added to an element.
    <p><strong>Experimental.</strong></p>
     @see PseudoElementAddedEventParameter*/
    public void onPseudoElementAdded(Consumer<PseudoElementAddedEventParameter> callback) {
        registerEventCallback("DOM.pseudoElementAdded", node -> {
            PseudoElementAddedEventParameter param;
            try { param = EventCenter.deserializeJson(node, PseudoElementAddedEventParameter.class); }
            catch (IOException e) { e.printStackTrace(); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of DOM.pseudoElementRemoved.
    <p><strong>Experimental.</strong></p>
     @see #onPseudoElementRemoved*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class PseudoElementRemovedEventParameter implements CommonDomainType {
        /**Pseudo element's parent element id.*/
        private final NodeId parentId;
        /**The removed pseudo element id.*/
        private final NodeId pseudoElementId;
        public final NodeId parentId() { return parentId; }
        public final NodeId getParentId() { return parentId(); }
        public final NodeId pseudoElementId() { return pseudoElementId; }
        public final NodeId getPseudoElementId() { return pseudoElementId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            parentId.toJson(strBuilder.append("\"parentId\":"));
            pseudoElementId.toJson(strBuilder.append(",\"pseudoElementId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        PseudoElementRemovedEventParameter(
            @JsonProperty("parentId")NodeId parentId,
            @JsonProperty("pseudoElementId")NodeId pseudoElementId
        ) {
            this.parentId = parentId;
            this.pseudoElementId = pseudoElementId;
        }
    }
    /**Called when a pseudo element is removed from an element.
    <p><strong>Experimental.</strong></p>
     @see PseudoElementRemovedEventParameter*/
    public void onPseudoElementRemoved(Consumer<PseudoElementRemovedEventParameter> callback) {
        registerEventCallback("DOM.pseudoElementRemoved", node -> {
            PseudoElementRemovedEventParameter param;
            try { param = EventCenter.deserializeJson(node, PseudoElementRemovedEventParameter.class); }
            catch (IOException e) { e.printStackTrace(); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of DOM.setChildNodes.
     @see #onSetChildNodes*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetChildNodesEventParameter implements CommonDomainType {
        /**Parent node id to populate with children.*/
        private final NodeId parentId;
        /**Child nodes array.*/
        private final List<Node> nodes;
        public final NodeId parentId() { return parentId; }
        public final NodeId getParentId() { return parentId(); }
        public final List<Node> nodes() { return nodes; }
        public final List<Node> getNodes() { return nodes(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            parentId.toJson(strBuilder.append("\"parentId\":"));
                        strBuilder.append(",\"nodes\":[");
            nodes.get(0).toJson(strBuilder);
            for (int i = 1; i < nodes.size(); ++i)
                nodes.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        SetChildNodesEventParameter(
            @JsonProperty("parentId")NodeId parentId,
            @JsonProperty("nodes")List<Node> nodes
        ) {
            this.parentId = parentId;
            this.nodes = nodes;
        }
    }
    /**Fired when backend wants to provide client with the missing DOM structure. This happens upon
most of the calls requesting node ids.
     @see SetChildNodesEventParameter*/
    public void onSetChildNodes(Consumer<SetChildNodesEventParameter> callback) {
        registerEventCallback("DOM.setChildNodes", node -> {
            SetChildNodesEventParameter param;
            try { param = EventCenter.deserializeJson(node, SetChildNodesEventParameter.class); }
            catch (IOException e) { e.printStackTrace(); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of DOM.shadowRootPopped.
    <p><strong>Experimental.</strong></p>
     @see #onShadowRootPopped*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ShadowRootPoppedEventParameter implements CommonDomainType {
        /**Host element id.*/
        private final NodeId hostId;
        /**Shadow root id.*/
        private final NodeId rootId;
        public final NodeId hostId() { return hostId; }
        public final NodeId getHostId() { return hostId(); }
        public final NodeId rootId() { return rootId; }
        public final NodeId getRootId() { return rootId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            hostId.toJson(strBuilder.append("\"hostId\":"));
            rootId.toJson(strBuilder.append(",\"rootId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        ShadowRootPoppedEventParameter(
            @JsonProperty("hostId")NodeId hostId,
            @JsonProperty("rootId")NodeId rootId
        ) {
            this.hostId = hostId;
            this.rootId = rootId;
        }
    }
    /**Called when shadow root is popped from the element.
    <p><strong>Experimental.</strong></p>
     @see ShadowRootPoppedEventParameter*/
    public void onShadowRootPopped(Consumer<ShadowRootPoppedEventParameter> callback) {
        registerEventCallback("DOM.shadowRootPopped", node -> {
            ShadowRootPoppedEventParameter param;
            try { param = EventCenter.deserializeJson(node, ShadowRootPoppedEventParameter.class); }
            catch (IOException e) { e.printStackTrace(); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of DOM.shadowRootPushed.
    <p><strong>Experimental.</strong></p>
     @see #onShadowRootPushed*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ShadowRootPushedEventParameter implements CommonDomainType {
        /**Host element id.*/
        private final NodeId hostId;
        /**Shadow root.*/
        private final Node root;
        public final NodeId hostId() { return hostId; }
        public final NodeId getHostId() { return hostId(); }
        public final Node root() { return root; }
        public final Node getRoot() { return root(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            hostId.toJson(strBuilder.append("\"hostId\":"));
            root.toJson(strBuilder.append(",\"root\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        ShadowRootPushedEventParameter(
            @JsonProperty("hostId")NodeId hostId,
            @JsonProperty("root")Node root
        ) {
            this.hostId = hostId;
            this.root = root;
        }
    }
    /**Called when shadow root is pushed into the element.
    <p><strong>Experimental.</strong></p>
     @see ShadowRootPushedEventParameter*/
    public void onShadowRootPushed(Consumer<ShadowRootPushedEventParameter> callback) {
        registerEventCallback("DOM.shadowRootPushed", node -> {
            ShadowRootPushedEventParameter param;
            try { param = EventCenter.deserializeJson(node, ShadowRootPushedEventParameter.class); }
            catch (IOException e) { e.printStackTrace(); return; }
            callback.accept(param);
        });
    }
}

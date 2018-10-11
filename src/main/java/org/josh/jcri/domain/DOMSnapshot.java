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

/**This domain facilitates obtaining document snapshots with DOM, layout, and style information.
<p>From: browser_protocol.json</p>
<p>Protocol version: 1.3</p>
<p><strong>Experimental.</strong></p>
 @see CSS
 @see DOM
 @see DOMDebugger
 @see Page
 @author Joshua*/
@ParametersAreNonnullByDefault public class DOMSnapshot extends DomainBase {
    public DOMSnapshot(EventCenter evt, WebSocket ws) { super(evt, ws); }

    /**A Node in the DOM tree.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class DOMNode implements CommonDomainType {
        /**`Node`'s nodeType.*/
        private Integer nodeType;
        /**`Node`'s nodeName.*/
        private String nodeName;
        /**`Node`'s nodeValue.*/
        private String nodeValue;
        /**Only set for textarea elements, contains the text value.
        <em>Optional.</em>*/
        private String textValue;
        /**Only set for input elements, contains the input's associated text value.
        <em>Optional.</em>*/
        private String inputValue;
        /**Only set for radio and checkbox input elements, indicates if the element has been checked
        <em>Optional.</em>*/
        private Boolean inputChecked;
        /**Only set for option elements, indicates if the element has been selected
        <em>Optional.</em>*/
        private Boolean optionSelected;
        /**`Node`'s id, corresponds to DOM.Node.backendNodeId.*/
        private DOM.BackendNodeId backendNodeId;
        /**The indexes of the node's child nodes in the `domNodes` array returned by `getSnapshot`, if
any.
        <em>Optional.</em>*/
        private List<Integer> childNodeIndexes;
        /**Attributes of an `Element` node.
        <em>Optional.</em>*/
        private List<NameValue> attributes;
        /**Indexes of pseudo elements associated with this node in the `domNodes` array returned by
`getSnapshot`, if any.
        <em>Optional.</em>*/
        private List<Integer> pseudoElementIndexes;
        /**The index of the node's related layout tree node in the `layoutTreeNodes` array returned by
`getSnapshot`, if any.
        <em>Optional.</em>*/
        private Integer layoutNodeIndex;
        /**Document URL that `Document` or `FrameOwner` node points to.
        <em>Optional.</em>*/
        private String documentURL;
        /**Base URL that `Document` or `FrameOwner` node uses for URL completion.
        <em>Optional.</em>*/
        private String baseURL;
        /**Only set for documents, contains the document's content language.
        <em>Optional.</em>*/
        private String contentLanguage;
        /**Only set for documents, contains the document's character set encoding.
        <em>Optional.</em>*/
        private String documentEncoding;
        /**`DocumentType` node's publicId.
        <em>Optional.</em>*/
        private String publicId;
        /**`DocumentType` node's systemId.
        <em>Optional.</em>*/
        private String systemId;
        /**Frame ID for frame owner elements and also for the document node.
        <em>Optional.</em>*/
        private Page.FrameId frameId;
        /**The index of a frame owner element's content document in the `domNodes` array returned by
`getSnapshot`, if any.
        <em>Optional.</em>*/
        private Integer contentDocumentIndex;
        /**Type of a pseudo element node.
        <em>Optional.</em>*/
        private DOM.PseudoType pseudoType;
        /**Shadow root type.
        <em>Optional.</em>*/
        private DOM.ShadowRootType shadowRootType;
        /**Whether this DOM node responds to mouse clicks. This includes nodes that have had click
event listeners attached via JavaScript as well as anchor tags that naturally navigate when
clicked.
        <em>Optional.</em>*/
        private Boolean isClickable;
        /**Details of the node's event listeners, if any.
        <em>Optional.</em>*/
        private List<DOMDebugger.EventListener> eventListeners;
        /**The selected url for nodes with a srcset attribute.
        <em>Optional.</em>*/
        private String currentSourceURL;
        /**The url of the script (if any) that generates this node.
        <em>Optional.</em>*/
        private String originURL;
        /**Scroll offsets, set when this node is a Document.
        <em>Optional.</em>*/
        private Double scrollOffsetX;
        /**&lt;No document in protocol.&gt;
        <em>Optional.</em>*/
        private Double scrollOffsetY;
        public final DOMNode nodeType(Integer nodeType) { this.nodeType = nodeType; return this; }
        public final DOMNode setNodeType(Integer nodeType) { return nodeType(nodeType); }
        public final Integer nodeType() { return nodeType; }
        public final Integer getNodeType() { return nodeType(); }
        public final DOMNode nodeName(String nodeName) { this.nodeName = nodeName; return this; }
        public final DOMNode setNodeName(String nodeName) { return nodeName(nodeName); }
        public final String nodeName() { return nodeName; }
        public final String getNodeName() { return nodeName(); }
        public final DOMNode nodeValue(String nodeValue) { this.nodeValue = nodeValue; return this; }
        public final DOMNode setNodeValue(String nodeValue) { return nodeValue(nodeValue); }
        public final String nodeValue() { return nodeValue; }
        public final String getNodeValue() { return nodeValue(); }
        public final DOMNode textValue(@Nullable String textValue) { this.textValue = textValue; return this; }
        public final DOMNode optTextValue(@Nullable String textValue) { return textValue(textValue); }
        public final String textValue() { return textValue; }
        public final String getTextValue() { return textValue(); }
        public final DOMNode inputValue(@Nullable String inputValue) { this.inputValue = inputValue; return this; }
        public final DOMNode optInputValue(@Nullable String inputValue) { return inputValue(inputValue); }
        public final String inputValue() { return inputValue; }
        public final String getInputValue() { return inputValue(); }
        public final DOMNode inputChecked(@Nullable Boolean inputChecked) { this.inputChecked = inputChecked; return this; }
        public final DOMNode optInputChecked(@Nullable Boolean inputChecked) { return inputChecked(inputChecked); }
        public final Boolean inputChecked() { return inputChecked; }
        public final Boolean getInputChecked() { return inputChecked(); }
        public final DOMNode optionSelected(@Nullable Boolean optionSelected) { this.optionSelected = optionSelected; return this; }
        public final DOMNode optOptionSelected(@Nullable Boolean optionSelected) { return optionSelected(optionSelected); }
        public final Boolean optionSelected() { return optionSelected; }
        public final Boolean getOptionSelected() { return optionSelected(); }
        public final DOMNode backendNodeId(DOM.BackendNodeId backendNodeId) { this.backendNodeId = backendNodeId; return this; }
        public final DOMNode setBackendNodeId(DOM.BackendNodeId backendNodeId) { return backendNodeId(backendNodeId); }
        public final DOM.BackendNodeId backendNodeId() { return backendNodeId; }
        public final DOM.BackendNodeId getBackendNodeId() { return backendNodeId(); }
        public final DOMNode childNodeIndexes(@Nullable List<Integer> childNodeIndexes) { this.childNodeIndexes = childNodeIndexes; return this; }
        public final DOMNode optChildNodeIndexes(@Nullable List<Integer> childNodeIndexes) { return childNodeIndexes(childNodeIndexes); }
        public final List<Integer> childNodeIndexes() { return childNodeIndexes; }
        public final List<Integer> getChildNodeIndexes() { return childNodeIndexes(); }
        public final DOMNode attributes(@Nullable List<NameValue> attributes) { this.attributes = attributes; return this; }
        public final DOMNode optAttributes(@Nullable List<NameValue> attributes) { return attributes(attributes); }
        public final List<NameValue> attributes() { return attributes; }
        public final List<NameValue> getAttributes() { return attributes(); }
        public final DOMNode pseudoElementIndexes(@Nullable List<Integer> pseudoElementIndexes) { this.pseudoElementIndexes = pseudoElementIndexes; return this; }
        public final DOMNode optPseudoElementIndexes(@Nullable List<Integer> pseudoElementIndexes) { return pseudoElementIndexes(pseudoElementIndexes); }
        public final List<Integer> pseudoElementIndexes() { return pseudoElementIndexes; }
        public final List<Integer> getPseudoElementIndexes() { return pseudoElementIndexes(); }
        public final DOMNode layoutNodeIndex(@Nullable Integer layoutNodeIndex) { this.layoutNodeIndex = layoutNodeIndex; return this; }
        public final DOMNode optLayoutNodeIndex(@Nullable Integer layoutNodeIndex) { return layoutNodeIndex(layoutNodeIndex); }
        public final Integer layoutNodeIndex() { return layoutNodeIndex; }
        public final Integer getLayoutNodeIndex() { return layoutNodeIndex(); }
        public final DOMNode documentURL(@Nullable String documentURL) { this.documentURL = documentURL; return this; }
        public final DOMNode optDocumentURL(@Nullable String documentURL) { return documentURL(documentURL); }
        public final String documentURL() { return documentURL; }
        public final String getDocumentURL() { return documentURL(); }
        public final DOMNode baseURL(@Nullable String baseURL) { this.baseURL = baseURL; return this; }
        public final DOMNode optBaseURL(@Nullable String baseURL) { return baseURL(baseURL); }
        public final String baseURL() { return baseURL; }
        public final String getBaseURL() { return baseURL(); }
        public final DOMNode contentLanguage(@Nullable String contentLanguage) { this.contentLanguage = contentLanguage; return this; }
        public final DOMNode optContentLanguage(@Nullable String contentLanguage) { return contentLanguage(contentLanguage); }
        public final String contentLanguage() { return contentLanguage; }
        public final String getContentLanguage() { return contentLanguage(); }
        public final DOMNode documentEncoding(@Nullable String documentEncoding) { this.documentEncoding = documentEncoding; return this; }
        public final DOMNode optDocumentEncoding(@Nullable String documentEncoding) { return documentEncoding(documentEncoding); }
        public final String documentEncoding() { return documentEncoding; }
        public final String getDocumentEncoding() { return documentEncoding(); }
        public final DOMNode publicId(@Nullable String publicId) { this.publicId = publicId; return this; }
        public final DOMNode optPublicId(@Nullable String publicId) { return publicId(publicId); }
        public final String publicId() { return publicId; }
        public final String getPublicId() { return publicId(); }
        public final DOMNode systemId(@Nullable String systemId) { this.systemId = systemId; return this; }
        public final DOMNode optSystemId(@Nullable String systemId) { return systemId(systemId); }
        public final String systemId() { return systemId; }
        public final String getSystemId() { return systemId(); }
        public final DOMNode frameId(@Nullable Page.FrameId frameId) { this.frameId = frameId; return this; }
        public final DOMNode optFrameId(@Nullable Page.FrameId frameId) { return frameId(frameId); }
        public final Page.FrameId frameId() { return frameId; }
        public final Page.FrameId getFrameId() { return frameId(); }
        public final DOMNode contentDocumentIndex(@Nullable Integer contentDocumentIndex) { this.contentDocumentIndex = contentDocumentIndex; return this; }
        public final DOMNode optContentDocumentIndex(@Nullable Integer contentDocumentIndex) { return contentDocumentIndex(contentDocumentIndex); }
        public final Integer contentDocumentIndex() { return contentDocumentIndex; }
        public final Integer getContentDocumentIndex() { return contentDocumentIndex(); }
        public final DOMNode pseudoType(@Nullable DOM.PseudoType pseudoType) { this.pseudoType = pseudoType; return this; }
        public final DOMNode optPseudoType(@Nullable DOM.PseudoType pseudoType) { return pseudoType(pseudoType); }
        public final DOM.PseudoType pseudoType() { return pseudoType; }
        public final DOM.PseudoType getPseudoType() { return pseudoType(); }
        public final DOMNode shadowRootType(@Nullable DOM.ShadowRootType shadowRootType) { this.shadowRootType = shadowRootType; return this; }
        public final DOMNode optShadowRootType(@Nullable DOM.ShadowRootType shadowRootType) { return shadowRootType(shadowRootType); }
        public final DOM.ShadowRootType shadowRootType() { return shadowRootType; }
        public final DOM.ShadowRootType getShadowRootType() { return shadowRootType(); }
        public final DOMNode isClickable(@Nullable Boolean isClickable) { this.isClickable = isClickable; return this; }
        public final DOMNode optIsClickable(@Nullable Boolean isClickable) { return isClickable(isClickable); }
        public final Boolean isClickable() { return isClickable; }
        public final Boolean getIsClickable() { return isClickable(); }
        public final DOMNode eventListeners(@Nullable List<DOMDebugger.EventListener> eventListeners) { this.eventListeners = eventListeners; return this; }
        public final DOMNode optEventListeners(@Nullable List<DOMDebugger.EventListener> eventListeners) { return eventListeners(eventListeners); }
        public final List<DOMDebugger.EventListener> eventListeners() { return eventListeners; }
        public final List<DOMDebugger.EventListener> getEventListeners() { return eventListeners(); }
        public final DOMNode currentSourceURL(@Nullable String currentSourceURL) { this.currentSourceURL = currentSourceURL; return this; }
        public final DOMNode optCurrentSourceURL(@Nullable String currentSourceURL) { return currentSourceURL(currentSourceURL); }
        public final String currentSourceURL() { return currentSourceURL; }
        public final String getCurrentSourceURL() { return currentSourceURL(); }
        public final DOMNode originURL(@Nullable String originURL) { this.originURL = originURL; return this; }
        public final DOMNode optOriginURL(@Nullable String originURL) { return originURL(originURL); }
        public final String originURL() { return originURL; }
        public final String getOriginURL() { return originURL(); }
        public final DOMNode scrollOffsetX(@Nullable Double scrollOffsetX) { this.scrollOffsetX = scrollOffsetX; return this; }
        public final DOMNode optScrollOffsetX(@Nullable Double scrollOffsetX) { return scrollOffsetX(scrollOffsetX); }
        public final Double scrollOffsetX() { return scrollOffsetX; }
        public final Double getScrollOffsetX() { return scrollOffsetX(); }
        public final DOMNode scrollOffsetY(@Nullable Double scrollOffsetY) { this.scrollOffsetY = scrollOffsetY; return this; }
        public final DOMNode optScrollOffsetY(@Nullable Double scrollOffsetY) { return scrollOffsetY(scrollOffsetY); }
        public final Double scrollOffsetY() { return scrollOffsetY; }
        public final Double getScrollOffsetY() { return scrollOffsetY(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (nodeType == null) throw new IllegalArgumentException("DOMSnapshot.DOMNode.nodeType is necessary field.");
            if (nodeName == null) throw new IllegalArgumentException("DOMSnapshot.DOMNode.nodeName is necessary field.");
            if (nodeValue == null) throw new IllegalArgumentException("DOMSnapshot.DOMNode.nodeValue is necessary field.");
            if (backendNodeId == null) throw new IllegalArgumentException("DOMSnapshot.DOMNode.backendNodeId is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"nodeType\":").append(nodeType);
            strBuilder.append(",\"nodeName\":").append('"').append(DomainBase.escapeJson(nodeName)).append('"');
            strBuilder.append(",\"nodeValue\":").append('"').append(DomainBase.escapeJson(nodeValue)).append('"');
            if (textValue != null) strBuilder.append(",\"textValue\":").append('"').append(DomainBase.escapeJson(textValue)).append('"');
            if (inputValue != null) strBuilder.append(",\"inputValue\":").append('"').append(DomainBase.escapeJson(inputValue)).append('"');
            if (inputChecked != null) strBuilder.append(",\"inputChecked\":").append(inputChecked);
            if (optionSelected != null) strBuilder.append(",\"optionSelected\":").append(optionSelected);
            backendNodeId.toJson(strBuilder.append(",\"backendNodeId\":"));
            if (childNodeIndexes != null) {
                strBuilder.append(",\"childNodeIndexes\":[");
                strBuilder.append(childNodeIndexes.get(0));
                for (int i = 1; i < childNodeIndexes.size(); ++i)
                    strBuilder.append(',').append(childNodeIndexes.get(i));
                strBuilder.append(']');
            }
            if (attributes != null) {
                strBuilder.append(",\"attributes\":[");
                attributes.get(0).toJson(strBuilder);
                for (int i = 1; i < attributes.size(); ++i)
                    attributes.get(i).toJson(strBuilder.append(','));
                strBuilder.append(']');
            }
            if (pseudoElementIndexes != null) {
                strBuilder.append(",\"pseudoElementIndexes\":[");
                strBuilder.append(pseudoElementIndexes.get(0));
                for (int i = 1; i < pseudoElementIndexes.size(); ++i)
                    strBuilder.append(',').append(pseudoElementIndexes.get(i));
                strBuilder.append(']');
            }
            if (layoutNodeIndex != null) strBuilder.append(",\"layoutNodeIndex\":").append(layoutNodeIndex);
            if (documentURL != null) strBuilder.append(",\"documentURL\":").append('"').append(DomainBase.escapeJson(documentURL)).append('"');
            if (baseURL != null) strBuilder.append(",\"baseURL\":").append('"').append(DomainBase.escapeJson(baseURL)).append('"');
            if (contentLanguage != null) strBuilder.append(",\"contentLanguage\":").append('"').append(DomainBase.escapeJson(contentLanguage)).append('"');
            if (documentEncoding != null) strBuilder.append(",\"documentEncoding\":").append('"').append(DomainBase.escapeJson(documentEncoding)).append('"');
            if (publicId != null) strBuilder.append(",\"publicId\":").append('"').append(DomainBase.escapeJson(publicId)).append('"');
            if (systemId != null) strBuilder.append(",\"systemId\":").append('"').append(DomainBase.escapeJson(systemId)).append('"');
            if (frameId != null) frameId.toJson(strBuilder.append(",\"frameId\":"));
            if (contentDocumentIndex != null) strBuilder.append(",\"contentDocumentIndex\":").append(contentDocumentIndex);
            if (pseudoType != null) pseudoType.toJson(strBuilder.append(",\"pseudoType\":"));
            if (shadowRootType != null) shadowRootType.toJson(strBuilder.append(",\"shadowRootType\":"));
            if (isClickable != null) strBuilder.append(",\"isClickable\":").append(isClickable);
            if (eventListeners != null) {
                strBuilder.append(",\"eventListeners\":[");
                eventListeners.get(0).toJson(strBuilder);
                for (int i = 1; i < eventListeners.size(); ++i)
                    eventListeners.get(i).toJson(strBuilder.append(','));
                strBuilder.append(']');
            }
            if (currentSourceURL != null) strBuilder.append(",\"currentSourceURL\":").append('"').append(DomainBase.escapeJson(currentSourceURL)).append('"');
            if (originURL != null) strBuilder.append(",\"originURL\":").append('"').append(DomainBase.escapeJson(originURL)).append('"');
            if (scrollOffsetX != null) strBuilder.append(",\"scrollOffsetX\":").append(scrollOffsetX);
            if (scrollOffsetY != null) strBuilder.append(",\"scrollOffsetY\":").append(scrollOffsetY);
            strBuilder.append('}');
            return strBuilder;
        }
        public DOMNode() {}
        public DOMNode(
            @JsonProperty("nodeType")Integer nodeType,
            @JsonProperty("nodeName")String nodeName,
            @JsonProperty("nodeValue")String nodeValue,
            @Nullable @JsonProperty("textValue")String textValue,
            @Nullable @JsonProperty("inputValue")String inputValue,
            @Nullable @JsonProperty("inputChecked")Boolean inputChecked,
            @Nullable @JsonProperty("optionSelected")Boolean optionSelected,
            @JsonProperty("backendNodeId")DOM.BackendNodeId backendNodeId,
            @Nullable @JsonProperty("childNodeIndexes")List<Integer> childNodeIndexes,
            @Nullable @JsonProperty("attributes")List<NameValue> attributes,
            @Nullable @JsonProperty("pseudoElementIndexes")List<Integer> pseudoElementIndexes,
            @Nullable @JsonProperty("layoutNodeIndex")Integer layoutNodeIndex,
            @Nullable @JsonProperty("documentURL")String documentURL,
            @Nullable @JsonProperty("baseURL")String baseURL,
            @Nullable @JsonProperty("contentLanguage")String contentLanguage,
            @Nullable @JsonProperty("documentEncoding")String documentEncoding,
            @Nullable @JsonProperty("publicId")String publicId,
            @Nullable @JsonProperty("systemId")String systemId,
            @Nullable @JsonProperty("frameId")Page.FrameId frameId,
            @Nullable @JsonProperty("contentDocumentIndex")Integer contentDocumentIndex,
            @Nullable @JsonProperty("pseudoType")DOM.PseudoType pseudoType,
            @Nullable @JsonProperty("shadowRootType")DOM.ShadowRootType shadowRootType,
            @Nullable @JsonProperty("isClickable")Boolean isClickable,
            @Nullable @JsonProperty("eventListeners")List<DOMDebugger.EventListener> eventListeners,
            @Nullable @JsonProperty("currentSourceURL")String currentSourceURL,
            @Nullable @JsonProperty("originURL")String originURL,
            @Nullable @JsonProperty("scrollOffsetX")Double scrollOffsetX,
            @Nullable @JsonProperty("scrollOffsetY")Double scrollOffsetY
        ) {
            this.nodeType = nodeType;
            this.nodeName = nodeName;
            this.nodeValue = nodeValue;
            this.textValue = textValue;
            this.inputValue = inputValue;
            this.inputChecked = inputChecked;
            this.optionSelected = optionSelected;
            this.backendNodeId = backendNodeId;
            this.childNodeIndexes = childNodeIndexes;
            this.attributes = attributes;
            this.pseudoElementIndexes = pseudoElementIndexes;
            this.layoutNodeIndex = layoutNodeIndex;
            this.documentURL = documentURL;
            this.baseURL = baseURL;
            this.contentLanguage = contentLanguage;
            this.documentEncoding = documentEncoding;
            this.publicId = publicId;
            this.systemId = systemId;
            this.frameId = frameId;
            this.contentDocumentIndex = contentDocumentIndex;
            this.pseudoType = pseudoType;
            this.shadowRootType = shadowRootType;
            this.isClickable = isClickable;
            this.eventListeners = eventListeners;
            this.currentSourceURL = currentSourceURL;
            this.originURL = originURL;
            this.scrollOffsetX = scrollOffsetX;
            this.scrollOffsetY = scrollOffsetY;
        }
    }

    /**Details of post layout rendered text positions. The exact layout should not be regarded as
stable and may change between versions.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class InlineTextBox implements CommonDomainType {
        /**The bounding box in document coordinates. Note that scroll offset of the document is ignored.*/
        private DOM.Rect boundingBox;
        /**The starting index in characters, for this post layout textbox substring. Characters that
would be represented as a surrogate pair in UTF-16 have length 2.*/
        private Integer startCharacterIndex;
        /**The number of characters in this post layout textbox substring. Characters that would be
represented as a surrogate pair in UTF-16 have length 2.*/
        private Integer numCharacters;
        public final InlineTextBox boundingBox(DOM.Rect boundingBox) { this.boundingBox = boundingBox; return this; }
        public final InlineTextBox setBoundingBox(DOM.Rect boundingBox) { return boundingBox(boundingBox); }
        public final DOM.Rect boundingBox() { return boundingBox; }
        public final DOM.Rect getBoundingBox() { return boundingBox(); }
        public final InlineTextBox startCharacterIndex(Integer startCharacterIndex) { this.startCharacterIndex = startCharacterIndex; return this; }
        public final InlineTextBox setStartCharacterIndex(Integer startCharacterIndex) { return startCharacterIndex(startCharacterIndex); }
        public final Integer startCharacterIndex() { return startCharacterIndex; }
        public final Integer getStartCharacterIndex() { return startCharacterIndex(); }
        public final InlineTextBox numCharacters(Integer numCharacters) { this.numCharacters = numCharacters; return this; }
        public final InlineTextBox setNumCharacters(Integer numCharacters) { return numCharacters(numCharacters); }
        public final Integer numCharacters() { return numCharacters; }
        public final Integer getNumCharacters() { return numCharacters(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (boundingBox == null) throw new IllegalArgumentException("DOMSnapshot.InlineTextBox.boundingBox is necessary field.");
            if (startCharacterIndex == null) throw new IllegalArgumentException("DOMSnapshot.InlineTextBox.startCharacterIndex is necessary field.");
            if (numCharacters == null) throw new IllegalArgumentException("DOMSnapshot.InlineTextBox.numCharacters is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            boundingBox.toJson(strBuilder.append("\"boundingBox\":"));
            strBuilder.append(",\"startCharacterIndex\":").append(startCharacterIndex);
            strBuilder.append(",\"numCharacters\":").append(numCharacters);
            strBuilder.append('}');
            return strBuilder;
        }
        public InlineTextBox() {}
        public InlineTextBox(
            @JsonProperty("boundingBox")DOM.Rect boundingBox,
            @JsonProperty("startCharacterIndex")Integer startCharacterIndex,
            @JsonProperty("numCharacters")Integer numCharacters
        ) {
            this.boundingBox = boundingBox;
            this.startCharacterIndex = startCharacterIndex;
            this.numCharacters = numCharacters;
        }
    }

    /**Details of an element in the DOM tree with a LayoutObject.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class LayoutTreeNode implements CommonDomainType {
        /**The index of the related DOM node in the `domNodes` array returned by `getSnapshot`.*/
        private Integer domNodeIndex;
        /**The bounding box in document coordinates. Note that scroll offset of the document is ignored.*/
        private DOM.Rect boundingBox;
        /**Contents of the LayoutText, if any.
        <em>Optional.</em>*/
        private String layoutText;
        /**The post-layout inline text nodes, if any.
        <em>Optional.</em>*/
        private List<InlineTextBox> inlineTextNodes;
        /**Index into the `computedStyles` array returned by `getSnapshot`.
        <em>Optional.</em>*/
        private Integer styleIndex;
        /**Global paint order index, which is determined by the stacking order of the nodes. Nodes
that are painted together will have the same index. Only provided if includePaintOrder in
getSnapshot was true.
        <em>Optional.</em>*/
        private Integer paintOrder;
        /**Set to true to indicate the element begins a new stacking context.
        <em>Optional.</em>*/
        private Boolean isStackingContext;
        public final LayoutTreeNode domNodeIndex(Integer domNodeIndex) { this.domNodeIndex = domNodeIndex; return this; }
        public final LayoutTreeNode setDomNodeIndex(Integer domNodeIndex) { return domNodeIndex(domNodeIndex); }
        public final Integer domNodeIndex() { return domNodeIndex; }
        public final Integer getDomNodeIndex() { return domNodeIndex(); }
        public final LayoutTreeNode boundingBox(DOM.Rect boundingBox) { this.boundingBox = boundingBox; return this; }
        public final LayoutTreeNode setBoundingBox(DOM.Rect boundingBox) { return boundingBox(boundingBox); }
        public final DOM.Rect boundingBox() { return boundingBox; }
        public final DOM.Rect getBoundingBox() { return boundingBox(); }
        public final LayoutTreeNode layoutText(@Nullable String layoutText) { this.layoutText = layoutText; return this; }
        public final LayoutTreeNode optLayoutText(@Nullable String layoutText) { return layoutText(layoutText); }
        public final String layoutText() { return layoutText; }
        public final String getLayoutText() { return layoutText(); }
        public final LayoutTreeNode inlineTextNodes(@Nullable List<InlineTextBox> inlineTextNodes) { this.inlineTextNodes = inlineTextNodes; return this; }
        public final LayoutTreeNode optInlineTextNodes(@Nullable List<InlineTextBox> inlineTextNodes) { return inlineTextNodes(inlineTextNodes); }
        public final List<InlineTextBox> inlineTextNodes() { return inlineTextNodes; }
        public final List<InlineTextBox> getInlineTextNodes() { return inlineTextNodes(); }
        public final LayoutTreeNode styleIndex(@Nullable Integer styleIndex) { this.styleIndex = styleIndex; return this; }
        public final LayoutTreeNode optStyleIndex(@Nullable Integer styleIndex) { return styleIndex(styleIndex); }
        public final Integer styleIndex() { return styleIndex; }
        public final Integer getStyleIndex() { return styleIndex(); }
        public final LayoutTreeNode paintOrder(@Nullable Integer paintOrder) { this.paintOrder = paintOrder; return this; }
        public final LayoutTreeNode optPaintOrder(@Nullable Integer paintOrder) { return paintOrder(paintOrder); }
        public final Integer paintOrder() { return paintOrder; }
        public final Integer getPaintOrder() { return paintOrder(); }
        public final LayoutTreeNode isStackingContext(@Nullable Boolean isStackingContext) { this.isStackingContext = isStackingContext; return this; }
        public final LayoutTreeNode optIsStackingContext(@Nullable Boolean isStackingContext) { return isStackingContext(isStackingContext); }
        public final Boolean isStackingContext() { return isStackingContext; }
        public final Boolean getIsStackingContext() { return isStackingContext(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (domNodeIndex == null) throw new IllegalArgumentException("DOMSnapshot.LayoutTreeNode.domNodeIndex is necessary field.");
            if (boundingBox == null) throw new IllegalArgumentException("DOMSnapshot.LayoutTreeNode.boundingBox is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"domNodeIndex\":").append(domNodeIndex);
            boundingBox.toJson(strBuilder.append(",\"boundingBox\":"));
            if (layoutText != null) strBuilder.append(",\"layoutText\":").append('"').append(DomainBase.escapeJson(layoutText)).append('"');
            if (inlineTextNodes != null) {
                strBuilder.append(",\"inlineTextNodes\":[");
                inlineTextNodes.get(0).toJson(strBuilder);
                for (int i = 1; i < inlineTextNodes.size(); ++i)
                    inlineTextNodes.get(i).toJson(strBuilder.append(','));
                strBuilder.append(']');
            }
            if (styleIndex != null) strBuilder.append(",\"styleIndex\":").append(styleIndex);
            if (paintOrder != null) strBuilder.append(",\"paintOrder\":").append(paintOrder);
            if (isStackingContext != null) strBuilder.append(",\"isStackingContext\":").append(isStackingContext);
            strBuilder.append('}');
            return strBuilder;
        }
        public LayoutTreeNode() {}
        public LayoutTreeNode(
            @JsonProperty("domNodeIndex")Integer domNodeIndex,
            @JsonProperty("boundingBox")DOM.Rect boundingBox,
            @Nullable @JsonProperty("layoutText")String layoutText,
            @Nullable @JsonProperty("inlineTextNodes")List<InlineTextBox> inlineTextNodes,
            @Nullable @JsonProperty("styleIndex")Integer styleIndex,
            @Nullable @JsonProperty("paintOrder")Integer paintOrder,
            @Nullable @JsonProperty("isStackingContext")Boolean isStackingContext
        ) {
            this.domNodeIndex = domNodeIndex;
            this.boundingBox = boundingBox;
            this.layoutText = layoutText;
            this.inlineTextNodes = inlineTextNodes;
            this.styleIndex = styleIndex;
            this.paintOrder = paintOrder;
            this.isStackingContext = isStackingContext;
        }
    }

    /**A subset of the full ComputedStyle as defined by the request whitelist.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ComputedStyle implements CommonDomainType {
        /**Name/value pairs of computed style properties.*/
        private List<NameValue> properties;
        public final ComputedStyle properties(List<NameValue> properties) { this.properties = properties; return this; }
        public final ComputedStyle setProperties(List<NameValue> properties) { return properties(properties); }
        public final List<NameValue> properties() { return properties; }
        public final List<NameValue> getProperties() { return properties(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (properties == null) throw new IllegalArgumentException("DOMSnapshot.ComputedStyle.properties is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
                        strBuilder.append("\"properties\":[");
            properties.get(0).toJson(strBuilder);
            for (int i = 1; i < properties.size(); ++i)
                properties.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public ComputedStyle() {}
        public ComputedStyle(
            @JsonProperty("properties")List<NameValue> properties
        ) {
            this.properties = properties;
        }
    }

    /**A name/value pair.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class NameValue implements CommonDomainType {
        /**Attribute/property name.*/
        private String name;
        /**Attribute/property value.*/
        private String value;
        public final NameValue name(String name) { this.name = name; return this; }
        public final NameValue setName(String name) { return name(name); }
        public final String name() { return name; }
        public final String getName() { return name(); }
        public final NameValue value(String value) { this.value = value; return this; }
        public final NameValue setValue(String value) { return value(value); }
        public final String value() { return value; }
        public final String getValue() { return value(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (name == null) throw new IllegalArgumentException("DOMSnapshot.NameValue.name is necessary field.");
            if (value == null) throw new IllegalArgumentException("DOMSnapshot.NameValue.value is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"name\":").append('"').append(DomainBase.escapeJson(name)).append('"');
            strBuilder.append(",\"value\":").append('"').append(DomainBase.escapeJson(value)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public NameValue() {}
        public NameValue(
            @JsonProperty("name")String name,
            @JsonProperty("value")String value
        ) {
            this.name = name;
            this.value = value;
        }
    }

    /**Index of the string in the strings table.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class StringIndex implements CommonDomainType {
        private Integer _value;
        public StringIndex() {}
        public StringIndex(Integer value) { _value = value; }
        public final StringIndex value(Integer value) { _value = value; return this; }
        public final Integer value() { return _value; }
        public final StringIndex setValue(Integer value) { return value(value); }
        public final Integer getValue() { return value(); }
        @Override public String toString() { return String.valueOf(_value); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (_value == null) throw new IllegalArgumentException("DOMSnapshot.StringIndex.value is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            return strBuilder.append(_value);
        }
    }

    /**Index of the string in the strings table.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ArrayOfStrings implements CommonDomainType {
        private List<StringIndex> _value;
        public ArrayOfStrings() {}
        public ArrayOfStrings(List<StringIndex> value) { _value = value; }
        public final ArrayOfStrings value(List<StringIndex> value) { _value = value; return this; }
        public final List<StringIndex> value() { return _value; }
        public final ArrayOfStrings setValue(List<StringIndex> value) { return value(value); }
        public final List<StringIndex> getValue() { return value(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (_value == null) throw new IllegalArgumentException("DOMSnapshot.ArrayOfStrings.value is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            return strBuilder.append(_value);
        }
    }

    /**Data that is only present on rare nodes.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class RareStringData implements CommonDomainType {
        /**&lt;No document in protocol.&gt;*/
        private List<Integer> index;
        /**&lt;No document in protocol.&gt;*/
        private List<StringIndex> value;
        public final RareStringData index(List<Integer> index) { this.index = index; return this; }
        public final RareStringData setIndex(List<Integer> index) { return index(index); }
        public final List<Integer> index() { return index; }
        public final List<Integer> getIndex() { return index(); }
        public final RareStringData value(List<StringIndex> value) { this.value = value; return this; }
        public final RareStringData setValue(List<StringIndex> value) { return value(value); }
        public final List<StringIndex> value() { return value; }
        public final List<StringIndex> getValue() { return value(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (index == null) throw new IllegalArgumentException("DOMSnapshot.RareStringData.index is necessary field.");
            if (value == null) throw new IllegalArgumentException("DOMSnapshot.RareStringData.value is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
                        strBuilder.append("\"index\":[");
            strBuilder.append(index.get(0));
            for (int i = 1; i < index.size(); ++i)
                strBuilder.append(',').append(index.get(i));
            strBuilder.append(']');
                        strBuilder.append(",\"value\":[");
            value.get(0).toJson(strBuilder);
            for (int i = 1; i < value.size(); ++i)
                value.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public RareStringData() {}
        public RareStringData(
            @JsonProperty("index")List<Integer> index,
            @JsonProperty("value")List<StringIndex> value
        ) {
            this.index = index;
            this.value = value;
        }
    }

    /**&lt;No document in protocol.&gt;*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class RareBooleanData implements CommonDomainType {
        /**&lt;No document in protocol.&gt;*/
        private List<Integer> index;
        public final RareBooleanData index(List<Integer> index) { this.index = index; return this; }
        public final RareBooleanData setIndex(List<Integer> index) { return index(index); }
        public final List<Integer> index() { return index; }
        public final List<Integer> getIndex() { return index(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (index == null) throw new IllegalArgumentException("DOMSnapshot.RareBooleanData.index is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
                        strBuilder.append("\"index\":[");
            strBuilder.append(index.get(0));
            for (int i = 1; i < index.size(); ++i)
                strBuilder.append(',').append(index.get(i));
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public RareBooleanData() {}
        public RareBooleanData(
            @JsonProperty("index")List<Integer> index
        ) {
            this.index = index;
        }
    }

    /**&lt;No document in protocol.&gt;*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class RareIntegerData implements CommonDomainType {
        /**&lt;No document in protocol.&gt;*/
        private List<Integer> index;
        /**&lt;No document in protocol.&gt;*/
        private List<Integer> value;
        public final RareIntegerData index(List<Integer> index) { this.index = index; return this; }
        public final RareIntegerData setIndex(List<Integer> index) { return index(index); }
        public final List<Integer> index() { return index; }
        public final List<Integer> getIndex() { return index(); }
        public final RareIntegerData value(List<Integer> value) { this.value = value; return this; }
        public final RareIntegerData setValue(List<Integer> value) { return value(value); }
        public final List<Integer> value() { return value; }
        public final List<Integer> getValue() { return value(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (index == null) throw new IllegalArgumentException("DOMSnapshot.RareIntegerData.index is necessary field.");
            if (value == null) throw new IllegalArgumentException("DOMSnapshot.RareIntegerData.value is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
                        strBuilder.append("\"index\":[");
            strBuilder.append(index.get(0));
            for (int i = 1; i < index.size(); ++i)
                strBuilder.append(',').append(index.get(i));
            strBuilder.append(']');
                        strBuilder.append(",\"value\":[");
            strBuilder.append(value.get(0));
            for (int i = 1; i < value.size(); ++i)
                strBuilder.append(',').append(value.get(i));
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public RareIntegerData() {}
        public RareIntegerData(
            @JsonProperty("index")List<Integer> index,
            @JsonProperty("value")List<Integer> value
        ) {
            this.index = index;
            this.value = value;
        }
    }

    /**&lt;No document in protocol.&gt;*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class Rectangle implements CommonDomainType {
        private List<Double> _value;
        public Rectangle() {}
        public Rectangle(List<Double> value) { _value = value; }
        public final Rectangle value(List<Double> value) { _value = value; return this; }
        public final List<Double> value() { return _value; }
        public final Rectangle setValue(List<Double> value) { return value(value); }
        public final List<Double> getValue() { return value(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (_value == null) throw new IllegalArgumentException("DOMSnapshot.Rectangle.value is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            return strBuilder.append(_value);
        }
    }

    /**Document snapshot.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class DocumentSnapshot implements CommonDomainType {
        /**Document URL that `Document` or `FrameOwner` node points to.*/
        private StringIndex documentURL;
        /**Base URL that `Document` or `FrameOwner` node uses for URL completion.*/
        private StringIndex baseURL;
        /**Contains the document's content language.*/
        private StringIndex contentLanguage;
        /**Contains the document's character set encoding.*/
        private StringIndex encodingName;
        /**`DocumentType` node's publicId.*/
        private StringIndex publicId;
        /**`DocumentType` node's systemId.*/
        private StringIndex systemId;
        /**Frame ID for frame owner elements and also for the document node.*/
        private StringIndex frameId;
        /**A table with dom nodes.*/
        private NodeTreeSnapshot nodes;
        /**The nodes in the layout tree.*/
        private LayoutTreeSnapshot layout;
        /**The post-layout inline text nodes.*/
        private TextBoxSnapshot textBoxes;
        /**Scroll offsets.
        <em>Optional.</em>*/
        private Double scrollOffsetX;
        /**&lt;No document in protocol.&gt;
        <em>Optional.</em>*/
        private Double scrollOffsetY;
        public final DocumentSnapshot documentURL(StringIndex documentURL) { this.documentURL = documentURL; return this; }
        public final DocumentSnapshot setDocumentURL(StringIndex documentURL) { return documentURL(documentURL); }
        public final StringIndex documentURL() { return documentURL; }
        public final StringIndex getDocumentURL() { return documentURL(); }
        public final DocumentSnapshot baseURL(StringIndex baseURL) { this.baseURL = baseURL; return this; }
        public final DocumentSnapshot setBaseURL(StringIndex baseURL) { return baseURL(baseURL); }
        public final StringIndex baseURL() { return baseURL; }
        public final StringIndex getBaseURL() { return baseURL(); }
        public final DocumentSnapshot contentLanguage(StringIndex contentLanguage) { this.contentLanguage = contentLanguage; return this; }
        public final DocumentSnapshot setContentLanguage(StringIndex contentLanguage) { return contentLanguage(contentLanguage); }
        public final StringIndex contentLanguage() { return contentLanguage; }
        public final StringIndex getContentLanguage() { return contentLanguage(); }
        public final DocumentSnapshot encodingName(StringIndex encodingName) { this.encodingName = encodingName; return this; }
        public final DocumentSnapshot setEncodingName(StringIndex encodingName) { return encodingName(encodingName); }
        public final StringIndex encodingName() { return encodingName; }
        public final StringIndex getEncodingName() { return encodingName(); }
        public final DocumentSnapshot publicId(StringIndex publicId) { this.publicId = publicId; return this; }
        public final DocumentSnapshot setPublicId(StringIndex publicId) { return publicId(publicId); }
        public final StringIndex publicId() { return publicId; }
        public final StringIndex getPublicId() { return publicId(); }
        public final DocumentSnapshot systemId(StringIndex systemId) { this.systemId = systemId; return this; }
        public final DocumentSnapshot setSystemId(StringIndex systemId) { return systemId(systemId); }
        public final StringIndex systemId() { return systemId; }
        public final StringIndex getSystemId() { return systemId(); }
        public final DocumentSnapshot frameId(StringIndex frameId) { this.frameId = frameId; return this; }
        public final DocumentSnapshot setFrameId(StringIndex frameId) { return frameId(frameId); }
        public final StringIndex frameId() { return frameId; }
        public final StringIndex getFrameId() { return frameId(); }
        public final DocumentSnapshot nodes(NodeTreeSnapshot nodes) { this.nodes = nodes; return this; }
        public final DocumentSnapshot setNodes(NodeTreeSnapshot nodes) { return nodes(nodes); }
        public final NodeTreeSnapshot nodes() { return nodes; }
        public final NodeTreeSnapshot getNodes() { return nodes(); }
        public final DocumentSnapshot layout(LayoutTreeSnapshot layout) { this.layout = layout; return this; }
        public final DocumentSnapshot setLayout(LayoutTreeSnapshot layout) { return layout(layout); }
        public final LayoutTreeSnapshot layout() { return layout; }
        public final LayoutTreeSnapshot getLayout() { return layout(); }
        public final DocumentSnapshot textBoxes(TextBoxSnapshot textBoxes) { this.textBoxes = textBoxes; return this; }
        public final DocumentSnapshot setTextBoxes(TextBoxSnapshot textBoxes) { return textBoxes(textBoxes); }
        public final TextBoxSnapshot textBoxes() { return textBoxes; }
        public final TextBoxSnapshot getTextBoxes() { return textBoxes(); }
        public final DocumentSnapshot scrollOffsetX(@Nullable Double scrollOffsetX) { this.scrollOffsetX = scrollOffsetX; return this; }
        public final DocumentSnapshot optScrollOffsetX(@Nullable Double scrollOffsetX) { return scrollOffsetX(scrollOffsetX); }
        public final Double scrollOffsetX() { return scrollOffsetX; }
        public final Double getScrollOffsetX() { return scrollOffsetX(); }
        public final DocumentSnapshot scrollOffsetY(@Nullable Double scrollOffsetY) { this.scrollOffsetY = scrollOffsetY; return this; }
        public final DocumentSnapshot optScrollOffsetY(@Nullable Double scrollOffsetY) { return scrollOffsetY(scrollOffsetY); }
        public final Double scrollOffsetY() { return scrollOffsetY; }
        public final Double getScrollOffsetY() { return scrollOffsetY(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (documentURL == null) throw new IllegalArgumentException("DOMSnapshot.DocumentSnapshot.documentURL is necessary field.");
            if (baseURL == null) throw new IllegalArgumentException("DOMSnapshot.DocumentSnapshot.baseURL is necessary field.");
            if (contentLanguage == null) throw new IllegalArgumentException("DOMSnapshot.DocumentSnapshot.contentLanguage is necessary field.");
            if (encodingName == null) throw new IllegalArgumentException("DOMSnapshot.DocumentSnapshot.encodingName is necessary field.");
            if (publicId == null) throw new IllegalArgumentException("DOMSnapshot.DocumentSnapshot.publicId is necessary field.");
            if (systemId == null) throw new IllegalArgumentException("DOMSnapshot.DocumentSnapshot.systemId is necessary field.");
            if (frameId == null) throw new IllegalArgumentException("DOMSnapshot.DocumentSnapshot.frameId is necessary field.");
            if (nodes == null) throw new IllegalArgumentException("DOMSnapshot.DocumentSnapshot.nodes is necessary field.");
            if (layout == null) throw new IllegalArgumentException("DOMSnapshot.DocumentSnapshot.layout is necessary field.");
            if (textBoxes == null) throw new IllegalArgumentException("DOMSnapshot.DocumentSnapshot.textBoxes is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            documentURL.toJson(strBuilder.append("\"documentURL\":"));
            baseURL.toJson(strBuilder.append(",\"baseURL\":"));
            contentLanguage.toJson(strBuilder.append(",\"contentLanguage\":"));
            encodingName.toJson(strBuilder.append(",\"encodingName\":"));
            publicId.toJson(strBuilder.append(",\"publicId\":"));
            systemId.toJson(strBuilder.append(",\"systemId\":"));
            frameId.toJson(strBuilder.append(",\"frameId\":"));
            nodes.toJson(strBuilder.append(",\"nodes\":"));
            layout.toJson(strBuilder.append(",\"layout\":"));
            textBoxes.toJson(strBuilder.append(",\"textBoxes\":"));
            if (scrollOffsetX != null) strBuilder.append(",\"scrollOffsetX\":").append(scrollOffsetX);
            if (scrollOffsetY != null) strBuilder.append(",\"scrollOffsetY\":").append(scrollOffsetY);
            strBuilder.append('}');
            return strBuilder;
        }
        public DocumentSnapshot() {}
        public DocumentSnapshot(
            @JsonProperty("documentURL")StringIndex documentURL,
            @JsonProperty("baseURL")StringIndex baseURL,
            @JsonProperty("contentLanguage")StringIndex contentLanguage,
            @JsonProperty("encodingName")StringIndex encodingName,
            @JsonProperty("publicId")StringIndex publicId,
            @JsonProperty("systemId")StringIndex systemId,
            @JsonProperty("frameId")StringIndex frameId,
            @JsonProperty("nodes")NodeTreeSnapshot nodes,
            @JsonProperty("layout")LayoutTreeSnapshot layout,
            @JsonProperty("textBoxes")TextBoxSnapshot textBoxes,
            @Nullable @JsonProperty("scrollOffsetX")Double scrollOffsetX,
            @Nullable @JsonProperty("scrollOffsetY")Double scrollOffsetY
        ) {
            this.documentURL = documentURL;
            this.baseURL = baseURL;
            this.contentLanguage = contentLanguage;
            this.encodingName = encodingName;
            this.publicId = publicId;
            this.systemId = systemId;
            this.frameId = frameId;
            this.nodes = nodes;
            this.layout = layout;
            this.textBoxes = textBoxes;
            this.scrollOffsetX = scrollOffsetX;
            this.scrollOffsetY = scrollOffsetY;
        }
    }

    /**Table containing nodes.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class NodeTreeSnapshot implements CommonDomainType {
        /**Parent node index.
        <em>Optional.</em>*/
        private List<Integer> parentIndex;
        /**`Node`'s nodeType.
        <em>Optional.</em>*/
        private List<Integer> nodeType;
        /**`Node`'s nodeName.
        <em>Optional.</em>*/
        private List<StringIndex> nodeName;
        /**`Node`'s nodeValue.
        <em>Optional.</em>*/
        private List<StringIndex> nodeValue;
        /**`Node`'s id, corresponds to DOM.Node.backendNodeId.
        <em>Optional.</em>*/
        private List<DOM.BackendNodeId> backendNodeId;
        /**Attributes of an `Element` node. Flatten name, value pairs.
        <em>Optional.</em>*/
        private List<ArrayOfStrings> attributes;
        /**Only set for textarea elements, contains the text value.
        <em>Optional.</em>*/
        private RareStringData textValue;
        /**Only set for input elements, contains the input's associated text value.
        <em>Optional.</em>*/
        private RareStringData inputValue;
        /**Only set for radio and checkbox input elements, indicates if the element has been checked
        <em>Optional.</em>*/
        private RareBooleanData inputChecked;
        /**Only set for option elements, indicates if the element has been selected
        <em>Optional.</em>*/
        private RareBooleanData optionSelected;
        /**The index of the document in the list of the snapshot documents.
        <em>Optional.</em>*/
        private RareIntegerData contentDocumentIndex;
        /**Type of a pseudo element node.
        <em>Optional.</em>*/
        private RareStringData pseudoType;
        /**Whether this DOM node responds to mouse clicks. This includes nodes that have had click
event listeners attached via JavaScript as well as anchor tags that naturally navigate when
clicked.
        <em>Optional.</em>*/
        private RareBooleanData isClickable;
        /**The selected url for nodes with a srcset attribute.
        <em>Optional.</em>*/
        private RareStringData currentSourceURL;
        /**The url of the script (if any) that generates this node.
        <em>Optional.</em>*/
        private RareStringData originURL;
        public final NodeTreeSnapshot parentIndex(@Nullable List<Integer> parentIndex) { this.parentIndex = parentIndex; return this; }
        public final NodeTreeSnapshot optParentIndex(@Nullable List<Integer> parentIndex) { return parentIndex(parentIndex); }
        public final List<Integer> parentIndex() { return parentIndex; }
        public final List<Integer> getParentIndex() { return parentIndex(); }
        public final NodeTreeSnapshot nodeType(@Nullable List<Integer> nodeType) { this.nodeType = nodeType; return this; }
        public final NodeTreeSnapshot optNodeType(@Nullable List<Integer> nodeType) { return nodeType(nodeType); }
        public final List<Integer> nodeType() { return nodeType; }
        public final List<Integer> getNodeType() { return nodeType(); }
        public final NodeTreeSnapshot nodeName(@Nullable List<StringIndex> nodeName) { this.nodeName = nodeName; return this; }
        public final NodeTreeSnapshot optNodeName(@Nullable List<StringIndex> nodeName) { return nodeName(nodeName); }
        public final List<StringIndex> nodeName() { return nodeName; }
        public final List<StringIndex> getNodeName() { return nodeName(); }
        public final NodeTreeSnapshot nodeValue(@Nullable List<StringIndex> nodeValue) { this.nodeValue = nodeValue; return this; }
        public final NodeTreeSnapshot optNodeValue(@Nullable List<StringIndex> nodeValue) { return nodeValue(nodeValue); }
        public final List<StringIndex> nodeValue() { return nodeValue; }
        public final List<StringIndex> getNodeValue() { return nodeValue(); }
        public final NodeTreeSnapshot backendNodeId(@Nullable List<DOM.BackendNodeId> backendNodeId) { this.backendNodeId = backendNodeId; return this; }
        public final NodeTreeSnapshot optBackendNodeId(@Nullable List<DOM.BackendNodeId> backendNodeId) { return backendNodeId(backendNodeId); }
        public final List<DOM.BackendNodeId> backendNodeId() { return backendNodeId; }
        public final List<DOM.BackendNodeId> getBackendNodeId() { return backendNodeId(); }
        public final NodeTreeSnapshot attributes(@Nullable List<ArrayOfStrings> attributes) { this.attributes = attributes; return this; }
        public final NodeTreeSnapshot optAttributes(@Nullable List<ArrayOfStrings> attributes) { return attributes(attributes); }
        public final List<ArrayOfStrings> attributes() { return attributes; }
        public final List<ArrayOfStrings> getAttributes() { return attributes(); }
        public final NodeTreeSnapshot textValue(@Nullable RareStringData textValue) { this.textValue = textValue; return this; }
        public final NodeTreeSnapshot optTextValue(@Nullable RareStringData textValue) { return textValue(textValue); }
        public final RareStringData textValue() { return textValue; }
        public final RareStringData getTextValue() { return textValue(); }
        public final NodeTreeSnapshot inputValue(@Nullable RareStringData inputValue) { this.inputValue = inputValue; return this; }
        public final NodeTreeSnapshot optInputValue(@Nullable RareStringData inputValue) { return inputValue(inputValue); }
        public final RareStringData inputValue() { return inputValue; }
        public final RareStringData getInputValue() { return inputValue(); }
        public final NodeTreeSnapshot inputChecked(@Nullable RareBooleanData inputChecked) { this.inputChecked = inputChecked; return this; }
        public final NodeTreeSnapshot optInputChecked(@Nullable RareBooleanData inputChecked) { return inputChecked(inputChecked); }
        public final RareBooleanData inputChecked() { return inputChecked; }
        public final RareBooleanData getInputChecked() { return inputChecked(); }
        public final NodeTreeSnapshot optionSelected(@Nullable RareBooleanData optionSelected) { this.optionSelected = optionSelected; return this; }
        public final NodeTreeSnapshot optOptionSelected(@Nullable RareBooleanData optionSelected) { return optionSelected(optionSelected); }
        public final RareBooleanData optionSelected() { return optionSelected; }
        public final RareBooleanData getOptionSelected() { return optionSelected(); }
        public final NodeTreeSnapshot contentDocumentIndex(@Nullable RareIntegerData contentDocumentIndex) { this.contentDocumentIndex = contentDocumentIndex; return this; }
        public final NodeTreeSnapshot optContentDocumentIndex(@Nullable RareIntegerData contentDocumentIndex) { return contentDocumentIndex(contentDocumentIndex); }
        public final RareIntegerData contentDocumentIndex() { return contentDocumentIndex; }
        public final RareIntegerData getContentDocumentIndex() { return contentDocumentIndex(); }
        public final NodeTreeSnapshot pseudoType(@Nullable RareStringData pseudoType) { this.pseudoType = pseudoType; return this; }
        public final NodeTreeSnapshot optPseudoType(@Nullable RareStringData pseudoType) { return pseudoType(pseudoType); }
        public final RareStringData pseudoType() { return pseudoType; }
        public final RareStringData getPseudoType() { return pseudoType(); }
        public final NodeTreeSnapshot isClickable(@Nullable RareBooleanData isClickable) { this.isClickable = isClickable; return this; }
        public final NodeTreeSnapshot optIsClickable(@Nullable RareBooleanData isClickable) { return isClickable(isClickable); }
        public final RareBooleanData isClickable() { return isClickable; }
        public final RareBooleanData getIsClickable() { return isClickable(); }
        public final NodeTreeSnapshot currentSourceURL(@Nullable RareStringData currentSourceURL) { this.currentSourceURL = currentSourceURL; return this; }
        public final NodeTreeSnapshot optCurrentSourceURL(@Nullable RareStringData currentSourceURL) { return currentSourceURL(currentSourceURL); }
        public final RareStringData currentSourceURL() { return currentSourceURL; }
        public final RareStringData getCurrentSourceURL() { return currentSourceURL(); }
        public final NodeTreeSnapshot originURL(@Nullable RareStringData originURL) { this.originURL = originURL; return this; }
        public final NodeTreeSnapshot optOriginURL(@Nullable RareStringData originURL) { return originURL(originURL); }
        public final RareStringData originURL() { return originURL; }
        public final RareStringData getOriginURL() { return originURL(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            if (parentIndex != null) {
                strBuilder.append("\"parentIndex\":[");
                strBuilder.append(parentIndex.get(0));
                for (int i = 1; i < parentIndex.size(); ++i)
                    strBuilder.append(',').append(parentIndex.get(i));
                strBuilder.append(']');
            }
            if (nodeType != null) {
                strBuilder.append(",\"nodeType\":[");
                strBuilder.append(nodeType.get(0));
                for (int i = 1; i < nodeType.size(); ++i)
                    strBuilder.append(',').append(nodeType.get(i));
                strBuilder.append(']');
            }
            if (nodeName != null) {
                strBuilder.append(",\"nodeName\":[");
                nodeName.get(0).toJson(strBuilder);
                for (int i = 1; i < nodeName.size(); ++i)
                    nodeName.get(i).toJson(strBuilder.append(','));
                strBuilder.append(']');
            }
            if (nodeValue != null) {
                strBuilder.append(",\"nodeValue\":[");
                nodeValue.get(0).toJson(strBuilder);
                for (int i = 1; i < nodeValue.size(); ++i)
                    nodeValue.get(i).toJson(strBuilder.append(','));
                strBuilder.append(']');
            }
            if (backendNodeId != null) {
                strBuilder.append(",\"backendNodeId\":[");
                backendNodeId.get(0).toJson(strBuilder);
                for (int i = 1; i < backendNodeId.size(); ++i)
                    backendNodeId.get(i).toJson(strBuilder.append(','));
                strBuilder.append(']');
            }
            if (attributes != null) {
                strBuilder.append(",\"attributes\":[");
                attributes.get(0).toJson(strBuilder);
                for (int i = 1; i < attributes.size(); ++i)
                    attributes.get(i).toJson(strBuilder.append(','));
                strBuilder.append(']');
            }
            if (textValue != null) textValue.toJson(strBuilder.append(",\"textValue\":"));
            if (inputValue != null) inputValue.toJson(strBuilder.append(",\"inputValue\":"));
            if (inputChecked != null) inputChecked.toJson(strBuilder.append(",\"inputChecked\":"));
            if (optionSelected != null) optionSelected.toJson(strBuilder.append(",\"optionSelected\":"));
            if (contentDocumentIndex != null) contentDocumentIndex.toJson(strBuilder.append(",\"contentDocumentIndex\":"));
            if (pseudoType != null) pseudoType.toJson(strBuilder.append(",\"pseudoType\":"));
            if (isClickable != null) isClickable.toJson(strBuilder.append(",\"isClickable\":"));
            if (currentSourceURL != null) currentSourceURL.toJson(strBuilder.append(",\"currentSourceURL\":"));
            if (originURL != null) originURL.toJson(strBuilder.append(",\"originURL\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public NodeTreeSnapshot() {}
        public NodeTreeSnapshot(
            @Nullable @JsonProperty("parentIndex")List<Integer> parentIndex,
            @Nullable @JsonProperty("nodeType")List<Integer> nodeType,
            @Nullable @JsonProperty("nodeName")List<StringIndex> nodeName,
            @Nullable @JsonProperty("nodeValue")List<StringIndex> nodeValue,
            @Nullable @JsonProperty("backendNodeId")List<DOM.BackendNodeId> backendNodeId,
            @Nullable @JsonProperty("attributes")List<ArrayOfStrings> attributes,
            @Nullable @JsonProperty("textValue")RareStringData textValue,
            @Nullable @JsonProperty("inputValue")RareStringData inputValue,
            @Nullable @JsonProperty("inputChecked")RareBooleanData inputChecked,
            @Nullable @JsonProperty("optionSelected")RareBooleanData optionSelected,
            @Nullable @JsonProperty("contentDocumentIndex")RareIntegerData contentDocumentIndex,
            @Nullable @JsonProperty("pseudoType")RareStringData pseudoType,
            @Nullable @JsonProperty("isClickable")RareBooleanData isClickable,
            @Nullable @JsonProperty("currentSourceURL")RareStringData currentSourceURL,
            @Nullable @JsonProperty("originURL")RareStringData originURL
        ) {
            this.parentIndex = parentIndex;
            this.nodeType = nodeType;
            this.nodeName = nodeName;
            this.nodeValue = nodeValue;
            this.backendNodeId = backendNodeId;
            this.attributes = attributes;
            this.textValue = textValue;
            this.inputValue = inputValue;
            this.inputChecked = inputChecked;
            this.optionSelected = optionSelected;
            this.contentDocumentIndex = contentDocumentIndex;
            this.pseudoType = pseudoType;
            this.isClickable = isClickable;
            this.currentSourceURL = currentSourceURL;
            this.originURL = originURL;
        }
    }

    /**Details of an element in the DOM tree with a LayoutObject.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class LayoutTreeSnapshot implements CommonDomainType {
        /**The index of the related DOM node in the `domNodes` array returned by `getSnapshot`.*/
        private List<Integer> nodeIndex;
        /**Index into the `computedStyles` array returned by `captureSnapshot`.*/
        private List<ArrayOfStrings> styles;
        /**The absolute position bounding box.*/
        private List<Rectangle> bounds;
        /**Contents of the LayoutText, if any.*/
        private List<StringIndex> text;
        /**Stacking context information.*/
        private RareBooleanData stackingContexts;
        public final LayoutTreeSnapshot nodeIndex(List<Integer> nodeIndex) { this.nodeIndex = nodeIndex; return this; }
        public final LayoutTreeSnapshot setNodeIndex(List<Integer> nodeIndex) { return nodeIndex(nodeIndex); }
        public final List<Integer> nodeIndex() { return nodeIndex; }
        public final List<Integer> getNodeIndex() { return nodeIndex(); }
        public final LayoutTreeSnapshot styles(List<ArrayOfStrings> styles) { this.styles = styles; return this; }
        public final LayoutTreeSnapshot setStyles(List<ArrayOfStrings> styles) { return styles(styles); }
        public final List<ArrayOfStrings> styles() { return styles; }
        public final List<ArrayOfStrings> getStyles() { return styles(); }
        public final LayoutTreeSnapshot bounds(List<Rectangle> bounds) { this.bounds = bounds; return this; }
        public final LayoutTreeSnapshot setBounds(List<Rectangle> bounds) { return bounds(bounds); }
        public final List<Rectangle> bounds() { return bounds; }
        public final List<Rectangle> getBounds() { return bounds(); }
        public final LayoutTreeSnapshot text(List<StringIndex> text) { this.text = text; return this; }
        public final LayoutTreeSnapshot setText(List<StringIndex> text) { return text(text); }
        public final List<StringIndex> text() { return text; }
        public final List<StringIndex> getText() { return text(); }
        public final LayoutTreeSnapshot stackingContexts(RareBooleanData stackingContexts) { this.stackingContexts = stackingContexts; return this; }
        public final LayoutTreeSnapshot setStackingContexts(RareBooleanData stackingContexts) { return stackingContexts(stackingContexts); }
        public final RareBooleanData stackingContexts() { return stackingContexts; }
        public final RareBooleanData getStackingContexts() { return stackingContexts(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (nodeIndex == null) throw new IllegalArgumentException("DOMSnapshot.LayoutTreeSnapshot.nodeIndex is necessary field.");
            if (styles == null) throw new IllegalArgumentException("DOMSnapshot.LayoutTreeSnapshot.styles is necessary field.");
            if (bounds == null) throw new IllegalArgumentException("DOMSnapshot.LayoutTreeSnapshot.bounds is necessary field.");
            if (text == null) throw new IllegalArgumentException("DOMSnapshot.LayoutTreeSnapshot.text is necessary field.");
            if (stackingContexts == null) throw new IllegalArgumentException("DOMSnapshot.LayoutTreeSnapshot.stackingContexts is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
                        strBuilder.append("\"nodeIndex\":[");
            strBuilder.append(nodeIndex.get(0));
            for (int i = 1; i < nodeIndex.size(); ++i)
                strBuilder.append(',').append(nodeIndex.get(i));
            strBuilder.append(']');
                        strBuilder.append(",\"styles\":[");
            styles.get(0).toJson(strBuilder);
            for (int i = 1; i < styles.size(); ++i)
                styles.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
                        strBuilder.append(",\"bounds\":[");
            bounds.get(0).toJson(strBuilder);
            for (int i = 1; i < bounds.size(); ++i)
                bounds.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
                        strBuilder.append(",\"text\":[");
            text.get(0).toJson(strBuilder);
            for (int i = 1; i < text.size(); ++i)
                text.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            stackingContexts.toJson(strBuilder.append(",\"stackingContexts\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public LayoutTreeSnapshot() {}
        public LayoutTreeSnapshot(
            @JsonProperty("nodeIndex")List<Integer> nodeIndex,
            @JsonProperty("styles")List<ArrayOfStrings> styles,
            @JsonProperty("bounds")List<Rectangle> bounds,
            @JsonProperty("text")List<StringIndex> text,
            @JsonProperty("stackingContexts")RareBooleanData stackingContexts
        ) {
            this.nodeIndex = nodeIndex;
            this.styles = styles;
            this.bounds = bounds;
            this.text = text;
            this.stackingContexts = stackingContexts;
        }
    }

    /**Details of post layout rendered text positions. The exact layout should not be regarded as
stable and may change between versions.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class TextBoxSnapshot implements CommonDomainType {
        /**Intex of th elayout tree node that owns this box collection.*/
        private List<Integer> layoutIndex;
        /**The absolute position bounding box.*/
        private List<Rectangle> bounds;
        /**The starting index in characters, for this post layout textbox substring. Characters that
would be represented as a surrogate pair in UTF-16 have length 2.*/
        private List<Integer> start;
        /**The number of characters in this post layout textbox substring. Characters that would be
represented as a surrogate pair in UTF-16 have length 2.*/
        private List<Integer> length;
        public final TextBoxSnapshot layoutIndex(List<Integer> layoutIndex) { this.layoutIndex = layoutIndex; return this; }
        public final TextBoxSnapshot setLayoutIndex(List<Integer> layoutIndex) { return layoutIndex(layoutIndex); }
        public final List<Integer> layoutIndex() { return layoutIndex; }
        public final List<Integer> getLayoutIndex() { return layoutIndex(); }
        public final TextBoxSnapshot bounds(List<Rectangle> bounds) { this.bounds = bounds; return this; }
        public final TextBoxSnapshot setBounds(List<Rectangle> bounds) { return bounds(bounds); }
        public final List<Rectangle> bounds() { return bounds; }
        public final List<Rectangle> getBounds() { return bounds(); }
        public final TextBoxSnapshot start(List<Integer> start) { this.start = start; return this; }
        public final TextBoxSnapshot setStart(List<Integer> start) { return start(start); }
        public final List<Integer> start() { return start; }
        public final List<Integer> getStart() { return start(); }
        public final TextBoxSnapshot length(List<Integer> length) { this.length = length; return this; }
        public final TextBoxSnapshot setLength(List<Integer> length) { return length(length); }
        public final List<Integer> length() { return length; }
        public final List<Integer> getLength() { return length(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (layoutIndex == null) throw new IllegalArgumentException("DOMSnapshot.TextBoxSnapshot.layoutIndex is necessary field.");
            if (bounds == null) throw new IllegalArgumentException("DOMSnapshot.TextBoxSnapshot.bounds is necessary field.");
            if (start == null) throw new IllegalArgumentException("DOMSnapshot.TextBoxSnapshot.start is necessary field.");
            if (length == null) throw new IllegalArgumentException("DOMSnapshot.TextBoxSnapshot.length is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
                        strBuilder.append("\"layoutIndex\":[");
            strBuilder.append(layoutIndex.get(0));
            for (int i = 1; i < layoutIndex.size(); ++i)
                strBuilder.append(',').append(layoutIndex.get(i));
            strBuilder.append(']');
                        strBuilder.append(",\"bounds\":[");
            bounds.get(0).toJson(strBuilder);
            for (int i = 1; i < bounds.size(); ++i)
                bounds.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
                        strBuilder.append(",\"start\":[");
            strBuilder.append(start.get(0));
            for (int i = 1; i < start.size(); ++i)
                strBuilder.append(',').append(start.get(i));
            strBuilder.append(']');
                        strBuilder.append(",\"length\":[");
            strBuilder.append(length.get(0));
            for (int i = 1; i < length.size(); ++i)
                strBuilder.append(',').append(length.get(i));
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public TextBoxSnapshot() {}
        public TextBoxSnapshot(
            @JsonProperty("layoutIndex")List<Integer> layoutIndex,
            @JsonProperty("bounds")List<Rectangle> bounds,
            @JsonProperty("start")List<Integer> start,
            @JsonProperty("length")List<Integer> length
        ) {
            this.layoutIndex = layoutIndex;
            this.bounds = bounds;
            this.start = start;
            this.length = length;
        }
    }
    /**Disables DOM snapshot agent for the given page.*/
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
            return super.call("DOMSnapshot.disable", DisableResult.class,
                (code, msg)->new DisableResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<DisableResult> callAsync() {
            return super.callAsync("DOMSnapshot.disable", DisableResult.class,
                (code, msg)->new DisableResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<DisableResult> callAsync(Executor exec) {
            return super.callAsync("DOMSnapshot.disable", DisableResult.class,
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
    /**Enables DOM snapshot agent for the given page.*/
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
            return super.call("DOMSnapshot.enable", EnableResult.class,
                (code, msg)->new EnableResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<EnableResult> callAsync() {
            return super.callAsync("DOMSnapshot.enable", EnableResult.class,
                (code, msg)->new EnableResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<EnableResult> callAsync(Executor exec) {
            return super.callAsync("DOMSnapshot.enable", EnableResult.class,
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
    /**Returns a document snapshot, including the full DOM tree of the root node (including iframes,
template contents, and imported documents) in a flattened array, as well as layout and
white-listed computed style information for the nodes. Shadow DOM in the returned DOM tree is
flattened.
    @Deprecated*/
    public GetSnapshotParameter getSnapshot() { final GetSnapshotParameter v = new GetSnapshotParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of getSnapshot.
    @Deprecated*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetSnapshotParameter extends CommandBase {
        /**Whitelist of computed styles to return.*/
        private List<String> computedStyleWhitelist;
        /**Whether or not to retrieve details of DOM listeners (default false).
        <em>Optional.</em>*/
        private Boolean includeEventListeners;
        /**Whether to determine and include the paint order index of LayoutTreeNodes (default false).
        <em>Optional.</em>*/
        private Boolean includePaintOrder;
        /**Whether to include UA shadow tree in the snapshot (default false).
        <em>Optional.</em>*/
        private Boolean includeUserAgentShadowTree;
        public final GetSnapshotParameter computedStyleWhitelist(List<String> computedStyleWhitelist) { this.computedStyleWhitelist = computedStyleWhitelist; return this; }
        public final GetSnapshotParameter setComputedStyleWhitelist(List<String> computedStyleWhitelist) { return computedStyleWhitelist(computedStyleWhitelist); }
        public final List<String> computedStyleWhitelist() { return computedStyleWhitelist; }
        public final List<String> getComputedStyleWhitelist() { return computedStyleWhitelist(); }
        public final GetSnapshotParameter includeEventListeners(@Nullable Boolean includeEventListeners) { this.includeEventListeners = includeEventListeners; return this; }
        public final GetSnapshotParameter optIncludeEventListeners(@Nullable Boolean includeEventListeners) { return includeEventListeners(includeEventListeners); }
        public final Boolean includeEventListeners() { return includeEventListeners; }
        public final Boolean getIncludeEventListeners() { return includeEventListeners(); }
        public final GetSnapshotParameter includePaintOrder(@Nullable Boolean includePaintOrder) { this.includePaintOrder = includePaintOrder; return this; }
        public final GetSnapshotParameter optIncludePaintOrder(@Nullable Boolean includePaintOrder) { return includePaintOrder(includePaintOrder); }
        public final Boolean includePaintOrder() { return includePaintOrder; }
        public final Boolean getIncludePaintOrder() { return includePaintOrder(); }
        public final GetSnapshotParameter includeUserAgentShadowTree(@Nullable Boolean includeUserAgentShadowTree) { this.includeUserAgentShadowTree = includeUserAgentShadowTree; return this; }
        public final GetSnapshotParameter optIncludeUserAgentShadowTree(@Nullable Boolean includeUserAgentShadowTree) { return includeUserAgentShadowTree(includeUserAgentShadowTree); }
        public final Boolean includeUserAgentShadowTree() { return includeUserAgentShadowTree; }
        public final Boolean getIncludeUserAgentShadowTree() { return includeUserAgentShadowTree(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (computedStyleWhitelist == null) throw new IllegalArgumentException("DOMSnapshot.GetSnapshotParameter.computedStyleWhitelist is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
                        strBuilder.append("\"computedStyleWhitelist\":[");
            strBuilder.append('"').append(DomainBase.escapeJson(computedStyleWhitelist.get(0))).append('"');
            for (int i = 1; i < computedStyleWhitelist.size(); ++i)
                strBuilder.append(",\"").append(DomainBase.escapeJson(computedStyleWhitelist.get(i))).append('"');
            strBuilder.append(']');
            if (includeEventListeners != null) strBuilder.append(",\"includeEventListeners\":").append(includeEventListeners);
            if (includePaintOrder != null) strBuilder.append(",\"includePaintOrder\":").append(includePaintOrder);
            if (includeUserAgentShadowTree != null) strBuilder.append(",\"includeUserAgentShadowTree\":").append(includeUserAgentShadowTree);
            strBuilder.append('}');
            return strBuilder;
        }
        public GetSnapshotParameter() {}
        public GetSnapshotParameter(
            @JsonProperty("computedStyleWhitelist")List<String> computedStyleWhitelist,
            @Nullable @JsonProperty("includeEventListeners")Boolean includeEventListeners,
            @Nullable @JsonProperty("includePaintOrder")Boolean includePaintOrder,
            @Nullable @JsonProperty("includeUserAgentShadowTree")Boolean includeUserAgentShadowTree
        ) {
            this();
            this.computedStyleWhitelist = computedStyleWhitelist;
            this.includeEventListeners = includeEventListeners;
            this.includePaintOrder = includePaintOrder;
            this.includeUserAgentShadowTree = includeUserAgentShadowTree;
        }
        public CompletableFuture<GetSnapshotResult> call() {
            return super.call("DOMSnapshot.getSnapshot", GetSnapshotResult.class,
                (code, msg)->new GetSnapshotResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetSnapshotResult> callAsync() {
            return super.callAsync("DOMSnapshot.getSnapshot", GetSnapshotResult.class,
                (code, msg)->new GetSnapshotResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetSnapshotResult> callAsync(Executor exec) {
            return super.callAsync("DOMSnapshot.getSnapshot", GetSnapshotResult.class,
                (code, msg)->new GetSnapshotResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of getSnapshot.
    @Deprecated*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetSnapshotResult extends ResultBase {
        /**The nodes in the DOM tree. The DOMNode at index 0 corresponds to the root document.*/
        private final List<DOMNode> domNodes;
        /**The nodes in the layout tree.*/
        private final List<LayoutTreeNode> layoutTreeNodes;
        /**Whitelisted ComputedStyle properties for each node in the layout tree.*/
        private final List<ComputedStyle> computedStyles;
        public final List<DOMNode> domNodes() { return domNodes; }
        public final List<DOMNode> getDomNodes() { return domNodes(); }
        public final List<LayoutTreeNode> layoutTreeNodes() { return layoutTreeNodes; }
        public final List<LayoutTreeNode> getLayoutTreeNodes() { return layoutTreeNodes(); }
        public final List<ComputedStyle> computedStyles() { return computedStyles; }
        public final List<ComputedStyle> getComputedStyles() { return computedStyles(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
                        strBuilder.append("\"domNodes\":[");
            domNodes.get(0).toJson(strBuilder);
            for (int i = 1; i < domNodes.size(); ++i)
                domNodes.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
                        strBuilder.append(",\"layoutTreeNodes\":[");
            layoutTreeNodes.get(0).toJson(strBuilder);
            for (int i = 1; i < layoutTreeNodes.size(); ++i)
                layoutTreeNodes.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
                        strBuilder.append(",\"computedStyles\":[");
            computedStyles.get(0).toJson(strBuilder);
            for (int i = 1; i < computedStyles.size(); ++i)
                computedStyles.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public GetSnapshotResult(
            @JsonProperty("domNodes")List<DOMNode> domNodes,
            @JsonProperty("layoutTreeNodes")List<LayoutTreeNode> layoutTreeNodes,
            @JsonProperty("computedStyles")List<ComputedStyle> computedStyles
        ) {
            this.domNodes = domNodes;
            this.layoutTreeNodes = layoutTreeNodes;
            this.computedStyles = computedStyles;
        }
        public GetSnapshotResult(ResultBase.FailedResult e) {
            super(e);
            domNodes = null;
            layoutTreeNodes = null;
            computedStyles = null;
        }
    }
    /**Returns a document snapshot, including the full DOM tree of the root node (including iframes,
template contents, and imported documents) in a flattened array, as well as layout and
white-listed computed style information for the nodes. Shadow DOM in the returned DOM tree is
flattened.*/
    public CaptureSnapshotParameter captureSnapshot() { final CaptureSnapshotParameter v = new CaptureSnapshotParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of captureSnapshot.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class CaptureSnapshotParameter extends CommandBase {
        /**Whitelist of computed styles to return.*/
        private List<String> computedStyles;
        public final CaptureSnapshotParameter computedStyles(List<String> computedStyles) { this.computedStyles = computedStyles; return this; }
        public final CaptureSnapshotParameter setComputedStyles(List<String> computedStyles) { return computedStyles(computedStyles); }
        public final List<String> computedStyles() { return computedStyles; }
        public final List<String> getComputedStyles() { return computedStyles(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (computedStyles == null) throw new IllegalArgumentException("DOMSnapshot.CaptureSnapshotParameter.computedStyles is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
                        strBuilder.append("\"computedStyles\":[");
            strBuilder.append('"').append(DomainBase.escapeJson(computedStyles.get(0))).append('"');
            for (int i = 1; i < computedStyles.size(); ++i)
                strBuilder.append(",\"").append(DomainBase.escapeJson(computedStyles.get(i))).append('"');
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public CaptureSnapshotParameter() {}
        public CaptureSnapshotParameter(
            @JsonProperty("computedStyles")List<String> computedStyles
        ) {
            this();
            this.computedStyles = computedStyles;
        }
        public CompletableFuture<CaptureSnapshotResult> call() {
            return super.call("DOMSnapshot.captureSnapshot", CaptureSnapshotResult.class,
                (code, msg)->new CaptureSnapshotResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<CaptureSnapshotResult> callAsync() {
            return super.callAsync("DOMSnapshot.captureSnapshot", CaptureSnapshotResult.class,
                (code, msg)->new CaptureSnapshotResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<CaptureSnapshotResult> callAsync(Executor exec) {
            return super.callAsync("DOMSnapshot.captureSnapshot", CaptureSnapshotResult.class,
                (code, msg)->new CaptureSnapshotResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of captureSnapshot.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class CaptureSnapshotResult extends ResultBase {
        /**The nodes in the DOM tree. The DOMNode at index 0 corresponds to the root document.*/
        private final List<DocumentSnapshot> documents;
        /**Shared string table that all string properties refer to with indexes.*/
        private final List<String> strings;
        public final List<DocumentSnapshot> documents() { return documents; }
        public final List<DocumentSnapshot> getDocuments() { return documents(); }
        public final List<String> strings() { return strings; }
        public final List<String> getStrings() { return strings(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
                        strBuilder.append("\"documents\":[");
            documents.get(0).toJson(strBuilder);
            for (int i = 1; i < documents.size(); ++i)
                documents.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
                        strBuilder.append(",\"strings\":[");
            strBuilder.append('"').append(DomainBase.escapeJson(strings.get(0))).append('"');
            for (int i = 1; i < strings.size(); ++i)
                strBuilder.append(",\"").append(DomainBase.escapeJson(strings.get(i))).append('"');
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public CaptureSnapshotResult(
            @JsonProperty("documents")List<DocumentSnapshot> documents,
            @JsonProperty("strings")List<String> strings
        ) {
            this.documents = documents;
            this.strings = strings;
        }
        public CaptureSnapshotResult(ResultBase.FailedResult e) {
            super(e);
            documents = null;
            strings = null;
        }
    }
}

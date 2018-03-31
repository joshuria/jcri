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
        /**Index of the imported document's node of a link element in the `domNodes` array returned by
`getSnapshot`, if any.
        <em>Optional.</em>*/
        private Integer importedDocumentIndex;
        /**Index of the content node of a template element in the `domNodes` array returned by
`getSnapshot`.
        <em>Optional.</em>*/
        private Integer templateContentIndex;
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
        public final DOMNode importedDocumentIndex(@Nullable Integer importedDocumentIndex) { this.importedDocumentIndex = importedDocumentIndex; return this; }
        public final DOMNode optImportedDocumentIndex(@Nullable Integer importedDocumentIndex) { return importedDocumentIndex(importedDocumentIndex); }
        public final Integer importedDocumentIndex() { return importedDocumentIndex; }
        public final Integer getImportedDocumentIndex() { return importedDocumentIndex(); }
        public final DOMNode templateContentIndex(@Nullable Integer templateContentIndex) { this.templateContentIndex = templateContentIndex; return this; }
        public final DOMNode optTemplateContentIndex(@Nullable Integer templateContentIndex) { return templateContentIndex(templateContentIndex); }
        public final Integer templateContentIndex() { return templateContentIndex; }
        public final Integer getTemplateContentIndex() { return templateContentIndex(); }
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
            strBuilder.append(",\"nodeName\":").append('"').append(DomainBase.escapeQuote(nodeName)).append('"');
            strBuilder.append(",\"nodeValue\":").append('"').append(DomainBase.escapeQuote(nodeValue)).append('"');
            if (textValue != null) strBuilder.append(",\"textValue\":").append('"').append(DomainBase.escapeQuote(textValue)).append('"');
            if (inputValue != null) strBuilder.append(",\"inputValue\":").append('"').append(DomainBase.escapeQuote(inputValue)).append('"');
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
            if (documentURL != null) strBuilder.append(",\"documentURL\":").append('"').append(DomainBase.escapeQuote(documentURL)).append('"');
            if (baseURL != null) strBuilder.append(",\"baseURL\":").append('"').append(DomainBase.escapeQuote(baseURL)).append('"');
            if (contentLanguage != null) strBuilder.append(",\"contentLanguage\":").append('"').append(DomainBase.escapeQuote(contentLanguage)).append('"');
            if (documentEncoding != null) strBuilder.append(",\"documentEncoding\":").append('"').append(DomainBase.escapeQuote(documentEncoding)).append('"');
            if (publicId != null) strBuilder.append(",\"publicId\":").append('"').append(DomainBase.escapeQuote(publicId)).append('"');
            if (systemId != null) strBuilder.append(",\"systemId\":").append('"').append(DomainBase.escapeQuote(systemId)).append('"');
            if (frameId != null) frameId.toJson(strBuilder.append(",\"frameId\":"));
            if (contentDocumentIndex != null) strBuilder.append(",\"contentDocumentIndex\":").append(contentDocumentIndex);
            if (importedDocumentIndex != null) strBuilder.append(",\"importedDocumentIndex\":").append(importedDocumentIndex);
            if (templateContentIndex != null) strBuilder.append(",\"templateContentIndex\":").append(templateContentIndex);
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
            if (currentSourceURL != null) strBuilder.append(",\"currentSourceURL\":").append('"').append(DomainBase.escapeQuote(currentSourceURL)).append('"');
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
            @Nullable @JsonProperty("importedDocumentIndex")Integer importedDocumentIndex,
            @Nullable @JsonProperty("templateContentIndex")Integer templateContentIndex,
            @Nullable @JsonProperty("pseudoType")DOM.PseudoType pseudoType,
            @Nullable @JsonProperty("shadowRootType")DOM.ShadowRootType shadowRootType,
            @Nullable @JsonProperty("isClickable")Boolean isClickable,
            @Nullable @JsonProperty("eventListeners")List<DOMDebugger.EventListener> eventListeners,
            @Nullable @JsonProperty("currentSourceURL")String currentSourceURL
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
            this.importedDocumentIndex = importedDocumentIndex;
            this.templateContentIndex = templateContentIndex;
            this.pseudoType = pseudoType;
            this.shadowRootType = shadowRootType;
            this.isClickable = isClickable;
            this.eventListeners = eventListeners;
            this.currentSourceURL = currentSourceURL;
        }
    }

    /**Details of post layout rendered text positions. The exact layout should not be regarded as
stable and may change between versions.*/
    @ParametersAreNonnullByDefault public static class InlineTextBox implements CommonDomainType {
        /**The absolute position bounding box.*/
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
    @ParametersAreNonnullByDefault public static class LayoutTreeNode implements CommonDomainType {
        /**The index of the related DOM node in the `domNodes` array returned by `getSnapshot`.*/
        private Integer domNodeIndex;
        /**The absolute position bounding box.*/
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
            if (layoutText != null) strBuilder.append(",\"layoutText\":").append('"').append(DomainBase.escapeQuote(layoutText)).append('"');
            if (inlineTextNodes != null) {
                strBuilder.append(",\"inlineTextNodes\":[");
                inlineTextNodes.get(0).toJson(strBuilder);
                for (int i = 1; i < inlineTextNodes.size(); ++i)
                    inlineTextNodes.get(i).toJson(strBuilder.append(','));
                strBuilder.append(']');
            }
            if (styleIndex != null) strBuilder.append(",\"styleIndex\":").append(styleIndex);
            if (paintOrder != null) strBuilder.append(",\"paintOrder\":").append(paintOrder);
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
            @Nullable @JsonProperty("paintOrder")Integer paintOrder
        ) {
            this.domNodeIndex = domNodeIndex;
            this.boundingBox = boundingBox;
            this.layoutText = layoutText;
            this.inlineTextNodes = inlineTextNodes;
            this.styleIndex = styleIndex;
            this.paintOrder = paintOrder;
        }
    }

    /**A subset of the full ComputedStyle as defined by the request whitelist.*/
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
            strBuilder.append("\"name\":").append('"').append(DomainBase.escapeQuote(name)).append('"');
            strBuilder.append(",\"value\":").append('"').append(DomainBase.escapeQuote(value)).append('"');
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
    /**Returns a document snapshot, including the full DOM tree of the root node (including iframes,
template contents, and imported documents) in a flattened array, as well as layout and
white-listed computed style information for the nodes. Shadow DOM in the returned DOM tree is
flattened.*/
    public GetSnapshotParameter getSnapshot() { final GetSnapshotParameter v = new GetSnapshotParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of getSnapshot.*/
    @ParametersAreNonnullByDefault public static class GetSnapshotParameter extends CommandBase {
        /**Whitelist of computed styles to return.*/
        private List<String> computedStyleWhitelist;
        /**Whether or not to retrieve details of DOM listeners (default false).
        <em>Optional.</em>*/
        private Boolean includeEventListeners;
        /**Whether to determine and include the paint order index of LayoutTreeNodes (default false).
        <em>Optional.</em>*/
        private Boolean includePaintOrder;
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
            strBuilder.append('"').append(DomainBase.escapeQuote(computedStyleWhitelist.get(0))).append('"');
            for (int i = 1; i < computedStyleWhitelist.size(); ++i)
                strBuilder.append(",\"").append(DomainBase.escapeQuote(computedStyleWhitelist.get(i))).append('"');
            strBuilder.append(']');
            if (includeEventListeners != null) strBuilder.append(",\"includeEventListeners\":").append(includeEventListeners);
            if (includePaintOrder != null) strBuilder.append(",\"includePaintOrder\":").append(includePaintOrder);
            strBuilder.append('}');
            return strBuilder;
        }
        public GetSnapshotParameter() {}
        public GetSnapshotParameter(
            @JsonProperty("computedStyleWhitelist")List<String> computedStyleWhitelist,
            @Nullable @JsonProperty("includeEventListeners")Boolean includeEventListeners,
            @Nullable @JsonProperty("includePaintOrder")Boolean includePaintOrder
        ) {
            this();
            this.computedStyleWhitelist = computedStyleWhitelist;
            this.includeEventListeners = includeEventListeners;
            this.includePaintOrder = includePaintOrder;
        }
        public CompletableFuture<GetSnapshotResult> call() {
            return super.call("DOMSnapshot.getSnapshot", GetSnapshotResult.class,
                (code, msg)->new GetSnapshotResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetSnapshotResult> call(Executor exec) {
            return super.call("DOMSnapshot.getSnapshot", GetSnapshotResult.class,
                (code, msg)->new GetSnapshotResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of getSnapshot.*/
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
}

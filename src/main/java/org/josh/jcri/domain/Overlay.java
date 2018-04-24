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

/**This domain provides various functionality related to drawing atop the inspected page.
<p>From: browser_protocol.json</p>
<p>Protocol version: 1.3</p>
<p><strong>Experimental.</strong></p>
 @see DOM
 @see Page
 @see Runtime
 @author Joshua*/
@ParametersAreNonnullByDefault public class Overlay extends DomainBase {
    public Overlay(EventCenter evt, WebSocket ws) { super(evt, ws); }

    /**Configuration data for the highlighting of page elements.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class HighlightConfig implements CommonDomainType {
        /**Whether the node info tooltip should be shown (default: false).
        <em>Optional.</em>*/
        private Boolean showInfo;
        /**Whether the rulers should be shown (default: false).
        <em>Optional.</em>*/
        private Boolean showRulers;
        /**Whether the extension lines from node to the rulers should be shown (default: false).
        <em>Optional.</em>*/
        private Boolean showExtensionLines;
        /**&lt;No document in protocol.&gt;
        <em>Optional.</em>*/
        private Boolean displayAsMaterial;
        /**The content box highlight fill color (default: transparent).
        <em>Optional.</em>*/
        private DOM.RGBA contentColor;
        /**The padding highlight fill color (default: transparent).
        <em>Optional.</em>*/
        private DOM.RGBA paddingColor;
        /**The border highlight fill color (default: transparent).
        <em>Optional.</em>*/
        private DOM.RGBA borderColor;
        /**The margin highlight fill color (default: transparent).
        <em>Optional.</em>*/
        private DOM.RGBA marginColor;
        /**The event target element highlight fill color (default: transparent).
        <em>Optional.</em>*/
        private DOM.RGBA eventTargetColor;
        /**The shape outside fill color (default: transparent).
        <em>Optional.</em>*/
        private DOM.RGBA shapeColor;
        /**The shape margin fill color (default: transparent).
        <em>Optional.</em>*/
        private DOM.RGBA shapeMarginColor;
        /**Selectors to highlight relevant nodes.
        <em>Optional.</em>*/
        private String selectorList;
        /**The grid layout color (default: transparent).
        <em>Optional.</em>*/
        private DOM.RGBA cssGridColor;
        public final HighlightConfig showInfo(@Nullable Boolean showInfo) { this.showInfo = showInfo; return this; }
        public final HighlightConfig optShowInfo(@Nullable Boolean showInfo) { return showInfo(showInfo); }
        public final Boolean showInfo() { return showInfo; }
        public final Boolean getShowInfo() { return showInfo(); }
        public final HighlightConfig showRulers(@Nullable Boolean showRulers) { this.showRulers = showRulers; return this; }
        public final HighlightConfig optShowRulers(@Nullable Boolean showRulers) { return showRulers(showRulers); }
        public final Boolean showRulers() { return showRulers; }
        public final Boolean getShowRulers() { return showRulers(); }
        public final HighlightConfig showExtensionLines(@Nullable Boolean showExtensionLines) { this.showExtensionLines = showExtensionLines; return this; }
        public final HighlightConfig optShowExtensionLines(@Nullable Boolean showExtensionLines) { return showExtensionLines(showExtensionLines); }
        public final Boolean showExtensionLines() { return showExtensionLines; }
        public final Boolean getShowExtensionLines() { return showExtensionLines(); }
        public final HighlightConfig displayAsMaterial(@Nullable Boolean displayAsMaterial) { this.displayAsMaterial = displayAsMaterial; return this; }
        public final HighlightConfig optDisplayAsMaterial(@Nullable Boolean displayAsMaterial) { return displayAsMaterial(displayAsMaterial); }
        public final Boolean displayAsMaterial() { return displayAsMaterial; }
        public final Boolean getDisplayAsMaterial() { return displayAsMaterial(); }
        public final HighlightConfig contentColor(@Nullable DOM.RGBA contentColor) { this.contentColor = contentColor; return this; }
        public final HighlightConfig optContentColor(@Nullable DOM.RGBA contentColor) { return contentColor(contentColor); }
        public final DOM.RGBA contentColor() { return contentColor; }
        public final DOM.RGBA getContentColor() { return contentColor(); }
        public final HighlightConfig paddingColor(@Nullable DOM.RGBA paddingColor) { this.paddingColor = paddingColor; return this; }
        public final HighlightConfig optPaddingColor(@Nullable DOM.RGBA paddingColor) { return paddingColor(paddingColor); }
        public final DOM.RGBA paddingColor() { return paddingColor; }
        public final DOM.RGBA getPaddingColor() { return paddingColor(); }
        public final HighlightConfig borderColor(@Nullable DOM.RGBA borderColor) { this.borderColor = borderColor; return this; }
        public final HighlightConfig optBorderColor(@Nullable DOM.RGBA borderColor) { return borderColor(borderColor); }
        public final DOM.RGBA borderColor() { return borderColor; }
        public final DOM.RGBA getBorderColor() { return borderColor(); }
        public final HighlightConfig marginColor(@Nullable DOM.RGBA marginColor) { this.marginColor = marginColor; return this; }
        public final HighlightConfig optMarginColor(@Nullable DOM.RGBA marginColor) { return marginColor(marginColor); }
        public final DOM.RGBA marginColor() { return marginColor; }
        public final DOM.RGBA getMarginColor() { return marginColor(); }
        public final HighlightConfig eventTargetColor(@Nullable DOM.RGBA eventTargetColor) { this.eventTargetColor = eventTargetColor; return this; }
        public final HighlightConfig optEventTargetColor(@Nullable DOM.RGBA eventTargetColor) { return eventTargetColor(eventTargetColor); }
        public final DOM.RGBA eventTargetColor() { return eventTargetColor; }
        public final DOM.RGBA getEventTargetColor() { return eventTargetColor(); }
        public final HighlightConfig shapeColor(@Nullable DOM.RGBA shapeColor) { this.shapeColor = shapeColor; return this; }
        public final HighlightConfig optShapeColor(@Nullable DOM.RGBA shapeColor) { return shapeColor(shapeColor); }
        public final DOM.RGBA shapeColor() { return shapeColor; }
        public final DOM.RGBA getShapeColor() { return shapeColor(); }
        public final HighlightConfig shapeMarginColor(@Nullable DOM.RGBA shapeMarginColor) { this.shapeMarginColor = shapeMarginColor; return this; }
        public final HighlightConfig optShapeMarginColor(@Nullable DOM.RGBA shapeMarginColor) { return shapeMarginColor(shapeMarginColor); }
        public final DOM.RGBA shapeMarginColor() { return shapeMarginColor; }
        public final DOM.RGBA getShapeMarginColor() { return shapeMarginColor(); }
        public final HighlightConfig selectorList(@Nullable String selectorList) { this.selectorList = selectorList; return this; }
        public final HighlightConfig optSelectorList(@Nullable String selectorList) { return selectorList(selectorList); }
        public final String selectorList() { return selectorList; }
        public final String getSelectorList() { return selectorList(); }
        public final HighlightConfig cssGridColor(@Nullable DOM.RGBA cssGridColor) { this.cssGridColor = cssGridColor; return this; }
        public final HighlightConfig optCssGridColor(@Nullable DOM.RGBA cssGridColor) { return cssGridColor(cssGridColor); }
        public final DOM.RGBA cssGridColor() { return cssGridColor; }
        public final DOM.RGBA getCssGridColor() { return cssGridColor(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            if (showInfo != null) strBuilder.append("\"showInfo\":").append(showInfo);
            if (showRulers != null) strBuilder.append(",\"showRulers\":").append(showRulers);
            if (showExtensionLines != null) strBuilder.append(",\"showExtensionLines\":").append(showExtensionLines);
            if (displayAsMaterial != null) strBuilder.append(",\"displayAsMaterial\":").append(displayAsMaterial);
            if (contentColor != null) contentColor.toJson(strBuilder.append(",\"contentColor\":"));
            if (paddingColor != null) paddingColor.toJson(strBuilder.append(",\"paddingColor\":"));
            if (borderColor != null) borderColor.toJson(strBuilder.append(",\"borderColor\":"));
            if (marginColor != null) marginColor.toJson(strBuilder.append(",\"marginColor\":"));
            if (eventTargetColor != null) eventTargetColor.toJson(strBuilder.append(",\"eventTargetColor\":"));
            if (shapeColor != null) shapeColor.toJson(strBuilder.append(",\"shapeColor\":"));
            if (shapeMarginColor != null) shapeMarginColor.toJson(strBuilder.append(",\"shapeMarginColor\":"));
            if (selectorList != null) strBuilder.append(",\"selectorList\":").append('"').append(DomainBase.escapeQuote(selectorList)).append('"');
            if (cssGridColor != null) cssGridColor.toJson(strBuilder.append(",\"cssGridColor\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public HighlightConfig() {}
        public HighlightConfig(
            @Nullable @JsonProperty("showInfo")Boolean showInfo,
            @Nullable @JsonProperty("showRulers")Boolean showRulers,
            @Nullable @JsonProperty("showExtensionLines")Boolean showExtensionLines,
            @Nullable @JsonProperty("displayAsMaterial")Boolean displayAsMaterial,
            @Nullable @JsonProperty("contentColor")DOM.RGBA contentColor,
            @Nullable @JsonProperty("paddingColor")DOM.RGBA paddingColor,
            @Nullable @JsonProperty("borderColor")DOM.RGBA borderColor,
            @Nullable @JsonProperty("marginColor")DOM.RGBA marginColor,
            @Nullable @JsonProperty("eventTargetColor")DOM.RGBA eventTargetColor,
            @Nullable @JsonProperty("shapeColor")DOM.RGBA shapeColor,
            @Nullable @JsonProperty("shapeMarginColor")DOM.RGBA shapeMarginColor,
            @Nullable @JsonProperty("selectorList")String selectorList,
            @Nullable @JsonProperty("cssGridColor")DOM.RGBA cssGridColor
        ) {
            this.showInfo = showInfo;
            this.showRulers = showRulers;
            this.showExtensionLines = showExtensionLines;
            this.displayAsMaterial = displayAsMaterial;
            this.contentColor = contentColor;
            this.paddingColor = paddingColor;
            this.borderColor = borderColor;
            this.marginColor = marginColor;
            this.eventTargetColor = eventTargetColor;
            this.shapeColor = shapeColor;
            this.shapeMarginColor = shapeMarginColor;
            this.selectorList = selectorList;
            this.cssGridColor = cssGridColor;
        }
    }

    /**&lt;No document in protocol.&gt;*/
    @ParametersAreNonnullByDefault public enum InspectMode implements CommonDomainType {
        SearchForNode("searchForNode"),
        SearchForUAShadowDOM("searchForUAShadowDOM"),
        None("none");

        private final String _value;
        /**Convert string representation to type.
         @throws IllegalArgumentException if given value cannot convert to enum type. */
        @JsonCreator public static InspectMode of(String value) {
            return Enum.valueOf(InspectMode.class, value.substring(0, 1).toUpperCase() + value.substring(1));
        }
        InspectMode(String value) { _value = value; }
        /**Check if parameter fields of method are all valid. */
        @Override public void check() throws IllegalArgumentException { /* Need not check */ }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) { return strBuilder.append('"').append(_value).append('"'); }
        @Override public String toString() { return "\"" + _value + "\""; }
    }
    /**Disables domain notifications.*/
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
            return super.call("Overlay.disable", DisableResult.class,
                (code, msg)->new DisableResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<DisableResult> call(Executor exec) {
            return super.call("Overlay.disable", DisableResult.class,
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
    /**Enables domain notifications.*/
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
            return super.call("Overlay.enable", EnableResult.class,
                (code, msg)->new EnableResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<EnableResult> call(Executor exec) {
            return super.call("Overlay.enable", EnableResult.class,
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
    /**For testing.*/
    public GetHighlightObjectForTestParameter getHighlightObjectForTest() { final GetHighlightObjectForTestParameter v = new GetHighlightObjectForTestParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of getHighlightObjectForTest.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetHighlightObjectForTestParameter extends CommandBase {
        /**Id of the node to get highlight object for.*/
        private DOM.NodeId nodeId;
        public final GetHighlightObjectForTestParameter nodeId(DOM.NodeId nodeId) { this.nodeId = nodeId; return this; }
        public final GetHighlightObjectForTestParameter setNodeId(DOM.NodeId nodeId) { return nodeId(nodeId); }
        public final DOM.NodeId nodeId() { return nodeId; }
        public final DOM.NodeId getNodeId() { return nodeId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (nodeId == null) throw new IllegalArgumentException("Overlay.GetHighlightObjectForTestParameter.nodeId is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            nodeId.toJson(strBuilder.append("\"nodeId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public GetHighlightObjectForTestParameter() {}
        public GetHighlightObjectForTestParameter(
            @JsonProperty("nodeId")DOM.NodeId nodeId
        ) {
            this();
            this.nodeId = nodeId;
        }
        public CompletableFuture<GetHighlightObjectForTestResult> call() {
            return super.call("Overlay.getHighlightObjectForTest", GetHighlightObjectForTestResult.class,
                (code, msg)->new GetHighlightObjectForTestResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetHighlightObjectForTestResult> call(Executor exec) {
            return super.call("Overlay.getHighlightObjectForTest", GetHighlightObjectForTestResult.class,
                (code, msg)->new GetHighlightObjectForTestResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of getHighlightObjectForTest.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetHighlightObjectForTestResult extends ResultBase {
        /**Highlight data for the node.*/
        private final Object highlight;
        public final Object highlight() { return highlight; }
        public final Object getHighlight() { return highlight(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"highlight\":").append(highlight);
            strBuilder.append('}');
            return strBuilder;
        }
        public GetHighlightObjectForTestResult(
            @JsonProperty("highlight")Object highlight
        ) {
            this.highlight = highlight;
        }
        public GetHighlightObjectForTestResult(ResultBase.FailedResult e) {
            super(e);
            highlight = null;
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
            return super.call("Overlay.hideHighlight", HideHighlightResult.class,
                (code, msg)->new HideHighlightResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<HideHighlightResult> call(Executor exec) {
            return super.call("Overlay.hideHighlight", HideHighlightResult.class,
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
    /**Highlights owner element of the frame with given id.*/
    public HighlightFrameParameter highlightFrame() { final HighlightFrameParameter v = new HighlightFrameParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of highlightFrame.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class HighlightFrameParameter extends CommandBase {
        /**Identifier of the frame to highlight.*/
        private Page.FrameId frameId;
        /**The content box highlight fill color (default: transparent).
        <em>Optional.</em>*/
        private DOM.RGBA contentColor;
        /**The content box highlight outline color (default: transparent).
        <em>Optional.</em>*/
        private DOM.RGBA contentOutlineColor;
        public final HighlightFrameParameter frameId(Page.FrameId frameId) { this.frameId = frameId; return this; }
        public final HighlightFrameParameter setFrameId(Page.FrameId frameId) { return frameId(frameId); }
        public final Page.FrameId frameId() { return frameId; }
        public final Page.FrameId getFrameId() { return frameId(); }
        public final HighlightFrameParameter contentColor(@Nullable DOM.RGBA contentColor) { this.contentColor = contentColor; return this; }
        public final HighlightFrameParameter optContentColor(@Nullable DOM.RGBA contentColor) { return contentColor(contentColor); }
        public final DOM.RGBA contentColor() { return contentColor; }
        public final DOM.RGBA getContentColor() { return contentColor(); }
        public final HighlightFrameParameter contentOutlineColor(@Nullable DOM.RGBA contentOutlineColor) { this.contentOutlineColor = contentOutlineColor; return this; }
        public final HighlightFrameParameter optContentOutlineColor(@Nullable DOM.RGBA contentOutlineColor) { return contentOutlineColor(contentOutlineColor); }
        public final DOM.RGBA contentOutlineColor() { return contentOutlineColor; }
        public final DOM.RGBA getContentOutlineColor() { return contentOutlineColor(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (frameId == null) throw new IllegalArgumentException("Overlay.HighlightFrameParameter.frameId is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            frameId.toJson(strBuilder.append("\"frameId\":"));
            if (contentColor != null) contentColor.toJson(strBuilder.append(",\"contentColor\":"));
            if (contentOutlineColor != null) contentOutlineColor.toJson(strBuilder.append(",\"contentOutlineColor\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public HighlightFrameParameter() {}
        public HighlightFrameParameter(
            @JsonProperty("frameId")Page.FrameId frameId,
            @Nullable @JsonProperty("contentColor")DOM.RGBA contentColor,
            @Nullable @JsonProperty("contentOutlineColor")DOM.RGBA contentOutlineColor
        ) {
            this();
            this.frameId = frameId;
            this.contentColor = contentColor;
            this.contentOutlineColor = contentOutlineColor;
        }
        public CompletableFuture<HighlightFrameResult> call() {
            return super.call("Overlay.highlightFrame", HighlightFrameResult.class,
                (code, msg)->new HighlightFrameResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<HighlightFrameResult> call(Executor exec) {
            return super.call("Overlay.highlightFrame", HighlightFrameResult.class,
                (code, msg)->new HighlightFrameResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of highlightFrame.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class HighlightFrameResult extends ResultBase {
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
        public HighlightFrameResult() { super(); }
        public HighlightFrameResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Highlights DOM node with given id or with the given JavaScript object wrapper. Either nodeId or
objectId must be specified.*/
    public HighlightNodeParameter highlightNode() { final HighlightNodeParameter v = new HighlightNodeParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of highlightNode.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class HighlightNodeParameter extends CommandBase {
        /**A descriptor for the highlight appearance.*/
        private HighlightConfig highlightConfig;
        /**Identifier of the node to highlight.
        <em>Optional.</em>*/
        private DOM.NodeId nodeId;
        /**Identifier of the backend node to highlight.
        <em>Optional.</em>*/
        private DOM.BackendNodeId backendNodeId;
        /**JavaScript object id of the node to be highlighted.
        <em>Optional.</em>*/
        private Runtime.RemoteObjectId objectId;
        public final HighlightNodeParameter highlightConfig(HighlightConfig highlightConfig) { this.highlightConfig = highlightConfig; return this; }
        public final HighlightNodeParameter setHighlightConfig(HighlightConfig highlightConfig) { return highlightConfig(highlightConfig); }
        public final HighlightConfig highlightConfig() { return highlightConfig; }
        public final HighlightConfig getHighlightConfig() { return highlightConfig(); }
        public final HighlightNodeParameter nodeId(@Nullable DOM.NodeId nodeId) { this.nodeId = nodeId; return this; }
        public final HighlightNodeParameter optNodeId(@Nullable DOM.NodeId nodeId) { return nodeId(nodeId); }
        public final DOM.NodeId nodeId() { return nodeId; }
        public final DOM.NodeId getNodeId() { return nodeId(); }
        public final HighlightNodeParameter backendNodeId(@Nullable DOM.BackendNodeId backendNodeId) { this.backendNodeId = backendNodeId; return this; }
        public final HighlightNodeParameter optBackendNodeId(@Nullable DOM.BackendNodeId backendNodeId) { return backendNodeId(backendNodeId); }
        public final DOM.BackendNodeId backendNodeId() { return backendNodeId; }
        public final DOM.BackendNodeId getBackendNodeId() { return backendNodeId(); }
        public final HighlightNodeParameter objectId(@Nullable Runtime.RemoteObjectId objectId) { this.objectId = objectId; return this; }
        public final HighlightNodeParameter optObjectId(@Nullable Runtime.RemoteObjectId objectId) { return objectId(objectId); }
        public final Runtime.RemoteObjectId objectId() { return objectId; }
        public final Runtime.RemoteObjectId getObjectId() { return objectId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (highlightConfig == null) throw new IllegalArgumentException("Overlay.HighlightNodeParameter.highlightConfig is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            highlightConfig.toJson(strBuilder.append("\"highlightConfig\":"));
            if (nodeId != null) nodeId.toJson(strBuilder.append(",\"nodeId\":"));
            if (backendNodeId != null) backendNodeId.toJson(strBuilder.append(",\"backendNodeId\":"));
            if (objectId != null) objectId.toJson(strBuilder.append(",\"objectId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public HighlightNodeParameter() {}
        public HighlightNodeParameter(
            @JsonProperty("highlightConfig")HighlightConfig highlightConfig,
            @Nullable @JsonProperty("nodeId")DOM.NodeId nodeId,
            @Nullable @JsonProperty("backendNodeId")DOM.BackendNodeId backendNodeId,
            @Nullable @JsonProperty("objectId")Runtime.RemoteObjectId objectId
        ) {
            this();
            this.highlightConfig = highlightConfig;
            this.nodeId = nodeId;
            this.backendNodeId = backendNodeId;
            this.objectId = objectId;
        }
        public CompletableFuture<HighlightNodeResult> call() {
            return super.call("Overlay.highlightNode", HighlightNodeResult.class,
                (code, msg)->new HighlightNodeResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<HighlightNodeResult> call(Executor exec) {
            return super.call("Overlay.highlightNode", HighlightNodeResult.class,
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
    /**Highlights given quad. Coordinates are absolute with respect to the main frame viewport.*/
    public HighlightQuadParameter highlightQuad() { final HighlightQuadParameter v = new HighlightQuadParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of highlightQuad.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class HighlightQuadParameter extends CommandBase {
        /**Quad to highlight*/
        private DOM.Quad quad;
        /**The highlight fill color (default: transparent).
        <em>Optional.</em>*/
        private DOM.RGBA color;
        /**The highlight outline color (default: transparent).
        <em>Optional.</em>*/
        private DOM.RGBA outlineColor;
        public final HighlightQuadParameter quad(DOM.Quad quad) { this.quad = quad; return this; }
        public final HighlightQuadParameter setQuad(DOM.Quad quad) { return quad(quad); }
        public final DOM.Quad quad() { return quad; }
        public final DOM.Quad getQuad() { return quad(); }
        public final HighlightQuadParameter color(@Nullable DOM.RGBA color) { this.color = color; return this; }
        public final HighlightQuadParameter optColor(@Nullable DOM.RGBA color) { return color(color); }
        public final DOM.RGBA color() { return color; }
        public final DOM.RGBA getColor() { return color(); }
        public final HighlightQuadParameter outlineColor(@Nullable DOM.RGBA outlineColor) { this.outlineColor = outlineColor; return this; }
        public final HighlightQuadParameter optOutlineColor(@Nullable DOM.RGBA outlineColor) { return outlineColor(outlineColor); }
        public final DOM.RGBA outlineColor() { return outlineColor; }
        public final DOM.RGBA getOutlineColor() { return outlineColor(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (quad == null) throw new IllegalArgumentException("Overlay.HighlightQuadParameter.quad is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            quad.toJson(strBuilder.append("\"quad\":"));
            if (color != null) color.toJson(strBuilder.append(",\"color\":"));
            if (outlineColor != null) outlineColor.toJson(strBuilder.append(",\"outlineColor\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public HighlightQuadParameter() {}
        public HighlightQuadParameter(
            @JsonProperty("quad")DOM.Quad quad,
            @Nullable @JsonProperty("color")DOM.RGBA color,
            @Nullable @JsonProperty("outlineColor")DOM.RGBA outlineColor
        ) {
            this();
            this.quad = quad;
            this.color = color;
            this.outlineColor = outlineColor;
        }
        public CompletableFuture<HighlightQuadResult> call() {
            return super.call("Overlay.highlightQuad", HighlightQuadResult.class,
                (code, msg)->new HighlightQuadResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<HighlightQuadResult> call(Executor exec) {
            return super.call("Overlay.highlightQuad", HighlightQuadResult.class,
                (code, msg)->new HighlightQuadResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of highlightQuad.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class HighlightQuadResult extends ResultBase {
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
        public HighlightQuadResult() { super(); }
        public HighlightQuadResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Highlights given rectangle. Coordinates are absolute with respect to the main frame viewport.*/
    public HighlightRectParameter highlightRect() { final HighlightRectParameter v = new HighlightRectParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of highlightRect.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class HighlightRectParameter extends CommandBase {
        /**X coordinate*/
        private Integer x;
        /**Y coordinate*/
        private Integer y;
        /**Rectangle width*/
        private Integer width;
        /**Rectangle height*/
        private Integer height;
        /**The highlight fill color (default: transparent).
        <em>Optional.</em>*/
        private DOM.RGBA color;
        /**The highlight outline color (default: transparent).
        <em>Optional.</em>*/
        private DOM.RGBA outlineColor;
        public final HighlightRectParameter x(Integer x) { this.x = x; return this; }
        public final HighlightRectParameter setX(Integer x) { return x(x); }
        public final Integer x() { return x; }
        public final Integer getX() { return x(); }
        public final HighlightRectParameter y(Integer y) { this.y = y; return this; }
        public final HighlightRectParameter setY(Integer y) { return y(y); }
        public final Integer y() { return y; }
        public final Integer getY() { return y(); }
        public final HighlightRectParameter width(Integer width) { this.width = width; return this; }
        public final HighlightRectParameter setWidth(Integer width) { return width(width); }
        public final Integer width() { return width; }
        public final Integer getWidth() { return width(); }
        public final HighlightRectParameter height(Integer height) { this.height = height; return this; }
        public final HighlightRectParameter setHeight(Integer height) { return height(height); }
        public final Integer height() { return height; }
        public final Integer getHeight() { return height(); }
        public final HighlightRectParameter color(@Nullable DOM.RGBA color) { this.color = color; return this; }
        public final HighlightRectParameter optColor(@Nullable DOM.RGBA color) { return color(color); }
        public final DOM.RGBA color() { return color; }
        public final DOM.RGBA getColor() { return color(); }
        public final HighlightRectParameter outlineColor(@Nullable DOM.RGBA outlineColor) { this.outlineColor = outlineColor; return this; }
        public final HighlightRectParameter optOutlineColor(@Nullable DOM.RGBA outlineColor) { return outlineColor(outlineColor); }
        public final DOM.RGBA outlineColor() { return outlineColor; }
        public final DOM.RGBA getOutlineColor() { return outlineColor(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (x == null) throw new IllegalArgumentException("Overlay.HighlightRectParameter.x is necessary field.");
            if (y == null) throw new IllegalArgumentException("Overlay.HighlightRectParameter.y is necessary field.");
            if (width == null) throw new IllegalArgumentException("Overlay.HighlightRectParameter.width is necessary field.");
            if (height == null) throw new IllegalArgumentException("Overlay.HighlightRectParameter.height is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"x\":").append(x);
            strBuilder.append(",\"y\":").append(y);
            strBuilder.append(",\"width\":").append(width);
            strBuilder.append(",\"height\":").append(height);
            if (color != null) color.toJson(strBuilder.append(",\"color\":"));
            if (outlineColor != null) outlineColor.toJson(strBuilder.append(",\"outlineColor\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public HighlightRectParameter() {}
        public HighlightRectParameter(
            @JsonProperty("x")Integer x,
            @JsonProperty("y")Integer y,
            @JsonProperty("width")Integer width,
            @JsonProperty("height")Integer height,
            @Nullable @JsonProperty("color")DOM.RGBA color,
            @Nullable @JsonProperty("outlineColor")DOM.RGBA outlineColor
        ) {
            this();
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.color = color;
            this.outlineColor = outlineColor;
        }
        public CompletableFuture<HighlightRectResult> call() {
            return super.call("Overlay.highlightRect", HighlightRectResult.class,
                (code, msg)->new HighlightRectResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<HighlightRectResult> call(Executor exec) {
            return super.call("Overlay.highlightRect", HighlightRectResult.class,
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
    /**Enters the 'inspect' mode. In this mode, elements that user is hovering over are highlighted.
Backend then generates 'inspectNodeRequested' event upon element selection.*/
    public SetInspectModeParameter setInspectMode() { final SetInspectModeParameter v = new SetInspectModeParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setInspectMode.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetInspectModeParameter extends CommandBase {
        /**Set an inspection mode.*/
        private InspectMode mode;
        /**A descriptor for the highlight appearance of hovered-over nodes. May be omitted if `enabled
== false`.
        <em>Optional.</em>*/
        private HighlightConfig highlightConfig;
        public final SetInspectModeParameter mode(InspectMode mode) { this.mode = mode; return this; }
        public final SetInspectModeParameter setMode(InspectMode mode) { return mode(mode); }
        public final InspectMode mode() { return mode; }
        public final InspectMode getMode() { return mode(); }
        public final SetInspectModeParameter highlightConfig(@Nullable HighlightConfig highlightConfig) { this.highlightConfig = highlightConfig; return this; }
        public final SetInspectModeParameter optHighlightConfig(@Nullable HighlightConfig highlightConfig) { return highlightConfig(highlightConfig); }
        public final HighlightConfig highlightConfig() { return highlightConfig; }
        public final HighlightConfig getHighlightConfig() { return highlightConfig(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (mode == null) throw new IllegalArgumentException("Overlay.SetInspectModeParameter.mode is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            mode.toJson(strBuilder.append("\"mode\":"));
            if (highlightConfig != null) highlightConfig.toJson(strBuilder.append(",\"highlightConfig\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public SetInspectModeParameter() {}
        public SetInspectModeParameter(
            @JsonProperty("mode")InspectMode mode,
            @Nullable @JsonProperty("highlightConfig")HighlightConfig highlightConfig
        ) {
            this();
            this.mode = mode;
            this.highlightConfig = highlightConfig;
        }
        public CompletableFuture<SetInspectModeResult> call() {
            return super.call("Overlay.setInspectMode", SetInspectModeResult.class,
                (code, msg)->new SetInspectModeResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetInspectModeResult> call(Executor exec) {
            return super.call("Overlay.setInspectMode", SetInspectModeResult.class,
                (code, msg)->new SetInspectModeResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setInspectMode.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetInspectModeResult extends ResultBase {
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
        public SetInspectModeResult() { super(); }
        public SetInspectModeResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**&lt;No document in protocol.&gt;*/
    public SetPausedInDebuggerMessageParameter setPausedInDebuggerMessage() { final SetPausedInDebuggerMessageParameter v = new SetPausedInDebuggerMessageParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setPausedInDebuggerMessage.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetPausedInDebuggerMessageParameter extends CommandBase {
        /**The message to display, also triggers resume and step over controls.
        <em>Optional.</em>*/
        private String message;
        public final SetPausedInDebuggerMessageParameter message(@Nullable String message) { this.message = message; return this; }
        public final SetPausedInDebuggerMessageParameter optMessage(@Nullable String message) { return message(message); }
        public final String message() { return message; }
        public final String getMessage() { return message(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            if (message != null) strBuilder.append("\"message\":").append('"').append(DomainBase.escapeQuote(message)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public SetPausedInDebuggerMessageParameter() {}
        public SetPausedInDebuggerMessageParameter(
            @Nullable @JsonProperty("message")String message
        ) {
            this();
            this.message = message;
        }
        public CompletableFuture<SetPausedInDebuggerMessageResult> call() {
            return super.call("Overlay.setPausedInDebuggerMessage", SetPausedInDebuggerMessageResult.class,
                (code, msg)->new SetPausedInDebuggerMessageResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetPausedInDebuggerMessageResult> call(Executor exec) {
            return super.call("Overlay.setPausedInDebuggerMessage", SetPausedInDebuggerMessageResult.class,
                (code, msg)->new SetPausedInDebuggerMessageResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setPausedInDebuggerMessage.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetPausedInDebuggerMessageResult extends ResultBase {
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
        public SetPausedInDebuggerMessageResult() { super(); }
        public SetPausedInDebuggerMessageResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Requests that backend shows debug borders on layers*/
    public SetShowDebugBordersParameter setShowDebugBorders() { final SetShowDebugBordersParameter v = new SetShowDebugBordersParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setShowDebugBorders.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetShowDebugBordersParameter extends CommandBase {
        /**True for showing debug borders*/
        private Boolean show;
        public final SetShowDebugBordersParameter show(Boolean show) { this.show = show; return this; }
        public final SetShowDebugBordersParameter setShow(Boolean show) { return show(show); }
        public final Boolean show() { return show; }
        public final Boolean getShow() { return show(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (show == null) throw new IllegalArgumentException("Overlay.SetShowDebugBordersParameter.show is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"show\":").append(show);
            strBuilder.append('}');
            return strBuilder;
        }
        public SetShowDebugBordersParameter() {}
        public SetShowDebugBordersParameter(
            @JsonProperty("show")Boolean show
        ) {
            this();
            this.show = show;
        }
        public CompletableFuture<SetShowDebugBordersResult> call() {
            return super.call("Overlay.setShowDebugBorders", SetShowDebugBordersResult.class,
                (code, msg)->new SetShowDebugBordersResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetShowDebugBordersResult> call(Executor exec) {
            return super.call("Overlay.setShowDebugBorders", SetShowDebugBordersResult.class,
                (code, msg)->new SetShowDebugBordersResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setShowDebugBorders.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetShowDebugBordersResult extends ResultBase {
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
        public SetShowDebugBordersResult() { super(); }
        public SetShowDebugBordersResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Requests that backend shows the FPS counter*/
    public SetShowFPSCounterParameter setShowFPSCounter() { final SetShowFPSCounterParameter v = new SetShowFPSCounterParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setShowFPSCounter.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetShowFPSCounterParameter extends CommandBase {
        /**True for showing the FPS counter*/
        private Boolean show;
        public final SetShowFPSCounterParameter show(Boolean show) { this.show = show; return this; }
        public final SetShowFPSCounterParameter setShow(Boolean show) { return show(show); }
        public final Boolean show() { return show; }
        public final Boolean getShow() { return show(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (show == null) throw new IllegalArgumentException("Overlay.SetShowFPSCounterParameter.show is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"show\":").append(show);
            strBuilder.append('}');
            return strBuilder;
        }
        public SetShowFPSCounterParameter() {}
        public SetShowFPSCounterParameter(
            @JsonProperty("show")Boolean show
        ) {
            this();
            this.show = show;
        }
        public CompletableFuture<SetShowFPSCounterResult> call() {
            return super.call("Overlay.setShowFPSCounter", SetShowFPSCounterResult.class,
                (code, msg)->new SetShowFPSCounterResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetShowFPSCounterResult> call(Executor exec) {
            return super.call("Overlay.setShowFPSCounter", SetShowFPSCounterResult.class,
                (code, msg)->new SetShowFPSCounterResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setShowFPSCounter.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetShowFPSCounterResult extends ResultBase {
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
        public SetShowFPSCounterResult() { super(); }
        public SetShowFPSCounterResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Requests that backend shows paint rectangles*/
    public SetShowPaintRectsParameter setShowPaintRects() { final SetShowPaintRectsParameter v = new SetShowPaintRectsParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setShowPaintRects.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetShowPaintRectsParameter extends CommandBase {
        /**True for showing paint rectangles*/
        private Boolean result;
        public final SetShowPaintRectsParameter result(Boolean result) { this.result = result; return this; }
        public final SetShowPaintRectsParameter setResult(Boolean result) { return result(result); }
        public final Boolean result() { return result; }
        public final Boolean getResult() { return result(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (result == null) throw new IllegalArgumentException("Overlay.SetShowPaintRectsParameter.result is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"result\":").append(result);
            strBuilder.append('}');
            return strBuilder;
        }
        public SetShowPaintRectsParameter() {}
        public SetShowPaintRectsParameter(
            @JsonProperty("result")Boolean result
        ) {
            this();
            this.result = result;
        }
        public CompletableFuture<SetShowPaintRectsResult> call() {
            return super.call("Overlay.setShowPaintRects", SetShowPaintRectsResult.class,
                (code, msg)->new SetShowPaintRectsResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetShowPaintRectsResult> call(Executor exec) {
            return super.call("Overlay.setShowPaintRects", SetShowPaintRectsResult.class,
                (code, msg)->new SetShowPaintRectsResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setShowPaintRects.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetShowPaintRectsResult extends ResultBase {
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
        public SetShowPaintRectsResult() { super(); }
        public SetShowPaintRectsResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Requests that backend shows scroll bottleneck rects*/
    public SetShowScrollBottleneckRectsParameter setShowScrollBottleneckRects() { final SetShowScrollBottleneckRectsParameter v = new SetShowScrollBottleneckRectsParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setShowScrollBottleneckRects.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetShowScrollBottleneckRectsParameter extends CommandBase {
        /**True for showing scroll bottleneck rects*/
        private Boolean show;
        public final SetShowScrollBottleneckRectsParameter show(Boolean show) { this.show = show; return this; }
        public final SetShowScrollBottleneckRectsParameter setShow(Boolean show) { return show(show); }
        public final Boolean show() { return show; }
        public final Boolean getShow() { return show(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (show == null) throw new IllegalArgumentException("Overlay.SetShowScrollBottleneckRectsParameter.show is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"show\":").append(show);
            strBuilder.append('}');
            return strBuilder;
        }
        public SetShowScrollBottleneckRectsParameter() {}
        public SetShowScrollBottleneckRectsParameter(
            @JsonProperty("show")Boolean show
        ) {
            this();
            this.show = show;
        }
        public CompletableFuture<SetShowScrollBottleneckRectsResult> call() {
            return super.call("Overlay.setShowScrollBottleneckRects", SetShowScrollBottleneckRectsResult.class,
                (code, msg)->new SetShowScrollBottleneckRectsResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetShowScrollBottleneckRectsResult> call(Executor exec) {
            return super.call("Overlay.setShowScrollBottleneckRects", SetShowScrollBottleneckRectsResult.class,
                (code, msg)->new SetShowScrollBottleneckRectsResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setShowScrollBottleneckRects.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetShowScrollBottleneckRectsResult extends ResultBase {
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
        public SetShowScrollBottleneckRectsResult() { super(); }
        public SetShowScrollBottleneckRectsResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Paints viewport size upon main frame resize.*/
    public SetShowViewportSizeOnResizeParameter setShowViewportSizeOnResize() { final SetShowViewportSizeOnResizeParameter v = new SetShowViewportSizeOnResizeParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setShowViewportSizeOnResize.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetShowViewportSizeOnResizeParameter extends CommandBase {
        /**Whether to paint size or not.*/
        private Boolean show;
        public final SetShowViewportSizeOnResizeParameter show(Boolean show) { this.show = show; return this; }
        public final SetShowViewportSizeOnResizeParameter setShow(Boolean show) { return show(show); }
        public final Boolean show() { return show; }
        public final Boolean getShow() { return show(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (show == null) throw new IllegalArgumentException("Overlay.SetShowViewportSizeOnResizeParameter.show is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"show\":").append(show);
            strBuilder.append('}');
            return strBuilder;
        }
        public SetShowViewportSizeOnResizeParameter() {}
        public SetShowViewportSizeOnResizeParameter(
            @JsonProperty("show")Boolean show
        ) {
            this();
            this.show = show;
        }
        public CompletableFuture<SetShowViewportSizeOnResizeResult> call() {
            return super.call("Overlay.setShowViewportSizeOnResize", SetShowViewportSizeOnResizeResult.class,
                (code, msg)->new SetShowViewportSizeOnResizeResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetShowViewportSizeOnResizeResult> call(Executor exec) {
            return super.call("Overlay.setShowViewportSizeOnResize", SetShowViewportSizeOnResizeResult.class,
                (code, msg)->new SetShowViewportSizeOnResizeResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setShowViewportSizeOnResize.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetShowViewportSizeOnResizeResult extends ResultBase {
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
        public SetShowViewportSizeOnResizeResult() { super(); }
        public SetShowViewportSizeOnResizeResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**&lt;No document in protocol.&gt;*/
    public SetSuspendedParameter setSuspended() { final SetSuspendedParameter v = new SetSuspendedParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setSuspended.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetSuspendedParameter extends CommandBase {
        /**Whether overlay should be suspended and not consume any resources until resumed.*/
        private Boolean suspended;
        public final SetSuspendedParameter suspended(Boolean suspended) { this.suspended = suspended; return this; }
        public final SetSuspendedParameter setSuspended(Boolean suspended) { return suspended(suspended); }
        public final Boolean suspended() { return suspended; }
        public final Boolean getSuspended() { return suspended(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (suspended == null) throw new IllegalArgumentException("Overlay.SetSuspendedParameter.suspended is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"suspended\":").append(suspended);
            strBuilder.append('}');
            return strBuilder;
        }
        public SetSuspendedParameter() {}
        public SetSuspendedParameter(
            @JsonProperty("suspended")Boolean suspended
        ) {
            this();
            this.suspended = suspended;
        }
        public CompletableFuture<SetSuspendedResult> call() {
            return super.call("Overlay.setSuspended", SetSuspendedResult.class,
                (code, msg)->new SetSuspendedResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetSuspendedResult> call(Executor exec) {
            return super.call("Overlay.setSuspended", SetSuspendedResult.class,
                (code, msg)->new SetSuspendedResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setSuspended.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetSuspendedResult extends ResultBase {
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
        public SetSuspendedResult() { super(); }
        public SetSuspendedResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Event parameter of Overlay.inspectNodeRequested.
     @see #onInspectNodeRequested*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class InspectNodeRequestedEventParameter implements CommonDomainType {
        /**Id of the node to inspect.*/
        private final DOM.BackendNodeId backendNodeId;
        public final DOM.BackendNodeId backendNodeId() { return backendNodeId; }
        public final DOM.BackendNodeId getBackendNodeId() { return backendNodeId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            backendNodeId.toJson(strBuilder.append("\"backendNodeId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        InspectNodeRequestedEventParameter(
            @JsonProperty("backendNodeId")DOM.BackendNodeId backendNodeId
        ) {
            this.backendNodeId = backendNodeId;
        }
    }
    /**Fired when the node should be inspected. This happens after call to `setInspectMode` or when
user manually inspects an element.
     @see InspectNodeRequestedEventParameter*/
    public void onInspectNodeRequested(Consumer<InspectNodeRequestedEventParameter> callback) {
        registerEventCallback("Overlay.inspectNodeRequested", node -> {
            InspectNodeRequestedEventParameter param;
            try { param = EventCenter.deserializeJson(node, InspectNodeRequestedEventParameter.class); }
            catch (IOException e) { e.printStackTrace(); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of Overlay.nodeHighlightRequested.
     @see #onNodeHighlightRequested*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class NodeHighlightRequestedEventParameter implements CommonDomainType {
        /**&lt;No document in protocol.&gt;*/
        private final DOM.NodeId nodeId;
        public final DOM.NodeId nodeId() { return nodeId; }
        public final DOM.NodeId getNodeId() { return nodeId(); }
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
        NodeHighlightRequestedEventParameter(
            @JsonProperty("nodeId")DOM.NodeId nodeId
        ) {
            this.nodeId = nodeId;
        }
    }
    /**Fired when the node should be highlighted. This happens after call to `setInspectMode`.
     @see NodeHighlightRequestedEventParameter*/
    public void onNodeHighlightRequested(Consumer<NodeHighlightRequestedEventParameter> callback) {
        registerEventCallback("Overlay.nodeHighlightRequested", node -> {
            NodeHighlightRequestedEventParameter param;
            try { param = EventCenter.deserializeJson(node, NodeHighlightRequestedEventParameter.class); }
            catch (IOException e) { e.printStackTrace(); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of Overlay.screenshotRequested.
     @see #onScreenshotRequested*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ScreenshotRequestedEventParameter implements CommonDomainType {
        /**Viewport to capture, in CSS.*/
        private final Page.Viewport viewport;
        public final Page.Viewport viewport() { return viewport; }
        public final Page.Viewport getViewport() { return viewport(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            viewport.toJson(strBuilder.append("\"viewport\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        ScreenshotRequestedEventParameter(
            @JsonProperty("viewport")Page.Viewport viewport
        ) {
            this.viewport = viewport;
        }
    }
    /**Fired when user asks to capture screenshot of some area on the page.
     @see ScreenshotRequestedEventParameter*/
    public void onScreenshotRequested(Consumer<ScreenshotRequestedEventParameter> callback) {
        registerEventCallback("Overlay.screenshotRequested", node -> {
            ScreenshotRequestedEventParameter param;
            try { param = EventCenter.deserializeJson(node, ScreenshotRequestedEventParameter.class); }
            catch (IOException e) { e.printStackTrace(); return; }
            callback.accept(param);
        });
    }
}

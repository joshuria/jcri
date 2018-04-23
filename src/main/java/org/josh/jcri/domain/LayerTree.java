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
 @see DOM
 @author Joshua*/
@ParametersAreNonnullByDefault public class LayerTree extends DomainBase {
    public LayerTree(EventCenter evt, WebSocket ws) { super(evt, ws); }

    /**Unique Layer identifier.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class LayerId implements CommonDomainType {
        private String _value;
        public LayerId() {}
        public LayerId(String value) { _value = value; }
        public final LayerId value(String value) { _value = value; return this; }
        public final String value() { return _value; }
        public final LayerId setValue(String value) { return value(value); }
        public final String getValue() { return value(); }
        @Override public String toString() { return _value; }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (_value == null) throw new IllegalArgumentException("LayerTree.LayerId.value is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            return strBuilder.append('"').append(DomainBase.escapeQuote(_value)).append('"');
        }
    }

    /**Unique snapshot identifier.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SnapshotId implements CommonDomainType {
        private String _value;
        public SnapshotId() {}
        public SnapshotId(String value) { _value = value; }
        public final SnapshotId value(String value) { _value = value; return this; }
        public final String value() { return _value; }
        public final SnapshotId setValue(String value) { return value(value); }
        public final String getValue() { return value(); }
        @Override public String toString() { return _value; }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (_value == null) throw new IllegalArgumentException("LayerTree.SnapshotId.value is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            return strBuilder.append('"').append(DomainBase.escapeQuote(_value)).append('"');
        }
    }

    /**Rectangle where scrolling happens on the main thread.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ScrollRect implements CommonDomainType {
        /**Rectangle itself.*/
        private DOM.Rect rect;
        /**Reason for rectangle to force scrolling on the main thread*/
        @ParametersAreNonnullByDefault public enum Type implements CommonDomainType {
            RepaintsOnScroll("RepaintsOnScroll"),
            TouchEventHandler("TouchEventHandler"),
            WheelEventHandler("WheelEventHandler");

            private final String _value;
            private static final Map<String, Type> _Lookup;
            static {
                Map<String, Type> m = new HashMap<>();
                for(Type v: values()) m.put(v.toString(), v);
                _Lookup = Collections.unmodifiableMap(m);
            }
            /**Convert string representation to type.
             @throws IllegalArgumentException if given value cannot convert to enum type. */
            @JsonCreator public static Type of(String value) {
                Type v = _Lookup.get(value.toLowerCase());
                return v != null ? v : Enum.valueOf(Type.class, value);
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
        public final ScrollRect rect(DOM.Rect rect) { this.rect = rect; return this; }
        public final ScrollRect setRect(DOM.Rect rect) { return rect(rect); }
        public final DOM.Rect rect() { return rect; }
        public final DOM.Rect getRect() { return rect(); }
        public final ScrollRect type(Type type) { this.type = type; return this; }
        public final ScrollRect setType(Type type) { return type(type); }
        public final Type type() { return type; }
        public final Type getType() { return type(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (rect == null) throw new IllegalArgumentException("LayerTree.ScrollRect.rect is necessary field.");
            if (type == null) throw new IllegalArgumentException("LayerTree.ScrollRect.type is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            rect.toJson(strBuilder.append("\"rect\":"));
            strBuilder.append(",\"type\":").append(type);
            strBuilder.append('}');
            return strBuilder;
        }
        public ScrollRect() {}
        public ScrollRect(
            @JsonProperty("rect")DOM.Rect rect,
            @JsonProperty("type")Type type
        ) {
            this.rect = rect;
            this.type = type;
        }
    }

    /**Sticky position constraints.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class StickyPositionConstraint implements CommonDomainType {
        /**Layout rectangle of the sticky element before being shifted*/
        private DOM.Rect stickyBoxRect;
        /**Layout rectangle of the containing block of the sticky element*/
        private DOM.Rect containingBlockRect;
        /**The nearest sticky layer that shifts the sticky box
        <em>Optional.</em>*/
        private LayerId nearestLayerShiftingStickyBox;
        /**The nearest sticky layer that shifts the containing block
        <em>Optional.</em>*/
        private LayerId nearestLayerShiftingContainingBlock;
        public final StickyPositionConstraint stickyBoxRect(DOM.Rect stickyBoxRect) { this.stickyBoxRect = stickyBoxRect; return this; }
        public final StickyPositionConstraint setStickyBoxRect(DOM.Rect stickyBoxRect) { return stickyBoxRect(stickyBoxRect); }
        public final DOM.Rect stickyBoxRect() { return stickyBoxRect; }
        public final DOM.Rect getStickyBoxRect() { return stickyBoxRect(); }
        public final StickyPositionConstraint containingBlockRect(DOM.Rect containingBlockRect) { this.containingBlockRect = containingBlockRect; return this; }
        public final StickyPositionConstraint setContainingBlockRect(DOM.Rect containingBlockRect) { return containingBlockRect(containingBlockRect); }
        public final DOM.Rect containingBlockRect() { return containingBlockRect; }
        public final DOM.Rect getContainingBlockRect() { return containingBlockRect(); }
        public final StickyPositionConstraint nearestLayerShiftingStickyBox(@Nullable LayerId nearestLayerShiftingStickyBox) { this.nearestLayerShiftingStickyBox = nearestLayerShiftingStickyBox; return this; }
        public final StickyPositionConstraint optNearestLayerShiftingStickyBox(@Nullable LayerId nearestLayerShiftingStickyBox) { return nearestLayerShiftingStickyBox(nearestLayerShiftingStickyBox); }
        public final LayerId nearestLayerShiftingStickyBox() { return nearestLayerShiftingStickyBox; }
        public final LayerId getNearestLayerShiftingStickyBox() { return nearestLayerShiftingStickyBox(); }
        public final StickyPositionConstraint nearestLayerShiftingContainingBlock(@Nullable LayerId nearestLayerShiftingContainingBlock) { this.nearestLayerShiftingContainingBlock = nearestLayerShiftingContainingBlock; return this; }
        public final StickyPositionConstraint optNearestLayerShiftingContainingBlock(@Nullable LayerId nearestLayerShiftingContainingBlock) { return nearestLayerShiftingContainingBlock(nearestLayerShiftingContainingBlock); }
        public final LayerId nearestLayerShiftingContainingBlock() { return nearestLayerShiftingContainingBlock; }
        public final LayerId getNearestLayerShiftingContainingBlock() { return nearestLayerShiftingContainingBlock(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (stickyBoxRect == null) throw new IllegalArgumentException("LayerTree.StickyPositionConstraint.stickyBoxRect is necessary field.");
            if (containingBlockRect == null) throw new IllegalArgumentException("LayerTree.StickyPositionConstraint.containingBlockRect is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            stickyBoxRect.toJson(strBuilder.append("\"stickyBoxRect\":"));
            containingBlockRect.toJson(strBuilder.append(",\"containingBlockRect\":"));
            if (nearestLayerShiftingStickyBox != null) nearestLayerShiftingStickyBox.toJson(strBuilder.append(",\"nearestLayerShiftingStickyBox\":"));
            if (nearestLayerShiftingContainingBlock != null) nearestLayerShiftingContainingBlock.toJson(strBuilder.append(",\"nearestLayerShiftingContainingBlock\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public StickyPositionConstraint() {}
        public StickyPositionConstraint(
            @JsonProperty("stickyBoxRect")DOM.Rect stickyBoxRect,
            @JsonProperty("containingBlockRect")DOM.Rect containingBlockRect,
            @Nullable @JsonProperty("nearestLayerShiftingStickyBox")LayerId nearestLayerShiftingStickyBox,
            @Nullable @JsonProperty("nearestLayerShiftingContainingBlock")LayerId nearestLayerShiftingContainingBlock
        ) {
            this.stickyBoxRect = stickyBoxRect;
            this.containingBlockRect = containingBlockRect;
            this.nearestLayerShiftingStickyBox = nearestLayerShiftingStickyBox;
            this.nearestLayerShiftingContainingBlock = nearestLayerShiftingContainingBlock;
        }
    }

    /**Serialized fragment of layer picture along with its offset within the layer.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class PictureTile implements CommonDomainType {
        /**Offset from owning layer left boundary*/
        private Double x;
        /**Offset from owning layer top boundary*/
        private Double y;
        /**Base64-encoded snapshot data.*/
        private String picture;
        public final PictureTile x(Double x) { this.x = x; return this; }
        public final PictureTile setX(Double x) { return x(x); }
        public final Double x() { return x; }
        public final Double getX() { return x(); }
        public final PictureTile y(Double y) { this.y = y; return this; }
        public final PictureTile setY(Double y) { return y(y); }
        public final Double y() { return y; }
        public final Double getY() { return y(); }
        public final PictureTile picture(String picture) { this.picture = picture; return this; }
        public final PictureTile setPicture(String picture) { return picture(picture); }
        public final String picture() { return picture; }
        public final String getPicture() { return picture(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (x == null) throw new IllegalArgumentException("LayerTree.PictureTile.x is necessary field.");
            if (y == null) throw new IllegalArgumentException("LayerTree.PictureTile.y is necessary field.");
            if (picture == null) throw new IllegalArgumentException("LayerTree.PictureTile.picture is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"x\":").append(x);
            strBuilder.append(",\"y\":").append(y);
            strBuilder.append(",\"picture\":").append('"').append(DomainBase.escapeQuote(picture)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public PictureTile() {}
        public PictureTile(
            @JsonProperty("x")Double x,
            @JsonProperty("y")Double y,
            @JsonProperty("picture")String picture
        ) {
            this.x = x;
            this.y = y;
            this.picture = picture;
        }
    }

    /**Information about a compositing layer.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class Layer implements CommonDomainType {
        /**The unique id for this layer.*/
        private LayerId layerId;
        /**The id of parent (not present for root).
        <em>Optional.</em>*/
        private LayerId parentLayerId;
        /**The backend id for the node associated with this layer.
        <em>Optional.</em>*/
        private DOM.BackendNodeId backendNodeId;
        /**Offset from parent layer, X coordinate.*/
        private Double offsetX;
        /**Offset from parent layer, Y coordinate.*/
        private Double offsetY;
        /**Layer width.*/
        private Double width;
        /**Layer height.*/
        private Double height;
        /**Transformation matrix for layer, default is identity matrix
        <em>Optional.</em>*/
        private List<Double> transform;
        /**Transform anchor point X, absent if no transform specified
        <em>Optional.</em>*/
        private Double anchorX;
        /**Transform anchor point Y, absent if no transform specified
        <em>Optional.</em>*/
        private Double anchorY;
        /**Transform anchor point Z, absent if no transform specified
        <em>Optional.</em>*/
        private Double anchorZ;
        /**Indicates how many time this layer has painted.*/
        private Integer paintCount;
        /**Indicates whether this layer hosts any content, rather than being used for
transform/scrolling purposes only.*/
        private Boolean drawsContent;
        /**Set if layer is not visible.
        <em>Optional.</em>*/
        private Boolean invisible;
        /**Rectangles scrolling on main thread only.
        <em>Optional.</em>*/
        private List<ScrollRect> scrollRects;
        /**Sticky position constraint information
        <em>Optional.</em>*/
        private StickyPositionConstraint stickyPositionConstraint;
        public final Layer layerId(LayerId layerId) { this.layerId = layerId; return this; }
        public final Layer setLayerId(LayerId layerId) { return layerId(layerId); }
        public final LayerId layerId() { return layerId; }
        public final LayerId getLayerId() { return layerId(); }
        public final Layer parentLayerId(@Nullable LayerId parentLayerId) { this.parentLayerId = parentLayerId; return this; }
        public final Layer optParentLayerId(@Nullable LayerId parentLayerId) { return parentLayerId(parentLayerId); }
        public final LayerId parentLayerId() { return parentLayerId; }
        public final LayerId getParentLayerId() { return parentLayerId(); }
        public final Layer backendNodeId(@Nullable DOM.BackendNodeId backendNodeId) { this.backendNodeId = backendNodeId; return this; }
        public final Layer optBackendNodeId(@Nullable DOM.BackendNodeId backendNodeId) { return backendNodeId(backendNodeId); }
        public final DOM.BackendNodeId backendNodeId() { return backendNodeId; }
        public final DOM.BackendNodeId getBackendNodeId() { return backendNodeId(); }
        public final Layer offsetX(Double offsetX) { this.offsetX = offsetX; return this; }
        public final Layer setOffsetX(Double offsetX) { return offsetX(offsetX); }
        public final Double offsetX() { return offsetX; }
        public final Double getOffsetX() { return offsetX(); }
        public final Layer offsetY(Double offsetY) { this.offsetY = offsetY; return this; }
        public final Layer setOffsetY(Double offsetY) { return offsetY(offsetY); }
        public final Double offsetY() { return offsetY; }
        public final Double getOffsetY() { return offsetY(); }
        public final Layer width(Double width) { this.width = width; return this; }
        public final Layer setWidth(Double width) { return width(width); }
        public final Double width() { return width; }
        public final Double getWidth() { return width(); }
        public final Layer height(Double height) { this.height = height; return this; }
        public final Layer setHeight(Double height) { return height(height); }
        public final Double height() { return height; }
        public final Double getHeight() { return height(); }
        public final Layer transform(@Nullable List<Double> transform) { this.transform = transform; return this; }
        public final Layer optTransform(@Nullable List<Double> transform) { return transform(transform); }
        public final List<Double> transform() { return transform; }
        public final List<Double> getTransform() { return transform(); }
        public final Layer anchorX(@Nullable Double anchorX) { this.anchorX = anchorX; return this; }
        public final Layer optAnchorX(@Nullable Double anchorX) { return anchorX(anchorX); }
        public final Double anchorX() { return anchorX; }
        public final Double getAnchorX() { return anchorX(); }
        public final Layer anchorY(@Nullable Double anchorY) { this.anchorY = anchorY; return this; }
        public final Layer optAnchorY(@Nullable Double anchorY) { return anchorY(anchorY); }
        public final Double anchorY() { return anchorY; }
        public final Double getAnchorY() { return anchorY(); }
        public final Layer anchorZ(@Nullable Double anchorZ) { this.anchorZ = anchorZ; return this; }
        public final Layer optAnchorZ(@Nullable Double anchorZ) { return anchorZ(anchorZ); }
        public final Double anchorZ() { return anchorZ; }
        public final Double getAnchorZ() { return anchorZ(); }
        public final Layer paintCount(Integer paintCount) { this.paintCount = paintCount; return this; }
        public final Layer setPaintCount(Integer paintCount) { return paintCount(paintCount); }
        public final Integer paintCount() { return paintCount; }
        public final Integer getPaintCount() { return paintCount(); }
        public final Layer drawsContent(Boolean drawsContent) { this.drawsContent = drawsContent; return this; }
        public final Layer setDrawsContent(Boolean drawsContent) { return drawsContent(drawsContent); }
        public final Boolean drawsContent() { return drawsContent; }
        public final Boolean getDrawsContent() { return drawsContent(); }
        public final Layer invisible(@Nullable Boolean invisible) { this.invisible = invisible; return this; }
        public final Layer optInvisible(@Nullable Boolean invisible) { return invisible(invisible); }
        public final Boolean invisible() { return invisible; }
        public final Boolean getInvisible() { return invisible(); }
        public final Layer scrollRects(@Nullable List<ScrollRect> scrollRects) { this.scrollRects = scrollRects; return this; }
        public final Layer optScrollRects(@Nullable List<ScrollRect> scrollRects) { return scrollRects(scrollRects); }
        public final List<ScrollRect> scrollRects() { return scrollRects; }
        public final List<ScrollRect> getScrollRects() { return scrollRects(); }
        public final Layer stickyPositionConstraint(@Nullable StickyPositionConstraint stickyPositionConstraint) { this.stickyPositionConstraint = stickyPositionConstraint; return this; }
        public final Layer optStickyPositionConstraint(@Nullable StickyPositionConstraint stickyPositionConstraint) { return stickyPositionConstraint(stickyPositionConstraint); }
        public final StickyPositionConstraint stickyPositionConstraint() { return stickyPositionConstraint; }
        public final StickyPositionConstraint getStickyPositionConstraint() { return stickyPositionConstraint(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (layerId == null) throw new IllegalArgumentException("LayerTree.Layer.layerId is necessary field.");
            if (offsetX == null) throw new IllegalArgumentException("LayerTree.Layer.offsetX is necessary field.");
            if (offsetY == null) throw new IllegalArgumentException("LayerTree.Layer.offsetY is necessary field.");
            if (width == null) throw new IllegalArgumentException("LayerTree.Layer.width is necessary field.");
            if (height == null) throw new IllegalArgumentException("LayerTree.Layer.height is necessary field.");
            if (paintCount == null) throw new IllegalArgumentException("LayerTree.Layer.paintCount is necessary field.");
            if (drawsContent == null) throw new IllegalArgumentException("LayerTree.Layer.drawsContent is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            layerId.toJson(strBuilder.append("\"layerId\":"));
            if (parentLayerId != null) parentLayerId.toJson(strBuilder.append(",\"parentLayerId\":"));
            if (backendNodeId != null) backendNodeId.toJson(strBuilder.append(",\"backendNodeId\":"));
            strBuilder.append(",\"offsetX\":").append(offsetX);
            strBuilder.append(",\"offsetY\":").append(offsetY);
            strBuilder.append(",\"width\":").append(width);
            strBuilder.append(",\"height\":").append(height);
            if (transform != null) {
                strBuilder.append(",\"transform\":[");
                strBuilder.append(transform.get(0));
                for (int i = 1; i < transform.size(); ++i)
                    strBuilder.append(',').append(transform.get(i));
                strBuilder.append(']');
            }
            if (anchorX != null) strBuilder.append(",\"anchorX\":").append(anchorX);
            if (anchorY != null) strBuilder.append(",\"anchorY\":").append(anchorY);
            if (anchorZ != null) strBuilder.append(",\"anchorZ\":").append(anchorZ);
            strBuilder.append(",\"paintCount\":").append(paintCount);
            strBuilder.append(",\"drawsContent\":").append(drawsContent);
            if (invisible != null) strBuilder.append(",\"invisible\":").append(invisible);
            if (scrollRects != null) {
                strBuilder.append(",\"scrollRects\":[");
                scrollRects.get(0).toJson(strBuilder);
                for (int i = 1; i < scrollRects.size(); ++i)
                    scrollRects.get(i).toJson(strBuilder.append(','));
                strBuilder.append(']');
            }
            if (stickyPositionConstraint != null) stickyPositionConstraint.toJson(strBuilder.append(",\"stickyPositionConstraint\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public Layer() {}
        public Layer(
            @JsonProperty("layerId")LayerId layerId,
            @Nullable @JsonProperty("parentLayerId")LayerId parentLayerId,
            @Nullable @JsonProperty("backendNodeId")DOM.BackendNodeId backendNodeId,
            @JsonProperty("offsetX")Double offsetX,
            @JsonProperty("offsetY")Double offsetY,
            @JsonProperty("width")Double width,
            @JsonProperty("height")Double height,
            @Nullable @JsonProperty("transform")List<Double> transform,
            @Nullable @JsonProperty("anchorX")Double anchorX,
            @Nullable @JsonProperty("anchorY")Double anchorY,
            @Nullable @JsonProperty("anchorZ")Double anchorZ,
            @JsonProperty("paintCount")Integer paintCount,
            @JsonProperty("drawsContent")Boolean drawsContent,
            @Nullable @JsonProperty("invisible")Boolean invisible,
            @Nullable @JsonProperty("scrollRects")List<ScrollRect> scrollRects,
            @Nullable @JsonProperty("stickyPositionConstraint")StickyPositionConstraint stickyPositionConstraint
        ) {
            this.layerId = layerId;
            this.parentLayerId = parentLayerId;
            this.backendNodeId = backendNodeId;
            this.offsetX = offsetX;
            this.offsetY = offsetY;
            this.width = width;
            this.height = height;
            this.transform = transform;
            this.anchorX = anchorX;
            this.anchorY = anchorY;
            this.anchorZ = anchorZ;
            this.paintCount = paintCount;
            this.drawsContent = drawsContent;
            this.invisible = invisible;
            this.scrollRects = scrollRects;
            this.stickyPositionConstraint = stickyPositionConstraint;
        }
    }

    /**Array of timings, one per paint step.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class PaintProfile implements CommonDomainType {
        private List<Double> _value;
        public PaintProfile() {}
        public PaintProfile(List<Double> value) { _value = value; }
        public final PaintProfile value(List<Double> value) { _value = value; return this; }
        public final List<Double> value() { return _value; }
        public final PaintProfile setValue(List<Double> value) { return value(value); }
        public final List<Double> getValue() { return value(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (_value == null) throw new IllegalArgumentException("LayerTree.PaintProfile.value is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            return strBuilder.append(_value);
        }
    }
    /**Provides the reasons why the given layer was composited.*/
    public CompositingReasonsParameter compositingReasons() { final CompositingReasonsParameter v = new CompositingReasonsParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of compositingReasons.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class CompositingReasonsParameter extends CommandBase {
        /**The id of the layer for which we want to get the reasons it was composited.*/
        private LayerId layerId;
        public final CompositingReasonsParameter layerId(LayerId layerId) { this.layerId = layerId; return this; }
        public final CompositingReasonsParameter setLayerId(LayerId layerId) { return layerId(layerId); }
        public final LayerId layerId() { return layerId; }
        public final LayerId getLayerId() { return layerId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (layerId == null) throw new IllegalArgumentException("LayerTree.CompositingReasonsParameter.layerId is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            layerId.toJson(strBuilder.append("\"layerId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public CompositingReasonsParameter() {}
        public CompositingReasonsParameter(
            @JsonProperty("layerId")LayerId layerId
        ) {
            this();
            this.layerId = layerId;
        }
        public CompletableFuture<CompositingReasonsResult> call() {
            return super.call("LayerTree.compositingReasons", CompositingReasonsResult.class,
                (code, msg)->new CompositingReasonsResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<CompositingReasonsResult> call(Executor exec) {
            return super.call("LayerTree.compositingReasons", CompositingReasonsResult.class,
                (code, msg)->new CompositingReasonsResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of compositingReasons.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class CompositingReasonsResult extends ResultBase {
        /**A list of strings specifying reasons for the given layer to become composited.*/
        private final List<String> compositingReasons;
        public final List<String> compositingReasons() { return compositingReasons; }
        public final List<String> getCompositingReasons() { return compositingReasons(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
                        strBuilder.append("\"compositingReasons\":[");
            strBuilder.append('"').append(DomainBase.escapeQuote(compositingReasons.get(0))).append('"');
            for (int i = 1; i < compositingReasons.size(); ++i)
                strBuilder.append(",\"").append(DomainBase.escapeQuote(compositingReasons.get(i))).append('"');
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public CompositingReasonsResult(
            @JsonProperty("compositingReasons")List<String> compositingReasons
        ) {
            this.compositingReasons = compositingReasons;
        }
        public CompositingReasonsResult(ResultBase.FailedResult e) {
            super(e);
            compositingReasons = null;
        }
    }
    /**Disables compositing tree inspection.*/
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
            return super.call("LayerTree.disable", DisableResult.class,
                (code, msg)->new DisableResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<DisableResult> call(Executor exec) {
            return super.call("LayerTree.disable", DisableResult.class,
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
    /**Enables compositing tree inspection.*/
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
            return super.call("LayerTree.enable", EnableResult.class,
                (code, msg)->new EnableResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<EnableResult> call(Executor exec) {
            return super.call("LayerTree.enable", EnableResult.class,
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
    /**Returns the snapshot identifier.*/
    public LoadSnapshotParameter loadSnapshot() { final LoadSnapshotParameter v = new LoadSnapshotParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of loadSnapshot.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class LoadSnapshotParameter extends CommandBase {
        /**An array of tiles composing the snapshot.*/
        private List<PictureTile> tiles;
        public final LoadSnapshotParameter tiles(List<PictureTile> tiles) { this.tiles = tiles; return this; }
        public final LoadSnapshotParameter setTiles(List<PictureTile> tiles) { return tiles(tiles); }
        public final List<PictureTile> tiles() { return tiles; }
        public final List<PictureTile> getTiles() { return tiles(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (tiles == null) throw new IllegalArgumentException("LayerTree.LoadSnapshotParameter.tiles is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
                        strBuilder.append("\"tiles\":[");
            tiles.get(0).toJson(strBuilder);
            for (int i = 1; i < tiles.size(); ++i)
                tiles.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public LoadSnapshotParameter() {}
        public LoadSnapshotParameter(
            @JsonProperty("tiles")List<PictureTile> tiles
        ) {
            this();
            this.tiles = tiles;
        }
        public CompletableFuture<LoadSnapshotResult> call() {
            return super.call("LayerTree.loadSnapshot", LoadSnapshotResult.class,
                (code, msg)->new LoadSnapshotResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<LoadSnapshotResult> call(Executor exec) {
            return super.call("LayerTree.loadSnapshot", LoadSnapshotResult.class,
                (code, msg)->new LoadSnapshotResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of loadSnapshot.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class LoadSnapshotResult extends ResultBase {
        /**The id of the snapshot.*/
        private final SnapshotId snapshotId;
        public final SnapshotId snapshotId() { return snapshotId; }
        public final SnapshotId getSnapshotId() { return snapshotId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            snapshotId.toJson(strBuilder.append("\"snapshotId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public LoadSnapshotResult(
            @JsonProperty("snapshotId")SnapshotId snapshotId
        ) {
            this.snapshotId = snapshotId;
        }
        public LoadSnapshotResult(ResultBase.FailedResult e) {
            super(e);
            snapshotId = null;
        }
    }
    /**Returns the layer snapshot identifier.*/
    public MakeSnapshotParameter makeSnapshot() { final MakeSnapshotParameter v = new MakeSnapshotParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of makeSnapshot.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class MakeSnapshotParameter extends CommandBase {
        /**The id of the layer.*/
        private LayerId layerId;
        public final MakeSnapshotParameter layerId(LayerId layerId) { this.layerId = layerId; return this; }
        public final MakeSnapshotParameter setLayerId(LayerId layerId) { return layerId(layerId); }
        public final LayerId layerId() { return layerId; }
        public final LayerId getLayerId() { return layerId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (layerId == null) throw new IllegalArgumentException("LayerTree.MakeSnapshotParameter.layerId is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            layerId.toJson(strBuilder.append("\"layerId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public MakeSnapshotParameter() {}
        public MakeSnapshotParameter(
            @JsonProperty("layerId")LayerId layerId
        ) {
            this();
            this.layerId = layerId;
        }
        public CompletableFuture<MakeSnapshotResult> call() {
            return super.call("LayerTree.makeSnapshot", MakeSnapshotResult.class,
                (code, msg)->new MakeSnapshotResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<MakeSnapshotResult> call(Executor exec) {
            return super.call("LayerTree.makeSnapshot", MakeSnapshotResult.class,
                (code, msg)->new MakeSnapshotResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of makeSnapshot.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class MakeSnapshotResult extends ResultBase {
        /**The id of the layer snapshot.*/
        private final SnapshotId snapshotId;
        public final SnapshotId snapshotId() { return snapshotId; }
        public final SnapshotId getSnapshotId() { return snapshotId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            snapshotId.toJson(strBuilder.append("\"snapshotId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public MakeSnapshotResult(
            @JsonProperty("snapshotId")SnapshotId snapshotId
        ) {
            this.snapshotId = snapshotId;
        }
        public MakeSnapshotResult(ResultBase.FailedResult e) {
            super(e);
            snapshotId = null;
        }
    }
    /**&lt;No document in protocol.&gt;*/
    public ProfileSnapshotParameter profileSnapshot() { final ProfileSnapshotParameter v = new ProfileSnapshotParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of profileSnapshot.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ProfileSnapshotParameter extends CommandBase {
        /**The id of the layer snapshot.*/
        private SnapshotId snapshotId;
        /**The maximum number of times to replay the snapshot (1, if not specified).
        <em>Optional.</em>*/
        private Integer minRepeatCount;
        /**The minimum duration (in seconds) to replay the snapshot.
        <em>Optional.</em>*/
        private Double minDuration;
        /**The clip rectangle to apply when replaying the snapshot.
        <em>Optional.</em>*/
        private DOM.Rect clipRect;
        public final ProfileSnapshotParameter snapshotId(SnapshotId snapshotId) { this.snapshotId = snapshotId; return this; }
        public final ProfileSnapshotParameter setSnapshotId(SnapshotId snapshotId) { return snapshotId(snapshotId); }
        public final SnapshotId snapshotId() { return snapshotId; }
        public final SnapshotId getSnapshotId() { return snapshotId(); }
        public final ProfileSnapshotParameter minRepeatCount(@Nullable Integer minRepeatCount) { this.minRepeatCount = minRepeatCount; return this; }
        public final ProfileSnapshotParameter optMinRepeatCount(@Nullable Integer minRepeatCount) { return minRepeatCount(minRepeatCount); }
        public final Integer minRepeatCount() { return minRepeatCount; }
        public final Integer getMinRepeatCount() { return minRepeatCount(); }
        public final ProfileSnapshotParameter minDuration(@Nullable Double minDuration) { this.minDuration = minDuration; return this; }
        public final ProfileSnapshotParameter optMinDuration(@Nullable Double minDuration) { return minDuration(minDuration); }
        public final Double minDuration() { return minDuration; }
        public final Double getMinDuration() { return minDuration(); }
        public final ProfileSnapshotParameter clipRect(@Nullable DOM.Rect clipRect) { this.clipRect = clipRect; return this; }
        public final ProfileSnapshotParameter optClipRect(@Nullable DOM.Rect clipRect) { return clipRect(clipRect); }
        public final DOM.Rect clipRect() { return clipRect; }
        public final DOM.Rect getClipRect() { return clipRect(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (snapshotId == null) throw new IllegalArgumentException("LayerTree.ProfileSnapshotParameter.snapshotId is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            snapshotId.toJson(strBuilder.append("\"snapshotId\":"));
            if (minRepeatCount != null) strBuilder.append(",\"minRepeatCount\":").append(minRepeatCount);
            if (minDuration != null) strBuilder.append(",\"minDuration\":").append(minDuration);
            if (clipRect != null) clipRect.toJson(strBuilder.append(",\"clipRect\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public ProfileSnapshotParameter() {}
        public ProfileSnapshotParameter(
            @JsonProperty("snapshotId")SnapshotId snapshotId,
            @Nullable @JsonProperty("minRepeatCount")Integer minRepeatCount,
            @Nullable @JsonProperty("minDuration")Double minDuration,
            @Nullable @JsonProperty("clipRect")DOM.Rect clipRect
        ) {
            this();
            this.snapshotId = snapshotId;
            this.minRepeatCount = minRepeatCount;
            this.minDuration = minDuration;
            this.clipRect = clipRect;
        }
        public CompletableFuture<ProfileSnapshotResult> call() {
            return super.call("LayerTree.profileSnapshot", ProfileSnapshotResult.class,
                (code, msg)->new ProfileSnapshotResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<ProfileSnapshotResult> call(Executor exec) {
            return super.call("LayerTree.profileSnapshot", ProfileSnapshotResult.class,
                (code, msg)->new ProfileSnapshotResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of profileSnapshot.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ProfileSnapshotResult extends ResultBase {
        /**The array of paint profiles, one per run.*/
        private final List<PaintProfile> timings;
        public final List<PaintProfile> timings() { return timings; }
        public final List<PaintProfile> getTimings() { return timings(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
                        strBuilder.append("\"timings\":[");
            timings.get(0).toJson(strBuilder);
            for (int i = 1; i < timings.size(); ++i)
                timings.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public ProfileSnapshotResult(
            @JsonProperty("timings")List<PaintProfile> timings
        ) {
            this.timings = timings;
        }
        public ProfileSnapshotResult(ResultBase.FailedResult e) {
            super(e);
            timings = null;
        }
    }
    /**Releases layer snapshot captured by the back-end.*/
    public ReleaseSnapshotParameter releaseSnapshot() { final ReleaseSnapshotParameter v = new ReleaseSnapshotParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of releaseSnapshot.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ReleaseSnapshotParameter extends CommandBase {
        /**The id of the layer snapshot.*/
        private SnapshotId snapshotId;
        public final ReleaseSnapshotParameter snapshotId(SnapshotId snapshotId) { this.snapshotId = snapshotId; return this; }
        public final ReleaseSnapshotParameter setSnapshotId(SnapshotId snapshotId) { return snapshotId(snapshotId); }
        public final SnapshotId snapshotId() { return snapshotId; }
        public final SnapshotId getSnapshotId() { return snapshotId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (snapshotId == null) throw new IllegalArgumentException("LayerTree.ReleaseSnapshotParameter.snapshotId is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            snapshotId.toJson(strBuilder.append("\"snapshotId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public ReleaseSnapshotParameter() {}
        public ReleaseSnapshotParameter(
            @JsonProperty("snapshotId")SnapshotId snapshotId
        ) {
            this();
            this.snapshotId = snapshotId;
        }
        public CompletableFuture<ReleaseSnapshotResult> call() {
            return super.call("LayerTree.releaseSnapshot", ReleaseSnapshotResult.class,
                (code, msg)->new ReleaseSnapshotResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<ReleaseSnapshotResult> call(Executor exec) {
            return super.call("LayerTree.releaseSnapshot", ReleaseSnapshotResult.class,
                (code, msg)->new ReleaseSnapshotResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of releaseSnapshot.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ReleaseSnapshotResult extends ResultBase {
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
        public ReleaseSnapshotResult() { super(); }
        public ReleaseSnapshotResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Replays the layer snapshot and returns the resulting bitmap.*/
    public ReplaySnapshotParameter replaySnapshot() { final ReplaySnapshotParameter v = new ReplaySnapshotParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of replaySnapshot.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ReplaySnapshotParameter extends CommandBase {
        /**The id of the layer snapshot.*/
        private SnapshotId snapshotId;
        /**The first step to replay from (replay from the very start if not specified).
        <em>Optional.</em>*/
        private Integer fromStep;
        /**The last step to replay to (replay till the end if not specified).
        <em>Optional.</em>*/
        private Integer toStep;
        /**The scale to apply while replaying (defaults to 1).
        <em>Optional.</em>*/
        private Double scale;
        public final ReplaySnapshotParameter snapshotId(SnapshotId snapshotId) { this.snapshotId = snapshotId; return this; }
        public final ReplaySnapshotParameter setSnapshotId(SnapshotId snapshotId) { return snapshotId(snapshotId); }
        public final SnapshotId snapshotId() { return snapshotId; }
        public final SnapshotId getSnapshotId() { return snapshotId(); }
        public final ReplaySnapshotParameter fromStep(@Nullable Integer fromStep) { this.fromStep = fromStep; return this; }
        public final ReplaySnapshotParameter optFromStep(@Nullable Integer fromStep) { return fromStep(fromStep); }
        public final Integer fromStep() { return fromStep; }
        public final Integer getFromStep() { return fromStep(); }
        public final ReplaySnapshotParameter toStep(@Nullable Integer toStep) { this.toStep = toStep; return this; }
        public final ReplaySnapshotParameter optToStep(@Nullable Integer toStep) { return toStep(toStep); }
        public final Integer toStep() { return toStep; }
        public final Integer getToStep() { return toStep(); }
        public final ReplaySnapshotParameter scale(@Nullable Double scale) { this.scale = scale; return this; }
        public final ReplaySnapshotParameter optScale(@Nullable Double scale) { return scale(scale); }
        public final Double scale() { return scale; }
        public final Double getScale() { return scale(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (snapshotId == null) throw new IllegalArgumentException("LayerTree.ReplaySnapshotParameter.snapshotId is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            snapshotId.toJson(strBuilder.append("\"snapshotId\":"));
            if (fromStep != null) strBuilder.append(",\"fromStep\":").append(fromStep);
            if (toStep != null) strBuilder.append(",\"toStep\":").append(toStep);
            if (scale != null) strBuilder.append(",\"scale\":").append(scale);
            strBuilder.append('}');
            return strBuilder;
        }
        public ReplaySnapshotParameter() {}
        public ReplaySnapshotParameter(
            @JsonProperty("snapshotId")SnapshotId snapshotId,
            @Nullable @JsonProperty("fromStep")Integer fromStep,
            @Nullable @JsonProperty("toStep")Integer toStep,
            @Nullable @JsonProperty("scale")Double scale
        ) {
            this();
            this.snapshotId = snapshotId;
            this.fromStep = fromStep;
            this.toStep = toStep;
            this.scale = scale;
        }
        public CompletableFuture<ReplaySnapshotResult> call() {
            return super.call("LayerTree.replaySnapshot", ReplaySnapshotResult.class,
                (code, msg)->new ReplaySnapshotResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<ReplaySnapshotResult> call(Executor exec) {
            return super.call("LayerTree.replaySnapshot", ReplaySnapshotResult.class,
                (code, msg)->new ReplaySnapshotResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of replaySnapshot.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ReplaySnapshotResult extends ResultBase {
        /**A data: URL for resulting image.*/
        private final String dataURL;
        public final String dataURL() { return dataURL; }
        public final String getDataURL() { return dataURL(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"dataURL\":").append('"').append(DomainBase.escapeQuote(dataURL)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public ReplaySnapshotResult(
            @JsonProperty("dataURL")String dataURL
        ) {
            this.dataURL = dataURL;
        }
        public ReplaySnapshotResult(ResultBase.FailedResult e) {
            super(e);
            dataURL = null;
        }
    }
    /**Replays the layer snapshot and returns canvas log.*/
    public SnapshotCommandLogParameter snapshotCommandLog() { final SnapshotCommandLogParameter v = new SnapshotCommandLogParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of snapshotCommandLog.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SnapshotCommandLogParameter extends CommandBase {
        /**The id of the layer snapshot.*/
        private SnapshotId snapshotId;
        public final SnapshotCommandLogParameter snapshotId(SnapshotId snapshotId) { this.snapshotId = snapshotId; return this; }
        public final SnapshotCommandLogParameter setSnapshotId(SnapshotId snapshotId) { return snapshotId(snapshotId); }
        public final SnapshotId snapshotId() { return snapshotId; }
        public final SnapshotId getSnapshotId() { return snapshotId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (snapshotId == null) throw new IllegalArgumentException("LayerTree.SnapshotCommandLogParameter.snapshotId is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            snapshotId.toJson(strBuilder.append("\"snapshotId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public SnapshotCommandLogParameter() {}
        public SnapshotCommandLogParameter(
            @JsonProperty("snapshotId")SnapshotId snapshotId
        ) {
            this();
            this.snapshotId = snapshotId;
        }
        public CompletableFuture<SnapshotCommandLogResult> call() {
            return super.call("LayerTree.snapshotCommandLog", SnapshotCommandLogResult.class,
                (code, msg)->new SnapshotCommandLogResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SnapshotCommandLogResult> call(Executor exec) {
            return super.call("LayerTree.snapshotCommandLog", SnapshotCommandLogResult.class,
                (code, msg)->new SnapshotCommandLogResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of snapshotCommandLog.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SnapshotCommandLogResult extends ResultBase {
        /**The array of canvas function calls.*/
        private final List<Object> commandLog;
        public final List<Object> commandLog() { return commandLog; }
        public final List<Object> getCommandLog() { return commandLog(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
                        strBuilder.append("\"commandLog\":[");
            strBuilder.append(commandLog.get(0));
            for (int i = 1; i < commandLog.size(); ++i)
                strBuilder.append(',').append(commandLog.get(i));
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public SnapshotCommandLogResult(
            @JsonProperty("commandLog")List<Object> commandLog
        ) {
            this.commandLog = commandLog;
        }
        public SnapshotCommandLogResult(ResultBase.FailedResult e) {
            super(e);
            commandLog = null;
        }
    }
    /**Event parameter of LayerTree.layerPainted.
     @see #onLayerPainted*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class LayerPaintedEventParameter implements CommonDomainType {
        /**The id of the painted layer.*/
        private final LayerId layerId;
        /**Clip rectangle.*/
        private final DOM.Rect clip;
        public final LayerId layerId() { return layerId; }
        public final LayerId getLayerId() { return layerId(); }
        public final DOM.Rect clip() { return clip; }
        public final DOM.Rect getClip() { return clip(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            layerId.toJson(strBuilder.append("\"layerId\":"));
            clip.toJson(strBuilder.append(",\"clip\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        LayerPaintedEventParameter(
            @JsonProperty("layerId")LayerId layerId,
            @JsonProperty("clip")DOM.Rect clip
        ) {
            this.layerId = layerId;
            this.clip = clip;
        }
    }
    /**&lt;No document in protocol.&gt;
     @see LayerPaintedEventParameter*/
    public void onLayerPainted(Consumer<LayerPaintedEventParameter> callback) {
        registerEventCallback("LayerTree.layerPainted", node -> {
            LayerPaintedEventParameter param;
            try { param = EventCenter.deserializeJson(node, LayerPaintedEventParameter.class); }
            catch (IOException e) { e.printStackTrace(); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of LayerTree.layerTreeDidChange.
     @see #onLayerTreeDidChange*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class LayerTreeDidChangeEventParameter implements CommonDomainType {
        /**Layer tree, absent if not in the comspositing mode.
        <em>Optional.</em>*/
        private final List<Layer> layers;
        public final List<Layer> layers() { return layers; }
        public final List<Layer> getLayers() { return layers(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            if (layers != null) {
                strBuilder.append("\"layers\":[");
                layers.get(0).toJson(strBuilder);
                for (int i = 1; i < layers.size(); ++i)
                    layers.get(i).toJson(strBuilder.append(','));
                strBuilder.append(']');
            }
            strBuilder.append('}');
            return strBuilder;
        }
        LayerTreeDidChangeEventParameter(
            @Nullable @JsonProperty("layers")List<Layer> layers
        ) {
            this.layers = layers;
        }
    }
    /**&lt;No document in protocol.&gt;
     @see LayerTreeDidChangeEventParameter*/
    public void onLayerTreeDidChange(Consumer<LayerTreeDidChangeEventParameter> callback) {
        registerEventCallback("LayerTree.layerTreeDidChange", node -> {
            LayerTreeDidChangeEventParameter param;
            try { param = EventCenter.deserializeJson(node, LayerTreeDidChangeEventParameter.class); }
            catch (IOException e) { e.printStackTrace(); return; }
            callback.accept(param);
        });
    }
}

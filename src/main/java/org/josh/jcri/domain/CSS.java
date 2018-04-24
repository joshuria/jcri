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

/**This domain exposes CSS read/write operations. All CSS objects (stylesheets, rules, and styles)
have an associated `id` used in subsequent operations on the related object. Each object type has
a specific `id` structure, and those are not interchangeable between objects of different kinds.
CSS objects can be loaded using the `get*ForNode()` calls (which accept a DOM node id). A client
can also keep track of stylesheets via the `styleSheetAdded`/`styleSheetRemoved` events and
subsequently load the required stylesheet contents using the `getStyleSheet[Text]()` methods.
<p>From: browser_protocol.json</p>
<p>Protocol version: 1.3</p>
<p><strong>Experimental.</strong></p>
 @see DOM
 @author Joshua*/
@ParametersAreNonnullByDefault public class CSS extends DomainBase {
    public CSS(EventCenter evt, WebSocket ws) { super(evt, ws); }

    /**&lt;No document in protocol.&gt;*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class StyleSheetId implements CommonDomainType {
        private String _value;
        public StyleSheetId() {}
        public StyleSheetId(String value) { _value = value; }
        public final StyleSheetId value(String value) { _value = value; return this; }
        public final String value() { return _value; }
        public final StyleSheetId setValue(String value) { return value(value); }
        public final String getValue() { return value(); }
        @Override public String toString() { return _value; }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (_value == null) throw new IllegalArgumentException("CSS.StyleSheetId.value is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            return strBuilder.append('"').append(DomainBase.escapeQuote(_value)).append('"');
        }
    }

    /**Stylesheet type: "injected" for stylesheets injected via extension, "user-agent" for user-agent
stylesheets, "inspector" for stylesheets created by the inspector (i.e. those holding the "via
inspector" rules), "regular" for regular stylesheets.*/
    @ParametersAreNonnullByDefault public enum StyleSheetOrigin implements CommonDomainType {
        Injected("injected"),
        User_agent("user-agent"),
        Inspector("inspector"),
        Regular("regular");

        private final String _value;
        /**Convert string representation to type.
         @throws IllegalArgumentException if given value cannot convert to enum type. */
        @JsonCreator public static StyleSheetOrigin of(String value) {
            return Enum.valueOf(StyleSheetOrigin.class, value.substring(0, 1).toUpperCase() + value.substring(1));
        }
        StyleSheetOrigin(String value) { _value = value; }
        /**Check if parameter fields of method are all valid. */
        @Override public void check() throws IllegalArgumentException { /* Need not check */ }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) { return strBuilder.append('"').append(_value).append('"'); }
        @Override public String toString() { return "\"" + _value + "\""; }
    }

    /**CSS rule collection for a single pseudo style.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class PseudoElementMatches implements CommonDomainType {
        /**Pseudo element type.*/
        private DOM.PseudoType pseudoType;
        /**Matches of CSS rules applicable to the pseudo style.*/
        private List<RuleMatch> matches;
        public final PseudoElementMatches pseudoType(DOM.PseudoType pseudoType) { this.pseudoType = pseudoType; return this; }
        public final PseudoElementMatches setPseudoType(DOM.PseudoType pseudoType) { return pseudoType(pseudoType); }
        public final DOM.PseudoType pseudoType() { return pseudoType; }
        public final DOM.PseudoType getPseudoType() { return pseudoType(); }
        public final PseudoElementMatches matches(List<RuleMatch> matches) { this.matches = matches; return this; }
        public final PseudoElementMatches setMatches(List<RuleMatch> matches) { return matches(matches); }
        public final List<RuleMatch> matches() { return matches; }
        public final List<RuleMatch> getMatches() { return matches(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (pseudoType == null) throw new IllegalArgumentException("CSS.PseudoElementMatches.pseudoType is necessary field.");
            if (matches == null) throw new IllegalArgumentException("CSS.PseudoElementMatches.matches is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            pseudoType.toJson(strBuilder.append("\"pseudoType\":"));
                        strBuilder.append(",\"matches\":[");
            matches.get(0).toJson(strBuilder);
            for (int i = 1; i < matches.size(); ++i)
                matches.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public PseudoElementMatches() {}
        public PseudoElementMatches(
            @JsonProperty("pseudoType")DOM.PseudoType pseudoType,
            @JsonProperty("matches")List<RuleMatch> matches
        ) {
            this.pseudoType = pseudoType;
            this.matches = matches;
        }
    }

    /**Inherited CSS rule collection from ancestor node.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class InheritedStyleEntry implements CommonDomainType {
        /**The ancestor node's inline style, if any, in the style inheritance chain.
        <em>Optional.</em>*/
        private CSSStyle inlineStyle;
        /**Matches of CSS rules matching the ancestor node in the style inheritance chain.*/
        private List<RuleMatch> matchedCSSRules;
        public final InheritedStyleEntry inlineStyle(@Nullable CSSStyle inlineStyle) { this.inlineStyle = inlineStyle; return this; }
        public final InheritedStyleEntry optInlineStyle(@Nullable CSSStyle inlineStyle) { return inlineStyle(inlineStyle); }
        public final CSSStyle inlineStyle() { return inlineStyle; }
        public final CSSStyle getInlineStyle() { return inlineStyle(); }
        public final InheritedStyleEntry matchedCSSRules(List<RuleMatch> matchedCSSRules) { this.matchedCSSRules = matchedCSSRules; return this; }
        public final InheritedStyleEntry setMatchedCSSRules(List<RuleMatch> matchedCSSRules) { return matchedCSSRules(matchedCSSRules); }
        public final List<RuleMatch> matchedCSSRules() { return matchedCSSRules; }
        public final List<RuleMatch> getMatchedCSSRules() { return matchedCSSRules(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (matchedCSSRules == null) throw new IllegalArgumentException("CSS.InheritedStyleEntry.matchedCSSRules is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            if (inlineStyle != null) inlineStyle.toJson(strBuilder.append("\"inlineStyle\":"));
                        strBuilder.append(",\"matchedCSSRules\":[");
            matchedCSSRules.get(0).toJson(strBuilder);
            for (int i = 1; i < matchedCSSRules.size(); ++i)
                matchedCSSRules.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public InheritedStyleEntry() {}
        public InheritedStyleEntry(
            @Nullable @JsonProperty("inlineStyle")CSSStyle inlineStyle,
            @JsonProperty("matchedCSSRules")List<RuleMatch> matchedCSSRules
        ) {
            this.inlineStyle = inlineStyle;
            this.matchedCSSRules = matchedCSSRules;
        }
    }

    /**Match data for a CSS rule.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class RuleMatch implements CommonDomainType {
        /**CSS rule in the match.*/
        private CSSRule rule;
        /**Matching selector indices in the rule's selectorList selectors (0-based).*/
        private List<Integer> matchingSelectors;
        public final RuleMatch rule(CSSRule rule) { this.rule = rule; return this; }
        public final RuleMatch setRule(CSSRule rule) { return rule(rule); }
        public final CSSRule rule() { return rule; }
        public final CSSRule getRule() { return rule(); }
        public final RuleMatch matchingSelectors(List<Integer> matchingSelectors) { this.matchingSelectors = matchingSelectors; return this; }
        public final RuleMatch setMatchingSelectors(List<Integer> matchingSelectors) { return matchingSelectors(matchingSelectors); }
        public final List<Integer> matchingSelectors() { return matchingSelectors; }
        public final List<Integer> getMatchingSelectors() { return matchingSelectors(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (rule == null) throw new IllegalArgumentException("CSS.RuleMatch.rule is necessary field.");
            if (matchingSelectors == null) throw new IllegalArgumentException("CSS.RuleMatch.matchingSelectors is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            rule.toJson(strBuilder.append("\"rule\":"));
                        strBuilder.append(",\"matchingSelectors\":[");
            strBuilder.append(matchingSelectors.get(0));
            for (int i = 1; i < matchingSelectors.size(); ++i)
                strBuilder.append(',').append(matchingSelectors.get(i));
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public RuleMatch() {}
        public RuleMatch(
            @JsonProperty("rule")CSSRule rule,
            @JsonProperty("matchingSelectors")List<Integer> matchingSelectors
        ) {
            this.rule = rule;
            this.matchingSelectors = matchingSelectors;
        }
    }

    /**Data for a simple selector (these are delimited by commas in a selector list).*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class Value implements CommonDomainType {
        /**Value text.*/
        private String text;
        /**Value range in the underlying resource (if available).
        <em>Optional.</em>*/
        private SourceRange range;
        public final Value text(String text) { this.text = text; return this; }
        public final Value setText(String text) { return text(text); }
        public final String text() { return text; }
        public final String getText() { return text(); }
        public final Value range(@Nullable SourceRange range) { this.range = range; return this; }
        public final Value optRange(@Nullable SourceRange range) { return range(range); }
        public final SourceRange range() { return range; }
        public final SourceRange getRange() { return range(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (text == null) throw new IllegalArgumentException("CSS.Value.text is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"text\":").append('"').append(DomainBase.escapeQuote(text)).append('"');
            if (range != null) range.toJson(strBuilder.append(",\"range\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public Value() {}
        public Value(
            @JsonProperty("text")String text,
            @Nullable @JsonProperty("range")SourceRange range
        ) {
            this.text = text;
            this.range = range;
        }
    }

    /**Selector list data.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SelectorList implements CommonDomainType {
        /**Selectors in the list.*/
        private List<Value> selectors;
        /**Rule selector text.*/
        private String text;
        public final SelectorList selectors(List<Value> selectors) { this.selectors = selectors; return this; }
        public final SelectorList setSelectors(List<Value> selectors) { return selectors(selectors); }
        public final List<Value> selectors() { return selectors; }
        public final List<Value> getSelectors() { return selectors(); }
        public final SelectorList text(String text) { this.text = text; return this; }
        public final SelectorList setText(String text) { return text(text); }
        public final String text() { return text; }
        public final String getText() { return text(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (selectors == null) throw new IllegalArgumentException("CSS.SelectorList.selectors is necessary field.");
            if (text == null) throw new IllegalArgumentException("CSS.SelectorList.text is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
                        strBuilder.append("\"selectors\":[");
            selectors.get(0).toJson(strBuilder);
            for (int i = 1; i < selectors.size(); ++i)
                selectors.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            strBuilder.append(",\"text\":").append('"').append(DomainBase.escapeQuote(text)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public SelectorList() {}
        public SelectorList(
            @JsonProperty("selectors")List<Value> selectors,
            @JsonProperty("text")String text
        ) {
            this.selectors = selectors;
            this.text = text;
        }
    }

    /**CSS stylesheet metainformation.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class CSSStyleSheetHeader implements CommonDomainType {
        /**The stylesheet identifier.*/
        private StyleSheetId styleSheetId;
        /**Owner frame identifier.*/
        private Page.FrameId frameId;
        /**Stylesheet resource URL.*/
        private String sourceURL;
        /**URL of source map associated with the stylesheet (if any).
        <em>Optional.</em>*/
        private String sourceMapURL;
        /**Stylesheet origin.*/
        private StyleSheetOrigin origin;
        /**Stylesheet title.*/
        private String title;
        /**The backend id for the owner node of the stylesheet.
        <em>Optional.</em>*/
        private DOM.BackendNodeId ownerNode;
        /**Denotes whether the stylesheet is disabled.*/
        private Boolean disabled;
        /**Whether the sourceURL field value comes from the sourceURL comment.
        <em>Optional.</em>*/
        private Boolean hasSourceURL;
        /**Whether this stylesheet is created for STYLE tag by parser. This flag is not set for
document.written STYLE tags.*/
        private Boolean isInline;
        /**Line offset of the stylesheet within the resource (zero based).*/
        private Double startLine;
        /**Column offset of the stylesheet within the resource (zero based).*/
        private Double startColumn;
        /**Size of the content (in characters).*/
        private Double length;
        public final CSSStyleSheetHeader styleSheetId(StyleSheetId styleSheetId) { this.styleSheetId = styleSheetId; return this; }
        public final CSSStyleSheetHeader setStyleSheetId(StyleSheetId styleSheetId) { return styleSheetId(styleSheetId); }
        public final StyleSheetId styleSheetId() { return styleSheetId; }
        public final StyleSheetId getStyleSheetId() { return styleSheetId(); }
        public final CSSStyleSheetHeader frameId(Page.FrameId frameId) { this.frameId = frameId; return this; }
        public final CSSStyleSheetHeader setFrameId(Page.FrameId frameId) { return frameId(frameId); }
        public final Page.FrameId frameId() { return frameId; }
        public final Page.FrameId getFrameId() { return frameId(); }
        public final CSSStyleSheetHeader sourceURL(String sourceURL) { this.sourceURL = sourceURL; return this; }
        public final CSSStyleSheetHeader setSourceURL(String sourceURL) { return sourceURL(sourceURL); }
        public final String sourceURL() { return sourceURL; }
        public final String getSourceURL() { return sourceURL(); }
        public final CSSStyleSheetHeader sourceMapURL(@Nullable String sourceMapURL) { this.sourceMapURL = sourceMapURL; return this; }
        public final CSSStyleSheetHeader optSourceMapURL(@Nullable String sourceMapURL) { return sourceMapURL(sourceMapURL); }
        public final String sourceMapURL() { return sourceMapURL; }
        public final String getSourceMapURL() { return sourceMapURL(); }
        public final CSSStyleSheetHeader origin(StyleSheetOrigin origin) { this.origin = origin; return this; }
        public final CSSStyleSheetHeader setOrigin(StyleSheetOrigin origin) { return origin(origin); }
        public final StyleSheetOrigin origin() { return origin; }
        public final StyleSheetOrigin getOrigin() { return origin(); }
        public final CSSStyleSheetHeader title(String title) { this.title = title; return this; }
        public final CSSStyleSheetHeader setTitle(String title) { return title(title); }
        public final String title() { return title; }
        public final String getTitle() { return title(); }
        public final CSSStyleSheetHeader ownerNode(@Nullable DOM.BackendNodeId ownerNode) { this.ownerNode = ownerNode; return this; }
        public final CSSStyleSheetHeader optOwnerNode(@Nullable DOM.BackendNodeId ownerNode) { return ownerNode(ownerNode); }
        public final DOM.BackendNodeId ownerNode() { return ownerNode; }
        public final DOM.BackendNodeId getOwnerNode() { return ownerNode(); }
        public final CSSStyleSheetHeader disabled(Boolean disabled) { this.disabled = disabled; return this; }
        public final CSSStyleSheetHeader setDisabled(Boolean disabled) { return disabled(disabled); }
        public final Boolean disabled() { return disabled; }
        public final Boolean getDisabled() { return disabled(); }
        public final CSSStyleSheetHeader hasSourceURL(@Nullable Boolean hasSourceURL) { this.hasSourceURL = hasSourceURL; return this; }
        public final CSSStyleSheetHeader optHasSourceURL(@Nullable Boolean hasSourceURL) { return hasSourceURL(hasSourceURL); }
        public final Boolean hasSourceURL() { return hasSourceURL; }
        public final Boolean getHasSourceURL() { return hasSourceURL(); }
        public final CSSStyleSheetHeader isInline(Boolean isInline) { this.isInline = isInline; return this; }
        public final CSSStyleSheetHeader setIsInline(Boolean isInline) { return isInline(isInline); }
        public final Boolean isInline() { return isInline; }
        public final Boolean getIsInline() { return isInline(); }
        public final CSSStyleSheetHeader startLine(Double startLine) { this.startLine = startLine; return this; }
        public final CSSStyleSheetHeader setStartLine(Double startLine) { return startLine(startLine); }
        public final Double startLine() { return startLine; }
        public final Double getStartLine() { return startLine(); }
        public final CSSStyleSheetHeader startColumn(Double startColumn) { this.startColumn = startColumn; return this; }
        public final CSSStyleSheetHeader setStartColumn(Double startColumn) { return startColumn(startColumn); }
        public final Double startColumn() { return startColumn; }
        public final Double getStartColumn() { return startColumn(); }
        public final CSSStyleSheetHeader length(Double length) { this.length = length; return this; }
        public final CSSStyleSheetHeader setLength(Double length) { return length(length); }
        public final Double length() { return length; }
        public final Double getLength() { return length(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (styleSheetId == null) throw new IllegalArgumentException("CSS.CSSStyleSheetHeader.styleSheetId is necessary field.");
            if (frameId == null) throw new IllegalArgumentException("CSS.CSSStyleSheetHeader.frameId is necessary field.");
            if (sourceURL == null) throw new IllegalArgumentException("CSS.CSSStyleSheetHeader.sourceURL is necessary field.");
            if (origin == null) throw new IllegalArgumentException("CSS.CSSStyleSheetHeader.origin is necessary field.");
            if (title == null) throw new IllegalArgumentException("CSS.CSSStyleSheetHeader.title is necessary field.");
            if (disabled == null) throw new IllegalArgumentException("CSS.CSSStyleSheetHeader.disabled is necessary field.");
            if (isInline == null) throw new IllegalArgumentException("CSS.CSSStyleSheetHeader.isInline is necessary field.");
            if (startLine == null) throw new IllegalArgumentException("CSS.CSSStyleSheetHeader.startLine is necessary field.");
            if (startColumn == null) throw new IllegalArgumentException("CSS.CSSStyleSheetHeader.startColumn is necessary field.");
            if (length == null) throw new IllegalArgumentException("CSS.CSSStyleSheetHeader.length is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            styleSheetId.toJson(strBuilder.append("\"styleSheetId\":"));
            frameId.toJson(strBuilder.append(",\"frameId\":"));
            strBuilder.append(",\"sourceURL\":").append('"').append(DomainBase.escapeQuote(sourceURL)).append('"');
            if (sourceMapURL != null) strBuilder.append(",\"sourceMapURL\":").append('"').append(DomainBase.escapeQuote(sourceMapURL)).append('"');
            origin.toJson(strBuilder.append(",\"origin\":"));
            strBuilder.append(",\"title\":").append('"').append(DomainBase.escapeQuote(title)).append('"');
            if (ownerNode != null) ownerNode.toJson(strBuilder.append(",\"ownerNode\":"));
            strBuilder.append(",\"disabled\":").append(disabled);
            if (hasSourceURL != null) strBuilder.append(",\"hasSourceURL\":").append(hasSourceURL);
            strBuilder.append(",\"isInline\":").append(isInline);
            strBuilder.append(",\"startLine\":").append(startLine);
            strBuilder.append(",\"startColumn\":").append(startColumn);
            strBuilder.append(",\"length\":").append(length);
            strBuilder.append('}');
            return strBuilder;
        }
        public CSSStyleSheetHeader() {}
        public CSSStyleSheetHeader(
            @JsonProperty("styleSheetId")StyleSheetId styleSheetId,
            @JsonProperty("frameId")Page.FrameId frameId,
            @JsonProperty("sourceURL")String sourceURL,
            @Nullable @JsonProperty("sourceMapURL")String sourceMapURL,
            @JsonProperty("origin")StyleSheetOrigin origin,
            @JsonProperty("title")String title,
            @Nullable @JsonProperty("ownerNode")DOM.BackendNodeId ownerNode,
            @JsonProperty("disabled")Boolean disabled,
            @Nullable @JsonProperty("hasSourceURL")Boolean hasSourceURL,
            @JsonProperty("isInline")Boolean isInline,
            @JsonProperty("startLine")Double startLine,
            @JsonProperty("startColumn")Double startColumn,
            @JsonProperty("length")Double length
        ) {
            this.styleSheetId = styleSheetId;
            this.frameId = frameId;
            this.sourceURL = sourceURL;
            this.sourceMapURL = sourceMapURL;
            this.origin = origin;
            this.title = title;
            this.ownerNode = ownerNode;
            this.disabled = disabled;
            this.hasSourceURL = hasSourceURL;
            this.isInline = isInline;
            this.startLine = startLine;
            this.startColumn = startColumn;
            this.length = length;
        }
    }

    /**CSS rule representation.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class CSSRule implements CommonDomainType {
        /**The css style sheet identifier (absent for user agent stylesheet and user-specified
stylesheet rules) this rule came from.
        <em>Optional.</em>*/
        private StyleSheetId styleSheetId;
        /**Rule selector data.*/
        private SelectorList selectorList;
        /**Parent stylesheet's origin.*/
        private StyleSheetOrigin origin;
        /**Associated style declaration.*/
        private CSSStyle style;
        /**Media list array (for rules involving media queries). The array enumerates media queries
starting with the innermost one, going outwards.
        <em>Optional.</em>*/
        private List<CSSMedia> media;
        public final CSSRule styleSheetId(@Nullable StyleSheetId styleSheetId) { this.styleSheetId = styleSheetId; return this; }
        public final CSSRule optStyleSheetId(@Nullable StyleSheetId styleSheetId) { return styleSheetId(styleSheetId); }
        public final StyleSheetId styleSheetId() { return styleSheetId; }
        public final StyleSheetId getStyleSheetId() { return styleSheetId(); }
        public final CSSRule selectorList(SelectorList selectorList) { this.selectorList = selectorList; return this; }
        public final CSSRule setSelectorList(SelectorList selectorList) { return selectorList(selectorList); }
        public final SelectorList selectorList() { return selectorList; }
        public final SelectorList getSelectorList() { return selectorList(); }
        public final CSSRule origin(StyleSheetOrigin origin) { this.origin = origin; return this; }
        public final CSSRule setOrigin(StyleSheetOrigin origin) { return origin(origin); }
        public final StyleSheetOrigin origin() { return origin; }
        public final StyleSheetOrigin getOrigin() { return origin(); }
        public final CSSRule style(CSSStyle style) { this.style = style; return this; }
        public final CSSRule setStyle(CSSStyle style) { return style(style); }
        public final CSSStyle style() { return style; }
        public final CSSStyle getStyle() { return style(); }
        public final CSSRule media(@Nullable List<CSSMedia> media) { this.media = media; return this; }
        public final CSSRule optMedia(@Nullable List<CSSMedia> media) { return media(media); }
        public final List<CSSMedia> media() { return media; }
        public final List<CSSMedia> getMedia() { return media(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (selectorList == null) throw new IllegalArgumentException("CSS.CSSRule.selectorList is necessary field.");
            if (origin == null) throw new IllegalArgumentException("CSS.CSSRule.origin is necessary field.");
            if (style == null) throw new IllegalArgumentException("CSS.CSSRule.style is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            if (styleSheetId != null) styleSheetId.toJson(strBuilder.append("\"styleSheetId\":"));
            selectorList.toJson(strBuilder.append(",\"selectorList\":"));
            origin.toJson(strBuilder.append(",\"origin\":"));
            style.toJson(strBuilder.append(",\"style\":"));
            if (media != null) {
                strBuilder.append(",\"media\":[");
                media.get(0).toJson(strBuilder);
                for (int i = 1; i < media.size(); ++i)
                    media.get(i).toJson(strBuilder.append(','));
                strBuilder.append(']');
            }
            strBuilder.append('}');
            return strBuilder;
        }
        public CSSRule() {}
        public CSSRule(
            @Nullable @JsonProperty("styleSheetId")StyleSheetId styleSheetId,
            @JsonProperty("selectorList")SelectorList selectorList,
            @JsonProperty("origin")StyleSheetOrigin origin,
            @JsonProperty("style")CSSStyle style,
            @Nullable @JsonProperty("media")List<CSSMedia> media
        ) {
            this.styleSheetId = styleSheetId;
            this.selectorList = selectorList;
            this.origin = origin;
            this.style = style;
            this.media = media;
        }
    }

    /**CSS coverage information.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class RuleUsage implements CommonDomainType {
        /**The css style sheet identifier (absent for user agent stylesheet and user-specified
stylesheet rules) this rule came from.*/
        private StyleSheetId styleSheetId;
        /**Offset of the start of the rule (including selector) from the beginning of the stylesheet.*/
        private Double startOffset;
        /**Offset of the end of the rule body from the beginning of the stylesheet.*/
        private Double endOffset;
        /**Indicates whether the rule was actually used by some element in the page.*/
        private Boolean used;
        public final RuleUsage styleSheetId(StyleSheetId styleSheetId) { this.styleSheetId = styleSheetId; return this; }
        public final RuleUsage setStyleSheetId(StyleSheetId styleSheetId) { return styleSheetId(styleSheetId); }
        public final StyleSheetId styleSheetId() { return styleSheetId; }
        public final StyleSheetId getStyleSheetId() { return styleSheetId(); }
        public final RuleUsage startOffset(Double startOffset) { this.startOffset = startOffset; return this; }
        public final RuleUsage setStartOffset(Double startOffset) { return startOffset(startOffset); }
        public final Double startOffset() { return startOffset; }
        public final Double getStartOffset() { return startOffset(); }
        public final RuleUsage endOffset(Double endOffset) { this.endOffset = endOffset; return this; }
        public final RuleUsage setEndOffset(Double endOffset) { return endOffset(endOffset); }
        public final Double endOffset() { return endOffset; }
        public final Double getEndOffset() { return endOffset(); }
        public final RuleUsage used(Boolean used) { this.used = used; return this; }
        public final RuleUsage setUsed(Boolean used) { return used(used); }
        public final Boolean used() { return used; }
        public final Boolean getUsed() { return used(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (styleSheetId == null) throw new IllegalArgumentException("CSS.RuleUsage.styleSheetId is necessary field.");
            if (startOffset == null) throw new IllegalArgumentException("CSS.RuleUsage.startOffset is necessary field.");
            if (endOffset == null) throw new IllegalArgumentException("CSS.RuleUsage.endOffset is necessary field.");
            if (used == null) throw new IllegalArgumentException("CSS.RuleUsage.used is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            styleSheetId.toJson(strBuilder.append("\"styleSheetId\":"));
            strBuilder.append(",\"startOffset\":").append(startOffset);
            strBuilder.append(",\"endOffset\":").append(endOffset);
            strBuilder.append(",\"used\":").append(used);
            strBuilder.append('}');
            return strBuilder;
        }
        public RuleUsage() {}
        public RuleUsage(
            @JsonProperty("styleSheetId")StyleSheetId styleSheetId,
            @JsonProperty("startOffset")Double startOffset,
            @JsonProperty("endOffset")Double endOffset,
            @JsonProperty("used")Boolean used
        ) {
            this.styleSheetId = styleSheetId;
            this.startOffset = startOffset;
            this.endOffset = endOffset;
            this.used = used;
        }
    }

    /**Text range within a resource. All numbers are zero-based.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SourceRange implements CommonDomainType {
        /**Start line of range.*/
        private Integer startLine;
        /**Start column of range (inclusive).*/
        private Integer startColumn;
        /**End line of range*/
        private Integer endLine;
        /**End column of range (exclusive).*/
        private Integer endColumn;
        public final SourceRange startLine(Integer startLine) { this.startLine = startLine; return this; }
        public final SourceRange setStartLine(Integer startLine) { return startLine(startLine); }
        public final Integer startLine() { return startLine; }
        public final Integer getStartLine() { return startLine(); }
        public final SourceRange startColumn(Integer startColumn) { this.startColumn = startColumn; return this; }
        public final SourceRange setStartColumn(Integer startColumn) { return startColumn(startColumn); }
        public final Integer startColumn() { return startColumn; }
        public final Integer getStartColumn() { return startColumn(); }
        public final SourceRange endLine(Integer endLine) { this.endLine = endLine; return this; }
        public final SourceRange setEndLine(Integer endLine) { return endLine(endLine); }
        public final Integer endLine() { return endLine; }
        public final Integer getEndLine() { return endLine(); }
        public final SourceRange endColumn(Integer endColumn) { this.endColumn = endColumn; return this; }
        public final SourceRange setEndColumn(Integer endColumn) { return endColumn(endColumn); }
        public final Integer endColumn() { return endColumn; }
        public final Integer getEndColumn() { return endColumn(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (startLine == null) throw new IllegalArgumentException("CSS.SourceRange.startLine is necessary field.");
            if (startColumn == null) throw new IllegalArgumentException("CSS.SourceRange.startColumn is necessary field.");
            if (endLine == null) throw new IllegalArgumentException("CSS.SourceRange.endLine is necessary field.");
            if (endColumn == null) throw new IllegalArgumentException("CSS.SourceRange.endColumn is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"startLine\":").append(startLine);
            strBuilder.append(",\"startColumn\":").append(startColumn);
            strBuilder.append(",\"endLine\":").append(endLine);
            strBuilder.append(",\"endColumn\":").append(endColumn);
            strBuilder.append('}');
            return strBuilder;
        }
        public SourceRange() {}
        public SourceRange(
            @JsonProperty("startLine")Integer startLine,
            @JsonProperty("startColumn")Integer startColumn,
            @JsonProperty("endLine")Integer endLine,
            @JsonProperty("endColumn")Integer endColumn
        ) {
            this.startLine = startLine;
            this.startColumn = startColumn;
            this.endLine = endLine;
            this.endColumn = endColumn;
        }
    }

    /**&lt;No document in protocol.&gt;*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ShorthandEntry implements CommonDomainType {
        /**Shorthand name.*/
        private String name;
        /**Shorthand value.*/
        private String value;
        /**Whether the property has "!important" annotation (implies `false` if absent).
        <em>Optional.</em>*/
        private Boolean important;
        public final ShorthandEntry name(String name) { this.name = name; return this; }
        public final ShorthandEntry setName(String name) { return name(name); }
        public final String name() { return name; }
        public final String getName() { return name(); }
        public final ShorthandEntry value(String value) { this.value = value; return this; }
        public final ShorthandEntry setValue(String value) { return value(value); }
        public final String value() { return value; }
        public final String getValue() { return value(); }
        public final ShorthandEntry important(@Nullable Boolean important) { this.important = important; return this; }
        public final ShorthandEntry optImportant(@Nullable Boolean important) { return important(important); }
        public final Boolean important() { return important; }
        public final Boolean getImportant() { return important(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (name == null) throw new IllegalArgumentException("CSS.ShorthandEntry.name is necessary field.");
            if (value == null) throw new IllegalArgumentException("CSS.ShorthandEntry.value is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"name\":").append('"').append(DomainBase.escapeQuote(name)).append('"');
            strBuilder.append(",\"value\":").append('"').append(DomainBase.escapeQuote(value)).append('"');
            if (important != null) strBuilder.append(",\"important\":").append(important);
            strBuilder.append('}');
            return strBuilder;
        }
        public ShorthandEntry() {}
        public ShorthandEntry(
            @JsonProperty("name")String name,
            @JsonProperty("value")String value,
            @Nullable @JsonProperty("important")Boolean important
        ) {
            this.name = name;
            this.value = value;
            this.important = important;
        }
    }

    /**&lt;No document in protocol.&gt;*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class CSSComputedStyleProperty implements CommonDomainType {
        /**Computed style property name.*/
        private String name;
        /**Computed style property value.*/
        private String value;
        public final CSSComputedStyleProperty name(String name) { this.name = name; return this; }
        public final CSSComputedStyleProperty setName(String name) { return name(name); }
        public final String name() { return name; }
        public final String getName() { return name(); }
        public final CSSComputedStyleProperty value(String value) { this.value = value; return this; }
        public final CSSComputedStyleProperty setValue(String value) { return value(value); }
        public final String value() { return value; }
        public final String getValue() { return value(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (name == null) throw new IllegalArgumentException("CSS.CSSComputedStyleProperty.name is necessary field.");
            if (value == null) throw new IllegalArgumentException("CSS.CSSComputedStyleProperty.value is necessary field.");
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
        public CSSComputedStyleProperty() {}
        public CSSComputedStyleProperty(
            @JsonProperty("name")String name,
            @JsonProperty("value")String value
        ) {
            this.name = name;
            this.value = value;
        }
    }

    /**CSS style representation.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class CSSStyle implements CommonDomainType {
        /**The css style sheet identifier (absent for user agent stylesheet and user-specified
stylesheet rules) this rule came from.
        <em>Optional.</em>*/
        private StyleSheetId styleSheetId;
        /**CSS properties in the style.*/
        private List<CSSProperty> cssProperties;
        /**Computed values for all shorthands found in the style.*/
        private List<ShorthandEntry> shorthandEntries;
        /**Style declaration text (if available).
        <em>Optional.</em>*/
        private String cssText;
        /**Style declaration range in the enclosing stylesheet (if available).
        <em>Optional.</em>*/
        private SourceRange range;
        public final CSSStyle styleSheetId(@Nullable StyleSheetId styleSheetId) { this.styleSheetId = styleSheetId; return this; }
        public final CSSStyle optStyleSheetId(@Nullable StyleSheetId styleSheetId) { return styleSheetId(styleSheetId); }
        public final StyleSheetId styleSheetId() { return styleSheetId; }
        public final StyleSheetId getStyleSheetId() { return styleSheetId(); }
        public final CSSStyle cssProperties(List<CSSProperty> cssProperties) { this.cssProperties = cssProperties; return this; }
        public final CSSStyle setCssProperties(List<CSSProperty> cssProperties) { return cssProperties(cssProperties); }
        public final List<CSSProperty> cssProperties() { return cssProperties; }
        public final List<CSSProperty> getCssProperties() { return cssProperties(); }
        public final CSSStyle shorthandEntries(List<ShorthandEntry> shorthandEntries) { this.shorthandEntries = shorthandEntries; return this; }
        public final CSSStyle setShorthandEntries(List<ShorthandEntry> shorthandEntries) { return shorthandEntries(shorthandEntries); }
        public final List<ShorthandEntry> shorthandEntries() { return shorthandEntries; }
        public final List<ShorthandEntry> getShorthandEntries() { return shorthandEntries(); }
        public final CSSStyle cssText(@Nullable String cssText) { this.cssText = cssText; return this; }
        public final CSSStyle optCssText(@Nullable String cssText) { return cssText(cssText); }
        public final String cssText() { return cssText; }
        public final String getCssText() { return cssText(); }
        public final CSSStyle range(@Nullable SourceRange range) { this.range = range; return this; }
        public final CSSStyle optRange(@Nullable SourceRange range) { return range(range); }
        public final SourceRange range() { return range; }
        public final SourceRange getRange() { return range(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (cssProperties == null) throw new IllegalArgumentException("CSS.CSSStyle.cssProperties is necessary field.");
            if (shorthandEntries == null) throw new IllegalArgumentException("CSS.CSSStyle.shorthandEntries is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            if (styleSheetId != null) styleSheetId.toJson(strBuilder.append("\"styleSheetId\":"));
                        strBuilder.append(",\"cssProperties\":[");
            cssProperties.get(0).toJson(strBuilder);
            for (int i = 1; i < cssProperties.size(); ++i)
                cssProperties.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
                        strBuilder.append(",\"shorthandEntries\":[");
            shorthandEntries.get(0).toJson(strBuilder);
            for (int i = 1; i < shorthandEntries.size(); ++i)
                shorthandEntries.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            if (cssText != null) strBuilder.append(",\"cssText\":").append('"').append(DomainBase.escapeQuote(cssText)).append('"');
            if (range != null) range.toJson(strBuilder.append(",\"range\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public CSSStyle() {}
        public CSSStyle(
            @Nullable @JsonProperty("styleSheetId")StyleSheetId styleSheetId,
            @JsonProperty("cssProperties")List<CSSProperty> cssProperties,
            @JsonProperty("shorthandEntries")List<ShorthandEntry> shorthandEntries,
            @Nullable @JsonProperty("cssText")String cssText,
            @Nullable @JsonProperty("range")SourceRange range
        ) {
            this.styleSheetId = styleSheetId;
            this.cssProperties = cssProperties;
            this.shorthandEntries = shorthandEntries;
            this.cssText = cssText;
            this.range = range;
        }
    }

    /**CSS property declaration data.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class CSSProperty implements CommonDomainType {
        /**The property name.*/
        private String name;
        /**The property value.*/
        private String value;
        /**Whether the property has "!important" annotation (implies `false` if absent).
        <em>Optional.</em>*/
        private Boolean important;
        /**Whether the property is implicit (implies `false` if absent).
        <em>Optional.</em>*/
        private Boolean implicit;
        /**The full property text as specified in the style.
        <em>Optional.</em>*/
        private String text;
        /**Whether the property is understood by the browser (implies `true` if absent).
        <em>Optional.</em>*/
        private Boolean parsedOk;
        /**Whether the property is disabled by the user (present for source-based properties only).
        <em>Optional.</em>*/
        private Boolean disabled;
        /**The entire property range in the enclosing style declaration (if available).
        <em>Optional.</em>*/
        private SourceRange range;
        public final CSSProperty name(String name) { this.name = name; return this; }
        public final CSSProperty setName(String name) { return name(name); }
        public final String name() { return name; }
        public final String getName() { return name(); }
        public final CSSProperty value(String value) { this.value = value; return this; }
        public final CSSProperty setValue(String value) { return value(value); }
        public final String value() { return value; }
        public final String getValue() { return value(); }
        public final CSSProperty important(@Nullable Boolean important) { this.important = important; return this; }
        public final CSSProperty optImportant(@Nullable Boolean important) { return important(important); }
        public final Boolean important() { return important; }
        public final Boolean getImportant() { return important(); }
        public final CSSProperty implicit(@Nullable Boolean implicit) { this.implicit = implicit; return this; }
        public final CSSProperty optImplicit(@Nullable Boolean implicit) { return implicit(implicit); }
        public final Boolean implicit() { return implicit; }
        public final Boolean getImplicit() { return implicit(); }
        public final CSSProperty text(@Nullable String text) { this.text = text; return this; }
        public final CSSProperty optText(@Nullable String text) { return text(text); }
        public final String text() { return text; }
        public final String getText() { return text(); }
        public final CSSProperty parsedOk(@Nullable Boolean parsedOk) { this.parsedOk = parsedOk; return this; }
        public final CSSProperty optParsedOk(@Nullable Boolean parsedOk) { return parsedOk(parsedOk); }
        public final Boolean parsedOk() { return parsedOk; }
        public final Boolean getParsedOk() { return parsedOk(); }
        public final CSSProperty disabled(@Nullable Boolean disabled) { this.disabled = disabled; return this; }
        public final CSSProperty optDisabled(@Nullable Boolean disabled) { return disabled(disabled); }
        public final Boolean disabled() { return disabled; }
        public final Boolean getDisabled() { return disabled(); }
        public final CSSProperty range(@Nullable SourceRange range) { this.range = range; return this; }
        public final CSSProperty optRange(@Nullable SourceRange range) { return range(range); }
        public final SourceRange range() { return range; }
        public final SourceRange getRange() { return range(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (name == null) throw new IllegalArgumentException("CSS.CSSProperty.name is necessary field.");
            if (value == null) throw new IllegalArgumentException("CSS.CSSProperty.value is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"name\":").append('"').append(DomainBase.escapeQuote(name)).append('"');
            strBuilder.append(",\"value\":").append('"').append(DomainBase.escapeQuote(value)).append('"');
            if (important != null) strBuilder.append(",\"important\":").append(important);
            if (implicit != null) strBuilder.append(",\"implicit\":").append(implicit);
            if (text != null) strBuilder.append(",\"text\":").append('"').append(DomainBase.escapeQuote(text)).append('"');
            if (parsedOk != null) strBuilder.append(",\"parsedOk\":").append(parsedOk);
            if (disabled != null) strBuilder.append(",\"disabled\":").append(disabled);
            if (range != null) range.toJson(strBuilder.append(",\"range\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public CSSProperty() {}
        public CSSProperty(
            @JsonProperty("name")String name,
            @JsonProperty("value")String value,
            @Nullable @JsonProperty("important")Boolean important,
            @Nullable @JsonProperty("implicit")Boolean implicit,
            @Nullable @JsonProperty("text")String text,
            @Nullable @JsonProperty("parsedOk")Boolean parsedOk,
            @Nullable @JsonProperty("disabled")Boolean disabled,
            @Nullable @JsonProperty("range")SourceRange range
        ) {
            this.name = name;
            this.value = value;
            this.important = important;
            this.implicit = implicit;
            this.text = text;
            this.parsedOk = parsedOk;
            this.disabled = disabled;
            this.range = range;
        }
    }

    /**CSS media rule descriptor.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class CSSMedia implements CommonDomainType {
        /**Media query text.*/
        private String text;
        /**Source of the media query: "mediaRule" if specified by a @media rule, "importRule" if
specified by an @import rule, "linkedSheet" if specified by a "media" attribute in a linked
stylesheet's LINK tag, "inlineSheet" if specified by a "media" attribute in an inline
stylesheet's STYLE tag.*/
        @ParametersAreNonnullByDefault public enum Source implements CommonDomainType {
            MediaRule("mediaRule"),
            ImportRule("importRule"),
            LinkedSheet("linkedSheet"),
            InlineSheet("inlineSheet");

            private final String _value;
            /**Convert string representation to type.
             @throws IllegalArgumentException if given value cannot convert to enum type. */
            @JsonCreator public static Source of(String value) {
                return Enum.valueOf(Source.class, value.substring(0, 1).toUpperCase() + value.substring(1));
            }
            Source(String value) { _value = value; }
            /**Check if parameter fields of method are all valid. */
            @Override public void check() throws IllegalArgumentException { /* Need not check */ }
            /**Convert method parameter object into json string and append into string builder.
             @return string builder instance that is given in parameter (for chaining coding style use.) */
            @Override public StringBuilder toJson(StringBuilder strBuilder) { return strBuilder.append('"').append(_value).append('"'); }
            @Override public String toString() { return "\"" + _value + "\""; }
        }
        private Source source;
        /**URL of the document containing the media query description.
        <em>Optional.</em>*/
        private String sourceURL;
        /**The associated rule (@media or @import) header range in the enclosing stylesheet (if
available).
        <em>Optional.</em>*/
        private SourceRange range;
        /**Identifier of the stylesheet containing this object (if exists).
        <em>Optional.</em>*/
        private StyleSheetId styleSheetId;
        /**Array of media queries.
        <em>Optional.</em>*/
        private List<MediaQuery> mediaList;
        public final CSSMedia text(String text) { this.text = text; return this; }
        public final CSSMedia setText(String text) { return text(text); }
        public final String text() { return text; }
        public final String getText() { return text(); }
        public final CSSMedia source(Source source) { this.source = source; return this; }
        public final CSSMedia setSource(Source source) { return source(source); }
        public final Source source() { return source; }
        public final Source getSource() { return source(); }
        public final CSSMedia sourceURL(@Nullable String sourceURL) { this.sourceURL = sourceURL; return this; }
        public final CSSMedia optSourceURL(@Nullable String sourceURL) { return sourceURL(sourceURL); }
        public final String sourceURL() { return sourceURL; }
        public final String getSourceURL() { return sourceURL(); }
        public final CSSMedia range(@Nullable SourceRange range) { this.range = range; return this; }
        public final CSSMedia optRange(@Nullable SourceRange range) { return range(range); }
        public final SourceRange range() { return range; }
        public final SourceRange getRange() { return range(); }
        public final CSSMedia styleSheetId(@Nullable StyleSheetId styleSheetId) { this.styleSheetId = styleSheetId; return this; }
        public final CSSMedia optStyleSheetId(@Nullable StyleSheetId styleSheetId) { return styleSheetId(styleSheetId); }
        public final StyleSheetId styleSheetId() { return styleSheetId; }
        public final StyleSheetId getStyleSheetId() { return styleSheetId(); }
        public final CSSMedia mediaList(@Nullable List<MediaQuery> mediaList) { this.mediaList = mediaList; return this; }
        public final CSSMedia optMediaList(@Nullable List<MediaQuery> mediaList) { return mediaList(mediaList); }
        public final List<MediaQuery> mediaList() { return mediaList; }
        public final List<MediaQuery> getMediaList() { return mediaList(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (text == null) throw new IllegalArgumentException("CSS.CSSMedia.text is necessary field.");
            if (source == null) throw new IllegalArgumentException("CSS.CSSMedia.source is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"text\":").append('"').append(DomainBase.escapeQuote(text)).append('"');
            strBuilder.append(",\"source\":").append(source);
            if (sourceURL != null) strBuilder.append(",\"sourceURL\":").append('"').append(DomainBase.escapeQuote(sourceURL)).append('"');
            if (range != null) range.toJson(strBuilder.append(",\"range\":"));
            if (styleSheetId != null) styleSheetId.toJson(strBuilder.append(",\"styleSheetId\":"));
            if (mediaList != null) {
                strBuilder.append(",\"mediaList\":[");
                mediaList.get(0).toJson(strBuilder);
                for (int i = 1; i < mediaList.size(); ++i)
                    mediaList.get(i).toJson(strBuilder.append(','));
                strBuilder.append(']');
            }
            strBuilder.append('}');
            return strBuilder;
        }
        public CSSMedia() {}
        public CSSMedia(
            @JsonProperty("text")String text,
            @JsonProperty("source")Source source,
            @Nullable @JsonProperty("sourceURL")String sourceURL,
            @Nullable @JsonProperty("range")SourceRange range,
            @Nullable @JsonProperty("styleSheetId")StyleSheetId styleSheetId,
            @Nullable @JsonProperty("mediaList")List<MediaQuery> mediaList
        ) {
            this.text = text;
            this.source = source;
            this.sourceURL = sourceURL;
            this.range = range;
            this.styleSheetId = styleSheetId;
            this.mediaList = mediaList;
        }
    }

    /**Media query descriptor.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class MediaQuery implements CommonDomainType {
        /**Array of media query expressions.*/
        private List<MediaQueryExpression> expressions;
        /**Whether the media query condition is satisfied.*/
        private Boolean active;
        public final MediaQuery expressions(List<MediaQueryExpression> expressions) { this.expressions = expressions; return this; }
        public final MediaQuery setExpressions(List<MediaQueryExpression> expressions) { return expressions(expressions); }
        public final List<MediaQueryExpression> expressions() { return expressions; }
        public final List<MediaQueryExpression> getExpressions() { return expressions(); }
        public final MediaQuery active(Boolean active) { this.active = active; return this; }
        public final MediaQuery setActive(Boolean active) { return active(active); }
        public final Boolean active() { return active; }
        public final Boolean getActive() { return active(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (expressions == null) throw new IllegalArgumentException("CSS.MediaQuery.expressions is necessary field.");
            if (active == null) throw new IllegalArgumentException("CSS.MediaQuery.active is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
                        strBuilder.append("\"expressions\":[");
            expressions.get(0).toJson(strBuilder);
            for (int i = 1; i < expressions.size(); ++i)
                expressions.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            strBuilder.append(",\"active\":").append(active);
            strBuilder.append('}');
            return strBuilder;
        }
        public MediaQuery() {}
        public MediaQuery(
            @JsonProperty("expressions")List<MediaQueryExpression> expressions,
            @JsonProperty("active")Boolean active
        ) {
            this.expressions = expressions;
            this.active = active;
        }
    }

    /**Media query expression descriptor.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class MediaQueryExpression implements CommonDomainType {
        /**Media query expression value.*/
        private Double value;
        /**Media query expression units.*/
        private String unit;
        /**Media query expression feature.*/
        private String feature;
        /**The associated range of the value text in the enclosing stylesheet (if available).
        <em>Optional.</em>*/
        private SourceRange valueRange;
        /**Computed length of media query expression (if applicable).
        <em>Optional.</em>*/
        private Double computedLength;
        public final MediaQueryExpression value(Double value) { this.value = value; return this; }
        public final MediaQueryExpression setValue(Double value) { return value(value); }
        public final Double value() { return value; }
        public final Double getValue() { return value(); }
        public final MediaQueryExpression unit(String unit) { this.unit = unit; return this; }
        public final MediaQueryExpression setUnit(String unit) { return unit(unit); }
        public final String unit() { return unit; }
        public final String getUnit() { return unit(); }
        public final MediaQueryExpression feature(String feature) { this.feature = feature; return this; }
        public final MediaQueryExpression setFeature(String feature) { return feature(feature); }
        public final String feature() { return feature; }
        public final String getFeature() { return feature(); }
        public final MediaQueryExpression valueRange(@Nullable SourceRange valueRange) { this.valueRange = valueRange; return this; }
        public final MediaQueryExpression optValueRange(@Nullable SourceRange valueRange) { return valueRange(valueRange); }
        public final SourceRange valueRange() { return valueRange; }
        public final SourceRange getValueRange() { return valueRange(); }
        public final MediaQueryExpression computedLength(@Nullable Double computedLength) { this.computedLength = computedLength; return this; }
        public final MediaQueryExpression optComputedLength(@Nullable Double computedLength) { return computedLength(computedLength); }
        public final Double computedLength() { return computedLength; }
        public final Double getComputedLength() { return computedLength(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (value == null) throw new IllegalArgumentException("CSS.MediaQueryExpression.value is necessary field.");
            if (unit == null) throw new IllegalArgumentException("CSS.MediaQueryExpression.unit is necessary field.");
            if (feature == null) throw new IllegalArgumentException("CSS.MediaQueryExpression.feature is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"value\":").append(value);
            strBuilder.append(",\"unit\":").append('"').append(DomainBase.escapeQuote(unit)).append('"');
            strBuilder.append(",\"feature\":").append('"').append(DomainBase.escapeQuote(feature)).append('"');
            if (valueRange != null) valueRange.toJson(strBuilder.append(",\"valueRange\":"));
            if (computedLength != null) strBuilder.append(",\"computedLength\":").append(computedLength);
            strBuilder.append('}');
            return strBuilder;
        }
        public MediaQueryExpression() {}
        public MediaQueryExpression(
            @JsonProperty("value")Double value,
            @JsonProperty("unit")String unit,
            @JsonProperty("feature")String feature,
            @Nullable @JsonProperty("valueRange")SourceRange valueRange,
            @Nullable @JsonProperty("computedLength")Double computedLength
        ) {
            this.value = value;
            this.unit = unit;
            this.feature = feature;
            this.valueRange = valueRange;
            this.computedLength = computedLength;
        }
    }

    /**Information about amount of glyphs that were rendered with given font.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class PlatformFontUsage implements CommonDomainType {
        /**Font's family name reported by platform.*/
        private String familyName;
        /**Indicates if the font was downloaded or resolved locally.*/
        private Boolean isCustomFont;
        /**Amount of glyphs that were rendered with this font.*/
        private Double glyphCount;
        public final PlatformFontUsage familyName(String familyName) { this.familyName = familyName; return this; }
        public final PlatformFontUsage setFamilyName(String familyName) { return familyName(familyName); }
        public final String familyName() { return familyName; }
        public final String getFamilyName() { return familyName(); }
        public final PlatformFontUsage isCustomFont(Boolean isCustomFont) { this.isCustomFont = isCustomFont; return this; }
        public final PlatformFontUsage setIsCustomFont(Boolean isCustomFont) { return isCustomFont(isCustomFont); }
        public final Boolean isCustomFont() { return isCustomFont; }
        public final Boolean getIsCustomFont() { return isCustomFont(); }
        public final PlatformFontUsage glyphCount(Double glyphCount) { this.glyphCount = glyphCount; return this; }
        public final PlatformFontUsage setGlyphCount(Double glyphCount) { return glyphCount(glyphCount); }
        public final Double glyphCount() { return glyphCount; }
        public final Double getGlyphCount() { return glyphCount(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (familyName == null) throw new IllegalArgumentException("CSS.PlatformFontUsage.familyName is necessary field.");
            if (isCustomFont == null) throw new IllegalArgumentException("CSS.PlatformFontUsage.isCustomFont is necessary field.");
            if (glyphCount == null) throw new IllegalArgumentException("CSS.PlatformFontUsage.glyphCount is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"familyName\":").append('"').append(DomainBase.escapeQuote(familyName)).append('"');
            strBuilder.append(",\"isCustomFont\":").append(isCustomFont);
            strBuilder.append(",\"glyphCount\":").append(glyphCount);
            strBuilder.append('}');
            return strBuilder;
        }
        public PlatformFontUsage() {}
        public PlatformFontUsage(
            @JsonProperty("familyName")String familyName,
            @JsonProperty("isCustomFont")Boolean isCustomFont,
            @JsonProperty("glyphCount")Double glyphCount
        ) {
            this.familyName = familyName;
            this.isCustomFont = isCustomFont;
            this.glyphCount = glyphCount;
        }
    }

    /**Properties of a web font: https://www.w3.org/TR/2008/REC-CSS2-20080411/fonts.html#font-descriptions*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class FontFace implements CommonDomainType {
        /**The font-family.*/
        private String fontFamily;
        /**The font-style.*/
        private String fontStyle;
        /**The font-variant.*/
        private String fontVariant;
        /**The font-weight.*/
        private String fontWeight;
        /**The font-stretch.*/
        private String fontStretch;
        /**The unicode-range.*/
        private String unicodeRange;
        /**The src.*/
        private String src;
        /**The resolved platform font family*/
        private String platformFontFamily;
        public final FontFace fontFamily(String fontFamily) { this.fontFamily = fontFamily; return this; }
        public final FontFace setFontFamily(String fontFamily) { return fontFamily(fontFamily); }
        public final String fontFamily() { return fontFamily; }
        public final String getFontFamily() { return fontFamily(); }
        public final FontFace fontStyle(String fontStyle) { this.fontStyle = fontStyle; return this; }
        public final FontFace setFontStyle(String fontStyle) { return fontStyle(fontStyle); }
        public final String fontStyle() { return fontStyle; }
        public final String getFontStyle() { return fontStyle(); }
        public final FontFace fontVariant(String fontVariant) { this.fontVariant = fontVariant; return this; }
        public final FontFace setFontVariant(String fontVariant) { return fontVariant(fontVariant); }
        public final String fontVariant() { return fontVariant; }
        public final String getFontVariant() { return fontVariant(); }
        public final FontFace fontWeight(String fontWeight) { this.fontWeight = fontWeight; return this; }
        public final FontFace setFontWeight(String fontWeight) { return fontWeight(fontWeight); }
        public final String fontWeight() { return fontWeight; }
        public final String getFontWeight() { return fontWeight(); }
        public final FontFace fontStretch(String fontStretch) { this.fontStretch = fontStretch; return this; }
        public final FontFace setFontStretch(String fontStretch) { return fontStretch(fontStretch); }
        public final String fontStretch() { return fontStretch; }
        public final String getFontStretch() { return fontStretch(); }
        public final FontFace unicodeRange(String unicodeRange) { this.unicodeRange = unicodeRange; return this; }
        public final FontFace setUnicodeRange(String unicodeRange) { return unicodeRange(unicodeRange); }
        public final String unicodeRange() { return unicodeRange; }
        public final String getUnicodeRange() { return unicodeRange(); }
        public final FontFace src(String src) { this.src = src; return this; }
        public final FontFace setSrc(String src) { return src(src); }
        public final String src() { return src; }
        public final String getSrc() { return src(); }
        public final FontFace platformFontFamily(String platformFontFamily) { this.platformFontFamily = platformFontFamily; return this; }
        public final FontFace setPlatformFontFamily(String platformFontFamily) { return platformFontFamily(platformFontFamily); }
        public final String platformFontFamily() { return platformFontFamily; }
        public final String getPlatformFontFamily() { return platformFontFamily(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (fontFamily == null) throw new IllegalArgumentException("CSS.FontFace.fontFamily is necessary field.");
            if (fontStyle == null) throw new IllegalArgumentException("CSS.FontFace.fontStyle is necessary field.");
            if (fontVariant == null) throw new IllegalArgumentException("CSS.FontFace.fontVariant is necessary field.");
            if (fontWeight == null) throw new IllegalArgumentException("CSS.FontFace.fontWeight is necessary field.");
            if (fontStretch == null) throw new IllegalArgumentException("CSS.FontFace.fontStretch is necessary field.");
            if (unicodeRange == null) throw new IllegalArgumentException("CSS.FontFace.unicodeRange is necessary field.");
            if (src == null) throw new IllegalArgumentException("CSS.FontFace.src is necessary field.");
            if (platformFontFamily == null) throw new IllegalArgumentException("CSS.FontFace.platformFontFamily is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"fontFamily\":").append('"').append(DomainBase.escapeQuote(fontFamily)).append('"');
            strBuilder.append(",\"fontStyle\":").append('"').append(DomainBase.escapeQuote(fontStyle)).append('"');
            strBuilder.append(",\"fontVariant\":").append('"').append(DomainBase.escapeQuote(fontVariant)).append('"');
            strBuilder.append(",\"fontWeight\":").append('"').append(DomainBase.escapeQuote(fontWeight)).append('"');
            strBuilder.append(",\"fontStretch\":").append('"').append(DomainBase.escapeQuote(fontStretch)).append('"');
            strBuilder.append(",\"unicodeRange\":").append('"').append(DomainBase.escapeQuote(unicodeRange)).append('"');
            strBuilder.append(",\"src\":").append('"').append(DomainBase.escapeQuote(src)).append('"');
            strBuilder.append(",\"platformFontFamily\":").append('"').append(DomainBase.escapeQuote(platformFontFamily)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public FontFace() {}
        public FontFace(
            @JsonProperty("fontFamily")String fontFamily,
            @JsonProperty("fontStyle")String fontStyle,
            @JsonProperty("fontVariant")String fontVariant,
            @JsonProperty("fontWeight")String fontWeight,
            @JsonProperty("fontStretch")String fontStretch,
            @JsonProperty("unicodeRange")String unicodeRange,
            @JsonProperty("src")String src,
            @JsonProperty("platformFontFamily")String platformFontFamily
        ) {
            this.fontFamily = fontFamily;
            this.fontStyle = fontStyle;
            this.fontVariant = fontVariant;
            this.fontWeight = fontWeight;
            this.fontStretch = fontStretch;
            this.unicodeRange = unicodeRange;
            this.src = src;
            this.platformFontFamily = platformFontFamily;
        }
    }

    /**CSS keyframes rule representation.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class CSSKeyframesRule implements CommonDomainType {
        /**Animation name.*/
        private Value animationName;
        /**List of keyframes.*/
        private List<CSSKeyframeRule> keyframes;
        public final CSSKeyframesRule animationName(Value animationName) { this.animationName = animationName; return this; }
        public final CSSKeyframesRule setAnimationName(Value animationName) { return animationName(animationName); }
        public final Value animationName() { return animationName; }
        public final Value getAnimationName() { return animationName(); }
        public final CSSKeyframesRule keyframes(List<CSSKeyframeRule> keyframes) { this.keyframes = keyframes; return this; }
        public final CSSKeyframesRule setKeyframes(List<CSSKeyframeRule> keyframes) { return keyframes(keyframes); }
        public final List<CSSKeyframeRule> keyframes() { return keyframes; }
        public final List<CSSKeyframeRule> getKeyframes() { return keyframes(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (animationName == null) throw new IllegalArgumentException("CSS.CSSKeyframesRule.animationName is necessary field.");
            if (keyframes == null) throw new IllegalArgumentException("CSS.CSSKeyframesRule.keyframes is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            animationName.toJson(strBuilder.append("\"animationName\":"));
                        strBuilder.append(",\"keyframes\":[");
            keyframes.get(0).toJson(strBuilder);
            for (int i = 1; i < keyframes.size(); ++i)
                keyframes.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public CSSKeyframesRule() {}
        public CSSKeyframesRule(
            @JsonProperty("animationName")Value animationName,
            @JsonProperty("keyframes")List<CSSKeyframeRule> keyframes
        ) {
            this.animationName = animationName;
            this.keyframes = keyframes;
        }
    }

    /**CSS keyframe rule representation.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class CSSKeyframeRule implements CommonDomainType {
        /**The css style sheet identifier (absent for user agent stylesheet and user-specified
stylesheet rules) this rule came from.
        <em>Optional.</em>*/
        private StyleSheetId styleSheetId;
        /**Parent stylesheet's origin.*/
        private StyleSheetOrigin origin;
        /**Associated key text.*/
        private Value keyText;
        /**Associated style declaration.*/
        private CSSStyle style;
        public final CSSKeyframeRule styleSheetId(@Nullable StyleSheetId styleSheetId) { this.styleSheetId = styleSheetId; return this; }
        public final CSSKeyframeRule optStyleSheetId(@Nullable StyleSheetId styleSheetId) { return styleSheetId(styleSheetId); }
        public final StyleSheetId styleSheetId() { return styleSheetId; }
        public final StyleSheetId getStyleSheetId() { return styleSheetId(); }
        public final CSSKeyframeRule origin(StyleSheetOrigin origin) { this.origin = origin; return this; }
        public final CSSKeyframeRule setOrigin(StyleSheetOrigin origin) { return origin(origin); }
        public final StyleSheetOrigin origin() { return origin; }
        public final StyleSheetOrigin getOrigin() { return origin(); }
        public final CSSKeyframeRule keyText(Value keyText) { this.keyText = keyText; return this; }
        public final CSSKeyframeRule setKeyText(Value keyText) { return keyText(keyText); }
        public final Value keyText() { return keyText; }
        public final Value getKeyText() { return keyText(); }
        public final CSSKeyframeRule style(CSSStyle style) { this.style = style; return this; }
        public final CSSKeyframeRule setStyle(CSSStyle style) { return style(style); }
        public final CSSStyle style() { return style; }
        public final CSSStyle getStyle() { return style(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (origin == null) throw new IllegalArgumentException("CSS.CSSKeyframeRule.origin is necessary field.");
            if (keyText == null) throw new IllegalArgumentException("CSS.CSSKeyframeRule.keyText is necessary field.");
            if (style == null) throw new IllegalArgumentException("CSS.CSSKeyframeRule.style is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            if (styleSheetId != null) styleSheetId.toJson(strBuilder.append("\"styleSheetId\":"));
            origin.toJson(strBuilder.append(",\"origin\":"));
            keyText.toJson(strBuilder.append(",\"keyText\":"));
            style.toJson(strBuilder.append(",\"style\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public CSSKeyframeRule() {}
        public CSSKeyframeRule(
            @Nullable @JsonProperty("styleSheetId")StyleSheetId styleSheetId,
            @JsonProperty("origin")StyleSheetOrigin origin,
            @JsonProperty("keyText")Value keyText,
            @JsonProperty("style")CSSStyle style
        ) {
            this.styleSheetId = styleSheetId;
            this.origin = origin;
            this.keyText = keyText;
            this.style = style;
        }
    }

    /**A descriptor of operation to mutate style declaration text.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class StyleDeclarationEdit implements CommonDomainType {
        /**The css style sheet identifier.*/
        private StyleSheetId styleSheetId;
        /**The range of the style text in the enclosing stylesheet.*/
        private SourceRange range;
        /**New style text.*/
        private String text;
        public final StyleDeclarationEdit styleSheetId(StyleSheetId styleSheetId) { this.styleSheetId = styleSheetId; return this; }
        public final StyleDeclarationEdit setStyleSheetId(StyleSheetId styleSheetId) { return styleSheetId(styleSheetId); }
        public final StyleSheetId styleSheetId() { return styleSheetId; }
        public final StyleSheetId getStyleSheetId() { return styleSheetId(); }
        public final StyleDeclarationEdit range(SourceRange range) { this.range = range; return this; }
        public final StyleDeclarationEdit setRange(SourceRange range) { return range(range); }
        public final SourceRange range() { return range; }
        public final SourceRange getRange() { return range(); }
        public final StyleDeclarationEdit text(String text) { this.text = text; return this; }
        public final StyleDeclarationEdit setText(String text) { return text(text); }
        public final String text() { return text; }
        public final String getText() { return text(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (styleSheetId == null) throw new IllegalArgumentException("CSS.StyleDeclarationEdit.styleSheetId is necessary field.");
            if (range == null) throw new IllegalArgumentException("CSS.StyleDeclarationEdit.range is necessary field.");
            if (text == null) throw new IllegalArgumentException("CSS.StyleDeclarationEdit.text is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            styleSheetId.toJson(strBuilder.append("\"styleSheetId\":"));
            range.toJson(strBuilder.append(",\"range\":"));
            strBuilder.append(",\"text\":").append('"').append(DomainBase.escapeQuote(text)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public StyleDeclarationEdit() {}
        public StyleDeclarationEdit(
            @JsonProperty("styleSheetId")StyleSheetId styleSheetId,
            @JsonProperty("range")SourceRange range,
            @JsonProperty("text")String text
        ) {
            this.styleSheetId = styleSheetId;
            this.range = range;
            this.text = text;
        }
    }
    /**Inserts a new rule with the given `ruleText` in a stylesheet with given `styleSheetId`, at the
position specified by `location`.*/
    public AddRuleParameter addRule() { final AddRuleParameter v = new AddRuleParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of addRule.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class AddRuleParameter extends CommandBase {
        /**The css style sheet identifier where a new rule should be inserted.*/
        private StyleSheetId styleSheetId;
        /**The text of a new rule.*/
        private String ruleText;
        /**Text position of a new rule in the target style sheet.*/
        private SourceRange location;
        public final AddRuleParameter styleSheetId(StyleSheetId styleSheetId) { this.styleSheetId = styleSheetId; return this; }
        public final AddRuleParameter setStyleSheetId(StyleSheetId styleSheetId) { return styleSheetId(styleSheetId); }
        public final StyleSheetId styleSheetId() { return styleSheetId; }
        public final StyleSheetId getStyleSheetId() { return styleSheetId(); }
        public final AddRuleParameter ruleText(String ruleText) { this.ruleText = ruleText; return this; }
        public final AddRuleParameter setRuleText(String ruleText) { return ruleText(ruleText); }
        public final String ruleText() { return ruleText; }
        public final String getRuleText() { return ruleText(); }
        public final AddRuleParameter location(SourceRange location) { this.location = location; return this; }
        public final AddRuleParameter setLocation(SourceRange location) { return location(location); }
        public final SourceRange location() { return location; }
        public final SourceRange getLocation() { return location(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (styleSheetId == null) throw new IllegalArgumentException("CSS.AddRuleParameter.styleSheetId is necessary field.");
            if (ruleText == null) throw new IllegalArgumentException("CSS.AddRuleParameter.ruleText is necessary field.");
            if (location == null) throw new IllegalArgumentException("CSS.AddRuleParameter.location is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            styleSheetId.toJson(strBuilder.append("\"styleSheetId\":"));
            strBuilder.append(",\"ruleText\":").append('"').append(DomainBase.escapeQuote(ruleText)).append('"');
            location.toJson(strBuilder.append(",\"location\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public AddRuleParameter() {}
        public AddRuleParameter(
            @JsonProperty("styleSheetId")StyleSheetId styleSheetId,
            @JsonProperty("ruleText")String ruleText,
            @JsonProperty("location")SourceRange location
        ) {
            this();
            this.styleSheetId = styleSheetId;
            this.ruleText = ruleText;
            this.location = location;
        }
        public CompletableFuture<AddRuleResult> call() {
            return super.call("CSS.addRule", AddRuleResult.class,
                (code, msg)->new AddRuleResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<AddRuleResult> call(Executor exec) {
            return super.call("CSS.addRule", AddRuleResult.class,
                (code, msg)->new AddRuleResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of addRule.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class AddRuleResult extends ResultBase {
        /**The newly created rule.*/
        private final CSSRule rule;
        public final CSSRule rule() { return rule; }
        public final CSSRule getRule() { return rule(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            rule.toJson(strBuilder.append("\"rule\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public AddRuleResult(
            @JsonProperty("rule")CSSRule rule
        ) {
            this.rule = rule;
        }
        public AddRuleResult(ResultBase.FailedResult e) {
            super(e);
            rule = null;
        }
    }
    /**Returns all class names from specified stylesheet.*/
    public CollectClassNamesParameter collectClassNames() { final CollectClassNamesParameter v = new CollectClassNamesParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of collectClassNames.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class CollectClassNamesParameter extends CommandBase {
        /**&lt;No document in protocol.&gt;*/
        private StyleSheetId styleSheetId;
        public final CollectClassNamesParameter styleSheetId(StyleSheetId styleSheetId) { this.styleSheetId = styleSheetId; return this; }
        public final CollectClassNamesParameter setStyleSheetId(StyleSheetId styleSheetId) { return styleSheetId(styleSheetId); }
        public final StyleSheetId styleSheetId() { return styleSheetId; }
        public final StyleSheetId getStyleSheetId() { return styleSheetId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (styleSheetId == null) throw new IllegalArgumentException("CSS.CollectClassNamesParameter.styleSheetId is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            styleSheetId.toJson(strBuilder.append("\"styleSheetId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public CollectClassNamesParameter() {}
        public CollectClassNamesParameter(
            @JsonProperty("styleSheetId")StyleSheetId styleSheetId
        ) {
            this();
            this.styleSheetId = styleSheetId;
        }
        public CompletableFuture<CollectClassNamesResult> call() {
            return super.call("CSS.collectClassNames", CollectClassNamesResult.class,
                (code, msg)->new CollectClassNamesResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<CollectClassNamesResult> call(Executor exec) {
            return super.call("CSS.collectClassNames", CollectClassNamesResult.class,
                (code, msg)->new CollectClassNamesResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of collectClassNames.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class CollectClassNamesResult extends ResultBase {
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
        public CollectClassNamesResult(
            @JsonProperty("classNames")List<String> classNames
        ) {
            this.classNames = classNames;
        }
        public CollectClassNamesResult(ResultBase.FailedResult e) {
            super(e);
            classNames = null;
        }
    }
    /**Creates a new special "via-inspector" stylesheet in the frame with given `frameId`.*/
    public CreateStyleSheetParameter createStyleSheet() { final CreateStyleSheetParameter v = new CreateStyleSheetParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of createStyleSheet.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class CreateStyleSheetParameter extends CommandBase {
        /**Identifier of the frame where "via-inspector" stylesheet should be created.*/
        private Page.FrameId frameId;
        public final CreateStyleSheetParameter frameId(Page.FrameId frameId) { this.frameId = frameId; return this; }
        public final CreateStyleSheetParameter setFrameId(Page.FrameId frameId) { return frameId(frameId); }
        public final Page.FrameId frameId() { return frameId; }
        public final Page.FrameId getFrameId() { return frameId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (frameId == null) throw new IllegalArgumentException("CSS.CreateStyleSheetParameter.frameId is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            frameId.toJson(strBuilder.append("\"frameId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public CreateStyleSheetParameter() {}
        public CreateStyleSheetParameter(
            @JsonProperty("frameId")Page.FrameId frameId
        ) {
            this();
            this.frameId = frameId;
        }
        public CompletableFuture<CreateStyleSheetResult> call() {
            return super.call("CSS.createStyleSheet", CreateStyleSheetResult.class,
                (code, msg)->new CreateStyleSheetResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<CreateStyleSheetResult> call(Executor exec) {
            return super.call("CSS.createStyleSheet", CreateStyleSheetResult.class,
                (code, msg)->new CreateStyleSheetResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of createStyleSheet.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class CreateStyleSheetResult extends ResultBase {
        /**Identifier of the created "via-inspector" stylesheet.*/
        private final StyleSheetId styleSheetId;
        public final StyleSheetId styleSheetId() { return styleSheetId; }
        public final StyleSheetId getStyleSheetId() { return styleSheetId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            styleSheetId.toJson(strBuilder.append("\"styleSheetId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public CreateStyleSheetResult(
            @JsonProperty("styleSheetId")StyleSheetId styleSheetId
        ) {
            this.styleSheetId = styleSheetId;
        }
        public CreateStyleSheetResult(ResultBase.FailedResult e) {
            super(e);
            styleSheetId = null;
        }
    }
    /**Disables the CSS agent for the given page.*/
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
            return super.call("CSS.disable", DisableResult.class,
                (code, msg)->new DisableResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<DisableResult> call(Executor exec) {
            return super.call("CSS.disable", DisableResult.class,
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
    /**Enables the CSS agent for the given page. Clients should not assume that the CSS agent has been
enabled until the result of this command is received.*/
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
            return super.call("CSS.enable", EnableResult.class,
                (code, msg)->new EnableResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<EnableResult> call(Executor exec) {
            return super.call("CSS.enable", EnableResult.class,
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
    /**Ensures that the given node will have specified pseudo-classes whenever its style is computed by
the browser.*/
    public ForcePseudoStateParameter forcePseudoState() { final ForcePseudoStateParameter v = new ForcePseudoStateParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of forcePseudoState.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ForcePseudoStateParameter extends CommandBase {
        /**The element id for which to force the pseudo state.*/
        private DOM.NodeId nodeId;
        /**Element pseudo classes to force when computing the element's style.*/
        private List<String> forcedPseudoClasses;
        public final ForcePseudoStateParameter nodeId(DOM.NodeId nodeId) { this.nodeId = nodeId; return this; }
        public final ForcePseudoStateParameter setNodeId(DOM.NodeId nodeId) { return nodeId(nodeId); }
        public final DOM.NodeId nodeId() { return nodeId; }
        public final DOM.NodeId getNodeId() { return nodeId(); }
        public final ForcePseudoStateParameter forcedPseudoClasses(List<String> forcedPseudoClasses) { this.forcedPseudoClasses = forcedPseudoClasses; return this; }
        public final ForcePseudoStateParameter setForcedPseudoClasses(List<String> forcedPseudoClasses) { return forcedPseudoClasses(forcedPseudoClasses); }
        public final List<String> forcedPseudoClasses() { return forcedPseudoClasses; }
        public final List<String> getForcedPseudoClasses() { return forcedPseudoClasses(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (nodeId == null) throw new IllegalArgumentException("CSS.ForcePseudoStateParameter.nodeId is necessary field.");
            if (forcedPseudoClasses == null) throw new IllegalArgumentException("CSS.ForcePseudoStateParameter.forcedPseudoClasses is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            nodeId.toJson(strBuilder.append("\"nodeId\":"));
                        strBuilder.append(",\"forcedPseudoClasses\":[");
            strBuilder.append('"').append(DomainBase.escapeQuote(forcedPseudoClasses.get(0))).append('"');
            for (int i = 1; i < forcedPseudoClasses.size(); ++i)
                strBuilder.append(",\"").append(DomainBase.escapeQuote(forcedPseudoClasses.get(i))).append('"');
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public ForcePseudoStateParameter() {}
        public ForcePseudoStateParameter(
            @JsonProperty("nodeId")DOM.NodeId nodeId,
            @JsonProperty("forcedPseudoClasses")List<String> forcedPseudoClasses
        ) {
            this();
            this.nodeId = nodeId;
            this.forcedPseudoClasses = forcedPseudoClasses;
        }
        public CompletableFuture<ForcePseudoStateResult> call() {
            return super.call("CSS.forcePseudoState", ForcePseudoStateResult.class,
                (code, msg)->new ForcePseudoStateResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<ForcePseudoStateResult> call(Executor exec) {
            return super.call("CSS.forcePseudoState", ForcePseudoStateResult.class,
                (code, msg)->new ForcePseudoStateResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of forcePseudoState.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class ForcePseudoStateResult extends ResultBase {
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
        public ForcePseudoStateResult() { super(); }
        public ForcePseudoStateResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**&lt;No document in protocol.&gt;*/
    public GetBackgroundColorsParameter getBackgroundColors() { final GetBackgroundColorsParameter v = new GetBackgroundColorsParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of getBackgroundColors.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetBackgroundColorsParameter extends CommandBase {
        /**Id of the node to get background colors for.*/
        private DOM.NodeId nodeId;
        public final GetBackgroundColorsParameter nodeId(DOM.NodeId nodeId) { this.nodeId = nodeId; return this; }
        public final GetBackgroundColorsParameter setNodeId(DOM.NodeId nodeId) { return nodeId(nodeId); }
        public final DOM.NodeId nodeId() { return nodeId; }
        public final DOM.NodeId getNodeId() { return nodeId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (nodeId == null) throw new IllegalArgumentException("CSS.GetBackgroundColorsParameter.nodeId is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            nodeId.toJson(strBuilder.append("\"nodeId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public GetBackgroundColorsParameter() {}
        public GetBackgroundColorsParameter(
            @JsonProperty("nodeId")DOM.NodeId nodeId
        ) {
            this();
            this.nodeId = nodeId;
        }
        public CompletableFuture<GetBackgroundColorsResult> call() {
            return super.call("CSS.getBackgroundColors", GetBackgroundColorsResult.class,
                (code, msg)->new GetBackgroundColorsResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetBackgroundColorsResult> call(Executor exec) {
            return super.call("CSS.getBackgroundColors", GetBackgroundColorsResult.class,
                (code, msg)->new GetBackgroundColorsResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of getBackgroundColors.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetBackgroundColorsResult extends ResultBase {
        /**The range of background colors behind this element, if it contains any visible text. If no
visible text is present, this will be undefined. In the case of a flat background color,
this will consist of simply that color. In the case of a gradient, this will consist of each
of the color stops. For anything more complicated, this will be an empty array. Images will
be ignored (as if the image had failed to load).
        <em>Optional.</em>*/
        private final List<String> backgroundColors;
        /**The computed font size for this node, as a CSS computed value string (e.g. '12px').
        <em>Optional.</em>*/
        private final String computedFontSize;
        /**The computed font weight for this node, as a CSS computed value string (e.g. 'normal' or
'100').
        <em>Optional.</em>*/
        private final String computedFontWeight;
        /**The computed font size for the document body, as a computed CSS value string (e.g. '16px').
        <em>Optional.</em>*/
        private final String computedBodyFontSize;
        public final List<String> backgroundColors() { return backgroundColors; }
        public final List<String> getBackgroundColors() { return backgroundColors(); }
        public final String computedFontSize() { return computedFontSize; }
        public final String getComputedFontSize() { return computedFontSize(); }
        public final String computedFontWeight() { return computedFontWeight; }
        public final String getComputedFontWeight() { return computedFontWeight(); }
        public final String computedBodyFontSize() { return computedBodyFontSize; }
        public final String getComputedBodyFontSize() { return computedBodyFontSize(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            if (backgroundColors != null) {
                strBuilder.append("\"backgroundColors\":[");
                strBuilder.append('"').append(DomainBase.escapeQuote(backgroundColors.get(0))).append('"');
                for (int i = 1; i < backgroundColors.size(); ++i)
                    strBuilder.append(",\"").append(DomainBase.escapeQuote(backgroundColors.get(i))).append('"');
                strBuilder.append(']');
            }
            if (computedFontSize != null) strBuilder.append(",\"computedFontSize\":").append('"').append(DomainBase.escapeQuote(computedFontSize)).append('"');
            if (computedFontWeight != null) strBuilder.append(",\"computedFontWeight\":").append('"').append(DomainBase.escapeQuote(computedFontWeight)).append('"');
            if (computedBodyFontSize != null) strBuilder.append(",\"computedBodyFontSize\":").append('"').append(DomainBase.escapeQuote(computedBodyFontSize)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public GetBackgroundColorsResult(
            @Nullable @JsonProperty("backgroundColors")List<String> backgroundColors,
            @Nullable @JsonProperty("computedFontSize")String computedFontSize,
            @Nullable @JsonProperty("computedFontWeight")String computedFontWeight,
            @Nullable @JsonProperty("computedBodyFontSize")String computedBodyFontSize
        ) {
            this.backgroundColors = backgroundColors;
            this.computedFontSize = computedFontSize;
            this.computedFontWeight = computedFontWeight;
            this.computedBodyFontSize = computedBodyFontSize;
        }
        public GetBackgroundColorsResult(ResultBase.FailedResult e) {
            super(e);
            backgroundColors = null;
            computedFontSize = null;
            computedFontWeight = null;
            computedBodyFontSize = null;
        }
    }
    /**Returns the computed style for a DOM node identified by `nodeId`.*/
    public GetComputedStyleForNodeParameter getComputedStyleForNode() { final GetComputedStyleForNodeParameter v = new GetComputedStyleForNodeParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of getComputedStyleForNode.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetComputedStyleForNodeParameter extends CommandBase {
        /**&lt;No document in protocol.&gt;*/
        private DOM.NodeId nodeId;
        public final GetComputedStyleForNodeParameter nodeId(DOM.NodeId nodeId) { this.nodeId = nodeId; return this; }
        public final GetComputedStyleForNodeParameter setNodeId(DOM.NodeId nodeId) { return nodeId(nodeId); }
        public final DOM.NodeId nodeId() { return nodeId; }
        public final DOM.NodeId getNodeId() { return nodeId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (nodeId == null) throw new IllegalArgumentException("CSS.GetComputedStyleForNodeParameter.nodeId is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            nodeId.toJson(strBuilder.append("\"nodeId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public GetComputedStyleForNodeParameter() {}
        public GetComputedStyleForNodeParameter(
            @JsonProperty("nodeId")DOM.NodeId nodeId
        ) {
            this();
            this.nodeId = nodeId;
        }
        public CompletableFuture<GetComputedStyleForNodeResult> call() {
            return super.call("CSS.getComputedStyleForNode", GetComputedStyleForNodeResult.class,
                (code, msg)->new GetComputedStyleForNodeResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetComputedStyleForNodeResult> call(Executor exec) {
            return super.call("CSS.getComputedStyleForNode", GetComputedStyleForNodeResult.class,
                (code, msg)->new GetComputedStyleForNodeResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of getComputedStyleForNode.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetComputedStyleForNodeResult extends ResultBase {
        /**Computed style for the specified DOM node.*/
        private final List<CSSComputedStyleProperty> computedStyle;
        public final List<CSSComputedStyleProperty> computedStyle() { return computedStyle; }
        public final List<CSSComputedStyleProperty> getComputedStyle() { return computedStyle(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
                        strBuilder.append("\"computedStyle\":[");
            computedStyle.get(0).toJson(strBuilder);
            for (int i = 1; i < computedStyle.size(); ++i)
                computedStyle.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public GetComputedStyleForNodeResult(
            @JsonProperty("computedStyle")List<CSSComputedStyleProperty> computedStyle
        ) {
            this.computedStyle = computedStyle;
        }
        public GetComputedStyleForNodeResult(ResultBase.FailedResult e) {
            super(e);
            computedStyle = null;
        }
    }
    /**Returns the styles defined inline (explicitly in the "style" attribute and implicitly, using DOM
attributes) for a DOM node identified by `nodeId`.*/
    public GetInlineStylesForNodeParameter getInlineStylesForNode() { final GetInlineStylesForNodeParameter v = new GetInlineStylesForNodeParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of getInlineStylesForNode.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetInlineStylesForNodeParameter extends CommandBase {
        /**&lt;No document in protocol.&gt;*/
        private DOM.NodeId nodeId;
        public final GetInlineStylesForNodeParameter nodeId(DOM.NodeId nodeId) { this.nodeId = nodeId; return this; }
        public final GetInlineStylesForNodeParameter setNodeId(DOM.NodeId nodeId) { return nodeId(nodeId); }
        public final DOM.NodeId nodeId() { return nodeId; }
        public final DOM.NodeId getNodeId() { return nodeId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (nodeId == null) throw new IllegalArgumentException("CSS.GetInlineStylesForNodeParameter.nodeId is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            nodeId.toJson(strBuilder.append("\"nodeId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public GetInlineStylesForNodeParameter() {}
        public GetInlineStylesForNodeParameter(
            @JsonProperty("nodeId")DOM.NodeId nodeId
        ) {
            this();
            this.nodeId = nodeId;
        }
        public CompletableFuture<GetInlineStylesForNodeResult> call() {
            return super.call("CSS.getInlineStylesForNode", GetInlineStylesForNodeResult.class,
                (code, msg)->new GetInlineStylesForNodeResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetInlineStylesForNodeResult> call(Executor exec) {
            return super.call("CSS.getInlineStylesForNode", GetInlineStylesForNodeResult.class,
                (code, msg)->new GetInlineStylesForNodeResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of getInlineStylesForNode.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetInlineStylesForNodeResult extends ResultBase {
        /**Inline style for the specified DOM node.
        <em>Optional.</em>*/
        private final CSSStyle inlineStyle;
        /**Attribute-defined element style (e.g. resulting from "width=20 height=100%").
        <em>Optional.</em>*/
        private final CSSStyle attributesStyle;
        public final CSSStyle inlineStyle() { return inlineStyle; }
        public final CSSStyle getInlineStyle() { return inlineStyle(); }
        public final CSSStyle attributesStyle() { return attributesStyle; }
        public final CSSStyle getAttributesStyle() { return attributesStyle(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            if (inlineStyle != null) inlineStyle.toJson(strBuilder.append("\"inlineStyle\":"));
            if (attributesStyle != null) attributesStyle.toJson(strBuilder.append(",\"attributesStyle\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public GetInlineStylesForNodeResult(
            @Nullable @JsonProperty("inlineStyle")CSSStyle inlineStyle,
            @Nullable @JsonProperty("attributesStyle")CSSStyle attributesStyle
        ) {
            this.inlineStyle = inlineStyle;
            this.attributesStyle = attributesStyle;
        }
        public GetInlineStylesForNodeResult(ResultBase.FailedResult e) {
            super(e);
            inlineStyle = null;
            attributesStyle = null;
        }
    }
    /**Returns requested styles for a DOM node identified by `nodeId`.*/
    public GetMatchedStylesForNodeParameter getMatchedStylesForNode() { final GetMatchedStylesForNodeParameter v = new GetMatchedStylesForNodeParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of getMatchedStylesForNode.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetMatchedStylesForNodeParameter extends CommandBase {
        /**&lt;No document in protocol.&gt;*/
        private DOM.NodeId nodeId;
        public final GetMatchedStylesForNodeParameter nodeId(DOM.NodeId nodeId) { this.nodeId = nodeId; return this; }
        public final GetMatchedStylesForNodeParameter setNodeId(DOM.NodeId nodeId) { return nodeId(nodeId); }
        public final DOM.NodeId nodeId() { return nodeId; }
        public final DOM.NodeId getNodeId() { return nodeId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (nodeId == null) throw new IllegalArgumentException("CSS.GetMatchedStylesForNodeParameter.nodeId is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            nodeId.toJson(strBuilder.append("\"nodeId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public GetMatchedStylesForNodeParameter() {}
        public GetMatchedStylesForNodeParameter(
            @JsonProperty("nodeId")DOM.NodeId nodeId
        ) {
            this();
            this.nodeId = nodeId;
        }
        public CompletableFuture<GetMatchedStylesForNodeResult> call() {
            return super.call("CSS.getMatchedStylesForNode", GetMatchedStylesForNodeResult.class,
                (code, msg)->new GetMatchedStylesForNodeResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetMatchedStylesForNodeResult> call(Executor exec) {
            return super.call("CSS.getMatchedStylesForNode", GetMatchedStylesForNodeResult.class,
                (code, msg)->new GetMatchedStylesForNodeResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of getMatchedStylesForNode.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetMatchedStylesForNodeResult extends ResultBase {
        /**Inline style for the specified DOM node.
        <em>Optional.</em>*/
        private final CSSStyle inlineStyle;
        /**Attribute-defined element style (e.g. resulting from "width=20 height=100%").
        <em>Optional.</em>*/
        private final CSSStyle attributesStyle;
        /**CSS rules matching this node, from all applicable stylesheets.
        <em>Optional.</em>*/
        private final List<RuleMatch> matchedCSSRules;
        /**Pseudo style matches for this node.
        <em>Optional.</em>*/
        private final List<PseudoElementMatches> pseudoElements;
        /**A chain of inherited styles (from the immediate node parent up to the DOM tree root).
        <em>Optional.</em>*/
        private final List<InheritedStyleEntry> inherited;
        /**A list of CSS keyframed animations matching this node.
        <em>Optional.</em>*/
        private final List<CSSKeyframesRule> cssKeyframesRules;
        public final CSSStyle inlineStyle() { return inlineStyle; }
        public final CSSStyle getInlineStyle() { return inlineStyle(); }
        public final CSSStyle attributesStyle() { return attributesStyle; }
        public final CSSStyle getAttributesStyle() { return attributesStyle(); }
        public final List<RuleMatch> matchedCSSRules() { return matchedCSSRules; }
        public final List<RuleMatch> getMatchedCSSRules() { return matchedCSSRules(); }
        public final List<PseudoElementMatches> pseudoElements() { return pseudoElements; }
        public final List<PseudoElementMatches> getPseudoElements() { return pseudoElements(); }
        public final List<InheritedStyleEntry> inherited() { return inherited; }
        public final List<InheritedStyleEntry> getInherited() { return inherited(); }
        public final List<CSSKeyframesRule> cssKeyframesRules() { return cssKeyframesRules; }
        public final List<CSSKeyframesRule> getCssKeyframesRules() { return cssKeyframesRules(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            if (inlineStyle != null) inlineStyle.toJson(strBuilder.append("\"inlineStyle\":"));
            if (attributesStyle != null) attributesStyle.toJson(strBuilder.append(",\"attributesStyle\":"));
            if (matchedCSSRules != null) {
                strBuilder.append(",\"matchedCSSRules\":[");
                matchedCSSRules.get(0).toJson(strBuilder);
                for (int i = 1; i < matchedCSSRules.size(); ++i)
                    matchedCSSRules.get(i).toJson(strBuilder.append(','));
                strBuilder.append(']');
            }
            if (pseudoElements != null) {
                strBuilder.append(",\"pseudoElements\":[");
                pseudoElements.get(0).toJson(strBuilder);
                for (int i = 1; i < pseudoElements.size(); ++i)
                    pseudoElements.get(i).toJson(strBuilder.append(','));
                strBuilder.append(']');
            }
            if (inherited != null) {
                strBuilder.append(",\"inherited\":[");
                inherited.get(0).toJson(strBuilder);
                for (int i = 1; i < inherited.size(); ++i)
                    inherited.get(i).toJson(strBuilder.append(','));
                strBuilder.append(']');
            }
            if (cssKeyframesRules != null) {
                strBuilder.append(",\"cssKeyframesRules\":[");
                cssKeyframesRules.get(0).toJson(strBuilder);
                for (int i = 1; i < cssKeyframesRules.size(); ++i)
                    cssKeyframesRules.get(i).toJson(strBuilder.append(','));
                strBuilder.append(']');
            }
            strBuilder.append('}');
            return strBuilder;
        }
        public GetMatchedStylesForNodeResult(
            @Nullable @JsonProperty("inlineStyle")CSSStyle inlineStyle,
            @Nullable @JsonProperty("attributesStyle")CSSStyle attributesStyle,
            @Nullable @JsonProperty("matchedCSSRules")List<RuleMatch> matchedCSSRules,
            @Nullable @JsonProperty("pseudoElements")List<PseudoElementMatches> pseudoElements,
            @Nullable @JsonProperty("inherited")List<InheritedStyleEntry> inherited,
            @Nullable @JsonProperty("cssKeyframesRules")List<CSSKeyframesRule> cssKeyframesRules
        ) {
            this.inlineStyle = inlineStyle;
            this.attributesStyle = attributesStyle;
            this.matchedCSSRules = matchedCSSRules;
            this.pseudoElements = pseudoElements;
            this.inherited = inherited;
            this.cssKeyframesRules = cssKeyframesRules;
        }
        public GetMatchedStylesForNodeResult(ResultBase.FailedResult e) {
            super(e);
            inlineStyle = null;
            attributesStyle = null;
            matchedCSSRules = null;
            pseudoElements = null;
            inherited = null;
            cssKeyframesRules = null;
        }
    }
    /**Returns all media queries parsed by the rendering engine.*/
    public GetMediaQueriesParameter getMediaQueries() { final GetMediaQueriesParameter v = new GetMediaQueriesParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of getMediaQueries.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetMediaQueriesParameter extends CommandBase {
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
        public GetMediaQueriesParameter() {}
        public CompletableFuture<GetMediaQueriesResult> call() {
            return super.call("CSS.getMediaQueries", GetMediaQueriesResult.class,
                (code, msg)->new GetMediaQueriesResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetMediaQueriesResult> call(Executor exec) {
            return super.call("CSS.getMediaQueries", GetMediaQueriesResult.class,
                (code, msg)->new GetMediaQueriesResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of getMediaQueries.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetMediaQueriesResult extends ResultBase {
        /**&lt;No document in protocol.&gt;*/
        private final List<CSSMedia> medias;
        public final List<CSSMedia> medias() { return medias; }
        public final List<CSSMedia> getMedias() { return medias(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
                        strBuilder.append("\"medias\":[");
            medias.get(0).toJson(strBuilder);
            for (int i = 1; i < medias.size(); ++i)
                medias.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public GetMediaQueriesResult(
            @JsonProperty("medias")List<CSSMedia> medias
        ) {
            this.medias = medias;
        }
        public GetMediaQueriesResult(ResultBase.FailedResult e) {
            super(e);
            medias = null;
        }
    }
    /**Requests information about platform fonts which we used to render child TextNodes in the given
node.*/
    public GetPlatformFontsForNodeParameter getPlatformFontsForNode() { final GetPlatformFontsForNodeParameter v = new GetPlatformFontsForNodeParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of getPlatformFontsForNode.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetPlatformFontsForNodeParameter extends CommandBase {
        /**&lt;No document in protocol.&gt;*/
        private DOM.NodeId nodeId;
        public final GetPlatformFontsForNodeParameter nodeId(DOM.NodeId nodeId) { this.nodeId = nodeId; return this; }
        public final GetPlatformFontsForNodeParameter setNodeId(DOM.NodeId nodeId) { return nodeId(nodeId); }
        public final DOM.NodeId nodeId() { return nodeId; }
        public final DOM.NodeId getNodeId() { return nodeId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (nodeId == null) throw new IllegalArgumentException("CSS.GetPlatformFontsForNodeParameter.nodeId is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            nodeId.toJson(strBuilder.append("\"nodeId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public GetPlatformFontsForNodeParameter() {}
        public GetPlatformFontsForNodeParameter(
            @JsonProperty("nodeId")DOM.NodeId nodeId
        ) {
            this();
            this.nodeId = nodeId;
        }
        public CompletableFuture<GetPlatformFontsForNodeResult> call() {
            return super.call("CSS.getPlatformFontsForNode", GetPlatformFontsForNodeResult.class,
                (code, msg)->new GetPlatformFontsForNodeResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetPlatformFontsForNodeResult> call(Executor exec) {
            return super.call("CSS.getPlatformFontsForNode", GetPlatformFontsForNodeResult.class,
                (code, msg)->new GetPlatformFontsForNodeResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of getPlatformFontsForNode.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetPlatformFontsForNodeResult extends ResultBase {
        /**Usage statistics for every employed platform font.*/
        private final List<PlatformFontUsage> fonts;
        public final List<PlatformFontUsage> fonts() { return fonts; }
        public final List<PlatformFontUsage> getFonts() { return fonts(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
                        strBuilder.append("\"fonts\":[");
            fonts.get(0).toJson(strBuilder);
            for (int i = 1; i < fonts.size(); ++i)
                fonts.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public GetPlatformFontsForNodeResult(
            @JsonProperty("fonts")List<PlatformFontUsage> fonts
        ) {
            this.fonts = fonts;
        }
        public GetPlatformFontsForNodeResult(ResultBase.FailedResult e) {
            super(e);
            fonts = null;
        }
    }
    /**Returns the current textual content for a stylesheet.*/
    public GetStyleSheetTextParameter getStyleSheetText() { final GetStyleSheetTextParameter v = new GetStyleSheetTextParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of getStyleSheetText.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetStyleSheetTextParameter extends CommandBase {
        /**&lt;No document in protocol.&gt;*/
        private StyleSheetId styleSheetId;
        public final GetStyleSheetTextParameter styleSheetId(StyleSheetId styleSheetId) { this.styleSheetId = styleSheetId; return this; }
        public final GetStyleSheetTextParameter setStyleSheetId(StyleSheetId styleSheetId) { return styleSheetId(styleSheetId); }
        public final StyleSheetId styleSheetId() { return styleSheetId; }
        public final StyleSheetId getStyleSheetId() { return styleSheetId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (styleSheetId == null) throw new IllegalArgumentException("CSS.GetStyleSheetTextParameter.styleSheetId is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            styleSheetId.toJson(strBuilder.append("\"styleSheetId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public GetStyleSheetTextParameter() {}
        public GetStyleSheetTextParameter(
            @JsonProperty("styleSheetId")StyleSheetId styleSheetId
        ) {
            this();
            this.styleSheetId = styleSheetId;
        }
        public CompletableFuture<GetStyleSheetTextResult> call() {
            return super.call("CSS.getStyleSheetText", GetStyleSheetTextResult.class,
                (code, msg)->new GetStyleSheetTextResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetStyleSheetTextResult> call(Executor exec) {
            return super.call("CSS.getStyleSheetText", GetStyleSheetTextResult.class,
                (code, msg)->new GetStyleSheetTextResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of getStyleSheetText.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetStyleSheetTextResult extends ResultBase {
        /**The stylesheet text.*/
        private final String text;
        public final String text() { return text; }
        public final String getText() { return text(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"text\":").append('"').append(DomainBase.escapeQuote(text)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public GetStyleSheetTextResult(
            @JsonProperty("text")String text
        ) {
            this.text = text;
        }
        public GetStyleSheetTextResult(ResultBase.FailedResult e) {
            super(e);
            text = null;
        }
    }
    /**Find a rule with the given active property for the given node and set the new value for this
property*/
    public SetEffectivePropertyValueForNodeParameter setEffectivePropertyValueForNode() { final SetEffectivePropertyValueForNodeParameter v = new SetEffectivePropertyValueForNodeParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setEffectivePropertyValueForNode.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetEffectivePropertyValueForNodeParameter extends CommandBase {
        /**The element id for which to set property.*/
        private DOM.NodeId nodeId;
        /**&lt;No document in protocol.&gt;*/
        private String propertyName;
        /**&lt;No document in protocol.&gt;*/
        private String value;
        public final SetEffectivePropertyValueForNodeParameter nodeId(DOM.NodeId nodeId) { this.nodeId = nodeId; return this; }
        public final SetEffectivePropertyValueForNodeParameter setNodeId(DOM.NodeId nodeId) { return nodeId(nodeId); }
        public final DOM.NodeId nodeId() { return nodeId; }
        public final DOM.NodeId getNodeId() { return nodeId(); }
        public final SetEffectivePropertyValueForNodeParameter propertyName(String propertyName) { this.propertyName = propertyName; return this; }
        public final SetEffectivePropertyValueForNodeParameter setPropertyName(String propertyName) { return propertyName(propertyName); }
        public final String propertyName() { return propertyName; }
        public final String getPropertyName() { return propertyName(); }
        public final SetEffectivePropertyValueForNodeParameter value(String value) { this.value = value; return this; }
        public final SetEffectivePropertyValueForNodeParameter setValue(String value) { return value(value); }
        public final String value() { return value; }
        public final String getValue() { return value(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (nodeId == null) throw new IllegalArgumentException("CSS.SetEffectivePropertyValueForNodeParameter.nodeId is necessary field.");
            if (propertyName == null) throw new IllegalArgumentException("CSS.SetEffectivePropertyValueForNodeParameter.propertyName is necessary field.");
            if (value == null) throw new IllegalArgumentException("CSS.SetEffectivePropertyValueForNodeParameter.value is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            nodeId.toJson(strBuilder.append("\"nodeId\":"));
            strBuilder.append(",\"propertyName\":").append('"').append(DomainBase.escapeQuote(propertyName)).append('"');
            strBuilder.append(",\"value\":").append('"').append(DomainBase.escapeQuote(value)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public SetEffectivePropertyValueForNodeParameter() {}
        public SetEffectivePropertyValueForNodeParameter(
            @JsonProperty("nodeId")DOM.NodeId nodeId,
            @JsonProperty("propertyName")String propertyName,
            @JsonProperty("value")String value
        ) {
            this();
            this.nodeId = nodeId;
            this.propertyName = propertyName;
            this.value = value;
        }
        public CompletableFuture<SetEffectivePropertyValueForNodeResult> call() {
            return super.call("CSS.setEffectivePropertyValueForNode", SetEffectivePropertyValueForNodeResult.class,
                (code, msg)->new SetEffectivePropertyValueForNodeResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetEffectivePropertyValueForNodeResult> call(Executor exec) {
            return super.call("CSS.setEffectivePropertyValueForNode", SetEffectivePropertyValueForNodeResult.class,
                (code, msg)->new SetEffectivePropertyValueForNodeResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setEffectivePropertyValueForNode.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetEffectivePropertyValueForNodeResult extends ResultBase {
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
        public SetEffectivePropertyValueForNodeResult() { super(); }
        public SetEffectivePropertyValueForNodeResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Modifies the keyframe rule key text.*/
    public SetKeyframeKeyParameter setKeyframeKey() { final SetKeyframeKeyParameter v = new SetKeyframeKeyParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setKeyframeKey.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetKeyframeKeyParameter extends CommandBase {
        /**&lt;No document in protocol.&gt;*/
        private StyleSheetId styleSheetId;
        /**&lt;No document in protocol.&gt;*/
        private SourceRange range;
        /**&lt;No document in protocol.&gt;*/
        private String keyText;
        public final SetKeyframeKeyParameter styleSheetId(StyleSheetId styleSheetId) { this.styleSheetId = styleSheetId; return this; }
        public final SetKeyframeKeyParameter setStyleSheetId(StyleSheetId styleSheetId) { return styleSheetId(styleSheetId); }
        public final StyleSheetId styleSheetId() { return styleSheetId; }
        public final StyleSheetId getStyleSheetId() { return styleSheetId(); }
        public final SetKeyframeKeyParameter range(SourceRange range) { this.range = range; return this; }
        public final SetKeyframeKeyParameter setRange(SourceRange range) { return range(range); }
        public final SourceRange range() { return range; }
        public final SourceRange getRange() { return range(); }
        public final SetKeyframeKeyParameter keyText(String keyText) { this.keyText = keyText; return this; }
        public final SetKeyframeKeyParameter setKeyText(String keyText) { return keyText(keyText); }
        public final String keyText() { return keyText; }
        public final String getKeyText() { return keyText(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (styleSheetId == null) throw new IllegalArgumentException("CSS.SetKeyframeKeyParameter.styleSheetId is necessary field.");
            if (range == null) throw new IllegalArgumentException("CSS.SetKeyframeKeyParameter.range is necessary field.");
            if (keyText == null) throw new IllegalArgumentException("CSS.SetKeyframeKeyParameter.keyText is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            styleSheetId.toJson(strBuilder.append("\"styleSheetId\":"));
            range.toJson(strBuilder.append(",\"range\":"));
            strBuilder.append(",\"keyText\":").append('"').append(DomainBase.escapeQuote(keyText)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public SetKeyframeKeyParameter() {}
        public SetKeyframeKeyParameter(
            @JsonProperty("styleSheetId")StyleSheetId styleSheetId,
            @JsonProperty("range")SourceRange range,
            @JsonProperty("keyText")String keyText
        ) {
            this();
            this.styleSheetId = styleSheetId;
            this.range = range;
            this.keyText = keyText;
        }
        public CompletableFuture<SetKeyframeKeyResult> call() {
            return super.call("CSS.setKeyframeKey", SetKeyframeKeyResult.class,
                (code, msg)->new SetKeyframeKeyResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetKeyframeKeyResult> call(Executor exec) {
            return super.call("CSS.setKeyframeKey", SetKeyframeKeyResult.class,
                (code, msg)->new SetKeyframeKeyResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setKeyframeKey.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetKeyframeKeyResult extends ResultBase {
        /**The resulting key text after modification.*/
        private final Value keyText;
        public final Value keyText() { return keyText; }
        public final Value getKeyText() { return keyText(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            keyText.toJson(strBuilder.append("\"keyText\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public SetKeyframeKeyResult(
            @JsonProperty("keyText")Value keyText
        ) {
            this.keyText = keyText;
        }
        public SetKeyframeKeyResult(ResultBase.FailedResult e) {
            super(e);
            keyText = null;
        }
    }
    /**Modifies the rule selector.*/
    public SetMediaTextParameter setMediaText() { final SetMediaTextParameter v = new SetMediaTextParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setMediaText.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetMediaTextParameter extends CommandBase {
        /**&lt;No document in protocol.&gt;*/
        private StyleSheetId styleSheetId;
        /**&lt;No document in protocol.&gt;*/
        private SourceRange range;
        /**&lt;No document in protocol.&gt;*/
        private String text;
        public final SetMediaTextParameter styleSheetId(StyleSheetId styleSheetId) { this.styleSheetId = styleSheetId; return this; }
        public final SetMediaTextParameter setStyleSheetId(StyleSheetId styleSheetId) { return styleSheetId(styleSheetId); }
        public final StyleSheetId styleSheetId() { return styleSheetId; }
        public final StyleSheetId getStyleSheetId() { return styleSheetId(); }
        public final SetMediaTextParameter range(SourceRange range) { this.range = range; return this; }
        public final SetMediaTextParameter setRange(SourceRange range) { return range(range); }
        public final SourceRange range() { return range; }
        public final SourceRange getRange() { return range(); }
        public final SetMediaTextParameter text(String text) { this.text = text; return this; }
        public final SetMediaTextParameter setText(String text) { return text(text); }
        public final String text() { return text; }
        public final String getText() { return text(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (styleSheetId == null) throw new IllegalArgumentException("CSS.SetMediaTextParameter.styleSheetId is necessary field.");
            if (range == null) throw new IllegalArgumentException("CSS.SetMediaTextParameter.range is necessary field.");
            if (text == null) throw new IllegalArgumentException("CSS.SetMediaTextParameter.text is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            styleSheetId.toJson(strBuilder.append("\"styleSheetId\":"));
            range.toJson(strBuilder.append(",\"range\":"));
            strBuilder.append(",\"text\":").append('"').append(DomainBase.escapeQuote(text)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public SetMediaTextParameter() {}
        public SetMediaTextParameter(
            @JsonProperty("styleSheetId")StyleSheetId styleSheetId,
            @JsonProperty("range")SourceRange range,
            @JsonProperty("text")String text
        ) {
            this();
            this.styleSheetId = styleSheetId;
            this.range = range;
            this.text = text;
        }
        public CompletableFuture<SetMediaTextResult> call() {
            return super.call("CSS.setMediaText", SetMediaTextResult.class,
                (code, msg)->new SetMediaTextResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetMediaTextResult> call(Executor exec) {
            return super.call("CSS.setMediaText", SetMediaTextResult.class,
                (code, msg)->new SetMediaTextResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setMediaText.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetMediaTextResult extends ResultBase {
        /**The resulting CSS media rule after modification.*/
        private final CSSMedia media;
        public final CSSMedia media() { return media; }
        public final CSSMedia getMedia() { return media(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            media.toJson(strBuilder.append("\"media\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public SetMediaTextResult(
            @JsonProperty("media")CSSMedia media
        ) {
            this.media = media;
        }
        public SetMediaTextResult(ResultBase.FailedResult e) {
            super(e);
            media = null;
        }
    }
    /**Modifies the rule selector.*/
    public SetRuleSelectorParameter setRuleSelector() { final SetRuleSelectorParameter v = new SetRuleSelectorParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setRuleSelector.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetRuleSelectorParameter extends CommandBase {
        /**&lt;No document in protocol.&gt;*/
        private StyleSheetId styleSheetId;
        /**&lt;No document in protocol.&gt;*/
        private SourceRange range;
        /**&lt;No document in protocol.&gt;*/
        private String selector;
        public final SetRuleSelectorParameter styleSheetId(StyleSheetId styleSheetId) { this.styleSheetId = styleSheetId; return this; }
        public final SetRuleSelectorParameter setStyleSheetId(StyleSheetId styleSheetId) { return styleSheetId(styleSheetId); }
        public final StyleSheetId styleSheetId() { return styleSheetId; }
        public final StyleSheetId getStyleSheetId() { return styleSheetId(); }
        public final SetRuleSelectorParameter range(SourceRange range) { this.range = range; return this; }
        public final SetRuleSelectorParameter setRange(SourceRange range) { return range(range); }
        public final SourceRange range() { return range; }
        public final SourceRange getRange() { return range(); }
        public final SetRuleSelectorParameter selector(String selector) { this.selector = selector; return this; }
        public final SetRuleSelectorParameter setSelector(String selector) { return selector(selector); }
        public final String selector() { return selector; }
        public final String getSelector() { return selector(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (styleSheetId == null) throw new IllegalArgumentException("CSS.SetRuleSelectorParameter.styleSheetId is necessary field.");
            if (range == null) throw new IllegalArgumentException("CSS.SetRuleSelectorParameter.range is necessary field.");
            if (selector == null) throw new IllegalArgumentException("CSS.SetRuleSelectorParameter.selector is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            styleSheetId.toJson(strBuilder.append("\"styleSheetId\":"));
            range.toJson(strBuilder.append(",\"range\":"));
            strBuilder.append(",\"selector\":").append('"').append(DomainBase.escapeQuote(selector)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public SetRuleSelectorParameter() {}
        public SetRuleSelectorParameter(
            @JsonProperty("styleSheetId")StyleSheetId styleSheetId,
            @JsonProperty("range")SourceRange range,
            @JsonProperty("selector")String selector
        ) {
            this();
            this.styleSheetId = styleSheetId;
            this.range = range;
            this.selector = selector;
        }
        public CompletableFuture<SetRuleSelectorResult> call() {
            return super.call("CSS.setRuleSelector", SetRuleSelectorResult.class,
                (code, msg)->new SetRuleSelectorResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetRuleSelectorResult> call(Executor exec) {
            return super.call("CSS.setRuleSelector", SetRuleSelectorResult.class,
                (code, msg)->new SetRuleSelectorResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setRuleSelector.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetRuleSelectorResult extends ResultBase {
        /**The resulting selector list after modification.*/
        private final SelectorList selectorList;
        public final SelectorList selectorList() { return selectorList; }
        public final SelectorList getSelectorList() { return selectorList(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            selectorList.toJson(strBuilder.append("\"selectorList\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        public SetRuleSelectorResult(
            @JsonProperty("selectorList")SelectorList selectorList
        ) {
            this.selectorList = selectorList;
        }
        public SetRuleSelectorResult(ResultBase.FailedResult e) {
            super(e);
            selectorList = null;
        }
    }
    /**Sets the new stylesheet text.*/
    public SetStyleSheetTextParameter setStyleSheetText() { final SetStyleSheetTextParameter v = new SetStyleSheetTextParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setStyleSheetText.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetStyleSheetTextParameter extends CommandBase {
        /**&lt;No document in protocol.&gt;*/
        private StyleSheetId styleSheetId;
        /**&lt;No document in protocol.&gt;*/
        private String text;
        public final SetStyleSheetTextParameter styleSheetId(StyleSheetId styleSheetId) { this.styleSheetId = styleSheetId; return this; }
        public final SetStyleSheetTextParameter setStyleSheetId(StyleSheetId styleSheetId) { return styleSheetId(styleSheetId); }
        public final StyleSheetId styleSheetId() { return styleSheetId; }
        public final StyleSheetId getStyleSheetId() { return styleSheetId(); }
        public final SetStyleSheetTextParameter text(String text) { this.text = text; return this; }
        public final SetStyleSheetTextParameter setText(String text) { return text(text); }
        public final String text() { return text; }
        public final String getText() { return text(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (styleSheetId == null) throw new IllegalArgumentException("CSS.SetStyleSheetTextParameter.styleSheetId is necessary field.");
            if (text == null) throw new IllegalArgumentException("CSS.SetStyleSheetTextParameter.text is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            styleSheetId.toJson(strBuilder.append("\"styleSheetId\":"));
            strBuilder.append(",\"text\":").append('"').append(DomainBase.escapeQuote(text)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public SetStyleSheetTextParameter() {}
        public SetStyleSheetTextParameter(
            @JsonProperty("styleSheetId")StyleSheetId styleSheetId,
            @JsonProperty("text")String text
        ) {
            this();
            this.styleSheetId = styleSheetId;
            this.text = text;
        }
        public CompletableFuture<SetStyleSheetTextResult> call() {
            return super.call("CSS.setStyleSheetText", SetStyleSheetTextResult.class,
                (code, msg)->new SetStyleSheetTextResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetStyleSheetTextResult> call(Executor exec) {
            return super.call("CSS.setStyleSheetText", SetStyleSheetTextResult.class,
                (code, msg)->new SetStyleSheetTextResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setStyleSheetText.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetStyleSheetTextResult extends ResultBase {
        /**URL of source map associated with script (if any).
        <em>Optional.</em>*/
        private final String sourceMapURL;
        public final String sourceMapURL() { return sourceMapURL; }
        public final String getSourceMapURL() { return sourceMapURL(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            if (sourceMapURL != null) strBuilder.append("\"sourceMapURL\":").append('"').append(DomainBase.escapeQuote(sourceMapURL)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public SetStyleSheetTextResult(
            @Nullable @JsonProperty("sourceMapURL")String sourceMapURL
        ) {
            this.sourceMapURL = sourceMapURL;
        }
        public SetStyleSheetTextResult(ResultBase.FailedResult e) {
            super(e);
            sourceMapURL = null;
        }
    }
    /**Applies specified style edits one after another in the given order.*/
    public SetStyleTextsParameter setStyleTexts() { final SetStyleTextsParameter v = new SetStyleTextsParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of setStyleTexts.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetStyleTextsParameter extends CommandBase {
        /**&lt;No document in protocol.&gt;*/
        private List<StyleDeclarationEdit> edits;
        public final SetStyleTextsParameter edits(List<StyleDeclarationEdit> edits) { this.edits = edits; return this; }
        public final SetStyleTextsParameter setEdits(List<StyleDeclarationEdit> edits) { return edits(edits); }
        public final List<StyleDeclarationEdit> edits() { return edits; }
        public final List<StyleDeclarationEdit> getEdits() { return edits(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (edits == null) throw new IllegalArgumentException("CSS.SetStyleTextsParameter.edits is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
                        strBuilder.append("\"edits\":[");
            edits.get(0).toJson(strBuilder);
            for (int i = 1; i < edits.size(); ++i)
                edits.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public SetStyleTextsParameter() {}
        public SetStyleTextsParameter(
            @JsonProperty("edits")List<StyleDeclarationEdit> edits
        ) {
            this();
            this.edits = edits;
        }
        public CompletableFuture<SetStyleTextsResult> call() {
            return super.call("CSS.setStyleTexts", SetStyleTextsResult.class,
                (code, msg)->new SetStyleTextsResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<SetStyleTextsResult> call(Executor exec) {
            return super.call("CSS.setStyleTexts", SetStyleTextsResult.class,
                (code, msg)->new SetStyleTextsResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of setStyleTexts.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class SetStyleTextsResult extends ResultBase {
        /**The resulting styles after modification.*/
        private final List<CSSStyle> styles;
        public final List<CSSStyle> styles() { return styles; }
        public final List<CSSStyle> getStyles() { return styles(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
                        strBuilder.append("\"styles\":[");
            styles.get(0).toJson(strBuilder);
            for (int i = 1; i < styles.size(); ++i)
                styles.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public SetStyleTextsResult(
            @JsonProperty("styles")List<CSSStyle> styles
        ) {
            this.styles = styles;
        }
        public SetStyleTextsResult(ResultBase.FailedResult e) {
            super(e);
            styles = null;
        }
    }
    /**Enables the selector recording.*/
    public StartRuleUsageTrackingParameter startRuleUsageTracking() { final StartRuleUsageTrackingParameter v = new StartRuleUsageTrackingParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of startRuleUsageTracking.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class StartRuleUsageTrackingParameter extends CommandBase {
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
        public StartRuleUsageTrackingParameter() {}
        public CompletableFuture<StartRuleUsageTrackingResult> call() {
            return super.call("CSS.startRuleUsageTracking", StartRuleUsageTrackingResult.class,
                (code, msg)->new StartRuleUsageTrackingResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<StartRuleUsageTrackingResult> call(Executor exec) {
            return super.call("CSS.startRuleUsageTracking", StartRuleUsageTrackingResult.class,
                (code, msg)->new StartRuleUsageTrackingResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of startRuleUsageTracking.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class StartRuleUsageTrackingResult extends ResultBase {
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
        public StartRuleUsageTrackingResult() { super(); }
        public StartRuleUsageTrackingResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Stop tracking rule usage and return the list of rules that were used since last call to
`takeCoverageDelta` (or since start of coverage instrumentation)*/
    public StopRuleUsageTrackingParameter stopRuleUsageTracking() { final StopRuleUsageTrackingParameter v = new StopRuleUsageTrackingParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of stopRuleUsageTracking.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class StopRuleUsageTrackingParameter extends CommandBase {
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
        public StopRuleUsageTrackingParameter() {}
        public CompletableFuture<StopRuleUsageTrackingResult> call() {
            return super.call("CSS.stopRuleUsageTracking", StopRuleUsageTrackingResult.class,
                (code, msg)->new StopRuleUsageTrackingResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<StopRuleUsageTrackingResult> call(Executor exec) {
            return super.call("CSS.stopRuleUsageTracking", StopRuleUsageTrackingResult.class,
                (code, msg)->new StopRuleUsageTrackingResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of stopRuleUsageTracking.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class StopRuleUsageTrackingResult extends ResultBase {
        /**&lt;No document in protocol.&gt;*/
        private final List<RuleUsage> ruleUsage;
        public final List<RuleUsage> ruleUsage() { return ruleUsage; }
        public final List<RuleUsage> getRuleUsage() { return ruleUsage(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
                        strBuilder.append("\"ruleUsage\":[");
            ruleUsage.get(0).toJson(strBuilder);
            for (int i = 1; i < ruleUsage.size(); ++i)
                ruleUsage.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public StopRuleUsageTrackingResult(
            @JsonProperty("ruleUsage")List<RuleUsage> ruleUsage
        ) {
            this.ruleUsage = ruleUsage;
        }
        public StopRuleUsageTrackingResult(ResultBase.FailedResult e) {
            super(e);
            ruleUsage = null;
        }
    }
    /**Obtain list of rules that became used since last call to this method (or since start of coverage
instrumentation)*/
    public TakeCoverageDeltaParameter takeCoverageDelta() { final TakeCoverageDeltaParameter v = new TakeCoverageDeltaParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of takeCoverageDelta.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class TakeCoverageDeltaParameter extends CommandBase {
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
        public TakeCoverageDeltaParameter() {}
        public CompletableFuture<TakeCoverageDeltaResult> call() {
            return super.call("CSS.takeCoverageDelta", TakeCoverageDeltaResult.class,
                (code, msg)->new TakeCoverageDeltaResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<TakeCoverageDeltaResult> call(Executor exec) {
            return super.call("CSS.takeCoverageDelta", TakeCoverageDeltaResult.class,
                (code, msg)->new TakeCoverageDeltaResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of takeCoverageDelta.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class TakeCoverageDeltaResult extends ResultBase {
        /**&lt;No document in protocol.&gt;*/
        private final List<RuleUsage> coverage;
        public final List<RuleUsage> coverage() { return coverage; }
        public final List<RuleUsage> getCoverage() { return coverage(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
                        strBuilder.append("\"coverage\":[");
            coverage.get(0).toJson(strBuilder);
            for (int i = 1; i < coverage.size(); ++i)
                coverage.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public TakeCoverageDeltaResult(
            @JsonProperty("coverage")List<RuleUsage> coverage
        ) {
            this.coverage = coverage;
        }
        public TakeCoverageDeltaResult(ResultBase.FailedResult e) {
            super(e);
            coverage = null;
        }
    }
    /**Event parameter of CSS.fontsUpdated.
     @see #onFontsUpdated*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class FontsUpdatedEventParameter implements CommonDomainType {
        /**The web font that has loaded.
        <em>Optional.</em>*/
        private final FontFace font;
        public final FontFace font() { return font; }
        public final FontFace getFont() { return font(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            if (font != null) font.toJson(strBuilder.append("\"font\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        FontsUpdatedEventParameter(
            @Nullable @JsonProperty("font")FontFace font
        ) {
            this.font = font;
        }
    }
    /**Fires whenever a web font is updated.  A non-empty font parameter indicates a successfully loaded
web font
     @see FontsUpdatedEventParameter*/
    public void onFontsUpdated(Consumer<FontsUpdatedEventParameter> callback) {
        registerEventCallback("CSS.fontsUpdated", node -> {
            FontsUpdatedEventParameter param;
            try { param = EventCenter.deserializeJson(node, FontsUpdatedEventParameter.class); }
            catch (IOException e) { e.printStackTrace(); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of CSS.mediaQueryResultChanged.
     @see #onMediaQueryResultChanged*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class MediaQueryResultChangedEventParameter implements CommonDomainType {
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
        public MediaQueryResultChangedEventParameter() {}
    }
    /**Fires whenever a MediaQuery result changes (for example, after a browser window has been
resized.) The current implementation considers only viewport-dependent media features.
     @see MediaQueryResultChangedEventParameter*/
    public void onMediaQueryResultChanged(Consumer<MediaQueryResultChangedEventParameter> callback) {
        registerEventCallback("CSS.mediaQueryResultChanged", node -> {
            MediaQueryResultChangedEventParameter param;
            try { param = EventCenter.deserializeJson(node, MediaQueryResultChangedEventParameter.class); }
            catch (IOException e) { e.printStackTrace(); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of CSS.styleSheetAdded.
     @see #onStyleSheetAdded*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class StyleSheetAddedEventParameter implements CommonDomainType {
        /**Added stylesheet metainfo.*/
        private final CSSStyleSheetHeader header;
        public final CSSStyleSheetHeader header() { return header; }
        public final CSSStyleSheetHeader getHeader() { return header(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            header.toJson(strBuilder.append("\"header\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        StyleSheetAddedEventParameter(
            @JsonProperty("header")CSSStyleSheetHeader header
        ) {
            this.header = header;
        }
    }
    /**Fired whenever an active document stylesheet is added.
     @see StyleSheetAddedEventParameter*/
    public void onStyleSheetAdded(Consumer<StyleSheetAddedEventParameter> callback) {
        registerEventCallback("CSS.styleSheetAdded", node -> {
            StyleSheetAddedEventParameter param;
            try { param = EventCenter.deserializeJson(node, StyleSheetAddedEventParameter.class); }
            catch (IOException e) { e.printStackTrace(); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of CSS.styleSheetChanged.
     @see #onStyleSheetChanged*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class StyleSheetChangedEventParameter implements CommonDomainType {
        /**&lt;No document in protocol.&gt;*/
        private final StyleSheetId styleSheetId;
        public final StyleSheetId styleSheetId() { return styleSheetId; }
        public final StyleSheetId getStyleSheetId() { return styleSheetId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            styleSheetId.toJson(strBuilder.append("\"styleSheetId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        StyleSheetChangedEventParameter(
            @JsonProperty("styleSheetId")StyleSheetId styleSheetId
        ) {
            this.styleSheetId = styleSheetId;
        }
    }
    /**Fired whenever a stylesheet is changed as a result of the client operation.
     @see StyleSheetChangedEventParameter*/
    public void onStyleSheetChanged(Consumer<StyleSheetChangedEventParameter> callback) {
        registerEventCallback("CSS.styleSheetChanged", node -> {
            StyleSheetChangedEventParameter param;
            try { param = EventCenter.deserializeJson(node, StyleSheetChangedEventParameter.class); }
            catch (IOException e) { e.printStackTrace(); return; }
            callback.accept(param);
        });
    }
    /**Event parameter of CSS.styleSheetRemoved.
     @see #onStyleSheetRemoved*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class StyleSheetRemovedEventParameter implements CommonDomainType {
        /**Identifier of the removed stylesheet.*/
        private final StyleSheetId styleSheetId;
        public final StyleSheetId styleSheetId() { return styleSheetId; }
        public final StyleSheetId getStyleSheetId() { return styleSheetId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            styleSheetId.toJson(strBuilder.append("\"styleSheetId\":"));
            strBuilder.append('}');
            return strBuilder;
        }
        StyleSheetRemovedEventParameter(
            @JsonProperty("styleSheetId")StyleSheetId styleSheetId
        ) {
            this.styleSheetId = styleSheetId;
        }
    }
    /**Fired whenever an active document stylesheet is removed.
     @see StyleSheetRemovedEventParameter*/
    public void onStyleSheetRemoved(Consumer<StyleSheetRemovedEventParameter> callback) {
        registerEventCallback("CSS.styleSheetRemoved", node -> {
            StyleSheetRemovedEventParameter param;
            try { param = EventCenter.deserializeJson(node, StyleSheetRemovedEventParameter.class); }
            catch (IOException e) { e.printStackTrace(); return; }
            callback.accept(param);
        });
    }
}

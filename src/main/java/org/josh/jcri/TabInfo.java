package org.josh.jcri;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**Single tab's information replied by browser.
 This class stores single tab's information replied by browser with request:
 {@code http://host:port/json }. By sending this request, browser will reply list of json formatted
 all available tabs (and some other fields used by browser extensions). User can use this to specify
 which tab to connect to.
 @author Joshua
 @since 1.0 */
public class TabInfo {
    private final String description;
    private final String devtoolsFrontendUrl;
    private final String faviconUrl;
    private final String id;
    private final String title;
    private final String type;
    private final String url;
    private final String webSocketDebuggerUrl;

    @JsonCreator public TabInfo(
        @JsonProperty("description") String description,
        @JsonProperty("devtoolsFrontendUrl") String devtoolsFrontendUrl,
        @JsonProperty("faviconUrl") String faviconUrl,
        @JsonProperty("id") String id,
        @JsonProperty("title") String title,
        @JsonProperty("type") String type,
        @JsonProperty("url") String url,
        @JsonProperty("webSocketDebuggerUrl") String webSocketDebuggerUrl
    ) {
        this.description = description;
        this.devtoolsFrontendUrl = devtoolsFrontendUrl;
        this.faviconUrl = faviconUrl;
        this.id = id;
        this.title = title;
        this.type = type;
        this.url = url;
        this.webSocketDebuggerUrl = webSocketDebuggerUrl;
    }

    public final String getDescription() { return description; }
    public final String getDevtoolsFrontendUrl() { return devtoolsFrontendUrl; }
    public final String getFaviconUrl() { return faviconUrl; }
    public final String getId() { return id; }
    public final String getTitle() { return title; }
    public final String getType() { return type; }
    public final String getUrl() { return url; }
    public final String getWebSocketDebuggerUrl() { return webSocketDebuggerUrl; }
}

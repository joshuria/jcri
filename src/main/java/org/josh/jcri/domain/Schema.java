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

/**This domain is deprecated.
<p>From: js_protocol.json</p>
<p>Protocol version: 1.3</p>
 @author Joshua*/
 @Deprecated
@ParametersAreNonnullByDefault public class Schema extends DomainBase {
    public Schema(EventCenter evt, WebSocket ws) { super(evt, ws); }

    /**Description of the protocol domain.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class Domain implements CommonDomainType {
        /**Domain name.*/
        private String name;
        /**Domain version.*/
        private String version;
        public final Domain name(String name) { this.name = name; return this; }
        public final Domain setName(String name) { return name(name); }
        public final String name() { return name; }
        public final String getName() { return name(); }
        public final Domain version(String version) { this.version = version; return this; }
        public final Domain setVersion(String version) { return version(version); }
        public final String version() { return version; }
        public final String getVersion() { return version(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (name == null) throw new IllegalArgumentException("Schema.Domain.name is necessary field.");
            if (version == null) throw new IllegalArgumentException("Schema.Domain.version is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"name\":").append('"').append(DomainBase.escapeJson(name)).append('"');
            strBuilder.append(",\"version\":").append('"').append(DomainBase.escapeJson(version)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public Domain() {}
        public Domain(
            @JsonProperty("name")String name,
            @JsonProperty("version")String version
        ) {
            this.name = name;
            this.version = version;
        }
    }
    /**Returns supported domains.*/
    public GetDomainsParameter getDomains() { final GetDomainsParameter v = new GetDomainsParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of getDomains.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetDomainsParameter extends CommandBase {
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
        public GetDomainsParameter() {}
        public CompletableFuture<GetDomainsResult> call() {
            return super.call("Schema.getDomains", GetDomainsResult.class,
                (code, msg)->new GetDomainsResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetDomainsResult> callAsync() {
            return super.callAsync("Schema.getDomains", GetDomainsResult.class,
                (code, msg)->new GetDomainsResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetDomainsResult> callAsync(Executor exec) {
            return super.callAsync("Schema.getDomains", GetDomainsResult.class,
                (code, msg)->new GetDomainsResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of getDomains.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetDomainsResult extends ResultBase {
        /**List of supported domains.*/
        private final List<Domain> domains;
        public final List<Domain> domains() { return domains; }
        public final List<Domain> getDomains() { return domains(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
                        strBuilder.append("\"domains\":[");
            domains.get(0).toJson(strBuilder);
            for (int i = 1; i < domains.size(); ++i)
                domains.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public GetDomainsResult(
            @JsonProperty("domains")List<Domain> domains
        ) {
            this.domains = domains;
        }
        public GetDomainsResult(ResultBase.FailedResult e) {
            super(e);
            domains = null;
        }
    }
}

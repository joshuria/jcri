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

/**The Tethering domain defines methods and events for browser port binding.
<p>From: browser_protocol.json</p>
<p>Protocol version: 1.3</p>
<p><strong>Experimental.</strong></p>
 @author Joshua*/
@ParametersAreNonnullByDefault public class Tethering extends DomainBase {
    public Tethering(EventCenter evt, WebSocket ws) { super(evt, ws); }
    /**Request browser port binding.*/
    public BindParameter bind() { final BindParameter v = new BindParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of bind.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class BindParameter extends CommandBase {
        /**Port number to bind.*/
        private Integer port;
        public final BindParameter port(Integer port) { this.port = port; return this; }
        public final BindParameter setPort(Integer port) { return port(port); }
        public final Integer port() { return port; }
        public final Integer getPort() { return port(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (port == null) throw new IllegalArgumentException("Tethering.BindParameter.port is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"port\":").append(port);
            strBuilder.append('}');
            return strBuilder;
        }
        public BindParameter() {}
        public BindParameter(
            @JsonProperty("port")Integer port
        ) {
            this();
            this.port = port;
        }
        public CompletableFuture<BindResult> call() {
            return super.call("Tethering.bind", BindResult.class,
                (code, msg)->new BindResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<BindResult> call(Executor exec) {
            return super.call("Tethering.bind", BindResult.class,
                (code, msg)->new BindResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of bind.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class BindResult extends ResultBase {
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
        public BindResult() { super(); }
        public BindResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Request browser port unbinding.*/
    public UnbindParameter unbind() { final UnbindParameter v = new UnbindParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of unbind.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class UnbindParameter extends CommandBase {
        /**Port number to unbind.*/
        private Integer port;
        public final UnbindParameter port(Integer port) { this.port = port; return this; }
        public final UnbindParameter setPort(Integer port) { return port(port); }
        public final Integer port() { return port; }
        public final Integer getPort() { return port(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (port == null) throw new IllegalArgumentException("Tethering.UnbindParameter.port is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"port\":").append(port);
            strBuilder.append('}');
            return strBuilder;
        }
        public UnbindParameter() {}
        public UnbindParameter(
            @JsonProperty("port")Integer port
        ) {
            this();
            this.port = port;
        }
        public CompletableFuture<UnbindResult> call() {
            return super.call("Tethering.unbind", UnbindResult.class,
                (code, msg)->new UnbindResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<UnbindResult> call(Executor exec) {
            return super.call("Tethering.unbind", UnbindResult.class,
                (code, msg)->new UnbindResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of unbind.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class UnbindResult extends ResultBase {
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
        public UnbindResult() { super(); }
        public UnbindResult(ResultBase.FailedResult e) {
            super(e);
        }
    }
    /**Event parameter of Tethering.accepted.
     @see #onAccepted*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class AcceptedEventParameter implements CommonDomainType {
        /**Port number that was successfully bound.*/
        private final Integer port;
        /**Connection id to be used.*/
        private final String connectionId;
        public final Integer port() { return port; }
        public final Integer getPort() { return port(); }
        public final String connectionId() { return connectionId; }
        public final String getConnectionId() { return connectionId(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"port\":").append(port);
            strBuilder.append(",\"connectionId\":").append('"').append(DomainBase.escapeQuote(connectionId)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        AcceptedEventParameter(
            @JsonProperty("port")Integer port,
            @JsonProperty("connectionId")String connectionId
        ) {
            this.port = port;
            this.connectionId = connectionId;
        }
    }
    /**Informs that port was successfully bound and got a specified connection id.
     @see AcceptedEventParameter*/
    public void onAccepted(Consumer<AcceptedEventParameter> callback) {
        registerEventCallback("Tethering.accepted", node -> {
            AcceptedEventParameter param;
            try { param = EventCenter.deserializeJson(node, AcceptedEventParameter.class); }
            catch (IOException e) { e.printStackTrace(); return; }
            callback.accept(param);
        });
    }
}

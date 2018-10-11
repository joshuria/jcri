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

/**The SystemInfo domain defines methods and events for querying low-level system information.
<p>From: browser_protocol.json</p>
<p>Protocol version: 1.3</p>
<p><strong>Experimental.</strong></p>
 @author Joshua*/
@ParametersAreNonnullByDefault public class SystemInfo extends DomainBase {
    public SystemInfo(EventCenter evt, WebSocket ws) { super(evt, ws); }

    /**Describes a single graphics processor (GPU).*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GPUDevice implements CommonDomainType {
        /**PCI ID of the GPU vendor, if available; 0 otherwise.*/
        private Double vendorId;
        /**PCI ID of the GPU device, if available; 0 otherwise.*/
        private Double deviceId;
        /**String description of the GPU vendor, if the PCI ID is not available.*/
        private String vendorString;
        /**String description of the GPU device, if the PCI ID is not available.*/
        private String deviceString;
        public final GPUDevice vendorId(Double vendorId) { this.vendorId = vendorId; return this; }
        public final GPUDevice setVendorId(Double vendorId) { return vendorId(vendorId); }
        public final Double vendorId() { return vendorId; }
        public final Double getVendorId() { return vendorId(); }
        public final GPUDevice deviceId(Double deviceId) { this.deviceId = deviceId; return this; }
        public final GPUDevice setDeviceId(Double deviceId) { return deviceId(deviceId); }
        public final Double deviceId() { return deviceId; }
        public final Double getDeviceId() { return deviceId(); }
        public final GPUDevice vendorString(String vendorString) { this.vendorString = vendorString; return this; }
        public final GPUDevice setVendorString(String vendorString) { return vendorString(vendorString); }
        public final String vendorString() { return vendorString; }
        public final String getVendorString() { return vendorString(); }
        public final GPUDevice deviceString(String deviceString) { this.deviceString = deviceString; return this; }
        public final GPUDevice setDeviceString(String deviceString) { return deviceString(deviceString); }
        public final String deviceString() { return deviceString; }
        public final String getDeviceString() { return deviceString(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (vendorId == null) throw new IllegalArgumentException("SystemInfo.GPUDevice.vendorId is necessary field.");
            if (deviceId == null) throw new IllegalArgumentException("SystemInfo.GPUDevice.deviceId is necessary field.");
            if (vendorString == null) throw new IllegalArgumentException("SystemInfo.GPUDevice.vendorString is necessary field.");
            if (deviceString == null) throw new IllegalArgumentException("SystemInfo.GPUDevice.deviceString is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            strBuilder.append("\"vendorId\":").append(vendorId);
            strBuilder.append(",\"deviceId\":").append(deviceId);
            strBuilder.append(",\"vendorString\":").append('"').append(DomainBase.escapeJson(vendorString)).append('"');
            strBuilder.append(",\"deviceString\":").append('"').append(DomainBase.escapeJson(deviceString)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public GPUDevice() {}
        public GPUDevice(
            @JsonProperty("vendorId")Double vendorId,
            @JsonProperty("deviceId")Double deviceId,
            @JsonProperty("vendorString")String vendorString,
            @JsonProperty("deviceString")String deviceString
        ) {
            this.vendorId = vendorId;
            this.deviceId = deviceId;
            this.vendorString = vendorString;
            this.deviceString = deviceString;
        }
    }

    /**Provides information about the GPU(s) on the system.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GPUInfo implements CommonDomainType {
        /**The graphics devices on the system. Element 0 is the primary GPU.*/
        private List<GPUDevice> devices;
        /**An optional dictionary of additional GPU related attributes.
        <em>Optional.</em>*/
        private Object auxAttributes;
        /**An optional dictionary of graphics features and their status.
        <em>Optional.</em>*/
        private Object featureStatus;
        /**An optional array of GPU driver bug workarounds.*/
        private List<String> driverBugWorkarounds;
        public final GPUInfo devices(List<GPUDevice> devices) { this.devices = devices; return this; }
        public final GPUInfo setDevices(List<GPUDevice> devices) { return devices(devices); }
        public final List<GPUDevice> devices() { return devices; }
        public final List<GPUDevice> getDevices() { return devices(); }
        public final GPUInfo auxAttributes(@Nullable Object auxAttributes) { this.auxAttributes = auxAttributes; return this; }
        public final GPUInfo optAuxAttributes(@Nullable Object auxAttributes) { return auxAttributes(auxAttributes); }
        public final Object auxAttributes() { return auxAttributes; }
        public final Object getAuxAttributes() { return auxAttributes(); }
        public final GPUInfo featureStatus(@Nullable Object featureStatus) { this.featureStatus = featureStatus; return this; }
        public final GPUInfo optFeatureStatus(@Nullable Object featureStatus) { return featureStatus(featureStatus); }
        public final Object featureStatus() { return featureStatus; }
        public final Object getFeatureStatus() { return featureStatus(); }
        public final GPUInfo driverBugWorkarounds(List<String> driverBugWorkarounds) { this.driverBugWorkarounds = driverBugWorkarounds; return this; }
        public final GPUInfo setDriverBugWorkarounds(List<String> driverBugWorkarounds) { return driverBugWorkarounds(driverBugWorkarounds); }
        public final List<String> driverBugWorkarounds() { return driverBugWorkarounds; }
        public final List<String> getDriverBugWorkarounds() { return driverBugWorkarounds(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
            if (devices == null) throw new IllegalArgumentException("SystemInfo.GPUInfo.devices is necessary field.");
            if (driverBugWorkarounds == null) throw new IllegalArgumentException("SystemInfo.GPUInfo.driverBugWorkarounds is necessary field.");
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
                        strBuilder.append("\"devices\":[");
            devices.get(0).toJson(strBuilder);
            for (int i = 1; i < devices.size(); ++i)
                devices.get(i).toJson(strBuilder.append(','));
            strBuilder.append(']');
            if (auxAttributes != null) strBuilder.append(",\"auxAttributes\":").append(auxAttributes);
            if (featureStatus != null) strBuilder.append(",\"featureStatus\":").append(featureStatus);
                        strBuilder.append(",\"driverBugWorkarounds\":[");
            strBuilder.append('"').append(DomainBase.escapeJson(driverBugWorkarounds.get(0))).append('"');
            for (int i = 1; i < driverBugWorkarounds.size(); ++i)
                strBuilder.append(",\"").append(DomainBase.escapeJson(driverBugWorkarounds.get(i))).append('"');
            strBuilder.append(']');
            strBuilder.append('}');
            return strBuilder;
        }
        public GPUInfo() {}
        public GPUInfo(
            @JsonProperty("devices")List<GPUDevice> devices,
            @Nullable @JsonProperty("auxAttributes")Object auxAttributes,
            @Nullable @JsonProperty("featureStatus")Object featureStatus,
            @JsonProperty("driverBugWorkarounds")List<String> driverBugWorkarounds
        ) {
            this.devices = devices;
            this.auxAttributes = auxAttributes;
            this.featureStatus = featureStatus;
            this.driverBugWorkarounds = driverBugWorkarounds;
        }
    }
    /**Returns information about the system.*/
    public GetInfoParameter getInfo() { final GetInfoParameter v = new GetInfoParameter(); v.setEventCenterAndSocket(_evt, _ws); return v; }
    /**Parameter class of getInfo.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetInfoParameter extends CommandBase {
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
        public GetInfoParameter() {}
        public CompletableFuture<GetInfoResult> call() {
            return super.call("SystemInfo.getInfo", GetInfoResult.class,
                (code, msg)->new GetInfoResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetInfoResult> callAsync() {
            return super.callAsync("SystemInfo.getInfo", GetInfoResult.class,
                (code, msg)->new GetInfoResult(ResultBase.ofError(code, msg)));
        }
        public CompletableFuture<GetInfoResult> callAsync(Executor exec) {
            return super.callAsync("SystemInfo.getInfo", GetInfoResult.class,
                (code, msg)->new GetInfoResult(ResultBase.ofError(code, msg)), exec);
        }
    }
    /**Return result class of getInfo.*/
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ParametersAreNonnullByDefault public static class GetInfoResult extends ResultBase {
        /**Information about the GPUs on the system.*/
        private final GPUInfo gpu;
        /**A platform-dependent description of the model of the machine. On Mac OS, this is, for
example, 'MacBookPro'. Will be the empty string if not supported.*/
        private final String modelName;
        /**A platform-dependent description of the version of the machine. On Mac OS, this is, for
example, '10.1'. Will be the empty string if not supported.*/
        private final String modelVersion;
        /**The command line string used to launch the browser. Will be the empty string if not
supported.*/
        private final String commandLine;
        public final GPUInfo gpu() { return gpu; }
        public final GPUInfo getGpu() { return gpu(); }
        public final String modelName() { return modelName; }
        public final String getModelName() { return modelName(); }
        public final String modelVersion() { return modelVersion; }
        public final String getModelVersion() { return modelVersion(); }
        public final String commandLine() { return commandLine; }
        public final String getCommandLine() { return commandLine(); }
        /**Check if parameter fields of method are all valid.
         @throws IllegalArgumentException if any of parameter is not valid. */
        @Override public void check() throws IllegalArgumentException {
        }
        /**Convert method parameter object into json string and append into string builder.
         @return string builder instance that is given in parameter (for chaining coding style use.) */
        @Override public StringBuilder toJson(StringBuilder strBuilder) {
            strBuilder.append('{');
            gpu.toJson(strBuilder.append("\"gpu\":"));
            strBuilder.append(",\"modelName\":").append('"').append(DomainBase.escapeJson(modelName)).append('"');
            strBuilder.append(",\"modelVersion\":").append('"').append(DomainBase.escapeJson(modelVersion)).append('"');
            strBuilder.append(",\"commandLine\":").append('"').append(DomainBase.escapeJson(commandLine)).append('"');
            strBuilder.append('}');
            return strBuilder;
        }
        public GetInfoResult(
            @JsonProperty("gpu")GPUInfo gpu,
            @JsonProperty("modelName")String modelName,
            @JsonProperty("modelVersion")String modelVersion,
            @JsonProperty("commandLine")String commandLine
        ) {
            this.gpu = gpu;
            this.modelName = modelName;
            this.modelVersion = modelVersion;
            this.commandLine = commandLine;
        }
        public GetInfoResult(ResultBase.FailedResult e) {
            super(e);
            gpu = null;
            modelName = null;
            modelVersion = null;
            commandLine = null;
        }
    }
}

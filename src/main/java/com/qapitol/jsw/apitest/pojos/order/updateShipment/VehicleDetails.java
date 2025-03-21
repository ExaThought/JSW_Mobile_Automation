
package com.qapitol.jsw.apitest.pojos.order.updateShipment;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "truckNumber",
    "driverNumber",
    "driverName",
    "truckReceiptNumber"
})
@Generated("jsonschema2pojo")
public class VehicleDetails {

    @JsonProperty("truckNumber")
    private String truckNumber;
    @JsonProperty("driverNumber")
    private String driverNumber;
    @JsonProperty("driverName")
    private String driverName;
    @JsonProperty("truckReceiptNumber")
    private String truckReceiptNumber;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("truckNumber")
    public String getTruckNumber() {
        return truckNumber;
    }

    @JsonProperty("truckNumber")
    public void setTruckNumber(String truckNumber) {
        this.truckNumber = truckNumber;
    }

    @JsonProperty("driverNumber")
    public String getDriverNumber() {
        return driverNumber;
    }

    @JsonProperty("driverNumber")
    public void setDriverNumber(String driverNumber) {
        this.driverNumber = driverNumber;
    }

    @JsonProperty("driverName")
    public String getDriverName() {
        return driverName;
    }

    @JsonProperty("driverName")
    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    @JsonProperty("truckReceiptNumber")
    public String getTruckReceiptNumber() {
        return truckReceiptNumber;
    }

    @JsonProperty("truckReceiptNumber")
    public void setTruckReceiptNumber(String truckReceiptNumber) {
        this.truckReceiptNumber = truckReceiptNumber;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(VehicleDetails.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("truckNumber");
        sb.append('=');
        sb.append(((this.truckNumber == null)?"<null>":this.truckNumber));
        sb.append(',');
        sb.append("driverNumber");
        sb.append('=');
        sb.append(((this.driverNumber == null)?"<null>":this.driverNumber));
        sb.append(',');
        sb.append("driverName");
        sb.append('=');
        sb.append(((this.driverName == null)?"<null>":this.driverName));
        sb.append(',');
        sb.append("truckReceiptNumber");
        sb.append('=');
        sb.append(((this.truckReceiptNumber == null)?"<null>":this.truckReceiptNumber));
        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.driverNumber == null)? 0 :this.driverNumber.hashCode()));
        result = ((result* 31)+((this.truckNumber == null)? 0 :this.truckNumber.hashCode()));
        result = ((result* 31)+((this.driverName == null)? 0 :this.driverName.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.truckReceiptNumber == null)? 0 :this.truckReceiptNumber.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof VehicleDetails) == false) {
            return false;
        }
        VehicleDetails rhs = ((VehicleDetails) other);
        return ((((((this.driverNumber == rhs.driverNumber)||((this.driverNumber!= null)&&this.driverNumber.equals(rhs.driverNumber)))&&((this.truckNumber == rhs.truckNumber)||((this.truckNumber!= null)&&this.truckNumber.equals(rhs.truckNumber))))&&((this.driverName == rhs.driverName)||((this.driverName!= null)&&this.driverName.equals(rhs.driverName))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.truckReceiptNumber == rhs.truckReceiptNumber)||((this.truckReceiptNumber!= null)&&this.truckReceiptNumber.equals(rhs.truckReceiptNumber))));
    }

}


package com.qapitol.jsw.apitest.pojos.order.createShipment;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "truckNumber",
    "driverNumber",
    "truckReceiptNumber",
    "driverName"
})
@Generated("jsonschema2pojo")
public class VehicleDetails {

    @JsonProperty("truckNumber")
    private Object truckNumber;
    @JsonProperty("driverNumber")
    private Object driverNumber;
    @JsonProperty("truckReceiptNumber")
    private Object truckReceiptNumber;
    @JsonProperty("driverName")
    private Object driverName;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("truckNumber")
    public Object getTruckNumber() {
        return truckNumber;
    }

    @JsonProperty("truckNumber")
    public void setTruckNumber(Object truckNumber) {
        this.truckNumber = truckNumber;
    }

    @JsonProperty("driverNumber")
    public Object getDriverNumber() {
        return driverNumber;
    }

    @JsonProperty("driverNumber")
    public void setDriverNumber(Object driverNumber) {
        this.driverNumber = driverNumber;
    }

    @JsonProperty("truckReceiptNumber")
    public Object getTruckReceiptNumber() {
        return truckReceiptNumber;
    }

    @JsonProperty("truckReceiptNumber")
    public void setTruckReceiptNumber(Object truckReceiptNumber) {
        this.truckReceiptNumber = truckReceiptNumber;
    }

    @JsonProperty("driverName")
    public Object getDriverName() {
        return driverName;
    }

    @JsonProperty("driverName")
    public void setDriverName(Object driverName) {
        this.driverName = driverName;
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
        sb.append("truckReceiptNumber");
        sb.append('=');
        sb.append(((this.truckReceiptNumber == null)?"<null>":this.truckReceiptNumber));
        sb.append(',');
        sb.append("driverName");
        sb.append('=');
        sb.append(((this.driverName == null)?"<null>":this.driverName));
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

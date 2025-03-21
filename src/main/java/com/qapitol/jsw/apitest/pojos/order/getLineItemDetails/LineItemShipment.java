
package com.qapitol.jsw.apitest.pojos.order.getLineItemDetails;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "shipmentId",
    "shipmentNumber"
})
@Generated("jsonschema2pojo")
public class LineItemShipment {

    @JsonProperty("shipmentId")
    private Integer shipmentId;
    @JsonProperty("shipmentNumber")
    private String shipmentNumber;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("shipmentId")
    public Integer getShipmentId() {
        return shipmentId;
    }

    @JsonProperty("shipmentId")
    public void setShipmentId(Integer shipmentId) {
        this.shipmentId = shipmentId;
    }

    @JsonProperty("shipmentNumber")
    public String getShipmentNumber() {
        return shipmentNumber;
    }

    @JsonProperty("shipmentNumber")
    public void setShipmentNumber(String shipmentNumber) {
        this.shipmentNumber = shipmentNumber;
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
        sb.append(LineItemShipment.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("shipmentId");
        sb.append('=');
        sb.append(((this.shipmentId == null)?"<null>":this.shipmentId));
        sb.append(',');
        sb.append("shipmentNumber");
        sb.append('=');
        sb.append(((this.shipmentNumber == null)?"<null>":this.shipmentNumber));
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
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.shipmentNumber == null)? 0 :this.shipmentNumber.hashCode()));
        result = ((result* 31)+((this.shipmentId == null)? 0 :this.shipmentId.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof LineItemShipment) == false) {
            return false;
        }
        LineItemShipment rhs = ((LineItemShipment) other);
        return ((((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties)))&&((this.shipmentNumber == rhs.shipmentNumber)||((this.shipmentNumber!= null)&&this.shipmentNumber.equals(rhs.shipmentNumber))))&&((this.shipmentId == rhs.shipmentId)||((this.shipmentId!= null)&&this.shipmentId.equals(rhs.shipmentId))));
    }

}

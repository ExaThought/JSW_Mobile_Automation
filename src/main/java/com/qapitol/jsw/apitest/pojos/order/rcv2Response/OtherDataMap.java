
package com.qapitol.jsw.apitest.pojos.order.rcv2Response;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "delivery_attribute",
    "totalCashback"
})
@Generated("jsonschema2pojo")
public class OtherDataMap {

    @JsonProperty("delivery_attribute")
    private String deliveryAttribute;
    @JsonProperty("totalCashback")
    private Double totalCashback;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("delivery_attribute")
    public String getDeliveryAttribute() {
        return deliveryAttribute;
    }

    @JsonProperty("delivery_attribute")
    public void setDeliveryAttribute(String deliveryAttribute) {
        this.deliveryAttribute = deliveryAttribute;
    }

    @JsonProperty("totalCashback")
    public Double getTotalCashback() {
        return totalCashback;
    }

    @JsonProperty("totalCashback")
    public void setTotalCashback(Double totalCashback) {
        this.totalCashback = totalCashback;
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
        sb.append(OtherDataMap.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("deliveryAttribute");
        sb.append('=');
        sb.append(((this.deliveryAttribute == null)?"<null>":this.deliveryAttribute));
        sb.append(',');
        sb.append("totalCashback");
        sb.append('=');
        sb.append(((this.totalCashback == null)?"<null>":this.totalCashback));
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
        result = ((result* 31)+((this.deliveryAttribute == null)? 0 :this.deliveryAttribute.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.totalCashback == null)? 0 :this.totalCashback.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof OtherDataMap) == false) {
            return false;
        }
        OtherDataMap rhs = ((OtherDataMap) other);
        return ((((this.deliveryAttribute == rhs.deliveryAttribute)||((this.deliveryAttribute!= null)&&this.deliveryAttribute.equals(rhs.deliveryAttribute)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.totalCashback == rhs.totalCashback)||((this.totalCashback!= null)&&this.totalCashback.equals(rhs.totalCashback))));
    }

}

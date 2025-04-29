
package com.qapitol.jsw.apitest.pojos.order.Rcv2Response;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "quantity",
    "uom"
})
@Generated("jsonschema2pojo")
public class PrimaryQuantity {

    @JsonProperty("quantity")
    private Double quantity;
    @JsonProperty("uom")
    private String uom;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("quantity")
    public Double getQuantity() {
        return quantity;
    }

    @JsonProperty("quantity")
    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    @JsonProperty("uom")
    public String getUom() {
        return uom;
    }

    @JsonProperty("uom")
    public void setUom(String uom) {
        this.uom = uom;
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
        sb.append(PrimaryQuantity.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("quantity");
        sb.append('=');
        sb.append(((this.quantity == null)?"<null>":this.quantity));
        sb.append(',');
        sb.append("uom");
        sb.append('=');
        sb.append(((this.uom == null)?"<null>":this.uom));
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
        result = ((result* 31)+((this.uom == null)? 0 :this.uom.hashCode()));
        result = ((result* 31)+((this.quantity == null)? 0 :this.quantity.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof PrimaryQuantity) == false) {
            return false;
        }
        PrimaryQuantity rhs = ((PrimaryQuantity) other);
        return ((((this.uom == rhs.uom)||((this.uom!= null)&&this.uom.equals(rhs.uom)))&&((this.quantity == rhs.quantity)||((this.quantity!= null)&&this.quantity.equals(rhs.quantity))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
    }

}

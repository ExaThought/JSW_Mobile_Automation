
package com.qapitol.jsw.apitest.pojos.order.rcv2Response;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "primaryQuantity",
    "secondaryQuantity",
    "primaryUom",
    "ctUom",
    "secondaryUom"
})
@Generated("jsonschema2pojo")
public class Quantity {

    @JsonProperty("primaryQuantity")
    private String primaryQuantity;
    @JsonProperty("secondaryQuantity")
    private Object secondaryQuantity;
    @JsonProperty("primaryUom")
    private PrimaryUom primaryUom;
    @JsonProperty("ctUom")
    private CtUom ctUom;
    @JsonProperty("secondaryUom")
    private Object secondaryUom;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("primaryQuantity")
    public String getPrimaryQuantity() {
        return primaryQuantity;
    }

    @JsonProperty("primaryQuantity")
    public void setPrimaryQuantity(String primaryQuantity) {
        this.primaryQuantity = primaryQuantity;
    }

    @JsonProperty("secondaryQuantity")
    public Object getSecondaryQuantity() {
        return secondaryQuantity;
    }

    @JsonProperty("secondaryQuantity")
    public void setSecondaryQuantity(Object secondaryQuantity) {
        this.secondaryQuantity = secondaryQuantity;
    }

    @JsonProperty("primaryUom")
    public PrimaryUom getPrimaryUom() {
        return primaryUom;
    }

    @JsonProperty("primaryUom")
    public void setPrimaryUom(PrimaryUom primaryUom) {
        this.primaryUom = primaryUom;
    }

    @JsonProperty("ctUom")
    public CtUom getCtUom() {
        return ctUom;
    }

    @JsonProperty("ctUom")
    public void setCtUom(CtUom ctUom) {
        this.ctUom = ctUom;
    }

    @JsonProperty("secondaryUom")
    public Object getSecondaryUom() {
        return secondaryUom;
    }

    @JsonProperty("secondaryUom")
    public void setSecondaryUom(Object secondaryUom) {
        this.secondaryUom = secondaryUom;
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
        sb.append(Quantity.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("primaryQuantity");
        sb.append('=');
        sb.append(((this.primaryQuantity == null)?"<null>":this.primaryQuantity));
        sb.append(',');
        sb.append("secondaryQuantity");
        sb.append('=');
        sb.append(((this.secondaryQuantity == null)?"<null>":this.secondaryQuantity));
        sb.append(',');
        sb.append("primaryUom");
        sb.append('=');
        sb.append(((this.primaryUom == null)?"<null>":this.primaryUom));
        sb.append(',');
        sb.append("ctUom");
        sb.append('=');
        sb.append(((this.ctUom == null)?"<null>":this.ctUom));
        sb.append(',');
        sb.append("secondaryUom");
        sb.append('=');
        sb.append(((this.secondaryUom == null)?"<null>":this.secondaryUom));
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
        result = ((result* 31)+((this.secondaryUom == null)? 0 :this.secondaryUom.hashCode()));
        result = ((result* 31)+((this.primaryUom == null)? 0 :this.primaryUom.hashCode()));
        result = ((result* 31)+((this.primaryQuantity == null)? 0 :this.primaryQuantity.hashCode()));
        result = ((result* 31)+((this.secondaryQuantity == null)? 0 :this.secondaryQuantity.hashCode()));
        result = ((result* 31)+((this.ctUom == null)? 0 :this.ctUom.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Quantity) == false) {
            return false;
        }
        Quantity rhs = ((Quantity) other);
        return (((((((this.secondaryUom == rhs.secondaryUom)||((this.secondaryUom!= null)&&this.secondaryUom.equals(rhs.secondaryUom)))&&((this.primaryUom == rhs.primaryUom)||((this.primaryUom!= null)&&this.primaryUom.equals(rhs.primaryUom))))&&((this.primaryQuantity == rhs.primaryQuantity)||((this.primaryQuantity!= null)&&this.primaryQuantity.equals(rhs.primaryQuantity))))&&((this.secondaryQuantity == rhs.secondaryQuantity)||((this.secondaryQuantity!= null)&&this.secondaryQuantity.equals(rhs.secondaryQuantity))))&&((this.ctUom == rhs.ctUom)||((this.ctUom!= null)&&this.ctUom.equals(rhs.ctUom))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
    }

}

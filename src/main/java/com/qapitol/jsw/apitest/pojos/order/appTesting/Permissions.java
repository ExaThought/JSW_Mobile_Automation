
package com.qapitol.jsw.apitest.pojos.order.appTesting;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "showPricing"
})
@Generated("jsonschema2pojo")
public class Permissions {

    @JsonProperty("showPricing")
    private Boolean showPricing;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("showPricing")
    public Boolean getShowPricing() {
        return showPricing;
    }

    @JsonProperty("showPricing")
    public void setShowPricing(Boolean showPricing) {
        this.showPricing = showPricing;
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
        sb.append(Permissions.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("showPricing");
        sb.append('=');
        sb.append(((this.showPricing == null)?"<null>":this.showPricing));
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
        result = ((result* 31)+((this.showPricing == null)? 0 :this.showPricing.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Permissions) == false) {
            return false;
        }
        Permissions rhs = ((Permissions) other);
        return (((this.showPricing == rhs.showPricing)||((this.showPricing!= null)&&this.showPricing.equals(rhs.showPricing)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
    }

}

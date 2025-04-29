
package com.qapitol.jsw.apitest.pojos.order.Rcv2Response;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "failedItems"
})
@Generated("jsonschema2pojo")
public class CartCustomMetaData {

    @JsonProperty("failedItems")
    private FailedItems failedItems;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("failedItems")
    public FailedItems getFailedItems() {
        return failedItems;
    }

    @JsonProperty("failedItems")
    public void setFailedItems(FailedItems failedItems) {
        this.failedItems = failedItems;
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
        sb.append(CartCustomMetaData.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("failedItems");
        sb.append('=');
        sb.append(((this.failedItems == null)?"<null>":this.failedItems));
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
        result = ((result* 31)+((this.failedItems == null)? 0 :this.failedItems.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof CartCustomMetaData) == false) {
            return false;
        }
        CartCustomMetaData rhs = ((CartCustomMetaData) other);
        return (((this.failedItems == rhs.failedItems)||((this.failedItems!= null)&&this.failedItems.equals(rhs.failedItems)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
    }

}

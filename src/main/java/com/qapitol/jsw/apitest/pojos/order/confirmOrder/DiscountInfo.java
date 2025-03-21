
package com.qapitol.jsw.apitest.pojos.order.confirmOrder;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "discountInfo"
})
@Generated("jsonschema2pojo")
public class DiscountInfo {

    @JsonProperty("discountInfo")
    private List<Object> discountInfo = new ArrayList<Object>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("discountInfo")
    public List<Object> getDiscountInfo() {
        return discountInfo;
    }

    @JsonProperty("discountInfo")
    public void setDiscountInfo(List<Object> discountInfo) {
        this.discountInfo = discountInfo;
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
        sb.append(DiscountInfo.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("discountInfo");
        sb.append('=');
        sb.append(((this.discountInfo == null)?"<null>":this.discountInfo));
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
        result = ((result* 31)+((this.discountInfo == null)? 0 :this.discountInfo.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof DiscountInfo) == false) {
            return false;
        }
        DiscountInfo rhs = ((DiscountInfo) other);
        return (((this.discountInfo == rhs.discountInfo)||((this.discountInfo!= null)&&this.discountInfo.equals(rhs.discountInfo)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
    }

}


package com.qapitol.jsw.apitest.pojos.order.centralCartSizeResponse;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "total_lineItem_count",
    "ENQUIRY_CART"
})
@Generated("jsonschema2pojo")
public class Data {

    @JsonProperty("total_lineItem_count")
    private Integer totalLineItemCount;
    @JsonProperty("ENQUIRY_CART")
    private Integer enquiryCart;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("total_lineItem_count")
    public Integer getTotalLineItemCount() {
        return totalLineItemCount;
    }

    @JsonProperty("total_lineItem_count")
    public void setTotalLineItemCount(Integer totalLineItemCount) {
        this.totalLineItemCount = totalLineItemCount;
    }

    @JsonProperty("ENQUIRY_CART")
    public Integer getEnquiryCart() {
        return enquiryCart;
    }

    @JsonProperty("ENQUIRY_CART")
    public void setEnquiryCart(Integer enquiryCart) {
        this.enquiryCart = enquiryCart;
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
        sb.append(Data.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("totalLineItemCount");
        sb.append('=');
        sb.append(((this.totalLineItemCount == null)?"<null>":this.totalLineItemCount));
        sb.append(',');
        sb.append("enquiryCart");
        sb.append('=');
        sb.append(((this.enquiryCart == null)?"<null>":this.enquiryCart));
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
        result = ((result* 31)+((this.enquiryCart == null)? 0 :this.enquiryCart.hashCode()));
        result = ((result* 31)+((this.totalLineItemCount == null)? 0 :this.totalLineItemCount.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Data) == false) {
            return false;
        }
        Data rhs = ((Data) other);
        return ((((this.enquiryCart == rhs.enquiryCart)||((this.enquiryCart!= null)&&this.enquiryCart.equals(rhs.enquiryCart)))&&((this.totalLineItemCount == rhs.totalLineItemCount)||((this.totalLineItemCount!= null)&&this.totalLineItemCount.equals(rhs.totalLineItemCount))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
    }

}

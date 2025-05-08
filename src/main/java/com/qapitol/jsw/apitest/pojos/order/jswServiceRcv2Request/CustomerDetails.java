
package com.qapitol.jsw.apitest.pojos.order.jswServiceRcv2Request;

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
    "sf_customer_id",
    "ct_customer_id"
})
@Generated("jsonschema2pojo")
public class CustomerDetails {


    @JsonProperty("sf_customer_id")
    private String sfCustomerId;
    @JsonProperty("ct_customer_id")
    private String ctCustomerId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("sf_customer_id")
    public String getSfCustomerId() {
        return sfCustomerId;
    }

    @JsonProperty("sf_customer_id")
    public void setSfCustomerId(String sfCustomerId) {
        this.sfCustomerId = sfCustomerId;
    }

    @JsonProperty("ct_customer_id")
    public String getCtCustomerId() {
        return ctCustomerId;
    }

    @JsonProperty("ct_customer_id")
    public void setCtCustomerId(String ctCustomerId) {
        this.ctCustomerId = ctCustomerId;
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
        sb.append(CustomerDetails.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("sfCustomerId");
        sb.append('=');
        sb.append(((this.sfCustomerId == null)?"<null>":this.sfCustomerId));
        sb.append(',');
        sb.append("ctCustomerId");
        sb.append('=');
        sb.append(((this.ctCustomerId == null)?"<null>":this.ctCustomerId));
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
        result = ((result* 31)+((this.ctCustomerId == null)? 0 :this.ctCustomerId.hashCode()));
        result = ((result* 31)+((this.sfCustomerId == null)? 0 :this.sfCustomerId.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof CustomerDetails) == false) {
            return false;
        }
        CustomerDetails rhs = ((CustomerDetails) other);
        return ((((this.ctCustomerId == rhs.ctCustomerId)||((this.ctCustomerId!= null)&&this.ctCustomerId.equals(rhs.ctCustomerId)))&&((this.sfCustomerId == rhs.sfCustomerId)||((this.sfCustomerId!= null)&&this.sfCustomerId.equals(rhs.sfCustomerId))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
    }

}

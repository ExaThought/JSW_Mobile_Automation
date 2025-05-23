
package com.qapitol.jsw.apitest.pojos.order.centralCartSlcAddToCartRequest;

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
    "is_pb_distributor",
    "retailer_name"
})
@Generated("jsonschema2pojo")
public class PbDistributor {

    @JsonProperty("is_pb_distributor")
    private Boolean isPbDistributor;
    @JsonProperty("retailer_name")
    private String retailerName;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("is_pb_distributor")
    public Boolean getIsPbDistributor() {
        return isPbDistributor;
    }

    @JsonProperty("is_pb_distributor")
    public void setIsPbDistributor(Boolean isPbDistributor) {
        this.isPbDistributor = isPbDistributor;
    }

    @JsonProperty("retailer_name")
    public String getRetailerName() {
        return retailerName;
    }

    @JsonProperty("retailer_name")
    public void setRetailerName(String retailerName) {
        this.retailerName = retailerName;
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
        sb.append(PbDistributor.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("isPbDistributor");
        sb.append('=');
        sb.append(((this.isPbDistributor == null)?"<null>":this.isPbDistributor));
        sb.append(',');
        sb.append("retailerName");
        sb.append('=');
        sb.append(((this.retailerName == null)?"<null>":this.retailerName));
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
        result = ((result* 31)+((this.isPbDistributor == null)? 0 :this.isPbDistributor.hashCode()));
        result = ((result* 31)+((this.retailerName == null)? 0 :this.retailerName.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof PbDistributor) == false) {
            return false;
        }
        PbDistributor rhs = ((PbDistributor) other);
        return ((((this.isPbDistributor == rhs.isPbDistributor)||((this.isPbDistributor!= null)&&this.isPbDistributor.equals(rhs.isPbDistributor)))&&((this.retailerName == rhs.retailerName)||((this.retailerName!= null)&&this.retailerName.equals(rhs.retailerName))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
    }

}

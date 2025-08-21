
package com.qapitol.jsw.apitest.pojos.order.orderConfirm.OrderConfirmReqPayload;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "sellerOrderNumber",
    "updatedStatus"
})
@Generated("jsonschema2pojo")
public class ChildOrder {

    @JsonProperty("sellerOrderNumber")
    private String sellerOrderNumber;
    @JsonProperty("updatedStatus")
    private String updatedStatus;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("sellerOrderNumber")
    public String getSellerOrderNumber() {
        return sellerOrderNumber;
    }

    @JsonProperty("sellerOrderNumber")
    public void setSellerOrderNumber(String sellerOrderNumber) {
        this.sellerOrderNumber = sellerOrderNumber;
    }

    @JsonProperty("updatedStatus")
    public String getUpdatedStatus() {
        return updatedStatus;
    }

    @JsonProperty("updatedStatus")
    public void setUpdatedStatus(String updatedStatus) {
        this.updatedStatus = updatedStatus;
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
        sb.append(ChildOrder.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("sellerOrderNumber");
        sb.append('=');
        sb.append(((this.sellerOrderNumber == null)?"<null>":this.sellerOrderNumber));
        sb.append(',');
        sb.append("updatedStatus");
        sb.append('=');
        sb.append(((this.updatedStatus == null)?"<null>":this.updatedStatus));
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
        result = ((result* 31)+((this.sellerOrderNumber == null)? 0 :this.sellerOrderNumber.hashCode()));
        result = ((result* 31)+((this.updatedStatus == null)? 0 :this.updatedStatus.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ChildOrder) == false) {
            return false;
        }
        ChildOrder rhs = ((ChildOrder) other);
        return ((((this.sellerOrderNumber == rhs.sellerOrderNumber)||((this.sellerOrderNumber!= null)&&this.sellerOrderNumber.equals(rhs.sellerOrderNumber)))&&((this.updatedStatus == rhs.updatedStatus)||((this.updatedStatus!= null)&&this.updatedStatus.equals(rhs.updatedStatus))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
    }

}


package com.qapitol.jsw.apitest.pojos.order.confirmOrder;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
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
    "orderNumber",
    "orderStatus",
    "opsSpoc",
    "childOrders",
    "isVerifiedPO",
    "isVerifiedSO"
})
@Generated("jsonschema2pojo")
public class ConfirmOrder {

    @JsonProperty("orderNumber")
    private String orderNumber;
    @JsonProperty("orderStatus")
    private String orderStatus;
    @JsonProperty("opsSpoc")
    private String opsSpoc;
    @JsonProperty("childOrders")
    private List<ChildOrder> childOrders = new ArrayList<ChildOrder>();
    @JsonProperty("isVerifiedPO")
    private Boolean isVerifiedPO;
    @JsonProperty("isVerifiedSO")
    private Boolean isVerifiedSO;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("orderNumber")
    public String getOrderNumber() {
        return orderNumber;
    }

    @JsonProperty("orderNumber")
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    @JsonProperty("orderStatus")
    public String getOrderStatus() {
        return orderStatus;
    }

    @JsonProperty("orderStatus")
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @JsonProperty("opsSpoc")
    public String getOpsSpoc() {
        return opsSpoc;
    }

    @JsonProperty("opsSpoc")
    public void setOpsSpoc(String opsSpoc) {
        this.opsSpoc = opsSpoc;
    }

    @JsonProperty("childOrders")
    public List<ChildOrder> getChildOrders() {
        return childOrders;
    }

    @JsonProperty("childOrders")
    public void setChildOrders(List<ChildOrder> childOrders) {
        this.childOrders = childOrders;
    }

    @JsonProperty("isVerifiedPO")
    public Boolean getIsVerifiedPO() {
        return isVerifiedPO;
    }

    @JsonProperty("isVerifiedPO")
    public void setIsVerifiedPO(Boolean isVerifiedPO) {
        this.isVerifiedPO = isVerifiedPO;
    }

    @JsonProperty("isVerifiedSO")
    public Boolean getIsVerifiedSO() {
        return isVerifiedSO;
    }

    @JsonProperty("isVerifiedSO")
    public void setIsVerifiedSO(Boolean isVerifiedSO) {
        this.isVerifiedSO = isVerifiedSO;
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
        sb.append(ConfirmOrder.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("orderNumber");
        sb.append('=');
        sb.append(((this.orderNumber == null)?"<null>":this.orderNumber));
        sb.append(',');
        sb.append("orderStatus");
        sb.append('=');
        sb.append(((this.orderStatus == null)?"<null>":this.orderStatus));
        sb.append(',');
        sb.append("opsSpoc");
        sb.append('=');
        sb.append(((this.opsSpoc == null)?"<null>":this.opsSpoc));
        sb.append(',');
        sb.append("childOrders");
        sb.append('=');
        sb.append(((this.childOrders == null)?"<null>":this.childOrders));
        sb.append(',');
        sb.append("isVerifiedPO");
        sb.append('=');
        sb.append(((this.isVerifiedPO == null)?"<null>":this.isVerifiedPO));
        sb.append(',');
        sb.append("isVerifiedSO");
        sb.append('=');
        sb.append(((this.isVerifiedSO == null)?"<null>":this.isVerifiedSO));
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
        result = ((result* 31)+((this.isVerifiedSO == null)? 0 :this.isVerifiedSO.hashCode()));
        result = ((result* 31)+((this.orderNumber == null)? 0 :this.orderNumber.hashCode()));
        result = ((result* 31)+((this.orderStatus == null)? 0 :this.orderStatus.hashCode()));
        result = ((result* 31)+((this.childOrders == null)? 0 :this.childOrders.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.opsSpoc == null)? 0 :this.opsSpoc.hashCode()));
        result = ((result* 31)+((this.isVerifiedPO == null)? 0 :this.isVerifiedPO.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ConfirmOrder) == false) {
            return false;
        }
        ConfirmOrder rhs = ((ConfirmOrder) other);
        return ((((((((this.isVerifiedSO == rhs.isVerifiedSO)||((this.isVerifiedSO!= null)&&this.isVerifiedSO.equals(rhs.isVerifiedSO)))&&((this.orderNumber == rhs.orderNumber)||((this.orderNumber!= null)&&this.orderNumber.equals(rhs.orderNumber))))&&((this.orderStatus == rhs.orderStatus)||((this.orderStatus!= null)&&this.orderStatus.equals(rhs.orderStatus))))&&((this.childOrders == rhs.childOrders)||((this.childOrders!= null)&&this.childOrders.equals(rhs.childOrders))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.opsSpoc == rhs.opsSpoc)||((this.opsSpoc!= null)&&this.opsSpoc.equals(rhs.opsSpoc))))&&((this.isVerifiedPO == rhs.isVerifiedPO)||((this.isVerifiedPO!= null)&&this.isVerifiedPO.equals(rhs.isVerifiedPO))));
    }

}

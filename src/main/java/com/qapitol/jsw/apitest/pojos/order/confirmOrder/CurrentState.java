
package com.qapitol.jsw.apitest.pojos.order.confirmOrder;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "orderState",
    "previousOrderState",
    "paymentStatus",
    "businessUnit",
    "deliveryType",
    "orderType",
    "paymentType",
    "paymentStatusInfo",
    "orderStateInfo"
})
@Generated("jsonschema2pojo")
public class CurrentState {

    @JsonProperty("orderState")
    private String orderState;
    @JsonProperty("previousOrderState")
    private String previousOrderState;
    @JsonProperty("paymentStatus")
    private String paymentStatus;
    @JsonProperty("businessUnit")
    private String businessUnit;
    @JsonProperty("deliveryType")
    private String deliveryType;
    @JsonProperty("orderType")
    private String orderType;
    @JsonProperty("paymentType")
    private String paymentType;
    @JsonProperty("paymentStatusInfo")
    private Object paymentStatusInfo;
    @JsonProperty("orderStateInfo")
    private OrderStateInfo orderStateInfo;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("orderState")
    public String getOrderState() {
        return orderState;
    }

    @JsonProperty("orderState")
    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    @JsonProperty("previousOrderState")
    public String getPreviousOrderState() {
        return previousOrderState;
    }

    @JsonProperty("previousOrderState")
    public void setPreviousOrderState(String previousOrderState) {
        this.previousOrderState = previousOrderState;
    }

    @JsonProperty("paymentStatus")
    public String getPaymentStatus() {
        return paymentStatus;
    }

    @JsonProperty("paymentStatus")
    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @JsonProperty("businessUnit")
    public String getBusinessUnit() {
        return businessUnit;
    }

    @JsonProperty("businessUnit")
    public void setBusinessUnit(String businessUnit) {
        this.businessUnit = businessUnit;
    }

    @JsonProperty("deliveryType")
    public String getDeliveryType() {
        return deliveryType;
    }

    @JsonProperty("deliveryType")
    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    @JsonProperty("orderType")
    public String getOrderType() {
        return orderType;
    }

    @JsonProperty("orderType")
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    @JsonProperty("paymentType")
    public String getPaymentType() {
        return paymentType;
    }

    @JsonProperty("paymentType")
    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    @JsonProperty("paymentStatusInfo")
    public Object getPaymentStatusInfo() {
        return paymentStatusInfo;
    }

    @JsonProperty("paymentStatusInfo")
    public void setPaymentStatusInfo(Object paymentStatusInfo) {
        this.paymentStatusInfo = paymentStatusInfo;
    }

    @JsonProperty("orderStateInfo")
    public OrderStateInfo getOrderStateInfo() {
        return orderStateInfo;
    }

    @JsonProperty("orderStateInfo")
    public void setOrderStateInfo(OrderStateInfo orderStateInfo) {
        this.orderStateInfo = orderStateInfo;
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
        sb.append(CurrentState.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("orderState");
        sb.append('=');
        sb.append(((this.orderState == null)?"<null>":this.orderState));
        sb.append(',');
        sb.append("previousOrderState");
        sb.append('=');
        sb.append(((this.previousOrderState == null)?"<null>":this.previousOrderState));
        sb.append(',');
        sb.append("paymentStatus");
        sb.append('=');
        sb.append(((this.paymentStatus == null)?"<null>":this.paymentStatus));
        sb.append(',');
        sb.append("businessUnit");
        sb.append('=');
        sb.append(((this.businessUnit == null)?"<null>":this.businessUnit));
        sb.append(',');
        sb.append("deliveryType");
        sb.append('=');
        sb.append(((this.deliveryType == null)?"<null>":this.deliveryType));
        sb.append(',');
        sb.append("orderType");
        sb.append('=');
        sb.append(((this.orderType == null)?"<null>":this.orderType));
        sb.append(',');
        sb.append("paymentType");
        sb.append('=');
        sb.append(((this.paymentType == null)?"<null>":this.paymentType));
        sb.append(',');
        sb.append("paymentStatusInfo");
        sb.append('=');
        sb.append(((this.paymentStatusInfo == null)?"<null>":this.paymentStatusInfo));
        sb.append(',');
        sb.append("orderStateInfo");
        sb.append('=');
        sb.append(((this.orderStateInfo == null)?"<null>":this.orderStateInfo));
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
        result = ((result* 31)+((this.previousOrderState == null)? 0 :this.previousOrderState.hashCode()));
        result = ((result* 31)+((this.orderType == null)? 0 :this.orderType.hashCode()));
        result = ((result* 31)+((this.paymentStatusInfo == null)? 0 :this.paymentStatusInfo.hashCode()));
        result = ((result* 31)+((this.businessUnit == null)? 0 :this.businessUnit.hashCode()));
        result = ((result* 31)+((this.deliveryType == null)? 0 :this.deliveryType.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.paymentStatus == null)? 0 :this.paymentStatus.hashCode()));
        result = ((result* 31)+((this.orderStateInfo == null)? 0 :this.orderStateInfo.hashCode()));
        result = ((result* 31)+((this.orderState == null)? 0 :this.orderState.hashCode()));
        result = ((result* 31)+((this.paymentType == null)? 0 :this.paymentType.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof CurrentState) == false) {
            return false;
        }
        CurrentState rhs = ((CurrentState) other);
        return (((((((((((this.previousOrderState == rhs.previousOrderState)||((this.previousOrderState!= null)&&this.previousOrderState.equals(rhs.previousOrderState)))&&((this.orderType == rhs.orderType)||((this.orderType!= null)&&this.orderType.equals(rhs.orderType))))&&((this.paymentStatusInfo == rhs.paymentStatusInfo)||((this.paymentStatusInfo!= null)&&this.paymentStatusInfo.equals(rhs.paymentStatusInfo))))&&((this.businessUnit == rhs.businessUnit)||((this.businessUnit!= null)&&this.businessUnit.equals(rhs.businessUnit))))&&((this.deliveryType == rhs.deliveryType)||((this.deliveryType!= null)&&this.deliveryType.equals(rhs.deliveryType))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.paymentStatus == rhs.paymentStatus)||((this.paymentStatus!= null)&&this.paymentStatus.equals(rhs.paymentStatus))))&&((this.orderStateInfo == rhs.orderStateInfo)||((this.orderStateInfo!= null)&&this.orderStateInfo.equals(rhs.orderStateInfo))))&&((this.orderState == rhs.orderState)||((this.orderState!= null)&&this.orderState.equals(rhs.orderState))))&&((this.paymentType == rhs.paymentType)||((this.paymentType!= null)&&this.paymentType.equals(rhs.paymentType))));
    }

}

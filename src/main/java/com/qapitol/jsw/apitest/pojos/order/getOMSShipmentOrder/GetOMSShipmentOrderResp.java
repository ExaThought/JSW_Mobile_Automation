
package com.qapitol.jsw.apitest.pojos.order.getOMSShipmentOrder;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "customerOrderId",
    "customerOrderNumber",
    "customerOrderBillingAddress",
    "customerOrderShippingAddress",
    "orderPaymentType",
    "sellerShipmentsInfo"
})
@Generated("jsonschema2pojo")
public class GetOMSShipmentOrderResp {

    @JsonProperty("customerOrderId")
    private Integer customerOrderId;
    @JsonProperty("customerOrderNumber")
    private String customerOrderNumber;
    @JsonProperty("customerOrderBillingAddress")
    private Object customerOrderBillingAddress;
    @JsonProperty("customerOrderShippingAddress")
    private Object customerOrderShippingAddress;
    @JsonProperty("orderPaymentType")
    private Object orderPaymentType;
    @JsonProperty("sellerShipmentsInfo")
    private List<SellerShipmentsInfo> sellerShipmentsInfo = new ArrayList<SellerShipmentsInfo>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("customerOrderId")
    public Integer getCustomerOrderId() {
        return customerOrderId;
    }

    @JsonProperty("customerOrderId")
    public void setCustomerOrderId(Integer customerOrderId) {
        this.customerOrderId = customerOrderId;
    }

    @JsonProperty("customerOrderNumber")
    public String getCustomerOrderNumber() {
        return customerOrderNumber;
    }

    @JsonProperty("customerOrderNumber")
    public void setCustomerOrderNumber(String customerOrderNumber) {
        this.customerOrderNumber = customerOrderNumber;
    }

    @JsonProperty("customerOrderBillingAddress")
    public Object getCustomerOrderBillingAddress() {
        return customerOrderBillingAddress;
    }

    @JsonProperty("customerOrderBillingAddress")
    public void setCustomerOrderBillingAddress(Object customerOrderBillingAddress) {
        this.customerOrderBillingAddress = customerOrderBillingAddress;
    }

    @JsonProperty("customerOrderShippingAddress")
    public Object getCustomerOrderShippingAddress() {
        return customerOrderShippingAddress;
    }

    @JsonProperty("customerOrderShippingAddress")
    public void setCustomerOrderShippingAddress(Object customerOrderShippingAddress) {
        this.customerOrderShippingAddress = customerOrderShippingAddress;
    }

    @JsonProperty("orderPaymentType")
    public Object getOrderPaymentType() {
        return orderPaymentType;
    }

    @JsonProperty("orderPaymentType")
    public void setOrderPaymentType(Object orderPaymentType) {
        this.orderPaymentType = orderPaymentType;
    }

    @JsonProperty("sellerShipmentsInfo")
    public List<SellerShipmentsInfo> getSellerShipmentsInfo() {
        return sellerShipmentsInfo;
    }

    @JsonProperty("sellerShipmentsInfo")
    public void setSellerShipmentsInfo(List<SellerShipmentsInfo> sellerShipmentsInfo) {
        this.sellerShipmentsInfo = sellerShipmentsInfo;
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
        sb.append(GetOMSShipmentOrderResp.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("customerOrderId");
        sb.append('=');
        sb.append(((this.customerOrderId == null)?"<null>":this.customerOrderId));
        sb.append(',');
        sb.append("customerOrderNumber");
        sb.append('=');
        sb.append(((this.customerOrderNumber == null)?"<null>":this.customerOrderNumber));
        sb.append(',');
        sb.append("customerOrderBillingAddress");
        sb.append('=');
        sb.append(((this.customerOrderBillingAddress == null)?"<null>":this.customerOrderBillingAddress));
        sb.append(',');
        sb.append("customerOrderShippingAddress");
        sb.append('=');
        sb.append(((this.customerOrderShippingAddress == null)?"<null>":this.customerOrderShippingAddress));
        sb.append(',');
        sb.append("orderPaymentType");
        sb.append('=');
        sb.append(((this.orderPaymentType == null)?"<null>":this.orderPaymentType));
        sb.append(',');
        sb.append("sellerShipmentsInfo");
        sb.append('=');
        sb.append(((this.sellerShipmentsInfo == null)?"<null>":this.sellerShipmentsInfo));
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
        result = ((result* 31)+((this.customerOrderId == null)? 0 :this.customerOrderId.hashCode()));
        result = ((result* 31)+((this.customerOrderBillingAddress == null)? 0 :this.customerOrderBillingAddress.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.customerOrderNumber == null)? 0 :this.customerOrderNumber.hashCode()));
        result = ((result* 31)+((this.sellerShipmentsInfo == null)? 0 :this.sellerShipmentsInfo.hashCode()));
        result = ((result* 31)+((this.customerOrderShippingAddress == null)? 0 :this.customerOrderShippingAddress.hashCode()));
        result = ((result* 31)+((this.orderPaymentType == null)? 0 :this.orderPaymentType.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof GetOMSShipmentOrderResp) == false) {
            return false;
        }
        GetOMSShipmentOrderResp rhs = ((GetOMSShipmentOrderResp) other);
        return ((((((((this.customerOrderId == rhs.customerOrderId)||((this.customerOrderId!= null)&&this.customerOrderId.equals(rhs.customerOrderId)))&&((this.customerOrderBillingAddress == rhs.customerOrderBillingAddress)||((this.customerOrderBillingAddress!= null)&&this.customerOrderBillingAddress.equals(rhs.customerOrderBillingAddress))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.customerOrderNumber == rhs.customerOrderNumber)||((this.customerOrderNumber!= null)&&this.customerOrderNumber.equals(rhs.customerOrderNumber))))&&((this.sellerShipmentsInfo == rhs.sellerShipmentsInfo)||((this.sellerShipmentsInfo!= null)&&this.sellerShipmentsInfo.equals(rhs.sellerShipmentsInfo))))&&((this.customerOrderShippingAddress == rhs.customerOrderShippingAddress)||((this.customerOrderShippingAddress!= null)&&this.customerOrderShippingAddress.equals(rhs.customerOrderShippingAddress))))&&((this.orderPaymentType == rhs.orderPaymentType)||((this.orderPaymentType!= null)&&this.orderPaymentType.equals(rhs.orderPaymentType))));
    }

}

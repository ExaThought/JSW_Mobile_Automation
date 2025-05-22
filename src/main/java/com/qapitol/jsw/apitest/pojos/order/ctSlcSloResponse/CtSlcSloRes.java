
package com.qapitol.jsw.apitest.pojos.order.ctSlcSloResponse;

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
    "customerId",
    "cartId",
    "cartVersion",
    "price",
    "taxes",
    "deliveryCharge",
    "otherCharges",
    "subTotal",
    "amountDue",
    "orderId",
    "orderNumber"
})
@Generated("jsonschema2pojo")
public class CtSlcSloRes {

    @JsonProperty("customerId")
    private String customerId;
    @JsonProperty("cartId")
    private String cartId;
    @JsonProperty("cartVersion")
    private Integer cartVersion;
    @JsonProperty("price")
    private String price;
    @JsonProperty("taxes")
    private String taxes;
    @JsonProperty("deliveryCharge")
    private String deliveryCharge;
    @JsonProperty("otherCharges")
    private Object otherCharges;
    @JsonProperty("subTotal")
    private String subTotal;
    @JsonProperty("amountDue")
    private String amountDue;
    @JsonProperty("orderId")
    private Object orderId;
    @JsonProperty("orderNumber")
    private Object orderNumber;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("customerId")
    public String getCustomerId() {
        return customerId;
    }

    @JsonProperty("customerId")
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @JsonProperty("cartId")
    public String getCartId() {
        return cartId;
    }

    @JsonProperty("cartId")
    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    @JsonProperty("cartVersion")
    public Integer getCartVersion() {
        return cartVersion;
    }

    @JsonProperty("cartVersion")
    public void setCartVersion(Integer cartVersion) {
        this.cartVersion = cartVersion;
    }

    @JsonProperty("price")
    public String getPrice() {
        return price;
    }

    @JsonProperty("price")
    public void setPrice(String price) {
        this.price = price;
    }

    @JsonProperty("taxes")
    public String getTaxes() {
        return taxes;
    }

    @JsonProperty("taxes")
    public void setTaxes(String taxes) {
        this.taxes = taxes;
    }

    @JsonProperty("deliveryCharge")
    public String getDeliveryCharge() {
        return deliveryCharge;
    }

    @JsonProperty("deliveryCharge")
    public void setDeliveryCharge(String deliveryCharge) {
        this.deliveryCharge = deliveryCharge;
    }

    @JsonProperty("otherCharges")
    public Object getOtherCharges() {
        return otherCharges;
    }

    @JsonProperty("otherCharges")
    public void setOtherCharges(Object otherCharges) {
        this.otherCharges = otherCharges;
    }

    @JsonProperty("subTotal")
    public String getSubTotal() {
        return subTotal;
    }

    @JsonProperty("subTotal")
    public void setSubTotal(String subTotal) {
        this.subTotal = subTotal;
    }

    @JsonProperty("amountDue")
    public String getAmountDue() {
        return amountDue;
    }

    @JsonProperty("amountDue")
    public void setAmountDue(String amountDue) {
        this.amountDue = amountDue;
    }

    @JsonProperty("orderId")
    public Object getOrderId() {
        return orderId;
    }

    @JsonProperty("orderId")
    public void setOrderId(Object orderId) {
        this.orderId = orderId;
    }

    @JsonProperty("orderNumber")
    public Object getOrderNumber() {
        return orderNumber;
    }

    @JsonProperty("orderNumber")
    public void setOrderNumber(Object orderNumber) {
        this.orderNumber = orderNumber;
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
        sb.append(CtSlcSloRes.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("customerId");
        sb.append('=');
        sb.append(((this.customerId == null)?"<null>":this.customerId));
        sb.append(',');
        sb.append("cartId");
        sb.append('=');
        sb.append(((this.cartId == null)?"<null>":this.cartId));
        sb.append(',');
        sb.append("cartVersion");
        sb.append('=');
        sb.append(((this.cartVersion == null)?"<null>":this.cartVersion));
        sb.append(',');
        sb.append("price");
        sb.append('=');
        sb.append(((this.price == null)?"<null>":this.price));
        sb.append(',');
        sb.append("taxes");
        sb.append('=');
        sb.append(((this.taxes == null)?"<null>":this.taxes));
        sb.append(',');
        sb.append("deliveryCharge");
        sb.append('=');
        sb.append(((this.deliveryCharge == null)?"<null>":this.deliveryCharge));
        sb.append(',');
        sb.append("otherCharges");
        sb.append('=');
        sb.append(((this.otherCharges == null)?"<null>":this.otherCharges));
        sb.append(',');
        sb.append("subTotal");
        sb.append('=');
        sb.append(((this.subTotal == null)?"<null>":this.subTotal));
        sb.append(',');
        sb.append("amountDue");
        sb.append('=');
        sb.append(((this.amountDue == null)?"<null>":this.amountDue));
        sb.append(',');
        sb.append("orderId");
        sb.append('=');
        sb.append(((this.orderId == null)?"<null>":this.orderId));
        sb.append(',');
        sb.append("orderNumber");
        sb.append('=');
        sb.append(((this.orderNumber == null)?"<null>":this.orderNumber));
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
        result = ((result* 31)+((this.amountDue == null)? 0 :this.amountDue.hashCode()));
        result = ((result* 31)+((this.orderNumber == null)? 0 :this.orderNumber.hashCode()));
        result = ((result* 31)+((this.orderId == null)? 0 :this.orderId.hashCode()));
        result = ((result* 31)+((this.price == null)? 0 :this.price.hashCode()));
        result = ((result* 31)+((this.otherCharges == null)? 0 :this.otherCharges.hashCode()));
        result = ((result* 31)+((this.cartId == null)? 0 :this.cartId.hashCode()));
        result = ((result* 31)+((this.customerId == null)? 0 :this.customerId.hashCode()));
        result = ((result* 31)+((this.taxes == null)? 0 :this.taxes.hashCode()));
        result = ((result* 31)+((this.deliveryCharge == null)? 0 :this.deliveryCharge.hashCode()));
        result = ((result* 31)+((this.subTotal == null)? 0 :this.subTotal.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.cartVersion == null)? 0 :this.cartVersion.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof CtSlcSloRes) == false) {
            return false;
        }
        CtSlcSloRes rhs = ((CtSlcSloRes) other);
        return (((((((((((((this.amountDue == rhs.amountDue)||((this.amountDue!= null)&&this.amountDue.equals(rhs.amountDue)))&&((this.orderNumber == rhs.orderNumber)||((this.orderNumber!= null)&&this.orderNumber.equals(rhs.orderNumber))))&&((this.orderId == rhs.orderId)||((this.orderId!= null)&&this.orderId.equals(rhs.orderId))))&&((this.price == rhs.price)||((this.price!= null)&&this.price.equals(rhs.price))))&&((this.otherCharges == rhs.otherCharges)||((this.otherCharges!= null)&&this.otherCharges.equals(rhs.otherCharges))))&&((this.cartId == rhs.cartId)||((this.cartId!= null)&&this.cartId.equals(rhs.cartId))))&&((this.customerId == rhs.customerId)||((this.customerId!= null)&&this.customerId.equals(rhs.customerId))))&&((this.taxes == rhs.taxes)||((this.taxes!= null)&&this.taxes.equals(rhs.taxes))))&&((this.deliveryCharge == rhs.deliveryCharge)||((this.deliveryCharge!= null)&&this.deliveryCharge.equals(rhs.deliveryCharge))))&&((this.subTotal == rhs.subTotal)||((this.subTotal!= null)&&this.subTotal.equals(rhs.subTotal))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.cartVersion == rhs.cartVersion)||((this.cartVersion!= null)&&this.cartVersion.equals(rhs.cartVersion))));
    }

}

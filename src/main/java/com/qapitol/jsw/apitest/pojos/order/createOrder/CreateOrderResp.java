
package com.qapitol.jsw.apitest.pojos.order.createOrder;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

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
public class CreateOrderResp {

    @JsonProperty("customerId")
    private Object customerId;
    @JsonProperty("cartId")
    private String cartId;
    @JsonProperty("cartVersion")
    private Object cartVersion;
    @JsonProperty("price")
    private Object price;
    @JsonProperty("taxes")
    private Object taxes;
    @JsonProperty("deliveryCharge")
    private Object deliveryCharge;
    @JsonProperty("otherCharges")
    private Object otherCharges;
    @JsonProperty("subTotal")
    private Object subTotal;
    @JsonProperty("amountDue")
    private Object amountDue;
    @JsonProperty("orderId")
    private String orderId;
    @JsonProperty("orderNumber")
    private String orderNumber;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("customerId")
    public Object getCustomerId() {
        return customerId;
    }

    @JsonProperty("customerId")
    public void setCustomerId(Object customerId) {
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
    public Object getCartVersion() {
        return cartVersion;
    }

    @JsonProperty("cartVersion")
    public void setCartVersion(Object cartVersion) {
        this.cartVersion = cartVersion;
    }

    @JsonProperty("price")
    public Object getPrice() {
        return price;
    }

    @JsonProperty("price")
    public void setPrice(Object price) {
        this.price = price;
    }

    @JsonProperty("taxes")
    public Object getTaxes() {
        return taxes;
    }

    @JsonProperty("taxes")
    public void setTaxes(Object taxes) {
        this.taxes = taxes;
    }

    @JsonProperty("deliveryCharge")
    public Object getDeliveryCharge() {
        return deliveryCharge;
    }

    @JsonProperty("deliveryCharge")
    public void setDeliveryCharge(Object deliveryCharge) {
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
    public Object getSubTotal() {
        return subTotal;
    }

    @JsonProperty("subTotal")
    public void setSubTotal(Object subTotal) {
        this.subTotal = subTotal;
    }

    @JsonProperty("amountDue")
    public Object getAmountDue() {
        return amountDue;
    }

    @JsonProperty("amountDue")
    public void setAmountDue(Object amountDue) {
        this.amountDue = amountDue;
    }

    @JsonProperty("orderId")
    public String getOrderId() {
        return orderId;
    }

    @JsonProperty("orderId")
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @JsonProperty("orderNumber")
    public String getOrderNumber() {
        return orderNumber;
    }

    @JsonProperty("orderNumber")
    public void setOrderNumber(String orderNumber) {
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
        sb.append(CreateOrderResp.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
        result = ((result* 31)+((this.orderNumber == null)? 0 :this.orderNumber.hashCode()));
        result = ((result* 31)+((this.orderId == null)? 0 :this.orderId.hashCode()));
        result = ((result* 31)+((this.otherCharges == null)? 0 :this.otherCharges.hashCode()));
        result = ((result* 31)+((this.cartId == null)? 0 :this.cartId.hashCode()));
        result = ((result* 31)+((this.taxes == null)? 0 :this.taxes.hashCode()));
        result = ((result* 31)+((this.deliveryCharge == null)? 0 :this.deliveryCharge.hashCode()));
        result = ((result* 31)+((this.subTotal == null)? 0 :this.subTotal.hashCode()));
        result = ((result* 31)+((this.cartVersion == null)? 0 :this.cartVersion.hashCode()));
        result = ((result* 31)+((this.amountDue == null)? 0 :this.amountDue.hashCode()));
        result = ((result* 31)+((this.price == null)? 0 :this.price.hashCode()));
        result = ((result* 31)+((this.customerId == null)? 0 :this.customerId.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof CreateOrderResp) == false) {
            return false;
        }
        CreateOrderResp rhs = ((CreateOrderResp) other);
        return (((((((((((((this.orderNumber == rhs.orderNumber)||((this.orderNumber!= null)&&this.orderNumber.equals(rhs.orderNumber)))&&((this.orderId == rhs.orderId)||((this.orderId!= null)&&this.orderId.equals(rhs.orderId))))&&((this.otherCharges == rhs.otherCharges)||((this.otherCharges!= null)&&this.otherCharges.equals(rhs.otherCharges))))&&((this.cartId == rhs.cartId)||((this.cartId!= null)&&this.cartId.equals(rhs.cartId))))&&((this.taxes == rhs.taxes)||((this.taxes!= null)&&this.taxes.equals(rhs.taxes))))&&((this.deliveryCharge == rhs.deliveryCharge)||((this.deliveryCharge!= null)&&this.deliveryCharge.equals(rhs.deliveryCharge))))&&((this.subTotal == rhs.subTotal)||((this.subTotal!= null)&&this.subTotal.equals(rhs.subTotal))))&&((this.cartVersion == rhs.cartVersion)||((this.cartVersion!= null)&&this.cartVersion.equals(rhs.cartVersion))))&&((this.amountDue == rhs.amountDue)||((this.amountDue!= null)&&this.amountDue.equals(rhs.amountDue))))&&((this.price == rhs.price)||((this.price!= null)&&this.price.equals(rhs.price))))&&((this.customerId == rhs.customerId)||((this.customerId!= null)&&this.customerId.equals(rhs.customerId))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
    }

}

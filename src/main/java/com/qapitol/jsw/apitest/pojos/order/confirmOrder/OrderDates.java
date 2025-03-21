
package com.qapitol.jsw.apitest.pojos.order.confirmOrder;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "orderCreatedDate",
    "orderPlacedDate",
    "committedDeliveryDate",
    "scheduledDeliveryDate",
    "nextActionDate",
    "paymentCompletionDate",
    "acceptedDate",
    "rejectedDate",
    "orderConfirmationDate"
})
@Generated("jsonschema2pojo")
public class OrderDates {

    @JsonProperty("orderCreatedDate")
    private String orderCreatedDate;
    @JsonProperty("orderPlacedDate")
    private String orderPlacedDate;
    @JsonProperty("committedDeliveryDate")
    private Object committedDeliveryDate;
    @JsonProperty("scheduledDeliveryDate")
    private String scheduledDeliveryDate;
    @JsonProperty("nextActionDate")
    private Object nextActionDate;
    @JsonProperty("paymentCompletionDate")
    private Object paymentCompletionDate;
    @JsonProperty("acceptedDate")
    private Object acceptedDate;
    @JsonProperty("rejectedDate")
    private Object rejectedDate;
    @JsonProperty("orderConfirmationDate")
    private String orderConfirmationDate;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("orderCreatedDate")
    public String getOrderCreatedDate() {
        return orderCreatedDate;
    }

    @JsonProperty("orderCreatedDate")
    public void setOrderCreatedDate(String orderCreatedDate) {
        this.orderCreatedDate = orderCreatedDate;
    }

    @JsonProperty("orderPlacedDate")
    public String getOrderPlacedDate() {
        return orderPlacedDate;
    }

    @JsonProperty("orderPlacedDate")
    public void setOrderPlacedDate(String orderPlacedDate) {
        this.orderPlacedDate = orderPlacedDate;
    }

    @JsonProperty("committedDeliveryDate")
    public Object getCommittedDeliveryDate() {
        return committedDeliveryDate;
    }

    @JsonProperty("committedDeliveryDate")
    public void setCommittedDeliveryDate(Object committedDeliveryDate) {
        this.committedDeliveryDate = committedDeliveryDate;
    }

    @JsonProperty("scheduledDeliveryDate")
    public String getScheduledDeliveryDate() {
        return scheduledDeliveryDate;
    }

    @JsonProperty("scheduledDeliveryDate")
    public void setScheduledDeliveryDate(String scheduledDeliveryDate) {
        this.scheduledDeliveryDate = scheduledDeliveryDate;
    }

    @JsonProperty("nextActionDate")
    public Object getNextActionDate() {
        return nextActionDate;
    }

    @JsonProperty("nextActionDate")
    public void setNextActionDate(Object nextActionDate) {
        this.nextActionDate = nextActionDate;
    }

    @JsonProperty("paymentCompletionDate")
    public Object getPaymentCompletionDate() {
        return paymentCompletionDate;
    }

    @JsonProperty("paymentCompletionDate")
    public void setPaymentCompletionDate(Object paymentCompletionDate) {
        this.paymentCompletionDate = paymentCompletionDate;
    }

    @JsonProperty("acceptedDate")
    public Object getAcceptedDate() {
        return acceptedDate;
    }

    @JsonProperty("acceptedDate")
    public void setAcceptedDate(Object acceptedDate) {
        this.acceptedDate = acceptedDate;
    }

    @JsonProperty("rejectedDate")
    public Object getRejectedDate() {
        return rejectedDate;
    }

    @JsonProperty("rejectedDate")
    public void setRejectedDate(Object rejectedDate) {
        this.rejectedDate = rejectedDate;
    }

    @JsonProperty("orderConfirmationDate")
    public String getOrderConfirmationDate() {
        return orderConfirmationDate;
    }

    @JsonProperty("orderConfirmationDate")
    public void setOrderConfirmationDate(String orderConfirmationDate) {
        this.orderConfirmationDate = orderConfirmationDate;
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
        sb.append(OrderDates.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("orderCreatedDate");
        sb.append('=');
        sb.append(((this.orderCreatedDate == null)?"<null>":this.orderCreatedDate));
        sb.append(',');
        sb.append("orderPlacedDate");
        sb.append('=');
        sb.append(((this.orderPlacedDate == null)?"<null>":this.orderPlacedDate));
        sb.append(',');
        sb.append("committedDeliveryDate");
        sb.append('=');
        sb.append(((this.committedDeliveryDate == null)?"<null>":this.committedDeliveryDate));
        sb.append(',');
        sb.append("scheduledDeliveryDate");
        sb.append('=');
        sb.append(((this.scheduledDeliveryDate == null)?"<null>":this.scheduledDeliveryDate));
        sb.append(',');
        sb.append("nextActionDate");
        sb.append('=');
        sb.append(((this.nextActionDate == null)?"<null>":this.nextActionDate));
        sb.append(',');
        sb.append("paymentCompletionDate");
        sb.append('=');
        sb.append(((this.paymentCompletionDate == null)?"<null>":this.paymentCompletionDate));
        sb.append(',');
        sb.append("acceptedDate");
        sb.append('=');
        sb.append(((this.acceptedDate == null)?"<null>":this.acceptedDate));
        sb.append(',');
        sb.append("rejectedDate");
        sb.append('=');
        sb.append(((this.rejectedDate == null)?"<null>":this.rejectedDate));
        sb.append(',');
        sb.append("orderConfirmationDate");
        sb.append('=');
        sb.append(((this.orderConfirmationDate == null)?"<null>":this.orderConfirmationDate));
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
        result = ((result* 31)+((this.orderPlacedDate == null)? 0 :this.orderPlacedDate.hashCode()));
        result = ((result* 31)+((this.nextActionDate == null)? 0 :this.nextActionDate.hashCode()));
        result = ((result* 31)+((this.paymentCompletionDate == null)? 0 :this.paymentCompletionDate.hashCode()));
        result = ((result* 31)+((this.orderCreatedDate == null)? 0 :this.orderCreatedDate.hashCode()));
        result = ((result* 31)+((this.committedDeliveryDate == null)? 0 :this.committedDeliveryDate.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.orderConfirmationDate == null)? 0 :this.orderConfirmationDate.hashCode()));
        result = ((result* 31)+((this.acceptedDate == null)? 0 :this.acceptedDate.hashCode()));
        result = ((result* 31)+((this.rejectedDate == null)? 0 :this.rejectedDate.hashCode()));
        result = ((result* 31)+((this.scheduledDeliveryDate == null)? 0 :this.scheduledDeliveryDate.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof OrderDates) == false) {
            return false;
        }
        OrderDates rhs = ((OrderDates) other);
        return (((((((((((this.orderPlacedDate == rhs.orderPlacedDate)||((this.orderPlacedDate!= null)&&this.orderPlacedDate.equals(rhs.orderPlacedDate)))&&((this.nextActionDate == rhs.nextActionDate)||((this.nextActionDate!= null)&&this.nextActionDate.equals(rhs.nextActionDate))))&&((this.paymentCompletionDate == rhs.paymentCompletionDate)||((this.paymentCompletionDate!= null)&&this.paymentCompletionDate.equals(rhs.paymentCompletionDate))))&&((this.orderCreatedDate == rhs.orderCreatedDate)||((this.orderCreatedDate!= null)&&this.orderCreatedDate.equals(rhs.orderCreatedDate))))&&((this.committedDeliveryDate == rhs.committedDeliveryDate)||((this.committedDeliveryDate!= null)&&this.committedDeliveryDate.equals(rhs.committedDeliveryDate))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.orderConfirmationDate == rhs.orderConfirmationDate)||((this.orderConfirmationDate!= null)&&this.orderConfirmationDate.equals(rhs.orderConfirmationDate))))&&((this.acceptedDate == rhs.acceptedDate)||((this.acceptedDate!= null)&&this.acceptedDate.equals(rhs.acceptedDate))))&&((this.rejectedDate == rhs.rejectedDate)||((this.rejectedDate!= null)&&this.rejectedDate.equals(rhs.rejectedDate))))&&((this.scheduledDeliveryDate == rhs.scheduledDeliveryDate)||((this.scheduledDeliveryDate!= null)&&this.scheduledDeliveryDate.equals(rhs.scheduledDeliveryDate))));
    }

}


package com.qapitol.jsw.apitest.pojos.order.rcv2Response;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "delivery",
    "other",
    "discountedDelivery"
})
@Generated("jsonschema2pojo")
public class Charges {

    @JsonProperty("delivery")
    private Delivery delivery;
    @JsonProperty("other")
    private Other other;
    @JsonProperty("discountedDelivery")
    private DiscountedDelivery discountedDelivery;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("delivery")
    public Delivery getDelivery() {
        return delivery;
    }

    @JsonProperty("delivery")
    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    @JsonProperty("other")
    public Other getOther() {
        return other;
    }

    @JsonProperty("other")
    public void setOther(Other other) {
        this.other = other;
    }

    @JsonProperty("discountedDelivery")
    public DiscountedDelivery getDiscountedDelivery() {
        return discountedDelivery;
    }

    @JsonProperty("discountedDelivery")
    public void setDiscountedDelivery(DiscountedDelivery discountedDelivery) {
        this.discountedDelivery = discountedDelivery;
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
        sb.append(Charges.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("delivery");
        sb.append('=');
        sb.append(((this.delivery == null)?"<null>":this.delivery));
        sb.append(',');
        sb.append("other");
        sb.append('=');
        sb.append(((this.other == null)?"<null>":this.other));
        sb.append(',');
        sb.append("discountedDelivery");
        sb.append('=');
        sb.append(((this.discountedDelivery == null)?"<null>":this.discountedDelivery));
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
        result = ((result* 31)+((this.delivery == null)? 0 :this.delivery.hashCode()));
        result = ((result* 31)+((this.other == null)? 0 :this.other.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.discountedDelivery == null)? 0 :this.discountedDelivery.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Charges) == false) {
            return false;
        }
        Charges rhs = ((Charges) other);
        return (((((this.delivery == rhs.delivery)||((this.delivery!= null)&&this.delivery.equals(rhs.delivery)))&&((this.other == rhs.other)||((this.other!= null)&&this.other.equals(rhs.other))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.discountedDelivery == rhs.discountedDelivery)||((this.discountedDelivery!= null)&&this.discountedDelivery.equals(rhs.discountedDelivery))));
    }

}

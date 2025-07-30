
package com.qapitol.jsw.apitest.pojos.order.rcv2Response;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "sellerDiscount",
    "platformDiscount"
})
@Generated("jsonschema2pojo")
public class Discounts {

    @JsonProperty("sellerDiscount")
    private SellerDiscount sellerDiscount;
    @JsonProperty("platformDiscount")
    private PlatformDiscount platformDiscount;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("sellerDiscount")
    public SellerDiscount getSellerDiscount() {
        return sellerDiscount;
    }

    @JsonProperty("sellerDiscount")
    public void setSellerDiscount(SellerDiscount sellerDiscount) {
        this.sellerDiscount = sellerDiscount;
    }

    @JsonProperty("platformDiscount")
    public PlatformDiscount getPlatformDiscount() {
        return platformDiscount;
    }

    @JsonProperty("platformDiscount")
    public void setPlatformDiscount(PlatformDiscount platformDiscount) {
        this.platformDiscount = platformDiscount;
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
        sb.append(Discounts.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("sellerDiscount");
        sb.append('=');
        sb.append(((this.sellerDiscount == null)?"<null>":this.sellerDiscount));
        sb.append(',');
        sb.append("platformDiscount");
        sb.append('=');
        sb.append(((this.platformDiscount == null)?"<null>":this.platformDiscount));
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
        result = ((result* 31)+((this.platformDiscount == null)? 0 :this.platformDiscount.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.sellerDiscount == null)? 0 :this.sellerDiscount.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Discounts) == false) {
            return false;
        }
        Discounts rhs = ((Discounts) other);
        return ((((this.platformDiscount == rhs.platformDiscount)||((this.platformDiscount!= null)&&this.platformDiscount.equals(rhs.platformDiscount)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.sellerDiscount == rhs.sellerDiscount)||((this.sellerDiscount!= null)&&this.sellerDiscount.equals(rhs.sellerDiscount))));
    }

}

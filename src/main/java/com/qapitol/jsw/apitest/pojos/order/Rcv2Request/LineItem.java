
package com.qapitol.jsw.apitest.pojos.order.Rcv2Request;

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
    "product_mmid",
    "ct_attributes",
    "purchased_quantity"
})
@Generated("jsonschema2pojo")
public class LineItem {

    @JsonProperty("product_mmid")
    private String productMmid;
    @JsonProperty("ct_attributes")
    private List<CtAttribute> ctAttributes = new ArrayList<CtAttribute>();
    @JsonProperty("purchased_quantity")
    private PurchasedQuantity purchasedQuantity;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("product_mmid")
    public String getProductMmid() {
        return productMmid;
    }

    @JsonProperty("product_mmid")
    public void setProductMmid(String productMmid) {
        this.productMmid = productMmid;
    }

    @JsonProperty("ct_attributes")
    public List<CtAttribute> getCtAttributes() {
        return ctAttributes;
    }

    @JsonProperty("ct_attributes")
    public void setCtAttributes(List<CtAttribute> ctAttributes) {
        this.ctAttributes = ctAttributes;
    }

    @JsonProperty("purchased_quantity")
    public PurchasedQuantity getPurchasedQuantity() {
        return purchasedQuantity;
    }

    @JsonProperty("purchased_quantity")
    public void setPurchasedQuantity(PurchasedQuantity purchasedQuantity) {
        this.purchasedQuantity = purchasedQuantity;
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
        sb.append(LineItem.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("productMmid");
        sb.append('=');
        sb.append(((this.productMmid == null)?"<null>":this.productMmid));
        sb.append(',');
        sb.append("ctAttributes");
        sb.append('=');
        sb.append(((this.ctAttributes == null)?"<null>":this.ctAttributes));
        sb.append(',');
        sb.append("purchasedQuantity");
        sb.append('=');
        sb.append(((this.purchasedQuantity == null)?"<null>":this.purchasedQuantity));
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
        result = ((result* 31)+((this.purchasedQuantity == null)? 0 :this.purchasedQuantity.hashCode()));
        result = ((result* 31)+((this.ctAttributes == null)? 0 :this.ctAttributes.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.productMmid == null)? 0 :this.productMmid.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof LineItem) == false) {
            return false;
        }
        LineItem rhs = ((LineItem) other);
        return (((((this.purchasedQuantity == rhs.purchasedQuantity)||((this.purchasedQuantity!= null)&&this.purchasedQuantity.equals(rhs.purchasedQuantity)))&&((this.ctAttributes == rhs.ctAttributes)||((this.ctAttributes!= null)&&this.ctAttributes.equals(rhs.ctAttributes))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.productMmid == rhs.productMmid)||((this.productMmid!= null)&&this.productMmid.equals(rhs.productMmid))));
    }

}

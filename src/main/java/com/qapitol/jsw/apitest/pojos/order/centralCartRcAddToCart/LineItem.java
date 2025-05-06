
package com.qapitol.jsw.apitest.pojos.order.centralCartRcAddToCart;

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
    "attributes",
    "purchased_quantity"
})
@Generated("jsonschema2pojo")
public class LineItem {

    @JsonProperty("product_mmid")
    private String productMmid;
    @JsonProperty("attributes")
    private List<Attribute> attributes = new ArrayList<Attribute>();
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

    @JsonProperty("attributes")
    public List<Attribute> getAttributes() {
        return attributes;
    }

    @JsonProperty("attributes")
    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
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
        sb.append("attributes");
        sb.append('=');
        sb.append(((this.attributes == null)?"<null>":this.attributes));
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
        result = ((result* 31)+((this.attributes == null)? 0 :this.attributes.hashCode()));
        result = ((result* 31)+((this.purchasedQuantity == null)? 0 :this.purchasedQuantity.hashCode()));
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
        return (((((this.attributes == rhs.attributes)||((this.attributes!= null)&&this.attributes.equals(rhs.attributes)))&&((this.purchasedQuantity == rhs.purchasedQuantity)||((this.purchasedQuantity!= null)&&this.purchasedQuantity.equals(rhs.purchasedQuantity))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.productMmid == rhs.productMmid)||((this.productMmid!= null)&&this.productMmid.equals(rhs.productMmid))));
    }

}

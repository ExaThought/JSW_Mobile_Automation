
package com.qapitol.jsw.apitest.pojos.order.rcv2AddToCartRequestPayload;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "productKey",
    "variantKey",
    "sku",
    "source",
    "variantMMId",
    "productMMId",
    "productTypeKey",
    "attributeData"
})
@Generated("jsonschema2pojo")
public class LineItem {

    @JsonProperty("productKey")
    private String productKey;
    @JsonProperty("variantKey")
    private String variantKey;
    @JsonProperty("sku")
    private String sku;
    @JsonProperty("source")
    private String source;
    @JsonProperty("variantMMId")
    private String variantMMId;
    @JsonProperty("productMMId")
    private String productMMId;
    @JsonProperty("productTypeKey")
    private String productTypeKey;
    @JsonProperty("attributeData")
    private AttributeData attributeData;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("productKey")
    public String getProductKey() {
        return productKey;
    }

    @JsonProperty("productKey")
    public void setProductKey(String productKey) {
        this.productKey = productKey;
    }

    @JsonProperty("variantKey")
    public String getVariantKey() {
        return variantKey;
    }

    @JsonProperty("variantKey")
    public void setVariantKey(String variantKey) {
        this.variantKey = variantKey;
    }

    @JsonProperty("sku")
    public String getSku() {
        return sku;
    }

    @JsonProperty("sku")
    public void setSku(String sku) {
        this.sku = sku;
    }

    @JsonProperty("source")
    public String getSource() {
        return source;
    }

    @JsonProperty("source")
    public void setSource(String source) {
        this.source = source;
    }

    @JsonProperty("variantMMId")
    public String getVariantMMId() {
        return variantMMId;
    }

    @JsonProperty("variantMMId")
    public void setVariantMMId(String variantMMId) {
        this.variantMMId = variantMMId;
    }

    @JsonProperty("productMMId")
    public String getProductMMId() {
        return productMMId;
    }

    @JsonProperty("productMMId")
    public void setProductMMId(String productMMId) {
        this.productMMId = productMMId;
    }

    @JsonProperty("productTypeKey")
    public String getProductTypeKey() {
        return productTypeKey;
    }

    @JsonProperty("productTypeKey")
    public void setProductTypeKey(String productTypeKey) {
        this.productTypeKey = productTypeKey;
    }

    @JsonProperty("attributeData")
    public AttributeData getAttributeData() {
        return attributeData;
    }

    @JsonProperty("attributeData")
    public void setAttributeData(AttributeData attributeData) {
        this.attributeData = attributeData;
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
        sb.append("productKey");
        sb.append('=');
        sb.append(((this.productKey == null)?"<null>":this.productKey));
        sb.append(',');
        sb.append("variantKey");
        sb.append('=');
        sb.append(((this.variantKey == null)?"<null>":this.variantKey));
        sb.append(',');
        sb.append("sku");
        sb.append('=');
        sb.append(((this.sku == null)?"<null>":this.sku));
        sb.append(',');
        sb.append("source");
        sb.append('=');
        sb.append(((this.source == null)?"<null>":this.source));
        sb.append(',');
        sb.append("variantMMId");
        sb.append('=');
        sb.append(((this.variantMMId == null)?"<null>":this.variantMMId));
        sb.append(',');
        sb.append("productMMId");
        sb.append('=');
        sb.append(((this.productMMId == null)?"<null>":this.productMMId));
        sb.append(',');
        sb.append("productTypeKey");
        sb.append('=');
        sb.append(((this.productTypeKey == null)?"<null>":this.productTypeKey));
        sb.append(',');
        sb.append("attributeData");
        sb.append('=');
        sb.append(((this.attributeData == null)?"<null>":this.attributeData));
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
        result = ((result* 31)+((this.productMMId == null)? 0 :this.productMMId.hashCode()));
        result = ((result* 31)+((this.productTypeKey == null)? 0 :this.productTypeKey.hashCode()));
        result = ((result* 31)+((this.source == null)? 0 :this.source.hashCode()));
        result = ((result* 31)+((this.variantMMId == null)? 0 :this.variantMMId.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.productKey == null)? 0 :this.productKey.hashCode()));
        result = ((result* 31)+((this.variantKey == null)? 0 :this.variantKey.hashCode()));
        result = ((result* 31)+((this.sku == null)? 0 :this.sku.hashCode()));
        result = ((result* 31)+((this.attributeData == null)? 0 :this.attributeData.hashCode()));
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
        return ((((((((((this.productMMId == rhs.productMMId)||((this.productMMId!= null)&&this.productMMId.equals(rhs.productMMId)))&&((this.productTypeKey == rhs.productTypeKey)||((this.productTypeKey!= null)&&this.productTypeKey.equals(rhs.productTypeKey))))&&((this.source == rhs.source)||((this.source!= null)&&this.source.equals(rhs.source))))&&((this.variantMMId == rhs.variantMMId)||((this.variantMMId!= null)&&this.variantMMId.equals(rhs.variantMMId))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.productKey == rhs.productKey)||((this.productKey!= null)&&this.productKey.equals(rhs.productKey))))&&((this.variantKey == rhs.variantKey)||((this.variantKey!= null)&&this.variantKey.equals(rhs.variantKey))))&&((this.sku == rhs.sku)||((this.sku!= null)&&this.sku.equals(rhs.sku))))&&((this.attributeData == rhs.attributeData)||((this.attributeData!= null)&&this.attributeData.equals(rhs.attributeData))));
    }

}

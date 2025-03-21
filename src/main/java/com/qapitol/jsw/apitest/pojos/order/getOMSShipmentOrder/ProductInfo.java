
package com.qapitol.jsw.apitest.pojos.order.getOMSShipmentOrder;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "sku",
    "name",
    "brand",
    "imageUrl",
    "variantId",
    "attributes",
    "variantKey",
    "pricingMode",
    "productForm",
    "productSlug",
    "productType",
    "primaryQuantity",
    "forFreightCharge",
    "secondaryQuantity",
    "isPortalPriceAvailable"
})
@Generated("jsonschema2pojo")
public class ProductInfo {

    @JsonProperty("sku")
    private String sku;
    @JsonProperty("name")
    private Object name;
    @JsonProperty("brand")
    private String brand;
    @JsonProperty("imageUrl")
    private String imageUrl;
    @JsonProperty("variantId")
    private Integer variantId;
    @JsonProperty("attributes")
    private Map<String, String> attributes;
    @JsonProperty("variantKey")
    private String variantKey;
    @JsonProperty("pricingMode")
    private String pricingMode;
    @JsonProperty("productForm")
    private String productForm;
    @JsonProperty("productSlug")
    private String productSlug;
    @JsonProperty("productType")
    private String productType;
    @JsonProperty("primaryQuantity")
    private String primaryQuantity;
    @JsonProperty("forFreightCharge")
    private Double forFreightCharge;
    @JsonProperty("secondaryQuantity")
    private String secondaryQuantity;
    @JsonProperty("isPortalPriceAvailable")
    private Boolean isPortalPriceAvailable;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("sku")
    public String getSku() {
        return sku;
    }

    @JsonProperty("sku")
    public void setSku(String sku) {
        this.sku = sku;
    }

    @JsonProperty("name")
    public Object getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(Object name) {
        this.name = name;
    }

    @JsonProperty("brand")
    public String getBrand() {
        return brand;
    }

    @JsonProperty("brand")
    public void setBrand(String brand) {
        this.brand = brand;
    }

    @JsonProperty("imageUrl")
    public String getImageUrl() {
        return imageUrl;
    }

    @JsonProperty("imageUrl")
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @JsonProperty("variantId")
    public Integer getVariantId() {
        return variantId;
    }

    @JsonProperty("variantId")
    public void setVariantId(Integer variantId) {
        this.variantId = variantId;
    }

    @JsonProperty("attributes")
    public Map<String, String> getAttributes() {
        return attributes;
    }

    @JsonProperty("attributes")
    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    @JsonProperty("variantKey")
    public String getVariantKey() {
        return variantKey;
    }

    @JsonProperty("variantKey")
    public void setVariantKey(String variantKey) {
        this.variantKey = variantKey;
    }

    @JsonProperty("pricingMode")
    public String getPricingMode() {
        return pricingMode;
    }

    @JsonProperty("pricingMode")
    public void setPricingMode(String pricingMode) {
        this.pricingMode = pricingMode;
    }

    @JsonProperty("productForm")
    public String getProductForm() {
        return productForm;
    }

    @JsonProperty("productForm")
    public void setProductForm(String productForm) {
        this.productForm = productForm;
    }

    @JsonProperty("productSlug")
    public String getProductSlug() {
        return productSlug;
    }

    @JsonProperty("productSlug")
    public void setProductSlug(String productSlug) {
        this.productSlug = productSlug;
    }

    @JsonProperty("productType")
    public String getProductType() {
        return productType;
    }

    @JsonProperty("productType")
    public void setProductType(String productType) {
        this.productType = productType;
    }

    @JsonProperty("primaryQuantity")
    public String getPrimaryQuantity() {
        return primaryQuantity;
    }

    @JsonProperty("primaryQuantity")
    public void setPrimaryQuantity(String primaryQuantity) {
        this.primaryQuantity = primaryQuantity;
    }

    @JsonProperty("forFreightCharge")
    public Double getForFreightCharge() {
        return forFreightCharge;
    }

    @JsonProperty("forFreightCharge")
    public void setForFreightCharge(Double forFreightCharge) {
        this.forFreightCharge = forFreightCharge;
    }

    @JsonProperty("secondaryQuantity")
    public String getSecondaryQuantity() {
        return secondaryQuantity;
    }

    @JsonProperty("secondaryQuantity")
    public void setSecondaryQuantity(String secondaryQuantity) {
        this.secondaryQuantity = secondaryQuantity;
    }

    @JsonProperty("isPortalPriceAvailable")
    public Boolean getIsPortalPriceAvailable() {
        return isPortalPriceAvailable;
    }

    @JsonProperty("isPortalPriceAvailable")
    public void setIsPortalPriceAvailable(Boolean isPortalPriceAvailable) {
        this.isPortalPriceAvailable = isPortalPriceAvailable;
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
        sb.append(ProductInfo.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("sku");
        sb.append('=');
        sb.append(((this.sku == null)?"<null>":this.sku));
        sb.append(',');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null)?"<null>":this.name));
        sb.append(',');
        sb.append("brand");
        sb.append('=');
        sb.append(((this.brand == null)?"<null>":this.brand));
        sb.append(',');
        sb.append("imageUrl");
        sb.append('=');
        sb.append(((this.imageUrl == null)?"<null>":this.imageUrl));
        sb.append(',');
        sb.append("variantId");
        sb.append('=');
        sb.append(((this.variantId == null)?"<null>":this.variantId));
        sb.append(',');
        sb.append("attributes");
        sb.append('=');
        sb.append(((this.attributes == null)?"<null>":this.attributes));
        sb.append(',');
        sb.append("variantKey");
        sb.append('=');
        sb.append(((this.variantKey == null)?"<null>":this.variantKey));
        sb.append(',');
        sb.append("pricingMode");
        sb.append('=');
        sb.append(((this.pricingMode == null)?"<null>":this.pricingMode));
        sb.append(',');
        sb.append("productForm");
        sb.append('=');
        sb.append(((this.productForm == null)?"<null>":this.productForm));
        sb.append(',');
        sb.append("productSlug");
        sb.append('=');
        sb.append(((this.productSlug == null)?"<null>":this.productSlug));
        sb.append(',');
        sb.append("productType");
        sb.append('=');
        sb.append(((this.productType == null)?"<null>":this.productType));
        sb.append(',');
        sb.append("primaryQuantity");
        sb.append('=');
        sb.append(((this.primaryQuantity == null)?"<null>":this.primaryQuantity));
        sb.append(',');
        sb.append("forFreightCharge");
        sb.append('=');
        sb.append(((this.forFreightCharge == null)?"<null>":this.forFreightCharge));
        sb.append(',');
        sb.append("secondaryQuantity");
        sb.append('=');
        sb.append(((this.secondaryQuantity == null)?"<null>":this.secondaryQuantity));
        sb.append(',');
        sb.append("isPortalPriceAvailable");
        sb.append('=');
        sb.append(((this.isPortalPriceAvailable == null)?"<null>":this.isPortalPriceAvailable));
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
        result = ((result* 31)+((this.productForm == null)? 0 :this.productForm.hashCode()));
        result = ((result* 31)+((this.productSlug == null)? 0 :this.productSlug.hashCode()));
        result = ((result* 31)+((this.forFreightCharge == null)? 0 :this.forFreightCharge.hashCode()));
        result = ((result* 31)+((this.pricingMode == null)? 0 :this.pricingMode.hashCode()));
        result = ((result* 31)+((this.imageUrl == null)? 0 :this.imageUrl.hashCode()));
        result = ((result* 31)+((this.primaryQuantity == null)? 0 :this.primaryQuantity.hashCode()));
        result = ((result* 31)+((this.secondaryQuantity == null)? 0 :this.secondaryQuantity.hashCode()));
        result = ((result* 31)+((this.name == null)? 0 :this.name.hashCode()));
        result = ((result* 31)+((this.attributes == null)? 0 :this.attributes.hashCode()));
        result = ((result* 31)+((this.variantId == null)? 0 :this.variantId.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.sku == null)? 0 :this.sku.hashCode()));
        result = ((result* 31)+((this.variantKey == null)? 0 :this.variantKey.hashCode()));
        result = ((result* 31)+((this.brand == null)? 0 :this.brand.hashCode()));
        result = ((result* 31)+((this.isPortalPriceAvailable == null)? 0 :this.isPortalPriceAvailable.hashCode()));
        result = ((result* 31)+((this.productType == null)? 0 :this.productType.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ProductInfo) == false) {
            return false;
        }
        ProductInfo rhs = ((ProductInfo) other);
        return (((((((((((((((((this.productForm == rhs.productForm)||((this.productForm!= null)&&this.productForm.equals(rhs.productForm)))&&((this.productSlug == rhs.productSlug)||((this.productSlug!= null)&&this.productSlug.equals(rhs.productSlug))))&&((this.forFreightCharge == rhs.forFreightCharge)||((this.forFreightCharge!= null)&&this.forFreightCharge.equals(rhs.forFreightCharge))))&&((this.pricingMode == rhs.pricingMode)||((this.pricingMode!= null)&&this.pricingMode.equals(rhs.pricingMode))))&&((this.imageUrl == rhs.imageUrl)||((this.imageUrl!= null)&&this.imageUrl.equals(rhs.imageUrl))))&&((this.primaryQuantity == rhs.primaryQuantity)||((this.primaryQuantity!= null)&&this.primaryQuantity.equals(rhs.primaryQuantity))))&&((this.secondaryQuantity == rhs.secondaryQuantity)||((this.secondaryQuantity!= null)&&this.secondaryQuantity.equals(rhs.secondaryQuantity))))&&((this.name == rhs.name)||((this.name!= null)&&this.name.equals(rhs.name))))&&((this.attributes == rhs.attributes)||((this.attributes!= null)&&this.attributes.equals(rhs.attributes))))&&((this.variantId == rhs.variantId)||((this.variantId!= null)&&this.variantId.equals(rhs.variantId))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.sku == rhs.sku)||((this.sku!= null)&&this.sku.equals(rhs.sku))))&&((this.variantKey == rhs.variantKey)||((this.variantKey!= null)&&this.variantKey.equals(rhs.variantKey))))&&((this.brand == rhs.brand)||((this.brand!= null)&&this.brand.equals(rhs.brand))))&&((this.isPortalPriceAvailable == rhs.isPortalPriceAvailable)||((this.isPortalPriceAvailable!= null)&&this.isPortalPriceAvailable.equals(rhs.isPortalPriceAvailable))))&&((this.productType == rhs.productType)||((this.productType!= null)&&this.productType.equals(rhs.productType))));
    }

}

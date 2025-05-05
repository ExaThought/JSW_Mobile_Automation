
package com.qapitol.jsw.apitest.pojos.order.centralCartAddToCartResponse;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "catalouge_id",
    "product_name",
    "brand_name",
    "grade",
    "subgrade",
    "uoms",
    "image_url",
    "product_slug"
})
@Generated("jsonschema2pojo")
public class CatalougeInfo {

    @JsonProperty("catalouge_id")
    private String catalougeId;
    @JsonProperty("product_name")
    private String productName;
    @JsonProperty("brand_name")
    private String brandName;
    @JsonProperty("grade")
    private String grade;
    @JsonProperty("subgrade")
    private String subgrade;
    @JsonProperty("uoms")
    private Object uoms;
    @JsonProperty("image_url")
    private String imageUrl;
    @JsonProperty("product_slug")
    private String productSlug;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("catalouge_id")
    public String getCatalougeId() {
        return catalougeId;
    }

    @JsonProperty("catalouge_id")
    public void setCatalougeId(String catalougeId) {
        this.catalougeId = catalougeId;
    }

    @JsonProperty("product_name")
    public String getProductName() {
        return productName;
    }

    @JsonProperty("product_name")
    public void setProductName(String productName) {
        this.productName = productName;
    }

    @JsonProperty("brand_name")
    public String getBrandName() {
        return brandName;
    }

    @JsonProperty("brand_name")
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    @JsonProperty("grade")
    public String getGrade() {
        return grade;
    }

    @JsonProperty("grade")
    public void setGrade(String grade) {
        this.grade = grade;
    }

    @JsonProperty("subgrade")
    public String getSubgrade() {
        return subgrade;
    }

    @JsonProperty("subgrade")
    public void setSubgrade(String subgrade) {
        this.subgrade = subgrade;
    }

    @JsonProperty("uoms")
    public Object getUoms() {
        return uoms;
    }

    @JsonProperty("uoms")
    public void setUoms(Object uoms) {
        this.uoms = uoms;
    }

    @JsonProperty("image_url")
    public String getImageUrl() {
        return imageUrl;
    }

    @JsonProperty("image_url")
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @JsonProperty("product_slug")
    public String getProductSlug() {
        return productSlug;
    }

    @JsonProperty("product_slug")
    public void setProductSlug(String productSlug) {
        this.productSlug = productSlug;
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
        sb.append(CatalougeInfo.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("catalougeId");
        sb.append('=');
        sb.append(((this.catalougeId == null)?"<null>":this.catalougeId));
        sb.append(',');
        sb.append("productName");
        sb.append('=');
        sb.append(((this.productName == null)?"<null>":this.productName));
        sb.append(',');
        sb.append("brandName");
        sb.append('=');
        sb.append(((this.brandName == null)?"<null>":this.brandName));
        sb.append(',');
        sb.append("grade");
        sb.append('=');
        sb.append(((this.grade == null)?"<null>":this.grade));
        sb.append(',');
        sb.append("subgrade");
        sb.append('=');
        sb.append(((this.subgrade == null)?"<null>":this.subgrade));
        sb.append(',');
        sb.append("uoms");
        sb.append('=');
        sb.append(((this.uoms == null)?"<null>":this.uoms));
        sb.append(',');
        sb.append("imageUrl");
        sb.append('=');
        sb.append(((this.imageUrl == null)?"<null>":this.imageUrl));
        sb.append(',');
        sb.append("productSlug");
        sb.append('=');
        sb.append(((this.productSlug == null)?"<null>":this.productSlug));
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
        result = ((result* 31)+((this.brandName == null)? 0 :this.brandName.hashCode()));
        result = ((result* 31)+((this.catalougeId == null)? 0 :this.catalougeId.hashCode()));
        result = ((result* 31)+((this.uoms == null)? 0 :this.uoms.hashCode()));
        result = ((result* 31)+((this.grade == null)? 0 :this.grade.hashCode()));
        result = ((result* 31)+((this.imageUrl == null)? 0 :this.imageUrl.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.subgrade == null)? 0 :this.subgrade.hashCode()));
        result = ((result* 31)+((this.productSlug == null)? 0 :this.productSlug.hashCode()));
        result = ((result* 31)+((this.productName == null)? 0 :this.productName.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof CatalougeInfo) == false) {
            return false;
        }
        CatalougeInfo rhs = ((CatalougeInfo) other);
        return ((((((((((this.brandName == rhs.brandName)||((this.brandName!= null)&&this.brandName.equals(rhs.brandName)))&&((this.catalougeId == rhs.catalougeId)||((this.catalougeId!= null)&&this.catalougeId.equals(rhs.catalougeId))))&&((this.uoms == rhs.uoms)||((this.uoms!= null)&&this.uoms.equals(rhs.uoms))))&&((this.grade == rhs.grade)||((this.grade!= null)&&this.grade.equals(rhs.grade))))&&((this.imageUrl == rhs.imageUrl)||((this.imageUrl!= null)&&this.imageUrl.equals(rhs.imageUrl))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.subgrade == rhs.subgrade)||((this.subgrade!= null)&&this.subgrade.equals(rhs.subgrade))))&&((this.productSlug == rhs.productSlug)||((this.productSlug!= null)&&this.productSlug.equals(rhs.productSlug))))&&((this.productName == rhs.productName)||((this.productName!= null)&&this.productName.equals(rhs.productName))));
    }

}

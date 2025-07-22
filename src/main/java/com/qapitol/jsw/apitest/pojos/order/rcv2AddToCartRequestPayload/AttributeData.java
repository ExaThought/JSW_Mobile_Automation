
package com.qapitol.jsw.apitest.pojos.order.rcv2AddToCartRequestPayload;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "productAttributes",
    "additionalAttributes",
    "customAttributes",
    "attributesMeta"
})
@Generated("jsonschema2pojo")
public class AttributeData {

    @JsonProperty("productAttributes")
    private List<ProductAttribute> productAttributes = new ArrayList<ProductAttribute>();
    @JsonProperty("additionalAttributes")
    private Object additionalAttributes;
    @JsonProperty("customAttributes")
    private List<CustomAttribute> customAttributes = new ArrayList<CustomAttribute>();
    @JsonProperty("attributesMeta")
    private List<String> attributesMeta = new ArrayList<String>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("productAttributes")
    public List<ProductAttribute> getProductAttributes() {
        return productAttributes;
    }

    @JsonProperty("productAttributes")
    public void setProductAttributes(List<ProductAttribute> productAttributes) {
        this.productAttributes = productAttributes;
    }

    @JsonProperty("additionalAttributes")
    public Object getAdditionalAttributes() {
        return additionalAttributes;
    }

    @JsonProperty("additionalAttributes")
    public void setAdditionalAttributes(Object additionalAttributes) {
        this.additionalAttributes = additionalAttributes;
    }

    @JsonProperty("customAttributes")
    public List<CustomAttribute> getCustomAttributes() {
        return customAttributes;
    }

    @JsonProperty("customAttributes")
    public void setCustomAttributes(List<CustomAttribute> customAttributes) {
        this.customAttributes = customAttributes;
    }

    @JsonProperty("attributesMeta")
    public List<String> getAttributesMeta() {
        return attributesMeta;
    }

    @JsonProperty("attributesMeta")
    public void setAttributesMeta(List<String> attributesMeta) {
        this.attributesMeta = attributesMeta;
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
        sb.append(AttributeData.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("productAttributes");
        sb.append('=');
        sb.append(((this.productAttributes == null)?"<null>":this.productAttributes));
        sb.append(',');
        sb.append("additionalAttributes");
        sb.append('=');
        sb.append(((this.additionalAttributes == null)?"<null>":this.additionalAttributes));
        sb.append(',');
        sb.append("customAttributes");
        sb.append('=');
        sb.append(((this.customAttributes == null)?"<null>":this.customAttributes));
        sb.append(',');
        sb.append("attributesMeta");
        sb.append('=');
        sb.append(((this.attributesMeta == null)?"<null>":this.attributesMeta));
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
        result = ((result* 31)+((this.attributesMeta == null)? 0 :this.attributesMeta.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.productAttributes == null)? 0 :this.productAttributes.hashCode()));
        result = ((result* 31)+((this.additionalAttributes == null)? 0 :this.additionalAttributes.hashCode()));
        result = ((result* 31)+((this.customAttributes == null)? 0 :this.customAttributes.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof AttributeData) == false) {
            return false;
        }
        AttributeData rhs = ((AttributeData) other);
        return ((((((this.attributesMeta == rhs.attributesMeta)||((this.attributesMeta!= null)&&this.attributesMeta.equals(rhs.attributesMeta)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.productAttributes == rhs.productAttributes)||((this.productAttributes!= null)&&this.productAttributes.equals(rhs.productAttributes))))&&((this.additionalAttributes == rhs.additionalAttributes)||((this.additionalAttributes!= null)&&this.additionalAttributes.equals(rhs.additionalAttributes))))&&((this.customAttributes == rhs.customAttributes)||((this.customAttributes!= null)&&this.customAttributes.equals(rhs.customAttributes))));
    }

}

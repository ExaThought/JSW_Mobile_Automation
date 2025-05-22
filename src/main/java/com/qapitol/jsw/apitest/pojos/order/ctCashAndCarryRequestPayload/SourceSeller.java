
package com.qapitol.jsw.apitest.pojos.order.ctCashAndCarryRequestPayload;

import java.util.LinkedHashMap;
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
    "sourceSellerVendorMasterId",
    "SourceSellerSKUPrice",
    "SourceSellerMobile",
    "sourceSellerId",
    "sourceSellerGST",
    "SourceSellerEmail",
    "sourceSellerCompanyName",
    "sourceSellerAddress"
})
@Generated("jsonschema2pojo")
public class SourceSeller {

    @JsonProperty("sourceSellerVendorMasterId")
    private String sourceSellerVendorMasterId;
    @JsonProperty("SourceSellerSKUPrice")
    private String sourceSellerSKUPrice;
    @JsonProperty("SourceSellerMobile")
    private String sourceSellerMobile;
    @JsonProperty("sourceSellerId")
    private String sourceSellerId;
    @JsonProperty("sourceSellerGST")
    private String sourceSellerGST;
    @JsonProperty("SourceSellerEmail")
    private String sourceSellerEmail;
    @JsonProperty("sourceSellerCompanyName")
    private String sourceSellerCompanyName;
    @JsonProperty("sourceSellerAddress")
    private SourceSellerAddress sourceSellerAddress;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("sourceSellerVendorMasterId")
    public String getSourceSellerVendorMasterId() {
        return sourceSellerVendorMasterId;
    }

    @JsonProperty("sourceSellerVendorMasterId")
    public void setSourceSellerVendorMasterId(String sourceSellerVendorMasterId) {
        this.sourceSellerVendorMasterId = sourceSellerVendorMasterId;
    }

    @JsonProperty("SourceSellerSKUPrice")
    public String getSourceSellerSKUPrice() {
        return sourceSellerSKUPrice;
    }

    @JsonProperty("SourceSellerSKUPrice")
    public void setSourceSellerSKUPrice(String sourceSellerSKUPrice) {
        this.sourceSellerSKUPrice = sourceSellerSKUPrice;
    }

    @JsonProperty("SourceSellerMobile")
    public String getSourceSellerMobile() {
        return sourceSellerMobile;
    }

    @JsonProperty("SourceSellerMobile")
    public void setSourceSellerMobile(String sourceSellerMobile) {
        this.sourceSellerMobile = sourceSellerMobile;
    }

    @JsonProperty("sourceSellerId")
    public String getSourceSellerId() {
        return sourceSellerId;
    }

    @JsonProperty("sourceSellerId")
    public void setSourceSellerId(String sourceSellerId) {
        this.sourceSellerId = sourceSellerId;
    }

    @JsonProperty("sourceSellerGST")
    public String getSourceSellerGST() {
        return sourceSellerGST;
    }

    @JsonProperty("sourceSellerGST")
    public void setSourceSellerGST(String sourceSellerGST) {
        this.sourceSellerGST = sourceSellerGST;
    }

    @JsonProperty("SourceSellerEmail")
    public String getSourceSellerEmail() {
        return sourceSellerEmail;
    }

    @JsonProperty("SourceSellerEmail")
    public void setSourceSellerEmail(String sourceSellerEmail) {
        this.sourceSellerEmail = sourceSellerEmail;
    }

    @JsonProperty("sourceSellerCompanyName")
    public String getSourceSellerCompanyName() {
        return sourceSellerCompanyName;
    }

    @JsonProperty("sourceSellerCompanyName")
    public void setSourceSellerCompanyName(String sourceSellerCompanyName) {
        this.sourceSellerCompanyName = sourceSellerCompanyName;
    }

    @JsonProperty("sourceSellerAddress")
    public SourceSellerAddress getSourceSellerAddress() {
        return sourceSellerAddress;
    }

    @JsonProperty("sourceSellerAddress")
    public void setSourceSellerAddress(SourceSellerAddress sourceSellerAddress) {
        this.sourceSellerAddress = sourceSellerAddress;
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
        sb.append(SourceSeller.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("sourceSellerVendorMasterId");
        sb.append('=');
        sb.append(((this.sourceSellerVendorMasterId == null)?"<null>":this.sourceSellerVendorMasterId));
        sb.append(',');
        sb.append("sourceSellerSKUPrice");
        sb.append('=');
        sb.append(((this.sourceSellerSKUPrice == null)?"<null>":this.sourceSellerSKUPrice));
        sb.append(',');
        sb.append("sourceSellerMobile");
        sb.append('=');
        sb.append(((this.sourceSellerMobile == null)?"<null>":this.sourceSellerMobile));
        sb.append(',');
        sb.append("sourceSellerId");
        sb.append('=');
        sb.append(((this.sourceSellerId == null)?"<null>":this.sourceSellerId));
        sb.append(',');
        sb.append("sourceSellerGST");
        sb.append('=');
        sb.append(((this.sourceSellerGST == null)?"<null>":this.sourceSellerGST));
        sb.append(',');
        sb.append("sourceSellerEmail");
        sb.append('=');
        sb.append(((this.sourceSellerEmail == null)?"<null>":this.sourceSellerEmail));
        sb.append(',');
        sb.append("sourceSellerCompanyName");
        sb.append('=');
        sb.append(((this.sourceSellerCompanyName == null)?"<null>":this.sourceSellerCompanyName));
        sb.append(',');
        sb.append("sourceSellerAddress");
        sb.append('=');
        sb.append(((this.sourceSellerAddress == null)?"<null>":this.sourceSellerAddress));
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
        result = ((result* 31)+((this.sourceSellerSKUPrice == null)? 0 :this.sourceSellerSKUPrice.hashCode()));
        result = ((result* 31)+((this.sourceSellerAddress == null)? 0 :this.sourceSellerAddress.hashCode()));
        result = ((result* 31)+((this.sourceSellerVendorMasterId == null)? 0 :this.sourceSellerVendorMasterId.hashCode()));
        result = ((result* 31)+((this.sourceSellerGST == null)? 0 :this.sourceSellerGST.hashCode()));
        result = ((result* 31)+((this.sourceSellerId == null)? 0 :this.sourceSellerId.hashCode()));
        result = ((result* 31)+((this.sourceSellerEmail == null)? 0 :this.sourceSellerEmail.hashCode()));
        result = ((result* 31)+((this.sourceSellerCompanyName == null)? 0 :this.sourceSellerCompanyName.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.sourceSellerMobile == null)? 0 :this.sourceSellerMobile.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof SourceSeller) == false) {
            return false;
        }
        SourceSeller rhs = ((SourceSeller) other);
        return ((((((((((this.sourceSellerSKUPrice == rhs.sourceSellerSKUPrice)||((this.sourceSellerSKUPrice!= null)&&this.sourceSellerSKUPrice.equals(rhs.sourceSellerSKUPrice)))&&((this.sourceSellerAddress == rhs.sourceSellerAddress)||((this.sourceSellerAddress!= null)&&this.sourceSellerAddress.equals(rhs.sourceSellerAddress))))&&((this.sourceSellerVendorMasterId == rhs.sourceSellerVendorMasterId)||((this.sourceSellerVendorMasterId!= null)&&this.sourceSellerVendorMasterId.equals(rhs.sourceSellerVendorMasterId))))&&((this.sourceSellerGST == rhs.sourceSellerGST)||((this.sourceSellerGST!= null)&&this.sourceSellerGST.equals(rhs.sourceSellerGST))))&&((this.sourceSellerId == rhs.sourceSellerId)||((this.sourceSellerId!= null)&&this.sourceSellerId.equals(rhs.sourceSellerId))))&&((this.sourceSellerEmail == rhs.sourceSellerEmail)||((this.sourceSellerEmail!= null)&&this.sourceSellerEmail.equals(rhs.sourceSellerEmail))))&&((this.sourceSellerCompanyName == rhs.sourceSellerCompanyName)||((this.sourceSellerCompanyName!= null)&&this.sourceSellerCompanyName.equals(rhs.sourceSellerCompanyName))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.sourceSellerMobile == rhs.sourceSellerMobile)||((this.sourceSellerMobile!= null)&&this.sourceSellerMobile.equals(rhs.sourceSellerMobile))));
    }

}

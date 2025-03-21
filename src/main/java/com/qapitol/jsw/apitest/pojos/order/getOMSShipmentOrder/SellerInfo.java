
package com.qapitol.jsw.apitest.pojos.order.getOMSShipmentOrder;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "gstin",
    "channels",
    "sellerId",
    "sellerName",
    "sellerType",
    "companyName",
    "contactDetails"
})
@Generated("jsonschema2pojo")
public class SellerInfo {

    @JsonProperty("gstin")
    private String gstin;
    @JsonProperty("channels")
    private Object channels;
    @JsonProperty("sellerId")
    private String sellerId;
    @JsonProperty("sellerName")
    private String sellerName;
    @JsonProperty("sellerType")
    private String sellerType;
    @JsonProperty("companyName")
    private String companyName;
    @JsonProperty("contactDetails")
    private ContactDetails contactDetails;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("gstin")
    public String getGstin() {
        return gstin;
    }

    @JsonProperty("gstin")
    public void setGstin(String gstin) {
        this.gstin = gstin;
    }

    @JsonProperty("channels")
    public Object getChannels() {
        return channels;
    }

    @JsonProperty("channels")
    public void setChannels(Object channels) {
        this.channels = channels;
    }

    @JsonProperty("sellerId")
    public String getSellerId() {
        return sellerId;
    }

    @JsonProperty("sellerId")
    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    @JsonProperty("sellerName")
    public String getSellerName() {
        return sellerName;
    }

    @JsonProperty("sellerName")
    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    @JsonProperty("sellerType")
    public String getSellerType() {
        return sellerType;
    }

    @JsonProperty("sellerType")
    public void setSellerType(String sellerType) {
        this.sellerType = sellerType;
    }

    @JsonProperty("companyName")
    public String getCompanyName() {
        return companyName;
    }

    @JsonProperty("companyName")
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @JsonProperty("contactDetails")
    public ContactDetails getContactDetails() {
        return contactDetails;
    }

    @JsonProperty("contactDetails")
    public void setContactDetails(ContactDetails contactDetails) {
        this.contactDetails = contactDetails;
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
        sb.append(SellerInfo.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("gstin");
        sb.append('=');
        sb.append(((this.gstin == null)?"<null>":this.gstin));
        sb.append(',');
        sb.append("channels");
        sb.append('=');
        sb.append(((this.channels == null)?"<null>":this.channels));
        sb.append(',');
        sb.append("sellerId");
        sb.append('=');
        sb.append(((this.sellerId == null)?"<null>":this.sellerId));
        sb.append(',');
        sb.append("sellerName");
        sb.append('=');
        sb.append(((this.sellerName == null)?"<null>":this.sellerName));
        sb.append(',');
        sb.append("sellerType");
        sb.append('=');
        sb.append(((this.sellerType == null)?"<null>":this.sellerType));
        sb.append(',');
        sb.append("companyName");
        sb.append('=');
        sb.append(((this.companyName == null)?"<null>":this.companyName));
        sb.append(',');
        sb.append("contactDetails");
        sb.append('=');
        sb.append(((this.contactDetails == null)?"<null>":this.contactDetails));
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
        result = ((result* 31)+((this.sellerId == null)? 0 :this.sellerId.hashCode()));
        result = ((result* 31)+((this.channels == null)? 0 :this.channels.hashCode()));
        result = ((result* 31)+((this.companyName == null)? 0 :this.companyName.hashCode()));
        result = ((result* 31)+((this.sellerName == null)? 0 :this.sellerName.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.gstin == null)? 0 :this.gstin.hashCode()));
        result = ((result* 31)+((this.sellerType == null)? 0 :this.sellerType.hashCode()));
        result = ((result* 31)+((this.contactDetails == null)? 0 :this.contactDetails.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof SellerInfo) == false) {
            return false;
        }
        SellerInfo rhs = ((SellerInfo) other);
        return (((((((((this.sellerId == rhs.sellerId)||((this.sellerId!= null)&&this.sellerId.equals(rhs.sellerId)))&&((this.channels == rhs.channels)||((this.channels!= null)&&this.channels.equals(rhs.channels))))&&((this.companyName == rhs.companyName)||((this.companyName!= null)&&this.companyName.equals(rhs.companyName))))&&((this.sellerName == rhs.sellerName)||((this.sellerName!= null)&&this.sellerName.equals(rhs.sellerName))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.gstin == rhs.gstin)||((this.gstin!= null)&&this.gstin.equals(rhs.gstin))))&&((this.sellerType == rhs.sellerType)||((this.sellerType!= null)&&this.sellerType.equals(rhs.sellerType))))&&((this.contactDetails == rhs.contactDetails)||((this.contactDetails!= null)&&this.contactDetails.equals(rhs.contactDetails))));
    }

}

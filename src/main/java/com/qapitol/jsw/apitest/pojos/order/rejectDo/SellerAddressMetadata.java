package com.qapitol.jsw.apitest.pojos.order.rejectDo;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "vendorLocationDisplayName",
        "vendorLocationKey",
        "isRegisteredBillingAddress",
        "address",
        "locationType"
})
@Generated("jsonschema2pojo")
public class SellerAddressMetadata {

    @JsonProperty("vendorLocationDisplayName")
    private String vendorLocationDisplayName;
    @JsonProperty("vendorLocationKey")
    private String vendorLocationKey;
    @JsonProperty("isRegisteredBillingAddress")
    private Boolean isRegisteredBillingAddress;
    @JsonProperty("address")
    private Address address;
    @JsonProperty("locationType")
    private String locationType;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("vendorLocationDisplayName")
    public String getVendorLocationDisplayName() {
        return vendorLocationDisplayName;
    }

    @JsonProperty("vendorLocationDisplayName")
    public void setVendorLocationDisplayName(String vendorLocationDisplayName) {
        this.vendorLocationDisplayName = vendorLocationDisplayName;
    }

    @JsonProperty("vendorLocationKey")
    public String getVendorLocationKey() {
        return vendorLocationKey;
    }

    @JsonProperty("vendorLocationKey")
    public void setVendorLocationKey(String vendorLocationKey) {
        this.vendorLocationKey = vendorLocationKey;
    }

    @JsonProperty("isRegisteredBillingAddress")
    public Boolean getIsRegisteredBillingAddress() {
        return isRegisteredBillingAddress;
    }

    @JsonProperty("isRegisteredBillingAddress")
    public void setIsRegisteredBillingAddress(Boolean isRegisteredBillingAddress) {
        this.isRegisteredBillingAddress = isRegisteredBillingAddress;
    }

    @JsonProperty("address")
    public Address getAddress() {
        return address;
    }

    @JsonProperty("address")
    public void setAddress(Address address) {
        this.address = address;
    }

    @JsonProperty("locationType")
    public String getLocationType() {
        return locationType;
    }

    @JsonProperty("locationType")
    public void setLocationType(String locationType) {
        this.locationType = locationType;
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
        sb.append(SellerAddressMetadata.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("vendorLocationDisplayName");
        sb.append('=');
        sb.append(((this.vendorLocationDisplayName == null)?"<null>":this.vendorLocationDisplayName));
        sb.append(',');
        sb.append("vendorLocationKey");
        sb.append('=');
        sb.append(((this.vendorLocationKey == null)?"<null>":this.vendorLocationKey));
        sb.append(',');
        sb.append("isRegisteredBillingAddress");
        sb.append('=');
        sb.append(((this.isRegisteredBillingAddress == null)?"<null>":this.isRegisteredBillingAddress));
        sb.append(',');
        sb.append("address");
        sb.append('=');
        sb.append(((this.address == null)?"<null>":this.address));
        sb.append(',');
        sb.append("locationType");
        sb.append('=');
        sb.append(((this.locationType == null)?"<null>":this.locationType));
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
        result = ((result* 31)+((this.vendorLocationKey == null)? 0 :this.vendorLocationKey.hashCode()));
        result = ((result* 31)+((this.isRegisteredBillingAddress == null)? 0 :this.isRegisteredBillingAddress.hashCode()));
        result = ((result* 31)+((this.address == null)? 0 :this.address.hashCode()));
        result = ((result* 31)+((this.locationType == null)? 0 :this.locationType.hashCode()));
        result = ((result* 31)+((this.vendorLocationDisplayName == null)? 0 :this.vendorLocationDisplayName.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof SellerAddressMetadata) == false) {
            return false;
        }
        SellerAddressMetadata rhs = ((SellerAddressMetadata) other);
        return (((((((this.vendorLocationKey == rhs.vendorLocationKey)||((this.vendorLocationKey!= null)&&this.vendorLocationKey.equals(rhs.vendorLocationKey)))&&((this.isRegisteredBillingAddress == rhs.isRegisteredBillingAddress)||((this.isRegisteredBillingAddress!= null)&&this.isRegisteredBillingAddress.equals(rhs.isRegisteredBillingAddress))))&&((this.address == rhs.address)||((this.address!= null)&&this.address.equals(rhs.address))))&&((this.locationType == rhs.locationType)||((this.locationType!= null)&&this.locationType.equals(rhs.locationType))))&&((this.vendorLocationDisplayName == rhs.vendorLocationDisplayName)||((this.vendorLocationDisplayName!= null)&&this.vendorLocationDisplayName.equals(rhs.vendorLocationDisplayName))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
    }

}

package com.qapitol.jsw.apitest.pojos.order.rcv2Request;

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
    "sf_address_id",
    "ct_address_id",
    "address_type"
})
@Generated("jsonschema2pojo")
public class CartAddress {

    @JsonProperty("sf_address_id")
    private String sfAddressId;
    @JsonProperty("ct_address_id")
    private String ctAddressId;
    @JsonProperty("address_type")
    private String addressType;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("sf_address_id")
    public String getSfAddressId() {
        return sfAddressId;
    }

    @JsonProperty("sf_address_id")
    public void setSfAddressId(String sfAddressId) {
        this.sfAddressId = sfAddressId;
    }

    @JsonProperty("ct_address_id")
    public String getCtAddressId() {
        return ctAddressId;
    }

    @JsonProperty("ct_address_id")
    public void setCtAddressId(String ctAddressId) {
        this.ctAddressId = ctAddressId;
    }

    @JsonProperty("address_type")
    public String getAddressType() {
        return addressType;
    }

    @JsonProperty("address_type")
    public void setAddressType(String addressType) {
        this.addressType = addressType;
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
        sb.append(CartAddress.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("sfAddressId");
        sb.append('=');
        sb.append(((this.sfAddressId == null)?"<null>":this.sfAddressId));
        sb.append(',');
        sb.append("ctAddressId");
        sb.append('=');
        sb.append(((this.ctAddressId == null)?"<null>":this.ctAddressId));
        sb.append(',');
        sb.append("addressType");
        sb.append('=');
        sb.append(((this.addressType == null)?"<null>":this.addressType));
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
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.ctAddressId == null)? 0 :this.ctAddressId.hashCode()));
        result = ((result* 31)+((this.sfAddressId == null)? 0 :this.sfAddressId.hashCode()));
        result = ((result* 31)+((this.addressType == null)? 0 :this.addressType.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof CartAddress) == false) {
            return false;
        }
        CartAddress rhs = ((CartAddress) other);
        return (((((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties)))&&((this.ctAddressId == rhs.ctAddressId)||((this.ctAddressId!= null)&&this.ctAddressId.equals(rhs.ctAddressId))))&&((this.sfAddressId == rhs.sfAddressId)||((this.sfAddressId!= null)&&this.sfAddressId.equals(rhs.sfAddressId))))&&((this.addressType == rhs.addressType)||((this.addressType!= null)&&this.addressType.equals(rhs.addressType))));
    }

}

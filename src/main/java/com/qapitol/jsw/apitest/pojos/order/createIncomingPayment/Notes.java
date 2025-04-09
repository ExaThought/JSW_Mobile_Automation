
package com.qapitol.jsw.apitest.pojos.order.createIncomingPayment;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "address",
    "buyerCompanyName",
    "buyerEmail",
    "CT_CustomerId",
    "CT_OrderId",
    "CT_OrderNumber",
    "gstin",
    "razorPayCustomerId",
    "sellerCompanyName"
})
@Generated("jsonschema2pojo")
public class Notes {

    @JsonProperty("address")
    private String address;
    @JsonProperty("buyerCompanyName")
    private String buyerCompanyName;
    @JsonProperty("buyerEmail")
    private String buyerEmail;
    @JsonProperty("CT_CustomerId")
    private String cTCustomerId;
    @JsonProperty("CT_OrderId")
    private String cTOrderId;
    @JsonProperty("CT_OrderNumber")
    private String cTOrderNumber;
    @JsonProperty("gstin")
    private String gstin;
    @JsonProperty("razorPayCustomerId")
    private String razorPayCustomerId;
    @JsonProperty("sellerCompanyName")
    private String sellerCompanyName;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("address")
    public String getAddress() {
        return address;
    }

    @JsonProperty("address")
    public void setAddress(String address) {
        this.address = address;
    }

    @JsonProperty("buyerCompanyName")
    public String getBuyerCompanyName() {
        return buyerCompanyName;
    }

    @JsonProperty("buyerCompanyName")
    public void setBuyerCompanyName(String buyerCompanyName) {
        this.buyerCompanyName = buyerCompanyName;
    }

    @JsonProperty("buyerEmail")
    public String getBuyerEmail() {
        return buyerEmail;
    }

    @JsonProperty("buyerEmail")
    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }

    @JsonProperty("CT_CustomerId")
    public String getCTCustomerId() {
        return cTCustomerId;
    }

    @JsonProperty("CT_CustomerId")
    public void setCTCustomerId(String cTCustomerId) {
        this.cTCustomerId = cTCustomerId;
    }

    @JsonProperty("CT_OrderId")
    public String getCTOrderId() {
        return cTOrderId;
    }

    @JsonProperty("CT_OrderId")
    public void setCTOrderId(String cTOrderId) {
        this.cTOrderId = cTOrderId;
    }

    @JsonProperty("CT_OrderNumber")
    public String getCTOrderNumber() {
        return cTOrderNumber;
    }

    @JsonProperty("CT_OrderNumber")
    public void setCTOrderNumber(String cTOrderNumber) {
        this.cTOrderNumber = cTOrderNumber;
    }

    @JsonProperty("gstin")
    public String getGstin() {
        return gstin;
    }

    @JsonProperty("gstin")
    public void setGstin(String gstin) {
        this.gstin = gstin;
    }

    @JsonProperty("razorPayCustomerId")
    public String getRazorPayCustomerId() {
        return razorPayCustomerId;
    }

    @JsonProperty("razorPayCustomerId")
    public void setRazorPayCustomerId(String razorPayCustomerId) {
        this.razorPayCustomerId = razorPayCustomerId;
    }

    @JsonProperty("sellerCompanyName")
    public String getSellerCompanyName() {
        return sellerCompanyName;
    }

    @JsonProperty("sellerCompanyName")
    public void setSellerCompanyName(String sellerCompanyName) {
        this.sellerCompanyName = sellerCompanyName;
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
        sb.append(Notes.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("address");
        sb.append('=');
        sb.append(((this.address == null)?"<null>":this.address));
        sb.append(',');
        sb.append("buyerCompanyName");
        sb.append('=');
        sb.append(((this.buyerCompanyName == null)?"<null>":this.buyerCompanyName));
        sb.append(',');
        sb.append("buyerEmail");
        sb.append('=');
        sb.append(((this.buyerEmail == null)?"<null>":this.buyerEmail));
        sb.append(',');
        sb.append("cTCustomerId");
        sb.append('=');
        sb.append(((this.cTCustomerId == null)?"<null>":this.cTCustomerId));
        sb.append(',');
        sb.append("cTOrderId");
        sb.append('=');
        sb.append(((this.cTOrderId == null)?"<null>":this.cTOrderId));
        sb.append(',');
        sb.append("cTOrderNumber");
        sb.append('=');
        sb.append(((this.cTOrderNumber == null)?"<null>":this.cTOrderNumber));
        sb.append(',');
        sb.append("gstin");
        sb.append('=');
        sb.append(((this.gstin == null)?"<null>":this.gstin));
        sb.append(',');
        sb.append("razorPayCustomerId");
        sb.append('=');
        sb.append(((this.razorPayCustomerId == null)?"<null>":this.razorPayCustomerId));
        sb.append(',');
        sb.append("sellerCompanyName");
        sb.append('=');
        sb.append(((this.sellerCompanyName == null)?"<null>":this.sellerCompanyName));
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
        result = ((result* 31)+((this.address == null)? 0 :this.address.hashCode()));
        result = ((result* 31)+((this.cTOrderNumber == null)? 0 :this.cTOrderNumber.hashCode()));
        result = ((result* 31)+((this.buyerEmail == null)? 0 :this.buyerEmail.hashCode()));
        result = ((result* 31)+((this.cTOrderId == null)? 0 :this.cTOrderId.hashCode()));
        result = ((result* 31)+((this.razorPayCustomerId == null)? 0 :this.razorPayCustomerId.hashCode()));
        result = ((result* 31)+((this.cTCustomerId == null)? 0 :this.cTCustomerId.hashCode()));
        result = ((result* 31)+((this.sellerCompanyName == null)? 0 :this.sellerCompanyName.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.buyerCompanyName == null)? 0 :this.buyerCompanyName.hashCode()));
        result = ((result* 31)+((this.gstin == null)? 0 :this.gstin.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Notes) == false) {
            return false;
        }
        Notes rhs = ((Notes) other);
        return (((((((((((this.address == rhs.address)||((this.address!= null)&&this.address.equals(rhs.address)))&&((this.cTOrderNumber == rhs.cTOrderNumber)||((this.cTOrderNumber!= null)&&this.cTOrderNumber.equals(rhs.cTOrderNumber))))&&((this.buyerEmail == rhs.buyerEmail)||((this.buyerEmail!= null)&&this.buyerEmail.equals(rhs.buyerEmail))))&&((this.cTOrderId == rhs.cTOrderId)||((this.cTOrderId!= null)&&this.cTOrderId.equals(rhs.cTOrderId))))&&((this.razorPayCustomerId == rhs.razorPayCustomerId)||((this.razorPayCustomerId!= null)&&this.razorPayCustomerId.equals(rhs.razorPayCustomerId))))&&((this.cTCustomerId == rhs.cTCustomerId)||((this.cTCustomerId!= null)&&this.cTCustomerId.equals(rhs.cTCustomerId))))&&((this.sellerCompanyName == rhs.sellerCompanyName)||((this.sellerCompanyName!= null)&&this.sellerCompanyName.equals(rhs.sellerCompanyName))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.buyerCompanyName == rhs.buyerCompanyName)||((this.buyerCompanyName!= null)&&this.buyerCompanyName.equals(rhs.buyerCompanyName))))&&((this.gstin == rhs.gstin)||((this.gstin!= null)&&this.gstin.equals(rhs.gstin))));
    }

}

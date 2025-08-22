
package com.qapitol.jsw.apitest.pojos.order.orderConfirm.orderConfirmResPayload;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "PAYER",
    "DOC_TYPE",
    "INCOTERM1",
    "INCOTERM2",
    "SOLD_PARTY",
    "BILL_TOPARTY",
    "PAYMENT_TERM",
    "SHIP_TOPARTY"
})
@Generated("jsonschema2pojo")
public class SapMetadataJson {

    @JsonProperty("PAYER")
    private String payer;
    @JsonProperty("DOC_TYPE")
    private String docType;
    @JsonProperty("INCOTERM1")
    private String incoterm1;
    @JsonProperty("INCOTERM2")
    private String incoterm2;
    @JsonProperty("SOLD_PARTY")
    private String soldParty;
    @JsonProperty("BILL_TOPARTY")
    private String billToparty;
    @JsonProperty("PAYMENT_TERM")
    private String paymentTerm;
    @JsonProperty("SHIP_TOPARTY")
    private String shipToparty;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("PAYER")
    public String getPayer() {
        return payer;
    }

    @JsonProperty("PAYER")
    public void setPayer(String payer) {
        this.payer = payer;
    }

    @JsonProperty("DOC_TYPE")
    public String getDocType() {
        return docType;
    }

    @JsonProperty("DOC_TYPE")
    public void setDocType(String docType) {
        this.docType = docType;
    }

    @JsonProperty("INCOTERM1")
    public String getIncoterm1() {
        return incoterm1;
    }

    @JsonProperty("INCOTERM1")
    public void setIncoterm1(String incoterm1) {
        this.incoterm1 = incoterm1;
    }

    @JsonProperty("INCOTERM2")
    public String getIncoterm2() {
        return incoterm2;
    }

    @JsonProperty("INCOTERM2")
    public void setIncoterm2(String incoterm2) {
        this.incoterm2 = incoterm2;
    }

    @JsonProperty("SOLD_PARTY")
    public String getSoldParty() {
        return soldParty;
    }

    @JsonProperty("SOLD_PARTY")
    public void setSoldParty(String soldParty) {
        this.soldParty = soldParty;
    }

    @JsonProperty("BILL_TOPARTY")
    public String getBillToparty() {
        return billToparty;
    }

    @JsonProperty("BILL_TOPARTY")
    public void setBillToparty(String billToparty) {
        this.billToparty = billToparty;
    }

    @JsonProperty("PAYMENT_TERM")
    public String getPaymentTerm() {
        return paymentTerm;
    }

    @JsonProperty("PAYMENT_TERM")
    public void setPaymentTerm(String paymentTerm) {
        this.paymentTerm = paymentTerm;
    }

    @JsonProperty("SHIP_TOPARTY")
    public String getShipToparty() {
        return shipToparty;
    }

    @JsonProperty("SHIP_TOPARTY")
    public void setShipToparty(String shipToparty) {
        this.shipToparty = shipToparty;
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
        sb.append(SapMetadataJson.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("payer");
        sb.append('=');
        sb.append(((this.payer == null)?"<null>":this.payer));
        sb.append(',');
        sb.append("docType");
        sb.append('=');
        sb.append(((this.docType == null)?"<null>":this.docType));
        sb.append(',');
        sb.append("incoterm1");
        sb.append('=');
        sb.append(((this.incoterm1 == null)?"<null>":this.incoterm1));
        sb.append(',');
        sb.append("incoterm2");
        sb.append('=');
        sb.append(((this.incoterm2 == null)?"<null>":this.incoterm2));
        sb.append(',');
        sb.append("soldParty");
        sb.append('=');
        sb.append(((this.soldParty == null)?"<null>":this.soldParty));
        sb.append(',');
        sb.append("billToparty");
        sb.append('=');
        sb.append(((this.billToparty == null)?"<null>":this.billToparty));
        sb.append(',');
        sb.append("paymentTerm");
        sb.append('=');
        sb.append(((this.paymentTerm == null)?"<null>":this.paymentTerm));
        sb.append(',');
        sb.append("shipToparty");
        sb.append('=');
        sb.append(((this.shipToparty == null)?"<null>":this.shipToparty));
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
        result = ((result* 31)+((this.incoterm2 == null)? 0 :this.incoterm2 .hashCode()));
        result = ((result* 31)+((this.incoterm1 == null)? 0 :this.incoterm1 .hashCode()));
        result = ((result* 31)+((this.billToparty == null)? 0 :this.billToparty.hashCode()));
        result = ((result* 31)+((this.docType == null)? 0 :this.docType.hashCode()));
        result = ((result* 31)+((this.soldParty == null)? 0 :this.soldParty.hashCode()));
        result = ((result* 31)+((this.shipToparty == null)? 0 :this.shipToparty.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.payer == null)? 0 :this.payer.hashCode()));
        result = ((result* 31)+((this.paymentTerm == null)? 0 :this.paymentTerm.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof SapMetadataJson) == false) {
            return false;
        }
        SapMetadataJson rhs = ((SapMetadataJson) other);
        return ((((((((((this.incoterm2 == rhs.incoterm2)||((this.incoterm2 != null)&&this.incoterm2 .equals(rhs.incoterm2)))&&((this.incoterm1 == rhs.incoterm1)||((this.incoterm1 != null)&&this.incoterm1 .equals(rhs.incoterm1))))&&((this.billToparty == rhs.billToparty)||((this.billToparty!= null)&&this.billToparty.equals(rhs.billToparty))))&&((this.docType == rhs.docType)||((this.docType!= null)&&this.docType.equals(rhs.docType))))&&((this.soldParty == rhs.soldParty)||((this.soldParty!= null)&&this.soldParty.equals(rhs.soldParty))))&&((this.shipToparty == rhs.shipToparty)||((this.shipToparty!= null)&&this.shipToparty.equals(rhs.shipToparty))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.payer == rhs.payer)||((this.payer!= null)&&this.payer.equals(rhs.payer))))&&((this.paymentTerm == rhs.paymentTerm)||((this.paymentTerm!= null)&&this.paymentTerm.equals(rhs.paymentTerm))));
    }

}

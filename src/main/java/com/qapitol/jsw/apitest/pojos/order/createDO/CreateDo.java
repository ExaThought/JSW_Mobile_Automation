
package com.qapitol.jsw.apitest.pojos.order.createDO;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "customerOrderId",
    "requestAmount",
    "transactionStatus",
    "psLineItemsDetails",
    "currentLedgerBalance",
    "freightValue",
    "sellerAddress",
    "sellerAddressMetadata",
    "sellerCompanyName",
    "deliveryType"
})
@Generated("jsonschema2pojo")
public class CreateDo {

    @JsonProperty("customerOrderId")
    private Object customerOrderId;
    @JsonProperty("requestAmount")
    private Double requestAmount;
    @JsonProperty("transactionStatus")
    private String transactionStatus;
    @JsonProperty("psLineItemsDetails")
    private List<PsLineItemsDetail> psLineItemsDetails = new ArrayList<PsLineItemsDetail>();
    @JsonProperty("currentLedgerBalance")
    private Integer currentLedgerBalance;
    @JsonProperty("freightValue")
    private Integer freightValue;
    @JsonProperty("sellerAddress")
    private String sellerAddress;
    @JsonProperty("sellerAddressMetadata")
    private SellerAddressMetadata sellerAddressMetadata;
    @JsonProperty("sellerCompanyName")
    private String sellerCompanyName;
    @JsonProperty("deliveryType")
    private String deliveryType;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("customerOrderId")
    public Object getCustomerOrderId() {
        return customerOrderId;
    }

    @JsonProperty("customerOrderId")
    public void setCustomerOrderId(Object customerOrderId) {
        this.customerOrderId = customerOrderId;
    }

    @JsonProperty("requestAmount")
    public Double getRequestAmount() {
        return requestAmount;
    }

    @JsonProperty("requestAmount")
    public void setRequestAmount(Double requestAmount) {
        this.requestAmount = requestAmount;
    }

    @JsonProperty("transactionStatus")
    public String getTransactionStatus() {
        return transactionStatus;
    }

    @JsonProperty("transactionStatus")
    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    @JsonProperty("psLineItemsDetails")
    public List<PsLineItemsDetail> getPsLineItemsDetails() {
        return psLineItemsDetails;
    }

    @JsonProperty("psLineItemsDetails")
    public void setPsLineItemsDetails(List<PsLineItemsDetail> psLineItemsDetails) {
        this.psLineItemsDetails = psLineItemsDetails;
    }

    @JsonProperty("currentLedgerBalance")
    public Integer getCurrentLedgerBalance() {
        return currentLedgerBalance;
    }

    @JsonProperty("currentLedgerBalance")
    public void setCurrentLedgerBalance(Integer currentLedgerBalance) {
        this.currentLedgerBalance = currentLedgerBalance;
    }

    @JsonProperty("freightValue")
    public Integer getFreightValue() {
        return freightValue;
    }

    @JsonProperty("freightValue")
    public void setFreightValue(Integer freightValue) {
        this.freightValue = freightValue;
    }

    @JsonProperty("sellerAddress")
    public String getSellerAddress() {
        return sellerAddress;
    }

    @JsonProperty("sellerAddress")
    public void setSellerAddress(String sellerAddress) {
        this.sellerAddress = sellerAddress;
    }

    @JsonProperty("sellerAddressMetadata")
    public SellerAddressMetadata getSellerAddressMetadata() {
        return sellerAddressMetadata;
    }

    @JsonProperty("sellerAddressMetadata")
    public void setSellerAddressMetadata(SellerAddressMetadata sellerAddressMetadata) {
        this.sellerAddressMetadata = sellerAddressMetadata;
    }

    @JsonProperty("sellerCompanyName")
    public String getSellerCompanyName() {
        return sellerCompanyName;
    }

    @JsonProperty("sellerCompanyName")
    public void setSellerCompanyName(String sellerCompanyName) {
        this.sellerCompanyName = sellerCompanyName;
    }

    @JsonProperty("deliveryType")
    public String getDeliveryType() {
        return deliveryType;
    }

    @JsonProperty("deliveryType")
    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
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
        sb.append(CreateDo.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("customerOrderId");
        sb.append('=');
        sb.append(((this.customerOrderId == null)?"<null>":this.customerOrderId));
        sb.append(',');
        sb.append("requestAmount");
        sb.append('=');
        sb.append(((this.requestAmount == null)?"<null>":this.requestAmount));
        sb.append(',');
        sb.append("transactionStatus");
        sb.append('=');
        sb.append(((this.transactionStatus == null)?"<null>":this.transactionStatus));
        sb.append(',');
        sb.append("psLineItemsDetails");
        sb.append('=');
        sb.append(((this.psLineItemsDetails == null)?"<null>":this.psLineItemsDetails));
        sb.append(',');
        sb.append("currentLedgerBalance");
        sb.append('=');
        sb.append(((this.currentLedgerBalance == null)?"<null>":this.currentLedgerBalance));
        sb.append(',');
        sb.append("freightValue");
        sb.append('=');
        sb.append(((this.freightValue == null)?"<null>":this.freightValue));
        sb.append(',');
        sb.append("sellerAddress");
        sb.append('=');
        sb.append(((this.sellerAddress == null)?"<null>":this.sellerAddress));
        sb.append(',');
        sb.append("sellerAddressMetadata");
        sb.append('=');
        sb.append(((this.sellerAddressMetadata == null)?"<null>":this.sellerAddressMetadata));
        sb.append(',');
        sb.append("sellerCompanyName");
        sb.append('=');
        sb.append(((this.sellerCompanyName == null)?"<null>":this.sellerCompanyName));
        sb.append(',');
        sb.append("deliveryType");
        sb.append('=');
        sb.append(((this.deliveryType == null)?"<null>":this.deliveryType));
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
        result = ((result* 31)+((this.psLineItemsDetails == null)? 0 :this.psLineItemsDetails.hashCode()));
        result = ((result* 31)+((this.sellerAddressMetadata == null)? 0 :this.sellerAddressMetadata.hashCode()));
        result = ((result* 31)+((this.currentLedgerBalance == null)? 0 :this.currentLedgerBalance.hashCode()));
        result = ((result* 31)+((this.transactionStatus == null)? 0 :this.transactionStatus.hashCode()));
        result = ((result* 31)+((this.customerOrderId == null)? 0 :this.customerOrderId.hashCode()));
        result = ((result* 31)+((this.deliveryType == null)? 0 :this.deliveryType.hashCode()));
        result = ((result* 31)+((this.freightValue == null)? 0 :this.freightValue.hashCode()));
        result = ((result* 31)+((this.sellerAddress == null)? 0 :this.sellerAddress.hashCode()));
        result = ((result* 31)+((this.sellerCompanyName == null)? 0 :this.sellerCompanyName.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.requestAmount == null)? 0 :this.requestAmount.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof CreateDo) == false) {
            return false;
        }
        CreateDo rhs = ((CreateDo) other);
        return ((((((((((((this.psLineItemsDetails == rhs.psLineItemsDetails)||((this.psLineItemsDetails!= null)&&this.psLineItemsDetails.equals(rhs.psLineItemsDetails)))&&((this.sellerAddressMetadata == rhs.sellerAddressMetadata)||((this.sellerAddressMetadata!= null)&&this.sellerAddressMetadata.equals(rhs.sellerAddressMetadata))))&&((this.currentLedgerBalance == rhs.currentLedgerBalance)||((this.currentLedgerBalance!= null)&&this.currentLedgerBalance.equals(rhs.currentLedgerBalance))))&&((this.transactionStatus == rhs.transactionStatus)||((this.transactionStatus!= null)&&this.transactionStatus.equals(rhs.transactionStatus))))&&((this.customerOrderId == rhs.customerOrderId)||((this.customerOrderId!= null)&&this.customerOrderId.equals(rhs.customerOrderId))))&&((this.deliveryType == rhs.deliveryType)||((this.deliveryType!= null)&&this.deliveryType.equals(rhs.deliveryType))))&&((this.freightValue == rhs.freightValue)||((this.freightValue!= null)&&this.freightValue.equals(rhs.freightValue))))&&((this.sellerAddress == rhs.sellerAddress)||((this.sellerAddress!= null)&&this.sellerAddress.equals(rhs.sellerAddress))))&&((this.sellerCompanyName == rhs.sellerCompanyName)||((this.sellerCompanyName!= null)&&this.sellerCompanyName.equals(rhs.sellerCompanyName))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.requestAmount == rhs.requestAmount)||((this.requestAmount!= null)&&this.requestAmount.equals(rhs.requestAmount))));
    }

}


package com.qapitol.jsw.apitest.pojos.order.createIncomingPayment;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "acquirer_data",
    "amount",
    "amount_refunded",
    "bank",
    "base_amount",
    "captured",
    "card_id",
    "contact",
    "created_at",
    "currency",
    "description",
    "email",
    "entity",
    "error_code",
    "error_description",
    "error_reason",
    "error_source",
    "error_step",
    "fee",
    "id",
    "international",
    "invoice_id",
    "method",
    "notes",
    "order_id",
    "refund_status",
    "reward",
    "status",
    "tax",
    "vpa",
    "wallet"
})
@Generated("jsonschema2pojo")
public class Entity {

    @JsonProperty("acquirer_data")
    private AcquirerData acquirerData;
    @JsonProperty("amount")
    private Integer amount;
    @JsonProperty("amount_refunded")
    private Integer amountRefunded;
    @JsonProperty("bank")
    private String bank;
    @JsonProperty("base_amount")
    private Integer baseAmount;
    @JsonProperty("captured")
    private Boolean captured;
    @JsonProperty("card_id")
    private Object cardId;
    @JsonProperty("contact")
    private String contact;
    @JsonProperty("created_at")
    private Integer createdAt;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("description")
    private Object description;
    @JsonProperty("email")
    private String email;
    @JsonProperty("entity")
    private String entity;
    @JsonProperty("error_code")
    private Object errorCode;
    @JsonProperty("error_description")
    private Object errorDescription;
    @JsonProperty("error_reason")
    private Object errorReason;
    @JsonProperty("error_source")
    private Object errorSource;
    @JsonProperty("error_step")
    private Object errorStep;
    @JsonProperty("fee")
    private Integer fee;
    @JsonProperty("id")
    private String id;
    @JsonProperty("international")
    private Boolean international;
    @JsonProperty("invoice_id")
    private Object invoiceId;
    @JsonProperty("method")
    private String method;
    @JsonProperty("notes")
    private Notes notes;
    @JsonProperty("order_id")
    private String orderId;
    @JsonProperty("refund_status")
    private Object refundStatus;
    @JsonProperty("reward")
    private Object reward;
    @JsonProperty("status")
    private String status;
    @JsonProperty("tax")
    private Integer tax;
    @JsonProperty("vpa")
    private Object vpa;
    @JsonProperty("wallet")
    private Object wallet;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("acquirer_data")
    public AcquirerData getAcquirerData() {
        return acquirerData;
    }

    @JsonProperty("acquirer_data")
    public void setAcquirerData(AcquirerData acquirerData) {
        this.acquirerData = acquirerData;
    }

    @JsonProperty("amount")
    public Integer getAmount() {
        return amount;
    }

    @JsonProperty("amount")
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @JsonProperty("amount_refunded")
    public Integer getAmountRefunded() {
        return amountRefunded;
    }

    @JsonProperty("amount_refunded")
    public void setAmountRefunded(Integer amountRefunded) {
        this.amountRefunded = amountRefunded;
    }

    @JsonProperty("bank")
    public String getBank() {
        return bank;
    }

    @JsonProperty("bank")
    public void setBank(String bank) {
        this.bank = bank;
    }

    @JsonProperty("base_amount")
    public Integer getBaseAmount() {
        return baseAmount;
    }

    @JsonProperty("base_amount")
    public void setBaseAmount(Integer baseAmount) {
        this.baseAmount = baseAmount;
    }

    @JsonProperty("captured")
    public Boolean getCaptured() {
        return captured;
    }

    @JsonProperty("captured")
    public void setCaptured(Boolean captured) {
        this.captured = captured;
    }

    @JsonProperty("card_id")
    public Object getCardId() {
        return cardId;
    }

    @JsonProperty("card_id")
    public void setCardId(Object cardId) {
        this.cardId = cardId;
    }

    @JsonProperty("contact")
    public String getContact() {
        return contact;
    }

    @JsonProperty("contact")
    public void setContact(String contact) {
        this.contact = contact;
    }

    @JsonProperty("created_at")
    public Integer getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("created_at")
    public void setCreatedAt(Integer createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("currency")
    public String getCurrency() {
        return currency;
    }

    @JsonProperty("currency")
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @JsonProperty("description")
    public Object getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(Object description) {
        this.description = description;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("entity")
    public String getEntity() {
        return entity;
    }

    @JsonProperty("entity")
    public void setEntity(String entity) {
        this.entity = entity;
    }

    @JsonProperty("error_code")
    public Object getErrorCode() {
        return errorCode;
    }

    @JsonProperty("error_code")
    public void setErrorCode(Object errorCode) {
        this.errorCode = errorCode;
    }

    @JsonProperty("error_description")
    public Object getErrorDescription() {
        return errorDescription;
    }

    @JsonProperty("error_description")
    public void setErrorDescription(Object errorDescription) {
        this.errorDescription = errorDescription;
    }

    @JsonProperty("error_reason")
    public Object getErrorReason() {
        return errorReason;
    }

    @JsonProperty("error_reason")
    public void setErrorReason(Object errorReason) {
        this.errorReason = errorReason;
    }

    @JsonProperty("error_source")
    public Object getErrorSource() {
        return errorSource;
    }

    @JsonProperty("error_source")
    public void setErrorSource(Object errorSource) {
        this.errorSource = errorSource;
    }

    @JsonProperty("error_step")
    public Object getErrorStep() {
        return errorStep;
    }

    @JsonProperty("error_step")
    public void setErrorStep(Object errorStep) {
        this.errorStep = errorStep;
    }

    @JsonProperty("fee")
    public Integer getFee() {
        return fee;
    }

    @JsonProperty("fee")
    public void setFee(Integer fee) {
        this.fee = fee;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("international")
    public Boolean getInternational() {
        return international;
    }

    @JsonProperty("international")
    public void setInternational(Boolean international) {
        this.international = international;
    }

    @JsonProperty("invoice_id")
    public Object getInvoiceId() {
        return invoiceId;
    }

    @JsonProperty("invoice_id")
    public void setInvoiceId(Object invoiceId) {
        this.invoiceId = invoiceId;
    }

    @JsonProperty("method")
    public String getMethod() {
        return method;
    }

    @JsonProperty("method")
    public void setMethod(String method) {
        this.method = method;
    }

    @JsonProperty("notes")
    public Notes getNotes() {
        return notes;
    }

    @JsonProperty("notes")
    public void setNotes(Notes notes) {
        this.notes = notes;
    }

    @JsonProperty("order_id")
    public String getOrderId() {
        return orderId;
    }

    @JsonProperty("order_id")
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @JsonProperty("refund_status")
    public Object getRefundStatus() {
        return refundStatus;
    }

    @JsonProperty("refund_status")
    public void setRefundStatus(Object refundStatus) {
        this.refundStatus = refundStatus;
    }

    @JsonProperty("reward")
    public Object getReward() {
        return reward;
    }

    @JsonProperty("reward")
    public void setReward(Object reward) {
        this.reward = reward;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("tax")
    public Integer getTax() {
        return tax;
    }

    @JsonProperty("tax")
    public void setTax(Integer tax) {
        this.tax = tax;
    }

    @JsonProperty("vpa")
    public Object getVpa() {
        return vpa;
    }

    @JsonProperty("vpa")
    public void setVpa(Object vpa) {
        this.vpa = vpa;
    }

    @JsonProperty("wallet")
    public Object getWallet() {
        return wallet;
    }

    @JsonProperty("wallet")
    public void setWallet(Object wallet) {
        this.wallet = wallet;
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
        sb.append(Entity.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("acquirerData");
        sb.append('=');
        sb.append(((this.acquirerData == null)?"<null>":this.acquirerData));
        sb.append(',');
        sb.append("amount");
        sb.append('=');
        sb.append(((this.amount == null)?"<null>":this.amount));
        sb.append(',');
        sb.append("amountRefunded");
        sb.append('=');
        sb.append(((this.amountRefunded == null)?"<null>":this.amountRefunded));
        sb.append(',');
        sb.append("bank");
        sb.append('=');
        sb.append(((this.bank == null)?"<null>":this.bank));
        sb.append(',');
        sb.append("baseAmount");
        sb.append('=');
        sb.append(((this.baseAmount == null)?"<null>":this.baseAmount));
        sb.append(',');
        sb.append("captured");
        sb.append('=');
        sb.append(((this.captured == null)?"<null>":this.captured));
        sb.append(',');
        sb.append("cardId");
        sb.append('=');
        sb.append(((this.cardId == null)?"<null>":this.cardId));
        sb.append(',');
        sb.append("contact");
        sb.append('=');
        sb.append(((this.contact == null)?"<null>":this.contact));
        sb.append(',');
        sb.append("createdAt");
        sb.append('=');
        sb.append(((this.createdAt == null)?"<null>":this.createdAt));
        sb.append(',');
        sb.append("currency");
        sb.append('=');
        sb.append(((this.currency == null)?"<null>":this.currency));
        sb.append(',');
        sb.append("description");
        sb.append('=');
        sb.append(((this.description == null)?"<null>":this.description));
        sb.append(',');
        sb.append("email");
        sb.append('=');
        sb.append(((this.email == null)?"<null>":this.email));
        sb.append(',');
        sb.append("entity");
        sb.append('=');
        sb.append(((this.entity == null)?"<null>":this.entity));
        sb.append(',');
        sb.append("errorCode");
        sb.append('=');
        sb.append(((this.errorCode == null)?"<null>":this.errorCode));
        sb.append(',');
        sb.append("errorDescription");
        sb.append('=');
        sb.append(((this.errorDescription == null)?"<null>":this.errorDescription));
        sb.append(',');
        sb.append("errorReason");
        sb.append('=');
        sb.append(((this.errorReason == null)?"<null>":this.errorReason));
        sb.append(',');
        sb.append("errorSource");
        sb.append('=');
        sb.append(((this.errorSource == null)?"<null>":this.errorSource));
        sb.append(',');
        sb.append("errorStep");
        sb.append('=');
        sb.append(((this.errorStep == null)?"<null>":this.errorStep));
        sb.append(',');
        sb.append("fee");
        sb.append('=');
        sb.append(((this.fee == null)?"<null>":this.fee));
        sb.append(',');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("international");
        sb.append('=');
        sb.append(((this.international == null)?"<null>":this.international));
        sb.append(',');
        sb.append("invoiceId");
        sb.append('=');
        sb.append(((this.invoiceId == null)?"<null>":this.invoiceId));
        sb.append(',');
        sb.append("method");
        sb.append('=');
        sb.append(((this.method == null)?"<null>":this.method));
        sb.append(',');
        sb.append("notes");
        sb.append('=');
        sb.append(((this.notes == null)?"<null>":this.notes));
        sb.append(',');
        sb.append("orderId");
        sb.append('=');
        sb.append(((this.orderId == null)?"<null>":this.orderId));
        sb.append(',');
        sb.append("refundStatus");
        sb.append('=');
        sb.append(((this.refundStatus == null)?"<null>":this.refundStatus));
        sb.append(',');
        sb.append("reward");
        sb.append('=');
        sb.append(((this.reward == null)?"<null>":this.reward));
        sb.append(',');
        sb.append("status");
        sb.append('=');
        sb.append(((this.status == null)?"<null>":this.status));
        sb.append(',');
        sb.append("tax");
        sb.append('=');
        sb.append(((this.tax == null)?"<null>":this.tax));
        sb.append(',');
        sb.append("vpa");
        sb.append('=');
        sb.append(((this.vpa == null)?"<null>":this.vpa));
        sb.append(',');
        sb.append("wallet");
        sb.append('=');
        sb.append(((this.wallet == null)?"<null>":this.wallet));
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
        result = ((result* 31)+((this.notes == null)? 0 :this.notes.hashCode()));
        result = ((result* 31)+((this.errorDescription == null)? 0 :this.errorDescription.hashCode()));
        result = ((result* 31)+((this.errorSource == null)? 0 :this.errorSource.hashCode()));
        result = ((result* 31)+((this.orderId == null)? 0 :this.orderId.hashCode()));
        result = ((result* 31)+((this.fee == null)? 0 :this.fee.hashCode()));
        result = ((result* 31)+((this.description == null)? 0 :this.description.hashCode()));
        result = ((result* 31)+((this.errorCode == null)? 0 :this.errorCode.hashCode()));
        result = ((result* 31)+((this.refundStatus == null)? 0 :this.refundStatus.hashCode()));
        result = ((result* 31)+((this.errorStep == null)? 0 :this.errorStep.hashCode()));
        result = ((result* 31)+((this.createdAt == null)? 0 :this.createdAt.hashCode()));
        result = ((result* 31)+((this.bank == null)? 0 :this.bank.hashCode()));
        result = ((result* 31)+((this.amountRefunded == null)? 0 :this.amountRefunded.hashCode()));
        result = ((result* 31)+((this.captured == null)? 0 :this.captured.hashCode()));
        result = ((result* 31)+((this.contact == null)? 0 :this.contact.hashCode()));
        result = ((result* 31)+((this.errorReason == null)? 0 :this.errorReason.hashCode()));
        result = ((result* 31)+((this.currency == null)? 0 :this.currency.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.international == null)? 0 :this.international.hashCode()));
        result = ((result* 31)+((this.email == null)? 0 :this.email.hashCode()));
        result = ((result* 31)+((this.reward == null)? 0 :this.reward.hashCode()));
        result = ((result* 31)+((this.amount == null)? 0 :this.amount.hashCode()));
        result = ((result* 31)+((this.wallet == null)? 0 :this.wallet.hashCode()));
        result = ((result* 31)+((this.method == null)? 0 :this.method.hashCode()));
        result = ((result* 31)+((this.vpa == null)? 0 :this.vpa.hashCode()));
        result = ((result* 31)+((this.tax == null)? 0 :this.tax.hashCode()));
        result = ((result* 31)+((this.baseAmount == null)? 0 :this.baseAmount.hashCode()));
        result = ((result* 31)+((this.acquirerData == null)? 0 :this.acquirerData.hashCode()));
        result = ((result* 31)+((this.cardId == null)? 0 :this.cardId.hashCode()));
        result = ((result* 31)+((this.invoiceId == null)? 0 :this.invoiceId.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.entity == null)? 0 :this.entity.hashCode()));
        result = ((result* 31)+((this.status == null)? 0 :this.status.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Entity) == false) {
            return false;
        }
        Entity rhs = ((Entity) other);
        return (((((((((((((((((((((((((((((((((this.notes == rhs.notes)||((this.notes!= null)&&this.notes.equals(rhs.notes)))&&((this.errorDescription == rhs.errorDescription)||((this.errorDescription!= null)&&this.errorDescription.equals(rhs.errorDescription))))&&((this.errorSource == rhs.errorSource)||((this.errorSource!= null)&&this.errorSource.equals(rhs.errorSource))))&&((this.orderId == rhs.orderId)||((this.orderId!= null)&&this.orderId.equals(rhs.orderId))))&&((this.fee == rhs.fee)||((this.fee!= null)&&this.fee.equals(rhs.fee))))&&((this.description == rhs.description)||((this.description!= null)&&this.description.equals(rhs.description))))&&((this.errorCode == rhs.errorCode)||((this.errorCode!= null)&&this.errorCode.equals(rhs.errorCode))))&&((this.refundStatus == rhs.refundStatus)||((this.refundStatus!= null)&&this.refundStatus.equals(rhs.refundStatus))))&&((this.errorStep == rhs.errorStep)||((this.errorStep!= null)&&this.errorStep.equals(rhs.errorStep))))&&((this.createdAt == rhs.createdAt)||((this.createdAt!= null)&&this.createdAt.equals(rhs.createdAt))))&&((this.bank == rhs.bank)||((this.bank!= null)&&this.bank.equals(rhs.bank))))&&((this.amountRefunded == rhs.amountRefunded)||((this.amountRefunded!= null)&&this.amountRefunded.equals(rhs.amountRefunded))))&&((this.captured == rhs.captured)||((this.captured!= null)&&this.captured.equals(rhs.captured))))&&((this.contact == rhs.contact)||((this.contact!= null)&&this.contact.equals(rhs.contact))))&&((this.errorReason == rhs.errorReason)||((this.errorReason!= null)&&this.errorReason.equals(rhs.errorReason))))&&((this.currency == rhs.currency)||((this.currency!= null)&&this.currency.equals(rhs.currency))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.international == rhs.international)||((this.international!= null)&&this.international.equals(rhs.international))))&&((this.email == rhs.email)||((this.email!= null)&&this.email.equals(rhs.email))))&&((this.reward == rhs.reward)||((this.reward!= null)&&this.reward.equals(rhs.reward))))&&((this.amount == rhs.amount)||((this.amount!= null)&&this.amount.equals(rhs.amount))))&&((this.wallet == rhs.wallet)||((this.wallet!= null)&&this.wallet.equals(rhs.wallet))))&&((this.method == rhs.method)||((this.method!= null)&&this.method.equals(rhs.method))))&&((this.vpa == rhs.vpa)||((this.vpa!= null)&&this.vpa.equals(rhs.vpa))))&&((this.tax == rhs.tax)||((this.tax!= null)&&this.tax.equals(rhs.tax))))&&((this.baseAmount == rhs.baseAmount)||((this.baseAmount!= null)&&this.baseAmount.equals(rhs.baseAmount))))&&((this.acquirerData == rhs.acquirerData)||((this.acquirerData!= null)&&this.acquirerData.equals(rhs.acquirerData))))&&((this.cardId == rhs.cardId)||((this.cardId!= null)&&this.cardId.equals(rhs.cardId))))&&((this.invoiceId == rhs.invoiceId)||((this.invoiceId!= null)&&this.invoiceId.equals(rhs.invoiceId))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.entity == rhs.entity)||((this.entity!= null)&&this.entity.equals(rhs.entity))))&&((this.status == rhs.status)||((this.status!= null)&&this.status.equals(rhs.status))));
    }

}

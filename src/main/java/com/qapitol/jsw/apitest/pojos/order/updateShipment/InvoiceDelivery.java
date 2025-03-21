
package com.qapitol.jsw.apitest.pojos.order.updateShipment;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "invoiceInfo",
    "invoiceNumber",
    "quantity",
    "amount",
    "invoicedDeliveryDate",
    "measurementId",
    "lineitemId",
    "shipmentId"
})
@Generated("jsonschema2pojo")
public class InvoiceDelivery {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("invoiceInfo")
    private InvoiceInfo invoiceInfo;
    @JsonProperty("invoiceNumber")
    private String invoiceNumber;
    @JsonProperty("quantity")
    private Integer quantity;
    @JsonProperty("amount")
    private Integer amount;
    @JsonProperty("invoicedDeliveryDate")
    private String invoicedDeliveryDate;
    @JsonProperty("measurementId")
    private Object measurementId;
    @JsonProperty("lineitemId")
    private Integer lineitemId;
    @JsonProperty("shipmentId")
    private Integer shipmentId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("invoiceInfo")
    public InvoiceInfo getInvoiceInfo() {
        return invoiceInfo;
    }

    @JsonProperty("invoiceInfo")
    public void setInvoiceInfo(InvoiceInfo invoiceInfo) {
        this.invoiceInfo = invoiceInfo;
    }

    @JsonProperty("invoiceNumber")
    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    @JsonProperty("invoiceNumber")
    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    @JsonProperty("quantity")
    public Integer getQuantity() {
        return quantity;
    }

    @JsonProperty("quantity")
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @JsonProperty("amount")
    public Integer getAmount() {
        return amount;
    }

    @JsonProperty("amount")
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @JsonProperty("invoicedDeliveryDate")
    public String getInvoicedDeliveryDate() {
        return invoicedDeliveryDate;
    }

    @JsonProperty("invoicedDeliveryDate")
    public void setInvoicedDeliveryDate(String invoicedDeliveryDate) {
        this.invoicedDeliveryDate = invoicedDeliveryDate;
    }

    @JsonProperty("measurementId")
    public Object getMeasurementId() {
        return measurementId;
    }

    @JsonProperty("measurementId")
    public void setMeasurementId(Object measurementId) {
        this.measurementId = measurementId;
    }

    @JsonProperty("lineitemId")
    public Integer getLineitemId() {
        return lineitemId;
    }

    @JsonProperty("lineitemId")
    public void setLineitemId(Integer lineitemId) {
        this.lineitemId = lineitemId;
    }

    @JsonProperty("shipmentId")
    public Integer getShipmentId() {
        return shipmentId;
    }

    @JsonProperty("shipmentId")
    public void setShipmentId(Integer shipmentId) {
        this.shipmentId = shipmentId;
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
        sb.append(InvoiceDelivery.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("invoiceInfo");
        sb.append('=');
        sb.append(((this.invoiceInfo == null)?"<null>":this.invoiceInfo));
        sb.append(',');
        sb.append("invoiceNumber");
        sb.append('=');
        sb.append(((this.invoiceNumber == null)?"<null>":this.invoiceNumber));
        sb.append(',');
        sb.append("quantity");
        sb.append('=');
        sb.append(((this.quantity == null)?"<null>":this.quantity));
        sb.append(',');
        sb.append("amount");
        sb.append('=');
        sb.append(((this.amount == null)?"<null>":this.amount));
        sb.append(',');
        sb.append("invoicedDeliveryDate");
        sb.append('=');
        sb.append(((this.invoicedDeliveryDate == null)?"<null>":this.invoicedDeliveryDate));
        sb.append(',');
        sb.append("measurementId");
        sb.append('=');
        sb.append(((this.measurementId == null)?"<null>":this.measurementId));
        sb.append(',');
        sb.append("lineitemId");
        sb.append('=');
        sb.append(((this.lineitemId == null)?"<null>":this.lineitemId));
        sb.append(',');
        sb.append("shipmentId");
        sb.append('=');
        sb.append(((this.shipmentId == null)?"<null>":this.shipmentId));
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
        result = ((result* 31)+((this.lineitemId == null)? 0 :this.lineitemId.hashCode()));
        result = ((result* 31)+((this.invoiceInfo == null)? 0 :this.invoiceInfo.hashCode()));
        result = ((result* 31)+((this.amount == null)? 0 :this.amount.hashCode()));
        result = ((result* 31)+((this.quantity == null)? 0 :this.quantity.hashCode()));
        result = ((result* 31)+((this.shipmentId == null)? 0 :this.shipmentId.hashCode()));
        result = ((result* 31)+((this.invoiceNumber == null)? 0 :this.invoiceNumber.hashCode()));
        result = ((result* 31)+((this.invoicedDeliveryDate == null)? 0 :this.invoicedDeliveryDate.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.measurementId == null)? 0 :this.measurementId.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof InvoiceDelivery) == false) {
            return false;
        }
        InvoiceDelivery rhs = ((InvoiceDelivery) other);
        return (((((((((((this.lineitemId == rhs.lineitemId)||((this.lineitemId!= null)&&this.lineitemId.equals(rhs.lineitemId)))&&((this.invoiceInfo == rhs.invoiceInfo)||((this.invoiceInfo!= null)&&this.invoiceInfo.equals(rhs.invoiceInfo))))&&((this.amount == rhs.amount)||((this.amount!= null)&&this.amount.equals(rhs.amount))))&&((this.quantity == rhs.quantity)||((this.quantity!= null)&&this.quantity.equals(rhs.quantity))))&&((this.shipmentId == rhs.shipmentId)||((this.shipmentId!= null)&&this.shipmentId.equals(rhs.shipmentId))))&&((this.invoiceNumber == rhs.invoiceNumber)||((this.invoiceNumber!= null)&&this.invoiceNumber.equals(rhs.invoiceNumber))))&&((this.invoicedDeliveryDate == rhs.invoicedDeliveryDate)||((this.invoicedDeliveryDate!= null)&&this.invoicedDeliveryDate.equals(rhs.invoicedDeliveryDate))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.measurementId == rhs.measurementId)||((this.measurementId!= null)&&this.measurementId.equals(rhs.measurementId))));
    }

}

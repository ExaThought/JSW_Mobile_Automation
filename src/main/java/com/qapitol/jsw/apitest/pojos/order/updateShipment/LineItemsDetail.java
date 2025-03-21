
package com.qapitol.jsw.apitest.pojos.order.updateShipment;

import com.fasterxml.jackson.annotation.*;
import com.qapitol.jsw.apitest.pojos.order.createShipment.PlannedDelivery;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "lineitemId",
    "productInfo",
    "plannedDelivery",
    "invoiceDelivery",
    "gst",
    "taxType"
})
@Generated("jsonschema2pojo")
public class LineItemsDetail {

    @JsonProperty("lineitemId")
    private Integer lineitemId;
    @JsonProperty("productInfo")
    private ProductInfo productInfo;
    @JsonProperty("plannedDelivery")
    private PlannedDelivery plannedDelivery;
    @JsonProperty("invoiceDelivery")
    private InvoiceDelivery invoiceDelivery;
    @JsonProperty("gst")
    private Double gst;
    @JsonProperty("taxType")
    private String taxType;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("lineitemId")
    public Integer getLineitemId() {
        return lineitemId;
    }

    @JsonProperty("lineitemId")
    public void setLineitemId(Integer lineitemId) {
        this.lineitemId = lineitemId;
    }

    @JsonProperty("productInfo")
    public ProductInfo getProductInfo() {
        return productInfo;
    }

    @JsonProperty("productInfo")
    public void setProductInfo(ProductInfo productInfo) {
        this.productInfo = productInfo;
    }

    @JsonProperty("plannedDelivery")
    public PlannedDelivery getPlannedDelivery() {
        return plannedDelivery;
    }

    @JsonProperty("plannedDelivery")
    public void setPlannedDelivery(PlannedDelivery plannedDelivery) {
        this.plannedDelivery = plannedDelivery;
    }

    @JsonProperty("invoiceDelivery")
    public InvoiceDelivery getInvoiceDelivery() {
        return invoiceDelivery;
    }

    @JsonProperty("invoiceDelivery")
    public void setInvoiceDelivery(InvoiceDelivery invoiceDelivery) {
        this.invoiceDelivery = invoiceDelivery;
    }

    @JsonProperty("gst")
    public Double getGst() {
        return gst;
    }

    @JsonProperty("gst")
    public void setGst(Double gst) {
        this.gst = gst;
    }

    @JsonProperty("taxType")
    public String getTaxType() {
        return taxType;
    }

    @JsonProperty("taxType")
    public void setTaxType(String taxType) {
        this.taxType = taxType;
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
        sb.append(LineItemsDetail.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("lineitemId");
        sb.append('=');
        sb.append(((this.lineitemId == null)?"<null>":this.lineitemId));
        sb.append(',');
        sb.append("productInfo");
        sb.append('=');
        sb.append(((this.productInfo == null)?"<null>":this.productInfo));
        sb.append(',');
        sb.append("plannedDelivery");
        sb.append('=');
        sb.append(((this.plannedDelivery == null)?"<null>":this.plannedDelivery));
        sb.append(',');
        sb.append("invoiceDelivery");
        sb.append('=');
        sb.append(((this.invoiceDelivery == null)?"<null>":this.invoiceDelivery));
        sb.append(',');
        sb.append("gst");
        sb.append('=');
        sb.append(((this.gst == null)?"<null>":this.gst));
        sb.append(',');
        sb.append("taxType");
        sb.append('=');
        sb.append(((this.taxType == null)?"<null>":this.taxType));
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
        result = ((result* 31)+((this.gst == null)? 0 :this.gst.hashCode()));
        result = ((result* 31)+((this.plannedDelivery == null)? 0 :this.plannedDelivery.hashCode()));
        result = ((result* 31)+((this.invoiceDelivery == null)? 0 :this.invoiceDelivery.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.taxType == null)? 0 :this.taxType.hashCode()));
        result = ((result* 31)+((this.productInfo == null)? 0 :this.productInfo.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof LineItemsDetail) == false) {
            return false;
        }
        LineItemsDetail rhs = ((LineItemsDetail) other);
        return ((((((((this.lineitemId == rhs.lineitemId)||((this.lineitemId!= null)&&this.lineitemId.equals(rhs.lineitemId)))&&((this.gst == rhs.gst)||((this.gst!= null)&&this.gst.equals(rhs.gst))))&&((this.plannedDelivery == rhs.plannedDelivery)||((this.plannedDelivery!= null)&&this.plannedDelivery.equals(rhs.plannedDelivery))))&&((this.invoiceDelivery == rhs.invoiceDelivery)||((this.invoiceDelivery!= null)&&this.invoiceDelivery.equals(rhs.invoiceDelivery))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.taxType == rhs.taxType)||((this.taxType!= null)&&this.taxType.equals(rhs.taxType))))&&((this.productInfo == rhs.productInfo)||((this.productInfo!= null)&&this.productInfo.equals(rhs.productInfo))));
    }

}

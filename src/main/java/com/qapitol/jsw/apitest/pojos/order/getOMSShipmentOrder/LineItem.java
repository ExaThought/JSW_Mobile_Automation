
package com.qapitol.jsw.apitest.pojos.order.getOMSShipmentOrder;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "lineItemId",
    "quantity",
    "currentState",
    "productInfo",
    "alreadyPlannedQuantity",
    "purchaseOrderId",
    "variantMMId",
    "sourceSeller",
    "sellerShippingAddress",
        "plannedDelivery",
        "invoiceDelivery",
        "gst",
        "taxType"
})
@Generated("jsonschema2pojo")
public class LineItem {

    @JsonProperty("lineItemId")
    private Integer lineItemId;
    @JsonProperty("quantity")
    private Double quantity;
    @JsonProperty("currentState")
    private Object currentState;
    @JsonProperty("productInfo")
    private ProductInfo productInfo;
    @JsonProperty("alreadyPlannedQuantity")
    private Object alreadyPlannedQuantity;
    @JsonProperty("purchaseOrderId")
    private Object purchaseOrderId;
    @JsonProperty("variantMMId")
    private String variantMMId;
    @JsonProperty("sourceSeller")
    private Object sourceSeller;
    @JsonProperty("sellerShippingAddress")
    private SellerShippingAddress sellerShippingAddress;
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

    @JsonProperty("lineItemId")
    public Integer getLineItemId() {
        return lineItemId;
    }

    @JsonProperty("lineItemId")
    public void setLineItemId(Integer lineItemId) {
        this.lineItemId = lineItemId;
    }

    @JsonProperty("quantity")
    public Double getQuantity() {
        return quantity;
    }

    @JsonProperty("quantity")
    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    @JsonProperty("currentState")
    public Object getCurrentState() {
        return currentState;
    }

    @JsonProperty("currentState")
    public void setCurrentState(Object currentState) {
        this.currentState = currentState;
    }

    @JsonProperty("productInfo")
    public ProductInfo getProductInfo() {
        return productInfo;
    }

    @JsonProperty("productInfo")
    public void setProductInfo(ProductInfo productInfo) {
        this.productInfo = productInfo;
    }

    @JsonProperty("alreadyPlannedQuantity")
    public Object getAlreadyPlannedQuantity() {
        return alreadyPlannedQuantity;
    }

    @JsonProperty("alreadyPlannedQuantity")
    public void setAlreadyPlannedQuantity(Object alreadyPlannedQuantity) {
        this.alreadyPlannedQuantity = alreadyPlannedQuantity;
    }

    @JsonProperty("purchaseOrderId")
    public Object getPurchaseOrderId() {
        return purchaseOrderId;
    }

    @JsonProperty("purchaseOrderId")
    public void setPurchaseOrderId(Object purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    @JsonProperty("variantMMId")
    public String getVariantMMId() {
        return variantMMId;
    }

    @JsonProperty("variantMMId")
    public void setVariantMMId(String variantMMId) {
        this.variantMMId = variantMMId;
    }

    @JsonProperty("sourceSeller")
    public Object getSourceSeller() {
        return sourceSeller;
    }

    @JsonProperty("sourceSeller")
    public void setSourceSeller(Object sourceSeller) {
        this.sourceSeller = sourceSeller;
    }

    @JsonProperty("sellerShippingAddress")
    public SellerShippingAddress getSellerShippingAddress() {
        return sellerShippingAddress;
    }

    @JsonProperty("sellerShippingAddress")
    public void setSellerShippingAddress(SellerShippingAddress sellerShippingAddress) {
        this.sellerShippingAddress = sellerShippingAddress;
    }

    @JsonProperty("plannedDelivery")
    public PlannedDelivery getPlannedDelivery() {
        return this.plannedDelivery;
    }

    @JsonProperty("plannedDelivery")
    public void setPlannedDelivery(PlannedDelivery plannedDelivery) {
        this.plannedDelivery = plannedDelivery;
    }

    @JsonProperty("invoiceDelivery")
    public InvoiceDelivery getInvoiceDelivery() {
        return this.invoiceDelivery;
    }

    @JsonProperty("invoiceDelivery")
    public void setInvoiceDelivery(InvoiceDelivery invoiceDelivery) {
        this.invoiceDelivery = invoiceDelivery;
    }

    @JsonProperty("gst")
    public Double getGst() {
        return this.gst;
    }

    @JsonProperty("gst")
    public void setGst(Double gst) {
        this.gst = gst;
    }

    @JsonProperty("taxType")
    public String getTaxType() {
        return this.taxType;
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
        sb.append(LineItem.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("lineItemId");
        sb.append('=');
        sb.append(((this.lineItemId == null)?"<null>":this.lineItemId));
        sb.append(',');
        sb.append("quantity");
        sb.append('=');
        sb.append(((this.quantity == null)?"<null>":this.quantity));
        sb.append(',');
        sb.append("currentState");
        sb.append('=');
        sb.append(((this.currentState == null)?"<null>":this.currentState));
        sb.append(',');
        sb.append("productInfo");
        sb.append('=');
        sb.append(((this.productInfo == null)?"<null>":this.productInfo));
        sb.append(',');
        sb.append("alreadyPlannedQuantity");
        sb.append('=');
        sb.append(((this.alreadyPlannedQuantity == null)?"<null>":this.alreadyPlannedQuantity));
        sb.append(',');
        sb.append("purchaseOrderId");
        sb.append('=');
        sb.append(((this.purchaseOrderId == null)?"<null>":this.purchaseOrderId));
        sb.append(',');
        sb.append("variantMMId");
        sb.append('=');
        sb.append(((this.variantMMId == null)?"<null>":this.variantMMId));
        sb.append(',');
        sb.append("sourceSeller");
        sb.append('=');
        sb.append(((this.sourceSeller == null)?"<null>":this.sourceSeller));
        sb.append(',');
        sb.append("sellerShippingAddress");
        sb.append('=');
        sb.append(((this.sellerShippingAddress == null)?"<null>":this.sellerShippingAddress));
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
        result = ((result* 31)+((this.sellerShippingAddress == null)? 0 :this.sellerShippingAddress.hashCode()));
        result = ((result* 31)+((this.quantity == null)? 0 :this.quantity.hashCode()));
        result = ((result* 31)+((this.sourceSeller == null)? 0 :this.sourceSeller.hashCode()));
        result = ((result* 31)+((this.lineItemId == null)? 0 :this.lineItemId.hashCode()));
        result = ((result* 31)+((this.purchaseOrderId == null)? 0 :this.purchaseOrderId.hashCode()));
        result = ((result* 31)+((this.variantMMId == null)? 0 :this.variantMMId.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.currentState == null)? 0 :this.currentState.hashCode()));
        result = ((result* 31)+((this.alreadyPlannedQuantity == null)? 0 :this.alreadyPlannedQuantity.hashCode()));
        result = ((result* 31)+((this.productInfo == null)? 0 :this.productInfo.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof LineItem) == false) {
            return false;
        }
        LineItem rhs = ((LineItem) other);
        return (((((((((((this.sellerShippingAddress == rhs.sellerShippingAddress)||((this.sellerShippingAddress!= null)&&this.sellerShippingAddress.equals(rhs.sellerShippingAddress)))&&((this.quantity == rhs.quantity)||((this.quantity!= null)&&this.quantity.equals(rhs.quantity))))&&((this.sourceSeller == rhs.sourceSeller)||((this.sourceSeller!= null)&&this.sourceSeller.equals(rhs.sourceSeller))))&&((this.lineItemId == rhs.lineItemId)||((this.lineItemId!= null)&&this.lineItemId.equals(rhs.lineItemId))))&&((this.purchaseOrderId == rhs.purchaseOrderId)||((this.purchaseOrderId!= null)&&this.purchaseOrderId.equals(rhs.purchaseOrderId))))&&((this.variantMMId == rhs.variantMMId)||((this.variantMMId!= null)&&this.variantMMId.equals(rhs.variantMMId))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.currentState == rhs.currentState)||((this.currentState!= null)&&this.currentState.equals(rhs.currentState))))&&((this.alreadyPlannedQuantity == rhs.alreadyPlannedQuantity)||((this.alreadyPlannedQuantity!= null)&&this.alreadyPlannedQuantity.equals(rhs.alreadyPlannedQuantity))))&&((this.productInfo == rhs.productInfo)||((this.productInfo!= null)&&this.productInfo.equals(rhs.productInfo))));
    }

}

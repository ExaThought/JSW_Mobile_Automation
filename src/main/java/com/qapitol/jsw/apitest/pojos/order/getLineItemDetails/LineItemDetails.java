
package com.qapitol.jsw.apitest.pojos.order.getLineItemDetails;

import com.fasterxml.jackson.annotation.*;
import com.qapitol.jsw.apitest.pojos.order.getOMSShipmentOrder.SellerInfo;
import com.qapitol.jsw.apitest.pojos.order.updateShipment.ProductInfo;

import javax.annotation.processing.Generated;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "lineItemId",
    "productInfo",
    "measurementUnit",
    "measurementUnitDescription",
    "currentStatus",
    "nextLineItemStates",
    "sellerInfo",
    "channelInfo",
    "purchaseOrderId",
    "isNco",
    "ncoReason",
    "ncoFileUrl",
    "reasonForShortClose",
    "quantityOrdered",
    "quantityShipped",
    "quantityReturned",
    "quantityReplaced",
    "quantityShortClose",
    "quantityDelivered",
    "quantityCancelled",
    "quantityTolerance",
    "listPrice",
    "discountedPrice",
    "orderedValue",
    "orderedValueWithTax",
    "deliveredValue",
    "deliveredValueWithTax",
    "returnValue",
    "replaceValue",
    "serviceCost",
    "serviceCostWithTax",
    "serviceCostBreakup",
    "serviceCostBreakupLineItem",
    "variantMMId",
    "materialMasterSkuName",
    "lineItemShipments",
    "sourceSeller",
    "vendorMasterId",
    "sourceSellerVendorMasterId",
    "cashbackDetails",
    "orderNumber",
    "doReleasedQuantity"
})
@Generated("jsonschema2pojo")
public class LineItemDetails {

    @JsonProperty("lineItemId")
    private Integer lineItemId;
    @JsonProperty("productInfo")
    private ProductInfo productInfo;
    @JsonProperty("measurementUnit")
    private String measurementUnit;
    @JsonProperty("measurementUnitDescription")
    private String measurementUnitDescription;
    @JsonProperty("currentStatus")
    private CurrentStatus currentStatus;
    @JsonProperty("nextLineItemStates")
    private List<NextLineItemState> nextLineItemStates = new ArrayList<NextLineItemState>();
    @JsonProperty("sellerInfo")
    private SellerInfo sellerInfo;
    @JsonProperty("channelInfo")
    private Object channelInfo;
    @JsonProperty("purchaseOrderId")
    private Object purchaseOrderId;
    @JsonProperty("isNco")
    private Object isNco;
    @JsonProperty("ncoReason")
    private Object ncoReason;
    @JsonProperty("ncoFileUrl")
    private Object ncoFileUrl;
    @JsonProperty("reasonForShortClose")
    private Object reasonForShortClose;
    @JsonProperty("quantityOrdered")
    private Integer quantityOrdered;
    @JsonProperty("quantityShipped")
    private Double quantityShipped;
    @JsonProperty("quantityReturned")
    private Integer quantityReturned;
    @JsonProperty("quantityReplaced")
    private Integer quantityReplaced;
    @JsonProperty("quantityShortClose")
    private Integer quantityShortClose;
    @JsonProperty("quantityDelivered")
    private Double quantityDelivered;
    @JsonProperty("quantityCancelled")
    private Integer quantityCancelled;
    @JsonProperty("quantityTolerance")
    private Integer quantityTolerance;
    @JsonProperty("listPrice")
    private ListPrice listPrice;
    @JsonProperty("discountedPrice")
    private DiscountedPrice discountedPrice;
    @JsonProperty("orderedValue")
    private OrderedValue orderedValue;
    @JsonProperty("orderedValueWithTax")
    private OrderedValueWithTax orderedValueWithTax;
    @JsonProperty("deliveredValue")
    private DeliveredValue deliveredValue;
    @JsonProperty("deliveredValueWithTax")
    private DeliveredValueWithTax deliveredValueWithTax;
    @JsonProperty("returnValue")
    private ReturnValue returnValue;
    @JsonProperty("replaceValue")
    private ReplaceValue replaceValue;
    @JsonProperty("serviceCost")
    private ServiceCost serviceCost;
    @JsonProperty("serviceCostWithTax")
    private ServiceCostWithTax serviceCostWithTax;
    @JsonProperty("serviceCostBreakup")
    private ServiceCostBreakup serviceCostBreakup;
    @JsonProperty("serviceCostBreakupLineItem")
    private ServiceCostBreakupLineItem serviceCostBreakupLineItem;
    @JsonProperty("variantMMId")
    private String variantMMId;
    @JsonProperty("materialMasterSkuName")
    private String materialMasterSkuName;
    @JsonProperty("lineItemShipments")
    private List<LineItemShipment> lineItemShipments = new ArrayList<LineItemShipment>();
    @JsonProperty("sourceSeller")
    private SourceSeller sourceSeller;
    @JsonProperty("vendorMasterId")
    private String vendorMasterId;
    @JsonProperty("sourceSellerVendorMasterId")
    private String sourceSellerVendorMasterId;
    @JsonProperty("cashbackDetails")
    private CashbackDetails cashbackDetails;
    @JsonProperty("orderNumber")
    private Object orderNumber;
    @JsonProperty("doReleasedQuantity")
    private Double doReleasedQuantity;
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

    @JsonProperty("productInfo")
    public ProductInfo getProductInfo() {
        return productInfo;
    }

    @JsonProperty("productInfo")
    public void setProductInfo(ProductInfo productInfo) {
        this.productInfo = productInfo;
    }

    @JsonProperty("measurementUnit")
    public String getMeasurementUnit() {
        return measurementUnit;
    }

    @JsonProperty("measurementUnit")
    public void setMeasurementUnit(String measurementUnit) {
        this.measurementUnit = measurementUnit;
    }

    @JsonProperty("measurementUnitDescription")
    public String getMeasurementUnitDescription() {
        return measurementUnitDescription;
    }

    @JsonProperty("measurementUnitDescription")
    public void setMeasurementUnitDescription(String measurementUnitDescription) {
        this.measurementUnitDescription = measurementUnitDescription;
    }

    @JsonProperty("currentStatus")
    public CurrentStatus getCurrentStatus() {
        return currentStatus;
    }

    @JsonProperty("currentStatus")
    public void setCurrentStatus(CurrentStatus currentStatus) {
        this.currentStatus = currentStatus;
    }

    @JsonProperty("nextLineItemStates")
    public List<NextLineItemState> getNextLineItemStates() {
        return nextLineItemStates;
    }

    @JsonProperty("nextLineItemStates")
    public void setNextLineItemStates(List<NextLineItemState> nextLineItemStates) {
        this.nextLineItemStates = nextLineItemStates;
    }

    @JsonProperty("sellerInfo")
    public SellerInfo getSellerInfo() {
        return sellerInfo;
    }

    @JsonProperty("sellerInfo")
    public void setSellerInfo(SellerInfo sellerInfo) {
        this.sellerInfo = sellerInfo;
    }

    @JsonProperty("channelInfo")
    public Object getChannelInfo() {
        return channelInfo;
    }

    @JsonProperty("channelInfo")
    public void setChannelInfo(Object channelInfo) {
        this.channelInfo = channelInfo;
    }

    @JsonProperty("purchaseOrderId")
    public Object getPurchaseOrderId() {
        return purchaseOrderId;
    }

    @JsonProperty("purchaseOrderId")
    public void setPurchaseOrderId(Object purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    @JsonProperty("isNco")
    public Object getIsNco() {
        return isNco;
    }

    @JsonProperty("isNco")
    public void setIsNco(Object isNco) {
        this.isNco = isNco;
    }

    @JsonProperty("ncoReason")
    public Object getNcoReason() {
        return ncoReason;
    }

    @JsonProperty("ncoReason")
    public void setNcoReason(Object ncoReason) {
        this.ncoReason = ncoReason;
    }

    @JsonProperty("ncoFileUrl")
    public Object getNcoFileUrl() {
        return ncoFileUrl;
    }

    @JsonProperty("ncoFileUrl")
    public void setNcoFileUrl(Object ncoFileUrl) {
        this.ncoFileUrl = ncoFileUrl;
    }

    @JsonProperty("reasonForShortClose")
    public Object getReasonForShortClose() {
        return reasonForShortClose;
    }

    @JsonProperty("reasonForShortClose")
    public void setReasonForShortClose(Object reasonForShortClose) {
        this.reasonForShortClose = reasonForShortClose;
    }

    @JsonProperty("quantityOrdered")
    public Integer getQuantityOrdered() {
        return quantityOrdered;
    }

    @JsonProperty("quantityOrdered")
    public void setQuantityOrdered(Integer quantityOrdered) {
        this.quantityOrdered = quantityOrdered;
    }

    @JsonProperty("quantityShipped")
    public Double getQuantityShipped() {
        return quantityShipped;
    }

    @JsonProperty("quantityShipped")
    public void setQuantityShipped(Double quantityShipped) {
        this.quantityShipped = quantityShipped;
    }

    @JsonProperty("quantityReturned")
    public Integer getQuantityReturned() {
        return quantityReturned;
    }

    @JsonProperty("quantityReturned")
    public void setQuantityReturned(Integer quantityReturned) {
        this.quantityReturned = quantityReturned;
    }

    @JsonProperty("quantityReplaced")
    public Integer getQuantityReplaced() {
        return quantityReplaced;
    }

    @JsonProperty("quantityReplaced")
    public void setQuantityReplaced(Integer quantityReplaced) {
        this.quantityReplaced = quantityReplaced;
    }

    @JsonProperty("quantityShortClose")
    public Integer getQuantityShortClose() {
        return quantityShortClose;
    }

    @JsonProperty("quantityShortClose")
    public void setQuantityShortClose(Integer quantityShortClose) {
        this.quantityShortClose = quantityShortClose;
    }

    @JsonProperty("quantityDelivered")
    public Double getQuantityDelivered() {
        return quantityDelivered;
    }

    @JsonProperty("quantityDelivered")
    public void setQuantityDelivered(Double quantityDelivered) {
        this.quantityDelivered = quantityDelivered;
    }

    @JsonProperty("quantityCancelled")
    public Integer getQuantityCancelled() {
        return quantityCancelled;
    }

    @JsonProperty("quantityCancelled")
    public void setQuantityCancelled(Integer quantityCancelled) {
        this.quantityCancelled = quantityCancelled;
    }

    @JsonProperty("quantityTolerance")
    public Integer getQuantityTolerance() {
        return quantityTolerance;
    }

    @JsonProperty("quantityTolerance")
    public void setQuantityTolerance(Integer quantityTolerance) {
        this.quantityTolerance = quantityTolerance;
    }

    @JsonProperty("listPrice")
    public ListPrice getListPrice() {
        return listPrice;
    }

    @JsonProperty("listPrice")
    public void setListPrice(ListPrice listPrice) {
        this.listPrice = listPrice;
    }

    @JsonProperty("discountedPrice")
    public DiscountedPrice getDiscountedPrice() {
        return discountedPrice;
    }

    @JsonProperty("discountedPrice")
    public void setDiscountedPrice(DiscountedPrice discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    @JsonProperty("orderedValue")
    public OrderedValue getOrderedValue() {
        return orderedValue;
    }

    @JsonProperty("orderedValue")
    public void setOrderedValue(OrderedValue orderedValue) {
        this.orderedValue = orderedValue;
    }

    @JsonProperty("orderedValueWithTax")
    public OrderedValueWithTax getOrderedValueWithTax() {
        return orderedValueWithTax;
    }

    @JsonProperty("orderedValueWithTax")
    public void setOrderedValueWithTax(OrderedValueWithTax orderedValueWithTax) {
        this.orderedValueWithTax = orderedValueWithTax;
    }

    @JsonProperty("deliveredValue")
    public DeliveredValue getDeliveredValue() {
        return deliveredValue;
    }

    @JsonProperty("deliveredValue")
    public void setDeliveredValue(DeliveredValue deliveredValue) {
        this.deliveredValue = deliveredValue;
    }

    @JsonProperty("deliveredValueWithTax")
    public DeliveredValueWithTax getDeliveredValueWithTax() {
        return deliveredValueWithTax;
    }

    @JsonProperty("deliveredValueWithTax")
    public void setDeliveredValueWithTax(DeliveredValueWithTax deliveredValueWithTax) {
        this.deliveredValueWithTax = deliveredValueWithTax;
    }

    @JsonProperty("returnValue")
    public ReturnValue getReturnValue() {
        return returnValue;
    }

    @JsonProperty("returnValue")
    public void setReturnValue(ReturnValue returnValue) {
        this.returnValue = returnValue;
    }

    @JsonProperty("replaceValue")
    public ReplaceValue getReplaceValue() {
        return replaceValue;
    }

    @JsonProperty("replaceValue")
    public void setReplaceValue(ReplaceValue replaceValue) {
        this.replaceValue = replaceValue;
    }

    @JsonProperty("serviceCost")
    public ServiceCost getServiceCost() {
        return serviceCost;
    }

    @JsonProperty("serviceCost")
    public void setServiceCost(ServiceCost serviceCost) {
        this.serviceCost = serviceCost;
    }

    @JsonProperty("serviceCostWithTax")
    public ServiceCostWithTax getServiceCostWithTax() {
        return serviceCostWithTax;
    }

    @JsonProperty("serviceCostWithTax")
    public void setServiceCostWithTax(ServiceCostWithTax serviceCostWithTax) {
        this.serviceCostWithTax = serviceCostWithTax;
    }

    @JsonProperty("serviceCostBreakup")
    public ServiceCostBreakup getServiceCostBreakup() {
        return serviceCostBreakup;
    }

    @JsonProperty("serviceCostBreakup")
    public void setServiceCostBreakup(ServiceCostBreakup serviceCostBreakup) {
        this.serviceCostBreakup = serviceCostBreakup;
    }

    @JsonProperty("serviceCostBreakupLineItem")
    public ServiceCostBreakupLineItem getServiceCostBreakupLineItem() {
        return serviceCostBreakupLineItem;
    }

    @JsonProperty("serviceCostBreakupLineItem")
    public void setServiceCostBreakupLineItem(ServiceCostBreakupLineItem serviceCostBreakupLineItem) {
        this.serviceCostBreakupLineItem = serviceCostBreakupLineItem;
    }

    @JsonProperty("variantMMId")
    public String getVariantMMId() {
        return variantMMId;
    }

    @JsonProperty("variantMMId")
    public void setVariantMMId(String variantMMId) {
        this.variantMMId = variantMMId;
    }

    @JsonProperty("materialMasterSkuName")
    public String getMaterialMasterSkuName() {
        return materialMasterSkuName;
    }

    @JsonProperty("materialMasterSkuName")
    public void setMaterialMasterSkuName(String materialMasterSkuName) {
        this.materialMasterSkuName = materialMasterSkuName;
    }

    @JsonProperty("lineItemShipments")
    public List<LineItemShipment> getLineItemShipments() {
        return lineItemShipments;
    }

    @JsonProperty("lineItemShipments")
    public void setLineItemShipments(List<LineItemShipment> lineItemShipments) {
        this.lineItemShipments = lineItemShipments;
    }

    @JsonProperty("sourceSeller")
    public SourceSeller getSourceSeller() {
        return sourceSeller;
    }

    @JsonProperty("sourceSeller")
    public void setSourceSeller(SourceSeller sourceSeller) {
        this.sourceSeller = sourceSeller;
    }

    @JsonProperty("vendorMasterId")
    public String getVendorMasterId() {
        return vendorMasterId;
    }

    @JsonProperty("vendorMasterId")
    public void setVendorMasterId(String vendorMasterId) {
        this.vendorMasterId = vendorMasterId;
    }

    @JsonProperty("sourceSellerVendorMasterId")
    public String getSourceSellerVendorMasterId() {
        return sourceSellerVendorMasterId;
    }

    @JsonProperty("sourceSellerVendorMasterId")
    public void setSourceSellerVendorMasterId(String sourceSellerVendorMasterId) {
        this.sourceSellerVendorMasterId = sourceSellerVendorMasterId;
    }

    @JsonProperty("cashbackDetails")
    public CashbackDetails getCashbackDetails() {
        return cashbackDetails;
    }

    @JsonProperty("cashbackDetails")
    public void setCashbackDetails(CashbackDetails cashbackDetails) {
        this.cashbackDetails = cashbackDetails;
    }

    @JsonProperty("orderNumber")
    public Object getOrderNumber() {
        return orderNumber;
    }

    @JsonProperty("orderNumber")
    public void setOrderNumber(Object orderNumber) {
        this.orderNumber = orderNumber;
    }

    @JsonProperty("doReleasedQuantity")
    public Double getDoReleasedQuantity() {
        return doReleasedQuantity;
    }

    @JsonProperty("doReleasedQuantity")
    public void setDoReleasedQuantity(Double doReleasedQuantity) {
        this.doReleasedQuantity = doReleasedQuantity;
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
        sb.append(LineItemDetails.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("lineItemId");
        sb.append('=');
        sb.append(((this.lineItemId == null)?"<null>":this.lineItemId));
        sb.append(',');
        sb.append("productInfo");
        sb.append('=');
        sb.append(((this.productInfo == null)?"<null>":this.productInfo));
        sb.append(',');
        sb.append("measurementUnit");
        sb.append('=');
        sb.append(((this.measurementUnit == null)?"<null>":this.measurementUnit));
        sb.append(',');
        sb.append("measurementUnitDescription");
        sb.append('=');
        sb.append(((this.measurementUnitDescription == null)?"<null>":this.measurementUnitDescription));
        sb.append(',');
        sb.append("currentStatus");
        sb.append('=');
        sb.append(((this.currentStatus == null)?"<null>":this.currentStatus));
        sb.append(',');
        sb.append("nextLineItemStates");
        sb.append('=');
        sb.append(((this.nextLineItemStates == null)?"<null>":this.nextLineItemStates));
        sb.append(',');
        sb.append("sellerInfo");
        sb.append('=');
        sb.append(((this.sellerInfo == null)?"<null>":this.sellerInfo));
        sb.append(',');
        sb.append("channelInfo");
        sb.append('=');
        sb.append(((this.channelInfo == null)?"<null>":this.channelInfo));
        sb.append(',');
        sb.append("purchaseOrderId");
        sb.append('=');
        sb.append(((this.purchaseOrderId == null)?"<null>":this.purchaseOrderId));
        sb.append(',');
        sb.append("isNco");
        sb.append('=');
        sb.append(((this.isNco == null)?"<null>":this.isNco));
        sb.append(',');
        sb.append("ncoReason");
        sb.append('=');
        sb.append(((this.ncoReason == null)?"<null>":this.ncoReason));
        sb.append(',');
        sb.append("ncoFileUrl");
        sb.append('=');
        sb.append(((this.ncoFileUrl == null)?"<null>":this.ncoFileUrl));
        sb.append(',');
        sb.append("reasonForShortClose");
        sb.append('=');
        sb.append(((this.reasonForShortClose == null)?"<null>":this.reasonForShortClose));
        sb.append(',');
        sb.append("quantityOrdered");
        sb.append('=');
        sb.append(((this.quantityOrdered == null)?"<null>":this.quantityOrdered));
        sb.append(',');
        sb.append("quantityShipped");
        sb.append('=');
        sb.append(((this.quantityShipped == null)?"<null>":this.quantityShipped));
        sb.append(',');
        sb.append("quantityReturned");
        sb.append('=');
        sb.append(((this.quantityReturned == null)?"<null>":this.quantityReturned));
        sb.append(',');
        sb.append("quantityReplaced");
        sb.append('=');
        sb.append(((this.quantityReplaced == null)?"<null>":this.quantityReplaced));
        sb.append(',');
        sb.append("quantityShortClose");
        sb.append('=');
        sb.append(((this.quantityShortClose == null)?"<null>":this.quantityShortClose));
        sb.append(',');
        sb.append("quantityDelivered");
        sb.append('=');
        sb.append(((this.quantityDelivered == null)?"<null>":this.quantityDelivered));
        sb.append(',');
        sb.append("quantityCancelled");
        sb.append('=');
        sb.append(((this.quantityCancelled == null)?"<null>":this.quantityCancelled));
        sb.append(',');
        sb.append("quantityTolerance");
        sb.append('=');
        sb.append(((this.quantityTolerance == null)?"<null>":this.quantityTolerance));
        sb.append(',');
        sb.append("listPrice");
        sb.append('=');
        sb.append(((this.listPrice == null)?"<null>":this.listPrice));
        sb.append(',');
        sb.append("discountedPrice");
        sb.append('=');
        sb.append(((this.discountedPrice == null)?"<null>":this.discountedPrice));
        sb.append(',');
        sb.append("orderedValue");
        sb.append('=');
        sb.append(((this.orderedValue == null)?"<null>":this.orderedValue));
        sb.append(',');
        sb.append("orderedValueWithTax");
        sb.append('=');
        sb.append(((this.orderedValueWithTax == null)?"<null>":this.orderedValueWithTax));
        sb.append(',');
        sb.append("deliveredValue");
        sb.append('=');
        sb.append(((this.deliveredValue == null)?"<null>":this.deliveredValue));
        sb.append(',');
        sb.append("deliveredValueWithTax");
        sb.append('=');
        sb.append(((this.deliveredValueWithTax == null)?"<null>":this.deliveredValueWithTax));
        sb.append(',');
        sb.append("returnValue");
        sb.append('=');
        sb.append(((this.returnValue == null)?"<null>":this.returnValue));
        sb.append(',');
        sb.append("replaceValue");
        sb.append('=');
        sb.append(((this.replaceValue == null)?"<null>":this.replaceValue));
        sb.append(',');
        sb.append("serviceCost");
        sb.append('=');
        sb.append(((this.serviceCost == null)?"<null>":this.serviceCost));
        sb.append(',');
        sb.append("serviceCostWithTax");
        sb.append('=');
        sb.append(((this.serviceCostWithTax == null)?"<null>":this.serviceCostWithTax));
        sb.append(',');
        sb.append("serviceCostBreakup");
        sb.append('=');
        sb.append(((this.serviceCostBreakup == null)?"<null>":this.serviceCostBreakup));
        sb.append(',');
        sb.append("serviceCostBreakupLineItem");
        sb.append('=');
        sb.append(((this.serviceCostBreakupLineItem == null)?"<null>":this.serviceCostBreakupLineItem));
        sb.append(',');
        sb.append("variantMMId");
        sb.append('=');
        sb.append(((this.variantMMId == null)?"<null>":this.variantMMId));
        sb.append(',');
        sb.append("materialMasterSkuName");
        sb.append('=');
        sb.append(((this.materialMasterSkuName == null)?"<null>":this.materialMasterSkuName));
        sb.append(',');
        sb.append("lineItemShipments");
        sb.append('=');
        sb.append(((this.lineItemShipments == null)?"<null>":this.lineItemShipments));
        sb.append(',');
        sb.append("sourceSeller");
        sb.append('=');
        sb.append(((this.sourceSeller == null)?"<null>":this.sourceSeller));
        sb.append(',');
        sb.append("vendorMasterId");
        sb.append('=');
        sb.append(((this.vendorMasterId == null)?"<null>":this.vendorMasterId));
        sb.append(',');
        sb.append("sourceSellerVendorMasterId");
        sb.append('=');
        sb.append(((this.sourceSellerVendorMasterId == null)?"<null>":this.sourceSellerVendorMasterId));
        sb.append(',');
        sb.append("cashbackDetails");
        sb.append('=');
        sb.append(((this.cashbackDetails == null)?"<null>":this.cashbackDetails));
        sb.append(',');
        sb.append("orderNumber");
        sb.append('=');
        sb.append(((this.orderNumber == null)?"<null>":this.orderNumber));
        sb.append(',');
        sb.append("doReleasedQuantity");
        sb.append('=');
        sb.append(((this.doReleasedQuantity == null)?"<null>":this.doReleasedQuantity));
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
        result = ((result* 31)+((this.quantityReturned == null)? 0 :this.quantityReturned.hashCode()));
        result = ((result* 31)+((this.measurementUnitDescription == null)? 0 :this.measurementUnitDescription.hashCode()));
        result = ((result* 31)+((this.deliveredValueWithTax == null)? 0 :this.deliveredValueWithTax.hashCode()));
        result = ((result* 31)+((this.channelInfo == null)? 0 :this.channelInfo.hashCode()));
        result = ((result* 31)+((this.returnValue == null)? 0 :this.returnValue.hashCode()));
        result = ((result* 31)+((this.orderNumber == null)? 0 :this.orderNumber.hashCode()));
        result = ((result* 31)+((this.ncoReason == null)? 0 :this.ncoReason.hashCode()));
        result = ((result* 31)+((this.quantityShortClose == null)? 0 :this.quantityShortClose.hashCode()));
        result = ((result* 31)+((this.quantityCancelled == null)? 0 :this.quantityCancelled.hashCode()));
        result = ((result* 31)+((this.quantityOrdered == null)? 0 :this.quantityOrdered.hashCode()));
        result = ((result* 31)+((this.quantityDelivered == null)? 0 :this.quantityDelivered.hashCode()));
        result = ((result* 31)+((this.sourceSellerVendorMasterId == null)? 0 :this.sourceSellerVendorMasterId.hashCode()));
        result = ((result* 31)+((this.serviceCostWithTax == null)? 0 :this.serviceCostWithTax.hashCode()));
        result = ((result* 31)+((this.deliveredValue == null)? 0 :this.deliveredValue.hashCode()));
        result = ((result* 31)+((this.serviceCostBreakup == null)? 0 :this.serviceCostBreakup.hashCode()));
        result = ((result* 31)+((this.doReleasedQuantity == null)? 0 :this.doReleasedQuantity.hashCode()));
        result = ((result* 31)+((this.lineItemId == null)? 0 :this.lineItemId.hashCode()));
        result = ((result* 31)+((this.currentStatus == null)? 0 :this.currentStatus.hashCode()));
        result = ((result* 31)+((this.serviceCost == null)? 0 :this.serviceCost.hashCode()));
        result = ((result* 31)+((this.replaceValue == null)? 0 :this.replaceValue.hashCode()));
        result = ((result* 31)+((this.materialMasterSkuName == null)? 0 :this.materialMasterSkuName.hashCode()));
        result = ((result* 31)+((this.vendorMasterId == null)? 0 :this.vendorMasterId.hashCode()));
        result = ((result* 31)+((this.serviceCostBreakupLineItem == null)? 0 :this.serviceCostBreakupLineItem.hashCode()));
        result = ((result* 31)+((this.variantMMId == null)? 0 :this.variantMMId.hashCode()));
        result = ((result* 31)+((this.quantityTolerance == null)? 0 :this.quantityTolerance.hashCode()));
        result = ((result* 31)+((this.sellerInfo == null)? 0 :this.sellerInfo.hashCode()));
        result = ((result* 31)+((this.isNco == null)? 0 :this.isNco.hashCode()));
        result = ((result* 31)+((this.measurementUnit == null)? 0 :this.measurementUnit.hashCode()));
        result = ((result* 31)+((this.orderedValueWithTax == null)? 0 :this.orderedValueWithTax.hashCode()));
        result = ((result* 31)+((this.productInfo == null)? 0 :this.productInfo.hashCode()));
        result = ((result* 31)+((this.reasonForShortClose == null)? 0 :this.reasonForShortClose.hashCode()));
        result = ((result* 31)+((this.ncoFileUrl == null)? 0 :this.ncoFileUrl.hashCode()));
        result = ((result* 31)+((this.quantityShipped == null)? 0 :this.quantityShipped.hashCode()));
        result = ((result* 31)+((this.orderedValue == null)? 0 :this.orderedValue.hashCode()));
        result = ((result* 31)+((this.discountedPrice == null)? 0 :this.discountedPrice.hashCode()));
        result = ((result* 31)+((this.sourceSeller == null)? 0 :this.sourceSeller.hashCode()));
        result = ((result* 31)+((this.nextLineItemStates == null)? 0 :this.nextLineItemStates.hashCode()));
        result = ((result* 31)+((this.purchaseOrderId == null)? 0 :this.purchaseOrderId.hashCode()));
        result = ((result* 31)+((this.cashbackDetails == null)? 0 :this.cashbackDetails.hashCode()));
        result = ((result* 31)+((this.lineItemShipments == null)? 0 :this.lineItemShipments.hashCode()));
        result = ((result* 31)+((this.quantityReplaced == null)? 0 :this.quantityReplaced.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.listPrice == null)? 0 :this.listPrice.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof LineItemDetails) == false) {
            return false;
        }
        LineItemDetails rhs = ((LineItemDetails) other);
        return ((((((((((((((((((((((((((((((((((((((((((((this.quantityReturned == rhs.quantityReturned)||((this.quantityReturned!= null)&&this.quantityReturned.equals(rhs.quantityReturned)))&&((this.measurementUnitDescription == rhs.measurementUnitDescription)||((this.measurementUnitDescription!= null)&&this.measurementUnitDescription.equals(rhs.measurementUnitDescription))))&&((this.deliveredValueWithTax == rhs.deliveredValueWithTax)||((this.deliveredValueWithTax!= null)&&this.deliveredValueWithTax.equals(rhs.deliveredValueWithTax))))&&((this.channelInfo == rhs.channelInfo)||((this.channelInfo!= null)&&this.channelInfo.equals(rhs.channelInfo))))&&((this.returnValue == rhs.returnValue)||((this.returnValue!= null)&&this.returnValue.equals(rhs.returnValue))))&&((this.orderNumber == rhs.orderNumber)||((this.orderNumber!= null)&&this.orderNumber.equals(rhs.orderNumber))))&&((this.ncoReason == rhs.ncoReason)||((this.ncoReason!= null)&&this.ncoReason.equals(rhs.ncoReason))))&&((this.quantityShortClose == rhs.quantityShortClose)||((this.quantityShortClose!= null)&&this.quantityShortClose.equals(rhs.quantityShortClose))))&&((this.quantityCancelled == rhs.quantityCancelled)||((this.quantityCancelled!= null)&&this.quantityCancelled.equals(rhs.quantityCancelled))))&&((this.quantityOrdered == rhs.quantityOrdered)||((this.quantityOrdered!= null)&&this.quantityOrdered.equals(rhs.quantityOrdered))))&&((this.quantityDelivered == rhs.quantityDelivered)||((this.quantityDelivered!= null)&&this.quantityDelivered.equals(rhs.quantityDelivered))))&&((this.sourceSellerVendorMasterId == rhs.sourceSellerVendorMasterId)||((this.sourceSellerVendorMasterId!= null)&&this.sourceSellerVendorMasterId.equals(rhs.sourceSellerVendorMasterId))))&&((this.serviceCostWithTax == rhs.serviceCostWithTax)||((this.serviceCostWithTax!= null)&&this.serviceCostWithTax.equals(rhs.serviceCostWithTax))))&&((this.deliveredValue == rhs.deliveredValue)||((this.deliveredValue!= null)&&this.deliveredValue.equals(rhs.deliveredValue))))&&((this.serviceCostBreakup == rhs.serviceCostBreakup)||((this.serviceCostBreakup!= null)&&this.serviceCostBreakup.equals(rhs.serviceCostBreakup))))&&((this.doReleasedQuantity == rhs.doReleasedQuantity)||((this.doReleasedQuantity!= null)&&this.doReleasedQuantity.equals(rhs.doReleasedQuantity))))&&((this.lineItemId == rhs.lineItemId)||((this.lineItemId!= null)&&this.lineItemId.equals(rhs.lineItemId))))&&((this.currentStatus == rhs.currentStatus)||((this.currentStatus!= null)&&this.currentStatus.equals(rhs.currentStatus))))&&((this.serviceCost == rhs.serviceCost)||((this.serviceCost!= null)&&this.serviceCost.equals(rhs.serviceCost))))&&((this.replaceValue == rhs.replaceValue)||((this.replaceValue!= null)&&this.replaceValue.equals(rhs.replaceValue))))&&((this.materialMasterSkuName == rhs.materialMasterSkuName)||((this.materialMasterSkuName!= null)&&this.materialMasterSkuName.equals(rhs.materialMasterSkuName))))&&((this.vendorMasterId == rhs.vendorMasterId)||((this.vendorMasterId!= null)&&this.vendorMasterId.equals(rhs.vendorMasterId))))&&((this.serviceCostBreakupLineItem == rhs.serviceCostBreakupLineItem)||((this.serviceCostBreakupLineItem!= null)&&this.serviceCostBreakupLineItem.equals(rhs.serviceCostBreakupLineItem))))&&((this.variantMMId == rhs.variantMMId)||((this.variantMMId!= null)&&this.variantMMId.equals(rhs.variantMMId))))&&((this.quantityTolerance == rhs.quantityTolerance)||((this.quantityTolerance!= null)&&this.quantityTolerance.equals(rhs.quantityTolerance))))&&((this.sellerInfo == rhs.sellerInfo)||((this.sellerInfo!= null)&&this.sellerInfo.equals(rhs.sellerInfo))))&&((this.isNco == rhs.isNco)||((this.isNco!= null)&&this.isNco.equals(rhs.isNco))))&&((this.measurementUnit == rhs.measurementUnit)||((this.measurementUnit!= null)&&this.measurementUnit.equals(rhs.measurementUnit))))&&((this.orderedValueWithTax == rhs.orderedValueWithTax)||((this.orderedValueWithTax!= null)&&this.orderedValueWithTax.equals(rhs.orderedValueWithTax))))&&((this.productInfo == rhs.productInfo)||((this.productInfo!= null)&&this.productInfo.equals(rhs.productInfo))))&&((this.reasonForShortClose == rhs.reasonForShortClose)||((this.reasonForShortClose!= null)&&this.reasonForShortClose.equals(rhs.reasonForShortClose))))&&((this.ncoFileUrl == rhs.ncoFileUrl)||((this.ncoFileUrl!= null)&&this.ncoFileUrl.equals(rhs.ncoFileUrl))))&&((this.quantityShipped == rhs.quantityShipped)||((this.quantityShipped!= null)&&this.quantityShipped.equals(rhs.quantityShipped))))&&((this.orderedValue == rhs.orderedValue)||((this.orderedValue!= null)&&this.orderedValue.equals(rhs.orderedValue))))&&((this.discountedPrice == rhs.discountedPrice)||((this.discountedPrice!= null)&&this.discountedPrice.equals(rhs.discountedPrice))))&&((this.sourceSeller == rhs.sourceSeller)||((this.sourceSeller!= null)&&this.sourceSeller.equals(rhs.sourceSeller))))&&((this.nextLineItemStates == rhs.nextLineItemStates)||((this.nextLineItemStates!= null)&&this.nextLineItemStates.equals(rhs.nextLineItemStates))))&&((this.purchaseOrderId == rhs.purchaseOrderId)||((this.purchaseOrderId!= null)&&this.purchaseOrderId.equals(rhs.purchaseOrderId))))&&((this.cashbackDetails == rhs.cashbackDetails)||((this.cashbackDetails!= null)&&this.cashbackDetails.equals(rhs.cashbackDetails))))&&((this.lineItemShipments == rhs.lineItemShipments)||((this.lineItemShipments!= null)&&this.lineItemShipments.equals(rhs.lineItemShipments))))&&((this.quantityReplaced == rhs.quantityReplaced)||((this.quantityReplaced!= null)&&this.quantityReplaced.equals(rhs.quantityReplaced))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.listPrice == rhs.listPrice)||((this.listPrice!= null)&&this.listPrice.equals(rhs.listPrice))));
    }

}


package com.qapitol.jsw.apitest.pojos.order.rcv2Response;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "lineItemIdentifier",
    "productSlug",
    "productForm",
    "productKey",
    "variantId",
    "variantKey",
    "sku",
    "imageUrl",
    "name",
    "sellerName",
    "sellerKey",
    "attributes",
    "deliveryStatus",
    "quantity",
    "totalQuantityReadyForDispatch",
    "payableAmount",
    "ctAttributes",
    "availableQuantity",
    "freightInclusivePrice",
    "brand",
    "channelName",
    "channelKey",
    "sellerVendorMasterId",
    "channelAddress",
    "productType",
    "grade",
    "subGrade",
    "lineItemTaxRatio",
    "portalLineItemPrice",
    "distributorLineItemPrice",
    "lineItemPriceList",
    "lineItemAmount",
    "lineItemTaxedAmount",
    "lineItemAmounts",
    "lineItemAmountsV2",
    "addedAt",
    "lastModifiedAt",
    "productMMId",
    "tdcDocumentAvailable",
    "salesForceSkuId",
    "lineItemStatus",
    "pricingMode",
    "forFreight",
    "portalPriceAvailable"
})
@Generated("jsonschema2pojo")
public class LineItem {

    @JsonProperty("lineItemIdentifier")
    private LineItemIdentifier lineItemIdentifier;
    @JsonProperty("productSlug")
    private String productSlug;
    @JsonProperty("productForm")
    private String productForm;
    @JsonProperty("productKey")
    private String productKey;
    @JsonProperty("variantId")
    private Integer variantId;
    @JsonProperty("variantKey")
    private String variantKey;
    @JsonProperty("sku")
    private String sku;
    @JsonProperty("imageUrl")
    private String imageUrl;
    @JsonProperty("name")
    private String name;
    @JsonProperty("sellerName")
    private String sellerName;
    @JsonProperty("sellerKey")
    private String sellerKey;
    @JsonProperty("attributes")
    private Attributes attributes;
    @JsonProperty("deliveryStatus")
    private List<Deliverystatus> deliveryStatus = new ArrayList<Deliverystatus>();
    @JsonProperty("quantity")
    private Quantity quantity;
    @JsonProperty("totalQuantityReadyForDispatch")
    private TotalQuantityReadyForDispatch totalQuantityReadyForDispatch;
    @JsonProperty("payableAmount")
    private PayableAmount payableAmount;
    @JsonProperty("ctAttributes")
    private CtAttributes ctAttributes;
    @JsonProperty("availableQuantity")
    private AvailableQuantity availableQuantity;
    @JsonProperty("freightInclusivePrice")
    private Boolean freightInclusivePrice;
    @JsonProperty("brand")
    private String brand;
    @JsonProperty("channelName")
    private String channelName;
    @JsonProperty("channelKey")
    private String channelKey;
    @JsonProperty("sellerVendorMasterId")
    private String sellerVendorMasterId;
    @JsonProperty("channelAddress")
    private ChannelAddress channelAddress;
    @JsonProperty("productType")
    private String productType;
    @JsonProperty("grade")
    private String grade;
    @JsonProperty("subGrade")
    private String subGrade;
    @JsonProperty("lineItemTaxRatio")
    private Double lineItemTaxRatio;
    @JsonProperty("portalLineItemPrice")
    private PortalLineItemPrice portalLineItemPrice;
    @JsonProperty("distributorLineItemPrice")
    private DistributorLineItemPrice distributorLineItemPrice;
    @JsonProperty("lineItemPriceList")
    private List<Object> lineItemPriceList = new ArrayList<Object>();
    @JsonProperty("lineItemAmount")
    private LineItemAmount lineItemAmount;
    @JsonProperty("lineItemTaxedAmount")
    private LineItemTaxedAmount lineItemTaxedAmount;
    @JsonProperty("lineItemAmounts")
    private LineItemAmounts lineItemAmounts;
    @JsonProperty("lineItemAmountsV2")
    private LineItemAmountsV2 lineItemAmountsV2;
    @JsonProperty("addedAt")
    private String addedAt;
    @JsonProperty("lastModifiedAt")
    private String lastModifiedAt;
    @JsonProperty("productMMId")
    private String productMMId;
    @JsonProperty("tdcDocumentAvailable")
    private Boolean tdcDocumentAvailable;
    @JsonProperty("salesForceSkuId")
    private String salesForceSkuId;
    @JsonProperty("lineItemStatus")
    private LineItemStatus lineItemStatus;
    @JsonProperty("pricingMode")
    private String pricingMode;
    @JsonProperty("forFreight")
    private Double forFreight;
    @JsonProperty("portalPriceAvailable")
    private Boolean portalPriceAvailable;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("lineItemIdentifier")
    public LineItemIdentifier getLineItemIdentifier() {
        return lineItemIdentifier;
    }

    @JsonProperty("lineItemIdentifier")
    public void setLineItemIdentifier(LineItemIdentifier lineItemIdentifier) {
        this.lineItemIdentifier = lineItemIdentifier;
    }

    @JsonProperty("productSlug")
    public String getProductSlug() {
        return productSlug;
    }

    @JsonProperty("productSlug")
    public void setProductSlug(String productSlug) {
        this.productSlug = productSlug;
    }

    @JsonProperty("productForm")
    public String getProductForm() {
        return productForm;
    }

    @JsonProperty("productForm")
    public void setProductForm(String productForm) {
        this.productForm = productForm;
    }

    @JsonProperty("productKey")
    public String getProductKey() {
        return productKey;
    }

    @JsonProperty("productKey")
    public void setProductKey(String productKey) {
        this.productKey = productKey;
    }

    @JsonProperty("variantId")
    public Integer getVariantId() {
        return variantId;
    }

    @JsonProperty("variantId")
    public void setVariantId(Integer variantId) {
        this.variantId = variantId;
    }

    @JsonProperty("variantKey")
    public String getVariantKey() {
        return variantKey;
    }

    @JsonProperty("variantKey")
    public void setVariantKey(String variantKey) {
        this.variantKey = variantKey;
    }

    @JsonProperty("sku")
    public String getSku() {
        return sku;
    }

    @JsonProperty("sku")
    public void setSku(String sku) {
        this.sku = sku;
    }

    @JsonProperty("imageUrl")
    public String getImageUrl() {
        return imageUrl;
    }

    @JsonProperty("imageUrl")
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("sellerName")
    public String getSellerName() {
        return sellerName;
    }

    @JsonProperty("sellerName")
    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    @JsonProperty("sellerKey")
    public String getSellerKey() {
        return sellerKey;
    }

    @JsonProperty("sellerKey")
    public void setSellerKey(String sellerKey) {
        this.sellerKey = sellerKey;
    }

    @JsonProperty("attributes")
    public Attributes getAttributes() {
        return attributes;
    }

    @JsonProperty("attributes")
    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    @JsonProperty("deliveryStatus")
    public List<Deliverystatus> getDeliveryStatus() {
        return deliveryStatus;
    }

    @JsonProperty("deliveryStatus")
    public void setDeliveryStatus(List<Deliverystatus> deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    @JsonProperty("quantity")
    public Quantity getQuantity() {
        return quantity;
    }

    @JsonProperty("quantity")
    public void setQuantity(Quantity quantity) {
        this.quantity = quantity;
    }

    @JsonProperty("totalQuantityReadyForDispatch")
    public TotalQuantityReadyForDispatch getTotalQuantityReadyForDispatch() {
        return totalQuantityReadyForDispatch;
    }

    @JsonProperty("totalQuantityReadyForDispatch")
    public void setTotalQuantityReadyForDispatch(TotalQuantityReadyForDispatch totalQuantityReadyForDispatch) {
        this.totalQuantityReadyForDispatch = totalQuantityReadyForDispatch;
    }

    @JsonProperty("payableAmount")
    public PayableAmount getPayableAmount() {
        return payableAmount;
    }

    @JsonProperty("payableAmount")
    public void setPayableAmount(PayableAmount payableAmount) {
        this.payableAmount = payableAmount;
    }

    @JsonProperty("ctAttributes")
    public CtAttributes getCtAttributes() {
        return ctAttributes;
    }

    @JsonProperty("ctAttributes")
    public void setCtAttributes(CtAttributes ctAttributes) {
        this.ctAttributes = ctAttributes;
    }

    @JsonProperty("availableQuantity")
    public AvailableQuantity getAvailableQuantity() {
        return availableQuantity;
    }

    @JsonProperty("availableQuantity")
    public void setAvailableQuantity(AvailableQuantity availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    @JsonProperty("freightInclusivePrice")
    public Boolean getFreightInclusivePrice() {
        return freightInclusivePrice;
    }

    @JsonProperty("freightInclusivePrice")
    public void setFreightInclusivePrice(Boolean freightInclusivePrice) {
        this.freightInclusivePrice = freightInclusivePrice;
    }

    @JsonProperty("brand")
    public String getBrand() {
        return brand;
    }

    @JsonProperty("brand")
    public void setBrand(String brand) {
        this.brand = brand;
    }

    @JsonProperty("channelName")
    public String getChannelName() {
        return channelName;
    }

    @JsonProperty("channelName")
    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    @JsonProperty("channelKey")
    public String getChannelKey() {
        return channelKey;
    }

    @JsonProperty("channelKey")
    public void setChannelKey(String channelKey) {
        this.channelKey = channelKey;
    }

    @JsonProperty("sellerVendorMasterId")
    public String getSellerVendorMasterId() {
        return sellerVendorMasterId;
    }

    @JsonProperty("sellerVendorMasterId")
    public void setSellerVendorMasterId(String sellerVendorMasterId) {
        this.sellerVendorMasterId = sellerVendorMasterId;
    }

    @JsonProperty("channelAddress")
    public ChannelAddress getChannelAddress() {
        return channelAddress;
    }

    @JsonProperty("channelAddress")
    public void setChannelAddress(ChannelAddress channelAddress) {
        this.channelAddress = channelAddress;
    }

    @JsonProperty("productType")
    public String getProductType() {
        return productType;
    }

    @JsonProperty("productType")
    public void setProductType(String productType) {
        this.productType = productType;
    }

    @JsonProperty("grade")
    public String getGrade() {
        return grade;
    }

    @JsonProperty("grade")
    public void setGrade(String grade) {
        this.grade = grade;
    }

    @JsonProperty("subGrade")
    public String getSubGrade() {
        return subGrade;
    }

    @JsonProperty("subGrade")
    public void setSubGrade(String subGrade) {
        this.subGrade = subGrade;
    }

    @JsonProperty("lineItemTaxRatio")
    public Double getLineItemTaxRatio() {
        return lineItemTaxRatio;
    }

    @JsonProperty("lineItemTaxRatio")
    public void setLineItemTaxRatio(Double lineItemTaxRatio) {
        this.lineItemTaxRatio = lineItemTaxRatio;
    }

    @JsonProperty("portalLineItemPrice")
    public PortalLineItemPrice getPortalLineItemPrice() {
        return portalLineItemPrice;
    }

    @JsonProperty("portalLineItemPrice")
    public void setPortalLineItemPrice(PortalLineItemPrice portalLineItemPrice) {
        this.portalLineItemPrice = portalLineItemPrice;
    }

    @JsonProperty("distributorLineItemPrice")
    public DistributorLineItemPrice getDistributorLineItemPrice() {
        return distributorLineItemPrice;
    }

    @JsonProperty("distributorLineItemPrice")
    public void setDistributorLineItemPrice(DistributorLineItemPrice distributorLineItemPrice) {
        this.distributorLineItemPrice = distributorLineItemPrice;
    }

    @JsonProperty("lineItemPriceList")
    public List<Object> getLineItemPriceList() {
        return lineItemPriceList;
    }

    @JsonProperty("lineItemPriceList")
    public void setLineItemPriceList(List<Object> lineItemPriceList) {
        this.lineItemPriceList = lineItemPriceList;
    }

    @JsonProperty("lineItemAmount")
    public LineItemAmount getLineItemAmount() {
        return lineItemAmount;
    }

    @JsonProperty("lineItemAmount")
    public void setLineItemAmount(LineItemAmount lineItemAmount) {
        this.lineItemAmount = lineItemAmount;
    }

    @JsonProperty("lineItemTaxedAmount")
    public LineItemTaxedAmount getLineItemTaxedAmount() {
        return lineItemTaxedAmount;
    }

    @JsonProperty("lineItemTaxedAmount")
    public void setLineItemTaxedAmount(LineItemTaxedAmount lineItemTaxedAmount) {
        this.lineItemTaxedAmount = lineItemTaxedAmount;
    }

    @JsonProperty("lineItemAmounts")
    public LineItemAmounts getLineItemAmounts() {
        return lineItemAmounts;
    }

    @JsonProperty("lineItemAmounts")
    public void setLineItemAmounts(LineItemAmounts lineItemAmounts) {
        this.lineItemAmounts = lineItemAmounts;
    }

    @JsonProperty("lineItemAmountsV2")
    public LineItemAmountsV2 getLineItemAmountsV2() {
        return lineItemAmountsV2;
    }

    @JsonProperty("lineItemAmountsV2")
    public void setLineItemAmountsV2(LineItemAmountsV2 lineItemAmountsV2) {
        this.lineItemAmountsV2 = lineItemAmountsV2;
    }

    @JsonProperty("addedAt")
    public String getAddedAt() {
        return addedAt;
    }

    @JsonProperty("addedAt")
    public void setAddedAt(String addedAt) {
        this.addedAt = addedAt;
    }

    @JsonProperty("lastModifiedAt")
    public String getLastModifiedAt() {
        return lastModifiedAt;
    }

    @JsonProperty("lastModifiedAt")
    public void setLastModifiedAt(String lastModifiedAt) {
        this.lastModifiedAt = lastModifiedAt;
    }

    @JsonProperty("productMMId")
    public String getProductMMId() {
        return productMMId;
    }

    @JsonProperty("productMMId")
    public void setProductMMId(String productMMId) {
        this.productMMId = productMMId;
    }

    @JsonProperty("tdcDocumentAvailable")
    public Boolean getTdcDocumentAvailable() {
        return tdcDocumentAvailable;
    }

    @JsonProperty("tdcDocumentAvailable")
    public void setTdcDocumentAvailable(Boolean tdcDocumentAvailable) {
        this.tdcDocumentAvailable = tdcDocumentAvailable;
    }

    @JsonProperty("salesForceSkuId")
    public String getSalesForceSkuId() {
        return salesForceSkuId;
    }

    @JsonProperty("salesForceSkuId")
    public void setSalesForceSkuId(String salesForceSkuId) {
        this.salesForceSkuId = salesForceSkuId;
    }

    @JsonProperty("lineItemStatus")
    public LineItemStatus getLineItemStatus() {
        return lineItemStatus;
    }

    @JsonProperty("lineItemStatus")
    public void setLineItemStatus(LineItemStatus lineItemStatus) {
        this.lineItemStatus = lineItemStatus;
    }

    @JsonProperty("pricingMode")
    public String getPricingMode() {
        return pricingMode;
    }

    @JsonProperty("pricingMode")
    public void setPricingMode(String pricingMode) {
        this.pricingMode = pricingMode;
    }

    @JsonProperty("forFreight")
    public Double getForFreight() {
        return forFreight;
    }

    @JsonProperty("forFreight")
    public void setForFreight(Double forFreight) {
        this.forFreight = forFreight;
    }

    @JsonProperty("portalPriceAvailable")
    public Boolean getPortalPriceAvailable() {
        return portalPriceAvailable;
    }

    @JsonProperty("portalPriceAvailable")
    public void setPortalPriceAvailable(Boolean portalPriceAvailable) {
        this.portalPriceAvailable = portalPriceAvailable;
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
        sb.append("lineItemIdentifier");
        sb.append('=');
        sb.append(((this.lineItemIdentifier == null)?"<null>":this.lineItemIdentifier));
        sb.append(',');
        sb.append("productSlug");
        sb.append('=');
        sb.append(((this.productSlug == null)?"<null>":this.productSlug));
        sb.append(',');
        sb.append("productForm");
        sb.append('=');
        sb.append(((this.productForm == null)?"<null>":this.productForm));
        sb.append(',');
        sb.append("productKey");
        sb.append('=');
        sb.append(((this.productKey == null)?"<null>":this.productKey));
        sb.append(',');
        sb.append("variantId");
        sb.append('=');
        sb.append(((this.variantId == null)?"<null>":this.variantId));
        sb.append(',');
        sb.append("variantKey");
        sb.append('=');
        sb.append(((this.variantKey == null)?"<null>":this.variantKey));
        sb.append(',');
        sb.append("sku");
        sb.append('=');
        sb.append(((this.sku == null)?"<null>":this.sku));
        sb.append(',');
        sb.append("imageUrl");
        sb.append('=');
        sb.append(((this.imageUrl == null)?"<null>":this.imageUrl));
        sb.append(',');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null)?"<null>":this.name));
        sb.append(',');
        sb.append("sellerName");
        sb.append('=');
        sb.append(((this.sellerName == null)?"<null>":this.sellerName));
        sb.append(',');
        sb.append("sellerKey");
        sb.append('=');
        sb.append(((this.sellerKey == null)?"<null>":this.sellerKey));
        sb.append(',');
        sb.append("attributes");
        sb.append('=');
        sb.append(((this.attributes == null)?"<null>":this.attributes));
        sb.append(',');
        sb.append("deliveryStatus");
        sb.append('=');
        sb.append(((this.deliveryStatus == null)?"<null>":this.deliveryStatus));
        sb.append(',');
        sb.append("quantity");
        sb.append('=');
        sb.append(((this.quantity == null)?"<null>":this.quantity));
        sb.append(',');
        sb.append("totalQuantityReadyForDispatch");
        sb.append('=');
        sb.append(((this.totalQuantityReadyForDispatch == null)?"<null>":this.totalQuantityReadyForDispatch));
        sb.append(',');
        sb.append("payableAmount");
        sb.append('=');
        sb.append(((this.payableAmount == null)?"<null>":this.payableAmount));
        sb.append(',');
        sb.append("ctAttributes");
        sb.append('=');
        sb.append(((this.ctAttributes == null)?"<null>":this.ctAttributes));
        sb.append(',');
        sb.append("availableQuantity");
        sb.append('=');
        sb.append(((this.availableQuantity == null)?"<null>":this.availableQuantity));
        sb.append(',');
        sb.append("freightInclusivePrice");
        sb.append('=');
        sb.append(((this.freightInclusivePrice == null)?"<null>":this.freightInclusivePrice));
        sb.append(',');
        sb.append("brand");
        sb.append('=');
        sb.append(((this.brand == null)?"<null>":this.brand));
        sb.append(',');
        sb.append("channelName");
        sb.append('=');
        sb.append(((this.channelName == null)?"<null>":this.channelName));
        sb.append(',');
        sb.append("channelKey");
        sb.append('=');
        sb.append(((this.channelKey == null)?"<null>":this.channelKey));
        sb.append(',');
        sb.append("sellerVendorMasterId");
        sb.append('=');
        sb.append(((this.sellerVendorMasterId == null)?"<null>":this.sellerVendorMasterId));
        sb.append(',');
        sb.append("channelAddress");
        sb.append('=');
        sb.append(((this.channelAddress == null)?"<null>":this.channelAddress));
        sb.append(',');
        sb.append("productType");
        sb.append('=');
        sb.append(((this.productType == null)?"<null>":this.productType));
        sb.append(',');
        sb.append("grade");
        sb.append('=');
        sb.append(((this.grade == null)?"<null>":this.grade));
        sb.append(',');
        sb.append("subGrade");
        sb.append('=');
        sb.append(((this.subGrade == null)?"<null>":this.subGrade));
        sb.append(',');
        sb.append("lineItemTaxRatio");
        sb.append('=');
        sb.append(((this.lineItemTaxRatio == null)?"<null>":this.lineItemTaxRatio));
        sb.append(',');
        sb.append("portalLineItemPrice");
        sb.append('=');
        sb.append(((this.portalLineItemPrice == null)?"<null>":this.portalLineItemPrice));
        sb.append(',');
        sb.append("distributorLineItemPrice");
        sb.append('=');
        sb.append(((this.distributorLineItemPrice == null)?"<null>":this.distributorLineItemPrice));
        sb.append(',');
        sb.append("lineItemPriceList");
        sb.append('=');
        sb.append(((this.lineItemPriceList == null)?"<null>":this.lineItemPriceList));
        sb.append(',');
        sb.append("lineItemAmount");
        sb.append('=');
        sb.append(((this.lineItemAmount == null)?"<null>":this.lineItemAmount));
        sb.append(',');
        sb.append("lineItemTaxedAmount");
        sb.append('=');
        sb.append(((this.lineItemTaxedAmount == null)?"<null>":this.lineItemTaxedAmount));
        sb.append(',');
        sb.append("lineItemAmounts");
        sb.append('=');
        sb.append(((this.lineItemAmounts == null)?"<null>":this.lineItemAmounts));
        sb.append(',');
        sb.append("lineItemAmountsV2");
        sb.append('=');
        sb.append(((this.lineItemAmountsV2 == null)?"<null>":this.lineItemAmountsV2));
        sb.append(',');
        sb.append("addedAt");
        sb.append('=');
        sb.append(((this.addedAt == null)?"<null>":this.addedAt));
        sb.append(',');
        sb.append("lastModifiedAt");
        sb.append('=');
        sb.append(((this.lastModifiedAt == null)?"<null>":this.lastModifiedAt));
        sb.append(',');
        sb.append("productMMId");
        sb.append('=');
        sb.append(((this.productMMId == null)?"<null>":this.productMMId));
        sb.append(',');
        sb.append("tdcDocumentAvailable");
        sb.append('=');
        sb.append(((this.tdcDocumentAvailable == null)?"<null>":this.tdcDocumentAvailable));
        sb.append(',');
        sb.append("salesForceSkuId");
        sb.append('=');
        sb.append(((this.salesForceSkuId == null)?"<null>":this.salesForceSkuId));
        sb.append(',');
        sb.append("lineItemStatus");
        sb.append('=');
        sb.append(((this.lineItemStatus == null)?"<null>":this.lineItemStatus));
        sb.append(',');
        sb.append("pricingMode");
        sb.append('=');
        sb.append(((this.pricingMode == null)?"<null>":this.pricingMode));
        sb.append(',');
        sb.append("forFreight");
        sb.append('=');
        sb.append(((this.forFreight == null)?"<null>":this.forFreight));
        sb.append(',');
        sb.append("portalPriceAvailable");
        sb.append('=');
        sb.append(((this.portalPriceAvailable == null)?"<null>":this.portalPriceAvailable));
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
        result = ((result* 31)+((this.channelAddress == null)? 0 :this.channelAddress.hashCode()));
        result = ((result* 31)+((this.productForm == null)? 0 :this.productForm.hashCode()));
        result = ((result* 31)+((this.addedAt == null)? 0 :this.addedAt.hashCode()));
        result = ((result* 31)+((this.lastModifiedAt == null)? 0 :this.lastModifiedAt.hashCode()));
        result = ((result* 31)+((this.sellerName == null)? 0 :this.sellerName.hashCode()));
        result = ((result* 31)+((this.forFreight == null)? 0 :this.forFreight.hashCode()));
        result = ((result* 31)+((this.productKey == null)? 0 :this.productKey.hashCode()));
        result = ((result* 31)+((this.payableAmount == null)? 0 :this.payableAmount.hashCode()));
        result = ((result* 31)+((this.subGrade == null)? 0 :this.subGrade.hashCode()));
        result = ((result* 31)+((this.lineItemAmount == null)? 0 :this.lineItemAmount.hashCode()));
        result = ((result* 31)+((this.pricingMode == null)? 0 :this.pricingMode.hashCode()));
        result = ((result* 31)+((this.lineItemIdentifier == null)? 0 :this.lineItemIdentifier.hashCode()));
        result = ((result* 31)+((this.totalQuantityReadyForDispatch == null)? 0 :this.totalQuantityReadyForDispatch.hashCode()));
        result = ((result* 31)+((this.imageUrl == null)? 0 :this.imageUrl.hashCode()));
        result = ((result* 31)+((this.productMMId == null)? 0 :this.productMMId.hashCode()));
        result = ((result* 31)+((this.portalPriceAvailable == null)? 0 :this.portalPriceAvailable.hashCode()));
        result = ((result* 31)+((this.variantId == null)? 0 :this.variantId.hashCode()));
        result = ((result* 31)+((this.lineItemStatus == null)? 0 :this.lineItemStatus.hashCode()));
        result = ((result* 31)+((this.variantKey == null)? 0 :this.variantKey.hashCode()));
        result = ((result* 31)+((this.sku == null)? 0 :this.sku.hashCode()));
        result = ((result* 31)+((this.brand == null)? 0 :this.brand.hashCode()));
        result = ((result* 31)+((this.productType == null)? 0 :this.productType.hashCode()));
        result = ((result* 31)+((this.availableQuantity == null)? 0 :this.availableQuantity.hashCode()));
        result = ((result* 31)+((this.quantity == null)? 0 :this.quantity.hashCode()));
        result = ((result* 31)+((this.sellerKey == null)? 0 :this.sellerKey.hashCode()));
        result = ((result* 31)+((this.lineItemTaxRatio == null)? 0 :this.lineItemTaxRatio.hashCode()));
        result = ((result* 31)+((this.lineItemTaxedAmount == null)? 0 :this.lineItemTaxedAmount.hashCode()));
        result = ((result* 31)+((this.lineItemAmounts == null)? 0 :this.lineItemAmounts.hashCode()));
        result = ((result* 31)+((this.portalLineItemPrice == null)? 0 :this.portalLineItemPrice.hashCode()));
        result = ((result* 31)+((this.productSlug == null)? 0 :this.productSlug.hashCode()));
        result = ((result* 31)+((this.lineItemAmountsV2 == null)? 0 :this.lineItemAmountsV2 .hashCode()));
        result = ((result* 31)+((this.freightInclusivePrice == null)? 0 :this.freightInclusivePrice.hashCode()));
        result = ((result* 31)+((this.channelKey == null)? 0 :this.channelKey.hashCode()));
        result = ((result* 31)+((this.grade == null)? 0 :this.grade.hashCode()));
        result = ((result* 31)+((this.name == null)? 0 :this.name.hashCode()));
        result = ((result* 31)+((this.distributorLineItemPrice == null)? 0 :this.distributorLineItemPrice.hashCode()));
        result = ((result* 31)+((this.tdcDocumentAvailable == null)? 0 :this.tdcDocumentAvailable.hashCode()));
        result = ((result* 31)+((this.salesForceSkuId == null)? 0 :this.salesForceSkuId.hashCode()));
        result = ((result* 31)+((this.attributes == null)? 0 :this.attributes.hashCode()));
        result = ((result* 31)+((this.channelName == null)? 0 :this.channelName.hashCode()));
        result = ((result* 31)+((this.ctAttributes == null)? 0 :this.ctAttributes.hashCode()));
        result = ((result* 31)+((this.lineItemPriceList == null)? 0 :this.lineItemPriceList.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.sellerVendorMasterId == null)? 0 :this.sellerVendorMasterId.hashCode()));
        result = ((result* 31)+((this.deliveryStatus == null)? 0 :this.deliveryStatus.hashCode()));
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
        return ((((((((((((((((((((((((((((((((((((((((((((((this.channelAddress == rhs.channelAddress)||((this.channelAddress!= null)&&this.channelAddress.equals(rhs.channelAddress)))&&((this.productForm == rhs.productForm)||((this.productForm!= null)&&this.productForm.equals(rhs.productForm))))&&((this.addedAt == rhs.addedAt)||((this.addedAt!= null)&&this.addedAt.equals(rhs.addedAt))))&&((this.lastModifiedAt == rhs.lastModifiedAt)||((this.lastModifiedAt!= null)&&this.lastModifiedAt.equals(rhs.lastModifiedAt))))&&((this.sellerName == rhs.sellerName)||((this.sellerName!= null)&&this.sellerName.equals(rhs.sellerName))))&&((this.forFreight == rhs.forFreight)||((this.forFreight!= null)&&this.forFreight.equals(rhs.forFreight))))&&((this.productKey == rhs.productKey)||((this.productKey!= null)&&this.productKey.equals(rhs.productKey))))&&((this.payableAmount == rhs.payableAmount)||((this.payableAmount!= null)&&this.payableAmount.equals(rhs.payableAmount))))&&((this.subGrade == rhs.subGrade)||((this.subGrade!= null)&&this.subGrade.equals(rhs.subGrade))))&&((this.lineItemAmount == rhs.lineItemAmount)||((this.lineItemAmount!= null)&&this.lineItemAmount.equals(rhs.lineItemAmount))))&&((this.pricingMode == rhs.pricingMode)||((this.pricingMode!= null)&&this.pricingMode.equals(rhs.pricingMode))))&&((this.lineItemIdentifier == rhs.lineItemIdentifier)||((this.lineItemIdentifier!= null)&&this.lineItemIdentifier.equals(rhs.lineItemIdentifier))))&&((this.totalQuantityReadyForDispatch == rhs.totalQuantityReadyForDispatch)||((this.totalQuantityReadyForDispatch!= null)&&this.totalQuantityReadyForDispatch.equals(rhs.totalQuantityReadyForDispatch))))&&((this.imageUrl == rhs.imageUrl)||((this.imageUrl!= null)&&this.imageUrl.equals(rhs.imageUrl))))&&((this.productMMId == rhs.productMMId)||((this.productMMId!= null)&&this.productMMId.equals(rhs.productMMId))))&&((this.portalPriceAvailable == rhs.portalPriceAvailable)||((this.portalPriceAvailable!= null)&&this.portalPriceAvailable.equals(rhs.portalPriceAvailable))))&&((this.variantId == rhs.variantId)||((this.variantId!= null)&&this.variantId.equals(rhs.variantId))))&&((this.lineItemStatus == rhs.lineItemStatus)||((this.lineItemStatus!= null)&&this.lineItemStatus.equals(rhs.lineItemStatus))))&&((this.variantKey == rhs.variantKey)||((this.variantKey!= null)&&this.variantKey.equals(rhs.variantKey))))&&((this.sku == rhs.sku)||((this.sku!= null)&&this.sku.equals(rhs.sku))))&&((this.brand == rhs.brand)||((this.brand!= null)&&this.brand.equals(rhs.brand))))&&((this.productType == rhs.productType)||((this.productType!= null)&&this.productType.equals(rhs.productType))))&&((this.availableQuantity == rhs.availableQuantity)||((this.availableQuantity!= null)&&this.availableQuantity.equals(rhs.availableQuantity))))&&((this.quantity == rhs.quantity)||((this.quantity!= null)&&this.quantity.equals(rhs.quantity))))&&((this.sellerKey == rhs.sellerKey)||((this.sellerKey!= null)&&this.sellerKey.equals(rhs.sellerKey))))&&((this.lineItemTaxRatio == rhs.lineItemTaxRatio)||((this.lineItemTaxRatio!= null)&&this.lineItemTaxRatio.equals(rhs.lineItemTaxRatio))))&&((this.lineItemTaxedAmount == rhs.lineItemTaxedAmount)||((this.lineItemTaxedAmount!= null)&&this.lineItemTaxedAmount.equals(rhs.lineItemTaxedAmount))))&&((this.lineItemAmounts == rhs.lineItemAmounts)||((this.lineItemAmounts!= null)&&this.lineItemAmounts.equals(rhs.lineItemAmounts))))&&((this.portalLineItemPrice == rhs.portalLineItemPrice)||((this.portalLineItemPrice!= null)&&this.portalLineItemPrice.equals(rhs.portalLineItemPrice))))&&((this.productSlug == rhs.productSlug)||((this.productSlug!= null)&&this.productSlug.equals(rhs.productSlug))))&&((this.lineItemAmountsV2 == rhs.lineItemAmountsV2)||((this.lineItemAmountsV2 != null)&&this.lineItemAmountsV2 .equals(rhs.lineItemAmountsV2))))&&((this.freightInclusivePrice == rhs.freightInclusivePrice)||((this.freightInclusivePrice!= null)&&this.freightInclusivePrice.equals(rhs.freightInclusivePrice))))&&((this.channelKey == rhs.channelKey)||((this.channelKey!= null)&&this.channelKey.equals(rhs.channelKey))))&&((this.grade == rhs.grade)||((this.grade!= null)&&this.grade.equals(rhs.grade))))&&((this.name == rhs.name)||((this.name!= null)&&this.name.equals(rhs.name))))&&((this.distributorLineItemPrice == rhs.distributorLineItemPrice)||((this.distributorLineItemPrice!= null)&&this.distributorLineItemPrice.equals(rhs.distributorLineItemPrice))))&&((this.tdcDocumentAvailable == rhs.tdcDocumentAvailable)||((this.tdcDocumentAvailable!= null)&&this.tdcDocumentAvailable.equals(rhs.tdcDocumentAvailable))))&&((this.salesForceSkuId == rhs.salesForceSkuId)||((this.salesForceSkuId!= null)&&this.salesForceSkuId.equals(rhs.salesForceSkuId))))&&((this.attributes == rhs.attributes)||((this.attributes!= null)&&this.attributes.equals(rhs.attributes))))&&((this.channelName == rhs.channelName)||((this.channelName!= null)&&this.channelName.equals(rhs.channelName))))&&((this.ctAttributes == rhs.ctAttributes)||((this.ctAttributes!= null)&&this.ctAttributes.equals(rhs.ctAttributes))))&&((this.lineItemPriceList == rhs.lineItemPriceList)||((this.lineItemPriceList!= null)&&this.lineItemPriceList.equals(rhs.lineItemPriceList))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.sellerVendorMasterId == rhs.sellerVendorMasterId)||((this.sellerVendorMasterId!= null)&&this.sellerVendorMasterId.equals(rhs.sellerVendorMasterId))))&&((this.deliveryStatus == rhs.deliveryStatus)||((this.deliveryStatus!= null)&&this.deliveryStatus.equals(rhs.deliveryStatus))));
    }

}

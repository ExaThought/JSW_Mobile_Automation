
package com.qapitol.jsw.apitest.pojos.order.rcv2Response;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "lineItemCustomData",
    "line_item_id",
    "product_mmid",
    "attributes",
    "purchased_quantity",
    "primary_quantity",
    "vendor_location_key",
    "source_vendor_location_key",
    "pricing_mode",
    "freight_category_group",
    "line_item_price",
    "line_item_meta_data",
    "documents",
    "nco_details",
    "catalouge_info"
})
@Generated("jsonschema2pojo")
public class LineItem {

    @JsonProperty("lineItemCustomData")
    private Object lineItemCustomData;
    @JsonProperty("line_item_id")
    private String lineItemId;
    @JsonProperty("product_mmid")
    private String productMmid;
    @JsonProperty("attributes")
    private List<Attribute> attributes = new ArrayList<Attribute>();
    @JsonProperty("purchased_quantity")
    private PurchasedQuantity purchasedQuantity;
    @JsonProperty("primary_quantity")
    private PrimaryQuantity primaryQuantity;
    @JsonProperty("vendor_location_key")
    private String vendorLocationKey;
    @JsonProperty("source_vendor_location_key")
    private Object sourceVendorLocationKey;
    @JsonProperty("pricing_mode")
    private String pricingMode;
    @JsonProperty("freight_category_group")
    private Object freightCategoryGroup;
    @JsonProperty("line_item_price")
    private LineItemPrice lineItemPrice;
    @JsonProperty("line_item_meta_data")
    private LineItemMetaData lineItemMetaData;
    @JsonProperty("documents")
    private Object documents;
    @JsonProperty("nco_details")
    private Object ncoDetails;
    @JsonProperty("catalouge_info")
    private CatalougeInfo catalougeInfo;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("lineItemCustomData")
    public Object getLineItemCustomData() {
        return lineItemCustomData;
    }

    @JsonProperty("lineItemCustomData")
    public void setLineItemCustomData(Object lineItemCustomData) {
        this.lineItemCustomData = lineItemCustomData;
    }

    @JsonProperty("line_item_id")
    public String getLineItemId() {
        return lineItemId;
    }

    @JsonProperty("line_item_id")
    public void setLineItemId(String lineItemId) {
        this.lineItemId = lineItemId;
    }

    @JsonProperty("product_mmid")
    public String getProductMmid() {
        return productMmid;
    }

    @JsonProperty("product_mmid")
    public void setProductMmid(String productMmid) {
        this.productMmid = productMmid;
    }

    @JsonProperty("attributes")
    public List<Attribute> getAttributes() {
        return attributes;
    }

    @JsonProperty("attributes")
    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    @JsonProperty("purchased_quantity")
    public PurchasedQuantity getPurchasedQuantity() {
        return purchasedQuantity;
    }

    @JsonProperty("purchased_quantity")
    public void setPurchasedQuantity(PurchasedQuantity purchasedQuantity) {
        this.purchasedQuantity = purchasedQuantity;
    }

    @JsonProperty("primary_quantity")
    public PrimaryQuantity getPrimaryQuantity() {
        return primaryQuantity;
    }

    @JsonProperty("primary_quantity")
    public void setPrimaryQuantity(PrimaryQuantity primaryQuantity) {
        this.primaryQuantity = primaryQuantity;
    }

    @JsonProperty("vendor_location_key")
    public String getVendorLocationKey() {
        return vendorLocationKey;
    }

    @JsonProperty("vendor_location_key")
    public void setVendorLocationKey(String vendorLocationKey) {
        this.vendorLocationKey = vendorLocationKey;
    }

    @JsonProperty("source_vendor_location_key")
    public Object getSourceVendorLocationKey() {
        return sourceVendorLocationKey;
    }

    @JsonProperty("source_vendor_location_key")
    public void setSourceVendorLocationKey(Object sourceVendorLocationKey) {
        this.sourceVendorLocationKey = sourceVendorLocationKey;
    }

    @JsonProperty("pricing_mode")
    public String getPricingMode() {
        return pricingMode;
    }

    @JsonProperty("pricing_mode")
    public void setPricingMode(String pricingMode) {
        this.pricingMode = pricingMode;
    }

    @JsonProperty("freight_category_group")
    public Object getFreightCategoryGroup() {
        return freightCategoryGroup;
    }

    @JsonProperty("freight_category_group")
    public void setFreightCategoryGroup(Object freightCategoryGroup) {
        this.freightCategoryGroup = freightCategoryGroup;
    }

    @JsonProperty("line_item_price")
    public LineItemPrice getLineItemPrice() {
        return lineItemPrice;
    }

    @JsonProperty("line_item_price")
    public void setLineItemPrice(LineItemPrice lineItemPrice) {
        this.lineItemPrice = lineItemPrice;
    }

    @JsonProperty("line_item_meta_data")
    public LineItemMetaData getLineItemMetaData() {
        return lineItemMetaData;
    }

    @JsonProperty("line_item_meta_data")
    public void setLineItemMetaData(LineItemMetaData lineItemMetaData) {
        this.lineItemMetaData = lineItemMetaData;
    }

    @JsonProperty("documents")
    public Object getDocuments() {
        return documents;
    }

    @JsonProperty("documents")
    public void setDocuments(Object documents) {
        this.documents = documents;
    }

    @JsonProperty("nco_details")
    public Object getNcoDetails() {
        return ncoDetails;
    }

    @JsonProperty("nco_details")
    public void setNcoDetails(Object ncoDetails) {
        this.ncoDetails = ncoDetails;
    }

    @JsonProperty("catalouge_info")
    public CatalougeInfo getCatalougeInfo() {
        return catalougeInfo;
    }

    @JsonProperty("catalouge_info")
    public void setCatalougeInfo(CatalougeInfo catalougeInfo) {
        this.catalougeInfo = catalougeInfo;
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
        sb.append("lineItemCustomData");
        sb.append('=');
        sb.append(((this.lineItemCustomData == null)?"<null>":this.lineItemCustomData));
        sb.append(',');
        sb.append("lineItemId");
        sb.append('=');
        sb.append(((this.lineItemId == null)?"<null>":this.lineItemId));
        sb.append(',');
        sb.append("productMmid");
        sb.append('=');
        sb.append(((this.productMmid == null)?"<null>":this.productMmid));
        sb.append(',');
        sb.append("attributes");
        sb.append('=');
        sb.append(((this.attributes == null)?"<null>":this.attributes));
        sb.append(',');
        sb.append("purchasedQuantity");
        sb.append('=');
        sb.append(((this.purchasedQuantity == null)?"<null>":this.purchasedQuantity));
        sb.append(',');
        sb.append("primaryQuantity");
        sb.append('=');
        sb.append(((this.primaryQuantity == null)?"<null>":this.primaryQuantity));
        sb.append(',');
        sb.append("vendorLocationKey");
        sb.append('=');
        sb.append(((this.vendorLocationKey == null)?"<null>":this.vendorLocationKey));
        sb.append(',');
        sb.append("sourceVendorLocationKey");
        sb.append('=');
        sb.append(((this.sourceVendorLocationKey == null)?"<null>":this.sourceVendorLocationKey));
        sb.append(',');
        sb.append("pricingMode");
        sb.append('=');
        sb.append(((this.pricingMode == null)?"<null>":this.pricingMode));
        sb.append(',');
        sb.append("freightCategoryGroup");
        sb.append('=');
        sb.append(((this.freightCategoryGroup == null)?"<null>":this.freightCategoryGroup));
        sb.append(',');
        sb.append("lineItemPrice");
        sb.append('=');
        sb.append(((this.lineItemPrice == null)?"<null>":this.lineItemPrice));
        sb.append(',');
        sb.append("lineItemMetaData");
        sb.append('=');
        sb.append(((this.lineItemMetaData == null)?"<null>":this.lineItemMetaData));
        sb.append(',');
        sb.append("documents");
        sb.append('=');
        sb.append(((this.documents == null)?"<null>":this.documents));
        sb.append(',');
        sb.append("ncoDetails");
        sb.append('=');
        sb.append(((this.ncoDetails == null)?"<null>":this.ncoDetails));
        sb.append(',');
        sb.append("catalougeInfo");
        sb.append('=');
        sb.append(((this.catalougeInfo == null)?"<null>":this.catalougeInfo));
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
        result = ((result* 31)+((this.vendorLocationKey == null)? 0 :this.vendorLocationKey.hashCode()));
        result = ((result* 31)+((this.lineItemPrice == null)? 0 :this.lineItemPrice.hashCode()));
        result = ((result* 31)+((this.lineItemId == null)? 0 :this.lineItemId.hashCode()));
        result = ((result* 31)+((this.documents == null)? 0 :this.documents.hashCode()));
        result = ((result* 31)+((this.catalougeInfo == null)? 0 :this.catalougeInfo.hashCode()));
        result = ((result* 31)+((this.lineItemCustomData == null)? 0 :this.lineItemCustomData.hashCode()));
        result = ((result* 31)+((this.sourceVendorLocationKey == null)? 0 :this.sourceVendorLocationKey.hashCode()));
        result = ((result* 31)+((this.ncoDetails == null)? 0 :this.ncoDetails.hashCode()));
        result = ((result* 31)+((this.pricingMode == null)? 0 :this.pricingMode.hashCode()));
        result = ((result* 31)+((this.lineItemMetaData == null)? 0 :this.lineItemMetaData.hashCode()));
        result = ((result* 31)+((this.primaryQuantity == null)? 0 :this.primaryQuantity.hashCode()));
        result = ((result* 31)+((this.freightCategoryGroup == null)? 0 :this.freightCategoryGroup.hashCode()));
        result = ((result* 31)+((this.attributes == null)? 0 :this.attributes.hashCode()));
        result = ((result* 31)+((this.purchasedQuantity == null)? 0 :this.purchasedQuantity.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.productMmid == null)? 0 :this.productMmid.hashCode()));
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
        return (((((((((((((((((this.vendorLocationKey == rhs.vendorLocationKey)||((this.vendorLocationKey!= null)&&this.vendorLocationKey.equals(rhs.vendorLocationKey)))&&((this.lineItemPrice == rhs.lineItemPrice)||((this.lineItemPrice!= null)&&this.lineItemPrice.equals(rhs.lineItemPrice))))&&((this.lineItemId == rhs.lineItemId)||((this.lineItemId!= null)&&this.lineItemId.equals(rhs.lineItemId))))&&((this.documents == rhs.documents)||((this.documents!= null)&&this.documents.equals(rhs.documents))))&&((this.catalougeInfo == rhs.catalougeInfo)||((this.catalougeInfo!= null)&&this.catalougeInfo.equals(rhs.catalougeInfo))))&&((this.lineItemCustomData == rhs.lineItemCustomData)||((this.lineItemCustomData!= null)&&this.lineItemCustomData.equals(rhs.lineItemCustomData))))&&((this.sourceVendorLocationKey == rhs.sourceVendorLocationKey)||((this.sourceVendorLocationKey!= null)&&this.sourceVendorLocationKey.equals(rhs.sourceVendorLocationKey))))&&((this.ncoDetails == rhs.ncoDetails)||((this.ncoDetails!= null)&&this.ncoDetails.equals(rhs.ncoDetails))))&&((this.pricingMode == rhs.pricingMode)||((this.pricingMode!= null)&&this.pricingMode.equals(rhs.pricingMode))))&&((this.lineItemMetaData == rhs.lineItemMetaData)||((this.lineItemMetaData!= null)&&this.lineItemMetaData.equals(rhs.lineItemMetaData))))&&((this.primaryQuantity == rhs.primaryQuantity)||((this.primaryQuantity!= null)&&this.primaryQuantity.equals(rhs.primaryQuantity))))&&((this.freightCategoryGroup == rhs.freightCategoryGroup)||((this.freightCategoryGroup!= null)&&this.freightCategoryGroup.equals(rhs.freightCategoryGroup))))&&((this.attributes == rhs.attributes)||((this.attributes!= null)&&this.attributes.equals(rhs.attributes))))&&((this.purchasedQuantity == rhs.purchasedQuantity)||((this.purchasedQuantity!= null)&&this.purchasedQuantity.equals(rhs.purchasedQuantity))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.productMmid == rhs.productMmid)||((this.productMmid!= null)&&this.productMmid.equals(rhs.productMmid))));
    }

}

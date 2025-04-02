
package com.qapitol.jsw.apitest.pojos.order.createOrder;

import com.fasterxml.jackson.annotation.*;
import com.qapitol.jsw.apitest.pojos.order.getLineItemDetails.CashbackDetails;
import com.qapitol.jsw.apitest.pojos.order.getOrder.CashBacks;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "type",
    "sourceSeller",
    "sellerVendorMasterId",
    "sellerId",
    "sapLineItemId",
    "salesForceSkuId",
    "productKey",
    "orderedQuantity",
    "NCOReason",
    "name",
    "MMID",
    "meta",
    "isNCO",
    "finalSellingPrice",
    "channelKey",
    "attributes"
})
@Generated("jsonschema2pojo")
public class LineItem {

    @JsonProperty("type")
    private String type;
    @JsonProperty("sourceSeller")
    private SourceSeller sourceSeller;
    @JsonProperty("sellerVendorMasterId")
    private String sellerVendorMasterId;
    @JsonProperty("sellerId")
    private String sellerId;
    @JsonProperty("sapLineItemId")
    private Integer sapLineItemId;
    @JsonProperty("salesForceSkuId")
    private String salesForceSkuId;
    @JsonProperty("productKey")
    private String productKey;
    @JsonProperty("orderedQuantity")
    private Double orderedQuantity;
    @JsonProperty("NCOReason")
    private String nCOReason;
    @JsonProperty("name")
    private String name;
    @JsonProperty("MMID")
    private String mmid;
    @JsonProperty("meta")
    private Meta meta;
    @JsonProperty("isNCO")
    private Boolean isNCO;
    @JsonProperty("finalSellingPrice")
    private Double finalSellingPrice;
    @JsonProperty("channelKey")
    private String channelKey;
    @JsonProperty("attributes")
    private Attributes attributes;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();
    @JsonProperty("cashbackDetails")
    private CashbackDetails cashbackDetails;

    @JsonProperty("cashbackDetails")
    public CashbackDetails getCashbackDetails(){return cashbackDetails;}

    @JsonProperty("cashbackDetails")
    public void setCashbackDetails(){this.cashbackDetails= cashbackDetails;}

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("sourceSeller")
    public SourceSeller getSourceSeller() {
        return sourceSeller;
    }

    @JsonProperty("sourceSeller")
    public void setSourceSeller(SourceSeller sourceSeller) {
        this.sourceSeller = sourceSeller;
    }

    @JsonProperty("sellerVendorMasterId")
    public String getSellerVendorMasterId() {
        return sellerVendorMasterId;
    }

    @JsonProperty("sellerVendorMasterId")
    public void setSellerVendorMasterId(String sellerVendorMasterId) {
        this.sellerVendorMasterId = sellerVendorMasterId;
    }

    @JsonProperty("sellerId")
    public String getSellerId() {
        return sellerId;
    }

    @JsonProperty("sellerId")
    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    @JsonProperty("sapLineItemId")
    public Integer getSapLineItemId() {
        return sapLineItemId;
    }

    @JsonProperty("sapLineItemId")
    public void setSapLineItemId(Integer sapLineItemId) {
        this.sapLineItemId = sapLineItemId;
    }

    @JsonProperty("salesForceSkuId")
    public String getSalesForceSkuId() {
        return salesForceSkuId;
    }

    @JsonProperty("salesForceSkuId")
    public void setSalesForceSkuId(String salesForceSkuId) {
        this.salesForceSkuId = salesForceSkuId;
    }

    @JsonProperty("productKey")
    public String getProductKey() {
        return productKey;
    }

    @JsonProperty("productKey")
    public void setProductKey(String productKey) {
        this.productKey = productKey;
    }

    @JsonProperty("orderedQuantity")
    public Double getOrderedQuantity() {
        return orderedQuantity;
    }

    @JsonProperty("orderedQuantity")
    public void setOrderedQuantity(Double orderedQuantity) {
        this.orderedQuantity = orderedQuantity;
    }

    @JsonProperty("NCOReason")
    public String getNCOReason() {
        return nCOReason;
    }

    @JsonProperty("NCOReason")
    public void setNCOReason(String nCOReason) {
        this.nCOReason = nCOReason;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("MMID")
    public String getMmid() {
        return mmid;
    }

    @JsonProperty("MMID")
    public void setMmid(String mmid) {
        this.mmid = mmid;
    }

    @JsonProperty("meta")
    public Meta getMeta() {
        return meta;
    }

    @JsonProperty("meta")
    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    @JsonProperty("isNCO")
    public Boolean getIsNCO() {
        return isNCO;
    }

    @JsonProperty("isNCO")
    public void setIsNCO(Boolean isNCO) {
        this.isNCO = isNCO;
    }

    @JsonProperty("finalSellingPrice")
    public Double getFinalSellingPrice() {
        return finalSellingPrice;
    }

    @JsonProperty("finalSellingPrice")
    public void setFinalSellingPrice(Double finalSellingPrice) {
        this.finalSellingPrice = finalSellingPrice;
    }

    @JsonProperty("channelKey")
    public String getChannelKey() {
        return channelKey;
    }

    @JsonProperty("channelKey")
    public void setChannelKey(String channelKey) {
        this.channelKey = channelKey;
    }

    @JsonProperty("attributes")
    public Attributes getAttributes() {
        return attributes;
    }

    @JsonProperty("attributes")
    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
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
        sb.append("type");
        sb.append('=');
        sb.append(((this.type == null)?"<null>":this.type));
        sb.append(',');
        sb.append("sourceSeller");
        sb.append('=');
        sb.append(((this.sourceSeller == null)?"<null>":this.sourceSeller));
        sb.append(',');
        sb.append("sellerVendorMasterId");
        sb.append('=');
        sb.append(((this.sellerVendorMasterId == null)?"<null>":this.sellerVendorMasterId));
        sb.append(',');
        sb.append("sellerId");
        sb.append('=');
        sb.append(((this.sellerId == null)?"<null>":this.sellerId));
        sb.append(',');
        sb.append("sapLineItemId");
        sb.append('=');
        sb.append(((this.sapLineItemId == null)?"<null>":this.sapLineItemId));
        sb.append(',');
        sb.append("salesForceSkuId");
        sb.append('=');
        sb.append(((this.salesForceSkuId == null)?"<null>":this.salesForceSkuId));
        sb.append(',');
        sb.append("productKey");
        sb.append('=');
        sb.append(((this.productKey == null)?"<null>":this.productKey));
        sb.append(',');
        sb.append("orderedQuantity");
        sb.append('=');
        sb.append(((this.orderedQuantity == null)?"<null>":this.orderedQuantity));
        sb.append(',');
        sb.append("nCOReason");
        sb.append('=');
        sb.append(((this.nCOReason == null)?"<null>":this.nCOReason));
        sb.append(',');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null)?"<null>":this.name));
        sb.append(',');
        sb.append("mmid");
        sb.append('=');
        sb.append(((this.mmid == null)?"<null>":this.mmid));
        sb.append(',');
        sb.append("meta");
        sb.append('=');
        sb.append(((this.meta == null)?"<null>":this.meta));
        sb.append(',');
        sb.append("isNCO");
        sb.append('=');
        sb.append(((this.isNCO == null)?"<null>":this.isNCO));
        sb.append(',');
        sb.append("finalSellingPrice");
        sb.append('=');
        sb.append(((this.finalSellingPrice == null)?"<null>":this.finalSellingPrice));
        sb.append(',');
        sb.append("channelKey");
        sb.append('=');
        sb.append(((this.channelKey == null)?"<null>":this.channelKey));
        sb.append(',');
        sb.append("attributes");
        sb.append('=');
        sb.append(((this.attributes == null)?"<null>":this.attributes));
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
        result = ((result* 31)+((this.orderedQuantity == null)? 0 :this.orderedQuantity.hashCode()));
        result = ((result* 31)+((this.mmid == null)? 0 :this.mmid.hashCode()));
        result = ((result* 31)+((this.type == null)? 0 :this.type.hashCode()));
        result = ((result* 31)+((this.productKey == null)? 0 :this.productKey.hashCode()));
        result = ((result* 31)+((this.isNCO == null)? 0 :this.isNCO.hashCode()));
        result = ((result* 31)+((this.sellerId == null)? 0 :this.sellerId.hashCode()));
        result = ((result* 31)+((this.nCOReason == null)? 0 :this.nCOReason.hashCode()));
        result = ((result* 31)+((this.sourceSeller == null)? 0 :this.sourceSeller.hashCode()));
        result = ((result* 31)+((this.channelKey == null)? 0 :this.channelKey.hashCode()));
        result = ((result* 31)+((this.meta == null)? 0 :this.meta.hashCode()));
        result = ((result* 31)+((this.sapLineItemId == null)? 0 :this.sapLineItemId.hashCode()));
        result = ((result* 31)+((this.name == null)? 0 :this.name.hashCode()));
        result = ((result* 31)+((this.salesForceSkuId == null)? 0 :this.salesForceSkuId.hashCode()));
        result = ((result* 31)+((this.attributes == null)? 0 :this.attributes.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.sellerVendorMasterId == null)? 0 :this.sellerVendorMasterId.hashCode()));
        result = ((result* 31)+((this.finalSellingPrice == null)? 0 :this.finalSellingPrice.hashCode()));
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
        return ((((((((((((((((((this.orderedQuantity == rhs.orderedQuantity)||((this.orderedQuantity!= null)&&this.orderedQuantity.equals(rhs.orderedQuantity)))&&((this.mmid == rhs.mmid)||((this.mmid!= null)&&this.mmid.equals(rhs.mmid))))&&((this.type == rhs.type)||((this.type!= null)&&this.type.equals(rhs.type))))&&((this.productKey == rhs.productKey)||((this.productKey!= null)&&this.productKey.equals(rhs.productKey))))&&((this.isNCO == rhs.isNCO)||((this.isNCO!= null)&&this.isNCO.equals(rhs.isNCO))))&&((this.sellerId == rhs.sellerId)||((this.sellerId!= null)&&this.sellerId.equals(rhs.sellerId))))&&((this.nCOReason == rhs.nCOReason)||((this.nCOReason!= null)&&this.nCOReason.equals(rhs.nCOReason))))&&((this.sourceSeller == rhs.sourceSeller)||((this.sourceSeller!= null)&&this.sourceSeller.equals(rhs.sourceSeller))))&&((this.channelKey == rhs.channelKey)||((this.channelKey!= null)&&this.channelKey.equals(rhs.channelKey))))&&((this.meta == rhs.meta)||((this.meta!= null)&&this.meta.equals(rhs.meta))))&&((this.sapLineItemId == rhs.sapLineItemId)||((this.sapLineItemId!= null)&&this.sapLineItemId.equals(rhs.sapLineItemId))))&&((this.name == rhs.name)||((this.name!= null)&&this.name.equals(rhs.name))))&&((this.salesForceSkuId == rhs.salesForceSkuId)||((this.salesForceSkuId!= null)&&this.salesForceSkuId.equals(rhs.salesForceSkuId))))&&((this.attributes == rhs.attributes)||((this.attributes!= null)&&this.attributes.equals(rhs.attributes))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.sellerVendorMasterId == rhs.sellerVendorMasterId)||((this.sellerVendorMasterId!= null)&&this.sellerVendorMasterId.equals(rhs.sellerVendorMasterId))))&&((this.finalSellingPrice == rhs.finalSellingPrice)||((this.finalSellingPrice!= null)&&this.finalSellingPrice.equals(rhs.finalSellingPrice))));
    }

}

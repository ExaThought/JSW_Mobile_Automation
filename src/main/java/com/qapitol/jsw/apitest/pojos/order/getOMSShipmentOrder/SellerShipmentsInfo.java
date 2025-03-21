
package com.qapitol.jsw.apitest.pojos.order.getOMSShipmentOrder;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "sellerOrderNumber",
    "sellerOrderId",
    "sellerInfo",
    "shipmentDetails",
    "lineItems",
    "warehouses"
})
@Generated("jsonschema2pojo")
public class SellerShipmentsInfo {

    @JsonProperty("sellerOrderNumber")
    private String sellerOrderNumber;
    @JsonProperty("sellerOrderId")
    private Integer sellerOrderId;
    @JsonProperty("sellerInfo")
    private SellerInfo sellerInfo;
    @JsonProperty("shipmentDetails")
    private List<ShipmentDetails> shipmentDetails;
    @JsonProperty("lineItems")
    private List<LineItem> lineItems = new ArrayList<LineItem>();
    @JsonProperty("warehouses")
    private Object warehouses;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("sellerOrderNumber")
    public String getSellerOrderNumber() {
        return sellerOrderNumber;
    }

    @JsonProperty("sellerOrderNumber")
    public void setSellerOrderNumber(String sellerOrderNumber) {
        this.sellerOrderNumber = sellerOrderNumber;
    }

    @JsonProperty("sellerOrderId")
    public Integer getSellerOrderId() {
        return sellerOrderId;
    }

    @JsonProperty("sellerOrderId")
    public void setSellerOrderId(Integer sellerOrderId) {
        this.sellerOrderId = sellerOrderId;
    }

    @JsonProperty("sellerInfo")
    public SellerInfo getSellerInfo() {
        return sellerInfo;
    }

    @JsonProperty("sellerInfo")
    public void setSellerInfo(SellerInfo sellerInfo) {
        this.sellerInfo = sellerInfo;
    }

    @JsonProperty("shipmentDetails")
    public List<ShipmentDetails> getShipmentDetails() {
        return shipmentDetails;
    }

    @JsonProperty("shipmentDetails")
    public void setShipmentDetails(List<ShipmentDetails> shipmentDetails) {
        this.shipmentDetails = shipmentDetails;
    }

    @JsonProperty("lineItems")
    public List<LineItem> getLineItems() {
        return lineItems;
    }

    @JsonProperty("lineItems")
    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    @JsonProperty("warehouses")
    public Object getWarehouses() {
        return warehouses;
    }

    @JsonProperty("warehouses")
    public void setWarehouses(Object warehouses) {
        this.warehouses = warehouses;
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
        sb.append(SellerShipmentsInfo.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("sellerOrderNumber");
        sb.append('=');
        sb.append(((this.sellerOrderNumber == null)?"<null>":this.sellerOrderNumber));
        sb.append(',');
        sb.append("sellerOrderId");
        sb.append('=');
        sb.append(((this.sellerOrderId == null)?"<null>":this.sellerOrderId));
        sb.append(',');
        sb.append("sellerInfo");
        sb.append('=');
        sb.append(((this.sellerInfo == null)?"<null>":this.sellerInfo));
        sb.append(',');
        sb.append("shipmentDetails");
        sb.append('=');
        sb.append(((this.shipmentDetails == null)?"<null>":this.shipmentDetails));
        sb.append(',');
        sb.append("lineItems");
        sb.append('=');
        sb.append(((this.lineItems == null)?"<null>":this.lineItems));
        sb.append(',');
        sb.append("warehouses");
        sb.append('=');
        sb.append(((this.warehouses == null)?"<null>":this.warehouses));
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
        result = ((result* 31)+((this.sellerOrderNumber == null)? 0 :this.sellerOrderNumber.hashCode()));
        result = ((result* 31)+((this.lineItems == null)? 0 :this.lineItems.hashCode()));
        result = ((result* 31)+((this.shipmentDetails == null)? 0 :this.shipmentDetails.hashCode()));
        result = ((result* 31)+((this.warehouses == null)? 0 :this.warehouses.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.sellerInfo == null)? 0 :this.sellerInfo.hashCode()));
        result = ((result* 31)+((this.sellerOrderId == null)? 0 :this.sellerOrderId.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof SellerShipmentsInfo) == false) {
            return false;
        }
        SellerShipmentsInfo rhs = ((SellerShipmentsInfo) other);
        return ((((((((this.sellerOrderNumber == rhs.sellerOrderNumber)||((this.sellerOrderNumber!= null)&&this.sellerOrderNumber.equals(rhs.sellerOrderNumber)))&&((this.lineItems == rhs.lineItems)||((this.lineItems!= null)&&this.lineItems.equals(rhs.lineItems))))&&((this.shipmentDetails == rhs.shipmentDetails)||((this.shipmentDetails!= null)&&this.shipmentDetails.equals(rhs.shipmentDetails))))&&((this.warehouses == rhs.warehouses)||((this.warehouses!= null)&&this.warehouses.equals(rhs.warehouses))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.sellerInfo == rhs.sellerInfo)||((this.sellerInfo!= null)&&this.sellerInfo.equals(rhs.sellerInfo))))&&((this.sellerOrderId == rhs.sellerOrderId)||((this.sellerOrderId!= null)&&this.sellerOrderId.equals(rhs.sellerOrderId))));
    }

}

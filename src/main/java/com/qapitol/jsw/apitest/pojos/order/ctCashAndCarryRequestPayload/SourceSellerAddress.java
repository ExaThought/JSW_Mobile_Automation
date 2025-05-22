
package com.qapitol.jsw.apitest.pojos.order.ctCashAndCarryRequestPayload;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "sourceSellerShipFromAddress",
    "sourceSellerBillFromAddress"
})
@Generated("jsonschema2pojo")
public class SourceSellerAddress {

    @JsonProperty("sourceSellerShipFromAddress")
    private SourceSellerShipFromAddress sourceSellerShipFromAddress;
    @JsonProperty("sourceSellerBillFromAddress")
    private SourceSellerBillFromAddress sourceSellerBillFromAddress;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("sourceSellerShipFromAddress")
    public SourceSellerShipFromAddress getSourceSellerShipFromAddress() {
        return sourceSellerShipFromAddress;
    }

    @JsonProperty("sourceSellerShipFromAddress")
    public void setSourceSellerShipFromAddress(SourceSellerShipFromAddress sourceSellerShipFromAddress) {
        this.sourceSellerShipFromAddress = sourceSellerShipFromAddress;
    }

    @JsonProperty("sourceSellerBillFromAddress")
    public SourceSellerBillFromAddress getSourceSellerBillFromAddress() {
        return sourceSellerBillFromAddress;
    }

    @JsonProperty("sourceSellerBillFromAddress")
    public void setSourceSellerBillFromAddress(SourceSellerBillFromAddress sourceSellerBillFromAddress) {
        this.sourceSellerBillFromAddress = sourceSellerBillFromAddress;
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
        sb.append(SourceSellerAddress.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("sourceSellerShipFromAddress");
        sb.append('=');
        sb.append(((this.sourceSellerShipFromAddress == null)?"<null>":this.sourceSellerShipFromAddress));
        sb.append(',');
        sb.append("sourceSellerBillFromAddress");
        sb.append('=');
        sb.append(((this.sourceSellerBillFromAddress == null)?"<null>":this.sourceSellerBillFromAddress));
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
        result = ((result* 31)+((this.sourceSellerBillFromAddress == null)? 0 :this.sourceSellerBillFromAddress.hashCode()));
        result = ((result* 31)+((this.sourceSellerShipFromAddress == null)? 0 :this.sourceSellerShipFromAddress.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof SourceSellerAddress) == false) {
            return false;
        }
        SourceSellerAddress rhs = ((SourceSellerAddress) other);
        return ((((this.sourceSellerBillFromAddress == rhs.sourceSellerBillFromAddress)||((this.sourceSellerBillFromAddress!= null)&&this.sourceSellerBillFromAddress.equals(rhs.sourceSellerBillFromAddress)))&&((this.sourceSellerShipFromAddress == rhs.sourceSellerShipFromAddress)||((this.sourceSellerShipFromAddress!= null)&&this.sourceSellerShipFromAddress.equals(rhs.sourceSellerShipFromAddress))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
    }

}


package com.qapitol.jsw.apitest.pojos.order.createIncomingPayment;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "bank_transaction_id"
})
@Generated("jsonschema2pojo")
public class AcquirerData {

    @JsonProperty("bank_transaction_id")
    private String bankTransactionId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("bank_transaction_id")
    public String getBankTransactionId() {
        return bankTransactionId;
    }

    @JsonProperty("bank_transaction_id")
    public void setBankTransactionId(String bankTransactionId) {
        this.bankTransactionId = bankTransactionId;
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
        sb.append(AcquirerData.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("bankTransactionId");
        sb.append('=');
        sb.append(((this.bankTransactionId == null)?"<null>":this.bankTransactionId));
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
        result = ((result* 31)+((this.bankTransactionId == null)? 0 :this.bankTransactionId.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof AcquirerData) == false) {
            return false;
        }
        AcquirerData rhs = ((AcquirerData) other);
        return (((this.bankTransactionId == rhs.bankTransactionId)||((this.bankTransactionId!= null)&&this.bankTransactionId.equals(rhs.bankTransactionId)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
    }

}

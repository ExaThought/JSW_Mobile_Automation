
package com.qapitol.jsw.apitest.pojos.order.paymentDue;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "totalUsableCreditLimit",
    "totalSanctionedCreditLimit",
    "accountReceivablesOverDueAmount",
    "usableLedgerBalance",
    "totalDueAmount",
    "totalDueNowAmount",
    "totalDueInDaysAmount",
    "totalDueLaterAmount"
})
@Generated("jsonschema2pojo")
public class Data {

    @JsonProperty("totalUsableCreditLimit")
    private Double totalUsableCreditLimit;
    @JsonProperty("totalSanctionedCreditLimit")
    private Double totalSanctionedCreditLimit;
    @JsonProperty("accountReceivablesOverDueAmount")
    private Double accountReceivablesOverDueAmount;
    @JsonProperty("usableLedgerBalance")
    private Double usableLedgerBalance;
    @JsonProperty("totalDueAmount")
    private Double totalDueAmount;
    @JsonProperty("totalDueNowAmount")
    private Double totalDueNowAmount;
    @JsonProperty("totalDueInDaysAmount")
    private Integer totalDueInDaysAmount;
    @JsonProperty("totalDueLaterAmount")
    private Double totalDueLaterAmount;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("totalUsableCreditLimit")
    public Double getTotalUsableCreditLimit() {
        return totalUsableCreditLimit;
    }

    @JsonProperty("totalUsableCreditLimit")
    public void setTotalUsableCreditLimit(Double totalUsableCreditLimit) {
        this.totalUsableCreditLimit = totalUsableCreditLimit;
    }

    @JsonProperty("totalSanctionedCreditLimit")
    public Double getTotalSanctionedCreditLimit() {
        return totalSanctionedCreditLimit;
    }

    @JsonProperty("totalSanctionedCreditLimit")
    public void setTotalSanctionedCreditLimit(Double totalSanctionedCreditLimit) {
        this.totalSanctionedCreditLimit = totalSanctionedCreditLimit;
    }

    @JsonProperty("accountReceivablesOverDueAmount")
    public Double getAccountReceivablesOverDueAmount() {
        return accountReceivablesOverDueAmount;
    }

    @JsonProperty("accountReceivablesOverDueAmount")
    public void setAccountReceivablesOverDueAmount(Double accountReceivablesOverDueAmount) {
        this.accountReceivablesOverDueAmount = accountReceivablesOverDueAmount;
    }

    @JsonProperty("usableLedgerBalance")
    public Double getUsableLedgerBalance() {
        return usableLedgerBalance;
    }

    @JsonProperty("usableLedgerBalance")
    public void setUsableLedgerBalance(Double usableLedgerBalance) {
        this.usableLedgerBalance = usableLedgerBalance;
    }

    @JsonProperty("totalDueAmount")
    public Double getTotalDueAmount() {
        return totalDueAmount;
    }

    @JsonProperty("totalDueAmount")
    public void setTotalDueAmount(Double totalDueAmount) {
        this.totalDueAmount = totalDueAmount;
    }

    @JsonProperty("totalDueNowAmount")
    public Double getTotalDueNowAmount() {
        return totalDueNowAmount;
    }

    @JsonProperty("totalDueNowAmount")
    public void setTotalDueNowAmount(Double totalDueNowAmount) {
        this.totalDueNowAmount = totalDueNowAmount;
    }

    @JsonProperty("totalDueInDaysAmount")
    public Integer getTotalDueInDaysAmount() {
        return totalDueInDaysAmount;
    }

    @JsonProperty("totalDueInDaysAmount")
    public void setTotalDueInDaysAmount(Integer totalDueInDaysAmount) {
        this.totalDueInDaysAmount = totalDueInDaysAmount;
    }

    @JsonProperty("totalDueLaterAmount")
    public Double getTotalDueLaterAmount() {
        return totalDueLaterAmount;
    }

    @JsonProperty("totalDueLaterAmount")
    public void setTotalDueLaterAmount(Double totalDueLaterAmount) {
        this.totalDueLaterAmount = totalDueLaterAmount;
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
        sb.append(Data.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("totalUsableCreditLimit");
        sb.append('=');
        sb.append(((this.totalUsableCreditLimit == null)?"<null>":this.totalUsableCreditLimit));
        sb.append(',');
        sb.append("totalSanctionedCreditLimit");
        sb.append('=');
        sb.append(((this.totalSanctionedCreditLimit == null)?"<null>":this.totalSanctionedCreditLimit));
        sb.append(',');
        sb.append("accountReceivablesOverDueAmount");
        sb.append('=');
        sb.append(((this.accountReceivablesOverDueAmount == null)?"<null>":this.accountReceivablesOverDueAmount));
        sb.append(',');
        sb.append("usableLedgerBalance");
        sb.append('=');
        sb.append(((this.usableLedgerBalance == null)?"<null>":this.usableLedgerBalance));
        sb.append(',');
        sb.append("totalDueAmount");
        sb.append('=');
        sb.append(((this.totalDueAmount == null)?"<null>":this.totalDueAmount));
        sb.append(',');
        sb.append("totalDueNowAmount");
        sb.append('=');
        sb.append(((this.totalDueNowAmount == null)?"<null>":this.totalDueNowAmount));
        sb.append(',');
        sb.append("totalDueInDaysAmount");
        sb.append('=');
        sb.append(((this.totalDueInDaysAmount == null)?"<null>":this.totalDueInDaysAmount));
        sb.append(',');
        sb.append("totalDueLaterAmount");
        sb.append('=');
        sb.append(((this.totalDueLaterAmount == null)?"<null>":this.totalDueLaterAmount));
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
        result = ((result* 31)+((this.totalDueInDaysAmount == null)? 0 :this.totalDueInDaysAmount.hashCode()));
        result = ((result* 31)+((this.totalUsableCreditLimit == null)? 0 :this.totalUsableCreditLimit.hashCode()));
        result = ((result* 31)+((this.totalDueAmount == null)? 0 :this.totalDueAmount.hashCode()));
        result = ((result* 31)+((this.totalDueLaterAmount == null)? 0 :this.totalDueLaterAmount.hashCode()));
        result = ((result* 31)+((this.usableLedgerBalance == null)? 0 :this.usableLedgerBalance.hashCode()));
        result = ((result* 31)+((this.totalDueNowAmount == null)? 0 :this.totalDueNowAmount.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.accountReceivablesOverDueAmount == null)? 0 :this.accountReceivablesOverDueAmount.hashCode()));
        result = ((result* 31)+((this.totalSanctionedCreditLimit == null)? 0 :this.totalSanctionedCreditLimit.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Data) == false) {
            return false;
        }
        Data rhs = ((Data) other);
        return ((((((((((this.totalDueInDaysAmount == rhs.totalDueInDaysAmount)||((this.totalDueInDaysAmount!= null)&&this.totalDueInDaysAmount.equals(rhs.totalDueInDaysAmount)))&&((this.totalUsableCreditLimit == rhs.totalUsableCreditLimit)||((this.totalUsableCreditLimit!= null)&&this.totalUsableCreditLimit.equals(rhs.totalUsableCreditLimit))))&&((this.totalDueAmount == rhs.totalDueAmount)||((this.totalDueAmount!= null)&&this.totalDueAmount.equals(rhs.totalDueAmount))))&&((this.totalDueLaterAmount == rhs.totalDueLaterAmount)||((this.totalDueLaterAmount!= null)&&this.totalDueLaterAmount.equals(rhs.totalDueLaterAmount))))&&((this.usableLedgerBalance == rhs.usableLedgerBalance)||((this.usableLedgerBalance!= null)&&this.usableLedgerBalance.equals(rhs.usableLedgerBalance))))&&((this.totalDueNowAmount == rhs.totalDueNowAmount)||((this.totalDueNowAmount!= null)&&this.totalDueNowAmount.equals(rhs.totalDueNowAmount))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.accountReceivablesOverDueAmount == rhs.accountReceivablesOverDueAmount)||((this.accountReceivablesOverDueAmount!= null)&&this.accountReceivablesOverDueAmount.equals(rhs.accountReceivablesOverDueAmount))))&&((this.totalSanctionedCreditLimit == rhs.totalSanctionedCreditLimit)||((this.totalSanctionedCreditLimit!= null)&&this.totalSanctionedCreditLimit.equals(rhs.totalSanctionedCreditLimit))));
    }

}

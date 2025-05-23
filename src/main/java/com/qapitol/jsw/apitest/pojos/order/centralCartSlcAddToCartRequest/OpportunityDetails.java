
package com.qapitol.jsw.apitest.pojos.order.centralCartSlcAddToCartRequest;

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
    "opportunity_id",
    "opportunity_url",
    "sales_associate_name",
    "opportunity_owner_email",
    "opportunity_string",
    "seller_pi"
})
@Generated("jsonschema2pojo")
public class OpportunityDetails {

    @JsonProperty("opportunity_id")
    private String opportunityId;
    @JsonProperty("opportunity_url")
    private String opportunityUrl;
    @JsonProperty("sales_associate_name")
    private String salesAssociateName;
    @JsonProperty("opportunity_owner_email")
    private String opportunityOwnerEmail;
    @JsonProperty("opportunity_string")
    private String opportunityString;
    @JsonProperty("seller_pi")
    private String sellerPi;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("opportunity_id")
    public String getOpportunityId() {
        return opportunityId;
    }

    @JsonProperty("opportunity_id")
    public void setOpportunityId(String opportunityId) {
        this.opportunityId = opportunityId;
    }

    @JsonProperty("opportunity_url")
    public String getOpportunityUrl() {
        return opportunityUrl;
    }

    @JsonProperty("opportunity_url")
    public void setOpportunityUrl(String opportunityUrl) {
        this.opportunityUrl = opportunityUrl;
    }

    @JsonProperty("sales_associate_name")
    public String getSalesAssociateName() {
        return salesAssociateName;
    }

    @JsonProperty("sales_associate_name")
    public void setSalesAssociateName(String salesAssociateName) {
        this.salesAssociateName = salesAssociateName;
    }

    @JsonProperty("opportunity_owner_email")
    public String getOpportunityOwnerEmail() {
        return opportunityOwnerEmail;
    }

    @JsonProperty("opportunity_owner_email")
    public void setOpportunityOwnerEmail(String opportunityOwnerEmail) {
        this.opportunityOwnerEmail = opportunityOwnerEmail;
    }

    @JsonProperty("opportunity_string")
    public String getOpportunityString() {
        return opportunityString;
    }

    @JsonProperty("opportunity_string")
    public void setOpportunityString(String opportunityString) {
        this.opportunityString = opportunityString;
    }

    @JsonProperty("seller_pi")
    public String getSellerPi() {
        return sellerPi;
    }

    @JsonProperty("seller_pi")
    public void setSellerPi(String sellerPi) {
        this.sellerPi = sellerPi;
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
        sb.append(OpportunityDetails.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("opportunityId");
        sb.append('=');
        sb.append(((this.opportunityId == null)?"<null>":this.opportunityId));
        sb.append(',');
        sb.append("opportunityUrl");
        sb.append('=');
        sb.append(((this.opportunityUrl == null)?"<null>":this.opportunityUrl));
        sb.append(',');
        sb.append("salesAssociateName");
        sb.append('=');
        sb.append(((this.salesAssociateName == null)?"<null>":this.salesAssociateName));
        sb.append(',');
        sb.append("opportunityOwnerEmail");
        sb.append('=');
        sb.append(((this.opportunityOwnerEmail == null)?"<null>":this.opportunityOwnerEmail));
        sb.append(',');
        sb.append("opportunityString");
        sb.append('=');
        sb.append(((this.opportunityString == null)?"<null>":this.opportunityString));
        sb.append(',');
        sb.append("sellerPi");
        sb.append('=');
        sb.append(((this.sellerPi == null)?"<null>":this.sellerPi));
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
        result = ((result* 31)+((this.salesAssociateName == null)? 0 :this.salesAssociateName.hashCode()));
        result = ((result* 31)+((this.sellerPi == null)? 0 :this.sellerPi.hashCode()));
        result = ((result* 31)+((this.opportunityId == null)? 0 :this.opportunityId.hashCode()));
        result = ((result* 31)+((this.opportunityOwnerEmail == null)? 0 :this.opportunityOwnerEmail.hashCode()));
        result = ((result* 31)+((this.opportunityString == null)? 0 :this.opportunityString.hashCode()));
        result = ((result* 31)+((this.opportunityUrl == null)? 0 :this.opportunityUrl.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof OpportunityDetails) == false) {
            return false;
        }
        OpportunityDetails rhs = ((OpportunityDetails) other);
        return ((((((((this.salesAssociateName == rhs.salesAssociateName)||((this.salesAssociateName!= null)&&this.salesAssociateName.equals(rhs.salesAssociateName)))&&((this.sellerPi == rhs.sellerPi)||((this.sellerPi!= null)&&this.sellerPi.equals(rhs.sellerPi))))&&((this.opportunityId == rhs.opportunityId)||((this.opportunityId!= null)&&this.opportunityId.equals(rhs.opportunityId))))&&((this.opportunityOwnerEmail == rhs.opportunityOwnerEmail)||((this.opportunityOwnerEmail!= null)&&this.opportunityOwnerEmail.equals(rhs.opportunityOwnerEmail))))&&((this.opportunityString == rhs.opportunityString)||((this.opportunityString!= null)&&this.opportunityString.equals(rhs.opportunityString))))&&((this.opportunityUrl == rhs.opportunityUrl)||((this.opportunityUrl!= null)&&this.opportunityUrl.equals(rhs.opportunityUrl))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
    }

}

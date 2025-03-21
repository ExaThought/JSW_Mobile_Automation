
package com.qapitol.jsw.apitest.pojos.order.getOMSShipmentOrder;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "primaryEmail",
    "primaryContact",
    "secondaryEmail",
    "secondaryContact"
})
@Generated("jsonschema2pojo")
public class ContactDetails {

    @JsonProperty("primaryEmail")
    private String primaryEmail;
    @JsonProperty("primaryContact")
    private String primaryContact;
    @JsonProperty("secondaryEmail")
    private Object secondaryEmail;
    @JsonProperty("secondaryContact")
    private Object secondaryContact;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("primaryEmail")
    public String getPrimaryEmail() {
        return primaryEmail;
    }

    @JsonProperty("primaryEmail")
    public void setPrimaryEmail(String primaryEmail) {
        this.primaryEmail = primaryEmail;
    }

    @JsonProperty("primaryContact")
    public String getPrimaryContact() {
        return primaryContact;
    }

    @JsonProperty("primaryContact")
    public void setPrimaryContact(String primaryContact) {
        this.primaryContact = primaryContact;
    }

    @JsonProperty("secondaryEmail")
    public Object getSecondaryEmail() {
        return secondaryEmail;
    }

    @JsonProperty("secondaryEmail")
    public void setSecondaryEmail(Object secondaryEmail) {
        this.secondaryEmail = secondaryEmail;
    }

    @JsonProperty("secondaryContact")
    public Object getSecondaryContact() {
        return secondaryContact;
    }

    @JsonProperty("secondaryContact")
    public void setSecondaryContact(Object secondaryContact) {
        this.secondaryContact = secondaryContact;
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
        sb.append(ContactDetails.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("primaryEmail");
        sb.append('=');
        sb.append(((this.primaryEmail == null)?"<null>":this.primaryEmail));
        sb.append(',');
        sb.append("primaryContact");
        sb.append('=');
        sb.append(((this.primaryContact == null)?"<null>":this.primaryContact));
        sb.append(',');
        sb.append("secondaryEmail");
        sb.append('=');
        sb.append(((this.secondaryEmail == null)?"<null>":this.secondaryEmail));
        sb.append(',');
        sb.append("secondaryContact");
        sb.append('=');
        sb.append(((this.secondaryContact == null)?"<null>":this.secondaryContact));
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
        result = ((result* 31)+((this.secondaryEmail == null)? 0 :this.secondaryEmail.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.primaryEmail == null)? 0 :this.primaryEmail.hashCode()));
        result = ((result* 31)+((this.secondaryContact == null)? 0 :this.secondaryContact.hashCode()));
        result = ((result* 31)+((this.primaryContact == null)? 0 :this.primaryContact.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ContactDetails) == false) {
            return false;
        }
        ContactDetails rhs = ((ContactDetails) other);
        return ((((((this.secondaryEmail == rhs.secondaryEmail)||((this.secondaryEmail!= null)&&this.secondaryEmail.equals(rhs.secondaryEmail)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.primaryEmail == rhs.primaryEmail)||((this.primaryEmail!= null)&&this.primaryEmail.equals(rhs.primaryEmail))))&&((this.secondaryContact == rhs.secondaryContact)||((this.secondaryContact!= null)&&this.secondaryContact.equals(rhs.secondaryContact))))&&((this.primaryContact == rhs.primaryContact)||((this.primaryContact!= null)&&this.primaryContact.equals(rhs.primaryContact))));
    }

}

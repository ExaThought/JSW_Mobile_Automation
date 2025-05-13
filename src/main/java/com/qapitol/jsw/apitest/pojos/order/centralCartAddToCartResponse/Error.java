package com.qapitol.jsw.apitest.pojos.order.centralCartAddToCartResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class Error {

    @JsonProperty("code")
    private int code;

    @JsonProperty("messages")
    private List<String> messages;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
}

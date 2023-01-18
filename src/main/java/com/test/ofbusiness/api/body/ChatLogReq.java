package com.test.ofbusiness.api.body;

public class ChatLogReq {

    public final String message;
    public final Long timestamp;
    public final boolean isSent;

    public ChatLogReq(String message, Long timestamp, boolean isSent) {
        this.message = message;
        this.timestamp = timestamp;
        this.isSent = isSent;
    }
}

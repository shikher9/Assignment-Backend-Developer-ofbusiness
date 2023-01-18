package com.test.ofbusiness.model;

import java.util.Objects;

public class ChatLogEntry {

    private final String msgId;
    private final String userId;
    private final String message;
    private final Long timestamp;

    public ChatLogEntry(String msgId, String userId, String message, Long timestamp) {
        this.msgId = msgId;
        this.userId = userId;
        this.message = message;
        this.timestamp = timestamp;
    }


    public String getMsgId() {
        return msgId;
    }

    public String getUserId() {
        return userId;
    }

    public String getMessage() {
        return message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatLogEntry that = (ChatLogEntry) o;
        return msgId.equals(that.msgId) &&
                userId.equals(that.userId) &&
                message.equals(that.message) &&
                timestamp.equals(that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(msgId, userId, message, timestamp);
    }
}

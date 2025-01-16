package com.example.demo.model;

public class NotificationRequest {
    private String type;
    private String recipient;
    private String message;

    public NotificationRequest() {
    }

    public NotificationRequest(String type, String recipient, String message) {
        this.type = type;
        this.recipient = recipient;
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "NotificationRequest{" +
                "type='" + type + '\'' +
                ", recipient='" + recipient + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
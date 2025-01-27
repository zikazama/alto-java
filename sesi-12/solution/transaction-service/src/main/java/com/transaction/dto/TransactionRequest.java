package com.transaction.dto;

import com.transaction.entity.TransactionType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TransactionRequest {

    @NotBlank(message = "Sender cannot be blank")
    private String sender;

    @NotBlank(message = "Receiver cannot be blank")
    private String receiver;

    @NotNull(message = "Amount cannot be null")
    @Min(value = 1, message = "Amount must be greater than 0")
    private Double amount;

    @NotNull(message = "Transaction type cannot be null")
    private TransactionType type;

    // Default constructor
    public TransactionRequest() {
    }

    // Parameterized constructor
    public TransactionRequest(String sender, String receiver, Double amount, TransactionType type) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        this.type = type;
    }

    // Getters and Setters
    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    // toString method for debugging and logging
    @Override
    public String toString() {
        return "TransactionRequest{" +
                "sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", amount=" + amount +
                ", type=" + type +
                '}';
    }
}

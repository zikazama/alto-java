package com.example.demo.model;

public class Transaction {
    private String id;
    private double amount;
    private String description;

    // Constructor
    public Transaction(String id, double amount, String description) {
        this.id = id;
        this.amount = amount;
        this.description = description;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Transaction{id='" + id + "', amount=" + amount + ", description='" + description + "'}";
    }
}
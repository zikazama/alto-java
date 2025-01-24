package com.financial.dto;

import lombok.Data;

@Data
public class TransactionDTO {
    private String sender;
    private String receiver;
    private Double amount;
    private String type;
}

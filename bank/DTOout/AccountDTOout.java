package com.example.bank.DTOout;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class AccountDTOout {
    private String accountNumber;
    private Double balance;
    private Boolean isActive;
}

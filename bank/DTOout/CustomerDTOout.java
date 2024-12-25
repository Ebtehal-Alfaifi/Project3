package com.example.bank.DTOout;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CustomerDTOout {
    private String username;
    private String name;
    private String email;
    private String phoneNumber;
    private List<AccountDTOout> accounts;
}



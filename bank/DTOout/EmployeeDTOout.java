package com.example.bank.DTOout;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeDTOout {
    private String username;

    private String name;

    private String email;

    private String position;

    private Double salary;
}

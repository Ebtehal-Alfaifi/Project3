package com.example.bank.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "varchar(16) not null unique")
    @NotEmpty(message = "Account Number cannot be empty")
    @Pattern(regexp = "\\b\\d{4}-\\d{4}-\\d{4}-\\d{4}\\b",
            message = "Account Number must follow the format XXXX-XXXX-XXXX-XXXX")
    private String accountNumber;


    @NotNull(message = " Balance cannot be null")
    @DecimalMin(value = "0.0", inclusive = true, message = "Balance must be a non-negative decimal number")
    @Column(columnDefinition = "double not null")
    private Double balance;


    @AssertFalse
    @Column(columnDefinition = "boolean not null")
    private Boolean isActive;

    @ManyToOne
    @JsonIgnore
    private Customer customer;
}

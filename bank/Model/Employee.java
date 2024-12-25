package com.example.bank.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Employee {
    @Id
    private Integer id;

    @Column(columnDefinition = "varchar(20) not null")
    @NotEmpty(message = " Position cannot be null")
    private String position;

    @Column(columnDefinition = "double not null")
    @PositiveOrZero(message = "salary must be a non-negative decimal number")
    private Double salary;

    @OneToOne
    @MapsId
    @JsonIgnore
    private MyUser user;
}

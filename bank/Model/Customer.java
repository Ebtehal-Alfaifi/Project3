package com.example.bank.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer {
    @Id
    private Integer id;

    @Column(columnDefinition = "varchar(10) not null")
    @NotEmpty(message = " Phone Number cannot be null")
    @Pattern(regexp = "^05\\d{8}$",
            message = "Customer Phone Number must start with '05' and has 10 digits long.")
    private String phoneNumber;

    @OneToOne
    @MapsId
    @JsonIgnore
    private MyUser user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private Set<Account> accounts;
}

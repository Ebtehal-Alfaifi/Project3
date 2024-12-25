package com.example.bank.DTOin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class CustomerDTOin {

    private Integer customerId;

    @NotEmpty(message = "Username cannot be null")
    @Size(min = 3, max = 10, message = "Username can not be less than 3 and  more than 10 characters")
    private String username;

    @NotEmpty(message = "Password cannot be null")
    @Size(min = 8, max = 300, message = " Password must be at least 8 characters")
    private String password;

    @NotEmpty(message = "Name cannot be null")
    @Size(min = 2, max = 20, message = "Name must be between 2 and 20 characters")
    private String name;

    @NotEmpty(message = "Customer Email cannot be null")
    @Email(message = "Customer Email must be a valid email format")
    @Size(max = 30,message = "email character can not be more than 30")
    private String email;

    @NotEmpty(message = "Phone Number cannot be null")
    @Pattern(regexp = "^05\\d{8}$",
            message = "Customer Phone Number must start with '05' and be exactly 10 digits long.")
    private String phoneNumber;
}

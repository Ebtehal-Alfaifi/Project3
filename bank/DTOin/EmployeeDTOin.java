package com.example.bank.DTOin;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
public class EmployeeDTOin {
    private Integer employeeId;

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

    @NotEmpty(message = "Employee Position cannot be empty")
    private String position;

    @PositiveOrZero(message = "Salary must be a non-negative decimal number")
    private Double salary;
}

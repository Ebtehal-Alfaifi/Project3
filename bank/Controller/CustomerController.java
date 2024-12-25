package com.example.bank.Controller;
import com.example.bank.Api.ApiResponse;
import com.example.bank.DTOin.CustomerDTOin;
import com.example.bank.Model.MyUser;
import com.example.bank.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/get")
    public ResponseEntity getAllCustomers(@AuthenticationPrincipal MyUser myUser) {
        return ResponseEntity.status(200).body(customerService.getAllCustomer(myUser.getId()));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid CustomerDTOin customerDTO) {
        customerService.registerCustomer(customerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Customer register successful"));
    }

    @PutMapping("/update/{customerId}")
    public ResponseEntity updateCustomer(@AuthenticationPrincipal MyUser myUser,
                                         @PathVariable Integer customerId,
                                         @RequestBody @Valid CustomerDTOin customerDTO) {
        customerService.updateCustomer(myUser.getId(), customerId, customerDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Customer with ID: " + customerId + " has been updated successfully"));
    }

    @DeleteMapping("/delete/{customerId}")
    public ResponseEntity deleteCustomer(@AuthenticationPrincipal MyUser myUser,
                                         @PathVariable Integer customerId) {
        customerService.deleteCustomer(myUser.getId(), customerId);
        return ResponseEntity.status(200).body(new ApiResponse("Customer deleted successful"));
    }

}

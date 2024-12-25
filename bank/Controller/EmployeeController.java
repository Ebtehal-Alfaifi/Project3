package com.example.bank.Controller;
import com.example.bank.Api.ApiResponse;
import com.example.bank.DTOin.EmployeeDTOin;
import com.example.bank.Model.MyUser;
import com.example.bank.Service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/get")
    public ResponseEntity getAllEmployee(@AuthenticationPrincipal MyUser myUser) {
        return ResponseEntity.status(200).body(employeeService.getAllEmployees(myUser.getId()));
    }


    @PostMapping("/register")
    public ResponseEntity registerEmployee(@RequestBody @Valid EmployeeDTOin employeeDTOin){
        employeeService.register(employeeDTOin);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Hello to our web"));

    }

    @PutMapping("/update/{employeeId}")
    public ResponseEntity updateEmployee(@AuthenticationPrincipal MyUser myUser,
                                         @PathVariable Integer employeeId,
                                         @RequestBody @Valid EmployeeDTOin employeeDTOin) {
        employeeService.updateEmployee(myUser.getId(), employeeId, employeeDTOin);
        return ResponseEntity.status(200).body(new ApiResponse("Employee  has been updated successful"));
    }


    @DeleteMapping("/delete/{employeeId}")
    public ResponseEntity deleteEmployee(@AuthenticationPrincipal MyUser myUser, @PathVariable Integer employeeId) {
        employeeService.deleteEmployee(myUser.getId(), employeeId);
        return ResponseEntity.status(200).body(new ApiResponse("Employee  has been deleted successful"));
    }




}

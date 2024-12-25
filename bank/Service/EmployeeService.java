package com.example.bank.Service;
import com.example.bank.Api.ApiException;
import com.example.bank.DTOin.EmployeeDTOin;
import com.example.bank.DTOout.EmployeeDTOout;
import com.example.bank.Model.Employee;
import com.example.bank.Model.MyUser;
import com.example.bank.Repository.AuthRepository;
import com.example.bank.Repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final AuthRepository authRepository;

    public List<EmployeeDTOout> getAllEmployees(Integer adminId) {
        MyUser user = authRepository.findMyUserById(adminId);
        if (user == null)
            throw new ApiException("Admin not found");

        if (user.getRole().equals("ADMIN")) {
            List<Employee> employees = employeeRepository.findAll();
            List<EmployeeDTOout> employeeDTOS = new ArrayList<>();

            for (Employee e : employees) {
                employeeDTOS.add(new EmployeeDTOout(e.getUser().getUsername(), e.getUser().getName(),
                        e.getUser().getEmail(),
                        e.getPosition(),
                        e.getSalary()));
            }
            return employeeDTOS;
        }
        else throw new ApiException("You do not have the Authorization to access");
    }

    public void register(EmployeeDTOin employeeDTO) {
        MyUser myUser = new MyUser();
        myUser.setUsername(employeeDTO.getUsername());
        myUser.setPassword(new BCryptPasswordEncoder().encode(employeeDTO.getPassword()));
        myUser.setName(employeeDTO.getName());
        myUser.setEmail(employeeDTO.getEmail());
        myUser.setRole("EMPLOYEE");
        authRepository.save(myUser);

        Employee employee = new Employee();
        employee.setPosition(employeeDTO.getPosition());
        employee.setSalary(employeeDTO.getSalary());
        employee.setUser(myUser);
        employeeRepository.save(employee);
    }


    public void updateEmployee(Integer authId, Integer employeeId,EmployeeDTOin employeeDTOin) {
        MyUser auth = authRepository.findMyUserById(authId);
        if (auth == null)
            throw new ApiException("Admin was not found");

        MyUser oldEmployee = authRepository.findMyUserById(employeeId);
        if (oldEmployee == null){
            throw new ApiException("Employee was not found");
        }

        if (!authId.equals(employeeId) || !auth.getRole().equals("ADMIN")) {
            throw new ApiException("You don't have  Authorization to update this employee");
        }
            oldEmployee.setUsername(employeeDTOin.getUsername());
            oldEmployee.setPassword(new BCryptPasswordEncoder().encode(employeeDTOin.getPassword()));
            oldEmployee.setName(employeeDTOin.getName());
            oldEmployee.setEmail(employeeDTOin.getEmail());
            oldEmployee.getEmployee().setPosition(employeeDTOin.getPosition());
            oldEmployee.getEmployee().setSalary(employeeDTOin.getSalary());
            authRepository.save(oldEmployee);
    }

    public void deleteEmployee(Integer authId, Integer employeeId) {
        MyUser auth = authRepository.findMyUserById(authId);
        if (auth == null){
            throw new ApiException("Admin was not found");}

        MyUser oldEmployee = authRepository.findMyUserById(employeeId);
        if (oldEmployee == null){
            throw new ApiException("Employee  was not found");
        }

        if (!authId.equals(employeeId) || !auth.getRole().equals("ADMIN")){
        throw new ApiException("You don't have the Authorization to delete  that  employee");
        }
        authRepository.delete(oldEmployee);

    }
}

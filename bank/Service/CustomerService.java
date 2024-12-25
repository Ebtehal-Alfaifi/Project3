package com.example.bank.Service;


import com.example.bank.Api.ApiException;
import com.example.bank.DTOin.CustomerDTOin;
import com.example.bank.DTOout.AccountDTOout;
import com.example.bank.DTOout.CustomerDTOout;
import com.example.bank.Model.Account;
import com.example.bank.Model.Customer;
import com.example.bank.Model.MyUser;
import com.example.bank.Repository.AuthRepository;
import com.example.bank.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final AuthRepository authRepsitory;
    private final CustomerRepository customerRepostiory;


    public List<CustomerDTOout>getAllCustomer(Integer auth){
        MyUser myUser=authRepsitory.findMyUserById(auth);
        if(myUser==null){
            throw new ApiException("Cannot found Account");
        }
        if(!myUser.getRole().equals("EMPLOYEE")|| !myUser.getRole().equals("ADMIN")){
            throw new ApiException("Sorry , You do not have the authority to see this Details ");
        }
            List<Customer>customerList=customerRepostiory.findAll();
            if(customerList.isEmpty()){
                throw new ApiException("There no Customer yet");
            }
            List<CustomerDTOout>customerDTOS=new ArrayList<>();
            for(Customer customer:customerList){
                List<AccountDTOout>accountDTOS=new ArrayList<>();
                for(Account aa:customer.getAccounts())
                    accountDTOS.add(new AccountDTOout(aa.getAccountNumber(), aa.getBalance(), aa.getIsActive()));

                customerDTOS.add(new CustomerDTOout(customer.getUser().getUsername(),customer.getUser().getEmail()
                        ,customer.getUser().getName(),customer.getPhoneNumber(),accountDTOS));
            }
            return customerDTOS;

    }



    public  void registerCustomer(CustomerDTOin customerDTOin){
        MyUser myUser=authRepsitory.findMyUserByUsername(customerDTOin.getUsername());
        if (myUser != null) {
            throw new ApiException("User already exists");
        }

        MyUser myUser1=new MyUser();

        myUser1.setUsername(customerDTOin.getUsername());
        myUser1.setPassword(new BCryptPasswordEncoder().encode(customerDTOin.getPassword()));
        myUser1.setName(customerDTOin.getName());
        myUser1.setEmail(customerDTOin.getEmail());
        myUser1.setRole("CUSTOMER");
        authRepsitory.save(myUser1);

        Customer customer=new Customer();
        customer.setPhoneNumber(customerDTOin.getPhoneNumber());
        customer.setUser(myUser1);


        customerRepostiory.save(customer);
    }

    public void updateCustomer(Integer authId, Integer customerId,CustomerDTOin customerDTO) {
        MyUser auth = authRepsitory.findMyUserById(authId);
        if (auth == null){
            throw new ApiException("Admin was not found");
        }

        MyUser oldCustomer = authRepsitory.findMyUserById(customerId);
        if (oldCustomer == null)
            throw new ApiException("Customer not found");

        if (!authId.equals(customerId)||!auth.getRole().equals("ADMIN") || !auth.getRole().equals("EMPLOYEE")) {
            throw new ApiException("You don't have access to update this customer");
        }
            oldCustomer.setUsername(customerDTO.getUsername());
            oldCustomer.setPassword(new BCryptPasswordEncoder().encode(customerDTO.getPassword()));
            oldCustomer.setName(customerDTO.getName());
            oldCustomer.setEmail(customerDTO.getEmail());
            oldCustomer.getCustomer().setPhoneNumber(customerDTO.getPhoneNumber());
            authRepsitory.save(oldCustomer);
    }


    public void deleteCustomer(Integer authId, Integer customerId) {
        MyUser user = authRepsitory.findMyUserById(authId);
        if (user == null){
            throw new ApiException("Account Cannot found ");
        }

        MyUser customer = authRepsitory.findMyUserById(customerId);
        if (customer == null){
            throw new ApiException("Cannot found customer");
        }

        if (authId.equals(customerId) || user.getRole().equals("ADMIN"))
            authRepsitory.delete(customer);
        else throw new ApiException("You Cannot delete this customer");
    }


}
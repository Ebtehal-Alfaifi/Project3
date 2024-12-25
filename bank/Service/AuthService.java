package com.example.bank.Service;


import com.example.bank.Api.ApiException;
import com.example.bank.Model.MyUser;
import com.example.bank.Repository.AccountRepository;
import com.example.bank.Repository.AuthRepository;
import com.example.bank.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepsitory;

    public List<MyUser> getAllUsers(Integer adminId) {
        MyUser admin = authRepsitory.findMyUserById(adminId);
        if (admin == null){
            throw new ApiException("Admin Cannot found Admin ");
        }

        if (admin.getRole().equalsIgnoreCase("ADMIN"))
            return authRepsitory.findAll();

        else throw new ApiException("You don't have the permission to access this endpoint");
    }

}

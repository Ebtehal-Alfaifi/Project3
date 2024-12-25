package com.example.bank.Repository;

import com.example.bank.Model.Account;
import com.example.bank.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {
    Account findAccountById(Integer id);
    Account findAccountsByIdAndCustomer(Integer id, Customer customer);
    List<Account> findAccountsByCustomer(Customer customer);

}

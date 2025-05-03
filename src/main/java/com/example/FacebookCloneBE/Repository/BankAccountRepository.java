package com.example.FacebookCloneBE.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.FacebookCloneBE.Model.Bank;
import com.example.FacebookCloneBE.Model.BankAccount;
import com.example.FacebookCloneBE.Model.User;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    List<BankAccount> findByBankID(Bank bank);

    List<BankAccount> findByUserID(User user);
}

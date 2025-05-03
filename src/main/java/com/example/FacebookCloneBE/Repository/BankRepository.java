package com.example.FacebookCloneBE.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.FacebookCloneBE.Model.Bank;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {

}

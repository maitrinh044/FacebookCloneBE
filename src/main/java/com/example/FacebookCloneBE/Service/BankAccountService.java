package com.example.FacebookCloneBE.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.FacebookCloneBE.DTO.BankAccountDTO.BankAccountDTO;
import com.example.FacebookCloneBE.Model.BankAccount;

@Service
public interface BankAccountService {
    public List<BankAccountDTO> getByBank(Long bankId);

    public Optional<BankAccountDTO> getById(Long id);

    public List<BankAccountDTO> getAllBankAccount();

    public List<BankAccountDTO> getByUser(Long userId);

    public Optional<BankAccountDTO> controlActiveStatus(Long id);

    public Optional<BankAccountDTO> addBankAccount(BankAccountDTO bankAccountDTO);

    public Optional<BankAccountDTO> updateBankAccount(BankAccountDTO bankAccountDTO);
}

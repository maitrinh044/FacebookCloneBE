package com.example.FacebookCloneBE.Mapper;

import com.example.FacebookCloneBE.DTO.BankAccountDTO.BankAccountDTO;
import com.example.FacebookCloneBE.Model.BankAccount;

public class BankAccountMapper {
    public static BankAccount toEntityPOST(BankAccountDTO bankAccountDTO) {
        BankAccount bankAccount = new BankAccount();
        // bankAccount.setId(bankAccountDTO.getId());
        bankAccount.setBankAccountNumber(bankAccountDTO.getBankAccountNumber());
        bankAccount.setBankID(bankAccountDTO.getBankID());
        bankAccount.setUserID(bankAccountDTO.getUserID());
        bankAccount.setActiveStatus(bankAccountDTO.getActiveStatus());
        return bankAccount;
    }

    public static BankAccount toEntityPUT(BankAccountDTO bankAccountDTO) {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setId(bankAccountDTO.getId());
        bankAccount.setBankAccountNumber(bankAccountDTO.getBankAccountNumber());
        bankAccount.setBankID(bankAccountDTO.getBankID());
        bankAccount.setUserID(bankAccountDTO.getUserID());
        bankAccount.setActiveStatus(bankAccountDTO.getActiveStatus());
        return bankAccount;
    }

    public static BankAccountDTO toDTO(BankAccount bankAccount) {
        BankAccountDTO bankAccountDTO = new BankAccountDTO();
        bankAccountDTO.setId(bankAccount.getId());
        bankAccountDTO.setBankID(bankAccount.getBankID());
        bankAccountDTO.setUserID(bankAccount.getUserID());
        bankAccountDTO.setBankAccountNumber(bankAccount.getBankAccountNumber());
        bankAccountDTO.setActiveStatus(bankAccount.getActiveStatus());
        return bankAccountDTO;
    }
}

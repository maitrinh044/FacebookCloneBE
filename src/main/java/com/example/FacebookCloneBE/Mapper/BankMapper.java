package com.example.FacebookCloneBE.Mapper;

import com.example.FacebookCloneBE.DTO.BankDTO.BankDTO;
import com.example.FacebookCloneBE.Model.Bank;

public class BankMapper {
    public static Bank toEntityPOST(BankDTO bankDTO) {
        Bank bank = new Bank();
        // bank.setId(bankDTO.getId());
        bank.setNameBank(bankDTO.getNameBank());
        bank.setActiveStatus(bankDTO.getActiveStatus());
        return bank;
    }

    public static Bank toEntityPUT(BankDTO bankDTO) {
        Bank bank = new Bank();
        bank.setId(bankDTO.getId());
        bank.setNameBank(bankDTO.getNameBank());
        bank.setActiveStatus(bankDTO.getActiveStatus());
        return bank;
    }

    public static BankDTO toDTO(Bank bank) {
        BankDTO bankDTO = new BankDTO();
        bankDTO.setId(bank.getId());
        bankDTO.setNameBank(bank.getNameBank());
        bankDTO.setActiveStatus(bank.getActiveStatus());
        return bankDTO;
    }
}

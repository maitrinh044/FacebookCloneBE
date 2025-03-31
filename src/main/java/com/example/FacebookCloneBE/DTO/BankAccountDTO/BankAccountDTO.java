package com.example.FacebookCloneBE.DTO.BankAccountDTO;

import com.example.FacebookCloneBE.Enum.ActiveEnum;
import com.example.FacebookCloneBE.Model.Bank;
import com.example.FacebookCloneBE.Model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankAccountDTO {
    private Long id;
    private String bankAccountNumber;
    private User userID;
    private Bank bankID;
    private ActiveEnum activeStatus;
}

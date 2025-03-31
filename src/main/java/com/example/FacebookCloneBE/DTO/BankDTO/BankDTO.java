package com.example.FacebookCloneBE.DTO.BankDTO;

import com.example.FacebookCloneBE.Enum.ActiveEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankDTO {
    private Long id;
    private String nameBank;
    private ActiveEnum activeStatus;
}

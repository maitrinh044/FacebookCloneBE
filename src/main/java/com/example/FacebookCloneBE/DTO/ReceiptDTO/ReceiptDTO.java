package com.example.FacebookCloneBE.DTO.ReceiptDTO;

import java.time.LocalDateTime;

import com.example.FacebookCloneBE.Enum.ActiveEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiptDTO {
    private Long id;
    private Double total;
    private Long bankAccountID;
    private LocalDateTime createdAt;
    private ActiveEnum activeStatus;
}

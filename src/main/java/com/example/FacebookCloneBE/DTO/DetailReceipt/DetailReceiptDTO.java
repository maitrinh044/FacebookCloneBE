package com.example.FacebookCloneBE.DTO.DetailReceipt;

import com.example.FacebookCloneBE.Enum.ActiveEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailReceiptDTO {
    private Long id;
    private Long receiptID;
    private Long detailGameID;
    private ActiveEnum activeStatus;
}

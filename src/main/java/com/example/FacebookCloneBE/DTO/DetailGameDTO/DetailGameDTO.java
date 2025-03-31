package com.example.FacebookCloneBE.DTO.DetailGameDTO;

import java.time.LocalDateTime;

import com.example.FacebookCloneBE.Enum.ActiveEnum;
import com.example.FacebookCloneBE.Model.Game;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailGameDTO {
    private Long id;
    private Game gameID; // Chỉ lấy ID thay vì toàn bộ entity Game
    private LocalDateTime planDuration;
    private Double price;
    private ActiveEnum activeStatus;
}

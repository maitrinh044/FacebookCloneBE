package com.example.FacebookCloneBE.DTO.GameDTO;

import java.time.LocalDateTime;

import com.example.FacebookCloneBE.Enum.ActiveEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameDTO {
    private Long id;
    private String nameGame;
    private LocalDateTime createdAt;
    private String description;
    private ActiveEnum activeStatus;
}

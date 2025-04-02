package com.example.FacebookCloneBE.DTO.ReviewGameDTO;

import com.example.FacebookCloneBE.Enum.ActiveEnum;
import com.example.FacebookCloneBE.Enum.RatingEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewGameDTO {
    private Long id;
    private Long userID;
    private Long gameID;
    private RatingEnum rating;
    private LocalDateTime createdAt;
    private ActiveEnum activeStatus;
}

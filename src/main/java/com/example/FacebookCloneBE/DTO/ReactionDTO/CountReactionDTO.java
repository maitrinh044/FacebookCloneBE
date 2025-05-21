package com.example.FacebookCloneBE.DTO.ReactionDTO;

import com.example.FacebookCloneBE.Enum.ReactionType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountReactionDTO {

    private ReactionType reactionType;
    private int count;
}

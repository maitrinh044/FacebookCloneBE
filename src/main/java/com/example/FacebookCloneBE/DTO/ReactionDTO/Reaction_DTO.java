package com.example.FacebookCloneBE.DTO.ReactionDTO;

import java.sql.Timestamp;

import com.example.FacebookCloneBE.Enum.ActiveEnum;
import com.example.FacebookCloneBE.Enum.ReactionType;
import com.example.FacebookCloneBE.Enum.TargetType;
import com.example.FacebookCloneBE.Model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reaction_DTO {
    private long id;
    private ReactionType type;
    private User user;
    private TargetType targetType;
    private long targetId;
    private Timestamp createdAt;
    private ActiveEnum activeStatus;
}

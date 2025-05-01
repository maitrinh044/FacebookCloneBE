package com.example.FacebookCloneBE.Mapper;

import com.example.FacebookCloneBE.DTO.ReactionDTO.Reaction_DTO;
import com.example.FacebookCloneBE.Model.Reaction;

public class ReactionMapperData {
    public static Reaction toEntityPOST(Reaction_DTO reaction_DTO) {
        Reaction rc = new Reaction();
        // rc.setId(reaction_DTO.getId());
        rc.setActiveStatus(reaction_DTO.getActiveStatus());
        rc.setCreatedAt(reaction_DTO.getCreatedAt());
        rc.setTargetId(reaction_DTO.getTargetId());
        rc.setTargetType(reaction_DTO.getTargetType());
        rc.setType(reaction_DTO.getType());
        rc.setUser(reaction_DTO.getUser());
        return rc;
    }

    public static Reaction toEntityPUT(Reaction_DTO reaction_DTO) {
        Reaction rc = new Reaction();
        rc.setId(reaction_DTO.getId());
        rc.setActiveStatus(reaction_DTO.getActiveStatus());
        rc.setCreatedAt(reaction_DTO.getCreatedAt());
        rc.setTargetId(reaction_DTO.getTargetId());
        rc.setTargetType(reaction_DTO.getTargetType());
        rc.setType(reaction_DTO.getType());
        rc.setUser(reaction_DTO.getUser());
        return rc;
    }

    public static Reaction_DTO toDTO(Reaction reaction) {
        Reaction_DTO rcDto = new Reaction_DTO();
        rcDto.setId(reaction.getId());
        rcDto.setActiveStatus(reaction.getActiveStatus());
        rcDto.setCreatedAt(reaction.getCreatedAt());
        rcDto.setTargetId(reaction.getTargetId());
        rcDto.setTargetType(reaction.getTargetType());
        rcDto.setType(reaction.getType());
        rcDto.setUser(reaction.getUser());
        return rcDto;
    }
}

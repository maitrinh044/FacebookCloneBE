package com.example.FacebookCloneBE.Mapper;

import com.example.FacebookCloneBE.DTO.ReactionDTO.ReactionDTO;
import com.example.FacebookCloneBE.Model.Reaction;
import com.example.FacebookCloneBE.Model.User;
import com.example.FacebookCloneBE.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReactionMapper {
    @Autowired private UserRepository userRepository;

    public ReactionDTO toDTO(Reaction reaction) {
        if (reaction == null) return null;
        
        return new ReactionDTO(
            reaction.getId(),
            reaction.getType(),
            reaction.getUser().getId(),
            reaction.getTargetType(),
            reaction.getTargetId(),
            reaction.getCreatedAt(),
            reaction.getActiveStatus()
        );
    }

    public Reaction toEntity(ReactionDTO reactionDTO) {
        if (reactionDTO == null) return null;
        
        Reaction reaction = new Reaction();
        reaction.setId(reactionDTO.getId());
        reaction.setType(reactionDTO.getType()); // Sử dụng enum ReactionType
        reaction.setUser(userRepository.findById(reactionDTO.getUserId()).orElse(null));
        reaction.setTargetType(reactionDTO.getTargetType());
        reaction.setTargetId(reactionDTO.getTargetId());
        reaction.setActiveStatus(reactionDTO.getActiveStatus());
        
        return reaction;
    }
}
package com.example.FacebookCloneBE.Service;

import com.example.FacebookCloneBE.DTO.ReactionDTO.ReactionDTO;
import com.example.FacebookCloneBE.Model.Reaction;
import com.example.FacebookCloneBE.Repository.ReactionRepository;
import com.example.FacebookCloneBE.Enum.ActiveEnum;
import com.example.FacebookCloneBE.Enum.ReactionType;
import com.example.FacebookCloneBE.Enum.TargetType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReactionService {
    @Autowired private ReactionRepository reactionRepository;
    @Autowired private ReactionMapper reactionMapper;

    public List<ReactionDTO> getReactions(long targetId, TargetType targetType) {
        return reactionRepository.findByTargetTypeAndTargetId(targetType, targetId).stream()
                .map(reactionMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ReactionDTO toggleReaction(ReactionDTO reactionDTO) {
        Optional<Reaction> existing = reactionRepository.findByUserIdAndTargetTypeAndTargetId(
            reactionDTO.getUserId(), 
            reactionDTO.getTargetType(), 
            reactionDTO.getTargetId()
        );

        if (existing.isPresent()) {
            // Nếu đã tồn tại reaction cùng loại -> xóa
            // Nếu khác loại -> cập nhật loại mới
            if (existing.get().getType() == reactionDTO.getType()) {
                reactionRepository.delete(existing.get());
                return null;
            } else {
                existing.get().setType(reactionDTO.getType());
                return reactionMapper.toDTO(reactionRepository.save(existing.get()));
            }
        } else {
            // Tạo mới reaction
            Reaction reaction = reactionMapper.toEntity(reactionDTO);
            reaction.setActiveStatus(ActiveEnum.ACTIVE);
            return reactionMapper.toDTO(reactionRepository.save(reaction));
        }
    }

    public long countReactions(long targetId, TargetType targetType) {
        return reactionRepository.countByTargetTypeAndTargetId(targetType, targetId);
    }

    public long countReactionsByType(long targetId, TargetType targetType, ReactionType reactionType) {
        return reactionRepository.countByTargetTypeAndTargetIdAndType(targetType, targetId, reactionType);
    }
}
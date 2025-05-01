package com.example.FacebookCloneBE.Service;

import com.example.FacebookCloneBE.DTO.PostDTO.PostDTO;
import com.example.FacebookCloneBE.DTO.ReactionDTO.ReactionDTO;
import com.example.FacebookCloneBE.DTO.ReactionDTO.Reaction_DTO;
import com.example.FacebookCloneBE.DTO.UserDTO.UserDTO;
import com.example.FacebookCloneBE.Mapper.PageMapper;
import com.example.FacebookCloneBE.Mapper.ReactionMapper;
import com.example.FacebookCloneBE.Mapper.ReactionMapperData;
import com.example.FacebookCloneBE.Mapper.UserMapper;
import com.example.FacebookCloneBE.Model.Page;
import com.example.FacebookCloneBE.Model.Reaction;
import com.example.FacebookCloneBE.Model.User;
import com.example.FacebookCloneBE.Repository.ReactionRepository;

import io.jsonwebtoken.security.Jwks.OP;

import com.example.FacebookCloneBE.Enum.ActiveEnum;
import com.example.FacebookCloneBE.Enum.ReactionType;
import com.example.FacebookCloneBE.Enum.TargetType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ReactionService {
    @Autowired
    private ReactionRepository reactionRepository;
    @Autowired
    private ReactionMapper reactionMapper;
    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;

    public List<ReactionDTO> getReactions(long targetId, TargetType targetType) {
        return reactionRepository.findByTargetTypeAndTargetId(targetType, targetId).stream()
                .map(reactionMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ReactionDTO toggleReaction(ReactionDTO reactionDTO) {
        Optional<Reaction> existing = reactionRepository.findByUserIdAndTargetTypeAndTargetId(
                reactionDTO.getUserId(),
                reactionDTO.getTargetType(),
                reactionDTO.getTargetId());

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

    public Optional<Reaction_DTO> getByUserAndTarget(Long userId, TargetType targetType, Long targetId) {
        return Optional.of(ReactionMapperData
                .toDTO(reactionRepository.findByUserIdAndTargetTypeAndTargetId(userId, targetType, targetId).get()));
    }

    public boolean checkExistingReaction(Long userId, TargetType targetType, Long targetId) {
        if (reactionRepository.findByUserIdAndTargetTypeAndTargetId(userId, targetType, targetId).get() != null) {
            return true;
        }
        return false;
    }

    public List<Reaction_DTO> getByUser(Long userId) {
        try {
            Iterable<Reaction> list = reactionRepository.findByUserId(userId);
            return StreamSupport.stream(list.spliterator(), false).map(ReactionMapperData::toDTO).toList();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public Optional<Reaction_DTO> controlReactionByTargetAndUser(Long userId, TargetType targetType, Long targetId,
            ReactionType reactionType) {
        try {
            Optional<Reaction> existingRC = reactionRepository
                    .findByUser_IdAndTargetTypeAndTargetId(userId, targetType, targetId);
            if (existingRC.isPresent()) {
                if (existingRC.get().getType().equals(reactionType)) {
                    reactionRepository.delete(existingRC.get());
                    return Optional.empty();
                } else {
                    existingRC.get().setType(reactionType);
                    Reaction rc = reactionRepository.save(existingRC.get());
                    return Optional.of(ReactionMapperData.toDTO(rc));
                }
            } else {
                Reaction rc = new Reaction();
                rc.setActiveStatus(ActiveEnum.ACTIVE);
                LocalDateTime now = LocalDateTime.now();
                java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf(now);
                rc.setCreatedAt(timestamp);
                rc.setTargetId(targetId);
                rc.setTargetType(targetType);
                rc.setType(reactionType);
                rc.setUser(UserMapper.toEntity(userService.getUserById(userId).get()));
                Reaction tmp = reactionRepository.save(rc);
                return Optional.of(ReactionMapperData.toDTO(tmp));
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            System.err.println("Error while control reaction: " + e.getMessage());
            return Optional.empty();
        }
    }
}
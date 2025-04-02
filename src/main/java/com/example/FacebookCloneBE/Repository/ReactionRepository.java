package com.example.FacebookCloneBE.Repository;

import com.example.FacebookCloneBE.Model.Reaction;
import com.example.FacebookCloneBE.Enum.ReactionType;
import com.example.FacebookCloneBE.Enum.TargetType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction, Long> {
    List<Reaction> findByTargetTypeAndTargetId(TargetType targetType, long targetId);
    Optional<Reaction> findByUserIdAndTargetTypeAndTargetId(long userId, TargetType targetType, long targetId);
    long countByTargetTypeAndTargetId(TargetType targetType, long targetId);
    long countByTargetTypeAndTargetIdAndType(TargetType targetType, long targetId, ReactionType type);
}
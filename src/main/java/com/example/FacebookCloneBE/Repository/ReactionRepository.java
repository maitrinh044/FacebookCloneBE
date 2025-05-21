package com.example.FacebookCloneBE.Repository;

import com.example.FacebookCloneBE.Model.Group;
import com.example.FacebookCloneBE.Model.Reaction;
import com.example.FacebookCloneBE.Enum.ReactionType;
import com.example.FacebookCloneBE.Enum.TargetType;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction, Long> {
    List<Reaction> findByTargetTypeAndTargetId(TargetType targetType, long targetId);

    Optional<Reaction> findByUser_IdAndTargetTypeAndTargetId(Long userId, TargetType targetType, Long targetId);

    Iterable<Reaction> findByUserId(Long userId);

    Optional<Reaction> findByUserIdAndTargetTypeAndTargetId(long userId, TargetType targetType, long targetId);

    long countByTargetTypeAndTargetId(TargetType targetType, long targetId);

    long countByTargetTypeAndTargetIdAndType(TargetType targetType, long targetId, ReactionType type);

    // @Query("SELECT r.type, COUNT(*) AS count \r\n" + //
    // "FROM reactions r \r\n" + //
    // "WHERE r.target_type = :targetType AND r.target_id = :targetId \r\n" + //
    // "GROUP BY r.type \r\n" + //
    // "ORDER BY count DESC \r\n" + //
    // "LIMIT 3; -- Giới hạn kết quả về 3 dòng")
    // List<Reaction> findTop3ReactionsByPostId(@Param("targetType") TargetType
    // targetType,
    // @Param("targetId") Long targetId);
    @Query("SELECT r.type, COUNT(r) AS count FROM Reaction r WHERE r.targetType = :targetType AND r.targetId = :targetId GROUP BY r.type ORDER BY count DESC")
    List<Object[]> findTop3ReactionsByPostId(@Param("targetType") TargetType targetType,
            @Param("targetId") Long targetId,
            org.springframework.data.domain.Pageable pageable);
}
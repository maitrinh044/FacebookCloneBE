package com.example.FacebookCloneBE.Repository;

import com.example.FacebookCloneBE.Enum.ActiveEnum;
import com.example.FacebookCloneBE.Enum.RoleEnum;
import com.example.FacebookCloneBE.Model.Group;
import com.example.FacebookCloneBE.Model.GroupMemberShip;
import com.example.FacebookCloneBE.Model.User;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupMemberShipRepository extends JpaRepository<GroupMemberShip, Long> {
        List<GroupMemberShip> findByGroupID(Group groupID);

        List<GroupMemberShip> findByUserID(User userID);

        List<GroupMemberShip> findByGroupID(Group group, Sort sort);

        List<GroupMemberShip> findByUserID(User user, Sort sort);

        @Modifying
        @Transactional
        @Query("UPDATE GroupMemberShip g SET g.activeStatus = :status WHERE g.groupID.id = :groupId AND g.userID.id = :userId")
        void updateActiveStatus(@Param("groupId") Long groupId, @Param("userId") Long userId,
                        @Param("status") ActiveEnum status);

        @Modifying
        @Transactional
        @Query("UPDATE GroupMemberShip g SET g.role = :role WHERE g.groupID.id = :groupId AND g.userID.id = :userId")
        void updateMemberRole(@Param("groupId") Long groupId, @Param("userId") Long userId,
                        @Param("role") RoleEnum role);

        @Query("SELECT g FROM GroupMemberShip g WHERE g.groupID.id = :groupId AND g.userID.id = :userId")
        Optional<GroupMemberShip> findByGroupIDAndUserID(@Param("groupId") Long groupId,
                        @Param("userId") Long userId);

}

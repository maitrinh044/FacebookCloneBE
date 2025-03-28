package com.example.FacebookCloneBE.Repository;

import com.example.FacebookCloneBE.Enum.ActiveEnum;
import com.example.FacebookCloneBE.Model.Group;
import com.example.FacebookCloneBE.Model.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    @Modifying
    @Query("UPDATE Group g SET g.activeStatus = :status WHERE g.groupID = :groupId")
    void deleteGroup(@Param("groupId") Long groupId, @Param("status") ActiveEnum status);

    List<Group> findGroupsByCreateBy(User adminId);

    @Query("SELECT g FROM Group g WHERE LOWER(g.groupName) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Group> findByKeyword(@Param("keyword") String keyword);
}
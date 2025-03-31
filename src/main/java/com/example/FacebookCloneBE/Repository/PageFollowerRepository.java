package com.example.FacebookCloneBE.Repository;

import com.example.FacebookCloneBE.Enum.ActiveEnum;
import com.example.FacebookCloneBE.Model.GroupMemberShip;
import com.example.FacebookCloneBE.Model.Page;
import com.example.FacebookCloneBE.Model.PageFollower;
import com.example.FacebookCloneBE.Model.User;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PageFollowerRepository extends JpaRepository<PageFollower, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE PageFollower p SET p.activeStatus = :status WHERE p.pageID.id = :pageId AND p.userID.id = :userId")
    void updateActiveStatus(@Param("pageId") Long pageID, @Param("userId") Long userID,
            @Param("status") ActiveEnum status);

    @Query("SELECT p FROM PageFollower p WHERE p.pageID.id = :pageId AND p.userID.id = :userId")
    Optional<PageFollower> findByPageIDAndUserID(@Param("pageId") Long pageId,
            @Param("userId") Long userId);

    List<PageFollower> findByPageID(Page pageID);
}

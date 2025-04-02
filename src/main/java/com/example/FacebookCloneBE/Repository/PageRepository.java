package com.example.FacebookCloneBE.Repository;

import com.example.FacebookCloneBE.Enum.ActiveEnum;
import com.example.FacebookCloneBE.Model.Page;

import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PageRepository extends JpaRepository<Page, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE Page p SET p.activeStatus = :status WHERE p.pageID = :pageID")
    void updateActiveStatus(@Param("pageID") Long pageID,
            @Param("status") ActiveEnum status);

    @Query("SELECT p FROM Page p WHERE LOWER(p.pageName) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Page> findByKeyword(String keyword);
}

package com.example.FacebookCloneBE.Repository;

import com.example.FacebookCloneBE.Model.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {
    @Query("SELECT m FROM Media m WHERE m.user.id = :id")
    List<Media> findByUserId(Long id);

}

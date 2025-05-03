package com.example.FacebookCloneBE.Repository;

import com.example.FacebookCloneBE.Model.Game;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    @Query("SELECT g FROM Game g WHERE g.nameGame LIKE CONCAT('%', :keyword, '%') OR g.description LIKE CONCAT('%', :keyword, '%')")
    public List<Game> findByKeyword(String keyword);

}

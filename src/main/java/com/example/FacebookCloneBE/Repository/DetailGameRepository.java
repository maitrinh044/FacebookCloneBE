package com.example.FacebookCloneBE.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.FacebookCloneBE.Enum.ActiveEnum;
import com.example.FacebookCloneBE.Model.DetailGame;
import com.example.FacebookCloneBE.Model.Game;

import jakarta.transaction.Transactional;

@Repository
public interface DetailGameRepository extends JpaRepository<DetailGame, Long> {
    List<DetailGame> findByGameID(Game game);

    @Transactional
    @Query("UPDATE DetailGame SET activeStatus = :activeStatus WHERE gameID = :gameID")
    List<DetailGame> controlActiveStatusByGameID(@Param("gameID") Long gameID,
            @Param("activeStatus") ActiveEnum activeEnum);
}

package com.example.FacebookCloneBE.Repository;

import com.example.FacebookCloneBE.Model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

}

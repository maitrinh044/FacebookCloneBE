package com.example.FacebookCloneBE.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.FacebookCloneBE.DTO.GameDTO.GameDTO;

@Service
public interface GameService {
    List<GameDTO> getAll();

    Optional<GameDTO> getById(Long id);

    Optional<GameDTO> addGame(GameDTO game);

    Optional<GameDTO> updateGame(GameDTO game);

    boolean checkExistingGame(Long id);

    // Optional<GameDTO> controlDelete(Long id);

    // List<GameDTO> getByKeyword(String keyword);

    // List<GameDTO> getAllGameSorted(String column, String order);
}

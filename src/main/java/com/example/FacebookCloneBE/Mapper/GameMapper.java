package com.example.FacebookCloneBE.Mapper;

import com.example.FacebookCloneBE.DTO.GameDTO.GameDTO;
import com.example.FacebookCloneBE.Model.Game;

public class GameMapper {
    public static Game toEntityPOST(GameDTO gameDTO) {
        Game game = new Game();
        // game.setId(gameDTO.getId());
        game.setNameGame(gameDTO.getNameGame());
        game.setDescription(gameDTO.getDescription());
        game.setCreatedAt(gameDTO.getCreatedAt());
        game.setActiveStatus(gameDTO.getActiveStatus());
        return game;
    }

    public static Game toEntityPUT(GameDTO gameDTO) {
        Game game = new Game();
        game.setId(gameDTO.getId());
        game.setNameGame(gameDTO.getNameGame());
        game.setDescription(gameDTO.getDescription());
        game.setCreatedAt(gameDTO.getCreatedAt());
        game.setActiveStatus(gameDTO.getActiveStatus());
        return game;
    }

    public static GameDTO toDTO(Game game) {
        GameDTO gameDTO = new GameDTO();
        gameDTO.setId(game.getId());
        gameDTO.setNameGame(game.getNameGame());
        gameDTO.setCreatedAt(game.getCreatedAt());
        gameDTO.setActiveStatus(game.getActiveStatus());
        gameDTO.setDescription(game.getDescription());
        return gameDTO;
    }
}

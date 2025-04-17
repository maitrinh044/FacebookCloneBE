package com.example.FacebookCloneBE.Service.Impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FacebookCloneBE.DTO.GameDTO.GameDTO;
import com.example.FacebookCloneBE.DTO.GroupDTO.GroupDTO;
import com.example.FacebookCloneBE.Mapper.GameMapper;
import com.example.FacebookCloneBE.Mapper.GroupMapper;
import com.example.FacebookCloneBE.Model.Game;
import com.example.FacebookCloneBE.Model.Group;
import com.example.FacebookCloneBE.Repository.GameRepository;
import com.example.FacebookCloneBE.Service.GameService;

@Service
public class GameServiceImpl implements GameService {
    @Autowired
    private GameRepository gameRepository;

    public List<GameDTO> getAll() {
        try {
            List<Game> listEntity = gameRepository.findAll();
            return StreamSupport.stream(listEntity.spliterator(), false).map(GameMapper::toDTO).toList();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error while get all game: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public Optional<GameDTO> getById(Long id) {
        try {
            Optional<Game> game = gameRepository.findById(id);
            return Optional.of(GameMapper.toDTO(game.get()));
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error while get game by id: " + e.getMessage());
            return Optional.empty();
        }
    }

    public Optional<GameDTO> addGame(GameDTO gameDTO) {
        try {
            Game game = GameMapper.toEntityPOST(gameDTO);
            game = gameRepository.save(game);
            return Optional.of(GameMapper.toDTO(game));
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error while add game: " + e.getMessage());
            return Optional.empty();
        }
    }

    public boolean checkExistingGame(Long id) {
        List<Game> list = gameRepository.findAll();
        for (Game game : list) {
            if (game.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public Optional<GameDTO> updateGame(GameDTO gameDTO) {
        try {
            Optional<Game> existingGameOpt = gameRepository.findById(gameDTO.getId());
            if (!checkExistingGame(gameDTO.getId())) {
                System.err.println("This game is not exist, can not update!");
                return Optional.empty();
            }

            Game existingGame = GameMapper.toEntityPUT(gameDTO);
            System.out.println("Existing game: " + existingGame.getId());
            Game updatedGame = gameRepository.save(existingGame);
            GameDTO gmDTO = GameMapper.toDTO(updatedGame);
            return Optional.of(gmDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    // public Optional<GameDTO> controlDelete(Long id) {
    // }

    // public List<GameDTO> getByKeyword(String keyword) {
    // }

    // public List<GameDTO> getAllGameSorted(String column, String order) {
    // }
}

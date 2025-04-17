package com.example.FacebookCloneBE.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.example.FacebookCloneBE.DTO.GameDTO.GameDTO;
import com.example.FacebookCloneBE.Reponse.ResponseData;
import com.example.FacebookCloneBE.Service.GameService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class GameController {
    @Autowired
    private GameService gameService;
    private ResponseData responseData = new ResponseData();

    @GetMapping("/getAllGames")
    public ResponseEntity<ResponseData> getAllGames() {
        try {
            List<GameDTO> list = gameService.getAll();
            if (list.iterator().hasNext()) {
                responseData.setData(list);
                responseData.setMessage("Get all games success!");
                responseData.setStatusCode(200);
                return ResponseEntity.ok(responseData);
            } else {
                responseData.setMessage("Not found any games!");
                responseData.setStatusCode(404);
                return ResponseEntity.status(404).body(responseData);
            }
        } catch (Exception e) {
            responseData.setMessage("Error when get all games!");
            responseData.setStatusCode(500);
            return ResponseEntity.status(500).body(responseData);
        }
    }

    @GetMapping("/getGameById/{id}")
    public ResponseEntity<ResponseData> getGameById(@PathVariable("id") Long id) {
        try {
            Optional<GameDTO> gmDTO = gameService.getById(id);
            responseData.setData(gmDTO.get());
            responseData.setMessage("Get game by id success!");
            responseData.setStatusCode(200);
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            responseData.setMessage("Error when get game by id!");
            responseData.setStatusCode(500);
            return ResponseEntity.status(500).body(responseData);
        }
    }

    @PostMapping("/addGame")
    public ResponseEntity<ResponseData> addGame(@RequestBody GameDTO gameDTO) {
        try {
            if (gameService.checkExistingGame(gameDTO.getId())) {
                responseData.setMessage("This game is existing! Can not add this game");
                responseData.setStatusCode(500);
                return ResponseEntity.status(500).body(responseData);
            }
            Optional<GameDTO> gm = gameService.addGame(gameDTO);
            responseData.setData(gm.get());
            responseData.setMessage("Add game success!");
            responseData.setStatusCode(200);
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            responseData.setMessage("Error when add game!");
            responseData.setStatusCode(500);
            return ResponseEntity.status(500).body(responseData);
        }
    }

    @PutMapping("/updateGame")
    public ResponseEntity<ResponseData> updateGame(@RequestBody GameDTO gameDTO) {
        try {
            if (!gameService.checkExistingGame(gameDTO.getId())) {
                responseData.setMessage("This game is not existing! Can not update this game");
                responseData.setStatusCode(500);
                return ResponseEntity.status(500).body(responseData);
            }
            Optional<GameDTO> gm = gameService.updateGame(gameDTO);
            responseData.setData(gm.get());
            responseData.setMessage("Updated game success!");
            responseData.setStatusCode(200);
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            responseData.setMessage("Error when update game!");
            responseData.setStatusCode(500);
            return ResponseEntity.status(500).body(responseData);
        }
    }

}

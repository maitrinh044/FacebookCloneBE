package com.example.FacebookCloneBE.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.FacebookCloneBE.DTO.DetailGameDTO.DetailGameDTO;
import com.example.FacebookCloneBE.Reponse.ResponseData;
import com.example.FacebookCloneBE.Service.DetailGameService;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/detailgames")
public class DetailGameController {
    @Autowired
    private DetailGameService detailGameService;
    private ResponseData responseData = new ResponseData();

    @GetMapping("/getByGame/{id}")
    public ResponseEntity<ResponseData> getByGame(@PathVariable("id") Long id) {
        try {
            List<DetailGameDTO> list = detailGameService.getByGame(id);
            if (list.iterator().hasNext()) {
                responseData.setData(list);
                responseData.setMessage("Get detailgames by gameId success!");
                responseData.setStatusCode(200);
                return ResponseEntity.ok(responseData);
            } else {
                responseData.setMessage("Not found any detailgames by gameId!");
                responseData.setStatusCode(404);
                return ResponseEntity.status(404).body(responseData);
            }
        } catch (Exception e) {
            // TODO: handle exception
            responseData.setMessage("Error when get detailgames by gameId!");
            responseData.setStatusCode(500);
            return ResponseEntity.status(500).body(responseData);
        }
    }

    @PostMapping("/addDetailGame")
    public ResponseEntity<ResponseData> addDG(@RequestBody DetailGameDTO detailGameDTO) {
        try {
            Optional<DetailGameDTO> addDG = detailGameService.addDetailGame(detailGameDTO);
            if (addDG.isPresent()) {
                responseData.setData(addDG);
                responseData.setMessage("Add detailGame success!");
                responseData.setStatusCode(200);
                return ResponseEntity.ok(responseData);
            } else {
                responseData.setMessage("Input is invalid!");
                responseData.setStatusCode(401);
                return ResponseEntity.status(401).body(responseData);
            }
        } catch (Exception e) {
            // TODO: handle exception
            responseData.setMessage("Error when add detailGame!");
            responseData.setStatusCode(500);
            return ResponseEntity.status(500).body(responseData);
        }
    }

    // @PutMapping("/controlActiveStatus/{gameID}")
    // public ResponseEntity<ResponseData> controlActiveStatus(@PathVariable Long
    // gameID) {
    // try {
    // List<DetailGameDTO> list =
    // detailGameService.controlActiveStatusByGame(gameID);
    // if (list.iterator().hasNext()) {
    // responseData.setData(list);
    // responseData.setMessage("Control activeStatus of detailgames by gameId
    // success!");
    // responseData.setStatusCode(200);
    // return ResponseEntity.ok(responseData);
    // } else {
    // responseData.setMessage("Not found game!");
    // responseData.setStatusCode(404);
    // return ResponseEntity.status(404).body(responseData);
    // }
    // } catch (Exception e) {
    // // TODO: handle exception
    // responseData.setMessage("Error when control activeStatus of detailgames by
    // gameId!");
    // responseData.setStatusCode(500);
    // return ResponseEntity.status(500).body(responseData);
    // }
    // }
}

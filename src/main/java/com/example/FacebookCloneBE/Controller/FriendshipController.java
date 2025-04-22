package com.example.FacebookCloneBE.Controller;

import com.example.FacebookCloneBE.DTO.FriendshipDTO.FriendshipDTO;
import com.example.FacebookCloneBE.Reponse.ResponseData;
import com.example.FacebookCloneBE.Service.FriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/friendships")
public class FriendshipController {

    @Autowired
    private FriendshipService friendshipService;

    ResponseData responseData = new ResponseData();

    @GetMapping("/getAllFriendships")
    public ResponseEntity<ResponseData> getAllFriendships() {
        Iterable<FriendshipDTO> friendships = friendshipService.getAllFriendships();
        if (friendships.iterator().hasNext()) {
            responseData.setData(friendships);
            responseData.setMessage("Success");
            responseData.setStatusCode(200);
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        } else {
            responseData.setData(null);
            responseData.setMessage("Unsuccess");
            responseData.setStatusCode(204);
            return new ResponseEntity<>(responseData, HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/getAllFriendshipRequests")
    public ResponseEntity<ResponseData> getAllFriendshipRequests() {
        Iterable<FriendshipDTO> requests = friendshipService.getAllFriendshipRequests();
        if (requests.iterator().hasNext()) {
            responseData.setData(requests);
            responseData.setMessage("Success");
            responseData.setStatusCode(200);
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        } else {
            responseData.setData(null);
            responseData.setMessage("Unsuccess");
            responseData.setStatusCode(204);
            return new ResponseEntity<>(responseData, HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/getPendingRequests/{userId}")
    public ResponseEntity<ResponseData> getPendingRequests(@PathVariable("userId") long userId) {
        Iterable<FriendshipDTO> pendingRequests = friendshipService.getPendingRequests(userId);
        if (pendingRequests.iterator().hasNext()) {
            responseData.setData(pendingRequests);
            responseData.setMessage("Success");
            responseData.setStatusCode(200);
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        } else {
            responseData.setData(null);
            responseData.setMessage("Unsuccess");
            responseData.setStatusCode(204);
            return new ResponseEntity<>(responseData, HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/getFriendshipById/{friendshipId}")
    public ResponseEntity<ResponseData> getFriendshipById(@PathVariable("friendshipId") long friendshipId) {
        Optional<FriendshipDTO> friendship = friendshipService.getFriendshipById(friendshipId);
        if (friendship.isPresent()) {
            responseData.setData(friendship.get());
            responseData.setMessage("Success");
            responseData.setStatusCode(200);
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        } else {
            responseData.setData(null);
            responseData.setMessage("Unsuccess");
            responseData.setStatusCode(204);
            return new ResponseEntity<>(responseData, HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/addFriendship")
    public ResponseEntity<ResponseData> addFriendship(@RequestBody FriendshipDTO friendshipDTO) {
        Optional<FriendshipDTO> savedFriendship = friendshipService.addFriendship(friendshipDTO);
        if (savedFriendship.isPresent()) {
            responseData.setData(savedFriendship.get());
            responseData.setMessage("Friendship request sent");
            responseData.setStatusCode(201);
            return new ResponseEntity<>(responseData, HttpStatus.CREATED);
        } else {
            responseData.setData(null);
            responseData.setMessage("Failed to send request");
            responseData.setStatusCode(400);
            return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateFriendship")
    public ResponseEntity<ResponseData> updateFriendship(@RequestBody FriendshipDTO friendshipDTO) {
        Optional<FriendshipDTO> updatedFriendship = friendshipService.updateFriendship(friendshipDTO);
        if (updatedFriendship.isPresent()) {
            responseData.setData(updatedFriendship.get());
            responseData.setMessage("Friendship updated");
            responseData.setStatusCode(200);
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        } else {
            responseData.setData(null);
            responseData.setMessage("Update failed");
            responseData.setStatusCode(400);
            return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
        }
    }
}
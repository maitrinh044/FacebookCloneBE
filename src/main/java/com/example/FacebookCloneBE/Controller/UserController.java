package com.example.FacebookCloneBE.Controller;

import com.example.FacebookCloneBE.DTO.UserDTO.UserDTO;
import com.example.FacebookCloneBE.Reponse.ResponseData;
import com.example.FacebookCloneBE.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    ResponseData responseData = new ResponseData();
    @GetMapping("/getAllUsers")
    public ResponseEntity<ResponseData> getAllUsers() {
        Iterable<UserDTO> users = userService.getAllUsers();
        if (users.iterator().hasNext()) {
            responseData.setData(users);
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

    @GetMapping("/getUserById/{userID}")
    public ResponseEntity<ResponseData> getUserById(@PathVariable("userID") long userID) {
        ResponseData responseData = new ResponseData();
        Optional<UserDTO> user = userService.getUserById(userID);
        if (user.isPresent()) {
            responseData.setData(user.get());
            responseData.setMessage("Success");
            responseData.setStatusCode(200);
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        }
        else {
            responseData.setData(null);
            responseData.setMessage("Unsuccess");
            responseData.setStatusCode(204);
            return new ResponseEntity<>(responseData, HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/getAllFriends/{userId}")
    public ResponseEntity<ResponseData> getAllFriends(@PathVariable("userId") long userId) {
        Iterable<UserDTO> users = userService.getAllFriends(userId);
        if (users.iterator().hasNext()) {
            responseData.setData(users);
            responseData.setMessage("Success");
            responseData.setStatusCode(200);
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        }
        else {
            responseData.setData(null);
            responseData.setMessage("Unsuccess");
            responseData.setStatusCode(204);
            return new ResponseEntity<>(responseData, HttpStatus.NO_CONTENT);
        }
    }
}

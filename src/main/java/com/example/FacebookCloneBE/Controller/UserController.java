package com.example.FacebookCloneBE.Controller;

import com.example.FacebookCloneBE.DTO.GroupDTO.GroupDTO;
import com.example.FacebookCloneBE.DTO.UserDTO.UserDTO;
import com.example.FacebookCloneBE.Reponse.ResponseData;
import com.example.FacebookCloneBE.Service.GroupMemberShipService;
import com.example.FacebookCloneBE.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private GroupMemberShipService groupMemberShipService;

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

    @GetMapping("/getGroupsByUserId/{userId}")
    public ResponseEntity<ResponseData> getGroupsByUserId(@PathVariable("userId") Long userId) {
        try {
            List<GroupDTO> list = groupMemberShipService.getGroupsByUserId(userId);
            if (list.isEmpty()) {
                responseData.setMessage("User này chưa tham gia nhóm nào.");
                responseData.setStatusCode(404);
                responseData.setData(null);
                return new ResponseEntity<>(responseData, HttpStatus.NO_CONTENT);
            } else {
                responseData.setData(list);
                responseData.setMessage("Lấy danh sách nhóm đã tham gia thành công!");
                responseData.setStatusCode(200);
                return new ResponseEntity<>(responseData, HttpStatus.OK);
            }
        } catch (Exception e) {
            responseData.setStatusCode(500);
            responseData.setMessage("Lỗi khi lấy danh sách nhóm đã tham gia: " + e.getMessage());
            return new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

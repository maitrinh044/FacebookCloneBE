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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        } else {
            responseData.setData(null);
            responseData.setMessage("Unsuccess");
            responseData.setStatusCode(204);
            return new ResponseEntity<>(responseData, HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/getAllFriends/{userId}")
    public ResponseEntity<ResponseData> getAllFriends(@PathVariable("userId") long userId) {
        Iterable<UserDTO> users = userService.getAllFriends(userId);
        // if (users.iterator().hasNext()) {
        responseData.setData(users);
        responseData.setMessage("Success");
        responseData.setStatusCode(200);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
        // } else {
        // responseData.setData(null);
        // responseData.setMessage("Unsuccess");
        // responseData.setStatusCode(204);
        // return new ResponseEntity<>(responseData, HttpStatus.NO_CONTENT);
        // }
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

    @PutMapping("/updateUser")
    public ResponseEntity<ResponseData> updateUser(@RequestBody UserDTO userDTO) {
        try {
            Optional<UserDTO> updatedUserDTO = userService.updateUser(userDTO);
            if (updatedUserDTO.isPresent()) {
                responseData.setData(updatedUserDTO.get());
                responseData.setMessage("Updated user success!");
                responseData.setStatusCode(200);
                return ResponseEntity.ok(responseData);
            } else {
                responseData.setMessage("Not found user!");
                responseData.setStatusCode(404);
                return ResponseEntity.status(404).body(responseData);
            }
        } catch (Exception e) {
            // TODO: handle exception
            responseData.setMessage("Error while update user!");
            responseData.setStatusCode(500);
            return ResponseEntity.status(500).body(responseData);
        }
    }

    @GetMapping("/findByKeyword/{keyword}")
    public ResponseEntity<ResponseData> findByKeyword(@PathVariable String keyword) {
        try {
            List<UserDTO> list = userService.findByKeyword(keyword);
            responseData.setData(list);
            responseData.setMessage("Get users by keyword success!");
            responseData.setStatusCode(200);
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            // TODO: handle exception
            responseData.setMessage("Error when get users by keyword!");
            responseData.setStatusCode(500);
            return ResponseEntity.status(500).body(responseData);
        }
    }

    @PutMapping("/controlActiveStatus/{userId}")
    public ResponseEntity<ResponseData> controlActiveStatus(@PathVariable Long userId) {
        try {
            Optional<UserDTO> user = userService.controlActive(userId);
            responseData.setData(user);
            responseData.setMessage("Control activeStatus of user success!");
            responseData.setStatusCode(200);
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            // TODO: handle exception
            responseData.setMessage("Error when control activeStatus of user");
            responseData.setStatusCode(500);
            return ResponseEntity.status(500).body(responseData);
        }
    }
}

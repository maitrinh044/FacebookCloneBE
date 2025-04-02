package com.example.FacebookCloneBE.Controller;

import com.example.FacebookCloneBE.DTO.ChatGroupDTO.ChatGroupDTO;
import com.example.FacebookCloneBE.Reponse.ResponseData;
import com.example.FacebookCloneBE.Service.ChatGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/chatgroups")
public class ChatGroupController {

    @Autowired
    private ChatGroupService chatGroupService;

    ResponseData responseData = new ResponseData();

    // Get all chat groups
    @GetMapping("/")
    public ResponseEntity<ResponseData> getAllChatGroups() {
        responseData.setData(chatGroupService.getAllChatGroups());
        responseData.setStatusCode(200);
        responseData.setMessage("Success");
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    // Get a chat group by ID
    @GetMapping("/{groupId}")
    public ResponseEntity<ResponseData> getChatGroupById(@PathVariable long groupId) {
        var chatGroupDTO = chatGroupService.getChatGroupById(groupId);
        if (chatGroupDTO.isPresent()) {
            responseData.setData(chatGroupDTO.get());
            responseData.setStatusCode(200);
            responseData.setMessage("Success");
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        } else {
            responseData.setData(null);
            responseData.setStatusCode(404);
            responseData.setMessage("Chat group not found");
            return new ResponseEntity<>(responseData, HttpStatus.NOT_FOUND);
        }
    }

    // Create a new chat group
    @PostMapping("/")
    public ResponseEntity<ResponseData> createChatGroup(@Valid @RequestBody ChatGroupDTO chatGroupDTO) {
        ChatGroupDTO createdChatGroup = chatGroupService.createChatGroup(chatGroupDTO);
        responseData.setData(createdChatGroup);
        responseData.setStatusCode(201);
        responseData.setMessage("Chat group created successfully");
        return new ResponseEntity<>(responseData, HttpStatus.CREATED);
    }

    // Update an existing chat group
    @PutMapping("/{groupId}")
    public ResponseEntity<ResponseData> updateChatGroup(
            @PathVariable long groupId, 
            @Valid @RequestBody ChatGroupDTO chatGroupDTO) {
        ChatGroupDTO updatedChatGroup = chatGroupService.updateChatGroup(groupId, chatGroupDTO);
        if (updatedChatGroup != null) {
            responseData.setData(updatedChatGroup);
            responseData.setStatusCode(200);
            responseData.setMessage("Chat group updated successfully");
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        } else {
            responseData.setData(null);
            responseData.setStatusCode(404);
            responseData.setMessage("Chat group not found");
            return new ResponseEntity<>(responseData, HttpStatus.NOT_FOUND);
        }
    }

    // Delete a chat group
    @DeleteMapping("/{groupId}")
    public ResponseEntity<ResponseData> deleteChatGroup(@PathVariable long groupId) {
        boolean isDeleted = chatGroupService.deleteChatGroup(groupId);
        if (isDeleted) {
            responseData.setData(null);
            responseData.setStatusCode(200);
            responseData.setMessage("Chat group deleted successfully");
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        } else {
            responseData.setData(null);
            responseData.setStatusCode(404);
            responseData.setMessage("Chat group not found");
            return new ResponseEntity<>(responseData, HttpStatus.NOT_FOUND);
        }
    }
}

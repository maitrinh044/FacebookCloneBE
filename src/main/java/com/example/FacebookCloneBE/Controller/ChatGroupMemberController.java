package com.example.FacebookCloneBE.Controller;

import com.example.FacebookCloneBE.DTO.ChatGroupMemberDTO.ChatGroupMemberDTO;
import com.example.FacebookCloneBE.Reponse.ResponseData;
import com.example.FacebookCloneBE.Service.ChatGroupMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/chatGroupMember")
public class ChatGroupMemberController {

    @Autowired
    private ChatGroupMemberService chatGroupMemberService;

    private final ResponseData responseData = new ResponseData();

    // Lấy tất cả chat group members
    @GetMapping("/getAll")
    public ResponseEntity<ResponseData> getAllChatGroupMembers() {
        try {
            List<ChatGroupMemberDTO> chatGroupMembers = chatGroupMemberService.getAllChatGroupMembers();
            if (!chatGroupMembers.isEmpty()) {
                responseData.setData(chatGroupMembers);
                responseData.setStatusCode(200);
                responseData.setMessage("Success");
                return new ResponseEntity<>(responseData, HttpStatus.OK);
            } else {
                responseData.setData(null);
                responseData.setStatusCode(204);
                responseData.setMessage("No chat group members found");
                return new ResponseEntity<>(responseData, HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseData.setData(null);
            responseData.setStatusCode(500);
            responseData.setMessage("Error fetching chat group members");
            return new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Lấy chat group member theo ID
    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseData> getChatGroupMemberById(@PathVariable long id) {
        try {
            Optional<ChatGroupMemberDTO> chatGroupMemberDTO = chatGroupMemberService.getChatGroupMemberById(id);
            if (chatGroupMemberDTO.isPresent()) {
                responseData.setData(chatGroupMemberDTO.get());
                responseData.setStatusCode(200);
                responseData.setMessage("Success");
                return new ResponseEntity<>(responseData, HttpStatus.OK);
            } else {
                responseData.setData(null);
                responseData.setStatusCode(404);
                responseData.setMessage("Chat group member not found");
                return new ResponseEntity<>(responseData, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseData.setData(null);
            responseData.setStatusCode(500);
            responseData.setMessage("Error fetching chat group member");
            return new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Tạo chat group member mới
    @PostMapping("/create")
    public ResponseEntity<ResponseData> createChatGroupMember(@RequestBody ChatGroupMemberDTO chatGroupMemberDTO) {
        try {
            ChatGroupMemberDTO createdMember = chatGroupMemberService.createChatGroupMember(chatGroupMemberDTO);
            responseData.setData(createdMember);
            responseData.setStatusCode(201);
            responseData.setMessage("Chat group member created successfully");
            return new ResponseEntity<>(responseData, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            responseData.setData(null);
            responseData.setStatusCode(500);
            responseData.setMessage("Error creating chat group member");
            return new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Cập nhật chat group member
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseData> updateChatGroupMember(@PathVariable long id, @RequestBody ChatGroupMemberDTO chatGroupMemberDTO) {
        try {
            ChatGroupMemberDTO updatedMember = chatGroupMemberService.updateChatGroupMember(id, chatGroupMemberDTO);
            if (updatedMember != null) {
                responseData.setData(updatedMember);
                responseData.setStatusCode(200);
                responseData.setMessage("Chat group member updated successfully");
                return new ResponseEntity<>(responseData, HttpStatus.OK);
            } else {
                responseData.setData(null);
                responseData.setStatusCode(404);
                responseData.setMessage("Chat group member not found");
                return new ResponseEntity<>(responseData, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseData.setData(null);
            responseData.setStatusCode(500);
            responseData.setMessage("Error updating chat group member");
            return new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Xóa chat group member
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseData> deleteChatGroupMember(@PathVariable long id) {
        try {
            boolean isDeleted = chatGroupMemberService.deleteChatGroupMember(id);
            if (isDeleted) {
                responseData.setData(null);
                responseData.setStatusCode(200);
                responseData.setMessage("Chat group member deleted successfully");
                return new ResponseEntity<>(responseData, HttpStatus.OK);
            } else {
                responseData.setData(null);
                responseData.setStatusCode(404);
                responseData.setMessage("Chat group member not found");
                return new ResponseEntity<>(responseData, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseData.setData(null);
            responseData.setStatusCode(500);
            responseData.setMessage("Error deleting chat group member");
            return new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

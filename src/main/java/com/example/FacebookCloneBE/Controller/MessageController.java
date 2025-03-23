package com.example.FacebookCloneBE.Controller;

import com.example.FacebookCloneBE.DTO.MessageDTO.MessageDTO;
import com.example.FacebookCloneBE.Reponse.ResponseData;
import com.example.FacebookCloneBE.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class MessageController {
    @Autowired
    private MessageService messageService;

    ResponseData responseData = new ResponseData();

    @GetMapping("/getMessageByUser")
    public ResponseEntity<ResponseData> messageByUser(@RequestParam Long senderId, @RequestParam Long receiverId) {
        Iterable<MessageDTO> messageDTOList = messageService.getMessagesForUser(senderId, receiverId);
        if (messageDTOList.iterator().hasNext()) {
            responseData.setData(messageDTOList);
            responseData.setStatusCode(200);
            responseData.setMessage("Success");
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        } else {
            responseData.setData(null);
            responseData.setStatusCode(204);
            responseData.setMessage("Unsuccessful");
            return new ResponseEntity<>(responseData, HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/addNewMessage")
    public ResponseEntity<ResponseData> addNewMessage(@Valid @RequestBody MessageDTO messageDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors()
                    .stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.toList());
            responseData.setStatusCode(400);
            responseData.setMessage(errors.get(0));
            responseData.setData(null);
            return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
        }

        try {
            Optional<MessageDTO> savedMessage = messageService.addMessage(messageDTO);
            if (savedMessage.isPresent()) {
                responseData.setData(savedMessage.get());
                responseData.setStatusCode(200);
                responseData.setMessage("Success");
                return new ResponseEntity<>(responseData, HttpStatus.OK);
            } else {
                responseData.setData(null);
                responseData.setStatusCode(204);
                responseData.setMessage("Unsuccessful");
                return new ResponseEntity<>(responseData, HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseData.setData(null);
            responseData.setStatusCode(505);
            responseData.setMessage("Unsuccessful");
            return new ResponseEntity<>(responseData, HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/getLastMessage")
    public ResponseEntity<ResponseData> getLastMessage(@RequestParam("senderId") Long senderId, @RequestParam("receiverId") Long receiverId) {
        try {
            Optional<MessageDTO> lastMessage = messageService.getLastMessageForUser(senderId, receiverId);
            if (lastMessage.isPresent()) {
                responseData.setData(lastMessage.get());
                responseData.setStatusCode(200);
                responseData.setMessage("Success");
                return new ResponseEntity<>(responseData, HttpStatus.OK);
            } else {
                responseData.setData(null);
                responseData.setStatusCode(204);
                responseData.setMessage("Unsuccessful");
                return new ResponseEntity<>(responseData, HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseData.setData(null);
            responseData.setStatusCode(505);
            responseData.setMessage("Unsuccessful");
            return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
        }
    }

}

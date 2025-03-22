package com.example.FacebookCloneBE.Controller;

import com.example.FacebookCloneBE.DTO.MessageDTO.MessageDTO;
import com.example.FacebookCloneBE.Model.Message;
import com.example.FacebookCloneBE.Reponse.ResponseData;
import com.example.FacebookCloneBE.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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


}

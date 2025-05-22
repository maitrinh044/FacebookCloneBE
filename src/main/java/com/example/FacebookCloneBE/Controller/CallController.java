package com.example.FacebookCloneBE.Controller;

import com.example.FacebookCloneBE.DTO.SignalingMessageDTO.SignalingMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class CallController {

    private final SimpMessagingTemplate messagingTemplate;

    public CallController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/call")
    public void handleCallSignal(SignalingMessage message, Principal principal) {
        System.out.println("message: " + message);
        messagingTemplate.convertAndSendToUser(String.valueOf(message.getTo().getId()), "/topic/call", message);
        System.out.println("Message sent!");
    }
}


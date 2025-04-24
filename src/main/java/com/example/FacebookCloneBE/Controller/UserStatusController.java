package com.example.FacebookCloneBE.Controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/status")
public class UserStatusController {

    private final SimpMessagingTemplate messagingTemplate;

    public UserStatusController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    // Xử lý trạng thái khi người dùng online
    @MessageMapping("/online")
    public void userOnline(String username) {
        System.out.println(username + " is online");
        messagingTemplate.convertAndSend("/topic/status", username + " is online");
    }

    // Xử lý trạng thái khi người dùng offline
    @MessageMapping("/offline")
    public void userOffline(String username) {
        System.out.println(username + " is offline");
        messagingTemplate.convertAndSend("/topic/status", username + " is offline");
    }
}

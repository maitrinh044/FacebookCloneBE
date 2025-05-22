package com.example.FacebookCloneBE.Controller;

import com.example.FacebookCloneBE.DTO.MessageDTO.MessageDTO;
import com.example.FacebookCloneBE.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;
    private final MessageService messageService;

    @Autowired
    public ChatController(SimpMessagingTemplate messagingTemplate, MessageService messageService) {
        this.messagingTemplate = messagingTemplate;
        this.messageService = messageService;
    }

    // Nhận tin nhắn từ client và gửi lại cho người nhận qua topic riêng
    @MessageMapping("/sendMessage")
    public void sendMessage(MessageDTO messageDTO, Principal principal) {
        System.out.println("Tin nhắn nhận được: " + messageDTO);
        System.out.println("Người gửi: " + principal.getName());
        // Gửi đến người nhận theo đích danh
        String receiverTopic = "/topic/messages/" + messageDTO.getReceiverId().getId();
        System.out.println("Gửi đến topic: " + receiverTopic);
        messagingTemplate.convertAndSendToUser(String.valueOf(messageDTO.getReceiverId().getId()), "/topic/messages", messageDTO);
    }

}

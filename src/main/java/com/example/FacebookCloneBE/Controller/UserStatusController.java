package com.example.FacebookCloneBE.Controller;

import com.example.FacebookCloneBE.DTO.UserDTO.UserDTO;
import com.example.FacebookCloneBE.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/status")
public class UserStatusController {

    private final SimpMessagingTemplate messagingTemplate;
    private final UserService userService;

    public UserStatusController(SimpMessagingTemplate messagingTemplate, UserService userService) {
        this.messagingTemplate = messagingTemplate;
        this.userService = userService;
    }

    // Xử lý trạng thái khi người dùng online
    @MessageMapping("/online")
    public void userOnline(Long userId) {
        // Lưu trạng thái online vào cơ sở dữ liệu (ví dụ: từ username lấy userId)
        userService.updateOnlineStatus(userId, true);

        System.out.println(userId + " is online");

        // Gửi thông báo tới tất cả client về trạng thái online
        messagingTemplate.convertAndSend("/topic/status/", userId + " is online");

        List<UserDTO> onlineFriends = userService.getOnlineFriends(userId);

        if (onlineFriends != null) {
            for (UserDTO onlineFriend : onlineFriends) {
                messagingTemplate.convertAndSend("/topic/status", onlineFriend.getId()  + " is online");
            }
        }
    }

    // Xử lý trạng thái khi người dùng offline
    @MessageMapping("/offline")
    public void userOffline(Long userId) {
        userService.updateOnlineStatus(userId, false);
        System.out.println(userId + " is offline");
        messagingTemplate.convertAndSend("/topic/status", userId + " is offline");
    }

}

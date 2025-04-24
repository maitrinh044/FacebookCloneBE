package com.example.FacebookCloneBE.Listener;

import com.example.FacebookCloneBE.Service.PresenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.List;

@Component
public class PresenceEventListener {

    @Autowired
    private PresenceService presenceService;

    @EventListener
    public void handleSessionConnected(SessionConnectedEvent event) {
        // Từ header, lấy userId đã đăng nhập
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        Long userId = extractUserId(headerAccessor); // tự định nghĩa

        if (userId != null) {
            presenceService.markOnline(userId);
        }
    }

    @EventListener
    public void handleSessionDisconnect(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        Long userId = extractUserId(headerAccessor); // tự định nghĩa

        if (userId != null) {
            presenceService.markOffline(userId);
        }
    }

    private Long extractUserId(StompHeaderAccessor accessor) {
        // Ví dụ nếu lưu userId ở header 'user-id':
        List<String> headers = accessor.getNativeHeader("user-id");
        if (headers != null && !headers.isEmpty()) {
            return Long.parseLong(headers.get(0));
        }
        return null;
    }
}

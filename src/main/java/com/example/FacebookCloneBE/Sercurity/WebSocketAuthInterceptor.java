package com.example.FacebookCloneBE.Sercurity;

import com.example.FacebookCloneBE.Config.JwtUtils;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Component;

@Component
public class WebSocketAuthInterceptor implements ChannelInterceptor {

    private final JwtUtils jwtUtils;

    public WebSocketAuthInterceptor(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
            // Lấy token từ header "Authorization"
            String authHeader = accessor.getFirstNativeHeader("Authorization");

            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7); // bỏ "Bearer "

                if (jwtUtils.validateToken(token)) {
                    Long userId = Long.valueOf(jwtUtils.getUserIdFromToken(token));

                    // Gán userId thành Principal cho kết nối WebSocket
                    accessor.setUser(new StompPrincipal(userId.toString()));
                } else {
                    throw new IllegalArgumentException("Invalid JWT token");
                }
            } else {
                throw new IllegalArgumentException("Missing or invalid Authorization header");
            }
        }

        return message;
    }


}

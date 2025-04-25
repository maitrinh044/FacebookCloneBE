package com.example.FacebookCloneBE.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // Định nghĩa các đường dẫn mà message broker sẽ quản lý
        config.enableSimpleBroker("/topic"); // Đây là prefix để gửi message tới client
        config.setApplicationDestinationPrefixes("/app"); // Prefix gửi tới server
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Đăng ký endpoint cho client kết nối (WebSocket)
        registry.addEndpoint("/ws").setAllowedOrigins("*"); // Đảm bảo server chấp nhận WebSocket qua "/ws"
    }
}

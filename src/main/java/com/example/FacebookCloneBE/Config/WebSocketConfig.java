package com.example.FacebookCloneBE.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker // Cho phép WebSocket message broker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // Định nghĩa các điểm cuối của broker
        registry.enableSimpleBroker("/topic");  // Enable simple broker để client có thể nhận các tin nhắn
        registry.setApplicationDestinationPrefixes("/app");  // Định nghĩa các prefix gửi tin nhắn tới server
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Đăng ký các điểm cuối của WebSocket
        registry.addEndpoint("/ws").withSockJS();  // Client sẽ kết nối tới "/ws"
    }
}

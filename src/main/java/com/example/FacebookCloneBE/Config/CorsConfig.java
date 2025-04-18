package com.example.FacebookCloneBE.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Áp dụng CORS cho tất cả các endpoint
                .allowedOrigins("http://localhost:5173")  // Cho phép các yêu cầu từ front-end
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Cho phép các phương thức này
                .allowedHeaders("*")  // Cho phép mọi header
                .allowCredentials(true);  // Cho phép gửi thông tin xác thực (cookies, header)
    }
}



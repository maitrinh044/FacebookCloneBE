package com.example.FacebookCloneBE.DTO.JwtResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponseDTO {
    private String accessToken;
    private String refreshToken;
    private Long userId;
    private String email;

    // Constructor cho lỗi (chỉ cần message, không có token)
    public JwtResponseDTO(String message) {
        this.accessToken = null;
        this.refreshToken = null;
        this.userId = null;
        this.email = null;
    }
}

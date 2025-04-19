package com.example.FacebookCloneBE.Sercurity;

import com.example.FacebookCloneBE.DTO.JwtResponseDTO.JwtResponseDTO;
import com.example.FacebookCloneBE.DTO.UserDTO.UserDTO;
import org.springframework.stereotype.Service;

@Service
public interface JwtAuthService {
    JwtResponseDTO authenticateUser(UserDTO user);
    JwtResponseDTO refreshJwtToken(String refreshToken) throws Exception;
    boolean logoutUser(String refreshToken);
    String getUserIdFromToken(String token);
}

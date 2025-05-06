package com.example.FacebookCloneBE.Sercurity;

import com.example.FacebookCloneBE.Config.JwtUtils;
import com.example.FacebookCloneBE.DTO.JwtResponseDTO.JwtResponseDTO;
import com.example.FacebookCloneBE.DTO.UserDTO.UserDTO;
import com.example.FacebookCloneBE.Mapper.UserMapper;
import com.example.FacebookCloneBE.Model.User;
import com.example.FacebookCloneBE.Model.UserSession;
import com.example.FacebookCloneBE.Repository.UserRepository;
import com.example.FacebookCloneBE.Repository.UserSessionRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class JwtAuthServiceImpl implements JwtAuthService {

    private final JwtUtils jwtUtils;
    private final UserSessionRepository userSessionRepository;
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Autowired
    UserRepository userRepository;

    @Autowired
    public JwtAuthServiceImpl(JwtUtils jwtUtils, UserSessionRepository userSessionRepository) {
        this.jwtUtils = jwtUtils;
        this.userSessionRepository = userSessionRepository;
    }

    // Tạo JWT khi đăng nhập
    public JwtResponseDTO authenticateUser(UserDTO user) {
        // Tạo Access Token
        String accessToken = jwtUtils.generateAccessToken(user);
        // Tạo Refresh Token
        String refreshToken = jwtUtils.generateRefreshToken(user);

        // Lưu refresh token vào database
        UserSession userSession = new UserSession(user.getId(), refreshToken, LocalDateTime.now(), LocalDateTime.now().plusDays(5));
        System.out.print("refresh token: " + refreshToken);
        userSessionRepository.save(userSession);
        System.out.println("Token trả về client: " + refreshToken); // In trong authenticateUser()

        // Lấy thông tin người dùng từ cơ sở dữ liệu, giả sử bạn đã có đối tượng `user`
                Long userId = user.getId();
                String email = user.getEmail();

        // Trả về JwtResponseDTO với đầy đủ tham số
        return new JwtResponseDTO(accessToken, refreshToken, userId, email);
    }

    public JwtResponseDTO refreshJwtToken(String refreshToken) throws Exception {
        System.out.println("refresh token trong auth service impl: " + refreshToken); // In trong refreshJwtToken()
        System.out.println("refresh token length: " + refreshToken.length());
        Optional<UserSession> sessionOpt = userSessionRepository.findByRefreshToken(refreshToken);
        if (sessionOpt.isPresent()) {
            System.out.println("user session found: " + sessionOpt.get().getId());
        } else {
            System.out.println("user session not found");
        }
        if (sessionOpt.isEmpty()) {
            throw new Exception("Invalid refresh token");
        }

        UserSession session = sessionOpt.get();

        // Kiểm tra hạn
        if (session.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new Exception("Refresh token has expired");
        }

        // Lấy User từ userId trong session
        Long userId = session.getUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception("User not found"));

        // Tạo Access Token mới
        String accessToken = jwtUtils.generateAccessToken(UserMapper.toUserDTO(user));

        return new JwtResponseDTO(accessToken, refreshToken, user.getId(), user.getEmail());
    }



    // Xóa Refresh Token khi logout
    public boolean logoutUser(String refreshToken) {
        // Tìm session chứa refresh token
        System.out.println("refreshToken: " + refreshToken);
        Optional<UserSession> userSessionOptional = userSessionRepository.findByRefreshToken(refreshToken);

        if (userSessionOptional.isPresent()) {
            // Xóa session này khỏi cơ sở dữ liệu
            userSessionRepository.delete(userSessionOptional.get());
            System.out.println("User logged out successfully");
            return true; // Đăng xuất thành công
        } else {
            System.out.println("User not found");
            return false; // Không tìm thấy session, không thể đăng xuất
        }
    }


    @Override
    public String getUserIdFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

}


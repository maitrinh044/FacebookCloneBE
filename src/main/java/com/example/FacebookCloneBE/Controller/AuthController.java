package com.example.FacebookCloneBE.Controller;


import com.example.FacebookCloneBE.DTO.UserDTO.UserDTO;
import com.example.FacebookCloneBE.DTO.UserDTO.UserLoginDTO;
import com.example.FacebookCloneBE.Model.User;
import com.example.FacebookCloneBE.Sercurity.JwtAuthService;
import com.example.FacebookCloneBE.DTO.JwtResponseDTO.JwtResponseDTO;
import com.example.FacebookCloneBE.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtAuthService jwtAuthService;

    @Autowired
    private UserService userService;

    // Đăng nhập và trả về JWT Token
    @PostMapping("/login")
    public ResponseEntity<JwtResponseDTO> authenticateUser(@RequestBody UserLoginDTO user) {
        try {
            System.out.println(user.getEmailOrPhone());
            UserDTO userDTO = userService.findByEmailOrPhone(user.getEmailOrPhone());

            JwtResponseDTO jwtResponseDTO = jwtAuthService.authenticateUser(userDTO);

            return ResponseEntity.ok(jwtResponseDTO);
        } catch (Exception e) {
            // Xử lý lỗi nếu thông tin đăng nhập sai
            e.printStackTrace();
            return ResponseEntity.status(401).body(new JwtResponseDTO("Invalid credentials"));

        }
    }

    // Làm mới Access Token từ Refresh Token
    @PostMapping("/refresh-token")
    public ResponseEntity<JwtResponseDTO> refreshToken(@RequestBody String refreshToken) {
        try {
            JwtResponseDTO jwtResponseDTO = jwtAuthService.refreshJwtToken(refreshToken);
            return ResponseEntity.ok(jwtResponseDTO);
        } catch (Exception e) {
            // Nếu refresh token không hợp lệ
            return ResponseEntity.status(401).body(new JwtResponseDTO("Invalid credentials"));

        }
    }

    // Đăng xuất và xóa Refresh Token
    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody String refreshToken) {
        try {
            // Loại bỏ dấu ngoặc kép nếu có
            refreshToken = refreshToken.trim().replace("\"", "");
            // Kiểm tra xem refresh token có hợp lệ không
            if (refreshToken == null || refreshToken.isEmpty()) {
                return ResponseEntity.status(400).body("Refresh token is missing");
            }

            // Kiểm tra và xử lý đăng xuất người dùng
            boolean isLoggedOut = jwtAuthService.logoutUser(refreshToken);
            if (isLoggedOut) {
                return ResponseEntity.ok("User logged out successfully");
            } else {
                return ResponseEntity.status(400).body("Error logging out: Invalid refresh token");
            }
        } catch (Exception e) {
            // Xử lý lỗi đăng xuất
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }



    // Kiểm tra quyền người dùng
    @GetMapping("/user-info")
    public ResponseEntity<UserDTO> getUserInfo(HttpServletRequest request) {
        // Lấy token từ header Authorization
        String token = request.getHeader("Authorization");
        System.out.println(token);
        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.status(400).body(null);  // Trả về lỗi nếu không có token hoặc token sai định dạng
        }
        token = token.substring(7);  // Lấy token sau "Bearer "

        // Lấy userId từ token
        String userId = jwtAuthService.getUserIdFromToken(token);

        // Tìm người dùng theo userId
        Optional<UserDTO> optionalUser = userService.getUserById(Long.parseLong(userId));

        // Kiểm tra nếu người dùng không tồn tại
        if (optionalUser.isEmpty()) {
            return ResponseEntity.notFound().build();  // Trả về 404 nếu không tìm thấy người dùng
        }

        // Lấy user từ Optional
        UserDTO user = optionalUser.get();

        return ResponseEntity.ok(user);
    }
}

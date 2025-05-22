package com.example.FacebookCloneBE.Config;

import com.example.FacebookCloneBE.DTO.UserDTO.UserDTO;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

    // Key bí mật dùng để ký JWT (Thường nên lấy từ file cấu hình hoặc môi trường, không nên hardcode)
    @Value("${jwt.secret}")
    private String jwtSecret;
    private final int jwtExpirationMs = 86400000; // 1 ngày = 24 giờ * 60 phút * 60 giây * 1000 mili giây

    // Tạo Access Token
    public String generateAccessToken(UserDTO user) {
        System.out.println("user id: " + user.getId());

        return Jwts.builder()
                .setSubject(user.getId().toString()) // lưu userId vào token
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    // Tạo Refresh Token
    public String generateRefreshToken(UserDTO user) {
        return Jwts.builder()
                .setSubject(user.getId().toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 604800000)) // 7 ngày
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    // Lấy id từ token
    public String getUserIdFromToken(String token) {
        try {
            // Tạo một JwtParser từ parserBuilder
            Claims claims = Jwts.parser()
                    .setSigningKey(jwtSecret) // Set secret key
                    .build() // Build JwtParser
                    .parseClaimsJws(token) // Parse the token
                    .getBody(); // Get claims body

            // Trả về subject (userName) từ claims
            return claims.getSubject();
        } catch (SignatureException e) {
            // Xử lý lỗi không hợp lệ token, nếu có
            throw new IllegalArgumentException("Invalid JWT signature");
        } catch (Exception e) {
            // Xử lý các lỗi khác
            throw new IllegalArgumentException("Invalid JWT token");
        }
    }

    // Kiểm tra xem token có hợp lệ không
    public boolean validateToken(String token) {
        try {
            Jwts.parser()  // Sử dụng parserBuilder() thay vì parser()
                    .setSigningKey(jwtSecret)  // Cung cấp secret key để xác minh chữ ký
                    .build()  // Xây dựng parser
                    .parseClaimsJws(token);  // Phân tích và kiểm tra tính hợp lệ của token
            return true;  // Token hợp lệ
        } catch (SignatureException | MalformedJwtException | ExpiredJwtException | UnsupportedJwtException | IllegalArgumentException e) {
            // Nếu có lỗi thì return false
            return false;
        }
    }

    // Trích xuất thông tin (claims) từ token
    public Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}


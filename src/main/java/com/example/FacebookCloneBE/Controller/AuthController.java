package com.example.FacebookCloneBE.Controller;


import com.example.FacebookCloneBE.DTO.UserDTO.UserDTO;
import com.example.FacebookCloneBE.DTO.UserDTO.UserLoginDTO;
import com.example.FacebookCloneBE.DTO.UserDTO.UserRegisterDTO;
import com.example.FacebookCloneBE.Model.User;
import com.example.FacebookCloneBE.Reponse.ResponseData;
import com.example.FacebookCloneBE.Sercurity.JwtAuthService;
import com.example.FacebookCloneBE.DTO.JwtResponseDTO.JwtResponseDTO;
import com.example.FacebookCloneBE.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtAuthService jwtAuthService;

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    ResponseData responseData = new ResponseData();


    // Đăng nhập và trả về JWT Token
    @PostMapping("/login")
    public ResponseEntity<ResponseData> authenticateUser(@RequestBody @Valid  UserLoginDTO user, BindingResult bindingResult) {
        try {
            List<String> errors = new ArrayList<>();
            if (bindingResult.hasErrors()) {
                // Trả về danh sách lỗi
                errors = bindingResult.getFieldErrors().stream()
                        .map(err -> err.getField() + ": " + err.getDefaultMessage())
                        .collect(Collectors.toList());
            }
            Optional<UserDTO> userDTO = userService.findByEmailOrPhone(user.getEmailOrPhone());
            System.out.println("password: " + user.getPassword());
            String encodedPassword = passwordEncoder.encode(user.getPassword());

            // In mật khẩu đã mã hóa
            System.out.println("Mật khẩu đã mã hóa: " + encodedPassword);
            if (userDTO.isPresent()) {
                if (!passwordEncoder.matches(user.getPassword(), userDTO.get().getPassword())) {
                    errors.add("Sai mật khẩu");
                }
            } else {
                errors.add("Email hoặc số điện thoại không chính xác");
            }

            if (!errors.isEmpty()) {
                responseData.setData(errors);
                responseData.setMessage("Đăng nhập thất bại");
                responseData.setStatusCode(HttpStatus.BAD_REQUEST.value());
                return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
            }
            JwtResponseDTO jwtResponseDTO = jwtAuthService.authenticateUser(userDTO.get());
            responseData.setData(jwtResponseDTO);
            responseData.setStatusCode(204);
            responseData.setMessage("Đăng nhập thành công");
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        } catch (Exception e) {
            // Xử lý lỗi nếu thông tin đăng nhập sai
            e.printStackTrace();
            responseData.setData(null);
            responseData.setMessage("Đăng nhập thất bại");
            responseData.setStatusCode(HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
        }
    }

    // Làm mới Access Token từ Refresh Token
    @PostMapping("/refresh-token")
    public ResponseEntity<JwtResponseDTO> refreshToken(@RequestBody Map<String, String> body) {
        try {
            String refreshToken = body.get("refreshToken");
            System.out.println("refresh token auth controller: " + refreshToken);
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

    // Đăng ký tài khoản user
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid UserRegisterDTO dto, BindingResult bindingResult) {
        try {
            System.out.println("user dto register: " + dto);
            List<String> errors = new ArrayList<>();
            if (bindingResult.hasErrors()) {
                // Trả về danh sách lỗi
                errors = bindingResult.getFieldErrors().stream()
                        .map(err -> err.getField() + ": " + err.getDefaultMessage())
                        .collect(Collectors.toList());
            }
            if (dto.getEmailOrPhone() == null) {
                errors.add("Phải bao gồm số di động hoặc email");
            } else {
                // Kiểm tra xem chuỗi có phải số điện thoại hợp lệ (chỉ chứa số và độ dài chính xác 10 chữ số)
                boolean isPhone = dto.getEmailOrPhone().matches("^0\\d{9}$");

                // Kiểm tra chuỗi có phải là email hợp lệ
                boolean isEmail = dto.getEmailOrPhone().matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA0-9]{2,7}$");

                if (!isPhone && !isEmail) {
                    errors.add("Email hoặc số điện thoại không hợp lệ");
                }
            }

            if (dto.getEmailOrPhone() != null && !userService.findByEmailOrPhone(dto.getEmailOrPhone()).isEmpty()) {
                System.out.println("1");
                errors.add("Email hoặc số điện thoại đã tồn tại");
            }
            // Nếu có lỗi, trả về lỗi
            if (!errors.isEmpty()) {
                responseData.setData(errors);
                responseData.setMessage("Có lỗi khi đăng ký");
                responseData.setStatusCode(505);
                return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
            }

            LocalDateTime localDate = LocalDateTime.now();
            dto.setCreateAt(localDate);

            // Mã hóa mật khẩu
            String encodedPassword = passwordEncoder.encode(dto.getPassword());
            dto.setPassword(encodedPassword);  // Đặt mật khẩu đã mã hóa
            Optional<UserDTO> savedUser = userService.addRegisterUser(dto);

            if (savedUser.isPresent()) {
                responseData.setData(savedUser.get());
                responseData.setStatusCode(200);
                responseData.setMessage("User registered successfully");
                return new ResponseEntity<>(responseData, HttpStatus.CREATED);
            } else {
                responseData.setStatusCode(400);
                responseData.setMessage("Error registering user");
                responseData.setData(dto);
                return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
            }
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi server!");
        }
    }
}

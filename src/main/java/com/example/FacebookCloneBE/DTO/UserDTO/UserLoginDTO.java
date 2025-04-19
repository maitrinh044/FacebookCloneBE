package com.example.FacebookCloneBE.DTO.UserDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserLoginDTO {
    @NotBlank(message = "Email hoặc số điện thoại không được để trống")
    private String emailOrPhone;
    @NotBlank(message = "Mật khẩu không được để trống")
    private String password;
}

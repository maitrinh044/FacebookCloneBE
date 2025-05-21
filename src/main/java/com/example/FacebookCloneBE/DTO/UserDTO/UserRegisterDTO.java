package com.example.FacebookCloneBE.DTO.UserDTO;

import com.example.FacebookCloneBE.Enum.GenderEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

// @Data
// @AllArgsConstructor
// @NoArgsConstructor
// public class UserRegisterDTO {

//     @NotBlank(message = "Tên không được để trống")
//     private String firstName;
//     @NotBlank(message = "Họ không được để trống")
//     private String lastName;
//     private String emailOrPhone;
//     @NotBlank(message = "Mật khẩu không được để trống")
//     private String password;
//     @NotNull(message = "Ngày sinh không được để trống")
//     @Past(message = "Ngày sinh phải là ngày trong quá khứ")
//     public LocalDate birthday;
//     @NotNull(message = "Giới tính không được để trống")
//     private GenderEnum gender;
//     private LocalDateTime createAt;
// }

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.Period;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterDTO {

    @NotBlank(message = "Tên không được để trống")
    private String firstName;

    @NotBlank(message = "Họ không được để trống")
    private String lastName;

    private String emailOrPhone;

    @NotBlank(message = "Mật khẩu không được để trống")
    private String password;

    @NotNull(message = "Ngày sinh không được để trống")
    @Past(message = "Ngày sinh phải là ngày trong quá khứ")
    private LocalDate birthday;

    @NotNull(message = "Giới tính không được để trống")
    private GenderEnum gender;

    private LocalDateTime createAt;

    public boolean isAgeValid() {
        return birthday != null && Period.between(birthday, LocalDate.now()).getYears() >= 18;
    }
}
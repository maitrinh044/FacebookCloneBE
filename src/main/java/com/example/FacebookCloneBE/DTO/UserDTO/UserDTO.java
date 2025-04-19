package com.example.FacebookCloneBE.DTO.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.FacebookCloneBE.Enum.ActiveEnum;
import com.example.FacebookCloneBE.Enum.GenderEnum;
import com.example.FacebookCloneBE.Model.Role;

import jakarta.persistence.Column;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
    private String profilePicture;
    private String coverPhoto;
    private String biography;
    private GenderEnum gender;
    private LocalDate birthday;
    private boolean isOnline;
    private LocalDateTime createAt;
    private ActiveEnum activeStatus;
    private Role role;
}

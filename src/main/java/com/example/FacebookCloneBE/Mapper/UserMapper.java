package com.example.FacebookCloneBE.Mapper;

import com.example.FacebookCloneBE.DTO.UserDTO.UserDTO;
import com.example.FacebookCloneBE.DTO.UserDTO.UserRegisterDTO;
import com.example.FacebookCloneBE.Model.User;
import com.example.FacebookCloneBE.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.example.FacebookCloneBE.Enum.ActiveEnum.ACTIVE;

@Component
public class UserMapper {

    public static UserDTO toUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setProfilePicture(user.getProfilePicture());
        userDTO.setCoverPhoto(user.getCoverPhoto());
        userDTO.setBiography(user.getBiography());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhone(user.getPhone());
        userDTO.setPassword(user.getPassword());
        userDTO.setGender(user.getGender());
        userDTO.setBirthday(user.getBirthday());
        userDTO.setOnline(user.isOnline());
        userDTO.setCreateAt(user.getCreateAt());
        userDTO.setActiveStatus(user.getActiveStatus());
        userDTO.setRole(user.getRole());
        return userDTO;
    }

    public static User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setProfilePicture(userDTO.getProfilePicture());
        user.setCoverPhoto(userDTO.getCoverPhoto());
        user.setBiography(userDTO.getBiography());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setPassword(userDTO.getPassword());
        user.setGender(userDTO.getGender());
        user.setBirthday(userDTO.getBirthday());
        user.setOnline(userDTO.isOnline());
        user.setCreateAt(userDTO.getCreateAt());
        user.setActiveStatus(userDTO.getActiveStatus());
        user.setRole(userDTO.getRole());
        return user;
    }

    public User toEnTityFromRegister(UserRegisterDTO registerDTO) {
        User user = new User();
        user.setFirstName(registerDTO.getFirstName());
        user.setLastName(registerDTO.getLastName());
        if (registerDTO.getEmailOrPhone() != null) {
            if (registerDTO.getEmailOrPhone().contains("@")) {
                user.setEmail(registerDTO.getEmailOrPhone());
            } else {
                user.setPhone(registerDTO.getEmailOrPhone());
            }
        }
        user.setGender(registerDTO.getGender());
        user.setPassword(registerDTO.getPassword());
        user.setBirthday(registerDTO.getBirthday());
        user.setCreateAt(registerDTO.getCreateAt());
        user.setActiveStatus(ACTIVE);

        return user;
    }
}

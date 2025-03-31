package com.example.FacebookCloneBE.Mapper;

import com.example.FacebookCloneBE.DTO.UserDTO.UserDTO;
import com.example.FacebookCloneBE.Model.User;

public class UserMapper {
    public static UserDTO toUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setProfilePicture(user.getProfilePicture());
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

}

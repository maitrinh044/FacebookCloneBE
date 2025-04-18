package com.example.FacebookCloneBE.Mapper;

import com.example.FacebookCloneBE.DTO.UserDTO.UserDTO;
import com.example.FacebookCloneBE.DTO.UserDTO.UserLoginDTO;
import com.example.FacebookCloneBE.Model.User;
import com.example.FacebookCloneBE.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UserMapper {

    private UserRepository userRepository;

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

    private UserDTO toDTOFromUserLoginDTO(UserLoginDTO userLoginDTO) {
        // Tìm người dùng từ cơ sở dữ liệu qua email hoặc số điện thoại
        Optional<User> existingUser = userRepository.findByEmailOrPhone(userLoginDTO.getEmailOrPhone());

        // Nếu không tìm thấy người dùng
        if (existingUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        // Lấy User từ Optional
        User user = existingUser.get();

        // Chuyển đổi từ User sang UserDTO
        UserDTO userDTO = toUserDTO(user);

        // Trả về UserDTO
        return userDTO;
    }


}

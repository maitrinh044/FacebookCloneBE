package com.example.FacebookCloneBE.Service;

import com.example.FacebookCloneBE.DTO.UserDTO.UserDTO;
import com.example.FacebookCloneBE.Model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {
    public Iterable<UserDTO> getAllUsers();

    public Optional<UserDTO> getUserById(long id);

    public Optional<UserDTO> addUser(UserDTO userDTO);

    public Optional<UserDTO> updateUser(UserDTO userDTO);

    public Iterable<UserDTO> getAllFriends(long userId);

    // Tìm người dùng theo email hoặc số điện thoại
    UserDTO findByEmailOrPhone(String emailOrPhone);
}

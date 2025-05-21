package com.example.FacebookCloneBE.Service;

import com.example.FacebookCloneBE.DTO.UserDTO.UserDTO;
import com.example.FacebookCloneBE.DTO.UserDTO.UserRegisterDTO;
import com.example.FacebookCloneBE.Model.User;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    public Iterable<UserDTO> getAllUsers();

    public Optional<UserDTO> getUserById(long id);

    public Optional<UserDTO> addUser(UserDTO userDTO);

    public Optional<UserDTO> updateUser(UserDTO userDTO);

    public Iterable<UserDTO> getAllFriends(long userId);

    // Tìm người dùng theo email hoặc số điện thoại
    public Optional<UserDTO> findByEmailOrPhone(String emailOrPhone);

    public Optional<UserDTO> addRegisterUser(UserRegisterDTO userRegisterDTO);

    public boolean checkExistingUser(Long userId);

    public void updateOnlineStatus(Long userId, boolean status);

    public List<UserDTO> getOnlineFriends(long userId);

    public List<UserDTO> findByKeyword(String keyword);

    public Optional<UserDTO> controlActive(Long userId);

}

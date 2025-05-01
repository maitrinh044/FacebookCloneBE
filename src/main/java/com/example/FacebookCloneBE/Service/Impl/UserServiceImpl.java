package com.example.FacebookCloneBE.Service.Impl;

import com.example.FacebookCloneBE.DTO.UserDTO.UserDTO;
import com.example.FacebookCloneBE.DTO.UserDTO.UserRegisterDTO;
import com.example.FacebookCloneBE.Mapper.UserMapper;
import com.example.FacebookCloneBE.Model.Role;
import com.example.FacebookCloneBE.Model.User;
import com.example.FacebookCloneBE.Repository.FriendshipRepository;
import com.example.FacebookCloneBE.Repository.RoleRepository;
import com.example.FacebookCloneBE.Repository.UserRepository;
import com.example.FacebookCloneBE.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FriendshipRepository friendshipRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Iterable<UserDTO> getAllUsers() {
        try {
            Iterable<User> userList = userRepository.findAll();
            Iterable<UserDTO> userDTOList = StreamSupport.stream(userList.spliterator(), false)
                    .map(UserMapper::toUserDTO).toList();
            return userDTOList;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public Optional<UserDTO> getUserById(long id) {
        try {
            Optional<User> user = userRepository.findById(id);
            return user.map(UserMapper::toUserDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Optional<UserDTO> addUser(UserDTO userDTO) {
        try {
            Optional<User> existingUser = userRepository.findById(userDTO.getId());
            if (existingUser.isPresent()) {
                System.out.println("user id already exists");
                return null;
            } else {
                User user = UserMapper.toEntity(userDTO);
                User savedUser = userRepository.save(user);
                return Optional.of(UserMapper.toUserDTO(savedUser));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Optional<UserDTO> updateUser(UserDTO userDTO) {
        try {
            Optional<User> existingUser = userRepository.findById(userDTO.getId());
            if (existingUser.isPresent()) {
                User user = UserMapper.toEntity(userDTO);
                User updatedUser = userRepository.save(user);
                return Optional.of(UserMapper.toUserDTO(updatedUser));
            } else {
                System.out.println("user does not exist");
                return Optional.empty();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Iterable<UserDTO> getAllFriends(long userId) {
        try {
            Iterable<User> friendList = friendshipRepository.getAllFriends(userId);
            Iterable<UserDTO> friendListDTO = StreamSupport.stream(friendList.spliterator(), false)
                    .map(UserMapper::toUserDTO).toList();
            return friendListDTO;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    // Tìm người dùng theo email hoặc số điện thoại
    @Override
    public Optional<UserDTO> findByEmailOrPhone(String emailOrPhone) {
        if (emailOrPhone.contains("@")) {
            // Tìm theo email
            User user = userRepository.findByEmailOrPhone(emailOrPhone);
            if (user != null) {
                return Optional.of(UserMapper.toUserDTO(user));
            } else {
                return Optional.empty();
            }
        } else {
            // Tìm theo số điện thoại
            User user = userRepository.findByEmailOrPhone(emailOrPhone);
            if (user != null) {
                return Optional.of(UserMapper.toUserDTO(user));
            } else {
                return Optional.empty();
            }

        }
    }

    @Override
    public Optional<UserDTO> addRegisterUser(UserRegisterDTO userRegisterDTO) {
        try {
            UserMapper userMap = new UserMapper();
            User user = userMap.toEnTityFromRegister(userRegisterDTO);
            Role role = roleRepository.getById(2L);
            user.setRole(role);
            User savedUser = userRepository.save(user);
            if (savedUser != null) {
                return Optional.of(UserMapper.toUserDTO(savedUser));
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean checkExistingUser(Long userId) {
        Iterable<UserDTO> list = getAllUsers();
        for (UserDTO userDTO : list) {
            if (userDTO.getId().equals(userId))
                return true;
        }
        return false;
    }

    @Override
    public void updateOnlineStatus(Long userId, boolean status) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setOnline(status); // Cập nhật trạng thái online
            System.out.println(userId + " is " + status);
            userRepository.save(user);
        }
    }

    @Override
    public List<UserDTO> getOnlineFriends(long userId) {
        Iterable<UserDTO> friends = getAllFriends(userId);
        List<UserDTO> onlineFriends = new ArrayList<UserDTO>();
        for (UserDTO friend : friends) {
            if (friend.isOnline()) {
                onlineFriends.add(friend);
            }
        }
        return onlineFriends;
    }

}

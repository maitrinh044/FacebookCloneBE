package com.example.FacebookCloneBE.Service;

import com.example.FacebookCloneBE.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PresenceService {
    @Autowired
    private UserRepository userRepository;

    public void markOnline(Long userId) {
        userRepository.findById(userId).ifPresent(user -> {
            user.setOnline(true);
            userRepository.save(user);
        });
    }

    public void markOffline(Long userId) {
        userRepository.findById(userId).ifPresent(user -> {
            user.setOnline(false);
            userRepository.save(user);
        });
    }
}

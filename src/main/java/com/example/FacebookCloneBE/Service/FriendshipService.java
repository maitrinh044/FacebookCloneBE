package com.example.FacebookCloneBE.Service;

import com.example.FacebookCloneBE.DTO.FriendshipDTO.FriendshipDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface FriendshipService {
    Iterable<FriendshipDTO> getAllFriendships();

    Iterable<FriendshipDTO> getAllFriendshipRequests();

    Optional<FriendshipDTO> getFriendshipById(long id);

    Optional<FriendshipDTO> addFriendship(FriendshipDTO friendshipDTO);

    Optional<FriendshipDTO> updateFriendship(FriendshipDTO friendshipDTO);

    Iterable<FriendshipDTO> getPendingRequests(long userId);
}
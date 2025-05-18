package com.example.FacebookCloneBE.Mapper;

import com.example.FacebookCloneBE.DTO.FriendshipDTO.FriendshipDTO;
import com.example.FacebookCloneBE.Model.Friendship;

public class FriendshipMapper {
    public static Friendship toEntity(FriendshipDTO friendshipDTO) {
        Friendship friendship = new Friendship();
        friendship.setId(friendshipDTO.getId());
        friendship.setUser1(friendshipDTO.getUser1());
        friendship.setUser2(friendshipDTO.getUser2());
        friendship.setType(friendshipDTO.getType());
        friendship.setCreatedAt(friendshipDTO.getCreatedAt());
        return friendship;
    }

    public static Friendship toEntityPOST(FriendshipDTO friendshipDTO) {
        Friendship friendship = new Friendship();
        // friendship.setId(friendshipDTO.getId());
        friendship.setUser1(friendshipDTO.getUser1());
        friendship.setUser2(friendshipDTO.getUser2());
        friendship.setType(friendshipDTO.getType());
        friendship.setCreatedAt(friendshipDTO.getCreatedAt());
        return friendship;
    }

    public static FriendshipDTO toDTO(Friendship friendship) {
        FriendshipDTO friendshipDTO = new FriendshipDTO();
        friendshipDTO.setId(friendship.getId());
        friendshipDTO.setUser1(friendship.getUser1());
        friendshipDTO.setUser2(friendship.getUser2());
        friendshipDTO.setType(friendship.getType());
        friendshipDTO.setCreatedAt(friendship.getCreatedAt());
        return friendshipDTO;
    }
}

package com.example.FacebookCloneBE.Service.Impl;

import com.example.FacebookCloneBE.DTO.FriendshipDTO.FriendshipDTO;
import com.example.FacebookCloneBE.Enum.ActiveEnum;
import com.example.FacebookCloneBE.Enum.FriendshipTypeEnum;
import com.example.FacebookCloneBE.Mapper.FriendshipMapper;
import com.example.FacebookCloneBE.Model.Friendship;
import com.example.FacebookCloneBE.Repository.FriendshipRepository;
import com.example.FacebookCloneBE.Service.FriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class FriendshipServiceImpl implements FriendshipService {

    @Autowired
    private FriendshipRepository friendshipRepository;

    @Override
    public Iterable<FriendshipDTO> getAllFriendships() {
        try {
            Iterable<Friendship> friendships = friendshipRepository.findAll();
            return StreamSupport.stream(friendships.spliterator(), false)
                    .map(FriendshipMapper::toDTO)
                    .toList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public Iterable<FriendshipDTO> getAllFriendshipRequests() {
        try {
            Iterable<Friendship> requests = friendshipRepository.findAll();
            return StreamSupport.stream(requests.spliterator(), false)
                    .filter(f -> f.getType() == FriendshipTypeEnum.PENDING)
                    .map(FriendshipMapper::toDTO)
                    .toList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public Optional<FriendshipDTO> getFriendshipById(long id) {
        try {
            Optional<Friendship> friendship = friendshipRepository.findById(id);
            return friendship.map(FriendshipMapper::toDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Optional<FriendshipDTO> addFriendship(FriendshipDTO friendshipDTO) {
        try {
            Friendship friendship = FriendshipMapper.toEntityPOST(friendshipDTO);
            // friendship.setCreatedAt(LocalDateTime.now());
            // friendship.setActiveStatus(ActiveEnum.ACTIVE);
            // friendship.setType(friendship.getType() != null ? friendship.getType() :
            // FriendshipTypeEnum.PENDING);
            Friendship savedFriendship = friendshipRepository.save(friendship);
            return Optional.of(FriendshipMapper.toDTO(savedFriendship));
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Optional<FriendshipDTO> updateFriendship(FriendshipDTO friendshipDTO) {
        try {
            Optional<Friendship> existingFriendship = friendshipRepository.findById(friendshipDTO.getId());
            if (existingFriendship.isPresent()) {
                Friendship friendship = FriendshipMapper.toEntity(friendshipDTO);
                friendship.setCreatedAt(existingFriendship.get().getCreatedAt());
                Friendship updatedFriendship = friendshipRepository.save(friendship);
                return Optional.of(FriendshipMapper.toDTO(updatedFriendship));
            } else {
                System.out.println("Friendship does not exist");
                return Optional.empty();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Iterable<FriendshipDTO> getPendingRequests(long userId) {
        try {
            Iterable<Friendship> friendships = friendshipRepository.findAll();
            return StreamSupport.stream(friendships.spliterator(), false)
                    .filter(f -> f.getUser2().getId() == userId && f.getType() == FriendshipTypeEnum.PENDING)
                    .map(FriendshipMapper::toDTO)
                    .toList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
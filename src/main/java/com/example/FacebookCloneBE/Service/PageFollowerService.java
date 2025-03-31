package com.example.FacebookCloneBE.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.FacebookCloneBE.DTO.PageFollwerDTO.PageFollowerDTO;
import com.example.FacebookCloneBE.DTO.UserDTO.UserDTO;

import jakarta.transaction.Transactional;

@Service
public interface PageFollowerService {
    Optional<PageFollowerDTO> createPageFollower(PageFollowerDTO pageFollowerDTO);

    Optional<PageFollowerDTO> getPF(Long pageID, Long userID);

    @Transactional
    Optional<PageFollowerDTO> controlFollowPage(Long pageID, Long userID); // activeStatus = false

    List<UserDTO> getFollowers(Long pageID);

    boolean isUserFollowing(Long pageID, Long userID);
}

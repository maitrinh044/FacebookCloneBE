package com.example.FacebookCloneBE.Service.Impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FacebookCloneBE.DTO.PageFollwerDTO.PageFollowerDTO;
import com.example.FacebookCloneBE.DTO.UserDTO.UserDTO;
import com.example.FacebookCloneBE.Enum.ActiveEnum;
import com.example.FacebookCloneBE.Mapper.PageFollwerMapper;
import com.example.FacebookCloneBE.Mapper.UserMapper;
import com.example.FacebookCloneBE.Model.Page;
import com.example.FacebookCloneBE.Model.PageFollower;
import com.example.FacebookCloneBE.Model.User;
import com.example.FacebookCloneBE.Repository.PageFollowerRepository;
import com.example.FacebookCloneBE.Repository.PageRepository;
import com.example.FacebookCloneBE.Repository.UserRepository;
import com.example.FacebookCloneBE.Service.PageFollowerService;

@Service
public class PageFollowerServiceImpl implements PageFollowerService {
    @Autowired
    private PageFollowerRepository pageFollowerRepository;
    @Autowired
    private PageRepository pageRepository;
    @Autowired
    private UserRepository userRepository;

    public boolean checkExistingPageFollower(Long id) {
        Optional<PageFollower> pf = pageFollowerRepository.findById(id);
        if (pf.isPresent()) {
            System.out.println("This pageFollower is existing!");
            return true;
        }
        return false;
    }

    public boolean checkExistingPageFollowerByUserIDAndPageID(Long pageID, Long userID) {
        Optional<PageFollower> pf = pageFollowerRepository.findByPageIDAndUserID(pageID, userID);
        if (pf.isPresent()) {
            System.out.println("This pageFollower is existing!");
            return true;
        }
        return false;
    }

    @Override
    public Optional<PageFollowerDTO> createPageFollower(PageFollowerDTO pageFollowerDTO) {
        try {
            if (checkExistingPageFollower(pageFollowerDTO.getId())) {
                System.out.println("Can not create a pageFollower");
                return Optional.empty();
            }
            PageFollower pf = pageFollowerRepository.save(PageFollwerMapper.toPageFollowerEntityPOST(pageFollowerDTO));
            return Optional.of(PageFollwerMapper.toPageFollowerDTO(pf));
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Optional<PageFollowerDTO> getPF(Long pageID, Long userID) {
        try {
            return Optional.of(PageFollwerMapper.toPageFollowerDTO(pageFollowerRepository
                    .findByPageIDAndUserID(pageID, userID).get()));
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Optional<PageFollowerDTO> controlFollowPage(Long pageID, Long userID) {
        try {
            if (!checkExistingPageFollowerByUserIDAndPageID(pageID, userID)) {
                System.out.println("Can not unfollow");
                return Optional.empty();
            }
            Optional<PageFollower> pf = pageFollowerRepository.findByPageIDAndUserID(pageID, userID);
            if (pf.get().getActiveStatus() == (ActiveEnum.ACTIVE)) {
                pageFollowerRepository.updateActiveStatus(pageID, userID, ActiveEnum.INACTIVE);
            } else {
                pageFollowerRepository.updateActiveStatus(pageID, userID, ActiveEnum.ACTIVE);
            }
            return Optional.of(PageFollwerMapper.toPageFollowerDTO(pf.get()));
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public List<UserDTO> getFollowers(Long pageID) {
        try {
            Optional<Page> pg = pageRepository.findById(pageID);
            List<PageFollower> listPF = pageFollowerRepository.findByPageID(pg.get());
            List<UserDTO> listUser = new ArrayList<>();
            for (PageFollower pageFollower : listPF) {
                UserDTO user = UserMapper.toUserDTO(pageFollower.getUserID());
                listUser.add(user);
            }
            return listUser;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public boolean isUserFollowing(Long pageID, Long userID) {
        try {
            if (checkExistingPageFollowerByUserIDAndPageID(pageID, userID)) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

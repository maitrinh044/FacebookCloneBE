package com.example.FacebookCloneBE.Mapper;

import com.example.FacebookCloneBE.DTO.PageFollwerDTO.*;
import com.example.FacebookCloneBE.Model.PageFollower;

public class PageFollwerMapper {
    public static PageFollower toPageFollowerEntityPOST(PageFollowerDTO pfDTO) {
        PageFollower pf = new PageFollower();
        // pf.setId(pfDTO.getId());
        pf.setPageID(pfDTO.getPageID());
        pf.setUserID(pfDTO.getUserID());
        pf.setFollowedAt(pfDTO.getFollowedAt());
        pf.setActiveStatus(pf.getActiveStatus());
        return pf;
    }

    public static PageFollower toPageFollowerEntityPUT(PageFollowerDTO pfDTO) {
        PageFollower pf = new PageFollower();
        pf.setId(pfDTO.getId());
        pf.setPageID(pfDTO.getPageID());
        pf.setUserID(pfDTO.getUserID());
        pf.setFollowedAt(pfDTO.getFollowedAt());
        pf.setActiveStatus(pf.getActiveStatus());
        return pf;
    }

    public static PageFollowerDTO toPageFollowerDTO(PageFollower pf) {
        PageFollowerDTO pfDTO = new PageFollowerDTO();
        pfDTO.setId(pf.getId());
        pfDTO.setPageID(pf.getPageID());
        pfDTO.setUserID(pf.getUserID());
        pfDTO.setFollowedAt(pf.getFollowedAt());
        pfDTO.setActiveStatus(pf.getActiveStatus());
        return pfDTO;
    }

}

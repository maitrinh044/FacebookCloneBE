package com.example.FacebookCloneBE.Mapper;

import com.example.FacebookCloneBE.DTO.GroupMemberShipDTO.GroupMemberShipDTO;
import com.example.FacebookCloneBE.Model.GroupMemberShip;

public class GroupMemberShipMapper {
    public static GroupMemberShipDTO toGMSDTO(GroupMemberShip gms) {
        GroupMemberShipDTO gmsDTO = new GroupMemberShipDTO();
        gmsDTO.setId(gms.getId());
        gmsDTO.setGroupID(gms.getGroupID());
        gmsDTO.setUserID(gms.getUserID());
        gmsDTO.setRole(gms.getRole());
        gmsDTO.setJoinAt(gms.getJoinedAt());
        gmsDTO.setActiveStatus(gms.getActiveStatus());
        return gmsDTO;
    }

    public static GroupMemberShip toGMSEntityPost(GroupMemberShipDTO gmsDTO) {
        GroupMemberShip gms = new GroupMemberShip();
        // gms.setId(gmsDTO.getId());
        gms.setGroupID(gmsDTO.getGroupID());
        gms.setUserID(gmsDTO.getUserID());
        gms.setRole(gmsDTO.getRole());
        gms.setJoinedAt(gmsDTO.getJoinAt());
        gms.setActiveStatus(gmsDTO.getActiveStatus());
        return gms;
    }

    public static GroupMemberShip toGMSEntityPut(GroupMemberShipDTO gmsDTO) {
        GroupMemberShip gms = new GroupMemberShip();
        gms.setId(gmsDTO.getId());
        gms.setGroupID(gmsDTO.getGroupID());
        gms.setUserID(gmsDTO.getUserID());
        gms.setRole(gmsDTO.getRole());
        gms.setJoinedAt(gmsDTO.getJoinAt());
        gms.setActiveStatus(gmsDTO.getActiveStatus());
        return gms;
    }
}

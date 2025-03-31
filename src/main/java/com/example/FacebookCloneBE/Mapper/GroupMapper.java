package com.example.FacebookCloneBE.Mapper;

import com.example.FacebookCloneBE.DTO.GroupDTO.GroupDTO;
import com.example.FacebookCloneBE.Model.Group;

public class GroupMapper {
    public static GroupDTO toGroupDTO(Group group) {
        GroupDTO groupDTO = new GroupDTO();
        groupDTO.setGroupID(group.getGroupID());
        groupDTO.setGroupName(group.getGroupName());
        groupDTO.setCreatedAt(group.getCreateAt());
        groupDTO.setCreateBy(group.getCreateBy());
        groupDTO.setActiveStatus(group.getActiveStatus());
        return groupDTO;
    }

    public static Group toGroupEntityPost(GroupDTO groupDTO) {
        Group group = new Group();
        // group.setGroupID(groupDTO.getGroupID());
        group.setGroupName(groupDTO.getGroupName());
        group.setCreateAt(groupDTO.getCreatedAt());
        group.setCreateBy(groupDTO.getCreateBy());
        group.setActiveStatus(groupDTO.getActiveStatus());
        return group;
    }

    public static Group toGroupEntityPut(GroupDTO groupDTO) {
        Group group = new Group();
        group.setGroupID(groupDTO.getGroupID());
        group.setGroupName(groupDTO.getGroupName());
        group.setCreateAt(groupDTO.getCreatedAt());
        group.setCreateBy(groupDTO.getCreateBy());
        group.setActiveStatus(groupDTO.getActiveStatus());
        return group;
    }
}

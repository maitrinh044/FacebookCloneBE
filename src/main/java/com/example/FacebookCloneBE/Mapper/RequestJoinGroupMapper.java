package com.example.FacebookCloneBE.Mapper;

import com.example.FacebookCloneBE.DTO.RequestJoinGroupDTO.RequestJoinGroupDTO;
import com.example.FacebookCloneBE.Model.RequestJoinGroup;

public class RequestJoinGroupMapper {
    // Chuyển từ Entity -> DTO
    public static RequestJoinGroupDTO toRJGDTO(RequestJoinGroup requestJoinGroup) {
        if (requestJoinGroup == null) {
            return null;
        }
        return new RequestJoinGroupDTO(
                requestJoinGroup.getId(),
                requestJoinGroup.getUser(),
                requestJoinGroup.getGroup(),
                requestJoinGroup.getRequestStatus(),
                requestJoinGroup.getRequestDate(),
                requestJoinGroup.getActiveStatus());
    }

    // Chuyển từ DTO -> Entity
    public static RequestJoinGroup toRJGEntity(RequestJoinGroupDTO dto) {
        RequestJoinGroup entity = new RequestJoinGroup();
        entity.setId(dto.getId());
        entity.setUser(dto.getUser());
        entity.setGroup(dto.getGroup());
        entity.setRequestStatus(dto.getRequestStatus());
        entity.setRequestDate(dto.getRequestDate());
        entity.setActiveStatus(dto.getActiveStatus());
        return entity;
    }
}

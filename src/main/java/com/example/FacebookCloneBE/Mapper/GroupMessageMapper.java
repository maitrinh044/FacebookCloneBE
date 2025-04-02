package com.example.FacebookCloneBE.Mapper;

import com.example.FacebookCloneBE.DTO.GroupMessageDTO.GroupMessageDTO;
import com.example.FacebookCloneBE.Model.GroupMessage;

public class GroupMessageMapper {
    public static GroupMessageDTO toDTO(GroupMessage groupMessage) {
        GroupMessageDTO groupMessageDTO = new GroupMessageDTO();
        groupMessageDTO.setId(groupMessage.getId());
        groupMessageDTO.setContent(groupMessage.getContent());
        groupMessageDTO.setChatGroup(groupMessage.getChatGroup());
        groupMessageDTO.setSender(groupMessage.getSender());
        groupMessageDTO.setType(groupMessage.getType());
        groupMessageDTO.setSendAt(groupMessage.getSentAt());
        return groupMessageDTO;
    }

    public static GroupMessage toEntity(GroupMessageDTO groupMessageDTO) {
        GroupMessage groupMessage = new GroupMessage();
        groupMessage.setId(groupMessageDTO.getId());
        groupMessage.setContent(groupMessageDTO.getContent());
        groupMessage.setChatGroup(groupMessageDTO.getChatGroup());
        groupMessage.setSender(groupMessageDTO.getSender());
        groupMessage.setType(groupMessageDTO.getType());
        groupMessage.setSentAt(groupMessageDTO.getSendAt());
        return groupMessage;
    }
}

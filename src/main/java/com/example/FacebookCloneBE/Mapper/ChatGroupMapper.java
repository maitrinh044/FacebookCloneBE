package com.example.FacebookCloneBE.Mapper;

import com.example.FacebookCloneBE.DTO.ChatGroupDTO.ChatGroupDTO;
import com.example.FacebookCloneBE.Model.ChatGroup;

public class ChatGroupMapper {

    public static ChatGroupDTO toChatGroupDTO(ChatGroup chatGroup) {
        return new ChatGroupDTO(
            chatGroup.getId(),
            chatGroup.getGroupName(),
            chatGroup.getCreatedBy(),
            chatGroup.getCreatedAt()
        );
    }

    public static ChatGroup toEntity(ChatGroupDTO chatGroupDTO) {
        ChatGroup chatGroup = new ChatGroup();
        chatGroup.setId(chatGroupDTO.getId());
        chatGroup.setGroupName(chatGroupDTO.getGroupName());
        chatGroup.setCreatedBy(chatGroupDTO.getCreatedBy());
        chatGroup.setCreatedAt(chatGroupDTO.getCreatedAt());
        return chatGroup;
    }
}

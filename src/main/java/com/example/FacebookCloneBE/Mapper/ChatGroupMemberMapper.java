package com.example.FacebookCloneBE.Mapper;

import com.example.FacebookCloneBE.DTO.ChatGroupMemberDTO.ChatGroupMemberDTO;
import com.example.FacebookCloneBE.Model.ChatGroupMember;

public class ChatGroupMemberMapper {
    public static ChatGroupMemberDTO toChatGroupMemberDTO(ChatGroupMember chatGroupMember) {
        return new ChatGroupMemberDTO(
                chatGroupMember.getId(),
                ChatGroupMapper.toChatGroupDTO(chatGroupMember.getChatGroup()),
                UserMapper.toUserDTO(chatGroupMember.getUser()),
                chatGroupMember.getRole(),
                chatGroupMember.getJoinedAt()
        );
    }

    public static ChatGroupMember toEntity(ChatGroupMemberDTO chatGroupMemberDTO) {
        ChatGroupMember chatGroupMember = new ChatGroupMember();
        chatGroupMember.setChatGroup(ChatGroupMapper.toEntity(chatGroupMemberDTO.getChatGroup()));
        chatGroupMember.setUser(UserMapper.toEntity(chatGroupMemberDTO.getUser()));
        chatGroupMember.setRole(chatGroupMemberDTO.getRole());
        chatGroupMember.setJoinedAt(chatGroupMemberDTO.getJoinedAt());
        return chatGroupMember;
    }
}

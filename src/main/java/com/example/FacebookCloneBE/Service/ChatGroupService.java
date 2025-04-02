package com.example.FacebookCloneBE.Service;

import com.example.FacebookCloneBE.DTO.ChatGroupDTO.ChatGroupDTO;

import java.util.List;
import java.util.Optional;

public interface ChatGroupService {
    List<ChatGroupDTO> getAllChatGroups();
    Optional<ChatGroupDTO> getChatGroupById(long groupId);
    ChatGroupDTO createChatGroup(ChatGroupDTO chatGroupDTO);
    ChatGroupDTO updateChatGroup(long groupId, ChatGroupDTO chatGroupDTO);
    boolean deleteChatGroup(long groupId);
}

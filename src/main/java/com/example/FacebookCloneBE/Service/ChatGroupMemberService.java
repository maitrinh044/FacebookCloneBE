package com.example.FacebookCloneBE.Service;

import com.example.FacebookCloneBE.DTO.ChatGroupMemberDTO.ChatGroupMemberDTO;

import java.util.List;
import java.util.Optional;

public interface ChatGroupMemberService {
    List<ChatGroupMemberDTO> getAllChatGroupMembers();
    Optional<ChatGroupMemberDTO> getChatGroupMemberById(long id);
    ChatGroupMemberDTO createChatGroupMember(ChatGroupMemberDTO chatGroupMemberDTO);
    ChatGroupMemberDTO updateChatGroupMember(long id, ChatGroupMemberDTO chatGroupMemberDTO);
    boolean deleteChatGroupMember(long id);
}

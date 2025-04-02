package com.example.FacebookCloneBE.Service.Impl;

import com.example.FacebookCloneBE.DTO.ChatGroupMemberDTO.ChatGroupMemberDTO;
import com.example.FacebookCloneBE.Mapper.ChatGroupMapper;
import com.example.FacebookCloneBE.Mapper.ChatGroupMemberMapper;
import com.example.FacebookCloneBE.Mapper.UserMapper;
import com.example.FacebookCloneBE.Model.ChatGroupMember;
import com.example.FacebookCloneBE.Repository.ChatGroupMemberRepository;
import com.example.FacebookCloneBE.Service.ChatGroupMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChatGroupMemberServiceImpl implements ChatGroupMemberService {

    @Autowired
    private ChatGroupMemberRepository chatGroupMemberRepository;

    @Override
    public List<ChatGroupMemberDTO> getAllChatGroupMembers() {
        List<ChatGroupMember> chatGroupMembers = chatGroupMemberRepository.findAll();
        return chatGroupMembers.stream()
                .map(ChatGroupMemberMapper::toChatGroupMemberDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ChatGroupMemberDTO> getChatGroupMemberById(long id) {
        Optional<ChatGroupMember> chatGroupMember = chatGroupMemberRepository.findById(id);
        return chatGroupMember.map(ChatGroupMemberMapper::toChatGroupMemberDTO);
    }

    @Override
    public ChatGroupMemberDTO createChatGroupMember(ChatGroupMemberDTO chatGroupMemberDTO) {
        ChatGroupMember chatGroupMember = ChatGroupMemberMapper.toEntity(chatGroupMemberDTO);
        ChatGroupMember savedChatGroupMember = chatGroupMemberRepository.save(chatGroupMember);
        return ChatGroupMemberMapper.toChatGroupMemberDTO(savedChatGroupMember);
    }

    @Override
    public ChatGroupMemberDTO updateChatGroupMember(long id, ChatGroupMemberDTO chatGroupMemberDTO) {
        Optional<ChatGroupMember> chatGroupMemberOptional = chatGroupMemberRepository.findById(id);
        if (chatGroupMemberOptional.isPresent()) {
            ChatGroupMember chatGroupMember = chatGroupMemberOptional.get();

            // Kiểm tra xem chatGroup hoặc user có phải là null không trước khi ánh xạ
            if (chatGroupMemberDTO.getChatGroup() != null) {
                chatGroupMember.setChatGroup(ChatGroupMapper.toEntity(chatGroupMemberDTO.getChatGroup()));
            }

            if (chatGroupMemberDTO.getUser() != null) {
                chatGroupMember.setUser(UserMapper.toEntity(chatGroupMemberDTO.getUser()));
            }

            // Cập nhật các thông tin khác
            chatGroupMember.setRole(chatGroupMemberDTO.getRole());
            chatGroupMember.setJoinedAt(chatGroupMemberDTO.getJoinedAt());

            // Lưu lại và trả về DTO đã cập nhật
            ChatGroupMember updatedChatGroupMember = chatGroupMemberRepository.save(chatGroupMember);
            return ChatGroupMemberMapper.toChatGroupMemberDTO(updatedChatGroupMember);
        }
        return null; // Nếu không tìm thấy ChatGroupMember
    }


    @Override
    public boolean deleteChatGroupMember(long id) {
        if (chatGroupMemberRepository.existsById(id)) {
            chatGroupMemberRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

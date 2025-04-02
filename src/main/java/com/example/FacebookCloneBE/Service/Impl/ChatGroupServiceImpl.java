package com.example.FacebookCloneBE.Service.Impl;

import com.example.FacebookCloneBE.DTO.ChatGroupDTO.ChatGroupDTO;
import com.example.FacebookCloneBE.Mapper.ChatGroupMapper;
import com.example.FacebookCloneBE.Model.ChatGroup;
import com.example.FacebookCloneBE.Repository.ChatGroupRepository;
import com.example.FacebookCloneBE.Service.ChatGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChatGroupServiceImpl implements ChatGroupService {

    @Autowired
    private ChatGroupRepository chatGroupRepository;

    @Override
    public List<ChatGroupDTO> getAllChatGroups() {
        List<ChatGroup> chatGroups = chatGroupRepository.findAll();
        return chatGroups.stream()
                .map(ChatGroupMapper::toChatGroupDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ChatGroupDTO> getChatGroupById(long groupId) {
        Optional<ChatGroup> chatGroup = chatGroupRepository.findById(groupId);
        return chatGroup.map(ChatGroupMapper::toChatGroupDTO);
    }

    @Override
    public ChatGroupDTO createChatGroup(ChatGroupDTO chatGroupDTO) {
        ChatGroup chatGroup = ChatGroupMapper.toEntity(chatGroupDTO);
        ChatGroup savedGroup = chatGroupRepository.save(chatGroup);
        return ChatGroupMapper.toChatGroupDTO(savedGroup);
    }

    @Override
    public ChatGroupDTO updateChatGroup(long groupId, ChatGroupDTO chatGroupDTO) {
        Optional<ChatGroup> chatGroupOptional = chatGroupRepository.findById(groupId);
        if (chatGroupOptional.isPresent()) {
            ChatGroup chatGroup = chatGroupOptional.get();
            chatGroup.setGroupName(chatGroupDTO.getGroupName());
            chatGroup.setCreatedBy(chatGroupDTO.getCreatedBy());
            chatGroup.setCreatedAt(chatGroupDTO.getCreatedAt());
            ChatGroup updatedChatGroup = chatGroupRepository.save(chatGroup);
            return ChatGroupMapper.toChatGroupDTO(updatedChatGroup);
        }
        return null; // If not found, return null
    }

    @Override
    public boolean deleteChatGroup(long groupId) {
        if (chatGroupRepository.existsById(groupId)) {
            chatGroupRepository.deleteById(groupId);
            return true;
        }
        return false;
    }
}

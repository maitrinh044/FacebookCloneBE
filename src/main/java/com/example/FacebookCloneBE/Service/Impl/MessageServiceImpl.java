package com.example.FacebookCloneBE.Service.Impl;

import com.example.FacebookCloneBE.DTO.MessageDTO.MessageDTO;
import com.example.FacebookCloneBE.DTO.UserDTO.UserDTO;
import com.example.FacebookCloneBE.Mapper.MessageMapper;
import com.example.FacebookCloneBE.Model.Message;
import com.example.FacebookCloneBE.Repository.MessageRepository;
import com.example.FacebookCloneBE.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.stream.StreamSupport;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public Iterable<MessageDTO> getMessagesForUser(long senderId, long receiverId) {
        try {
            Iterable<Message> messageList = messageRepository.getMessageByUsers(senderId, receiverId);
            Iterable<MessageDTO> messageDTOList = StreamSupport.stream(messageList.spliterator(), false).map(MessageMapper::toMessageDTO).toList();
            return messageDTOList;
        }
        catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}

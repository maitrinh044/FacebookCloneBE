package com.example.FacebookCloneBE.Service;

import com.example.FacebookCloneBE.DTO.MessageDTO.MessageDTO;
import com.example.FacebookCloneBE.DTO.UserDTO.UserDTO;
import org.springframework.stereotype.Service;

@Service
public interface MessageService {
    Iterable<MessageDTO> getMessagesForUser(long senderId, long receiverId);

}


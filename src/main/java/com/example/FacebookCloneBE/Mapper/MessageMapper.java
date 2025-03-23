package com.example.FacebookCloneBE.Mapper;

import com.example.FacebookCloneBE.DTO.MessageDTO.MessageDTO;
import com.example.FacebookCloneBE.DTO.UserDTO.UserDTO;
import com.example.FacebookCloneBE.Model.Message;
import com.example.FacebookCloneBE.Model.User;

public class MessageMapper {
    public static MessageDTO toMessageDTO(Message message) {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setId(message.getId());
        messageDTO.setContent(message.getContent());
        messageDTO.setSenderId(message.getSenderId());
        messageDTO.setReceiverId(message.getReceiverId());
        messageDTO.setType(message.getType());
        messageDTO.setSendAt(message.getSendAt());
        return messageDTO;
    }

    public static Message toEntity(MessageDTO messageDTO) {
        Message message = new Message();
        message.setId(messageDTO.getId());
        message.setContent(messageDTO.getContent());
        message.setSenderId(messageDTO.getSenderId());
        message.setReceiverId(messageDTO.getReceiverId());
        message.setType(messageDTO.getType());
        message.setSendAt(messageDTO.getSendAt());
        return message;
    }
}

package com.example.FacebookCloneBE.DTO.MessageDTO;

import com.example.FacebookCloneBE.Enum.MessageTypeEnum;
import com.example.FacebookCloneBE.Model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO {
    private long id;
    private String content;
    private User senderId;
    private User receiverId;
    private MessageTypeEnum type;
    private LocalDateTime sendAt;
}

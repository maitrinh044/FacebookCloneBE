package com.example.FacebookCloneBE.DTO.GroupMessageDTO;

import com.example.FacebookCloneBE.Enum.MessageTypeEnum;
import com.example.FacebookCloneBE.Model.ChatGroup;
import com.example.FacebookCloneBE.Model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupMessageDTO {
    private Long id;
    private ChatGroup chatGroup;
    private User sender;
    private MessageTypeEnum type;
    private String content;
    private LocalDateTime sendAt;
}

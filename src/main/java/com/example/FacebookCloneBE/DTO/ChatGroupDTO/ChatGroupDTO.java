package com.example.FacebookCloneBE.DTO.ChatGroupDTO;

import com.example.FacebookCloneBE.Model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatGroupDTO {
    private long id;
    private String groupName;
    private User createdBy;
    private Timestamp createdAt;
}

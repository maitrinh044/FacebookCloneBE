package com.example.FacebookCloneBE.DTO.ChatGroupMemberDTO;

import com.example.FacebookCloneBE.DTO.ChatGroupDTO.ChatGroupDTO;
import com.example.FacebookCloneBE.DTO.UserDTO.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatGroupMemberDTO {
    private long id;
    private ChatGroupDTO chatGroup;
    private UserDTO user;
    private String role; // ADMIN, MEMBER
    private Timestamp joinedAt;
}

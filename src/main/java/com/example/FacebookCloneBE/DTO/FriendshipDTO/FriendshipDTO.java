package com.example.FacebookCloneBE.DTO.FriendshipDTO;

import com.example.FacebookCloneBE.Enum.FriendshipTypeEnum;
import com.example.FacebookCloneBE.Model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FriendshipDTO {
    private Long id;
    private User user1;
    private User user2;
    private FriendshipTypeEnum type;
    private LocalDateTime createdAt;
}

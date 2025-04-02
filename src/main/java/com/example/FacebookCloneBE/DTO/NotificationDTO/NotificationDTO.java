package com.example.FacebookCloneBE.DTO.NotificationDTO;

import com.example.FacebookCloneBE.Enum.NotificationTypeEnum;
import com.example.FacebookCloneBE.Model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDTO {
    private long id;
    private User userId;
    private String content;
    private NotificationTypeEnum type;
    private boolean isRead;
    private Timestamp createdAt;
}

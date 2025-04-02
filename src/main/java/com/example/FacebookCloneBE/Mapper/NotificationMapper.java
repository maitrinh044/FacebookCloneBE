package com.example.FacebookCloneBE.Mapper;

import com.example.FacebookCloneBE.DTO.NotificationDTO.NotificationDTO;
import com.example.FacebookCloneBE.Model.Notification;

public class NotificationMapper {
    public static NotificationDTO toNotificationDTO(Notification notification) {
        return new NotificationDTO(
            notification.getId(),
            notification.getUserId(),
            notification.getContent(),
            notification.getType(),
            notification.isRead(),
            notification.getCreatedAt()
        );
    }

    public static Notification toEntity(NotificationDTO notificationDTO) {
        Notification notification = new Notification();
        notification.setId(notificationDTO.getId());
        notification.setUserId(notificationDTO.getUserId());
        notification.setContent(notificationDTO.getContent());
        notification.setType(notificationDTO.getType());
        notification.setRead(notificationDTO.isRead());
        notification.setCreatedAt(notificationDTO.getCreatedAt());
        return notification;
    }
}

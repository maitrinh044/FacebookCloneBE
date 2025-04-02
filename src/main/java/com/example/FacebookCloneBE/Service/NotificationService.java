package com.example.FacebookCloneBE.Service;

import com.example.FacebookCloneBE.DTO.NotificationDTO.NotificationDTO;

import java.util.List;
import java.util.Optional;

public interface NotificationService {
    List<NotificationDTO> getAllNotifications();
    Optional<NotificationDTO> getNotificationById(long notificationId);
    NotificationDTO createNotification(NotificationDTO notificationDTO);
    NotificationDTO updateNotification(long notificationId, NotificationDTO notificationDTO);
    boolean deleteNotification(long notificationId);
}

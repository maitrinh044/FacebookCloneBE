package com.example.FacebookCloneBE.Service.Impl;

import com.example.FacebookCloneBE.DTO.NotificationDTO.NotificationDTO;
import com.example.FacebookCloneBE.Mapper.NotificationMapper;
import com.example.FacebookCloneBE.Model.Notification;
import com.example.FacebookCloneBE.Repository.NotificationRepository;
import com.example.FacebookCloneBE.Service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public List<NotificationDTO> getAllNotifications() {
        List<Notification> notifications = notificationRepository.findAll();
        return notifications.stream()
                .map(NotificationMapper::toNotificationDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<NotificationDTO> getNotificationById(long notificationId) {
        Optional<Notification> notification = notificationRepository.findById(notificationId);
        return notification.map(NotificationMapper::toNotificationDTO);
    }

    @Override
    public NotificationDTO createNotification(NotificationDTO notificationDTO) {
        Notification notification = NotificationMapper.toEntity(notificationDTO);
        Notification savedNotification = notificationRepository.save(notification);
        return NotificationMapper.toNotificationDTO(savedNotification);
    }

    @Override
    public NotificationDTO updateNotification(long notificationId, NotificationDTO notificationDTO) {
        Optional<Notification> notificationOptional = notificationRepository.findById(notificationId);
        if (notificationOptional.isPresent()) {
            Notification notification = notificationOptional.get();
            notification.setContent(notificationDTO.getContent());
            notification.setType(notificationDTO.getType());
            notification.setRead(notificationDTO.isRead());
            notification.setCreatedAt(notificationDTO.getCreatedAt());
            Notification updatedNotification = notificationRepository.save(notification);
            return NotificationMapper.toNotificationDTO(updatedNotification);
        }
        return null;  // Nếu không tìm thấy notification, trả về null
    }

    @Override
    public boolean deleteNotification(long notificationId) {
        if (notificationRepository.existsById(notificationId)) {
            notificationRepository.deleteById(notificationId);
            return true;
        }
        return false;
    }
}

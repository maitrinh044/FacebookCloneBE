package com.example.FacebookCloneBE.Controller;

import com.example.FacebookCloneBE.DTO.NotificationDTO.NotificationDTO;
import com.example.FacebookCloneBE.Reponse.ResponseData;
import com.example.FacebookCloneBE.Service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    private final ResponseData responseData = new ResponseData();

    // Lấy tất cả notification
    @GetMapping("/getAllNotifications")
    public ResponseEntity<ResponseData> getAllNotifications() {
        try {
            List<NotificationDTO> notifications = notificationService.getAllNotifications();
            if (!notifications.isEmpty()) {
                responseData.setData(notifications);
                responseData.setStatusCode(200);
                responseData.setMessage("Success");
                return new ResponseEntity<>(responseData, HttpStatus.OK);
            } else {
                responseData.setData(null);
                responseData.setStatusCode(204);
                responseData.setMessage("No notifications found");
                return new ResponseEntity<>(responseData, HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseData.setData(null);
            responseData.setStatusCode(500);
            responseData.setMessage("Error fetching notifications");
            return new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Lấy notification theo id
    @GetMapping("/getNotificationById/{id}")
    public ResponseEntity<ResponseData> getNotificationById(@PathVariable long id) {
        try {
            Optional<NotificationDTO> notificationDTO = notificationService.getNotificationById(id);
            if (notificationDTO.isPresent()) {
                responseData.setData(notificationDTO.get());
                responseData.setStatusCode(200);
                responseData.setMessage("Success");
                return new ResponseEntity<>(responseData, HttpStatus.OK);
            } else {
                responseData.setData(null);
                responseData.setStatusCode(404);
                responseData.setMessage("Notification not found");
                return new ResponseEntity<>(responseData, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseData.setData(null);
            responseData.setStatusCode(500);
            responseData.setMessage("Error fetching notification");
            return new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Tạo notification mới
    @PostMapping("/createNotification")
    public ResponseEntity<ResponseData> createNotification(@RequestBody NotificationDTO notificationDTO) {
        try {
            NotificationDTO createdNotification = notificationService.createNotification(notificationDTO);
            responseData.setData(createdNotification);
            responseData.setStatusCode(201);
            responseData.setMessage("Notification created successfully");
            return new ResponseEntity<>(responseData, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            responseData.setData(null);
            responseData.setStatusCode(500);
            responseData.setMessage("Error creating notification");
            return new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Cập nhật notification
    @PutMapping("/updateNotification/{id}")
    public ResponseEntity<ResponseData> updateNotification(@PathVariable long id, @RequestBody NotificationDTO notificationDTO) {
        try {
            NotificationDTO updatedNotification = notificationService.updateNotification(id, notificationDTO);
            if (updatedNotification != null) {
                responseData.setData(updatedNotification);
                responseData.setStatusCode(200);
                responseData.setMessage("Notification updated successfully");
                return new ResponseEntity<>(responseData, HttpStatus.OK);
            } else {
                responseData.setData(null);
                responseData.setStatusCode(404);
                responseData.setMessage("Notification not found");
                return new ResponseEntity<>(responseData, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseData.setData(null);
            responseData.setStatusCode(500);
            responseData.setMessage("Error updating notification");
            return new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Xóa notification
    @DeleteMapping("/deleteNotification/{id}")
    public ResponseEntity<ResponseData> deleteNotification(@PathVariable long id) {
        try {
            boolean isDeleted = notificationService.deleteNotification(id);
            if (isDeleted) {
                responseData.setData(null);
                responseData.setStatusCode(200);
                responseData.setMessage("Notification deleted successfully");
                return new ResponseEntity<>(responseData, HttpStatus.OK);
            } else {
                responseData.setData(null);
                responseData.setStatusCode(404);
                responseData.setMessage("Notification not found");
                return new ResponseEntity<>(responseData, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseData.setData(null);
            responseData.setStatusCode(500);
            responseData.setMessage("Error deleting notification");
            return new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

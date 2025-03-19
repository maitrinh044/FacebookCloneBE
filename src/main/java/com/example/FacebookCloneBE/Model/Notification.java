package com.example.FacebookCloneBE.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@Table(name = "notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Người nhận thông báo

    @Column(nullable = false)
    private String type; // Loại thông báo (POST, COMMENT, FRIEND_REQUEST)

    @Column(nullable = false)
    private String content; // Nội dung thông báo

    @Column(nullable = false)
    private boolean isRead = false; // Trạng thái đã đọc

    @Column
    private Timestamp createdAt;
}

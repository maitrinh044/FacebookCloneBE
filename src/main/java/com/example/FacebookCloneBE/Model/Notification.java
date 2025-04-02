package com.example.FacebookCloneBE.Model;

import com.example.FacebookCloneBE.Enum.ActiveEnum;
import com.example.FacebookCloneBE.Enum.NotificationTypeEnum;
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
    @JoinColumn(name = "user_id")
    private User userId;
    @Column
    private String content;
    @Column
    @Enumerated(EnumType.STRING)
    private NotificationTypeEnum type;
    @Column
    private boolean isRead;
    @Column
    private Timestamp createdAt;
    @Column(name = "active_status")
    @Enumerated(EnumType.STRING)
    private ActiveEnum activeStatus;
}

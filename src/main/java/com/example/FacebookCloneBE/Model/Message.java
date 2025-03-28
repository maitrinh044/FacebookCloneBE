package com.example.FacebookCloneBE.Model;

import com.example.FacebookCloneBE.Enum.ActiveEnum;
import com.example.FacebookCloneBE.Enum.MessageTypeEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String content;
    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User senderId;
    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private User receiverId;
    @Column
    @Enumerated(EnumType.STRING)
    private MessageTypeEnum type;
    @Column
    private LocalDateTime sendAt;
    @Column(name = "active_status")
    @Enumerated(EnumType.STRING)
    private ActiveEnum activeStatus;
}

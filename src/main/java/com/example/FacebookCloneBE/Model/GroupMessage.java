package com.example.FacebookCloneBE.Model;

import com.example.FacebookCloneBE.Enum.ActiveEnum;
import com.example.FacebookCloneBE.Enum.MessageTypeEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@Table(name = "group_message")
public class GroupMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "chat_group_id")
    private ChatGroup chatGroup;
    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;
    @Column
    @Enumerated(EnumType.STRING)
    private MessageTypeEnum type;
    @Column
    private String content;
    @Column
    private Timestamp sentAt;
    @Column(name = "active_status")
    @Enumerated(EnumType.STRING)
    private ActiveEnum activeStatus;
}

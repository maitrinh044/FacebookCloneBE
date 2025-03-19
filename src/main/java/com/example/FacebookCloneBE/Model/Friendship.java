package com.example.FacebookCloneBE.Model;

import com.example.FacebookCloneBE.Enum.FriendshipTypeEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@Table(name = "friend_ship")
public class Friendship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "user_1")
    private User user1;
    @ManyToOne
    @JoinColumn(name = "user_2")
    private User user2;
    @Column
    @Enumerated(EnumType.STRING)
    private FriendshipTypeEnum type;
    @Column
    private Timestamp createdAt;
}

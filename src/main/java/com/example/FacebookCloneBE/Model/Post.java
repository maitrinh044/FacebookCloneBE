package com.example.FacebookCloneBE.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

import com.example.FacebookCloneBE.Enum.ActiveEnum;

@Entity
@Data
@NoArgsConstructor
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String content;

    @JoinColumn(name = "group_id", nullable = true)
    @ManyToOne
    private Group group;

    @JoinColumn(name = "page_id", nullable = true)
    @ManyToOne
    private Page page;

    private String imageUrl;

    @Column(nullable = false, updatable = false)
    private Timestamp createdAt = new Timestamp(System.currentTimeMillis());

    @Column(name = "active_status")
    @Enumerated(EnumType.STRING)
    private ActiveEnum activeStatus;
}

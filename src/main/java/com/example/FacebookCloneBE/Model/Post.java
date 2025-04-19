package com.example.FacebookCloneBE.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @JsonIgnoreProperties("posts")
    private User user;

    @ManyToOne
    @JoinColumn(name = "page_id", nullable = true)
    private Page page;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = true)
    private Group group;

    @Column(nullable = false)
    private String content;

    private String imageUrl;

    @Column(nullable = false, updatable = false)
    private Timestamp createdAt = new Timestamp(System.currentTimeMillis());

    @Column(name = "active_status")
    @Enumerated(EnumType.STRING)
    private ActiveEnum activeStatus;
}

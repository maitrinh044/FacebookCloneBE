package com.example.FacebookCloneBE.DTO.CommentDTO;

import com.example.FacebookCloneBE.Enum.ActiveEnum;
import java.sql.Timestamp;

public class CommentDTO {
    private long id;
    private long userId;
    private long postId;
    private String content;
    private Timestamp createdAt;
    private ActiveEnum activeStatus;

    // Constructors, Getters and Setters
    public CommentDTO() {
    }

    public CommentDTO(long id, long userId, long postId, String content,
            Timestamp createdAt, ActiveEnum activeStatus) {
        this.id = id;
        this.userId = userId;
        this.postId = postId;
        this.content = content;
        this.createdAt = createdAt;
        this.activeStatus = activeStatus;
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public ActiveEnum getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(ActiveEnum activeStatus) {
        this.activeStatus = activeStatus;
    }
}
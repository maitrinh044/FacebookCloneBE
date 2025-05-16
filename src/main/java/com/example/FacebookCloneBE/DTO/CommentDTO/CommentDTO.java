package com.example.FacebookCloneBE.DTO.CommentDTO;

import com.example.FacebookCloneBE.Enum.ActiveEnum;
import java.sql.Timestamp;
import java.util.List;

public class CommentDTO {
    private long id;
    private long userId;
    private long postId;
    private Long parentCommentId; // Thêm trường này, sử dụng Long để hỗ trợ null
    private String content;
    private Timestamp createdAt;
    private ActiveEnum activeStatus;
    private List<CommentDTO> replies; // Thêm danh sách replies

    // Constructors
    public CommentDTO() {
    }

    public CommentDTO(long id, long userId, long postId, Long parentCommentId, String content,
            Timestamp createdAt, ActiveEnum activeStatus) {
        this.id = id;
        this.userId = userId;
        this.postId = postId;
        this.parentCommentId = parentCommentId;
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

    public Long getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(Long parentCommentId) {
        this.parentCommentId = parentCommentId;
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

    public List<CommentDTO> getReplies() {
        return replies;
    }

    public void setReplies(List<CommentDTO> replies) {
        this.replies = replies;
    }
}
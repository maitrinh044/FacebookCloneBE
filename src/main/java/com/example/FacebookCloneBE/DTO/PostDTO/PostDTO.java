package com.example.FacebookCloneBE.DTO.PostDTO;

import com.example.FacebookCloneBE.Enum.ActiveEnum;
import java.sql.Timestamp;

public class PostDTO {
    private Long id;
    private Long userId;
    private Long pageId;
    private Long groupId;
    private String content;
    private String imageUrl;
    private Timestamp createdAt;
    private ActiveEnum activeStatus;

    // Constructors
    public PostDTO() {
    }

    public PostDTO(Long id, Long userId, Long pageId, Long groupId, String content, 
                  String imageUrl, Timestamp createdAt, ActiveEnum activeStatus) {
        this.id = id;
        this.userId = userId;
        this.pageId = pageId;
        this.groupId = groupId;
        this.content = content;
        this.imageUrl = imageUrl;
        this.createdAt = createdAt;
        this.activeStatus = activeStatus;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPageId() {
        return pageId;
    }

    public void setPageId(Long pageId) {
        this.pageId = pageId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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
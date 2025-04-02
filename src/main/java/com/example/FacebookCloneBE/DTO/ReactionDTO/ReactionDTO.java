package com.example.FacebookCloneBE.DTO.ReactionDTO;

import com.example.FacebookCloneBE.Enum.ActiveEnum;
import com.example.FacebookCloneBE.Enum.ReactionType;
import com.example.FacebookCloneBE.Enum.TargetType;
import java.sql.Timestamp;

public class ReactionDTO {
    private long id;
    private ReactionType type; // Sử dụng enum ReactionType
    private long userId;
    private TargetType targetType;
    private long targetId;
    private Timestamp createdAt;
    private ActiveEnum activeStatus;

    // Constructors
    public ReactionDTO() {}

    public ReactionDTO(long id, ReactionType type, long userId, TargetType targetType, 
                      long targetId, Timestamp createdAt, ActiveEnum activeStatus) {
        this.id = id;
        this.type = type;
        this.userId = userId;
        this.targetType = targetType;
        this.targetId = targetId;
        this.createdAt = createdAt;
        this.activeStatus = activeStatus;
    }

    // Getters and Setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    
    public ReactionType getType() { return type; }
    public void setType(ReactionType type) { this.type = type; }
    
    public long getUserId() { return userId; }
    public void setUserId(long userId) { this.userId = userId; }
    
    public TargetType getTargetType() { return targetType; }
    public void setTargetType(TargetType targetType) { this.targetType = targetType; }
    
    public long getTargetId() { return targetId; }
    public void setTargetId(long targetId) { this.targetId = targetId; }
    
    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
    
    public ActiveEnum getActiveStatus() { return activeStatus; }
    public void setActiveStatus(ActiveEnum activeStatus) { this.activeStatus = activeStatus; }
}

package com.example.FacebookCloneBE.DTO.ReactionDTO;

import com.example.FacebookCloneBE.Enum.ActiveEnum;
import com.example.FacebookCloneBE.Enum.TargetType;
import java.sql.Timestamp;

public class EnhancedReactionDTO {
    private long id;
    private String reactionType; // Tên của ReactionType (LIKE, LOVE,...)
    private String emoji; // Biểu tượng cảm xúc
    private String label; // Nhãn hiển thị
    private long userId;
    private TargetType targetType;
    private long targetId;
    private Timestamp createdAt;
    private ActiveEnum activeStatus;

    // Constructor
    public EnhancedReactionDTO(long id, String reactionType, String emoji, String label, 
                              long userId, TargetType targetType, long targetId, 
                              Timestamp createdAt, ActiveEnum activeStatus) {
        this.id = id;
        this.reactionType = reactionType;
        this.emoji = emoji;
        this.label = label;
        this.userId = userId;
        this.targetType = targetType;
        this.targetId = targetId;
        this.createdAt = createdAt;
        this.activeStatus = activeStatus;
    }

    // Getters and Setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getReactionType() { return reactionType; }
    public void setReactionType(String reactionType) { this.reactionType = reactionType; }

    public String getEmoji() { return emoji; }
    public void setEmoji(String emoji) { this.emoji = emoji; }

    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }

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

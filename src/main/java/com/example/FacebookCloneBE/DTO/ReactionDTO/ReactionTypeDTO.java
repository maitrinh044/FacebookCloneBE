package com.example.FacebookCloneBE.DTO.ReactionDTO;

public class ReactionTypeDTO {
    private String id;
    private String emoji;
    private String label;

    public ReactionTypeDTO(String id, String emoji, String label) {
        this.id = id;
        this.emoji = emoji;
        this.label = label;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getEmoji() { return emoji; }
    public void setEmoji(String emoji) { this.emoji = emoji; }

    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }
}
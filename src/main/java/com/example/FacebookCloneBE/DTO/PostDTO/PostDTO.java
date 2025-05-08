package com.example.FacebookCloneBE.DTO.PostDTO;

import com.example.FacebookCloneBE.Enum.ActiveEnum;
import com.example.FacebookCloneBE.Model.Group;
import com.example.FacebookCloneBE.Model.Page;
import com.example.FacebookCloneBE.Model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
    private Long id;
    private User userId;
    private Page pageId;
    private Group groupId;
    private String content;
    private String imageUrl;
    private Timestamp createdAt;
    private ActiveEnum activeStatus;
    private Long originalPostId; // Thêm trường này để lưu bài viết gốc
}
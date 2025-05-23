package com.example.FacebookCloneBE.DTO.CommentDTO;

import java.sql.Timestamp;
import java.util.List;

import com.example.FacebookCloneBE.Enum.ActiveEnum;
import com.example.FacebookCloneBE.Model.Post;
import com.example.FacebookCloneBE.Model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO_Data {
    private long id;
    private User userId;
    private Post postId;
    private Long parentCommentId; // Thêm trường này
    private String content;
    private Timestamp createdAt;
    private ActiveEnum activeStatus;
    private List<CommentDTO_Data> replies; // Thêm danh sách replies
}
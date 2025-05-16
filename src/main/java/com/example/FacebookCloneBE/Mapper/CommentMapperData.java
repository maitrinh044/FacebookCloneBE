package com.example.FacebookCloneBE.Mapper;

import com.example.FacebookCloneBE.DTO.CommentDTO.CommentDTO_Data;
import com.example.FacebookCloneBE.Model.Comment;

public class CommentMapperData {
    public static CommentDTO_Data toDTO(Comment cmt) {
        CommentDTO_Data cmtData = new CommentDTO_Data();
        cmtData.setId(cmt.getId());
        cmtData.setActiveStatus(cmt.getActiveStatus());
        cmtData.setContent(cmt.getContent());
        cmtData.setCreatedAt(cmt.getCreatedAt());
        cmtData.setPostId(cmt.getPost());
        cmtData.setUserId(cmt.getUser());
        cmtData.setParentCommentId(cmt.getParentComment() != null ? cmt.getParentComment().getId() : null); // Thêm ánh xạ parentCommentId
        // Lưu ý: replies sẽ được set trong CommentService, không ánh xạ ở đây
        return cmtData;
    }

    public static Comment toEntity(CommentDTO_Data cmtData) {
        Comment cmt = new Comment();
        cmt.setActiveStatus(cmtData.getActiveStatus());
        cmt.setContent(cmtData.getContent());
        cmt.setCreatedAt(cmtData.getCreatedAt());
        cmt.setId(cmtData.getId());
        cmt.setPost(cmtData.getPostId());
        cmt.setUser(cmtData.getUserId());
        if (cmtData.getParentCommentId() != null) {
            Comment parentComment = new Comment();
            parentComment.setId(cmtData.getParentCommentId());
            cmt.setParentComment(parentComment);
        }
        return cmt;
    }
}
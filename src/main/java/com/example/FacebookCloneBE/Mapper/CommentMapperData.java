package com.example.FacebookCloneBE.Mapper;

import com.example.FacebookCloneBE.DTO.CommentDTO.CommentDTO;
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
        return cmt;
    }
}

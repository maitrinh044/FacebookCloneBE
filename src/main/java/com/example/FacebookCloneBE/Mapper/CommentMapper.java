package com.example.FacebookCloneBE.Mapper;

import com.example.FacebookCloneBE.DTO.CommentDTO.CommentDTO;
import com.example.FacebookCloneBE.Model.Comment;
import com.example.FacebookCloneBE.Model.User;
import com.example.FacebookCloneBE.Model.Post;
import com.example.FacebookCloneBE.Repository.UserRepository;
import com.example.FacebookCloneBE.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {
    @Autowired private UserRepository userRepository;
    @Autowired private PostRepository postRepository;

    public CommentDTO toDTO(Comment comment) {
        if (comment == null) return null;
        
        return new CommentDTO(
            comment.getId(),
            comment.getUser().getId(),
            comment.getPost().getId(),
            comment.getContent(),
            comment.getCreatedAt(),
            comment.getActiveStatus()
        );
    }

    public Comment toEntity(CommentDTO commentDTO) {
        if (commentDTO == null) return null;
        
        Comment comment = new Comment();
        comment.setId(commentDTO.getId());
        comment.setUser(userRepository.findById(commentDTO.getUserId()).orElse(null));
        comment.setPost(postRepository.findById(commentDTO.getPostId()).orElse(null));
        comment.setContent(commentDTO.getContent());
        comment.setActiveStatus(commentDTO.getActiveStatus());
        
        return comment;
    }
}
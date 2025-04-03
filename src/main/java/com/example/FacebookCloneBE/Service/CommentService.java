package com.example.FacebookCloneBE.Service;

import com.example.FacebookCloneBE.DTO.CommentDTO.CommentDTO;
import com.example.FacebookCloneBE.Mapper.CommentMapper;
import com.example.FacebookCloneBE.Model.Comment;
import com.example.FacebookCloneBE.Repository.CommentRepository;
import com.example.FacebookCloneBE.Enum.ActiveEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired private CommentRepository commentRepository;
    @Autowired private CommentMapper commentMapper;

    public List<CommentDTO> getCommentsByPost(long postId) {
        return commentRepository.findByPostId(postId).stream()
                .map(commentMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CommentDTO createComment(CommentDTO commentDTO) {
        Comment comment = commentMapper.toEntity(commentDTO);
        comment.setActiveStatus(ActiveEnum.ACTIVE);
        return commentMapper.toDTO(commentRepository.save(comment));
    }

    public Optional<CommentDTO> updateComment(long id, CommentDTO commentDTO) {
        return commentRepository.findById(id)
                .map(existing -> {
                    existing.setContent(commentDTO.getContent());
                    return commentMapper.toDTO(commentRepository.save(existing));
                });
    }

    public boolean deleteComment(long id) {
        if (commentRepository.existsById(id)) {
            commentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
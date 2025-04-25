package com.example.FacebookCloneBE.Service;

import com.example.FacebookCloneBE.DTO.CommentDTO.CommentDTO;
import com.example.FacebookCloneBE.DTO.CommentDTO.CommentDTO_Data;
import com.example.FacebookCloneBE.Mapper.CommentMapper;
import com.example.FacebookCloneBE.Mapper.CommentMapperData;
import com.example.FacebookCloneBE.Mapper.GroupMapper;
import com.example.FacebookCloneBE.Model.Comment;
import com.example.FacebookCloneBE.Model.Group;
import com.example.FacebookCloneBE.Repository.CommentRepository;
import com.example.FacebookCloneBE.Enum.ActiveEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private CommentMapper commentMapper;

    public List<CommentDTO> getCommentsByPost(long postId) {
        return commentRepository.findByPostId(postId).stream()
                .map(commentMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<CommentDTO_Data> getCommentByPost(long postid) {
        try {
            List<Comment> cmt = commentRepository.findByPostId(postid);
            System.out.println("List comment get by postId: ");
            for (Comment comment : cmt) {
                System.out.println(comment.getContent());
            }
            return StreamSupport.stream(cmt.spliterator(), false).map(CommentMapperData::toDTO).toList();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error while get group by id: " + e.getMessage());
            return Collections.EMPTY_LIST;
        }
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
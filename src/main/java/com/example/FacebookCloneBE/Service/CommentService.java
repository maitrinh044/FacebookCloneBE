package com.example.FacebookCloneBE.Service;

import com.example.FacebookCloneBE.DTO.CommentDTO.CommentDTO;
import com.example.FacebookCloneBE.DTO.CommentDTO.CommentDTO_Data;
import com.example.FacebookCloneBE.Mapper.CommentMapper;
import com.example.FacebookCloneBE.Mapper.CommentMapperData;
import com.example.FacebookCloneBE.Model.Comment;
import com.example.FacebookCloneBE.Repository.CommentRepository;
import com.example.FacebookCloneBE.Enum.ActiveEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private CommentMapper commentMapper;

    public List<CommentDTO> getCommentsByPost(long postId) {
        List<Comment> comments = commentRepository.findByPostIdAndActiveStatus(postId, ActiveEnum.ACTIVE);
        List<CommentDTO> commentDTOs = new ArrayList<>();

        // Lấy bình luận cấp cao nhất (parentCommentId = null)
        for (Comment comment : comments) {
            if (comment.getParentComment() == null) {
                CommentDTO dto = commentMapper.toDTO(comment);
                // Lấy replies cho bình luận
                dto.setReplies(getReplies(comment.getId()));
                commentDTOs.add(dto);
            }
        }
        return commentDTOs;
    }

    public List<CommentDTO> getReplies(long parentCommentId) {
        List<Comment> replies = commentRepository.findByParentCommentIdAndActiveStatus(parentCommentId,
                ActiveEnum.ACTIVE); // Sửa lỗi: đổi "find dane" thành "findByParentCommentIdAndActiveStatus"
        List<CommentDTO> replyDTOs = new ArrayList<>();
        for (Comment reply : replies) {
            CommentDTO dto = commentMapper.toDTO(reply);
            // Đệ quy để lấy replies của replies (nếu cần hỗ trợ nhiều cấp)
            dto.setReplies(getReplies(reply.getId()));
            replyDTOs.add(dto);
        }
        return replyDTOs;
    }

    public List<CommentDTO_Data> getCommentByPost(long postId) {
        try {
            List<Comment> comments = commentRepository.findByPostIdAndActiveStatus(postId, ActiveEnum.ACTIVE);
            List<CommentDTO_Data> commentDTOs = new ArrayList<>();

            // Lấy bình luận cấp cao nhất
            for (Comment comment : comments) {
                if (comment.getParentComment() == null) {
                    CommentDTO_Data dto = CommentMapperData.toDTO(comment);
                    // Lấy replies
                    dto.setReplies(getRepliesData(comment.getId()));
                    commentDTOs.add(dto);
                }
            }
            return commentDTOs;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error while getting comments by post: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    private List<CommentDTO_Data> getRepliesData(long parentCommentId) {
        List<Comment> replies = commentRepository.findByParentCommentIdAndActiveStatus(parentCommentId,
                ActiveEnum.ACTIVE);
        List<CommentDTO_Data> replyDTOs = new ArrayList<>();
        for (Comment reply : replies) {
            CommentDTO_Data dto = CommentMapperData.toDTO(reply);
            dto.setReplies(getRepliesData(reply.getId())); // Đệ quy
            replyDTOs.add(dto);
        }
        return replyDTOs;
    }

    public CommentDTO createComment(CommentDTO commentDTO) {
        // Kiểm tra parentCommentId hợp lệ
        if (commentDTO.getParentCommentId() != null) {
            if (!commentRepository.existsById(commentDTO.getParentCommentId())) {
                throw new IllegalArgumentException("Parent comment not found");
            }
        }

        Comment comment = commentMapper.toEntity(commentDTO);
        comment.setActiveStatus(ActiveEnum.ACTIVE);
        Comment saved = commentRepository.save(comment);
        return commentMapper.toDTO(saved);
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
            // Xóa tất cả replies trước
            List<Comment> replies = commentRepository.findByParentCommentIdAndActiveStatus(id, ActiveEnum.ACTIVE);
            for (Comment reply : replies) {
                commentRepository.deleteById(reply.getId());
            }
            commentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<CommentDTO_Data> controlActiveStatus(Long id) {
        Optional<Comment> cmt = commentRepository.findById(id);
        if (cmt.isPresent()) {
            cmt.get().setActiveStatus(
                    cmt.get().getActiveStatus().equals(ActiveEnum.ACTIVE) ? ActiveEnum.INACTIVE : ActiveEnum.ACTIVE);
            Comment updateCmt = commentRepository.save(cmt.get());
            return Optional.of(CommentMapperData.toDTO(updateCmt));
        }
        return Optional.empty();
    }
}
package com.example.FacebookCloneBE.Repository;

import com.example.FacebookCloneBE.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(long postId);
    List<Comment> findByUserId(long userId);
    List<Comment> findByPostIdAndActiveStatus(long postId, ActiveEnum activeStatus);
    long countByPostId(long postId);
}
package com.example.FacebookCloneBE.Repository;

import com.example.FacebookCloneBE.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    // Tìm tất cả bài viết của một user
    @Query("SELECT p FROM Post p WHERE p.user.id = :userId AND activeStatus = 'ACTIVE' ORDER BY p.createdAt DESC")
    List<Post> findByUserId(@Param("userId") Long userId);

    // Tìm tất cả bài viết của một page
    @Query("SELECT p FROM Post p WHERE p.page.id = :pageId ORDER BY p.createdAt DESC")
    List<Post> findByPageId(@Param("pageId") Long pageId);

    // Tìm tất cả bài viết của một group
    @Query("SELECT p FROM Post p WHERE p.group.id = :groupId ORDER BY p.createdAt DESC")
    List<Post> findByGroupId(@Param("groupId") Long groupId);

    // Tìm bài viết theo trạng thái active
    List<Post> findByActiveStatus(String activeStatus);

    // Tìm bài viết chứa từ khóa trong nội dung
    @Query("SELECT p FROM Post p WHERE p.content LIKE %:keyword% ORDER BY p.createdAt DESC")
    List<Post> findByContentContaining(@Param("keyword") String keyword);

    // Tìm bài viết mới nhất (có thể giới hạn số lượng)
    @Query("SELECT p FROM Post p ORDER BY p.createdAt DESC")
    List<Post> findLatestPosts();

    // Đếm số bài viết của một user
    @Query("SELECT COUNT(p) FROM Post p WHERE p.user.id = :userId")
    Long countByUserId(@Param("userId") Long userId);

    // Tìm bài viết theo user và trạng thái active
    @Query("SELECT p FROM Post p WHERE p.user.id = :userId AND p.activeStatus = :activeStatus ORDER BY p.createdAt DESC")
    List<Post> findByUserIdAndActiveStatus(@Param("userId") Long userId, @Param("activeStatus") String activeStatus);
}
package com.example.FacebookCloneBE.Mapper;

import com.example.FacebookCloneBE.DTO.PostDTO.PostDTO;
import com.example.FacebookCloneBE.Model.Post;
import com.example.FacebookCloneBE.Model.User;
import com.example.FacebookCloneBE.Model.Page;
import com.example.FacebookCloneBE.Model.Group;
import com.example.FacebookCloneBE.Repository.UserRepository;
import com.example.FacebookCloneBE.Repository.PageRepository;
import com.example.FacebookCloneBE.Repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PageRepository pageRepository;

    @Autowired
    private GroupRepository groupRepository;

    // Convert từ Post entity sang PostDTO
    public PostDTO toDTO(Post post) {
        if (post == null) {
            return null;
        }

        PostDTO dto = new PostDTO();
        dto.setId(post.getId());
        dto.setUserId(post.getUser() != null ? post.getUser().getId() : null);
        dto.setPageId(post.getPage() != null ? post.getPage().getPageID() : null);
        dto.setGroupId(post.getGroup() != null ? post.getGroup().getGroupID() : null);
        dto.setContent(post.getContent());
        dto.setImageUrl(post.getImageUrl());
        dto.setCreatedAt(post.getCreatedAt());
        dto.setActiveStatus(post.getActiveStatus());
        
        return dto;
    }

    // Convert từ PostDTO sang Post entity
    public Post toEntity(PostDTO postDTO) {
        if (postDTO == null) {
            return null;
        }

        Post post = new Post();
        post.setId(postDTO.getId());
        
        // Set user
        if (postDTO.getUserId() != null) {
            User user = userRepository.findById(postDTO.getUserId()).orElse(null);
            post.setUser(user);
        }
        
        // Set page (nếu có)
        if (postDTO.getPageId() != null) {
            Page page = pageRepository.findById(postDTO.getPageId()).orElse(null);
            post.setPage(page);
        }
        
        // Set group (nếu có)
        if (postDTO.getGroupId() != null) {
            Group group = groupRepository.findById(postDTO.getGroupId()).orElse(null);
            post.setGroup(group);
        }
        
        post.setContent(postDTO.getContent());
        post.setImageUrl(postDTO.getImageUrl());
        post.setCreatedAt(postDTO.getCreatedAt());
        post.setActiveStatus(postDTO.getActiveStatus());
        
        return post;
    }

    // Cập nhật entity từ DTO (dùng cho update)
    public Post updateFromDTO(Post existingPost, PostDTO postDTO) {
        if (postDTO == null || existingPost == null) {
            return existingPost;
        }

        // Chỉ cập nhật các trường được phép thay đổi
        if (postDTO.getContent() != null) {
            existingPost.setContent(postDTO.getContent());
        }
        
        if (postDTO.getImageUrl() != null) {
            existingPost.setImageUrl(postDTO.getImageUrl());
        }
        
        if (postDTO.getActiveStatus() != null) {
            existingPost.setActiveStatus(postDTO.getActiveStatus());
        }
        
        return existingPost;
    }
}
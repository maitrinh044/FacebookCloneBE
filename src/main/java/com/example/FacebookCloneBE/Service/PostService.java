package com.example.FacebookCloneBE.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FacebookCloneBE.DTO.PostDTO.PostDTO;
import com.example.FacebookCloneBE.Enum.ActiveEnum;
import com.example.FacebookCloneBE.Model.Post;
import com.example.FacebookCloneBE.Model.User;
import com.example.FacebookCloneBE.Model.Page;
import com.example.FacebookCloneBE.Model.Group;
import com.example.FacebookCloneBE.Repository.PostRepository;
import com.example.FacebookCloneBE.Repository.UserRepository;
import com.example.FacebookCloneBE.Repository.PageRepository;
import com.example.FacebookCloneBE.Repository.GroupRepository;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PageRepository pageRepository;

    @Autowired
    private GroupRepository groupRepository;

    // Convert Post entity to PostDTO
    private PostDTO convertToDTO(Post post) {
        return new PostDTO(
            post.getId(),
            post.getUser() != null ? post.getUser().getId() : null,
            post.getPage() != null ? post.getPage().getPageID() : null,
            post.getGroup() != null ? post.getGroup().getGroupID() : null,
            post.getContent(),
            post.getImageUrl(),
            post.getCreatedAt(),
            post.getActiveStatus()
        );
    }

    // Get post by ID
    public Optional<PostDTO> getPostByID(Long id) {
        return postRepository.findById(id)
                .map(this::convertToDTO);
    }

    // Get all posts
    public List<PostDTO> getAllPosts() {
        return postRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Get posts by user ID
    public List<PostDTO> getPostsByUser(Long userId) {
        return postRepository.findByUserId(userId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Get posts by page ID
    public List<PostDTO> getPostsByPage(Long pageId) {
        return postRepository.findByPageId(pageId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Get posts by group ID
    public List<PostDTO> getPostsByGroup(Long groupId) {
        return postRepository.findByGroupId(groupId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Create new post
    public Optional<PostDTO> createPost(PostDTO postDTO) {
        Post post = new Post();
        
        // Set user
        if (postDTO.getUserId() != null) {
            User user = userRepository.findById(postDTO.getUserId()).orElse(null);
            post.setUser(user);
        }
        
        // Set page (if exists)
        if (postDTO.getPageId() != null) {
            Page page = pageRepository.findById(postDTO.getPageId()).orElse(null);
            post.setPage(page);
        }
        
        // Set group (if exists)
        if (postDTO.getGroupId() != null) {
            Group group = groupRepository.findById(postDTO.getGroupId()).orElse(null);
            post.setGroup(group);
        }
        
        post.setContent(postDTO.getContent());
        post.setImageUrl(postDTO.getImageUrl());
        post.setActiveStatus(postDTO.getActiveStatus() != null ? postDTO.getActiveStatus() : ActiveEnum.ACTIVE);
        
        Post savedPost = postRepository.save(post);
        return Optional.of(convertToDTO(savedPost));
    }

    // Update existing post
    public Optional<PostDTO> updatePost(PostDTO postDTO) {
        return postRepository.findById(postDTO.getId())
                .map(existingPost -> {
                    // Update content if provided
                    if (postDTO.getContent() != null) {
                        existingPost.setContent(postDTO.getContent());
                    }
                    
                    // Update image URL if provided
                    if (postDTO.getImageUrl() != null) {
                        existingPost.setImageUrl(postDTO.getImageUrl());
                    }
                    
                    // Update active status if provided
                    if (postDTO.getActiveStatus() != null) {
                        existingPost.setActiveStatus(postDTO.getActiveStatus());
                    }
                    
                    Post updatedPost = postRepository.save(existingPost);
                    return convertToDTO(updatedPost);
                });
    }

    // Toggle active status of post
    public Optional<PostDTO> controlActivePost(Long id) {
        return postRepository.findById(id)
                .map(post -> {
                    ActiveEnum newStatus = post.getActiveStatus() == ActiveEnum.ACTIVE 
                            ? ActiveEnum.INACTIVE 
                            : ActiveEnum.ACTIVE;
                    post.setActiveStatus(newStatus);
                    Post updatedPost = postRepository.save(post);
                    return convertToDTO(updatedPost);
                });
    }

    // Delete post
    public boolean deletePost(Long id) {
        if (postRepository.existsById(id)) {
            postRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
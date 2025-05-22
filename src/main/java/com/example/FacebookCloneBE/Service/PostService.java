package com.example.FacebookCloneBE.Service;

// import java.security.Timestamp;
import java.sql.Timestamp;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.FacebookCloneBE.DTO.PostDTO.CountSharePost;
import com.example.FacebookCloneBE.DTO.PostDTO.PostDTO;
import com.example.FacebookCloneBE.DTO.ReactionDTO.CountReactionDTO;
import com.example.FacebookCloneBE.Enum.ActiveEnum;
import com.example.FacebookCloneBE.Enum.ReactionType;
import com.example.FacebookCloneBE.Mapper.PostMapper;
import com.example.FacebookCloneBE.Model.Post;
import com.example.FacebookCloneBE.Model.User;
import com.example.FacebookCloneBE.Model.Page;
import com.example.FacebookCloneBE.Model.Group;
import com.example.FacebookCloneBE.Repository.PostRepository;
import com.example.FacebookCloneBE.Repository.UserRepository;

import io.jsonwebtoken.lang.Collections;

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

    private PostDTO convertToDTO(Post post) {
        return new PostDTO(
                post.getId(),
                post.getUser() != null ? post.getUser() : null,
                post.getPage() != null ? post.getPage() : null,
                post.getGroup() != null ? post.getGroup() : null,
                post.getContent(),
                post.getImageUrl(),
                post.getCreatedAt(),
                post.getActiveStatus(),
                post.getOriginalPost() != null ? post.getOriginalPost().getId() : null);
    }

    public Optional<PostDTO> getPostByID(Long id) {
        return postRepository.findById(id).map(this::convertToDTO);
    }

    public List<PostDTO> getAllPosts() {
        return postRepository.findAll(Sort.by(Sort.Order.desc("createdAt"))) // Sắp xếp theo createdAt giảm dần
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<PostDTO> createPost(PostDTO postDTO) {
        Post post = new Post();

        if (postDTO.getUserId() != null) {
            User user = userRepository.findById(postDTO.getUserId().getId()).orElse(null);
            if (user != null)
                post.setUser(user);
            else
                return Optional.empty();
        }

        if (postDTO.getPageId() != null) {
            Page page = pageRepository.findById(postDTO.getPageId().getPageID()).orElse(null);
            if (page != null)
                post.setPage(page);
            else
                return Optional.empty();
        }

        if (postDTO.getGroupId() != null) {
            Group group = groupRepository.findById(postDTO.getGroupId().getGroupID()).orElse(null);
            if (group != null)
                post.setGroup(group);
            else
                return Optional.empty();
        }

        post.setContent(postDTO.getContent());
        post.setImageUrl(postDTO.getImageUrl());
        post.setActiveStatus(postDTO.getActiveStatus() != null ? postDTO.getActiveStatus() : ActiveEnum.ACTIVE);

        Post savedPost = postRepository.save(post);
        return Optional.of(convertToDTO(savedPost));
    }

    public Optional<PostDTO> updatePost(PostDTO postDTO) {
        return postRepository.findById(postDTO.getId()).map(existingPost -> {
            if (postDTO.getContent() != null)
                existingPost.setContent(postDTO.getContent());
            if (postDTO.getImageUrl() != null)
                existingPost.setImageUrl(postDTO.getImageUrl());
            if (postDTO.getActiveStatus() != null)
                existingPost.setActiveStatus(postDTO.getActiveStatus());

            Post updatedPost = postRepository.save(existingPost);
            return convertToDTO(updatedPost);
        });
    }

    public boolean deletePost(Long id) {
        if (postRepository.existsById(id)) {
            postRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<PostDTO> controlActiveStatus(Long id) {
        Optional<Post> post = postRepository.findById(id);
        PostDTO postDTO = convertToDTO(post.get());
        return postRepository.findById(postDTO.getId()).map(existingPost -> {
            if (postDTO.getActiveStatus() != null)
                existingPost.setActiveStatus(
                        postDTO.getActiveStatus() == ActiveEnum.ACTIVE ? ActiveEnum.INACTIVE : ActiveEnum.ACTIVE);

            Post updatedPost = postRepository.save(existingPost);
            return convertToDTO(updatedPost);
        });
    }

    public List<PostDTO> getPostByUser(Long userId) {
        try {
            List<Post> list = postRepository.findByUserId(userId);
            return list.stream().map(this::convertToDTO).toList();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public Optional<PostDTO> sharePostToProfile(Long userId, Long originalPostId, String caption) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Post> originalPost = postRepository.findById(originalPostId);

        if (user.isPresent() && originalPost.isPresent()) {
            Post sharedPost = new Post();
            sharedPost.setUser(user.get());
            sharedPost.setOriginalPost(originalPost.get());
            sharedPost.setContent(caption != null ? caption : originalPost.get().getContent());
            sharedPost.setActiveStatus(ActiveEnum.ACTIVE);

            postRepository.save(sharedPost);
            return Optional.of(convertToDTO(sharedPost));
        }
        return Optional.empty();
    }

    // Trong PostService.java
    public boolean checkExistingPost(Long postId) {
        // Giả sử bạn sử dụng repository để kiểm tra bài viết có tồn tại không
        return postRepository.existsById(postId); // Sử dụng method existsById của Spring Data JPA
    }

    public List<PostDTO> getFriendPosts(Long userId) {
        List<Post> friendPosts = postRepository.getFriendPosts(userId); // gọi phương thức trong repository
        return friendPosts.stream()
                .map(this::convertToDTO) // chuyển đổi sang DTO
                .collect(Collectors.toList());
    }

    public List<PostDTO> getByKeyword(String keyword) {
        return postRepository.findByContentContaining(keyword).stream().map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<PostDTO> getByStartAndEnd(Timestamp startTime, Timestamp endtime) {
        if (startTime != null && endtime != null) {
            return postRepository.findByCreatedAtBetween(startTime, endtime).stream().map(this::convertToDTO)
                    .collect(Collectors.toList());
        } else if (startTime != null) {
            return postRepository.findByCreatedAtAfter(startTime).stream().map(this::convertToDTO)
                    .collect(Collectors.toList());
        } else if (endtime != null) {
            return postRepository.findByCreatedAtBefore(endtime).stream().map(this::convertToDTO)
                    .collect(Collectors.toList());
        } else {
            return postRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
        }
    }

    public Optional<CountSharePost> getCountSharePost(Long postId) {
        try {
            List<Object[]> results = postRepository.getCountSharePost(postId);
//            System.out.println("Result of count share post: " + results.size());
            if (results.isEmpty()) {
                return Optional.empty(); // Trả về Optional.empty() nếu không có kết quả
            }

            Object[] result = results.get(0); // Lấy kết quả đầu tiên
            CountSharePost csp = new CountSharePost();
            csp.setPostId(((Number) result[0]).intValue()); // Lấy postId
            csp.setCount(((Number) result[1]).intValue()); // Lấy số lượng chia sẻ
            return Optional.of(csp); // Trả về Optional chứa csp
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty(); // Trả về Optional.empty() nếu có lỗi
        }
    }
}

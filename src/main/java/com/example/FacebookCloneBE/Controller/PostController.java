package com.example.FacebookCloneBE.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.example.FacebookCloneBE.DTO.PostDTO.PostDTO;
import com.example.FacebookCloneBE.Reponse.ResponseData;
import com.example.FacebookCloneBE.Service.PostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class PostController {

    @Autowired
    private PostService postService;
    private ResponseData responseData = new ResponseData();

    @GetMapping("/getPostByID/{id}")
    public ResponseEntity<ResponseData> getPostById(@PathVariable("id") Long id) {
        try {
            Optional<PostDTO> post = postService.getPostByID(id);
            if (post.isPresent()) {
                responseData.setData(post.get());
                responseData.setMessage("Get post by id success!");
                responseData.setStatusCode(200);
                return ResponseEntity.ok(responseData);
            } else {
                responseData.setMessage("Post not found!");
                responseData.setStatusCode(404);
                return ResponseEntity.status(404).body(responseData);
            }
        } catch (Exception e) {
            responseData.setMessage("Error when get post by id! " + e.getMessage());
            responseData.setStatusCode(500);
            return ResponseEntity.status(500).body(responseData);
        }
    }

    @GetMapping("/getAllPosts")
    public ResponseEntity<ResponseData> getAllPosts() {
        try {
            List<PostDTO> list = postService.getAllPosts();
            if (!list.isEmpty()) {
                responseData.setData(list);
                responseData.setMessage("Get all posts success!");
                responseData.setStatusCode(200);
                return ResponseEntity.ok(responseData);
            } else {
                responseData.setMessage("No posts found!");
                responseData.setStatusCode(404);
                return ResponseEntity.status(404).body(responseData);
            }
        } catch (Exception e) {
            responseData.setMessage("Error when get all posts! " + e.getMessage());
            responseData.setStatusCode(500);
            return ResponseEntity.status(500).body(responseData);
        }
    }

    @GetMapping("/getPostsByUser/{userId}")
    public ResponseEntity<ResponseData> getPostsByUser(@PathVariable("userId") Long userId) {
        try {
            List<PostDTO> list = postService.getPostsByUser(userId);
            if (!list.isEmpty()) {
                responseData.setData(list);
                responseData.setMessage("Get posts by user success!");
                responseData.setStatusCode(200);
                return ResponseEntity.ok(responseData);
            } else {
                responseData.setMessage("No posts found for this user!");
                responseData.setStatusCode(404);
                return ResponseEntity.status(404).body(responseData);
            }
        } catch (Exception e) {
            responseData.setMessage("Error when get posts by user! " + e.getMessage());
            responseData.setStatusCode(500);
            return ResponseEntity.status(500).body(responseData);
        }
    }

    @GetMapping("/getPostsByPage/{pageId}")
    public ResponseEntity<ResponseData> getPostsByPage(@PathVariable("pageId") Long pageId) {
        try {
            List<PostDTO> list = postService.getPostsByPage(pageId);
            if (!list.isEmpty()) {
                responseData.setData(list);
                responseData.setMessage("Get posts by page success!");
                responseData.setStatusCode(200);
                return ResponseEntity.ok(responseData);
            } else {
                responseData.setMessage("No posts found for this page!");
                responseData.setStatusCode(404);
                return ResponseEntity.status(404).body(responseData);
            }
        } catch (Exception e) {
            responseData.setMessage("Error when get posts by page! " + e.getMessage());
            responseData.setStatusCode(500);
            return ResponseEntity.status(500).body(responseData);
        }
    }

    @GetMapping("/getPostsByGroup/{groupId}")
    public ResponseEntity<ResponseData> getPostsByGroup(@PathVariable("groupId") Long groupId) {
        try {
            List<PostDTO> list = postService.getPostsByGroup(groupId);
            if (!list.isEmpty()) {
                responseData.setData(list);
                responseData.setMessage("Get posts by group success!");
                responseData.setStatusCode(200);
                return ResponseEntity.ok(responseData);
            } else {
                responseData.setMessage("No posts found for this group!");
                responseData.setStatusCode(404);
                return ResponseEntity.status(404).body(responseData);
            }
        } catch (Exception e) {
            responseData.setMessage("Error when get posts by group! " + e.getMessage());
            responseData.setStatusCode(500);
            return ResponseEntity.status(500).body(responseData);
        }
    }

    @PostMapping("/createPost")
    public ResponseEntity<ResponseData> createPost(@RequestBody PostDTO postDTO) {
        try {
            Optional<PostDTO> newPost = postService.createPost(postDTO);
            if (newPost.isPresent()) {
                responseData.setData(newPost.get());
                responseData.setMessage("Created a post success!");
                responseData.setStatusCode(200);
                return ResponseEntity.ok(responseData);
            } else {
                responseData.setMessage("Failed to create post!");
                responseData.setStatusCode(400);
                return ResponseEntity.status(400).body(responseData);
            }
        } catch (Exception e) {
            responseData.setStatusCode(500);
            responseData.setMessage("Error when creating a post: " + e.getMessage());
            return ResponseEntity.status(500).body(responseData);
        }
    }

    @PutMapping("/updatePost")
    public ResponseEntity<ResponseData> updatePost(@RequestBody PostDTO postDTO) {
        try {
            Optional<PostDTO> updatedPost = postService.updatePost(postDTO);
            if (updatedPost.isPresent()) {
                responseData.setData(updatedPost.get());
                responseData.setMessage("Updated post success!");
                responseData.setStatusCode(200);
                return ResponseEntity.ok(responseData);
            } else {
                responseData.setMessage("Post not found!");
                responseData.setStatusCode(404);
                return ResponseEntity.status(404).body(responseData);
            }
        } catch (Exception e) {
            responseData.setStatusCode(500);
            responseData.setMessage("Error when updating post: " + e.getMessage());
            return ResponseEntity.status(500).body(responseData);
        }
    }

    @PutMapping("/controlActiveStatusPost/{id}")
    public ResponseEntity<ResponseData> controlActiveStatusPost(@PathVariable("id") Long id) {
        try {
            Optional<PostDTO> updatedPost = postService.controlActivePost(id);
            if (updatedPost.isPresent()) {
                responseData.setData(updatedPost.get());
                responseData.setMessage("Updated activeStatus of post success!");
                responseData.setStatusCode(200);
                return ResponseEntity.ok(responseData);
            } else {
                responseData.setMessage("Post not found!");
                responseData.setStatusCode(404);
                return ResponseEntity.status(404).body(responseData);
            }
        } catch (Exception e) {
            responseData.setStatusCode(500);
            responseData.setMessage("Error when updating activeStatus of post: " + e.getMessage());
            return ResponseEntity.status(500).body(responseData);
        }
    }

    @DeleteMapping("/deletePost/{id}")
    public ResponseEntity<ResponseData> deletePost(@PathVariable("id") Long id) {
        try {
            boolean isDeleted = postService.deletePost(id);
            if (isDeleted) {
                responseData.setMessage("Deleted post success!");
                responseData.setStatusCode(200);
                return ResponseEntity.ok(responseData);
            } else {
                responseData.setMessage("Post not found!");
                responseData.setStatusCode(404);
                return ResponseEntity.status(404).body(responseData);
            }
        } catch (Exception e) {
            responseData.setStatusCode(500);
            responseData.setMessage("Error when deleting post: " + e.getMessage());
            return ResponseEntity.status(500).body(responseData);
        }
    }
}
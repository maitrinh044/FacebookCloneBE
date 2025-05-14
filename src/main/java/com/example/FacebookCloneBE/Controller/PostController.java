package com.example.FacebookCloneBE.Controller;

// import java.security.Timestamp;
// import java.sql.Date;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.FacebookCloneBE.DTO.PostDTO.PostDTO;
import com.example.FacebookCloneBE.DTO.ShareDTO.ShareDTO;
import com.example.FacebookCloneBE.Reponse.ResponseData;
import com.example.FacebookCloneBE.Service.PostService;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/getPostByID/{id}")
    public ResponseEntity<ResponseData> getPostById(@PathVariable("id") Long id) {
        ResponseData responseData = new ResponseData();
        try {
            Optional<PostDTO> post = postService.getPostByID(id);
            return post.map(p -> {
                responseData.setData(p);
                responseData.setMessage("Get post by id success!");
                responseData.setStatusCode(200);
                return ResponseEntity.ok(responseData);
            }).orElseGet(() -> {
                responseData.setMessage("Post not found!");
                responseData.setStatusCode(404);
                return ResponseEntity.status(404).body(responseData);
            });
        } catch (Exception e) {
            responseData.setMessage("Error when getting post by id: " + e.getMessage());
            responseData.setStatusCode(500);
            return ResponseEntity.status(500).body(responseData);
        }
    }

    @GetMapping("/getAllPosts")
    public ResponseEntity<ResponseData> getAllPosts() {
        ResponseData responseData = new ResponseData();
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
            responseData.setMessage("Error when getting all posts: " + e.getMessage());
            responseData.setStatusCode(500);
            return ResponseEntity.status(500).body(responseData);
        }
    }

    @PostMapping("/createPost")
    public ResponseEntity<ResponseData> createPost(@RequestBody PostDTO postDTO) {
        ResponseData responseData = new ResponseData();
        try {
            Optional<PostDTO> newPost = postService.createPost(postDTO);
            return newPost.map(post -> {
                responseData.setData(post);
                responseData.setMessage("Created a post successfully!");
                responseData.setStatusCode(200);
                return ResponseEntity.ok(responseData);
            }).orElseGet(() -> {
                responseData.setMessage("Failed to create post!");
                responseData.setStatusCode(400);
                return ResponseEntity.status(400).body(responseData);
            });
        } catch (Exception e) {
            responseData.setMessage("Error when creating post: " + e.getMessage());
            responseData.setStatusCode(500);
            return ResponseEntity.status(500).body(responseData);
        }
    }

    @PutMapping("/updatePost")
    public ResponseEntity<ResponseData> updatePost(@RequestBody PostDTO postDTO) {
        ResponseData responseData = new ResponseData();
        try {
            Optional<PostDTO> updatedPost = postService.updatePost(postDTO);
            return updatedPost.map(post -> {
                responseData.setData(post);
                responseData.setMessage("Updated post successfully!");
                responseData.setStatusCode(200);
                return ResponseEntity.ok(responseData);
            }).orElseGet(() -> {
                responseData.setMessage("Post not found!");
                responseData.setStatusCode(404);
                return ResponseEntity.status(404).body(responseData);
            });
        } catch (Exception e) {
            responseData.setMessage("Error when updating post: " + e.getMessage());
            responseData.setStatusCode(500);
            return ResponseEntity.status(500).body(responseData);
        }
    }

    @DeleteMapping("/deletePost/{id}")
    public ResponseEntity<ResponseData> deletePost(@PathVariable("id") Long id) {
        ResponseData responseData = new ResponseData();
        try {
            boolean isDeleted = postService.deletePost(id);
            if (isDeleted) {
                responseData.setMessage("Deleted post successfully!");
                responseData.setStatusCode(200);
                return ResponseEntity.ok(responseData);
            } else {
                responseData.setMessage("Post not found!");
                responseData.setStatusCode(404);
                return ResponseEntity.status(404).body(responseData);
            }
        } catch (Exception e) {
            responseData.setMessage("Error when deleting post: " + e.getMessage());
            responseData.setStatusCode(500);
            return ResponseEntity.status(500).body(responseData);
        }
    }

    @GetMapping("/getPostByUser/{id}")
    public ResponseEntity<ResponseData> getPostByUser(@PathVariable Long id) {
        ResponseData responseData = new ResponseData();
        try {
            List<PostDTO> list = postService.getPostByUser(id);
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
            // TODO: handle exception
            responseData.setMessage("Error while get post by user!");
            responseData.setStatusCode(500);
            return ResponseEntity.status(500).body(responseData);
        }
    }

    @PostMapping("/shareToProfile")
    public ResponseEntity<ResponseData> shareToProfile(@RequestBody ShareDTO shareDTO) {
        ResponseData responseData = new ResponseData();
        try {
            Optional<PostDTO> result = postService.sharePostToProfile(
                    shareDTO.getUserId(),
                    shareDTO.getOriginalPostId(),
                    shareDTO.getCaption());

            return result.map(post -> {
                responseData.setMessage("Shared to profile successfully!");
                responseData.setStatusCode(200);
                responseData.setData(post);
                return ResponseEntity.ok(responseData);
            }).orElseGet(() -> {
                responseData.setMessage("Failed to share post to profile");
                responseData.setStatusCode(400);
                return ResponseEntity.status(400).body(responseData);
            });
        } catch (Exception e) {
            responseData.setMessage("Error when sharing post to profile: " + e.getMessage());
            responseData.setStatusCode(500);
            return ResponseEntity.status(500).body(responseData);
        }
    }

    @GetMapping("/getFriendPosts/{userId}")
    public ResponseEntity<ResponseData> getFriendPosts(@PathVariable Long userId) {
        ResponseData responseData = new ResponseData();
        try {
            List<PostDTO> posts = postService.getFriendPosts(userId);
            responseData.setData(posts);
            responseData.setMessage("Lấy bài viết của bạn bè thành công!");
            responseData.setStatusCode(200);
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            responseData.setMessage("Lỗi khi lấy bài viết bạn bè: " + e.getMessage());
            responseData.setStatusCode(500);
            return ResponseEntity.status(500).body(responseData);
        }
    }
    
    @PutMapping("/controlActiveStatus/{id}")
    public ResponseEntity<ResponseData> controlActiveStatus(@PathVariable Long id) {
        ResponseData responseData = new ResponseData();
        try {
            Optional<PostDTO> postDTO = postService.controlActiveStatus(id);
            if (postDTO.isPresent()) {
                responseData.setData(postDTO.get());
                responseData.setMessage("Control activeStatus success!");
                responseData.setStatusCode(200);
                return ResponseEntity.ok(responseData);
            } else {
                responseData.setMessage("Not found this post!");
                responseData.setStatusCode(404);
                return ResponseEntity.status(404).body(responseData);
            }
        } catch (Exception e) {
            // TODO: handle exception
            responseData.setMessage("Error when control activeStatus this post!");
            responseData.setStatusCode(500);
            return ResponseEntity.status(500).body(responseData);
        }
    }

    @GetMapping("/getByKeyword/{keyword}")
    public ResponseEntity<ResponseData> getByKeyword(@PathVariable String keyword) {
        ResponseData responseData = new ResponseData();
        try {
            List<PostDTO> list = postService.getByKeyword(keyword);
            if (list.iterator().hasNext()) {
                responseData.setData(list);
                responseData.setMessage("Get posts by keyword success!");
                responseData.setStatusCode(200);
                return ResponseEntity.ok(responseData);
            } else {
                responseData.setMessage("Not found post by keyword!");
                responseData.setStatusCode(404);
                return ResponseEntity.status(404).body(responseData);
            }
        } catch (Exception e) {
            // TODO: handle exception
            responseData.setMessage("Error when get posts by keyword!");
            responseData.setStatusCode(500);
            return ResponseEntity.status(500).body(responseData);
        }
    }

    @GetMapping("/getByStartAndEnd")
    public ResponseEntity<ResponseData> getByStartAndEnd(
            @RequestParam(value = "start", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date start,

            @RequestParam(value = "end", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date end) {

        ResponseData responseData = new ResponseData();

        Timestamp startTime = (start != null) ? new java.sql.Timestamp(start.getTime()) : null;
        Timestamp endTime = (end != null) ? new java.sql.Timestamp(end.getTime()) : null;

        try {
            List<PostDTO> list = postService.getByStartAndEnd(startTime, endTime);

            if (!list.isEmpty()) {
                responseData.setData(list);
                responseData.setMessage("Get posts by time range success!");
                responseData.setStatusCode(200);
                return ResponseEntity.ok(responseData);
            } else {
                responseData.setMessage("No posts found in the specified time range!");
                responseData.setStatusCode(404);
                return ResponseEntity.status(404).body(responseData);
            }
        } catch (Exception e) {
            responseData.setMessage("Error occurred while getting posts!");
            responseData.setStatusCode(500);
            return ResponseEntity.status(500).body(responseData);
        }
    }

}

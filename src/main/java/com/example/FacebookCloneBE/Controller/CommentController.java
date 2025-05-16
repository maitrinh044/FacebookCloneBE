package com.example.FacebookCloneBE.Controller;

import com.example.FacebookCloneBE.DTO.CommentDTO.CommentDTO;
import com.example.FacebookCloneBE.DTO.CommentDTO.CommentDTO_Data;
import com.example.FacebookCloneBE.Enum.ActiveEnum;
import com.example.FacebookCloneBE.Reponse.ResponseData;
import com.example.FacebookCloneBE.Service.CommentService;
import com.example.FacebookCloneBE.Service.PostService;
import com.example.FacebookCloneBE.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;
    private ResponseData responseData = new ResponseData();

    @GetMapping("/post/{postId}")
    public ResponseEntity<ResponseData> getByPost(@PathVariable long postId) {
        try {
            List<CommentDTO> comments = commentService.getCommentsByPost(postId);
            responseData.setData(comments);
            responseData.setMessage("Success");
            responseData.setStatusCode(200);
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            responseData.setMessage("Error: " + e.getMessage());
            responseData.setStatusCode(500);
            return ResponseEntity.internalServerError().body(responseData);
        }
    }

    @GetMapping("/POST/{postId}")
    public ResponseEntity<ResponseData> getCommentByPost(@PathVariable("postId") Long postId) {
        try {
            List<CommentDTO_Data> comments = commentService.getCommentByPost(postId);
            responseData.setData(comments);
            responseData.setMessage("Success");
            responseData.setStatusCode(200);
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            responseData.setMessage("Error: " + e.getMessage());
            responseData.setStatusCode(500);
            return ResponseEntity.internalServerError().body(responseData);
        }
    }

    @PostMapping
    public ResponseEntity<ResponseData> create(@RequestBody CommentDTO commentDTO) {
        try {
            CommentDTO created = commentService.createComment(commentDTO);
            responseData.setData(created);
            responseData.setMessage("Comment created");
            responseData.setStatusCode(201);
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            responseData.setMessage("Error: " + e.getMessage());
            responseData.setStatusCode(500);
            return ResponseEntity.internalServerError().body(responseData);
        }
    }

    @PostMapping("/addComment")
    public ResponseEntity<ResponseData> addComment(@RequestParam Long userId, @RequestParam Long postId,
            @RequestParam String content, @RequestParam(required = false) Long parentCommentId) {
        try {
            if (userService.checkExistingUser(userId) && postService.checkExistingPost(postId)) {
                CommentDTO cmtDTO = new CommentDTO();
                LocalDateTime now = LocalDateTime.now();
                java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf(now);
                cmtDTO.setActiveStatus(ActiveEnum.ACTIVE);
                cmtDTO.setContent(content);
                cmtDTO.setCreatedAt(timestamp);
                cmtDTO.setPostId(postId);
                cmtDTO.setUserId(userId);
                cmtDTO.setParentCommentId(parentCommentId); // Thêm parentCommentId
                CommentDTO created = commentService.createComment(cmtDTO);
                responseData.setData(created);
                responseData.setMessage("Comment created");
                responseData.setStatusCode(201);
                return ResponseEntity.ok(responseData);
            } else {
                responseData.setMessage("Not found user or post existing!");
                responseData.setStatusCode(404);
                return ResponseEntity.status(404).body(responseData);
            }
        } catch (Exception e) {
            responseData.setMessage("Error: " + e.getMessage());
            responseData.setStatusCode(500);
            return ResponseEntity.internalServerError().body(responseData);
        }
    }

    @PostMapping("/reply")
    public ResponseEntity<ResponseData> createReply(@RequestBody CommentDTO commentDTO) {
        try {
            if (commentDTO.getParentCommentId() == null) {
                throw new IllegalArgumentException("Parent comment ID is required for a reply");
            }
            CommentDTO created = commentService.createComment(commentDTO);
            responseData.setData(created);
            responseData.setMessage("Reply created");
            responseData.setStatusCode(201);
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            responseData.setMessage("Error: " + e.getMessage());
            responseData.setStatusCode(500);
            return ResponseEntity.internalServerError().body(responseData);
        }
    }

    @GetMapping("/{commentId}/replies")
    public ResponseEntity<ResponseData> getReplies(@PathVariable long commentId) {
        try {
            List<CommentDTO> replies = commentService.getReplies(commentId);
            responseData.setData(replies);
            responseData.setMessage("Success");
            responseData.setStatusCode(200);
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            responseData.setMessage("Error: " + e.getMessage());
            responseData.setStatusCode(500);
            return ResponseEntity.internalServerError().body(responseData);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseData> update(@PathVariable long id, @RequestBody CommentDTO commentDTO) {
        try {
            Optional<CommentDTO> updated = commentService.updateComment(id, commentDTO);
            if (updated.isPresent()) {
                responseData.setData(updated.get());
                responseData.setMessage("Comment updated");
                responseData.setStatusCode(200);
                return ResponseEntity.ok(responseData);
            }
            responseData.setMessage("Comment not found");
            responseData.setStatusCode(404);
            return ResponseEntity.status(404).body(responseData);
        } catch (Exception e) {
            responseData.setMessage("Error: " + e.getMessage());
            responseData.setStatusCode(500);
            return ResponseEntity.internalServerError().body(responseData);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData> delete(@PathVariable long id) {
        try {
            if (commentService.deleteComment(id)) {
                responseData.setMessage("Comment deleted");
                responseData.setStatusCode(200);
                return ResponseEntity.ok(responseData);
            }
            responseData.setMessage("Comment not found");
            responseData.setStatusCode(404);
            return ResponseEntity.status(404).body(responseData);
        } catch (Exception e) {
            responseData.setMessage("Error: " + e.getMessage());
            responseData.setStatusCode(500);
            return ResponseEntity.internalServerError().body(responseData);
        }
    }
}
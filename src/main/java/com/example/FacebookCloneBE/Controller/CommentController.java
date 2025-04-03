package com.example.FacebookCloneBE.Controller;

import com.example.FacebookCloneBE.DTO.CommentDTO.CommentDTO;
import com.example.FacebookCloneBE.Reponse.ResponseData;
import com.example.FacebookCloneBE.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/comments")
public class CommentController {
    @Autowired private CommentService commentService;
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
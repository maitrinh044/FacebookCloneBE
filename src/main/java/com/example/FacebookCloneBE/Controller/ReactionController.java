package com.example.FacebookCloneBE.Controller;

import com.example.FacebookCloneBE.DTO.ReactionDTO.ReactionDTO;
import com.example.FacebookCloneBE.Enum.ReactionType;
import com.example.FacebookCloneBE.Enum.TargetType;
import com.example.FacebookCloneBE.Reponse.ResponseData;
import com.example.FacebookCloneBE.Service.ReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/reactions")
public class ReactionController {
    @Autowired
    private ReactionService reactionService;
    private ResponseData responseData = new ResponseData();

    @GetMapping("/{targetType}/{targetId}")
    public ResponseEntity<ResponseData> getReactions(
            @PathVariable TargetType targetType,
            @PathVariable long targetId) {
        try {
            List<ReactionDTO> reactions = reactionService.getReactions(targetId, targetType);
            responseData.setData(reactions);
            responseData.setMessage("Success");
            responseData.setStatusCode(200);
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            responseData.setMessage("Error: " + e.getMessage());
            responseData.setStatusCode(500);
            return ResponseEntity.internalServerError().body(responseData);
        }
    }

    @PostMapping("/toggle")
    public ResponseEntity<ResponseData> toggleReaction(@RequestBody ReactionDTO reactionDTO) {
        try {
            ReactionDTO result = reactionService.toggleReaction(reactionDTO);
            if (result != null) {
                responseData.setData(result);
                responseData.setMessage(result.getType() + " reaction updated");
            } else {
                responseData.setMessage("Reaction removed");
            }
            responseData.setStatusCode(200);
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            responseData.setMessage("Error: " + e.getMessage());
            responseData.setStatusCode(500);
            return ResponseEntity.internalServerError().body(responseData);
        }
    }

    @GetMapping("/count/{targetType}/{targetId}")
    public ResponseEntity<ResponseData> countReactions(
            @PathVariable TargetType targetType,
            @PathVariable long targetId) {
        try {
            long count = reactionService.countReactions(targetId, targetType);
            responseData.setData(count);
            responseData.setMessage("Total reactions");
            responseData.setStatusCode(200);
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            responseData.setMessage("Error: " + e.getMessage());
            responseData.setStatusCode(500);
            return ResponseEntity.internalServerError().body(responseData);
        }
    }

    @GetMapping("/count/{targetType}/{targetId}/{reactionType}")
    public ResponseEntity<ResponseData> countReactionsByType(
            @PathVariable TargetType targetType,
            @PathVariable long targetId,
            @PathVariable ReactionType reactionType) {
        try {
            long count = reactionService.countReactionsByType(targetId, targetType, reactionType);
            responseData.setData(count);
            responseData.setMessage(reactionType + " reactions count");
            responseData.setStatusCode(200);
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            responseData.setMessage("Error: " + e.getMessage());
            responseData.setStatusCode(500);
            return ResponseEntity.internalServerError().body(responseData);
        }
    }
}
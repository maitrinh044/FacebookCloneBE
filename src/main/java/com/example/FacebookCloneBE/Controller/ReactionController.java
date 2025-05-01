package com.example.FacebookCloneBE.Controller;

import com.example.FacebookCloneBE.DTO.ReactionDTO.ReactionDTO;
import com.example.FacebookCloneBE.DTO.ReactionDTO.Reaction_DTO;
import com.example.FacebookCloneBE.Enum.ReactionType;
import com.example.FacebookCloneBE.Enum.TargetType;
import com.example.FacebookCloneBE.Reponse.ResponseData;
import com.example.FacebookCloneBE.Service.ReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/getReactionByUserAndTarget")
    public ResponseEntity<ResponseData> getReactionByUserAndTarget(@RequestParam Long userId,
            @RequestParam String targetType, @RequestParam Long targetId) {
        try {
            TargetType tt = null;
            switch (targetType) {
                case "POST":
                    tt = TargetType.POST;
                    break;
                case "COMMENT":
                    tt = TargetType.COMMENT;
                    break;
                default:
                    break;
            }
            Optional<Reaction_DTO> rcDTO = reactionService.getByUserAndTarget(userId, tt, targetId);
            responseData.setData(rcDTO.get());
            responseData.setMessage("Get reaction by user and target success!");
            responseData.setStatusCode(200);
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            // TODO: handle exception
            responseData.setMessage("Error while get reaction by user and target!");
            responseData.setStatusCode(500);
            return ResponseEntity.status(500).body(responseData);
        }
    }

    @PostMapping("/controlReaction")
    public ResponseEntity<ResponseData> controlReaction(@RequestParam Long userId,
            @RequestParam String targetType,
            @RequestParam Long targetId,
            @RequestParam String reactionType) {
        try {
            TargetType tt = null;
            switch (targetType) {
                case "POST":
                    tt = TargetType.POST;
                    break;
                case "COMMENT":
                    tt = TargetType.COMMENT;
                    break;
                default:
                    break;
            }
            ReactionType rt = null;
            switch (reactionType) {
                case "LIKE":
                    rt = ReactionType.LIKE;
                    break;
                case "LOVE":
                    rt = ReactionType.LOVE;
                    break;
                case "HAHA":
                    rt = ReactionType.HAHA;
                    break;
                case "WOW":
                    rt = ReactionType.WOW;
                    break;
                case "SAD":
                    rt = ReactionType.SAD;
                    break;
                case "ANGRY":
                    rt = ReactionType.ANGRY;
                    break;
                default:
                    break;
            }
            Optional<Reaction_DTO> tmp = reactionService.controlReactionByTargetAndUser(userId, tt, targetId, rt);
            if (tmp.isPresent()) {
                responseData.setData(tmp.get());
                responseData.setMessage("Control reaction success!");
                responseData.setStatusCode(200);
                return ResponseEntity.ok(responseData);
            } else {
                responseData.setMessage("Reaction is empty!");
                responseData.setStatusCode(404);
                return ResponseEntity.status(404).body(responseData);
            }
        } catch (Exception e) {
            // TODO: handle exception
            responseData.setMessage("Error while control reaction!");
            responseData.setStatusCode(500);
            return ResponseEntity.status(500).body(responseData);
        }
    }

    @GetMapping("/getByUser/{userId}")
    public ResponseEntity<ResponseData> getByUser(@PathVariable("userId") Long userId) {
        try {
            List<Reaction_DTO> list = reactionService.getByUser(userId);
            if (list.iterator().hasNext()) {
                responseData.setData(list);
                responseData.setMessage("Get reactions by userId success!");
                responseData.setStatusCode(200);
                return ResponseEntity.ok(responseData);
            } else {
                responseData.setMessage("List reactions by userId is empty!");
                responseData.setStatusCode(404);
                return ResponseEntity.status(404).body(responseData);
            }
        } catch (Exception e) {
            // TODO: handle exception
            responseData.setMessage("Error while get reactions by userId!");
            responseData.setStatusCode(500);
            return ResponseEntity.status(500).body(responseData);
        }
    }
}
package com.example.FacebookCloneBE.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.FacebookCloneBE.DTO.PageFollwerDTO.PageFollowerDTO;
import com.example.FacebookCloneBE.DTO.UserDTO.UserDTO;
import com.example.FacebookCloneBE.Model.PageFollower;
import com.example.FacebookCloneBE.Reponse.ResponseData;
import com.example.FacebookCloneBE.Service.PageFollowerService;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class PageFollowerController {
    @Autowired
    private PageFollowerService pageFollowerService;
    private ResponseData responseData = new ResponseData();

    @GetMapping("/getFollowers/{id}")
    public ResponseEntity<ResponseData> getFollowersByPageID(@PathVariable("id") Long id) {
        try {
            List<UserDTO> listUser = pageFollowerService.getFollowers(id);
            if (listUser.iterator().hasNext()) {
                responseData.setData(listUser);
                responseData.setMessage("Get followers by pageID success!");
                responseData.setStatusCode(200);
                return ResponseEntity.ok(responseData);
            } else {
                responseData.setMessage("Not found any followers by pageID!");
                responseData.setStatusCode(404);
                return ResponseEntity.status(404).body(responseData);
            }
        } catch (Exception e) {
            responseData.setMessage("Error when get followers by pageID!! " + e.getMessage());
            responseData.setStatusCode(500);
            return ResponseEntity.status(500).body(responseData);
        }
    }

    @PostMapping("/createPageFollower")
    public ResponseEntity<ResponseData> createPageFollower(@RequestBody PageFollowerDTO pfDTO) {
        try {
            Optional<PageFollowerDTO> pf = pageFollowerService.createPageFollower(pfDTO);
            responseData.setData(pf);
            responseData.setMessage("Created a pageFollower success!");
            responseData.setStatusCode(200);
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            responseData.setMessage("Error when created a pageFollower! " + e.getMessage());
            responseData.setStatusCode(500);
            return ResponseEntity.status(500).body(responseData);
        }
    }

    @GetMapping("/getPF")
    public ResponseEntity<ResponseData> getPF(@RequestParam Long pageID, @RequestParam Long userID) {
        try {
            Optional<PageFollowerDTO> pf = pageFollowerService.getPF(pageID, userID);
            responseData.setData(pf.get());
            responseData.setMessage("success");
            responseData.setStatusCode(200);
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            responseData.setMessage("Error");
            responseData.setStatusCode(500);
            return ResponseEntity.status(500).body(responseData);
        }
    }

    @PutMapping("/controlFollow")
    public ResponseEntity<ResponseData> controlFollow(@RequestParam Long pageID, @RequestParam Long userID) {
        try {
            Optional<PageFollowerDTO> pf = pageFollowerService.controlFollowPage(pageID, userID);
            if (pf.isPresent()) {
                responseData.setData(pf.get());
                responseData.setMessage("Updated activeStatus of this pageFollower success!");
                responseData.setStatusCode(200);
                return ResponseEntity.ok(responseData);
            } else {
                responseData.setMessage("This user was not followed this page!");
                responseData.setStatusCode(404);
                return ResponseEntity.status(404).body(responseData);
            }
        } catch (Exception e) {
            responseData.setMessage("Error when updated activeStatus of this pageFollower! " + e.getMessage());
            responseData.setStatusCode(500);
            return ResponseEntity.status(500).body(responseData);
        }
    }

    @GetMapping("/isUserFollowing")
    public ResponseEntity<ResponseData> isUserFollowing(@RequestParam Long pageID, @RequestParam Long userID) {
        try {
            boolean flag = pageFollowerService.isUserFollowing(pageID, userID);
            responseData.setData(flag);
            responseData.setMessage("User is follwing this page!");
            responseData.setStatusCode(200);
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            responseData.setMessage("Error when get isUserFollowing");
            responseData.setStatusCode(500);
            return ResponseEntity.status(500).body(responseData);
        }
    }
}

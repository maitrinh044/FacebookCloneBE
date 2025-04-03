package com.example.FacebookCloneBE.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.example.FacebookCloneBE.DTO.GroupMemberShipDTO.GroupMemberShipDTO;
import com.example.FacebookCloneBE.Enum.RoleEnum;
import com.example.FacebookCloneBE.Reponse.ResponseData;
//import com.example.FacebookCloneBE.Repository.LikeRepository;
import com.example.FacebookCloneBE.Service.GroupMemberShipService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class GroupMemberShipController {

    @Autowired
    private GroupMemberShipService groupMemberShipService;
    private ResponseData responseData = new ResponseData();

    @GetMapping("/getAllGMS")
    public ResponseEntity<ResponseData> getAllGMS() {
        try {
            List<GroupMemberShipDTO> list = groupMemberShipService.getAllGroupMemberShips();
            if (list.iterator().hasNext()) {
                responseData.setData(list);
                responseData.setMessage("Get all groupMemberShip success!");
                responseData.setStatusCode(200);
                return ResponseEntity.ok(responseData);
            } else {
                responseData.setMessage("No data yet");
                responseData.setStatusCode(404);
                return ResponseEntity.status(404).body(responseData);
            }
        } catch (Exception e) {
            responseData.setStatusCode(500);
            responseData.setMessage("Error when get all groupMemberShip! " + e.getMessage());
            return ResponseEntity.status(500).body(responseData);
        }
    }

    @GetMapping("/getGMSByGroupIDAndUserID")
    public ResponseEntity<ResponseData> getGMSByGroupIDAndUserID(@RequestParam Long groupID,
            @RequestParam Long userID) {
        try {
            Optional<GroupMemberShipDTO> gmsDTO = groupMemberShipService.getGMSByGroupIdAndUserId(groupID, userID);
            if (gmsDTO.isPresent()) {
                responseData.setData(gmsDTO.get());
                responseData.setMessage("Get groupMemberShip by groupID and userID success!");
                responseData.setStatusCode(200);
                return ResponseEntity.ok(responseData);
            } else {
                responseData.setMessage("Not found this groupMemberShip by groupID and userID!");
                responseData.setStatusCode(404);
                return ResponseEntity.status(404).body(responseData);
            }
        } catch (Exception e) {
            responseData.setStatusCode(500);
            responseData.setMessage("Error this groupMemberShip by groupID and userID! " + e.getMessage());
            return ResponseEntity.status(500).body(responseData);
        }
    }

    @GetMapping("/getGMSByGroupID/{id}")
    public ResponseEntity<ResponseData> getGMSByGroupID(@PathVariable("id") Long groupID) {
        try {
            List<GroupMemberShipDTO> list = groupMemberShipService.getGroupMemberShipsByGroup(groupID);
            if (list.iterator().hasNext()) {
                responseData.setData(list);
                responseData.setMessage("Get list groupMemberShip by groupID success!");
                responseData.setStatusCode(200);
                return ResponseEntity.ok(responseData);
            } else {
                responseData.setMessage("Not found groupMemberShip by groupID!");
                responseData.setStatusCode(404);
                return ResponseEntity.status(404).body(responseData);
            }
        } catch (Exception e) {
            responseData.setStatusCode(500);
            responseData.setMessage("Error list groupMemberShip by groupID! " + e.getMessage());
            return ResponseEntity.status(500).body(responseData);
        }
    }

    @GetMapping("/getGMSByUserID/{id}")
    public ResponseEntity<ResponseData> getGMSByUserID(@PathVariable("id") Long userID) {
        try {
            List<GroupMemberShipDTO> list = groupMemberShipService.getGroupMemberShipsByUser(userID);
            if (list.iterator().hasNext()) {
                responseData.setData(list);
                responseData.setMessage("Get list groupMemberShip by userID success!");
                responseData.setStatusCode(200);
                return ResponseEntity.ok(responseData);
            } else {
                responseData.setMessage("Not found groupMemberShip by userID!");
                responseData.setStatusCode(404);
                return ResponseEntity.status(404).body(responseData);
            }
        } catch (Exception e) {
            responseData.setStatusCode(500);
            responseData.setMessage("Error list groupMemberShip by userID! " + e.getMessage());
            return ResponseEntity.status(500).body(responseData);
        }
    }

    @GetMapping("/getGMSSortedByGroupIDOrUserID")
    public ResponseEntity<ResponseData> getGroupMemberShipsSortedByGroupIDOrUserID(
            @RequestParam(defaultValue = "groupid") String model,
            @RequestParam Long id,
            @RequestParam String column,
            @RequestParam String order) {
        ResponseData responseData = new ResponseData(); // Khởi tạo mới mỗi request
        try {
            List<GroupMemberShipDTO> list = groupMemberShipService.getGroupMemberShipsSortedByGroupIDOrUserID(model, id,
                    column, order);

            if (list.isEmpty()) { // Kiểm tra danh sách có rỗng không
                responseData.setMessage("Not found groupMemberShipSorted!");
                responseData.setStatusCode(404);
                return ResponseEntity.status(404).body(responseData);
            }

            responseData.setData(list);
            responseData.setMessage("Get list groupMemberShipSorted success!");
            responseData.setStatusCode(200);
            return ResponseEntity.ok(responseData);

        } catch (Exception e) {
            responseData.setStatusCode(500);
            responseData.setMessage("Error when get list groupMemberShipSorted! " + e.getMessage());
            return ResponseEntity.status(500).body(responseData);
        }
    }

    @PostMapping("/addGMS")
    public ResponseEntity<ResponseData> addMemberToGroup(@RequestBody GroupMemberShipDTO groupMemberShipDTO) {
        try {
            System.out.println("grDTO: " + groupMemberShipDTO);
            Optional<GroupMemberShipDTO> newGMS = groupMemberShipService.addMemberToGroup(groupMemberShipDTO);
            System.out.println("newGr: " + newGMS);
            responseData.setData(newGMS.get());
            responseData.setMessage("Add new member to group success!");
            responseData.setStatusCode(200);
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            responseData.setStatusCode(500);
            responseData.setMessage("Error when add a new member to group! " + e.getMessage());
            return ResponseEntity.status(500).body(responseData);
        }
    }

    @PutMapping("controlActiveStatusMemberFromGroup")
    public ResponseEntity<ResponseData> controlActiveStatusMemberFromGroup(@RequestParam Long groupID,
            @RequestParam Long userID) {
        try {
            Optional<GroupMemberShipDTO> gms = groupMemberShipService.controlActiveStatusMemberFromGroup(groupID,
                    userID);
            responseData.setData(gms.get());
            responseData.setMessage("Control activeStatus member from Group success!");
            responseData.setStatusCode(200);
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            responseData.setStatusCode(500);
            responseData.setMessage("Error when add a new member to group! " + e.getMessage());
            return ResponseEntity.status(500).body(responseData);
        }
    }

    @PutMapping("updateMemberRole")
    public ResponseEntity<ResponseData> updateMemberRole(
            @RequestParam Long groupID,
            @RequestParam Long userID,
            @RequestParam String role) {
        try {
            RoleEnum tmp = RoleEnum.valueOf(role.toUpperCase()); // Chuyển role từ String -> Enum
            Optional<GroupMemberShipDTO> gmsDTO = groupMemberShipService.updateMemberRole(groupID, userID, tmp);

            if (gmsDTO.isPresent()) {
                responseData.setData(gmsDTO.get());
                responseData.setMessage("Updated role of member in group success!");
                responseData.setStatusCode(200);
                return ResponseEntity.ok(responseData);
            } else {
                responseData.setStatusCode(404);
                responseData.setMessage("Group MemberShip not found!");
                return ResponseEntity.status(404).body(responseData);
            }
        } catch (Exception e) {
            responseData.setStatusCode(500);
            responseData.setMessage("Error updating role: " + e.getMessage());
            return ResponseEntity.status(500).body(responseData);
        }
    }

}

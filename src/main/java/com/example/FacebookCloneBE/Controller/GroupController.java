package com.example.FacebookCloneBE.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.FacebookCloneBE.DTO.GroupDTO.GroupDTO;
import com.example.FacebookCloneBE.Reponse.ResponseData;
import com.example.FacebookCloneBE.Service.GroupService;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class GroupController {
    @Autowired
    private GroupService groupService;
    private ResponseData responseData = new ResponseData();

    @GetMapping("/getAllGroups")
    public ResponseEntity<ResponseData> getAllGroups() {
        try {
            List<GroupDTO> listGroupDTO = groupService.getAllGroups();
            responseData.setData(listGroupDTO);
            responseData.setStatusCode(200);
            responseData.setMessage("Lấy danh sách nhóm thành công!");
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            responseData.setStatusCode(500);
            ;
            responseData.setMessage("Lỗi khi lấy danh sách nhóm: " + e.getMessage());
            return ResponseEntity.status(500).body(responseData);
        }
    }

}

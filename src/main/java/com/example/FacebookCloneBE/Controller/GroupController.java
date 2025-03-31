package com.example.FacebookCloneBE.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.example.FacebookCloneBE.DTO.GroupDTO.GroupDTO;
import com.example.FacebookCloneBE.Mapper.GroupMapper;
import com.example.FacebookCloneBE.Model.Group;
import com.example.FacebookCloneBE.Reponse.ResponseData;
import com.example.FacebookCloneBE.Service.GroupService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
            responseData.setMessage("Lỗi khi lấy danh sách nhóm: " + e.getMessage());
            return ResponseEntity.status(500).body(responseData);
        }
    }

    @GetMapping("/getGroupById/{id}")
    public ResponseEntity<ResponseData> getGroupById(@PathVariable("id") Long id) {
        try {
            Optional<GroupDTO> groupDTO = groupService.getGroupById(id);
            responseData.setData(groupDTO.get());
            responseData.setMessage("Lấy Group từ id thành công!");
            responseData.setStatusCode(200);
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            responseData.setStatusCode(500);
            responseData.setMessage("Lỗi khi lấy Group: " + e.getMessage());
            return ResponseEntity.status(500).body(responseData);
        }
    }

    @PostMapping("/createGroup")
    public ResponseEntity<ResponseData> createGroup(@Valid @RequestBody GroupDTO groupDTO,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> fieldErrors = new HashMap<>();
            bindingResult.getFieldErrors()
                    .forEach(error -> fieldErrors.put(error.getField(), error.getDefaultMessage()));
            responseData.setStatusCode(400);
            responseData.setData(fieldErrors);
            responseData.setMessage("There are some fields invalid");
            return ResponseEntity.status(400).body(responseData);
        }
        try {
            Optional<GroupDTO> addGroup = groupService.createGroup(groupDTO);
            responseData.setData(addGroup);
            responseData.setMessage("Created a group success!");
            responseData.setStatusCode(200);
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            responseData.setStatusCode(500);
            responseData.setMessage("Error when created a group: " + e.getMessage());
            return ResponseEntity.status(500).body(responseData);
        }
    }

    @PutMapping("updateGroup/{id}")
    public ResponseEntity<ResponseData> updateGroup(@Valid @PathVariable("id") Long id,
            @Valid @RequestBody GroupDTO groupDTO) {
        try {
            Optional<GroupDTO> grDTO = groupService.updateGroup(groupDTO);
            responseData.setData(grDTO.get());
            responseData.setMessage("Cập nhật Group thành công!");
            responseData.setStatusCode(200);
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            responseData.setStatusCode(500);
            responseData.setMessage("Lỗi khi cập nhật Group: " + e.getMessage());
            return ResponseEntity.status(500).body(responseData);
        }
    }

    @PutMapping("controlDeleteGroup/{id}")
    public ResponseEntity<ResponseData> putMethodName(@PathVariable("id") Long id) {
        try {
            Optional<GroupDTO> grDTO = groupService.controlDeleteGroup(id);

            if (grDTO.isEmpty()) {
                responseData.setStatusCode(404);
                responseData.setMessage("Group ID không tồn tại, không thể cập nhật hoạt động!");
                return ResponseEntity.status(404).body(responseData);
            }

            responseData.setData(grDTO.get());
            responseData.setMessage("Cập nhật hoạt động Group thành công!");
            responseData.setStatusCode(200);
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            responseData.setStatusCode(500);
            responseData.setMessage("Lỗi khi cập nhật Group: " + e.getMessage());
            return ResponseEntity.status(500).body(responseData);
        }
    }

    @GetMapping("/getGroupByCreateBy/{id}")
    public ResponseEntity<ResponseData> getMethodName(@Valid @PathVariable("id") Long idCreateBy) {
        try {
            List<GroupDTO> list = groupService
                    .getGroupsByAdmin(idCreateBy);
            if (list.isEmpty()) {
                responseData.setMessage("Not found groups were created by this createByUser");
                responseData.setStatusCode(404);
                return ResponseEntity.status(404).body(responseData);
            } else {
                responseData.setData(list);
                responseData.setMessage("Get list group by this createByUser success!");
                responseData.setStatusCode(200);
                return ResponseEntity.ok(responseData);
            }
        } catch (Exception e) {
            responseData.setStatusCode(500);
            responseData.setMessage("Error when get list group by this createByUser: " + e.getMessage());
            return ResponseEntity.status(500).body(responseData);
        }
    }

    @GetMapping("/getGroupByKeyword/{keyword}")
    public ResponseEntity<ResponseData> getMethodName(@Valid @PathVariable("keyword") String param) {
        try {
            List<GroupDTO> list = groupService.getGroupsByName(param);
            if (list.isEmpty()) {
                responseData.setMessage("Not found groups by this keyword");
                responseData.setStatusCode(404);
                return ResponseEntity.status(404).body(responseData);
            } else {
                responseData.setData(list);
                responseData.setMessage("Get list group by this keyword success!");
                responseData.setStatusCode(200);
                return ResponseEntity.ok(responseData);
            }
        } catch (Exception e) {
            responseData.setStatusCode(500);
            responseData.setMessage("Error when get list group by this keyword: " + e.getMessage());
            return ResponseEntity.status(500).body(responseData);
        }
    }

    @GetMapping("/getGroupSorted")
    public ResponseEntity<ResponseData> getMethodName(@RequestParam(defaultValue = "groupID") String column,
            @RequestParam(defaultValue = "asc") String order) {
        try {

            List<GroupDTO> list = groupService.getGroupSorted(column, order);
            responseData.setData(list);
            responseData.setMessage("Get list group success!");
            responseData.setStatusCode(200);
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            responseData.setStatusCode(500);
            responseData.setMessage("Error when get list group sorted: " + e.getMessage());
            return ResponseEntity.status(500).body(responseData);
        }
    }

}

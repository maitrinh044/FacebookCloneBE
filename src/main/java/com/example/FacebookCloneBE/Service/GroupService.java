package com.example.FacebookCloneBE.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.FacebookCloneBE.DTO.GroupDTO.GroupDTO;
import com.example.FacebookCloneBE.Model.Group;
import com.example.FacebookCloneBE.Model.Post;
import com.example.FacebookCloneBE.Model.User;

import jakarta.transaction.Transactional;

@Service
public interface GroupService {
    List<GroupDTO> getAllGroups();

    Optional<GroupDTO> getGroupById(Long id);

    Optional<GroupDTO> createGroup(GroupDTO grDTO);

    Optional<GroupDTO> updateGroup(GroupDTO grDTO);

    @Transactional
    Optional<GroupDTO> controlDeleteGroup(Long id); // ko hien thi group cho user

    List<GroupDTO> getGroupsByAdmin(Long adminId);

    List<GroupDTO> getGroupsByName(String keyword);

    boolean checkExistGroup(Long id);

    List<GroupDTO> getGroupSorted(String colum, String order);
}

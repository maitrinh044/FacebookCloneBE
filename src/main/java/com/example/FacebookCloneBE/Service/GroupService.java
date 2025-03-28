package com.example.FacebookCloneBE.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.FacebookCloneBE.DTO.GroupDTO.GroupDTO;
import com.example.FacebookCloneBE.Model.Group;
import com.example.FacebookCloneBE.Model.Post;
import com.example.FacebookCloneBE.Model.User;

@Service
public interface GroupService {
    List<GroupDTO> getAllGroups();

    Optional<GroupDTO> getGroupById(Long id);

    Optional<GroupDTO> createGroup(Group gr);

    Optional<GroupDTO> updateGroup(Group gr);

    Optional<GroupDTO> deleteGroup(Long id); // ko hien thi group cho user

    List<GroupDTO> getGroupsByAdmin(Long adminId);

    List<GroupDTO> getGroupsByName(String keyword);

}

package com.example.FacebookCloneBE.Service.Impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FacebookCloneBE.DTO.GroupDTO.GroupDTO;
import com.example.FacebookCloneBE.Mapper.GroupMapper;
import com.example.FacebookCloneBE.Model.Group;
import com.example.FacebookCloneBE.Model.User;
import com.example.FacebookCloneBE.Repository.GroupRepository;
import com.example.FacebookCloneBE.Repository.UserRepository;
import com.example.FacebookCloneBE.Service.GroupService;

@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private UserRepository userRepository;

    public List<GroupDTO> getAllGroups() {
        try {
            List<Group> listEntity = groupRepository.findAll();
            return StreamSupport.stream(listEntity.spliterator(), false).map(GroupMapper::toGroupDTO).toList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public Optional<GroupDTO> getGroupById(Long id) {
        try {
            Optional<Group> gr = groupRepository.findById(id);
            return gr.map(GroupMapper::toGroupDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public boolean checkExistGroup(Long id) {
        List<Group> list = groupRepository.findAll();
        for (Group it : list) {
            if (it.getGroupID().equals(id))
                return true;
        }
        return false;
    }

    public Optional<GroupDTO> createGroup(Group gr) {
        try {
            if (checkExistGroup(gr.getGroupID())) {
                System.out.println("Group exists!!!");
                return null;
            }
            Group savedGroup = groupRepository.save(gr);
            GroupDTO groupDTO = GroupMapper.toGroupDTO(savedGroup);
            return Optional.of(groupDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public Optional<GroupDTO> updateGroup(Group gr) {
        try {
            if (!checkExistGroup(gr.getGroupID())) {
                System.out.println("Group exists!!!");
                return null;
            }
            Group savedGroup = groupRepository.save(gr);
            GroupDTO groupDTO = GroupMapper.toGroupDTO(savedGroup);
            return Optional.of(groupDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public Optional<GroupDTO> deleteGroup(Long id) {
        try {
            Optional<Group> gr = groupRepository.findById(id);
            if (checkExistGroup(gr.get().getGroupID())) {
                System.out.println("Group exists!!!");
                return null;
            }
            Group savedGroup = groupRepository.save(gr.get());
            GroupDTO groupDTO = GroupMapper.toGroupDTO(savedGroup);
            return Optional.of(groupDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public List<GroupDTO> getGroupsByAdmin(Long adminId) {
        try {
            Optional<User> admin = userRepository.findById(adminId);
            List<Group> listGroup = groupRepository.findGroupsByCreateBy(admin.get());
            return StreamSupport.stream(listGroup.spliterator(), false).map(GroupMapper::toGroupDTO).toList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public List<GroupDTO> getGroupsByName(String keyword) {
        try {
            List<Group> list = groupRepository.findByKeyword(keyword);
            return StreamSupport.stream(list.spliterator(), false).map(GroupMapper::toGroupDTO).toList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

}

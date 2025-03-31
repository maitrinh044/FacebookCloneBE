package com.example.FacebookCloneBE.Service.Impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.FacebookCloneBE.DTO.GroupDTO.GroupDTO;
import com.example.FacebookCloneBE.Enum.ActiveEnum;
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

    @Override
    public List<GroupDTO> getAllGroups() {
        try {
            List<Group> listEntity = groupRepository.findAll();
            return StreamSupport.stream(listEntity.spliterator(), false).map(GroupMapper::toGroupDTO).toList();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error while get all group: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public Optional<GroupDTO> getGroupById(Long id) {
        try {
            Optional<Group> gr = groupRepository.findById(id);
            return gr.map(GroupMapper::toGroupDTO);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error while get group by id: " + e.getMessage());
            return Optional.empty();
        }
    }

    public boolean checkExistGroup(Long id) {
        Optional<Group> existGroup = groupRepository.findById(id);
        if (existGroup.isPresent()) {
            return true;
        }
        return false;
    }

    @Override
    public Optional<GroupDTO> createGroup(GroupDTO grDTO) {
        try {
            if (checkExistGroup(grDTO.getGroupID())) {
                System.err.println("This group is exist, can not create!");
                return Optional.empty();
            }
            Group newGroup = GroupMapper.toGroupEntityPost(grDTO);
            newGroup = groupRepository.save(newGroup);

            return Optional.of(GroupMapper.toGroupDTO(newGroup));
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error while creating group: " + e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<GroupDTO> updateGroup(GroupDTO grDTO) {
        try {
            Optional<Group> existingGroupOpt = groupRepository.findById(grDTO.getGroupID());
            if (!checkExistGroup(grDTO.getGroupID())) {
                System.err.println("This group is not exist, can not update!");
                return Optional.empty();
            }

            Group existingGroup = GroupMapper.toGroupEntityPut(grDTO);
            Group updatedGroup = groupRepository.save(existingGroup);
            GroupDTO groupDTO = GroupMapper.toGroupDTO(updatedGroup);
            return Optional.of(groupDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Optional<GroupDTO> controlDeleteGroup(Long id) {
        try {
            if (!checkExistGroup(id)) {
                System.err.println("This group is not exist, can not update activeStatus!");
                return Optional.empty();
            } else {
                Optional<Group> gr = groupRepository.findById(id);
                if (gr.get().getActiveStatus() == ActiveEnum.ACTIVE) {
                    groupRepository.deleteGroup(id, ActiveEnum.INACTIVE);
                } else {
                    groupRepository.deleteGroup(id, ActiveEnum.ACTIVE);
                }
                return Optional.of(GroupMapper.toGroupDTO(gr.get()));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public List<GroupDTO> getGroupsByAdmin(Long adminId) {
        try {
            Optional<User> admin = userRepository.findById(adminId);
            System.out.println("Admin ID: " + adminId);
            List<Group> listGroup = groupRepository.findByCreateBy(admin.get());
            return StreamSupport.stream(listGroup.spliterator(), false).map(GroupMapper::toGroupDTO).toList();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error when get Group by createBy: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public List<GroupDTO> getGroupsByName(String keyword) {
        try {
            List<Group> list = groupRepository.findByKeyword(keyword);
            return StreamSupport.stream(list.spliterator(), false).map(GroupMapper::toGroupDTO).toList();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error when get Group by keyword: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public List<GroupDTO> getGroupSorted(String column, String order) {
        try {
            org.springframework.data.domain.Sort sort = org.springframework.data.domain.Sort
                    .by(org.springframework.data.domain.Sort.Direction.fromString(order), column);
            List<Group> groupList = groupRepository.findAll(sort);

            return groupList.stream().map(GroupMapper::toGroupDTO).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            // System.out.println("Error when get list group sorted: " + e.getMessage());
            return Collections.emptyList();
        }
    }

}

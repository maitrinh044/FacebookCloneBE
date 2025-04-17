package com.example.FacebookCloneBE.Service.Impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.FacebookCloneBE.DTO.GroupDTO.GroupDTO;
import com.example.FacebookCloneBE.DTO.GroupMemberShipDTO.GroupMemberShipDTO;
import com.example.FacebookCloneBE.DTO.UserDTO.UserDTO;
import com.example.FacebookCloneBE.Enum.ActiveEnum;
import com.example.FacebookCloneBE.Enum.RoleEnum;
import com.example.FacebookCloneBE.Mapper.GroupMapper;
import com.example.FacebookCloneBE.Mapper.GroupMemberShipMapper;
import com.example.FacebookCloneBE.Mapper.UserMapper;
import com.example.FacebookCloneBE.Model.Group;
import com.example.FacebookCloneBE.Model.GroupMemberShip;
import com.example.FacebookCloneBE.Model.User;
import com.example.FacebookCloneBE.Repository.GroupMemberShipRepository;
import com.example.FacebookCloneBE.Repository.GroupRepository;
import com.example.FacebookCloneBE.Repository.RequestJoinGroupRepository;
import com.example.FacebookCloneBE.Repository.UserRepository;
import com.example.FacebookCloneBE.Service.GroupMemberShipService;

@Service
public class GroupMemberShipServiceImpl implements GroupMemberShipService {

    @Autowired
    private GroupMemberShipRepository groupMemberShipRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private RequestJoinGroupRepository requestJoinGroupRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean checkExistGroup(Long idGroup) {
        Optional<Group> gr = groupRepository.findById(idGroup);
        if (gr.isPresent()) {
            System.out.println("This group is exist!!");
            return true;
        }
        return false;
    }

    @Override
    public boolean checkExistUser(Long idUser) {
        Optional<User> us = userRepository.findById(idUser);
        if (us.isPresent()) {
            System.out.println("This user is exist");
            return true;
        }
        return false;
    }

    @Override
    public boolean checkExistGroupMemberShip(Long idGroup, Long idUSer) {
        List<GroupMemberShip> list = groupMemberShipRepository.findByGroupID(groupRepository.findById(idGroup).get());
        for (GroupMemberShip groupMemberShip : list) {
            if (groupMemberShip.getUserID().getId().equals(idUSer)) {
                System.out.println("This groupMemberShip is exist!");
                return true;
            }
        }
        return false;
    }

    @Override
    public List<GroupMemberShipDTO> getAllGroupMemberShips() {
        try {
            List<GroupMemberShip> list = groupMemberShipRepository.findAll();
            return StreamSupport.stream(list.spliterator(), false).map(GroupMemberShipMapper::toGMSDTO).toList();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error when get all GroupMemberShip! " + e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public Optional<GroupMemberShipDTO> getGMSByGroupIdAndUserId(Long idGroup, Long idUser) {
        try {
            Optional<GroupMemberShip> gms = groupMemberShipRepository.findByGroupIDAndUserID(idGroup, idUser);
            return Optional.of(GroupMemberShipMapper.toGMSDTO(gms.get()));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error when get GroupMemberShip by groupId and userId! " + e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public List<GroupMemberShipDTO> getGroupMemberShipsByGroup(Long groupID) {
        try {
            if (!checkExistGroup(groupID)) {
                System.out.println("This group is not exist, can not get groupMemberShip by this group");
                return Collections.emptyList();
            }
            Optional<Group> gr = groupRepository.findById(groupID);
            List<GroupMemberShip> list = groupMemberShipRepository
                    .findByGroupID(gr.get());
            return StreamSupport.stream(list.spliterator(), false).map(GroupMemberShipMapper::toGMSDTO).toList();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error when get GroupMemberShips by group! " + e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public List<GroupMemberShipDTO> getGroupMemberShipsByUser(Long userID) {
        try {
            if (!checkExistUser(userID)) {
                System.out.println("This user is not exist, can not get groupMemberShip by this group");
                return Collections.emptyList();
            }
            Optional<User> us = userRepository.findById(userID);
            List<GroupMemberShip> list = groupMemberShipRepository
                    .findByUserID(us.get());
            return StreamSupport.stream(list.spliterator(), false).map(GroupMemberShipMapper::toGMSDTO).toList();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error when get GroupMemberShips by user! " + e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public List<GroupMemberShipDTO> getGroupMemberShipsSortedByGroupIDOrUserID(String model, Long id, String column,
            String order) {
        try {
            Sort.Direction direction = Sort.Direction.fromString(order);
            Sort sort = Sort.by(direction, column);

            if (model.equalsIgnoreCase("groupid")) {
                Group gr = groupRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Group not found with ID: " + id));
                List<GroupMemberShip> groupMemberShipList = groupMemberShipRepository.findByGroupID(gr, sort);
                return groupMemberShipList.stream().map(GroupMemberShipMapper::toGMSDTO).collect(Collectors.toList());

            } else if (model.equalsIgnoreCase("userid")) {
                User us = userRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + id));
                List<GroupMemberShip> groupMemberShipList = groupMemberShipRepository.findByUserID(us, sort);
                return groupMemberShipList.stream().map(GroupMemberShipMapper::toGMSDTO).collect(Collectors.toList());

            } else {
                System.out.println("Just get list groupMemberShip sorted by groupid or userid!");
                return Collections.emptyList();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error when getting list groupMemberShipSorted: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public Optional<GroupMemberShipDTO> addMemberToGroup(GroupMemberShipDTO groupMemberShipDTO) {
        try {
            if (!checkExistGroup(groupMemberShipDTO.getGroupID().getGroupID())) {
                System.err.println("Group ID không tồn tại: " + groupMemberShipDTO.getGroupID().getGroupID());
                return Optional.empty();
            }
            if (!checkExistUser(groupMemberShipDTO.getUserID().getId())) {
                System.err.println("User ID không tồn tại: " + groupMemberShipDTO.getUserID().getId());
                return Optional.empty();
            }
            if (checkExistGroupMemberShip(groupMemberShipDTO.getGroupID().getGroupID(),
                    groupMemberShipDTO.getUserID().getId())) {
                System.err.println("Thành viên đã tồn tại trong group, không thể thêm!");
                return Optional.empty();
            }

            GroupMemberShip newGroupMemberShip = GroupMemberShipMapper.toGMSEntityPost(groupMemberShipDTO);
            newGroupMemberShip = groupMemberShipRepository.save(newGroupMemberShip);
            return Optional.of(GroupMemberShipMapper.toGMSDTO(newGroupMemberShip));
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error while creating groupMemberShip: " + e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<GroupMemberShipDTO> controlActiveStatusMemberFromGroup(Long groupID, Long userID) {
        try {
            if (!checkExistGroupMemberShip(groupID, userID)) {
                System.err.println("This groupMemberShip is not exist, can not update activeStatus!");
                return Optional.empty();
            }
            Optional<GroupMemberShip> groupMemberShip = groupMemberShipRepository.findByGroupIDAndUserID(groupID,
                    userID);
            GroupMemberShipDTO groupMemberShipDTO = GroupMemberShipMapper.toGMSDTO(groupMemberShip.get());
            if (groupMemberShipDTO.getActiveStatus().equals(ActiveEnum.ACTIVE)) {
                groupMemberShipRepository.updateActiveStatus(groupMemberShipDTO.getGroupID().getGroupID(),
                        groupMemberShipDTO.getUserID().getId(), ActiveEnum.INACTIVE);
                return Optional.of(groupMemberShipDTO);
            } else {
                groupMemberShipRepository.updateActiveStatus(groupMemberShipDTO.getGroupID().getGroupID(),
                        groupMemberShipDTO.getUserID().getId(), ActiveEnum.ACTIVE);
                return Optional.of(groupMemberShipDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error while updating activeStatus groupMemberShip: " + e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<GroupMemberShipDTO> updateMemberRole(Long groupID, Long userID, RoleEnum role) {
        try {
            if (!checkExistGroupMemberShip(groupID, userID)) {
                System.err.println("This groupMemberShip is not exist, can not update role!");
                return Optional.empty();
            }
            Optional<GroupMemberShip> groupMemberShip = groupMemberShipRepository.findByGroupIDAndUserID(groupID,
                    userID);
            GroupMemberShipDTO groupMemberShipDTO = GroupMemberShipMapper.toGMSDTO(groupMemberShip.get());
            groupMemberShipRepository.updateMemberRole(groupMemberShipDTO.getGroupID().getGroupID(),
                    groupMemberShipDTO.getUserID().getId(), role);
            return Optional.of(groupMemberShipDTO);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error while updating role member in groupMemberShip: " + e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public List<UserDTO> getGroupMember(Long groupId) {
        List<User> users = groupMemberShipRepository.findUsersByGroupId((long) groupId);
        return users.stream()
                .map(UserMapper::toUserDTO) // hoặc new UserDTO(user)
                .collect(Collectors.toList());
    }

    @Override
    public List<GroupDTO> getGroupsByUserId(Long userId) {
        try {
            // Kiểm tra user có tồn tại không
            if (!checkExistUser(userId)) {
                System.out.println("User không tồn tại, không thể lấy danh sách group");
                return Collections.emptyList();
            }

            // Lấy user
            Optional<User> user = userRepository.findById(userId);
            if (user.isEmpty()) return Collections.emptyList();

            // Lấy danh sách GroupMemberShip
            List<GroupMemberShip> groupMemberships = groupMemberShipRepository.findByUserID(user.get());

            // Map sang GroupDTO
            return groupMemberships.stream()
                    .map(gms -> GroupMapper.toGroupDTO(gms.getGroupID()))
                    .collect(Collectors.toList());

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Lỗi khi lấy danh sách group từ user: " + e.getMessage());
            return Collections.emptyList();
        }
    }

}

package com.example.FacebookCloneBE.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.FacebookCloneBE.DTO.GroupDTO.GroupDTO;
import com.example.FacebookCloneBE.DTO.GroupMemberShipDTO.GroupMemberShipDTO;
import com.example.FacebookCloneBE.DTO.UserDTO.UserDTO;
import com.example.FacebookCloneBE.Enum.RoleEnum;
import com.example.FacebookCloneBE.Model.GroupMemberShip;
import com.example.FacebookCloneBE.Model.User;

@Service
public interface GroupMemberShipService {
    // Quản lý thành viên trong nhóm
    List<GroupMemberShipDTO> getAllGroupMemberShips();

    Optional<GroupMemberShipDTO> getGMSByGroupIdAndUserId(Long idGroup, Long idUser);

    List<GroupMemberShipDTO> getGroupMemberShipsByGroup(Long groupID);

    List<GroupMemberShipDTO> getGroupMemberShipsByUser(Long userID);

    List<GroupMemberShipDTO> getGroupMemberShipsSortedByGroupIDOrUserID(String model, Long id, String column,
            String order);

    Optional<GroupMemberShipDTO> addMemberToGroup(GroupMemberShipDTO groupMemberShipDTO);

    Optional<GroupMemberShipDTO> controlActiveStatusMemberFromGroup(Long groupID, Long userID);

    Optional<GroupMemberShipDTO> updateMemberRole(Long groupID, Long userID, RoleEnum role);

    List<UserDTO> getGroupMember(Long groupId);

    boolean checkExistGroup(Long idGroup);

    boolean checkExistUser(Long idUser);

    boolean checkExistGroupMemberShip(Long idGroup, Long idUSer);

    // boolean approveJoinRequest(Long idGroup, Long idUser);

    // boolean rejectJoinRequest(Long idGroup, Long idUser);
    List<GroupDTO> getGroupsByUserId(Long userId);
}

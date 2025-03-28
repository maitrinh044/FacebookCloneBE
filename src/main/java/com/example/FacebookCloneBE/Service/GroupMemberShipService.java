package com.example.FacebookCloneBE.Service;

import java.util.List;

public interface GroupMemberShipService {
    // Quản lý thành viên trong nhóm
    void addMemberToGroup(Long idGroup, List<String> listIdUser);

    void deleteMemberFromGroup(Long idGroup, List<String> listIdUser);

    boolean updateMemberRole(Long idGroup, Long idUser, String newRole);

    boolean approveJoinRequest(Long idGroup, Long idUser);

    boolean rejectJoinRequest(Long idGroup, Long idUser);

    boolean banMemberFromGroup(Long idGroup, Long idUser);

    boolean unbanMemberFromGroup(Long idGroup, Long idUser);
}

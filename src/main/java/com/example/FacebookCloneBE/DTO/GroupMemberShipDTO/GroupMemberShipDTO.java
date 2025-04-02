package com.example.FacebookCloneBE.DTO.GroupMemberShipDTO;

import java.sql.Date;

import com.example.FacebookCloneBE.Enum.ActiveEnum;
import com.example.FacebookCloneBE.Enum.RoleEnum;
import com.example.FacebookCloneBE.Model.Group;
import com.example.FacebookCloneBE.Model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupMemberShipDTO {
    private Long id;
    private Group groupID;
    private User userID;
    private RoleEnum role;
    private Date joinAt;
    private ActiveEnum activeStatus;
}

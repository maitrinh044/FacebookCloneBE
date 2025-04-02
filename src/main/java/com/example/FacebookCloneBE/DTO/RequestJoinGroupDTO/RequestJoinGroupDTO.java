package com.example.FacebookCloneBE.DTO.RequestJoinGroupDTO;

import java.sql.Date;
import java.time.LocalDate;

import com.example.FacebookCloneBE.Enum.ActiveEnum;
import com.example.FacebookCloneBE.Enum.RequestStatus;
import com.example.FacebookCloneBE.Model.Group;
import com.example.FacebookCloneBE.Model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestJoinGroupDTO {
    private Long id;
    private User user;
    private Group group;
    private RequestStatus requestStatus;
    private LocalDate requestDate;
    private ActiveEnum activeStatus;
}

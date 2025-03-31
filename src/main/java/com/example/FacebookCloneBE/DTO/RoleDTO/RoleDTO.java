package com.example.FacebookCloneBE.DTO.RoleDTO;

import com.example.FacebookCloneBE.Enum.ActiveEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {
    private Long id;
    private String roleName;
    private ActiveEnum activeStatus;
}

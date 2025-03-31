package com.example.FacebookCloneBE.DTO.PageDTO;

import java.sql.Date;

import com.example.FacebookCloneBE.Enum.ActiveEnum;
import com.example.FacebookCloneBE.Model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageDTO {
    private long pageID;
    private String pageName;
    private String description;
    private Date createAt;
    private User createBy;
    private ActiveEnum activeStatus;
}

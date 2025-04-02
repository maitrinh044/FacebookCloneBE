package com.example.FacebookCloneBE.DTO.PageFollwerDTO;

import java.sql.Date;

import com.example.FacebookCloneBE.Enum.ActiveEnum;
import com.example.FacebookCloneBE.Model.Page;
import com.example.FacebookCloneBE.Model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageFollowerDTO {
    private long id;
    private Page pageID;
    private User userID;
    private Date followedAt;
    private ActiveEnum activeStatus;
}

package com.example.FacebookCloneBE.Mapper;

import com.example.FacebookCloneBE.DTO.PageDTO.PageDTO;
import com.example.FacebookCloneBE.Model.Page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class PageMapper {
    public static PageDTO toPageDTO(Page pg) {
        PageDTO pgDTO = new PageDTO();
        pgDTO.setPageID(pg.getPageID());
        pgDTO.setPageName(pg.getPageName());
        pgDTO.setDescription(pg.getDescription());
        pgDTO.setCreateAt(pg.getCreateAt());
        pgDTO.setCreateBy(pg.getCreatedBy());
        pgDTO.setActiveStatus(pg.getActiveStatus());
        return pgDTO;
    }

    public static Page toPageEntityPOST(PageDTO pgDTO) {
        Page pg = new Page();
        // pg.setPageID(pgDTO.getPageID());
        pg.setPageName(pgDTO.getPageName());
        pg.setDescription(pgDTO.getDescription());
        pg.setCreateAt(pgDTO.getCreateAt());
        pg.setCreatedBy(pgDTO.getCreateBy());
        pg.setActiveStatus(pgDTO.getActiveStatus());
        return pg;
    }

    public static Page toPageEntityPUT(PageDTO pgDTO) {
        Page pg = new Page();
        pg.setPageID(pgDTO.getPageID());
        pg.setPageName(pgDTO.getPageName());
        pg.setDescription(pgDTO.getDescription());
        pg.setCreateAt(pgDTO.getCreateAt());
        pg.setCreatedBy(pgDTO.getCreateBy());
        pg.setActiveStatus(pgDTO.getActiveStatus());
        return pg;
    }
}

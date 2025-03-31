package com.example.FacebookCloneBE.Service;

import java.beans.Transient;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.example.FacebookCloneBE.Mapper.PageMapper;

import jakarta.transaction.Transactional;

import com.example.FacebookCloneBE.DTO.PageDTO.PageDTO;

@Service
public interface PageService {
    Optional<PageDTO> createPage(PageDTO pageDTO);

    Optional<PageDTO> updatePage(PageDTO pageDTO);

    @Transactional
    Optional<PageDTO> controlActivePage(Long pageID);

    Optional<PageDTO> getPageByID(Long pageID);

    List<PageDTO> getAllPages();

    List<PageDTO> getPagesSorted(String column, String order);

    List<PageDTO> getPagesByKeyWord(String keyword);
}

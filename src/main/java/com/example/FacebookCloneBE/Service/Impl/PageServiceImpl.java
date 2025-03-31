package com.example.FacebookCloneBE.Service.Impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.FacebookCloneBE.Mapper.PageMapper;

import com.example.FacebookCloneBE.DTO.PageDTO.PageDTO;
import com.example.FacebookCloneBE.Enum.ActiveEnum;
import com.example.FacebookCloneBE.Mapper.PageMapper;
import com.example.FacebookCloneBE.Model.Page;
import com.example.FacebookCloneBE.Repository.PageRepository;
import com.example.FacebookCloneBE.Service.PageService;

@Service
public class PageServiceImpl implements PageService {
    @Autowired
    private PageRepository pageRepository;

    public boolean checkExistingPage(Long pageID) {
        Optional<Page> existingPage = pageRepository.findById(pageID);
        if (existingPage.isPresent()) {
            System.out.println("This page is existing");
            return true;
        }
        return false;
    }

    @Override
    public Optional<PageDTO> createPage(PageDTO pageDTO) {
        try {
            if (checkExistingPage(pageDTO.getPageID())) {
                System.out.println("Can not create this page");
                return Optional.empty();
            }
            Page newPage = pageRepository.save(PageMapper.toPageEntityPOST(pageDTO));
            System.out.println("NEW PAGE: " + newPage);
            return Optional.of(PageMapper.toPageDTO(newPage));
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Optional<PageDTO> updatePage(PageDTO pageDTO) {
        try {
            if (!checkExistingPage(pageDTO.getPageID())) {
                System.out.println("Can not updated this page");
                return Optional.empty();
            }
            Page newPage = pageRepository.save(PageMapper.toPageEntityPUT(pageDTO));
            return Optional.of(PageMapper.toPageDTO(newPage));
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Optional<PageDTO> controlActivePage(Long pageID) {
        try {
            if (!checkExistingPage(pageID)) {
                System.out.println("Can not updated activeStatus of this page");
                return Optional.empty();
            } else {
                Optional<Page> pg = pageRepository.findById(pageID);
                if (pg.get().getActiveStatus() == ActiveEnum.ACTIVE) {
                    pageRepository.updateActiveStatus(pageID, ActiveEnum.INACTIVE);
                } else {
                    pageRepository.updateActiveStatus(pageID, ActiveEnum.ACTIVE);
                }
                return Optional.of(PageMapper.toPageDTO(pg.get()));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Optional<PageDTO> getPageByID(Long pageID) {
        try {
            Optional<Page> page = pageRepository.findById(pageID);
            return Optional.of(PageMapper.toPageDTO(page.get()));
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public List<PageDTO> getAllPages() {
        try {
            List<Page> list = pageRepository.findAll();
            return StreamSupport.stream(list.spliterator(), false).map(PageMapper::toPageDTO).toList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public List<PageDTO> getPagesSorted(String column, String order) {
        try {
            Sort sort = Sort.by(Sort.Direction.fromString(order), column);

            List<Page> list = pageRepository.findAll(sort);

            return list.stream().map(PageMapper::toPageDTO).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            // System.out.println("Error when get list page sorted: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public List<PageDTO> getPagesByKeyWord(String keyword) {
        try {
            List<Page> list = pageRepository.findByKeyword(keyword);
            return list.stream().map(PageMapper::toPageDTO).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}

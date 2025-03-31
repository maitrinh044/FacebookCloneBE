package com.example.FacebookCloneBE.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.example.FacebookCloneBE.DTO.PageDTO.PageDTO;
import com.example.FacebookCloneBE.Reponse.ResponseData;
import com.example.FacebookCloneBE.Repository.LikeRepository;
import com.example.FacebookCloneBE.Service.PageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class PageController {

    @Autowired
    private PageService pageService;
    private ResponseData responseData = new ResponseData();

    @GetMapping("/getPageByID/{id}")
    public ResponseEntity getPageById(@PathVariable("id") Long id) {
        try {
            Optional<PageDTO> pg = pageService.getPageByID(id);
            responseData.setData(pg.get());
            responseData.setMessage("Get page by id success!");
            responseData.setStatusCode(200);
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            responseData.setMessage("Error when get page by id! " + e.getMessage());
            responseData.setStatusCode(500);
            return ResponseEntity.status(500).body(responseData);
        }
    }

    @GetMapping("/getAllPage")
    public ResponseEntity<ResponseData> getAllPage() {
        try {
            List<PageDTO> list = pageService.getAllPages();
            if (list.iterator().hasNext()) {
                responseData.setData(list);
                responseData.setMessage("Get all page success!");
                responseData.setStatusCode(200);
                return ResponseEntity.ok(responseData);
            } else {
                responseData.setMessage("Can found any page exist!");
                responseData.setStatusCode(404);
                return ResponseEntity.status(404).body(responseData);
            }
        } catch (Exception e) {
            responseData.setMessage("Error when get all page! " + e.getMessage());
            responseData.setStatusCode(500);
            return ResponseEntity.status(500).body(responseData);
        }
    }

    @GetMapping("/getPageByKeyword/{keyword}")
    public ResponseEntity<ResponseData> getPageByKeyword(@PathVariable("keyword") String keyword) {
        try {
            List<PageDTO> list = pageService.getPagesByKeyWord(keyword);
            if (list.iterator().hasNext()) {
                responseData.setData(list);
                responseData.setMessage("Get pages by keyword success!");
                responseData.setStatusCode(200);
                return ResponseEntity.ok(responseData);
            } else {
                responseData.setMessage("Can found any pages by keyword!");
                responseData.setStatusCode(404);
                return ResponseEntity.status(404).body(responseData);
            }
        } catch (Exception e) {
            responseData.setMessage("Error when get pages by keyword! " + e.getMessage());
            responseData.setStatusCode(500);
            return ResponseEntity.status(500).body(responseData);
        }
    }

    @GetMapping("/getPagesSorted")
    public ResponseEntity<ResponseData> getMethodName(@RequestParam(defaultValue = "pageID") String column,
            @RequestParam(defaultValue = "asc") String order) {
        try {
            List<PageDTO> list = pageService.getPagesSorted(column, order);
            responseData.setData(list);
            responseData.setMessage("Get list page sorted success!");
            responseData.setStatusCode(200);
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            responseData.setStatusCode(500);
            responseData.setMessage("Error when get list page sorted: " + e.getMessage());
            return ResponseEntity.status(500).body(responseData);
        }
    }

    @PostMapping("/createPage")
    public ResponseEntity<ResponseData> createPage(@RequestBody PageDTO pgDTO) {
        try {
            Optional<PageDTO> addPage = pageService.createPage(pgDTO);
            responseData.setData(addPage.get());
            responseData.setMessage("Created a page success!");
            responseData.setStatusCode(200);
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            responseData.setStatusCode(500);
            responseData.setMessage("Error when created a page: " + e.getMessage());
            return ResponseEntity.status(500).body(responseData);
        }
    }

    @PutMapping("/updatePage")
    public ResponseEntity<ResponseData> updatePage(@RequestBody PageDTO pageDTO) {
        try {
            Optional<PageDTO> upPage = pageService.updatePage(pageDTO);
            responseData.setData(upPage.get());
            responseData.setMessage("Updated a page success!");
            responseData.setStatusCode(200);
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            responseData.setStatusCode(500);
            responseData.setMessage("Error when updated a page: " + e.getMessage());
            return ResponseEntity.status(500).body(responseData);
        }
    }

    @PutMapping("/controlActiveStatusPage/{id}")
    public ResponseEntity<ResponseData> controlActiveStatusPage(@PathVariable("id") Long id) {
        try {
            Optional<PageDTO> upPage = pageService.controlActivePage(id);
            responseData.setData(upPage.get());
            responseData.setMessage("Updated activeStatus of a page success!");
            responseData.setStatusCode(200);
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            responseData.setStatusCode(500);
            responseData.setMessage("Error when updated activeStatus of a page: " + e.getMessage());
            return ResponseEntity.status(500).body(responseData);
        }
    }
}

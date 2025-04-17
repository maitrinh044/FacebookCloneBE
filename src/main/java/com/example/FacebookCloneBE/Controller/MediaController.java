package com.example.FacebookCloneBE.Controller;

import com.example.FacebookCloneBE.Enum.MediaTypeEnum;
import com.example.FacebookCloneBE.Model.Media;
import com.example.FacebookCloneBE.Reponse.ResponseData;
import com.example.FacebookCloneBE.Service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/media")
public class MediaController {
    @Autowired
    private MediaService mediaService;

    @PostMapping("/upload")
    public ResponseEntity<ResponseData> uploadMedia(
            @RequestParam("file") MultipartFile file,
            @RequestParam("userId") Long userId
    ) {
        ResponseData responseData = new ResponseData();
        try {
            Media media = mediaService.uploadMedia(file, userId);
            if (media != null) {
                responseData.setData(media);
                responseData.setMessage("Upload successful");
                responseData.setStatusCode(200);
                return new ResponseEntity<>(responseData, HttpStatus.OK);
            } else {
                responseData.setStatusCode(404);
                responseData.setMessage("Upload failed");
                responseData.setData(null);
                return new ResponseEntity<>(responseData, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            responseData.setStatusCode(500);
            responseData.setMessage(e.getMessage());
            responseData.setStatusCode(500);
            return new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

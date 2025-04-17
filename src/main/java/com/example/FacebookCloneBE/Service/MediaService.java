package com.example.FacebookCloneBE.Service;

import com.example.FacebookCloneBE.Enum.MediaTypeEnum;
import com.example.FacebookCloneBE.Model.Media;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public interface MediaService {
    Media uploadMedia(MultipartFile file, Long userId) throws IOException;

}

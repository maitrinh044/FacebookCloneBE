package com.example.FacebookCloneBE.Service.Impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.FacebookCloneBE.Enum.MediaTypeEnum;
import com.example.FacebookCloneBE.Model.Media;
import com.example.FacebookCloneBE.Model.User;
import com.example.FacebookCloneBE.Repository.MediaRepository;
import com.example.FacebookCloneBE.Repository.UserRepository;
import com.example.FacebookCloneBE.Service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class MediaServiceImpl implements MediaService {

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Media uploadMedia(MultipartFile file, Long userId) {
        try {
            // Xác định type nếu chưa có
            String contentType = file.getContentType();
            MediaTypeEnum detectedType;

            if (contentType.startsWith("image")) {
                detectedType = MediaTypeEnum.IMAGE;
            } else if (contentType.startsWith("video")) {
                detectedType = MediaTypeEnum.VIDEO;
            } else {
                throw new IllegalArgumentException("File must be image or video");
            }

            // Upload file lên Cloudinary
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());

            // Tạo media mới
            Media media = new Media();
            media.setUrl((String) uploadResult.get("secure_url"));
            media.setPublicId((String) uploadResult.get("public_id"));
            media.setType(detectedType); // dùng type đã detect
            media.setUser(userRepository.findById(userId).orElseThrow());

            return mediaRepository.save(media);
        } catch (Exception e) {
            throw new RuntimeException("Upload failed: " + e.getMessage());
        }
    }
}

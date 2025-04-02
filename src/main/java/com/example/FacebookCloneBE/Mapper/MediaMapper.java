package com.example.FacebookCloneBE.Mapper;

import com.example.FacebookCloneBE.DTO.MediaDTO.MediaDTO;
import com.example.FacebookCloneBE.Model.Media;
import com.example.FacebookCloneBE.Model.User;
import com.example.FacebookCloneBE.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class MediaMapper {
    // Convert từ Media entity sang MediaDTO
    public static MediaDTO toDTO(Media media) {
        MediaDTO dto = new MediaDTO();
        dto.setId(media.getId());
        dto.setUrl(media.getUrl());
        dto.setType(media.getType());
        dto.setPublicId(media.getPublicId());
        dto.setUser(media.getUser());
        return dto;
    }

    // Convert từ MediaDTO sang Media entity
    public static Media toEntity(MediaDTO mediaDTO) {
        if (mediaDTO == null) {
            return null;
        }
        Media media = new Media();
        media.setId(mediaDTO.getId());
        media.setUrl(mediaDTO.getUrl());
        media.setType(mediaDTO.getType());
        media.setPublicId(mediaDTO.getPublicId());
        media.setUser(mediaDTO.getUser()); // Gán user entity vào media
        return media;
    }
}

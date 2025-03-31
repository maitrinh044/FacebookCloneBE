package com.example.FacebookCloneBE.DTO.MediaDTO;

import com.example.FacebookCloneBE.Enum.MediaTypeEnum;
import com.example.FacebookCloneBE.Model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MediaDTO {
    private long id;
    private String url;
    private MediaTypeEnum type;
    private String publicId;
    private User user;
}

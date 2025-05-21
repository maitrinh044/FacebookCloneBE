package com.example.FacebookCloneBE.DTO.PostDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountSharePost {
    private int postId;
    private int count;
}

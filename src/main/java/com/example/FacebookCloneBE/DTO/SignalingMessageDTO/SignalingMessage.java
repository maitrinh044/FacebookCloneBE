package com.example.FacebookCloneBE.DTO.SignalingMessageDTO;

import com.example.FacebookCloneBE.DTO.IceCandidateDTO;
import com.example.FacebookCloneBE.Model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignalingMessage {
    private User from;
    private User to;
    private String type;  // loại tín hiệu như "offer", "answer", "ice"
    private String callType;
    private String sdp;
    private IceCandidateDTO candidate;
}

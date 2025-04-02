package com.example.FacebookCloneBE.Model;

import com.example.FacebookCloneBE.Enum.MediaTypeEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "media")
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String url;
    @Column
    @Enumerated(EnumType.STRING)
    private MediaTypeEnum type;
    @Column
    private String publicId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}

package com.example.FacebookCloneBE.Model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.example.FacebookCloneBE.Enum.ActiveEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "\"groups\"")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupID;

    @Column(name = "groupName")
    private String groupName;

    @Column(name = "createAt")
    private LocalDateTime createAt;

    @JoinColumn(name = "createBy")
    @ManyToOne
    private User createBy;

    @Column(name = "active_status")
    @Enumerated(EnumType.STRING)
    private ActiveEnum activeStatus;
}

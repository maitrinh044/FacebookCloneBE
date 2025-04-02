package com.example.FacebookCloneBE.Model;

import java.sql.Date;

import org.hibernate.annotations.ManyToAny;

import com.example.FacebookCloneBE.Enum.ActiveEnum;

import jakarta.annotation.Generated;
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
@Table(name = "PageFollower")
public class PageFollower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "pageID", nullable = false)
    @ManyToOne
    private Page pageID;

    @JoinColumn(name = "userID", nullable = false)
    @ManyToOne
    private User userID;

    @Column(name = "followedAt")
    private Date followedAt;

    @Column(name = "active_status")
    @Enumerated(EnumType.STRING)
    private ActiveEnum activeStatus;
}

package com.example.FacebookCloneBE.Model;

import java.sql.Date;

import com.example.FacebookCloneBE.Enum.RoleEnum;

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
@Table(name = "GroupMemberShip")
public class GroupMemberShip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "groupID", nullable = false)
    @ManyToOne
    private Group groupID;

    @JoinColumn(name = "userID", nullable = false)
    @ManyToOne
    private User userID;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    @Column(name = "joinedAt")
    private Date joinedAt;
}

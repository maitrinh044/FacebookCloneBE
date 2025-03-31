package com.example.FacebookCloneBE.Model;

import java.sql.Date;

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
@Table(name = "Page")
public class Page {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pageID;

    @Column(name = "pageName")
    private String pageName;

    @Column(name = "description")
    private String description;

    @Column(name = "createAt")
    private Date createAt;

    @JoinColumn(name = "createdBy")
    @ManyToOne
    private User createdBy;

    @Column(name = "active_status")
    @Enumerated(EnumType.STRING)
    private ActiveEnum activeStatus;

}

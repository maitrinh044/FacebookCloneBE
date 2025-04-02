package com.example.FacebookCloneBE.Model;

import com.example.FacebookCloneBE.Enum.ActiveEnum;
import com.fasterxml.jackson.databind.annotation.EnumNaming;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Bank")
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nameBank;

    @Column
    @Enumerated(EnumType.STRING)
    private ActiveEnum activeStatus;
}

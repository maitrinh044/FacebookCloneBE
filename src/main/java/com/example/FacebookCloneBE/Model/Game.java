package com.example.FacebookCloneBE.Model;

import java.time.LocalDateTime;

import com.example.FacebookCloneBE.Enum.ActiveEnum;

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

@Data
@Entity
@NoArgsConstructor
@Table(name = "Game")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nameGame;

    @Column
    private LocalDateTime createdAt;

    @Column
    private String description;

    @Enumerated(EnumType.STRING)
    private ActiveEnum activeStatus;
}

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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "DetailGame")
public class DetailGame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Game gameID;

    @Column
    private int planDuration;

    @Column
    private Double price;

    @Column
    @Enumerated(EnumType.STRING)
    private ActiveEnum activeStatus;

}

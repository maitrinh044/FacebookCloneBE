package com.example.FacebookCloneBE.Model;

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
@Table(name = "DetailReceipt")
public class DetailReceipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Receipt receiptID;

    @JoinColumn(nullable = false)
    @ManyToOne
    private DetailGame detailGameID;

    @Column
    @Enumerated(EnumType.STRING)
    private ActiveEnum activeStatus;
}

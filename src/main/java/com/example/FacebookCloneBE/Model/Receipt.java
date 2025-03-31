package com.example.FacebookCloneBE.Model;

import java.time.LocalDateTime;

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

@Data
@Entity
@NoArgsConstructor
@Table(name = "Receipt")
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Double total;

    @JoinColumn(nullable = false)
    @ManyToOne
    private BankAccount bankAccountID;

    @Column
    private LocalDateTime createdAt;

    @Column
    @Enumerated(EnumType.STRING)
    private ActiveEnum activeStatus;
}

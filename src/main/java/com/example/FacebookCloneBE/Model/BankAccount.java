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

@Data
@Entity
@NoArgsConstructor
@Table(name = "BankAccount")
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String bankAccountNumber;

    @JoinColumn(nullable = false)
    @ManyToOne
    private User userID;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Bank bankID;

    @Column
    @Enumerated(EnumType.STRING)
    private ActiveEnum activeStatus;
}

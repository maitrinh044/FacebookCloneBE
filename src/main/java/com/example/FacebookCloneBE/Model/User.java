package com.example.FacebookCloneBE.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String profilePicture;
    @Column
    private String gender;
    @Column
    private String birthday;
    @Column
    private Timestamp createAt;

}

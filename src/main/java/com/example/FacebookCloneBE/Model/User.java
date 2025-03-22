package com.example.FacebookCloneBE.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.mapping.Join;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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
    @Column
    private String phone;
    @Column(nullable = false)
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String profilePicture;
    @Column
    private String biography;
    @Column
    private String gender;
    @Column
    private LocalDate birthday;
    @Column
    private boolean isOnline;
    @Column
    private LocalDateTime createAt;
    @OneToMany
    @JoinColumn(name = "post")
    @JsonIgnore
    private List<Post> posts;

}

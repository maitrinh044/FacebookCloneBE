package com.example.FacebookCloneBE.Repository;

import com.example.FacebookCloneBE.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT user FROM User user where user.email = :email")
    User findByEmail(@Param("email") String email);
    @Query("SELECT user FROM User user where user.phone = :phone")
    User findByPhoneNumber(@Param("phone") String phone);
    @Query("SELECT u FROM User u WHERE u.email = :emailOrPhone OR u.phone = :emailOrPhone")
    User findByEmailOrPhone(@Param("emailOrPhone") String emailOrPhone);



}

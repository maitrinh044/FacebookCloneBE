package com.example.FacebookCloneBE.Repository;

import com.example.FacebookCloneBE.Model.UserSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserSessionRepository extends JpaRepository<UserSession, Long> {
    @Query("SELECT u FROM UserSession u WHERE u.refreshToken = :refreshToken")
    Optional<UserSession> findByRefreshToken(@Param("refreshToken") String refreshToken);

    @Query("DELETE FROM UserSession WHERE refreshToken like :refreshToken")
    void deleteByRefreshToken(@Param("refreshToken") String refreshToken);
}


package com.example.FacebookCloneBE.Repository;

import com.example.FacebookCloneBE.Model.Friendship;
import com.example.FacebookCloneBE.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, Long> {
    @Query("SELECT fr.user2 FROM Friendship fr WHERE fr.user1.id = :userID AND fr.type = 'ACCEPTED' UNION " +
            "SELECT fr.user1 FROM Friendship fr WHERE fr.user2.id = :userID AND fr.type = 'ACCEPTED'")
    List<User> getAllFriends(@Param("userID") Long userID);
}

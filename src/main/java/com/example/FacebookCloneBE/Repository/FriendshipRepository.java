package com.example.FacebookCloneBE.Repository;

import com.example.FacebookCloneBE.Model.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, Long> {
}

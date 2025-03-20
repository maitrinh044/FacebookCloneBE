package com.example.FacebookCloneBE.Repository;

import com.example.FacebookCloneBE.Model.ChatGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatGroupRepository extends JpaRepository<ChatGroup, Long> {
}

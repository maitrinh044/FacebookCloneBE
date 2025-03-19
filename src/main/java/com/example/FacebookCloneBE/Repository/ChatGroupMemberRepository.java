package com.example.FacebookCloneBE.Repository;

import com.example.FacebookCloneBE.Model.ChatGroupMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatGroupMemberRepository extends JpaRepository<ChatGroupMember, Long> {
}

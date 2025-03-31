package com.example.FacebookCloneBE.Repository;

import com.example.FacebookCloneBE.Model.Message;
import com.example.FacebookCloneBE.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query("SELECT msg FROM Message msg WHERE " +
            "(msg.senderId.id = :senderId AND msg.receiverId.id = :receiverId) " +
            "OR (msg.senderId.id = :receiverId AND msg.receiverId.id = :senderId)")
    List<Message> getMessageByUsers(@Param("senderId") long senderId, @Param("receiverId") long receiverId);

    @Query("SELECT msg FROM Message msg WHERE " +
            "(msg.senderId.id = :senderId AND msg.receiverId.id = :receiverId) " +
            "OR (msg.senderId.id = :receiverId AND msg.receiverId.id = :senderId)" +
            "ORDER BY msg.sendAt DESC LIMIT 1")
    Message getLastMessageForUser(@Param("senderId") long senderId, @Param("receiverId") long receiverId);
}

package com.example.FacebookCloneBE.Repository;

import com.example.FacebookCloneBE.Model.Message;
import com.example.FacebookCloneBE.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query("SELECT msg FROM Message msg WHERE " +
            "(msg.senderId.id = :senderID AND msg.receiverId.id = :receiverID) " +
            "OR (msg.senderId.id = :receiverID AND msg.receiverId.id = :senderID)")
    List<Message> getMessageByUsers(long senderID, long receiverID);

}

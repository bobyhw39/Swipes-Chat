package com.enigma.swipeschat.repository;

import com.enigma.swipeschat.entity.ChatMessage;
import com.enigma.swipeschat.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRepository extends JpaRepository<ChatMessage,Long> {
    List<ChatMessage> findAllByRoom(Group room);
    List<ChatMessage> findAllBySenderAndReceiver(String sender, String receiver);
    List<ChatMessage> findAllByReceiverAndSender(String receiver, String sender);

    @Query(value = "SELECT tbl_chatmessage.* FROM \n" +
            "(SELECT MAX(id) AS id \n" +
            "         FROM tbl_chatmessage \n" +
            "         WHERE :sender IN (sender,receiver)\n" +
            "         GROUP BY IF (:sender = sender,receiver,sender)) AS latest\n" +
            "LEFT JOIN tbl_chatmessage USING(id)", nativeQuery=true )
    List<ChatMessage> findAll(@Param("sender") String sender);

}

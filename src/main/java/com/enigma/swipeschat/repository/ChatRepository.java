package com.enigma.swipeschat.repository;

import com.enigma.swipeschat.entity.ChatMessage;
import com.enigma.swipeschat.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<ChatMessage,Long> {
    List<ChatMessage> findAllByRoom(Group room);
    List<ChatMessage> findAllBySenderAndReceiver(String sender, String receiver);
    List<ChatMessage> findAllByReceiverAndSender(String receiver, String sender);
}

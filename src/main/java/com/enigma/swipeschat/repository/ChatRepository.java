package com.enigma.swipeschat.repository;

import com.enigma.swipeschat.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<ChatMessage,Long> {
    List<ChatMessage> findAllByRoom(String room);
}

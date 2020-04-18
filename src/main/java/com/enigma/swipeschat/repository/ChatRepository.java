package com.enigma.swipeschat.repository;

import com.enigma.swipeschat.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<ChatMessage,Long> {
}

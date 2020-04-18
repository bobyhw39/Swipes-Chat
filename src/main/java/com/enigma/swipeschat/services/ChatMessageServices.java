package com.enigma.swipeschat.services;

import com.enigma.swipeschat.entity.ChatMessage;
import com.enigma.swipeschat.repository.ChatRepository;
import com.google.common.annotations.GwtCompatible;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Service;

import java.util.List;

@GwtCompatible
@Service
public class ChatMessageServices {

    @Autowired
    ChatRepository chatRepository;



    public void saveChat(ChatMessage chatMessage){
        chatRepository.save(chatMessage);
    }

    public List<ChatMessage> getHistory(){
        return chatRepository.findAll();
    }
}

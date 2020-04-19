package com.enigma.swipeschat.services;

import com.enigma.swipeschat.entity.ChatMessage;
import com.enigma.swipeschat.repository.ChatRepository;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@GwtCompatible
@Service
public class ChatMessageServices {

    @Autowired
    ChatRepository chatRepository;

    @Autowired
    SimpUserRegistry userRegistry;

    public void saveChat(ChatMessage chatMessage){
        chatRepository.save(chatMessage);
    }

    public List<ChatMessage> getHistory(){
        return chatRepository.findAll();
    }

    public Set<SimpUser> getUsers() {
        return userRegistry.getUsers();
    }

}

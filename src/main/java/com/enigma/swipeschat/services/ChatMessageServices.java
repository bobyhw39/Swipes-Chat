package com.enigma.swipeschat.services;

import com.enigma.swipeschat.dto.UserGetDTO;
import com.enigma.swipeschat.entity.ChatMessage;
import com.enigma.swipeschat.entity.User;
import com.enigma.swipeschat.repository.ChatRepository;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@GwtCompatible
@Service
public class ChatMessageServices {

    private static final Logger logger = LoggerFactory.getLogger(ChatMessageServices.class);

    @Autowired
    ChatRepository chatRepository;

    @Autowired
    SimpUserRegistry userRegistry;

    public void saveChat(ChatMessage chatMessage){
        chatRepository.save(chatMessage);
    }

    public List<ChatMessage> getHistory(String room){
        logger.info("get history of " + room);
        return chatRepository.findAllByRoom(room);
    }

    public Set<SimpUser> getUsers() {
        return userRegistry.getUsers();
    }



}

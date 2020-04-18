package com.enigma.swipeschat.controller;

import com.enigma.swipeschat.entity.ChatMessage;
import com.enigma.swipeschat.services.ChatMessageServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@Controller
@CrossOrigin(origins = "*")
public class ChatController {

    @Autowired
    ChatMessageServices chatMessageServices;

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    ////////////////////////Group Chat Controller/////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        chatMessageServices.saveChat(chatMessage);
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }

//    @Autowired
//    private SimpUserRegistry simpUserRegistry;
//
//    @GetMapping("/allUser")
//    public Set<SimpUser> getNumberOfSessions() {
//        return simpUserRegistry.getUsers();
//    }
    ///////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////

    ////////////////////////Private Chat Controller///////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////



}

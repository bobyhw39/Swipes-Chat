package com.enigma.swipeschat.controller;

import com.enigma.swipeschat.entity.ChatMessage;
import com.enigma.swipeschat.exceptions.NotFoundException;
import com.enigma.swipeschat.services.ChatMessageServices;
import com.enigma.swipeschat.services.UserServices;
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

import static java.lang.String.format;

@Controller
@CrossOrigin(origins = "*")
public class ChatController {

    @Autowired
    ChatMessageServices chatMessageServices;

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    UserServices userServices;

    ////////////////////////Group Chat Controller/////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////
    @MessageMapping("/chat/{roomId}/sendMessage")
//    @SendTo("/topic/public")
    public ChatMessage sendMessage(@DestinationVariable String roomId,@Payload ChatMessage chatMessage) {

        if(userServices.getUser(chatMessage.getSender())==null){
            throw new NotFoundException("User " + chatMessage.getSender() +" not found");
        }
        chatMessage.setRoom(roomId);
        chatMessageServices.saveChat(chatMessage);
        simpMessagingTemplate.convertAndSend(format("/channel/%s", roomId), chatMessage);
        return chatMessage;
    }


    @MessageMapping("/chat/{roomId}/addUser")
//    @SendTo("/topic/public")
    public ChatMessage addUser(@DestinationVariable String roomId,@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {

        if(userServices.getUser(chatMessage.getSender())==null){
            throw new NotFoundException("User " + chatMessage.getSender() +" not found");
        }

        String currentRoomId = (String) headerAccessor.getSessionAttributes().put("room_id", roomId);
        if (currentRoomId != null) {
            ChatMessage leaveMessage = new ChatMessage();
            leaveMessage.setType(ChatMessage.MessageType.LEAVE);
            leaveMessage.setSender(chatMessage.getSender());
            leaveMessage.setRoom(roomId);
            simpMessagingTemplate.convertAndSend(format("/channel/%s", currentRoomId), leaveMessage);
        }
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        simpMessagingTemplate.convertAndSend(format("/channel/%s", roomId), chatMessage);
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

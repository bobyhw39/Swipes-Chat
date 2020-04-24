package com.enigma.swipeschat.controller;

import com.enigma.swipeschat.entity.ChatMessage;
import com.enigma.swipeschat.services.ChatMessageServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.broker.SimpleBrokerMessageHandler;
import org.springframework.messaging.simp.broker.SubscriptionRegistry;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.messaging.DefaultSimpUserRegistry;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import com.enigma.swipeschat.dto.ChatMessageGetHistoryDTO;

@RestController
@Api(value = "Swipes Chat Application")
@CrossOrigin(origins = "")
public class ChatRESTController {
    @Autowired
    ChatMessageServices chatMessageServices;

//    private final SimpUserRegistry simpUserRegistry;
//
//    public ChatRESTController(SimpUserRegistry simpUserRegistry) {
//        this.simpUserRegistry = simpUserRegistry;
//    }

final private SimpUserRegistry simpUserRegistry = new DefaultSimpUserRegistry();

//    @GetMapping("/ws/users")
//    public List<String> connectedEquipments() {
//        return simpUserRegistry
//                .getUsers()
//                .stream()
//                .map(SimpUser::getName)
//                .collect(Collectors.toList());
//    }
    @GetMapping("/ws/users")
    public Set<SimpUser> connectedEquipments() {
        System.out.println("check here");
        System.out.println(chatMessageServices.getUsers().toString());
        return chatMessageServices.getUsers();
    }

    ////////////////////////Message queries DB controller/////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/getAllChat/{room}")
    @ApiOperation(value = "View a history chat from group", response = ChatMessage.class)
    public List<ChatMessage> getAllChat(@PathVariable Long room){
        return chatMessageServices.getHistory(room);
    }

    @GetMapping("/historyPrivate/{user1}/{user2}")
    @ApiOperation(value = "View a history chat from private chat", response = ChatMessage.class)
    public List<ChatMessage> getAllChatPrivate(@PathVariable String user1,@PathVariable String user2){
        return chatMessageServices.getHistoryPrivate(user1,user2);
    }
    ///////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////

}

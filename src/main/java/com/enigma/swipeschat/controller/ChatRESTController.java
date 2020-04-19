package com.enigma.swipeschat.controller;

import com.enigma.swipeschat.entity.ChatMessage;
import com.enigma.swipeschat.services.ChatMessageServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.broker.SimpleBrokerMessageHandler;
import org.springframework.messaging.simp.broker.SubscriptionRegistry;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.messaging.DefaultSimpUserRegistry;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
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

    @GetMapping("/getAllChat")
    public List<ChatMessage> getAllChat(){
        return chatMessageServices.getHistory();
    }

    ///////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////

}

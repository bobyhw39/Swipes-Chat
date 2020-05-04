package com.enigma.swipeschat.controller;

import com.enigma.swipeschat.entity.ChatMessage;
import com.enigma.swipeschat.services.ChatMessageServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "Swipes Chat Application")
@CrossOrigin(origins = "")
public class ChatRESTController {
    @Autowired
    ChatMessageServices chatMessageServices;


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

    @GetMapping("/lastchat/{username}")
    @ApiOperation(value = "View a last chat", response = ChatMessage.class)
    public List<ChatMessage> lastChat(@PathVariable String username){
        return chatMessageServices.getLastChat(username);
    }
}

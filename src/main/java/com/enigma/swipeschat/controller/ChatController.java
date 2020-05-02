package com.enigma.swipeschat.controller;

import com.enigma.swipeschat.entity.ChatMessage;
import com.enigma.swipeschat.entity.Group;
import com.enigma.swipeschat.exceptions.NotFoundException;
import com.enigma.swipeschat.services.ChatMessageServices;
import com.enigma.swipeschat.services.UserServices;
import com.enigma.swipeschat.services.GroupServices;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
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

    @Autowired
    GroupServices groupServices;

    private Gson gson = new Gson();

    ////////////////////////Group Chat Controller/////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////
    @MessageMapping("/ws/group/{roomId}/sendMessageGroup")
//    @SubscribeMapping
//    @SendTo("/topic/public")
    public ChatMessage sendMessageGroup(@DestinationVariable String roomId,@Payload ChatMessage chatMessage) {

//        if(userServices.getUser(chatMessage.getSender())==null){
//            throw new NotFoundException("User " + chatMessage.getSender() +" not found");
//        }
//
//        if(groupServices.getGroup(roomId)==null){
//            throw new NotFoundException("Group " + roomId +" not found");
//        }

//        Group group = groupServices.getGroup(Long.parseLong(roomId));
//        chatMessage.setRoom(group);
        chatMessageServices.saveChat(chatMessage);
//        simpMessagingTemplate.convertAndSend(format("/channel/%s", roomId), chatMessage);
        simpMessagingTemplate.convertAndSend( "/topic/messages/group/"+roomId, chatMessage);
        System.out.println(chatMessage);
        return chatMessage;
    }


    @MessageMapping("/ws/group/{roomId}/addUser")
//    @SubscribeMapping("/chat/{roomId}/addUser")
//    @SendTo("/topic/public")
    public ChatMessage addUserGroup(@DestinationVariable Long roomId,@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {

        if(userServices.getUser(chatMessage.getSender())==null){
            throw new NotFoundException("User " + chatMessage.getSender() +" not found");
        }

        if(groupServices.getGroup(roomId)==null){
            throw new NotFoundException("Group " + roomId +" not found");
        }

        Group group = groupServices.getGroup(roomId);

        String currentRoomId = (String) headerAccessor.getSessionAttributes().put("room_id", roomId);
        if (currentRoomId != null) {
            ChatMessage leaveMessage = new ChatMessage();
            leaveMessage.setType(ChatMessage.MessageType.LEAVE);
            leaveMessage.setSender(chatMessage.getSender());
            leaveMessage.setRoom(group);
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

//    ////////////////////////Private Chat Controller///////////////////////////////////
//    //////////////////////////////////////////////////////////////////////////////////
//
//    @MessageMapping("/sendPrivateMessage")
////    @SendTo("/queue/reply")
//    public void sendPrivateMessage(@Payload ChatMessage chatMessage) {
//        simpMessagingTemplate.convertAndSendToUser(chatMessage.getReceiver().trim(), "/reply", chatMessage);
//        chatMessageServices.saveChat(chatMessage);
//        System.out.println("send message");
//        System.out.println(chatMessage.toString());
//        //return chatMessage;
//    }
//
//    @MessageMapping("/addPrivateUser")
//    @SendTo("/queue/reply")
//    public ChatMessage addPrivateUser(@Payload ChatMessage chatMessage,
//                                      SimpMessageHeaderAccessor headerAccessor) {
//        // Add user in web socket session
//        headerAccessor.getSessionAttributes().put("private-username", chatMessage.getSender());
//        System.out.println("user join");
//        System.out.println(chatMessage.toString());
//        return chatMessage;
//    }
//
//    ///////////////////////////////////////////////////////////////////////////////////
//    //////////////////////////////////////////////////////////////////////////////////

    ////////////////////////Private Chat Controller///////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////

    @MessageMapping("/ws/{to}")
    public void sendMessage(@DestinationVariable String to, ChatMessage message) {
        System.out.println("handling send message: " + message + " to: " + to);
            simpMessagingTemplate.convertAndSend("/topic/messages/" + to, message);
            chatMessageServices.saveChat(message);
//        System.out.println("send message");
//        System.out.println(chatMessage.toString());
    }

    @MessageMapping("/addPrivateUser")
    @SendTo("/queue/reply")
    public ChatMessage addPrivateUser(@Payload ChatMessage chatMessage,
                                      SimpMessageHeaderAccessor headerAccessor) {
        // Add user in web socket session
        headerAccessor.getSessionAttributes().put("private-username", chatMessage.getSender());
        System.out.println("user join");
        System.out.println(chatMessage.toString());
        return chatMessage;
    }

    ///////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////



}

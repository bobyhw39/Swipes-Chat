package com.enigma.swipeschat.services;

import com.enigma.swipeschat.dto.UserGetDTO;
import com.enigma.swipeschat.entity.ChatMessage;
import com.enigma.swipeschat.entity.User;
import com.enigma.swipeschat.entity.Group;
import com.enigma.swipeschat.exceptions.NotFoundException;
import com.enigma.swipeschat.repository.ChatRepository;
import com.enigma.swipeschat.repository.GroupRepository;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.Collections;

@GwtCompatible
@Service
public class ChatMessageServices {

    private static final Logger logger = LoggerFactory.getLogger(ChatMessageServices.class);

    @Autowired
    ChatRepository chatRepository;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    SimpUserRegistry userRegistry;

    @Autowired
    UserServices userServices;

    //////////////////////////////////////////////////////////////////////
    ///Functions
    /////////////////////////////////////////////////////////////////////

    public void checkUser(String user){
        UserGetDTO check = userServices.getUser(user);
        if(check == null){
            throw new NotFoundException("account not found. check your username and password");
        }

    }

    //////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    public void saveChat(ChatMessage chatMessage){
        chatRepository.save(chatMessage);
    }

    public List<ChatMessage> getHistory(Long room){
        Group group = groupRepository.getOne(room);
        logger.info("get history of " + room);
        return chatRepository.findAllByRoom(group);
    }

    public List<ChatMessage> getHistoryPrivate(String user1,String user2){
        logger.info("get history between " + user1 +" "+user2);
        checkUser(user1);
        checkUser(user2);
        List<ChatMessage> chatA = chatRepository.findAllBySenderAndReceiver(user1,user2);
        List<ChatMessage> chatB = chatRepository.findAllByReceiverAndSender(user1,user2);
        Set<ChatMessage> chatMerge = Sets.newHashSet();
        chatMerge.addAll(chatA);
        chatMerge.addAll(chatB);
        List<ChatMessage> chatToSort = Lists.newArrayList();
        chatToSort.addAll(chatMerge);
        Collections.sort(chatToSort,ChatMessage.dateComparator);
        return chatToSort;
    }


    public Set<SimpUser> getUsers() {
        return userRegistry.getUsers();
    }



}

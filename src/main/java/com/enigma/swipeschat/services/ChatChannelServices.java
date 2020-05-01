package com.enigma.swipeschat.services;

import com.enigma.swipeschat.dto.UserDeleteFriendsDTO;
import com.enigma.swipeschat.exceptions.BadRequestException;
import com.enigma.swipeschat.exceptions.NotFoundException;
import com.enigma.swipeschat.repository.ChatRepository;
import com.enigma.swipeschat.repository.ChatChannelRepository;
import com.enigma.swipeschat.repository.GroupRepository;
import com.enigma.swipeschat.repository.UserRepository;
import com.enigma.swipeschat.entity.ChatChannel;
import com.enigma.swipeschat.entity.User;
import com.enigma.swipeschat.dto.UserPostFriendsDTO;
import com.enigma.swipeschat.dto.UserGetDTO;
import com.google.common.annotations.GwtCompatible;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Service;
import java.util.List;

@GwtCompatible
@Service
public class ChatChannelServices {

    private static final Logger logger = LoggerFactory.getLogger(ChatMessageServices.class);

    @Autowired
    ChatRepository chatRepository;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SimpUserRegistry userRegistry;

    @Autowired
    UserServices userServices;

    @Autowired
    ChatChannelRepository chatChannelRepository;


    //////////////////////////////////////////////////////////////////////
    ///Functions
    /////////////////////////////////////////////////////////////////////

    public void checkUser(String user){
        UserGetDTO check = userServices.getUser(user);
        if(check == null){
            throw new NotFoundException("account not found");
        }

    }

    //////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////

    public List<ChatChannel> getListFriends(String username){
        checkUser(username);
        User query = userRepository.findByUsername(username);
        List<ChatChannel> friends = chatChannelRepository.findAllByUserOne(query);
        return friends;
    }

    public ChatChannel addFriend(UserPostFriendsDTO userPostFriendsDTO){
        User user1 = userRepository.findByUsername(userPostFriendsDTO.getUser());
        User wantToAdd = userRepository.findByUsername(userPostFriendsDTO.getFriend());
        checkUser(userPostFriendsDTO.getUser());
        checkUser(userPostFriendsDTO.getFriend());
        ChatChannel check = chatChannelRepository.findByUserOneAndUserTwo(user1,wantToAdd);
        if(check!=null){
                throw new BadRequestException("User  : " + userPostFriendsDTO.getFriend() + " Already added as friend");
        }
        ChatChannel chatChannel = new ChatChannel(null,user1,wantToAdd);
        chatChannelRepository.save(chatChannel);
        return chatChannel;
    }

    public ChatChannel deleteFriend(UserDeleteFriendsDTO userDeleteFriendsDTO){
        checkUser(userDeleteFriendsDTO.getUser());
        checkUser(userDeleteFriendsDTO.getFriend());
        User user = userRepository.findByUsername(userDeleteFriendsDTO.getUser());
        User friend = userRepository.findByUsername(userDeleteFriendsDTO.getFriend());
        ChatChannel chatChannel = chatChannelRepository.findByUserOneAndUserTwo(user,friend);
        chatChannelRepository.delete(chatChannel);
        return chatChannel;
    }

}

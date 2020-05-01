package com.enigma.swipeschat.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.enigma.swipeschat.services.ChatChannelServices;
import com.enigma.swipeschat.entity.ChatChannel;
import com.enigma.swipeschat.dto.UserPostFriendsDTO;
import com.enigma.swipeschat.dto.UserDeleteFriendsDTO;
import com.enigma.swipeschat.exceptions.ErrorDetails;

import java.sql.Date;
import java.util.List;

@RestController
@Api(value = "Swipes Chat Application")
@CrossOrigin(origins = "")
@RequestMapping("friends")
public class ChatChannelRESTController {

    @Autowired
    ChatChannelServices chatChannelServices;

    @GetMapping("/{username}")
    public List<ChatChannel> getListFriends(@PathVariable String username){
        return chatChannelServices.getListFriends(username);
    }

    @PostMapping("/addFriend")
    public ChatChannel addFriends(@RequestBody UserPostFriendsDTO userPostFriendsDTO){
        return chatChannelServices.addFriend(userPostFriendsDTO);
    }

    @DeleteMapping("/delete")
    public ErrorDetails unFriend(@RequestBody UserDeleteFriendsDTO userDeleteFriendsDTO){
        chatChannelServices.deleteFriend(userDeleteFriendsDTO);
        return new ErrorDetails(new Date(System.currentTimeMillis()),"Unfriend "+userDeleteFriendsDTO.getFriend()+" success","200","friends/delete");
    }


}

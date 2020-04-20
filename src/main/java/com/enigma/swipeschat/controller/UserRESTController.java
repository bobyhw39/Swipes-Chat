package com.enigma.swipeschat.controller;

import com.enigma.swipeschat.dto.UserDeleteFriendsDTO;
import com.enigma.swipeschat.dto.UserGetDTO;
import com.enigma.swipeschat.dto.UserPostFriendsDTO;
import com.enigma.swipeschat.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserRESTController {

    @Autowired
    UserServices userServices;

    @GetMapping("/{username}")
    public UserGetDTO getUser(@Valid @PathVariable String username){
        return userServices.getUser(username);
    }

    @GetMapping("/search")
    public List<UserGetDTO> searchUser (@RequestParam String username){
        return userServices.searchUser(username);
    }

    @PostMapping("/addFriend")
    public String addFriend(@RequestBody UserPostFriendsDTO userPostFriendsDTO){
        userServices.addFriend(userPostFriendsDTO);
        return "add friend success";
    }

    @GetMapping("/friends/{username}")
    public List<UserGetDTO> listFriends(@Valid @PathVariable String username){
        return userServices.listFriends(username);
    }
    @DeleteMapping("/friends/delete/")
    public String delete(@RequestBody UserDeleteFriendsDTO user){
        return userServices.deleteFriend(user);
    }
}

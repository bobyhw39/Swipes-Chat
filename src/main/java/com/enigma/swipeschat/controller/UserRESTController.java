package com.enigma.swipeschat.controller;

import com.enigma.swipeschat.dto.UserDeleteFriendsDTO;
import com.enigma.swipeschat.dto.UserGetDTO;
import com.enigma.swipeschat.dto.UserPostDTO;
import com.enigma.swipeschat.dto.UserPostFriendsDTO;
import com.enigma.swipeschat.dto.UserPostGroupDTO;
import com.enigma.swipeschat.services.UserServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.enigma.swipeschat.exceptions.ErrorDetails;
import com.enigma.swipeschat.dto.UserPostLoginDTO;
import com.enigma.swipeschat.entity.User;

import javax.validation.Valid;
import java.sql.Date;
import java.util.List;

@RestController
@Api(value = "Swipes Chat Application")
@RequestMapping("user")
public class UserRESTController {

    @Autowired
    UserServices userServices;

    @PostMapping("")
    @ApiOperation(value = "Create a account", response = UserPostDTO.class)
    public ErrorDetails createUser(@RequestBody UserPostDTO userPostDTO){
        userServices.createUser(userPostDTO);
        return new ErrorDetails(new Date(System.currentTimeMillis()),"Created account success","200","user");
    }

    @ApiOperation(value = "View a info user", response = UserGetDTO.class)
    @GetMapping("/{username}")
    public UserGetDTO getUser(@Valid @PathVariable String username){
        return userServices.getUser(username);
    }

    @GetMapping("/search")
    @ApiOperation(value = "Search a User", response = UserGetDTO.class)
    public List<UserGetDTO> searchUser (@RequestParam String username){
        return userServices.searchUser(username);
    }

    @PostMapping("/login")
    @ApiOperation(value = "User login", response = UserPostLoginDTO.class)
    public ErrorDetails login(@RequestBody UserPostLoginDTO userPostLoginDTO){
        return userServices.login(userPostLoginDTO);
    }

    @PostMapping("/createGroup")
    @ApiOperation(value = "User create a group", response = UserPostLoginDTO.class)
    public ErrorDetails createGroup(@RequestBody UserPostGroupDTO userPostGroupDTO){
        return userServices.createGroup(userPostGroupDTO);
    }

}

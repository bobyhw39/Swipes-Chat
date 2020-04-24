package com.enigma.swipeschat.controller;

import com.enigma.swipeschat.dto.GroupDeleteUserDTO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.enigma.swipeschat.services.GroupServices;
import com.enigma.swipeschat.entity.Group;
import com.enigma.swipeschat.exceptions.ErrorDetails;
import com.enigma.swipeschat.dto.GroupPostUserDTO;

import javax.validation.Valid;
import java.util.List;
import java.sql.Date;

@RestController
@Api(value = "Swipes Chat Application")
@RequestMapping("group")
public class GroupRESTController {

    @Autowired
    GroupServices groupServices;

    @GetMapping("/{id}")
    public Group getGroup(@Valid @PathVariable Long id){
        return groupServices.getGroup(id);
    }

    @GetMapping("/user/{username}")
    public List<Group> groupList(@Valid @PathVariable String username){
        return groupServices.getListGroupFromUsername(username);
    }

    @PostMapping("/addUser")
    public ErrorDetails addUser(GroupPostUserDTO groupPostUserDTO){
        groupServices.addUser(groupPostUserDTO);
        return new ErrorDetails(new Date(System.currentTimeMillis()),"Add "+groupPostUserDTO.getUsername(),"200","group/addUser");
    }

    @DeleteMapping("/deleteUser")
    public ErrorDetails deleteUser(GroupDeleteUserDTO groupDeleteUserDTO){
        groupServices.deleteUser(groupDeleteUserDTO);
        return new ErrorDetails(new Date(System.currentTimeMillis()),"Delete "+groupDeleteUserDTO.getUsername(),"200","group/deleteUser");
    }

}

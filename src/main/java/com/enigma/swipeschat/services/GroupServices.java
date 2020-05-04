package com.enigma.swipeschat.services;

import com.enigma.swipeschat.dto.UserGetDTO;
import com.enigma.swipeschat.exceptions.NotFoundException;
import com.google.common.annotations.GwtCompatible;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.enigma.swipeschat.repository.GroupRepository;
import com.enigma.swipeschat.repository.UserRepository;
import com.enigma.swipeschat.entity.Group;
import com.enigma.swipeschat.entity.User;
import com.enigma.swipeschat.dto.GroupPostUserDTO;
import com.enigma.swipeschat.dto.GroupDeleteUserDTO;
import java.util.List;

@GwtCompatible
@Service
public class GroupServices {

    private static final Logger logger = LoggerFactory.getLogger(GroupServices.class);

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserServices userServices;

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

    public Group getGroup(Long id){
        Group group = groupRepository.getOne(id);
        return group;
    }

    public Group addUser(GroupPostUserDTO groupPostUserDTO){
        Group group = getGroup(groupPostUserDTO.getIdGroup());
        checkUser(groupPostUserDTO.getUsername());
        User user = userRepository.findByUsername(groupPostUserDTO.getUsername());
        if(user == null){
            throw new NotFoundException("account not found. check your username and password");
        }
        if(group == null){
            throw new NotFoundException("group not found");
        }
        List<User> userList = group.getUser();
        userList.add(user);
        System.out.println(userList);
        groupRepository.save(new Group(group.getId(),group.getName(),group.getDescription(),userList,null));
        return new Group(group.getId(),group.getName(),group.getDescription(),userList,null);
    }
//
    public Group deleteUser(GroupDeleteUserDTO groupDeleteUserDTO){
        Group group = getGroup(groupDeleteUserDTO.getIdGroup());
        User user = userRepository.findByUsername(groupDeleteUserDTO.getUsername());
        if(user == null){
            throw new NotFoundException("account not found. check your username and password");
        }
        if(group == null){
            throw new NotFoundException("group not found");
        }
        List<User> userList = group.getUser();
        userList.remove(user);
        groupRepository.save(new Group(group.getId(),group.getName(),group.getDescription(),userList,null));
        return new Group(group.getId(),group.getName(),group.getDescription(),userList,null);
    }

    public List<Group> getListGroupFromUsername(String username){
        User usr = userRepository.findByUsername(username);
        List<Group> groupList = groupRepository.findGroupByUser(usr);
        return groupList;
    }




}

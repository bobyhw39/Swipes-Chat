package com.enigma.swipeschat.services;

import com.enigma.swipeschat.dto.UserDeleteFriendsDTO;
import com.enigma.swipeschat.dto.UserGetDTO;
import com.enigma.swipeschat.dto.UserPostFriendsDTO;
import com.enigma.swipeschat.entity.User;
import com.enigma.swipeschat.entity.Group;
import com.enigma.swipeschat.repository.UserRepository;
import com.enigma.swipeschat.repository.GroupRepository;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Lists;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.enigma.swipeschat.dto.UserPostLoginDTO;
import com.enigma.swipeschat.dto.UserPostDTO;
import com.enigma.swipeschat.dto.UserPostGroupDTO;
import com.enigma.swipeschat.exceptions.NotFoundException;
import com.enigma.swipeschat.exceptions.BadRequestException;
import com.enigma.swipeschat.exceptions.ErrorDetails;

import java.sql.Date;
import java.util.List;

@GwtCompatible
@Service
public class UserServices {

    private static final Logger logger = LoggerFactory.getLogger(UserServices.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    GroupRepository groupRepository;


    public User createUser(UserPostDTO userPostDTO){
        User validateUser = userRepository.findByUsername(userPostDTO.getUsername());
        if(validateUser!=null){
            throw new BadRequestException("User  : " + userPostDTO.getUsername() + " Found Cannot Create Account");
        }
        User user = new User(null,userPostDTO.getEmail(),userPostDTO.getUsername(),userPostDTO.getPassword(),userPostDTO.getFullName(),null);
        userRepository.save(user);
        return user;
    }

    public UserGetDTO getUser(String username){
        logger.info("get user of " + username);
        User user = userRepository.findByUsername(username);
        ModelMapper modelMapper = new ModelMapper();
        UserGetDTO userGetDTO = modelMapper.map(user, UserGetDTO.class);
        return userGetDTO;
    }


    public List<UserGetDTO> searchUser(String username){
        List<UserGetDTO> userGetDTOList = Lists.newArrayList();
        List<User> users = userRepository.findByUsernameContainsIgnoreCase(username);
        for (User user: users) {
            ModelMapper modelMapper = new ModelMapper();
            UserGetDTO userGetDTO = modelMapper.map(user,UserGetDTO.class);
            userGetDTOList.add(userGetDTO);
        }
        logger.info("search user "+ username);
        return userGetDTOList;
    }

    public ErrorDetails login(UserPostLoginDTO userPostLoginDTO){
        User searchUser = userRepository.findByUsername(userPostLoginDTO.getUsername());
        if(userPostLoginDTO.getUsername().equals(searchUser.getUsername()) && userPostLoginDTO.getPassword().equals(searchUser.getPassword())){
            return new ErrorDetails(new Date(System.currentTimeMillis()),"Login success","200","user/login");
        }
        else{
            throw new NotFoundException("account not found. check your username and password");
        }
    }

    public ErrorDetails createGroup(UserPostGroupDTO userPostGroupDTO){
        User searchUser = userRepository.findByUsername(userPostGroupDTO.getUsername());
        if(searchUser == null){
            throw new NotFoundException("account not found. check your username and password");
        }
        List<User> userList = Lists.newArrayList();
        userList.add(searchUser);
        Group group = new Group(null,userPostGroupDTO.getName(),userPostGroupDTO.getDescription(),userList,null);
        groupRepository.save(group);
        return new ErrorDetails(new Date(System.currentTimeMillis()),"Create Group success","200","user/createGroup");
    }

}

package com.enigma.swipeschat.services;

import com.enigma.swipeschat.dto.UserDeleteFriendsDTO;
import com.enigma.swipeschat.dto.UserGetDTO;
import com.enigma.swipeschat.dto.UserPostFriendsDTO;
import com.enigma.swipeschat.entity.User;
import com.enigma.swipeschat.repository.UserRepository;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Lists;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@GwtCompatible
@Service
public class UserServices {

    private static final Logger logger = LoggerFactory.getLogger(UserServices.class);

    @Autowired
    UserRepository userRepository;

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

    public User addFriend(UserPostFriendsDTO userPostFriendsDTO){
        User searchFriend = userRepository.findByUsername(userPostFriendsDTO.getFriend());
        User abc = userRepository.findByUsername(userPostFriendsDTO.getUser());
        User users = userRepository.getOne(abc.getId());
        User friends = userRepository.findByUsername(searchFriend.getUsername());
        users.getFriends().add(friends);
        userRepository.save(users);
        return users;
    }

    public List<UserGetDTO> listFriends(String username){
        List<UserGetDTO> userGetDTOS = Lists.newArrayList();
        User users = userRepository.findByUsername(username);
        System.out.println(users.toString());
//        users.getFriends();
        for (User user: users.getFriends()) {
            ModelMapper modelMapper = new ModelMapper();
            UserGetDTO userGetDTO = modelMapper.map(user,UserGetDTO.class);
            userGetDTOS.add(userGetDTO);
        }
        return userGetDTOS;
    }

    public String deleteFriend(UserDeleteFriendsDTO userDeleteFriendsDTO){
        User searchUsers = userRepository.findByUsername(userDeleteFriendsDTO.getUser());
        User searchFriend = userRepository.findByUsername(userDeleteFriendsDTO.getFriend());
        User users = userRepository.getOne(searchUsers.getId());
        List<UserGetDTO> friends = listFriends(users.getUsername());
        System.out.println(friends.toString());
        if(friends!=null){
            friends.remove(searchFriend.getId());
            return  "delete success";
        }
        return  "gagal govlok";
    }

}

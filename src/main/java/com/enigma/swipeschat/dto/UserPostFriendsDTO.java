package com.enigma.swipeschat.dto;

import com.enigma.swipeschat.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserPostFriendsDTO {
    private String user;
    private String friend;
}

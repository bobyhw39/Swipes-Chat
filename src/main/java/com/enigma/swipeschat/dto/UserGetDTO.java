package com.enigma.swipeschat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserGetDTO {
    private long id;
    private String email;
    private String username;
    private String fullName;

}

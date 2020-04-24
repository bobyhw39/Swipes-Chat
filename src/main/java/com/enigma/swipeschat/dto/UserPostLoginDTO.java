package com.enigma.swipeschat.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserPostLoginDTO {
    private String username;
    private String password;
}

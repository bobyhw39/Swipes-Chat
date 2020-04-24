package com.enigma.swipeschat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GroupDeleteUserDTO {
    private Long idGroup;
    private String username;
}

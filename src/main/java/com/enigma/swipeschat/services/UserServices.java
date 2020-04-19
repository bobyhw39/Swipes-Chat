package com.enigma.swipeschat.services;

import com.enigma.swipeschat.repository.UserRepository;
import com.google.common.annotations.GwtCompatible;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@GwtCompatible
@Service
public class UserServices {

    @Autowired
    UserRepository userRepository;



}

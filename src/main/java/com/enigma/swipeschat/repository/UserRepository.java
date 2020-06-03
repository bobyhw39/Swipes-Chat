package com.enigma.swipeschat.repository;

import com.enigma.swipeschat.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
    List<User> findByUsernameContainsIgnoreCase(String username);
    User getById(Long id);

}

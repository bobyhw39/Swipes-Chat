package com.enigma.swipeschat.repository;

import com.enigma.swipeschat.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}

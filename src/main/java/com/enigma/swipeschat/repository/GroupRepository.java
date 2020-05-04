package com.enigma.swipeschat.repository;

import com.enigma.swipeschat.entity.Group;
import com.enigma.swipeschat.entity.User;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group,Long> {
    Group findByName(String name);
    List<Group> findGroupByUser(User user);
}

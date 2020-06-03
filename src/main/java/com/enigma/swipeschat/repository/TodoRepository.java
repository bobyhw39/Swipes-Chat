package com.enigma.swipeschat.repository;

import com.enigma.swipeschat.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo,Long> {
    Todo getById(Long id);
}

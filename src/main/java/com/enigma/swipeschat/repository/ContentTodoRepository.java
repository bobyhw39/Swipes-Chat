package com.enigma.swipeschat.repository;

import com.enigma.swipeschat.entity.ContentTodo;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.jpa.repository.JpaRepository;

@RestController
public interface ContentTodoRepository extends JpaRepository<ContentTodo,Long> {
    ContentTodo getById(Long id);
}

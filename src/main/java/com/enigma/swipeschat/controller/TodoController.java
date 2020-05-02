package com.enigma.swipeschat.controller;
import java.util.List;

import com.enigma.swipeschat.dto.TodoDto;
import com.enigma.swipeschat.entity.ContentTodo;
import com.enigma.swipeschat.entity.Todo;
import com.enigma.swipeschat.exceptions.BadRequestException;
import com.enigma.swipeschat.services.ContentTodoService;
import com.enigma.swipeschat.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("todo")
public class TodoController {

    @Autowired
    TodoService serv;

    @Autowired
    ContentTodoService servT;

    @GetMapping("")
    public List<Todo> getAllEvent(){
        return serv.getAllList();
    }

    @GetMapping("/{id}")
    public Todo getById(@PathVariable Long id) {
        return serv.getById(id);
    }

    @PostMapping("")
    public Todo create(@RequestBody TodoDto Todo) {
        return serv.create(Todo);
    }

    @PutMapping("/update")
    public Todo update(@RequestBody Todo Todo) throws BadRequestException {
        return serv.update(Todo);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        serv.delete(id);
    }

    @GetMapping("/content")
    public List<ContentTodo> getContent(){
        return servT.getAll();
    }

    @PostMapping("/content")
    public ContentTodo create(@RequestBody ContentTodo con) {
        return servT.create(con);
    }
}

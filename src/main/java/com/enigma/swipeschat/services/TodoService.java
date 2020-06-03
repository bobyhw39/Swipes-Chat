package com.enigma.swipeschat.services;

import java.util.List;

import com.enigma.swipeschat.dto.TodoDto;
import com.enigma.swipeschat.entity.ContentTodo;
import com.enigma.swipeschat.entity.Todo;
import com.enigma.swipeschat.entity.User;
import com.enigma.swipeschat.exceptions.BadRequestException;
import com.enigma.swipeschat.repository.TodoRepository;
import com.enigma.swipeschat.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class TodoService {

    @Autowired
    TodoRepository repo;

    @Autowired
    UserRepository repoUser;

    @Autowired
    ContentTodoService serv;

    public List<Todo> getAllList(){
        return repo.findAll();
    }

    public Todo getById(Long id) {
        return repo.getById(id);
    }

    public Todo create(TodoDto todos) {
        User usr = repoUser.getOne(todos.getUser().getId());
        ModelMapper mapper = new ModelMapper();
        Todo td = mapper.map(todos, Todo.class);
        repo.save(td);
        td.setUser(usr);
        setContentTodo(todos.getContent(), td);
        return td;
    }

    public Todo update(Todo todos) throws BadRequestException {
        Todo todo = repo.getById(todos.getId());
        todo.setTitle(todos.getTitle());
        todo.setContent(todos.getContent());
        for (ContentTodo contentTodo : todos.getContent()) {
            contentTodo.setTodo(todo);
            serv.update(contentTodo);
        }

        return repo.saveAndFlush(todo);
    }

    public void delete(Long id) {
        System.out.println("Succes delete "+ id);
        repo.deleteById(id);
    }

    public void setContentTodo(List<ContentTodo> list, Todo td) {
        for (ContentTodo contentTodo : list) {
            setContent(contentTodo, td);
            serv.create(contentTodo);
        }
    }
    public void setContent (ContentTodo content, Todo td) {
        content.setTodo(td);
    }
}

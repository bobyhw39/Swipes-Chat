//package com.enigma.swipeschat.controller;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.event.eventtodo.entity.ContentTodo;
//import com.example.event.eventtodo.entity.Todo;
//import com.example.event.eventtodo.service.ContentTodoService;
//import com.example.event.eventtodo.service.TodoService;
//
//@RestController
//@RequestMapping("todo/")
//public class TodoController {
//
//    @Autowired
//    TodoService serv;
//
//    @Autowired
//    ContentTodoService servT;
//
//    @GetMapping("")
//    public List<Todo> getAllEvent(){
//        return serv.getAllList();
//    }
//
//    @GetMapping("{id}")
//    public Todo getById(@PathVariable Long id) {
//        return serv.getById(id);
//    }
//
//    @PostMapping("")
//    public Todo create(@RequestBody Todo Todo) {
//        return serv.create(Todo);
//    }
//
//    @PutMapping("update")
//    public Todo update(@RequestBody Todo Todo) {
//        return serv.update(Todo);
//    }
//
//    @DeleteMapping("{id}")
//    public void delete(@PathVariable Long id) {
//        serv.delete(id);
//    }
//
//    @GetMapping("/content")
//    public List<ContentTodo> getContent(){
//        return servT.getAll();
//    }
//
//    @PostMapping("/content")
//    public ContentTodo create(@RequestBody ContentTodo con) {
//        return servT.create(con);
//    }
//}

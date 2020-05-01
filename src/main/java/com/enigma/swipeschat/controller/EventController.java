//package com.enigma.swipeschat.controller;
//
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
//import com.example.event.eventtodo.entity.Events;
//import com.example.event.eventtodo.entity.Todo;
//import com.example.event.eventtodo.service.EventService;
//
//@RestController
//@RequestMapping("event/")
//public class EventController {
//
//    @Autowired
//    EventService serv;
//
//    @GetMapping("")
//    public List<Events> getAllEvent(){
//        return serv.getAllList();
//    }
//    @GetMapping("{id}")
//    public Events getById(@PathVariable Long id) {
//        return serv.getById(id);
//    }
//
//    @PostMapping("")
//    public Events create(@RequestBody Events event) {
//        return serv.create(event);
//    }
//
//    @PutMapping("update")
//    public Events update(@RequestBody Events event) {
//        return serv.update(event);
//    }
//
//    @DeleteMapping("{id}")
//    public void delete(@PathVariable Long id) {
//        serv.delete(id);
//    }
//}

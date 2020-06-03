package com.enigma.swipeschat.controller;

import java.util.List;

import com.enigma.swipeschat.dto.EventDto;
import com.enigma.swipeschat.entity.Events;
import com.enigma.swipeschat.services.EventService;
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
@RequestMapping("event")
public class EventController {

    @Autowired
    EventService serv;

    @GetMapping("")
    public List<Events> getAllEvent(){
        return serv.getAllList();
    }
    @GetMapping("/{id}")
    public Events getById(@PathVariable Long id) {
        return serv.getById(id);
    }

    @PostMapping("")
    public Events create(@RequestBody EventDto event) {
        return serv.create(event);
    }

    @PutMapping("/update")
    public Events update(@RequestBody Events event) {
        return serv.update(event);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        serv.delete(id);
    }
}

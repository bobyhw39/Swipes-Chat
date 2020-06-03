package com.enigma.swipeschat.services;

import java.util.List;

import com.enigma.swipeschat.dto.EventDto;
import com.enigma.swipeschat.entity.Events;
import com.enigma.swipeschat.entity.User;
import com.enigma.swipeschat.repository.EventRepository;
import com.enigma.swipeschat.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    @Autowired
    EventRepository repo;

    @Autowired
    UserRepository repoUser;

    public List<Events> getAllList(){
        return repo.findAll();
    }

    public Events getById(Long id) {
        return repo.getById(id);
    }

    public Events create(EventDto event) {
        User usr = repoUser.getOne(event.getUser().getId());
        ModelMapper mapper = new ModelMapper();
        Events even = mapper.map(event, Events.class);
        even.setUser(usr);
        return repo.save(even);
    }

    public Events update(Events event) {
        Events evt = repo.getById(event.getId());
        evt.setTitle(event.getTitle());
        evt.setStartEvent(event.getStartEvent());
        evt.setEndEvent(event.getEndEvent());
        evt.setDescription(event.getDescription());
        evt.setLocation(event.getLocation());
        return repo.saveAndFlush(evt);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}


package com.enigma.swipeschat.services;

import java.util.List;

import com.enigma.swipeschat.entity.ContentTodo;
import com.enigma.swipeschat.exceptions.BadRequestException;
import com.enigma.swipeschat.repository.ContentTodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class ContentTodoService {

    @Autowired
    ContentTodoRepository repo;

    public ContentTodo getById(Long id) {
        return repo.getById(id);
    }

    public List<ContentTodo> getAll() {
        return repo.findAll();
    }

    public ContentTodo create(ContentTodo con) {
        return repo.save(con);
    }

    public List<ContentTodo> create(List<ContentTodo> con) {
        return repo.saveAll(con);
    }

    public ContentTodo update(ContentTodo con) throws BadRequestException {
        ContentTodo cont = repo.getById(con.getId());
        contentTodoNotExist(cont.getId());
        setContentAndCentang(cont, con);
        return repo.saveAndFlush(cont);

    }

    public void setContentAndCentang(ContentTodo ct, ContentTodo cc) {
        ct.setContent(cc.getContent());
        ct.setCentang(cc.getCentang());
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public void contentTodoNotExist(Long id) throws BadRequestException {
        if(repo.getById(id)== null) {
            throw new BadRequestException("Content is Null");
        }
    }

}

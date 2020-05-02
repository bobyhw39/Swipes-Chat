package com.enigma.swipeschat.dto;

import com.enigma.swipeschat.entity.ContentTodo;
import com.enigma.swipeschat.entity.User;

import java.util.List;


public class TodoDto {

    private Long id;
    private String title;
    private List<ContentTodo> content;
    private User user;
    public TodoDto() {
        // TODO Auto-generated constructor stub
    }
    public TodoDto(String title, List<ContentTodo> content, User user) {
        super();
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public TodoDto(Long id, String title, List<ContentTodo> content, User user) {
        super();
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public List<ContentTodo> getContent() {
        return content;
    }
    public void setContent(List<ContentTodo> content) {
        this.content = content;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

}

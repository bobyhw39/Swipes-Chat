package com.enigma.swipeschat.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.enigma.swipeschat.entity.User;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "todo")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @OneToMany(mappedBy = "todo", orphanRemoval = true )
    @Cascade(CascadeType.PERSIST)
    private List<ContentTodo> content;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    public Todo() {
        // TODO Auto-generated constructor stub
    }

    public Todo(String title, List<ContentTodo> content, User user) {
        super();
        this.title = title;
        this.content = content;
        this.user = user;
    }



    public Todo(Long id, String title, List<ContentTodo> content, User user) {
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

    @Override
    public String toString() {
        return "Todo [title=" + title + ", content=" + content + ", user=" + user + "]";
    }



}

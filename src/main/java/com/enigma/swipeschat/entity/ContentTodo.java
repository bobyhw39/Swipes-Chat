package com.enigma.swipeschat.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "content_todo")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class ContentTodo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "centang")
    private Integer centang;

    @JsonIgnore
    @ManyToOne
//	@Cascade(CascadeType.PERSIST)
    @JoinColumn(name = "todo_id")
    private Todo todo;


    public ContentTodo() {
        // TODO Auto-generated constructor stub
    }

    public ContentTodo(String content, Integer centang) {
        super();
        this.content = content;
        this.centang = centang;
    }



    public ContentTodo(Long id, String content, Integer centang) {
        super();
        this.id = id;
        this.content = content;
        this.centang = centang;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public Todo getTodo() {
        return todo;
    }

    public void setTodo(Todo todo) {
        this.todo = todo;
    }

    public Integer getCentang() {
        return centang;
    }

    public void setCentang(Integer centang) {
        this.centang = centang;
    }

}

package com.enigma.swipeschat.entity;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "events")
public class Events {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title")
    private String title;

    @Column(name="start_date")
    private Timestamp startEvent;

    @Column(name = "end_event")
    private Timestamp endEvent;

    @Column(name="location")
    private String location;

    @Column(name= "description")
    private String description;


    @ManyToOne
    @JoinColumn(name="id_user")
    private User user;

    public Events() {
        // TODO Auto-generated constructor stub
    }

    public Events(String title, Timestamp startEvent, Timestamp endEvent, String location, String description) {
        super();
        this.title = title;
        this.startEvent = startEvent;
        this.endEvent = endEvent;
        this.location = location;
        this.description = description;
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

    public Timestamp getStartEvent() {
        return startEvent;
    }

    public void setStartEvent(Timestamp startEvent) {
        this.startEvent = startEvent;
    }

    public Timestamp getEndEvent() {
        return endEvent;
    }

    public void setEndEvent(Timestamp endEvent) {
        this.endEvent = endEvent;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //	@JsonIgnore
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

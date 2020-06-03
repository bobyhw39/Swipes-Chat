package com.enigma.swipeschat.dto;


import java.sql.Timestamp;


import com.enigma.swipeschat.entity.User;

public class EventDto {

    private Long id;
    private String title;
    private Timestamp startEvent;
    private Timestamp endEvent;
    private String location;
    private String description;
    private User user;
    public EventDto() {
        // TODO Auto-generated constructor stub
    }
    public EventDto(String title, Timestamp startEvent, Timestamp endEvent, String location, String description,
                    User user) {
        super();
        this.title = title;
        this.startEvent = startEvent;
        this.endEvent = endEvent;
        this.location = location;
        this.description = description;
        this.user = user;
    }

    public EventDto(Long id, String title, Timestamp startEvent, Timestamp endEvent, String location,
                    String description, User user) {
        super();
        this.id = id;
        this.title = title;
        this.startEvent = startEvent;
        this.endEvent = endEvent;
        this.location = location;
        this.description = description;
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
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }


}

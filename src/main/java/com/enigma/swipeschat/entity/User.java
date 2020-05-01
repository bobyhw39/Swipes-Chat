package com.enigma.swipeschat.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "tbl_user",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = "username"),
            @UniqueConstraint(columnNames = "email")
        })
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "full_name", nullable = false)
    private String fullName;

//    @JsonIgnore
//    @OneToMany(mappedBy = "user")
//    private List<Events> events;


//    @ManyToMany(cascade={CascadeType.ALL})
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(
//            name = "user_has_friends",
//            joinColumns = { @JoinColumn(name = "id") },
//            inverseJoinColumns = { @JoinColumn(name = "id_friends") }
//    )
//    private List<User> friends;


    @ManyToMany(mappedBy = "user")
    private List<Group> groups;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

//
//    @JsonIgnore
//    public List<User> getFriends() {
//        return friends;
//    }
//
//    @JsonProperty
//    public void setFriends(List<User> friends) {
//        this.friends = friends;
//    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

//    public List<Events> getEvents() {
//        return events;
//    }
//
//    public void setEvents(List<Events> events) {
//        this.events = events;
//    }
}

package com.enigma.swipeschat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@AllArgsConstructor
//@NoArgsConstructor
@Entity
@Table(name = "tbl_group")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "name_group", nullable = false)
    private String name;

    @NotBlank
    @Column(name = "description")
    private String description;

//    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//    @ManyToMany(cascade={CascadeType.ALL})
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_has_groups",
            joinColumns = { @JoinColumn(name = "id_group") },
            inverseJoinColumns = { @JoinColumn(name = "id_user") }
    )
    List<User> user;

    public Group() {
    }

    @OneToMany(mappedBy = "room")
    private List<ChatMessage> chatMessages;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonIgnore
    public List<User> getUser() {
        return user;
    }

    @JsonProperty
    public void setUser(List<User> user) {
        this.user = user;
    }

    @JsonIgnore
    public List<ChatMessage> getChatMessages() {
        return chatMessages;
    }

    @JsonProperty
    public void setChatMessages(List<ChatMessage> chatMessages) {
        this.chatMessages = chatMessages;
    }
}

package com.enigma.swipeschat.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name="tbl_chatmessage")
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private MessageType type;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "sender", nullable = false)
    private String sender;

    @Column(name = "receiver")
    private String receiver;

    @Column(name = "date", nullable = false)
    private LocalDateTime dateTime= LocalDateTime.now();;

    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE,
        TYPING,
        OFFLINE,
        ONLINE
    }

}


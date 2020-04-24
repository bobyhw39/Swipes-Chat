package com.enigma.swipeschat.entity;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Comparator;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@ToString
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

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group room;


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

    public static Comparator<ChatMessage> dateComparator = new Comparator<ChatMessage>() {
        @Override
        public int compare(ChatMessage chatMessage, ChatMessage t1) {
            LocalDateTime msgDate1 = chatMessage.getDateTime();
            LocalDateTime msgDate2 = t1.getDateTime();
            return msgDate1.compareTo(msgDate2);
        }
    };
}


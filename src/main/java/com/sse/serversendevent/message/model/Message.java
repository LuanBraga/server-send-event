package com.sse.serversendevent.message.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@Table
public class Message {

    public Message() {}

    public Message(String description, String url) {
        this.description = description;
        this.url = url;
    }

    @Id
    @SequenceGenerator(
            name = "message_sequence",
            sequenceName = "message_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "message_sequence"
    )
    private Long id;

    private String description;

    private String url;
}
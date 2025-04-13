package com.nodalx.service1.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String content;
    private LocalDateTime timestamp;
    private String source;

    // Default constructor required by JPA
    public Message() {
    }

    public Message(String content, String source) {
        this.content = content;
        this.source = source;
        this.timestamp = LocalDateTime.now();
    }
} 
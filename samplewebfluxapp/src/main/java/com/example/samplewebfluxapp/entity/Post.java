package com.example.samplewebfluxapp.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "posts")
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int id;

    @Column
    private int userId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String body;

    public Post() {
        super();
    }

    public Post(int userId, String title, String body) {
        super();
        this.userId = userId;
        this.title = title;
        this.body = body;
    }

    public String toString() {
        return "{id: " + id 
            + ", userId: " + userId 
            + ", title:¥'" + title + "¥'"
            + ", body:¥'" + body + "¥'}";
    }
}

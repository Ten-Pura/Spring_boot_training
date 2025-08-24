package com.example.webapp.entity;

import java.util.Date;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Table(name = "msgdata")
@Data
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    @NotNull
    private long id;

    @Column(nullable = false)
    @NotBlank
    private String content;

    @Column
    private Date datetime;

    @ManyToOne
    private Person Person;
}

package com.example.webapp.entity;

import com.example.webapp.validator.Phone;

import java.util.List;

import lombok.Data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="people")
@NamedQueries(
    @NamedQuery(
        name = "findWithName",
        query = "from Person where name like :fname"
    )
)
@Data
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    @NotNull
    private long id;

    @Column(length = 50, nullable = false)
    @NotBlank()
    private String name;

    @Column(length = 200, nullable = true)
    @Email()
    private String mail;

    @Column(nullable = true)
    @Min(value=0)
    @Max(value=200)
    private Integer age;

    @Column(nullable = true)
    @Phone(onlyNumber = true)
    private String memo;

    @OneToMany(mappedBy = "Person")
    @Column(nullable = true)
    private List<Message> messages;

}

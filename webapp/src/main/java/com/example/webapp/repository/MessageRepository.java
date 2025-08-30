package com.example.webapp.repository;

import com.example.webapp.entity.Message;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long>{

    public Optional<Message> findById(Long id);
}

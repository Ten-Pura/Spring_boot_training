package com.example.board_app.my_board_app.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.board_app.my_board_app.entity.Post;
import com.example.board_app.my_board_app.repository.PostRepository;

@Service
public class ServiceForPost {

    @Autowired
    PostRepository repository;

    public void saveNewPost(Post post) {
        post.setCreatedAt(LocalDateTime.now());
        repository.saveAndFlush(post);
    }

    public void editPostContent(Long id, String newContent) {
        Post post = repository.findById(id).orElse(null);
        if (post != null) {
            post.setContent(newContent);
            post.setCreatedAt(LocalDateTime.now());
            repository.saveAndFlush(post);
        }
    }


}

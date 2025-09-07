package com.example.board_app.my_board_app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.board_app.my_board_app.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    public Post findById(long id);
    public List<Post> findAllByOrderByIdDesc();
    
}

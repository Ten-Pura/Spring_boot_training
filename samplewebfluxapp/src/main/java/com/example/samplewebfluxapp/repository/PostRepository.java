package com.example.samplewebfluxapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.samplewebfluxapp.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    public Post findById(int id);

}

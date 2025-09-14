package com.example.board_app.my_board_app.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.board_app.my_board_app.entity.Post;
import com.example.board_app.my_board_app.repository.PostRepository;

import jakarta.transaction.Transactional;

@Service
public class ServiceForPost {

    @Autowired
    PostRepository repository;

    /*
     * 受け取ったPostをテーブルPostsに登録する。
     * 
     * @Param Post post
     * @return void
     */
    @Transactional
    public void saveNewPost(Post post) {
        post.setCreatedAt(LocalDateTime.now());
        repository.saveAndFlush(post);
    }

    /*
     * 受け取ったidと一致するレコードをテーブルから検索し、Post型で返す。
     * 
     * @Param Long id
     * @return Post post
     */
    @Transactional
    public Post findPostById(Long id) {
        Post post = repository.findById(id).orElse(null);
        return post;
    }

    /*
     * 受け取ったidと一致するレコードをテーブルPostsから検索する。
     * ヒットしたレコードを受け取ったpostの値に置き換えUpdateする。
     * idと一致するレコードがヒットしない場合は何もしない。
     * 
     * @Param Long id
     * @Param Post post
     * @return void
     */
    @Transactional
    public void editPost(Long id, Post post) {
        Post targetPost = repository.findById(id).orElse(null);
        if (targetPost != null) {
            targetPost.setTitle(post.getTitle());
            targetPost.setSubtitle(post.getSubtitle());
            targetPost.setContent(post.getContent());
            targetPost.setCreatedAt(LocalDateTime.now());
            repository.saveAndFlush(targetPost);
        }
    }


}

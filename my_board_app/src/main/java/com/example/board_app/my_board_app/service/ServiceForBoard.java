package com.example.board_app.my_board_app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.board_app.my_board_app.entity.Post;
import com.example.board_app.my_board_app.repository.PostRepository;

import jakarta.transaction.Transactional;

@Service
public class ServiceForBoard {

    @Autowired
    PostRepository postRepositoy;

    /*
     * テーブルPostsから全件を取得し、Listで返す。
     * 
     * @Param non
     * @return List<Post>
     */
    @Transactional
    public List<Post> getAllPosts() {
        return postRepositoy.findAll();
    }
    
}

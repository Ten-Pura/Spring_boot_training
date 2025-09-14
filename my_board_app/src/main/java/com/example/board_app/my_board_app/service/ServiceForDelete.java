package com.example.board_app.my_board_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.board_app.my_board_app.repository.PostRepository;

import jakarta.transaction.Transactional;

@Service
public class ServiceForDelete {

    @Autowired
    PostRepository postRepository;

    /*
     *テーブルPostsから受けとって削除する。
     *  
     * @Param id
     * @return void
     */
    @Transactional
    public void deletePostById(Long id) {
        postRepository.deleteById(id);
    }

}

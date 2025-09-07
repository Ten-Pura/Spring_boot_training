package com.example.board_app.my_board_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.board_app.my_board_app.entity.Post;
import com.example.board_app.my_board_app.repository.PostRepository;

@Controller
public class TestController {

    @Autowired
    PostRepository postRepository;

    @RequestMapping("/test")
    public String test(Model model) {
        List<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "test";
    }

}

package com.example.board_app.my_board_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.board_app.my_board_app.entity.Post;
import com.example.board_app.my_board_app.service.ServiceForBoard;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    ServiceForBoard service;

    /*
     *「/board」へのアクセスを処理
     * テーブルPostsから全件を取得して表示する。 
     * 
     * @Param model
     * @return String
     */
    @RequestMapping(value="", method=RequestMethod.GET)
    public String board(Model model) {
        model.addAttribute("title", "最近の投稿");
        List<Post> listAllPosts = service.getAllPosts();
        model.addAttribute("posts", listAllPosts);
        return "board";
    }

}

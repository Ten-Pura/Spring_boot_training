package com.example.board_app.my_board_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.board_app.my_board_app.entity.Post;
import com.example.board_app.my_board_app.service.ServiceForPost;


@Controller
@RequestMapping("/post")
public class PostController {

    @Autowired
    ServiceForPost service;

    @RequestMapping(value = "/post", method=RequestMethod.GET)
    public String getPostPage(
        @ModelAttribute("formModel") Post formModel,
        Model model
    ) {
        model.addAttribute("title", "Post Page");
        return "post";
    }
    
    @RequestMapping(value = "/post", method=RequestMethod.POST)
    public String postPostPage(
        @ModelAttribute("formModel") Post formModel,
        Model model
    ) {
        formModel.setUserId("Guest");
        service.saveNewPost(formModel);
        return "redirect:/"; 
    }
    
}

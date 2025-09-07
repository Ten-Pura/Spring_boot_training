package com.example.board_app.my_board_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;


import com.example.board_app.my_board_app.service.ServiceForIndex;
 

@Controller
public class IndexController {
    
    @Autowired
    ServiceForIndex service;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("title", "トップページ");
        model.addAttribute("msg", service.getWelcomeMessage());
        return "index";
    }

}

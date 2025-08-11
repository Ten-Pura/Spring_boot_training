package com.example.webapp.controller;

import java.lang.annotation.Repeatable;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;

@Controller
public class HomeController {
    
    @RequestMapping("/{temp}")
    public String index(@PathVariable String temp, Model model) {
        String msg = "(Empty)"; 
        if(!temp.isEmpty()) {
            msg = temp;
        }
        model.addAttribute("msg", msg);
        return "index";
    }
}

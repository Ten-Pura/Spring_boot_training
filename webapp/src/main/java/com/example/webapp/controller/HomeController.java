package com.example.webapp.controller;

import java.lang.annotation.Repeatable;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    
    @RequestMapping(value="/", method=RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("msg", "フォームを入力してください。");
        return "index";
    }

    @RequestMapping(value="/", method=RequestMethod.POST) 
    public String form(
            @RequestParam(value="check1",  required=false)boolean  check1,
            @RequestParam(value="radio1",  required=false)String   radio1,
            @RequestParam(value="select1", required=false)String   select1,
            @RequestParam(value="select2", required=false)String[] select2, 
            Model model) {
        
        String result = "Your input data";
        result = result + "¥nchecked:" + check1;
        result = result + "¥nsex:" + radio1;
        result = result + "¥nSmartPhone:" + select1;
        result = result + "¥nOS:" + select2[0];
        model.addAttribute("msg",result);
        return "index";
    }

}

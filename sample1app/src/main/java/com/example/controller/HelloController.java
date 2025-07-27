package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    @RequestMapping(value="/", method=RequestMethod.GET)
    public ModelAndView index(ModelAndView mav) {
        mav.addObject("msg", "名前を書いてください。");
        mav.setViewName("index");
        return mav;
    }

    @RequestMapping(value="/", method=RequestMethod.POST)
    public ModelAndView form(@RequestParam("text1") String str,
    ModelAndView mav) {
        mav.addObject("mag", "こんにちは" + str + "さん！");
        mav.setViewName("index");
        return mav;
    }
}
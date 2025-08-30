package com.example.webapp.controller;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.webapp.entity.Message;
import com.example.webapp.repository.MessageRepository;
import com.example.webapp.dao.PersonDAOMessageImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Controller
@RequestMapping("/msg")
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private PersonDAOMessageImpl dao;

    @PersistenceContext
    private EntityManager entityManager;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index(
            ModelAndView mav, 
            @ModelAttribute("fromModel") Message message) {
        mav.setViewName("messages/index");
        mav.addObject("title", "Message Board");
        mav.addObject("msg", "Message Board Sample");
        mav.addObject("fromModel", message);
        List<Message> list = dao.getAll();
        mav.addObject("data", list);
        return mav;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @Transactional
    public ModelAndView msgform(
            ModelAndView mav,
            @ModelAttribute("fromModel") Message message) {
        mav.setViewName("showMessage");
        message.setDatetime( Calendar.getInstance().getTime() );
        messageRepository.saveAndFlush(message);
        mav.addObject("title", "Message");
        mav.addObject("msg", "新しいMessageを受け付けました。");
        return new ModelAndView("redirect:/msg/");
    }
}

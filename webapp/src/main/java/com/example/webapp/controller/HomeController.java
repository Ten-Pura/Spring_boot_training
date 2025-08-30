package com.example.webapp.controller;

import java.lang.annotation.Repeatable;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;

import com.example.webapp.entity.Person;
import com.example.webapp.repository.PersonRepository;
import com.example.webapp.dao.PersonDAOPersonImpl;

@Controller
public class HomeController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonDAOPersonImpl dao;

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public String find(Model model) {
        model.addAttribute("msg", "Personnのサンプルです。");
        Iterable<Person> list = dao.getAll();
        model.addAttribute("data", list);
        return "find";
    }

    @RequestMapping(value = "/find", method = RequestMethod.POST)
    public String find_result(
        HttpServletRequest request,
        Model model) {
            String param = request.getParameter("find_str");
            if (param == "") {
                return "redirect:/find";
            } else {
                model.addAttribute("title", "Find result");
                model.addAttribute("msg", "「" + param + "」の検索結果");
                List<Person> list = dao.find(param);
                model.addAttribute("data", list);
                return "find";
            }
        }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(@ModelAttribute("formModel") Person Person, Model model) {
        model.addAttribute("title", "Hello page");
        model.addAttribute("msg", "This is a JPA sample data.");
        List<Person> list = personRepository.findAllOrderByName();
        model.addAttribute("data", list);
        return "index";
    }

    @RequestMapping(value="/", method=RequestMethod.POST)
    @Transactional
    public String form(
        @ModelAttribute("formModel") @Validated  Person Person,
        BindingResult result,
        Model model) {
        System.out.println(result.getFieldErrors());
        if (!result.hasErrors()) {
            personRepository.saveAndFlush(Person);
            return "redirect:/";
        }else {
            model.addAttribute("title", "Hello page");
            model.addAttribute("msg", "Sorry, there are some errors");
            Iterable<Person> list = dao.getAll();
            model.addAttribute("data", list);
            return "index";
        }
    }

    @RequestMapping(value = "/pase/{pase}", method = RequestMethod.GET)
    public String pase(@PathVariable int pase, Model model) {
        model.addAttribute("title", pase + "pase");
        model.addAttribute("msg", pase + "pase");
        int limit = 2;
        Iterable<Person> list = dao.getPase(pase, limit);
        model.addAttribute("data", list);
        return "pase";
    }

    @RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
    public String edit(
        @ModelAttribute("formModel") Person Person,
        @PathVariable("id") int id, 
        Model model) {
            model.addAttribute("title", "Edit page");
            model.addAttribute("msg", "This is a edit page by JPA");
            Optional<Person> data = personRepository.findById((long) id);
            model.addAttribute("formModel", data.get());
            return "edit";
    }

    @RequestMapping(value="/edit", method=RequestMethod.POST)
    @Transactional
    public String update(
        @ModelAttribute("formModel") Person Person,
        Model model) {
        personRepository.saveAndFlush(Person);
        return "redirect:/";
    }

    @RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
    public String delete_page(
        @PathVariable("id") int id,
        Model model) {
        model.addAttribute("title", "Delete page");
        model.addAttribute("msg", "This is a delete page by JPA");
        Optional<Person> data = personRepository.findById((long) id);
        model.addAttribute("formModel", data.get());
        return "delete";
    }

    @RequestMapping(value="/delete", method=RequestMethod.POST)
    @Transactional
    public String delete(@ModelAttribute("formModel") Person Person) {
        personRepository.deleteById(Person.getId());
        return "redirect:/";
    }
}

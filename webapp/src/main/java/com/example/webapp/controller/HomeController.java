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

import jakarta.transaction.Transactional;
import jakarta.annotation.PostConstruct;

import com.example.webapp.entity.Person;
import com.example.webapp.repository.PersonRepository;

@Controller
public class HomeController {

    @Autowired
    private PersonRepository personRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(@ModelAttribute("formModel") Person Person, Model model) {
        model.addAttribute("title", "Hello page");
        model.addAttribute("msg", "This is a JPA sample data.");
        List<Person> list = personRepository.findAll();
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
            Iterable<Person> list = personRepository.findAll();
            model.addAttribute("data", list);
            return "index";
        }
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

    @PostConstruct
    public void init() {
        //1つ目のダミーデータ作成
        Person p1 = new Person();
        p1.setName("山田太郎");
        p1.setMail("yamada@yamada.com");
        p1.setAge(20);
        p1.setMemo("");
        personRepository.saveAndFlush(p1);
        //2つ目のダミーデータ作成
        Person p2 = new Person();
        p2.setName("鈴木花子");
        p2.setMail(null);
        p2.setAge(25);
        p2.setMemo("09012345678");
        personRepository.saveAndFlush(p2);
    }
}

package com.example.board_app.my_board_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.board_app.my_board_app.entity.Post;
import com.example.board_app.my_board_app.service.ServiceForPost;

import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/post")
public class PostController {

    @Autowired
    ServiceForPost service;

    /*
     *「/post」（GETメソッド）へのアクセスを処理
     *「post.html」を表示する。
     *  
     * @param formModel
     * @param model
     * @return String
    */
    @RequestMapping(value = "", method=RequestMethod.GET)
    public String getPostPage(
        @ModelAttribute("formModel") Post formModel,
        Model model
    ) {
        model.addAttribute("title", "Post Page");
        return "post";
    }
    
    /*
     * 「/post」（POSTメソッド）へのアクセスを処理
     * Post型のformMedelを受け取り、テーブルPostsに登録する。
     * 登録が成功したら「/」にリダイレクトする。
     * 
     * @Param fromModel
     * @Param model
     * @return String
    */
    @RequestMapping(value = "", method=RequestMethod.POST)
    public String postPostPage(
        @ModelAttribute("formModel") Post formModel,
        Model model
    ) {
        formModel.setUserId("Guest");
        service.saveNewPost(formModel);
        return "redirect:/"; 
    }

    /*
     * 「/edit」へのアクセスを処理
     * パラメーターidからテーブルPostsを検索して、編集ページを表示する。
     * 検索してもヒットしない場合はルートにリダイレクト
     * 
     * @Param Long id
     * @Param Post formModel
     * @Model model
     * @return String
     */
    @RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
    public String getEditPostPase(
        @PathVariable("id") Long id,
        @ModelAttribute("formModel") Post formModel,
        Model model
    ) {
        Post post = service.findPostById(id);
        if(post != null) {
            model.addAttribute("title", "編集ページ");
            model.addAttribute("msg", "Postを編集");
            formModel.setId(post.getId());
            formModel.setTitle(post.getTitle());
            formModel.setSubtitle(post.getSubtitle());
            formModel.setContent(post.getContent());
            formModel.setUserId(post.getUserId());
            formModel.setCreatedAt(post.getCreatedAt());
            return "edit";  
        }else{
            return "redirect:/";
        }
    }

    /*
     *「/edit」へのアクセスを処理
     * パラメータidとフォームformModelを受け取り、
     * idと一致するレコードをformModelでUpdateする。
     * 
     * @Param Long id
     * @Post formModel
     * @return String
     */
    @RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
    public String requestMethodName(
        @PathVariable("id") Long id,
        @ModelAttribute("formModel") Post formModel
    ) {
        service.editPost(id, formModel);
        return "redirect:/";
    }
    

    /*
     * テスト用
     * Postを3つ登録
     */
    @PostConstruct
    public void init() {
        Post p1 = new Post();
        p1.setTitle("First Post");
        p1.setSubtitle("Frist time");
        p1.setContent("Content01");
        p1.setUserId("Admin");
        service.saveNewPost(p1);
        Post p2 = new Post();
        p2.setTitle("Second Post");
        p2.setSubtitle("Second time");
        p2.setContent("Content02");
        p2.setUserId("Admin");
        service.saveNewPost(p2);
        Post p3 = new Post();
        p3.setTitle("Third Post");
        p3.setSubtitle("Third time");
        p3.setContent("Content03");
        p3.setUserId("Admin");
        service.saveNewPost(p3);
    }
    
}

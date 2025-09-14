package com.example.board_app.my_board_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.board_app.my_board_app.service.ServiceForDelete;


@Controller
@RequestMapping("/delete")
public class DeleteController {

    @Autowired
    ServiceForDelete service;

    /*
     *「/delete」へのアクセスを処理
     * パラメータidで受け取り、受け取ったidに一致するレコードをテーブルPostsから削除する。
     * 
     * @Param id
     * @return String
     */
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public String DeletePostById(@PathVariable("id") Long id) {
        service.deletePostById(id);
        return "redirect:/board";
    }
    

}

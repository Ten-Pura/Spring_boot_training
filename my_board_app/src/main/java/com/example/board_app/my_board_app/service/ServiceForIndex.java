package com.example.board_app.my_board_app.service;

import org.springframework.stereotype.Service;

@Service
public class ServiceForIndex {

    /*
     * トップページに掲載するメッセージを返す。
     * 
     * @Param non
     * @return String
     */
    public String getWelcomeMessage() {
        return "Welcome to the Board Application!";
    }

}

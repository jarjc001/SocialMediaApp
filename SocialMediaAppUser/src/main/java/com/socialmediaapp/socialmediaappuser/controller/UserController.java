package com.socialmediaapp.socialmediaappuser.controller;

import com.socialmediaapp.socialmediaappuser.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    @Autowired
    UserDao users;
//
//
//
//
//    @GetMapping("/")
//    public String index(Model model) {
//
//    }
//
//    @PostMapping("/addStore")
//    public String addStore(User user) {
//        users.save(user);
//        return "redirect:/";
//    }


}
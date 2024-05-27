package com.socialmediaapp.socialmediaapppost.controller;

import com.socialmediaapp.socialmediaapppost.dao.PostDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PostController {
    @Autowired
    PostDao users;
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

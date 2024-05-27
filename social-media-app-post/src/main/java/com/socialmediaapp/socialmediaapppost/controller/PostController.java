package com.socialmediaapp.socialmediaapppost.controller;

import com.socialmediaapp.socialmediaapppost.dao.PostDao;
import com.socialmediaapp.socialmediaapppost.dto.Post;
import com.socialmediaapp.socialmediaapppost.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    PostDao users;


    @GetMapping("/{username}/{postId}")
    public Post getPost(@PathVariable("username") String username, @PathVariable("postId") long postId){
        return new Post(postId, username, "Test content", new Timestamp(System.currentTimeMillis()));
    }


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

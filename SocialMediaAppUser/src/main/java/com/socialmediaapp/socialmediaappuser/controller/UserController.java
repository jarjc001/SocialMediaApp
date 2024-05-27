package com.socialmediaapp.socialmediaappuser.controller;

import com.socialmediaapp.socialmediaappuser.dao.UserDao;
import com.socialmediaapp.socialmediaappuser.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserDao users;

    @GetMapping("/{username}")
    public User getUser(@PathVariable("username") String username){
        return new User(username,"HashPassword".toCharArray(),"testEmail","testfullname" );

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

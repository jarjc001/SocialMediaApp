package com.socialmediaapp.socialmediaappuser.service;

import com.socialmediaapp.socialmediaappuser.dto.User;

public interface UserService {

    // dont know to have authetercate user method here too?????

    User getCurrentUser();

    User loginUser(String username, char[] password);

    User addUser(String username, char[] password, String email, String fullName);

    User editUserInfo(String email, String fullName);

    User editUserPassword(char[] password);


    void removeUser();





}

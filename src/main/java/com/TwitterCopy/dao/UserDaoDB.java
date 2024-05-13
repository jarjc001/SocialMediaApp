package com.TwitterCopy.dao;

import com.TwitterCopy.dto.User;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoDB implements UserDao{

    JdbcTemplate jdbc

    @Override
    public User getUserByLogin(String username, String password, User user) {
        return null;
    }

    @Override
    public User addUser(User user) {
        return null;
    }

    @Override
    public boolean checkUsernameInDB(String username) {
        return false;
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteuser(User user) {

    }
}

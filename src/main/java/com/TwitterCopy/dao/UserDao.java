package com.TwitterCopy.dao;


import com.TwitterCopy.dto.User;

public interface UserDao {

    /**
     * To authenticate a user's username and password
     * @param username username
     * @param Password password
     * @return If details are correct
     */
    boolean authenticateUser(String username, String Password);

    /**
     * Get the user info from a given username
     */
    User getUserByUsername(String username) throws DataBaseException;


    /**Add a new user to the database if the username is unique
     * @param user object with the info of new user
     * @return the user that has been added
     */
    User addUser(User user);

    /**
     * Checks if the username has been taken in db
     * @param username username
     * @return true id username is already there, false if it is free to take
     */
    boolean checkUsernameInDB(String username);

    /**Update info of a user in database
     * @param user user object with the edited info
     */
    void updateUser(User user);

    /**delete user from database,
     * have to have username and password
     * @param user
     */
    void deleteuser(User user);


}

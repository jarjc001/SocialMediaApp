package com.SocialMediaAppPost.dao;


import com.SocialMediaAppPost.dto.User;

public interface PostDao {

    /**
     * To authenticate a user's password with a certain username
     *
     * @param username username
     * @param password password
     * @return If details are correct
     */
    boolean authenticateUser(String username, char[] password) throws DataBaseException;

    /**
     * Get the user info from a given username
     */
    User getUserByUsername(String username) throws DataBaseException;


    /**Add a new user to the database if the username is unique
     * @param user object with the info of new user
     * @return the user that has been added
     */
    User addUser(User user) throws DataBaseException;

    /**
     * Checks if the username has been taken in db
     * @param username username
     * @return true id username is already there, false if it is free to take
     */
    boolean checkUsernameInDB(String username);

    /**Update info of a user in database, will update email or full name
     * @param user user object with the edited info
     */
    void updateUserExtraInfo(User user);

    /**
     * updtae the password for a given user in the database
     * @param username username of user
     * @param password new password of user
     */
    void updateUserPassword(String username, char[] password) throws DataBaseException;

    /**delete user from database,
     * have to have username and password
     * @param user
     */
    void deleteUser(User user);


}

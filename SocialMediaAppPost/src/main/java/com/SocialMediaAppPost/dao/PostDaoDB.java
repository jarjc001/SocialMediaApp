package com.SocialMediaAppPost.dao;

import com.SocialMediaAppPost.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

@Repository
public class PostDaoDB implements PostDao {

    ///TODO
    // change exceptions to custom ones

    @Autowired
    JdbcTemplate jdbc;


    @Override
    public User getUserByUsername(String username) throws DataBaseException {
        User user;

        final String SELECT_USERNAME = "SELECT * FROM userAccounts WHERE username = ?";
        try {
            user = jdbc.queryForObject(SELECT_USERNAME, new PostMapper(), username);
        } catch (DataAccessException e) { //Username not in db
            throw new DataBaseException("Username not in DB");
        }
        return user;


    }


    @Override
    public User addUser(User user) throws DataBaseException {
        final String INSERT_USER = "INSERT INTO useraccounts" +
                "(username,password,email,fullname)" +
                " VALUES (?,?,?,?)";

        try {
            jdbc.update(INSERT_USER,
                    user.getUsername(),
                    hashPassword(user.getPassword()),
                    user.getEmail(),
                    user.getFullName());
        } catch (NullPointerException e) {
            throw new DataBaseException("No password with user");
        }

        return user;
    }

    @Override
    public boolean checkUsernameInDB(String username) {
        final String SELECT_USERNAME = "SELECT * FROM userAccounts WHERE username = ?";
        try {
            User user = jdbc.queryForObject(SELECT_USERNAME, new PostMapper(), username);
        } catch (DataAccessException e) { //Username free
            return false;
        }
        return true;    //Username already Taken

    }


    @Override
    public void updateUserExtraInfo(User user) {
        final String UPDATE_USER = "UPDATE userAccounts SET email = ?, fullname = ? WHERE username = ?";

        jdbc.update(UPDATE_USER,
                user.getEmail(),
                user.getFullName(),
                user.getUsername());
    }

    //todo
    @Override
    public void updateUserPassword(String username, char[] password) throws DataBaseException {
        final String UPDATE_USER = "UPDATE userAccounts SET password = ? WHERE username = ?";

        try {
            jdbc.update(UPDATE_USER,
                    hashPassword(password),
                    username);
        } catch (DataBaseException e) {
            throw new DataBaseException("Could not update password");
        }
    }

    @Override
    @Transactional
    public void deleteUser(User user) {
        //subscriptions
        final String DELETE_SUBSCRIPTIONS_1 = "DELETE FROM subscriptions WHERE username = ?";
        final String DELETE_SUBSCRIPTIONS_2 = "DELETE FROM subscriptions WHERE subUsers = ?";
        jdbc.update(DELETE_SUBSCRIPTIONS_1, user.getUsername());
        jdbc.update(DELETE_SUBSCRIPTIONS_2, user.getUsername());

        //comments
        final String DELETE_COMMENTS = "DELETE FROM comments WHERE username = ?";
        jdbc.update(DELETE_COMMENTS, user.getUsername());

        //likes
        final String DELETE_LIKES = "DELETE FROM likes WHERE username = ?";
        jdbc.update(DELETE_LIKES, user.getUsername());

        //posts
        final String DELETE_POSTS = "DELETE FROM posts WHERE username = ?";
        jdbc.update(DELETE_POSTS, user.getUsername());

        //userAccounts
        final String DELETE_USER = "DELETE FROM userAccounts WHERE username = ?";
        jdbc.update(DELETE_USER, user.getUsername());
    }


    /**
     * Hash a password into a string
     *
     * @param password password to hash
     * @return hash password as a string
     */
    private String hashPassword(char[] password) throws DataBaseException {

        byte[] salt = generateSalt();
        byte[] hash = sha256(password, salt);
        clearPassword(password); // Clear the password array
        return Base64.getEncoder().encodeToString(salt) + ":" + Base64.getEncoder().encodeToString(hash);

    }

    /**
     * generate a salt
     *
     * @return salt
     */
    private byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }


    //

    /**
     * Method to hash a password using SHA-256 and a salt into a byte[]
     *
     * @param password password to salt
     * @param salt     salt
     * @return hashed password as byte[]
     */
    private byte[] sha256(char[] password, byte[] salt) throws DataBaseException {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            return md.digest(new String(password).getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new DataBaseException("Encription Failed");
        }
    }

    /**
     * Method to clear the password array
     */
    private void clearPassword(char[] password) {
        if (password != null) {
            Arrays.fill(password, '\0');
        }
    }

    @Override
    public boolean authenticateUser(String username, char[] password) {
        final String SELECT_PASSWORD = "SELECT password FROM userAccounts WHERE username = ?";
        try {
            // query the password in the db
            String storedPassword = jdbc.queryForObject(SELECT_PASSWORD, String.class, username);
            assert storedPassword != null;
            String[] parts = storedPassword.split(":");

            //get the salt of the db
            byte[] salt = Base64.getDecoder().decode(parts[0]);

            //hash incoming password
            byte[] hash = sha256(password, salt);

            // Clear the passwordT char array
            clearPassword(password);

            return storedPassword.equals(Base64.getEncoder().encodeToString(salt) + ":" + Base64.getEncoder().encodeToString(hash));
        } catch (DataAccessException | NullPointerException | DataBaseException e) { //Wrong Username
            return false;
        }
    }


}






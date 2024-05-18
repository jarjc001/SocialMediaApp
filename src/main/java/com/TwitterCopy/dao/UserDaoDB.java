package com.TwitterCopy.dao;

import com.TwitterCopy.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

@Repository
public class UserDaoDB implements UserDao {

    ///TODO
    // change exceptions to custom ones

    @Autowired
    JdbcTemplate jdbc;


    @Override
    public User getUserByUsername(String username) {

        User newUser = new User();

        return null;
    }

    //todo
    // figure out password enter
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
        }catch (NullPointerException e){
            throw new DataBaseException("No password with user");
        }


        return user;
    }

    @Override
    public boolean checkUsernameInDB(String username) {
        final String SELECT_USERNAME = "SELECT * FROM userAccounts WHERE username = ?";
        try {
            User user = jdbc.queryForObject(SELECT_USERNAME, new UserMapper(), username);
        }catch (DataAccessException e){ //Username free
            return false;
        }
        return true;    //Username already Taken

    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteuser(User user) {

    }




    /**
     * Hash a password into a string
     *
     * @param password password to hash
     * @return hash password as a string
     */
    private String hashPassword(char[] password) throws  DataBaseException {

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






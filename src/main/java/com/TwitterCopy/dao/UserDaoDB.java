package com.TwitterCopy.dao;

import com.TwitterCopy.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.swing.tree.RowMapper;
import javax.swing.tree.TreePath;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Base64;

@Repository
public class UserDaoDB implements UserDao{

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
    public User addUser(User user) {
        final String INSERT_USER = "INSERT INTO useraccounts"+
                "(username,password,email,fullname)" +
                " VALUES (?,?,?,?)";

        jdbc.update(INSERT_USER,
                user.getUsername(),
                Arrays.toString(user.getPassword()),
                user.getEmail(),
                user.getFullName());

        return user;
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












    // Method to hash a password
    private String hashPassword(char[] password) throws NoSuchAlgorithmException {
        byte[] salt = generateSalt();
        byte[] hash = sha256(password, salt);
        clearPassword(password); // Clear the password array
        return Base64.getEncoder().encodeToString(salt) + ":" + Base64.getEncoder().encodeToString(hash);
    }

    // Method to generate a salt
    private byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }


    // Method to hash a password using SHA-256 and a salt
    private byte[] sha256(char[] password, byte[] salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(salt);
        return md.digest(new String(password).getBytes());
    }

    // Method to clear the password array
    private void clearPassword(char[] password) {
        if (password != null) {
            Arrays.fill(password, '\0');
        }
    }

    //todo
    @Override
    public boolean authenticateUser(String username, String Password) {
        final String SELECT_PASSWORD = "SELECT password FROM users WHERE username = ?";
        try{
            jdbc.query(SELECT_PASSWORD, new RowMapper<User>() {
                @Override
                public int[] getRowsForPaths(TreePath[] path) {
                    return new int[0];
                }
            },username);
        }catch (DataAccessException e){ //Username free
            return false;
        }
        return false;
    }

    //todo
    // have to add jdbc to query stored password with username
    private static boolean authenticate(char[] passwordToCheck, String storedPassword) throws NoSuchAlgorithmException {

        String[] parts = storedPassword.split(":");
        byte[] salt = Base64.getDecoder().decode(parts[0]);
        byte[] hash = sha256(passwordToCheck, salt);
        clearPassword(passwordToCheck); // Clear the passwordToCheck array
        return storedPassword.equals(Base64.getEncoder().encodeToString(salt) + ":" + Base64.getEncoder().encodeToString(hash));

    }




}






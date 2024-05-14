package com.TwitterCopy.dao;

import com.TwitterCopy.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

@Repository
public class UserDaoDB implements UserDao{

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public boolean authenticateUser(String username, String Password) {
        return false;
    }

    @Override
    public User getUserByUsername(String username) {

        User newUser = new User();

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


    ///// TODO
    public String encryptPass(String password) {
        try {
            //retrieve instance of the encryptor of SHA-256
            MessageDigest digestor = MessageDigest.getInstance("SHA-256");//retrieve bytes to encrypt
            byte[] encodedhash = digestor.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder encryptionValue = new StringBuilder(2 * encodedhash.length);//perform encryption
            for (byte b : encodedhash) {
                String hexVal = Integer.toHexString(0xff & b);
                if (hexVal.length() == 1) {
                    encryptionValue.append('0');
                }
                encryptionValue.append(hexVal);
            }//return encrypted value
            return encryptionValue.toString();}
        catch (Exception ex) {
            return ex.getMessage();
        }
    }


    // Method to hash a password
    private static String hashPassword(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] salt = generateSalt();
        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = factory.generateSecret(spec).getEncoded();
        return Base64.getEncoder().encodeToString(salt) + ":" + Base64.getEncoder().encodeToString(hash);
    }

    // Method to hash a password using a specific salt
    private static String hashPassword(String password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = factory.generateSecret(spec).getEncoded();
        return Base64.getEncoder().encodeToString(salt) + ":" + Base64.getEncoder().encodeToString(hash);
    }

    // Method to generate a salt
    private static byte[] generateSalt() {
        // Generate a secure random salt
        // Example: SecureRandom.getInstanceStrong().generateSeed(16);
        return new byte[16];
    }



}






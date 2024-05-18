package com.TwitterCopy;


import com.TwitterCopy.dao.DataBaseException;
import com.TwitterCopy.dao.UserDao;
import com.TwitterCopy.dto.User;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class TestUserDao {

    @Autowired
    UserDao userDao;

    // user1
    String testUsername1 = "testUsername1";
    char[] testPassword1;
    String hashTestPassword1 = "CNrWX1fB/zo1klU+U1/iPA==:mSYiB/gV71idejmz21Cf+YQwKJtJIeOi/SPzsmvi1jc=";
    String testEmail1 = "test1@email.com";
    String testFullName1 = "test name1";

    User testUser1;

    //user2
    String testUsername2 = "testUsername2";
    char[] testPassword2;
    String hashTestPassword2 = "qYfpzqf2NkXVDOSdBGyRTQ==:g7rsUGTMBs6tclMeIO25+PPOSgEr/8lY6VS3pEBt32k=";
    String testEmail2 = "test2@email.com";
    String testFullName2 = "test name2";

    User testUser2;


    public TestUserDao() {
    }

    @BeforeEach
    void setUp() {
        testPassword1 = "password1".toCharArray();
        testPassword2 = "password2".toCharArray();

        testUser1 = new User(testUsername1,testPassword1,testEmail1,testEmail1);
        testUser2 = new User(testUsername2,testPassword2,testEmail2,testEmail2);


    }


    @Test
    void testAuthenticateUser() throws DataBaseException {
        // authenticate a user given a password and username

        //test cases:
        // 1. password same db password
        // 2. password not same as db password
        // 3. username not in db
        // 4. password is in db but not the right one

        // 1.
        Assertions.assertTrue(userDao.authenticateUser(testUsername1, testPassword1), "TestUser1 with correct password");
        Assertions.assertTrue(userDao.authenticateUser(testUsername2, testPassword2), "TestUser2 with correct password");

        // 2.
        char[] wrongPassword = "passwordNotInDataBase".toCharArray();
        Assertions.assertFalse(userDao.authenticateUser(testUsername1, wrongPassword), "TestUser1 with wrong password");
        wrongPassword = "passwordNotInDataBase".toCharArray();
        Assertions.assertFalse(userDao.authenticateUser(testUsername2, wrongPassword), "TestUser2 with wrong password");

        // 3.
        String usernameNotInDB = "UsernameNotInDb";
        wrongPassword = "passwordNotInDataBase".toCharArray();
        Assertions.assertFalse(userDao.authenticateUser(usernameNotInDB, wrongPassword), "User not in db");

        // 4.
        testPassword1 = "password1".toCharArray();
        testPassword2 = "password2".toCharArray();
        Assertions.assertFalse(userDao.authenticateUser(testUsername1, testPassword2), "Password in database, but not match username");
        Assertions.assertFalse(userDao.authenticateUser(testUsername2, testPassword1), "Password in database, but not match username");

    }

    @Test
    void testAddUser(){
        // add a user to the database



    }

}

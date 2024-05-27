package com.socialmediaapp.socialmediaappuser;


import com.socialmediaapp.socialmediaappuser.dao.UserDataBaseException;
import com.socialmediaapp.socialmediaappuser.dao.UserDao;
import com.socialmediaapp.socialmediaappuser.dto.User;
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
    String testEmail1 = "test1@email.com";
    String testFullName1 = "test name1";

    User testUser1;

    //user2
    String testUsername2 = "testUsername2";
    char[] testPassword2;
    String testEmail2 = "test2@email.com";
    String testFullName2 = "test name2";

    User testUser2;


    public TestUserDao() {
    }

    @BeforeEach
    void setUp() {
        testPassword1 = "password1".toCharArray();
        testPassword2 = "password2".toCharArray();

        //user1 in db
        testUser1 = new User(testUsername1, testPassword1, testEmail1, testFullName1);

        //user2 not in db
        testUser2 = new User(testUsername2, testPassword2, testEmail2, testFullName2);

        // user1
        if (userDao.checkUsernameInDB(testUsername1)) {
            userDao.deleteUser(testUser1);
        }
        try {
            userDao.addUser(testUser1);
        } catch (UserDataBaseException e) {
            System.out.println(e.getMessage());
        }

        // user2
        if (userDao.checkUsernameInDB(testUsername2)) {
            userDao.deleteUser(testUser2);
        }

        testPassword1 = "password1".toCharArray();


    }


    @Test
    void testAuthenticateUser() throws UserDataBaseException {
        // authenticate a user given a password and username

        //test cases:
        // 1. password same db password
        // 2. password not same as db password
        // 3. username not in db
        // 4. password is in db but not the right one

        // 1.
        Assertions.assertTrue(userDao.authenticateUser(testUsername1, testPassword1), "TestUser1 with correct password");


        // 2.
        char[] wrongPassword = "passwordNotInDataBase".toCharArray();
        Assertions.assertFalse(userDao.authenticateUser(testUsername1, wrongPassword), "TestUser1 with wrong password");


        // 3.
        String usernameNotInDB = "UsernameNotInDb";
        wrongPassword = "passwordNotInDataBase".toCharArray();
        Assertions.assertFalse(userDao.authenticateUser(usernameNotInDB, wrongPassword), "User not in db");

        // 4.
        Assertions.assertFalse(userDao.authenticateUser(testUsername1, testPassword2), "Password in database, but not match username");


    }


    @Test
    void testAddUser() throws UserDataBaseException {
        // add a user to the database

        //test cases
        // 1. add a user into the db
        // 2. try to add incomplete user


        // 1.
        char[] sameAsTestPassword2 = "password2".toCharArray();
        userDao.addUser(testUser2);

        // check if password in testuser2 object has been cleared
        Assertions.assertNotEquals(testPassword2, sameAsTestPassword2, "Password in User object has been cleared after adding user to DB");

        Assertions.assertTrue(userDao.authenticateUser(testUser2.getUsername(), sameAsTestPassword2), "User has been added to DB");


        // 2.
        // has no password as char[] in it
        User testUser3 = new User();
        boolean testPass = false;

        try {
            userDao.addUser(testUser3);
        } catch (UserDataBaseException e) {
            testPass = true;
        }
        Assertions.assertTrue(testPass, "User had no password");
    }


    @Test
    void testGetUser() {
        // 1. right username
        // 2. wrong username

        //1.
        boolean failTest = false;
        User testUserFromDB = new User();

        try {
            testUserFromDB = userDao.getUserByUsername(testUsername1);
        } catch (UserDataBaseException e) {
            failTest = true;
        }

        Assertions.assertFalse(failTest, "Failed to get User from DB");

        Assertions.assertEquals(testUser1, testUserFromDB, "Got all the user info");


        // 2.
        String wrongUsername = "UsernameNotInDB";
        try {
            testUserFromDB = userDao.getUserByUsername(wrongUsername);
            failTest = true;
        } catch (UserDataBaseException ignored) {
        }

        Assertions.assertFalse(failTest, "Username not in DB");


    }


    @Test
    void testDeleteUser() {
        // 1. delete user that is in db
        // 2. delete user not in db

        // 1.
        Assertions.assertTrue(userDao.checkUsernameInDB(testUsername1),"User 1 before being deleted from DB");

        userDao.deleteUser(testUser1);

        Assertions.assertFalse(userDao.checkUsernameInDB(testUsername1),"User 1 has been deleted from DB");


        // 2.
        Assertions.assertFalse(userDao.checkUsernameInDB(testUsername2),"User 2 is not in the DB");

        boolean errorHappen = false;

        try {
            userDao.deleteUser(testUser2);
        }catch (Exception e){
            errorHappen = true;
        }

        Assertions.assertFalse(userDao.checkUsernameInDB(testUsername2),"User 2 still not in the DB");
        Assertions.assertFalse(errorHappen,"Delete User method did not cause an error");

    }

    //todo
    @Test
    void testUpdateUserPassword() {
        // 1. change password
        // 2. will it Authenticate User

        // 1.
        User oldUser = testUser1;
        char[] newPassword= "newPassword".toCharArray();




    }

    //todo
    @Test
    void testUpdateUserExtraInfo() {

    }

}

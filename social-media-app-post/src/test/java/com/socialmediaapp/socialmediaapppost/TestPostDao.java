package com.socialmediaapp.socialmediaapppost;


import com.socialmediaapp.socialmediaapppost.dao.DataBaseException;
import com.socialmediaapp.socialmediaapppost.dao.PostDao;
import com.socialmediaapp.socialmediaapppost.dto.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class TestPostDao {

    @Autowired
    PostDao postDao;

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


    public TestPostDao() {
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
        if (postDao.checkUsernameInDB(testUsername1)) {
            postDao.deleteUser(testUser1);
        }
        try {
            postDao.addUser(testUser1);
        } catch (DataBaseException e) {
            System.out.println(e.getMessage());
        }

        // user2
        if (postDao.checkUsernameInDB(testUsername2)) {
            postDao.deleteUser(testUser2);
        }

        testPassword1 = "password1".toCharArray();


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
        Assertions.assertTrue(postDao.authenticateUser(testUsername1, testPassword1), "TestUser1 with correct password");


        // 2.
        char[] wrongPassword = "passwordNotInDataBase".toCharArray();
        Assertions.assertFalse(postDao.authenticateUser(testUsername1, wrongPassword), "TestUser1 with wrong password");


        // 3.
        String usernameNotInDB = "UsernameNotInDb";
        wrongPassword = "passwordNotInDataBase".toCharArray();
        Assertions.assertFalse(postDao.authenticateUser(usernameNotInDB, wrongPassword), "User not in db");

        // 4.
        Assertions.assertFalse(postDao.authenticateUser(testUsername1, testPassword2), "Password in database, but not match username");


    }


    @Test
    void testAddUser() throws DataBaseException {
        // add a user to the database

        //test cases
        // 1. add a user into the db
        // 2. try to add incomplete user


        // 1.
        char[] sameAsTestPassword2 = "password2".toCharArray();
        postDao.addUser(testUser2);

        // check if password in testuser2 object has been cleared
        Assertions.assertNotEquals(testPassword2, sameAsTestPassword2, "Password in User object has been cleared after adding user to DB");

        Assertions.assertTrue(postDao.authenticateUser(testUser2.getUsername(), sameAsTestPassword2), "User has been added to DB");


        // 2.
        // has no password as char[] in it
        User testUser3 = new User();
        boolean testPass = false;

        try {
            postDao.addUser(testUser3);
        } catch (DataBaseException e) {
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
            testUserFromDB = postDao.getUserByUsername(testUsername1);
        } catch (DataBaseException e) {
            failTest = true;
        }

        Assertions.assertFalse(failTest, "Failed to get User from DB");

        Assertions.assertEquals(testUser1, testUserFromDB, "Got all the user info");


        // 2.
        String wrongUsername = "UsernameNotInDB";
        try {
            testUserFromDB = postDao.getUserByUsername(wrongUsername);
            failTest = true;
        } catch (DataBaseException ignored) {
        }

        Assertions.assertFalse(failTest, "Username not in DB");


    }


    @Test
    void testDeleteUser() {
        // 1. delete user that is in db
        // 2. delete user not in db

        // 1.
        Assertions.assertTrue(postDao.checkUsernameInDB(testUsername1),"User 1 before being deleted from DB");

        postDao.deleteUser(testUser1);

        Assertions.assertFalse(postDao.checkUsernameInDB(testUsername1),"User 1 has been deleted from DB");


        // 2.
        Assertions.assertFalse(postDao.checkUsernameInDB(testUsername2),"User 2 is not in the DB");

        boolean errorHappen = false;

        try {
            postDao.deleteUser(testUser2);
        }catch (Exception e){
            errorHappen = true;
        }

        Assertions.assertFalse(postDao.checkUsernameInDB(testUsername2),"User 2 still not in the DB");
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

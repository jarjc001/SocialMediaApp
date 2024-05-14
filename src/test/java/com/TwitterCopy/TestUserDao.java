package com.TwitterCopy;


import com.TwitterCopy.dao.UserDao;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class TestUserDao {

    @Autowired
    UserDao userDao;

    String testUsername = "testUsername1";

    String testPassword = "password";

    String testEmail = "test@email.com";

    String testFullName = "test name";


}

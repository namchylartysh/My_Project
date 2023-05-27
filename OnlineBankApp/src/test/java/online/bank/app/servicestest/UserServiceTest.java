package online.bank.app.servicestest;
import online.bank.app.models.User;
import online.bank.app.models.testmodels.TestUser;
import online.bank.app.repositories.UserRepository;
import online.bank.app.services.UserService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    static final int USER_ID = 1;
    static final String FIRST_NAME = "Ivan";
    static final String LAST_NAME = "Ivanov";
    static final String EMAIL = "user@user.ru";
    static final String PASSWORD = "user1";

    @Test
    public void findAllTest() {
        User user = new User(USER_ID, FIRST_NAME, LAST_NAME, EMAIL, PASSWORD);
        userService.save(user);
        List<User> users = userService.findAll();

        Assertions.assertEquals(1, users.size());
    }
}

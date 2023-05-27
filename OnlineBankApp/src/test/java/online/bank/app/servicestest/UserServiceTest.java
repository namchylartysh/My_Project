package online.bank.app.servicestest;
import online.bank.app.models.Role;
import online.bank.app.models.User;
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

    @Autowired
    UserRepository userRepository;
    static final Role ROLE = Role.CLIENT;
    static final String FIRST_NAME = "Max";
    static final String LAST_NAME = "Maximov";
    static final String EMAIL = "user@user.ru";
    static final String PASSWORD = "user99";


    @Test
    public void findAllTest() {
        User user = new User(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, ROLE);
        userService.save(user);
        List<User> users = userService.findAll();
        Assertions.assertEquals(1, users.size());
    }
}

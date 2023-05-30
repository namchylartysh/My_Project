package online.bank.app.services;

import online.bank.app.models.Role;
import online.bank.app.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
class UserServiceTest {

    @Autowired
    UserService userService;


    static final int ID = 1;
    static final String FIRST_NAME = "Jack";
    static final String LAST_NAME = "Sparrow";
    static final String EMAIL = "pirate@user.ru";
    static final String PASSWORD = "user12";
    static final Role ROLE = Role.CLIENT;

    @Test
    void findAllTest() {
        int size = userService.findAll().size();
        User user = new User(ID, FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, ROLE);
        userService.save(user);
        Assertions.assertEquals(size+1, userService.findAll().size());
    }

    @Test
    void findByIdTest() {
        User user = new User();
        user.setUser_id(ID);
        user.setFirstName(FIRST_NAME);
        user.setLastName(LAST_NAME);
        user.setEmail(EMAIL);
        user.setPassword(PASSWORD);
        user.setRole(ROLE);

        userService.save(user);
        User result = userService.findById(ID);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(ID, result.getUser_id());
    }

    @Test
    void findByEmailTest() {
        User user = new User();
        user.setUser_id(ID);
        user.setFirstName(FIRST_NAME);
        user.setLastName(LAST_NAME);
        user.setEmail(EMAIL);
        user.setPassword(PASSWORD);
        user.setRole(ROLE);

        userService.save(user);
        User result = userService.findByEmail(EMAIL);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(EMAIL, result.getEmail());
    }

    @Test
    void authenticateTest() {
        User user = new User();
        user.setUser_id(ID);
        user.setFirstName(FIRST_NAME);
        user.setLastName(LAST_NAME);
        user.setEmail(EMAIL);
        user.setPassword(PASSWORD);
        user.setRole(ROLE);

        userService.save(user);
        User result = userService.authenticate(EMAIL, PASSWORD);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(EMAIL, result.getEmail());
        Assertions.assertEquals(PASSWORD, result.getPassword());
    }

}
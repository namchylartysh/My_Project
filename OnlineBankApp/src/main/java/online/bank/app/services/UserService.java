package online.bank.app.services;

import online.bank.app.helpers.Token;
import online.bank.app.models.User;
import online.bank.app.models.testmodels.TestUser;
import online.bank.app.repositories.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;

@Service
public class UserService {

    private static final Logger logger = LogManager.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        logger.fatal("Executing findAll method");
        return userRepository.findAll();
    }

    public User findById(Integer id) {
        logger.fatal("Executing findById method with id: " + id);
        return userRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }

    public void save(User user) {
        String token = Token.generateToken();
        user.setToken(token);
        userRepository.save(user);
    }

    public User add(User user) {
        User newUser = new User(user.getUser_id(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword());
        newUser = userRepository.save(newUser);
        return newUser;
    }

    public void delete(Integer id) {
        logger.fatal("Executing delete method with id: " + id);
        userRepository.deleteById(id);
    }

    public User findByEmail(String email) {
        logger.fatal("Executing findByEmail method with email: " + email);
        Optional<User> user = userRepository.findByEmail(email);
        return user.orElseThrow(() -> new RuntimeException("User not found for email: " + email));
    }

    public User authenticate(String email, String password) {
        logger.fatal("Executing authenticate method with email: " + email);
        User user = findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public User getCurrentUser(HttpSession session) {
        logger.fatal("Executing getCurrentUser method");
        return (User) session.getAttribute("currentUser");
    }
}

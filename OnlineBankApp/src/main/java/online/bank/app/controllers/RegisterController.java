package online.bank.app.controllers;

import online.bank.app.helpers.Token;
import online.bank.app.models.Role;
import online.bank.app.models.User;
import online.bank.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;


@Controller
public class RegisterController {

    @Autowired
    UserService userService;

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerProcess(@RequestParam String firstName,
                                  @RequestParam String lastName,
                                  @RequestParam String email,
                                  @RequestParam String password) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(Role.CLIENT);
        String token = Token.generateToken();
        user.setToken(token);
        LocalDateTime created_at = LocalDateTime.now();
        user.setCreated_at(created_at);
        userService.save(user);
        return "redirect:/login";
    }

}

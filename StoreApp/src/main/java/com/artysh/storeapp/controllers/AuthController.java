package com.artysh.storeapp.controllers;

import com.artysh.storeapp.models.Client;
import com.artysh.storeapp.services.RegistrationService;
import com.artysh.storeapp.util.ClientValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final ClientValidator clientValidator;
    private final RegistrationService registrationService;

    @Autowired
    public AuthController(ClientValidator clientValidator, RegistrationService registrationService) {
        this.clientValidator = clientValidator;
        this.registrationService = registrationService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("client") Client client) {
        return "auth/registration";
    }

    @PostMapping("/registration") // сюда будут приходить данные с формы регистрации
    public String performRegistration(@ModelAttribute("client") @Valid Client client, BindingResult bindingResult) {
        clientValidator.validate(client, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/auth/registration";
        }

        registrationService.register(client);

        return "redirect:/auth/login";
    }
}

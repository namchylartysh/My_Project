package online.bank.app.controllers;

import online.bank.app.helpers.GenerateAccountNumber;
import online.bank.app.models.User;
import online.bank.app.repositories.AccountRepository;
import online.bank.app.repositories.UserRepository;
import online.bank.app.services.AccountService;
import online.bank.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;



    @PostMapping("/create_account")
    public String createAccount(@RequestParam("account_name") String accountName,
                                @RequestParam("account_type") String accountType,
                                @RequestParam(value = "balance", defaultValue = "0") BigDecimal balance,
                                RedirectAttributes redirectAttributes,
                                HttpSession session){

        if(accountName.isEmpty() || accountType.isEmpty()){
            redirectAttributes.addFlashAttribute("error", "Account Name and Type cannot be empty!");
            return "redirect:/app/dashboard";
        }

        User user = userService.getCurrentUser(session);
        int userId = user.getUser_id();
        int setAccountNumber = GenerateAccountNumber.generateAccountNumber();
        String accountNumber = Integer.toString(setAccountNumber);
        LocalDateTime created_at = LocalDateTime.now();
        try {
            accountService.createBankAccount(userId, accountNumber, accountName, accountType, balance, created_at);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        redirectAttributes.addFlashAttribute("success", "Account Created Successfully!");
        return "redirect:/app/dashboard";
    }

    @PostMapping("/delete_account/{id}")
    public String deleteAccount(@PathVariable("id") final Integer id) {
        accountService.deleteAccount(id);
        return "redirect:/app/dashboard";
    }
}

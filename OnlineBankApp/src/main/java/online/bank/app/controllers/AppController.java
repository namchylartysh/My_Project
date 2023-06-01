package online.bank.app.controllers;

import java.math.BigDecimal;
import java.util.List;

import online.bank.app.models.*;
import online.bank.app.repositories.AccountRepository;
import online.bank.app.repositories.PaymentHistoryRepository;
import online.bank.app.repositories.TransactionHistoryRepository;
import online.bank.app.repositories.UserRepository;
import online.bank.app.services.AccountService;
import online.bank.app.services.PaymentHistoryService;
import online.bank.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/app")
public class AppController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PaymentHistoryService paymentHistoryService;

    @Autowired
    PaymentHistoryRepository paymentHistoryRepository;


    @Autowired
    TransactionHistoryRepository transactionHistoryRepository;

    @Autowired
    AccountService accountService;

    @Autowired
    AccountRepository accountRepository;

    @GetMapping("/dashboard")
    public String userDashboard(Model model, HttpSession session) {
        User currentUser = userService.getCurrentUser(session);
        if (currentUser == null) {
            return "redirect:/login";
        }
        if (currentUser.getRole() == Role.ADMIN) {
            return "redirect:/app/dashboard_admin";
        }
        model.addAttribute("user", currentUser);
        List<Account> userAccounts = accountService.getUserAccountsById(currentUser.getUser_id());
        BigDecimal totalAccountsBalance = accountService.getTotalBalance(currentUser.getUser_id());
        model.addAttribute("userAccounts", userAccounts);
        model.addAttribute("totalBalance", totalAccountsBalance);
        return "dashboard";
    }

    @GetMapping("/dashboard_admin")
    public String adminDashboard(Model model, HttpSession session) {
        User admin = userService.getCurrentUser(session);
        if (admin == null) {
            return "redirect:/login";
        }
        model.addAttribute("admin", admin);
        List<User> allUsers = userService.findAll();
        List<Account> allUsersAccounts = accountService.findAll();
        model.addAttribute("allUsers", allUsers);
        model.addAttribute("allUsersAccounts", allUsersAccounts);
        return "dashboard_admin";
    }

    @GetMapping("/display_accounts/{id}")
    public String viewUserAccounts (@PathVariable Integer id, Model model, HttpSession session) {
        User admin = userService.getCurrentUser(session);
        if (admin.getRole() != Role.ADMIN) {
            return "redirect:/login";
        }
        model.addAttribute("admin", admin);
        User user = userService.findById(id);
        model.addAttribute("user", user);
        List<Account> thisUserAccounts = accountRepository.findAllByUserId(id);
        model.addAttribute("thisUserAccounts", thisUserAccounts);
        return "display_accounts";
    }

    @GetMapping("/transaction_history")
    public String getTransactHistory(Model model, HttpSession session) {
        User currentUser = userService.getCurrentUser(session);
        List<TransactionHistory> userTransactHistory = transactionHistoryRepository.getTransactionRecordsById(currentUser.getUser_id());
        model.addAttribute("transaction_history", userTransactHistory);
        return "transaction_history";
    }
}

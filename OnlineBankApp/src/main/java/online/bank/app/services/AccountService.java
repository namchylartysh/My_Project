package online.bank.app.services;

import online.bank.app.models.Account;
import online.bank.app.models.User;
import online.bank.app.repositories.AccountRepository;
import online.bank.app.repositories.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AccountService {

    private static final Logger logger = LogManager.getLogger(AccountService.class);

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    public List<Account> findAll() {
        logger.fatal("Executing findAll method");
        return accountRepository.findAll();
    }

    public void deleteAccount(Integer id) {
        logger.fatal("Executing deleteAccount method with id: " + id);
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("This account not found"));
        accountRepository.delete(account);
    }


    public void createBankAccount(int user_id, String accountNumber, String accountName, String accountType, BigDecimal balance, LocalDateTime created_at) throws Exception {
        logger.fatal("Executing createBankAccount method");
        Account account = new Account();
        account.setAccountNumber(accountNumber);
        account.setAccountName(accountName);
        account.setAccountType(accountType);
        account.setBalance(balance);
        account.setCreated_at(created_at);
        User user = userRepository.findById(user_id).orElseThrow(() -> new Exception("User not found with ID: " + user_id));
        account.setUser_id(user_id);
        account.setUser(user);
        accountRepository.save(account);
    }


    public List<Account> getUserAccountsById(int user_id) {
        logger.fatal("Executing getUserAccountsById method with user_id: " + user_id);
        List<Account> accounts = accountRepository.findAllByUserId(user_id);

//        if (accounts.isEmpty()) {
//            throw new NoSuchElementException("No accounts found for user with ID: " + user_id);
//        }

        return accounts;
    }

    public double getAccountBalance(int user_id, int account_id) {
        logger.fatal("Executing getAccountBalance method with user_id: " + user_id + ", account_id: " + account_id);
        return accountRepository.getAccountBalance(user_id, account_id);
    }

    public BigDecimal getTotalBalance(int user_id) {
        logger.fatal("Executing getTotalBalance method with user_id: " + user_id);
        List<Account> userAccounts = getUserAccountsById(user_id);
//
//        if (userAccounts.isEmpty()) {
//            throw new NoSuchElementException("No accounts found for user with ID: " + user_id);
//        }

        BigDecimal totalBalance = BigDecimal.ZERO;
        for (Account account : userAccounts) {
            totalBalance = totalBalance.add(account.getBalance());
        }

        return totalBalance;
    }
}

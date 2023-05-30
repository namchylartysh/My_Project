package online.bank.app.services;

import online.bank.app.models.Account;
import online.bank.app.models.Role;
import online.bank.app.models.User;
import online.bank.app.repositories.AccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
class AccountServiceTest {
    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    static final int ACCOUNT_ID = 1;
    static final String NUMBER = "123";
    static final String NAME = "Start-Up";
    static final String TYPE = "business";
    static final BigDecimal BALANCE = BigDecimal.valueOf(100.00);
    static final LocalDateTime CREATED_AT = LocalDateTime.now();

    @Test
    public void createBankAccountTest() throws Exception {
        User user = new User(1, "Tom", "Cruz", "tom@mail.com", "tomcruz", Role.CLIENT);
        userService.save(user);
        String accountNumber = "123";
        String accountName = "Personal";
        String accountType = "Savings";
        BigDecimal balance = new BigDecimal("1000.00");
        LocalDateTime createdAt = LocalDateTime.now();

        accountService.createBankAccount(user.getUser_id(), accountNumber, accountName, accountType, balance, createdAt);

        List<Account> userAccounts = accountService.getUserAccountsById(user.getUser_id());
        Account createdAccount = userAccounts.get(0);

        Assertions.assertEquals(accountNumber, createdAccount.getAccountNumber());
        Assertions.assertEquals(accountName, createdAccount.getAccountName());
        Assertions.assertEquals(accountType, createdAccount.getAccountType());
        Assertions.assertEquals(balance, createdAccount.getBalance());
        Assertions.assertEquals(createdAt, createdAccount.getCreated_at());
        Assertions.assertEquals(user.getUser_id(), createdAccount.getUser().getUser_id());
    }

    @Test
    public void getAccountBalanceTest() {
        User currentUser = new User(1, "John", "Sean", "seanj@mail.com", "sean99", Role.CLIENT);
        userService.save(currentUser);
        Account account = new Account();
        account.setAccount_id(ACCOUNT_ID);
        account.setAccountNumber(NUMBER);
        account.setAccountName(NAME);
        account.setAccountType(TYPE);
        account.setBalance(BALANCE);
        account.setCreated_at(CREATED_AT);
        account.setUser_id(currentUser.getUser_id());
        accountRepository.save(account);
        BigDecimal result;
        result = BigDecimal.valueOf(accountService.getAccountBalance(currentUser.getUser_id(), account.getAccount_id()));
        Assertions.assertEquals(BALANCE, result);
    }

}
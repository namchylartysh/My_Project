package online.bank.app.services;

import online.bank.app.models.Account;
import online.bank.app.models.User;
import online.bank.app.repositories.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.controller.vo.AccountListResponse;
import spring.controller.vo.TransactionHistoryListResponse;

import javax.servlet.http.HttpSession;
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

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    TransactionHistoryRepository transactionHistoryRepository;

    @Autowired
    TransactRepository transactRepository;

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
        return accounts;
    }

    public Double getAccountBalance(int user_id, int account_id) {
        logger.fatal("Executing getAccountBalance method with user_id: " + user_id + ", account_id: " + account_id);
        return accountRepository.getAccountBalance(user_id, account_id);
    }

//    public Double getBalanceAccount(int account_id) {
//
//    }

    public BigDecimal getTotalBalance(int user_id) {
        logger.fatal("Executing getTotalBalance method with user_id: " + user_id);
        List<Account> userAccounts = getUserAccountsById(user_id);
        BigDecimal totalBalance = BigDecimal.ZERO;
        for (Account account : userAccounts) {
            totalBalance = totalBalance.add(account.getBalance());
        }

        return totalBalance;
    }

    public void deposit(int accountId, Double depositAmount) {
//        currentBalance = accountService.getAccountBalance(user.getUser_id(), account_id);
//        newBalance = currentBalance + depositAmountValue;
        accountRepository.changeAccountBalanceById(depositAmount, accountId);
    }

    public AccountListResponse displayAccounts(Integer id) {
        return (AccountListResponse) getUserAccountsById(id);
    }

    public boolean makePayment(String recipient, String accountNumber, int accountId, String reference, int paymentAmount) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        String reasonCode = "Payment Processed Successfully!";
        return paymentRepository.makePayment(accountId, recipient, accountNumber, paymentAmount, reference, "success", reasonCode, currentDateTime);
    }

    public TransactionHistoryListResponse getTransactionHistory(int id) {
        return (TransactionHistoryListResponse) transactionHistoryRepository.getTransactionRecordsById(id);
    }

    public boolean transferAmount(String transferFrom, String transferTo, int transferAmount, HttpSession session) {


        String errorMessage;

        if (transferFrom.isEmpty() || transferTo.isEmpty() || transferAmount <= 0) {
            errorMessage = "Invalid transfer details. The account transferring from and to along with the amount cannot be empty or zero.";
            throw new IllegalArgumentException(errorMessage);
        }

        int transferFromId = Integer.parseInt(transferFrom);
        int transferToId = Integer.parseInt(transferTo);


        if (transferFromId == transferToId) {
            errorMessage = "Cannot transfer into the same account. Please select the appropriate account to perform the transfer.";
            throw new IllegalArgumentException(errorMessage);
        }

        double currentBalanceOfAccountTransferringFrom = getAccountBalance(userService.getCurrentUser(session).getUser_id(), transferFromId);

        if (currentBalanceOfAccountTransferringFrom < transferAmount) {
            errorMessage = "Insufficient funds to perform this transfer!";
            throw new IllegalArgumentException(errorMessage);
        }

        double currentBalanceOfAccountTransferringTo = getAccountBalance(userService.getCurrentUser(session).getUser_id(), transferToId);

        double newBalanceOfAccountTransferringFrom = currentBalanceOfAccountTransferringFrom - transferAmount;
        double newBalanceOfAccountTransferringTo = currentBalanceOfAccountTransferringTo + transferAmount;

        // Change the balance of the account transferring from:
        accountRepository.changeAccountBalanceById(newBalanceOfAccountTransferringFrom, transferFromId);

        // Change the balance of the account transferring to:
        accountRepository.changeAccountBalanceById(newBalanceOfAccountTransferringTo, transferToId);

        return true;
    }

    public boolean withdraw(int accountId, int withdrawalAmount) {
        return true;
    }
}

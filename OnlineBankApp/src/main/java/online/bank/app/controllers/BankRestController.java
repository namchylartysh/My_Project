package online.bank.app.controllers;


import online.bank.app.models.Account;
import online.bank.app.models.Role;
import online.bank.app.repositories.PaymentRepository;
import online.bank.app.repositories.TransactRepository;
import spring.controller.vo.TransactionHistory;
import online.bank.app.controllers.vo.AccountVO;
import online.bank.app.controllers.vo.UserVO;
import online.bank.app.repositories.AccountRepository;
import online.bank.app.repositories.UserRepository;
import online.bank.app.services.AccountService;
import online.bank.app.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.controller.BankRestApi;
import spring.controller.vo.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

@RestController
public class BankRestController implements BankRestApi {

    private final UserService userService;
    private final UserRepository userRepository;
    private final AccountService accountService;
    private final AccountRepository accountRepository;
    private final PaymentRepository paymentRepository;
    private final TransactRepository transactRepository;

    public BankRestController(UserService userService, UserRepository userRepository, AccountService accountService, AccountRepository accountRepository, PaymentRepository paymentRepository, TransactRepository transactRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.accountService = accountService;
        this.accountRepository = accountRepository;
        this.paymentRepository = paymentRepository;
        this.transactRepository = transactRepository;
    }

    @Override
    public ResponseEntity<AccountVO> createAccount(AccountVO body) {
        AccountVO accountVO = new AccountVO();
        accountVO.setAccount_id(body.getAccount_id());
        accountVO.setAccountNumber(body.getAccountNumber());
        accountVO.setAccountName(body.getAccountName());
        accountVO.setAccountType(body.getAccountType());
        accountVO.setBalance(body.getBalance());
        accountVO.setCreated_at(body.getCreated_at());
        return ResponseEntity.ok(accountVO);
    }

    @Override
    public ResponseEntity<Void> deleteAccount(Integer id) {
        accountRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> deposit(InlineObject inlineObject) {
        int accountId = inlineObject.getAccountId();
        double amount = inlineObject.getDepositAmount();

        Account account = accountService.findById(accountId);
        if (account == null) {
            return ResponseEntity.notFound().build();
        }
        BigDecimal currentBalance = account.getBalance();
        BigDecimal newBalance = currentBalance.add(BigDecimal.valueOf(amount));
        account.setBalance(newBalance);
        accountRepository.save(account);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<AccountVO>> displayAccounts(Integer id) {
        List<Account> accounts = accountRepository.findAllByUserId(id);
        List<AccountVO> accountVOs = new ArrayList<>();
        for (Account account : accounts) {
            AccountVO accountVO = AccountVO.valueOf(account);
            accountVOs.add(accountVO);
        }
        return ResponseEntity.ok(accountVOs);
    }

    @Override
    public ResponseEntity<UserVO> loginUser(String email, String password) {
        UserVO user = UserVO.valueOf(userService.authenticate(email, password));
        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<Void> payment(InlineObject3 inlineObject3) {

        String recipient = inlineObject3.getRecipient();
        String accountNumber = inlineObject3.getAccountNumber();
        String accountId = inlineObject3.getAccountId();
        String reference = inlineObject3.getReference();
        double paymentAmount = inlineObject3.getPaymentAmount();

        String errorMessage;
        String successMessage;

        int accountID = Integer.parseInt(accountId);

        Account account = accountService.findById(accountID);
        if (account == null) {
            return ResponseEntity.notFound().build();
        }

        BigDecimal currentBalance = account.getBalance();
        BigDecimal paymentAmountBigDecimal = BigDecimal.valueOf(paymentAmount);

        BigDecimal newBalance = currentBalance.subtract(paymentAmountBigDecimal);

        String reasonCode = "Payment Processed Successfully!";
        paymentRepository.makePayment(accountID, recipient, accountNumber, paymentAmount, reference, "success", reasonCode, LocalDateTime.now());

        account.setBalance(newBalance);
        accountRepository.save(account);

        successMessage = reasonCode;
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<UserVO> registerUser(User user) {
        UserVO userVO = new UserVO(user.getUserId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), Role.CLIENT);
        return ResponseEntity.ok(userVO);
    }

    @Override
    public ResponseEntity<List<TransactionHistory>> transactionHistory(Integer id) {
        List<online.bank.app.models.TransactionHistory> transactions = accountService.getTransactionHistory(id);

        List<TransactionHistory> transactionHistories = new ArrayList<>();
        for (online.bank.app.models.TransactionHistory transaction : transactions) {
            TransactionHistory history = new TransactionHistory();
            history.setUserId(transaction.getAccount_id());
            history.setAmount(transaction.getAmount());
            history.setCreatedAt(transaction.getCreated_at());
            transactionHistories.add(history);
        }

        return ResponseEntity.ok(transactionHistories);
    }

    @Override
    public ResponseEntity<Void> transfer(InlineObject1 inlineObject1) {
        int sourceAccountId = Integer.parseInt(inlineObject1.getTransferFrom());
        int destinationAccountId = Integer.parseInt(inlineObject1.getTransferTo());
        Double amount = Double.valueOf(inlineObject1.getTransferAmount());

        Account sourceAccount = accountService.findById(sourceAccountId);

        Account destinationAccount = accountService.findById(destinationAccountId);

        BigDecimal sourceBalance = sourceAccount.getBalance();

        BigDecimal newSourceBalance = sourceBalance.subtract(BigDecimal.valueOf(amount));
        sourceAccount.setBalance(newSourceBalance);
        accountRepository.save(sourceAccount);

        BigDecimal destinationBalance = destinationAccount.getBalance();
        BigDecimal newDestinationBalance = destinationBalance.add(BigDecimal.valueOf(amount));
        destinationAccount.setBalance(newDestinationBalance);
        accountRepository.save(destinationAccount);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> withdraw(InlineObject2 inlineObject2) {
        int accountId = inlineObject2.getAccountId();
        BigDecimal amount = BigDecimal.valueOf(inlineObject2.getWithdrawalAmount());

        Account account = accountService.findById(accountId);
        BigDecimal currentBalance = account.getBalance();
        BigDecimal newBalance = currentBalance.subtract(amount);
        account.setBalance(newBalance);
        accountRepository.save(account);
        return ResponseEntity.ok().build();
    }
}

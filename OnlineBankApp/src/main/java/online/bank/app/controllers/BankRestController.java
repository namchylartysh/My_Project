package online.bank.app.controllers;

import online.bank.app.controllers.vo.AccountVO;
import online.bank.app.controllers.vo.TransactionHistoryVO;
import online.bank.app.controllers.vo.UserVO;
import online.bank.app.services.AccountService;
import online.bank.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.controller.BankRestApi;
import spring.controller.vo.*;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
public class BankRestController implements BankRestApi {

    private List<AccountVO> accounts = new ArrayList<>();
    private List<TransactionHistoryVO> transactionHistory = new ArrayList<>();
    private List<UserVO> users = new ArrayList<>();

    private final UserService userService;

    private final AccountService accountService;

    @Autowired
    public BankRestController(UserService userService, AccountService accountService, HttpSession session) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    @PostMapping("/app/create_account")
    public ResponseEntity<Object> createAccount(@RequestBody InlineObject inlineObject) throws Exception {
        int account_id = inlineObject.getAccountId();
        String accountNumber = inlineObject.getAccountNumber();
        String accountName = inlineObject.getAccountName();
        String accountType = inlineObject.getAccountType();
        BigDecimal balance = BigDecimal.valueOf(inlineObject.getBalance());
        LocalDateTime created_at = LocalDateTime.now();
        accountService.createBankAccount(account_id, accountNumber, accountName, accountType, balance, created_at);

        return ResponseEntity.ok().build();
    }

    @Override
    @DeleteMapping("/account/delete_account/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Integer id) {
        accountService.deleteAccount(id);
        return ResponseEntity.ok().build();
    }

    @Override
    @PostMapping("/deposit")
    public ResponseEntity<Void> deposit(@RequestBody InlineObject1 inlineObject1) {
        int account_id = inlineObject1.getAccount_id();
        Double depositAmount = inlineObject1.getDepositAmount();

//        accountService.getAccountBalance(userService.getCurrentUser(session).getUser_id(), account_id);

        accountService.deposit(account_id, depositAmount);

        return ResponseEntity.ok().build();
    }

    @Override
    @GetMapping("/display_accounts/{id}")
    public ResponseEntity<AccountListResponse> displayAccounts(@PathVariable Integer id) {
        AccountListResponse accountListResponse = accountService.displayAccounts(id);
        if (accountListResponse == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(accountListResponse);
    }

    @Override
    @PostMapping("/payment")
    public ResponseEntity<Void> payment(@RequestBody InlineObject4 inlineObject4) {
        String recipient = inlineObject4.getRecipient();
        String accountNumber = inlineObject4.getAccountNumber();
        int accountId = Integer.parseInt(inlineObject4.getAccountId());
        String reference = inlineObject4.getReference();
        int paymentAmount = inlineObject4.getPaymentAmount();

        boolean success = accountService.makePayment(recipient, accountNumber, accountId, reference, paymentAmount);

        if (success) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    @Override
    @PostMapping("/transfer")
    public ResponseEntity<Void> transfer(@RequestBody InlineObject2 inlineObject2, HttpSession session) {
        String transferFrom = inlineObject2.getTransferFrom();
        String transferTo = inlineObject2.getTransferTo();
        int transferAmount = inlineObject2.getTransferAmount();

        boolean success = accountService.transferAmount(transferFrom, transferTo, transferAmount, session);

        if (success) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Override
    @PostMapping("/withdraw")
    public ResponseEntity<Void> withdraw(@RequestBody InlineObject3 inlineObject3) {
        int accountId = inlineObject3.getAccountId();
        int withdrawalAmount = inlineObject3.getWithdrawalAmount();

        boolean success = accountService.withdraw(accountId, withdrawalAmount);

        if (success) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Override
    @GetMapping("/transaction_history")
    public ResponseEntity<TransactionHistoryListResponse> transactionHistory(@RequestBody InlineObject inlineObject) {
        int id = inlineObject.getAccountId();
        // Call the service method to get the transaction history
        TransactionHistoryListResponse transactionHistoryListResponse = accountService.getTransactionHistory(id);

        if (transactionHistoryListResponse == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(transactionHistoryListResponse);
    }

    @Override
    public ResponseEntity<TransactionHistoryListResponse> transactionHistory() {
        return null;
    }

    @Override
    public ResponseEntity<Void> transfer(InlineObject2 inlineObject2) {
        return null;
    }

}

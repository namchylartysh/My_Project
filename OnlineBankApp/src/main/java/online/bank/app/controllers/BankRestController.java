package online.bank.app.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import spring.controller.BankRestApi;
import spring.controller.vo.*;

@RestController
public class BankRestController implements BankRestApi {

    @Override
    public ResponseEntity<Object> createAccount(InlineObject inlineObject) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteAccount(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deposit(InlineObject1 inlineObject1) {
        return null;
    }

    @Override
    public ResponseEntity<AccountListResponse> displayAccounts(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<Void> payment(InlineObject4 inlineObject4) {
        return null;
    }

    @Override
    public ResponseEntity<TransactionHistoryListResponse> transactionHistory() {
        return null;
    }

    @Override
    public ResponseEntity<Void> transfer(InlineObject2 inlineObject2) {
        return null;
    }

    @Override
    public ResponseEntity<Void> withdraw(InlineObject3 inlineObject3) {
        return null;
    }
}

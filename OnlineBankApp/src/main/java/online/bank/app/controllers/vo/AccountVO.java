package online.bank.app.controllers.vo;

import online.bank.app.models.Account;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AccountVO {
    private int account_id;
    private String accountNumber;
    private String accountName;
    private String accountType;
    private BigDecimal balance;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public AccountVO(int account_id, String accountNumber, String accountName, String accountType, BigDecimal balance, LocalDateTime created_at, LocalDateTime updated_at) {
        this.account_id = account_id;
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.accountType = accountType;
        this.balance = balance;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getAccount_id() {
        return account_id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getAccountType() {
        return accountType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public static AccountVO valueOf(Account account) {
        return new AccountVO(account.getAccount_id(), account.getAccountNumber(), account.getAccountName(), account.getAccountType(), account.getBalance(), account.getCreated_at(), account.getUpdated_at());
    }
}

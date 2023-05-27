package online.bank.app.controllers.vo;

import online.bank.app.models.TransactionHistory;


public class TransactionHistoryVO {
    private int transaction_id;
    private int account_id;
    private String transaction_type;
    private double amount;
    private String source;
    private String status;
    private String reason_code;

    public TransactionHistoryVO(int transaction_id, int account_id, String transaction_type, double amount, String source, String status, String reason_code) {
        this.transaction_id = transaction_id;
        this.account_id = account_id;
        this.transaction_type = transaction_type;
        this.amount = amount;
        this.source = source;
        this.status = status;
        this.reason_code = reason_code;
    }

    public int getTransaction_id() {
        return transaction_id;
    }

    public int getAccount_id() {
        return account_id;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public double getAmount() {
        return amount;
    }

    public String getSource() {
        return source;
    }

    public String getStatus() {
        return status;
    }

    public String getReason_code() {
        return reason_code;
    }

    public static TransactionHistoryVO valueOf(TransactionHistory transactionHistory) {
        return new TransactionHistoryVO(transactionHistory.getTransaction_id(), transactionHistory.getAccount_id(), transactionHistory.getTransaction_type(), transactionHistory.getAmount(), transactionHistory.getSource(), transactionHistory.getStatus(), transactionHistory.getReason_code());
    }
}

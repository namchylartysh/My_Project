package online.bank.app.controllers.vo;

import online.bank.app.models.Transact;

import java.time.LocalDateTime;

public class TransactVO {

    private int transaction_id;
    private String transaction_type;
    private double amount;
    private String source;
    private String status;
    private String reason_code;
    private LocalDateTime created_at;

    public TransactVO(int transaction_id, String transaction_type, double amount, String source, String status, String reason_code, LocalDateTime created_at) {
        this.transaction_id = transaction_id;
        this.transaction_type = transaction_type;
        this.amount = amount;
        this.source = source;
        this.status = status;
        this.reason_code = reason_code;
        this.created_at = created_at;
    }

    public int getTransaction_id() {
        return transaction_id;
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

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public static TransactVO valueOf(Transact transact) {
        return new TransactVO(transact.getTransaction_id(), transact.getTransaction_type(), transact.getAmount(), transact.getSource(), transact.getStatus(), transact.getReason_code(), transact.getCreated_at());
    }
}

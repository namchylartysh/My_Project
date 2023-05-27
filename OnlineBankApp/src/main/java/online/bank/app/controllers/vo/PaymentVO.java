package online.bank.app.controllers.vo;

import online.bank.app.models.Payment;

import java.time.LocalDateTime;

public class PaymentVO {

    private int payment_id;
    private String recipient;
    private String recipient_account_number;
    private double amount;
    private String status;
    private LocalDateTime created_at;
    private String reference_number;
    private String reason_code;

    public PaymentVO(int payment_id, String recipient, String recipient_account_number, double amount, String status, LocalDateTime created_at, String reference_number, String reason_code) {
        this.payment_id = payment_id;
        this.recipient = recipient;
        this.recipient_account_number = recipient_account_number;
        this.amount = amount;
        this.status = status;
        this.created_at = created_at;
        this.reference_number = reference_number;
        this.reason_code = reason_code;
    }

    public int getPayment_id() {
        return payment_id;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getRecipient_account_number() {
        return recipient_account_number;
    }

    public double getAmount() {
        return amount;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public String getReference_number() {
        return reference_number;
    }

    public String getReason_code() {
        return reason_code;
    }

    public static PaymentVO valueOf(Payment payment) {
        return new PaymentVO(payment.getPayment_id(), payment.getRecipient(), payment.getRecipient_account_number(), payment.getAmount(), payment.getStatus(), payment.getCreated_at(), payment.getReference_number(), payment.getReason_code());
    }
}

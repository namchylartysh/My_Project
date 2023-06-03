package online.bank.app.repositories;

import online.bank.app.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    @Modifying
    @Query(value = " INSERT INTO payments(account_id, recipient, recipient_account_number, amount, reference_number, status, reason_code, created_at) " +
            "VALUES(:account_id,:recipient, :recipient_account_number, :amount, :reference_number, :status, :reason_code, :created_at )", nativeQuery = true)
    @Transactional
    void makePayment(@Param("account_id") int account_id,
                     @Param("recipient") String recipient,
                     @Param("recipient_account_number") String recipient_account_number,
                     @Param("amount") double amount,
                     @Param("reference_number") String reference_number,
                     @Param("status") String status,
                     @Param("reason_code") String reason_code,
                     @Param("created_at") LocalDateTime created_at);
}

package online.bank.app.repositories;

import online.bank.app.models.Transact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Repository
public interface TransactRepository extends JpaRepository<Transact, Integer> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO transaction_history(account_id, transaction_type, amount, source, status, reason_code, created_at, user_id)" +
            "VALUES(:account_id, :transact_type, :amount, :source, :status, :reason_code, :created_at, :user_id)", nativeQuery = true)
    void recordTransaction(@Param("account_id")int account_id,
                        @Param("transact_type")String transact_type,
                        @Param("amount")double amount,
                        @Param("source")String source,
                        @Param("status")String status,
                        @Param("reason_code")String reason_code,
                        @Param("created_at") LocalDateTime created_at, @Param("user_id") int user_id);
}

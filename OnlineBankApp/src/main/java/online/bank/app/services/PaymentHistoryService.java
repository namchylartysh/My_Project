package online.bank.app.services;

import online.bank.app.models.PaymentHistory;
import online.bank.app.repositories.PaymentHistoryRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;


@Service
public class PaymentHistoryService {

    private static final Logger logger = LogManager.getLogger(PaymentHistoryService.class);

    @Autowired
    private PaymentHistoryRepository paymentHistoryRepository;

    public PaymentHistory getPaymentRecordsById(Integer id) {
        logger.fatal("Executing getPaymentRecordsById method with id: " + id);
        return paymentHistoryRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }
}

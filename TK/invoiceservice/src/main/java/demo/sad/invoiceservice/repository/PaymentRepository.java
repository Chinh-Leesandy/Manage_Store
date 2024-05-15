package demo.sad.invoiceservice.repository;

import demo.sad.invoiceservice.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, String> {
}

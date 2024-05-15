package demo.sad.invoiceservice.repository;

import demo.sad.invoiceservice.entity.Invoice;
import org.bouncycastle.util.Times;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, String> {
    List<Invoice> findAllByCustomer_CustomerIdOrderByTimeAsc(String customerId);

    Invoice findFirstByOrderByTimeAsc();

    Invoice findFirstByOrderByTimeDesc();

    List<Invoice> findAllByTimeBetween(Date start, Date end);
}

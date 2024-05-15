package demo.sad.invoiceservice.service;

import demo.sad.invoiceservice.entity.Customer;
import demo.sad.invoiceservice.entity.Invoice;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface InvoiceService {
    String hello();

    boolean createInvoice(Invoice invoice);

    Invoice getInvoice(String invoiceId);

    List<Invoice> getAllInvoicesOfCustomer(String customerId);

    List<Invoice> getAll();

    Invoice confirmTransaction(Invoice invoiceId);

    Invoice getFirstInvoiceByTimeAsc();

    Invoice getFirstInvoiceByTimeDesc();

    List<Invoice> getInvoicesByTimeBetween(Date start, Date end);

}

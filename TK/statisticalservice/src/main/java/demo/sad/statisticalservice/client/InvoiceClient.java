package demo.sad.statisticalservice.client;

import demo.sad.statisticalservice.entity.Invoice;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@FeignClient(value = "invoice-service", url = "http://localhost:8090")
@Service
public interface InvoiceClient {
    @GetMapping("api/v1/invoice/history")
    List<Invoice> getInvoiceHistoryByCustomerId(@RequestParam String customerId);

    @GetMapping("api/v1/invoice/all")
    List<Invoice> getAll();

    @GetMapping("api/v1/invoice/first-invoice-time-asc")
    Invoice getFirstInvoiceByTimeAsc();

    @GetMapping("api/v1/invoice/first-invoice-time-desc")
    Invoice getFirstInvoiceByTimeDesc();

    @GetMapping("api/v1/invoice/get-invoices-between")
    List<Invoice> getInvoicesByTimeBetween(@RequestParam String start, @RequestParam String end);

}

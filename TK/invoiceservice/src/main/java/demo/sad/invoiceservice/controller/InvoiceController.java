package demo.sad.invoiceservice.controller;


import demo.sad.invoiceservice.client.CartClient;
import demo.sad.invoiceservice.client.DiscoveryClient;
import demo.sad.invoiceservice.entity.Customer;
import demo.sad.invoiceservice.entity.Invoice;
import demo.sad.invoiceservice.entity.dto.CustomerInvoiceAdapter;
import demo.sad.invoiceservice.service.InvoiceService;
import demo.sad.invoiceservice.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/invoice")
@CrossOrigin
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;

    @GetMapping("/hello-all")
    public String helloAll() {
        return invoiceService.hello();
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello from Invoice Service";
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(invoiceService.getAll());
    }

    @GetMapping("/history")
    public ResponseEntity<?> getInvoiceHistoryByCustomerId(@RequestParam String customerId) {
        return ResponseEntity.ok(invoiceService.getAllInvoicesOfCustomer(customerId));
    }


    @PostMapping("/create")
    public ResponseEntity<?> createInvoice(@RequestBody Invoice invoice) {

        return ResponseEntity.ok(invoiceService.createInvoice(invoice));
    }

    @PostMapping("/confirm-transaction")
    public ResponseEntity<?> confirmTransaction(@RequestBody Invoice invoice) {
        System.out.println("Confirm from invoice");
        return ResponseEntity.ok(invoiceService.confirmTransaction(invoice));
    }

    @GetMapping("/first-invoice-time-asc")
    public ResponseEntity<?> getFirstInvoiceByTimeAsc() {
        return ResponseEntity.ok(invoiceService.getFirstInvoiceByTimeAsc());
    }

    @GetMapping("/first-invoice-time-desc")
    public ResponseEntity<?> getFirstInvoiceByTimeDesc() {
        return ResponseEntity.ok(invoiceService.getFirstInvoiceByTimeDesc());
    }

    @GetMapping("/get-invoices-between")
    public ResponseEntity<?> getInvoicesBetween(@RequestParam String start, @RequestParam String end) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = formatter.parse(start);
        Date endDate = formatter.parse(end);

        return ResponseEntity.ok(invoiceService.getInvoicesByTimeBetween(startDate, endDate));
    }

}

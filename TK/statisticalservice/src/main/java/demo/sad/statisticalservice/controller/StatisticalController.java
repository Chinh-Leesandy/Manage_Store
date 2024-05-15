package demo.sad.statisticalservice.controller;


import demo.sad.statisticalservice.client.CustomerClient;
import demo.sad.statisticalservice.client.InvoiceClient;
import demo.sad.statisticalservice.entity.statistical.CustomerIncomeStatistical;
import demo.sad.statisticalservice.entity.statistical.IncomeStatistical;
import demo.sad.statisticalservice.entity.statistical.TimeIncomeStatistical;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/statistical")
public class StatisticalController {

    private final CustomerClient customerClient;
    private final InvoiceClient invoiceClient;



    private IncomeStatistical incomeStatistical;

    public StatisticalController(CustomerClient customerClient, InvoiceClient invoiceClient) {
        this.customerClient = customerClient;
        this.invoiceClient = invoiceClient;
    }

    @GetMapping("/by-customer")
    public ResponseEntity<?> getIncomeStatisticalByCustomer() {
        incomeStatistical = new CustomerIncomeStatistical();
        incomeStatistical.setCustomerClient(customerClient);
        incomeStatistical.setInvoiceClient(invoiceClient);
        List<?> records = incomeStatistical.getRecords();
        return ResponseEntity.ok(records);
    }

    @GetMapping("/by-time")
    public ResponseEntity<?> getIncomeStatisticalByTime() {
        incomeStatistical = new TimeIncomeStatistical();
        incomeStatistical.setCustomerClient(customerClient);
        incomeStatistical.setInvoiceClient(invoiceClient);

        List<?> records = incomeStatistical.getRecords();
        return ResponseEntity.ok(records);
    }
}

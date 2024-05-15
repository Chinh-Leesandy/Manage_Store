package demo.sad.statisticalservice.entity.statistical;

import com.fasterxml.jackson.annotation.JsonIgnore;
import demo.sad.statisticalservice.client.InvoiceClient;
import demo.sad.statisticalservice.entity.Customer;
import demo.sad.statisticalservice.entity.Invoice;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Setter

public class CustomerRecord implements IncomeRecord {
    private Customer customer;
    private List<Invoice> invoices;

    @JsonIgnore
    private InvoiceClient invoiceClient;

    @Override
    public IncomeRecord createRecord() {
        invoices = invoiceClient.getInvoiceHistoryByCustomerId(customer.getCustomerId());
        return this;
    }
}

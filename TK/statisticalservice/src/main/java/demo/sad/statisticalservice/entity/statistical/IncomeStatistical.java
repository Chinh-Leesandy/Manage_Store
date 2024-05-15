package demo.sad.statisticalservice.entity.statistical;

import demo.sad.statisticalservice.client.CustomerClient;
import demo.sad.statisticalservice.client.InvoiceClient;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Setter
public abstract class IncomeStatistical {
    protected InvoiceClient invoiceClient;

    protected CustomerClient customerClient;

    public abstract List<IncomeRecord> getRecords();
}

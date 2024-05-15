package demo.sad.statisticalservice.entity.statistical;

import com.fasterxml.jackson.annotation.JsonIgnore;
import demo.sad.statisticalservice.client.InvoiceClient;
import demo.sad.statisticalservice.entity.Invoice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TimeRecord implements IncomeRecord {

    private int month;
    private int year;
    private List<Invoice> invoices;
    @JsonIgnore
    private InvoiceClient invoiceClient;

    @Override
    public IncomeRecord createRecord() {
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.plusMonths(1).minusDays(1);
//        System.out.println(convertToDate(startDate) + " " + convertToDate(endDate));
        invoices = invoiceClient.getInvoicesByTimeBetween(convertToDate(startDate), convertToDate(endDate));
        return this;
    }

    private String convertToDate(LocalDate localDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return localDate.format(formatter);
    }
}

package demo.sad.statisticalservice.entity.statistical;

import demo.sad.statisticalservice.client.InvoiceClient;
import demo.sad.statisticalservice.entity.Invoice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;


public class TimeIncomeStatistical extends IncomeStatistical {

    @Override
    public List<IncomeRecord> getRecords() {
        Date firstDate = invoiceClient.getFirstInvoiceByTimeAsc().getTime();
        Date lastDate = invoiceClient.getFirstInvoiceByTimeDesc().getTime();
        List<IncomeRecord> incomeRecords = new ArrayList<>();
        for (int i = getYear(firstDate); i <= getYear(lastDate); i++) {
            for (int j = 1; j <= 12; j++) {
                TimeRecord timeRecord = new TimeRecord();
                timeRecord.setYear(i);
                timeRecord.setMonth(j);
                timeRecord.setInvoiceClient(invoiceClient);
                timeRecord.createRecord();
                if (timeRecord.getInvoices().isEmpty()) {
                    continue;
                }
                incomeRecords.add(timeRecord);
            }
        }
        return incomeRecords;
    }


    private int getMonth(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonthValue();
    }

    private int getYear(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear();
    }
}

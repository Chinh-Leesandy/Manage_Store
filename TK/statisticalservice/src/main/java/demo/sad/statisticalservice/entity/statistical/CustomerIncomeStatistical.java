package demo.sad.statisticalservice.entity.statistical;

import demo.sad.statisticalservice.entity.Customer;

import java.util.ArrayList;
import java.util.List;


public class CustomerIncomeStatistical extends IncomeStatistical {

    @Override
    public List<IncomeRecord> getRecords() {
            List< Customer> customers = customerClient.getAllCustomers();
            List<IncomeRecord> incomeRecords = new ArrayList<>();
            for (Customer customer : customers) {
                System.out.println(customer.toString());
                CustomerRecord customerRecord = new CustomerRecord();
                customerRecord.setCustomer(customer);
                customerRecord.setInvoiceClient(invoiceClient);
                incomeRecords.add(customerRecord.createRecord());
            }
            return incomeRecords;
    }
}

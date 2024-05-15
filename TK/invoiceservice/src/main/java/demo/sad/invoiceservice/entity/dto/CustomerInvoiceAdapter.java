package demo.sad.invoiceservice.entity.dto;


import demo.sad.invoiceservice.entity.Customer;
import demo.sad.invoiceservice.entity.Invoice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerInvoiceAdapter {
    private Customer customer;

    private Invoice invoice;
}

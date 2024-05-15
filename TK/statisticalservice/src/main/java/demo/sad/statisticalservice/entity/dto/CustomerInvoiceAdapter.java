package demo.sad.statisticalservice.entity.dto;


import demo.sad.statisticalservice.entity.Customer;
import demo.sad.statisticalservice.entity.Invoice;
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

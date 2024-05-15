package demo.sad.invoiceservice.service.impl;

import demo.sad.invoiceservice.client.CartClient;
import demo.sad.invoiceservice.client.DiscoveryClient;
import demo.sad.invoiceservice.entity.Customer;
import demo.sad.invoiceservice.entity.Invoice;
import demo.sad.invoiceservice.entity.InvoiceProduct;
import demo.sad.invoiceservice.entity.Product;
import demo.sad.invoiceservice.repository.InvoiceProductRepository;
import demo.sad.invoiceservice.repository.InvoiceRepository;
import demo.sad.invoiceservice.service.InvoiceService;
import demo.sad.invoiceservice.util.Util;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final CartClient cartClient;
    private final DiscoveryClient discoveryClient;


    @Override
    public String hello() {
        System.out.println(cartClient.hello());
        System.out.println(discoveryClient.hello());
        return "Hello from Invoice Service";
    }


    @Override
    public boolean createInvoice(Invoice invoice) {
        invoice.setInvoiceId(Util.createId());


        for (InvoiceProduct invoiceProduct : invoice.getInvoiceProducts()) {
            invoiceProduct.setInvoice(invoice);
        }

        Invoice savedInvoice = invoiceRepository.save(invoice);
        return savedInvoice.getInvoiceId() != null;
    }

    @Override
    public Invoice getInvoice(String invoiceId) {
        return invoiceRepository.findById(invoiceId).orElse(null);
    }

    @Override
    public List<Invoice> getAllInvoicesOfCustomer(String customerId) {
        return invoiceRepository.findAllByCustomer_CustomerIdOrderByTimeAsc(customerId);
    }

    @Override
    public List<Invoice> getAll() {
        return invoiceRepository.findAll();
    }

    @Override
    public Invoice confirmTransaction(Invoice invoice) {
        invoice = invoiceRepository.findById(invoice.getInvoiceId()).orElse(null);
        if (invoice == null) {
            System.out.println("Invoice is null");
            return null;
        }

        discoveryClient.confirmTransaction(invoice);
        cartClient.confirmTransaction(invoice);

        return invoice;
    }

    @Override
    public Invoice getFirstInvoiceByTimeAsc() {
        return invoiceRepository.findFirstByOrderByTimeAsc();
    }

    @Override
    public Invoice getFirstInvoiceByTimeDesc() {
        return invoiceRepository.findFirstByOrderByTimeDesc();
    }

    @Override
    public List<Invoice> getInvoicesByTimeBetween(Date start, Date end) {
        return invoiceRepository.findAllByTimeBetween(start, end);
    }

}

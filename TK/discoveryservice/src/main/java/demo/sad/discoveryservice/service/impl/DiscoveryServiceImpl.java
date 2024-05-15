package demo.sad.discoveryservice.service.impl;

import demo.sad.discoveryservice.entity.Invoice;
import demo.sad.discoveryservice.entity.InvoiceProduct;
import demo.sad.discoveryservice.repository.ProductRepository;
import demo.sad.discoveryservice.service.DiscoveryService;
import demo.sad.discoveryservice.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class DiscoveryServiceImpl implements DiscoveryService {


    private final ProductRepository productRepository;

    public DiscoveryServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> searchByName(String keyword) {
        return productRepository.findAllByNameContains(keyword);
    }

    @Override
    public Invoice confirmTransaction(Invoice invoice) {
        System.out.println(invoice);
        for (InvoiceProduct invoiceProduct : invoice.getInvoiceProducts()) {
            Product product = productRepository.findById(invoiceProduct.getId().getProductId()).orElse(null);
            if (product != null) {
                product.setQuantity(product.getQuantity() - invoiceProduct.getAmount());
                productRepository.save(product);
            }
        }

        return invoice;
    }

}

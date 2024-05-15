package demo.sad.discoveryservice.service;

import demo.sad.discoveryservice.entity.Product;
import demo.sad.discoveryservice.entity.Invoice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DiscoveryService {
   List<Product> searchByName(String keyword);

   Invoice confirmTransaction(Invoice invoice);
}
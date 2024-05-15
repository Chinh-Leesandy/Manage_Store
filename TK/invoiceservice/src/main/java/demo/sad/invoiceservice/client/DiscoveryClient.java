package demo.sad.invoiceservice.client;

import demo.sad.invoiceservice.entity.Invoice;
import demo.sad.invoiceservice.entity.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "http://localhost:8088/", value = "discovery-service")
@Service
public interface DiscoveryClient {

    @PostMapping("/api/v1/discovery/confirm-transaction")
    Invoice confirmTransaction(Invoice invoice);

    @GetMapping("/api/v1/discovery/hello")
    String hello();

    @GetMapping("/api/v1/discovery/find-by-id")
    Product getProduct(@RequestParam String productId);
}

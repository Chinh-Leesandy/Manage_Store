package demo.sad.invoiceservice.client;


import demo.sad.invoiceservice.entity.Invoice;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@FeignClient(url = "http://localhost:8089", value = "cart-service")
@Service
public interface CartClient {

    @PostMapping("/api/v1/cart/confirm-transaction")
    Invoice confirmTransaction(Invoice invoice);

    @GetMapping("/api/v1/cart/hello")
    String hello();
}

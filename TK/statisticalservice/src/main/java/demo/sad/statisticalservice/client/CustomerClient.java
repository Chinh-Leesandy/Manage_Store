package demo.sad.statisticalservice.client;


import demo.sad.statisticalservice.entity.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "customer-service", url = "http://localhost:8089")
@Service
public interface CustomerClient {
    @GetMapping("api/v1/customer/all")
    List<Customer> getAllCustomers();
}

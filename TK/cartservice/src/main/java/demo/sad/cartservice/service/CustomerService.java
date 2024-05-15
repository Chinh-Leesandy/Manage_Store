package demo.sad.cartservice.service;

import demo.sad.cartservice.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {
    Customer login(String username, String password);

    boolean register(String username, String password);


    Customer createCustomer(Customer customer);

    List<Customer> getAll();
}

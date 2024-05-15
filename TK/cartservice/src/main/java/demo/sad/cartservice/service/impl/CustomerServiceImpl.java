package demo.sad.cartservice.service.impl;

import demo.sad.cartservice.entity.Customer;
import demo.sad.cartservice.repository.CustomerRepository;
import demo.sad.cartservice.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer login(String username, String password) {
        return customerRepository.findByUsernameAndPassword(username, password);
    }

    public boolean register(String username, String password) {
        return true;
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> getAll() {
        return customerRepository.findAll();
    }
}

package demo.sad.cartservice.controller;

import demo.sad.cartservice.service.CustomerService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello from Customer Service";
    }

    @GetMapping("/login")
    public ResponseEntity<?> login(String username, String password) {
        return ResponseEntity.ok(customerService.login(username, password));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(String username, String password) {
        return ResponseEntity.ok(customerService.register(username, password));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(customerService.getAll());
    }
}

package demo.sad.discoveryservice.controller;

import demo.sad.discoveryservice.entity.Invoice;
import demo.sad.discoveryservice.entity.Product;
import demo.sad.discoveryservice.repository.ProductRepository;
import demo.sad.discoveryservice.service.DiscoveryService;
import demo.sad.discoveryservice.util.Util;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/discovery")
public class DiscoveryController {

    private final DiscoveryService discoveryService;
    private final ProductRepository productRepository;

    public DiscoveryController(DiscoveryService discoveryService, ProductRepository productRepository) {
        this.discoveryService = discoveryService;
        this.productRepository = productRepository;
    }


    @GetMapping("/hello")
    public String hello() {
        return "Hello from Discovery Service";
    }

    @GetMapping("/all")
    public ResponseEntity<?> getProducts() {
        return ResponseEntity.ok(productRepository.findAll());
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam String keyword) {
        return ResponseEntity.ok(discoveryService.searchByName(keyword));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        product.setProductId(Util.createIdOrElse(product.getProductId()));
        return ResponseEntity.ok(productRepository.save(product));
    }

    @PostMapping("/confirm-transaction")
    public ResponseEntity<?> confirmTransaction(@RequestBody Invoice invoice) {
        return ResponseEntity.ok(discoveryService.confirmTransaction(invoice));
    }

    @GetMapping("/find-by-id")
    public ResponseEntity<?> getProduct(@RequestParam String productId) {
        return ResponseEntity.ok(productRepository.findById(productId));
    }
}

package demo.sad.cartservice.controller;


import demo.sad.cartservice.entity.Customer;
import demo.sad.cartservice.entity.CustomerProductAdapter;
import demo.sad.cartservice.entity.Invoice;
import demo.sad.cartservice.entity.Product;
import demo.sad.cartservice.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }


    @PostMapping("/removeproduct")
    public ResponseEntity<?> removeProductFromCart(@RequestBody CustomerProductAdapter adapter) {
        System.out.println("adapter: " + adapter.getCartProduct().toString());
        return ResponseEntity.ok(cartService.removeProductToCart(adapter.getCustomer().getCustomerId(), adapter.getCartProduct()));
    }

    @GetMapping("/create")
    public ResponseEntity<?> createCart(@RequestBody Customer customer) {
        return ResponseEntity.ok(cartService.createCart(customer));
    }

    @GetMapping("/get")
    public ResponseEntity<?> getCart(@RequestParam String customerId) {
        return ResponseEntity.ok(cartService.getCart(customerId));
    }

    @PostMapping("/addproduct")
    public ResponseEntity<?> addProductToCart(@RequestBody CustomerProductAdapter adapter) {
        return ResponseEntity.ok(cartService.addProductToCart(adapter.getCustomer().getCustomerId(), adapter.getCartProduct()));
    }

    @GetMapping("/hello")
    public ResponseEntity<?> hello() {
        return ResponseEntity.ok("Hello from Cart Service!");
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(cartService.getAll());
    }

    @PostMapping("/confirm-transaction")
    public ResponseEntity<?> confirmTransaction(@RequestBody Invoice invoice) {
        System.out.println("Confirm from cart");
        return ResponseEntity.ok(cartService.confirmTransaction(invoice));
    }
}

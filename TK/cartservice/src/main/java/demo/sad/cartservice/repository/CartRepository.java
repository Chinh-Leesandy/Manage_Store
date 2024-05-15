package demo.sad.cartservice.repository;

import demo.sad.cartservice.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, String> {
    Cart findByCustomer_CustomerId(String customerId);
}

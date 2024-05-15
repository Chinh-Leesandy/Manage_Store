package demo.sad.cartservice.repository;

import demo.sad.cartservice.entity.CartProduct;
import demo.sad.cartservice.entity.CartProductKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartProductRepository extends JpaRepository<CartProduct, CartProductKey>{

}

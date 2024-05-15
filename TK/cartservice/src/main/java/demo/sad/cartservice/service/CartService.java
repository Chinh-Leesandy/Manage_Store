package demo.sad.cartservice.service;

import demo.sad.cartservice.entity.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {

    Cart getCart(String customerId);

    Cart createCart(String customerId);

    Cart createCart(Customer customer);

    List<Cart> getAll();

    Cart addProductToCart(String customerId, CartProduct product);

    Cart removeProductToCart(String customerId, CartProduct product);

    Cart confirmTransaction(Invoice adapter);
}

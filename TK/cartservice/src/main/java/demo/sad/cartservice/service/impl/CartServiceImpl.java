package demo.sad.cartservice.service.impl;

import demo.sad.cartservice.entity.*;
import demo.sad.cartservice.repository.CartProductRepository;
import demo.sad.cartservice.repository.CartRepository;
import demo.sad.cartservice.repository.CustomerRepository;
import demo.sad.cartservice.service.CartService;
import demo.sad.cartservice.util.Util;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CartServiceImpl implements CartService {
    private final CustomerRepository customerRepository;
    private final CartRepository cartRepository;

    private final CartProductRepository cartProductRepository;

    public CartServiceImpl(CustomerRepository customerRepository, CartRepository cartRepository, CartProductRepository cartProductRepository) {
        this.customerRepository = customerRepository;
        this.cartRepository = cartRepository;
        this.cartProductRepository = cartProductRepository;
    }

    @Override
    public Cart createCart(String customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null) {
            return null;
        }
        return createCart(customer);
    }

    @Override
    public Cart createCart(Customer customer) {
        Cart cart = new Cart();
        cart.setCartId(Util.createId());
        cart.setCustomer(customer);
        return cartRepository.save(cart);
    }

    @Override
    public Cart addProductToCart(String customerId, CartProduct product) {
        Cart cart = cartRepository.findByCustomer_CustomerId(customerId);
        if (cart == null) {
            cart = createCart(customerId);
        }
        System.out.println("product: " + product.toString());
        if (product.getId().getCartId().isEmpty()) {
            product.getId().setCartId(cart.getCartId());
        }
        CartProduct cartProduct = cartProductRepository.findById(product.getId()).orElse(null);
        if (cartProduct == null) {
            cartProductRepository.save(product);
            return cart;
        }
        cartProduct.setAmount(cartProduct.getAmount() + product.getAmount());
        cartProductRepository.save(cartProduct);
        return cart;
    }

    @Override
    public Cart removeProductToCart(String customerId, CartProduct product) {
        Cart cart = cartRepository.findByCustomer_CustomerId(customerId);
        if (product.getId().getCartId().isEmpty()) {
            product.getId().setCartId(cart.getCartId());
        }
        CartProduct cartProduct = cartProductRepository.findById(product.getId()).orElse(null);

        if (cartProduct == null) {
            return null;
        }
        cartProduct.setAmount(cartProduct.getAmount() - product.getAmount());
        if (cartProduct.getAmount() <= 0) {
            cartProductRepository.delete(cartProduct);
        }
//        cartProductRepository.save(cartProduct);
        return cart;
    }

    @Override
    public Cart confirmTransaction(Invoice invoice) {
        Cart cart = cartRepository.findByCustomer_CustomerId(invoice.getCustomer().getCustomerId());
        if (cart == null) {
            return null;
        }

        try {
            Set<CartProduct> cartProducts = cart.getCartProducts();
            cartProductRepository.deleteAll(cartProducts);

//            cartRepository.save(cart);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return cart;
    }

    @Override
    public Cart getCart(String customerId) {
        Cart cart = cartRepository.findByCustomer_CustomerId(customerId);
        return cart == null ? createCart(customerId) : cart;
    }

    @Override
    public List<Cart> getAll() {
        return cartRepository.findAll();
    }
}

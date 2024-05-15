package demo.sad.cartservice.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CartProductKey implements Serializable {
    @Column(name="cart_id")
    String cartId;

    @Column(name="product_id")
    String productId;
}

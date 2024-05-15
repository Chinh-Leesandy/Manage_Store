package demo.sad.cartservice.entity;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "cart_product")
public class CartProduct {
    @EmbeddedId
    CartProductKey id;

    @Column(name = "amount")
    private int amount;

}

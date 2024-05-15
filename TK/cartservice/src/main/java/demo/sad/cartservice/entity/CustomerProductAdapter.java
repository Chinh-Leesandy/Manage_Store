package demo.sad.cartservice.entity;


import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerProductAdapter {
    private Customer customer;
    private CartProduct cartProduct;
}

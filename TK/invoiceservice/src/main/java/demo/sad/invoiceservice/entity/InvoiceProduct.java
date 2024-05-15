package demo.sad.invoiceservice.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "invoice_product")
public class InvoiceProduct {
    @EmbeddedId
    InvoiceProductKey id = new InvoiceProductKey();

    @Column(name = "amount")
    private int amount;

    @Column(name = "total_price")
    private double totalPrice;

    @ManyToOne
    @MapsId("invoiceId")
    @JoinColumn(name = "invoice_id")
    @JsonIgnore
    private Invoice invoice;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

}

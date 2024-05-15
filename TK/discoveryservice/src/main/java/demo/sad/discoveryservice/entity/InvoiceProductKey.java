package demo.sad.discoveryservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class InvoiceProductKey implements Serializable {
    @Column(name = "invoice_id")
    String invoiceId;

    @Column(name = "product_id")
    String productId;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceProductKey that = (InvoiceProductKey) o;
        return Objects.equals(invoiceId, that.invoiceId) &&
                Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoiceId, productId);
    }
}

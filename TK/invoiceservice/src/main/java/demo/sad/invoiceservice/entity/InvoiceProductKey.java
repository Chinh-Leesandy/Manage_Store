package demo.sad.invoiceservice.entity;

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
    String invoiceId;
    String productId;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InvoiceProductKey key)) return false;
        return Objects.equals(invoiceId, key.invoiceId) &&
                Objects.equals(productId, key.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getInvoiceId(), getProductId());
    }
}

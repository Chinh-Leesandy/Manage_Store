package demo.sad.discoveryservice.repository;

import demo.sad.discoveryservice.entity.InvoiceProduct;
import demo.sad.discoveryservice.entity.InvoiceProductKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceProductRepository extends JpaRepository<InvoiceProduct, InvoiceProductKey> {
}

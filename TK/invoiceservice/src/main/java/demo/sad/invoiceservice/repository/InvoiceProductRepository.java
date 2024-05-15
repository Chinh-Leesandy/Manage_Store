package demo.sad.invoiceservice.repository;

import demo.sad.invoiceservice.entity.InvoiceProduct;
import demo.sad.invoiceservice.entity.InvoiceProductKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceProductRepository extends JpaRepository<InvoiceProduct, InvoiceProductKey>{
}

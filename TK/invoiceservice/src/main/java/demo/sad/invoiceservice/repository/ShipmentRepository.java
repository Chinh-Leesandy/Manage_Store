package demo.sad.invoiceservice.repository;

import demo.sad.invoiceservice.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentRepository extends JpaRepository<Shipment, String> {
}

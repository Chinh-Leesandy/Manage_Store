package demo.sad.cartservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "shipment")

public class Shipment {
    @Id
    @Column(name = "shipment_id")
    private String shipmentId;

    @Column(name = "shipment_name")
    private String shipmentName;

    @Column(name = "shipment_cost")
    private double shipmentCost;
}

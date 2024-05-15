package demo.sad.invoiceservice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Timestamp;
import java.util.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "invoice")
@ToString
public class Invoice {
    @Id
//    @GeneratedValue(generator = "uuid")
//    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "invoice_id")
    private String invoiceId;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "shipment_id")
    private Shipment shipment;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
    private Collection<InvoiceProduct> invoiceProducts = new ArrayList<>();

    @Column(name = "total_amount")
    private int totalAmount;

    @Column(name = "total_price")
    private double totalPrice;

    @Column(name = "time", columnDefinition = "Date")
    private Date time;
}
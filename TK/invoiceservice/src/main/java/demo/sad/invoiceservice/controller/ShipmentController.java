package demo.sad.invoiceservice.controller;


import demo.sad.invoiceservice.entity.Shipment;
import demo.sad.invoiceservice.repository.ShipmentRepository;
import demo.sad.invoiceservice.util.Util;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/shipment")
public class ShipmentController {

    private final ShipmentRepository shipmentRepository;

    public ShipmentController(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello from Shipment Service";
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(shipmentRepository.findAll());
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateShipment(Shipment shipment) {
        Shipment existingShipment = shipmentRepository.findById(shipment.getShipmentId()).orElse(null);
        if (existingShipment == null) {
            return ResponseEntity.badRequest().body("Shipment not found");
        }
        existingShipment.setShipmentName(shipment.getShipmentName());
        existingShipment.setShipmentCost(shipment.getShipmentCost());
        return ResponseEntity.ok(shipmentRepository.save(shipment));
    }

    @GetMapping("/cost")
    public ResponseEntity<?> getShipmentCost(@RequestBody String shipmentId) {
        Shipment shipment = shipmentRepository.findById(shipmentId).orElse(null);
        if (shipment == null) {
            return ResponseEntity.badRequest().body("Shipment not found");
        }
        return ResponseEntity.ok(shipment.getShipmentCost());
    }

    @PostMapping("/create")
    public ResponseEntity<?> createShipment(@RequestBody Shipment shipment) {
        shipment.setShipmentId(Util.createId());
        return ResponseEntity.ok(shipmentRepository.save(shipment));
    }
}

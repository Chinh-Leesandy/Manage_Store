package demo.sad.invoiceservice.controller;


import demo.sad.invoiceservice.entity.Payment;
import demo.sad.invoiceservice.repository.PaymentRepository;
import demo.sad.invoiceservice.util.Util;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/payment")
public class PaymentController {

    private final PaymentRepository paymentRepository;

    public PaymentController(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello from Payment Service";
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(paymentRepository.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addPayment(@RequestBody Payment payment) {
        payment.setPaymentId(Util.createIdOrElse(payment.getPaymentId()));
        return ResponseEntity.ok(paymentRepository.save(payment));
    }

    @PutMapping("/update")
    public ResponseEntity<?> updatePayment(@RequestBody Payment payment) {
        Payment existingPayment = paymentRepository.findById(payment.getPaymentId()).orElse(null);
        if (existingPayment == null) {
            return ResponseEntity.badRequest().body("Payment not found");
        }
        existingPayment.setPaymentMethod(payment.getPaymentMethod());
        return ResponseEntity.ok(paymentRepository.save(payment));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deletePayment(@RequestBody Payment payment) {
        paymentRepository.delete(payment);
        return ResponseEntity.ok("Payment deleted: " + payment.toString());
    }

}

package com.grecha.billpayment.controller;

import com.grecha.billpayment.exception.ResourceNotFoundException;
import com.grecha.billpayment.model.Payment;
import com.grecha.billpayment.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class PaymentController {

    @Autowired
    PaymentRepository paymentRepository;

    // Get All
    @GetMapping("/payments")
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }


    // Create
    @PostMapping("/payments")
    public Payment createPayment(@Valid @RequestBody Payment payment) {
        return paymentRepository.save(payment);
    }

    // Get
    @GetMapping("/payments/{id}")
    public Payment getPaymentById(@PathVariable(value = "id") Long paymentId) {
        return paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", paymentId));
    }

    // Update
    @PutMapping("/payments/{id}")
    public Payment updatePayment(@PathVariable(value = "id") Long paymentId,
                           @Valid @RequestBody Payment paymentDetails) {

        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", paymentId));

        payment.setAccount(paymentDetails.getAccount());
        payment.setAmount(paymentDetails.getAmount());
        payment.setBillers(paymentDetails.getBillers());
        payment.setCustomers(paymentDetails.getCustomers());

        Payment updatedPayment = paymentRepository.save(payment);
        return updatedPayment;
    }

    // Delete
    @DeleteMapping("/payments/{id}")
    public ResponseEntity<?> deletePayment(@PathVariable(value = "id") Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ResourceNotFoundException("Biller", "id", paymentId));

        paymentRepository.delete(payment);

        return ResponseEntity.ok().build();
    }
}

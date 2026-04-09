package com.movieticket.controller;

import com.movieticket.dto.PaymentDTO;
import com.movieticket.dto.PaymentRequest;
import com.movieticket.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payments")
@CrossOrigin(origins = "*")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/process")
    public ResponseEntity<PaymentDTO> processPayment(@Valid @RequestBody PaymentRequest paymentRequest) {
        PaymentDTO paymentDTO = paymentService.processPayment(paymentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentDTO);
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<PaymentDTO> getPaymentById(@PathVariable Long paymentId) {
        PaymentDTO paymentDTO = paymentService.getPaymentById(paymentId);
        return ResponseEntity.ok(paymentDTO);
    }

    @GetMapping("/transaction/{transactionId}")
    public ResponseEntity<PaymentDTO> getPaymentByTransactionId(@PathVariable String transactionId) {
        PaymentDTO paymentDTO = paymentService.getPaymentByTransactionId(transactionId);
        return ResponseEntity.ok(paymentDTO);
    }

    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<PaymentDTO> getPaymentByBookingId(@PathVariable Long bookingId) {
        PaymentDTO paymentDTO = paymentService.getPaymentByBookingId(bookingId);
        return ResponseEntity.ok(paymentDTO);
    }

    @PatchMapping("/{paymentId}/confirm")
    public ResponseEntity<PaymentDTO> confirmPayment(@PathVariable Long paymentId) {
        PaymentDTO paymentDTO = paymentService.confirmPayment(paymentId);
        return ResponseEntity.ok(paymentDTO);
    }

    @PatchMapping("/{paymentId}/fail")
    public ResponseEntity<PaymentDTO> failPayment(@PathVariable Long paymentId) {
        PaymentDTO paymentDTO = paymentService.failPayment(paymentId);
        return ResponseEntity.ok(paymentDTO);
    }
}

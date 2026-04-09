package com.movieticket.service;

import com.movieticket.dto.PaymentDTO;
import com.movieticket.dto.PaymentRequest;
import com.movieticket.entity.Booking;
import com.movieticket.entity.Payment;
import com.movieticket.repository.BookingRepository;
import com.movieticket.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository, BookingRepository bookingRepository) {
        this.paymentRepository = paymentRepository;
        this.bookingRepository = bookingRepository;
    }

    public PaymentDTO processPayment(PaymentRequest paymentRequest) {
        Booking booking = bookingRepository.findById(paymentRequest.getBookingId())
            .orElseThrow(() -> new IllegalArgumentException("Booking not found"));

        if (!booking.getStatus().equals(Booking.BookingStatus.PENDING)) {
            throw new IllegalArgumentException("Only pending bookings can be paid");
        }

        Payment payment = new Payment();
        payment.setTransactionId(generateTransactionId());
        payment.setBooking(booking);
        payment.setAmount(booking.getTotalPrice());
        payment.setPaymentMethod(paymentRequest.getPaymentMethod());
        payment.setStatus(Payment.PaymentStatus.PENDING);
        payment.setGateway("PaymentGateway"); // This can be replaced with actual gateway
        payment.setCreatedAt(LocalDateTime.now());
        payment.setUpdatedAt(LocalDateTime.now());

        Payment savedPayment = paymentRepository.save(payment);
        return convertToDTO(savedPayment);
    }

    public PaymentDTO confirmPayment(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
            .orElseThrow(() -> new IllegalArgumentException("Payment not found"));

        if (!payment.getStatus().equals(Payment.PaymentStatus.PENDING)) {
            throw new IllegalArgumentException("Only pending payments can be confirmed");
        }

        payment.setStatus(Payment.PaymentStatus.SUCCESS);
        payment.setPaidAt(LocalDateTime.now());
        payment.setUpdatedAt(LocalDateTime.now());

        // Update booking status
        Booking booking = payment.getBooking();
        booking.setStatus(Booking.BookingStatus.CONFIRMED);
        booking.setUpdatedAt(LocalDateTime.now());
        bookingRepository.save(booking);

        Payment updatedPayment = paymentRepository.save(payment);
        return convertToDTO(updatedPayment);
    }

    public PaymentDTO failPayment(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
            .orElseThrow(() -> new IllegalArgumentException("Payment not found"));

        payment.setStatus(Payment.PaymentStatus.FAILED);
        payment.setUpdatedAt(LocalDateTime.now());

        Payment updatedPayment = paymentRepository.save(payment);
        return convertToDTO(updatedPayment);
    }

    public PaymentDTO getPaymentById(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
            .orElseThrow(() -> new IllegalArgumentException("Payment not found"));
        return convertToDTO(payment);
    }

    public PaymentDTO getPaymentByTransactionId(String transactionId) {
        Payment payment = paymentRepository.findByTransactionId(transactionId)
            .orElseThrow(() -> new IllegalArgumentException("Payment not found"));
        return convertToDTO(payment);
    }

    public PaymentDTO getPaymentByBookingId(Long bookingId) {
        Payment payment = paymentRepository.findByBookingBookingId(bookingId);
        if (payment == null) {
            throw new IllegalArgumentException("Payment not found for booking");
        }
        return convertToDTO(payment);
    }

    private String generateTransactionId() {
        return "TXN" + System.currentTimeMillis() + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    private PaymentDTO convertToDTO(Payment payment) {
        return new PaymentDTO(
            payment.getPaymentId(),
            payment.getTransactionId(),
            payment.getBooking().getBookingId(),
            payment.getAmount(),
            payment.getPaymentMethod(),
            payment.getStatus(),
            payment.getGateway(),
            payment.getPaidAt()
        );
    }
}

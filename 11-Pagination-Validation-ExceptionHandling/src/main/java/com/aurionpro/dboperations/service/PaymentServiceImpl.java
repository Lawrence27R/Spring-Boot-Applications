package com.aurionpro.dboperations.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aurionpro.dboperations.dto.ResponsePageDto;
import com.aurionpro.dboperations.entity.Payment;
import com.aurionpro.dboperations.entity.PaymentMode;
import com.aurionpro.dboperations.exception.PaymentDoesNotExistException;
import com.aurionpro.dboperations.repository.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public void addPayment(Payment payment) {
        paymentRepository.save(payment);
    }

    @Override
    public Page<Payment> findByPaymentMode(PaymentMode paymentMode, Pageable pageable) {
        Page<Payment> payments = paymentRepository.findByPaymentMode(paymentMode, pageable);
        if (payments.isEmpty()) {
            throw new PaymentDoesNotExistException("No payments found with the mode: " + paymentMode);
        }
        return payments;
    }


    @Override
    public Optional<Payment> findPaymentById(int paymentId) {
        Optional<Payment> payment = paymentRepository.findById(paymentId);
        if (!payment.isPresent()) {
            throw new PaymentDoesNotExistException("Payment with ID " + paymentId + " does not exist.");
        }
        return payment;
    }

	@Override
	public ResponsePageDto<Payment> getAllPayments(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Payment> paymentPage = paymentRepository.findAll(pageable);

        ResponsePageDto<Payment> paymentPageDto = new ResponsePageDto<>();
        paymentPageDto.setTotalPages(paymentPage.getTotalPages());
        paymentPageDto.setTotalElements(paymentPage.getTotalElements());
        paymentPageDto.setSize(paymentPage.getSize());
        paymentPageDto.setContents(paymentPage.getContent());
        paymentPageDto.setLastPage(paymentPage.isLast());

        return paymentPageDto;
	}
}

package com.JobFindingPlatform.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Base64;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.JobFindingPlatform.DTO.PaymentRequestDTO;
import com.JobFindingPlatform.DTO.PaymentResponseDTO;
import com.JobFindingPlatform.Entity.Payment;
import com.JobFindingPlatform.Enum.PaymentStatus;
import com.JobFindingPlatform.Repository.PaymentRepository;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepo;

    @Autowired
    private InvoiceService invoiceService;

    public PaymentResponseDTO processPayment(PaymentRequestDTO dto) {
        // Save payment
        Payment pay = new Payment();
        pay.setUserId(dto.getUserId());
        pay.setPlanId(dto.getPlanId());
        pay.setAmount(dto.getAmount());
        pay.setPaymentStatus(PaymentStatus.SUCCESS);
        pay.setTransactionId(UUID.randomUUID().toString());
        pay.setTimeStamp(ZonedDateTime.now(ZoneId.of("Asia/Kolkata")).toLocalDateTime());



        paymentRepo.save(pay);

        // Generate invoice PDF
        byte[] pdfBytes = invoiceService.generateInvoice(pay);

        // Prepare payload as JSON
        Map<String, Object> payload = new HashMap<>();
        payload.put("to", "prajyotkanagale1008@gmail.com"); // Replace with real user email
        payload.put("subject", "Your ZIDIOConnect Invoice");
        payload.put("body", "Dear User, \n\nThank you for your payment. Please find your invoice attached.");
        payload.put("pdfBytes", Base64.getEncoder().encodeToString(pdfBytes));

        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Wrap request
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(payload, headers);

        // Send REST call
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(
                "http://localhost:8081/api/notifications/send-invoice",
                requestEntity,
                String.class
        );

        // Prepare response
        PaymentResponseDTO response = new PaymentResponseDTO();
        response.setTransactionId(pay.getTransactionId());
        response.setPaymentStatus(pay.getPaymentStatus());
        response.setAmount(pay.getAmount());

        return response;
    }
}

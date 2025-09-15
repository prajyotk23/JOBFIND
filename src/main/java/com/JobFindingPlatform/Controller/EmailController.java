package com.JobFindingPlatform.Controller;

import java.util.Base64;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.JobFindingPlatform.DTO.EmailRequestDTO;
import com.JobFindingPlatform.Service.EmailService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class EmailController {

	
	@Autowired
	private EmailService emailService;
	
	@PostMapping("/send")
	public ResponseEntity<String>sendEmail(@RequestBody EmailRequestDTO dto ){
		emailService.sendEmail(dto, null);
		return ResponseEntity.ok("Email sent successfully");
	}
	
	@PostMapping("/send-invoice")
	public String sendInvoice (@RequestBody Map<String, String> payload) {
	String to = payload.get("to");
	String subject = payload.get("subject");
	String body = payload.get("body");
	String pdfBase64 = payload.get("pdfBytes");
	
	// Convert Base64 back to byte[]
	byte[] pdfBytes = Base64.getDecoder().decode(pdfBase64);
	
	EmailRequestDTO request = new EmailRequestDTO(to, subject, body);
	emailService.sendEmail(request, pdfBytes);
	
	return "Invoice Email Sent Successfully";
	}
	
}

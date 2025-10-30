package com.JobFindingPlatform.Service;

import java.util.Properties;

import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import jakarta.mail.util.ByteArrayDataSource;

import org.springframework.stereotype.Service;

import com.JobFindingPlatform.DTO.EmailRequestDTO;

import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
public class EmailService {

//	@Autowired
//	private JavaMailSender mailSender;
//	
//	
//	public void sendEmail(EmailRequestDTO dto) {
//		
//		SimpleMailMessage message = new SimpleMailMessage();
//		message.setTo(dto.getTo());
//		message.setSubject(dto.getSubject());
//		message.setText(dto.getBody());
//		
//		mailSender.send(message);
//		
//	}
	
	
	
	public void sendEmail(EmailRequestDTO dto, byte[]pdfBytes) {
		
		final String fromEmail= "prajyotkanagale1008@gmail.com";
		final String password = "jhds hmcv dwqs gpcz";
		
		
		
		Properties props = new Properties();
		
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		
		
		
		Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				
				return new PasswordAuthentication(fromEmail,password);
			}
			
		});
		
		try {
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromEmail));
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(dto.getTo()) );
			message.setSubject(dto.getSubject());
			message.setText(dto.getBody());
			
			
			MimeBodyPart mesageBodyPart = new MimeBodyPart();
			mesageBodyPart.setText(dto.getBody());
			
			
			MimeBodyPart attachmentPart = new MimeBodyPart();
			DataSource dataSource = new ByteArrayDataSource(pdfBytes, "application/pdf");
			attachmentPart.setDataHandler(new DataHandler(dataSource));
			attachmentPart.setFileName("invoice.pdf");
			
			
			Multipart multiPart = new MimeMultipart();
			multiPart.addBodyPart(mesageBodyPart);
			multiPart.addBodyPart(attachmentPart);
			
			message.setContent(multiPart);
			
			Transport.send(message);
			System.out.println("Email sent to"+dto.getTo());
			System.out.println("invoice sent to"+dto.getTo());
			
			
		} catch (MessagingException e) {
			throw new RuntimeException("Failed to send Email" , e);
			
		}
	}
}

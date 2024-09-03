package kz.tenizcap.emailservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import kz.tenizcap.emailservice.dto.CouponPaymentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender emailMailSender;
    private final SpringTemplateEngine thymeleafTemplateEngine;
    private final ObjectMapper objectMapper;

    @Value("${spring.mail.sender}")
    private String sender;

    public CouponPaymentDto sendCouponNotification(CouponPaymentDto couponPaymentDto)  {
        Map<String, Object> values = convertToMap(couponPaymentDto);
        try {
            sendMessageUsingThymeleafTemplate(couponPaymentDto.getEmail(), "Test", values);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return couponPaymentDto;
    }

    private void sendHtmlMessage(String to, String subject, String htmlBody) throws MessagingException {
        MimeMessage message = emailMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlBody, true);
        emailMailSender.send(message);
    }

    public void sendMessageUsingThymeleafTemplate(
            String to, String subject, Map<String, Object> templateModel)
            throws MessagingException {

        Context thymeleafContext = new Context();
        thymeleafContext.setVariables(templateModel);
        String htmlBody = thymeleafTemplateEngine.process("email_template.html", thymeleafContext);

        sendHtmlMessage(to, subject, htmlBody);
    }

    private Map<String, Object> convertToMap (CouponPaymentDto couponPaymentDto)  {
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("name", couponPaymentDto.getName());
        objectMap.put("account", couponPaymentDto.getAccount());
        objectMap.put("amount", couponPaymentDto.getAmount());
        objectMap.put("currency", couponPaymentDto.getCurrency());
        return objectMap;
    }

    public void sendSimpleMessage(String message) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom(sender);
        email.setTo("bistibekov.a@gmail.com");
        email.setSubject("Test");
        email.setText(message);
        emailMailSender.send(email);
    }
}

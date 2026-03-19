package com.example.hosty_bot;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final SendGrid sendGrid;

    @Value("${sendgrid.from.email}")
    private String fromEmail;

    public EmailService(SendGrid sendGrid) {
        this.sendGrid = sendGrid;
    }

    public void sendEmail(String to, String subject, String contentText) {
        try {
            Email from = new Email(fromEmail);
            Email recipient = new Email(to);
            Content content = new Content("text/plain", contentText);
            Mail mail = new Mail(from, subject, recipient, content);

            Request request = new Request();
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            Response response = sendGrid.api(request);

            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());

        } catch (Exception e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }
}

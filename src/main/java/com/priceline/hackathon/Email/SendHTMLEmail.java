package com.priceline.hackathon.Email;

import com.priceline.hackathon.Email.dto.Email;
import com.priceline.hackathon.Email.dto.User;
import com.priceline.hackathon.service.TweetService;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

@Service
public class SendHTMLEmail {



    // Sender's email ID needs to be mentioned
    private static final String FROM = "shareitforadeal@gmail.com";
    private static final String USERNAME = "shareitforadeal";//change accordingly
    private static final String PASSWORD = "test$1234";//change accordingly
    private static final String ENCODING = "text/html; charset=UTF-8";

    private static final Properties prop = new Properties();

    static {
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
    }


    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    JavaMailSender mailSender;

    public void sendEmail(Email email) {

        // Get the Session object.
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(USERNAME, PASSWORD);
                    }
                });

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(FROM));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email.getUser().getEmailAddress()));

            // Set Subject: header field
            message.setSubject(email.getSubject());


            String htmlBody = getHtmlBody(email);

            // Send the actual HTML message, as big as you like
            message.setContent(
                    htmlBody,
                    ENCODING);

            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    public String getHtmlBody(Email email) throws Exception {


        Context context = new Context();
        context.setVariable("email", email);

        return templateEngine.process(email.getTemplete(), context);

    }
}
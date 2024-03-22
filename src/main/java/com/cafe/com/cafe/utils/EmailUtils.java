package com.cafe.com.cafe.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailUtils {

    @Autowired
    private JavaMailSender emailSender;

    public void sendSimpleMessage(String to, String subject, String text, List<String> list) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("codewithbhautik01@gmail.com");
        helper.setTo(to);
        helper.setSubject(subject);

        if (list != null && list.size() > 0) {
            helper.setCc(getCcArray(list));
        }

        helper.setText(text, true); // Set HTML content

        emailSender.send(message);
    }

    private String[] getCcArray(List<String> ccList) {
        String[] cc = new String[ccList.size()];

        for (int i = 0; i < ccList.size(); i++) {
            cc[i] = ccList.get(i);
        }
        return cc;
    }

    public void forgotMail(String to, String subject, String password) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage(); // mime messages
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("codewithbhautik01@gmail.com");
        helper.setTo(to);
        helper.setSubject(subject);
        String htmlMsg = "<h1>Account Registration Confirmation</h1>" +
                "<p>Dear User,</p>" +
                "<p>We're writing to confirm that you've recently request for your registration password related your account. Here's your account information:</p>"
                +
                "<ul> <li>Email: " + to + "</li> <li>Password:" + password + "</li> </ul>" +
                "<p>Please keep this information confidential and do not share it with anyone. If you need to change your password in the future, you can do so by logging into your account.</p>"
                +
                "<p>If you have any questions or concerns, please don't hesitate to contact us.</p>" +
                "<p>Best regards,</p><p>Bhautik Vaghani<br>Namaste Cafe</p>"
                + "<br><a href=\"http://localhost:4200/\" style=\"display: inline-block; padding: 10px 20px; font-size: 16px; text-align: center; text-decoration: none; background-color: #4CAF50; color: #fff; border-radius: 5px; border: 2px solid #4CAF50; transition: background-color 0.3s;\">\r\n" + //
                                        "    Click here to login\r\n" + //
                                        "</a></p>";

        message.setContent(htmlMsg, "text/html");
        emailSender.send(message);
    }
    public void emailOTP(String to, String subject, int password) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage(); // mime messages
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("codewithbhautik01@gmail.com");
        helper.setTo(to);
        helper.setSubject(subject);
        String htmlMsg = "<h1>Account Registration Confirmation</h1>" +
                "<p>Dear User,</p>" +
                "<p>We're writing to confirm that you've recently request for your registration password related your account. Here's your account information:</p>"
                +
                "<ul> <li>Email: " + to + "</li> <li>Password:" + password + "</li> </ul>" +
                "<p>Please keep this information confidential and do not share it with anyone. If you need to change your password in the future, you can do so by logging into your account.</p>"
                +
                "<p>If you have any questions or concerns, please don't hesitate to contact us.</p>" +
                "<p>Best regards,</p><p>Bhautik Vaghani<br>Namaste Cafe</p>"
                + "<br><a href=\"http://localhost:4200/\" style=\"display: inline-block; padding: 10px 20px; font-size: 16px; text-align: center; text-decoration: none; background-color: #4CAF50; color: #fff; border-radius: 5px; border: 2px solid #4CAF50; transition: background-color 0.3s;\">\r\n" + //
                                        "    Click here to login\r\n" + //
                                        "</a></p>";

        message.setContent(htmlMsg, "text/html");
        emailSender.send(message);
    }
    
}

package com.cafe.com.cafe.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
// this class creatred in video-4
@Service
public class EmailUtils {

    @Autowired
    private JavaMailSender emailSender;

    // sends email to admins when a user's status is updated video-4
    public void sendSimpleMessage(String to, String subject, String text, List<String> list) {
        SimpleMailMessage message = new SimpleMailMessage(); // simple mail message
        message.setFrom("codewithbhautik01@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        // sends to all admins, store admins in a list
        if (list != null && list.size() > 0) {
            message.setCc(getCcArray(list));
        }
        emailSender.send(message);
    }
    // v-4
    private String[] getCcArray(List<String> ccList) {
        String[] cc = new String[ccList.size()];

        for (int i = 0; i < ccList.size(); i++) {
            cc[i] = ccList.get(i);
        }
        return cc;
    }
}

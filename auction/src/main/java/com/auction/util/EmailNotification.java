package com.auction.util;

import com.auction.entity.Auction;
import com.auction.entity.User;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by Stepan
 * on 12-Dec-17
 */

public class EmailNotification {

    public void sendEmail(Auction auction){
        final String username = "forproject917@gmail.com";
        final String password = "qwertyuiop8";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session msession = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(msession);
            message.setFrom(new InternetAddress("forproject917@gmail.com"));
            Iterator<User> iterator = auction.getParticipants().iterator();
            User user;
            while (iterator.hasNext()) {
                user = iterator.next();
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(user.getEmail()));
                message.setSubject("Auction");
                message.setText("Dear " + user.getFirstName() + ","
                        + " auction " + auction.getName() + " is closed, maybe you're the winner?!"
                        + "\n\n No spam to this email, please!");

                Transport.send(message);
            }
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}

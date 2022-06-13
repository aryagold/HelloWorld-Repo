package za.co.vzap.Communication.Email;

import java.util.Properties;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import za.co.vzap.Communication.Model.CommunicationDto;

public class Email extends Thread {
    private CommunicationDto dto;

    private Properties properties;
    private Session session;
    private MimeMessage message;

    public Email(CommunicationDto dto) {
        this.dto = dto;
    }

    public void sendSalesReceipt() {
        String from = "vzapemail@gmail.com";

        String host = "smtp.gmail.com";

        properties = System.getProperties();

        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.trust", "*");
        properties.put("mail.smtp.socketfactory.port", "465");
        properties.put("mail.smtp.socketfactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.socketfactory.fallback", "false");

        session = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("vzapemail@gmail.com", "Java@vzap");

            }

        });

        session.setDebug(true);

        try {
            message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(dto.emailAddressTo));
            message.setSubject("Carol's Boutique Receipt");
            Multipart multipart = new MimeMultipart();

            BodyPart body = new MimeBodyPart();
            BodyPart body2 = new MimeBodyPart();
            String text = "this is the database zip file, please test it out and see if it works fine, let me know if i have to resend or something";
            String htmlText = "<a href='this is where our review website goes'>Please leave a review to let us know about your experience with our store</a>";
            body.setContent(text, "text/html");
            body2.setContent(htmlText, "text/html");
            multipart.addBodyPart(body);
            multipart.addBodyPart(body2);
            MimeBodyPart messageBodyPartAttach = new MimeBodyPart();


            messageBodyPartAttach.setContentID("<file>");

            multipart.addBodyPart(messageBodyPartAttach);

            message.setContent(multipart);

            message.setContent(multipart);

            System.out.println("sending...");

            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
        }

    }

    public void sendRefundReceipt() {
        String from = "vzapemail@gmail.com";

        String host = "smtp.gmail.com";

        properties = System.getProperties();

        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.trust", "*");
        properties.put("mail.smtp.socketfactory.port", "465");
        properties.put("mail.smtp.socketfactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.socketfactory.fallback", "false");

        session = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("vzapemail@gmail.com", "Java@vzap");

            }

        });

        session.setDebug(true);

        try {
            message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(dto.emailAddressTo));
            message.setSubject("Carol's Boutique Receipt");
            Multipart multipart = new MimeMultipart();

            BodyPart body = new MimeBodyPart();
            BodyPart body2 = new MimeBodyPart();
            String text = "this is the database zip file, please test it out and see if it works fine, let me know if i have to resend or something";
            String htmlText = "<a href='this is where our review website goes'>Please leave a review to let us know about your experience with our store</a>";
            body.setContent(text, "text/html");
            body2.setContent(htmlText, "text/html");
            multipart.addBodyPart(body);
            multipart.addBodyPart(body2);
            MimeBodyPart messageBodyPartAttach = new MimeBodyPart();

 
            messageBodyPartAttach.setContentID("<file>");

            multipart.addBodyPart(messageBodyPartAttach);

            message.setContent(multipart);

            message.setContent(multipart);

            System.out.println("sending...");

            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
        }

    }
    
    public void sendDepletedStock() {
    
    }
    
    public void sendReserve() {
    
    }
    
    public void sendSubscription() {
    
    }

    public void sendEmail() {
        switch (dto.type) {
            case 1:
                sendSalesReceipt();
                break;
            case 2:
                sendRefundReceipt();
                break;
            case 3:
                sendDepletedStock();
                break;
            case 4:
                sendReserve();
                break;
            case 5:
                sendSubscription();
        }
    }

    @Override
    public void run() {
        sendEmail();
    }
}

package za.co.vzap.Communication.Email;

import java.io.File;
import java.io.IOException;
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
import za.co.vzap.Branch.Model.Branch;

public class Email extends Thread {
    private int protocol;
    private String to, saleid;
    private File attatchment;
    private Branch branchFrom, branchTo;

    private Properties properties;
    private Session session;
    private MimeMessage message;

    public Email(int protocol, String to, String saleid) {
        this.protocol = protocol;
        this.to = to;
        this.saleid = saleid;
    }

    public Email(int protocol, String to, String saleid, Branch branchFrom, Branch branchTo) {
        this.protocol = protocol;
        this.to = to;
        this.saleid = saleid;
        this.branchTo = branchTo;
        this.branchFrom = branchFrom;
    }

    public Email(int protocol, String to, String saleid, File attatchment) {
        this.protocol = protocol;
        this.to = to;
        this.saleid = saleid;
        this.attatchment = attatchment;
    }

    public void sendAttatchEmail() {

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
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Carol's Boutique Database");
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

            try {
                messageBodyPartAttach.attachFile(attatchment);
            } catch (IOException e) {
            }

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

    public void sendReciept() {

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
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Carol's Boutique Database");
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

            try {
                messageBodyPartAttach.attachFile(attatchment);
            } catch (IOException e) {
            }

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

    public void sendRefundReciept() {
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
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Carol's Boutique Reciept");
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

            try {
                messageBodyPartAttach.attachFile(attatchment);
            } catch (IOException e) {
            }

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

    public void sendIBT() {

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
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Carol's Boutique IBT request");
            Multipart multipart = new MimeMultipart();

            BodyPart body = new MimeBodyPart();
            String text = branchFrom.getName() + " branch is requesting an IBT from you, " + branchTo.getName() + " branch";
            body.setContent(text, "text/html");
            multipart.addBodyPart(body);
            MimeBodyPart messageBodyPartAttach = new MimeBodyPart();

            try {
                messageBodyPartAttach.attachFile(attatchment);
            } catch (IOException e) {
            }

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

    public void sendEmail() {
        switch (protocol) {
            case 1:
                sendAttatchEmail();
                break;
            case 2:
                sendReciept();
                break;
            case 3:
                sendRefundReciept();
                break;
            case 4:
                sendIBT();
                break;
        }
    }

    @Override
    public void run() {
        sendEmail();
    }
}

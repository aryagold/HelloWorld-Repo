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
import za.co.vzap.POS.Model.RefundDto;
import za.co.vzap.POS.Model.RefundItemDto;
import za.co.vzap.POS.Model.SaleDto;
import za.co.vzap.POS.Model.SaleLineItemDto;

public class Email extends Thread {
    private CommunicationDto dto;

    private Properties properties;
    private Session session;
    private MimeMessage message;
    
    private String mailFrom = "vzapemail@gmail.com";

    public Email(CommunicationDto dto) {

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
        
        this.dto = dto;
    }

    public void sendSalesReceipt() {
        try {
            SaleDto sale = (SaleDto) dto.data;
            
            message = new MimeMessage(session);
            Multipart multipart = new MimeMultipart();
            
            message.setFrom(new InternetAddress(this.mailFrom));
            
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(dto.emailAddressTo));
            message.setSubject("Carol's Boutique Receipt - " + sale.saleId);
            
            BodyPart body = new MimeBodyPart();
            
            String htmlBody = "<p>";
            htmlBody += "<div>Sale ID; " + sale.saleId + "</div>";
            htmlBody += "<div>Date; " + sale.date + "</div>";
            htmlBody += "<table>";
            
            double total = 0;
            
            for(SaleLineItemDto item : sale.lineitems) {
                htmlBody += "<tr><td>" + item.productName + "</td><td>" + item.sizeName + "</td><td>" + item.price + "</td></tr>";
                
                total += item.price;
            }
            
            htmlBody += "</table>";
            htmlBody += "<div>Total; " + total + "</div>";
            
            htmlBody += "</p>";
           
//            String text = "this is the database zip file, please test it out and see if it works fine, let me know if i have to resend or something";
//            String htmlText = "<a href='this is where our review website goes'>Please leave a review to let us know about your experience with our store</a>";
            body.setContent(htmlBody, "text/html");
            
            multipart.addBodyPart(body);
          
//            MimeBodyPart messageBodyPartAttach = new MimeBodyPart();
//
//
//            messageBodyPartAttach.setContentID("<file>");
//
//            multipart.addBodyPart(messageBodyPartAttach);

            message.setContent(multipart);

//            message.setContent(multipart);

            System.out.println("sending...");

            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            
        }

    }

    public void sendRefundReceipt() {

        try {
            RefundDto refund = (RefundDto) dto.data;
            
            message = new MimeMessage(session);
            Multipart multipart = new MimeMultipart();
            
            message.setFrom(new InternetAddress(this.mailFrom));
            
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(dto.emailAddressTo));
            message.setSubject("Carol's Boutique Refund Receipt - Refund " + refund.Id);
            
            BodyPart body = new MimeBodyPart();

            String htmlBody = "<p>";
            htmlBody += "<div>Refund ID; " + refund.Id + "</div>";
            htmlBody += "<div>Date; " + refund.date + "</div>";
            htmlBody += "<table>";

            double total = 0;

            for (RefundItemDto item : refund.refundItems) {
                htmlBody += "<tr><td>" + item.productName + "</td><td>" + item.sizeName + "</td><td>" + item.price + "</td></tr>";

                total += item.price;
            }

            htmlBody += "</table>";
            htmlBody += "<div>Refund Total; " + total + "</div>";

            htmlBody += "</p>";

//            String text = "this is the database zip file, please test it out and see if it works fine, let me know if i have to resend or something";
//            String htmlText = "<a href='this is where our review website goes'>Please leave a review to let us know about your experience with our store</a>";
            body.setContent(htmlBody, "text/html");

            multipart.addBodyPart(body);

//            MimeBodyPart messageBodyPartAttach = new MimeBodyPart();
//
//
//            messageBodyPartAttach.setContentID("<file>");
//
//            multipart.addBodyPart(messageBodyPartAttach);
            message.setContent(multipart);

//            message.setContent(multipart);
            System.out.println("sending...");

            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {

        }

    }
    
    public void sendDepletedStock() {
    
    }
    
    public void sendReserve() {
        try {
            SaleDto sale = (SaleDto) dto.data;

            message = new MimeMessage(session);
            Multipart multipart = new MimeMultipart();

            message.setFrom(new InternetAddress(this.mailFrom));

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(dto.emailAddressTo));
            message.setSubject("Carol's Boutique Reserve Notification for Sale - " + sale.saleId);

            BodyPart body = new MimeBodyPart();

            String htmlBody = "<p>";
            htmlBody += "<div>Sale ID; " + sale.saleId + "</div>";
            htmlBody += "<div>Date; " + sale.date + "</div>";
            htmlBody += "<table>";

            double total = 0;

            for (SaleLineItemDto item : sale.lineitems) {
                htmlBody += "<tr><td>" + item.productName + "</td><td>" + item.sizeName + "</td><td>" + item.price + "</td></tr>";

                total += item.price;
            }

            htmlBody += "</table>";
            htmlBody += "<div>Total; " + total + "</div>";

            htmlBody += "</p>";

//            String text = "this is the database zip file, please test it out and see if it works fine, let me know if i have to resend or something";
//            String htmlText = "<a href='this is where our review website goes'>Please leave a review to let us know about your experience with our store</a>";
            body.setContent(htmlBody, "text/html");

            multipart.addBodyPart(body);

//            MimeBodyPart messageBodyPartAttach = new MimeBodyPart();
//
//
//            messageBodyPartAttach.setContentID("<file>");
//
//            multipart.addBodyPart(messageBodyPartAttach);
            message.setContent(multipart);

//            message.setContent(multipart);
            System.out.println("sending...");

            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {

        }
    }
    
    public void sendSubscription() {
        try {

            message = new MimeMessage(session);
            Multipart multipart = new MimeMultipart();

            message.setFrom(new InternetAddress(this.mailFrom));

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(dto.emailAddressTo));
            message.setSubject("Carol's Boutique Subscription");

            BodyPart body = new MimeBodyPart();

            String htmlBody = "<p>Hi, " + dto.emailAddressTo.substring(0, dto.emailAddressTo.length() - 10) + ". Thank you for subscribing to Carol's Boutique newsletter";

//            String text = "this is the database zip file, please test it out and see if it works fine, let me know if i have to resend or something";
//            String htmlText = "<a href='this is where our review website goes'>Please leave a review to let us know about your experience with our store</a>";
            body.setContent(htmlBody, "text/html");

            multipart.addBodyPart(body);

//            MimeBodyPart messageBodyPartAttach = new MimeBodyPart();
//
//
//            messageBodyPartAttach.setContentID("<file>");
//
//            multipart.addBodyPart(messageBodyPartAttach);
            message.setContent(multipart);

//            message.setContent(multipart);
            System.out.println("sending...");

            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {

        }
    }

    public void sendEmail() { 
        switch (dto.emailType.getValue()) {
            case 0:
                sendSalesReceipt();
                break;
            case 1:
                sendRefundReceipt();
                break;
            case 2:
                sendDepletedStock();
                break;
            case 3:
                sendReserve();
                break;
            case 4:
                sendSubscription();
        }
    }

    @Override
    public void run() {
        sendEmail();
    }
}

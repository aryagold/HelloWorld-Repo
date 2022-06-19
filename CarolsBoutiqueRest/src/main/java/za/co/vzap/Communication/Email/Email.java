package za.co.vzap.Communication.Email;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import za.co.vzap.Inventory.Model.InventoryDto;
import za.co.vzap.POS.Model.RefundDto;
import za.co.vzap.POS.Model.RefundItemDto;
import za.co.vzap.POS.Model.SaleDto;
import za.co.vzap.POS.Model.SaleLineItemDto;

public class Email extends Thread {
    private CommunicationDto communicationDto;

    private Properties properties;
    private Session session;
    
    private String mailFrom = "vzapemail@gmail.com";
    private String smtpUser = "aryagoldridge@gmail.com";
    private String smtpPassword = "";

    public Email(CommunicationDto communicationDto) {

        String host = "smtp.gmail.com";

        properties = System.getProperties();

        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        session = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(smtpUser, smtpPassword);
            }

        });

        session.setDebug(true);
        
        this.communicationDto = communicationDto;
    }
    
    @Override
    public void run() {
        sendEmail();
    }
    
    public void sendEmail() {
        switch (communicationDto.emailType.getValue()) {
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
                sendReserved();
                break;
            case 4:
                sendSubscribed();
        }
    }

    public void sendSalesReceipt() {
            SaleDto sale = (SaleDto) communicationDto.data;
            
            String subject = "Carol's Boutique Receipt - " + sale.saleId;
            
            String htmlBody = "<div>";
            htmlBody += "<div>Sale ID; " + sale.saleId + "</div>";
            htmlBody += "<div>Date; " + sale.date + "</div>";
            htmlBody += "<table>";
            
            for(SaleLineItemDto item : sale.lineitems) {
                htmlBody += "<tr><td>" + item.productName + "</td><td>" + item.sizeName + "</td><td>" + item.price + "</td></tr>";
            }
            
            htmlBody += "</table>";
            htmlBody += "<div>Total; " + sale.getTotal() + "</div>";
            
            htmlBody += "<a href='review.jsp'>Please leave a review to let us know about your experience with our store</a>";
            htmlBody += "<div style=\"font-size=0.5em; font-weight:bold; margin-top:10px;\">All returns are to be performed within 10 calendar days from purchase</div>";
            htmlBody += "</div>";
        
            doSend(communicationDto.emailAddressTo, subject, htmlBody);
    }

    public void sendRefundReceipt() {
            RefundDto refund = (RefundDto) communicationDto.data;
            
            String subject = "Carol's Boutique Refund Receipt - Refund " + refund.Id;

            String htmlBody = "<div>";
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

            htmlBody += "</div>";
            
            doSend(communicationDto.emailAddressTo, subject, htmlBody);
    }
    
    public void sendDepletedStock() {
        InventoryDto inventory = (InventoryDto) communicationDto.data;
        
        String subject = "Low Stock on " + inventory.productName;
        
        String htmlBody = "<div>";
        
        htmlBody += "<div>Stock for " + inventory.productName + " Size " + inventory.sizeName + " is low</div>";
        htmlBody += "<div>Quantity on hand; " + inventory.quantity + "</div>";
        
        htmlBody += "</div>";
        
        doSend(communicationDto.emailAddressTo, subject, htmlBody);
    }
    
    public void sendReserved() {
            SaleDto sale = (SaleDto) communicationDto.data;

            String subject = "Carol's Boutique Reserve Notification for Sale - " + sale.saleId;

            String htmlBody = "<div>";
            htmlBody += "<div>Collection due in 24 hours</div>";
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

            htmlBody += "</div>";

            doSend(communicationDto.emailAddressTo, subject, htmlBody);
            
    }
    
    public void sendSubscribed() {
            String subject = "Carol's Boutique Subscription";

            String htmlBody = "<div>Hi. Thank you for subscribing to Carol's Boutique newsletter</div>";
            
            doSend(communicationDto.emailAddressTo, subject, htmlBody);
    }
    
    private void doSend(String to, String subject, String html) {
        try {
            MimeMessage mimeMessage = new MimeMessage(session);
           
            mimeMessage.setFrom(new InternetAddress(this.mailFrom));
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            
            mimeMessage.setSubject(subject);
         
            mimeMessage.setContent(html, "text/html");
            
            System.out.println("sending...");
            
            Transport.send(mimeMessage);
            
            System.out.println("Sent message successfully....");
        } catch (MessagingException ex) {
            Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

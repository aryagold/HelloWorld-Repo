package za.co.vzap.Communication.SMS;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class SMSController {
    public String userid, password;
    public String fileName;

    public SMSController() {
        this.userid = "GROUP2";
        this.password = "2group";
        this.fileName = "/Users/aryagoldridge/NetBeansProjects/HelloWorld-Repo/CarolsBoutiqueRest/SMSRequest.xml";
    }

    public void writeToXML(String message, String phoneNumber, String date) throws IOException, XMLStreamException {
        File file = new File(fileName);
        System.out.println("File existence " + file.exists());
        XMLOutputFactory xof = XMLOutputFactory.newInstance();
        XMLStreamWriter xsw = xof.createXMLStreamWriter(new FileWriter(file));
        xsw.writeStartDocument();
        xsw.writeStartElement("smsreq");
        xsw.writeStartElement("datetime");
        xsw.writeCharacters(date);
        System.out.println("Written date " + date);
        xsw.writeEndElement();
        xsw.writeStartElement("user");
        xsw.writeCharacters(userid);
        System.out.println("Written id " + userid);
        xsw.writeEndElement();
        xsw.writeStartElement("pass");
        xsw.writeCharacters(password);
        System.out.println("Written password " + password);
        xsw.writeEndElement();
        xsw.writeStartElement("msisdn");
        xsw.writeCharacters(phoneNumber);
        System.out.println("Written number " + phoneNumber);
        xsw.writeEndElement();
        xsw.writeStartElement("message");
        xsw.writeCharacters(message);
        System.out.println("Written message " + message);
        xsw.writeEndElement();
        xsw.writeEndElement();
        xsw.writeEndDocument();
        System.out.println("Written to XML");
        xsw.flush();
        xsw.close();
    }
    
    
    public void sendSms() {
        String urlString = "http://196.41.180.157:8080/sms/sms_request";
        System.out.println("Connecting to " + urlString);

        try {
            FileInputStream fis = new FileInputStream(fileName);
            byte[] fileData = new byte[fis.available()];
            fis.read(fileData);
            fis.close();
            System.out.println("Read in file");

            URL url = new URL(urlString);
            HttpURLConnection m_connection = (HttpURLConnection) url.openConnection();
            m_connection.setRequestMethod("POST");
            m_connection.setDoOutput(true);

            System.out.println("Made Connection");

            OutputStream os = m_connection.getOutputStream();
            os.write(fileData);
            os.flush();
            os.close();

            System.out.println("Sent data, waiting for response….");

            int responseCode = m_connection.getResponseCode();
            String responseMsg = m_connection.getResponseMessage();

            System.out.println("Server returned – " + responseCode + ":" + responseMsg);

        } catch (Exception ex) {
            System.out.println("Failed to make connection to ‘" + urlString + "‘:" + ex);
        }
    }
}

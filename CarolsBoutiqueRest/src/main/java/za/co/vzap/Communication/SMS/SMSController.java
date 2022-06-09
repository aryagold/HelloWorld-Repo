package za.co.vzap.Communication.SMS;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class SMSController {
    private String userid, password, message, phoneNumber;
    private LocalDateTime date;

    public SMSController(String userid, String password, String message, String phoneNumber, LocalDateTime date) {
        this.userid = userid;
        this.password = password;
        this.message = message;
        this.phoneNumber = phoneNumber;
        this.date = date;
    }

    public File writeToXML() throws IOException, XMLStreamException {
        File file = new File("SMSRequest.xml");
        XMLOutputFactory xof = XMLOutputFactory.newInstance();
        XMLStreamWriter xsw = xof.createXMLStreamWriter(new FileWriter(file));
        xsw.writeStartDocument();
        xsw.writeStartElement("smsreq");
        xsw.writeStartElement("datetime");
        xsw.writeCharacters(date.toString());
        xsw.writeEndElement();
        xsw.writeStartElement("user");
        xsw.writeCharacters(userid);
        xsw.writeEndElement();
        xsw.writeStartElement("pass");
        xsw.writeCharacters(password);
        xsw.writeEndElement();
        xsw.writeStartElement("msisdn");
        xsw.writeCharacters(phoneNumber);
        xsw.writeEndElement();
        xsw.writeStartElement("message");
        xsw.writeCharacters(message);
        xsw.writeEndElement();
        xsw.writeEndElement();
        xsw.writeEndDocument();
        xsw.flush();
        xsw.close();
        return file;
    }
}

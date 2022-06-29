package za.co.vzap.Communication.SMS;

import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SMSController {
    public String userid, password;
//    public String fileName;

    public SMSController() {
        this.userid = "GROUP2";
        this.password = "2group";
//        this.fileName = "/Users/aryagoldridge/NetBeansProjects/HelloWorld-Repo/CarolsBoutiqueRest/SMSRequest.xml";
    }

//    public void writeToXML(String message, String phoneNumber, String date) throws IOException, XMLStreamException {
//        File file = new File(fileName);
//        System.out.println("File existence " + file.exists());
//        XMLOutputFactory xof = XMLOutputFactory.newInstance();
//        XMLStreamWriter xsw = xof.createXMLStreamWriter(new FileWriter(file));
//        xsw.writeStartDocument();
//        xsw.writeStartElement("smsreq");
//        xsw.writeStartElement("datetime");
//        xsw.writeCharacters(date);
//        System.out.println("Written date " + date);
//        xsw.writeEndElement();
//        xsw.writeStartElement("user");
//        xsw.writeCharacters(userid);
//        System.out.println("Written id " + userid);
//        xsw.writeEndElement();
//        xsw.writeStartElement("pass");
//        xsw.writeCharacters(password);
//        System.out.println("Written password " + password);
//        xsw.writeEndElement();
//        xsw.writeStartElement("msisdn");
//        xsw.writeCharacters(phoneNumber);
//        System.out.println("Written number " + phoneNumber);
//        xsw.writeEndElement();
//        xsw.writeStartElement("message");
//        xsw.writeCharacters(message);
//        System.out.println("Written message " + message);
//        xsw.writeEndElement();
//        xsw.writeEndElement();
//        xsw.writeEndDocument();
//        System.out.println("Written to XML");
//        xsw.flush();
//        xsw.close();
//    }
    
//    public void format() throws ParserConfigurationException, IOException, TransformerException, SAXException, org.xml.sax.SAXException {
//        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//        DocumentBuilder builder = factory.newDocumentBuilder();
//        Document document;
//        document = builder.parse(new InputSource(new InputStreamReader(new FileInputStream(fileName))));
//        Transformer transformer = TransformerFactory.newInstance().newTransformer();
//        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
//        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
//        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//        Source source = new DOMSource((Node) document);
//        Result result = new StreamResult(new File(fileName));
//        transformer.transform(source, result);
//    }
    
    
    public void sendSms(String message, String phoneNumber, String date) {
        String urlString = "http://196.41.180.157:8080/sms/sms_request";
        System.out.println("Connecting to " + urlString);

        try {
//            FileInputStream fis = new FileInputStream(fileName);
//            byte[] fileData = new byte[fis.available()];
//            fis.read(fileData);
//            fis.close();
//            System.out.println("Read in file");
            
            String xmlString = "<smsreq>\n"
                    + "    <datetime>" + date + "</datetime>\n"
                    + "    <user>" + userid + "</user>\n"
                    + "    <pass>" + password + "</pass>\n"
                    + "    <msisdn>" + phoneNumber + "</msisdn>\n"
                    + "    <message>" + message + "</message>\n"
                    + "</smsreq>";

            URL url = new URL(urlString);
            HttpURLConnection m_connection = (HttpURLConnection) url.openConnection();
            m_connection.setRequestMethod("POST");
            m_connection.setDoOutput(true);

            System.out.println("Made Connection");

            OutputStream os = m_connection.getOutputStream();
            os.write(xmlString.getBytes());
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.co.vzap.Communication.Email;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import za.co.vzap.Interface.Repository.DataBasePropertyReader;

/**
 *
 * @author macpe
 */
public class EmailPropertyReader {
    
    private File file;
    private String mailFrom;
    private String smtpUser;
    private String smtpPassword;
    
    public EmailPropertyReader() throws FileNotFoundException{
        
        this.file = new File("C:\\Users\\macpe\\Desktop\\repo\\HelloWorld-repo\\HelloWorld-Repo\\CarolsBoutiqueRest\\PropertiesFileEmail.txt");
        FileReader fr = new FileReader(file);
        System.out.println("starting");
        readFromFile(fr);
        
    }
    
    private void readFromFile(FileReader fr){
        
        try {
            System.out.println("in try");
            BufferedReader br = new BufferedReader(fr);
            String line = " ";

            while (line != null) {

                line = br.readLine();
                System.out.println("line = "+line);
                if (line == null) {
                    continue;
                }

                int posOfOpen = posOfOpen(line);
                int posOfClose = posOfClose(line);

                String key = line.substring(0, posOfOpen);
                System.out.println("key "+key);
                switch (key) {

                    case "MailFrom":

                        mailFrom = line.substring(posOfOpen + 1, posOfClose);
                        System.out.println("mail from : "+mailFrom);
                        break;

                    case "smtpUser":

                        smtpUser = line.substring(posOfOpen + 1, posOfClose);
                        System.out.println("smtpUser : " + smtpUser);
                        break;

                    case "smtpPassword":

                        smtpPassword = line.substring(posOfOpen + 1, posOfClose);
                        System.out.println("smtpPassword : " + smtpPassword);
                        break;

                }

            }

        } catch (IOException ex) {
            Logger.getLogger(DataBasePropertyReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private int posOfOpen(String line) {

        if (line != null) {
            int pos = 0;

            for (int i = 0; i < line.length(); i++) {

                if (line.charAt(i) == '<') {
                    pos = i;
                }

            }
            
            return pos;
        }
        return 0;
    }

    private int posOfClose(String line) {

        if (line != null) {
            int pos = 0;

            for (int i = 0; i < line.length(); i++) {

                if (line.charAt(i) == '>') {
                    pos = i;
                }

            }

            return pos;
        }

        return 0;

    }

    public String getMailFrom() {
        return mailFrom;
    }

    public String getSmtpUser() {
        return smtpUser;
    }

    public String getSmtpPassword() {
        return smtpPassword;
    }
    
    
    
}

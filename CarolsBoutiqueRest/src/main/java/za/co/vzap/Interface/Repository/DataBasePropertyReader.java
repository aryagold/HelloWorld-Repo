/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.co.vzap.Interface.Repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Matthew
 */
public class DataBasePropertyReader {

    private FileReader fr;
    private File file;
    private String driver;
    private String dataBase;
    private String userName;
    private String password;

    public DataBasePropertyReader() throws FileNotFoundException {

        this.file = new File("/Users/aryagoldridge/NetBeansProjects/HelloWorld-Repo/CarolsBoutiqueRest/PropertiesFileSQL.txt");
        this.fr = new FileReader(file);

        readFromFile(fr);

    }

    private void readFromFile(FileReader fr) {

        try {

            BufferedReader br = new BufferedReader(fr);
            String line = " ";

            while (line != null) {

                line = br.readLine();

                if (line == null) {
                    continue;
                }

                int posOfOpen = posOfOpen(line);
                int posOfClose = posOfClose(line);

                String key = line.substring(0, posOfOpen);

                switch (key) {

                    case "Driver":

                        driver = line.substring(posOfOpen + 1, posOfClose);

                        break;

                    case "DataBase":

                        dataBase = line.substring(posOfOpen + 1, posOfClose);

                        break;

                    case "UserName":

                        userName = line.substring(posOfOpen + 1, posOfClose);

                        break;

                    case "Password":

                        password = line.substring(posOfOpen + 1, posOfClose);

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

    public String getDriver() {
        return driver;
    }

    public String getDataBase() {
        return dataBase;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

}

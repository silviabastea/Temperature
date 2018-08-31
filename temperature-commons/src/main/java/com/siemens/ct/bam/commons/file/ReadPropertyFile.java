package com.siemens.ct.bam.commons.file;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadPropertyFile {


    public static final String CONFIG_PROPERTIES_FILE_NAME = "config.properties";

    public static String getProperty(String propertyKey) {
        Properties properties = new Properties();
        InputStream input = null;
        String property = null;

        try {
            String filename = CONFIG_PROPERTIES_FILE_NAME;
            input = new FileInputStream(filename);
            properties.load(input);
            property = properties.getProperty(propertyKey);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return property;

    }

    public static String getHost() {
        String host = null;
        host = getProperty("host");

        return host;
    }

    public static String getUsername() {
        String username = getProperty("username");
        return username;
    }

    public static String getPassword() {
        String password = getProperty("password");
        return password;
    }

}

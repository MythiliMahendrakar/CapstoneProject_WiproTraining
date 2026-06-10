package com.blazedemo.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    Properties prop;

    public ConfigReader() {
        try {
            FileInputStream fis =
                    new FileInputStream("src/test/resources/config.properties");

            prop = new Properties();
            prop.load(fis);

        } catch (IOException e) {
            throw new RuntimeException("Config file not found or unreadable", e);
        }
    }

    public String getUrl() {
        return prop.getProperty("url");
    }

    public String getBrowser() {
        return prop.getProperty("browser");
    }
}
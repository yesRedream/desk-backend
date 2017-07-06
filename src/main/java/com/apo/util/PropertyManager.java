package com.apo.util;


import java.io.IOException;
import java.util.Properties;

/**
 * Created by Andrii Pohrebniak andrii.pohrebniak@gmail.com on 06/07/2017.
 */
public class PropertyManager {
    private Properties properties;

    public PropertyManager(String fileName) {
        this.properties = new Properties();
        try {
            properties.load(this.getClass().getResourceAsStream(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String propertyName) {
        return properties.getProperty(propertyName);
    }
}

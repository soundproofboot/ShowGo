package com.showgo.util;
import java.io.*;
import java.util.*;

/**
 * Interface for loading properties files. Based on interface from Madison College's Adv Java
 *
 * @throws IOException - when the properties file cannot be read
 *
 * @author Eric Knapp
 * @author Paula Waite
 */
public interface PropertiesLoader{

    default Properties loadProperties(String propertiesFilePath) throws IOException {
        Properties properties = new Properties();
        properties.load(this.getClass().getResourceAsStream(propertiesFilePath));
        return properties;
    }
}
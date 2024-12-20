package com.example.zo_login.config;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class UTF8ResourceBundle {
    public static ResourceBundle getBundle(String baseName, String locale) {
        try {

            String fileName = baseName + "_" + locale + ".properties";
            InputStream stream = UTF8ResourceBundle.class.getClassLoader().getResourceAsStream("messages_vi_vn.properties");
            if (stream == null) {
                throw new RuntimeException("Cannot find properties file: " + fileName);
            }

            // Đọc file với UTF-8 encoding
            return new PropertyResourceBundle(new InputStreamReader(stream, StandardCharsets.UTF_8));
        } catch (Exception e) {
            throw new RuntimeException("Cannot load UTF-8 ResourceBundle: " + e.getMessage(), e);
        }
    }
}

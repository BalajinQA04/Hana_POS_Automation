package com.hanapos.utilities;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.Properties;

public class AllureEnvironmentWriter {
    public static void setEnvironmentDetails(String outputDir, Map<String, String> envData) {
        try (OutputStream output = new FileOutputStream(outputDir + "/environment.properties")) {
            Properties prop = new Properties();
            for (Map.Entry<String, String> entry : envData.entrySet()) {
                prop.setProperty(entry.getKey(), entry.getValue());
            }
            prop.store(output, "Allure Environment Properties");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


package com.morris.pattern.simplefactory.v3;

import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {

    private PropertiesUtil() {

    }

    public static String getChartType() {

        Properties properties = new Properties();
        try {
            properties.load(PropertiesUtil.class.getClassLoader().getResourceAsStream("config.properties"));
            return properties.getProperty("chartType");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

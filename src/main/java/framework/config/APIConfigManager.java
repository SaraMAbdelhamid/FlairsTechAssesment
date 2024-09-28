package framework.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class APIConfigManager {

    private final Properties properties;

    public APIConfigManager() {
        properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return;
            }
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    public String getBaseURL() {
        return properties.getProperty("APIBASEURL");
    }

}

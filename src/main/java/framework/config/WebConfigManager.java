package framework.config;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class WebConfigManager {
    private final Properties properties;

    public WebConfigManager() {
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

    public String getBrowser() {
        return properties.getProperty("BROWSER");
    }

    public String getBaseUrl() {
        return properties.getProperty("BASEURL");
    }
    public long getWaitDuration() {
        return Long.parseLong(properties.getProperty("WAIT_DURATION"));
    }

    public String getRemoteBrowserURL(){ return properties.getProperty("REMOTE_BROWSER_URL");}

}

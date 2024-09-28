package framework.drivers;

import framework.base.DriverManager;
import framework.config.WebConfigManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class RemoteDriverManager implements DriverManager {

    @Override
    public  WebDriver createDriver() {
        WebDriver driver = null;
        String gridUrl = new WebConfigManager().getRemoteBrowserURL(); // URL to the Selenium Grid Hub
        DesiredCapabilities capabilities = new DesiredCapabilities();
        try {
            driver = new RemoteWebDriver(new URL(gridUrl), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return driver;
    }
}

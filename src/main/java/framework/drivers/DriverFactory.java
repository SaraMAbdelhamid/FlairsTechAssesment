package framework.drivers;



import framework.base.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {
    public static WebDriver createDriver(String browserName) {
        DriverManager driverManager = switch (browserName.toLowerCase()) {
            case "chrome" -> new ChromeDriverManager();
            case "firefox" -> new FirefoxDriverManager();
            case "edge" -> new EdgeDriverManager();
            case "remote" -> new RemoteDriverManager();
            default -> throw new IllegalArgumentException("Browser not supported: " + browserName);
        };
        return driverManager.createDriver();
    }
}

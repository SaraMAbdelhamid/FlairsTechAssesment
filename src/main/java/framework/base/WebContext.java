package framework.base;

import framework.config.WebConfigManager;
import framework.drivers.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

public class WebContext {

    private WebDriver driver;
    @Before(value = "@WebLogin")
    public void setUp() {
        WebConfigManager configManager = new WebConfigManager();
        driver = DriverFactory.createDriver(configManager.getBrowser());
        driver.manage().window().maximize();
        driver.get(configManager.getBaseUrl());
    }
    public WebDriver getDriver(){
        return this.driver;
    }
    @After(value="@WebLogin")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }


}

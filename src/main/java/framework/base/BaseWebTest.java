package framework.base;

import framework.config.WebConfigManager;
import framework.drivers.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseWebTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebConfigManager configManager = new WebConfigManager();
        driver = DriverFactory.createDriver(configManager.getBrowser());
        driver.manage().window().maximize();
        driver.get(configManager.getBaseUrl());
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
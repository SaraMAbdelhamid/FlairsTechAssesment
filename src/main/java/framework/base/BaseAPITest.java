package framework.base;

import framework.clients.RestAssuredClient;
import framework.config.APIConfigManager;
import framework.config.WebConfigManager;
import framework.drivers.DriverFactory;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseAPITest {
    protected RestAssuredClient client;
    @BeforeMethod
    public void setUp() {
        APIConfigManager config = new APIConfigManager();
        client = new RestAssuredClient();
        client.buildRequestSpecification(config);
    }

}
package framework.drivers;


import framework.base.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class EdgeDriverManager implements DriverManager {

    @Override
    public WebDriver createDriver() {
        return new EdgeDriver();
    }
}
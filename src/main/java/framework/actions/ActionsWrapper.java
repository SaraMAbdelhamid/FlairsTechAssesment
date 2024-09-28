package framework.actions;

import framework.config.WebConfigManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ActionsWrapper  {
    private final Actions actions;
   WebDriverWait wait;
   WebDriver driver;

    public ActionsWrapper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(new WebConfigManager()
                .getWaitDuration())); // Default wait time of 10 seconds
        this.actions = new Actions(driver);

    }

    // Wait for an element to be clickable and then click it
    public void clickWhenClickable(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }

    public void waitForElementVisibility(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public int countElementsFound(By locator){
        waitForElementVisibility(locator);
        List<WebElement> elements = driver.findElements(locator);
        return elements.size();
    }


    // Wait for an element to be visible and then enter text
    public void enterTextWhenVisible(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
    }

    // Wait for an element to be visible and then perform hover action
    public void hoverOverElement(By locator) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        actions.moveToElement(element).perform();
    }

    // Wait for an element to be visible and then perform a right-click action
    public void rightClickWhenVisible(By locator) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        actions.contextClick(element).perform();
    }

    // Wait for an element to be present and then retrieve its text
    public String getElementText(By locator) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return element.getText();
    }

    // Wait for an element to be visible and then check if it is selected
    public boolean isElementSelected(By locator) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return element.isSelected();
    }

    // Wait for an element to be visible and then check if it is enabled
    public boolean isElementEnabled(By locator) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return element.isEnabled();
    }

    // Perform a drag-and-drop action
    public void dragAndDrop(By sourceLocator, By targetLocator) {
        WebElement sourceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(sourceLocator));
        WebElement targetElement = wait.until(ExpectedConditions.visibilityOfElementLocated(targetLocator));
        actions.dragAndDrop(sourceElement, targetElement).perform();
    }

    // Wait for an element to be visible and then perform a double-click action
    public void doubleClickWhenVisible(By locator) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        actions.doubleClick(element).perform();
    }
}


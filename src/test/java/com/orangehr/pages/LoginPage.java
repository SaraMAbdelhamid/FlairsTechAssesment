package com.orangehr.pages;

import framework.actions.ActionsWrapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class LoginPage {
    private final ActionsWrapper actionWrapper;

    private final By usernameField = By.xpath("//input[@name='username']");
    private final By passwordField = By.xpath("//input[@name='password']");
    private final By loginButton = By.xpath("//button[normalize-space()='Login']");

    public LoginPage(WebDriver driver) {
        this.actionWrapper = new ActionsWrapper(driver);
    }


    public void fillUserNameField(String username){
        actionWrapper.enterTextWhenVisible(usernameField, username);
    }
    public void fillPasswordField(String password) {
        actionWrapper.enterTextWhenVisible(passwordField, password);
    }
    public void pressLoginButton(){
        actionWrapper.clickWhenClickable(loginButton);
    }

}

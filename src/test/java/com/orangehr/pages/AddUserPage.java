package com.orangehr.pages;

import framework.actions.ActionsWrapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class AddUserPage {
    private final ActionsWrapper actionWrapper;
    private final By employeeNameField = By.xpath("//div[label='Employee Name']/following-sibling::div//input");
    private final By dropDownList= By.xpath("//div[@role='listbox']//span");
    private final By dropDownSelection= By.xpath("//div[@role='listbox']//span[1]");
    private final By userNameField = By.xpath("//div[label='Username']/following-sibling::div//input");
    private final By passwordField = By.xpath("//div[label='Password']/following-sibling::div//input");
    private final By userRoleArrow = By.xpath("//div[label='User Role']/following-sibling::div//i");
    private final By statusArrow = By.xpath("//div[label='Status']/following-sibling::div//i");



    private final By confirmPasswordField = By.xpath("//div[label='Confirm Password']/following-sibling::div//input");
    private final By saveButton = By.xpath("//button[normalize-space()='Save']");

    public AddUserPage(WebDriver driver) {
        this.actionWrapper = new ActionsWrapper(driver);
    }


    public void fillEmployeeName(String employeeName){
        actionWrapper.enterTextWhenVisible(employeeNameField,employeeName);
    }
    public String getAListedEmployee(){
       return actionWrapper.getElementText(dropDownList);
    }
    public void fillUserName(String userName){
        actionWrapper.enterTextWhenVisible(userNameField,userName);
    }
    public void fillPassword(String password){
        actionWrapper.enterTextWhenVisible(passwordField,password);
    }
    public void fillConfirmPassword(String password){
        actionWrapper.enterTextWhenVisible(confirmPasswordField,password);
    }
    public void expandUserRoleDropDown(){
        actionWrapper.clickWhenClickable(userRoleArrow);
    }
    public void selectAnEmployeeName(){
        actionWrapper.clickWhenClickable(dropDownSelection);
    }
    public void selectAUserRole(){
        actionWrapper.clickWhenClickable(dropDownSelection);
    }

    public void expandUserStatusDropDown(){
        actionWrapper.clickWhenClickable(statusArrow);
    }

    public void selectAStatus(){
        actionWrapper.clickWhenClickable(dropDownSelection);
    }



    public void clickOnSaveButton(){
        actionWrapper.clickWhenClickable(saveButton);
    }

}

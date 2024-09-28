package com.orangehr.pages;

import framework.actions.ActionsWrapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class DashboardPage {
    private final ActionsWrapper actionWrapper;

    private final By adminTab = By.xpath("//a[@href='/web/index.php/admin/viewAdminModule']");
    private final By tableRecords = By.xpath("//div[@class='oxd-table-card']");
    private final By addButton = By.xpath("//button[normalize-space()='Add']");

    private final By confirmDelete = By.xpath("//button[normalize-space()='Yes, Delete']");
    private final By searchButton = By.xpath("//button[normalize-space()='Search']");

    private final By trashButton = By.xpath("//i[@class='oxd-icon bi-trash']");

    public DashboardPage(WebDriver driver) {
        this.actionWrapper = new ActionsWrapper(driver);
    }


    public void clickOnAdminTab(){
        actionWrapper.clickWhenClickable(adminTab);
    }
    public int getTableRecords() {
       return actionWrapper.countElementsFound(tableRecords);
    }
    public void clickOnAddButton(){
        actionWrapper.clickWhenClickable(addButton);
    }

    public void confirmDeleteUser(){
        actionWrapper.clickWhenClickable(confirmDelete);
    }

    public void clickOnSearchButton(){
        actionWrapper.clickWhenClickable(searchButton);
    }

    public void clickOnTrashButton(){
        actionWrapper.clickWhenClickable(trashButton);
    }

}

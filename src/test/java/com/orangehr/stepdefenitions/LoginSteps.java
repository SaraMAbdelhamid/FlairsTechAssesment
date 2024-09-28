package com.orangehr.stepdefenitions;

import framework.base.WebContext;
import framework.readers.DataReader;
import framework.readers.JsonReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import com.orangehr.pages.AddUserPage;
import com.orangehr.pages.DashboardPage;
import com.orangehr.pages.LoginPage;
import org.testng.Assert;

import java.io.IOException;
import java.util.Map;

import static framework.utils.JsonUtils.extractFromJson;
import static framework.utils.JsonUtils.toJson;

public class LoginSteps{
    WebContext context;
     LoginPage loginPage;
    DashboardPage dashboardPage;
    AddUserPage addUserPage;
    int numberOfRecords;
    String data;
    DataReader reader;


    public LoginSteps(WebContext context){
        this.context = context;
    }
    @Given("the user navigates to the login page")
    public void userNavigatesToTheLoginPage() {
        this.loginPage = new LoginPage(context.getDriver());
    }

    @Given("the user has valid credentials")
    public void userLoadsCredentialsAndData() throws IOException {
        this.reader = new JsonReader();
        data = toJson(reader.readData("test-data.json"));
    }
    @When("the user enters the username")
    public void userEntersUserName() {
        loginPage.fillUserNameField("Admin");
    }
    @When("fills the password field")
    public void userEntersPassword() {
        loginPage.fillPasswordField("admin123");
    }
    @When("clicks on login button")
    public void userClicksLogin(){
        loginPage.pressLoginButton();
        this.dashboardPage = new DashboardPage(context.getDriver());
        this.addUserPage = new AddUserPage(context.getDriver());
    }
    @Then("clicks on Admin Tab")
    public void userClicksOnAdminTab() {
        this.dashboardPage.clickOnAdminTab();
    }
    @Then("He should be able to sees Users records")
    public void userShouldSeeUsersRecords() {
        this.numberOfRecords = dashboardPage.getTableRecords();
    }

    @Given("the user clicks on add users button")
    public void userClicksOnAddUsersButton() {
        dashboardPage.clickOnAddButton();
    }

    @And("fills in all the user details")
    public void fillsInAllTheUserDetails() throws InterruptedException {

        addUserPage.fillUserName(extractFromJson(data,"web-test-data.userNameToCreate"));
        addUserPage.fillEmployeeName("a");
        addUserPage.selectAnEmployeeName();
        addUserPage.fillPassword(extractFromJson(data,"web-test-data.passwordToCreate"));
        addUserPage.fillConfirmPassword(extractFromJson(data,"web-test-data.passwordToCreate"));
        addUserPage.expandUserStatusDropDown();
        addUserPage.selectAStatus();
        addUserPage.expandUserRoleDropDown();
        addUserPage.selectAUserRole();
    }

    @And("clicks on save")
    public void clicksOnSave() {
        addUserPage.clickOnSaveButton();
    }

    @Then("number of records should be increased by one")
    public void numberOfRecordsShouldBeIncreasedByOne() {
       // dashboardPage.clickOnAdminTab();
        Assert.assertEquals(dashboardPage.getTableRecords(),numberOfRecords+1);
    }

    @Then("created user can be searched for")
    public void createdUserCanBeSearchedFor() {
        addUserPage.fillUserName(extractFromJson(data,"web-test-data.userNameToCreate"));
        dashboardPage.clickOnSearchButton();
    }

    @And("can be deleted after confirming the delete")
    public void canBeDeletedAfterConfirmingTheDelete() {
        dashboardPage.clickOnTrashButton();
        dashboardPage.confirmDeleteUser();
    }

    @Then("the records should be reduced by one")
    public void theRecordsShouldBeReducedByOne() {
        dashboardPage.clickOnAdminTab();
        Assert.assertEquals(dashboardPage.getTableRecords(),numberOfRecords);
    }
}

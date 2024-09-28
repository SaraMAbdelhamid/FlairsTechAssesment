package tests;
import framework.base.BaseWebTest;
import framework.readers.DataReader;
import framework.readers.JsonReader;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.orangehr.pages.AddUserPage;
import com.orangehr.pages.DashboardPage;
import com.orangehr.pages.LoginPage;

import java.io.IOException;

import static framework.utils.JsonUtils.extractFromJson;
import static framework.utils.JsonUtils.toJson;

public class LoginTest extends BaseWebTest {
    String data;
    DataReader reader;
    int numberOfRecords;

    @Test
    public void testLogin() throws IOException {
        this.reader = new JsonReader();
        data = toJson(reader.readData("test-data.json"));
        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboardPage = new DashboardPage(driver);
        AddUserPage addUserPage = new AddUserPage(driver);
        loginPage.fillUserNameField("Admin");
        loginPage.fillPasswordField("admin123");
        loginPage.pressLoginButton();
        dashboardPage.clickOnAdminTab();
        numberOfRecords = dashboardPage.getTableRecords();
        dashboardPage.clickOnAddButton();
        addUserPage.fillUserName(extractFromJson(data,"web-test-data.userNameToCreate"));
        addUserPage.fillEmployeeName("a");
        addUserPage.selectAnEmployeeName();
        addUserPage.fillPassword(extractFromJson(data,"web-test-data.passwordToCreate"));
        addUserPage.fillConfirmPassword(extractFromJson(data,"web-test-data.passwordToCreate"));
        addUserPage.expandUserStatusDropDown();
        addUserPage.selectAStatus();
        addUserPage.expandUserRoleDropDown();
        addUserPage.selectAUserRole();
        addUserPage.clickOnSaveButton();
        //dashboardPage.clickOnAdminTab();
        Assert.assertEquals(dashboardPage.getTableRecords(),numberOfRecords+1);
        addUserPage.fillUserName(extractFromJson(data,"web-test-data.userNameToCreate"));
        dashboardPage.clickOnSearchButton();
        dashboardPage.clickOnTrashButton();
        dashboardPage.confirmDeleteUser();
        dashboardPage.clickOnAdminTab();
        Assert.assertEquals(dashboardPage.getTableRecords(),numberOfRecords);
    }
}

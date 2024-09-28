package com.orangehr.stepdefenitions;

import com.orangehr.models.Candidate;
import framework.base.ApiContext;
import framework.readers.DataReader;
import framework.readers.JsonReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.Cookies;
import io.restassured.response.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static framework.utils.JsonUtils.extractFromJson;
import static framework.utils.JsonUtils.toJson;

public class ApiSteps {
    ApiContext context;
    String data;
    DataReader reader;
    Cookies cookies;
    Map<String,String> map=new HashMap<String,String>();
    Candidate candidate = new Candidate();
    Response response;
    String candidateId;

    String token;
    public ApiSteps(ApiContext context){
        this.context = context;
    }

    @Given("the user navigates to the login page to acquire token and session cookies")
    public void userNavigatesToTheLoginPage() throws IOException {
        this.reader = new JsonReader();
        data = toJson(reader.readData("test-data.json"));
         response = context.getClient().get("auth/login");
    }

    @Then("the user has valid session cookies and token")
    public void theUserHasValidSessionCookiesAndToken() {
        cookies = context.getClient().getResponseCookies(response);
        token = context.getClient().extractFromResponseBodyAsString(response,"token=\"&quot;","&quot;\"");
    }

    @When("the user logins using the credentials and acquired token")
    public void theUserLoginsUsingTheCredentialsAndAcquiredToken() {
        map.put("username","Admin");
        map.put("password","admin123");
        map.put("_token",token);
        response =context.getClient().post("auth/validate",cookies,map);
    }

    @Then("access should be granted")
    public void accessShouldBeGranted() {
        cookies = context.getClient().getResponseCookies(response);
    }

    @And("user should be able to fetch all candidates")
    public void userShouldBeAbleToFetchAllCandidates() {
        response = context.getClient().get("api/v2/recruitment/candidates",cookies);
    }

    @And("should be able to create a new candidate")
    public void shouldBeAbleToCreateANewCandidate() {
        candidate.setFirstName(extractFromJson(data,"api-test-data.firstName"));
        candidate.setMiddleName(extractFromJson(data,"api-test-data.middleName"));
        candidate.setLastName(extractFromJson(data,"api-test-data.lastName"));
        candidate.setEmail(extractFromJson(data,"api-test-data.email"));
        response = context.getClient().post("api/v2/recruitment/candidates",cookies,candidate);
        String candidate = response.body().asString();
        candidateId = extractFromJson(candidate,"data.id");
    }

    @Then("He should be able to delete the created candidate using its id")
    public void heShouldBeAbleToDeleteTheCreatedCandidateUsingItsId() {
        context.getClient().delete("api/v2/recruitment/candidates",cookies,"{\"ids\":["+candidateId+"]}");
    }
}

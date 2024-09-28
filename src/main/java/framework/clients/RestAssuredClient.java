package framework.clients;



import framework.config.APIConfigManager;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


import java.util.Map;

import static io.restassured.RestAssured.given;


public class RestAssuredClient {

    RequestSpecification spec;

    public void buildRequestSpecification(APIConfigManager config) {
        this.spec =  new RequestSpecBuilder().setBaseUri(config.getBaseURL()).build();
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    public Response get(String endpoint,  Cookies cookies) {
        return given().spec(this.spec).cookies(cookies).get(endpoint);
    }
    public Response get(String endpoint) {
        return given().spec(this.spec).get(endpoint);
    }

    public Response post(String endpoint, Cookies cookies,Map<String, ?> formParams ) {
        return given().spec(this.spec).cookies(cookies).formParams(formParams).post(endpoint);
    }
    public Response post(String endpoint,  Cookies cookies ,Object body) {
        return given().spec(this.spec).contentType("application/json").cookies(cookies).body(body).post(endpoint);
    }
    public Response delete(String endpoint, Cookies cookies,String body) {
        return given().spec(this.spec).contentType("application/json").body(body).cookies(cookies).delete(endpoint);
    }
    public String getResponseBodyAsString(Response response) {return response.getBody().asString();}
    public String extractFromResponseBodyAsString(Response response,String leftPattern, String rightPattern) {
        String[] texts = response.getBody().asString().split(leftPattern);
         return texts[1].split(rightPattern)[0];
    }
    public Cookies getResponseCookies(Response response) {return response.getDetailedCookies();}
}


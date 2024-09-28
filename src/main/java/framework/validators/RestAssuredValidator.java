package framework.validators;


import io.restassured.response.Response;

import static framework.utils.JsonUtils.extractFromJson;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class RestAssuredValidator {

    // Validate the response status code
    public static void validateStatusCode(Response response, int expectedStatusCode) {
        assertThat("Status code", response.statusCode(), equalTo(expectedStatusCode));
    }

    // Validate the response time
    public static void validateResponseTime(Response response, long maxResponseTimeMs) {
        assertThat("Response time", response.time(), lessThan(maxResponseTimeMs));
    }

    // Validate a JSON field's value
    public static void validateJsonField(Response response, String jsonPath, Object expectedValue) {
        assertThat("JSON field validation",
                extractFromJson(response.asString(),jsonPath)
                , equalTo(expectedValue));
    }

    // Validate that a JSON field exists
    public static void validateFieldExists(Response response, String jsonPath) {
        assertThat("Field exists",
                extractFromJson(response.asString(),jsonPath),
                notNullValue());
    }

    // Validate that the response body contains a specific string
    public static void validateBodyContains(Response response, String expectedString) {
        assertThat("Response body contains string", response.asString().contains(expectedString));
    }

    // Validate that a header is present and matches the expected value
    public static void validateHeader(Response response, String headerName, String expectedValue) {
        assertThat("Header validation", response.getHeader(headerName), equalTo(expectedValue));
    }

    // Validate that a header is present
    public static void validateHeaderExists(Response response, String headerName) {
        assertThat("Header exists", response.getHeader(headerName), notNullValue());
    }
    // Validate that the response body is not null
    public static void validateBodyNotNull(Response response) {
        assertThat("Response body is not null", response.getBody().asString(), notNullValue());
    }
    // Validate the content type of the response
    public static void validateContentType(Response response, String expectedContentType) {
        assertThat("Content type validation", response.getContentType(), equalTo(expectedContentType));
    }
}


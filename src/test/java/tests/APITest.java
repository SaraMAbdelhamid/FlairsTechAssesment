package tests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import framework.base.ApiContext;
import framework.base.BaseAPITest;
import framework.readers.DataReader;
import framework.readers.JsonReader;
import framework.utils.JsonUtils;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import com.orangehr.models.Candidate;
import org.testng.annotations.Test;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static framework.utils.JsonUtils.extractFromJson;
import static framework.utils.JsonUtils.toJson;


@JsonIgnoreProperties(ignoreUnknown = true)
public class APITest extends BaseAPITest {
    String data;
    DataReader reader;
    Cookies cookies;
    Map<String,String> map=new HashMap<String,String>();
    Candidate candidate = new Candidate();
    @Test
    public void testAPI() throws IOException {
        this.reader = new JsonReader();
        data = toJson(reader.readData("test-data.json"));
        Response response = client.get("auth/login");
        cookies = client.getResponseCookies(response);
        String token = client.extractFromResponseBodyAsString(response,"token=\"&quot;","&quot;\"");
        map.put("username","Admin");
        map.put("password","admin123");
        map.put("_token",token);
        Response response2 =client.post("auth/validate",cookies,map);
        cookies = client.getResponseCookies(response2);
        Response response3 = client.get("api/v2/recruitment/candidates",cookies);
        client.getResponseBodyAsString(response3);

        candidate.setFirstName(extractFromJson(data,"api-test-data.firstName"));
        candidate.setMiddleName(extractFromJson(data,"api-test-data.middleName"));
        candidate.setLastName(extractFromJson(data,"api-test-data.lastName"));
        candidate.setEmail(extractFromJson(data,"api-test-data.email"));
        Response response4 = client.post("api/v2/recruitment/candidates",cookies,candidate);
        String candidate = response4.body().asString();
        String candidateId = extractFromJson(candidate,"data.id");
        client.delete("api/v2/recruitment/candidates",cookies,"{\"ids\":["+candidateId+"]}");
    }

}

package framework.base;

import framework.clients.RestAssuredClient;
import framework.config.APIConfigManager;
import io.cucumber.java.Before;

public class ApiContext {

    protected RestAssuredClient client;
    @Before(value="@APILogin")
    public void setUp() {
        APIConfigManager config = new APIConfigManager();
        client = new RestAssuredClient();
        client.buildRequestSpecification(config);
    }
    public RestAssuredClient getClient(){
        return this.client;
    }
}

package framework.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.path.json.JsonPath;

import java.io.IOException;

public class JsonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T fromJson(String json, Class<T> classType) throws IOException {
        return objectMapper.readValue(json, classType);
    }

    public static String toJson(Object object) throws IOException {
        return objectMapper.writeValueAsString(object);
    }

    public static String extractFromJson(String jsonString,String jsonPath ){
        return new JsonPath(jsonString).getString(jsonPath);
    }

}

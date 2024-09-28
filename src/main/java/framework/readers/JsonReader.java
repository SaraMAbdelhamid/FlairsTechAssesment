package framework.readers;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class JsonReader implements DataReader{

    @Override
    public Map<Object, Object> readData(String filePath) throws IOException {
        InputStream input = getClass().getClassLoader().getResourceAsStream(filePath);
        return new ObjectMapper().readValue(input, Map.class);
    }


}

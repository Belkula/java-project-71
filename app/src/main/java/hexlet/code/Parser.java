package hexlet.code;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.fasterxml.jackson.core.type.TypeReference;



public class Parser {
	
private static ObjectMapper TypeMapper(String filePath) {
    ObjectMapper mapper = null;
    String extension = filePath.substring(filePath.length() - 4);
    switch (extension) {
        case "json":
            mapper = new ObjectMapper();
            break;
        case "yaml":
        case ".yml":
            mapper = new YAMLMapper();
            break;
        default:
            break;
    }
    return mapper;
}
	
	    public static TreeMap<String, Object> readFile(String filePath) throws IOException {
        TreeMap<String, Object> finMap;
        ObjectMapper mapper = TypeMapper(filePath);

        String strFromFile = new String(Files.readAllBytes(Paths.get(filePath)));
        finMap = mapper.readValue(strFromFile, new TypeReference<>() { });

        for (Map.Entry<String, Object> entry : finMap.entrySet()) {
            if (entry.getValue() == null) {
                entry.setValue("null");
            }
        }

        return finMap;
    }

}

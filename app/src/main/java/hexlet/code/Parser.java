package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;

public class Parser {

    private static ObjectMapper getObjectMapper(String filePath) {
        ObjectMapper mapper = null;
		String extension = filePath.substring(filePath.length() - 4);
		switch (extension) {
			case "json":
				mapper = new ObjectMapper();
				break;
			case "yml":
			case "yaml":
				mapper = new YAMLMapper();
				break;
			default:
			break;
		}

        return mapper;
    }

    public static TreeMap<String, Object> readFile(String filePath) throws IOException {
        TreeMap<String, Object> TreeMapout;
        ObjectMapper mapper = getObjectMapper(filePath);

        String strFromFile = new String(Files.readAllBytes(Paths.get(filePath)));
        TreeMapout = mapper.readValue(strFromFile, new TypeReference<>() { });

        for (Map.Entry<String, Object> entry : TreeMapout.entrySet()) {
            if (entry.getValue() == null) {
                entry.setValue("null");
            }
        }

        return TreeMapout;
    }

}
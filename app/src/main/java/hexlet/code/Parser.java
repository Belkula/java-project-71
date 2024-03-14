package hexlet.code;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Parser {
    public static Map<String, String> readJson(String filePath) throws IOException, ParseException {

        Object jsobj = new JSONParser().parse(new FileReader(filePath));
        JSONObject jsonObject = (JSONObject) jsobj;
        Map<String, String> jsonMap = new HashMap<>();

        for (Object key : jsonObject.keySet()) {
            String keyStr = (String) key;
            Object keyValue = jsonObject.get(keyStr);
            jsonMap.put(keyStr, keyValue.toString());
        }
        return jsonMap;
    }
	
public static Map<String, String> readYaml(String filePath) throws IOException, ParseException {

        File file = new File(filePath);
        YAMLFactory yamlFactory = new YAMLFactory();
        YAMLParser yamlParser = yamlFactory.createParser(file);
        JsonToken jsonToken = yamlParser.nextToken();
        Map<String, String> yamlMap = new HashMap<>();
        String fieldName = "";
        while (jsonToken != null) {
            switch (jsonToken) {
                case FIELD_NAME: fieldName = yamlParser.getText();
                    break;
                case START_OBJECT: 
                    break;
                case END_OBJECT: 
                    break;
                case START_ARRAY: 
                    break;
                case END_ARRAY: 
                    break;

                default:
                    if (!fieldName.equals("")) {
                        yamlMap.put(fieldName, yamlParser.getText());
                        fieldName = "";
                    }
                    break;
            }
            jsonToken = yamlParser.nextToken();
        }

        yamlParser.close();
        return yamlMap;
    }

public static String mapDiff(Map<String, String> map1, Map<String, String> map2) throws ParseException {
    Map<String, String> keysFromBothMaps = new TreeMap<>();
    keysFromBothMaps.putAll(map1);
    keysFromBothMaps.putAll(map2);
    StringBuilder output = new StringBuilder("{\n");

    for (Map.Entry<String, String> entry : keysFromBothMaps.entrySet()) {
        String map1Value = map1.get(entry.getKey());
        String map2Value = map2.get(entry.getKey());

        if (map1Value != null) {
            if (map2Value == null) {
                output.append("- ").append(entry.getKey()).append(": ").append(map1Value).append("\n");
            } else if (map1Value.equals(map2Value)) {
                output.append("  ").append(entry.getKey()).append(": ").append(map1Value).append("\n");
            } else {
                output.append("- ").append(entry.getKey()).append(": ").append(map1Value).append("\n");
                output.append("+ ").append(entry.getKey()).append(": ").append(map2Value).append("\n");
            }
        } else {
            output.append("+ ").append(entry.getKey()).append(": ").append(map2Value).append("\n");
        }
    }

    output.append("}\n");

    return output.toString();
}

}

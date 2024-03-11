package hexlet.code;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Differ {
    private static Map<String, String> readJson(String filePath) throws IOException, ParseException {

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

    public static String mapDiff(Map<String, String> map1, Map<String, String> map2) throws ParseException {

        Map<String, String> keysFromBothJson = new TreeMap<>();
        keysFromBothJson.putAll(map1);
        keysFromBothJson.putAll(map2);
        StringBuilder output = new StringBuilder("{\n");

        for (Map.Entry<String, String> entry : keysFromBothJson.entrySet()) {
            String map1Value = map1.get(entry.getKey());
            String map2Value = map2.get(entry.getKey());

            if (map1Value != null) {
                if (map2Value == null) {
                    output.append("- " + entry.getKey() + ": " + map1Value + "\n");
                } else if (map1Value.equals(map2Value)) {
                    output.append("  " + entry.getKey() + ": " + map1Value + "\n");
                } else if (!map1Value.equals(map2Value)) {
                    output.append("- " + entry.getKey() + ": " + map1Value + "\n");
                    output.append("+ " + entry.getKey() + ": " + map2Value + "\n");
                }
            } else {
                if (map2Value != null) {
                    output.append("+ " + entry.getKey() + ": " + map2Value + "\n");
                }
            }
        }

        output.append("}\n");

        return output.toString();
    }

    public static String generate(String filePath1, String filePath2) throws IOException, ParseException {

        Map<String, String> jsonMap1 = readJson(filePath1);
        Map<String, String> jsonMap2 = readJson(filePath2);
        return mapDiff(jsonMap1, jsonMap2);
    }
}

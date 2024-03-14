package hexlet.code;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.TreeMap;
import java.util.Map;

public class Differ {
	
	public static String mapDiff(Map<String, Object> map1, Map<String, Object> map2) throws ParseException {
    Map<String, Object> keysFromBothMaps = new TreeMap<>();
    keysFromBothMaps.putAll(map1);
    keysFromBothMaps.putAll(map2);
    StringBuilder output = new StringBuilder("{\n");

    for (Map.Entry<String, Object> entry : keysFromBothMaps.entrySet()) {
        Object map1Value = map1.get(entry.getKey());
        Object map2Value = map2.get(entry.getKey());

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
	
    public static String generate(String filePath1, String filePath2) throws IOException, ParseException {
	    TreeMap<String, Object> map1 = Parser.readFile(filePath1);
        TreeMap<String, Object> map2 = Parser.readFile(filePath2);
		return mapDiff(map1,map2);
    }
}

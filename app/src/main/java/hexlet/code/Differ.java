package hexlet.code;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Map;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws IOException, ParseException {
    Map<String, String> map1 = null;
    Map<String, String> map2 = null;

    String format1 = filePath1.substring(filePath1.length() - 4);
    String format2 = filePath2.substring(filePath2.length() - 4);

    if (format1.equals(format2)) {
        if (format1.equals("json")) {
            map1 = Parser.readJson(filePath1);
            map2 = Parser.readJson(filePath2);
        } else if (format1.equals("yaml")) {
            map1 = Parser.readYaml(filePath1);
            map2 = Parser.readYaml(filePath2);
        }

        if (map1 != null && map2 != null) {
            return Parser.mapDiff(map1, map2);
        } else {
            System.out.println("Failed to read maps from files.");
            return null;
        }
        } else {
            System.out.println("File formats are different.");
             return null;
        }
    }
}

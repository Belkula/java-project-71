package hexlet.code;

import java.util.List;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;
import hexlet.code.formatters.Json;

public class Styles {

    public static String formater(String format, List<Differs> output) {
        String result;
        switch (format) {
            case "json":
                result = Json.formatJson(output);
                break;
            case "plain":
                result = Plain.formatPlain(output);
                break;
            case "stylish":
            default:
                result = Stylish.formatStylish(output);
                break;
        }
        return result;
    }
}

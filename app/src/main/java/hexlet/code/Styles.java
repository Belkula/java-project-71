package hexlet.code;

import java.util.List;
import java.util.Map;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

public class Styles {

    public static String Formater(String format, List<Differs> output) {
        String result;
        switch (format) {
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

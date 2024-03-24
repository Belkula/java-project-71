import hexlet.code.Differ;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FormTest {

    @Test
    void testDefault() throws ParseException, IOException {
        String filePath1 = "src/test/resources/json/file1.json";
        String filePath2 = "src/test/resources/json/file2.json";

        String referenceStr = getReference("stylish");

        String output = null;
        try {
            output = Differ.generate(filePath1, filePath2, "");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        assertEquals(referenceStr, output);
    }

    @Test
    void testPlain() throws ParseException, IOException {
        String filePath1 = "src/test/resources/json/file1.json";
        String filePath2 = "src/test/resources/json/file2.json";

        String referenceStr = getReference("plain");

        String output = null;
        try {
            output = Differ.generate(filePath1, filePath2, "plain");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        assertEquals(referenceStr, output);
    }

    @Test
    void testJson() throws ParseException, IOException {
        String filePath1 = "src/test/resources/json/file1.json";
        String filePath2 = "src/test/resources/json/file2.json";

        String referenceStr = getReference("json");

        String output = null;
        try {
            output = Differ.generate(filePath1, filePath2, "json");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        assertEquals(referenceStr, output);
    }

    public String getReference(String style) {
        String result;
        switch (style) {
            case "json":
                result = "{"
                    + "\"key\":\"chars1\",\"status\":\"not Changed\",\"oldValue\":[\"a\",\"b\",\"c\"],\"new"
                    + "Value\":[\"a\",\"b\",\"c\"]},\n"
                    + "{" + "\"key\":\"chars2\",\"status\":\"updated\",\"old"
                    + "Value\":[\"d\",\"e\",\"f\"],\"newValue\":false},\n"
                    + "{" + "\"key\":\"checked\",\"status\":\"updated\",\"old"
                    + "Value\":false,\"newValue\":true},\n"
                    + "{" + "\"key\":\"default\",\"status\":\"updated\",\"old"
                    + "Value\":\"null\",\"newValue\":[\"value1\",\"value2\"]},\n"
                    + "{" + "\"key\":\"id\",\"status\":\"updated\",\"old"
                    + "Value\":45,\"newValue\":\"null\"},\n"
                    + "{" + "\"key\":\"key1\",\"status\":\"removed\",\"old"
                    + "Value\":\"value1\",\"newValue\":null},\n"
                    + "{" + "\"key\":\"key2\",\"status\":\"added\",\"old"
                    + "Value\":null,\"newValue\":\"value2\"},\n"
                    + "{" + "\"key\":\"numbers1\",\"status\":\"not Changed\",\"old"
                    + "Value\":[1,2,3,4],\"newValue\":[1,2,3,4]},\n"
                    + "{" + "\"key\":\"numbers2\",\"status\":\"updated\",\"old"
                    + "Value\":[2,3,4,5],\"newValue\":[22,33,44,55]},\n"
                    + "{" + "\"key\":\"numbers3\",\"status\":\"removed\",\"old"
                    + "Value\":[3,4,5],\"newValue\":null},\n"
                    + "{" + "\"key\":\"numbers4\",\"status\":\"added\",\"old"
                    + "Value\":null,\"newValue\":[4,5,6]},\n"
                    + "{" + "\"key\":\"obj1\",\"status\":\"added\",\"oldValue\":null,\"new"
                    + "Value\":{\"nestedKey\":\"value\",\"isNested\":true}},\n"
                    + "{" + "\"key\":\"setting1\",\"status\":\"updated\",\"old"
                    + "Value\":\"Some value\",\"newValue\":\"Another value\"},\n"
                    + "{" + "\"key\":\"setting2\",\"status\":\"updated\",\"old"
                    + "Value\":200,\"newValue\":300},\n"
                    + "{" + "\"key\":\"setting3\",\"status\":\"updated\",\"old"
                    + "Value\":true,\"newValue\":\"none\"" + "}";

                break;
            case "plain":
                result = "Property 'chars2' was updated. From [complex value] to false\n"
                    + "Property 'checked' was updated. From false to true\n"
                    + "Property 'default' was updated. From 'null' to [complex value]\n"
                    + "Property 'id' was updated. From 45 to 'null'\n"
                    + "Property 'key1' was removed\n"
                    + "Property 'key2' was added with value: 'value2'\n"
                    + "Property 'numbers2' was updated. From [complex value] to [complex value]\n"
                    + "Property 'numbers3' was removed\n"
                    + "Property 'numbers4' was added with value: [complex value]\n"
                    + "Property 'obj1' was added with value: [complex value]\n"
                    + "Property 'setting1' was updated. From 'Some value' to 'Another value'\n"
                    + "Property 'setting2' was updated. From 200 to 300\n"
                    + "Property 'setting3' was updated. From true to 'none'\n";
                break;
            case "stylish":
            default:
                result = "{\n"
                    + "  chars1: [a, b, c]\n"
                    + "- chars2: [d, e, f]\n"
                    + "+ chars2: false\n"
                    + "- checked: false\n"
                    + "+ checked: true\n"
                    + "- default: null\n"
                    + "+ default: [value1, value2]\n"
                    + "- id: 45\n"
                    + "+ id: null\n"
                    + "- key1: value1\n"
                    + "+ key2: value2\n"
                    + "  numbers1: [1, 2, 3, 4]\n"
                    + "- numbers2: [2, 3, 4, 5]\n"
                    + "+ numbers2: [22, 33, 44, 55]\n"
                    + "- numbers3: [3, 4, 5]\n"
                    + "+ numbers4: [4, 5, 6]\n"
                    + "+ obj1: {nestedKey=value, isNested=true}\n"
                    + "- setting1: Some value\n"
                    + "+ setting1: Another value\n"
                    + "- setting2: 200\n"
                    + "+ setting2: 300\n"
                    + "- setting3: true\n"
                    + "+ setting3: none\n"
                    + "}\n";
                break;
        }
        return result;
    }
}

import hexlet.code.Differ;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import java.io.IOException; 
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestForm {

    @Test
    void getformDiff() throws ParseException, IOException { 
        String filePath1 = "src/test/resources/json/file1.json";
        String filePath2 = "src/test/resources/json/file2.json";

        String referenceStr = "{\n"
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
			
        String output = null;
        try {
            output = Differ.generate(filePath1, filePath2, "stylish");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        assertEquals(referenceStr, output);
    }
}

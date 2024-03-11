import hexlet.code.Differ;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import java.io.IOException; 
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDiff {

    @Test
     void getMapsDifferencesTest() throws ParseException, IOException { 
        String filePath1 = "src/test/resources/json/file1.json";
        String filePath2 = "src/test/resources/json/file2.json";

        String referenceStr = "{\n"
                + "- follow: false\n"
                + "  host: hexlet.io\n"
                + "- proxy: 123.234.53.22\n"
                + "- timeout: 50\n"
                + "+ timeout: 20\n"
                + "+ verbose: true\n"
                + "}\n";

        String output = Differ.generate(filePath1, filePath2);
        assertEquals(output, referenceStr);
    }
}

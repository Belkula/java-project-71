package hexlet.code.formatters;

import hexlet.code.Differs;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class json {
    public static String formatJson(List<Differs> output) {
        ObjectMapper objectMapper = new ObjectMapper();
        StringBuilder outStr = new StringBuilder();

        try {
            int size = output.size();
            for (int i = 0; i < size; i++) {
                Differs differ = output.get(i);
                outStr.append(objectMapper.writeValueAsString(differ));
                if (i < size - 1) {
                    outStr.append(",\n");
                }
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return outStr.toString();
    }
}
